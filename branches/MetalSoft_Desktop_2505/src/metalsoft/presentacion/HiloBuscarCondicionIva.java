/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;


import javax.swing.JList;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.gestores.GestorCondicionIva;
import metalsoft.negocio.ventas.CondicionIva;
/**
 *
 * @author Lorreine Prescott
 */
public class HiloBuscarCondicionIva extends Thread {

    private ABMCondicionIva_Buscar ventana;
    private String valor;

    @Override
    public void run() {
        buscarCondicionIva();
    }

     public ABMCondicionIva_Buscar getVentana() {
        return ventana;
    }

    public void setVentana(ABMCondicionIva_Buscar ventana) {
        this.ventana = ventana;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    private void buscarCondicionIva()
    {
        GestorCondicionIva gestor=new GestorCondicionIva();
        CondicionIva[] ci=gestor.buscarConLIKE(valor);
        JList list=ventana.getLstCondicionIva();
        list.removeAll();
        ventana.setCondicionIva(ci);
        cargarLista(list,ci);
    }

     private void cargarLista(JList list, CondicionIva[] condicion) {

        ItemCombo item[]=new ItemCombo[condicion.length];
        for(int i=0;i<condicion.length;i++)
        {
            ItemCombo x=new ItemCombo();
            x.setMostrar(condicion[i].getNombre());
            item[i]=x;
        }
        list.setListData(item);
    }

}
