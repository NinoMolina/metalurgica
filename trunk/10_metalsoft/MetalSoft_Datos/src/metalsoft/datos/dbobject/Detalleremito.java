/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:38:50 GYT 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity detalleremito
*
*/

public class Detalleremito implements Serializable
{
	private long iddetalle;
	private long idremito;
	private int cantidad;
	private String descripcion;
	private long producto;


	public Detalleremito(){}
	public Detalleremito(long iddetalle ,long idremito ,int cantidad ,String descripcion ,long producto){
		this.iddetalle = iddetalle;
		this.idremito = idremito;
		this.cantidad = cantidad;
		this.descripcion = descripcion;
		this.producto = producto;
	}

	public void setIddetalle(long iddetalle ){
		 this.iddetalle =iddetalle;
	}
	public long getIddetalle(){
		 return(iddetalle);
	}
	public void setIdremito(long idremito ){
		 this.idremito =idremito;
	}
	public long getIdremito(){
		 return(idremito);
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
	public void setProducto(long producto ){
		 this.producto =producto;
	}
	public long getProducto(){
		 return(producto);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Detalleremito :");
		ret.append("iddetalle='"+iddetalle+"'");
		ret.append(", idremito='"+idremito+"'");
		ret.append(", cantidad='"+cantidad+"'");
		ret.append(", descripcion='"+descripcion+"'");
		ret.append(", producto='"+producto+"'");
		return ret.toString();
	}
}
