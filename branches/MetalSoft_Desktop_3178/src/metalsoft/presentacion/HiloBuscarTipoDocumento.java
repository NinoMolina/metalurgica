/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;

import javax.swing.JList;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.gestores.GestorTipoDocumento;
import metalsoft.negocio.rrhh.TipoDocumento;

/**
 *
 * @author Lorreine Prescott
 */
public class HiloBuscarTipoDocumento extends Thread {

    private ABMTipoDocumento_Buscar ventana;
    private String valor;

    @Override
    public void run() {
        buscarTipoDocumento();
    }

     public ABMTipoDocumento_Buscar getVentana() {
        return ventana;
    }

    public void setVentana(ABMTipoDocumento_Buscar ventana) {
        this.ventana = ventana;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    private void buscarTipoDocumento()
    {
        GestorTipoDocumento gestor=new GestorTipoDocumento();
        TipoDocumento[] td=gestor.buscarConLIKE(valor);
        JList list=ventana.getLstTipoDocumento();
        list.removeAll();
        ventana.setTipoDocumento(td);
        cargarLista(list,td);
    }

     private void cargarLista(JList list, TipoDocumento[] tipodoc) {

        ItemCombo item[]=new ItemCombo[tipodoc.length];
        for(int i=0;i<tipodoc.length;i++)
        {
            ItemCombo x=new ItemCombo();
            x.setMostrar(tipodoc[i].getTipo());
            item[i]=x;
        }
        list.setListData(item);
    }

}


