//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\metalsoft\\sistema\\gestores\\GestorPedidoCotizacion.java

package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.EstadopedidoDB;
import metalsoft.datos.dbobject.PrioridadDB;
import metalsoft.datos.dbobject.ProductoDB;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.PrioridadDAO;
import metalsoft.negocio.access.AccessEstadoPedido;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.access.AccessPrioridad;
import metalsoft.negocio.access.AccessProducto;
import metalsoft.negocio.access.AccessViews;
import metalsoft.negocio.ventas.Cliente;
import metalsoft.negocio.ventas.EstadoPedido;
import metalsoft.negocio.ventas.Pedido;
import metalsoft.negocio.ventas.Producto;
import metalsoft.util.Combo;
import metalsoft.util.ItemCombo;


public class GestorPedidoCotizacion 
{
    private int nroPedidoCliente;
    private int nroFactura;
    private String motivoCancelacion;
    private Date fechaCancelacion;
    private Date fechaConfirmacionPedido;
    private Date fechaEntregaReal;
    private Date fechaEntregaEstipulada;
    private Date fechaRequeridaCotizacion;
    private Date fechaPedidoCotizacion;
    private long idEstado;
    private long idPrioridad;
    private LinkedList<ViewDetallePedidoCotizacion> filasDetallePedido;
    private int nroPedido;
    private long idPedido;
    private long idCliente;
   
