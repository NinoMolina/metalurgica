/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:09 ART 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity factura
*
*/

public class Factura implements Serializable
{
	private long idfactura;
	private long nrofactura;
	private Date fechaemision;
	private long tipoiva;
	private Date fecharealcobro;
	private long formapago;
	private Date fechavencimiento;
	private long usuario;
	private long estado;
	private String tipofactura;


	public Factura(){}
	public Factura(long idfactura ,long nrofactura ,Date fechaemision ,long tipoiva ,Date fecharealcobro ,long formapago ,Date fechavencimiento ,long usuario ,long estado ,String tipofactura){
		this.idfactura = idfactura;
		this.nrofactura = nrofactura;
		this.fechaemision = fechaemision;
		this.tipoiva = tipoiva;
		this.fecharealcobro = fecharealcobro;
		this.formapago = formapago;
		this.fechavencimiento = fechavencimiento;
		this.usuario = usuario;
		this.estado = estado;
		this.tipofactura = tipofactura;
	}

	public void setIdfactura(long idfactura ){
		 this.idfactura =idfactura;
	}
	public long getIdfactura(){
		 return(idfactura);
	}
	public void setNrofactura(long nrofactura ){
		 this.nrofactura =nrofactura;
	}
	public long getNrofactura(){
		 return(nrofactura);
	}
	public void setFechaemision(Date fechaemision ){
		 this.fechaemision =fechaemision;
	}
	public Date getFechaemision(){
		 return(fechaemision);
	}
	public void setTipoiva(long tipoiva ){
		 this.tipoiva =tipoiva;
	}
	public long getTipoiva(){
		 return(tipoiva);
	}
	public void setFecharealcobro(Date fecharealcobro ){
		 this.fecharealcobro =fecharealcobro;
	}
	public Date getFecharealcobro(){
		 return(fecharealcobro);
	}
	public void setFormapago(long formapago ){
		 this.formapago =formapago;
	}
	public long getFormapago(){
		 return(formapago);
	}
	public void setFechavencimiento(Date fechavencimiento ){
		 this.fechavencimiento =fechavencimiento;
	}
	public Date getFechavencimiento(){
		 return(fechavencimiento);
	}
	public void setUsuario(long usuario ){
		 this.usuario =usuario;
	}
	public long getUsuario(){
		 return(usuario);
	}
	public void setEstado(long estado ){
		 this.estado =estado;
	}
	public long getEstado(){
		 return(estado);
	}
	public void setTipofactura(String tipofactura ){
		 this.tipofactura =tipofactura;
	}
	public String getTipofactura(){
		 return(tipofactura);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Factura :");
		ret.append("idfactura='"+idfactura+"'");
		ret.append(", nrofactura='"+nrofactura+"'");
		ret.append(", fechaemision='"+fechaemision+"'");
		ret.append(", tipoiva='"+tipoiva+"'");
		ret.append(", fecharealcobro='"+fecharealcobro+"'");
		ret.append(", formapago='"+formapago+"'");
		ret.append(", fechavencimiento='"+fechavencimiento+"'");
		ret.append(", usuario='"+usuario+"'");
		ret.append(", estado='"+estado+"'");
		ret.append(", tipofactura='"+tipofactura+"'");
		return ret.toString();
	}
}
