/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "empleado")
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
    @NamedQuery(name = "Empleado.findByIdempleado", query = "SELECT e FROM Empleado e WHERE e.idempleado = :idempleado"),
    @NamedQuery(name = "Empleado.findByLegajo", query = "SELECT e FROM Empleado e WHERE e.legajo = :legajo"),
    @NamedQuery(name = "Empleado.findByFechaingreso", query = "SELECT e FROM Empleado e WHERE e.fechaingreso = :fechaingreso"),
    @NamedQuery(name = "Empleado.findByNombre", query = "SELECT e FROM Empleado e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Empleado.findByApellido", query = "SELECT e FROM Empleado e WHERE e.apellido = :apellido"),
    @NamedQuery(name = "Empleado.findByTelefono", query = "SELECT e FROM Empleado e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "Empleado.findByEmail", query = "SELECT e FROM Empleado e WHERE e.email = :email"),
    @NamedQuery(name = "Empleado.findByNrodocumento", query = "SELECT e FROM Empleado e WHERE e.nrodocumento = :nrodocumento"),
    @NamedQuery(name = "Empleado.findByFechaegreso", query = "SELECT e FROM Empleado e WHERE e.fechaegreso = :fechaegreso"),
    @NamedQuery(name = "Empleado.findByMotivoegreso", query = "SELECT e FROM Empleado e WHERE e.motivoegreso = :motivoegreso")})
public class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idempleado")
    private Long idempleado;
    @Column(name = "legajo")
    private BigInteger legajo;
    @Column(name = "fechaingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaingreso;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "email")
    private String email;
    @Column(name = "nrodocumento")
    private Integer nrodocumento;
    @Column(name = "fechaegreso")
    @Temporal(TemporalType.DATE)
    private Date fechaegreso;
    @Column(name = "motivoegreso")
    private String motivoegreso;
    @JoinTable(name = "empleadoxturno", joinColumns = {
        @JoinColumn(name = "idempleado", referencedColumnName = "idempleado")}, inverseJoinColumns = {
        @JoinColumn(name = "idturno", referencedColumnName = "idturno")})
    @ManyToMany
    private Set<Turno> turnoSet;
    @OneToMany(mappedBy = "idempleado")
    private Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet;
    @OneToMany(mappedBy = "empleado")
    private Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSet;
    @OneToMany(mappedBy = "empleado")
    private Set<Mantenimientocorrectivo> mantenimientocorrectivoSet;
    @OneToMany(mappedBy = "idempleado")
    private Set<Disponibilidadhoraria> disponibilidadhorariaSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado1")
    private Set<Asistencia> asistenciaSet;
    @JoinColumn(name = "cargo", referencedColumnName = "idcargo")
    @ManyToOne
    private Cargo cargo;
    @JoinColumn(name = "categoria", referencedColumnName = "idcategoria")
    @ManyToOne
    private Categoria categoria;
    @JoinColumn(name = "domicilio", referencedColumnName = "iddomicilio")
    @ManyToOne
    private Domicilio domicilio;
    @JoinColumn(name = "tipodocumento", referencedColumnName = "idtipodocumento")
    @ManyToOne
    private Tipodocumento tipodocumento;
    @JoinColumn(name = "usuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuario;

    public Empleado() {
    }

    public Empleado(Long idempleado) {
        this.idempleado = idempleado;
    }

    public Long getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Long idempleado) {
        this.idempleado = idempleado;
    }

    public BigInteger getLegajo() {
        return legajo;
    }

    public void setLegajo(BigInteger legajo) {
        this.legajo = legajo;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNrodocumento() {
        return nrodocumento;
    }

    public void setNrodocumento(Integer nrodocumento) {
        this.nrodocumento = nrodocumento;
    }

    public Date getFechaegreso() {
        return fechaegreso;
    }

    public void setFechaegreso(Date fechaegreso) {
        this.fechaegreso = fechaegreso;
    }

    public String getMotivoegreso() {
        return motivoegreso;
    }

    public void setMotivoegreso(String motivoegreso) {
        this.motivoegreso = motivoegreso;
    }

    public Set<Turno> getTurnoSet() {
        return turnoSet;
    }

    public void setTurnoSet(Set<Turno> turnoSet) {
        this.turnoSet = turnoSet;
    }

    public Set<Detalleplanificacionproduccion> getDetalleplanificacionproduccionSet() {
        return detalleplanificacionproduccionSet;
    }

    public void setDetalleplanificacionproduccionSet(Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet) {
        this.detalleplanificacionproduccionSet = detalleplanificacionproduccionSet;
    }

    public Set<Ejecucionetapaproduccion> getEjecucionetapaproduccionSet() {
        return ejecucionetapaproduccionSet;
    }

    public void setEjecucionetapaproduccionSet(Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSet) {
        this.ejecucionetapaproduccionSet = ejecucionetapaproduccionSet;
    }

    public Set<Mantenimientocorrectivo> getMantenimientocorrectivoSet() {
        return mantenimientocorrectivoSet;
    }

    public void setMantenimientocorrectivoSet(Set<Mantenimientocorrectivo> mantenimientocorrectivoSet) {
        this.mantenimientocorrectivoSet = mantenimientocorrectivoSet;
    }

    public Set<Disponibilidadhoraria> getDisponibilidadhorariaSet() {
        return disponibilidadhorariaSet;
    }

    public void setDisponibilidadhorariaSet(Set<Disponibilidadhoraria> disponibilidadhorariaSet) {
        this.disponibilidadhorariaSet = disponibilidadhorariaSet;
    }

    public Set<Asistencia> getAsistenciaSet() {
        return asistenciaSet;
    }

    public void setAsistenciaSet(Set<Asistencia> asistenciaSet) {
        this.asistenciaSet = asistenciaSet;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Tipodocumento getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(Tipodocumento tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idempleado != null ? idempleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.idempleado == null && other.idempleado != null) || (this.idempleado != null && !this.idempleado.equals(other.idempleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Empleado[idempleado=" + idempleado + "]";
    }

}
