/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.util.Date;
import java.util.List;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;

/**
 *
 * @author Nino
 */
public class GestorProduccionDiaria {

    public List<Detalleplanificacionproduccion> buscarPlanificacionDiaria(Date fecha) {
        return JpaUtil.getDetallePlanificacionProduccionDiaria(fecha);
    }
}
