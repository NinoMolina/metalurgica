/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.util.List;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import metalsoft.util.Fecha;

/**
 *
 * @author Nino
 */
public class GestorProduccionDiaria {

    public List<Detalleplanificacionproduccion> buscarPlanificacionDiaria() {
        return JpaUtil.getDetallePlanificacionProduccionDiaria(Fecha.fechaActualDate());
    }
}
