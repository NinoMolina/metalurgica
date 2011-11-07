/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:05 ART 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity tipoiva
*
*/

public class TipoivaDB implements Serializable
{
	private long idtipoiva;
	private String nombre;
	private String descripcion;


	public TipoivaDB(){}
	public TipoivaDB(long idtipoiva ,String nombre ,String descripcion){
		this.idtipoiva = idtipoiva;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public void setIdtipoiva(long idtipoiva ){
		 this.idtipoiva =idtipoiva;
	}
	public long getIdtipoiva(){
		 return(idtipoiva);
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
		ret.append("metalsoft.datos.dbobject.Tipoiva :");
		ret.append("idtipoiva='"+idtipoiva+"'");
		ret.append(", nombre='"+nombre+"'");
		ret.append(", descripcion='"+descripcion+"'");
		return ret.toString();
	}
}