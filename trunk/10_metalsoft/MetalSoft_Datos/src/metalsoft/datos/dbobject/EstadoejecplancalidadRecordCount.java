/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:39:03 GYT 2010
*
* DAO-Generator Version 1.2
*
*/
package metalsoft.datos.dbobject;
import java.math.*;
import java.sql.*;
import java.net.URL;
import java.util.*;
import java.io.Serializable;
/**
* 
* Instance of this class stores the number of
* records in estadoejecplancalidad table
* 
*/

public class EstadoejecplancalidadRecordCount implements Serializable
{
	private int count;
	public  EstadoejecplancalidadRecordCount(){
	}
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.EstadoejecplancalidadRecordCount :");
		ret.append("count ='"+ count +"'");
		return ret.toString();
	}
}
