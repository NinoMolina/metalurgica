/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "responsable")
@NamedQueries({
    @NamedQuery(name = "Responsable.findAll", query = "SELECT r FROM Responsable r"),
    @NamedQuery(name = "Responsable.findByIdresponsable", query = "SELECT r FROM Responsable r WHERE r.idresponsable = :idresponsable"),
    @NamedQuery(name = "Responsable.findByNombre", query = "SELECT r FROM Responsable r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Responsable.findByApellido", query = "SELECT r FROM Responsable r WHERE r.apellido = :apellido"),
    @NamedQuery(name = "Responsable.findByTelefono", query = "SELECT r FROM Responsable r WHERE r.telefono = :telefono"),
    @NamedQuery(name = "Responsable.findByEmail", query = "SELECT r FROM Responsable r WHERE r.email = :email"),
    @NamedQuery(name = "Responsable.findByNrodocumento", query = "SELECT r FROM Responsable r WHERE r.nrodocumento = :nrodocumento"),
    @NamedQuery(name = "Responsable.findByFax", query = "SELECT r FROM Responsable r WHERE r.fax = :fax")})
public class Responsable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idresponsable")
    private Long idresponsable;
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
    @Column(name = "fax")
    private String fax;
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
    @OneToMany(mappedBy = "responsable")
    private Set<Empresametalurgica> empresametalurgicaSet;
    @OneToMany(mappedBy = "responsable1")
    private Set<Empresametalurgica> empresametalurgicaSet1;
    @OneToMany(mappedBy = "responsable")
    private Set<Cliente> clienteSet;
    @OneToMany(mappedBy = "responsable1")
    private Set<Cliente> clienteSet1;
    @OneToMany(mappedBy = "responsable")
    private Set<Proveedor> proveedorSet;
    @OneToMany(mappedBy = "responsable1")
    private Set<Proveedor> proveedorSet1;
    @OneToMany(mappedBy = "responsable")
    private Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet;
    @OneToMany(mappedBy = "responsable1")
    private Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet1;

    public Responsable() {
    }

    public Responsable(Long idresponsable) {
        this.idresponsable = idresponsable;
    }

    public Long getIdresponsable() {
        return idresponsable;
    }

    public void setIdresponsable(Long idresponsable) {
        this.idresponsable = idresponsable;
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

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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

    public Set<Empresametalurgica> getEmpresametalurgicaSet() {
        return empresametalurgicaSet;
    }

    public void setEmpresametalurgicaSet(Set<Empresametalurgica> empresametalurgicaSet) {
        this.empresametalurgicaSet = empresametalurgicaSet;
    }

    public Set<Empresametalurgica> getEmpresametalurgicaSet1() {
        return empresametalurgicaSet1;
    }

    public void setEmpresametalurgicaSet1(Set<Empresametalurgica> empresametalurgicaSet1) {
        this.empresametalurgicaSet1 = empresametalurgicaSet1;
    }

    public Set<Cliente> getClienteSet() {
        return clienteSet;
    }

    public void setClienteSet(Set<Cliente> clienteSet) {
        this.clienteSet = clienteSet;
    }

    public Set<Cliente> getClienteSet1() {
        return clienteSet1;
    }

    public void setClienteSet1(Set<Cliente> clienteSet1) {
        this.clienteSet1 = clienteSet1;
    }

    public Set<Proveedor> getProveedorSet() {
        return proveedorSet;
    }

    public void setProveedorSet(Set<Proveedor> proveedorSet) {
        this.proveedorSet = proveedorSet;
    }

    public Set<Proveedor> getProveedorSet1() {
        return proveedorSet1;
    }

    public void setProveedorSet1(Set<Proveedor> proveedorSet1) {
        this.proveedorSet1 = proveedorSet1;
    }

    public Set<Proveedormantenimientomaquina> getProveedormantenimientomaquinaSet() {
        return proveedormantenimientomaquinaSet;
    }

    public void setProveedormantenimientomaquinaSet(Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet) {
        this.proveedormantenimientomaquinaSet = proveedormantenimientomaquinaSet;
    }

    public Set<Proveedormantenimientomaquina> getProveedormantenimientomaquinaSet1() {
        return proveedormantenimientomaquinaSet1;
    }

    public void setProveedormantenimientomaquinaSet1(Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet1) {
        this.proveedormantenimientomaquinaSet1 = proveedormantenimientomaquinaSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idresponsable != null ? idresponsable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Responsable)) {
            return false;
        }
        Responsable other = (Responsable) object;
        if ((this.idresponsable == null && other.idresponsable != null) || (this.idresponsable != null && !this.idresponsable.equals(other.idresponsable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Responsable[idresponsable=" + idresponsable + "]";
    }

}
