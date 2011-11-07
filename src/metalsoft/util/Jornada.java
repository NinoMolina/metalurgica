/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.util;

/**
 *
 * @author Nino
 */
public class Jornada {

    public static int HORAS_JORNADA = (MetalsoftProperties.getProperties() == null ? 0 : Integer.valueOf(MetalsoftProperties.getProperty(MetalsoftProperties.HORAS_JORNADA)));
    public static int HORA_INICIO_JORNADA = (MetalsoftProperties.getProperties() == null ? 0 : Integer.valueOf(MetalsoftProperties.getProperty(MetalsoftProperties.HORA_INICIO_JORNADA)));;
    public static int CANT_HORAS_COMIDA = (MetalsoftProperties.getProperties() == null ? 0 : Integer.valueOf(MetalsoftProperties.getProperty(MetalsoftProperties.CANT_HORAS_COMIDA)));;
    public static int HORA_FIN_JORNADA = HORAS_JORNADA + CANT_HORAS_COMIDA + HORA_INICIO_JORNADA;
    public static int MINUTOS_ENTRE_ETAPAS = (MetalsoftProperties.getProperties() == null ? 0 : Integer.valueOf(MetalsoftProperties.getProperty(MetalsoftProperties.MINUTOS_ENTRE_ETAPAS)));;
}
