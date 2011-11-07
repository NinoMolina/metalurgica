/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Aug 30 13:24:00 ART 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity detallepresupuesto
*
*/

public class DetallepresupuestoDB implements Serializable
{
	private long iddetalle;
	private long idpresupuesto;
	private long iddetallepedido;
	private long idproducto;
	private int cantidad;
	private double precio;


	public DetallepresupuestoDB(){}
	public DetallepresupuestoDB(long iddetalle ,long idpresupuesto ,long iddetallepedido ,long idproducto ,int cantidad ,double precio){
		this.iddetalle = iddetalle;
		this.idpresupuesto = idpresupuesto;
		this.iddetallepedido = iddetallepedido;
		this.idproducto = idproducto;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public void setIddetalle(long iddetalle ){
		 this.iddetalle =iddetalle;
	}
	public long getIddetalle(){
		 return(iddetalle);
	}
	public void setIdpresupuesto(long idpresupuesto ){
		 this.idpresupuesto =idpresupuesto;
	}
	public long getIdpresupuesto(){
		 return(idpresupuesto);
	}
	public void setIddetallepedido(long iddetallepedido ){
		 this.iddetallepedido =iddetallepedido;
	}
	public long getIddetallepedido(){
		 return(iddetallepedido);
	}
	public void setIdproducto(long idproducto ){
		 this.idproducto =idproducto;
	}
	public long getIdproducto(){
		 return(idproducto);
	}
	public void setCantidad(int cantidad ){
		 this.cantidad =cantidad;
	}
	public int getCantidad(){
		 return(cantidad);
	}
	public void setPrecio(double precio ){
		 this.precio =precio;
	}
	public double getPrecio(){
		 return(precio);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Detallepresupuesto :");
		ret.append("iddetalle='"+iddetalle+"'");
		ret.append(", idpresupuesto='"+idpresupuesto+"'");
		ret.append(", iddetallepedido='"+iddetallepedido+"'");
		ret.append(", idproducto='"+idproducto+"'");
		ret.append(", cantidad='"+cantidad+"'");
		ret.append(", precio='"+precio+"'");
		return ret.toString();
	}
}
