/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;

import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JList;
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
    private ABMCliente_Buscar ventanaBuscar;
    private String valor;

    @Override
    public void run() {
        buscarClientes();
    }

    public ABMPedidoCotizacion getVentana() {
        return ventana;
    }

    public ABMCliente_Buscar getVentanaBuscar() {
        return ventanaBuscar;
    }

    public void setVentanaBuscar(ABMCliente_Buscar ventanaBuscar) {
        this.ventanaBuscar = ventanaBuscar;
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
        ItemCombo[] items=ventanaBuscar.getGestor().buscarClientes(getValor());
        JList combo=ventanaBuscar.getLstLista();
        combo.removeAll();
        cargarCombo(combo,items);
//        ventana.getLblCondIva().setText(clientes[0].getIva().getNombre());
//        ventana.getLblCuit().setText(clientes[0].getCUIT());
//        ventana.getBsyBuscar().setBusy(false);
//        ventana.getBsyBuscar().setVisible(false);
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

    private void cargarCombo(JList combo, ItemCombo[] items) {
        combo.setListData(items);
    }

}
