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
* This class represents the primary key of the codigodebarra table
*
*/

public class CodigodebarraPK implements Serializable
{
	private long idcodigo;


	public CodigodebarraPK(){}
	public CodigodebarraPK(long idcodigo){
		this.idcodigo = idcodigo;
	}

	public void setIdcodigo(long idcodigo ){
		 this.idcodigo =idcodigo;
	}
	public long getIdcodigo(){
		 return(idcodigo);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.CodigodebarraPK :");
		ret.append("idcodigo='"+idcodigo+"'");
		return ret.toString();
	}
}
