/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:08 ART 2010
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
* Implementation of DetallereclamoproveedorRecordCountDAO interface 
* 
*/

public class DetallereclamoproveedorRecordCountDAOImpl implements DetallereclamoproveedorRecordCountDAO
{
	public DetallereclamoproveedorRecordCountDAOImpl()
	{
	}
	public DetallereclamoproveedorRecordCount getRecordCount(Connection con) throws DetallereclamoproveedorRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM DETALLERECLAMOPROVEEDOR");
			rs = stmt.executeQuery();
			rs.next();
 			DetallereclamoproveedorRecordCount vo = new DetallereclamoproveedorRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new DetallereclamoproveedorRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new DetallereclamoproveedorRecordCountException(e);
		}
	}



	public DetallereclamoproveedorRecordCount getRecordCount(Connection con, String whereClause) throws DetallereclamoproveedorRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM DETALLERECLAMOPROVEEDOR  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			DetallereclamoproveedorRecordCount vo = new DetallereclamoproveedorRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new DetallereclamoproveedorRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new DetallereclamoproveedorRecordCountException(e);
		}
	}
}
