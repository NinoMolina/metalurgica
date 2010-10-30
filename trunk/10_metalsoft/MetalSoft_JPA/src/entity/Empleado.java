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
    @OneToMany(mappedBy = "idempleado")
    private Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet;
    @OneToMany(mappedBy = "idempleado1")
    private Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private Set<Empleadoxturno> empleadoxturnoSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado1")
    private Set<Empleadoxturno> empleadoxturnoSet1;
    @OneToMany(mappedBy = "empleado")
    private Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSet;
    @OneToMany(mappedBy = "empleado1")
    private Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSet1;
    @OneToMany(mappedBy = "empleado")
    private Set<Mantenimientocorrectivo> mantenimientocorrectivoSet;
    @OneToMany(mappedBy = "empleado1")
    private Set<Mantenimientocorrectivo> mantenimientocorrectivoSet1;
    @OneToMany(mappedBy = "idempleado")
    private Set<Disponibilidadhoraria> disponibilidadhorariaSet;
    @OneToMany(mappedBy = "idempleado1")
    private Set<Disponibilidadhoraria> disponibilidadhorariaSet1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado1")
    private Set<Asistencia> asistenciaSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado2")
    private Set<Asistencia> asistenciaSet1;
    @JoinColumn(name = "cargo", referencedColumnName = "idcargo")
    @ManyToOne
    private Cargo cargo;
    @JoinColumn(name = "cargo", referencedColumnName = "idcargo")
    @ManyToOne
    private Cargo cargo1;
    @JoinColumn(name = "categoria", referencedColumnName = "idcategoria")
    @ManyToOne
    private Categoria categoria;
    @JoinColumn(name = "categoria", referencedColumnName = "idcategoria")
    @ManyToOne
    private Categoria categoria1;
    @JoinColumn(name = "domicilio", referencedColumnName = "iddomicilio")
    @ManyToOne
    private Domicilio domicilio;
    @JoinColumn(name = "domicilio", referencedColumnName = "iddomicilio")
    @ManyToOne
    private Domicilio domicilio1;
    @JoinColumn(name = "tipodocumento", referencedColumnName = "idtipodocumento")
    @ManyToOne
    private Tipodocumento tipodocumento;
    @JoinColumn(name = "tipodocumento", referencedColumnName = "idtipodocumento")
    @ManyToOne
    private Tipodocumento tipodocumento1;
    @JoinColumn(name = "usuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "usuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuario1;

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

    public Set<Detalleplanificacionproduccion> getDetalleplanificacionproduccionSet() {
        return detalleplanificacionproduccionSet;
    }

    public void setDetalleplanificacionproduccionSet(Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet) {
        this.detalleplanificacionproduccionSet = detalleplanificacionproduccionSet;
    }

    public Set<Detalleplanificacionproduccion> getDetalleplanificacionproduccionSet1() {
        return detalleplanificacionproduccionSet1;
    }

    public void setDetalleplanificacionproduccionSet1(Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1) {
        this.detalleplanificacionproduccionSet1 = detalleplanificacionproduccionSet1;
    }

    public Set<Empleadoxturno> getEmpleadoxturnoSet() {
        return empleadoxturnoSet;
    }

    public void setEmpleadoxturnoSet(Set<Empleadoxturno> empleadoxturnoSet) {
        this.empleadoxturnoSet = empleadoxturnoSet;
    }

    public Set<Empleadoxturno> getEmpleadoxturnoSet1() {
        return empleadoxturnoSet1;
    }

    public void setEmpleadoxturnoSet1(Set<Empleadoxturno> empleadoxturnoSet1) {
        this.empleadoxturnoSet1 = empleadoxturnoSet1;
    }

    public Set<Ejecucionetapaproduccion> getEjecucionetapaproduccionSet() {
        return ejecucionetapaproduccionSet;
    }

    public void setEjecucionetapaproduccionSet(Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSet) {
        this.ejecucionetapaproduccionSet = ejecucionetapaproduccionSet;
    }

    public Set<Ejecucionetapaproduccion> getEjecucionetapaproduccionSet1() {
        return ejecucionetapaproduccionSet1;
    }

    public void setEjecucionetapaproduccionSet1(Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSet1) {
        this.ejecucionetapaproduccionSet1 = ejecucionetapaproduccionSet1;
    }

    public Set<Mantenimientocorrectivo> getMantenimientocorrectivoSet() {
        return mantenimientocorrectivoSet;
    }

    public void setMantenimientocorrectivoSet(Set<Mantenimientocorrectivo> mantenimientocorrectivoSet) {
        this.mantenimientocorrectivoSet = mantenimientocorrectivoSet;
    }

    public Set<Mantenimientocorrectivo> getMantenimientocorrectivoSet1() {
        return mantenimientocorrectivoSet1;
    }

    public void setMantenimientocorrectivoSet1(Set<Mantenimientocorrectivo> mantenimientocorrectivoSet1) {
        this.mantenimientocorrectivoSet1 = mantenimientocorrectivoSet1;
    }

    public Set<Disponibilidadhoraria> getDisponibilidadhorariaSet() {
        return disponibilidadhorariaSet;
    }

    public void setDisponibilidadhorariaSet(Set<Disponibilidadhoraria> disponibilidadhorariaSet) {
        this.disponibilidadhorariaSet = disponibilidadhorariaSet;
    }

    public Set<Disponibilidadhoraria> getDisponibilidadhorariaSet1() {
        return disponibilidadhorariaSet1;
    }

    public void setDisponibilidadhorariaSet1(Set<Disponibilidadhoraria> disponibilidadhorariaSet1) {
        this.disponibilidadhorariaSet1 = disponibilidadhorariaSet1;
    }

    public Set<Asistencia> getAsistenciaSet() {
        return asistenciaSet;
    }

    public void setAsistenciaSet(Set<Asistencia> asistenciaSet) {
        this.asistenciaSet = asistenciaSet;
    }

    public Set<Asistencia> getAsistenciaSet1() {
        return asistenciaSet1;
    }

    public void setAsistenciaSet1(Set<Asistencia> asistenciaSet1) {
        this.asistenciaSet1 = asistenciaSet1;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Cargo getCargo1() {
        return cargo1;
    }

    public void setCargo1(Cargo cargo1) {
        this.cargo1 = cargo1;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Categoria getCategoria1() {
        return categoria1;
    }

    public void setCategoria1(Categoria categoria1) {
        this.categoria1 = categoria1;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Domicilio getDomicilio1() {
        return domicilio1;
    }

    public void setDomicilio1(Domicilio domicilio1) {
        this.domicilio1 = domicilio1;
    }

    public Tipodocumento getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(Tipodocumento tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public Tipodocumento getTipodocumento1() {
        return tipodocumento1;
    }

    public void setTipodocumento1(Tipodocumento tipodocumento1) {
        this.tipodocumento1 = tipodocumento1;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
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
