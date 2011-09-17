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
import metalsoft.datos.dbobject.MateriaprimaDB;
import metalsoft.negocio.access.AccessMateriaPrima;
import metalsoft.util.ItemCombo;
/**
 *
 * @author Vicky
 */
public class HiloBuscarMateriaPrima extends Thread {
    private ABMMateriaPrima_Buscar ventana;
    private String valor;

    @Override
    public void run() {
        buscarMateriaPrima();
    }

    public ABMMateriaPrima_Buscar getVentana() {
        return ventana;
    }

    public void setVentana(ABMMateriaPrima_Buscar ventana) {
        this.ventana = ventana;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }


    private void buscarMateriaPrima()
    {
        Connection cn=null;
        PostgreSQLManager pg=null;
        pg=new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(HiloBuscarMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }
        MateriaprimaDB[] mp=AccessMateriaPrima.findByNombreILIKE(valor, cn);
        JList combo=ventana.getLstLista();
        combo.removeAll();
        cargarCombo(combo,mp);
    }

    private void cargarCombo(JList combo, MateriaprimaDB[] dbs) {
        ItemCombo item=null,items[]=new ItemCombo[dbs.length];
        for(int i=0;i<dbs.length;i++)
        {
            item=new ItemCombo(String.valueOf(dbs[i].getIdmateriaprima()), dbs[i].getNombre());
            items[i]=item;
        }
        combo.setListData(items);
    }

    private void cargarCombo(JList combo, ItemCombo[] items) {
        combo.setListData(items);
    }
}
