/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:38:43 GYT 2010
*
* DAO-Generator Version 1.2
*
*/
package metalsoft.datos.dbobject;
import java.math.*;
import java.sql.*;
import java.net.URL;
import java.io.Serializable;

/**
*
* This class represents the primary key of the detallemantenimientopreventivo table
*
*/

public class DetallemantenimientopreventivoPK implements Serializable
{
	private long idmantenimientopreventivo;
	private long iddetalle;


	public DetallemantenimientopreventivoPK(){}
	public DetallemantenimientopreventivoPK(long idmantenimientopreventivo ,long iddetalle){
		this.idmantenimientopreventivo = idmantenimientopreventivo;
		this.iddetalle = iddetalle;
	}

	public void setIdmantenimientopreventivo(long idmantenimientopreventivo ){
		 this.idmantenimientopreventivo =idmantenimientopreventivo;
	}
	public long getIdmantenimientopreventivo(){
		 return(idmantenimientopreventivo);
	}
	public void setIddetalle(long iddetalle ){
		 this.iddetalle =iddetalle;
	}
	public long getIddetalle(){
		 return(iddetalle);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.DetallemantenimientopreventivoPK :");
		ret.append("idmantenimientopreventivo='"+idmantenimientopreventivo+"'");
		ret.append(", iddetalle='"+iddetalle+"'");
		return ret.toString();
	}
}
