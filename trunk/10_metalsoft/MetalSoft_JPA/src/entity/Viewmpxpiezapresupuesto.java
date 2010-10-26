/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "viewmpxpiezapresupuesto")
@NamedQueries({
    @NamedQuery(name = "Viewmpxpiezapresupuesto.findAll", query = "SELECT v FROM Viewmpxpiezapresupuesto v"),
    @NamedQuery(name = "Viewmpxpiezapresupuesto.findByNroproducto", query = "SELECT v FROM Viewmpxpiezapresupuesto v WHERE v.nroproducto = :nroproducto"),
    @NamedQuery(name = "Viewmpxpiezapresupuesto.findByNombreproducto", query = "SELECT v FROM Viewmpxpiezapresupuesto v WHERE v.nombreproducto = :nombreproducto"),
    @NamedQuery(name = "Viewmpxpiezapresupuesto.findByCantproducto", query = "SELECT v FROM Viewmpxpiezapresupuesto v WHERE v.cantproducto = :cantproducto"),
    @NamedQuery(name = "Viewmpxpiezapresupuesto.findByNombrepieza", query = "SELECT v FROM Viewmpxpiezapresupuesto v WHERE v.nombrepieza = :nombrepieza"),
    @NamedQuery(name = "Viewmpxpiezapresupuesto.findByCantpieza", query = "SELECT v FROM Viewmpxpiezapresupuesto v WHERE v.cantpieza = :cantpieza"),
    @NamedQuery(name = "Viewmpxpiezapresupuesto.findByNombremateriaprima", query = "SELECT v FROM Viewmpxpiezapresupuesto v WHERE v.nombremateriaprima = :nombremateriaprima"),
    @NamedQuery(name = "Viewmpxpiezapresupuesto.findByPreciomateriaprima", query = "SELECT v FROM Viewmpxpiezapresupuesto v WHERE v.preciomateriaprima = :preciomateriaprima"),
    @NamedQuery(name = "Viewmpxpiezapresupuesto.findByCantmateriaprima", query = "SELECT v FROM Viewmpxpiezapresupuesto v WHERE v.cantmateriaprima = :cantmateriaprima"),
    @NamedQuery(name = "Viewmpxpiezapresupuesto.findByCanttotal", query = "SELECT v FROM Viewmpxpiezapresupuesto v WHERE v.canttotal = :canttotal"),
    @NamedQuery(name = "Viewmpxpiezapresupuesto.findByPreciototal", query = "SELECT v FROM Viewmpxpiezapresupuesto v WHERE v.preciototal = :preciototal"),
    @NamedQuery(name = "Viewmpxpiezapresupuesto.findByIdpresupuesto", query = "SELECT v FROM Viewmpxpiezapresupuesto v WHERE v.idpresupuesto = :idpresupuesto"),
    @NamedQuery(name = "Viewmpxpiezapresupuesto.findByIddetallepresupuesto", query = "SELECT v FROM Viewmpxpiezapresupuesto v WHERE v.iddetallepresupuesto = :iddetallepresupuesto"),
    @NamedQuery(name = "Viewmpxpiezapresupuesto.findByIddetalleproductopresupuesto", query = "SELECT v FROM Viewmpxpiezapresupuesto v WHERE v.iddetalleproductopresupuesto = :iddetalleproductopresupuesto"),
    @NamedQuery(name = "Viewmpxpiezapresupuesto.findByIdproducto", query = "SELECT v FROM Viewmpxpiezapresupuesto v WHERE v.idproducto = :idproducto"),
    @NamedQuery(name = "Viewmpxpiezapresupuesto.findByIdpieza", query = "SELECT v FROM Viewmpxpiezapresupuesto v WHERE v.idpieza = :idpieza"),
    @NamedQuery(name = "Viewmpxpiezapresupuesto.findByIdmateriaprima", query = "SELECT v FROM Viewmpxpiezapresupuesto v WHERE v.idmateriaprima = :idmateriaprima"),
    @NamedQuery(name = "Viewmpxpiezapresupuesto.findByIdproveedor", query = "SELECT v FROM Viewmpxpiezapresupuesto v WHERE v.idproveedor = :idproveedor")})
public class Viewmpxpiezapresupuesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "nroproducto")
    private BigInteger nroproducto;
    @Column(name = "nombreproducto")
    private String nombreproducto;
    @Column(name = "cantproducto")
    private Integer cantproducto;
    @Column(name = "nombrepieza")
    private String nombrepieza;
    @Column(name = "cantpieza")
    private Integer cantpieza;
    @Column(name = "nombremateriaprima")
    private String nombremateriaprima;
    @Column(name = "preciomateriaprima")
    private Double preciomateriaprima;
    @Column(name = "cantmateriaprima")
    private Integer cantmateriaprima;
    @Column(name = "canttotal")
    private Integer canttotal;
    @Column(name = "preciototal")
    private Double preciototal;
    @Column(name = "idpresupuesto")
    private BigInteger idpresupuesto;
    @Column(name = "iddetallepresupuesto")
    private BigInteger iddetallepresupuesto;
    @Column(name = "iddetalleproductopresupuesto")
    private BigInteger iddetalleproductopresupuesto;
    @Column(name = "idproducto")
    private BigInteger idproducto;
    @Column(name = "idpieza")
    private BigInteger idpieza;
    @Column(name = "idmateriaprima")
    private BigInteger idmateriaprima;
    @Column(name = "idproveedor")
    private BigInteger idproveedor;

    public Viewmpxpiezapresupuesto() {
    }

    public BigInteger getNroproducto() {
        return nroproducto;
    }

    public void setNroproducto(BigInteger nroproducto) {
        this.nroproducto = nroproducto;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public Integer getCantproducto() {
        return cantproducto;
    }

    public void setCantproducto(Integer cantproducto) {
        this.cantproducto = cantproducto;
    }

    public String getNombrepieza() {
        return nombrepieza;
    }

    public void setNombrepieza(String nombrepieza) {
        this.nombrepieza = nombrepieza;
    }

    public Integer getCantpieza() {
        return cantpieza;
    }

    public void setCantpieza(Integer cantpieza) {
        this.cantpieza = cantpieza;
    }

    public String getNombremateriaprima() {
        return nombremateriaprima;
    }

    public void setNombremateriaprima(String nombremateriaprima) {
        this.nombremateriaprima = nombremateriaprima;
    }

    public Double getPreciomateriaprima() {
        return preciomateriaprima;
    }

    public void setPreciomateriaprima(Double preciomateriaprima) {
        this.preciomateriaprima = preciomateriaprima;
    }

    public Integer getCantmateriaprima() {
        return cantmateriaprima;
    }

    public void setCantmateriaprima(Integer cantmateriaprima) {
        this.cantmateriaprima = cantmateriaprima;
    }

    public Integer getCanttotal() {
        return canttotal;
    }

    public void setCanttotal(Integer canttotal) {
        this.canttotal = canttotal;
    }

    public Double getPreciototal() {
        return preciototal;
    }

    public void setPreciototal(Double preciototal) {
        this.preciototal = preciototal;
    }

    public BigInteger getIdpresupuesto() {
        return idpresupuesto;
    }

    public void setIdpresupuesto(BigInteger idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

    public BigInteger getIddetallepresupuesto() {
        return iddetallepresupuesto;
    }

    public void setIddetallepresupuesto(BigInteger iddetallepresupuesto) {
        this.iddetallepresupuesto = iddetallepresupuesto;
    }

    public BigInteger getIddetalleproductopresupuesto() {
        return iddetalleproductopresupuesto;
    }

    public void setIddetalleproductopresupuesto(BigInteger iddetalleproductopresupuesto) {
        this.iddetalleproductopresupuesto = iddetalleproductopresupuesto;
    }

    public BigInteger getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(BigInteger idproducto) {
        this.idproducto = idproducto;
    }

    public BigInteger getIdpieza() {
        return idpieza;
    }

    public void setIdpieza(BigInteger idpieza) {
        this.idpieza = idpieza;
    }

    public BigInteger getIdmateriaprima() {
        return idmateriaprima;
    }

    public void setIdmateriaprima(BigInteger idmateriaprima) {
        this.idmateriaprima = idmateriaprima;
    }

    public BigInteger getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(BigInteger idproveedor) {
        this.idproveedor = idproveedor;
    }

}
