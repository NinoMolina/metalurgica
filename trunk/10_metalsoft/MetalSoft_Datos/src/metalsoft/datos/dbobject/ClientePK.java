/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:07 ART 2010
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
* This class represents the primary key of the cliente table
*
*/

public class ClientePK implements Serializable
{
	private long idcliente;


	public ClientePK(){}
	public ClientePK(long idcliente){
		this.idcliente = idcliente;
	}

	public void setIdcliente(long idcliente ){
		 this.idcliente =idcliente;
	}
	public long getIdcliente(){
		 return(idcliente);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.ClientePK :");
		ret.append("idcliente='"+idcliente+"'");
		return ret.toString();
	}
}
