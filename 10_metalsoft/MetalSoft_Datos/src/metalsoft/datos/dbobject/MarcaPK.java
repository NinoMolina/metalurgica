/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:03 ART 2010
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
* This class represents the primary key of the marca table
*
*/

public class MarcaPK implements Serializable
{
	private long idmarca;


	public MarcaPK(){}
	public MarcaPK(long idmarca){
		this.idmarca = idmarca;
	}

	public void setIdmarca(long idmarca ){
		 this.idmarca =idmarca;
	}
	public long getIdmarca(){
		 return(idmarca);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.MarcaPK :");
		ret.append("idmarca='"+idmarca+"'");
		return ret.toString();
	}
}