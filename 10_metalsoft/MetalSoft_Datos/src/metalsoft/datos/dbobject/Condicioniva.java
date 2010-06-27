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
* This classes is the Data Transfer Object(Value Object) defination for the entity condicioniva
*
*/

public class Condicioniva implements Serializable
{
	private long idcondicioniva;
	private String nombre;
	private String descripcion;


	public Condicioniva(){}
	public Condicioniva(long idcondicioniva ,String nombre ,String descripcion){
		this.idcondicioniva = idcondicioniva;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public void setIdcondicioniva(long idcondicioniva ){
		 this.idcondicioniva =idcondicioniva;
	}
	public long getIdcondicioniva(){
		 return(idcondicioniva);
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
		ret.append("metalsoft.datos.dbobject.Condicioniva :");
		ret.append("idcondicioniva='"+idcondicioniva+"'");
		ret.append(", nombre='"+nombre+"'");
		ret.append(", descripcion='"+descripcion+"'");
		return ret.toString();
	}
}
