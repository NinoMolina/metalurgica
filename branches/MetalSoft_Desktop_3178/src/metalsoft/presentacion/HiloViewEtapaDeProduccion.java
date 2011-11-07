/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.negocio.access.AccessViews;
import metalsoft.negocio.gestores.IBuscadorView;
import metalsoft.negocio.gestores.ViewEtapaDeProduccion;

/**
 *
 * @author Nino
 */
public class HiloViewEtapaDeProduccion extends Thread{

    private IBuscadorView client;
    private String valor;
    public HiloViewEtapaDeProduccion() {
    }

    @Override
    public void run() {
        buscarEtapasDeProduccion();
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public IBuscadorView getClient() {
        return client;
    }

    public void setClient(IBuscadorView client) {
        this.client = client;
    }

    private void buscarEtapasDeProduccion() {
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        JTable table=client.getTable(HiloViewEtapaDeProduccion.class.getName());
        LinkedList<ViewEtapaDeProduccion> list=client.getFilas(HiloViewEtapaDeProduccion.class.getName());
        try {
            cn = pg.concectGetCn();
            list=AccessViews.etapasDeProduccionILIKE(valor, cn);
            table.updateUI();
        } catch (Exception ex) {
            Logger.getLogger(HiloViewEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(HiloViewEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }



}
