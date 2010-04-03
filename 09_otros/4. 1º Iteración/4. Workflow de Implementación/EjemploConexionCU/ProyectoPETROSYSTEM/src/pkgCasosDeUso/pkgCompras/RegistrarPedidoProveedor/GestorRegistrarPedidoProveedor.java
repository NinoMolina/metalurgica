/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCasosDeUso.pkgCompras.RegistrarPedidoProveedor;

import java.util.Date;
import pkgClasesDeNegocio.AdministracionDePersonal.DatosEmpresa;
import pkgClasesDeNegocio.AdministracionDePersonal.Empleado;
import pkgClasesDeNegocio.AdministracionDePersonal.Estado;
import pkgClasesDeNegocio.Compras.DetallePedidoAProveedor;
import pkgClasesDeNegocio.Compras.PedidoAProveedor;
import pkgClasesDeNegocio.Compras.Producto;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgClasesDeNegocio.CuentaCorriente.RepresentanteDeProveedor;
import pkgRecursosDeSoporte.Mensaje;
import pkgRecursosDeSoporte.ValText;
import pkgRecursosDeSoporte.pkgLista.Iterador;
import pkgRecursosDeSoporte.pkgLista.Lista;
/**
 *
 * @author Armando
 */
public class GestorRegistrarPedidoProveedor {

    private MediadorCURegistrarPedidoProveedor mediadorCU=new MediadorCURegistrarPedidoProveedor();
    //Atributo para prueba!!!!
    private Lista representantesPrueba=new Lista();
    
    private PllaPrincipalRegistrarPedido pllaPrincipal;
    private PllaDetallePedido pllaDetalle;
    private PedidoAProveedor pedidoAProveedor;
    private IntermediarioBDRegistrarPedidoProveedor intermediario=new IntermediarioBDRegistrarPedidoProveedor();
    private ImpresorPedidoProveedor impresorPedido=new ImpresorPedidoProveedor();
    private Lista proveedores;
    private Proveedor proveedor;
    private Lista representantes;
    private Lista productosDeProveedor;
    private Date fecha;
    
    private Lista detallesPedido;
   
    
    public void cancelarPedidoProveedor(){
     

        setPedidoAProveedor(new PedidoAProveedor());
       
        setProveedor(new Proveedor());
        
        
        setRepresentantes(new Lista());
        
       
        setFecha(new Date());
        
       
        setProductosDeProveedor(new Lista());
        
        setDetallesPedido(new Lista());
        
    
    }
    
    public void nuevoPedidoProveedor(PllaPrincipalRegistrarPedido plla) {
        this.pllaPrincipal=plla;
        setPedidoAProveedor(new PedidoAProveedor());
        this.setProveedores(new Lista());
        this.setProveedor(new Proveedor());
        this.setRepresentantes(new Lista());
        this.setFecha(new Date());
        this.setDetallesPedido(new Lista());
        this.mediadorCU.setGestor(this);
        
        this.buscarProveedores();
        this.pllaPrincipal.mostrarProveedores(getProveedores());
   
    }
    
    

    public void tomarProveedor(String razonSocial) {
        Proveedor pABuscar=new Proveedor();
        pABuscar.setRazonSocial(razonSocial);
        
       try{ 
        this.setProveedor(this.intermediario.getProveedor(pABuscar));
        this.buscarRepresentantesProveedor();
        this.tomarFechaHoraSistema();
        this.pllaPrincipal.mostrarRepresentantesProveedor(getRepresentantes());
        this.pllaPrincipal.mostrarFechaHoraSistema(getFecha());
        
        
       }
       catch(Exception e){
        this.pllaPrincipal.mostrarMensajePantalla("Error al intentar acceder a los datos del proveedor",Mensaje.TIPO_ERROR);
       }
    }

