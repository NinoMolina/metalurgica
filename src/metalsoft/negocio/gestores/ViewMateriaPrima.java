/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

/**
 *
 * @author Nino
 */
public class ViewMateriaPrima {

    private String nombreMateriaPrima;
    private String descripcion;
    private double alto,ancho,largo;
    private String unidadmedida;
    private String tipomaterial;
    private long idmateriaprima;

    public ViewMateriaPrima() {
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getIdmateriaprima() {
        return idmateriaprima;
    }

    public void setIdmateriaprima(long idmateriaprima) {
        this.idmateriaprima = idmateriaprima;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public String getNombreMateriaPrima() {
        return nombreMateriaPrima;
    }

    public void setNombreMateriaPrima(String nombreMateriaPrima) {
        this.nombreMateriaPrima = nombreMateriaPrima;
    }

    public String getTipomaterial() {
        return tipomaterial;
    }

    public void setTipomaterial(String tipomaterial) {
        this.tipomaterial = tipomaterial;
    }

    public String getUnidadmedida() {
        return unidadmedida;
    }

    public void setUnidadmedida(String unidadmedida) {
        this.unidadmedida = unidadmedida;
    }

    
}
