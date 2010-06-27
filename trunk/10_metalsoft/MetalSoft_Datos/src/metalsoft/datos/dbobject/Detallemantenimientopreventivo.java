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
* This classes is the Data Transfer Object(Value Object) defination for the entity detallemantenimientopreventivo
*
*/

public class Detallemantenimientopreventivo implements Serializable
{
	private long idmantenimientopreventivo;
	private long iddetalle;
	private Time duracion;
	private long servicio;
	private String observaciones;


	public Detallemantenimientopreventivo(){}
	public Detallemantenimientopreventivo(long idmantenimientopreventivo ,long iddetalle ,Time duracion ,long servicio ,String observaciones){
		this.idmantenimientopreventivo = idmantenimientopreventivo;
		this.iddetalle = iddetalle;
		this.duracion = duracion;
		this.servicio = servicio;
		this.observaciones = observaciones;
	}

	public void setIdmantenimientopreventivo(long idmantenimientopreventivo ){
		 this.idmantenimientopreventivo =idmantenimientopreventivo;
	}
	public long getIdmantenimientopreventivo(){
		 return(idmantenimientopreventivo);
	}
	public void setIddetalle(long iddetalle ){
		 this.iddetalle =iddetalle;
	}
	public long getIddetalle(){
		 return(iddetalle);
	}
	public void setDuracion(Time duracion ){
		 this.duracion =duracion;
	}
	public Time getDuracion(){
		 return(duracion);
	}
	public void setServicio(long servicio ){
		 this.servicio =servicio;
	}
	public long getServicio(){
		 return(servicio);
	}
	public void setObservaciones(String observaciones ){
		 this.observaciones =observaciones;
	}
	public String getObservaciones(){
		 return(observaciones);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Detallemantenimientopreventivo :");
		ret.append("idmantenimientopreventivo='"+idmantenimientopreventivo+"'");
		ret.append(", iddetalle='"+iddetalle+"'");
		ret.append(", duracion='"+duracion+"'");
		ret.append(", servicio='"+servicio+"'");
		ret.append(", observaciones='"+observaciones+"'");
		return ret.toString();
	}
}