    public void buscarProductosProveedor() {
        
        try{
         
         Lista productos=this.intermediario.getProductosDeProveedor(getProveedor());
         
         if(productos.estaVacia()==false){
                getProveedor().setProductos(productos);
          this.setProductosDeProveedor(getProveedor().getProductos());
          this.pllaPrincipal.mostrarProductos(productos);
         }
         else{
          this.pllaPrincipal.mostrarMensajePantalla("No existen productos cargados para el proveedor seleccionado",Mensaje.TIPO_AVISO);  
         }
   
        }
        catch(Exception e){
          
           this.pllaPrincipal.mostrarMensajePantalla("Error al intentar acceder a los productos del proveedor",Mensaje.TIPO_ERROR);  
        }
        
        
        
        
    }
    
    public Producto getProducto(String nomProducto){
     Producto productoABuscar=new Producto();
    
     productoABuscar.setNombre(nomProducto);
     Iterador itProductos=this.getProductosDeProveedor().crearIterador();
     
     Producto productoEncontrado=(Producto) itProductos.buscarElemento(productoABuscar);
         
     return productoEncontrado;
    }

    public void tomarFechaHoraRecepcion(Date fRecepcion, String hRecepcion) {
        getPedidoAProveedor().setFechaEstimadaEntrega(fRecepcion);
        getPedidoAProveedor().setHoraEstimadaEntrega(hRecepcion);
        this.pllaPrincipal.solicitarConfirmacion();
        
    }

    public void tomarRepresentanteProveedor(String nombreRepre, String appRepre) {
        
        RepresentanteDeProveedor representante=new RepresentanteDeProveedor();
        
        if(nombreRepre!=null && this.getRepresentantes().estaVacia()==false){
         representante.setNombre(nombreRepre);
         representante.setApellido(appRepre);
         
         RepresentanteDeProveedor repreEncontrado=(RepresentanteDeProveedor)this.getRepresentantes().crearIterador().buscarElemento(representante);
            
         if(repreEncontrado!=null){
                this.getPedidoAProveedor().setRepresentanteProveedor(repreEncontrado);
         }  
        }
        else{
           representante.addAttribute("id_RepresentanteDeProveedor",null); 
            this.getPedidoAProveedor().setRepresentanteProveedor(representante);
        }
        
        
    }

    public void tomarConfirmacion(boolean b) {
        
       if(b){
         //Proceder a crear el pedido. No olvidar de setear la lista de detalles al Pedido
         if(this.validarDatosMinimos()){
          
             this.crearPedidoProveedor();
             this.pllaPrincipal.solicitarConfirmacionImpresion();
             
              
             this.pllaPrincipal.mostrarMensajePantalla("Pedido Registrado Exitosamente",Mensaje.TIPO_AVISO);
             this.pllaPrincipal.cerrarPantalla();
         
         }
         else{
          this.pllaPrincipal.solicitarIngresoDatosMinimos();
         }
           
       }
       else{
        this.cancelarCU();
       }
        
        
    }
    
    public void tomarConfirmacionImpresion(boolean confirmacion){
     try{
        
        if(confirmacion){
         this.imprimirPedido();
        } 
     }
     catch(Exception ex){
      Mensaje.mensaje_Estandar("Error al imprimir pedido","Pedido",Mensaje.TIPO_ERROR);
     }
        
    
    }

    public void llamarCURegistrarRepresentanteProveedor() {
        this.setModalPantallaPpal(false);
        mediadorCU.llamarCURegistrarRepresentanteProveedor();   
        
        
    }

    public void llamarCURegistrarRepresentanteProveedor(String nomProveedor) {
      
       Proveedor pAux=this.getProveedor(nomProveedor);
       
       if(pAux!=null){
        this.setModalPantallaPpal(false);   
        mediadorCU.llamarCURegistrarRepresentanteProveedor(pAux);
       }
       else{
        this.pllaPrincipal.mostrarMensajePantalla("Proveedor no encontrado", Mensaje.TIPO_AVISO);
       } 
    }
    
