/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.util.List;
import javax.swing.JComboBox;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.AccioncalidadJpaController;
import metalsoft.datos.jpa.controller.TipomaquinaJpaController;
import metalsoft.datos.jpa.entity.Accioncalidad;
import metalsoft.datos.jpa.entity.Tipomaquina;
import metalsoft.util.ItemCombo;

/**
 *
 * @author Vicky
 */
public class GestorProcesoCalidad {
    
    
    public void cargarComboAccion(JComboBox combo){
        combo.removeAllItems();
        AccioncalidadJpaController con=new AccioncalidadJpaController(JpaUtil.getEntityManagerFactory());
        List<Accioncalidad> list=con.findAccioncalidadEntities();
        combo.addItem(new ItemCombo("-1","--Seleccionar--"));
        for(Accioncalidad m : list){
            combo.addItem(new ItemCombo(String.valueOf(m.getIdaccioncalidad()),m.getNombre()));
        }
    }
    
    public void cargarComboTipoMaquina(JComboBox combo){
        combo.removeAllItems();
        TipomaquinaJpaController con=new TipomaquinaJpaController(JpaUtil.getEntityManagerFactory());
        List<Tipomaquina> list=con.findTipomaquinaEntities();
        combo.addItem(new ItemCombo("-1","--Seleccionar--"));
        for(Tipomaquina m : list){
            combo.addItem(new ItemCombo(String.valueOf(m.getIdtipomaquina()),m.getNombre()));
        }
    }
    
    public Accioncalidad getAccionCalidad(long id){
        AccioncalidadJpaController con=new AccioncalidadJpaController(JpaUtil.getEntityManagerFactory());
        return con.findAccioncalidad(id);
    }
    
    public Tipomaquina getTipoMaquina(long id){
        TipomaquinaJpaController con=new TipomaquinaJpaController(JpaUtil.getEntityManagerFactory());
        return con.findTipomaquina(id);
    }
}
