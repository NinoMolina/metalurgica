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
public class ViewEtapaDeProduccion {

    private int numero;
    private String nombre;
    private Date horasMaquina,horasHombre,duracionEstimada;
    private long idetapa;

    public ViewEtapaDeProduccion() {
    }

    public Date getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(Date duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }

    public Date getHorasHombre() {
        return horasHombre;
    }

    public void setHorasHombre(Date horasHombre) {
        this.horasHombre = horasHombre;
    }

    public Date getHorasMaquina() {
        return horasMaquina;
    }

    public void setHorasMaquina(Date horasMaquina) {
        this.horasMaquina = horasMaquina;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    
    public long getIdetapa() {
        return idetapa;
    }

    public void setIdetapa(long idetapa) {
        this.idetapa = idetapa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
