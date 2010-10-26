/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "viewprocalidadxpiezapresupesto")
@NamedQueries({
    @NamedQuery(name = "Viewprocalidadxpiezapresupesto.findAll", query = "SELECT v FROM Viewprocalidadxpiezapresupesto v"),
    @NamedQuery(name = "Viewprocalidadxpiezapresupesto.findByNroproducto", query = "SELECT v FROM Viewprocalidadxpiezapresupesto v WHERE v.nroproducto = :nroproducto"),
    @NamedQuery(name = "Viewprocalidadxpiezapresupesto.findByNombreproducto", query = "SELECT v FROM Viewprocalidadxpiezapresupesto v WHERE v.nombreproducto = :nombreproducto"),
    @NamedQuery(name = "Viewprocalidadxpiezapresupesto.findByCantproducto", query = "SELECT v FROM Viewprocalidadxpiezapresupesto v WHERE v.cantproducto = :cantproducto"),
    @NamedQuery(name = "Viewprocalidadxpiezapresupesto.findByNombrepieza", query = "SELECT v FROM Viewprocalidadxpiezapresupesto v WHERE v.nombrepieza = :nombrepieza"),
    @NamedQuery(name = "Viewprocalidadxpiezapresupesto.findByCantpieza", query = "SELECT v FROM Viewprocalidadxpiezapresupesto v WHERE v.cantpieza = :cantpieza"),
    @NamedQuery(name = "Viewprocalidadxpiezapresupesto.findByNroprocesocalidad", query = "SELECT v FROM Viewprocalidadxpiezapresupesto v WHERE v.nroprocesocalidad = :nroprocesocalidad"),
    @NamedQuery(name = "Viewprocalidadxpiezapresupesto.findByNombreprocesocalidad", query = "SELECT v FROM Viewprocalidadxpiezapresupesto v WHERE v.nombreprocesocalidad = :nombreprocesocalidad"),
    @NamedQuery(name = "Viewprocalidadxpiezapresupesto.findByCantprocesocalidad", query = "SELECT v FROM Viewprocalidadxpiezapresupesto v WHERE v.cantprocesocalidad = :cantprocesocalidad"),
    @NamedQuery(name = "Viewprocalidadxpiezapresupesto.findByDuracionprocalidadxpieza", query = "SELECT v FROM Viewprocalidadxpiezapresupesto v WHERE v.duracionprocalidadxpieza = :duracionprocalidadxpieza"),
    @NamedQuery(name = "Viewprocalidadxpiezapresupesto.findByIdpresupuesto", query = "SELECT v FROM Viewprocalidadxpiezapresupesto v WHERE v.idpresupuesto = :idpresupuesto"),
    @NamedQuery(name = "Viewprocalidadxpiezapresupesto.findByIddetallepresupuesto", query = "SELECT v FROM Viewprocalidadxpiezapresupesto v WHERE v.iddetallepresupuesto = :iddetallepresupuesto"),
    @NamedQuery(name = "Viewprocalidadxpiezapresupesto.findByIddetalleproductopresupuesto", query = "SELECT v FROM Viewprocalidadxpiezapresupesto v WHERE v.iddetalleproductopresupuesto = :iddetalleproductopresupuesto"),
    @NamedQuery(name = "Viewprocalidadxpiezapresupesto.findByIddetallepiezacalidadpresupuesto", query = "SELECT v FROM Viewprocalidadxpiezapresupesto v WHERE v.iddetallepiezacalidadpresupuesto = :iddetallepiezacalidadpresupuesto"),
    @NamedQuery(name = "Viewprocalidadxpiezapresupesto.findByIdproducto", query = "SELECT v FROM Viewprocalidadxpiezapresupesto v WHERE v.idproducto = :idproducto"),
    @NamedQuery(name = "Viewprocalidadxpiezapresupesto.findByIdpieza", query = "SELECT v FROM Viewprocalidadxpiezapresupesto v WHERE v.idpieza = :idpieza"),
    @NamedQuery(name = "Viewprocalidadxpiezapresupesto.findByIdprocesocalidad", query = "SELECT v FROM Viewprocalidadxpiezapresupesto v WHERE v.idprocesocalidad = :idprocesocalidad")})
public class Viewprocalidadxpiezapresupesto implements Serializable {
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
    @Column(name = "nroprocesocalidad")
    private BigInteger nroprocesocalidad;
    @Column(name = "nombreprocesocalidad")
    private String nombreprocesocalidad;
    @Column(name = "cantprocesocalidad")
    private Integer cantprocesocalidad;
    @Column(name = "duracionprocalidadxpieza")
    @Temporal(TemporalType.TIME)
    private Date duracionprocalidadxpieza;
    @Lob
    @Column(name = "duraciontotal")
    private Object duraciontotal;
    @Column(name = "idpresupuesto")
    private BigInteger idpresupuesto;
    @Column(name = "iddetallepresupuesto")
    private BigInteger iddetallepresupuesto;
    @Column(name = "iddetalleproductopresupuesto")
    private BigInteger iddetalleproductopresupuesto;
    @Column(name = "iddetallepiezacalidadpresupuesto")
    private BigInteger iddetallepiezacalidadpresupuesto;
    @Column(name = "idproducto")
    private BigInteger idproducto;
    @Column(name = "idpieza")
    private BigInteger idpieza;
    @Column(name = "idprocesocalidad")
    private BigInteger idprocesocalidad;

    public Viewprocalidadxpiezapresupesto() {
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

    public BigInteger getNroprocesocalidad() {
        return nroprocesocalidad;
    }

    public void setNroprocesocalidad(BigInteger nroprocesocalidad) {
        this.nroprocesocalidad = nroprocesocalidad;
    }

    public String getNombreprocesocalidad() {
        return nombreprocesocalidad;
    }

    public void setNombreprocesocalidad(String nombreprocesocalidad) {
        this.nombreprocesocalidad = nombreprocesocalidad;
    }

    public Integer getCantprocesocalidad() {
        return cantprocesocalidad;
    }

    public void setCantprocesocalidad(Integer cantprocesocalidad) {
        this.cantprocesocalidad = cantprocesocalidad;
    }

    public Date getDuracionprocalidadxpieza() {
        return duracionprocalidadxpieza;
    }

    public void setDuracionprocalidadxpieza(Date duracionprocalidadxpieza) {
        this.duracionprocalidadxpieza = duracionprocalidadxpieza;
    }

    public Object getDuraciontotal() {
        return duraciontotal;
    }

    public void setDuraciontotal(Object duraciontotal) {
        this.duraciontotal = duraciontotal;
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

    public BigInteger getIddetallepiezacalidadpresupuesto() {
        return iddetallepiezacalidadpresupuesto;
    }

    public void setIddetallepiezacalidadpresupuesto(BigInteger iddetallepiezacalidadpresupuesto) {
        this.iddetallepiezacalidadpresupuesto = iddetallepiezacalidadpresupuesto;
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

    public BigInteger getIdprocesocalidad() {
        return idprocesocalidad;
    }

    public void setIdprocesocalidad(BigInteger idprocesocalidad) {
        this.idprocesocalidad = idprocesocalidad;
    }

}
