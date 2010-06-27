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
* This classes is the Data Transfer Object(Value Object) defination for the entity pedidomatriz
*
*/

public class Pedidomatriz implements Serializable
{
	private long idpedidomatriz;
	private long nropedidomatriz;
	private Date fechapedidomatriz;
	private long idmatriz;
	private String observaciones;


	public Pedidomatriz(){}
	public Pedidomatriz(long idpedidomatriz ,long nropedidomatriz ,Date fechapedidomatriz ,long idmatriz ,String observaciones){
		this.idpedidomatriz = idpedidomatriz;
		this.nropedidomatriz = nropedidomatriz;
		this.fechapedidomatriz = fechapedidomatriz;
		this.idmatriz = idmatriz;
		this.observaciones = observaciones;
	}

	public void setIdpedidomatriz(long idpedidomatriz ){
		 this.idpedidomatriz =idpedidomatriz;
	}
	public long getIdpedidomatriz(){
		 return(idpedidomatriz);
	}
	public void setNropedidomatriz(long nropedidomatriz ){
		 this.nropedidomatriz =nropedidomatriz;
	}
	public long getNropedidomatriz(){
		 return(nropedidomatriz);
	}
	public void setFechapedidomatriz(Date fechapedidomatriz ){
		 this.fechapedidomatriz =fechapedidomatriz;
	}
	public Date getFechapedidomatriz(){
		 return(fechapedidomatriz);
	}
	public void setIdmatriz(long idmatriz ){
		 this.idmatriz =idmatriz;
	}
	public long getIdmatriz(){
		 return(idmatriz);
	}
	public void setObservaciones(String observaciones ){
		 this.observaciones =observaciones;
	}
	public String getObservaciones(){
		 return(observaciones);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Pedidomatriz :");
		ret.append("idpedidomatriz='"+idpedidomatriz+"'");
		ret.append(", nropedidomatriz='"+nropedidomatriz+"'");
		ret.append(", fechapedidomatriz='"+fechapedidomatriz+"'");
		ret.append(", idmatriz='"+idmatriz+"'");
		ret.append(", observaciones='"+observaciones+"'");
		return ret.toString();
	}
}
