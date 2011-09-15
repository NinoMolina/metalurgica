/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;


import java.sql.Connection;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JList;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.negocio.access.AccessCliente;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.gestores.IBuscador;

/**
 *
 * @author Nino
 */
public class HiloBuscarCliente extends Thread {
    private Timer timer;
    private IBuscador client;
    private String valor;

    @Override
    public void run() {
        buscarClientes();
    }

    public IBuscador getVentana() {
        return client;
    }

    public void setVentana(IBuscador ventana) {
        this.client = ventana;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    private void buscarClientes()
    {
        Connection cn=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(HiloBuscarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        metalsoft.datos.dbobject.ClienteDB[] clientes=AccessCliente.findByRazonsocialILIKE(valor,cn);
        JList list=client.getList(HiloBuscarCliente.class.getName());
        JComboBox combo=client.getCombo(HiloBuscarCliente.class.getName());
        client.setBusqueda(clientes);
        if(list!=null)
        {
            list.removeAll();
            cargarLista(list,clientes);
        }
        if(combo!=null)
        {
            combo.removeAllItems();
            cargarCombo(combo, clientes);
        }

    }

    public void iniciarTimer() {
        timer=new Timer(true);
    }

    private void cargarLista(JList list, metalsoft.datos.dbobject.ClienteDB[] clientes) {
        ItemCombo item=null,items[]=new ItemCombo[clientes.length];
        for(int i=0;i<clientes.length;i++)
        {
            item=new ItemCombo(String.valueOf(clientes[i].getIdcliente()), clientes[i].getRazonsocial());
            items[i]=item;
        }
        list.setListData(items);
    }
    private void cargarCombo(JComboBox combo, metalsoft.datos.dbobject.ClienteDB[] clientes) {
        ItemCombo item=null;
        for(int i=0;i<clientes.length;i++)
        {
            item=new ItemCombo(String.valueOf(clientes[i].getIdcliente()), clientes[i].getRazonsocial());
            combo.addItem(item);
        }
    }

    private void cargarLista(JList combo, ItemCombo[] items) {
        combo.setListData(items);
    }

}
