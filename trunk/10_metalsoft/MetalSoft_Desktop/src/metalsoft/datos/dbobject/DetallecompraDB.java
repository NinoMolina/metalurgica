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
* This classes is the Data Transfer Object(Value Object) defination for the entity detallecompra
*
*/

public class DetallecompraDB implements Serializable
{
	private long iddetalle;
	private long idcompra;
	private int cantidad;
	private long materiaprima;
	private double preciohistorico;
	private Date fecharecepcionparcial;
	private long estado;


	public DetallecompraDB(){}
	public DetallecompraDB(long iddetalle ,long idcompra ,int cantidad ,long materiaprima ,double preciohistorico ,Date fecharecepcionparcial ,long estado){
		this.iddetalle = iddetalle;
		this.idcompra = idcompra;
		this.cantidad = cantidad;
		this.materiaprima = materiaprima;
		this.preciohistorico = preciohistorico;
		this.fecharecepcionparcial = fecharecepcionparcial;
		this.estado = estado;
	}

	public void setIddetalle(long iddetalle ){
		 this.iddetalle =iddetalle;
	}
	public long getIddetalle(){
		 return(iddetalle);
	}
	public void setIdcompra(long idcompra ){
		 this.idcompra =idcompra;
	}
	public long getIdcompra(){
		 return(idcompra);
	}
	public void setCantidad(int cantidad ){
		 this.cantidad =cantidad;
	}
	public int getCantidad(){
		 return(cantidad);
	}
	public void setMateriaprima(long materiaprima ){
		 this.materiaprima =materiaprima;
	}
	public long getMateriaprima(){
		 return(materiaprima);
	}
	public void setPreciohistorico(double preciohistorico ){
		 this.preciohistorico =preciohistorico;
	}
	public double getPreciohistorico(){
		 return(preciohistorico);
	}
	public void setFecharecepcionparcial(Date fecharecepcionparcial ){
		 this.fecharecepcionparcial =fecharecepcionparcial;
	}
	public Date getFecharecepcionparcial(){
		 return(fecharecepcionparcial);
	}
	public void setEstado(long estado ){
		 this.estado =estado;
	}
	public long getEstado(){
		 return(estado);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Detallecompra :");
		ret.append("iddetalle='"+iddetalle+"'");
		ret.append(", idcompra='"+idcompra+"'");
		ret.append(", cantidad='"+cantidad+"'");
		ret.append(", materiaprima='"+materiaprima+"'");
		ret.append(", preciohistorico='"+preciohistorico+"'");
		ret.append(", fecharecepcionparcial='"+fecharecepcionparcial+"'");
		ret.append(", estado='"+estado+"'");
		return ret.toString();
	}
}
