/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:40:09 GYT 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity servicio
*
*/

public class Servicio implements Serializable
{
	private long idservicio;
	private String nombre;
	private String descripcion;


	public Servicio(){}
	public Servicio(long idservicio ,String nombre ,String descripcion){
		this.idservicio = idservicio;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public void setIdservicio(long idservicio ){
		 this.idservicio =idservicio;
	}
	public long getIdservicio(){
		 return(idservicio);
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
		ret.append("metalsoft.datos.dbobject.Servicio :");
		ret.append("idservicio='"+idservicio+"'");
		ret.append(", nombre='"+nombre+"'");
		ret.append(", descripcion='"+descripcion+"'");
		return ret.toString();
	}
}
