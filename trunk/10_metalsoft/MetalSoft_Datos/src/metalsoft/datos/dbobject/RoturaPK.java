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
* This class represents the primary key of the rotura table
*
*/

public class RoturaPK implements Serializable
{
	private long idrotura;


	public RoturaPK(){}
	public RoturaPK(long idrotura){
		this.idrotura = idrotura;
	}

	public void setIdrotura(long idrotura ){
		 this.idrotura =idrotura;
	}
	public long getIdrotura(){
		 return(idrotura);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.RoturaPK :");
		ret.append("idrotura='"+idrotura+"'");
		return ret.toString();
	}
}
