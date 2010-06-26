/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:39:25 GYT 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity matriz
*
*/

public class Matriz implements Serializable
{
	private long idmatriz;
	private long codmatriz;
	private String nombre;
	private String descripcion;
	private String observaciones;
	private Date fechacreacion;
	private long materiaprima;
	private long tipomaterial;


	public Matriz(){}
	public Matriz(long idmatriz ,long codmatriz ,String nombre ,String descripcion ,String observaciones ,Date fechacreacion ,long materiaprima ,long tipomaterial){
		this.idmatriz = idmatriz;
		this.codmatriz = codmatriz;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.observaciones = observaciones;
		this.fechacreacion = fechacreacion;
		this.materiaprima = materiaprima;
		this.tipomaterial = tipomaterial;
	}

	public void setIdmatriz(long idmatriz ){
		 this.idmatriz =idmatriz;
	}
	public long getIdmatriz(){
		 return(idmatriz);
	}
	public void setCodmatriz(long codmatriz ){
		 this.codmatriz =codmatriz;
	}
	public long getCodmatriz(){
		 return(codmatriz);
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
	public void setObservaciones(String observaciones ){
		 this.observaciones =observaciones;
	}
	public String getObservaciones(){
		 return(observaciones);
	}
	public void setFechacreacion(Date fechacreacion ){
		 this.fechacreacion =fechacreacion;
	}
	public Date getFechacreacion(){
		 return(fechacreacion);
	}
	public void setMateriaprima(long materiaprima ){
		 this.materiaprima =materiaprima;
	}
	public long getMateriaprima(){
		 return(materiaprima);
	}
	public void setTipomaterial(long tipomaterial ){
		 this.tipomaterial =tipomaterial;
	}
	public long getTipomaterial(){
		 return(tipomaterial);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Matriz :");
		ret.append("idmatriz='"+idmatriz+"'");
		ret.append(", codmatriz='"+codmatriz+"'");
		ret.append(", nombre='"+nombre+"'");
		ret.append(", descripcion='"+descripcion+"'");
		ret.append(", observaciones='"+observaciones+"'");
		ret.append(", fechacreacion='"+fechacreacion+"'");
		ret.append(", materiaprima='"+materiaprima+"'");
		ret.append(", tipomaterial='"+tipomaterial+"'");
		return ret.toString();
	}
}
