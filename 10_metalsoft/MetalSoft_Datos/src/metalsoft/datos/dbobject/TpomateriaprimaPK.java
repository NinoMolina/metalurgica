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
* This class represents the primary key of the tpomateriaprima table
*
*/

public class TpomateriaprimaPK implements Serializable
{
	private long idtipomateriaprima;


	public TpomateriaprimaPK(){}
	public TpomateriaprimaPK(long idtipomateriaprima){
		this.idtipomateriaprima = idtipomateriaprima;
	}

	public void setIdtipomateriaprima(long idtipomateriaprima ){
		 this.idtipomateriaprima =idtipomateriaprima;
	}
	public long getIdtipomateriaprima(){
		 return(idtipomateriaprima);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.TpomateriaprimaPK :");
		ret.append("idtipomateriaprima='"+idtipomateriaprima+"'");
		return ret.toString();
	}
}
