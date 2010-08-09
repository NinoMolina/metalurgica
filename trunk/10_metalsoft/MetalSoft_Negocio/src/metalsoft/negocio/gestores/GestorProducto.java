/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.PiezaDB;
import metalsoft.datos.dbobject.ProductoDB;
import metalsoft.datos.dbobject.Tipomaterial;
import metalsoft.negocio.access.AccessProducto;
import metalsoft.negocio.access.AccessTipoMaterial;
import metalsoft.negocio.produccion.TipoMaterial;
import metalsoft.negocio.ventas.Pieza;
import metalsoft.negocio.ventas.Producto;

/**
 *
 * @author Nino
 */
public class GestorProducto implements IBuscador{

    private metalsoft.datos.dbobject.PiezaDB[] piezasDB;
    private JList lstPiezas;
    private String descripcionProducto;
    private String nombreProducto;
    private String numeroProducto;
    private String precioUnitarioProducto;
    private ArrayList<Object[]> arlDatosDetalleProducto;
    private ArrayList arlIdsPiezasDetalleProducto;
    private long idProducto;
    public GestorProducto(){}

    public void buscarPiezas(final String valor)
    {
        if(valor.compareTo("")!=0) {
            final GestorProducto x=this;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                private HiloBuscarPieza hiloBuscarPieza;
                @Override
                public void run() {
                    hiloBuscarPieza=new HiloBuscarPieza();
                    hiloBuscarPieza.setVentana(x);
                    hiloBuscarPieza.setValor(valor);
                    hiloBuscarPieza.start();
                }
            }, 1500);
        }
    }

    public void setListaPiezas(JList lst)
    {
        lstPiezas=lst;
    }

    public PiezaDB buscarPiezaEnEncontradas(long id)
    {
        int elementos=piezasDB.length;
        PiezaDB p=null;
        for(int i=0;i<elementos;i++)
        {
            p=piezasDB[i];
            if(p.getIdpieza()==id)return p;
        }
        return null;
    }

    public JList getList() {
        return lstPiezas;
    }

    public void setBusqueda(Object[] obj) {
        piezasDB=(PiezaDB[]) obj;
    }

    public Pieza buscarPiezaParaDetalleProducto(long id) {
        PiezaDB pDB=buscarPiezaEnEncontradas(id);
        Tipomaterial tmDB=buscarTipoMaterial(pDB.getTipomaterial());
        Pieza p=Parser.parseToPieza(pDB);
        TipoMaterial tm=Parser.parseToTipoMaterial(tmDB);
        p.setTipoMaterial(tm);
        return p;
    }

    private Tipomaterial buscarTipoMaterial(long id) {
        return AccessTipoMaterial.buscarTipoMaterial(id);
    }

    public void setDescripcionProducto(String descripcion) {
        descripcionProducto=descripcion;
    }

    public void setNombreProducto(String nombre) {
        nombreProducto=nombre;
    }

    public void setNumeroProducto(String numero) {
        numeroProducto=numero;
    }

    public void setPrecioUnitarioProducto(String precioUnitario) {
        precioUnitarioProducto=precioUnitario;
    }

    public void setListaDetalle(ArrayList arlDatos,ArrayList arlIds) {
        arlDatosDetalleProducto=arlDatos;
        arlIdsPiezasDetalleProducto=arlIds;
    }

    public long registrarProducto() {
        Producto prod=new Producto();
        prod.setDescripcion(descripcionProducto);
        prod.setNombre(nombreProducto);
        prod.setNroProducto(Integer.parseInt(numeroProducto));
        prod.setPrecioUnitario(Float.parseFloat(precioUnitarioProducto));
        prod.setDetalle(crearDetalleProducto(prod));

        long result=-1;
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        try {

            cn = pg.concectGetCn();
            cn.setAutoCommit(false);

            long idProd=prod.guardar(prod,arlIdsPiezasDetalleProducto, cn);
            result=idProd;
            cn.commit();
            idProducto=result;

        } catch (Exception ex) {
            try {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    private ArrayList crearDetalleProducto(Producto prod)
    {
        ArrayList arlDetalle=new ArrayList();
        Iterator<Object[]> iter=arlDatosDetalleProducto.iterator();
        Object[] datos=null;
        while(iter.hasNext())
        {
            datos=iter.next();
            int cant=Integer.parseInt(String.valueOf(datos[0]));
            String desc=String.valueOf(datos[1]);
            arlDetalle.add(prod.crearDetalleProducto(cant, desc));
        }
        if(!arlDetalle.isEmpty())return arlDetalle;
        else return null;
    }

    public ProductoDB[] buscarProductos(String valor) {
        Connection cn=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return AccessProducto.findByNombreILIKE(valor,cn);
    }

    public ProductoDB buscarProductoDB(long idProducto) {
        ProductoDB db=null;
        Connection cn=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
            db=AccessProducto.findById(idProducto,cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }

    public ArrayList<ViewDetalleProducto> viewDetalleProducto(long idProducto) {
        Connection cn=null;
        ArrayList<ViewDetalleProducto> arl=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
            arl=AccessProducto.viewDetalleProducto(idProducto, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arl;
    }

}
