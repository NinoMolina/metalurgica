/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:04 ART 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity proveedor
*
*/

public class ProveedorDB implements Serializable
{
	private long idproveedor;
	private long nroproveedor;
	private String razonsocial;
	private long responsable;
	private String telefono;
	private String celular;
	private String mail;
	private long domicilio;
	private Date fechaalta;
	private Date fechabaja;
	private String cuil;
	private long condicion;
	private String cuit;


	public ProveedorDB(){}
	public ProveedorDB(long idproveedor ,long nroproveedor ,String razonsocial ,long responsable ,String telefono ,String celular ,String mail ,long domicilio ,Date fechaalta ,Date fechabaja ,String cuil ,long condicion ,String cuit){
		this.idproveedor = idproveedor;
		this.nroproveedor = nroproveedor;
		this.razonsocial = razonsocial;
		this.responsable = responsable;
		this.telefono = telefono;
		this.celular = celular;
		this.mail = mail;
		this.domicilio = domicilio;
		this.fechaalta = fechaalta;
		this.fechabaja = fechabaja;
		this.cuil = cuil;
		this.condicion = condicion;
		this.cuit = cuit;
	}

	public void setIdproveedor(long idproveedor ){
		 this.idproveedor =idproveedor;
	}
	public long getIdproveedor(){
		 return(idproveedor);
	}
	public void setNroproveedor(long nroproveedor ){
		 this.nroproveedor =nroproveedor;
	}
	public long getNroproveedor(){
		 return(nroproveedor);
	}
	public void setRazonsocial(String razonsocial ){
		 this.razonsocial =razonsocial;
	}
	public String getRazonsocial(){
		 return(razonsocial);
	}
	public void setResponsable(long responsable ){
		 this.responsable =responsable;
	}
	public long getResponsable(){
		 return(responsable);
	}
	public void setTelefono(String telefono ){
		 this.telefono =telefono;
	}
	public String getTelefono(){
		 return(telefono);
	}
	public void setCelular(String celular ){
		 this.celular =celular;
	}
	public String getCelular(){
		 return(celular);
	}
	public void setMail(String mail ){
		 this.mail =mail;
	}
	public String getMail(){
		 return(mail);
	}
	public void setDomicilio(long domicilio ){
		 this.domicilio =domicilio;
	}
	public long getDomicilio(){
		 return(domicilio);
	}
	public void setFechaalta(Date fechaalta ){
		 this.fechaalta =fechaalta;
	}
	public Date getFechaalta(){
		 return(fechaalta);
	}
	public void setFechabaja(Date fechabaja ){
		 this.fechabaja =fechabaja;
	}
	public Date getFechabaja(){
		 return(fechabaja);
	}
	public void setCuil(String cuil ){
		 this.cuil =cuil;
	}
	public String getCuil(){
		 return(cuil);
	}
	public void setCondicion(long condicion ){
		 this.condicion =condicion;
	}
	public long getCondicion(){
		 return(condicion);
	}
	public void setCuit(String cuit ){
		 this.cuit =cuit;
	}
	public String getCuit(){
		 return(cuit);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Proveedor :");
		ret.append("idproveedor='"+idproveedor+"'");
		ret.append(", nroproveedor='"+nroproveedor+"'");
		ret.append(", razonsocial='"+razonsocial+"'");
		ret.append(", responsable='"+responsable+"'");
		ret.append(", telefono='"+telefono+"'");
		ret.append(", celular='"+celular+"'");
		ret.append(", mail='"+mail+"'");
		ret.append(", domicilio='"+domicilio+"'");
		ret.append(", fechaalta='"+fechaalta+"'");
		ret.append(", fechabaja='"+fechabaja+"'");
		ret.append(", cuil='"+cuil+"'");
		ret.append(", condicion='"+condicion+"'");
		ret.append(", cuit='"+cuit+"'");
		return ret.toString();
	}
}