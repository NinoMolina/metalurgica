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
import java.util.*;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
import metalsoft.datos.idao.*;

/**
* 
* Implementation of DetallempasignadaRecordCountDAO interface 
* 
*/

public class DetallempasignadaRecordCountDAOImpl implements DetallempasignadaRecordCountDAO
{
	public DetallempasignadaRecordCountDAOImpl()
	{
	}
	public DetallempasignadaRecordCount getRecordCount(Connection con) throws DetallempasignadaRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM DETALLEMPASIGNADA");
			rs = stmt.executeQuery();
			rs.next();
 			DetallempasignadaRecordCount vo = new DetallempasignadaRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new DetallempasignadaRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new DetallempasignadaRecordCountException(e);
		}
	}



	public DetallempasignadaRecordCount getRecordCount(Connection con, String whereClause) throws DetallempasignadaRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM DETALLEMPASIGNADA  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			DetallempasignadaRecordCount vo = new DetallempasignadaRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new DetallempasignadaRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new DetallempasignadaRecordCountException(e);
		}
	}
}
