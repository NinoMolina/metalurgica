/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:02 ART 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity empleado
*
*/

public class Empleado implements Serializable
{
	private long idempleado;
	private long legajo;
	private Date fechaingreso;
	private String nombre;
	private String apellido;
	private String telefono;
	private String email;
	private long domicilio;
	private int nrodocumento;
	private long tipodocumento;
	private long categoria;
	private long usuario;
	private Date fechaegreso;
	private String motivoegreso;
	private long cargo;


	public Empleado(){}
	public Empleado(long idempleado ,long legajo ,Date fechaingreso ,String nombre ,String apellido ,String telefono ,String email ,long domicilio ,int nrodocumento ,long tipodocumento ,long categoria ,long usuario ,Date fechaegreso ,String motivoegreso ,long cargo){
		this.idempleado = idempleado;
		this.legajo = legajo;
		this.fechaingreso = fechaingreso;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.domicilio = domicilio;
		this.nrodocumento = nrodocumento;
		this.tipodocumento = tipodocumento;
		this.categoria = categoria;
		this.usuario = usuario;
		this.fechaegreso = fechaegreso;
		this.motivoegreso = motivoegreso;
		this.cargo = cargo;
	}

	public void setIdempleado(long idempleado ){
		 this.idempleado =idempleado;
	}
	public long getIdempleado(){
		 return(idempleado);
	}
	public void setLegajo(long legajo ){
		 this.legajo =legajo;
	}
	public long getLegajo(){
		 return(legajo);
	}
	public void setFechaingreso(Date fechaingreso ){
		 this.fechaingreso =fechaingreso;
	}
	public Date getFechaingreso(){
		 return(fechaingreso);
	}
	public void setNombre(String nombre ){
		 this.nombre =nombre;
	}
	public String getNombre(){
		 return(nombre);
	}
	public void setApellido(String apellido ){
		 this.apellido =apellido;
	}
	public String getApellido(){
		 return(apellido);
	}
	public void setTelefono(String telefono ){
		 this.telefono =telefono;
	}
	public String getTelefono(){
		 return(telefono);
	}
	public void setEmail(String email ){
		 this.email =email;
	}
	public String getEmail(){
		 return(email);
	}
	public void setDomicilio(long domicilio ){
		 this.domicilio =domicilio;
	}
	public long getDomicilio(){
		 return(domicilio);
	}
	public void setNrodocumento(int nrodocumento ){
		 this.nrodocumento =nrodocumento;
	}
	public int getNrodocumento(){
		 return(nrodocumento);
	}
	public void setTipodocumento(long tipodocumento ){
		 this.tipodocumento =tipodocumento;
	}
	public long getTipodocumento(){
		 return(tipodocumento);
	}
	public void setCategoria(long categoria ){
		 this.categoria =categoria;
	}
	public long getCategoria(){
		 return(categoria);
	}
	public void setUsuario(long usuario ){
		 this.usuario =usuario;
	}
	public long getUsuario(){
		 return(usuario);
	}
	public void setFechaegreso(Date fechaegreso ){
		 this.fechaegreso =fechaegreso;
	}
	public Date getFechaegreso(){
		 return(fechaegreso);
	}
	public void setMotivoegreso(String motivoegreso ){
		 this.motivoegreso =motivoegreso;
	}
	public String getMotivoegreso(){
		 return(motivoegreso);
	}
	public void setCargo(long cargo ){
		 this.cargo =cargo;
	}
	public long getCargo(){
		 return(cargo);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Empleado :");
		ret.append("idempleado='"+idempleado+"'");
		ret.append(", legajo='"+legajo+"'");
		ret.append(", fechaingreso='"+fechaingreso+"'");
		ret.append(", nombre='"+nombre+"'");
		ret.append(", apellido='"+apellido+"'");
		ret.append(", telefono='"+telefono+"'");
		ret.append(", email='"+email+"'");
		ret.append(", domicilio='"+domicilio+"'");
		ret.append(", nrodocumento='"+nrodocumento+"'");
		ret.append(", tipodocumento='"+tipodocumento+"'");
		ret.append(", categoria='"+categoria+"'");
		ret.append(", usuario='"+usuario+"'");
		ret.append(", fechaegreso='"+fechaegreso+"'");
		ret.append(", motivoegreso='"+motivoegreso+"'");
		ret.append(", cargo='"+cargo+"'");
		return ret.toString();
	}
}
