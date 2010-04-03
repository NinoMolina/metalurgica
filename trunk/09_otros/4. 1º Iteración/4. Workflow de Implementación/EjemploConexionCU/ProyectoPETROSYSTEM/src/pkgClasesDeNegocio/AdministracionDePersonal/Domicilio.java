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
public class Domicilio implements Comparable{
  
    private String nombreCalle=new String();
    private int nroCalle;
    private String departamento=new String();
    private int nroPiso;
    private String barrio=new String();
    private Provincia provincia=new Provincia();
    private Ciudad ciudad=new Ciudad();
    
     private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    

    public Domicilio() {}

    public Domicilio(String nombreCalle,int nroCalle,String departamento,int nroPiso,
                     String barrio,Provincia provincia,Ciudad ciudad) {
        this.nombreCalle=nombreCalle;
        this.nroCalle = nroCalle;
        this.departamento=departamento;
        this.nroPiso = nroPiso;
        this.barrio=barrio;
        this.provincia=provincia;
        this.ciudad=ciudad;
    }
    
    public int compareTo(Object o) {
        Domicilio otro=(Domicilio) o;
        
        return otro.nombreCalle.compareTo(this.nombreCalle);
    }
    
    
    public String getNombreCalle() {
        return nombreCalle;
    }

    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle = nombreCalle;
    }

    public int getNroCalle() {
        return nroCalle;
    }

    public void setNroCalle(int nroCalle) {
        this.nroCalle = nroCalle;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getNroPiso() {
        return nroPiso;
    }

    public void setNroPiso(int nroPiso) {
        this.nroPiso = nroPiso;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

   
    


}