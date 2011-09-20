/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:01 ART 2010
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
* Implementation of DetalleplanprocesoscalidadRecordCountDAO interface 
* 
*/

public class DetalleplanprocesoscalidadRecordCountDAOImpl implements DetalleplanprocesoscalidadRecordCountDAO
{
	public DetalleplanprocesoscalidadRecordCountDAOImpl()
	{
	}
	public DetalleplanprocesoscalidadRecordCount getRecordCount(Connection con) throws DetalleplanprocesoscalidadRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM DETALLEPLANPROCESOSCALIDAD");
			rs = stmt.executeQuery();
			rs.next();
 			DetalleplanprocesoscalidadRecordCount vo = new DetalleplanprocesoscalidadRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new DetalleplanprocesoscalidadRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new DetalleplanprocesoscalidadRecordCountException(e);
		}
	}



	public DetalleplanprocesoscalidadRecordCount getRecordCount(Connection con, String whereClause) throws DetalleplanprocesoscalidadRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM DETALLEPLANPROCESOSCALIDAD  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			DetalleplanprocesoscalidadRecordCount vo = new DetalleplanprocesoscalidadRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new DetalleplanprocesoscalidadRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new DetalleplanprocesoscalidadRecordCountException(e);
		}
	}
}