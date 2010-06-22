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
* This classes is the Data Transfer Object(Value Object) defination for the entity tipomaterial
*
*/

public class Tipomaterial implements Serializable
{
	private long idtipomaterial;
	private String nombre;
	private String descripcion;


	public Tipomaterial(){}
	public Tipomaterial(long idtipomaterial ,String nombre ,String descripcion){
		this.idtipomaterial = idtipomaterial;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public void setIdtipomaterial(long idtipomaterial ){
		 this.idtipomaterial =idtipomaterial;
	}
	public long getIdtipomaterial(){
		 return(idtipomaterial);
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
		ret.append("metalsoft.datos.dbobject.Tipomaterial :");
		ret.append("idtipomaterial='"+idtipomaterial+"'");
		ret.append(", nombre='"+nombre+"'");
		ret.append(", descripcion='"+descripcion+"'");
		return ret.toString();
	}
}
