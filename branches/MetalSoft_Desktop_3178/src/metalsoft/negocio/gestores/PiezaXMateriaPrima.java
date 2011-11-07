/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

/**
 *
 * @author Nino
 */
public class PiezaXMateriaPrima extends PiezaXAlgo{

    private long idMateriaPrima;
    private double anchoPieza,altoPieza,largoPieza;
    private int cantidadPieza,cantidadProducto;
    private double anchoMatPrima,altoMatPrima,largoMatPrima;
    private ViewMateriaPrima materiaPrima;

    public PiezaXMateriaPrima() {
    }

    public int getCantidadPieza() {
        return cantidadPieza;
    }

    public void setCantidadPieza(int cantidadPieza) {
        this.cantidadPieza = cantidadPieza;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }


    public long getIdMateriaPrima() {
        return idMateriaPrima;
    }

    public void setIdMateriaPrima(long idMateriaPrima) {
        this.idMateriaPrima = idMateriaPrima;
    }

    public double getAltoMatPrima() {
        return altoMatPrima;
    }

    public void setAltoMatPrima(double altoMatPrima) {
        this.altoMatPrima = altoMatPrima;
    }

    public double getAltoPieza() {
        return altoPieza;
    }

    public void setAltoPieza(double altoPieza) {
        this.altoPieza = altoPieza;
    }

    public double getAnchoMatPrima() {
        return anchoMatPrima;
    }

    public void setAnchoMatPrima(double anchoMatPrima) {
        this.anchoMatPrima = anchoMatPrima;
    }

    public double getAnchoPieza() {
        return anchoPieza;
    }

    public void setAnchoPieza(double anchoPieza) {
        this.anchoPieza = anchoPieza;
    }

    public double getLargoMatPrima() {
        return largoMatPrima;
    }

    public void setLargoMatPrima(double largoMatPrima) {
        this.largoMatPrima = largoMatPrima;
    }

    public double getLargoPieza() {
        return largoPieza;
    }

    public void setLargoPieza(double largoPieza) {
        this.largoPieza = largoPieza;
    }

    public ViewMateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(ViewMateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    
}
