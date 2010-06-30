/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.Materiaprima;
import metalsoft.datos.dbobject.Matriz;
import metalsoft.datos.dbobject.Tipomaterial;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.MateriaprimaDAO;
import metalsoft.datos.idao.MatrizDAO;
import metalsoft.datos.idao.TipomaterialDAO;
import metalsoft.negocio.ItemCombo;

/**
 *
 * @author Mariana
 */
public class GestorMatriz {
    private Tipomaterial[] tipoMateriales;
    private Materiaprima[] materiaPrima;
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

     public int guardarMatriz(long codigo, String nombre, String descripcion, int materiaPrima, int tipoMaterial) throws SQLException {
        MatrizDAO dao=new DAOFactoryImpl().createMatrizDAO();
        Matriz m = new Matriz();
        m.setCodmatriz(codigo);
        m.setNombre(nombre);
        m.setDescripcion(descripcion);
        m.setMateriaprima(this.materiaPrima[materiaPrima].getIdmateriaprima());
        m.setTipomaterial(this.tipoMateriales[tipoMaterial].getIdtipomaterial());
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
            combo.addItem("--Seleccionar--");
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
      public Matriz[] buscarMatriz(String valor) {
        MatrizDAO dao=new DAOFactoryImpl().createMatrizDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.Matriz[] m=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorMatriz.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            m = dao.findExecutingUserWhere("nombre ILIKE '"+valor+"%'", sqlParams, cn);
            cn.close();
        } catch (Exception ex) {
            Logger.getLogger(GestorMatriz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parseToMatriz(m);
    }
      private Matriz[] parseToMatriz(Matriz[] m) {
        if(m==null)return null;

        Matriz[] mat=new Matriz[m.length];
        for(int i=0;i<m.length;i++)
        {
            Matriz x=new Matriz();
            x.setNombre(m[i].getNombre());
            x.setCodmatriz(m[i].getCodmatriz());
            x.setDescripcion(m[i].getDescripcion());
            x.setMateriaprima(m[i].getMateriaprima());
            mat[i]=x;
        }
        return mat;
    }


}
