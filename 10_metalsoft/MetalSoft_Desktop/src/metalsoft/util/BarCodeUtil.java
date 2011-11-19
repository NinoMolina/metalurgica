/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.util;

/**
 *
 * @author Nino
 */
public class BarCodeUtil {

    public static final String COD_PRODUCTO_REAL = "1";
    public static final String COD_EJECUCION_ETAPA_PRODUCCION = "2";
    public static final String COD_PIEZA_REAL = "3";
    public static final String COD_EJECUCION_PROCESO_CALIDAD = "4";
    
    public static final String TAG_COMIENZO = "mscb";
    public static final String COD_PROCESO_CALIDAD_OK = "OK";
    public static final String COD_PROCESO_CALIDAD_ERROR = "ERROR";
    
    public static String generarCodigo(String COD,String valor) {
        String cod = "";

        cod = TAG_COMIENZO + "-" + COD + "-" + valor;

        return cod;
    }
}
