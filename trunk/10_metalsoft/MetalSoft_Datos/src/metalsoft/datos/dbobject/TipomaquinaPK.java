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
* This class represents the primary key of the tipomaquina table
*
*/

public class TipomaquinaPK implements Serializable
{
	private long idtipomaquina;


	public TipomaquinaPK(){}
	public TipomaquinaPK(long idtipomaquina){
		this.idtipomaquina = idtipomaquina;
	}

	public void setIdtipomaquina(long idtipomaquina ){
		 this.idtipomaquina =idtipomaquina;
	}
	public long getIdtipomaquina(){
		 return(idtipomaquina);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.TipomaquinaPK :");
		ret.append("idtipomaquina='"+idtipomaquina+"'");
		return ret.toString();
	}
}