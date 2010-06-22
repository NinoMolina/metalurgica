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
* This class represents the primary key of the ejecucionprocesocalidad table
*
*/

public class EjecucionprocesocalidadPK implements Serializable
{
	private long nroejecucion;
	private long idprocesocalidad;


	public EjecucionprocesocalidadPK(){}
	public EjecucionprocesocalidadPK(long nroejecucion ,long idprocesocalidad){
		this.nroejecucion = nroejecucion;
		this.idprocesocalidad = idprocesocalidad;
	}

	public void setNroejecucion(long nroejecucion ){
		 this.nroejecucion =nroejecucion;
	}
	public long getNroejecucion(){
		 return(nroejecucion);
	}
	public void setIdprocesocalidad(long idprocesocalidad ){
		 this.idprocesocalidad =idprocesocalidad;
	}
	public long getIdprocesocalidad(){
		 return(idprocesocalidad);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.EjecucionprocesocalidadPK :");
		ret.append("nroejecucion='"+nroejecucion+"'");
		ret.append(", idprocesocalidad='"+idprocesocalidad+"'");
		return ret.toString();
	}
}
