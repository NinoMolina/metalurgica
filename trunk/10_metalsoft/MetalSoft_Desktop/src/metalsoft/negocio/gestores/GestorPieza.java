//Source file: D:\\Mis documentos\\facultad\\5to a�o\\Proyecto Final\\Repositorio\\10_metalsoft\\rational\\metalsoft\\sistema\\gestores\\GestorPieza.java

package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JList;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.EtapadeproduccionDB;
import metalsoft.datos.dbobject.MaquinaDB;
import metalsoft.datos.dbobject.MateriaprimaDB;
import metalsoft.datos.dbobject.PiezaDB;
import metalsoft.datos.dbobject.PiezaPKDB;
import metalsoft.datos.dbobject.PiezarealDB;
import metalsoft.datos.dbobject.PiezaxetapadeproduccionDB;
import metalsoft.datos.dbobject.TipomaquinaDB;
import metalsoft.datos.factory.DAOFactoryImpl;
//import metalsoft.datos.idao.PiezaDAO;
import metalsoft.datos.idao.PiezaDAO;


import metalsoft.negocio.produccion.TipoMaterial;
import metalsoft.datos.dbobject.TipomaterialDB;
import metalsoft.datos.idao.TipomaterialDAO;
import metalsoft.negocio.produccion.Matriz;
import metalsoft.datos.idao.MatrizDAO;
import metalsoft.negocio.almacenamiento.MateriaPrima;
import metalsoft.datos.idao.MateriaprimaDAO;
import metalsoft.datos.idao.PiezaxetapadeproduccionDAO;
import metalsoft.negocio.access.AccessMaquina;
import metalsoft.negocio.access.AccessPieza;
import metalsoft.negocio.access.AccessTipoMaquina;
import metalsoft.negocio.mantmaquinarias.Maquina;
import metalsoft.negocio.mantmaquinarias.TipoMaquina;
import metalsoft.negocio.ventas.EtapaDeProduccion;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.produccion.PiezaReal;
import metalsoft.presentacion.HiloBuscarEtapaDeProduccion;


public class GestorPieza  implements IBuscador
{
   private PiezaDB[] piezas;
   private TipomaterialDB[] tipoMaterial;
   private MateriaprimaDB[] materiaPrima;
   private EtapadeproduccionDB[] etapaDeProduccion;
   private metalsoft.datos.dbobject.MatrizDB[] matriz;
   private metalsoft.datos.dbobject.PiezarealDB[] piezaReal;
   private metalsoft.datos.dbobject.EtapadeproduccionDB[] etapasDB;
   private JList lstEtapas;


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

   public int guardar(String nombre, Double alto, Double ancho, Double largo, long idTM, long idMP, long idMa) {
        PiezaDAO dao=new DAOFactoryImpl().createPiezaDAO();
        PiezaDB p=new PiezaDB();
        p.setNombre(nombre);
        p.setAlto(alto);
        p.setAncho(ancho);
        p.setLargo(largo);
        p.setTipomaterial(idTM);
        p.setMateriaprima(idMP);
        p.setMatriz(idMa);

        int id=-1;
        
        Connection cn=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        

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

    public PiezaDB[] buscarConLIKE(String valor) {
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
        //metalsoft.negocio.ventas.Pieza[] pnegocio=parseToPieza(piezas);

        return piezas;

    }
    public metalsoft.datos.dbobject.PiezaDB buscarPieza(long id)
    {
        PiezaDAO dao=new DAOFactoryImpl().createPiezaDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.PiezaDB pie=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        PiezaPKDB pk=new PiezaPKDB(id);
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            pie = dao.findByPrimaryKey(pk, cn);
            cn.close();
            
        } catch (Exception ex) {
            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        //metalsoft.negocio.ventas.Pieza[] pnegocio=parseToPieza(piezas);

        return pie;

    }

    public boolean modificarPieza(long idpieza, String nombre, Double alto, Double ancho, Double largo, long idTM, long idMP, long idMa) {
        
        Connection cn=null;
        
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Object[] sqlParams=new Object[0];
        Object[] sqlParams=new Object[1];
        sqlParams[0]=idpieza;
        
        //realizo la modificación
        PiezaDB modificado=new PiezaDB();
        modificado.setIdpieza(idpieza);
        modificado.setAlto(alto);
        modificado.setAncho(ancho);
        modificado.setLargo(largo);
        modificado.setNombre(nombre);
        modificado.setTipomaterial(idTM);
        modificado.setMateriaprima(idMP);
        modificado.setMatriz(idMa);
        PiezaPKDB pie=new PiezaPKDB(idpieza);

        long cantidadFilas=AccessPieza.update(modificado, pie, cn);
        
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(cantidadFilas>0) return true;
        else return false;
        
    }

    public boolean eliminarPieza(long idpieza) {
        PiezaDAO dao=new DAOFactoryImpl().createPiezaDAO();
        Connection cn=null;
        
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
       

        //realizo la eliminación

        int result=-1;
        try {

            result = dao.delete(new PiezaPKDB(idpieza), cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result>0)return true;
        else return false;
    }







    //// PARA REFERENCIAR A TIPOMATERIAL

    public long buscarTipoMaterial(TipoMaterial tipomaterial)
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
            TipomaterialDB[] tm= daoTM.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);
            if(tm.length>0) idTM=tm[0].getIdtipomaterial();

        } catch (Exception ex) {
            Logger.getLogger(GestorTipoMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idTM;
    }

    public TipoMaterial buscarTipoMaterialPorID(long id)
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
            TipomaterialDB[] tm = daoTM.findExecutingUserWhere("idtipomaterial = ?", sqlParams, cn);
            if(tm.length>0) material=parseToTipomaterial(tm[0]);

        } catch (Exception ex) {
            Logger.getLogger(GestorTipoMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }

        return material;
    }
  
