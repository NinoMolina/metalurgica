/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:05 ART 2010
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
* This class represents the primary key of the rol table
*
*/

public class RolPK implements Serializable
{
	private long idrol;


	public RolPK(){}
	public RolPK(long idrol){
		this.idrol = idrol;
	}

	public void setIdrol(long idrol ){
		 this.idrol =idrol;
	}
	public long getIdrol(){
		 return(idrol);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.RolPK :");
		ret.append("idrol='"+idrol+"'");
		return ret.toString();
	}
}