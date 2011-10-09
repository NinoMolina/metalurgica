/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.EjecucionplanificacionproduccionJpaController;
import metalsoft.datos.jpa.controller.EmpleadoJpaController;
import metalsoft.datos.jpa.controller.MaquinaJpaController;
import metalsoft.datos.jpa.controller.RoturaJpaController;
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.entity.Ejecucionplanificacionproduccion;
import metalsoft.datos.jpa.entity.Empleado;
import metalsoft.datos.jpa.entity.Maquina;
import metalsoft.datos.jpa.entity.Rotura;
import metalsoft.util.ItemCombo;

/**
 *
 * @author Vicky
 */
public class GestorParadaDeMaquina {
    public GestorParadaDeMaquina(){

    }
    public void cargarComboMaquinas(JComboBox combo){
        MaquinaJpaController con=new MaquinaJpaController(JpaUtil.getEntityManagerFactory());
        List<Maquina> list=con.findMaquinaEntities();
        combo.addItem(new ItemCombo("-1","--Seleccionar--"));
        for(Maquina m : list){
            combo.addItem(new ItemCombo(String.valueOf(m.getIdmaquina()),m.getNombre()));
        }
    }
    public void cargarComboRoturas(JComboBox combo){
        RoturaJpaController con=new RoturaJpaController(JpaUtil.getEntityManagerFactory());
        List<Rotura> list=con.findRoturaEntities();
        combo.addItem(new ItemCombo("-1","--Seleccionar--"));
        for(Rotura m : list){
            combo.addItem(new ItemCombo(String.valueOf(m.getIdrotura()),m.getNombre()));
        }
    }
    public void cargarComboEmpleado(JComboBox combo){
        EmpleadoJpaController con=new EmpleadoJpaController(JpaUtil.getEntityManagerFactory());
        List<Empleado> list=con.findEmpleadoEntities();
        combo.addItem(new ItemCombo("-1","--Seleccionar--"));
        for(Empleado m : list){
            combo.addItem(new ItemCombo(String.valueOf(m.getIdempleado()),m.getNombre()));
        }
    }
    
    public List<Ejecucionplanificacionproduccion> obtenerListaPedidos(){
        List<Ejecucionplanificacionproduccion> list=JpaUtil.getEjecucionplanificacionproduccionSegunEstado(1);
        return list;
    }
    
    public boolean guardadParadaDeMaquina(Ejecucionplanificacionproduccion v){
        EjecucionplanificacionproduccionJpaController con=new EjecucionplanificacionproduccionJpaController(JpaUtil.getEntityManagerFactory());
        try {
            con.edit(v);
            return true;
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(GestorParadaDeMaquina.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(GestorParadaDeMaquina.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(GestorParadaDeMaquina.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
