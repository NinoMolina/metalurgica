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
* This class represents the primary key of the provincia table
*
*/

public class ProvinciaPK implements Serializable
{
	private long idprovincia;


	public ProvinciaPK(){}
	public ProvinciaPK(long idprovincia){
		this.idprovincia = idprovincia;
	}

	public void setIdprovincia(long idprovincia ){
		 this.idprovincia =idprovincia;
	}
	public long getIdprovincia(){
		 return(idprovincia);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.ProvinciaPK :");
		ret.append("idprovincia='"+idprovincia+"'");
		return ret.toString();
	}
}
