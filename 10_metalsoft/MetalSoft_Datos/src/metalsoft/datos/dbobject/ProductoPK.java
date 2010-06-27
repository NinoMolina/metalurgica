/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:04 ART 2010
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
* This class represents the primary key of the producto table
*
*/

public class ProductoPK implements Serializable
{
	private long idproducto;


	public ProductoPK(){}
	public ProductoPK(long idproducto){
		this.idproducto = idproducto;
	}

	public void setIdproducto(long idproducto ){
		 this.idproducto =idproducto;
	}
	public long getIdproducto(){
		 return(idproducto);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.ProductoPK :");
		ret.append("idproducto='"+idproducto+"'");
		return ret.toString();
	}
}
