/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:08 ART 2010
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
* This class represents the primary key of the ejecucionetapaproduccion table
*
*/

public class EjecucionetapaproduccionPK implements Serializable
{
	private long nroejecucion;
	private long idetapaproduccion;


	public EjecucionetapaproduccionPK(){}
	public EjecucionetapaproduccionPK(long nroejecucion ,long idetapaproduccion){
		this.nroejecucion = nroejecucion;
		this.idetapaproduccion = idetapaproduccion;
	}

	public void setNroejecucion(long nroejecucion ){
		 this.nroejecucion =nroejecucion;
	}
	public long getNroejecucion(){
		 return(nroejecucion);
	}
	public void setIdetapaproduccion(long idetapaproduccion ){
		 this.idetapaproduccion =idetapaproduccion;
	}
	public long getIdetapaproduccion(){
		 return(idetapaproduccion);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.EjecucionetapaproduccionPK :");
		ret.append("nroejecucion='"+nroejecucion+"'");
		ret.append(", idetapaproduccion='"+idetapaproduccion+"'");
		return ret.toString();
	}
}
