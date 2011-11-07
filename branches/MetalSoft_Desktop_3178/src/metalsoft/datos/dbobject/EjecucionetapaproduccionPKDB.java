/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Oct 19 00:24:14 ART 2010
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

public class EjecucionetapaproduccionPKDB implements Serializable
{
	private long idejecucion;
	private long idetapaproduccion;


	public EjecucionetapaproduccionPKDB(){}
	public EjecucionetapaproduccionPKDB(long idejecucion ,long idetapaproduccion){
		this.idejecucion = idejecucion;
		this.idetapaproduccion = idetapaproduccion;
	}

	public void setIdejecucion(long idejecucion ){
		 this.idejecucion =idejecucion;
	}
	public long getIdejecucion(){
		 return(idejecucion);
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
		ret.append("idejecucion='"+idejecucion+"'");
		ret.append(", idetapaproduccion='"+idetapaproduccion+"'");
		return ret.toString();
	}
}
