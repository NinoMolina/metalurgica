/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;

import javax.swing.JList;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.gestores.GestorServicioMaquina;
import metalsoft.negocio.mantenimiento.ServicioMaquina;

/**
 *
 * @author Lorreine Prescott
 */
public class HiloBuscarServicioMaquina extends Thread {

    private ABMServicioMaquina_Buscar ventana;
    private String valor;

    @Override
    public void run() {
        buscarServicioMaquina();
    }

     public ABMServicioMaquina_Buscar getVentana() {
        return ventana;
    }

    public void setVentana(ABMServicioMaquina_Buscar ventana) {
        this.ventana = ventana;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    private void buscarServicioMaquina()
    {
        GestorServicioMaquina gestor=new GestorServicioMaquina();
        ServicioMaquina[] sm=gestor.buscarConLIKE(valor);
        JList list=ventana.getLstServicioMaquina();
        list.removeAll();
        ventana.setServicioMaquina(sm);
        cargarLista(list,sm);
    }

     private void cargarLista(JList list, ServicioMaquina[] servicio) {

        ItemCombo item[]=new ItemCombo[servicio.length];
        for(int i=0;i<servicio.length;i++)
        {
            ItemCombo x=new ItemCombo();
            x.setMostrar(servicio[i].getNombre());
            item[i]=x;
        }
        list.setListData(item);
    }

}
