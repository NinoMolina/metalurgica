/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.util.Date;
import metalsoft.negocio.ventas.EtapaDeProduccion;

/**
 *
 * @author Mariana
 */
public class ViewPiezaXEtapa {
    private long idPieza;
    private long idEtapaProduccion;
    private Date dutacion;
    private String descripcion;

    public ViewPiezaXEtapa() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getDutacion() {
        return dutacion;
    }

    public void setDutacion(Date dutacion) {
        this.dutacion = dutacion;
    }

    public long getIdEtapaProduccion() {
        return idEtapaProduccion;
    }

    public void setIdEtapaProduccion(long idEtapaProduccion) {
        this.idEtapaProduccion = idEtapaProduccion;
    }

    public long getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(long idPieza) {
        this.idPieza = idPieza;
    }

}
