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
	public int insert(Detallepiezacalidadpresupuesto detallepiezacalidadpresupuesto, Connection con ) throws DetallepiezacalidadpresupuestoException;
	public int update(DetallepiezacalidadpresupuestoPK detallepiezacalidadpresupuestopk, Detallepiezacalidadpresupuesto detallepiezacalidadpresupuesto, Connection con) throws DetallepiezacalidadpresupuestoException;
	public int delete(DetallepiezacalidadpresupuestoPK detallepiezacalidadpresupuestopk, Connection con) throws DetallepiezacalidadpresupuestoException;
	public Detallepiezacalidadpresupuesto findByPrimaryKey(DetallepiezacalidadpresupuestoPK detallepiezacalidadpresupuestopk , Connection con) throws DetallepiezacalidadpresupuestoException;
	public Detallepiezacalidadpresupuesto[] findAll(Connection con) throws DetallepiezacalidadpresupuestoException;
	public Detallepiezacalidadpresupuesto[] findByIddetalle(long iddetalle,Connection con) throws DetallepiezacalidadpresupuestoException;
	public Detallepiezacalidadpresupuesto[] findByCantprocesocalidad(int cantprocesocalidad,Connection con) throws DetallepiezacalidadpresupuestoException;
	public Detallepiezacalidadpresupuesto[] findByDuracionxpieza(Time duracionxpieza,Connection con) throws DetallepiezacalidadpresupuestoException;
	public Detallepiezacalidadpresupuesto[] findByIdprocesocalidad(long idprocesocalidad,Connection con) throws DetallepiezacalidadpresupuestoException;
	public Detallepiezacalidadpresupuesto[] findByIddetalleproductopresupuesto(long iddetalleproductopresupuesto,Connection con) throws DetallepiezacalidadpresupuestoException;
	public Detallepiezacalidadpresupuesto[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws DetallepiezacalidadpresupuestoException;
	public Detallepiezacalidadpresupuesto[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws DetallepiezacalidadpresupuestoException;
}