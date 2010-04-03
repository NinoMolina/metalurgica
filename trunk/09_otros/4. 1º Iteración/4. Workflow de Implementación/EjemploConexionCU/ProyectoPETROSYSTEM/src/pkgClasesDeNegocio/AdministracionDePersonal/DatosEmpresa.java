/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgClasesDeNegocio.AdministracionDePersonal;

import pkgRecursosDeSoporte.ClaseBase;

/**
 *
 * @author Armando
 */
public class DatosEmpresa implements Comparable{

    private long cuit;
    private String razonSocial=new String();
    private Domicilio domicilio=new Domicilio();
    
    private ClaseBase atributosBD=new ClaseBase();

    public DatosEmpresa() {
    }

    public DatosEmpresa(long cuit,String razonSocial,Domicilio domicilio){
      this.cuit=cuit;
      this.razonSocial=razonSocial;
      this.domicilio=domicilio;
    }
    
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
 
    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }
    
      public int compareTo(Object o) {
       DatosEmpresa otro=(DatosEmpresa) o;
        
        
        return (int) (otro.cuit-this.cuit);
    }
    
    
    
}
