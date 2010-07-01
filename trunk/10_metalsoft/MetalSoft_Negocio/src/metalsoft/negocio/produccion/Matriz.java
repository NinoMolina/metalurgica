/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.produccion;

import java.math.*;
import java.sql.Date;
/**
 *
 * @author Mariana
 */
public class Matriz {
        private long codmatriz;
	private String nombre;
	private String descripcion;
	private String observaciones;
	private Date fechacreacion;
	private long materiaprima;
	private long tipomaterial;

    public Matriz(){}

        public long getCodmatriz(){
		 return(codmatriz);
	}

        public void setCodmatriz(long codmatriz) {
            this.codmatriz = codmatriz;
        }
	public void setNombre(String nombre ){
		 this.nombre =nombre;
	}
	public String getNombre(){
		 return(nombre);
	}
	public void setDescripcion(String descripcion ){
		 this.descripcion =descripcion;
	}
	public String getDescripcion(){
		 return(descripcion);
	}
	public void setObservaciones(String observaciones ){
		 this.observaciones =observaciones;
	}
	public String getObservaciones(){
		 return(observaciones);
	}
	public void setFechacreacion(Date fechacreacion ){
		 this.fechacreacion =fechacreacion;
	}
	public Date getFechacreacion(){
		 return(fechacreacion);
	}
	public void setMateriaprima(long materiaprima ){
		 this.materiaprima =materiaprima;
	}
	public long getMateriaprima(){
		 return(materiaprima);
	}
	public void setTipomaterial(long tipomaterial ){
		 this.tipomaterial =tipomaterial;
	}
	public long getTipomaterial(){
		 return(tipomaterial);
	}

}
