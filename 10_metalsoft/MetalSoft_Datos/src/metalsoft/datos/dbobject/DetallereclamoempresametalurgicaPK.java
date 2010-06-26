/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:38:48 GYT 2010
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
* This class represents the primary key of the detallereclamoempresametalurgica table
*
*/

public class DetallereclamoempresametalurgicaPK implements Serializable
{
	private long iddetalle;
	private long idreclamo;


	public DetallereclamoempresametalurgicaPK(){}
	public DetallereclamoempresametalurgicaPK(long iddetalle ,long idreclamo){
		this.iddetalle = iddetalle;
		this.idreclamo = idreclamo;
	}

	public void setIddetalle(long iddetalle ){
		 this.iddetalle =iddetalle;
	}
	public long getIddetalle(){
		 return(iddetalle);
	}
	public void setIdreclamo(long idreclamo ){
		 this.idreclamo =idreclamo;
	}
	public long getIdreclamo(){
		 return(idreclamo);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.DetallereclamoempresametalurgicaPK :");
		ret.append("iddetalle='"+iddetalle+"'");
		ret.append(", idreclamo='"+idreclamo+"'");
		return ret.toString();
	}
}
