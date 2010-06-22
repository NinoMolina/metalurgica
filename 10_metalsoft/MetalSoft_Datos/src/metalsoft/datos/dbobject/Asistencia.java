/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:07 ART 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity asistencia
*
*/

public class Asistencia implements Serializable
{
	private long empleado;
	private long fechaingreso;
	private Time horaingreso;
	private Time horaegreso;
	private String observaciones;


	public Asistencia(){}
	public Asistencia(long empleado ,long fechaingreso ,Time horaingreso ,Time horaegreso ,String observaciones){
		this.empleado = empleado;
		this.fechaingreso = fechaingreso;
		this.horaingreso = horaingreso;
		this.horaegreso = horaegreso;
		this.observaciones = observaciones;
	}

	public void setEmpleado(long empleado ){
		 this.empleado =empleado;
	}
	public long getEmpleado(){
		 return(empleado);
	}
	public void setFechaingreso(long fechaingreso ){
		 this.fechaingreso =fechaingreso;
	}
	public long getFechaingreso(){
		 return(fechaingreso);
	}
	public void setHoraingreso(Time horaingreso ){
		 this.horaingreso =horaingreso;
	}
	public Time getHoraingreso(){
		 return(horaingreso);
	}
	public void setHoraegreso(Time horaegreso ){
		 this.horaegreso =horaegreso;
	}
	public Time getHoraegreso(){
		 return(horaegreso);
	}
	public void setObservaciones(String observaciones ){
		 this.observaciones =observaciones;
	}
	public String getObservaciones(){
		 return(observaciones);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Asistencia :");
		ret.append("empleado='"+empleado+"'");
		ret.append(", fechaingreso='"+fechaingreso+"'");
		ret.append(", horaingreso='"+horaingreso+"'");
		ret.append(", horaegreso='"+horaegreso+"'");
		ret.append(", observaciones='"+observaciones+"'");
		return ret.toString();
	}
}
