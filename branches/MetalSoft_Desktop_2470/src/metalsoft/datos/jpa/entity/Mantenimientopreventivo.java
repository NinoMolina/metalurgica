/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "mantenimientopreventivo")
@NamedQueries({
    @NamedQuery(name = "Mantenimientopreventivo.findAll", query = "SELECT m FROM Mantenimientopreventivo m"),
    @NamedQuery(name = "Mantenimientopreventivo.findByIdmantenimientopreventivo", query = "SELECT m FROM Mantenimientopreventivo m WHERE m.idmantenimientopreventivo = :idmantenimientopreventivo"),
    @NamedQuery(name = "Mantenimientopreventivo.findByFechamantenimientoprevisto", query = "SELECT m FROM Mantenimientopreventivo m WHERE m.fechamantenimientoprevisto = :fechamantenimientoprevisto"),
    @NamedQuery(name = "Mantenimientopreventivo.findByFechaenviomantenimiento", query = "SELECT m FROM Mantenimientopreventivo m WHERE m.fechaenviomantenimiento = :fechaenviomantenimiento"),
    @NamedQuery(name = "Mantenimientopreventivo.findByHoraenviomantenimiento", query = "SELECT m FROM Mantenimientopreventivo m WHERE m.horaenviomantenimiento = :horaenviomantenimiento"),
    @NamedQuery(name = "Mantenimientopreventivo.findByPeriodo", query = "SELECT m FROM Mantenimientopreventivo m WHERE m.periodo = :periodo"),
    @NamedQuery(name = "Mantenimientopreventivo.findByNromantenimietno", query = "SELECT m FROM Mantenimientopreventivo m WHERE m.nromantenimietno = :nromantenimietno"),
    @NamedQuery(name = "Mantenimientopreventivo.findByFechafinmantenimientoreal", query = "SELECT m FROM Mantenimientopreventivo m WHERE m.fechafinmantenimientoreal = :fechafinmantenimientoreal"),
    @NamedQuery(name = "Mantenimientopreventivo.findByMaquina", query = "SELECT m FROM Mantenimientopreventivo m WHERE m.maquina = :maquina")})
public class Mantenimientopreventivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mantenimientopreventivo_seq")
    @SequenceGenerator(name = "mantenimientopreventivo_seq", sequenceName = "mantenimientopreventivo_idmantenimientopreventivo_seq", allocationSize = 1)
    @Column(name = "idmantenimientopreventivo")
    private Long idmantenimientopreventivo;
    @Column(name = "fechamantenimientoprevisto")
    @Temporal(TemporalType.DATE)
    private Date fechamantenimientoprevisto;
    @Column(name = "fechaenviomantenimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaenviomantenimiento;
    @Column(name = "horaenviomantenimiento")
    @Temporal(TemporalType.TIME)
    private Date horaenviomantenimiento;
    @Column(name = "nromantenimietno")
    private BigInteger nromantenimietno;
    @Column(name = "fechafinmantenimientoreal")
    @Temporal(TemporalType.DATE)
    private Date fechafinmantenimientoreal;
    @Column(name = "periodo")
    private BigInteger periodo;
    @Column(name = "duraciontotal")
    @Temporal(TemporalType.TIME)
    private Date duraciontotal;
    @Column(name = "maquina")
    private BigInteger maquina;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "mantenimientopreventivo")
    private Detallemantenimientopreventivo detallemantenimientopreventivo;
    @JoinColumn(name = "proveedormantenimiento", referencedColumnName = "idproveedormantenimiento")
    @ManyToOne
    private Proveedormantenimientomaquina proveedormantenimiento;

    public Mantenimientopreventivo() {
    }

    public Mantenimientopreventivo(Long idmantenimientopreventivo) {
        this.idmantenimientopreventivo = idmantenimientopreventivo;
    }

    public Long getIdmantenimientopreventivo() {
        return idmantenimientopreventivo;
    }

    public void setIdmantenimientopreventivo(Long idmantenimientopreventivo) {
        this.idmantenimientopreventivo = idmantenimientopreventivo;
    }

    public Date getFechamantenimientoprevisto() {
        return fechamantenimientoprevisto;
    }

    public void setFechamantenimientoprevisto(Date fechamantenimientoprevisto) {
        this.fechamantenimientoprevisto = fechamantenimientoprevisto;
    }

    public Date getFechaenviomantenimiento() {
        return fechaenviomantenimiento;
    }

    public void setFechaenviomantenimiento(Date fechaenviomantenimiento) {
        this.fechaenviomantenimiento = fechaenviomantenimiento;
    }

    public Date getHoraenviomantenimiento() {
        return horaenviomantenimiento;
    }

    public void setHoraenviomantenimiento(Date horaenviomantenimiento) {
        this.horaenviomantenimiento = horaenviomantenimiento;
    }

    public Date getDuraciontotal() {
        return duraciontotal;
    }

    public void setDuraciontotal(Date duraciontotal) {
        this.duraciontotal = duraciontotal;
    }

    public BigInteger getPeriodo() {
        return periodo;
    }

    public void setPeriodo(BigInteger periodo) {
        this.periodo = periodo;
    }

    public BigInteger getNromantenimietno() {
        return nromantenimietno;
    }

    public void setNromantenimietno(BigInteger nromantenimietno) {
        this.nromantenimietno = nromantenimietno;
    }

    public Date getFechafinmantenimientoreal() {
        return fechafinmantenimientoreal;
    }

    public void setFechafinmantenimientoreal(Date fechafinmantenimientoreal) {
        this.fechafinmantenimientoreal = fechafinmantenimientoreal;
    }

    public BigInteger getMaquina() {
        return maquina;
    }

    public void setMaquina(BigInteger maquina) {
        this.maquina = maquina;
    }

    public Detallemantenimientopreventivo getDetallemantenimientopreventivo() {
        return detallemantenimientopreventivo;
    }

    public void setDetallemantenimientopreventivo(Detallemantenimientopreventivo detallemantenimientopreventivo) {
        this.detallemantenimientopreventivo = detallemantenimientopreventivo;
    }

    public Proveedormantenimientomaquina getProveedormantenimiento() {
        return proveedormantenimiento;
    }

    public void setProveedormantenimiento(Proveedormantenimientomaquina proveedormantenimiento) {
        this.proveedormantenimiento = proveedormantenimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmantenimientopreventivo != null ? idmantenimientopreventivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mantenimientopreventivo)) {
            return false;
        }
        Mantenimientopreventivo other = (Mantenimientopreventivo) object;
        if ((this.idmantenimientopreventivo == null && other.idmantenimientopreventivo != null) || (this.idmantenimientopreventivo != null && !this.idmantenimientopreventivo.equals(other.idmantenimientopreventivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Mantenimientopreventivo[ idmantenimientopreventivo=" + idmantenimientopreventivo + " ]";
    }
    
}
