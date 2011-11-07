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
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.negocio.access.AccessProveedor;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.gestores.GestorPedidoCotizacion;
import metalsoft.negocio.gestores.IBuscador;
import metalsoft.negocio.compras.Proveedor;
/**
 *
 * @author Vicky
 */
public class HiloBuscarProveedor extends Thread {
    private Timer timer;
    private IBuscador prov;
    private String valor;

    @Override
    public void run() {
        buscarProveedores();
    }

    public IBuscador getVentana() {
        return prov;
    }

    public void setVentana(IBuscador ventana) {
        this.prov = ventana;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    private void buscarProveedores()
    {
        Connection cn=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(HiloBuscarProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        metalsoft.datos.dbobject.ProveedorDB[] proveedores=AccessProveedor.findByRazonsocialILIKE(valor,cn);
        JList list=prov.getList(HiloBuscarProveedor.class.getName());
        JComboBox combo=prov.getCombo(HiloBuscarProveedor.class.getName());
        prov.setBusqueda(proveedores);
        if(list!=null)
        {
            list.removeAll();
            cargarLista(list,proveedores);
        }
        if(combo!=null)
        {
            combo.removeAllItems();
            cargarCombo(combo, proveedores);
        }

    }

    public void iniciarTimer() {
        timer=new Timer(true);
    }

    private void cargarLista(JList list, metalsoft.datos.dbobject.ProveedorDB[] proveedores) {
        ItemCombo item=null,items[]=new ItemCombo[proveedores.length];
        for(int i=0;i<proveedores.length;i++)
        {
            item=new ItemCombo(String.valueOf(proveedores[i].getIdproveedor()), proveedores[i].getRazonsocial());
            items[i]=item;
        }
        list.setListData(items);
    }
    private void cargarCombo(JComboBox combo, metalsoft.datos.dbobject.ProveedorDB[] proveedores) {
        ItemCombo item=null;
        for(int i=0;i<proveedores.length;i++)
        {
            item=new ItemCombo(String.valueOf(proveedores[i].getIdproveedor()), proveedores[i].getRazonsocial());
            combo.addItem(item);
        }
    }

    private void cargarLista(JList combo, ItemCombo[] items) {
        combo.setListData(items);
    }
}
