//Source file: D:\\Mis documentos\\facultad\\5to a�o\\Proyecto Final\\Repositorio\\10_metalsoft\\rational\\metalsoft\\sistema\\gestores\\GestorPieza.java

package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.Materiaprima;
import metalsoft.datos.dbobject.MateriaprimaPK;
import metalsoft.datos.dbobject.Pieza;
import metalsoft.datos.dbobject.PiezaPK;
import metalsoft.datos.exception.PiezaException;
import metalsoft.datos.factory.DAOFactoryImpl;
//import metalsoft.datos.idao.PiezaDAO;
import metalsoft.datos.idao.PiezaDAO;


import metalsoft.negocio.produccion.TipoMaterial;
import metalsoft.datos.dbobject.Tipomaterial;
import metalsoft.datos.exception.MateriaprimaException;
import metalsoft.datos.idao.TipomaterialDAO;
import metalsoft.negocio.produccion.Matriz;
import metalsoft.datos.idao.MatrizDAO;
import metalsoft.negocio.almacenamiento.MateriaPrima;
import metalsoft.datos.idao.MateriaprimaDAO;


public class GestorPieza 
{
   private Pieza[] piezas;
   /**
    * @roseuid 4C27E2C30133
    */
   public GestorPieza() 
   {
    
   }
   public static void cargarLista()
   {

   }
   
   /**
    * @roseuid 4C1FF60D0255
    */
   public void nombre() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0256
    */
   public void buscarTipoMateriales() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0257
    */
   public void tipoMaterialSeleccionado() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0258
    */
   public void dimension() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0259
    */
   public void buscarMateriasPrimas() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D025A
    */
   public void materiaPrimaSeleccionada() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D025B
    */
   public void buscarMatrices() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D025C
    */
   public void matrizSeleccionada() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D025D
    */
   public void confirmacion() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D025E
    */
   public void registrarPieza() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D025F
    */
   public void finCasoDeUso() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0260
    */
   public void cancelacion() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0261
    */
   public void seCancelaCasoDeUso() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0262
    */
   public void noConfirmacion() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0264
    */
   public void piezaSeleccionada() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0265
    */
   public void buscarDatosPieza() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0266
    */
   public void cambios() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0268
    */
   public void actualizarDatos() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D026C
    */
   public void criterioBusquedaSeleccionado() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D026D
    */
   public void generarInforme() 
   {
    
   }

