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
* This class represents the primary key of the condicioniva table
*
*/

public class CondicionivaPK implements Serializable
{
	private long idcondicioniva;


	public CondicionivaPK(){}
	public CondicionivaPK(long idcondicioniva){
		this.idcondicioniva = idcondicioniva;
	}

	public void setIdcondicioniva(long idcondicioniva ){
		 this.idcondicioniva =idcondicioniva;
	}
	public long getIdcondicioniva(){
		 return(idcondicioniva);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.CondicionivaPK :");
		ret.append("idcondicioniva='"+idcondicioniva+"'");
		return ret.toString();
	}
}