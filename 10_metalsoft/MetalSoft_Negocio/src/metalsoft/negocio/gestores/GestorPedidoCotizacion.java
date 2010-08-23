//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\metalsoft\\sistema\\gestores\\GestorPedidoCotizacion.java

package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.EstadopedidoDB;
import metalsoft.datos.dbobject.PrioridadDB;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.PrioridadDAO;
import metalsoft.negocio.access.AccessEstadoPedido;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.access.AccessPrioridad;
import metalsoft.negocio.access.AccessViews;
import metalsoft.negocio.ventas.Cliente;
import metalsoft.negocio.ventas.EstadoPedido;
import metalsoft.negocio.ventas.Producto;
import metalsoft.util.Combo;
import metalsoft.util.ItemCombo;


public class GestorPedidoCotizacion 
{
   
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
   public void registrarPedidoCotizacion() 
   {
    
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

    public ViewDetallePedidoCotizacion buscarProductoParaDetallePedidoCotizacion(long idProducto) {
        PostgreSQLManager pg=null;
        Connection cn=null;
        ViewDetallePedidoCotizacion view=null;
        pg=new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
            view=AccessViews.detallePedidoCotizacion(idProducto, cn);
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
        return view;
    }


}
