/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCasosDeUso.pkgCompras.RegistrarRepresentanteProveedor;

import pkgCasosDeUso.pkgCompras.RegistrarRepresentanteProveedor.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgClasesDeNegocio.CuentaCorriente.RepresentanteDeProveedor;
import pkgClasesDeNegocio.Ventas.CondicionIva;
import pkgClasesDeNegocio.Ventas.Telefono;
import pkgClasesDeNegocio.Ventas.TipoTelefono;
import pkgRecursosDeSoporte.Mensaje;
import pkgRecursosDeSoporte.ValText;
import pkgRecursosDeSoporte.pkgLista.Iterador;
import pkgRecursosDeSoporte.pkgLista.Lista;

/**
 *
 * @author Diego
 */
public class GestorRegistrarRepresentanteProveedor {

    private MediadorCURegistrarRepresentanteProveedor mediadorCU=new MediadorCURegistrarRepresentanteProveedor();
    
    
    private IntermediarioBDRegistrarRepresentanteProveedor intermediario;
    private RepresentanteDeProveedor representanteDeProveedor;
    private PllaRegistrarRepresentanteProveedor plla;
    private Lista telefonos;
    private Lista proveedores;
    //private TipoTelefono tipoTelefonoSeleccionado;
    private Lista telefonosRepresentante=new Lista();
    private Proveedor proveedorSeleccionado;
    
    public void nuevoRepresentanteProveedor(PllaRegistrarRepresentanteProveedor plla) {
        this.plla=plla;
        
        this.telefonos=new Lista();
        this.proveedores=new Lista();
        this.intermediario=new IntermediarioBDRegistrarRepresentanteProveedor();
        representanteDeProveedor=new RepresentanteDeProveedor();
        this.mediadorCU.setGestor(this);
        
        this.buscarTelefonos();
        this.buscarProveedores();
   }

    public void tomarNombre(String nombre) {
        representanteDeProveedor.setNombre(nombre);    
    }
    
    public void tomarApellido(String apellido) {
        representanteDeProveedor.setApellido(apellido);    
    }

   public void tomarNumero1(int caracteristica, int numero, String tipoTelefono) {
       TipoTelefono tipoTelefonoAux = new TipoTelefono();
       tipoTelefonoAux.setNombre(tipoTelefono);
       tipoTelefonoAux=(TipoTelefono)telefonos.crearIterador().buscarElemento(tipoTelefonoAux);
       
       Telefono telefono = new Telefono();
       telefono.setCaracteristica(caracteristica);
       telefono.setNumero(numero);
       telefono.setTipoTelefono(tipoTelefonoAux);
        //Buscar antes en le lista de tipos de telefono, el tipo con el nombre que se pasa por parametro
        //Luego crear el objeto del tipo Telefono, y set el nro y tipoTelefono
        //Por ultimo agregar a la lista de telefonosRepresentante
        //Setear los atributos indicados en los comentarios de arriba
        
        this.telefonosRepresentante.insertar(telefono);
    }

    public void tomarNumero2(int caracteristica, int numero, String tipoTelefono) {
       TipoTelefono tipoTelefonoAux = new TipoTelefono();
       tipoTelefonoAux.setNombre(tipoTelefono);
       tipoTelefonoAux=(TipoTelefono)telefonos.crearIterador().buscarElemento(tipoTelefonoAux);
       
       Telefono telefono = new Telefono();
       telefono.setCaracteristica(caracteristica);
       telefono.setNumero(numero);
       telefono.setTipoTelefono(tipoTelefonoAux);
       this.telefonosRepresentante.insertar(telefono);
    }

    public void tomarNumero3(int caracteristica, int numero, String tipoTelefono) {
       TipoTelefono tipoTelefonoAux = new TipoTelefono();
       tipoTelefonoAux.setNombre(tipoTelefono);
       tipoTelefonoAux=(TipoTelefono)telefonos.crearIterador().buscarElemento(tipoTelefonoAux);
       
       Telefono telefono = new Telefono();
       telefono.setCaracteristica(caracteristica);
       telefono.setNumero(numero);
       telefono.setTipoTelefono(tipoTelefonoAux);
       this.telefonosRepresentante.insertar(telefono);
    }

