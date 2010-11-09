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
* This class represents the primary key of the detalleproductoreal table
*
*/

public class DetalleproductorealPK implements Serializable
{
	private long iddetalle;
	private long idproductoreal;


	public DetalleproductorealPK(){}
	public DetalleproductorealPK(long iddetalle ,long idproductoreal){
		this.iddetalle = iddetalle;
		this.idproductoreal = idproductoreal;
	}

	public void setIddetalle(long iddetalle ){
		 this.iddetalle =iddetalle;
	}
	public long getIddetalle(){
		 return(iddetalle);
	}
	public void setIdproductoreal(long idproductoreal ){
		 this.idproductoreal =idproductoreal;
	}
	public long getIdproductoreal(){
		 return(idproductoreal);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.DetalleproductorealPK :");
		ret.append("iddetalle='"+iddetalle+"'");
		ret.append(", idproductoreal='"+idproductoreal+"'");
		return ret.toString();
	}
}