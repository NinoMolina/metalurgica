/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import metalsoft.negocio.gestores.IBuscador;
import javax.swing.JList;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.gestores.GestorPieza;
import metalsoft.negocio.ventas.Pieza;
/**
 *
 * @author Vicky
 */
public class HiloBuscarPieza extends Thread {

    private IBuscador cliente;
    private String valor;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public IBuscador getVentana() {
        return cliente;
    }

    public void setVentana(IBuscador cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        buscarPieza();
    }

    private void buscarPieza()
    {
        GestorPieza gestor=new GestorPieza();
        metalsoft.datos.dbobject.PiezaDB[] tm=gestor.buscarConLIKE(valor);
        JList list=cliente.getList(HiloBuscarPieza.class.getName());
        list.removeAll();
        cliente.setBusqueda(tm);
        cargarLista(list,tm);

        //ventana.getBsyBuscar().setBusy(false);
        //ventana.getBsyBuscar().setVisible(false);
    }
    private void cargarLista(JList list, metalsoft.datos.dbobject.PiezaDB[] pieza) {

        ItemCombo item[]=new ItemCombo[pieza.length];
        for(int i=0;i<pieza.length;i++)
        {
            ItemCombo x=new ItemCombo();
            x.setMostrar(pieza[i].getNombre());
            x.setId(String.valueOf(pieza[i].getIdpieza()));
            item[i]=x;
        }
        list.setListData(item);
    }

}
