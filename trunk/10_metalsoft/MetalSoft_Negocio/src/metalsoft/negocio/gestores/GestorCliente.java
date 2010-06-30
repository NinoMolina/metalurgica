//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\negocio\\gestores\\GestorCliente.java

package metalsoft.negocio.gestores;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.Barrio;
import metalsoft.datos.dbobject.Condicioniva;
import metalsoft.datos.dbobject.Estadocliente;
import metalsoft.datos.dbobject.Localidad;
import metalsoft.datos.dbobject.Prioridad;
import metalsoft.datos.dbobject.Provincia;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.CondicionivaDAO;
import metalsoft.datos.idao.EstadoclienteDAO;
import metalsoft.datos.idao.LocalidadDAO;
import metalsoft.datos.idao.PrioridadDAO;
import metalsoft.datos.idao.ProvinciaDAO;
import metalsoft.negocio.ItemCombo;
import metalsoft.negocio.ventas.CondicionIva;


public class GestorCliente 
{
   private Condicioniva[] ci=null;
   private CondicionIva[] cinegocio=null;
   private Prioridad[] pr=null;
   private Estadocliente[] estados=null;
   private Provincia[] provincias=null;
   private Localidad[] localidades=null;
   private Barrio[] barrios=null;
   /**
    * @roseuid 4C280D160306
    */
   public GestorCliente() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D010D
    */
   public void razonSocial() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D010E
    */
   public void numeroDeCuit() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D010F
    */
   public CondicionIva[] buscarCondicionIva()
   {
        CondicionivaDAO dao=new DAOFactoryImpl().createCondicionivaDAO();
        PostgreSQLManager pg=new PostgreSQLManager();

        try {
            ci = dao.findAll(pg.concectGetCn());
            cinegocio=Parser.parseToCondIva(ci);
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cinegocio;
   }

   public void buscarCondicionIva(JComboBox combo)
   {
        CondicionivaDAO dao=new DAOFactoryImpl().createCondicionivaDAO();
        PostgreSQLManager pg=new PostgreSQLManager();

        try {
            ci = dao.findAll(pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem("--Seleccionar--");
            for(int i=0;i<ci.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(i));
                item.setMostrar(ci[i].getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
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

   public void obtenerPrioridades(JComboBox combo) {
        PrioridadDAO dao=new DAOFactoryImpl().createPrioridadDAO();
        PostgreSQLManager pg=new PostgreSQLManager();
        combo.removeAllItems();
        try {
            pr = dao.findAll(pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem("--Seleccionar--");
            for(int i=0;i<pr.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(i));
                item.setMostrar(pr[i].getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
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
        EstadoclienteDAO dao=new DAOFactoryImpl().createEstadoclienteDAO();
        PostgreSQLManager pg=new PostgreSQLManager();

        try {
            estados = dao.findAll(pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem("--Seleccionar--");
            for(int i=0;i<estados.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(i));
                item.setMostrar(estados[i].getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
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

   public void obtenerProvincias(JComboBox combo) {
        ProvinciaDAO dao=new DAOFactoryImpl().createProvinciaDAO();
        PostgreSQLManager pg=new PostgreSQLManager();

        try {
            provincias = dao.findAll(pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<provincias.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(i));
                item.setMostrar(provincias[i].getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
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

   public void buscarLocalidadesDeProvincia(JComboBox combo, int index) {
        LocalidadDAO dao=new DAOFactoryImpl().createLocalidadDAO();
        PostgreSQLManager pg=new PostgreSQLManager();
        try {
            localidades = dao.findByProvincia(provincias[index].getIdprovincia(),pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem("--Seleccionar--");
            for(int i=0;i<localidades.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(i));
                item.setMostrar(localidades[i].getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
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
   
   /**
    * @roseuid 4C1FF60D0110
    */
   public void direccionCliente() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0111
    */
   public void buscarBarriosExistentes() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0112
    */
   public void barrioSeleccionado() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0113
    */
   public void buscarLocalidadesExistentes() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0114
    */
   public void localidadSeleccionada() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0115
    */
   public void datosResponsables() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0116
    */
   public void confirmacion() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0117
    */
   public void registrarNuevoCliente() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0118
    */
   public void generarNumeroCliente() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0119
    */
   public void buscarUltimoNumeroCliente() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D011A
    */
   public void obtenerFechaActual() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D011B
    */
   public void finCasoDeUso() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D011C
    */
   public void cancelacion() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D011D
    */
   public void cancelarCasoDeUso() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D011E
    */
   public void ingresoNuevoBarrio() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D011F
    */
   public void llamarCasoDeUsoRegistrarBarrio() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0122
    */
   public void ingresoNuevaLocalidad() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0123
    */
   public void llamarCasoDeUsoRegistrarLocalidad() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0126
    */
   public void noConfirmacion() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0128
    */
   public void clienteSeleccionado() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0129
    */
   public void mostrarDatosCliente() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D012A
    */
   public void consultaFinalizada() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D012C
    */
   public void opcionImprimir() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0130
    */
   public void datosAModificar() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0132
    */
   public void actualizarCambios() 
   {
    
   }


}
