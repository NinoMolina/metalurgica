/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:09 ART 2010
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

public class MaquinaPK implements Serializable
{
	private long idmaquina;


	public MaquinaPK(){}
	public MaquinaPK(long idmaquina){
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
