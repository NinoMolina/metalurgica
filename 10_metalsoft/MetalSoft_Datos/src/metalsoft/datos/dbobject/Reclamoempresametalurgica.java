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
* This classes is the Data Transfer Object(Value Object) defination for the entity reclamoempresametalurgica
*
*/

public class Reclamoempresametalurgica implements Serializable
{
	private long idreclamo;
	private long nroreclamo;
	private long tiporeclamo;
	private String motivo;
	private Date fechareclamo;
	private long trabajotercerizado;


	public Reclamoempresametalurgica(){}
	public Reclamoempresametalurgica(long idreclamo ,long nroreclamo ,long tiporeclamo ,String motivo ,Date fechareclamo ,long trabajotercerizado){
		this.idreclamo = idreclamo;
		this.nroreclamo = nroreclamo;
		this.tiporeclamo = tiporeclamo;
		this.motivo = motivo;
		this.fechareclamo = fechareclamo;
		this.trabajotercerizado = trabajotercerizado;
	}

	public void setIdreclamo(long idreclamo ){
		 this.idreclamo =idreclamo;
	}
	public long getIdreclamo(){
		 return(idreclamo);
	}
	public void setNroreclamo(long nroreclamo ){
		 this.nroreclamo =nroreclamo;
	}
	public long getNroreclamo(){
		 return(nroreclamo);
	}
	public void setTiporeclamo(long tiporeclamo ){
		 this.tiporeclamo =tiporeclamo;
	}
	public long getTiporeclamo(){
		 return(tiporeclamo);
	}
	public void setMotivo(String motivo ){
		 this.motivo =motivo;
	}
	public String getMotivo(){
		 return(motivo);
	}
	public void setFechareclamo(Date fechareclamo ){
		 this.fechareclamo =fechareclamo;
	}
	public Date getFechareclamo(){
		 return(fechareclamo);
	}
	public void setTrabajotercerizado(long trabajotercerizado ){
		 this.trabajotercerizado =trabajotercerizado;
	}
	public long getTrabajotercerizado(){
		 return(trabajotercerizado);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Reclamoempresametalurgica :");
		ret.append("idreclamo='"+idreclamo+"'");
		ret.append(", nroreclamo='"+nroreclamo+"'");
		ret.append(", tiporeclamo='"+tiporeclamo+"'");
		ret.append(", motivo='"+motivo+"'");
		ret.append(", fechareclamo='"+fechareclamo+"'");
		ret.append(", trabajotercerizado='"+trabajotercerizado+"'");
		return ret.toString();
	}
}
