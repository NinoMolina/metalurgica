/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import metalsoft.datos.dbobject.ClienteDB;
import metalsoft.datos.dbobject.Condicioniva;
import metalsoft.datos.dbobject.DetallepedidoDB;
import metalsoft.datos.dbobject.Detallepiezacalidadpresupuesto;
import metalsoft.datos.dbobject.DetallepiezapresupuestoDB;
import metalsoft.datos.dbobject.DetallepresupuestoDB;
import metalsoft.datos.dbobject.DetalleproductoDB;
import metalsoft.datos.dbobject.DetalleproductopresupuestoDB;
import metalsoft.datos.dbobject.EtapadeproduccionDB;
import metalsoft.datos.dbobject.MateriaprimaDB;
import metalsoft.datos.dbobject.PedidoDB;
import metalsoft.datos.dbobject.PresupuestoDB;
import metalsoft.datos.dbobject.ProcesocalidadDB;
import metalsoft.datos.dbobject.ProductoDB;
import metalsoft.datos.dbobject.ProveedorDB;
import metalsoft.datos.dbobject.EmpresametalurgicaDB;
import metalsoft.datos.dbobject.Tipomaterial;
import metalsoft.negocio.almacenamiento.MateriaPrima;
import metalsoft.negocio.calidad.DetallePiezaCalidadPresupuesto;
import metalsoft.negocio.calidad.ProcesoCalidad;
import metalsoft.negocio.compras.Proveedor;
import metalsoft.negocio.produccion.TipoMaterial;
import metalsoft.datos.dbobject.PiezaDB;
import metalsoft.negocio.ventas.DetallePedido;
import metalsoft.negocio.ventas.DetallePresupuesto;
import metalsoft.negocio.ventas.DetalleProducto;
import metalsoft.negocio.ventas.DetalleProductoPresupuesto;
import metalsoft.negocio.ventas.Pedido;
import metalsoft.negocio.ventas.Presupuesto;
import metalsoft.negocio.ventas.Producto;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.compras.Responsable;
import metalsoft.negocio.rrhh.Domicilio;
import metalsoft.negocio.ventas.Cliente;
import metalsoft.negocio.ventas.CondicionIva;
import metalsoft.negocio.ventas.Pieza;
import metalsoft.util.Fecha;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.almacenamiento.MateriaPrima;
import metalsoft.datos.dbobject.MateriaprimaDB;
import metalsoft.negocio.ventas.DetallePiezaPresupuesto;
import metalsoft.negocio.trabajostercerizados.EmpresaMetalurgica;
/**
 *
 * @author Vicky
 */
public class Parser {

    public static MateriaprimaDB parseToMateriaPrimaDB(MateriaPrima materiaPrima) {
        MateriaprimaDB mp=new MateriaprimaDB();
        mp.setAlto(materiaPrima.getAlto());
        mp.setAncho(materiaPrima.getAncho());
        mp.setCodproducto(materiaPrima.getCodProducto());
        mp.setDescripcion(materiaPrima.getDescripcion());
        if(materiaPrima.getFechaAlta()!=null)
            mp.setFechaalta(new java.sql.Date(materiaPrima.getFechaAlta().getTime()));
        else
            mp.setFechaalta(null);
        if(materiaPrima.getFechaBaja()!=null)
            mp.setFechabaja(new java.sql.Date(materiaPrima.getFechaBaja().getTime()));
        else
            mp.setFechabaja(null);
        mp.setLargo(materiaPrima.getLargo());
        mp.setNombre(materiaPrima.getNombre());
        mp.setStock(materiaPrima.getStock());
        mp.setNromateriaprima(materiaPrima.getNroMateriaPrima());
        return mp;
    }

    public static ProcesocalidadDB parseToProcesoCalidadDB(ProcesoCalidad pc) {
        ProcesocalidadDB db=new ProcesocalidadDB();
        db.setDescripcion(pc.getDescripcion());

        if(pc.getDuracionEstimada()!=null)
            db.setDuracionestimada(new java.sql.Time(pc.getDuracionEstimada().getTime()));//Fecha.parseToTimeSQL
        else
            db.setDuracionestimada(null);
        if(pc.getFechaCreacion()!=null)
            db.setFechacreacion(new java.sql.Date(pc.getFechaCreacion().getTime()));
        else
            db.setFechacreacion(null);
        db.setEspecificacion(pc.getEspecificacion());
        db.setHerramienta(pc.getHerramienta());
        db.setNombre(pc.getNombre());
        db.setNroproceso(pc.getNroProceso());
        db.setTolerancia(pc.getTolerancia());

        return db;

    }

