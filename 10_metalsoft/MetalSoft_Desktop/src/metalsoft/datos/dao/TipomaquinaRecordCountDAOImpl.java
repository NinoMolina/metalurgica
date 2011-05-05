/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:05 ART 2010
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
* Implementation of TipomaquinaRecordCountDAO interface 
* 
*/

public class TipomaquinaRecordCountDAOImpl implements TipomaquinaRecordCountDAO
{
	public TipomaquinaRecordCountDAOImpl()
	{
	}
	public TipomaquinaRecordCount getRecordCount(Connection con) throws TipomaquinaRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM TIPOMAQUINA");
			rs = stmt.executeQuery();
			rs.next();
 			TipomaquinaRecordCount vo = new TipomaquinaRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new TipomaquinaRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new TipomaquinaRecordCountException(e);
		}
	}



	public TipomaquinaRecordCount getRecordCount(Connection con, String whereClause) throws TipomaquinaRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM TIPOMAQUINA  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			TipomaquinaRecordCount vo = new TipomaquinaRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new TipomaquinaRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new TipomaquinaRecordCountException(e);
		}
	}
}