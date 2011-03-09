/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.util.Date;

/**
 *
 * @author Nino
 */
public class ViewProcesoCalidad {

    private long nroproceso;
    private String nombreproceso;
    private Date duracionestimada;
    private String herramienta;
    private String nombreaccioncalidad;
    private int cantProcesos;
    private long idprocesocalidad;

    public ViewProcesoCalidad() {
    }

    public long getIdprocesocalidad() {
        return idprocesocalidad;
    }

    public void setIdprocesocalidad(long idprocesocalidad) {
        this.idprocesocalidad = idprocesocalidad;
    }

    
    public int getCantProcesos() {
        return cantProcesos;
    }

    public void setCantProcesos(int cantProcesos) {
        this.cantProcesos = cantProcesos;
    }

    public Date getDuracionestimada() {
        return duracionestimada;
    }

    public void setDuracionestimada(Date duracionestimada) {
        this.duracionestimada = duracionestimada;
    }

    public String getHerramienta() {
        return herramienta;
    }

    public void setHerramienta(String herramienta) {
        this.herramienta = herramienta;
    }

    public String getNombreaccioncalidad() {
        return nombreaccioncalidad;
    }

    public void setNombreaccioncalidad(String nombreaccioncalidad) {
        this.nombreaccioncalidad = nombreaccioncalidad;
    }

    public String getNombreproceso() {
        return nombreproceso;
    }

    public void setNombreproceso(String nombreproceso) {
        this.nombreproceso = nombreproceso;
    }

    public long getNroproceso() {
        return nroproceso;
    }

    public void setNroproceso(long nroproceso) {
        this.nroproceso = nroproceso;
    }

    
}
