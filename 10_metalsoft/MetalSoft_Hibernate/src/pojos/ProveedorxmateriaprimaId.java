package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA



/**
 * ProveedorxmateriaprimaId generated by hbm2java
 */
public class ProveedorxmateriaprimaId  implements java.io.Serializable {


     private long idmateriaprima;
     private long idproveedor;

    public ProveedorxmateriaprimaId() {
    }

    public ProveedorxmateriaprimaId(long idmateriaprima, long idproveedor) {
       this.idmateriaprima = idmateriaprima;
       this.idproveedor = idproveedor;
    }
   
    public long getIdmateriaprima() {
        return this.idmateriaprima;
    }
    
    public void setIdmateriaprima(long idmateriaprima) {
        this.idmateriaprima = idmateriaprima;
    }
    public long getIdproveedor() {
        return this.idproveedor;
    }
    
    public void setIdproveedor(long idproveedor) {
        this.idproveedor = idproveedor;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ProveedorxmateriaprimaId) ) return false;
		 ProveedorxmateriaprimaId castOther = ( ProveedorxmateriaprimaId ) other; 
         
		 return (this.getIdmateriaprima()==castOther.getIdmateriaprima())
 && (this.getIdproveedor()==castOther.getIdproveedor());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getIdmateriaprima();
         result = 37 * result + (int) this.getIdproveedor();
         return result;
   }   


}

