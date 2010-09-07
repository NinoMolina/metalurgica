//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\trabajostercerizados\\EmpresaMetalurgica.java

package metalsoft.negocio.trabajostercerizados;

import metalsoft.negocio.compras.PersonaJuridica;
import metalsoft.negocio.compras.Reclamo;

public class EmpresaMetalurgica extends PersonaJuridica 
{
   private long nroEmpresaMetalugica;

   
   /**
    * @roseuid 4C27ED1A0346
    */
   public EmpresaMetalurgica() 
   {
    
   }

    public long getNroEmpresaMetalugica() {
        return nroEmpresaMetalugica;
    }

    public void setNroEmpresaMetalugica(long nroEmpresaMetalugica) {
        this.nroEmpresaMetalugica = nroEmpresaMetalugica;
    }
   
  
}
