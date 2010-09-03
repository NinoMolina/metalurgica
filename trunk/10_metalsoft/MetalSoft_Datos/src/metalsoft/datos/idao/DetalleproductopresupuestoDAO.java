/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Fri Sep 03 13:20:27 ART 2010
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
public interface DetalleproductopresupuestoDAO
{
	public int insert(DetalleproductopresupuestoDB detalleproductopresupuesto, Connection con ) throws DetalleproductopresupuestoException;
	public int update(DetalleproductopresupuestoPK detalleproductopresupuestopk, DetalleproductopresupuestoDB detalleproductopresupuesto, Connection con) throws DetalleproductopresupuestoException;
	public int delete(DetalleproductopresupuestoPK detalleproductopresupuestopk, Connection con) throws DetalleproductopresupuestoException;
	public DetalleproductopresupuestoDB findByPrimaryKey(DetalleproductopresupuestoPK detalleproductopresupuestopk , Connection con) throws DetalleproductopresupuestoException;
	public DetalleproductopresupuestoDB[] findAll(Connection con) throws DetalleproductopresupuestoException;
	public DetalleproductopresupuestoDB[] findByIddetalle(long iddetalle,Connection con) throws DetalleproductopresupuestoException;
	public DetalleproductopresupuestoDB[] findByIddetallepresupuesto(long iddetallepresupuesto,Connection con) throws DetalleproductopresupuestoException;
	public DetalleproductopresupuestoDB[] findByIdpieza(long idpieza,Connection con) throws DetalleproductopresupuestoException;
	public DetalleproductopresupuestoDB[] findByIdmateriaprima(long idmateriaprima,Connection con) throws DetalleproductopresupuestoException;
	public DetalleproductopresupuestoDB[] findByCantmateriaprima(int cantmateriaprima,Connection con) throws DetalleproductopresupuestoException;
	public DetalleproductopresupuestoDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws DetalleproductopresupuestoException;
	public DetalleproductopresupuestoDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws DetalleproductopresupuestoException;
}
