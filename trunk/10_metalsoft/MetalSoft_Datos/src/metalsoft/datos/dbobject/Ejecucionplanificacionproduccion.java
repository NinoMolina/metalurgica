/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:08 ART 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity ejecucionplanificacionproduccion
*
*/

public class Ejecucionplanificacionproduccion implements Serializable
{
	private long idejecucion;
	private long idplanificacionproduccion;
	private Date fechainicio;
	private Date fechafin;
	private Time horainicio;
	private Time horafin;
	private long estado;


	public Ejecucionplanificacionproduccion(){}
	public Ejecucionplanificacionproduccion(long idejecucion ,long idplanificacionproduccion ,Date fechainicio ,Date fechafin ,Time horainicio ,Time horafin ,long estado){
		this.idejecucion = idejecucion;
		this.idplanificacionproduccion = idplanificacionproduccion;
		this.fechainicio = fechainicio;
		this.fechafin = fechafin;
		this.horainicio = horainicio;
		this.horafin = horafin;
		this.estado = estado;
	}

	public void setIdejecucion(long idejecucion ){
		 this.idejecucion =idejecucion;
	}
	public long getIdejecucion(){
		 return(idejecucion);
	}
	public void setIdplanificacionproduccion(long idplanificacionproduccion ){
		 this.idplanificacionproduccion =idplanificacionproduccion;
	}
	public long getIdplanificacionproduccion(){
		 return(idplanificacionproduccion);
	}
	public void setFechainicio(Date fechainicio ){
		 this.fechainicio =fechainicio;
	}
	public Date getFechainicio(){
		 return(fechainicio);
	}
	public void setFechafin(Date fechafin ){
		 this.fechafin =fechafin;
	}
	public Date getFechafin(){
		 return(fechafin);
	}
	public void setHorainicio(Time horainicio ){
		 this.horainicio =horainicio;
	}
	public Time getHorainicio(){
		 return(horainicio);
	}
	public void setHorafin(Time horafin ){
		 this.horafin =horafin;
	}
	public Time getHorafin(){
		 return(horafin);
	}
	public void setEstado(long estado ){
		 this.estado =estado;
	}
	public long getEstado(){
		 return(estado);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Ejecucionplanificacionproduccion :");
		ret.append("idejecucion='"+idejecucion+"'");
		ret.append(", idplanificacionproduccion='"+idplanificacionproduccion+"'");
		ret.append(", fechainicio='"+fechainicio+"'");
		ret.append(", fechafin='"+fechafin+"'");
		ret.append(", horainicio='"+horainicio+"'");
		ret.append(", horafin='"+horafin+"'");
		ret.append(", estado='"+estado+"'");
		return ret.toString();
	}
}
