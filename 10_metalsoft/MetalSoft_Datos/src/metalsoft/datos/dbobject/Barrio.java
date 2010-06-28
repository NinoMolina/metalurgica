/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 28 01:02:39 ART 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity barrio
*
*/

public class Barrio implements Serializable
{
	private long idbarrio;
	private String nombre;
	private long codpostal;
	private long localidad;


	public Barrio(){}
	public Barrio(long idbarrio ,String nombre ,long codpostal ,long localidad){
		this.idbarrio = idbarrio;
		this.nombre = nombre;
		this.codpostal = codpostal;
		this.localidad = localidad;
	}

	public void setIdbarrio(long idbarrio ){
		 this.idbarrio =idbarrio;
	}
	public long getIdbarrio(){
		 return(idbarrio);
	}
	public void setNombre(String nombre ){
		 this.nombre =nombre;
	}
	public String getNombre(){
		 return(nombre);
	}
	public void setCodpostal(long codpostal ){
		 this.codpostal =codpostal;
	}
	public long getCodpostal(){
		 return(codpostal);
	}
	public void setLocalidad(long localidad ){
		 this.localidad =localidad;
	}
	public long getLocalidad(){
		 return(localidad);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Barrio :");
		ret.append("idbarrio='"+idbarrio+"'");
		ret.append(", nombre='"+nombre+"'");
		ret.append(", codpostal='"+codpostal+"'");
		ret.append(", localidad='"+localidad+"'");
		return ret.toString();
	}
}
