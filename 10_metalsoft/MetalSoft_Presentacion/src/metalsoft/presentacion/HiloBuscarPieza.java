/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;

import javax.swing.JList;
import metalsoft.negocio.ItemCombo;
import metalsoft.negocio.gestores.GestorPieza;
import metalsoft.negocio.ventas.Pieza;
/**
 *
 * @author Vicky
 */
public class HiloBuscarPieza extends Thread {

    private ABMPieza_Buscar ventana;
    private String valor;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public ABMPieza_Buscar getVentana() {
        return ventana;
    }

    public void setVentana(ABMPieza_Buscar ventana) {
        this.ventana = ventana;
    }

    @Override
    public void run() {
        buscarPieza();
    }

    private void buscarPieza()
    {
        GestorPieza gestor=new GestorPieza();
        metalsoft.datos.dbobject.Pieza[] tm=gestor.buscarConLIKE(valor);
        JList list=ventana.getLstTipoMaterial();
        list.removeAll();
        ventana.setTm(tm);
        cargarLista(list,tm);

        //ventana.getBsyBuscar().setBusy(false);
        //ventana.getBsyBuscar().setVisible(false);
    }
    private void cargarLista(JList list, metalsoft.datos.dbobject.Pieza[] pieza) {

        ItemCombo item[]=new ItemCombo[pieza.length];
        for(int i=0;i<pieza.length;i++)
        {
            ItemCombo x=new ItemCombo();
            x.setMostrar(pieza[i].getNombre());
            item[i]=x;
        }
        list.setListData(item);
    }

}
