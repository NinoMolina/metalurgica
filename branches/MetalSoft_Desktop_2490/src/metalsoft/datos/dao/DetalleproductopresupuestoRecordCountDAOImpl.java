/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Fri Sep 03 13:20:27 ART 2010
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
* Implementation of DetalleproductopresupuestoRecordCountDAO interface 
* 
*/

public class DetalleproductopresupuestoRecordCountDAOImpl implements DetalleproductopresupuestoRecordCountDAO
{
	public DetalleproductopresupuestoRecordCountDAOImpl()
	{
	}
	public DetalleproductopresupuestoRecordCount getRecordCount(Connection con) throws DetalleproductopresupuestoRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM DETALLEPRODUCTOPRESUPUESTO");
			rs = stmt.executeQuery();
			rs.next();
 			DetalleproductopresupuestoRecordCount vo = new DetalleproductopresupuestoRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new DetalleproductopresupuestoRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new DetalleproductopresupuestoRecordCountException(e);
		}
	}



	public DetalleproductopresupuestoRecordCount getRecordCount(Connection con, String whereClause) throws DetalleproductopresupuestoRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM DETALLEPRODUCTOPRESUPUESTO  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			DetalleproductopresupuestoRecordCount vo = new DetalleproductopresupuestoRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new DetalleproductopresupuestoRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new DetalleproductopresupuestoRecordCountException(e);
		}
	}
}