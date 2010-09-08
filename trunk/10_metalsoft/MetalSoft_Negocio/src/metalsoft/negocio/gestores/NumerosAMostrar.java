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

    public static final int NRO_PRODUCTO=1;
    public static final int NRO_PEDIDO=2;
    public static final int NRO_PRESUPUESTO=3;

    public static String getNumeroString(int tiponro,long nro)
    {
        switch(tiponro)
        {
            case NRO_PRODUCTO:
                    return "PROD-"+String.valueOf(nro);
            case NRO_PEDIDO:
                    return "PEDI-"+String.valueOf(nro);
            case NRO_PRESUPUESTO:
                    return "PRES-"+String.valueOf(nro);
            default:
                    return null;
        }
    }

    public static long getNumeroLong(String nro)
    {
        String[] v=nro.split("-");
        return Long.parseLong(v[1]);
    }
}
