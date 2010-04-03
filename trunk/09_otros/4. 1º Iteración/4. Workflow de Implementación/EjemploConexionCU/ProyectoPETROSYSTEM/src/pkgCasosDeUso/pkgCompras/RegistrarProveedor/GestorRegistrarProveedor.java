/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCasosDeUso.pkgCompras.RegistrarProveedor;

import pkgClasesDeNegocio.AdministracionDePersonal.Ciudad;
import pkgClasesDeNegocio.AdministracionDePersonal.Domicilio;
import pkgClasesDeNegocio.AdministracionDePersonal.Pais;
import pkgClasesDeNegocio.AdministracionDePersonal.Provincia;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgClasesDeNegocio.CuentaCorriente.TipoProveedor;
import pkgClasesDeNegocio.Ventas.CondicionIva;
import pkgRecursosDeSoporte.Mensaje;
import pkgRecursosDeSoporte.pkgLista.Iterador;
import pkgRecursosDeSoporte.pkgLista.Lista;

/**
 *
 * @author Armando
 */
public class GestorRegistrarProveedor {
    private IntermediarioBDRegistrarProveedor intermediario;
    private Proveedor proveedor;
    private PllaRegistrarProveedor plla;
    private int nroCuit;
    private Lista paises;
    private Lista tipoProveedor;
    private Lista productosSegunTipoProveedor;
    private Lista condicionIva;
    private Lista provincia;
    private Lista ciudad;
    private Lista tipoTelefono;
    private Pais paisSeleccionado;
    private Domicilio domicilio;
    private Provincia provinciaSeleccionada;
    private Ciudad ciudadSeleccionada;
    private CondicionIva condicionIvaSeleccionada;
    
        
    public GestorRegistrarProveedor() {
    }

    public void nuevoProveedor(PllaRegistrarProveedor plla){
        this.plla=plla;
        this.paises=new Lista();
        this.tipoProveedor=new Lista();
        this.condicionIva= new Lista();
        this.provincia= new Lista();
        this.ciudad=new Lista();
        this.tipoTelefono=new Lista();
        this.intermediario=new IntermediarioBDRegistrarProveedor();
        proveedor=new Proveedor();
        paisSeleccionado =new Pais();
        domicilio= new Domicilio();
        provinciaSeleccionada=new Provincia();
        ciudadSeleccionada=new Ciudad();
    }

    public void tomarNroCUIT(int nroCuit) {
       this.nroCuit=nroCuit;
       this.validarNroCUIT();
       
       
    }

    public void tomarDomicilio(String nombreCalle, int numeroCalle, String departamento, int numeroPiso, String nombreBarrio) {

        domicilio.setBarrio(nombreBarrio);
        domicilio.setDepartamento(departamento);
        domicilio.setNombreCalle(nombreCalle);
        
        if (numeroCalle!=-1){
            domicilio.setNroCalle(numeroCalle);
        }
        
        if(numeroPiso!=-1){
            domicilio.setNroPiso(numeroPiso);
        }
        
    }

    public void tomarPaisSeleccionado(String pais) {
        
        paisSeleccionado.setNombre(pais);
        
    }

    public void tomarCiudadSeleccionada(String nomCiudad) {
    
     Iterador itCiudad=this.ciudad.crearIterador();
      
     Ciudad ciudadABuscar=new Ciudad();
     
     ciudadABuscar.setNombre(nomCiudad);
     
     this.ciudadSeleccionada=(Ciudad) itCiudad.buscarElemento(ciudadABuscar);   
     
     domicilio.setCiudad(ciudadSeleccionada);
     
     this.SetDomicilioProveedor();
    }

   public void getCiudades(String nomProvincia) {
        
        Provincia provinciaAux=new Provincia();
           
           provinciaAux.setNombre(nomProvincia);
           
            Iterador it=this.provincia.crearIterador();
            Provincia provinciaCondicion=(Provincia) it.buscarElemento(provinciaAux);
            
            if(provinciaCondicion!=null){this.buscarCiudad(provinciaCondicion);} 
            else{this.plla.mostrarCiudadesDomicilio(new Lista());} 
    }
    
  public void getProvincias(String nomPais){
           Pais paisAux=new Pais();
           
           paisAux.setNombre(nomPais);
           
            Iterador it=this.paises.crearIterador();
            Pais paisCondicion=(Pais) it.buscarElemento(paisAux);
            
           if(paisCondicion!=null){this.buscarProvincia(paisCondicion);} 
           else{this.plla.mostrarProvinciaDomicilio(new Lista());} 
        
       }
     
