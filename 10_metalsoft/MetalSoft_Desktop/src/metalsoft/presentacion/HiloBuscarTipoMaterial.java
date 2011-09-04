/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;

import javax.swing.JList;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.gestores.GestorTipoMaterial;
import metalsoft.negocio.produccion.TipoMaterial;

/**
 *
 * @author Nino
 */
public class HiloBuscarTipoMaterial extends Thread {

    private ABMTipoMaterial_Buscar ventana;
    private String valor;

    @Override
    public void run() {
        buscarTipoMaterial();
    }

    public ABMTipoMaterial_Buscar getVentana() {
        return ventana;
    }

    public void setVentana(ABMTipoMaterial_Buscar ventana) {
        this.ventana = ventana;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    private void buscarTipoMaterial()
    {
        GestorTipoMaterial gestor=new GestorTipoMaterial();
        TipoMaterial[] tm=gestor.buscarConLIKE(valor);
        JList list=ventana.getLstTipoMaterial();
        list.removeAll();
        ventana.setTm(tm);
        cargarLista(list,tm);
        
        //ventana.getBsyBuscar().setBusy(false);
        //ventana.getBsyBuscar().setVisible(false);
    }


    private void cargarLista(JList list, TipoMaterial[] tipoMaterial) {

        ItemCombo item[]=new ItemCombo[tipoMaterial.length];
        for(int i=0;i<tipoMaterial.length;i++)
        {
            ItemCombo x=new ItemCombo();
            x.setMostrar(tipoMaterial[i].getNombre());
            item[i]=x;
        }
        list.setListData(item);
    }

}
