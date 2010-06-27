/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:02 ART 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity ejecucionprocesocalidad
*
*/

public class Ejecucionprocesocalidad implements Serializable
{
	private long idejecucion;
	private long idprocesocalidad;
	private Time duracionreal;
	private String resultado;
	private long estado;
	private long nroejecucion;


	public Ejecucionprocesocalidad(){}
	public Ejecucionprocesocalidad(long idejecucion ,long idprocesocalidad ,Time duracionreal ,String resultado ,long estado ,long nroejecucion){
		this.idejecucion = idejecucion;
		this.idprocesocalidad = idprocesocalidad;
		this.duracionreal = duracionreal;
		this.resultado = resultado;
		this.estado = estado;
		this.nroejecucion = nroejecucion;
	}

	public void setIdejecucion(long idejecucion ){
		 this.idejecucion =idejecucion;
	}
	public long getIdejecucion(){
		 return(idejecucion);
	}
	public void setIdprocesocalidad(long idprocesocalidad ){
		 this.idprocesocalidad =idprocesocalidad;
	}
	public long getIdprocesocalidad(){
		 return(idprocesocalidad);
	}
	public void setDuracionreal(Time duracionreal ){
		 this.duracionreal =duracionreal;
	}
	public Time getDuracionreal(){
		 return(duracionreal);
	}
	public void setResultado(String resultado ){
		 this.resultado =resultado;
	}
	public String getResultado(){
		 return(resultado);
	}
	public void setEstado(long estado ){
		 this.estado =estado;
	}
	public long getEstado(){
		 return(estado);
	}
	public void setNroejecucion(long nroejecucion ){
		 this.nroejecucion =nroejecucion;
	}
	public long getNroejecucion(){
		 return(nroejecucion);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Ejecucionprocesocalidad :");
		ret.append("idejecucion='"+idejecucion+"'");
		ret.append(", idprocesocalidad='"+idprocesocalidad+"'");
		ret.append(", duracionreal='"+duracionreal+"'");
		ret.append(", resultado='"+resultado+"'");
		ret.append(", estado='"+estado+"'");
		ret.append(", nroejecucion='"+nroejecucion+"'");
		return ret.toString();
	}
}
