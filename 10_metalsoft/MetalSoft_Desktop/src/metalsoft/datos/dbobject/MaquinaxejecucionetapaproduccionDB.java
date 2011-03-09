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
* This classes is the Data Transfer Object(Value Object) defination for the entity maquinaxejecucionetapaproduccion
*
*/

public class MaquinaxejecucionetapaproduccionDB implements Serializable
{
	private long idejecucionetapaproduccion;
	private long idetapaproduccion;
	private long idmaquina;
	private Time horasmaquina;
	private Time horashombre;


	public MaquinaxejecucionetapaproduccionDB(){}
	public MaquinaxejecucionetapaproduccionDB(long idejecucionetapaproduccion ,long idetapaproduccion ,long idmaquina ,Time horasmaquina ,Time horashombre){
		this.idejecucionetapaproduccion = idejecucionetapaproduccion;
		this.idetapaproduccion = idetapaproduccion;
		this.idmaquina = idmaquina;
		this.horasmaquina = horasmaquina;
		this.horashombre = horashombre;
	}

	public void setIdejecucionetapaproduccion(long idejecucionetapaproduccion ){
		 this.idejecucionetapaproduccion =idejecucionetapaproduccion;
	}
	public long getIdejecucionetapaproduccion(){
		 return(idejecucionetapaproduccion);
	}
	public void setIdetapaproduccion(long idetapaproduccion ){
		 this.idetapaproduccion =idetapaproduccion;
	}
	public long getIdetapaproduccion(){
		 return(idetapaproduccion);
	}
	public void setIdmaquina(long idmaquina ){
		 this.idmaquina =idmaquina;
	}
	public long getIdmaquina(){
		 return(idmaquina);
	}
	public void setHorasmaquina(Time horasmaquina ){
		 this.horasmaquina =horasmaquina;
	}
	public Time getHorasmaquina(){
		 return(horasmaquina);
	}
	public void setHorashombre(Time horashombre ){
		 this.horashombre =horashombre;
	}
	public Time getHorashombre(){
		 return(horashombre);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Maquinaxejecucionetapaproduccion :");
		ret.append("idejecucionetapaproduccion='"+idejecucionetapaproduccion+"'");
		ret.append(", idetapaproduccion='"+idetapaproduccion+"'");
		ret.append(", idmaquina='"+idmaquina+"'");
		ret.append(", horasmaquina='"+horasmaquina+"'");
		ret.append(", horashombre='"+horashombre+"'");
		return ret.toString();
	}
}
