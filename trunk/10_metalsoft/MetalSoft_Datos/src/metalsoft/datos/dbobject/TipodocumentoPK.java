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
* This class represents the primary key of the tipodocumento table
*
*/

public class TipodocumentoPK implements Serializable
{
	private long idtipodocumento;


	public TipodocumentoPK(){}
	public TipodocumentoPK(long idtipodocumento){
		this.idtipodocumento = idtipodocumento;
	}

	public void setIdtipodocumento(long idtipodocumento ){
		 this.idtipodocumento =idtipodocumento;
	}
	public long getIdtipodocumento(){
		 return(idtipodocumento);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.TipodocumentoPK :");
		ret.append("idtipodocumento='"+idtipodocumento+"'");
		return ret.toString();
	}
}