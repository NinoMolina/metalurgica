/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.EtapadeproduccionDB;

import metalsoft.negocio.access.AccessEtapaDeProduccion;
import metalsoft.util.ItemCombo;
/**
 *
 * @author Vicky
 */
public class HiloBuscarEtapaDeProduccion extends Thread {
    private ABMEtapaDeProduccion_Buscar ventana;
    private String valor;

    @Override
    public void run() {
        buscarEtapaDeProduccion();
    }

    public ABMEtapaDeProduccion_Buscar getVentana() {
        return ventana;
    }

    public void setVentana(ABMEtapaDeProduccion_Buscar ventana) {
        this.ventana = ventana;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }


    private void buscarEtapaDeProduccion()
    {
        Connection cn=null;
        PostgreSQLManager pg=null;
        pg=new PostgreSQLManager();
        EtapadeproduccionDB[] etapaDeProduccionDB=null;
        try {
            cn = pg.concectGetCn();
            etapaDeProduccionDB=AccessEtapaDeProduccion.findByNombreILIKE(valor, cn);
            JList combo=ventana.getLstLista();
            combo.removeAll();
            cargarCombo(combo,etapaDeProduccionDB);
        } catch (Exception ex) {
            Logger.getLogger(HiloBuscarEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(HiloBuscarEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void cargarCombo(JList combo, EtapadeproduccionDB[] dbs) {
        ItemCombo item=null,items[]=new ItemCombo[dbs.length];
        for(int i=0;i<dbs.length;i++)
        {
            item=new ItemCombo(String.valueOf(dbs[i].getIdetapaproduccion()), dbs[i].getNombre());
            items[i]=item;
        }
        combo.setListData(items);
    }

    private void cargarCombo(JList combo, ItemCombo[] items) {
        combo.setListData(items);
    }
}