    public void tomarCondicionIva(String nomCondicionIva) {
     Iterador itCondicionIva=this.condicionIva.crearIterador();
      
     CondicionIva condicionIvaABuscar=new CondicionIva();
     
     condicionIvaABuscar.setNombre(nomCondicionIva);
     
     this.condicionIvaSeleccionada=(CondicionIva) itCondicionIva.buscarElemento(condicionIvaABuscar);   
     
     proveedor.setCondicionIva(condicionIvaSeleccionada);
        
    }

    public void tomarConfirmacion(int confirmacion) throws Exception {
    if (confirmacion==0){
        this.validarDatosMinimos();    
    }
    else{
        //this.plla.dispose;
        Mensaje.mensaje_Estandar("No se va a crear el Proveedor si no confirma", "El proveedor no se va a crear", Mensaje.TIPO_INFORMACION);
    }
    }

    public void tomarNroIngresoBruto(int nroIngresoBruto) {
    if (nroIngresoBruto!=-1){
        proveedor.setNroIngresoBruto(nroIngresoBruto);
    }  
    }

    void tomarTelefonos(Lista telefonos) {
        proveedor.setTelefonos(telefonos);
    }
    
   private void SetDomicilioProveedor() {
        proveedor.setDomicilio(domicilio);
    }

    public void tomarProvinciaSeleccionada(String nomProvincia) {
        
     Iterador itProvincia=this.provincia.crearIterador();
      
     Provincia provinciaABuscar=new Provincia();
     
     provinciaABuscar.setNombre(nomProvincia);
     
     this.provinciaSeleccionada=(Provincia) itProvincia.buscarElemento(provinciaABuscar);   
     
     domicilio.setProvincia(provinciaSeleccionada);
    }

   void tomarRazonSocial(String razonSocial) {
        proveedor.setRazonSocial(razonSocial);       
    }
        
    void tomarProvincia(String provincia) {
        Provincia provinciaABuscar=new Provincia();
        provinciaABuscar.setNombre(provincia);
           
        Iterador it=this.provincia.crearIterador();
        Provincia provinciaCondicion=(Provincia) it.buscarElemento(provinciaABuscar);
            
        this.buscarCiudad(provinciaCondicion);
    }

    public void tomarTipoProveedor(Lista tiposDeProveedor) {
      Iterador itTiposDeProveedor=tiposDeProveedor.crearIterador();
      
      Iterador itLstTipoProvedor=this.getTipoProveedor().crearIterador();
      Lista lstResultado=new Lista();
      
      while(itTiposDeProveedor.siguiente()){
      
          String nomTipoProveedor=(String)itTiposDeProveedor.getElementoActual();
          
          TipoProveedor objTipoProveedor=new TipoProveedor();
          objTipoProveedor.setNombre(nomTipoProveedor);
         
          TipoProveedor objEncontrado=(TipoProveedor)itLstTipoProvedor.buscarElemento(objTipoProveedor);
          
          lstResultado.insertarUltimo(objEncontrado);
      
      }
      
      this.proveedor.setTipoProveedor(lstResultado);

    }

    private void buscarCondicionIva() {
        try{ 
        condicionIva=intermediario.getCondicionIva();
        this.plla.mostrarCondicionIva(condicionIva);
        
       }
       catch(Exception e){
        this.plla.mostrarMensajePantalla("Error en Condicion Iva",Mensaje.TIPO_ERROR);
       }
    }

      
    private void buscarPaises() {
       
try{ 
        this.paises=intermediario.getPaises();
        this.plla.mostrarPaisesDomicilio(paises);
        
       }
       catch(Exception e){
        this.plla.mostrarMensajePantalla("Error en PAISES: "+e.getMessage(),Mensaje.TIPO_ERROR);
       }
       
    }
    
    private void buscarProvincia(Pais pais) {
                       
       try{ 
           
        Lista aux=new Lista();   
        
        aux=intermediario.getProvincia(pais);
        
        this.provincia=aux;
        
        this.plla.mostrarProvinciaDomicilio(provincia);
        
       }
       catch(Exception e){
        this.plla.mostrarMensajePantalla("Error en Provincias: "+e.getMessage(),Mensaje.TIPO_ERROR);
       }
       
    }
    
