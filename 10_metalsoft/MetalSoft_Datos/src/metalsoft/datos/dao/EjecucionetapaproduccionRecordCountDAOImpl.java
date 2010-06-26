/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:38:53 GYT 2010
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
* Implementation of EjecucionetapaproduccionRecordCountDAO interface 
* 
*/

public class EjecucionetapaproduccionRecordCountDAOImpl implements EjecucionetapaproduccionRecordCountDAO
{
	public EjecucionetapaproduccionRecordCountDAOImpl()
	{
	}
	public EjecucionetapaproduccionRecordCount getRecordCount(Connection con) throws EjecucionetapaproduccionRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM EJECUCIONETAPAPRODUCCION");
			rs = stmt.executeQuery();
			rs.next();
 			EjecucionetapaproduccionRecordCount vo = new EjecucionetapaproduccionRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new EjecucionetapaproduccionRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new EjecucionetapaproduccionRecordCountException(e);
		}
	}



	public EjecucionetapaproduccionRecordCount getRecordCount(Connection con, String whereClause) throws EjecucionetapaproduccionRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM EJECUCIONETAPAPRODUCCION  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			EjecucionetapaproduccionRecordCount vo = new EjecucionetapaproduccionRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new EjecucionetapaproduccionRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new EjecucionetapaproduccionRecordCountException(e);
		}
	}
}
