/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Oct 05 14:15:16 ART 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity calendario
*
*/

public class CalendarioDB implements Serializable
{
	private int dia;
	private int mes;
	private int anio;
	private long id;
	private Date fecha;
	private boolean todoeldia;
	private Time horadesde;
	private Time horahasta;


	public CalendarioDB(){}
	public CalendarioDB(int dia ,int mes ,int anio ,long id ,Date fecha ,boolean todoeldia ,Time horadesde ,Time horahasta){
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
		this.id = id;
		this.fecha = fecha;
		this.todoeldia = todoeldia;
		this.horadesde = horadesde;
		this.horahasta = horahasta;
	}

	public void setDia(int dia ){
		 this.dia =dia;
	}
	public int getDia(){
		 return(dia);
	}
	public void setMes(int mes ){
		 this.mes =mes;
	}
	public int getMes(){
		 return(mes);
	}
	public void setAnio(int anio ){
		 this.anio =anio;
	}
	public int getAnio(){
		 return(anio);
	}
	public void setId(long id ){
		 this.id =id;
	}
	public long getId(){
		 return(id);
	}
	public void setFecha(Date fecha ){
		 this.fecha =fecha;
	}
	public Date getFecha(){
		 return(fecha);
	}
	public void setTodoeldia(boolean todoeldia ){
		 this.todoeldia =todoeldia;
	}
	public boolean getTodoeldia(){
		 return(todoeldia);
	}
	public void setHoradesde(Time horadesde ){
		 this.horadesde =horadesde;
	}
	public Time getHoradesde(){
		 return(horadesde);
	}
	public void setHorahasta(Time horahasta ){
		 this.horahasta =horahasta;
	}
	public Time getHorahasta(){
		 return(horahasta);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Calendario :");
		ret.append("dia='"+dia+"'");
		ret.append(", mes='"+mes+"'");
		ret.append(", anio='"+anio+"'");
		ret.append(", id='"+id+"'");
		ret.append(", fecha='"+fecha+"'");
		ret.append(", todoeldia='"+todoeldia+"'");
		ret.append(", horadesde='"+horadesde+"'");
		ret.append(", horahasta='"+horahasta+"'");
		return ret.toString();
	}
}
