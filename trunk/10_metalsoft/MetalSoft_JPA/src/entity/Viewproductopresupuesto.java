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
@Table(name = "viewproductopresupuesto")
@NamedQueries({
    @NamedQuery(name = "Viewproductopresupuesto.findAll", query = "SELECT v FROM Viewproductopresupuesto v"),
    @NamedQuery(name = "Viewproductopresupuesto.findByCantidadproducto", query = "SELECT v FROM Viewproductopresupuesto v WHERE v.cantidadproducto = :cantidadproducto"),
    @NamedQuery(name = "Viewproductopresupuesto.findByNombreproducto", query = "SELECT v FROM Viewproductopresupuesto v WHERE v.nombreproducto = :nombreproducto"),
    @NamedQuery(name = "Viewproductopresupuesto.findByPreciounitario", query = "SELECT v FROM Viewproductopresupuesto v WHERE v.preciounitario = :preciounitario"),
    @NamedQuery(name = "Viewproductopresupuesto.findByImporte", query = "SELECT v FROM Viewproductopresupuesto v WHERE v.importe = :importe"),
    @NamedQuery(name = "Viewproductopresupuesto.findByIdpedido", query = "SELECT v FROM Viewproductopresupuesto v WHERE v.idpedido = :idpedido"),
    @NamedQuery(name = "Viewproductopresupuesto.findByIdpresupuesto", query = "SELECT v FROM Viewproductopresupuesto v WHERE v.idpresupuesto = :idpresupuesto"),
    @NamedQuery(name = "Viewproductopresupuesto.findByIddetallepresupuesto", query = "SELECT v FROM Viewproductopresupuesto v WHERE v.iddetallepresupuesto = :iddetallepresupuesto"),
    @NamedQuery(name = "Viewproductopresupuesto.findByIdproducto", query = "SELECT v FROM Viewproductopresupuesto v WHERE v.idproducto = :idproducto")})
public class Viewproductopresupuesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "cantidadproducto")
    private Integer cantidadproducto;
    @Column(name = "nombreproducto")
    private String nombreproducto;
    @Column(name = "preciounitario")
    private Double preciounitario;
    @Column(name = "importe")
    private Double importe;
    @Column(name = "idpedido")
    private BigInteger idpedido;
    @Column(name = "idpresupuesto")
    private BigInteger idpresupuesto;
    @Column(name = "iddetallepresupuesto")
    private BigInteger iddetallepresupuesto;
    @Column(name = "idproducto")
    private BigInteger idproducto;

    public Viewproductopresupuesto() {
    }

    public Integer getCantidadproducto() {
        return cantidadproducto;
    }

    public void setCantidadproducto(Integer cantidadproducto) {
        this.cantidadproducto = cantidadproducto;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public Double getPreciounitario() {
        return preciounitario;
    }

    public void setPreciounitario(Double preciounitario) {
        this.preciounitario = preciounitario;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public BigInteger getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(BigInteger idpedido) {
        this.idpedido = idpedido;
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

    public BigInteger getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(BigInteger idproducto) {
        this.idproducto = idproducto;
    }

}
