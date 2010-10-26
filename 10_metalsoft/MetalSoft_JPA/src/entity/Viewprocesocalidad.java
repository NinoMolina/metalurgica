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
@Table(name = "viewprocesocalidad")
@NamedQueries({
    @NamedQuery(name = "Viewprocesocalidad.findAll", query = "SELECT v FROM Viewprocesocalidad v"),
    @NamedQuery(name = "Viewprocesocalidad.findByNroproceso", query = "SELECT v FROM Viewprocesocalidad v WHERE v.nroproceso = :nroproceso"),
    @NamedQuery(name = "Viewprocesocalidad.findByNombreproceso", query = "SELECT v FROM Viewprocesocalidad v WHERE v.nombreproceso = :nombreproceso"),
    @NamedQuery(name = "Viewprocesocalidad.findByDuracionestimada", query = "SELECT v FROM Viewprocesocalidad v WHERE v.duracionestimada = :duracionestimada"),
    @NamedQuery(name = "Viewprocesocalidad.findByHerramienta", query = "SELECT v FROM Viewprocesocalidad v WHERE v.herramienta = :herramienta"),
    @NamedQuery(name = "Viewprocesocalidad.findByNombreaccioncalidad", query = "SELECT v FROM Viewprocesocalidad v WHERE v.nombreaccioncalidad = :nombreaccioncalidad"),
    @NamedQuery(name = "Viewprocesocalidad.findByIdprocesocalidad", query = "SELECT v FROM Viewprocesocalidad v WHERE v.idprocesocalidad = :idprocesocalidad")})
public class Viewprocesocalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "nroproceso")
    private BigInteger nroproceso;
    @Column(name = "nombreproceso")
    private String nombreproceso;
    @Column(name = "duracionestimada")
    @Temporal(TemporalType.TIME)
    private Date duracionestimada;
    @Column(name = "herramienta")
    private String herramienta;
    @Column(name = "nombreaccioncalidad")
    private String nombreaccioncalidad;
    @Column(name = "idprocesocalidad")
    private BigInteger idprocesocalidad;

    public Viewprocesocalidad() {
    }

    public BigInteger getNroproceso() {
        return nroproceso;
    }

    public void setNroproceso(BigInteger nroproceso) {
        this.nroproceso = nroproceso;
    }

    public String getNombreproceso() {
        return nombreproceso;
    }

    public void setNombreproceso(String nombreproceso) {
        this.nombreproceso = nombreproceso;
    }

    public Date getDuracionestimada() {
        return duracionestimada;
    }

    public void setDuracionestimada(Date duracionestimada) {
        this.duracionestimada = duracionestimada;
    }

    public String getHerramienta() {
        return herramienta;
    }

    public void setHerramienta(String herramienta) {
        this.herramienta = herramienta;
    }

    public String getNombreaccioncalidad() {
        return nombreaccioncalidad;
    }

    public void setNombreaccioncalidad(String nombreaccioncalidad) {
        this.nombreaccioncalidad = nombreaccioncalidad;
    }

    public BigInteger getIdprocesocalidad() {
        return idprocesocalidad;
    }

    public void setIdprocesocalidad(BigInteger idprocesocalidad) {
        this.idprocesocalidad = idprocesocalidad;
    }

}
