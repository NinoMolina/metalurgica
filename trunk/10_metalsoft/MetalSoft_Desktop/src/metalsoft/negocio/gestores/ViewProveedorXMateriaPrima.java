/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author Nino
 */
public class ViewProveedorXMateriaPrima implements Comparable{

    private long nroproveedor;
    private String razonsocial;
    private String condicioniva;
    private String provincia;
    private String mail;
    private String responsable;
    private double precio;
    private long idproveedor;
    private long idresponsable;
    private long idmateriaprima;
    private String telefono;
    public ViewProveedorXMateriaPrima()
    {
    }

    public String getCondicioniva() {
        return condicioniva;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCondicioniva(String condicioniva) {
        this.condicioniva = condicioniva;
    }

    public long getIdmateriaprima() {
        return idmateriaprima;
    }

    public void setIdmateriaprima(long idmateriaprima) {
        this.idmateriaprima = idmateriaprima;
    }

    public long getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(long idproveedor) {
        this.idproveedor = idproveedor;
    }

    public long getIdresponsable() {
        return idresponsable;
    }

    public void setIdresponsable(long idresponsable) {
        this.idresponsable = idresponsable;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public long getNroproveedor() {
        return nroproveedor;
    }

    public void setNroproveedor(long nroproveedor) {
        this.nroproveedor = nroproveedor;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public int compareTo(Object o) {
        ViewProveedorXMateriaPrima obj=(ViewProveedorXMateriaPrima) o;
        if(this.getIdmateriaprima()==obj.getIdmateriaprima() && this.getIdproveedor()==obj.getIdproveedor())
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }

    public static int contain(Comparable px, Collection coleccion)
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