    private void buscarCiudad(Provincia provincia) {
                       
       try{ 
           
        Lista aux=new Lista();   

        aux=intermediario.getCiudad(provincia);
        
        this.ciudad=aux;
        
        //this.plla.mostrarCiudad(ciudad);
        
        this.plla.mostrarCiudadesDomicilio(this.ciudad);

       }
       catch(Exception e){
        this.plla.mostrarMensajePantalla("Error en Ciudades",Mensaje.TIPO_ERROR);
       }
    }
    
   private void buscarTipoProveedor() {
       
       try{ 
        tipoProveedor=intermediario.getTipoProveedor();
        this.plla.mostrarTipoProveedor(getTipoProveedor());

       }
       catch(Exception e){
        this.plla.mostrarMensajePantalla("Error en Tipo de Proveedor",Mensaje.TIPO_ERROR);
       }
       
    }
    
   public void tomarPais(String pais){
    
        Pais paisABuscar=new Pais();
        paisABuscar.setNombre(pais);
           
        Iterador it=this.paises.crearIterador();
        Pais paisCondicion=(Pais) it.buscarElemento(paisABuscar);
            
        this.buscarProvincia(paisCondicion);
        }

    private void buscarTipoTelefono() {
    try{ 
        tipoTelefono=intermediario.getTelefonos();
        this.plla.mostrarTelefonos(tipoTelefono);

       }
       catch(Exception e){
        this.plla.mostrarMensajePantalla("Error en Teléfonos",Mensaje.TIPO_ERROR);
       }
    }
       
   private void validarDatosMinimos() throws Exception {
    if (proveedor.getRazonSocial()!=null && proveedor.getCuit()>0){
        this.crearProveedor();
    }
    else
        Mensaje.mensaje_Estandar("No se han completado los datos mínimos", "Faltan completar datos mínimos", Mensaje.TIPO_ERROR);
    }
        
    private void crearProveedor() throws Exception{
        //Mensaje.mensaje_Estandar("Espere mientras se crea el Proveedor...", "El proveedor se está creando", Mensaje.TIPO_INFORMACION);
        intermediario.crear(this.proveedor);
        
        plla.mostrarMensajeRegistracionExitosa();
    }
    
    private void validarNroCUIT(){
     try{
        
       Lista proveedores=this.intermediario.existeNroCUIT(nroCuit);
       
       if(proveedores.estaVacia()){
        this.buscarTipoProveedor();
        this.buscarPaises();
        this.buscarCondicionIva();
        this.buscarTipoTelefono();
        proveedor.setCuit(nroCuit);
        
        this.plla.habilitarCamposDatos();
        
       }
       else{
        this.plla.mostrarMensajePantalla("Ya existe un proveedor con ese número de CUIT", Mensaje.TIPO_AVISO);
       
       }
       
      }
     catch(Exception e){
         this.plla.mostrarMensajePantalla("Ocurrió un error al intentar verificar Número Cuit",Mensaje.TIPO_ERROR);
     
     }
    }
    
    public void buscarProductosSegunTipoProveedorSeleccionado(String nombreTipoProveedor) {

    
    try{ 
        
        TipoProveedor tipoProveedorABuscar=new TipoProveedor();
        tipoProveedorABuscar.setNombre(nombreTipoProveedor);
        
       TipoProveedor tipoProveedorEncontrado=(TipoProveedor)getTipoProveedor().crearIterador().buscarElemento(tipoProveedorABuscar);
        
        tipoProveedorEncontrado.setProductos(intermediario.getProductosSegunTipoProveedor(tipoProveedorEncontrado));
        
 
       }
       catch(Exception e){
        this.plla.mostrarMensajePantalla("Error en Tipo de Proveedor",Mensaje.TIPO_ERROR);
       }
       
    
    }
    
    public void resetNuevoProveedor(){
    
        this.limpiarListasProductosPorTipoProveedor();
        this.plla.actualizarTabla();
        
        
    
    }
    
    private void limpiarListasProductosPorTipoProveedor(){
       Iterador itTipoProveedor=this.tipoProveedor.crearIterador();
       
       while(itTipoProveedor.siguiente()){
        TipoProveedor tp=(TipoProveedor) itTipoProveedor.getElementoActual();
        tp.setProductos(new Lista());   
        
       }
       
        
        
    
    }

    public Lista getTipoProveedor() {
        return tipoProveedor;
    }
    
} 
