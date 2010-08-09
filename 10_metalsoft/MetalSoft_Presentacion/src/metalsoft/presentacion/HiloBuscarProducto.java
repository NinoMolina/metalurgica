/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;

import java.util.Timer;
import javax.swing.JList;
import metalsoft.datos.dbobject.ProductoDB;
import metalsoft.util.ItemCombo;

/**
 *
 * @author Nino
 */
public class HiloBuscarProducto extends Thread{

    private ABMProducto_Buscar ventana;
    private String valor;

    @Override
    public void run() {
        buscarProductos();
    }

    public ABMProducto_Buscar getVentana() {
        return ventana;
    }

    public void setVentana(ABMProducto_Buscar ventana) {
        this.ventana = ventana;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    private void buscarProductos()
    {
        ProductoDB[] productosDB=ventana.getGestor().buscarProductos(getValor());
        JList combo=ventana.getLstLista();
        combo.removeAll();
        cargarCombo(combo,productosDB);
    }

    private void cargarCombo(JList combo, metalsoft.datos.dbobject.ProductoDB[] dbs) {
        ItemCombo item=null,items[]=new ItemCombo[dbs.length];
        for(int i=0;i<dbs.length;i++)
        {
            item=new ItemCombo(String.valueOf(dbs[i].getIdproducto()), dbs[i].getNombre());
            items[i]=item;
        }
        combo.setListData(items);
    }

    private void cargarCombo(JList combo, ItemCombo[] items) {
        combo.setListData(items);
    }
}
