/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:39:22 GYT 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity marca
*
*/

public class Marca implements Serializable
{
	private long idmarca;
	private String nombre;
	private String descripcion;


	public Marca(){}
	public Marca(long idmarca ,String nombre ,String descripcion){
		this.idmarca = idmarca;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public void setIdmarca(long idmarca ){
		 this.idmarca =idmarca;
	}
	public long getIdmarca(){
		 return(idmarca);
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
		ret.append("metalsoft.datos.dbobject.Marca :");
		ret.append("idmarca='"+idmarca+"'");
		ret.append(", nombre='"+nombre+"'");
		ret.append(", descripcion='"+descripcion+"'");
		return ret.toString();
	}
}
