/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:40:03 GYT 2010
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
* This class represents the primary key of the reclamoproveedor table
*
*/

public class ReclamoproveedorPK implements Serializable
{
	private long idreclamo;


	public ReclamoproveedorPK(){}
	public ReclamoproveedorPK(long idreclamo){
		this.idreclamo = idreclamo;
	}

	public void setIdreclamo(long idreclamo ){
		 this.idreclamo =idreclamo;
	}
	public long getIdreclamo(){
		 return(idreclamo);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.ReclamoproveedorPK :");
		ret.append("idreclamo='"+idreclamo+"'");
		return ret.toString();
	}
}
