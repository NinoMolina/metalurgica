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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empleado_seq")
    @SequenceGenerator(name = "empleado_seq", sequenceName = "empleado_idempleado_seq", allocationSize = 1)
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
    private List<Detalleplanificacionproduccion> detalleplanificacionproduccionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private List<Empleadoxturno> empleadoxturnoList;
    @OneToMany(mappedBy = "empleado")
    private List<Ejecucionetapaproduccion> ejecucionetapaproduccionList;
    @OneToMany(mappedBy = "empleado")
    private List<Ejecucionetapaproduccion> ejecucionprocesocalidadList;
    @OneToMany(mappedBy = "empleado")
    private List<Mantenimientocorrectivo> mantenimientocorrectivoList;
    @OneToMany(mappedBy = "empleado")
    private List<Detalleplanificacioncalidad> detalleplanificacioncalidadList;
    @OneToMany(mappedBy = "idempleado")
    private List<Disponibilidadhoraria> disponibilidadhorariaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado1")
    private List<Asistencia> asistenciaList;
    @JoinColumn(name = "usuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "tipodocumento", referencedColumnName = "idtipodocumento")
    @ManyToOne
    private Tipodocumento tipodocumento;
    @JoinColumn(name = "domicilio", referencedColumnName = "iddomicilio")
    @ManyToOne
    private Domicilio domicilio;
    @JoinColumn(name = "categoria", referencedColumnName = "idcategoria")
    @ManyToOne
    private Categoria categoria;
    @JoinColumn(name = "cargo", referencedColumnName = "idcargo")
    @ManyToOne
    private Cargo cargo;

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

    public List<Detalleplanificacionproduccion> getDetalleplanificacionproduccionList() {
        return detalleplanificacionproduccionList;
    }

    public void setDetalleplanificacionproduccionList(List<Detalleplanificacionproduccion> detalleplanificacionproduccionList) {
        this.detalleplanificacionproduccionList = detalleplanificacionproduccionList;
    }

    public List<Empleadoxturno> getEmpleadoxturnoList() {
        return empleadoxturnoList;
    }

    public void setEmpleadoxturnoList(List<Empleadoxturno> empleadoxturnoList) {
        this.empleadoxturnoList = empleadoxturnoList;
    }

    public List<Ejecucionetapaproduccion> getEjecucionetapaproduccionList() {
        return ejecucionetapaproduccionList;
    }

    public void setEjecucionetapaproduccionList(List<Ejecucionetapaproduccion> ejecucionetapaproduccionList) {
        this.ejecucionetapaproduccionList = ejecucionetapaproduccionList;
    }

    public List<Mantenimientocorrectivo> getMantenimientocorrectivoList() {
        return mantenimientocorrectivoList;
    }

    public void setMantenimientocorrectivoList(List<Mantenimientocorrectivo> mantenimientocorrectivoList) {
        this.mantenimientocorrectivoList = mantenimientocorrectivoList;
    }

    public List<Detalleplanificacioncalidad> getDetalleplanificacioncalidadList() {
        return detalleplanificacioncalidadList;
    }

    public void setDetalleplanificacioncalidadList(List<Detalleplanificacioncalidad> detalleplanificacioncalidadList) {
        this.detalleplanificacioncalidadList = detalleplanificacioncalidadList;
    }

    public List<Disponibilidadhoraria> getDisponibilidadhorariaList() {
        return disponibilidadhorariaList;
    }

    public void setDisponibilidadhorariaList(List<Disponibilidadhoraria> disponibilidadhorariaList) {
        this.disponibilidadhorariaList = disponibilidadhorariaList;
    }

    public List<Asistencia> getAsistenciaList() {
        return asistenciaList;
    }

    public void setAsistenciaList(List<Asistencia> asistenciaList) {
        this.asistenciaList = asistenciaList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tipodocumento getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(Tipodocumento tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<Ejecucionetapaproduccion> getEjecucionprocesocalidadList() {
        return ejecucionprocesocalidadList;
    }

    public void setEjecucionprocesocalidadList(List<Ejecucionetapaproduccion> ejecucionprocesocalidadList) {
        this.ejecucionprocesocalidadList = ejecucionprocesocalidadList;
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
        return "metalsoft.datos.jpa.entity.Empleado[ idempleado=" + idempleado + " ]";
    }
}
