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
import java.util.*;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
public interface PlanoDAO
{
	public int insert(PlanoDB plano, Connection con ) throws PlanoException;
	public int update(PlanoPKDB planopk, PlanoDB plano, Connection con) throws PlanoException;
	public int delete(PlanoPKDB planopk, Connection con) throws PlanoException;
	public PlanoDB findByPrimaryKey(PlanoPKDB planopk , Connection con) throws PlanoException;
	public PlanoDB[] findAll(Connection con) throws PlanoException;
	public PlanoDB[] findByIdplano(long idplano,Connection con) throws PlanoException;
	public PlanoDB[] findByNroplano(long nroplano,Connection con) throws PlanoException;
	public PlanoDB[] findByEscala(int escala,Connection con) throws PlanoException;
	public PlanoDB[] findByPedido(long pedido,Connection con) throws PlanoException;
	public PlanoDB[] findByImagen(Object imagen,Connection con) throws PlanoException;
	public PlanoDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws PlanoException;
	public PlanoDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws PlanoException;
}