/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Nino
 */
public class ViewEtapasXPiezaPresupuesto {

    private long nroproducto;
    private String nombreproducto;
    private int cantproducto;
    private String nombrepieza;
    private int cantpieza;
    private long nroetapaproduccion;
    private String nombreetapaproduccion;
    private Date duracionetapaxpieza;
    private String duraciontotal;
    private long idpresupuesto;
    private long iddetallepresupuesto;
    private long iddetalleproductopresupuesto;
    private long iddetallepiezapresupuesto;
    private long idproducto;
    private long idpieza;
    private long idetapaproduccion;

    public ViewEtapasXPiezaPresupuesto() {
    }

    public int getCantpieza() {
        return cantpieza;
    }

    public void setCantpieza(int cantpieza) {
        this.cantpieza = cantpieza;
    }

    public int getCantproducto() {
        return cantproducto;
    }

    public void setCantproducto(int cantproducto) {
        this.cantproducto = cantproducto;
    }

    public Date getDuracionetapaxpieza() {
        return duracionetapaxpieza;
    }

    public void setDuracionetapaxpieza(Date duracionetapaxpieza) {
        this.duracionetapaxpieza = duracionetapaxpieza;
    }

    public String getDuraciontotal() {
        return duraciontotal;
    }

    public void setDuraciontotal(String duraciontotal) {
        this.duraciontotal = duraciontotal;
    }

    public long getIddetallepiezapresupuesto() {
        return iddetallepiezapresupuesto;
    }

    public void setIddetallepiezapresupuesto(long iddetallepiezapresupuesto) {
        this.iddetallepiezapresupuesto = iddetallepiezapresupuesto;
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

    public long getIdetapaproduccion() {
        return idetapaproduccion;
    }

    public void setIdetapaproduccion(long idetapaproduccion) {
        this.idetapaproduccion = idetapaproduccion;
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

    public long getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(long idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombreetapaproduccion() {
        return nombreetapaproduccion;
    }

    public void setNombreetapaproduccion(String nombreetapaproduccion) {
        this.nombreetapaproduccion = nombreetapaproduccion;
    }

    public String getNombrepieza() {
        return nombrepieza;
    }

    public void setNombrepieza(String nombrepieza) {
        this.nombrepieza = nombrepieza;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public long getNroetapaproduccion() {
        return nroetapaproduccion;
    }

    public void setNroetapaproduccion(long nroetapaproduccion) {
        this.nroetapaproduccion = nroetapaproduccion;
    }

    public long getNroproducto() {
        return nroproducto;
    }

    public void setNroproducto(long nroproducto) {
        this.nroproducto = nroproducto;
    }

    
}
