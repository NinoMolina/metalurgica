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
* This classes is the Data Transfer Object(Value Object) defination for the entity compra
*
*/

public class Compra implements Serializable
{
	private long idcompra;
	private long nrocompra;
	private Date fechacompra;
	private Date vigencia;
	private long documentoremito;
	private double preciototal;
	private long estado;
	private String motivo;
	private long proveedor;


	public Compra(){}
	public Compra(long idcompra ,long nrocompra ,Date fechacompra ,Date vigencia ,long documentoremito ,double preciototal ,long estado ,String motivo ,long proveedor){
		this.idcompra = idcompra;
		this.nrocompra = nrocompra;
		this.fechacompra = fechacompra;
		this.vigencia = vigencia;
		this.documentoremito = documentoremito;
		this.preciototal = preciototal;
		this.estado = estado;
		this.motivo = motivo;
		this.proveedor = proveedor;
	}

	public void setIdcompra(long idcompra ){
		 this.idcompra =idcompra;
	}
	public long getIdcompra(){
		 return(idcompra);
	}
	public void setNrocompra(long nrocompra ){
		 this.nrocompra =nrocompra;
	}
	public long getNrocompra(){
		 return(nrocompra);
	}
	public void setFechacompra(Date fechacompra ){
		 this.fechacompra =fechacompra;
	}
	public Date getFechacompra(){
		 return(fechacompra);
	}
	public void setVigencia(Date vigencia ){
		 this.vigencia =vigencia;
	}
	public Date getVigencia(){
		 return(vigencia);
	}
	public void setDocumentoremito(long documentoremito ){
		 this.documentoremito =documentoremito;
	}
	public long getDocumentoremito(){
		 return(documentoremito);
	}
	public void setPreciototal(double preciototal ){
		 this.preciototal =preciototal;
	}
	public double getPreciototal(){
		 return(preciototal);
	}
	public void setEstado(long estado ){
		 this.estado =estado;
	}
	public long getEstado(){
		 return(estado);
	}
	public void setMotivo(String motivo ){
		 this.motivo =motivo;
	}
	public String getMotivo(){
		 return(motivo);
	}
	public void setProveedor(long proveedor ){
		 this.proveedor =proveedor;
	}
	public long getProveedor(){
		 return(proveedor);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Compra :");
		ret.append("idcompra='"+idcompra+"'");
		ret.append(", nrocompra='"+nrocompra+"'");
		ret.append(", fechacompra='"+fechacompra+"'");
		ret.append(", vigencia='"+vigencia+"'");
		ret.append(", documentoremito='"+documentoremito+"'");
		ret.append(", preciototal='"+preciototal+"'");
		ret.append(", estado='"+estado+"'");
		ret.append(", motivo='"+motivo+"'");
		ret.append(", proveedor='"+proveedor+"'");
		return ret.toString();
	}
}
