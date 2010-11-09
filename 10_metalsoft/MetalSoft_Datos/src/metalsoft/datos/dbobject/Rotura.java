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
* This classes is the Data Transfer Object(Value Object) defination for the entity rotura
*
*/

public class Rotura implements Serializable
{
	private long idrotura;
	private String nombre;
	private String descripcion;


	public Rotura(){}
	public Rotura(long idrotura ,String nombre ,String descripcion){
		this.idrotura = idrotura;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public void setIdrotura(long idrotura ){
		 this.idrotura =idrotura;
	}
	public long getIdrotura(){
		 return(idrotura);
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
		ret.append("metalsoft.datos.dbobject.Rotura :");
		ret.append("idrotura='"+idrotura+"'");
		ret.append(", nombre='"+nombre+"'");
		ret.append(", descripcion='"+descripcion+"'");
		return ret.toString();
	}
}