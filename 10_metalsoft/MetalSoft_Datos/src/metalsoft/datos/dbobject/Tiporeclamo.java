/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:11 ART 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity tiporeclamo
*
*/

public class Tiporeclamo implements Serializable
{
	private long idtiporeclamo;
	private String nombre;
	private String descripcion;


	public Tiporeclamo(){}
	public Tiporeclamo(long idtiporeclamo ,String nombre ,String descripcion){
		this.idtiporeclamo = idtiporeclamo;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public void setIdtiporeclamo(long idtiporeclamo ){
		 this.idtiporeclamo =idtiporeclamo;
	}
	public long getIdtiporeclamo(){
		 return(idtiporeclamo);
	}
	public void setNombre(String nombre ){
		 this.nombre =nombre;
	}
	public String getNombre(){
		 return(nombre);
	}
	public void setDescripcion(String descripcion ){
		 this.descripcion =descripcion;
	}
	public String getDescripcion(){
		 return(descripcion);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Tiporeclamo :");
		ret.append("idtiporeclamo='"+idtiporeclamo+"'");
		ret.append(", nombre='"+nombre+"'");
		ret.append(", descripcion='"+descripcion+"'");
		return ret.toString();
	}
}
