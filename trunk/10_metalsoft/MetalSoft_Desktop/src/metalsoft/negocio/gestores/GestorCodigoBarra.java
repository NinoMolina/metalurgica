/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.CodigodebarraDB;
import metalsoft.datos.dbobject.CodigodebarraPKDB;
import metalsoft.datos.dbobject.PiezarealDB;
import metalsoft.datos.dbobject.PiezarealPKDB;
import metalsoft.datos.dbobject.ProductorealDB;
import metalsoft.datos.dbobject.ProductorealPKDB;
import metalsoft.datos.exception.CodigodebarraException;
import metalsoft.datos.exception.PiezarealException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.CodigodebarraDAO;
import metalsoft.datos.idao.PiezarealDAO;
import metalsoft.datos.idao.ProductorealDAO;
import metalsoft.negocio.access.AccessPiezaReal;
import metalsoft.negocio.produccion.CodigoDeBarra;

/**
 *
 * @author Vicky
 */
public class GestorCodigoBarra {

    public CodigodebarraDB[] buscarConILIKE(String valor) {
        Connection cn = null;
        PostgreSQLManager pg = null;
        CodigodebarraDB[] x = null;
        CodigodebarraDAO dao = new DAOFactoryImpl().createCodigodebarraDAO();
        Object[] sqlParams = new Object[0];
        try {
            pg = new PostgreSQLManager();
            cn = pg.concectGetCn();
            x = dao.findExecutingUserWhere("nombre ILIKE '" + valor + "%'", sqlParams, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return x;
    }

    public CodigodebarraDB buscarPorId(long valor) {
        Connection cn = null;
        PostgreSQLManager pg = null;
        CodigodebarraDB db = null;
        CodigodebarraDAO dao = new DAOFactoryImpl().createCodigodebarraDAO();
        try {
            pg = new PostgreSQLManager();
            cn = pg.concectGetCn();
            db = dao.findByIdcodigo(valor, cn)[0];
        } catch (Exception ex) {
            Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return db;
    }

    public boolean eliminar(long id) {
        CodigodebarraDAO dao = new DAOFactoryImpl().createCodigodebarraDAO();
        Connection cn = null;

        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
        }

        long result = -1;

        CodigodebarraPKDB pk = new CodigodebarraPKDB(id);
        try {
            result = dao.delete(pk, cn);
        } catch (CodigodebarraException ex) {
            Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cn.close();
            cn = null;
        } catch (SQLException ex) {
            Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public long guardarCodPieza(CodigoDeBarra codBarra, long idPiezaReal) {
        CodigodebarraDAO dao = new DAOFactoryImpl().createCodigodebarraDAO();
        CodigodebarraDB p = new CodigodebarraDB();
        p.setDescripcion(codBarra.getDescripcion());
        PiezarealDB prdb = new PiezarealDB();

        int id = -1;
        int idPieza = -1;

        Connection cn = null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
            prdb = AccessPiezaReal.findById(idPiezaReal, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
        }


        try {
            id = dao.insert(p, cn);
            p.setIdcodigo(id);
            p.setCodigo("PIE" + idPiezaReal);
            prdb.setIdcodbarra(id);

            CodigodebarraPKDB cpk = new CodigodebarraPKDB(id);
            id = dao.update(cpk, p, cn);

            PiezarealDAO daoPieza = new DAOFactoryImpl().createPiezarealDAO();
            PiezarealPKDB pipk = new PiezarealPKDB(prdb.getIdpiezareal(), prdb.getIdpieza());
            idPieza = daoPieza.update(pipk, prdb, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return id;
    }

    public long guardarCodProducto(CodigoDeBarra codBarra, long idPiezaReal) {
        CodigodebarraDAO dao = new DAOFactoryImpl().createCodigodebarraDAO();
        CodigodebarraDB p = new CodigodebarraDB();
        p.setDescripcion(codBarra.getDescripcion());
        ProductorealDB pr = new ProductorealDB();

        int id = -1;
        int idPieza = -1;

        Connection cn = null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        ///Buscar el Prducto Real con su access!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        } catch (Exception ex) {
            Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
        }


        try {
            id = dao.insert(p, cn);
            p.setIdcodigo(id);
            p.setCodigo("PIE" + id);
            pr.setCodigobarra(id);

            CodigodebarraPKDB cpk = new CodigodebarraPKDB(id);
            id = dao.update(cpk, p, cn);

            ProductorealDAO daoPieza = new DAOFactoryImpl().createProductorealDAO();
            ProductorealPKDB pipk = new ProductorealPKDB(pr.getIdproductoreal());
            idPieza = daoPieza.update(pipk, pr, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return id;
    }
}
