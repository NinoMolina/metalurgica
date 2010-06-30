//Source file: D:\\Mis documentos\\facultad\\5to a�o\\Proyecto Final\\Repositorio\\10_metalsoft\\rational\\metalsoft\\sistema\\gestores\\GestorPieza.java

package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.Etapadeproduccion;
import metalsoft.datos.dbobject.Materiaprima;
import metalsoft.datos.dbobject.MateriaprimaPK;
import metalsoft.datos.dbobject.Pieza;
import metalsoft.datos.dbobject.PiezaPK;
import metalsoft.datos.dbobject.Piezareal;
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
import metalsoft.negocio.ItemCombo;
import metalsoft.negocio.produccion.PiezaReal;


public class GestorPieza 
{
   private Pieza[] piezas;
   private Tipomaterial[] tipoMaterial;
   private Materiaprima[] materiaPrima;
   private Etapadeproduccion[] etapaDeProduccion;
   private metalsoft.datos.dbobject.Matriz[] matriz;
   private metalsoft.datos.dbobject.Piezareal[] piezaReal;


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
            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        idTM=buscarTipoMaterial(tipomaterial);
        if(idTM!=-1) p.setTipomaterial(idTM);
        try {
            id=dao.insert(p, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if(cn!=null)try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return id;
    }

    public metalsoft.negocio.ventas.Pieza[] buscarConLIKE(String valor) {
        PiezaDAO dao=new DAOFactoryImpl().createPiezaDAO();
        Connection cn=null;

        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            piezas = dao.findExecutingUserWhere("nombre ILIKE '"+valor+"%'", sqlParams, cn);
            cn.close();
        } catch (Exception ex) {
            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        metalsoft.negocio.ventas.Pieza[] pnegocio=parseToPieza(piezas);

        return pnegocio;

    }

//    public boolean modificarPieza(metalsoft.negocio.ventas.Pieza pieza, String nombre, String descripcion) {
//        PiezaDAO dao=new DAOFactoryImpl().createPiezaDAO();
//        Connection cn=null;
//        metalsoft.datos.dbobject.Pieza[] tm=null;
//        try {
//            cn = new PostgreSQLManager().concectGetCn();
//        } catch (Exception ex) {
//            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        //Object[] sqlParams=new Object[0];
//        Object[] sqlParams=new Object[2];
//        sqlParams[0]=tipoMaterial.getNombre();
//        sqlParams[1]=tipoMaterial.getDescripcion();
//        try {
//            tm = dao.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);
//
//        } catch (Exception ex) {
//            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        long id=-1;
//        if(tm.length>0)id=tm[0].getIdtipomaterial();
//        else return false;
//        //realizo la modificación
//        Tipomaterial modificado=new Tipomaterial();
//        modificado.setDescripcion(descripcion);
//        modificado.setNombre(nombre);
//        modificado.setIdtipomaterial(id);
//        int result=-1;
//        try {
//            result = dao.update(new MateriaprimaPK(id), modificado, cn);
//        } catch (MateriaprimaException ex) {
//            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            cn.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        if(result>0)return true;
//        else return false;
//    }
//
//    boolean eliminarTipoMaterial(TipoMaterial tipoMaterial) {
//        TipomaterialDAO dao=new DAOFactoryImpl().createTipomaterialDAO();
//        Connection cn=null;
//        metalsoft.datos.dbobject.Tipomaterial[] tm=null;
//        try {
//            cn = new PostgreSQLManager().concectGetCn();
//        } catch (Exception ex) {
//            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        //Object[] sqlParams=new Object[0];
//        Object[] sqlParams=new Object[2];
//        sqlParams[0]=tipoMaterial.getNombre();
//        sqlParams[1]=tipoMaterial.getDescripcion();
//        try {
//            tm = dao.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);
//
//        } catch (Exception ex) {
//            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        long id=-1;
//        if(tm.length>0)id=tm[0].getIdtipomaterial();
//        else return false;
//
//        //realizo la eliminación
//
//        int result=-1;
//        try {
//            result = dao.delete(new TipomaterialPK(id), cn);
//        } catch (TipomaterialException ex) {
//            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            cn.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        if(result>0)return true;
//        else return false;
//    }







    //// PARA REFERENCIAR A TIPOMATERIAL

    private long buscarTipoMaterial(TipoMaterial tipomaterial)
    {
        TipomaterialDAO daoTM=new DAOFactoryImpl().createTipomaterialDAO();
        Connection cn=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorTipoMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
        long idTM=-1;
        Object[] sqlParams=new Object[2];
        sqlParams[0]=tipomaterial.getNombre();
        sqlParams[1]=tipomaterial.getDescripcion();
        try {
            Tipomaterial[] tm = daoTM.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);
            if(tm.length>0) idTM=tm[0].getIdtipomaterial();

        } catch (Exception ex) {
            Logger.getLogger(GestorTipoMaterial.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorTipoMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
        TipoMaterial material=null;
        Object[] sqlParams=new Object[1];
        sqlParams[0]=id;

        try {
            Tipomaterial[] tm = daoTM.findExecutingUserWhere("idtipomaterial = ?", sqlParams, cn);
            if(tm.length>0) material=parseToTipomaterial(tm[0]);

        } catch (Exception ex) {
            Logger.getLogger(GestorTipoMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }

        return material;
    }
  
    //// PARA REFERENCIAR A MATERIAPRIMA

    private long buscarMateriaPrima(MateriaPrima materiaprima)
    {
        MateriaprimaDAO daoTM=new DAOFactoryImpl().createMateriaprimaDAO();
        Connection cn=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }
        long idMP=-1;
        Object[] sqlParams=new Object[1];
        sqlParams[0]=materiaprima.getNombre();

        try {
            metalsoft.datos.dbobject.Materiaprima[] tm = daoTM.findExecutingUserWhere("nombre = ? ", sqlParams, cn);
            if(tm.length>0) idMP=tm[0].getIdmateriaprima();

        } catch (Exception ex) {
            Logger.getLogger(GestorMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }
        MateriaPrima materiaprima=null;
        Object[] sqlParams=new Object[1];
        sqlParams[0]=id;

        try {
            Materiaprima[] tm = daoTM.findExecutingUserWhere("idmateriaprima = ?", sqlParams, cn);
            if(tm.length>0) materiaprima=parseToMateriaPrima(tm[0]);

        } catch (Exception ex) {
            Logger.getLogger(GestorMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }

        return materiaprima;
    }



    ///////////////////////TODOS LOS PARSES QUE SE USAN

    //PIEZA
    private metalsoft.negocio.ventas.Pieza[] parseToPieza(Pieza[] tm) {
        if(tm==null)return null;

        metalsoft.negocio.ventas.Pieza[] c=new metalsoft.negocio.ventas.Pieza[tm.length];
        for(int i=0;i<tm.length;i++)
        {
            metalsoft.negocio.ventas.Pieza x=new metalsoft.negocio.ventas.Pieza();
            x.setNombre(tm[i].getNombre());
            x.setDimensiones(tm[i].getDimensiones());
            x.setTipo(buscarTipoMaterialPorID(tm[i].getTipomaterial()));
            x.setMateria(buscarMateriaPrimaPorID(tm[i].getMateriaprima()));
            c[i]=x;
        }
        return c;
    }
    //TIPO MATERIAL
     private TipoMaterial parseToTipomaterial(Tipomaterial tm) {
        if(tm==null)return null;

        TipoMaterial x=new TipoMaterial();
        x.setNombre(tm.getNombre());
        x.setDescripcion(tm.getDescripcion());

        return x;
    }
     //MATERIA PRIMA
     private MateriaPrima parseToMateriaPrima(Materiaprima tm) {
        if(tm==null)return null;

        MateriaPrima x=new MateriaPrima();
        x.setNombre(tm.getNombre());

        return x;
    }
    //ETAPA DE PRODUCCION
     private metalsoft.negocio.ventas.EtapaDeProduccion parseToEtapaDeProduccion(Etapadeproduccion ep) {
        if(ep==null)return null;

        metalsoft.negocio.ventas.EtapaDeProduccion x=new metalsoft.negocio.ventas.EtapaDeProduccion();
        x.setNombre(ep.getNombre());
        x.setNumeroEtapa(ep.getNroetapaproduccion());
        x.setHorasMaquina(new Date(ep.getHorasmaquina().getTime()));
        x.setHorasHombre(new Date(ep.getHorashombre().getTime()));
        x.setDuracionEstimadaXUnidMed(new Date(ep.getDuracionestimada().getTime()));
        x.setFechaCreacion(ep.getFechacreacion());
        //java.sql.Time t=new Time(new Date().getTime());

        return x;
    }
    //PIEZA REAL
     private PiezaReal parseToPiezaReal(Piezareal pr) {
        if(pr==null)return null;

        PiezaReal x=new PiezaReal();
        x.setNroPieza(pr.getNropieza());

        return x;
    }
    //MATRIZ
      private Matriz parseToMatriz(metalsoft.datos.dbobject.Matriz m) {
        if(m==null)return null;

        Matriz x=new Matriz();
        x.setNombre(m.getNombre());
        x.setDescripcion(m.getDescripcion());
        x.setFechacreacion(m.getFechacreacion());

        return x;
    }




    /////////////////////////////////////////////////////////////////////////////
    //COMBOS
    //Tipo de Material
    public void obtenerTipoMaterial(JComboBox combo) {
        TipomaterialDAO dao=new DAOFactoryImpl().createTipomaterialDAO();
        PostgreSQLManager pg=new PostgreSQLManager();

        try {
            tipoMaterial = dao.findAll(pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem("--Seleccionar--");
            for(int i=0;i<tipoMaterial.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(i));
                item.setMostrar(tipoMaterial[i].getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //MateriaPrima
    public void obtenerMateriaPrima(JComboBox combo) {
        MateriaprimaDAO dao=new DAOFactoryImpl().createMateriaprimaDAO();
        PostgreSQLManager pg=new PostgreSQLManager();

        try {
            materiaPrima = dao.findAll(pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem("--Seleccionar--");
            for(int i=0;i<materiaPrima.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(i));
                item.setMostrar(materiaPrima[i].getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //Matriz
    public void obtenerMatriz(JComboBox combo) {
        MatrizDAO dao=new DAOFactoryImpl().createMatrizDAO();
        PostgreSQLManager pg=new PostgreSQLManager();
        try {
            matriz = dao.findAll(pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem("--Seleccionar--");
            for(int i=0;i<matriz.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(i));
                item.setMostrar(matriz[i].getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
