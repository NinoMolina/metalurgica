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
@Table(name = "viewmateriaprima")
@NamedQueries({
    @NamedQuery(name = "Viewmateriaprima.findAll", query = "SELECT v FROM Viewmateriaprima v"),
    @NamedQuery(name = "Viewmateriaprima.findByNombremateriaprima", query = "SELECT v FROM Viewmateriaprima v WHERE v.nombremateriaprima = :nombremateriaprima"),
    @NamedQuery(name = "Viewmateriaprima.findByDescripcion", query = "SELECT v FROM Viewmateriaprima v WHERE v.descripcion = :descripcion"),
    @NamedQuery(name = "Viewmateriaprima.findByAlto", query = "SELECT v FROM Viewmateriaprima v WHERE v.alto = :alto"),
    @NamedQuery(name = "Viewmateriaprima.findByAncho", query = "SELECT v FROM Viewmateriaprima v WHERE v.ancho = :ancho"),
    @NamedQuery(name = "Viewmateriaprima.findByLargo", query = "SELECT v FROM Viewmateriaprima v WHERE v.largo = :largo"),
    @NamedQuery(name = "Viewmateriaprima.findByUnidadmedida", query = "SELECT v FROM Viewmateriaprima v WHERE v.unidadmedida = :unidadmedida"),
    @NamedQuery(name = "Viewmateriaprima.findByTipomaterial", query = "SELECT v FROM Viewmateriaprima v WHERE v.tipomaterial = :tipomaterial"),
    @NamedQuery(name = "Viewmateriaprima.findByIdmateriaprima", query = "SELECT v FROM Viewmateriaprima v WHERE v.idmateriaprima = :idmateriaprima")})
public class Viewmateriaprima implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "nombremateriaprima")
    private String nombremateriaprima;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "alto")
    private BigDecimal alto;
    @Column(name = "ancho")
    private BigDecimal ancho;
    @Column(name = "largo")
    private BigDecimal largo;
    @Column(name = "unidadmedida")
    private String unidadmedida;
    @Column(name = "tipomaterial")
    private String tipomaterial;
    @Column(name = "idmateriaprima")
    private BigInteger idmateriaprima;

    public Viewmateriaprima() {
    }

    public String getNombremateriaprima() {
        return nombremateriaprima;
    }

    public void setNombremateriaprima(String nombremateriaprima) {
        this.nombremateriaprima = nombremateriaprima;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getUnidadmedida() {
        return unidadmedida;
    }

    public void setUnidadmedida(String unidadmedida) {
        this.unidadmedida = unidadmedida;
    }

    public String getTipomaterial() {
        return tipomaterial;
    }

    public void setTipomaterial(String tipomaterial) {
        this.tipomaterial = tipomaterial;
    }

    public BigInteger getIdmateriaprima() {
        return idmateriaprima;
    }

    public void setIdmateriaprima(BigInteger idmateriaprima) {
        this.idmateriaprima = idmateriaprima;
    }

}