    private Proveedor getProveedor(String nomProveedor){
       
       Iterador itP=this.proveedores.crearIterador();
       
       while(itP.siguiente()){
        Proveedor p=(Proveedor) itP.getElementoActual();
         if(p.getRazonSocial().compareTo(nomProveedor)==0){
          return p;
         }
        
       }
       
       return null;
        
    }
    
    private void setModalPantallaPpal(boolean estado){
     this.pllaPrincipal.setModal(estado);
    }
    
    private void imprimirPedido(){
        
      DatosEmpresa datosEmpresa=this.getDatosEmpresa(); 
        
      impresorPedido.imprimirPedido(pedidoAProveedor,datosEmpresa);
    }
    
    private DatosEmpresa getDatosEmpresa(){
      
       DatosEmpresa datosEmpresa=null; 
        
       try{
        datosEmpresa=intermediario.getDatosEmpresa();
                
        datosEmpresa.setDomicilio(intermediario.getDomicilioEmpresa(datosEmpresa));
          
      }
      catch(Exception e){
        pllaPrincipal.mostrarMensajePantalla("Error al intentar acceder a los datos de la empresa",Mensaje.TIPO_ERROR);
      }
       
      return datosEmpresa; 
       
    }

    private void asignarEmpleado() {
         Empleado empleado=new Empleado();
         empleado.addAttribute("id_Empleado",new Integer(1));
         getPedidoAProveedor().setEmpleado(empleado);
    }

    
    private void buscarProveedores(){
     try{   
        
            this.setProveedores(this.intermediario.getProveedores());
     
     }
     catch(Exception e){
       pllaPrincipal.mostrarMensajePantalla("Error al intentar mostrar los proveedores",Mensaje.TIPO_ERROR);
     }
        
  
    }
    
    private void buscarRepresentantesProveedor(){
      
        try{
         Lista representantes=this.intermediario.getRepresentantesDeProveedor(getProveedor());
         
         if(representantes.estaVacia()==false){
                getProveedor().setRepresentantes(representantes);
         }
         else{
                getProveedor().setRepresentantes(new Lista()); 
         }
         
            this.setRepresentantes(getProveedor().getRepresentantes());
         
         
        
        }
        catch(Exception e){
         pllaPrincipal.mostrarMensajePantalla("Error al intentar mostrar los representantes",Mensaje.TIPO_ERROR);
        }
        
    }

    private void cancelarCU() {
        this.pllaPrincipal.mostrarMensajePantalla("El Registro del Pedido ha sido cancelado",Mensaje.TIPO_AVISO);
    }

    private void crearPedidoProveedor() {
      try{  
            this.getPedidoAProveedor().setDetallesPedidoAProveedor(getDetallesPedido());
            this.tomarFechaHoraSistema();
            this.getPedidoAProveedor().setFechaRealizacion(fecha);
            this.getPedidoAProveedor().setHoraRealizacion(ValText.getFormatStringHora(getFecha()));
            this.getPedidoAProveedor().setProveedor(getProveedor());
        
        Estado estado=new Estado();
        estado.setNombre("Pendiente de Recepcion");
        
            this.getPedidoAProveedor().setEstado(estado);
        
        this.setEstadoPedido();
        this.asignarEmpleado();
        
        this.intermediario.crearPedidoProvedor(getPedidoAProveedor());
           
      }
      catch(Exception e){
       this.pllaPrincipal.mostrarMensajePantalla("Error al intentar crear el Pedido",Mensaje.TIPO_ERROR);
      }
        
    }
    
    public void setMontoTotalPedido(double montoTotalPedido){
        this.getPedidoAProveedor().setMontoTotal(montoTotalPedido);
    }

    private void setEstadoPedido() {
       try{ 
        
        this.intermediario.setEstadoPedido(getPedidoAProveedor());
       }
       catch(Exception e){
        this.pllaPrincipal.mostrarMensajePantalla("Error al intentar setear el estado al Pedido",Mensaje.TIPO_ERROR);
       }
        
    }

