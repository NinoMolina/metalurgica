/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:39:21 GYT 2010
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
* Implementation of MarcaRecordCountDAO interface 
* 
*/

public class MarcaRecordCountDAOImpl implements MarcaRecordCountDAO
{
	public MarcaRecordCountDAOImpl()
	{
	}
	public MarcaRecordCount getRecordCount(Connection con) throws MarcaRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM MARCA");
			rs = stmt.executeQuery();
			rs.next();
 			MarcaRecordCount vo = new MarcaRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new MarcaRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new MarcaRecordCountException(e);
		}
	}



	public MarcaRecordCount getRecordCount(Connection con, String whereClause) throws MarcaRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM MARCA  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			MarcaRecordCount vo = new MarcaRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new MarcaRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new MarcaRecordCountException(e);
		}
	}
}