   public int guardar(String nombre, String dimensiones, TipoMaterial tipomaterial) {
        PiezaDAO dao=new DAOFactoryImpl().createPiezaDAO();
        Pieza p=new Pieza();
        p.setNombre(nombre);
        p.setDimensiones(dimensiones);
        int id=-1;
        long idTM=-1;
        Connection cn=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        idTM=buscarTipoMaterial(tipomaterial);
        if(idTM!=-1) p.setTipomaterial(idTM);
        try {
            id=dao.insert(p, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorTipoMaterialDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if(cn!=null)try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestorTipoMaterialDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return id;
    }

    public metalsoft.negocio.ventas.Pieza[] buscarConLIKE(String valor) {
        PiezaDAO dao=new DAOFactoryImpl().createPiezaDAO();
        Connection cn=null;
        Pieza[] p=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            p = dao.findExecutingUserWhere("nombre ILIKE '"+valor+"%'", sqlParams, cn);
            cn.close();
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        metalsoft.negocio.ventas.Pieza[] pnegocio=parseToPieza(p);
        for(int i=0;i<pnegocio.length;i++)
        {
            pnegocio[i].setTipo(buscarTipoMaterialPorID(p[i].getTipomaterial()));
            pnegocio[i].setMateria(buscarMateriaPrimaPorID(p[i].getMateriaprima()));
        }

    }

    private metalsoft.negocio.ventas.Pieza[] parseToPieza(Pieza[] tm) {
        if(tm==null)return null;

        metalsoft.negocio.ventas.Pieza[] c=new metalsoft.negocio.ventas.Pieza[tm.length];
        for(int i=0;i<tm.length;i++)
        {
            metalsoft.negocio.ventas.Pieza x=new metalsoft.negocio.ventas.Pieza();
            x.setNombre(tm[i].getNombre());
            x.setDimensiones(tm[i].getDimensiones());
            x.setTipo(buscarTipoMaterialPorID(tm[i].getTipomaterial()));

            c[i]=x;
        }
        return c;
    }







    public boolean modificarPieza(metalsoft.negocio.ventas.Pieza pieza, String nombre, String descripcion) {
        TipomaterialDAO dao=new DAOFactoryImpl().createTipomaterialDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.Tipomaterial[] tm=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Object[] sqlParams=new Object[0];
        Object[] sqlParams=new Object[2];
        sqlParams[0]=tipoMaterial.getNombre();
        sqlParams[1]=tipoMaterial.getDescripcion();
        try {
            tm = dao.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        long id=-1;
        if(tm.length>0)id=tm[0].getIdtipomaterial();
        else return false;
        //realizo la modificación
        Tipomaterial modificado=new Tipomaterial();
        modificado.setDescripcion(descripcion);
        modificado.setNombre(nombre);
        modificado.setIdtipomaterial(id);
        int result=-1;
        try {
            result = dao.update(new MateriaprimaPK(id), modificado, cn);
        } catch (MateriaprimaException ex) {
            Logger.getLogger(GestorTipoMaterialDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorTipoMaterialDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result>0)return true;
        else return false;
    }

    boolean eliminarTipoMaterial(TipoMaterial tipoMaterial) {
        TipomaterialDAO dao=new DAOFactoryImpl().createTipomaterialDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.Tipomaterial[] tm=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Object[] sqlParams=new Object[0];
        Object[] sqlParams=new Object[2];
        sqlParams[0]=tipoMaterial.getNombre();
        sqlParams[1]=tipoMaterial.getDescripcion();
        try {
            tm = dao.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        long id=-1;
        if(tm.length>0)id=tm[0].getIdtipomaterial();
        else return false;

        //realizo la eliminación

        int result=-1;
        try {
            result = dao.delete(new TipomaterialPK(id), cn);
        } catch (TipomaterialException ex) {
            Logger.getLogger(GestorTipoMaterialDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorTipoMaterialDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result>0)return true;
        else return false;
    }







    //// PARA REFERENCIAR A TIPOMATERIAL

    private long buscarTipoMaterial(TipoMaterial tipomaterial)
    {
        TipomaterialDAO daoTM=new DAOFactoryImpl().createTipomaterialDAO();
        Connection cn=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        long idTM=-1;
        Object[] sqlParams=new Object[2];
        sqlParams[0]=tipomaterial.getNombre();
        sqlParams[1]=tipomaterial.getDescripcion();
        try {
            Tipomaterial[] tm = daoTM.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);
            if(tm.length>0) idTM=tm[0].getIdtipomaterial();

        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idTM;
    }



    private TipoMaterial buscarTipoMaterialPorID(long id)
    {
        TipomaterialDAO daoTM=new DAOFactoryImpl().createTipomaterialDAO();
        Connection cn=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        TipoMaterial material=null;
        Object[] sqlParams=new Object[1];
        sqlParams[0]=id;

        try {
            Tipomaterial[] tm = daoTM.findExecutingUserWhere("idtipomaterial = ?", sqlParams, cn);
            if(tm.length>0) material=parseToTipomaterial(tm[0]);

        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return material;
    }
    private TipoMaterial parseToTipomaterial(Tipomaterial tm) {
        if(tm==null)return null;

        TipoMaterial x=new TipoMaterial();
        x.setNombre(tm.getNombre());
        x.setDescripcion(tm.getDescripcion());

        return x;
    }

    //// PARA REFERENCIAR A MATERIAPRIMA

    private long buscarMateriaPrima(MateriaPrima materiaprima)
    {
        MateriaprimaDAO daoTM=new DAOFactoryImpl().createMateriaprimaDAO();
        Connection cn=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        long idMP=-1;
        Object[] sqlParams=new Object[1];
        sqlParams[0]=materiaprima.getNombre();

        try {
            metalsoft.datos.dbobject.Materiaprima[] tm = daoTM.findExecutingUserWhere("nombre = ? ", sqlParams, cn);
            if(tm.length>0) idMP=tm[0].getIdmateriaprima();

        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idMP;
    }
    private MateriaPrima buscarMateriaPrimaPorID(long id)
    {
        MateriaprimaDAO daoTM=new DAOFactoryImpl().createMateriaprimaDAO();
        Connection cn=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        MateriaPrima materiaprima=null;
        Object[] sqlParams=new Object[1];
        sqlParams[0]=id;

        try {
            Materiaprima[] tm = daoTM.findExecutingUserWhere("idmateriaprima = ?", sqlParams, cn);
            if(tm.length>0) materiaprima=parseToMateriaPrima(tm[0]);

        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return materiaprima;
    }
    private MateriaPrima parseToMateriaPrima(Materiaprima tm) {
        if(tm==null)return null;

        MateriaPrima x=new MateriaPrima();
        x.setNombre(tm.getNombre());


        return x;
    }
}
