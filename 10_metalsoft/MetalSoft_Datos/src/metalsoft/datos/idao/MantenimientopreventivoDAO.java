/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:39:15 GYT 2010
*
* DAO-Generator Version 1.2
*
*/
package metalsoft.datos.idao;
import java.math.*;
import java.sql.*;
import java.net.URL;

import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
public interface MantenimientopreventivoDAO
{
	public int insert(Mantenimientopreventivo mantenimientopreventivo, Connection con ) throws MantenimientopreventivoException;
	public int update(MantenimientopreventivoPK mantenimientopreventivopk, Mantenimientopreventivo mantenimientopreventivo, Connection con) throws MantenimientopreventivoException;
	public int delete(MantenimientopreventivoPK mantenimientopreventivopk, Connection con) throws MantenimientopreventivoException;
	public Mantenimientopreventivo findByPrimaryKey(MantenimientopreventivoPK mantenimientopreventivopk , Connection con) throws MantenimientopreventivoException;
	public Mantenimientopreventivo[] findAll(Connection con) throws MantenimientopreventivoException;
	public Mantenimientopreventivo[] findByIdmantenimientopreventivo(long idmantenimientopreventivo,Connection con) throws MantenimientopreventivoException;
	public Mantenimientopreventivo[] findByFechamantenimientoprevisto(Date fechamantenimientoprevisto,Connection con) throws MantenimientopreventivoException;
	public Mantenimientopreventivo[] findByFechaenviomantenimiento(Date fechaenviomantenimiento,Connection con) throws MantenimientopreventivoException;
	public Mantenimientopreventivo[] findByHoraenviomantenimiento(Time horaenviomantenimiento,Connection con) throws MantenimientopreventivoException;
	public Mantenimientopreventivo[] findByPeriodo(String periodo,Connection con) throws MantenimientopreventivoException;
	public Mantenimientopreventivo[] findByNromantenimietno(long nromantenimietno,Connection con) throws MantenimientopreventivoException;
	public Mantenimientopreventivo[] findByFechafinmantenimientoreal(Date fechafinmantenimientoreal,Connection con) throws MantenimientopreventivoException;
	public Mantenimientopreventivo[] findByProveedormantenimiento(long proveedormantenimiento,Connection con) throws MantenimientopreventivoException;
	public Mantenimientopreventivo[] findByMaquina(long maquina,Connection con) throws MantenimientopreventivoException;
	public Mantenimientopreventivo[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws MantenimientopreventivoException;
	public Mantenimientopreventivo[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws MantenimientopreventivoException;
}
