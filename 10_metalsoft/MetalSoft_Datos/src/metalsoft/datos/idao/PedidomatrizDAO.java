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
	public int insert(Pedidomatriz pedidomatriz, Connection con ) throws PedidomatrizException;
	public int update(PedidomatrizPK pedidomatrizpk, Pedidomatriz pedidomatriz, Connection con) throws PedidomatrizException;
	public int delete(PedidomatrizPK pedidomatrizpk, Connection con) throws PedidomatrizException;
	public Pedidomatriz findByPrimaryKey(PedidomatrizPK pedidomatrizpk , Connection con) throws PedidomatrizException;
	public Pedidomatriz[] findAll(Connection con) throws PedidomatrizException;
	public Pedidomatriz[] findByIdpedidomatriz(long idpedidomatriz,Connection con) throws PedidomatrizException;
	public Pedidomatriz[] findByNropedidomatriz(long nropedidomatriz,Connection con) throws PedidomatrizException;
	public Pedidomatriz[] findByFechapedidomatriz(Date fechapedidomatriz,Connection con) throws PedidomatrizException;
	public Pedidomatriz[] findByIdmatriz(long idmatriz,Connection con) throws PedidomatrizException;
	public Pedidomatriz[] findByObservaciones(String observaciones,Connection con) throws PedidomatrizException;
	public Pedidomatriz[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws PedidomatrizException;
	public Pedidomatriz[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws PedidomatrizException;
}