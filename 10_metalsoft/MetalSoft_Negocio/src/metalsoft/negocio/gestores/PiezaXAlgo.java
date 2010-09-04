/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author Nino
 */
public abstract class PiezaXAlgo implements Comparable{

    private long idPedido,idProducto,idPieza,idDetallePedido;
    private String nombrePieza,nombreProducto;

    public String getNombrePieza() {
        return nombrePieza;
    }

    public void setNombrePieza(String nombrePieza) {
        this.nombrePieza = nombrePieza;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    
    public long getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(long idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }
    
    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }

    public long getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(long idPieza) {
        this.idPieza = idPieza;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    
    public int compareTo(Object o) {
        PiezaXAlgo x=(PiezaXAlgo)o;

        long idPed=x.getIdPedido();
        long idPi=x.getIdPieza();
        long idPro=x.getIdProducto();
        long idDetPed=x.getIdDetallePedido();
//        if(idPed==this.getIdPedido() && idPi==this.getIdPieza() && idPro==this.getIdProducto() && idDetPed==this.getIdDetallePedido())
//        {
//            return 0;
//        }
        if(idPed==this.getIdPedido())
        {
            if(idDetPed==this.getIdDetallePedido())
            {
                if(idPro==this.getIdProducto())
                {
                    if(idPi==this.getIdPieza())
                    {
                        //son iguales
                        return 0;
                    }
                    else
                    {
                        //diferencia por pieza
                        if(this.getIdPieza()<idPi)return -1;
                        else return 1;
                    }
                }
                else
                {
                    //diferencia por producto
                    if(this.getIdProducto()<idPro)return -1;
                    else return 1;
                }
            }
            else
            {
                //diferencia por detalle pedido
                if(this.getIdDetallePedido()<idDetPed)return -1;
                else return 1;
            }
        }
        else
        {
            //diferencia por pedido
            if(this.getIdPedido()<idPed)return -1;
            else return 1;
        }


    }

    /*
     * retorna -1 si no contiene el objeto
     * retorna la posicion si es que se contienen el objeto
     */
    public int contain(Comparable px, Collection coleccion)
    {
        Iterator<Comparable> i=coleccion.iterator();
        Comparable x=null;
        int contador=0;
        while(i.hasNext())
        {
            x=i.next();
            if(x.compareTo(px)==0)return contador;
            contador++;
        }
        return -1;
    }

}
