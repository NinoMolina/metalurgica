/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import metalsoft.datos.dbobject.ClienteDB;
import metalsoft.datos.dbobject.Condicioniva;
import metalsoft.datos.dbobject.DetalleproductoDB;
import metalsoft.datos.dbobject.ProductoDB;
import metalsoft.datos.dbobject.Tipomaterial;
import metalsoft.negocio.produccion.TipoMaterial;
import metalsoft.datos.dbobject.PiezaDB;
import metalsoft.negocio.ventas.DetalleProducto;
import metalsoft.negocio.ventas.Producto;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.compras.Responsable;
import metalsoft.negocio.rrhh.Domicilio;
import metalsoft.negocio.ventas.Cliente;
import metalsoft.negocio.ventas.CondicionIva;
import metalsoft.negocio.ventas.Pieza;
import metalsoft.util.ItemCombo;
/**
 *
 * @author Vicky
 */
public class Parser {

    public static TipoMaterial[] parseToTipomaterial(Tipomaterial[] tm) {
        if(tm==null)return null;

        TipoMaterial[] c=new TipoMaterial[tm.length];
        for(int i=0;i<tm.length;i++)
        {
            TipoMaterial x=new TipoMaterial();
            x.setNombre(tm[i].getNombre());
            x.setDescripcion(tm[i].getDescripcion());
            c[i]=x;
        }
        return c;
    }

    public static TipoMaterial parseToTipoMaterial(Tipomaterial x)
    {
        TipoMaterial tm=new TipoMaterial();
        tm.setDescripcion(x.getDescripcion());
        tm.setNombre(x.getNombre());
        return tm;
    }
    public static metalsoft.negocio.ventas.Pieza[] parseToPieza(PiezaDB[] tm) {
        if(tm==null)return null;

        metalsoft.negocio.ventas.Pieza[] c=new metalsoft.negocio.ventas.Pieza[tm.length];
        for(int i=0;i<tm.length;i++)
        {
            metalsoft.negocio.ventas.Pieza x=new metalsoft.negocio.ventas.Pieza();
            x.setNombre(tm[i].getNombre());
            x.setDimensiones(tm[i].getDimensiones());

            //x.setTipo(tm[i].getTipomaterial());
            c[i]=x;
        }
        return c;
    }

    public static Pieza parseToPieza(PiezaDB x)
    {
        if(x==null)return null;
        Pieza c=new Pieza();
        c.setNombre(x.getNombre());
        c.setDimensiones(x.getDimensiones());
        return c;
    }

    public static CondicionIva[] parseToCondIva(Condicioniva[] ci) {
        if(ci==null) return null;
        CondicionIva[] x=new CondicionIva[ci.length];
        CondicionIva object=null;
        for(int i=0;i<ci.length;i++)
        {
            object=new CondicionIva();
            object.setNombre(ci[i].getNombre());
            object.setDescripcion(ci[i].getDescripcion());
            x[i]=object;
        }
        return x;
    }

    public static metalsoft.datos.dbobject.DomicilioDB parseToDomicilioDB(Domicilio x)
    {
        metalsoft.datos.dbobject.DomicilioDB db=new metalsoft.datos.dbobject.DomicilioDB();
        db.setCalle(x.getCalle());
        db.setDepto(x.getDepto());
        db.setNumerocalle(x.getNumeroCalle());
        db.setPiso(x.getPiso());
        db.setTorre(x.getTorre());
        return db;
    }

    public static Domicilio parseToDomicilio(metalsoft.datos.dbobject.DomicilioDB x)
    {
        Domicilio neg=new Domicilio();
        neg.setCalle(x.getCalle());
        neg.setDepto(x.getDepto());
        neg.setNumeroCalle(x.getNumerocalle());
        neg.setPiso(x.getPiso());
        neg.setTorre(x.getTorre());
        return neg;
    }

    public static metalsoft.datos.dbobject.ResponsableDB parseToResponsableDB(Responsable x)
    {
        metalsoft.datos.dbobject.ResponsableDB db=new metalsoft.datos.dbobject.ResponsableDB();
        db.setApellido(x.getApellido());
        db.setEmail(x.getEmail());
        db.setFax(x.getFax());
        db.setNombre(x.getNombre());
        db.setNrodocumento(x.getNroDocumento());
        db.setTelefono(x.getTelefono());
        return db;
    }

    public static Responsable parseToResponsable(metalsoft.datos.dbobject.ResponsableDB x)
    {
        Responsable neg=new Responsable();
        neg.setApellido(x.getApellido());
        neg.setEmail(x.getEmail());
        neg.setFax(x.getFax());
        neg.setNombre(x.getNombre());
        neg.setNroDocumento(x.getNrodocumento());
        neg.setTelefono(x.getTelefono());
        return neg;
    }

    public static ItemCombo[] parseToItemCombo(metalsoft.datos.dbobject.ClienteDB[] x)
    {
        ItemCombo[] items=new ItemCombo[x.length];
        int index=0;
        for(metalsoft.datos.dbobject.ClienteDB c:x)
        {
            items[index]=new ItemCombo(String.valueOf(index), c.getRazonsocial());
            index++;
        }
        return items;
    }

    public static ClienteDB parseToClienteDB(Cliente x)
    {
        ClienteDB clienteDB=new ClienteDB();
        clienteDB.setCelular(x.getCelular());
        clienteDB.setCuit(x.getCUIT());
        if(x.getFechaAlta()!=null)
            clienteDB.setFechaalta(new java.sql.Date(x.getFechaAlta().getTime()));
        else
            clienteDB.setFechaalta(null);

        if(x.getFechaBaja()!=null)
            clienteDB.setFechabaja(new java.sql.Date(x.getFechaBaja().getTime()));
        else
            clienteDB.setFechabaja(null);

        clienteDB.setMail(x.getMail());
        clienteDB.setNrocliente(x.getNroCliente());
        clienteDB.setRazonsocial(x.getRazonSocial());
        clienteDB.setTelefono(x.getTelefono());

        return clienteDB;
    }

    public static Cliente parseToCliente(ClienteDB x)
    {
        Cliente cliente=new Cliente();
        cliente.setCelular(x.getCelular());
        cliente.setCUIT(x.getCuit());
        if(x.getFechaalta()!=null)
            cliente.setFechaAlta(new java.util.Date(x.getFechaalta().getTime()));
        else
            cliente.setFechaAlta(null);

        if(x.getFechabaja()!=null)
            cliente.setFechaBaja(new java.sql.Date(x.getFechabaja().getTime()));
        else
            cliente.setFechaBaja(null);

        cliente.setMail(x.getMail());
        cliente.setNroCliente(x.getNrocliente());
        cliente.setRazonSocial(x.getRazonsocial());
        cliente.setTelefono(x.getTelefono());

        return cliente;
    }

    public static DetalleproductoDB parseToDetalleProductoDB(DetalleProducto x) {
        DetalleproductoDB db=new DetalleproductoDB();
        db.setCantidadpiezas(x.getCantidadPiezas());
        db.setDescripcion(x.getDescripcion());
        return db;
    }

    public static ProductoDB parseToProductoDB(Producto x) {
        ProductoDB db=new ProductoDB();
        db.setDescripcion(x.getDescripcion());
        db.setNombre(x.getNombre());
        db.setNroproducto(x.getNroProducto());
        db.setPreciounitario(x.getPrecioUnitario());
        return db;
    }
}
