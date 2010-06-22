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
* This class represents the primary key of the responsable table
*
*/

public class ResponsablePK implements Serializable
{
	private long idresponsable;


	public ResponsablePK(){}
	public ResponsablePK(long idresponsable){
		this.idresponsable = idresponsable;
	}

	public void setIdresponsable(long idresponsable ){
		 this.idresponsable =idresponsable;
	}
	public long getIdresponsable(){
		 return(idresponsable);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.ResponsablePK :");
		ret.append("idresponsable='"+idresponsable+"'");
		return ret.toString();
	}
}
