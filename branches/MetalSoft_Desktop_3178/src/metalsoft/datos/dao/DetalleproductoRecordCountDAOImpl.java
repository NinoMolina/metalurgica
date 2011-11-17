/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Aug 10 20:31:10 ART 2010
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
* Implementation of DetalleproductoRecordCountDAO interface 
* 
*/

public class DetalleproductoRecordCountDAOImpl implements DetalleproductoRecordCountDAO
{
	public DetalleproductoRecordCountDAOImpl()
	{
	}
	public DetalleproductoRecordCount getRecordCount(Connection con) throws DetalleproductoRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM DETALLEPRODUCTO");
			rs = stmt.executeQuery();
			rs.next();
 			DetalleproductoRecordCount vo = new DetalleproductoRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new DetalleproductoRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new DetalleproductoRecordCountException(e);
		}
	}



	public DetalleproductoRecordCount getRecordCount(Connection con, String whereClause) throws DetalleproductoRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM DETALLEPRODUCTO  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			DetalleproductoRecordCount vo = new DetalleproductoRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new DetalleproductoRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new DetalleproductoRecordCountException(e);
		}
	}
}