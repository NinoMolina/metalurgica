/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:03 ART 2010
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
* Implementation of EstadotrabajotercerizadoRecordCountDAO interface 
* 
*/

public class EstadotrabajotercerizadoRecordCountDAOImpl implements EstadotrabajotercerizadoRecordCountDAO
{
	public EstadotrabajotercerizadoRecordCountDAOImpl()
	{
	}
	public EstadotrabajotercerizadoRecordCount getRecordCount(Connection con) throws EstadotrabajotercerizadoRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM ESTADOTRABAJOTERCERIZADO");
			rs = stmt.executeQuery();
			rs.next();
 			EstadotrabajotercerizadoRecordCount vo = new EstadotrabajotercerizadoRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new EstadotrabajotercerizadoRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new EstadotrabajotercerizadoRecordCountException(e);
		}
	}



	public EstadotrabajotercerizadoRecordCount getRecordCount(Connection con, String whereClause) throws EstadotrabajotercerizadoRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM ESTADOTRABAJOTERCERIZADO  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			EstadotrabajotercerizadoRecordCount vo = new EstadotrabajotercerizadoRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new EstadotrabajotercerizadoRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new EstadotrabajotercerizadoRecordCountException(e);
		}
	}
}