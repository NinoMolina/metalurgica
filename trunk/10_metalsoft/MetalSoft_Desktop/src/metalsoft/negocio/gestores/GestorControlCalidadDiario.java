/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.util.Date;
import java.util.List;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.entity.Detalleplanificacioncalidad;

/**
 *
 * @author Nino
 */
public class GestorControlCalidadDiario {

    public List<Detalleplanificacioncalidad> buscarPlanificacionDiaria(Date fecha) {
        return JpaUtil.getDetallePlanificacionCalidadDiaria(fecha);
    }
}
