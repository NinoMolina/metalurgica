/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;

import javax.swing.JList;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.gestores.GestorCompra;
import metalsoft.negocio.compras.Compra;
/**
 *
 * @author Mariana
 */
public class HiloBuscarOrden  extends Thread {
    private ABMOrden_Buscar ventana;
    private String valor;

    @Override
    public void run() {
        buscarOrden();
    }

    public ABMOrden_Buscar getVentana() {
        return ventana;
    }

    public void setVentana(ABMOrden_Buscar ventana) {
        this.ventana = ventana;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    private void buscarOrden()
    {
        //TODO
    }
    

}

