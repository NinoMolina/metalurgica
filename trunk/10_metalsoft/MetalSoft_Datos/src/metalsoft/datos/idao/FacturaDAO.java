/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:03 ART 2010
*
* DAO-Generator Version 1.2
*
*/
package metalsoft.datos.idao;
import java.math.*;
import java.sql.*;
import java.net.URL;
import java.util.Collection;
import java.util.ArrayList;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
public interface FacturaDAO
{
	public int insert(FacturaDB factura, Connection con ) throws FacturaException;
	public int update(FacturaPK facturapk, FacturaDB factura, Connection con) throws FacturaException;
	public int delete(FacturaPK facturapk, Connection con) throws FacturaException;
	public FacturaDB findByPrimaryKey(FacturaPK facturapk , Connection con) throws FacturaException;
	public FacturaDB[] findAll(Connection con) throws FacturaException;
	public FacturaDB[] findByIdfactura(long idfactura,Connection con) throws FacturaException;
	public FacturaDB[] findByNrofactura(long nrofactura,Connection con) throws FacturaException;
	public FacturaDB[] findByFechaemision(Date fechaemision,Connection con) throws FacturaException;
	public FacturaDB[] findByTipoiva(long tipoiva,Connection con) throws FacturaException;
	public FacturaDB[] findByFecharealcobro(Date fecharealcobro,Connection con) throws FacturaException;
	public FacturaDB[] findByFormapago(long formapago,Connection con) throws FacturaException;
	public FacturaDB[] findByFechavencimiento(Date fechavencimiento,Connection con) throws FacturaException;
	public FacturaDB[] findByUsuario(long usuario,Connection con) throws FacturaException;
	public FacturaDB[] findByEstado(long estado,Connection con) throws FacturaException;
	public FacturaDB[] findByTipofactura(String tipofactura,Connection con) throws FacturaException;
	public FacturaDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws FacturaException;
	public FacturaDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws FacturaException;
}
