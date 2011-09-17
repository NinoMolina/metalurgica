/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:02 ART 2010
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
* Implementation of EstadoejecplanifpedidoRecordCountDAO interface 
* 
*/

public class EstadoejecplanifpedidoRecordCountDAOImpl implements EstadoejecplanifpedidoRecordCountDAO
{
	public EstadoejecplanifpedidoRecordCountDAOImpl()
	{
	}
	public EstadoejecplanifpedidoRecordCount getRecordCount(Connection con) throws EstadoejecplanifpedidoRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM ESTADOEJECPLANIFPEDIDO");
			rs = stmt.executeQuery();
			rs.next();
 			EstadoejecplanifpedidoRecordCount vo = new EstadoejecplanifpedidoRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new EstadoejecplanifpedidoRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new EstadoejecplanifpedidoRecordCountException(e);
		}
	}



	public EstadoejecplanifpedidoRecordCount getRecordCount(Connection con, String whereClause) throws EstadoejecplanifpedidoRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM ESTADOEJECPLANIFPEDIDO  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			EstadoejecplanifpedidoRecordCount vo = new EstadoejecplanifpedidoRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new EstadoejecplanifpedidoRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new EstadoejecplanifpedidoRecordCountException(e);
		}
	}
}
