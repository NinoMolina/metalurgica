/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:39:51 GYT 2010
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
* Implementation of ProveedormantenimientomaquinaRecordCountDAO interface 
* 
*/

public class ProveedormantenimientomaquinaRecordCountDAOImpl implements ProveedormantenimientomaquinaRecordCountDAO
{
	public ProveedormantenimientomaquinaRecordCountDAOImpl()
	{
	}
	public ProveedormantenimientomaquinaRecordCount getRecordCount(Connection con) throws ProveedormantenimientomaquinaRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM PROVEEDORMANTENIMIENTOMAQUINA");
			rs = stmt.executeQuery();
			rs.next();
 			ProveedormantenimientomaquinaRecordCount vo = new ProveedormantenimientomaquinaRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new ProveedormantenimientomaquinaRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new ProveedormantenimientomaquinaRecordCountException(e);
		}
	}



	public ProveedormantenimientomaquinaRecordCount getRecordCount(Connection con, String whereClause) throws ProveedormantenimientomaquinaRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM PROVEEDORMANTENIMIENTOMAQUINA  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			ProveedormantenimientomaquinaRecordCount vo = new ProveedormantenimientomaquinaRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new ProveedormantenimientomaquinaRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new ProveedormantenimientomaquinaRecordCountException(e);
		}
	}
}
