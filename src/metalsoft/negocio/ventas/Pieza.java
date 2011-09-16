//Source file: D:\\Mis documentos\\facultad\\5to a�o\\Proyecto Final\\Repositorio\\10_metalsoft\\rational\\metalsoft\\sistema\\ventas\\Pieza.java

package metalsoft.negocio.ventas;

import metalsoft.datos.dbobject.MatrizDB;
import metalsoft.negocio.almacenamiento.MateriaPrima;
import metalsoft.negocio.produccion.EjecucionEtapaDeProduccion;
import metalsoft.negocio.produccion.PiezaReal;
import metalsoft.negocio.produccion.TipoMaterial;


public class Pieza 
{
   private String nombre;
   private PiezaReal item;
   private TipoMaterial tipoMaterial;
   private Double alto;
   private Double ancho;
   private Double largo;
   private MateriaPrima materia;
   private MatrizDB matriz;

   public MatrizDB theMatriz;
   public PiezaReal thePiezaReal[];
   public TipoMaterial theTipoMaterial;
   public EtapaDeProduccion theEtapaDeProduccion[];
   public EjecucionEtapaDeProduccion theEjecucionEtapaDeProduccion[];
   public MateriaPrima theMateriaPrima;


   /**
    * @roseuid 4C27E32B00CA
    */
   public Pieza() 
   {
    
   }

    public Double getAlto() {
        return alto;
    }

    public void setAlto(Double alto) {
        this.alto = alto;
    }

    public Double getAncho() {
        return ancho;
    }

    public void setAncho(Double ancho) {
        this.ancho = ancho;
    }

    public Double getLargo() {
        return largo;
    }

    public void setLargo(Double largo) {
        this.largo = largo;
    }

    public TipoMaterial getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(TipoMaterial tipo) {
        this.tipoMaterial = tipo;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
   /**
    * @roseuid 4BC24C4D0244
    */
   public void crear() 
   {
    
   }
   
   /**
    * @roseuid 4BC24C57010C
    */
   public void conocerEtapaProduccion() 
   {
    
   }
   
   /**
    * @roseuid 4BC24C650115
    */
   public void conocerEtapaProduccionReal() 
   {
    
   }
   
   /**
    * @roseuid 4C1802D00105
    */
   public void conocerMatriz() 
   {
    
   }
   
   /**
    * @roseuid 4C1802DC0148
    */
   public void conocerTipoMaterial() 
   {
    
   }
   
   /**
    * @roseuid 4C1802E802AE
    */
   public void conocerPiezaReal() 
   {
    
   }
   
   /**
    * @roseuid 4C1803510273
    */
   public void conocerMateriaPrima() 
   {
    
   }
   
   /**
    * @roseuid 4C1FDC91035B
    */
   public void tomarCambios() 
   {
    
   }
   public PiezaReal getItem() {
        return item;
    }

    public void setItem(PiezaReal item) {
        this.item = item;
    }

    public MateriaPrima getMateria() {
        return materia;
    }

    public void setMateria(MateriaPrima materia) {
        this.materia = materia;
    }

    public MatrizDB getMatriz() {
        return matriz;
    }

    public void setMatriz(MatrizDB matriz) {
        this.matriz = matriz;
    }   
}
