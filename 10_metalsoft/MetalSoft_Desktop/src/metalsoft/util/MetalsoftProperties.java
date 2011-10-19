/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.infonode.docking.util.PropertiesUtil;

/**
 *
 * @author Nino
 */
public class MetalsoftProperties {

    private static Properties properties;
    private static final String pathName = "metalsoft-config.properties";
    private static FileInputStream fis;
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
        try {
            fis = new FileInputStream(pathName);
            properties = new Properties();
            properties.load(fis);
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
            RandomAccessFile raf = new RandomAccessFile(pathName, "rw");
            System.out.println("FP: " + raf.getFilePointer());
            while (raf.getFilePointer() < raf.length()) {
                System.out.println("FP: " + raf.getFilePointer());
                String line = raf.readLine();
                if (line != null && !"".equals(line)) {
                    String parts[] = line.split("=");
                    if (parts.length > 1) {
                        String key = parts[0];
                        if (key.equals(propName)) {
                            System.out.println("LINE: " + line.length());
                            raf.seek(raf.getFilePointer() - line.length() - 1);
                            System.out.println("FP: " + raf.getFilePointer());
                            raf.writeBytes(propName + "=" + value + System.getProperty("line.separator"));
//                            raf.writeBytes(System.getProperty("line.separator"));
                            System.out.println("FP: " + raf.getFilePointer());
                            break;
                        }
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MetalsoftProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
