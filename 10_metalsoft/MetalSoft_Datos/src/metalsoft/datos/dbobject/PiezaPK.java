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
* This class represents the primary key of the pieza table
*
*/

public class PiezaPK implements Serializable
{
	private long idpieza;


	public PiezaPK(){}
	public PiezaPK(long idpieza){
		this.idpieza = idpieza;
	}

	public void setIdpieza(long idpieza ){
		 this.idpieza =idpieza;
	}
	public long getIdpieza(){
		 return(idpieza);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.PiezaPK :");
		ret.append("idpieza='"+idpieza+"'");
		return ret.toString();
	}
}