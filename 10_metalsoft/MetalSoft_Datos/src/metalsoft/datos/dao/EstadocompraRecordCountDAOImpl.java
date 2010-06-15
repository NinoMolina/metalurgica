/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:39:01 GYT 2010
*
* DAO-Generator Version 1.2
*
*/

package metalsoft.datos.dao;
import java.math.*;
import java.sql.*;
import java.net.URL;
import java.util.*;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
import metalsoft.datos.idao.*;

/**
* 
* Implementation of EstadocompraRecordCountDAO interface 
* 
*/

public class EstadocompraRecordCountDAOImpl implements EstadocompraRecordCountDAO
{
	public EstadocompraRecordCountDAOImpl()
	{
	}
	public EstadocompraRecordCount getRecordCount(Connection con) throws EstadocompraRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM ESTADOCOMPRA");
			rs = stmt.executeQuery();
			rs.next();
 			EstadocompraRecordCount vo = new EstadocompraRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new EstadocompraRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new EstadocompraRecordCountException(e);
		}
	}



	public EstadocompraRecordCount getRecordCount(Connection con, String whereClause) throws EstadocompraRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM ESTADOCOMPRA  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			EstadocompraRecordCount vo = new EstadocompraRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new EstadocompraRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new EstadocompraRecordCountException(e);
		}
	}
}
