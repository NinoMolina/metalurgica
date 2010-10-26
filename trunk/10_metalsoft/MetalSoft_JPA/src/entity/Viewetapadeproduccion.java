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
@Table(name = "viewetapadeproduccion")
@NamedQueries({
    @NamedQuery(name = "Viewetapadeproduccion.findAll", query = "SELECT v FROM Viewetapadeproduccion v"),
    @NamedQuery(name = "Viewetapadeproduccion.findByNumero", query = "SELECT v FROM Viewetapadeproduccion v WHERE v.numero = :numero"),
    @NamedQuery(name = "Viewetapadeproduccion.findByNombre", query = "SELECT v FROM Viewetapadeproduccion v WHERE v.nombre = :nombre"),
    @NamedQuery(name = "Viewetapadeproduccion.findByHorashombre", query = "SELECT v FROM Viewetapadeproduccion v WHERE v.horashombre = :horashombre"),
    @NamedQuery(name = "Viewetapadeproduccion.findByHorasmaquina", query = "SELECT v FROM Viewetapadeproduccion v WHERE v.horasmaquina = :horasmaquina"),
    @NamedQuery(name = "Viewetapadeproduccion.findByDuracionestimada", query = "SELECT v FROM Viewetapadeproduccion v WHERE v.duracionestimada = :duracionestimada"),
    @NamedQuery(name = "Viewetapadeproduccion.findByIdetapa", query = "SELECT v FROM Viewetapadeproduccion v WHERE v.idetapa = :idetapa")})
public class Viewetapadeproduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "numero")
    private BigInteger numero;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "horashombre")
    @Temporal(TemporalType.TIME)
    private Date horashombre;
    @Column(name = "horasmaquina")
    @Temporal(TemporalType.TIME)
    private Date horasmaquina;
    @Column(name = "duracionestimada")
    @Temporal(TemporalType.TIME)
    private Date duracionestimada;
    @Column(name = "idetapa")
    private BigInteger idetapa;

    public Viewetapadeproduccion() {
    }

    public BigInteger getNumero() {
        return numero;
    }

    public void setNumero(BigInteger numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getHorashombre() {
        return horashombre;
    }

    public void setHorashombre(Date horashombre) {
        this.horashombre = horashombre;
    }

    public Date getHorasmaquina() {
        return horasmaquina;
    }

    public void setHorasmaquina(Date horasmaquina) {
        this.horasmaquina = horasmaquina;
    }

    public Date getDuracionestimada() {
        return duracionestimada;
    }

    public void setDuracionestimada(Date duracionestimada) {
        this.duracionestimada = duracionestimada;
    }

    public BigInteger getIdetapa() {
        return idetapa;
    }

    public void setIdetapa(BigInteger idetapa) {
        this.idetapa = idetapa;
    }

}
