/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:01 ART 2010
*
* DAO-Generator Version 1.2
*
*/
package metalsoft.datos.idao;
import java.math.*;
import java.sql.*;
import java.net.URL;
import java.util.*;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
public interface DetalleplanprocesoscalidadDAO
{
	public int insert(DetalleplanprocesoscalidadDB detalleplanprocesoscalidad, Connection con ) throws DetalleplanprocesoscalidadException;
	public int update(DetalleplanprocesoscalidadPKDB detalleplanprocesoscalidadpk, DetalleplanprocesoscalidadDB detalleplanprocesoscalidad, Connection con) throws DetalleplanprocesoscalidadException;
	public int delete(DetalleplanprocesoscalidadPKDB detalleplanprocesoscalidadpk, Connection con) throws DetalleplanprocesoscalidadException;
	public DetalleplanprocesoscalidadDB findByPrimaryKey(DetalleplanprocesoscalidadPKDB detalleplanprocesoscalidadpk , Connection con) throws DetalleplanprocesoscalidadException;
	public DetalleplanprocesoscalidadDB[] findAll(Connection con) throws DetalleplanprocesoscalidadException;
	public DetalleplanprocesoscalidadDB[] findByIddetalle(long iddetalle,Connection con) throws DetalleplanprocesoscalidadException;
	public DetalleplanprocesoscalidadDB[] findByIdplanprocesoscalidad(long idplanprocesoscalidad,Connection con) throws DetalleplanprocesoscalidadException;
	public DetalleplanprocesoscalidadDB[] findByIdpieza(long idpieza,Connection con) throws DetalleplanprocesoscalidadException;
	public DetalleplanprocesoscalidadDB[] findByIdprocesocalidad(long idprocesocalidad,Connection con) throws DetalleplanprocesoscalidadException;
	public DetalleplanprocesoscalidadDB[] findByDuracionestimada(Time duracionestimada,Connection con) throws DetalleplanprocesoscalidadException;
	public DetalleplanprocesoscalidadDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws DetalleplanprocesoscalidadException;
	public DetalleplanprocesoscalidadDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws DetalleplanprocesoscalidadException;
}