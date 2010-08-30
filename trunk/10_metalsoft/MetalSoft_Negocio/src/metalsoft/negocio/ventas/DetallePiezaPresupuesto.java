/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.ventas;

import java.util.Date;
import metalsoft.negocio.gestores.PiezaXEtapas;

/**
 *
 * @author Nino
 */

public class DetallePiezaPresupuesto {

    private EtapaDeProduccion etapa;
    private Date duracionEtapaXPieza;

    public DetallePiezaPresupuesto() {
    }

    public Date getDuracionEtapaXPieza() {
        return duracionEtapaXPieza;
    }

    public void setDuracionEtapaXPieza(Date duracionEtapaXPieza) {
        this.duracionEtapaXPieza = duracionEtapaXPieza;
    }

    public EtapaDeProduccion getEtapa() {
        return etapa;
    }

    public void setEtapa(EtapaDeProduccion etapa) {
        this.etapa = etapa;
    }

    public Date calcularDuracion(PiezaXEtapas pxe)
    {
        double alto=pxe.getAlto();
        double ancho=pxe.getAncho();
        double largo=pxe.getLargo();
    }


}
