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
* This class represents the primary key of the detallecompra table
*
*/

public class DetallecompraPK implements Serializable
{
	private long iddetalle;
	private long idcompra;


	public DetallecompraPK(){}
	public DetallecompraPK(long iddetalle ,long idcompra){
		this.iddetalle = iddetalle;
		this.idcompra = idcompra;
	}

	public void setIddetalle(long iddetalle ){
		 this.iddetalle =iddetalle;
	}
	public long getIddetalle(){
		 return(iddetalle);
	}
	public void setIdcompra(long idcompra ){
		 this.idcompra =idcompra;
	}
	public long getIdcompra(){
		 return(idcompra);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.DetallecompraPK :");
		ret.append("iddetalle='"+iddetalle+"'");
		ret.append(", idcompra='"+idcompra+"'");
		return ret.toString();
	}
}
