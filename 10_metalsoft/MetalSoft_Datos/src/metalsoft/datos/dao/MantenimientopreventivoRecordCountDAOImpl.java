/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:09 ART 2010
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
* Implementation of MantenimientopreventivoRecordCountDAO interface 
* 
*/

public class MantenimientopreventivoRecordCountDAOImpl implements MantenimientopreventivoRecordCountDAO
{
	public MantenimientopreventivoRecordCountDAOImpl()
	{
	}
	public MantenimientopreventivoRecordCount getRecordCount(Connection con) throws MantenimientopreventivoRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM MANTENIMIENTOPREVENTIVO");
			rs = stmt.executeQuery();
			rs.next();
 			MantenimientopreventivoRecordCount vo = new MantenimientopreventivoRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new MantenimientopreventivoRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new MantenimientopreventivoRecordCountException(e);
		}
	}



	public MantenimientopreventivoRecordCount getRecordCount(Connection con, String whereClause) throws MantenimientopreventivoRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM MANTENIMIENTOPREVENTIVO  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			MantenimientopreventivoRecordCount vo = new MantenimientopreventivoRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new MantenimientopreventivoRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new MantenimientopreventivoRecordCountException(e);
		}
	}
}
