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
* This class represents the primary key of the trabajotercerizado table
*
*/

public class TrabajotercerizadoPK implements Serializable
{
	private long idtrabajo;


	public TrabajotercerizadoPK(){}
	public TrabajotercerizadoPK(long idtrabajo){
		this.idtrabajo = idtrabajo;
	}

	public void setIdtrabajo(long idtrabajo ){
		 this.idtrabajo =idtrabajo;
	}
	public long getIdtrabajo(){
		 return(idtrabajo);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.TrabajotercerizadoPK :");
		ret.append("idtrabajo='"+idtrabajo+"'");
		return ret.toString();
	}
}
