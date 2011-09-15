/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Oct 17 02:56:00 ART 2010
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
* Implementation of EstadoplanificacionproduccionRecordCountDAO interface 
* 
*/

public class EstadoplanificacionproduccionRecordCountDAOImpl implements EstadoplanificacionproduccionRecordCountDAO
{
	public EstadoplanificacionproduccionRecordCountDAOImpl()
	{
	}
	public EstadoplanificacionproduccionRecordCount getRecordCount(Connection con) throws EstadoplanificacionproduccionRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM ESTADOPLANIFICACIONPRODUCCION");
			rs = stmt.executeQuery();
			rs.next();
 			EstadoplanificacionproduccionRecordCount vo = new EstadoplanificacionproduccionRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new EstadoplanificacionproduccionRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new EstadoplanificacionproduccionRecordCountException(e);
		}
	}



	public EstadoplanificacionproduccionRecordCount getRecordCount(Connection con, String whereClause) throws EstadoplanificacionproduccionRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM ESTADOPLANIFICACIONPRODUCCION  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			EstadoplanificacionproduccionRecordCount vo = new EstadoplanificacionproduccionRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new EstadoplanificacionproduccionRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new EstadoplanificacionproduccionRecordCountException(e);
		}
	}
}
