/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;

import javax.swing.JList;
import metalsoft.datos.dbobject.Matriz;
import metalsoft.negocio.ItemCombo;
import metalsoft.negocio.gestores.GestorMatriz;

/**
 *
 *
 * @author Mariana
 */
public class HiloBuscarMatriz extends Thread {
    private ABMMatriz_Buscar ventana;
    private String valor;

    @Override
    public void run() {
        buscarMatriz();
    }

    public ABMMatriz_Buscar getVentana() {
        return ventana;
    }

    public void setVentana(ABMMatriz_Buscar ventana) {
        this.ventana = ventana;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    private void buscarMatriz()
    {
        GestorMatriz gestor=new GestorMatriz();
        Matriz[] m = gestor.buscarMatriz(valor);
        JList list=ventana.getLstMatriz();
        list.removeAll();
        ventana.setMatriz(m);
        cargarLista(list,m);

        //ventana.getBsyBuscar().setBusy(false);
        //ventana.getBsyBuscar().setVisible(false);
    }


    private void cargarLista(JList list, Matriz[] matriz) {

        ItemCombo item[]=new ItemCombo[matriz.length];
        for(int i=0;i<matriz.length;i++)
        {
            ItemCombo x=new ItemCombo();
            x.setMostrar(matriz[i].getNombre());
            item[i]=x;
        }
        list.setListData(item);
    }

}
