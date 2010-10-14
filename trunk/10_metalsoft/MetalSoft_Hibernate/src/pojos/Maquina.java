package pojos;
// Generated 12/10/2010 01:33:18 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Maquina generated by hbm2java
 */
public class Maquina  implements java.io.Serializable {


     private long idmaquina;
     private Marca marca;
     private Estadomaquina estadomaquina;
     private Tipomaquina tipomaquina;
     private String nombre;
     private String descripcion;
     private Set<Maquinaxejecucionetapaproduccion> maquinaxejecucionetapaproduccions = new HashSet<Maquinaxejecucionetapaproduccion>(0);
     private Set<Etapadeproduccion> etapadeproduccions = new HashSet<Etapadeproduccion>(0);
     private Set<Maquinaxprocesocalidad> maquinaxprocesocalidads = new HashSet<Maquinaxprocesocalidad>(0);
     private Set<Mantenimientopreventivo> mantenimientopreventivos = new HashSet<Mantenimientopreventivo>(0);
     private Set<Mantenimientocorrectivo> mantenimientocorrectivos = new HashSet<Mantenimientocorrectivo>(0);

    public Maquina() {
    }

	
    public Maquina(long idmaquina) {
        this.idmaquina = idmaquina;
    }
    public Maquina(long idmaquina, Marca marca, Estadomaquina estadomaquina, Tipomaquina tipomaquina, String nombre, String descripcion, Set<Maquinaxejecucionetapaproduccion> maquinaxejecucionetapaproduccions, Set<Etapadeproduccion> etapadeproduccions, Set<Maquinaxprocesocalidad> maquinaxprocesocalidads, Set<Mantenimientopreventivo> mantenimientopreventivos, Set<Mantenimientocorrectivo> mantenimientocorrectivos) {
       this.idmaquina = idmaquina;
       this.marca = marca;
       this.estadomaquina = estadomaquina;
       this.tipomaquina = tipomaquina;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.maquinaxejecucionetapaproduccions = maquinaxejecucionetapaproduccions;
       this.etapadeproduccions = etapadeproduccions;
       this.maquinaxprocesocalidads = maquinaxprocesocalidads;
       this.mantenimientopreventivos = mantenimientopreventivos;
       this.mantenimientocorrectivos = mantenimientocorrectivos;
    }
   
    public long getIdmaquina() {
        return this.idmaquina;
    }
    
    public void setIdmaquina(long idmaquina) {
        this.idmaquina = idmaquina;
    }
    public Marca getMarca() {
        return this.marca;
    }
    
    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    public Estadomaquina getEstadomaquina() {
        return this.estadomaquina;
    }
    
    public void setEstadomaquina(Estadomaquina estadomaquina) {
        this.estadomaquina = estadomaquina;
    }
    public Tipomaquina getTipomaquina() {
        return this.tipomaquina;
    }
    
    public void setTipomaquina(Tipomaquina tipomaquina) {
        this.tipomaquina = tipomaquina;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Set<Maquinaxejecucionetapaproduccion> getMaquinaxejecucionetapaproduccions() {
        return this.maquinaxejecucionetapaproduccions;
    }
    
    public void setMaquinaxejecucionetapaproduccions(Set<Maquinaxejecucionetapaproduccion> maquinaxejecucionetapaproduccions) {
        this.maquinaxejecucionetapaproduccions = maquinaxejecucionetapaproduccions;
    }
    public Set<Etapadeproduccion> getEtapadeproduccions() {
        return this.etapadeproduccions;
    }
    
    public void setEtapadeproduccions(Set<Etapadeproduccion> etapadeproduccions) {
        this.etapadeproduccions = etapadeproduccions;
    }
    public Set<Maquinaxprocesocalidad> getMaquinaxprocesocalidads() {
        return this.maquinaxprocesocalidads;
    }
    
    public void setMaquinaxprocesocalidads(Set<Maquinaxprocesocalidad> maquinaxprocesocalidads) {
        this.maquinaxprocesocalidads = maquinaxprocesocalidads;
    }
    public Set<Mantenimientopreventivo> getMantenimientopreventivos() {
        return this.mantenimientopreventivos;
    }
    
    public void setMantenimientopreventivos(Set<Mantenimientopreventivo> mantenimientopreventivos) {
        this.mantenimientopreventivos = mantenimientopreventivos;
    }
    public Set<Mantenimientocorrectivo> getMantenimientocorrectivos() {
        return this.mantenimientocorrectivos;
    }
    
    public void setMantenimientocorrectivos(Set<Mantenimientocorrectivo> mantenimientocorrectivos) {
        this.mantenimientocorrectivos = mantenimientocorrectivos;
    }




}


