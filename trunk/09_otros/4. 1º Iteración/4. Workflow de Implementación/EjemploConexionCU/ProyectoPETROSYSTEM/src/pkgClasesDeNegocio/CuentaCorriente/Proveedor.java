/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgClasesDeNegocio.CuentaCorriente;

import pkgRecursosDeSoporte.pkgLista.*;
import pkgRecursosDeSoporte.*;
import pkgClasesDeNegocio.AdministracionDePersonal.Domicilio;
import pkgClasesDeNegocio.Ventas.CondicionIva;
/**
 *
 * @author Armando
 */
public class Proveedor implements Comparable{

   private String razonSocial=new String();
   private int cuit;
   private Domicilio domicilio=new Domicilio();
   private int nroIngresoBruto;
   private Lista representantes=new Lista();
   private Lista telefonos=new Lista();
   private CondicionIva condicionIva=new CondicionIva();
   private Lista tipoProveedor=new Lista();
   private CuentaCorrienteProveedor ctaCteProveedor=new CuentaCorrienteProveedor();
   private Lista productos=new Lista();
   
   private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    

    public Proveedor() {}

    public Proveedor(String razonSocial,int cuit,Domicilio domicilio,int nroIngresoBruto,
                     Lista representantes,Lista telefonos,CondicionIva condicionIva,
                     Lista tipoProveedor,CuentaCorrienteProveedor ctaCteProveedor,Lista productos) {
        this.razonSocial=razonSocial;
        this.cuit = cuit;
        this.domicilio=domicilio;
        this.nroIngresoBruto=nroIngresoBruto;
        this.representantes=representantes;
        this.telefonos=telefonos;
        this.condicionIva=condicionIva;
        this.tipoProveedor=tipoProveedor;
        this.ctaCteProveedor=ctaCteProveedor;
        this.productos=productos;
    }
    
    
    public int compareTo(Object o) {
        Proveedor otro=(Proveedor) o;
        
        return otro.cuit-this.cuit;
    }
    
   public boolean existeProveedor(Proveedor proveedor){
    int i=this.compareTo(proveedor);
    if(i==0) return true;
    return false;   
   }


    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public int getCuit() {
        return cuit;
    }

    public void setCuit(int cuit) {
        this.cuit = cuit;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public int getNroIngresoBruto() {
        return nroIngresoBruto;
    }

    public void setNroIngresoBruto(int nroIngresoBruto) {
        this.nroIngresoBruto = nroIngresoBruto;
    }

    public Lista getRepresentantes() {
        return representantes;
    }

    public void setRepresentantes(Lista representantes) {
        this.representantes = representantes;
    }

    public Lista getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(Lista telefonos) {
        this.telefonos = telefonos;
    }

    public CondicionIva getCondicionIva() {
        return condicionIva;
    }

    public void setCondicionIva(CondicionIva condicionIva) {
        this.condicionIva = condicionIva;
    }

    public Lista getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(Lista tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }

    public CuentaCorrienteProveedor getCtaCteProveedor() {
        return ctaCteProveedor;
    }

    public void setCtaCteProveedor(CuentaCorrienteProveedor ctaCteProveedor) {
        this.ctaCteProveedor = ctaCteProveedor;
    }

    public Lista getProductos() {
        return productos;
    }

    public void setProductos(Lista productos) {
        this.productos = productos;
    }

   
    
}