   /**
    * @roseuid 4C2036120340
    */
   public GestorPedidoCotizacion() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01CE
    */
   public void clienteSeleccionado() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01CF
    */
   public void numeroPedido() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01D0
    */
   public void fechaPedido() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01D1
    */
   public void fechaRequeridaCotizacion() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01D2
    */
   public void buscarProducto() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01D3
    */
   public void productoSeleccionado() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01D4
    */
   public void cantidadProducto() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01D5
    */
   public void planoPedido() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01D6
    */
   public void confirmacion() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01D7
    */
   public long registrarPedidoCotizacion()
   {
        Pedido p=new Pedido();
        p.setEsPedidoWeb(false);
        p.setFechaCancelacion(fechaCancelacion);
        p.setFechaConfirmacionPedido(fechaConfirmacionPedido);
        p.setFechaEntregaEstipulada(fechaEntregaEstipulada);
        p.setFechaEntregaReal(fechaEntregaReal);
        p.setFechaPedidoCotizacion(fechaPedidoCotizacion);
        p.setFechaRegistroPedidoCotizacion(fechaPedidoCotizacion);
        p.setFechaRequeridaCotizacion(fechaRequeridaCotizacion);
        p.setMotivoCancelacion(motivoCancelacion);
        p.setNroPedCotizCliente(nroPedidoCliente);
        p.setNroPedido(nroPedido);

        long result=-1;
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        try {

            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            //arlIdsPiezasDetalleProducto se crea y llena en el metodo crearDetalleProducto(prod)
            //Acá podria pasar como parametros a prod, filasDetalle y cn en vez de arlIdsPiezasDetalleProducto
            //y no usar el metodo crearDetalleProducto, el metodo prod.guardar() debería crear los detalles
            //y guardarlos
            long id=p.guardar(p,idCliente,idEstado,idPrioridad,filasDetallePedido, cn);
            result=id;
            cn.commit();
            idPedido=result;

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
   
   /**
    * @roseuid 4C1FF60D01D8
    */
   public int generarNumeroPedido()
   {
       int result=-1;
        PostgreSQLManager pg=null;
        Connection cn=null;
        pg=new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
            result=AccessFunctions.nvoNroPedido(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPedidoCotizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
   }
   
   /**
    * @roseuid 4C1FF60D01D9
    */
   public void obtenerFechaActual() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01DA
    */
   public void finCasoDeUso() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01DB
    */
   public void opcionRegistrarNuevoCliente() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01DC
    */
   public void llamarCasoDeUsoRegistrarCliente() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01DD
    */
   public void cancelacion() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01DE
    */
   public void cancelarCasoDeUso() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01E5
    */
   public void opcionRegistrarProducto() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01E6
    */
   public void llamarCasoDeUsoRegistrarProdcucto() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01ED
    */
   public void noConfirmacion() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01F1
    */
   public void criterioBusquedaSeleccionado() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01F2
    */
   public void mostrarDatosPedido() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01F3
    */
   public void imprimir() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01F4
    */
   public void generarInforme() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01F5
    */
   public void finConsulta() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01F8
    */
   public void seCancelaCasoDeUso() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01F9
    */
   public void noImprimir() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01FB
    */
   public void burcarUltimoNumeroPedido() 
   {
    
   }

    public Cliente[] buscarClientes(String valor) {
        GestorPedidoCotizacionDB gestordb=new GestorPedidoCotizacionDB();
        return gestordb.buscarClientes(valor);
    }

    public void obtenerPrioridades(JComboBox combo) {
        PrioridadDB[] prioridades=null;
        Connection cn=null;
        PostgreSQLManager pg=null;
        combo.removeAllItems();
        try {
            pg=new PostgreSQLManager();
            cn=pg.concectGetCn();
            prioridades = AccessPrioridad.findAll(cn);

            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<prioridades.length;i++)
            {
                Combo.cargarCombo(combo, String.valueOf(prioridades[i].getIdprioridad()), prioridades[i].getNombre());
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorGenerarPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void obtenerEstados(JComboBox combo) {
        EstadopedidoDB[] vDB=null;
        Connection cn=null;
        PostgreSQLManager pg=null;
        combo.removeAllItems();
        try {
            pg=new PostgreSQLManager();
            cn=pg.concectGetCn();
            vDB = AccessEstadoPedido.findAll(cn);

            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<vDB.length;i++)
            {
                Combo.cargarCombo(combo, String.valueOf(vDB[i].getIdestado()), vDB[i].getNombre());
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorGenerarPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

//    public ViewDetallePedidoCotizacion buscarProductoParaDetallePedidoCotizacion(long idProducto) {
//        PostgreSQLManager pg=null;
//        Connection cn=null;
//        ViewDetallePedidoCotizacion view=null;
//        pg=new PostgreSQLManager();
//        try {
//            cn = pg.concectGetCn();
//            view=AccessViews.detallePedidoCotizacion(idProducto, cn);
//        } catch (Exception ex) {
//            Logger.getLogger(GestorPedidoCotizacion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        finally
//        {
//            try {
//                pg.disconnect();
//            } catch (SQLException ex) {
//                Logger.getLogger(GestorPedidoCotizacion.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return view;
//    }

    public ProductoDB buscarProductoDB(long idProducto) {
        PostgreSQLManager pg=null;
        Connection cn=null;
        ProductoDB db=null;
        pg=new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
            db=AccessProducto.findById(idProducto,cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPedidoCotizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return db;
    }

    public int obtenerCantidadPiezasXProducto(long idProducto) {
        PostgreSQLManager pg=null;
        Connection cn=null;
        pg=new PostgreSQLManager();
        int result=-1;
        try {
            cn = pg.concectGetCn();
            result=AccessFunctions.cantPiezasXProducto(idProducto, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPedidoCotizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public void setNroPedidoCliente(int nroPedidoCliente) {
        this.nroPedidoCliente=nroPedidoCliente;
    }

    public void setNroFactura(int nroFactura) {
        this.nroFactura=nroFactura;
    }

    public void setMotivoCancelacion(String motivoCancelacion) {
        this.motivoCancelacion=motivoCancelacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion=fechaCancelacion;
    }

    public void setConfirmacionPedido(Date fechaConfirmacionPedido) {
        this.fechaConfirmacionPedido=fechaConfirmacionPedido;
    }

    public void setFechaEntregaReal(Date fechaEntregaReal) {
        this.fechaEntregaReal=fechaEntregaReal;
    }

    public void setFechaEntregaEstipulada(Date fechaEntregaEstipulada) {
        this.fechaEntregaEstipulada=fechaEntregaEstipulada;
    }

    public void setFechaRequeridaCotizacion(Date fechaRequeridaCotizacion) {
        this.fechaRequeridaCotizacion=fechaRequeridaCotizacion;
    }

    public void setFechaPedidoCotizacion(Date fechaPedidoCotizacion) {
        this.fechaPedidoCotizacion=fechaPedidoCotizacion;
    }

    public void setIdEstado(long idEstado) {
        this.idEstado=idEstado;
    }

    public void setIdPrioridad(long idPrioridad) {
        this.idPrioridad=idPrioridad;
    }

    public void setListaDetalle(LinkedList<ViewDetallePedidoCotizacion> filas) {
        this.filasDetallePedido=filas;
    }

    public void setNroPedido(int nroPedido) {
        this.nroPedido=nroPedido;
    }

    public void setIdCliente(long idCli) {
        idCliente=idCli;
    }




}
