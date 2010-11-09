/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:03 ART 2010
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
* Implementation of PlanificacioncalidadRecordCountDAO interface 
* 
*/

public class PlanificacioncalidadRecordCountDAOImpl implements PlanificacioncalidadRecordCountDAO
{
	public PlanificacioncalidadRecordCountDAOImpl()
	{
	}
	public PlanificacioncalidadRecordCount getRecordCount(Connection con) throws PlanificacioncalidadRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM PLANIFICACIONCALIDAD");
			rs = stmt.executeQuery();
			rs.next();
 			PlanificacioncalidadRecordCount vo = new PlanificacioncalidadRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new PlanificacioncalidadRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new PlanificacioncalidadRecordCountException(e);
		}
	}



	public PlanificacioncalidadRecordCount getRecordCount(Connection con, String whereClause) throws PlanificacioncalidadRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM PLANIFICACIONCALIDAD  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			PlanificacioncalidadRecordCount vo = new PlanificacioncalidadRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new PlanificacioncalidadRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new PlanificacioncalidadRecordCountException(e);
		}
	}
}