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
* This class represents the primary key of the formadepago table
*
*/

public class FormadepagoPK implements Serializable
{
	private int idformapago;


	public FormadepagoPK(){}
	public FormadepagoPK(int idformapago){
		this.idformapago = idformapago;
	}

	public void setIdformapago(int idformapago ){
		 this.idformapago =idformapago;
	}
	public int getIdformapago(){
		 return(idformapago);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.FormadepagoPK :");
		ret.append("idformapago='"+idformapago+"'");
		return ret.toString();
	}
}
