/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JComboBox;
import javax.swing.JList;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.EtapadeproduccionDB;
import metalsoft.negocio.ventas.EtapaDeProduccion;


/**
 *
 * @author Vicky
 */
public class GestorEtapaDeProduccion {

    private metalsoft.datos.dbobject.EtapadeproduccionDB[] etapaDeProduccionDB;
    private String nombre;
    private Timer horasMaquina;
    private Timer horasHombre;
    private Timer duracionEtapa;
    private Date fechaCreacion;
    private String unidadDeMedida;
    private HiloBuscarEtapaDeProduccion hiloBuscarPieza;

    public Timer getDuracionEtapa() {
        return duracionEtapa;
    }

    public void setDuracionEtapa(Timer duracionEtapa) {
        this.duracionEtapa = duracionEtapa;
    }

    public EtapadeproduccionDB[] getEtapaDeProduccionDB() {
        return etapaDeProduccionDB;
    }

    public void setEtapaDeProduccionDB(EtapadeproduccionDB[] etapaDeProduccionDB) {
        this.etapaDeProduccionDB = etapaDeProduccionDB;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Timer getHorasHombre() {
        return horasHombre;
    }

    public void setHorasHombre(Timer horasHombre) {
        this.horasHombre = horasHombre;
    }

    public Timer getHorasMaquina() {
        return horasMaquina;
    }

    public void setHorasMaquina(Timer horasMaquina) {
        this.horasMaquina = horasMaquina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public GestorEtapaDeProduccion(){}

    public void buscarEtapasDeProduccion(final String valor)
    {
        if(valor.compareTo("")!=0) {
            final GestorEtapaDeProduccion x=this;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                
                @Override
                public void run() {
                    hiloBuscarPieza=new HiloBuscarPieza();
                    hiloBuscarPieza.setVentana();
                    hiloBuscarPieza.setValor(valor);
                    hiloBuscarPieza.start();
                }
            }, 1500);
        }
    }

    
    public void setBusqueda(Object[] obj) {
        etapaDeProduccionDB=(EtapadeproduccionDB[]) obj;
    }

   /*
   HACER ESTE METODOOOO
    private MaquinaDB buscarTipoMaterial(long id) {
        return AccessMaquina.buscarMauina(id);
    }*/

    

    public long registrarEtapaDeProduccion() {
        EtapaDeProduccion prod=new EtapaDeProduccion();
        prod.setDuracionEstimadaXUnidMed(duracionEtapa);
        prod.setFechaCreacion(fechaCreacion);
        prod.setHorasHombre(horasHombre);
        prod.setHorasMaquina(horasMaquina);
        prod.setNombre(nombre);


        long result=-1;
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        try {

            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result= guardar(prod,arlIdsPiezasDetalleProducto, cn);
            
            cn.commit();
            

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
    public long modificarProducto() {
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
            eliminarDetalleProducto(arlDetalleAEliminar, cn);
            result=prod.modificar(idProducto,prod,filasDetalle, cn);

            cn.commit();
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

//    private ArrayList crearDetalleProducto(Producto prod)
//    {
//        ArrayList arlDetalle=new ArrayList();
//        Iterator<Object[]> iter=arlDatosDetalleProducto.iterator();
//        Object[] datos=null;
//        while(iter.hasNext())
//        {
//            datos=iter.next();
//            int cant=Integer.parseInt(String.valueOf(datos[0]));
//            String desc=String.valueOf(datos[1]);
//            arlDetalle.add(prod.crearDetalleProducto(cant, desc));
//        }
//        if(!arlDetalle.isEmpty())return arlDetalle;
//        else return null;
//    }

    private ArrayList crearDetalleProducto(Producto prod)
    {
        ArrayList arlDetalle=new ArrayList();
        Iterator<ViewDetalleProducto> iter=filasDetalle.iterator();
        arlIdsPiezasDetalleProducto=new ArrayList(filasDetalle.size());
        ViewDetalleProducto datos=null;
        while(iter.hasNext())
        {
            datos=iter.next();
            arlIdsPiezasDetalleProducto.add(datos.getIdPieza());
            int cant=datos.getCantidad();
            String desc=datos.getDescripcion();
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

    public void setDetalleAEliminar(ArrayList<ViewDetalleProducto> arlDetProdAEliminar) {
        this.arlDetalleAEliminar=arlDetProdAEliminar;
    }

    private void eliminarDetalleProducto(ArrayList<ViewDetalleProducto> arl, Connection cn) {
        String error="Error al eliminar el detalle de producto: ";
        boolean flag=false;
        if(!arl.isEmpty())
        {
            Iterator i=arl.iterator();
            ViewDetalleProducto view=null;
            int result=-1;
            while(i.hasNext())
            {
                view=(ViewDetalleProducto) i.next();
                result=DetalleProducto.eliminar(view.getIdDetalle(), cn);
                //si el resultado es menor o igual a cero entonces no se elimino el detalle.
                //se agrega a la cadena error el id del detalle y el nombre de la pieza
                if(result<=0)
                {
                    error+="\n id detalle: "+view.getIdDetalle()+", pieza: "+view.getNombrePieza();
                    flag=true;
                }
            }
        }
        if(flag)JOptionPane.showMessageDialog(null, error, "Error al eliminar Detalle Producto", JOptionPane.INFORMATION_MESSAGE, null);
    }

    public void setIdProducto(long idProducto) {
        this.idProducto=idProducto;
    }

    public void setListaDetalle(LinkedList<ViewDetalleProducto> filas) {
        filasDetalle=filas;
    }

    public JComboBox getCombo() {
        return null;
    }
}
