/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:39:06 GYT 2010
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
* This class represents the primary key of the estadomaquina table
*
*/

public class EstadomaquinaPK implements Serializable
{
	private long idestado;


	public EstadomaquinaPK(){}
	public EstadomaquinaPK(long idestado){
		this.idestado = idestado;
	}

	public void setIdestado(long idestado ){
		 this.idestado =idestado;
	}
	public long getIdestado(){
		 return(idestado);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.EstadomaquinaPK :");
		ret.append("idestado='"+idestado+"'");
		return ret.toString();
	}
}
