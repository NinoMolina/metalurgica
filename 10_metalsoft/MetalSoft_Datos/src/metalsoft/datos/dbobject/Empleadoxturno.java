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
* This classes is the Data Transfer Object(Value Object) defination for the entity empleadoxturno
*
*/

public class Empleadoxturno implements Serializable
{
	private long idempleado;
	private long idturno;


	public Empleadoxturno(){}
	public Empleadoxturno(long idempleado ,long idturno){
		this.idempleado = idempleado;
		this.idturno = idturno;
	}

	public void setIdempleado(long idempleado ){
		 this.idempleado =idempleado;
	}
	public long getIdempleado(){
		 return(idempleado);
	}
	public void setIdturno(long idturno ){
		 this.idturno =idturno;
	}
	public long getIdturno(){
		 return(idturno);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Empleadoxturno :");
		ret.append("idempleado='"+idempleado+"'");
		ret.append(", idturno='"+idturno+"'");
		return ret.toString();
	}
}
