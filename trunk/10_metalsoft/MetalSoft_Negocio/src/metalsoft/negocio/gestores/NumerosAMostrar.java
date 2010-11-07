/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

/**
 *
 * @author Nino
 */
public class NumerosAMostrar {

    public static final int NRO_PRODUCTO = 1;
    public static final int NRO_PEDIDO = 2;
    public static final int NRO_PRESUPUESTO = 3;
    public static final int NRO_CLIENTE = 4;
    public static final int NRO_MATERIAPRIMA = 5;
    public static final int NRO_PROVEEDOR = 6;
    public static final int NRO_EMPLEADO = 7;
    public static final int NRO_MAQUINA = 8;
    public static final int NRO_PLANIF_PRODUCCION = 9;

    public static String getNumeroString(int tiponro, long nro) {
        switch (tiponro) {
            case NRO_PRODUCTO:
                return "PROD-" + String.valueOf(nro);
            case NRO_PEDIDO:
                return "PEDI-" + String.valueOf(nro);
            case NRO_PRESUPUESTO:
                return "PRES-" + String.valueOf(nro);
            case NRO_CLIENTE:
                return "CLIE-" + String.valueOf(nro);
            case NRO_MATERIAPRIMA:
                return "MAPR-" + String.valueOf(nro);
            case NRO_PROVEEDOR:
                return "PROV-" + String.valueOf(nro);
            case NRO_EMPLEADO:
                return "EMPL-" + String.valueOf(nro);
            case NRO_MAQUINA:
                return "MAQ-" + String.valueOf(nro);
            case NRO_PLANIF_PRODUCCION:
                return "PLAN-" + String.valueOf(nro);
            default:
                return null;
        }
    }

    public static long getNumeroLong(String nro) {
        String[] v = nro.split("-");
        return Long.parseLong(v[1]);
    }

    public static int getNumeroInt(String nro) {
        String[] v = nro.split("-");
        return Integer.parseInt(v[1]);
    }
}