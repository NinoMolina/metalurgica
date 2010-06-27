/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:04 ART 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity procesocalidad
*
*/

public class Procesocalidad implements Serializable
{
	private long idprocesocalidad;
	private String nombre;
	private long nroproceso;
	private String especificacion;
	private String tolerancia;
	private String descripcion;
	private Time duracionestimada;
	private Date fechacreacion;
	private String herramienta;
	private long accioncalidad;


	public Procesocalidad(){}
	public Procesocalidad(long idprocesocalidad ,String nombre ,long nroproceso ,String especificacion ,String tolerancia ,String descripcion ,Time duracionestimada ,Date fechacreacion ,String herramienta ,long accioncalidad){
		this.idprocesocalidad = idprocesocalidad;
		this.nombre = nombre;
		this.nroproceso = nroproceso;
		this.especificacion = especificacion;
		this.tolerancia = tolerancia;
		this.descripcion = descripcion;
		this.duracionestimada = duracionestimada;
		this.fechacreacion = fechacreacion;
		this.herramienta = herramienta;
		this.accioncalidad = accioncalidad;
	}

	public void setIdprocesocalidad(long idprocesocalidad ){
		 this.idprocesocalidad =idprocesocalidad;
	}
	public long getIdprocesocalidad(){
		 return(idprocesocalidad);
	}
	public void setNombre(String nombre ){
		 this.nombre =nombre;
	}
	public String getNombre(){
		 return(nombre);
	}
	public void setNroproceso(long nroproceso ){
		 this.nroproceso =nroproceso;
	}
	public long getNroproceso(){
		 return(nroproceso);
	}
	public void setEspecificacion(String especificacion ){
		 this.especificacion =especificacion;
	}
	public String getEspecificacion(){
		 return(especificacion);
	}
	public void setTolerancia(String tolerancia ){
		 this.tolerancia =tolerancia;
	}
	public String getTolerancia(){
		 return(tolerancia);
	}
	public void setDescripcion(String descripcion ){
		 this.descripcion =descripcion;
	}
	public String getDescripcion(){
		 return(descripcion);
	}
	public void setDuracionestimada(Time duracionestimada ){
		 this.duracionestimada =duracionestimada;
	}
	public Time getDuracionestimada(){
		 return(duracionestimada);
	}
	public void setFechacreacion(Date fechacreacion ){
		 this.fechacreacion =fechacreacion;
	}
	public Date getFechacreacion(){
		 return(fechacreacion);
	}
	public void setHerramienta(String herramienta ){
		 this.herramienta =herramienta;
	}
	public String getHerramienta(){
		 return(herramienta);
	}
	public void setAccioncalidad(long accioncalidad ){
		 this.accioncalidad =accioncalidad;
	}
	public long getAccioncalidad(){
		 return(accioncalidad);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Procesocalidad :");
		ret.append("idprocesocalidad='"+idprocesocalidad+"'");
		ret.append(", nombre='"+nombre+"'");
		ret.append(", nroproceso='"+nroproceso+"'");
		ret.append(", especificacion='"+especificacion+"'");
		ret.append(", tolerancia='"+tolerancia+"'");
		ret.append(", descripcion='"+descripcion+"'");
		ret.append(", duracionestimada='"+duracionestimada+"'");
		ret.append(", fechacreacion='"+fechacreacion+"'");
		ret.append(", herramienta='"+herramienta+"'");
		ret.append(", accioncalidad='"+accioncalidad+"'");
		return ret.toString();
	}
}
