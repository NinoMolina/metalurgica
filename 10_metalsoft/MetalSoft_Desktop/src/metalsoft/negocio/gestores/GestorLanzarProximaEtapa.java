/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.util.Date;
import java.util.List;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.DetalleejecucionplanificacionJpaController;
import metalsoft.datos.jpa.controller.EjecucionetapaproduccionJpaController;
import metalsoft.datos.jpa.controller.EstadoejecetapaprodJpaController;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacion;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import metalsoft.datos.jpa.entity.Ejecucionetapaproduccion;
import metalsoft.negocio.gestores.estados.IdsEstadoEjecucionEtapaProduccion;
import metalsoft.util.Fecha;

/**
 *
 * @author Nino
 */
public class GestorLanzarProximaEtapa {

    public void lanzarProximaEtapa(Detalleejecucionplanificacion detalleejecucionplanificacion, Detalleplanificacionproduccion detalleplanificacionproduccion) throws Exception {
        
        List<Detalleejecucionplanificacion> lstDetallejecucion = detalleejecucionplanificacion.getIdejecucionplanificacionproduccion().getDetalleejecucionplanificacionList();
        
        Long idPieza = detalleejecucionplanificacion.getPieza().getIdpieza();
        Long idProducto = detalleplanificacionproduccion.getIdproducto().getIdproducto();
        Integer orden = detalleejecucionplanificacion.getOrden();
        
        for (Detalleejecucionplanificacion detalleejec : lstDetallejecucion) {
            
            Detalleplanificacionproduccion detalleplanifprod = JpaUtil.getDetalleplanificacionproduccionPorIdDetalleejecucion(detalleejec.getId());
            
            if(detalleejec.getPieza().getIdpieza() == idPieza && 
                    detalleplanifprod.getIdproducto().getIdproducto() == idProducto &&
                    detalleejec.getOrden() == (orden++)){
                
                lanzarEjecucionEtapa(detalleejec);
                
            }
            
        }
    }

    private void lanzarEjecucionEtapa(Detalleejecucionplanificacion detalleejecucionplanificacion) throws Exception {
        
        DetalleejecucionplanificacionJpaController detalleejecucionplanificacionJpaController = new DetalleejecucionplanificacionJpaController(JpaUtil.getEntityManagerFactory());
        EjecucionetapaproduccionJpaController ejecucionetapaproduccionJpaController = new EjecucionetapaproduccionJpaController(JpaUtil.getEntityManagerFactory());
        EstadoejecetapaprodJpaController estadoejecetapaprodJpaController = new EstadoejecetapaprodJpaController(JpaUtil.getEntityManagerFactory());
        
        Date fechaActual = Fecha.fechaActualDate();
        
        detalleejecucionplanificacion.setFechainicio(fechaActual);
        detalleejecucionplanificacion.setHorainicio(fechaActual);
        
        Ejecucionetapaproduccion ejecucionetapaproduccion = detalleejecucionplanificacion.getEjecucionetapa();
        ejecucionetapaproduccion.setEstado(estadoejecetapaprodJpaController.findEstadoejecetapaprod(IdsEstadoEjecucionEtapaProduccion.ENEJECUCION));
        
        ejecucionetapaproduccionJpaController.edit(ejecucionetapaproduccion);
        detalleejecucionplanificacionJpaController.edit(detalleejecucionplanificacion);
        
        /*
         * imprimir el codigo de barra para la ejecucion de la etapa
         */
        
    }
}
