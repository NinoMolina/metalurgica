/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.util.LinkedList;

/**
 *
 * @author Nino
 */
public class PiezaXEtapas extends PiezaXAlgo{

    private double ancho,alto,largo;
    private double precioProducto;
    private int cantProductos,cantPiezas;
    private LinkedList<ViewEtapaDeProduccion> etapas;

    public PiezaXEtapas() {
    }

    public int getCantPiezas() {
        return cantPiezas;
    }

    public void setCantPiezas(int cantPiezas) {
        this.cantPiezas = cantPiezas;
    }


    public int getCantProductos() {
        return cantProductos;
    }

    public void setCantProductos(int cantProductos) {
        this.cantProductos = cantProductos;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
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

    public LinkedList<ViewEtapaDeProduccion> getEtapas() {
        return etapas;
    }

    public void setEtapas(LinkedList<ViewEtapaDeProduccion> etapas) {
        this.etapas = etapas;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    
}
