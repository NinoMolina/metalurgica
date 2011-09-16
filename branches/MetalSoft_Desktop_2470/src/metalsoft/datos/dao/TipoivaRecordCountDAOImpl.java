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
* Implementation of TipoivaRecordCountDAO interface 
* 
*/

public class TipoivaRecordCountDAOImpl implements TipoivaRecordCountDAO
{
	public TipoivaRecordCountDAOImpl()
	{
	}
	public TipoivaRecordCount getRecordCount(Connection con) throws TipoivaRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM TIPOIVA");
			rs = stmt.executeQuery();
			rs.next();
 			TipoivaRecordCount vo = new TipoivaRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new TipoivaRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new TipoivaRecordCountException(e);
		}
	}



	public TipoivaRecordCount getRecordCount(Connection con, String whereClause) throws TipoivaRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM TIPOIVA  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			TipoivaRecordCount vo = new TipoivaRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new TipoivaRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new TipoivaRecordCountException(e);
		}
	}
}
