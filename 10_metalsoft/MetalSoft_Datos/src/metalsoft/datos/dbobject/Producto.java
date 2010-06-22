/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:10 ART 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity producto
*
*/

public class Producto implements Serializable
{
	private long idproducto;
	private long nroproducto;
	private String nombre;
	private int stock;
	private double preciounitario;
	private String descripcion;
	private long codbarra;


	public Producto(){}
	public Producto(long idproducto ,long nroproducto ,String nombre ,int stock ,double preciounitario ,String descripcion ,long codbarra){
		this.idproducto = idproducto;
		this.nroproducto = nroproducto;
		this.nombre = nombre;
		this.stock = stock;
		this.preciounitario = preciounitario;
		this.descripcion = descripcion;
		this.codbarra = codbarra;
	}

	public void setIdproducto(long idproducto ){
		 this.idproducto =idproducto;
	}
	public long getIdproducto(){
		 return(idproducto);
	}
	public void setNroproducto(long nroproducto ){
		 this.nroproducto =nroproducto;
	}
	public long getNroproducto(){
		 return(nroproducto);
	}
	public void setNombre(String nombre ){
		 this.nombre =nombre;
	}
	public String getNombre(){
		 return(nombre);
	}
	public void setStock(int stock ){
		 this.stock =stock;
	}
	public int getStock(){
		 return(stock);
	}
	public void setPreciounitario(double preciounitario ){
		 this.preciounitario =preciounitario;
	}
	public double getPreciounitario(){
		 return(preciounitario);
	}
	public void setDescripcion(String descripcion ){
		 this.descripcion =descripcion;
	}
	public String getDescripcion(){
		 return(descripcion);
	}
	public void setCodbarra(long codbarra ){
		 this.codbarra =codbarra;
	}
	public long getCodbarra(){
		 return(codbarra);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Producto :");
		ret.append("idproducto='"+idproducto+"'");
		ret.append(", nroproducto='"+nroproducto+"'");
		ret.append(", nombre='"+nombre+"'");
		ret.append(", stock='"+stock+"'");
		ret.append(", preciounitario='"+preciounitario+"'");
		ret.append(", descripcion='"+descripcion+"'");
		ret.append(", codbarra='"+codbarra+"'");
		return ret.toString();
	}
}
