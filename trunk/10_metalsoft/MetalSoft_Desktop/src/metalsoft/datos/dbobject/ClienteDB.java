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
* This classes is the Data Transfer Object(Value Object) defination for the entity cliente
*
*/

public class ClienteDB implements Serializable
{
	private long nrocliente;
	private long idcliente;
	private long prioridad;
	private long estado;
	private boolean esmoroso;
	private long usuario;
	private String razonsocial;
	private long responsable;
	private String telefono;
	private String celular;
	private String mail;
	private long domicilio;
	private Date fechaalta;
	private Date fechabaja;
	private String cuil;
	private long condicioniva;
	private String cuit;


	public ClienteDB(){}
	public ClienteDB(long nrocliente ,long idcliente ,long prioridad ,long estado ,boolean esmoroso ,long usuario ,String razonsocial ,long responsable ,String telefono ,String celular ,String mail ,long domicilio ,Date fechaalta ,Date fechabaja ,String cuil ,long condicioniva ,String cuit){
		this.nrocliente = nrocliente;
		this.idcliente = idcliente;
		this.prioridad = prioridad;
		this.estado = estado;
		this.esmoroso = esmoroso;
		this.usuario = usuario;
		this.razonsocial = razonsocial;
		this.responsable = responsable;
		this.telefono = telefono;
		this.celular = celular;
		this.mail = mail;
		this.domicilio = domicilio;
		this.fechaalta = fechaalta;
		this.fechabaja = fechabaja;
		this.cuil = cuil;
		this.condicioniva = condicioniva;
		this.cuit = cuit;
	}

	public void setNrocliente(long nrocliente ){
		 this.nrocliente =nrocliente;
	}
	public long getNrocliente(){
		 return(nrocliente);
	}
	public void setIdcliente(long idcliente ){
		 this.idcliente =idcliente;
	}
	public long getIdcliente(){
		 return(idcliente);
	}
	public void setPrioridad(long prioridad ){
		 this.prioridad =prioridad;
	}
	public long getPrioridad(){
		 return(prioridad);
	}
	public void setEstado(long estado ){
		 this.estado =estado;
	}
	public long getEstado(){
		 return(estado);
	}
	public void setEsmoroso(boolean esmoroso ){
		 this.esmoroso =esmoroso;
	}
	public boolean getEsmoroso(){
		 return(esmoroso);
	}
	public void setUsuario(long usuario ){
		 this.usuario =usuario;
	}
	public long getUsuario(){
		 return(usuario);
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
	public void setCondicioniva(long condicioniva ){
		 this.condicioniva =condicioniva;
	}
	public long getCondicioniva(){
		 return(condicioniva);
	}
	public void setCuit(String cuit ){
		 this.cuit =cuit;
	}
	public String getCuit(){
		 return(cuit);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Cliente :");
		ret.append("nrocliente='"+nrocliente+"'");
		ret.append(", idcliente='"+idcliente+"'");
		ret.append(", prioridad='"+prioridad+"'");
		ret.append(", estado='"+estado+"'");
		ret.append(", esmoroso='"+esmoroso+"'");
		ret.append(", usuario='"+usuario+"'");
		ret.append(", razonsocial='"+razonsocial+"'");
		ret.append(", responsable='"+responsable+"'");
		ret.append(", telefono='"+telefono+"'");
		ret.append(", celular='"+celular+"'");
		ret.append(", mail='"+mail+"'");
		ret.append(", domicilio='"+domicilio+"'");
		ret.append(", fechaalta='"+fechaalta+"'");
		ret.append(", fechabaja='"+fechabaja+"'");
		ret.append(", cuil='"+cuil+"'");
		ret.append(", condicioniva='"+condicioniva+"'");
		ret.append(", cuit='"+cuit+"'");
		return ret.toString();
	}
}