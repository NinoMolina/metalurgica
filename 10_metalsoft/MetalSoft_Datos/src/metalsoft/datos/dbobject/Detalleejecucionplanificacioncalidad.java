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
* This classes is the Data Transfer Object(Value Object) defination for the entity detalleejecucionplanificacioncalidad
*
*/

public class Detalleejecucionplanificacioncalidad implements Serializable
{
	private long iddetalle;
	private long idejecucionplanificacioncalidad;
	private long idplanificacioncalidad;
	private long ejecucionprocesocalidad;
	private long idprocesocalidad;
	private long pieza;
	private long piezareal;


	public Detalleejecucionplanificacioncalidad(){}
	public Detalleejecucionplanificacioncalidad(long iddetalle ,long idejecucionplanificacioncalidad ,long idplanificacioncalidad ,long ejecucionprocesocalidad ,long idprocesocalidad ,long pieza ,long piezareal){
		this.iddetalle = iddetalle;
		this.idejecucionplanificacioncalidad = idejecucionplanificacioncalidad;
		this.idplanificacioncalidad = idplanificacioncalidad;
		this.ejecucionprocesocalidad = ejecucionprocesocalidad;
		this.idprocesocalidad = idprocesocalidad;
		this.pieza = pieza;
		this.piezareal = piezareal;
	}

	public void setIddetalle(long iddetalle ){
		 this.iddetalle =iddetalle;
	}
	public long getIddetalle(){
		 return(iddetalle);
	}
	public void setIdejecucionplanificacioncalidad(long idejecucionplanificacioncalidad ){
		 this.idejecucionplanificacioncalidad =idejecucionplanificacioncalidad;
	}
	public long getIdejecucionplanificacioncalidad(){
		 return(idejecucionplanificacioncalidad);
	}
	public void setIdplanificacioncalidad(long idplanificacioncalidad ){
		 this.idplanificacioncalidad =idplanificacioncalidad;
	}
	public long getIdplanificacioncalidad(){
		 return(idplanificacioncalidad);
	}
	public void setEjecucionprocesocalidad(long ejecucionprocesocalidad ){
		 this.ejecucionprocesocalidad =ejecucionprocesocalidad;
	}
	public long getEjecucionprocesocalidad(){
		 return(ejecucionprocesocalidad);
	}
	public void setIdprocesocalidad(long idprocesocalidad ){
		 this.idprocesocalidad =idprocesocalidad;
	}
	public long getIdprocesocalidad(){
		 return(idprocesocalidad);
	}
	public void setPieza(long pieza ){
		 this.pieza =pieza;
	}
	public long getPieza(){
		 return(pieza);
	}
	public void setPiezareal(long piezareal ){
		 this.piezareal =piezareal;
	}
	public long getPiezareal(){
		 return(piezareal);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Detalleejecucionplanificacioncalidad :");
		ret.append("iddetalle='"+iddetalle+"'");
		ret.append(", idejecucionplanificacioncalidad='"+idejecucionplanificacioncalidad+"'");
		ret.append(", idplanificacioncalidad='"+idplanificacioncalidad+"'");
		ret.append(", ejecucionprocesocalidad='"+ejecucionprocesocalidad+"'");
		ret.append(", idprocesocalidad='"+idprocesocalidad+"'");
		ret.append(", pieza='"+pieza+"'");
		ret.append(", piezareal='"+piezareal+"'");
		return ret.toString();
	}
}
