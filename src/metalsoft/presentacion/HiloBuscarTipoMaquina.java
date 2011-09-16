/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;

import javax.swing.JList;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.gestores.GestorTipoMaquina;
import metalsoft.negocio.mantmaquinarias.TipoMaquina;
/**
 *
 * @author Lorreine Prescott
 */
public class HiloBuscarTipoMaquina extends Thread {

    private ABMTipoMaquina_Buscar ventana;
    private String valor;

    @Override
    public void run() {
        buscarTipoMaquina();
    }

    public ABMTipoMaquina_Buscar getVentana() {
        return ventana;
    }

    public void setVentana(ABMTipoMaquina_Buscar ventana) {
        this.ventana = ventana;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    private void buscarTipoMaquina()
    {
        GestorTipoMaquina gestor=new GestorTipoMaquina();
        TipoMaquina[] tmaq=gestor.buscarConLIKE(valor);
        JList list=ventana.getLstTipoMaquina();
        list.removeAll();

        ventana.setTipoMaquina(tmaq);
        cargarLista(list,tmaq);

    }

     private void cargarLista(JList list, TipoMaquina[] tmaquina) {

        ItemCombo item[]=new ItemCombo[tmaquina.length];
        for(int i=0;i<tmaquina.length;i++)
        {
            ItemCombo x=new ItemCombo();
            x.setMostrar(tmaquina[i].getNombre());
            item[i]=x;
        }
        list.setListData(item);
    }

}