    public static ProveedorDB parseToProveedorDB(Proveedor x) {
        ProveedorDB db=new ProveedorDB();
        db.setCelular(x.getCelular());
        db.setCuit(x.getCUIT());
        if(x.getFechaAlta()!=null)
            db.setFechaalta(new java.sql.Date(x.getFechaAlta().getTime()));
        else
            db.setFechaalta(null);

        if(x.getFechaBaja()!=null)
            db.setFechabaja(new java.sql.Date(x.getFechaBaja().getTime()));
        else
            db.setFechabaja(null);

        db.setMail(x.getMail());
        db.setNroproveedor(x.getNroProveedor());
        db.setRazonsocial(x.getRazonSocial());
        db.setTelefono(x.getTelefono());

        return db;
    }

     public static EmpresametalurgicaDB parseToEmpresametalurgicaDB(EmpresaMetalurgica x) {
        EmpresametalurgicaDB db=new EmpresametalurgicaDB();
        db.setCelular(x.getCelular());
        db.setCuit(x.getCUIT());
        if(x.getFechaAlta()!=null)
            db.setFechaalta(new java.sql.Date(x.getFechaAlta().getTime()));
        else
            db.setFechaalta(null);

        if(x.getFechaBaja()!=null)
            db.setFechabaja(new java.sql.Date(x.getFechaBaja().getTime()));
        else
            db.setFechabaja(null);

        db.setMail(x.getMail());
        db.setNroempresametalurgica(x.getNroEmpresaMetalurgica());
        db.setRazonsocial(x.getRazonSocial());
        db.setTelefono(x.getTelefono());

        return db;
    }


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
            x.setAlto(tm[i].getAlto());
            x.setAncho(tm[i].getAncho());
            x.setLargo(tm[i].getLargo());

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
        c.setAlto(x.getAlto());
        c.setAncho(x.getAncho());
        c.setLargo(x.getLargo());
        c.setNombre(x.getNombre());
        return c;
    }
    public static PiezaDB parseToPiezaDB(Pieza x)
    {
        PiezaDB piezaDB=new PiezaDB();
        piezaDB.setAlto(x.getAlto());
        piezaDB.setAncho(x.getAncho());
        piezaDB.setLargo(x.getLargo());
        piezaDB.setNombre(x.getNombre());
        return piezaDB;
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
    public static EtapadeproduccionDB parseToEtapaDeProduccionDB(metalsoft.negocio.ventas.EtapaDeProduccion x)
    {
        EtapadeproduccionDB db=new EtapadeproduccionDB();
        if(x.getDuracionEstimadaXUnidMed()!=null)
            db.setDuracionestimada(new java.sql.Time(x.getDuracionEstimadaXUnidMed().getTime()));//Fecha.parseToTimeSQL
        else
            db.setDuracionestimada(null);
        if(x.getFechaCreacion()!=null)
            db.setFechacreacion(new java.sql.Date(x.getFechaCreacion().getTime()));
        else
            db.setFechacreacion(null);
        if(x.getHorasHombre()!=null)
            db.setHorashombre(new java.sql.Time(x.getHorasHombre().getTime()));
        else
            db.setHorashombre(null);
        if(x.getHorasMaquina()!=null)
            db.setHorasmaquina(new java.sql.Time(x.getHorasMaquina().getTime()));
        else
            db.setHorasmaquina(null);
        //db.setIdetapaproduccion(x.getNumeroEtapa());
        //db.setMaquina(x.getMaquina());
        db.setNombre(x.getNombre());
        db.setNroetapaproduccion(x.getNumeroEtapa());

        return db;

    }

        public static Pedido parseToPedido(PedidoDB x) {
        Pedido db=new Pedido();
        db.setEsPedidoWeb(x.getEspedidoweb());

        if(x.getFechacancelacion()!=null)
            db.setFechaCancelacion(x.getFechacancelacion());
        if(x.getFechaconfirmacionpedido()!=null)
            db.setFechaConfirmacionPedido(x.getFechaconfirmacionpedido());
        if(x.getFechaentregaestipulada()!=null)
            db.setFechaEntregaEstipulada(x.getFechaentregaestipulada());
        if(x.getFechaentregareal()!=null)
            db.setFechaEntregaReal(x.getFechaentregareal());
        if(x.getFechapedidocotizacion()!=null)
            db.setFechaPedidoCotizacion(x.getFechapedidocotizacion());
        if(x.getFecharegpedcotiz()!=null)
            db.setFechaRegistroPedidoCotizacion(x.getFecharegpedcotiz());
        if(x.getFecharequeridacotizacion()!=null)
            db.setFechaRequeridaCotizacion(x.getFecharequeridacotizacion());

        db.setMotivoCancelacion(x.getMotivocancelacion());
        db.setNroPedCotizCliente(x.getNropedidocotizacioncliente());
        db.setNroPedido((int) x.getNropedido());
        return db;

    }
    public static PedidoDB parseToPedidoDB(Pedido x) {
        PedidoDB db=new PedidoDB();
        db.setEspedidoweb(x.getEsPedidoWeb());

        if(x.getFechaCancelacion()!=null)
            db.setFechacancelacion(Fecha.parseToDateSQL(x.getFechaCancelacion()));
        if(x.getFechaConfirmacionPedido()!=null)
            db.setFechaconfirmacionpedido(Fecha.parseToDateSQL(x.getFechaConfirmacionPedido()));
        if(x.getFechaEntregaEstipulada()!=null)
            db.setFechaentregaestipulada(Fecha.parseToDateSQL(x.getFechaEntregaEstipulada()));
        if(x.getFechaEntregaReal()!=null)
            db.setFechaentregareal(Fecha.parseToDateSQL(x.getFechaEntregaReal()));
        if(x.getFechaPedidoCotizacion()!=null)
            db.setFechapedidocotizacion(Fecha.parseToDateSQL(x.getFechaPedidoCotizacion()));
        if(x.getFechaRegistroPedidoCotizacion()!=null)
            db.setFecharegpedcotiz(Fecha.parseToDateSQL(x.getFechaRegistroPedidoCotizacion()));
        if(x.getFechaRequeridaCotizacion()!=null)
            db.setFecharequeridacotizacion(Fecha.parseToDateSQL(x.getFechaRequeridaCotizacion()));
        
        db.setMotivocancelacion(x.getMotivoCancelacion());
        db.setNropedidocotizacioncliente(x.getNroPedCotizCliente());
        db.setNropedido(x.getNroPedido());
        return db;

    }

    public static DetallepedidoDB parseToDetallePedidoDB(DetallePedido x) {
        DetallepedidoDB db=new DetallepedidoDB();
        db.setCantidad(x.getCantidad());
        db.setPrecio(x.getPrecio());
        return db;
    }
    public static Presupuesto parseToPresupuesto(PresupuestoDB p) {
        Presupuesto obj=new Presupuesto();
        if(p.getFechapresupuesto()==null)obj.setFechaPresupuesto(null);
        else obj.setFechaPresupuesto(p.getFechapresupuesto());
        if(p.getFechavencimiento()==null)obj.setFechaVencimiento(null);
        else obj.setFechaVencimiento(p.getFechavencimiento());
        obj.setMontoTotal((float) p.getMontototal());
        obj.setNroPresupuesto(p.getNropresupuesto());
        return obj;
    }
    public static PresupuestoDB parseToPresupuestoDB(Presupuesto p) {
        PresupuestoDB db=new PresupuestoDB();
        if(p.getFechaPresupuesto()==null)db.setFechapresupuesto(null);
        else db.setFechapresupuesto(Fecha.parseToDateSQL(p.getFechaPresupuesto()));
        if(p.getFechaVencimiento()==null)db.setFechavencimiento(null);
        else db.setFechavencimiento(Fecha.parseToDateSQL(p.getFechaVencimiento()));
        db.setMontototal(p.getMontoTotal());
        db.setNropresupuesto(p.getNroPresupuesto());
        return db;
    }

    public static DetallepresupuestoDB parseToDetallepresupuestoDB(DetallePresupuesto dp) {
        DetallepresupuestoDB db=new DetallepresupuestoDB();
        db.setCantidad(dp.getCantidad());
        db.setPrecio(dp.getPrecio());
        return db;
    }

    public static DetalleproductopresupuestoDB parseToDetalleproductopresupuestoDB(DetalleProductoPresupuesto x) {
        DetalleproductopresupuestoDB db=new DetalleproductopresupuestoDB();
        db.setCantpiezas(x.getCantidadPieza());
        db.setCantmateriaprima(x.getCantidadMateriaPrima());
        db.setPreciomateriaprima(x.getPreciomateriaprima());
        return db;
    }

    public static DetallepiezapresupuestoDB parseToDetallepiezapresupuestoDB(DetallePiezaPresupuesto x) {
        DetallepiezapresupuestoDB db=new DetallepiezapresupuestoDB();
        db.setDuracionpiezaxetapa(Fecha.parseToTimeSQL(x.getDuracionEtapaXPieza()));
        return db;
    }

    public static Detallepiezacalidadpresupuesto parseToDetallepiezacalidadpresupuestoDB(DetallePiezaCalidadPresupuesto dpcp) {
        Detallepiezacalidadpresupuesto db=new Detallepiezacalidadpresupuesto();
        db.setCantprocesocalidad(dpcp.getCantidadProcesoCalidad());
        db.setDuracionxpieza(Fecha.parseToTimeSQL(dpcp.getDuracionXPieza()));
        return db;
    }
}
