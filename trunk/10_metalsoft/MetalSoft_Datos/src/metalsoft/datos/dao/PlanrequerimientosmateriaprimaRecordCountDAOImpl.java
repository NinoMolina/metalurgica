/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:40:17 ART 2010
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
* Implementation of PlanrequerimientosmateriaprimaRecordCountDAO interface 
* 
*/

public class PlanrequerimientosmateriaprimaRecordCountDAOImpl implements PlanrequerimientosmateriaprimaRecordCountDAO
{
	public PlanrequerimientosmateriaprimaRecordCountDAOImpl()
	{
	}
	public PlanrequerimientosmateriaprimaRecordCount getRecordCount(Connection con) throws PlanrequerimientosmateriaprimaRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM PLANREQUERIMIENTOSMATERIAPRIMA");
			rs = stmt.executeQuery();
			rs.next();
 			PlanrequerimientosmateriaprimaRecordCount vo = new PlanrequerimientosmateriaprimaRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new PlanrequerimientosmateriaprimaRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new PlanrequerimientosmateriaprimaRecordCountException(e);
		}
	}



	public PlanrequerimientosmateriaprimaRecordCount getRecordCount(Connection con, String whereClause) throws PlanrequerimientosmateriaprimaRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM PLANREQUERIMIENTOSMATERIAPRIMA  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			PlanrequerimientosmateriaprimaRecordCount vo = new PlanrequerimientosmateriaprimaRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new PlanrequerimientosmateriaprimaRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new PlanrequerimientosmateriaprimaRecordCountException(e);
		}
	}
}
