/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.util;

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
}
