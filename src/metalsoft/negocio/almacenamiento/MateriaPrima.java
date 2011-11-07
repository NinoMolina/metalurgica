//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\almacenamiento\\MateriaPrima.java

package metalsoft.negocio.almacenamiento;

import java.sql.Connection;
import java.util.Date;
import metalsoft.negocio.access.AccessMateriaPrima;
import metalsoft.negocio.produccion.TipoMaterial;
import metalsoft.negocio.produccion.CodigoDeBarra;
import metalsoft.negocio.compras.Proveedor;

public class MateriaPrima 
{
   private int codProducto;
   private long nroMateriaPrima;
   private String nombre;
   private Proveedor proveedor;
   private double precio;
   private Date fechaAlta;
   private Date fechaBaja;
   private CodigoDeBarra codBarra;
   private TipoMaterial tipo;
   private Double largo;
   private Double ancho;
   private Double alto;
   private String descripcion;
   private int stock;
   private String unidadDeMedida;
   public TipoMaterial theTipoMaterial;
   public CodigoDeBarra theCodigoDeBarra;
   public Proveedor theProveedor[];


   
   /**
    * @roseuid 4C27ED230373
    */
   public MateriaPrima() 
   {
    
   }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public long getNroMateriaPrima() {
        return nroMateriaPrima;
    }

    public void setNroMateriaPrima(long nroMateriaPrima) {
        this.nroMateriaPrima = nroMateriaPrima;
    }
   
   /**
    * @roseuid 4BC24DD1004D
    */
   public void crear() 
   {
    
   }
   
   /**
    * @roseuid 4BC24DD40132
    */
   public void conocerCalidad() 
   {
    
   }
   
   /**
    * @roseuid 4BC24DDF017E
    */
   public void conocerProveedor() 
   {
    
   }
   
   /**
    * @roseuid 4C17F4670345
    */
   public void conocerTipoMaterial() 
   {
    
   }
   
   /**
    * @roseuid 4C17F48201BD
    */
   public void conocerCodigoDeBarra() 
   {
    
   }

    public long guardarMateriaPrima(MateriaPrima materiaPrima, long idTipoMaterial, long idUnidadMedida, long idCodBarra, Connection cn) {
        long result=-1;
       result=AccessMateriaPrima.insert(materiaPrima, idTipoMaterial, idUnidadMedida, idCodBarra, cn);

       return result;
    }

    public long modificar(MateriaPrima materiaPrima, long idMateriaPrima, long idTipoMaterial, long idUnidadMedida, long idCodBarra, Connection cn) {
        long result=-1;
       result=AccessMateriaPrima.update(materiaPrima, idMateriaPrima, idTipoMaterial, idUnidadMedida, idCodBarra, cn);

       return result;
    }
   
   /**
    * @roseuid 4C1F8DA6013C
    */
   public void tomarCambios() 
   {
    
   }
   
   /**
    * @roseuid 4C1FB38501F2
    */
   public void mostrarProveedores() 
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
    public CodigoDeBarra getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(CodigoDeBarra codBarra) {
        this.codBarra = codBarra;
    }

    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public CodigoDeBarra getTheCodigoDeBarra() {
        return theCodigoDeBarra;
    }

    public void setTheCodigoDeBarra(CodigoDeBarra theCodigoDeBarra) {
        this.theCodigoDeBarra = theCodigoDeBarra;
    }

    public Proveedor[] getTheProveedor() {
        return theProveedor;
    }

    public void setTheProveedor(Proveedor[] theProveedor) {
        this.theProveedor = theProveedor;
    }

    public TipoMaterial getTheTipoMaterial() {
        return theTipoMaterial;
    }

    public void setTheTipoMaterial(TipoMaterial theTipoMaterial) {
        this.theTipoMaterial = theTipoMaterial;
    }

    public TipoMaterial getTipo() {
        return tipo;
    }

    public void setTipo(TipoMaterial tipo) {
        this.tipo = tipo;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }
}
