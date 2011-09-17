/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:05 ART 2010
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
public interface TrabajotercerizadoDAO
{
	public int insert(TrabajotercerizadoDB trabajotercerizado, Connection con ) throws TrabajotercerizadoException;
	public int update(TrabajotercerizadoPKDB trabajotercerizadopk, TrabajotercerizadoDB trabajotercerizado, Connection con) throws TrabajotercerizadoException;
	public int delete(TrabajotercerizadoPKDB trabajotercerizadopk, Connection con) throws TrabajotercerizadoException;
	public TrabajotercerizadoDB findByPrimaryKey(TrabajotercerizadoPKDB trabajotercerizadopk , Connection con) throws TrabajotercerizadoException;
	public TrabajotercerizadoDB[] findAll(Connection con) throws TrabajotercerizadoException;
	public TrabajotercerizadoDB[] findByIdtrabajo(long idtrabajo,Connection con) throws TrabajotercerizadoException;
	public TrabajotercerizadoDB[] findByNrotrabajotercerizado(long nrotrabajotercerizado,Connection con) throws TrabajotercerizadoException;
	public TrabajotercerizadoDB[] findByFechapedidocotizacion(Date fechapedidocotizacion,Connection con) throws TrabajotercerizadoException;
	public TrabajotercerizadoDB[] findByFechaentregaestipulada(Date fechaentregaestipulada,Connection con) throws TrabajotercerizadoException;
	public TrabajotercerizadoDB[] findByFechaconfirmaciontrabajo(Date fechaconfirmaciontrabajo,Connection con) throws TrabajotercerizadoException;
	public TrabajotercerizadoDB[] findByFechacancelacion(Date fechacancelacion,Connection con) throws TrabajotercerizadoException;
	public TrabajotercerizadoDB[] findByFechaentregareal(Date fechaentregareal,Connection con) throws TrabajotercerizadoException;
	public TrabajotercerizadoDB[] findByFechaenvioaempresa(Date fechaenvioaempresa,Connection con) throws TrabajotercerizadoException;
	public TrabajotercerizadoDB[] findByMotivocancelacion(String motivocancelacion,Connection con) throws TrabajotercerizadoException;
	public TrabajotercerizadoDB[] findByEmpresa(long empresa,Connection con) throws TrabajotercerizadoException;
	public TrabajotercerizadoDB[] findByEstado(int estado,Connection con) throws TrabajotercerizadoException;
	public TrabajotercerizadoDB[] findByPedido(long pedido,Connection con) throws TrabajotercerizadoException;
	public TrabajotercerizadoDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws TrabajotercerizadoException;
	public TrabajotercerizadoDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws TrabajotercerizadoException;
}
