/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.util;

import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Nino
 */
public class Calculos{

    public static double mayor(double a,double b,double c)
    {
        if(a>b)
        {
            if(a>c)
                return a;
            else
                return c;
        }
        else
        {
            if(b>c)
                return b;
            else
                return c;
        }
    }

    public static double menor(double a,double b,double c)
    {
        if(a<b)
        {
            if(a<c)
                return a;
            else
                return c;
        }
        else
        {
            if(b<c)
                return b;
            else
                return c;
        }
    }
    public static double medio(double a,double b,double c)
    {
        if(a<b)
        {
            if(a>c)
                return a;
            else
                return c;
        }
        else
        {
            if(b>c)
                return b;
            else
                return c;
        }
    }

    public static int calcularCantidadMateriaPrima(int capacidadMatPrima, int cantPiezas) {
        if(capacidadMatPrima==0)return -1;
        double resto=cantPiezas%capacidadMatPrima;
        if(resto==0)return cantPiezas/capacidadMatPrima;
        else return (cantPiezas/capacidadMatPrima)+1;
    }

    //-1: cancelar
    public static int calcularCapacidadMateriaPrima(double altoMatPrima, double anchoMatPrima, double largoMatPrima, double altoPieza, double anchoPieza, double largoPieza,String nombrePieza,String nombreMatPrima) {
        double volumenPieza=altoPieza*anchoPieza*largoPieza;
        double volumenMatPrima=altoMatPrima*anchoMatPrima*largoMatPrima;
        int respuesta=-1;
        if(volumenPieza>volumenMatPrima)respuesta=JOptionPane.showConfirmDialog(null, "El volumen de la Pieza '"+nombrePieza+"' es mayor que el volumen de la materia prima '"+nombreMatPrima+"' seleccionada, desea continuar?","ATENCIÓN",JOptionPane.OK_CANCEL_OPTION);
        if(respuesta==JOptionPane.CANCEL_OPTION)return -1;

        //calculo los valores max med y min de la pieza y materia prima, donde los valores de la pieza
        //tienen que ser iguales o menores a los respectivos valores de la materia prima.
        double maxMatPrima=Calculos.mayor(altoMatPrima, anchoMatPrima, largoMatPrima);
        double minMatPrima=Calculos.menor(altoMatPrima, anchoMatPrima, largoMatPrima);
        double medMatPrima=Calculos.medio(altoMatPrima, anchoMatPrima, largoMatPrima);
        double maxPieza=Calculos.mayor(altoPieza, anchoPieza, largoPieza);
        double minPieza=Calculos.menor(altoPieza, anchoPieza, largoPieza);
        double medPieza=Calculos.medio(altoPieza, anchoPieza, largoPieza);

        if(maxPieza>maxMatPrima || medPieza>medMatPrima || minPieza>minMatPrima)respuesta=JOptionPane.showConfirmDialog(null, "Las dimensiones de la Pieza '"+nombrePieza+"' son mayores que la materia prima '"+nombreMatPrima+"' seleccionada, desea continuar?","ATENCIÓN",JOptionPane.OK_CANCEL_OPTION);
        if(respuesta==JOptionPane.CANCEL_OPTION)return -1;

        int entranEnMax=(int) (maxMatPrima / maxPieza);
        int entranEnMed=(int) (medMatPrima / medPieza);
        int entranEnMin=(int) (minMatPrima / minPieza);

        int entranTotal=entranEnMax*entranEnMed*entranEnMin;

        return entranTotal;
    }

    public static Date calcularDuracion(Date duracionEstimada, double alto, double ancho, double largo) {
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
