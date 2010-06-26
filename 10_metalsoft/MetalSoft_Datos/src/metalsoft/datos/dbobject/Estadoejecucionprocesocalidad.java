/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:40:15 ART 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity estadoejecucionprocesocalidad
*
*/

public class Estadoejecucionprocesocalidad implements Serializable
{
	private long idestado;
	private String nombre;
	private String descripcion;


	public Estadoejecucionprocesocalidad(){}
	public Estadoejecucionprocesocalidad(long idestado ,String nombre ,String descripcion){
		this.idestado = idestado;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public void setIdestado(long idestado ){
		 this.idestado =idestado;
	}
	public long getIdestado(){
		 return(idestado);
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
		ret.append("metalsoft.datos.dbobject.Estadoejecucionprocesocalidad :");
		ret.append("idestado='"+idestado+"'");
		ret.append(", nombre='"+nombre+"'");
		ret.append(", descripcion='"+descripcion+"'");
		return ret.toString();
	}
}
