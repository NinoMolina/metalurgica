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
* This class represents the primary key of the estadoejecplanifpedido table
*
*/

public class EstadoejecplanifpedidoPKDB implements Serializable
{
	private long idestado;


	public EstadoejecplanifpedidoPKDB(){}
	public EstadoejecplanifpedidoPKDB(long idestado){
		this.idestado = idestado;
	}

	public void setIdestado(long idestado ){
		 this.idestado =idestado;
	}
	public long getIdestado(){
		 return(idestado);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.EstadoejecplanifpedidoPK :");
		ret.append("idestado='"+idestado+"'");
		return ret.toString();
	}
}
