/**
 *					--DAO-Generator--
 *
 * Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
 * Date of code generation: Sun Oct 17 21:47:14 ART 2010
 *
 * DAO-Generator Version 1.2
 *
 */
package metalsoft.datos.dao;

import java.math.*;
import java.sql.*;
import java.net.URL;
import java.util.Collection;
import java.util.ArrayList;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
import metalsoft.datos.idao.*;

/**
 *
 * Implementation of MpasignadaxpiezarealDAO interface
 *
 */
public class MpasignadaxpiezarealDAOImpl implements MpasignadaxpiezarealDAO {

    /**
     * Method deletes a record from table MPASIGNADAXPIEZAREAL
     * @param MpasignadaxpiezarealPK mpasignadaxpiezarealpk
     * @param  Connection  con
     * @return  int
     *
     */
    public int delete(MpasignadaxpiezarealPKDB mpasignadaxpiezarealpk, Connection con) throws MpasignadaxpiezarealException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("delete from  MPASIGNADAXPIEZAREAL where id = ?");
            ps.setLong(1, mpasignadaxpiezarealpk.getId());
            return (ps.executeUpdate());
        } catch (SQLException sqle) {
            throw new MpasignadaxpiezarealException(sqle);
        } catch (Exception e) {
            throw new MpasignadaxpiezarealException(e);
        }
    }

    /**
     * This method updates a record in table MPASIGNADAXPIEZAREAL
     * @param MpasignadaxpiezarealPK
     * @param Mpasignadaxpiezareal
     * @param  Connection con
     * @return   int
     */
    public int update(MpasignadaxpiezarealPKDB mpasignadaxpiezarealpk, MpasignadaxpiezarealDB mpasignadaxpiezareal, Connection con) throws MpasignadaxpiezarealException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("update MPASIGNADAXPIEZAREAL set IDPIEZAREAL = ? , IDDETALLEMPASIGNADA = ?  where id = ?");
            ps.setLong(1, mpasignadaxpiezareal.getIdpiezareal());
            ps.setLong(2, mpasignadaxpiezareal.getIddetallempasignada());
            ps.setLong(3, mpasignadaxpiezarealpk.getId());

            return (ps.executeUpdate());
        } catch (SQLException sqle) {
            throw new MpasignadaxpiezarealException(sqle);
        } catch (Exception e) {
            throw new MpasignadaxpiezarealException(e);
        }
    }

    /**
     * This method inserts data in table MPASIGNADAXPIEZAREAL
     *
     * @param Mpasignadaxpiezareal mpasignadaxpiezareal
     * @param   Connection con
     * @return  MpasignadaxpiezarealPK
     */
    public int insert(MpasignadaxpiezarealDB mpasignadaxpiezareal, Connection con) throws MpasignadaxpiezarealException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("insert into MPASIGNADAXPIEZAREAL( IDPIEZAREAL, IDDETALLEMPASIGNADA) values (?, ?) RETURNING ID");
            ps.setLong(1, mpasignadaxpiezareal.getIdpiezareal());
            ps.setLong(2, mpasignadaxpiezareal.getIddetallempasignada());
            rs = ps.executeQuery();
            rs.next();
            return (int) rs.getLong(1);
        } catch (SQLException sqle) {
            throw new MpasignadaxpiezarealException(sqle);
        } catch (Exception e) {
            throw new MpasignadaxpiezarealException(e);
        }
    }

    /**
     *
     * Returns a row from the mpasignadaxpiezareal table for the primary key passed as parameter.
     *
     */
    public MpasignadaxpiezarealDB findByPrimaryKey(long id, Connection con) throws MpasignadaxpiezarealException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            final String SQLSTATEMENT = "Select idpiezareal, iddetallempasignada, id from mpasignadaxpiezareal where id = ?";
            stmt = con.prepareStatement(SQLSTATEMENT);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            return fetchSingleResult(rs);
        } catch (SQLException sqle) {
            throw new MpasignadaxpiezarealException(sqle);
        } catch (Exception e) {
            throw new MpasignadaxpiezarealException(e);
        } finally {
        }
    }

    /**
     *
     * Returns a row from the mpasignadaxpiezareal table for the primary key object passed as parameter.
     *
     * @param  MpasignadaxpiezarealPK mpasignadaxpiezarealpk
     * @param Connection con
     * @return  Mpasignadaxpiezareal
     */
    public MpasignadaxpiezarealDB findByPrimaryKey(MpasignadaxpiezarealPKDB mpasignadaxpiezarealpk, Connection con) throws MpasignadaxpiezarealException {
        return findByPrimaryKey(mpasignadaxpiezarealpk.getId(), con);
    }

    /**
     *
     * Returns all rows from mpasignadaxpiezareal table where IDPIEZAREAL= idpiezareal
     *
     * @param   long  idpiezareal
     * @param   Connection con
     * @return  Mpasignadaxpiezareal[]
     */
    public MpasignadaxpiezarealDB[] findByIdpiezareal(long idpiezareal, Connection con) throws MpasignadaxpiezarealException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String SQL_STATEMENT = "Select idpiezareal, iddetallempasignada, id from mpasignadaxpiezareal where idpiezareal = ? order by idpiezareal";
        try {
            stmt = con.prepareStatement(SQL_STATEMENT);
            stmt.setLong(1, idpiezareal);
            rs = stmt.executeQuery();
            return fetchMultiResults(rs);
        } catch (SQLException sqle) {
            throw new MpasignadaxpiezarealException(sqle);
        } catch (Exception e) {
            throw new MpasignadaxpiezarealException(e);
        } finally {
        }
    }

    /**
     *
     * Returns all rows from mpasignadaxpiezareal table where IDDETALLEMPASIGNADA= iddetallempasignada
     *
     * @param   long  iddetallempasignada
     * @param   Connection con
     * @return  Mpasignadaxpiezareal[]
     */
    public MpasignadaxpiezarealDB[] findByIddetallempasignada(long iddetallempasignada, Connection con) throws MpasignadaxpiezarealException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String SQL_STATEMENT = "Select idpiezareal, iddetallempasignada, id from mpasignadaxpiezareal where iddetallempasignada = ? order by iddetallempasignada";
        try {
            stmt = con.prepareStatement(SQL_STATEMENT);
            stmt.setLong(1, iddetallempasignada);
            rs = stmt.executeQuery();
            return fetchMultiResults(rs);
        } catch (SQLException sqle) {
            throw new MpasignadaxpiezarealException(sqle);
        } catch (Exception e) {
            throw new MpasignadaxpiezarealException(e);
        } finally {
        }
    }

    /**
     *
     * Returns all rows from mpasignadaxpiezareal table where ID= id
     *
     * @param   long  id
     * @param   Connection con
     * @return  Mpasignadaxpiezareal[]
     */
    public MpasignadaxpiezarealDB[] findById(long id, Connection con) throws MpasignadaxpiezarealException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String SQL_STATEMENT = "Select idpiezareal, iddetallempasignada, id from mpasignadaxpiezareal where id = ? order by id";
        try {
            stmt = con.prepareStatement(SQL_STATEMENT);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            return fetchMultiResults(rs);
        } catch (SQLException sqle) {
            throw new MpasignadaxpiezarealException(sqle);
        } catch (Exception e) {
            throw new MpasignadaxpiezarealException(e);
        } finally {
        }
    }

    /**
     * Returns all rows from mpasignadaxpiezareal table
     *
     * @param Connection con
     * @return  Mpasignadaxpiezareal[]
     *
     */
    public MpasignadaxpiezarealDB[] findAll(Connection con) throws MpasignadaxpiezarealException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String SQL_STATEMENT = "Select idpiezareal, iddetallempasignada, id from mpasignadaxpiezareal";
        try {
            stmt = con.prepareStatement(SQL_STATEMENT);
            rs = stmt.executeQuery();
            return fetchMultiResults(rs);
        } catch (SQLException sqle) {
            throw new MpasignadaxpiezarealException(sqle);
        } catch (Exception e) {
            throw new MpasignadaxpiezarealException(e);
        } finally {
        }
    }

    /**
     * Returns rows from mpasignadaxpiezareal table by executing the passed sql statement
     * after setting the passed values in Object[]
     *
     * @param String selectStatement
     * @param Object[] sqlParams
     * @param Connection con
     * @return  Mpasignadaxpiezareal[]
     *
     */
    public MpasignadaxpiezarealDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws MpasignadaxpiezarealException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        final String SQL_STATEMENT = selectStatement;
        try {
            stmt = con.prepareStatement(SQL_STATEMENT);
            for (int i = 0; i < sqlParams.length; i++) {
                stmt.setObject(i + 1, sqlParams[i]);
            }
            rs = stmt.executeQuery();
            return fetchMultiResults(rs);
        } catch (SQLException sqle) {
            throw new MpasignadaxpiezarealException(sqle);
        } catch (Exception e) {
            throw new MpasignadaxpiezarealException(e);
        } finally {
        }
    }

    /**
     * Returns rows from mpasignadaxpiezareal table by executing the select all fields statement
     * after setting the passed where clause and values in Object[]
     *
     * @param String whereClause
     * @param Object[] sqlParams
     * @param Connection con
     * @return  Mpasignadaxpiezareal[]
     *
     */
    public MpasignadaxpiezarealDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws MpasignadaxpiezarealException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String SQL_SELECT = "Select idpiezareal, iddetallempasignada, id from mpasignadaxpiezareal";
        final String SQL_STATEMENT = SQL_SELECT + " where " + whereClause;
        try {
            stmt = con.prepareStatement(SQL_STATEMENT);
            for (int i = 0; i < sqlParams.length; i++) {
                stmt.setObject(i + 1, sqlParams[i]);
            }
            rs = stmt.executeQuery();
            return fetchMultiResults(rs);
        } catch (SQLException sqle) {
            throw new MpasignadaxpiezarealException(sqle);
        } catch (Exception e) {
            throw new MpasignadaxpiezarealException(e);
        } finally {
        }
    }

    /**
     *
     * Populates a Data Transfer Object by fetching single record from resultSet
     *
     * @param ResultSet rs
     * @return  Mpasignadaxpiezareal
     *
     */
    protected MpasignadaxpiezarealDB fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            MpasignadaxpiezarealDB dto = new MpasignadaxpiezarealDB();
            populateVO(dto, rs);
            return dto;
        } else {
            return null;
        }
    }

    /**
     *
     * Populates a Data Transfer Object by fetching data from  ResultSet
     *
     * @param Mpasignadaxpiezareal dto
     * @param   ResultSet rs
     * @return  void
     */
    protected void populateVO(MpasignadaxpiezarealDB dto, ResultSet rs) throws SQLException {
        dto.setIdpiezareal(rs.getLong("idpiezareal"));
        dto.setIddetallempasignada(rs.getLong("iddetallempasignada"));
        dto.setId(rs.getLong("id"));
    }

    /**
     *
     * Returns an array of Value Objects by fetching data from resultSet
     *
     * @param   ResultSet rs
     * @return  Mpasignadaxpiezareal[]
     */
    protected MpasignadaxpiezarealDB[] fetchMultiResults(ResultSet rs) throws SQLException {
        Collection resultList = new ArrayList();
        while (rs.next()) {
            MpasignadaxpiezarealDB dto = new MpasignadaxpiezarealDB();
            populateVO(dto, rs);
            resultList.add(dto);
        }
        MpasignadaxpiezarealDB ret[] = new MpasignadaxpiezarealDB[resultList.size()];
        resultList.toArray(ret);
        return ret;
    }
}