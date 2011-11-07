/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;


import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.swing.JComboBox;
import metalsoft.datos.PostgreSQLManager;

import metalsoft.datos.dbobject.MateriaprimaDB;
import metalsoft.datos.dbobject.MatrizPKDB;
import metalsoft.datos.dbobject.TipomaterialDB;
import metalsoft.datos.exception.MatrizException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.MateriaprimaDAO;
import metalsoft.datos.idao.MatrizDAO;
import metalsoft.datos.idao.TipomaterialDAO;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.MatrizJpaController;
import metalsoft.datos.jpa.entity.Matriz;
import metalsoft.util.ItemCombo;


/**
 *
 * @author Mariana
 */
public class GestorMatriz {
    private TipomaterialDB[] tipoMateriales;
    private MateriaprimaDB[] materiaPrima;
    private Matriz matriz;

     public GestorMatriz()
      {}

    public Matriz getMatriz() {
        return matriz;
    }

    public void setMatriz(Matriz matriz) {
        this.matriz = matriz;
    }
     public void tomarMatrizSeleccionada(Matriz mat)
     {
         setMatriz(mat);
     }

     public ItemCombo getItemMateriaPrima(long id)
     {
         ItemCombo item=null;
         for(int i=0;i<materiaPrima.length;i++)
         {
             if(materiaPrima[i].getIdmateriaprima()==id)
             {
                 item=new ItemCombo(String.valueOf(i), materiaPrima[i].getNombre());
             }
         }
         return item;
     }

    public int getIndexMateriaPrima(long id) {
        for(int i=0;i<materiaPrima.length;i++)
         {
             if(materiaPrima[i].getIdmateriaprima()==id)
             {
                 //mas 1 porque en [0] esta --seleccionar--
                 return i+1;
             }
         }
         return -1;
    }

    public int getIndexTipoMaterial(long id) {
        for(int i=0;i<tipoMateriales.length;i++)
         {
             if(tipoMateriales[i].getIdtipomaterial()==id)
             {
                 //mas 1 porque en [0] esta --seleccionar--
                 return i+1;
             }
         }
         return -1;
    }

     public int guardarMatriz(long codigo, String nombre, String descripcion, int materiaPrima, int tipoMaterial) throws SQLException {
        MatrizDAO dao=new DAOFactoryImpl().createMatrizDAO();
        metalsoft.datos.dbobject.MatrizDB m = new metalsoft.datos.dbobject.MatrizDB();
        m.setCodmatriz(codigo);
        m.setNombre(nombre);
        m.setDescripcion(descripcion);

        long idTM=-1;
        long idMP=-1;

        int iTM= tipoMaterial;
        if(iTM!=-1) idTM=tipoMateriales[iTM].getIdtipomaterial();
        int iMP= materiaPrima;
        if(iMP!=-1) idMP=this.materiaPrima[iMP].getIdmateriaprima();


        m.setTipomaterial(idTM);
        m.setMateriaprima(idMP);

//        m.setMateriaprima(this.materiaPrima[materiaPrima].getIdmateriaprima());
//        m.setTipomaterial(this.tipoMateriales[tipoMaterial].getIdtipomaterial());

        Connection cn=null;
        int id=-1;
        try {
            cn = new PostgreSQLManager().concectGetCn();
            id=dao.insert(m, cn);
        } catch (Exception ex) {
            Logger.getLogger("hgfjhf").log(Level.SEVERE, null, ex);
        }
        finally
        {
            if(cn!=null)try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestorMatriz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return id;
    }
     public void buscarTipoMaterial(JComboBox combo)
   {
        TipomaterialDAO dao=new DAOFactoryImpl().createTipomaterialDAO();
        PostgreSQLManager pg=new PostgreSQLManager();

        try {
            tipoMateriales = dao.findAll(pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<tipoMateriales.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(i));
                item.setMostrar(tipoMateriales[i].getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorMatriz.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorMatriz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
   }
     public void buscarMateriaPrima(JComboBox combo)
   {
        MateriaprimaDAO dao=new DAOFactoryImpl().createMateriaprimaDAO();
        PostgreSQLManager pg=new PostgreSQLManager();

        try {
            materiaPrima = dao.findAll(pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<materiaPrima.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(i));
                item.setMostrar(materiaPrima[i].getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorMatriz.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorMatriz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
   }
      public List<Matriz> buscarMatriz(String valor) {
        List<Matriz> lstResultMatriz=new LinkedList<Matriz>();
        try {
            Query query = JpaUtil.getNamedQuery("Matriz.findByNombreLike");
            query.setParameter("nombre", valor+"%");
            lstResultMatriz = query.getResultList();
            
        } catch (Exception ex) {
            Logger.getLogger(GestorMatriz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstResultMatriz;
    }
      
      public boolean modificarMatriz(Matriz matriz, long codigo, String nombre, String descripcion, int materiaPrima, int tipoMaterial) {
        MatrizDAO dao=new DAOFactoryImpl().createMatrizDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.MatrizDB[] m=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorMatriz.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Object[] sqlParams=new Object[0];
        Object[] sqlParams=new Object[2];
//        sqlParams[0]=matriz.getCodmatriz();
        sqlParams[0]=matriz.getNombre();
        sqlParams[1]=matriz.getDescripcion();
//        sqlParams[5]=matriz.getMateriaprima();
//        sqlParams[6]=matriz.getTipomaterial();
        try {
            m = dao.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorMatriz.class.getName()).log(Level.SEVERE, null, ex);
        }
        long id=-1;
        if(m.length>0)id=m[0].getIdmatriz();
        else return false;
        //realizo la modificación
        metalsoft.datos.dbobject.MatrizDB modificada =new metalsoft.datos.dbobject.MatrizDB();
         modificada.setCodmatriz(codigo);
         modificada.setNombre(nombre);
         modificada.setDescripcion(descripcion);

        long idTM=-1;
        long idMP=-1;

        int iTM= tipoMaterial;
        if(iTM!=-1) idTM=tipoMateriales[iTM].getIdtipomaterial();
        int iMP= materiaPrima;
        if(iMP!=-1) idMP=this.materiaPrima[iMP].getIdmateriaprima();


        modificada.setTipomaterial(idTM);
        modificada.setMateriaprima(idMP);
        modificada.setIdmatriz(id);
        int result=-1;
        try {
            result = dao.update(new MatrizPKDB(id), modificada, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorMatriz.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorMatriz.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result>0)return true;
        else return false;
    }
       public boolean eliminarMatriz(Matriz matriz) {
        MatrizDAO dao=new DAOFactoryImpl().createMatrizDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.MatrizDB[] m=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorMatriz.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Object[] sqlParams=new Object[0];
        Object[] sqlParams=new Object[2];
        sqlParams[0]=matriz.getNombre();
        sqlParams[1]=matriz.getDescripcion();
        try {
//            deberia buscar por todos los parametros.
            m = dao.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorTipoMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
        long id=-1;
        if(m.length>0)id=m[0].getIdmatriz();
        else return false;

        //realizo la eliminación

        int result=-1;
        try {
            result = dao.delete(new MatrizPKDB(id), cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorMatriz.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorMatriz.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result>0)return true;
        else return false;
    }



}
