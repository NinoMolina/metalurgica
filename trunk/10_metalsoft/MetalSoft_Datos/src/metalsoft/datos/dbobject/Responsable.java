/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:11 ART 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity responsable
*
*/

public class Responsable implements Serializable
{
	private long idresponsable;
	private String nombre;
	private String apellido;
	private String telefono;
	private String email;
	private long domicilio;
	private int nrodocumento;
	private long tipodocumento;
	private String fax;


	public Responsable(){}
	public Responsable(long idresponsable ,String nombre ,String apellido ,String telefono ,String email ,long domicilio ,int nrodocumento ,long tipodocumento ,String fax){
		this.idresponsable = idresponsable;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.domicilio = domicilio;
		this.nrodocumento = nrodocumento;
		this.tipodocumento = tipodocumento;
		this.fax = fax;
	}

	public void setIdresponsable(long idresponsable ){
		 this.idresponsable =idresponsable;
	}
	public long getIdresponsable(){
		 return(idresponsable);
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
	public void setFax(String fax ){
		 this.fax =fax;
	}
	public String getFax(){
		 return(fax);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Responsable :");
		ret.append("idresponsable='"+idresponsable+"'");
		ret.append(", nombre='"+nombre+"'");
		ret.append(", apellido='"+apellido+"'");
		ret.append(", telefono='"+telefono+"'");
		ret.append(", email='"+email+"'");
		ret.append(", domicilio='"+domicilio+"'");
		ret.append(", nrodocumento='"+nrodocumento+"'");
		ret.append(", tipodocumento='"+tipodocumento+"'");
		ret.append(", fax='"+fax+"'");
		return ret.toString();
	}
}
