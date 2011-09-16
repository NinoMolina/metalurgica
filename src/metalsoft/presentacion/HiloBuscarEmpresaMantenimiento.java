/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.presentacion;

import java.sql.Connection;
import java.util.List;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JList;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.jpa.entity.Proveedormantenimientomaquina;
import metalsoft.negocio.gestores.GestorEmpresaMantenimiento;
import metalsoft.negocio.gestores.IBuscador;
import metalsoft.util.ItemCombo;

/**
 *
 * @author Vicky
 */
public class HiloBuscarEmpresaMantenimiento extends Thread {

    private ABMEmpresaMantenimiento_Buscar ventana;
    private String valor;

    @Override
    public void run() {
        buscarEmpresaMantenimiento();
    }

    public ABMEmpresaMantenimiento_Buscar getVentana() {
        return ventana;
    }

    public void setVentana(ABMEmpresaMantenimiento_Buscar ventana) {
        this.ventana = ventana;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    private void buscarEmpresaMantenimiento() {
        GestorEmpresaMantenimiento gestor = new GestorEmpresaMantenimiento();
        List<Proveedormantenimientomaquina> m = gestor.buscarProveedormantenimientomaquina(valor);
        JList list = ventana.getLstLista();
        list.removeAll();
        ventana.setProveedormantenimientomaquina(m);
        cargarLista(list, m);

        //ventana.getBsyBuscar().setBusy(false);
        //ventana.getBsyBuscar().setVisible(false);
    }

    private void cargarLista(JList list, List<Proveedormantenimientomaquina> matriz) {

        ItemCombo item[] = new ItemCombo[matriz.size()];
        for (int i = 0; i < matriz.size(); i++) {
            ItemCombo x = new ItemCombo();
            x.setMostrar(matriz.get(i).getRazonsocial());
            item[i] = x;
        }
        list.setListData(item);
    }
}
