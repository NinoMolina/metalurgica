/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "viewdetalleproducto")
@NamedQueries({
    @NamedQuery(name = "Viewdetalleproducto.findAll", query = "SELECT v FROM Viewdetalleproducto v"),
    @NamedQuery(name = "Viewdetalleproducto.findByNombrepieza", query = "SELECT v FROM Viewdetalleproducto v WHERE v.nombrepieza = :nombrepieza"),
    @NamedQuery(name = "Viewdetalleproducto.findByDescripcion", query = "SELECT v FROM Viewdetalleproducto v WHERE v.descripcion = :descripcion"),
    @NamedQuery(name = "Viewdetalleproducto.findByCantidadpiezas", query = "SELECT v FROM Viewdetalleproducto v WHERE v.cantidadpiezas = :cantidadpiezas"),
    @NamedQuery(name = "Viewdetalleproducto.findByAlto", query = "SELECT v FROM Viewdetalleproducto v WHERE v.alto = :alto"),
    @NamedQuery(name = "Viewdetalleproducto.findByAncho", query = "SELECT v FROM Viewdetalleproducto v WHERE v.ancho = :ancho"),
    @NamedQuery(name = "Viewdetalleproducto.findByLargo", query = "SELECT v FROM Viewdetalleproducto v WHERE v.largo = :largo"),
    @NamedQuery(name = "Viewdetalleproducto.findByNombretipomaterial", query = "SELECT v FROM Viewdetalleproducto v WHERE v.nombretipomaterial = :nombretipomaterial"),
    @NamedQuery(name = "Viewdetalleproducto.findByIdpieza", query = "SELECT v FROM Viewdetalleproducto v WHERE v.idpieza = :idpieza"),
    @NamedQuery(name = "Viewdetalleproducto.findByIddetalle", query = "SELECT v FROM Viewdetalleproducto v WHERE v.iddetalle = :iddetalle"),
    @NamedQuery(name = "Viewdetalleproducto.findByIdproducto", query = "SELECT v FROM Viewdetalleproducto v WHERE v.idproducto = :idproducto")})
public class Viewdetalleproducto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "nombrepieza")
    private String nombrepieza;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "cantidadpiezas")
    private Integer cantidadpiezas;
    @Column(name = "alto")
    private BigDecimal alto;
    @Column(name = "ancho")
    private BigDecimal ancho;
    @Column(name = "largo")
    private BigDecimal largo;
    @Column(name = "nombretipomaterial")
    private String nombretipomaterial;
    @Column(name = "idpieza")
    private BigInteger idpieza;
    @Column(name = "iddetalle")
    private BigInteger iddetalle;
    @Column(name = "idproducto")
    private BigInteger idproducto;

    public Viewdetalleproducto() {
    }

    public String getNombrepieza() {
        return nombrepieza;
    }

    public void setNombrepieza(String nombrepieza) {
        this.nombrepieza = nombrepieza;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidadpiezas() {
        return cantidadpiezas;
    }

    public void setCantidadpiezas(Integer cantidadpiezas) {
        this.cantidadpiezas = cantidadpiezas;
    }

    public BigDecimal getAlto() {
        return alto;
    }

    public void setAlto(BigDecimal alto) {
        this.alto = alto;
    }

    public BigDecimal getAncho() {
        return ancho;
    }

    public void setAncho(BigDecimal ancho) {
        this.ancho = ancho;
    }

    public BigDecimal getLargo() {
        return largo;
    }

    public void setLargo(BigDecimal largo) {
        this.largo = largo;
    }

    public String getNombretipomaterial() {
        return nombretipomaterial;
    }

    public void setNombretipomaterial(String nombretipomaterial) {
        this.nombretipomaterial = nombretipomaterial;
    }

    public BigInteger getIdpieza() {
        return idpieza;
    }

    public void setIdpieza(BigInteger idpieza) {
        this.idpieza = idpieza;
    }

    public BigInteger getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(BigInteger iddetalle) {
        this.iddetalle = iddetalle;
    }

    public BigInteger getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(BigInteger idproducto) {
        this.idproducto = idproducto;
    }

}
