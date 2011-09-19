/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:02 ART 2010
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
* Implementation of DetalletrabajotercerizadoRecordCountDAO interface 
* 
*/

public class DetalletrabajotercerizadoRecordCountDAOImpl implements DetalletrabajotercerizadoRecordCountDAO
{
	public DetalletrabajotercerizadoRecordCountDAOImpl()
	{
	}
	public DetalletrabajotercerizadoRecordCount getRecordCount(Connection con) throws DetalletrabajotercerizadoRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM DETALLETRABAJOTERCERIZADO");
			rs = stmt.executeQuery();
			rs.next();
 			DetalletrabajotercerizadoRecordCount vo = new DetalletrabajotercerizadoRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new DetalletrabajotercerizadoRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new DetalletrabajotercerizadoRecordCountException(e);
		}
	}



	public DetalletrabajotercerizadoRecordCount getRecordCount(Connection con, String whereClause) throws DetalletrabajotercerizadoRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM DETALLETRABAJOTERCERIZADO  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			DetalletrabajotercerizadoRecordCount vo = new DetalletrabajotercerizadoRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new DetalletrabajotercerizadoRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new DetalletrabajotercerizadoRecordCountException(e);
		}
	}
}