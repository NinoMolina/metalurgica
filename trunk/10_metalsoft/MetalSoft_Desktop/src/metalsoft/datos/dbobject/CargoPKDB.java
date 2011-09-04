/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:01 ART 2010
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
* This class represents the primary key of the cargo table
*
*/

public class CargoPKDB implements Serializable
{
	private long idcargo;


	public CargoPKDB(){}
	public CargoPKDB(long idcargo){
		this.idcargo = idcargo;
	}

	public void setIdcargo(long idcargo ){
		 this.idcargo =idcargo;
	}
	public long getIdcargo(){
		 return(idcargo);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.CargoPK :");
		ret.append("idcargo='"+idcargo+"'");
		return ret.toString();
	}
}
