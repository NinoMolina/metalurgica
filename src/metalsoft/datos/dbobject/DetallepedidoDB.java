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
* This classes is the Data Transfer Object(Value Object) defination for the entity detallepedido
*
*/

public class DetallepedidoDB implements Serializable
{
	private long iddetalle;
	private long idpedido;
	private double precio;
	private int cantidad;
	private long producto;


	public DetallepedidoDB(){}
	public DetallepedidoDB(long iddetalle ,long idpedido ,double precio ,int cantidad ,long producto){
		this.iddetalle = iddetalle;
		this.idpedido = idpedido;
		this.precio = precio;
		this.cantidad = cantidad;
		this.producto = producto;
	}

	public void setIddetalle(long iddetalle ){
		 this.iddetalle =iddetalle;
	}
	public long getIddetalle(){
		 return(iddetalle);
	}
	public void setIdpedido(long idpedido ){
		 this.idpedido =idpedido;
	}
	public long getIdpedido(){
		 return(idpedido);
	}
	public void setPrecio(double precio ){
		 this.precio =precio;
	}
	public double getPrecio(){
		 return(precio);
	}
	public void setCantidad(int cantidad ){
		 this.cantidad =cantidad;
	}
	public int getCantidad(){
		 return(cantidad);
	}
	public void setProducto(long producto ){
		 this.producto =producto;
	}
	public long getProducto(){
		 return(producto);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Detallepedido :");
		ret.append("iddetalle='"+iddetalle+"'");
		ret.append(", idpedido='"+idpedido+"'");
		ret.append(", precio='"+precio+"'");
		ret.append(", cantidad='"+cantidad+"'");
		ret.append(", producto='"+producto+"'");
		return ret.toString();
	}
}