/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.util.sort;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author Nino
 */
public class Sorts {

    /**
     *  Ordenamiento de Intercambio (Burbuja)
     */
    public static void bubbleSort (int[] v)
    {
      boolean ordenado = false;
      int i,j, aux, n = v.length;
      for (i=0; i<n-1 && !ordenado; i++)
      {
            ordenado = true;
            for (j=0; j<n-i-1; j++)
            {
                 if (v[j] > v[j+1])
                 {
                       ordenado = false;
                       aux = v[j];
                       v[j] = v[j+1];
                       v[j+1] = aux;
                 }
            }
      }
    }

    /**
     *  Ordenamiento de Selección
     */
    public static void seleccion (int[] v)
    {
          int n = v.length;
          for (int i = 0; i < n - 1; i++)
          {
                for (int j = i + 1; j < n; j++)
                {
                     if ( v[i] > v[j] )
                     {
                        int aux = v[i];
                        v[i] = v[j];
                        v[j] = aux;
                     }
                }
          }
    }

    /**
     *  Ordenamiento de Selección
     */
    public static Object[] seleccion (Collection c)
    {
          Object[] v=c.toArray();
          int n = v.length;
          for (int i = 0; i < n - 1; i++)
          {
                for (int j = i + 1; j < n; j++)
                {
                     if ( ((Comparable)v[i]).compareTo((Comparable)v[j])>0 )
                     {
                        Comparable aux = (Comparable)v[i];
                        v[i] = v[j];
                        v[j] = aux;
                     }
                }
          }
          return v;
    }

    /**
     *  Ordenamiento de Inserción
     */
    public static void insercion (int[] v)
    {
          int n = v.length;
          for (int j = 1; j < n; j++)
          {
                int k, y = v[j];
                for (k = j-1; k >= 0 && y < v[k]; k--)
                {
                    v[k+1]= v[k];
                }
                v[k+1]= y;
          }
    }

    /**
     *  Ordenamiento Quick Sort
     */
    public static void quickSort (int[] v)
    {
        ordenar ( 0, v.length - 1, v);
    }

    private static void ordenar (int izq, int der, int[] v)
    {
       int i, j, x, y;
       i = izq;
       j = der;
       x = v[(izq + der) / 2];
       do
       {
            while (v[i]<x && i<der) i++;
            while (x<v[j] && j>izq) j--;
            if (i <= j)
            {
                  y = v[i];
                  v[i] = v[j];
                  v[j] = y;
                  i++;
                  j--;
            }
       }
       while (i <= j);
       if (izq < j) ordenar(izq, j, v);
       if (i < der) ordenar(i, der, v);
    }


    /**
     *  Ordenamiento Heap Sort
     */
    public static void heapSort(int[] v)
    {
       int i, e, s, f, valori;
       int n = v.length;

       // crear el grupo inicial...
       for (i = 1; i < n; i++)
       {
            e = v[i];
            s = i;
            f = (s-1)/2;
            while (s>0 && v[f] < e)
            {
                  v[s] = v[f];
                  s = f;
                  f = (s-1)/2;
            }
            v[s] = e;
       }

       // se extrae la raiz, y se reordena el vector y el grupo...
       for (i = n-1; i>0; i--)
       {
            valori = v[i];
            v[i] = v[0];
            f = 0;
            if (i == 1) s = -1; else s = 1;
            if (i > 2 && v[2] > v[1])  s = 2;
            while ( s >= 0 && valori < v[s])
            {
                  v[f] = v[s];
                  f = s;
                  s = 2*f + 1;
                  if (s + 1 <= i - 1 && v[s] < v[s+1]) s++;
                  if (s > i - 1) s = -1;
            }
            v[f] = valori;
       }
    }

    /**
     *  Ordenamiento Shell Sort
     */
    public static void shellSort(int[] v)
    {
       int h, n = v.length;
       for (h = 1; h <= n / 9; h = 3*h + 1);
       for ( ; h > 0; h /= 3)
       {
             for (int j = h; j < n; j++)
             {
                  int k, y = v[j];
                  for (k = j - h; k >= 0 && y < v[k]; k-=h)
                  {
                        v[k+h] = v[k];
                  }
                  v[k+h] = y;
             }
       }
    }

}
