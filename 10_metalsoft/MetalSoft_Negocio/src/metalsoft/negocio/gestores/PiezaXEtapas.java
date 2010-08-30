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
public class PiezaXEtapas implements Comparable{

    private long idPedido,idProducto,idPieza;
    private double ancho,alto,largo;
    private double precioProducto;
    private LinkedList<ViewEtapaDeProduccion> etapas;

    public PiezaXEtapas() {
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

    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }

    public long getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(long idPieza) {
        this.idPieza = idPieza;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public int compareTo(Object o) {
        PiezaXEtapas x=(PiezaXEtapas)o;

        long idPed=x.getIdPedido();
        long idPi=x.getIdPieza();
        long idPro=x.getIdProducto();

        if(idPed==this.getIdPedido() && idPi==this.getIdPieza() && idPro==this.getIdProducto())
        {
            return 0;
        }
        if(idPed>=this.getIdPedido())return -1;
        else return 1;
    }
    
}
