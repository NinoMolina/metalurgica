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
* This class represents the primary key of the mantenimientopreventivo table
*
*/

public class MantenimientopreventivoPK implements Serializable
{
	private long idmantenimientopreventivo;


	public MantenimientopreventivoPK(){}
	public MantenimientopreventivoPK(long idmantenimientopreventivo){
		this.idmantenimientopreventivo = idmantenimientopreventivo;
	}

	public void setIdmantenimientopreventivo(long idmantenimientopreventivo ){
		 this.idmantenimientopreventivo =idmantenimientopreventivo;
	}
	public long getIdmantenimientopreventivo(){
		 return(idmantenimientopreventivo);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.MantenimientopreventivoPK :");
		ret.append("idmantenimientopreventivo='"+idmantenimientopreventivo+"'");
		return ret.toString();
	}
}