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
* This classes is the Data Transfer Object(Value Object) defination for the entity sesion
*
*/

public class Sesion implements Serializable
{
	private long idsesion;
	private Date fechainicio;
	private Date fechafin;
	private Time horainicio;
	private Time horafin;
	private long usuario;


	public Sesion(){}
	public Sesion(long idsesion ,Date fechainicio ,Date fechafin ,Time horainicio ,Time horafin ,long usuario){
		this.idsesion = idsesion;
		this.fechainicio = fechainicio;
		this.fechafin = fechafin;
		this.horainicio = horainicio;
		this.horafin = horafin;
		this.usuario = usuario;
	}

	public void setIdsesion(long idsesion ){
		 this.idsesion =idsesion;
	}
	public long getIdsesion(){
		 return(idsesion);
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
	public void setUsuario(long usuario ){
		 this.usuario =usuario;
	}
	public long getUsuario(){
		 return(usuario);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Sesion :");
		ret.append("idsesion='"+idsesion+"'");
		ret.append(", fechainicio='"+fechainicio+"'");
		ret.append(", fechafin='"+fechafin+"'");
		ret.append(", horainicio='"+horainicio+"'");
		ret.append(", horafin='"+horafin+"'");
		ret.append(", usuario='"+usuario+"'");
		return ret.toString();
	}
}
