/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:39:44 GYT 2010
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
* Implementation of ProcesocalidadRecordCountDAO interface 
* 
*/

public class ProcesocalidadRecordCountDAOImpl implements ProcesocalidadRecordCountDAO
{
	public ProcesocalidadRecordCountDAOImpl()
	{
	}
	public ProcesocalidadRecordCount getRecordCount(Connection con) throws ProcesocalidadRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM PROCESOCALIDAD");
			rs = stmt.executeQuery();
			rs.next();
 			ProcesocalidadRecordCount vo = new ProcesocalidadRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new ProcesocalidadRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new ProcesocalidadRecordCountException(e);
		}
	}



	public ProcesocalidadRecordCount getRecordCount(Connection con, String whereClause) throws ProcesocalidadRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM PROCESOCALIDAD  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			ProcesocalidadRecordCount vo = new ProcesocalidadRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new ProcesocalidadRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new ProcesocalidadRecordCountException(e);
		}
	}
}
