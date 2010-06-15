/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:39:12 GYT 2010
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
* Implementation of LocalidadRecordCountDAO interface 
* 
*/

public class LocalidadRecordCountDAOImpl implements LocalidadRecordCountDAO
{
	public LocalidadRecordCountDAOImpl()
	{
	}
	public LocalidadRecordCount getRecordCount(Connection con) throws LocalidadRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM LOCALIDAD");
			rs = stmt.executeQuery();
			rs.next();
 			LocalidadRecordCount vo = new LocalidadRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new LocalidadRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new LocalidadRecordCountException(e);
		}
	}



	public LocalidadRecordCount getRecordCount(Connection con, String whereClause) throws LocalidadRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM LOCALIDAD  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			LocalidadRecordCount vo = new LocalidadRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new LocalidadRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new LocalidadRecordCountException(e);
		}
	}
}
