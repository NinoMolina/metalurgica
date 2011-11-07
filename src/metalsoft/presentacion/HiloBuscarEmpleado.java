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
import metalsoft.datos.dbobject.EmpleadoDB;
import metalsoft.datos.dbobject.MateriaprimaDB;
import metalsoft.negocio.access.AccessEmpleado;
import metalsoft.negocio.access.AccessMateriaPrima;
import metalsoft.util.ItemCombo;
/**
 *
 * @author Vicky
 */
public class HiloBuscarEmpleado extends Thread {
    private ABMEmpleado_Buscar ventana;
    private String valor;

    @Override
    public void run() {
        buscarEmpleado();
    }

    public ABMEmpleado_Buscar getVentana() {
        return ventana;
    }

    public void setVentana(ABMEmpleado_Buscar ventana) {
        this.ventana = ventana;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }


    private void buscarEmpleado()
    {
        Connection cn=null;
        PostgreSQLManager pg=null;
        pg=new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(HiloBuscarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        EmpleadoDB[] mp=AccessEmpleado.findByNombreILIKE(valor, cn);
        JList combo=ventana.getLstLista();
        combo.removeAll();
        cargarCombo(combo,mp);
    }

    private void cargarCombo(JList combo, EmpleadoDB[] dbs) {
        ItemCombo item=null,items[]=new ItemCombo[dbs.length];
        for(int i=0;i<dbs.length;i++)
        {
            item=new ItemCombo(String.valueOf(dbs[i].getIdempleado()), dbs[i].getNombre());
            items[i]=item;
        }
        combo.setListData(items);
    }

    private void cargarCombo(JList combo, ItemCombo[] items) {
        combo.setListData(items);
    }
}
