/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;

import javax.swing.JList;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.gestores.GestorRotura;
import metalsoft.negocio.mantenimiento.Rotura;


/**
 *
 * @author Lorreine Prescott
 */
public class HiloBuscarRotura extends Thread {

    private ABMRotura_Buscar ventana;
    private String valor;

    @Override
    public void run() {
        buscarRotura();
    }

    public ABMRotura_Buscar getVentana() {
        return ventana;
    }

    public void setVentana(ABMRotura_Buscar ventana) {
        this.ventana = ventana;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    private void buscarRotura()
    {
        GestorRotura gestor=new GestorRotura();
        Rotura[] rot=gestor.buscarConLIKE(valor);
        JList list=ventana.getLstRotura();
        list.removeAll();

        ventana.setRot(rot);
        cargarLista(list,rot);

    }

     private void cargarLista(JList list, Rotura[] rotura) {

        ItemCombo item[]=new ItemCombo[rotura.length];
        for(int i=0;i<rotura.length;i++)
        {
            ItemCombo x=new ItemCombo();
            x.setMostrar(rotura[i].getNombre());
            item[i]=x;
        }
        list.setListData(item);
    }
}
