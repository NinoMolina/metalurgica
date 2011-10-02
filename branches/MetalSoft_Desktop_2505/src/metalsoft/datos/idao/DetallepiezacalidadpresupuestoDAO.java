/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Sep 06 16:24:06 ART 2010
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
public interface DetallepiezacalidadpresupuestoDAO
{
	public int insert(DetallepiezacalidadpresupuestoDB detallepiezacalidadpresupuesto, Connection con ) throws DetallepiezacalidadpresupuestoException;
	public int update(DetallepiezacalidadpresupuestoPKDB detallepiezacalidadpresupuestopk, DetallepiezacalidadpresupuestoDB detallepiezacalidadpresupuesto, Connection con) throws DetallepiezacalidadpresupuestoException;
	public int delete(DetallepiezacalidadpresupuestoPKDB detallepiezacalidadpresupuestopk, Connection con) throws DetallepiezacalidadpresupuestoException;
	public DetallepiezacalidadpresupuestoDB findByPrimaryKey(DetallepiezacalidadpresupuestoPKDB detallepiezacalidadpresupuestopk , Connection con) throws DetallepiezacalidadpresupuestoException;
	public DetallepiezacalidadpresupuestoDB[] findAll(Connection con) throws DetallepiezacalidadpresupuestoException;
	public DetallepiezacalidadpresupuestoDB[] findByIddetalle(long iddetalle,Connection con) throws DetallepiezacalidadpresupuestoException;
	public DetallepiezacalidadpresupuestoDB[] findByCantprocesocalidad(int cantprocesocalidad,Connection con) throws DetallepiezacalidadpresupuestoException;
	public DetallepiezacalidadpresupuestoDB[] findByDuracionxpieza(Time duracionxpieza,Connection con) throws DetallepiezacalidadpresupuestoException;
	public DetallepiezacalidadpresupuestoDB[] findByIdprocesocalidad(long idprocesocalidad,Connection con) throws DetallepiezacalidadpresupuestoException;
	public DetallepiezacalidadpresupuestoDB[] findByIddetalleproductopresupuesto(long iddetalleproductopresupuesto,Connection con) throws DetallepiezacalidadpresupuestoException;
	public DetallepiezacalidadpresupuestoDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws DetallepiezacalidadpresupuestoException;
	public DetallepiezacalidadpresupuestoDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws DetallepiezacalidadpresupuestoException;
}