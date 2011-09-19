/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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

    static {
        FileInputStream file = null;
        try {
            file = new FileInputStream(pathName);
            properties = new Properties();
            properties.load(file);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String propName) {
        return properties.getProperty(propName);
    }
}
