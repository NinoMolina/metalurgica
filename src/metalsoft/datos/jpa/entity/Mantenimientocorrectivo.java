/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "mantenimientocorrectivo")
@NamedQueries({
    @NamedQuery(name = "Mantenimientocorrectivo.findAll", query = "SELECT m FROM Mantenimientocorrectivo m"),
    @NamedQuery(name = "Mantenimientocorrectivo.findByIdmantenimientocorrectivo", query = "SELECT m FROM Mantenimientocorrectivo m WHERE m.idmantenimientocorrectivo = :idmantenimientocorrectivo"),
    @NamedQuery(name = "Mantenimientocorrectivo.findByFechaenviomantenimiento", query = "SELECT m FROM Mantenimientocorrectivo m WHERE m.fechaenviomantenimiento = :fechaenviomantenimiento"),
    @NamedQuery(name = "Mantenimientocorrectivo.findByHoraenviomantenimiento", query = "SELECT m FROM Mantenimientocorrectivo m WHERE m.horaenviomantenimiento = :horaenviomantenimiento"),
    @NamedQuery(name = "Mantenimientocorrectivo.findByNromantenimientocorrectivo", query = "SELECT m FROM Mantenimientocorrectivo m WHERE m.nromantenimientocorrectivo = :nromantenimientocorrectivo"),
    @NamedQuery(name = "Mantenimientocorrectivo.findByNrocomprobantearreglo", query = "SELECT m FROM Mantenimientocorrectivo m WHERE m.nrocomprobantearreglo = :nrocomprobantearreglo"),
    @NamedQuery(name = "Mantenimientocorrectivo.findByFechafinmantenimientoreal", query = "SELECT m FROM Mantenimientocorrectivo m WHERE m.fechafinmantenimientoreal = :fechafinmantenimientoreal"),
    @NamedQuery(name = "Mantenimientocorrectivo.findByMaquina", query = "SELECT m FROM Mantenimientocorrectivo m WHERE m.maquina = :maquina")})
public class Mantenimientocorrectivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mantenimientocorrectivo_seq")
    @SequenceGenerator(name = "mantenimientocorrectivo_seq", sequenceName = "mantenimientocorrectivo_idmantenimientocorrectivo_seq", allocationSize = 1)
    @Column(name = "idmantenimientocorrectivo")
    private Long idmantenimientocorrectivo;
    @Column(name = "fechaenviomantenimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaenviomantenimiento;
    @Column(name = "horaenviomantenimiento")
    @Temporal(TemporalType.TIME)
    private Date horaenviomantenimiento;
    @Column(name = "nromantenimientocorrectivo")
    private BigInteger nromantenimientocorrectivo;
    @Column(name = "nrocomprobantearreglo")
    private BigInteger nrocomprobantearreglo;
    @Column(name = "fechafinmantenimientoreal")
    @Temporal(TemporalType.DATE)
    private Date fechafinmantenimientoreal;
    @Column(name = "maquina")
    private BigInteger maquina;
    @JoinColumn(name = "proveedormantenimiento", referencedColumnName = "idproveedormantenimiento")
    @ManyToOne
    private Proveedormantenimientomaquina proveedormantenimiento;
    @JoinColumn(name = "empleado", referencedColumnName = "idempleado")
    @ManyToOne
    private Empleado empleado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmantenimientocorrectivo")
    private List<Detallemantenimientocorrectivo> detallemantenimientocorrectivoList;

    public Mantenimientocorrectivo() {
    }

    public Mantenimientocorrectivo(Long idmantenimientocorrectivo) {
        this.idmantenimientocorrectivo = idmantenimientocorrectivo;
    }

    public Long getIdmantenimientocorrectivo() {
        return idmantenimientocorrectivo;
    }

    public void setIdmantenimientocorrectivo(Long idmantenimientocorrectivo) {
        this.idmantenimientocorrectivo = idmantenimientocorrectivo;
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

    public BigInteger getNromantenimientocorrectivo() {
        return nromantenimientocorrectivo;
    }

    public void setNromantenimientocorrectivo(BigInteger nromantenimientocorrectivo) {
        this.nromantenimientocorrectivo = nromantenimientocorrectivo;
    }

    public BigInteger getNrocomprobantearreglo() {
        return nrocomprobantearreglo;
    }

    public void setNrocomprobantearreglo(BigInteger nrocomprobantearreglo) {
        this.nrocomprobantearreglo = nrocomprobantearreglo;
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

    public Proveedormantenimientomaquina getProveedormantenimiento() {
        return proveedormantenimiento;
    }

    public void setProveedormantenimiento(Proveedormantenimientomaquina proveedormantenimiento) {
        this.proveedormantenimiento = proveedormantenimiento;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Detallemantenimientocorrectivo> getDetallemantenimientocorrectivoList() {
        return detallemantenimientocorrectivoList;
    }

    public void setDetallemantenimientocorrectivoList(List<Detallemantenimientocorrectivo> detallemantenimientocorrectivoList) {
        this.detallemantenimientocorrectivoList = detallemantenimientocorrectivoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmantenimientocorrectivo != null ? idmantenimientocorrectivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mantenimientocorrectivo)) {
            return false;
        }
        Mantenimientocorrectivo other = (Mantenimientocorrectivo) object;
        if ((this.idmantenimientocorrectivo == null && other.idmantenimientocorrectivo != null) || (this.idmantenimientocorrectivo != null && !this.idmantenimientocorrectivo.equals(other.idmantenimientocorrectivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Mantenimientocorrectivo[ idmantenimientocorrectivo=" + idmantenimientocorrectivo + " ]";
    }
}
