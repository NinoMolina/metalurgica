/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;

import java.sql.Connection;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.ProductoDB;
import metalsoft.negocio.access.AccessProducto;
import metalsoft.negocio.gestores.IBuscador;
import metalsoft.util.ItemCombo;

/**
 *
 * @author Nino
 */
public class HiloBuscarProducto extends Thread{

    private IBuscador ventana;
    private String valor;

    @Override
    public void run() {
        buscarProductos();
    }

    public IBuscador getVentana() {
        return ventana;
    }

    public void setVentana(IBuscador ventana) {
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
        PostgreSQLManager pg=null;
        Connection cn=null;
        pg=new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(HiloBuscarProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        ProductoDB[] productosDB=AccessProducto.findByNombreILIKE(getValor(),cn);
        JList combo=ventana.getList(HiloBuscarProducto.class.getName());
        combo.removeAll();
        cargarCombo(combo,productosDB);
        ventana.setBusqueda(productosDB);
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
