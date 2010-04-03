/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgClasesDeNegocio.AdministracionDePersonal;

import java.util.Date;
import pkgRecursosDeSoporte.ClaseBase;
import pkgRecursosDeSoporte.pkgLista.Lista;

/**
 *
 * @author Armando
 */
public class Empleado implements Comparable{

    private String nombre=new String();
    private String apellido=new String();
    private Date fechaNacimiento=new Date();
    private Ciudad lugarNacimiento=new Ciudad();
    private int nroDocumento;
    //private TipoDocumento tipoDocumento=new TipoDocumento();
    private int cuil;
    private Date fechaIngreso=new Date();
    private Date fechaEgreso=new Date();
    private String nombrePadre=new String();
    private String nombreMadre=new String();
    private Domicilio domicilio=new Domicilio();
    private Lista telefonos=new Lista();
    //private Puesto puesto=new Puesto();
    private Estado estado=new Estado();
    private Lista historialEstado=new Lista();
    private Lista adelantos=new Lista();
    private Lista turnos=new Lista();
    private Lista planificaciones=new Lista();
    private Lista francos=new Lista();
    private Lista pagosEmpleado=new Lista();
    private Usuario usuario=new Usuario();
    
     private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    

    
    public Empleado(){}
    
    public Empleado(String nombre,String apellido,Date fechaNacimiento,Ciudad lugarNacimiento,
                   int nroDocumento,int cuil,Date fechaIngreso,Date fechaEgreso,String nombrePadre,
                   String nombreMadre,Domicilio domicilio,Lista telefonos,/*Puesto puesto,*/Estado estado,
                   Lista historialEstado,Lista adelantos,Lista turnos,Lista planificaciones,Lista francos,
                   Lista pagosEmpleado,Usuario usuario){
        
        this.nombre=nombre;
        this.apellido=apellido;
        this.fechaNacimiento=fechaNacimiento;
        this.lugarNacimiento=lugarNacimiento;
        this.nroDocumento=nroDocumento;
        this.cuil=cuil;
        this.fechaIngreso=fechaIngreso;
        this.fechaEgreso=fechaEgreso;
        this.nombrePadre=nombrePadre;
        this.nombreMadre=nombreMadre;
        this.domicilio=domicilio;
        this.telefonos=telefonos;
        //this.puesto=puesto;
        this.estado=estado;
        this.historialEstado=historialEstado;
        this.adelantos=adelantos;
        this.turnos=turnos;
        this.planificaciones=planificaciones;
        this.francos=francos;
        this.pagosEmpleado=pagosEmpleado;
        this.usuario=usuario;
        
    
    }
    
    public Empleado getEmpleadoSiEstado(Estado e){
      int res=this.estado.compareTo(e);
      
      if(res==0){return this;}
      
      return null;
    
    }

    public int compareTo(Object o) {
       Empleado otro=(Empleado) o;
        String nomApOtro=otro.nombre+" "+otro.apellido;
        String nomApEste=this.nombre+" "+this.apellido;
        
        return nomApOtro.compareTo(nomApEste);
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Ciudad getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(Ciudad lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public int getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(int nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public int getCuil() {
        return cuil;
    }

    public void setCuil(int cuil) {
        this.cuil = cuil;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public String getNombrePadre() {
        return nombrePadre;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }

    public String getNombreMadre() {
        return nombreMadre;
    }

    public void setNombreMadre(String nombreMadre) {
        this.nombreMadre = nombreMadre;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Lista getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(Lista telefonos) {
        this.telefonos = telefonos;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Lista getHistorialEstado() {
        return historialEstado;
    }

    public void setHistorialEstado(Lista historialEstado) {
        this.historialEstado = historialEstado;
    }

    public Lista getAdelantos() {
        return adelantos;
    }

    public void setAdelantos(Lista adelantos) {
        this.adelantos = adelantos;
    }

    public Lista getTurnos() {
        return turnos;
    }

    public void setTurnos(Lista turnos) {
        this.turnos = turnos;
    }

    public Lista getPlanificaciones() {
        return planificaciones;
    }

    public void setPlanificaciones(Lista planificaciones) {
        this.planificaciones = planificaciones;
    }

    public Lista getFrancos() {
        return francos;
    }

    public void setFrancos(Lista francos) {
        this.francos = francos;
    }

    public Lista getPagosEmpleado() {
        return pagosEmpleado;
    }

    public void setPagosEmpleado(Lista pagosEmpleado) {
        this.pagosEmpleado = pagosEmpleado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

   
    
    
}
