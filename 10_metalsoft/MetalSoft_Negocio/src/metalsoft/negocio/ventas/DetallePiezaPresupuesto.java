/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.ventas;

import java.util.Date;
import metalsoft.negocio.gestores.PiezaXEtapas;
import metalsoft.util.Fecha;

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

    public Date calcularDuracion(Date duracionEstimada,double alto,double ancho,double largo)
    {
        double volumen=alto*ancho*largo;

        int horas=duracionEstimada.getHours();
        int minutos=duracionEstimada.getMinutes();
        int segundos=duracionEstimada.getSeconds();

        horas=(int) (horas * volumen);
        minutos=(int) (minutos * volumen);
        segundos=(int) (segundos * volumen);

        Date duracion=(Date) duracionEstimada.clone();
        duracion.setHours(horas);
        duracion.setMinutes(minutos);
        duracion.setSeconds(segundos);

        duracion=Fecha.diferenciaEnSegundosMinutosHoras(duracionEstimada, duracion);
        
        return duracion;
    }


}
