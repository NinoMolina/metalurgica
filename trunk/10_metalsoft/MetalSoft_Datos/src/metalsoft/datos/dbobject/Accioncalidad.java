/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:00 ART 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity accioncalidad
*
*/

public class Accioncalidad implements Serializable
{
	private long idaccioncalidad;
	private String nombre;
	private String descripcion;


	public Accioncalidad(){}
	public Accioncalidad(long idaccioncalidad ,String nombre ,String descripcion){
		this.idaccioncalidad = idaccioncalidad;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public void setIdaccioncalidad(long idaccioncalidad ){
		 this.idaccioncalidad =idaccioncalidad;
	}
	public long getIdaccioncalidad(){
		 return(idaccioncalidad);
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
		ret.append("metalsoft.datos.dbobject.Accioncalidad :");
		ret.append("idaccioncalidad='"+idaccioncalidad+"'");
		ret.append(", nombre='"+nombre+"'");
		ret.append(", descripcion='"+descripcion+"'");
		return ret.toString();
	}
}
