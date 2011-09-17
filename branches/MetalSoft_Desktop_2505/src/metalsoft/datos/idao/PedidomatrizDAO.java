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
public interface PedidomatrizDAO
{
	public int insert(PedidomatrizDB pedidomatriz, Connection con ) throws PedidomatrizException;
	public int update(PedidomatrizPKDB pedidomatrizpk, PedidomatrizDB pedidomatriz, Connection con) throws PedidomatrizException;
	public int delete(PedidomatrizPKDB pedidomatrizpk, Connection con) throws PedidomatrizException;
	public PedidomatrizDB findByPrimaryKey(PedidomatrizPKDB pedidomatrizpk , Connection con) throws PedidomatrizException;
	public PedidomatrizDB[] findAll(Connection con) throws PedidomatrizException;
	public PedidomatrizDB[] findByIdpedidomatriz(long idpedidomatriz,Connection con) throws PedidomatrizException;
	public PedidomatrizDB[] findByNropedidomatriz(long nropedidomatriz,Connection con) throws PedidomatrizException;
	public PedidomatrizDB[] findByFechapedidomatriz(Date fechapedidomatriz,Connection con) throws PedidomatrizException;
	public PedidomatrizDB[] findByIdmatriz(long idmatriz,Connection con) throws PedidomatrizException;
	public PedidomatrizDB[] findByObservaciones(String observaciones,Connection con) throws PedidomatrizException;
	public PedidomatrizDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws PedidomatrizException;
	public PedidomatrizDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws PedidomatrizException;
}
