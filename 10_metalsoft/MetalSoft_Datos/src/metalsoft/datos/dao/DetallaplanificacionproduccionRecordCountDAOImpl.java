/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:01 ART 2010
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
* Implementation of DetallaplanificacionproduccionRecordCountDAO interface 
* 
*/

public class DetallaplanificacionproduccionRecordCountDAOImpl implements DetallaplanificacionproduccionRecordCountDAO
{
	public DetallaplanificacionproduccionRecordCountDAOImpl()
	{
	}
	public DetallaplanificacionproduccionRecordCount getRecordCount(Connection con) throws DetallaplanificacionproduccionRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM DETALLAPLANIFICACIONPRODUCCION");
			rs = stmt.executeQuery();
			rs.next();
 			DetallaplanificacionproduccionRecordCount vo = new DetallaplanificacionproduccionRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new DetallaplanificacionproduccionRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new DetallaplanificacionproduccionRecordCountException(e);
		}
	}



	public DetallaplanificacionproduccionRecordCount getRecordCount(Connection con, String whereClause) throws DetallaplanificacionproduccionRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM DETALLAPLANIFICACIONPRODUCCION  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			DetallaplanificacionproduccionRecordCount vo = new DetallaplanificacionproduccionRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new DetallaplanificacionproduccionRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new DetallaplanificacionproduccionRecordCountException(e);
		}
	}
}
