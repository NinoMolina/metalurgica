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
@Table(name = "viewdetallepedidocotizacion")
@NamedQueries({
    @NamedQuery(name = "Viewdetallepedidocotizacion.findAll", query = "SELECT v FROM Viewdetallepedidocotizacion v"),
    @NamedQuery(name = "Viewdetallepedidocotizacion.findByNroproducto", query = "SELECT v FROM Viewdetallepedidocotizacion v WHERE v.nroproducto = :nroproducto"),
    @NamedQuery(name = "Viewdetallepedidocotizacion.findByNombre", query = "SELECT v FROM Viewdetallepedidocotizacion v WHERE v.nombre = :nombre"),
    @NamedQuery(name = "Viewdetallepedidocotizacion.findByDescripcion", query = "SELECT v FROM Viewdetallepedidocotizacion v WHERE v.descripcion = :descripcion"),
    @NamedQuery(name = "Viewdetallepedidocotizacion.findByCantidad", query = "SELECT v FROM Viewdetallepedidocotizacion v WHERE v.cantidad = :cantidad"),
    @NamedQuery(name = "Viewdetallepedidocotizacion.findByPrecio", query = "SELECT v FROM Viewdetallepedidocotizacion v WHERE v.precio = :precio"),
    @NamedQuery(name = "Viewdetallepedidocotizacion.findByIdproducto", query = "SELECT v FROM Viewdetallepedidocotizacion v WHERE v.idproducto = :idproducto"),
    @NamedQuery(name = "Viewdetallepedidocotizacion.findByIddetalle", query = "SELECT v FROM Viewdetallepedidocotizacion v WHERE v.iddetalle = :iddetalle"),
    @NamedQuery(name = "Viewdetallepedidocotizacion.findByIdpedido", query = "SELECT v FROM Viewdetallepedidocotizacion v WHERE v.idpedido = :idpedido")})
public class Viewdetallepedidocotizacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "nroproducto")
    private BigInteger nroproducto;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "idproducto")
    private BigInteger idproducto;
    @Column(name = "iddetalle")
    private BigInteger iddetalle;
    @Column(name = "idpedido")
    private BigInteger idpedido;

    public Viewdetallepedidocotizacion() {
    }

    public BigInteger getNroproducto() {
        return nroproducto;
    }

    public void setNroproducto(BigInteger nroproducto) {
        this.nroproducto = nroproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public BigInteger getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(BigInteger idproducto) {
        this.idproducto = idproducto;
    }

    public BigInteger getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(BigInteger iddetalle) {
        this.iddetalle = iddetalle;
    }

    public BigInteger getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(BigInteger idpedido) {
        this.idpedido = idpedido;
    }

}