    public void tomarConfirmacion(int confirmacion) {
    if (confirmacion==0){
        this.setTelefonosRepresentante();
        this.validarDatosMinimos();
        plla.deshabilitarCampos();
    }
    else{
        //this.plla.dispose;
        Mensaje.mensaje_Estandar("No se va a crear el Representante de Proveedor si no confirma", "El Representante de Proveedor no se va a crear", Mensaje.TIPO_INFORMACION);
    }
    }

    void prepararParaIniciarDeNuevo() {
        representanteDeProveedor=new RepresentanteDeProveedor();//Lo puse para la prueba, y recien anduvo
        this.telefonosRepresentante=new Lista();
        this.setProveedorSeleccionado(new Proveedor());
    }

    private void setTelefonosRepresentante(){
     representanteDeProveedor.setTelefonos(telefonosRepresentante);
    }
    
    public void tomarProveedor(String proveedor) {
        
     this.setProveedorSeleccionado(this.getProveedorSeleccionado(proveedor));   
     
     representanteDeProveedor.addAttribute("fk_Proveedor", new Integer(ValText.getInt_Integer(getProveedorSeleccionado().getAttribute("id_Proveedor"))));
    }
    
    private Proveedor getProveedorSeleccionado(String razonSocial){
      Iterador itProveedor=this.proveedores.crearIterador();
      
      while(itProveedor.siguiente()){
       Proveedor p=(Proveedor) itProveedor.getElementoActual();
       
       if(p.getRazonSocial().compareTo(razonSocial)==0){
        return p;
       }
           
 
      }
      
     
     return null;
    
    }

    private void buscarProveedores() {
       try{ 
        proveedores=intermediario.getProveedores();
        if(proveedorSeleccionado!=null){
         this.plla.mostrarProveedorSeleccionado();
        }
        else{
         this.plla.mostrarProveedores(proveedores); 
        }
       }
       catch(Exception e){
        this.plla.mostrarMensajePantalla("Error en Proveedores",Mensaje.TIPO_ERROR);
       }
        
    }


  private void buscarTelefonos() {
       
       try{ 
        telefonos=intermediario.getTelefonos();
        this.plla.mostrarTelefonos(telefonos);

       }
       catch(Exception e){
        this.plla.mostrarMensajePantalla("Error en Teléfonos",Mensaje.TIPO_ERROR);
       }
       
    }

    private void crearRepresentanteProveedor() {
        try {
            intermediario.crear(this.representanteDeProveedor);
            
            if(mediadorCU.poseeConexion()){
             this.representanteDeProveedor.addAttribute("id_RepresentanteDeProveedor",new Integer(this.getIdUtlimoRegistro()));
             mediadorCU.nuevoRepresentante(this.representanteDeProveedor);
            }    
             plla.mostrarMensajeRegistracionExitosa();
        } 
        catch (Exception ex) {
            this.plla.mostrarMensajePantalla("Error en Representantes",Mensaje.TIPO_ERROR);
        }
    }
    
    public void finCU(){
      mediadorCU.finCU();
      plla.cerrarPantalla();
    }
    
    private int getIdUtlimoRegistro() throws Exception{
     return intermediario.getIdUltimoRegistro();
    }

    private void validarDatosMinimos() {
    if (representanteDeProveedor.getApellido()!=null && representanteDeProveedor.getNombre()!=null && representanteDeProveedor.getTelefonos().estaVacia()==false){
        this.crearRepresentanteProveedor();
    }
    else
        Mensaje.mensaje_Estandar("No se han completado los datos mínimos", "Faltan completar datos mínimos", Mensaje.TIPO_ERROR);
    }

    public MediadorCURegistrarRepresentanteProveedor getMediadorCU() {
        return mediadorCU;
    }

    public void setMediadorCU(MediadorCURegistrarRepresentanteProveedor mediadorCU) {
        this.mediadorCU = mediadorCU;
    }

    public void setProveedorSeleccionado(Proveedor proveedorSeleccionado) {
        this.proveedorSeleccionado = proveedorSeleccionado;
    }

    public Proveedor getProveedorSeleccionado() {
        return proveedorSeleccionado;
    }
}
