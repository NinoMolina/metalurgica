/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.util.List;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.entity.Detalleplanificacioncalidad;
import metalsoft.util.Fecha;

/**
 *
 * @author Nino
 */
public class GestorControlCalidadDiario {

    public List<Detalleplanificacioncalidad> buscarPlanificacionDiaria() {
        return JpaUtil.getDetallePlanificacionCalidadDiaria(Fecha.fechaActualDate());
    }
    
}
