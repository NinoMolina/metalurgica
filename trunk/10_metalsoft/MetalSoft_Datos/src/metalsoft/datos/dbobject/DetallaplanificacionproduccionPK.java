/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:01 ART 2010
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
* This class represents the primary key of the detallaplanificacionproduccion table
*
*/

public class DetallaplanificacionproduccionPK implements Serializable
{
	private long iddetalle;
	private long idplanificacionproduccion;


	public DetallaplanificacionproduccionPK(){}
	public DetallaplanificacionproduccionPK(long iddetalle ,long idplanificacionproduccion){
		this.iddetalle = iddetalle;
		this.idplanificacionproduccion = idplanificacionproduccion;
	}

	public void setIddetalle(long iddetalle ){
		 this.iddetalle =iddetalle;
	}
	public long getIddetalle(){
		 return(iddetalle);
	}
	public void setIdplanificacionproduccion(long idplanificacionproduccion ){
		 this.idplanificacionproduccion =idplanificacionproduccion;
	}
	public long getIdplanificacionproduccion(){
		 return(idplanificacionproduccion);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.DetallaplanificacionproduccionPK :");
		ret.append("iddetalle='"+iddetalle+"'");
		ret.append(", idplanificacionproduccion='"+idplanificacionproduccion+"'");
		return ret.toString();
	}
}
