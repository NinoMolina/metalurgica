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
* This class represents the primary key of the maquina table
*
*/

public class MaquinaPKDB implements Serializable
{
	private long idmaquina;


	public MaquinaPKDB(){}
	public MaquinaPKDB(long idmaquina){
		this.idmaquina = idmaquina;
	}

	public void setIdmaquina(long idmaquina ){
		 this.idmaquina =idmaquina;
	}
	public long getIdmaquina(){
		 return(idmaquina);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.MaquinaPK :");
		ret.append("idmaquina='"+idmaquina+"'");
		return ret.toString();
	}
}