    //// PARA REFERENCIAR A MATERIAPRIMA

    public long buscarMateriaPrima(MateriaPrima materiaprima)
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
            metalsoft.datos.dbobject.MateriaprimaDB[] tm = daoTM.findExecutingUserWhere("nombre = ? ", sqlParams, cn);
            if(tm.length>0) idMP=tm[0].getIdmateriaprima();

        } catch (Exception ex) {
            Logger.getLogger(GestorMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idMP;
    }
    public MateriaPrima buscarMateriaPrimaPorID(long id)
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
            MateriaprimaDB[] tm = daoTM.findExecutingUserWhere("idmateriaprima = ?", sqlParams, cn);
            if(tm.length>0) materiaprima=parseToMateriaPrima(tm[0]);

        } catch (Exception ex) {
            Logger.getLogger(GestorMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }

        return materiaprima;
    }

    //// PARA REFERENCIAR A MATRIZ

    public long buscarMatriz(Matriz matriz)
    {
        MatrizDAO daoMP=new DAOFactoryImpl().createMatrizDAO();
        Connection cn=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        long idMP=-1;
        Object[] sqlParams=new Object[2];
        sqlParams[0]=matriz.getNombre();
        sqlParams[1]=matriz.getDescripcion();

        try {
            metalsoft.datos.dbobject.MatrizDB[] tm = daoMP.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);
            if(tm.length>0) idMP=tm[0].getIdmatriz();

        } catch (Exception ex) {
            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idMP;
    }
    public Matriz buscarMatrizPorID(long id)
    {
        MatrizDAO daoM=new DAOFactoryImpl().createMatrizDAO();
        Connection cn=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        Matriz mat=null;
        Object[] sqlParams=new Object[1];
        sqlParams[0]=id;

        try {
            metalsoft.datos.dbobject.MatrizDB[] tm = daoM.findExecutingUserWhere("idmateriaprima = ?", sqlParams, cn);
            if(tm.length>0) mat=parseToMatriz(tm[0]);

        } catch (Exception ex) {
            Logger.getLogger(GestorMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mat;
    }



    ///////////////////////TODOS LOS PARSES QUE SE USAN

    //PIEZA
    private metalsoft.negocio.ventas.Pieza[] parseToPieza(PiezaDB[] tm) {
        if(tm==null)return null;

        metalsoft.negocio.ventas.Pieza[] c=new metalsoft.negocio.ventas.Pieza[tm.length];
        for(int i=0;i<tm.length;i++)
        {
            metalsoft.negocio.ventas.Pieza x=new metalsoft.negocio.ventas.Pieza();
            x.setNombre(tm[i].getNombre());
            x.setAlto(tm[i].getAlto());
            x.setAncho(tm[i].getAncho());
            x.setLargo(tm[i].getLargo());
            x.setTipoMaterial(buscarTipoMaterialPorID(tm[i].getTipomaterial()));
            x.setMateria(buscarMateriaPrimaPorID(tm[i].getMateriaprima()));
            c[i]=x;
        }
        return c;
    }
    //TIPO MATERIAL
     private TipoMaterial parseToTipomaterial(TipomaterialDB tm) {
        if(tm==null)return null;

        TipoMaterial x=new TipoMaterial();
        x.setNombre(tm.getNombre());
        x.setDescripcion(tm.getDescripcion());

        return x;
    }
     //MATERIA PRIMA
     private MateriaPrima parseToMateriaPrima(MateriaprimaDB tm) {
        if(tm==null)return null;

        MateriaPrima x=new MateriaPrima();
        x.setNombre(tm.getNombre());

        return x;
    }
    
    //PIEZA REAL
     private PiezaReal parseToPiezaReal(PiezarealDB pr) {
        if(pr==null)return null;

        PiezaReal x=new PiezaReal();
        x.setNroPieza(pr.getNropieza());

        return x;
    }
    //MATRIZ
      private Matriz parseToMatriz(metalsoft.datos.dbobject.MatrizDB m) {
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
            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<tipoMaterial.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(tipoMaterial[i].getIdtipomaterial()));
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
            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<materiaPrima.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(materiaPrima[i].getIdmateriaprima()));
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
            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<matriz.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(matriz[i].getIdmatriz()));
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

    public EtapaDeProduccion buscarEtapaParaDetalleProducto(long id) {
        EtapadeproduccionDB epDB=buscarEtapasEnEncontradas(id);
        TipomaquinaDB mDB=buscarMaquina(epDB.getTipomaquina());
        EtapaDeProduccion ep=Parser.parseToEtapaDeProduccion(epDB);
        TipoMaquina m=Parser.parseToTipoMaquina(mDB);
        ep.setMaquina(m);
        return ep;
    }

    public EtapadeproduccionDB buscarEtapasEnEncontradas(long id)
    {
        int elementos=etapasDB.length;
        EtapadeproduccionDB ep=null;
        for(int i=0;i<elementos;i++)
        {
            ep=etapasDB[i];
            if(ep.getIdetapaproduccion()==id)return ep;
        }
        return null;
    }

    private TipomaquinaDB buscarMaquina(long id) {
        return AccessTipoMaquina.findById(id);
    }

    public void setListaEtapas(JList lst) {
        lstEtapas=lst;
    }
     public JList getList() {
        return lstEtapas;
    }


        public void buscarEtapas(final String valor)
    {
        if(valor.compareTo("")!=0) {
            final GestorPieza x=this;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                private HiloBuscarEtapaDeProduccion hiloBuscarEtapaDeProduccion;
                @Override
                public void run() {
                    hiloBuscarEtapaDeProduccion=new HiloBuscarEtapaDeProduccion();
                    hiloBuscarEtapaDeProduccion.setVentana(x);
                    hiloBuscarEtapaDeProduccion.setValor(valor);
                    hiloBuscarEtapaDeProduccion.start();
                }
            }, 1500);
        }
    }

    @Override
    public JList getList(String className) {
        return lstEtapas;
    }

    @Override
    public JComboBox getCombo(String className) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setBusqueda(Object[] obj) {
        etapasDB=(EtapadeproduccionDB[]) obj;
    }

    public boolean guardarEtapasXPieza(ViewPiezaXEtapa piezaxEtapa, long idPieza) {
        PiezaxetapadeproduccionDAO dao = new DAOFactoryImpl().createPiezaxetapadeproduccionDAO();
        PiezaxetapadeproduccionDB pxe = new PiezaxetapadeproduccionDB();
        pxe.setIdetapaproduccion(piezaxEtapa.getIdEtapaProduccion());
        pxe.setIdpieza(idPieza);

        Connection cn=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }


        try {
            dao.insert(pxe, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally
        {
            if(cn!=null)
                try {
                    cn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorPieza.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
        }
        return true;
    }

}
