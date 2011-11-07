/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.CodigodebarraJpaController;
import metalsoft.negocio.produccion.CodigoDeBarra;

/**
 *
 * @author Nino
 */
public class ViewMateriaPrimaXPiezaPresupuesto {

    private long nroproducto;
    private String nombreproducto;
    private int cantproducto;
    private String nombrepieza;
    private int cantpieza;
    private String nombremateriaprima;
    private int cantmateriaprima;
    private double preciomateriaprima;
    private int canttotal;
    private double preciototal;
    private long idpresupuesto;
    private long iddetallepresupuesto;
    private long iddetalleproductopresupuesto;
    private long idproducto;
    private long idpieza;
    private long idmateriaprima,idproveedor;

    public ViewMateriaPrimaXPiezaPresupuesto() {
    }

    public long getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(long idproveedor) {
        this.idproveedor = idproveedor;
    }

    public double getPreciomateriaprima() {
        return preciomateriaprima;
    }

    public void setPreciomateriaprima(double preciomateriaprima) {
        this.preciomateriaprima = preciomateriaprima;
    }

    public double getPreciototal() {
        return preciototal;
    }

    public void setPreciototal(double preciototal) {
        this.preciototal = preciototal;
    }

    public int getCantmateriaprima() {
        return cantmateriaprima;
    }

    public void setCantmateriaprima(int cantmateriaprima) {
        this.cantmateriaprima = cantmateriaprima;
    }

    public int getCantpieza() {
        return cantpieza;
    }

    public void setCantpieza(int cantpieza) {
        this.cantpieza = cantpieza;
    }

    public int getCantproducto() {
        return cantproducto;
    }

    public void setCantproducto(int cantproducto) {
        this.cantproducto = cantproducto;
    }

    public int getCanttotal() {
        return canttotal;
    }

    public void setCanttotal(int canttotal) {
        this.canttotal = canttotal;
    }

    public long getIddetallepresupuesto() {
        return iddetallepresupuesto;
    }

    public void setIddetallepresupuesto(long iddetallepresupuesto) {
        this.iddetallepresupuesto = iddetallepresupuesto;
    }

    public long getIddetalleproductopresupuesto() {
        return iddetalleproductopresupuesto;
    }

    public void setIddetalleproductopresupuesto(long iddetalleproductopresupuesto) {
        this.iddetalleproductopresupuesto = iddetalleproductopresupuesto;
    }

    public long getIdmateriaprima() {
        return idmateriaprima;
    }

    public void setIdmateriaprima(long idmateriaprima) {
        this.idmateriaprima = idmateriaprima;
    }

    public long getIdpieza() {
        return idpieza;
    }

    public void setIdpieza(long idpieza) {
        this.idpieza = idpieza;
    }

    public long getIdpresupuesto() {
        return idpresupuesto;
    }

    public void setIdpresupuesto(long idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

    public long getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(long idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombremateriaprima() {
        return nombremateriaprima;
    }

    public void setNombremateriaprima(String nombremateriaprima) {
        this.nombremateriaprima = nombremateriaprima;
    }

    public String getNombrepieza() {
        return nombrepieza;
    }

    public void setNombrepieza(String nombrepieza) {
        this.nombrepieza = nombrepieza;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public long getNroproducto() {
        return nroproducto;
    }

    public void setNroproducto(long nroproducto) {
        this.nroproducto = nroproducto;
    }

    public metalsoft.datos.jpa.entity.Codigodebarra getCodigoByID(long idCodigo) {
       CodigodebarraJpaController controller=new CodigodebarraJpaController(JpaUtil.getEntityManagerFactory());
        return controller.findCodigodebarra(idCodigo);
    }

    
}
