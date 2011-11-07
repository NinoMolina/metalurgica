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
public class ViewProcesoCalidadXPiezaPresupuesto {

    private long nroproducto;
    private String nombreproducto;
    private int cantproducto;
    private String nombrepieza;
    private int cantpieza;
    private long nroprocesocalidad;
    private String nombreprocesocalidad;
    private int cantprocesocalidad;
    private Date duracionprocalidadxpieza;
    private String duraciontotal;
    private long idpresupuesto;
    private long iddetallepresupuesto;
    private long iddetalleproductopresupuesto;
    private long iddetallepiezacalidadpresupuesto;
    private long idproducto;
    private long idpieza;
    private long idprocesocalidad;

    public ViewProcesoCalidadXPiezaPresupuesto() {
    }

    public int getCantpieza() {
        return cantpieza;
    }

    public void setCantpieza(int cantpieza) {
        this.cantpieza = cantpieza;
    }

    public int getCantprocesocalidad() {
        return cantprocesocalidad;
    }

    public void setCantprocesocalidad(int cantprocesocalidad) {
        this.cantprocesocalidad = cantprocesocalidad;
    }

    public int getCantproducto() {
        return cantproducto;
    }

    public void setCantproducto(int cantproducto) {
        this.cantproducto = cantproducto;
    }

    public Date getDuracionprocalidadxpieza() {
        return duracionprocalidadxpieza;
    }

    public void setDuracionprocalidadxpieza(Date duracionprocalidadxpieza) {
        this.duracionprocalidadxpieza = duracionprocalidadxpieza;
    }

    public String getDuraciontotal() {
        return duraciontotal;
    }

    public void setDuraciontotal(String duraciontotal) {
        this.duraciontotal = duraciontotal;
    }

    public long getIddetallepiezacalidadpresupuesto() {
        return iddetallepiezacalidadpresupuesto;
    }

    public void setIddetallepiezacalidadpresupuesto(long iddetallepiezacalidadpresupuesto) {
        this.iddetallepiezacalidadpresupuesto = iddetallepiezacalidadpresupuesto;
    }

    public long getIddetallepresupuesto() {
        return iddetallepresupuesto;
    }

    public void setIddetallepresupuesto(long iddetallepresupuesto) {
        this.iddetallepresupuesto = iddetallepresupuesto;
    }

    public long getIddetalleproductopresupuesto() {
        return iddetalleproductopresupuesto;
    }

    public void setIddetalleproductopresupuesto(long iddetalleproductopresupuesto) {
        this.iddetalleproductopresupuesto = iddetalleproductopresupuesto;
    }

    public long getIdpieza() {
        return idpieza;
    }

    public void setIdpieza(long idpieza) {
        this.idpieza = idpieza;
    }

    public long getIdpresupuesto() {
        return idpresupuesto;
    }

    public void setIdpresupuesto(long idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

    public long getIdprocesocalidad() {
        return idprocesocalidad;
    }

    public void setIdprocesocalidad(long idprocesocalidad) {
        this.idprocesocalidad = idprocesocalidad;
    }

    public long getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(long idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombrepieza() {
        return nombrepieza;
    }

    public void setNombrepieza(String nombrepieza) {
        this.nombrepieza = nombrepieza;
    }

    public String getNombreprocesocalidad() {
        return nombreprocesocalidad;
    }

    public void setNombreprocesocalidad(String nombreprocesocalidad) {
        this.nombreprocesocalidad = nombreprocesocalidad;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public long getNroprocesocalidad() {
        return nroprocesocalidad;
    }

    public void setNroprocesocalidad(long nroprocesocalidad) {
        this.nroprocesocalidad = nroprocesocalidad;
    }

    public long getNroproducto() {
        return nroproducto;
    }

    public void setNroproducto(long nroproducto) {
        this.nroproducto = nroproducto;
    }

    
}
