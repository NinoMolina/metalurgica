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
import metalsoft.negocio.gestores.GestorEtapaDeProduccion;
import metalsoft.negocio.gestores.GestorPieza;
import metalsoft.negocio.gestores.IBuscador;
import metalsoft.util.ItemCombo;
/**
 *
 * @author Vicky
 */
public class HiloBuscarEtapaDeProduccion extends Thread {
    private String valor;
    private IBuscador ventana;

    @Override
    public void run() {
        buscarEtapaDeProduccion();
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


    private void buscarEtapaDeProduccion()
    {
//        Connection cn=null;
//        PostgreSQLManager pg=null;
//        pg=new PostgreSQLManager();
//        EtapadeproduccionDB[] etapaDeProduccionDB=null;
//        try {
//            cn = pg.concectGetCn();
//            etapaDeProduccionDB=AccessEtapaDeProduccion.findByNombreILIKE(valor, cn);
//            JList combo=ventana.getList(HiloBuscarEtapaDeProduccion.class.getName());
//            combo.removeAll();
//            cargarCombo(combo,etapaDeProduccionDB);
//        } catch (Exception ex) {
//            Logger.getLogger(HiloBuscarEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        finally
//        {
//            try {
//                pg.disconnect();
//            } catch (SQLException ex) {
//                Logger.getLogger(HiloBuscarEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

        GestorEtapaDeProduccion gestor=new GestorEtapaDeProduccion();
        metalsoft.datos.dbobject.EtapadeproduccionDB[] ep=gestor.buscarConILIKE(valor);
        JList list=ventana.getList(HiloBuscarEtapaDeProduccion.class.getName());
        list.removeAll();
        ventana.setBusqueda(ep);
        cargarCombo(list,ep);

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
