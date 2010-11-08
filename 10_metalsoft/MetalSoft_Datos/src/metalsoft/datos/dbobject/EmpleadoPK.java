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
* This class represents the primary key of the empleado table
*
*/

public class EmpleadoPK implements Serializable
{
	private long idempleado;


	public EmpleadoPK(){}
	public EmpleadoPK(long idempleado){
		this.idempleado = idempleado;
	}

	public void setIdempleado(long idempleado ){
		 this.idempleado =idempleado;
	}
	public long getIdempleado(){
		 return(idempleado);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.EmpleadoPK :");
		ret.append("idempleado='"+idempleado+"'");
		return ret.toString();
	}
}