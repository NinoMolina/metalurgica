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
* This class represents the primary key of the matriz table
*
*/

public class MatrizPK implements Serializable
{
	private long idmatriz;


	public MatrizPK(){}
	public MatrizPK(long idmatriz){
		this.idmatriz = idmatriz;
	}

	public void setIdmatriz(long idmatriz ){
		 this.idmatriz =idmatriz;
	}
	public long getIdmatriz(){
		 return(idmatriz);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.MatrizPK :");
		ret.append("idmatriz='"+idmatriz+"'");
		return ret.toString();
	}
}
