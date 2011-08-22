/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;
import java.sql.Connection;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JList;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.negocio.access.AccessEmpresaMetalurgica;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.gestores.IBuscador;

/**
 *
 * @author Lorreine Prescott
 */
public class HiloBuscarEmpresaMetalurgica extends Thread {
    private Timer timer;
    private IBuscador prov;
    private String valor;

    @Override
    public void run() {
        buscarEmpresasMetalurgicas();
    }

    public IBuscador getVentana() {
        return prov;
    }

    public void setVentana(IBuscador ventana) {
        this.prov = ventana;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    private void buscarEmpresasMetalurgicas()
    {
        Connection cn=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(HiloBuscarEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        metalsoft.datos.dbobject.EmpresametalurgicaDB[] empresasMetalurgicas= AccessEmpresaMetalurgica.findByRazonsocialILIKE(valor,cn);
        JList list=prov.getList(HiloBuscarEmpresaMetalurgica.class.getName());
        JComboBox combo=prov.getCombo(HiloBuscarEmpresaMetalurgica.class.getName());
        prov.setBusqueda(empresasMetalurgicas);
        if(list!=null)
        {
            list.removeAll();
            cargarLista(list,empresasMetalurgicas);
        }
        if(combo!=null)
        {
            combo.removeAllItems();
            cargarCombo(combo, empresasMetalurgicas);
        }

    }

    public void iniciarTimer() {
        timer=new Timer(true);
    }

    private void cargarLista(JList list, metalsoft.datos.dbobject.EmpresametalurgicaDB[] empresasMetalurgicas) {
        ItemCombo item=null,items[]=new ItemCombo[empresasMetalurgicas.length];
        for(int i=0;i<empresasMetalurgicas.length;i++)
        {
            item=new ItemCombo(String.valueOf(empresasMetalurgicas[i].getIdempresametalurgica()), empresasMetalurgicas[i].getRazonsocial());
            items[i]=item;
        }
        list.setListData(items);
    }
    private void cargarCombo(JComboBox combo, metalsoft.datos.dbobject.EmpresametalurgicaDB[] empresasMetalurgicas) {
        ItemCombo item=null;
        combo.removeAllItems();
        combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
        for(int i=0;i<empresasMetalurgicas.length;i++)
        {
            item=new ItemCombo(String.valueOf(empresasMetalurgicas[i].getIdempresametalurgica()), empresasMetalurgicas[i].getRazonsocial());
            combo.addItem(item);
        }
    }

    private void cargarLista(JList combo, ItemCombo[] items) {
        combo.setListData(items);
    }
}


