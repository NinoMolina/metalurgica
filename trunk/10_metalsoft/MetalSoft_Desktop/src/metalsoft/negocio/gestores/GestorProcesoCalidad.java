/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.AccioncalidadJpaController;
import metalsoft.datos.jpa.controller.ProcesocalidadJpaController;
import metalsoft.datos.jpa.controller.TipomaquinaJpaController;
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.entity.Accioncalidad;
import metalsoft.datos.jpa.entity.Procesocalidad;
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
    
    public long nuevoNumeroProceso(){
        ProcesocalidadJpaController con = new ProcesocalidadJpaController(JpaUtil.getEntityManagerFactory());
        List<Procesocalidad> list = new ArrayList<Procesocalidad>();
        long nro = 0;
        for(Procesocalidad p : list){
            if(p.getNroproceso().longValue() > nro){
                nro = p.getNroproceso().longValue();
            }
        }
        return nro;
    }
    
    public long guardarProceso(Procesocalidad p){
        ProcesocalidadJpaController con = new ProcesocalidadJpaController(JpaUtil.getEntityManagerFactory());
        con.create(p);
        
        return p.getIdprocesocalidad();
    }
    
    public long modificarProceso(Procesocalidad p){
        ProcesocalidadJpaController con = new ProcesocalidadJpaController(JpaUtil.getEntityManagerFactory());
        try {
            con.edit(p);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(GestorProcesoCalidad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(GestorProcesoCalidad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GestorProcesoCalidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return p.getIdprocesocalidad();
    }
    
    public Procesocalidad getProcesoCalidad(long id){
        ProcesocalidadJpaController con = new ProcesocalidadJpaController(JpaUtil.getEntityManagerFactory());
        return con.findProcesocalidad(id);
    }
    
    public List<Procesocalidad> getListProcesoCalidad(){
        ProcesocalidadJpaController con = new ProcesocalidadJpaController(JpaUtil.getEntityManagerFactory());
        return con.findProcesocalidadEntities();
    }
    
    public List<Procesocalidad> getListProcesoCalidadByName(String text){
        return JpaUtil.getProcesoCalidadByNombreLike(text);
    }
}
