/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;

import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import metalsoft.negocio.ItemCombo;
import metalsoft.negocio.gestores.GestorPedidoCotizacion;
import metalsoft.negocio.ventas.Cliente;

/**
 *
 * @author Nino
 */
public class HiloBuscarCliente extends Thread {
    private Timer timer;
    private ABMPedidoCotizacion ventana;
    private String valor;

    @Override
    public void run() {
        buscarClientes();
    }

    public ABMPedidoCotizacion getVentana() {
        return ventana;
    }

    public void setVentana(ABMPedidoCotizacion ventana) {
        this.ventana = ventana;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    private void buscarClientes()
    {
        GestorPedidoCotizacion gestor=new GestorPedidoCotizacion();
        Cliente[] clientes=gestor.buscarClientes(getValor());
        JComboBox combo=ventana.getCmbResultadoBusqueda();
        combo.removeAllItems();
        ventana.setClientes(clientes);
        cargarCombo(combo,clientes);
        ventana.getLblCondIva().setText(clientes[0].getIva().getNombre());
        ventana.getLblCuit().setText(clientes[0].getCUIT());
        ventana.getBsyBuscar().setBusy(false);
        ventana.getBsyBuscar().setVisible(false);
    }

    public void iniciarTimer() {
        timer=new Timer(true);
    }

    private void cargarCombo(JComboBox combo, Cliente[] clientes) {
        ItemCombo item=null;
        for(int i=0;i<clientes.length;i++)
        {
            item=new ItemCombo();
            item.setMostrar(clientes[i].getRazonSocial());
            item.setId(clientes[i].getCUIT());
            combo.addItem(item);
            combo.setSelectedIndex(0);
        }
        
    }



}
