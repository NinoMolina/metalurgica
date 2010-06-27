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
* This class represents the primary key of the detalleplanprocedimientos table
*
*/

public class DetalleplanprocedimientosPK implements Serializable
{
	private long iddetalle;
	private long idplanpprocedimientos;


	public DetalleplanprocedimientosPK(){}
	public DetalleplanprocedimientosPK(long iddetalle ,long idplanpprocedimientos){
		this.iddetalle = iddetalle;
		this.idplanpprocedimientos = idplanpprocedimientos;
	}

	public void setIddetalle(long iddetalle ){
		 this.iddetalle =iddetalle;
	}
	public long getIddetalle(){
		 return(iddetalle);
	}
	public void setIdplanpprocedimientos(long idplanpprocedimientos ){
		 this.idplanpprocedimientos =idplanpprocedimientos;
	}
	public long getIdplanpprocedimientos(){
		 return(idplanpprocedimientos);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.DetalleplanprocedimientosPK :");
		ret.append("iddetalle='"+iddetalle+"'");
		ret.append(", idplanpprocedimientos='"+idplanpprocedimientos+"'");
		return ret.toString();
	}
}
