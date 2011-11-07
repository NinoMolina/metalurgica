//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\ventas\\EstadoPedido.java

package metalsoft.negocio.ventas;


public abstract class EstadoPedido
{
   private int nombre;
   private int descripcion;
   

   public EstadoPedido getFirstEstado()
   {
       return new EstadoPedidoGenerado();
   }

   public abstract EstadoPedido goNext();
   
   /**
    * @roseuid 4C1FA5C500AD
    */
   public void esEstadoPedidoConfirmado() 
   {
    
   }
   
   /**
    * @roseuid 4C205FBB036F
    */
   public void esPedidoPlanificado() 
   {
    
   }
}
