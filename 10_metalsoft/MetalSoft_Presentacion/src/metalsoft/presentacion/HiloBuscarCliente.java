/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;

import com.lowagie.text.markup.Parser;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import metalsoft.util.ItemCombo;
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
        metalsoft.datos.dbobject.ClienteDB[] clientes=ventanaBuscar.getGestor().buscarClientes(getValor());
        JList combo=ventanaBuscar.getLstLista();
        combo.removeAll();
        cargarCombo(combo,clientes);
    }

    public void iniciarTimer() {
        timer=new Timer(true);
    }

    private void cargarCombo(JList combo, metalsoft.datos.dbobject.ClienteDB[] clientes) {
        ItemCombo item=null,items[]=new ItemCombo[clientes.length];
        for(int i=0;i<clientes.length;i++)
        {
            item=new ItemCombo(String.valueOf(clientes[i].getIdcliente()), clientes[i].getRazonsocial());
            items[i]=item;
        }
        combo.setListData(items);
    }

    private void cargarCombo(JList combo, ItemCombo[] items) {
        combo.setListData(items);
    }

}
