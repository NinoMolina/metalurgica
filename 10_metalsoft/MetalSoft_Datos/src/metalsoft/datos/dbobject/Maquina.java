/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:39:17 GYT 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity maquina
*
*/

public class Maquina implements Serializable
{
	private long idmaquina;
	private String nombre;
	private long marca;
	private String descripcion;
	private long estado;
	private long tipomaquina;


	public Maquina(){}
	public Maquina(long idmaquina ,String nombre ,long marca ,String descripcion ,long estado ,long tipomaquina){
		this.idmaquina = idmaquina;
		this.nombre = nombre;
		this.marca = marca;
		this.descripcion = descripcion;
		this.estado = estado;
		this.tipomaquina = tipomaquina;
	}

	public void setIdmaquina(long idmaquina ){
		 this.idmaquina =idmaquina;
	}
	public long getIdmaquina(){
		 return(idmaquina);
	}
	public void setNombre(String nombre ){
		 this.nombre =nombre;
	}
	public String getNombre(){
		 return(nombre);
	}
	public void setMarca(long marca ){
		 this.marca =marca;
	}
	public long getMarca(){
		 return(marca);
	}
	public void setDescripcion(String descripcion ){
		 this.descripcion =descripcion;
	}
	public String getDescripcion(){
		 return(descripcion);
	}
	public void setEstado(long estado ){
		 this.estado =estado;
	}
	public long getEstado(){
		 return(estado);
	}
	public void setTipomaquina(long tipomaquina ){
		 this.tipomaquina =tipomaquina;
	}
	public long getTipomaquina(){
		 return(tipomaquina);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Maquina :");
		ret.append("idmaquina='"+idmaquina+"'");
		ret.append(", nombre='"+nombre+"'");
		ret.append(", marca='"+marca+"'");
		ret.append(", descripcion='"+descripcion+"'");
		ret.append(", estado='"+estado+"'");
		ret.append(", tipomaquina='"+tipomaquina+"'");
		return ret.toString();
	}
}
