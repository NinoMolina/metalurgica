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
import java.util.*;
import java.io.Serializable;
/**
* 
* Instance of this class stores the number of
* records in planificacioncalidad table
* 
*/

public class PlanificacioncalidadRecordCount implements Serializable
{
	private int count;
	public  PlanificacioncalidadRecordCount(){
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
		ret.append("metalsoft.datos.dbobject.PlanificacioncalidadRecordCount :");
		ret.append("count ='"+ count +"'");
		return ret.toString();
	}
}
