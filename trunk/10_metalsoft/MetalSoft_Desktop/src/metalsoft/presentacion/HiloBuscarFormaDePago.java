/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;


import javax.swing.JList;
import metalsoft.negocio.gestores.GestorFormaDePago;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.ventas.FormaDePago;

/**
 *
 * @author Lorreine Prescott
 */
public class HiloBuscarFormaDePago extends Thread {

    private ABMFormaDePago_Buscar ventana;
    private String valor;

    @Override
    public void run() {
        buscarFormaDePago();
    }

     public ABMFormaDePago_Buscar getVentana() {
        return ventana;
    }

    public void setVentana(ABMFormaDePago_Buscar ventana) {
        this.ventana = ventana;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    private void buscarFormaDePago()
    {
        GestorFormaDePago gestor=new GestorFormaDePago();
        FormaDePago[] fp=gestor.buscarConLIKE(valor);
        JList list=ventana.getLstFormaDePago();
        list.removeAll();
        ventana.setFormaDePago(fp);
        cargarLista(list,fp);
    }

     private void cargarLista(JList list, FormaDePago[] formapago) {

        ItemCombo item[]=new ItemCombo[formapago.length];
        for(int i=0;i<formapago.length;i++)
        {
            ItemCombo x=new ItemCombo();
            x.setMostrar(formapago[i].getNombre());
            item[i]=x;
        }
        list.setListData(item);
    }

}
