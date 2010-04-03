/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCasosDeUso.pkgCompras.RegistrarPedidoProveedor;

import pkgClasesDeNegocio.Compras.PedidoAProveedor;
import pkgRecursosDeSoporte.pkgImpresion.Impresor;
import pkgRecursosDeSoporte.pkgImpresion.pkgObjImpresion.*;
import pkgRecursosDeSoporte.ValText;
import java.awt.*;
import pkgClasesDeNegocio.AdministracionDePersonal.DatosEmpresa;
import pkgClasesDeNegocio.Compras.DetallePedidoAProveedor;
import pkgRecursosDeSoporte.pkgLista.Iterador;

/**
 *
 * @author Armando
 */
public class ImpresorPedidoProveedor {
    
    private Impresor impresor=new Impresor();
    
    public ImpresorPedidoProveedor(){
    }
    
    
    public void imprimirPedido(PedidoAProveedor pedido,DatosEmpresa datosEmpresa){
           
      confeccionarFormularioPedido(pedido,datosEmpresa);  
        
      impresor.imprimir();
      impresor.finalizar();
       
    }
    
    
    
   private void confeccionarFormularioPedido(PedidoAProveedor pedido,DatosEmpresa dEmpresa){
     //Datos de la Empresa
      impresor.setFont(new Font("SansSerif",Font.BOLD,12 ) );
      
      //long vIntCuit=(long)dEmpresa.getCuit();
      
      String vCuit=ValText.getStringLong(dEmpresa.getCuit());
      String[] cuitAux=ValText.particionarNroCuit(vCuit); 
      String cuit=cuitAux[0]+"-"+cuitAux[1]+"-"+cuitAux[2];
      
      impresor.printCadena(dEmpresa.getRazonSocial()+"-CUIT:"+cuit,new Point(180,65));
      
      String domicilio=dEmpresa.getDomicilio().getNombreCalle()+" "+dEmpresa.getDomicilio().getNroCalle()+"- "+dEmpresa.getDomicilio().getCiudad().getNombre()+".";
      
      impresor.printCadena(domicilio,new Point(160,80));
      
      //Orden de Compra
      impresor.printCadena("ORDEN DE COMPRA- N° "+ValText.getInt_Integer(pedido.getAttribute("id_PedidoAProveedor")),new Point(200,110));
      
      //Proveedor
      impresor.printCadena("Proveedor:",new Point(50,145));
      impresor.printCadena("Representante de Proveedor:",new Point(50,160));
   
      //Fechas 
      impresor.printCadena("Fecha Pedido:",new Point(50,190));
      impresor.printCadena("Fecha Recepción:",new Point(220,190));
     
      //Datos
      impresor.setFont(new Font("SansSerif",Font.PLAIN,12 ));
    
      impresor.printCadena(pedido.getProveedor().getRazonSocial(),new Point(115,145));
      impresor.printCadena(pedido.getRepresentanteProveedor().getApellido()+" "+pedido.getRepresentanteProveedor().getNombre(),new Point(220,160));
      impresor.printCadena(ValText.getFormatStringFecha(pedido.getFechaRealizacion()),new Point(140,190));
      impresor.printCadena(ValText.getFormatStringFecha(pedido.getFechaEstimadaEntrega()),new Point(330,190));
    
     //Título Detalle de Pedido
      impresor.printCadena("Detalle de productos solicitados",new Point(50,220));
     
      impresor.setFont( new Font("SansSerif",Font.PLAIN,10 ) );
    
      //Encabezado detalle
     
      impresor.printTextoRectangulo(new Point(50,230),30,15,"N°");
    
      impresor.printTextoRectangulo(new Point(80,230),170,15,"Producto");//Producto
      
      impresor.printTextoRectangulo(new Point(250,230),70,15,"Unidad");//Unidad
    
      impresor.printTextoRectangulo(new Point(320,230),70,15,"Cantidad");//Cantidad
     
      impresor.printTextoRectangulo(new Point(390,230),70,15,"Precio Unit.");//Precio Unitario
    
      impresor.printTextoRectangulo(new Point(460,230),70,15,"Precio Total");//Precio Total
      
      //Cuerpo del detalle
      
      Iterador itDetalle=pedido.getDetallesPedidoAProveedor().crearIterador();
      
      int y=245;
      int nro=1;
      while(itDetalle.siguiente()){
         
          DetallePedidoAProveedor detalle=(DetallePedidoAProveedor) itDetalle.getElementoActual();
          
          impresor.printTextoRectangulo(new Point(50,y),30,15,ValText.getStringInt(nro));//N°  
          impresor.printTextoRectangulo(new Point(80,y),170,15,detalle.getProducto().getNombre());//Producto
          impresor.printTextoRectangulo(new Point(250,y),70,15,detalle.getProducto().getUnidad());//Unidad
          impresor.printTextoRectangulo(new Point(320,y),70,15,ValText.getStringDouble(detalle.getCantidad()));//Cantidad
          impresor.printTextoRectangulo(new Point(390,y),70,15,ValText.getStringDouble(detalle.getPrecioActual()));//Precio Unitario      
          impresor.printTextoRectangulo(new Point(460,y),70,15,ValText.getStringDouble(detalle.getPrecioTotal()));//Precio Total
          
          nro++;
          y=y+15;   
      }
      
     //Imprime el titulo Costo Total
     impresor.setFont(new Font("SansSerif",Font.BOLD,10 ));

     impresor.printCadena("Costo Total",new Point(395,y+15));//Costo Total
     
     //Imprime la celda del costo total del pedido
     impresor.printTextoRectangulo(new Point(460,y),70,15,ValText.getStringDouble(pedido.getMontoTotal()));//Celda Costo Total
    
     //Imprime "Elaborado por:"
     impresor.printCadena("Elaborado por:____________",new Point(50,y+60));
    
     //Imprime "Autorizado por:"
     impresor.printCadena("Autorizado por:____________",new Point(230,y+60));
    
     //Imprime "Recibido por:"
     impresor.printCadena("Recibido por:____________",new Point(400,y+60));
  
     impresor.printRectangulo(new Point(40,40),500,y+60);//Borde del pedido
    
   }
    
}
