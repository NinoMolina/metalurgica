/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nino
 */
public class MetalsoftProperties {

    private static Properties properties;
    private static final String pathName = "metalsoft-config.properties";
    /*
     * public fields
     */
    public static final String PUERTO_FIN_ETAPA = "puerto.fin.etapa";
    public static final String EXPRESION_REGULAR_CODIGO_BARRA = "expresion.regular.codigo.barra";
    public static final String IP_FIN_ETAPA = "ip.fin.etapa";
    public static final String IP_FIN_PROCESO_CALIDAD = "ip.fin.proceso.calidad";
    public static final String PUERTO_FIN_PROCESO_CALIDAD = "puerto.fin.proceso.calidad";
    public static final String HORAS_JORNADA = "horas.jornada";
    public static final String HORA_INICIO_JORNADA = "hora.inicio.jornada";
    public static final String CANT_HORAS_COMIDA = "cant.horas.comida";
    public static final String MINUTOS_ENTRE_ETAPAS = "minutos.entre.etapas";

    static {
        FileInputStream fis = null;
        try {
            properties = new Properties();
            properties.load(new FileInputStream(pathName));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public static String getProperty(String propName) {
        return properties.getProperty(propName);
    }

    public static Properties getProperties() {
        return properties;
    }

    public static void setProperties(Properties properties) {
        MetalsoftProperties.properties = properties;
    }

    public static void savePropertyValue(String propName, String value) {
        properties.setProperty(propName, value);
        try {
            properties.store(new FileOutputStream(pathName), null);
        } catch (IOException ex) {
            Logger.getLogger(MetalsoftProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
