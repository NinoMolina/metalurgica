/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:03 ART 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity planificacioncalidad
*
*/

public class PlanificacioncalidadDB implements Serializable
{
	private long idplanificacion;
	private long nroplanificacion;
	private Date fechacreacion;
	private String observaciones;
	private Date fechainicioprevista;
	private Date fechafinprevista;
	private long pedido;


	public PlanificacioncalidadDB(){}
	public PlanificacioncalidadDB(long idplanificacion ,long nroplanificacion ,Date fechacreacion ,String observaciones ,Date fechainicioprevista ,Date fechafinprevista ,long pedido){
		this.idplanificacion = idplanificacion;
		this.nroplanificacion = nroplanificacion;
		this.fechacreacion = fechacreacion;
		this.observaciones = observaciones;
		this.fechainicioprevista = fechainicioprevista;
		this.fechafinprevista = fechafinprevista;
		this.pedido = pedido;
	}

	public void setIdplanificacion(long idplanificacion ){
		 this.idplanificacion =idplanificacion;
	}
	public long getIdplanificacion(){
		 return(idplanificacion);
	}
	public void setNroplanificacion(long nroplanificacion ){
		 this.nroplanificacion =nroplanificacion;
	}
	public long getNroplanificacion(){
		 return(nroplanificacion);
	}
	public void setFechacreacion(Date fechacreacion ){
		 this.fechacreacion =fechacreacion;
	}
	public Date getFechacreacion(){
		 return(fechacreacion);
	}
	public void setObservaciones(String observaciones ){
		 this.observaciones =observaciones;
	}
	public String getObservaciones(){
		 return(observaciones);
	}
	public void setFechainicioprevista(Date fechainicioprevista ){
		 this.fechainicioprevista =fechainicioprevista;
	}
	public Date getFechainicioprevista(){
		 return(fechainicioprevista);
	}
	public void setFechafinprevista(Date fechafinprevista ){
		 this.fechafinprevista =fechafinprevista;
	}
	public Date getFechafinprevista(){
		 return(fechafinprevista);
	}
	public void setPedido(long pedido ){
		 this.pedido =pedido;
	}
	public long getPedido(){
		 return(pedido);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Planificacioncalidad :");
		ret.append("idplanificacion='"+idplanificacion+"'");
		ret.append(", nroplanificacion='"+nroplanificacion+"'");
		ret.append(", fechacreacion='"+fechacreacion+"'");
		ret.append(", observaciones='"+observaciones+"'");
		ret.append(", fechainicioprevista='"+fechainicioprevista+"'");
		ret.append(", fechafinprevista='"+fechafinprevista+"'");
		ret.append(", pedido='"+pedido+"'");
		return ret.toString();
	}
}
