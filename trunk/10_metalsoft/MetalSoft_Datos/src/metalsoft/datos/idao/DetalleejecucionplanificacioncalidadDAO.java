/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:08 ART 2010
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
public interface DetalleejecucionplanificacioncalidadDAO
{
	public int insert(Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad, Connection con ) throws DetalleejecucionplanificacioncalidadException;
	public int update(DetalleejecucionplanificacioncalidadPK detalleejecucionplanificacioncalidadpk, Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad, Connection con) throws DetalleejecucionplanificacioncalidadException;
	public int delete(DetalleejecucionplanificacioncalidadPK detalleejecucionplanificacioncalidadpk, Connection con) throws DetalleejecucionplanificacioncalidadException;
	public Detalleejecucionplanificacioncalidad findByPrimaryKey(DetalleejecucionplanificacioncalidadPK detalleejecucionplanificacioncalidadpk , Connection con) throws DetalleejecucionplanificacioncalidadException;
	public Detalleejecucionplanificacioncalidad[] findAll(Connection con) throws DetalleejecucionplanificacioncalidadException;
	public Detalleejecucionplanificacioncalidad[] findByIddetalle(long iddetalle,Connection con) throws DetalleejecucionplanificacioncalidadException;
	public Detalleejecucionplanificacioncalidad[] findByIdejecucionplanificacioncalidad(long idejecucionplanificacioncalidad,Connection con) throws DetalleejecucionplanificacioncalidadException;
	public Detalleejecucionplanificacioncalidad[] findByIdplanificacioncalidad(long idplanificacioncalidad,Connection con) throws DetalleejecucionplanificacioncalidadException;
	public Detalleejecucionplanificacioncalidad[] findByEjecucionprocesocalidad(long ejecucionprocesocalidad,Connection con) throws DetalleejecucionplanificacioncalidadException;
	public Detalleejecucionplanificacioncalidad[] findByIdprocesocalidad(long idprocesocalidad,Connection con) throws DetalleejecucionplanificacioncalidadException;
	public Detalleejecucionplanificacioncalidad[] findByPieza(long pieza,Connection con) throws DetalleejecucionplanificacioncalidadException;
	public Detalleejecucionplanificacioncalidad[] findByPiezareal(long piezareal,Connection con) throws DetalleejecucionplanificacioncalidadException;
	public Detalleejecucionplanificacioncalidad[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws DetalleejecucionplanificacioncalidadException;
	public Detalleejecucionplanificacioncalidad[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws DetalleejecucionplanificacioncalidadException;
}
