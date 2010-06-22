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
* This classes is the Data Transfer Object(Value Object) defination for the entity detallereclamoproveedor
*
*/

public class Detallereclamoproveedor implements Serializable
{
	private long iddetalle;
	private long idreclamo;
	private int cantidad;
	private String descripcion;
	private String motivo;
	private long iddetallecompra;
	private Date fechaegreso;
	private long idcompra;


	public Detallereclamoproveedor(){}
	public Detallereclamoproveedor(long iddetalle ,long idreclamo ,int cantidad ,String descripcion ,String motivo ,long iddetallecompra ,Date fechaegreso ,long idcompra){
		this.iddetalle = iddetalle;
		this.idreclamo = idreclamo;
		this.cantidad = cantidad;
		this.descripcion = descripcion;
		this.motivo = motivo;
		this.iddetallecompra = iddetallecompra;
		this.fechaegreso = fechaegreso;
		this.idcompra = idcompra;
	}

	public void setIddetalle(long iddetalle ){
		 this.iddetalle =iddetalle;
	}
	public long getIddetalle(){
		 return(iddetalle);
	}
	public void setIdreclamo(long idreclamo ){
		 this.idreclamo =idreclamo;
	}
	public long getIdreclamo(){
		 return(idreclamo);
	}
	public void setCantidad(int cantidad ){
		 this.cantidad =cantidad;
	}
	public int getCantidad(){
		 return(cantidad);
	}
	public void setDescripcion(String descripcion ){
		 this.descripcion =descripcion;
	}
	public String getDescripcion(){
		 return(descripcion);
	}
	public void setMotivo(String motivo ){
		 this.motivo =motivo;
	}
	public String getMotivo(){
		 return(motivo);
	}
	public void setIddetallecompra(long iddetallecompra ){
		 this.iddetallecompra =iddetallecompra;
	}
	public long getIddetallecompra(){
		 return(iddetallecompra);
	}
	public void setFechaegreso(Date fechaegreso ){
		 this.fechaegreso =fechaegreso;
	}
	public Date getFechaegreso(){
		 return(fechaegreso);
	}
	public void setIdcompra(long idcompra ){
		 this.idcompra =idcompra;
	}
	public long getIdcompra(){
		 return(idcompra);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Detallereclamoproveedor :");
		ret.append("iddetalle='"+iddetalle+"'");
		ret.append(", idreclamo='"+idreclamo+"'");
		ret.append(", cantidad='"+cantidad+"'");
		ret.append(", descripcion='"+descripcion+"'");
		ret.append(", motivo='"+motivo+"'");
		ret.append(", iddetallecompra='"+iddetallecompra+"'");
		ret.append(", fechaegreso='"+fechaegreso+"'");
		ret.append(", idcompra='"+idcompra+"'");
		return ret.toString();
	}
}