    private void setPedidoAProveedor(PedidoAProveedor pedidoAProveedor) {
        this.pedidoAProveedor=pedidoAProveedor;
    }
    
    private void tomarFechaHoraSistema(){
        getFecha().setTime(System.currentTimeMillis());
    }

    public PllaDetallePedido getPllaDetalle() {
        return pllaDetalle;
    }

    public void setPllaDetalle(PllaDetallePedido pllaDetalle) {
        this.pllaDetalle = pllaDetalle;
    }
    
    public void agregarDetallePedido(String nomProducto,double cantidad){
      
        Producto proABuscar=new Producto();
        proABuscar.setNombre(nomProducto);
        
        Producto proEncontrado=(Producto) this.getProductosDeProveedor().crearIterador().buscarElemento(proABuscar);
        
        DetallePedidoAProveedor detalle=new DetallePedidoAProveedor();
        
        detalle.setProducto(proEncontrado);
        detalle.setCantidad(cantidad);
        detalle.setPrecioActual(proEncontrado.getPrecioCosto());
        
        Estado estado=new Estado();
        estado.setNombre("Pendiente de recepcion");
        
        //Buscar luego el estado, paa traer su id
        
        detalle.setEstado(estado);
        
        this.getDetallesPedido().insertarUltimo(detalle);
        
        
        
    }
    
    public void nuevosDetalles(){
        this.setDetallesPedido(null);
        setDetallesPedido(new Lista());
    }
    
    public boolean existenDetalles(){
    
        if(this.getDetallesPedido().estaVacia()){
         return false;
        }
        
        return true;
        
        
    }

    private boolean validarDatosMinimos() {
        return this.existenDetalles();
    }

    public Lista getDetallesPedido() {
        return detallesPedido;
    }

    public void setDetallesPedido(Lista detallesPedido) {
        this.detallesPedido = detallesPedido;
    }

    public PedidoAProveedor getPedidoAProveedor() {
        return pedidoAProveedor;
    }

    
    private Lista getProveedores() {
        return proveedores;
    }

    private void setProveedores(Lista proveedores) {
        this.proveedores = proveedores;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    private void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    private Lista getRepresentantes() {
        return representantes;
    }

    private void setRepresentantes(Lista representantes) {
        this.representantes = representantes;
    }

    private Lista getProductosDeProveedor() {
        return productosDeProveedor;
    }

    private void setProductosDeProveedor(Lista productosDeProveedor) {
        this.productosDeProveedor = productosDeProveedor;
    }

    private Date getFecha() {
        return fecha;
    }

    private void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void actualizarListaRepresentantesPrueba(Lista representantesPrueba) {
        this.representantesPrueba = representantesPrueba;
    }
    
  
    public void finEjecucionCURegRepresentanteProveedor(Proveedor p){
     //Caso en que al llamar al otro CU, se le especifico el proveedor de los representantes
        this.setModalPantallaPpal(true);
        if(p!=null){
         Iterador it=p.getRepresentantes().crearIterador();
         this.pllaPrincipal.mostrarMensajePantalla("Proveedor seleccionado:"+p.getRazonSocial(),Mensaje.TIPO_AVISO);
         
         while(it.siguiente()){
          RepresentanteDeProveedor r=(RepresentanteDeProveedor)it.getElementoActual();
          
           pllaPrincipal.mostrarMensajePantalla(r.getNombre()+" "+r.getApellido(),Mensaje.TIPO_AVISO);
         
         }
      }
        else{
         if(!representantesPrueba.estaVacia()){
         Iterador it=this.representantesPrueba.crearIterador();
         
         while(it.siguiente()){
          RepresentanteDeProveedor r=(RepresentanteDeProveedor)it.getElementoActual();
          
           pllaPrincipal.mostrarMensajePantalla(r.getNombre()+" "+r.getApellido(),Mensaje.TIPO_AVISO);
         
         }  
        }
        
        }
        
    }

}
