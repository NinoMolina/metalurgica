/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "responsable_seq")
    @SequenceGenerator(name = "responsable_seq", sequenceName = "responsable_idresponsable_seq", allocationSize = 1)
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
    @JoinColumn(name = "tipodocumento", referencedColumnName = "idtipodocumento")
    @ManyToOne
    private Tipodocumento tipodocumento;
    @JoinColumn(name = "domicilio", referencedColumnName = "iddomicilio")
    @ManyToOne
    private Domicilio domicilio;
    @OneToMany(mappedBy = "responsable")
    private List<Empresametalurgica> empresametalurgicaList;
    @OneToMany(mappedBy = "responsable")
    private List<Cliente> clienteList;
    @OneToMany(mappedBy = "responsable")
    private List<Proveedor> proveedorList;
    @OneToMany(mappedBy = "responsable")
    private List<Proveedormantenimientomaquina> proveedormantenimientomaquinaList;

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

    public List<Empresametalurgica> getEmpresametalurgicaList() {
        return empresametalurgicaList;
    }

    public void setEmpresametalurgicaList(List<Empresametalurgica> empresametalurgicaList) {
        this.empresametalurgicaList = empresametalurgicaList;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    public List<Proveedor> getProveedorList() {
        return proveedorList;
    }

    public void setProveedorList(List<Proveedor> proveedorList) {
        this.proveedorList = proveedorList;
    }

    public List<Proveedormantenimientomaquina> getProveedormantenimientomaquinaList() {
        return proveedormantenimientomaquinaList;
    }

    public void setProveedormantenimientomaquinaList(List<Proveedormantenimientomaquina> proveedormantenimientomaquinaList) {
        this.proveedormantenimientomaquinaList = proveedormantenimientomaquinaList;
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
        return "metalsoft.datos.jpa.entity.Responsable[ idresponsable=" + idresponsable + " ]";
    }
    
}
