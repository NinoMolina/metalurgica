/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Aug 30 13:24:00 ART 2010
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
* This class represents the primary key of the detallepiezapresupuesto table
*
*/

public class DetallepiezapresupuestoPKDB implements Serializable
{
	private long iddetalle;


	public DetallepiezapresupuestoPKDB(){}
	public DetallepiezapresupuestoPKDB(long iddetalle){
		this.iddetalle = iddetalle;
	}

	public void setIddetalle(long iddetalle ){
		 this.iddetalle =iddetalle;
	}
	public long getIddetalle(){
		 return(iddetalle);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.DetallepiezapresupuestoPK :");
		ret.append("iddetalle='"+iddetalle+"'");
		return ret.toString();
	}
}