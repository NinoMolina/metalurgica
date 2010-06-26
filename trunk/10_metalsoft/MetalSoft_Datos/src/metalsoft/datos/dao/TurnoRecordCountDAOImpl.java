/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:40:20 GYT 2010
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
* Implementation of TurnoRecordCountDAO interface 
* 
*/

public class TurnoRecordCountDAOImpl implements TurnoRecordCountDAO
{
	public TurnoRecordCountDAOImpl()
	{
	}
	public TurnoRecordCount getRecordCount(Connection con) throws TurnoRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM TURNO");
			rs = stmt.executeQuery();
			rs.next();
 			TurnoRecordCount vo = new TurnoRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new TurnoRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new TurnoRecordCountException(e);
		}
	}



	public TurnoRecordCount getRecordCount(Connection con, String whereClause) throws TurnoRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM TURNO  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			TurnoRecordCount vo = new TurnoRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new TurnoRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new TurnoRecordCountException(e);
		}
	}
}
