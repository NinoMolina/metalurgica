/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:38:29 GYT 2010
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
* This class represents the primary key of the asistencia table
*
*/

public class AsistenciaPK implements Serializable
{
	private long empleado;
	private long fechaingreso;
	private Time horaingreso;


	public AsistenciaPK(){}
	public AsistenciaPK(long empleado ,long fechaingreso ,Time horaingreso){
		this.empleado = empleado;
		this.fechaingreso = fechaingreso;
		this.horaingreso = horaingreso;
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
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.AsistenciaPK :");
		ret.append("empleado='"+empleado+"'");
		ret.append(", fechaingreso='"+fechaingreso+"'");
		ret.append(", horaingreso='"+horaingreso+"'");
		return ret.toString();
	}
}
