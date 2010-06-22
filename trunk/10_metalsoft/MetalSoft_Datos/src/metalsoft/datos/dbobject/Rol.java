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
* This classes is the Data Transfer Object(Value Object) defination for the entity rol
*
*/

public class Rol implements Serializable
{
	private long idrol;
	private String rol;
	private String descripcion;


	public Rol(){}
	public Rol(long idrol ,String rol ,String descripcion){
		this.idrol = idrol;
		this.rol = rol;
		this.descripcion = descripcion;
	}

	public void setIdrol(long idrol ){
		 this.idrol =idrol;
	}
	public long getIdrol(){
		 return(idrol);
	}
	public void setRol(String rol ){
		 this.rol =rol;
	}
	public String getRol(){
		 return(rol);
	}
	public void setDescripcion(String descripcion ){
		 this.descripcion =descripcion;
	}
	public String getDescripcion(){
		 return(descripcion);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Rol :");
		ret.append("idrol='"+idrol+"'");
		ret.append(", rol='"+rol+"'");
		ret.append(", descripcion='"+descripcion+"'");
		return ret.toString();
	}
}
