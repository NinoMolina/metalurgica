/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 28 01:02:39 ART 2010
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
* This classes is the Data Transfer Object(Value Object) defination for the entity materiaprima
*
*/

public class MateriaprimaDB implements Serializable
{
	private long idmateriaprima;
	private long codproducto;
	private String nombre;
	private Date fechaalta;
	private Date fechabaja;
	private long codbarra;
	private Double largo;
    private Double ancho;
    private Double alto;
	private long stock;
	private long unidaddemedida;
	private String descripcion;
	private long tipomaterial;

        private long nromateriaprima;

	public MateriaprimaDB(){}
	public MateriaprimaDB(long idmateriaprima ,long codproducto ,String nombre ,Date fechaalta ,Date fechabaja ,long codbarra ,Double alto,Double ancho,Double largo ,long stock ,long unidaddemedida ,String descripcion ,long tipomaterial){
		this.idmateriaprima = idmateriaprima;
		this.codproducto = codproducto;
		this.nombre = nombre;
		this.fechaalta = fechaalta;
		this.fechabaja = fechabaja;
		this.codbarra = codbarra;
		this.alto = alto;
        this.ancho = ancho;
        this.largo = largo;
		this.stock = stock;
		this.unidaddemedida = unidaddemedida;
		this.descripcion = descripcion;
		this.tipomaterial = tipomaterial;
	}

    public long getNromateriaprima() {
        return nromateriaprima;
    }

    public void setNromateriaprima(long nromateriaprima) {
        this.nromateriaprima = nromateriaprima;
    }



    public Double getAlto() {
        return alto;
    }

    public void setAlto(Double alto) {
        this.alto = alto;
    }

    public Double getAncho() {
        return ancho;
    }

    public void setAncho(Double ancho) {
        this.ancho = ancho;
    }

    public Double getLargo() {
        return largo;
    }

    public void setLargo(Double largo) {
        this.largo = largo;
    }
	public void setIdmateriaprima(long idmateriaprima ){
		 this.idmateriaprima =idmateriaprima;
	}
	public long getIdmateriaprima(){
		 return(idmateriaprima);
	}
	public void setCodproducto(long codproducto ){
		 this.codproducto =codproducto;
	}
	public long getCodproducto(){
		 return(codproducto);
	}
	public void setNombre(String nombre ){
		 this.nombre =nombre;
	}
	public String getNombre(){
		 return(nombre);
	}
	public void setFechaalta(Date fechaalta ){
		 this.fechaalta =fechaalta;
	}
	public Date getFechaalta(){
		 return(fechaalta);
	}
	public void setFechabaja(Date fechabaja ){
		 this.fechabaja =fechabaja;
	}
	public Date getFechabaja(){
		 return(fechabaja);
	}
	public void setCodbarra(long codbarra ){
		 this.codbarra =codbarra;
	}
	public long getCodbarra(){
		 return(codbarra);
	}
	
	public void setStock(long stock ){
		 this.stock =stock;
	}
	public long getStock(){
		 return(stock);
	}
	public void setUnidaddemedida(long unidaddemedida ){
		 this.unidaddemedida =unidaddemedida;
	}
	public long getUnidaddemedida(){
		 return(unidaddemedida);
	}
	public void setDescripcion(String descripcion ){
		 this.descripcion =descripcion;
	}
	public String getDescripcion(){
		 return(descripcion);
	}
	public void setTipomaterial(long tipomaterial ){
		 this.tipomaterial =tipomaterial;
	}
	public long getTipomaterial(){
		 return(tipomaterial);
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("metalsoft.datos.dbobject.Materiaprima :");
		ret.append("idmateriaprima='"+idmateriaprima+"'");
		ret.append(", codproducto='"+codproducto+"'");
		ret.append(", nombre='"+nombre+"'");
		ret.append(", fechaalta='"+fechaalta+"'");
		ret.append(", fechabaja='"+fechabaja+"'");
		ret.append(", codbarra='"+codbarra+"'");
		ret.append(", alto='"+alto+"'");
        ret.append(", ancho='"+ancho+"'");
        ret.append(", largo='"+largo+"'");
		ret.append(", stock='"+stock+"'");
		ret.append(", unidaddemedida='"+unidaddemedida+"'");
		ret.append(", descripcion='"+descripcion+"'");
		ret.append(", tipomaterial='"+tipomaterial+"'");
		return ret.toString();
	}
}