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
@Table(name = "domicilio")
@NamedQueries({
    @NamedQuery(name = "Domicilio.findAll", query = "SELECT d FROM Domicilio d"),
    @NamedQuery(name = "Domicilio.findByIddomicilio", query = "SELECT d FROM Domicilio d WHERE d.iddomicilio = :iddomicilio"),
    @NamedQuery(name = "Domicilio.findByCalle", query = "SELECT d FROM Domicilio d WHERE d.calle = :calle"),
    @NamedQuery(name = "Domicilio.findByNumerocalle", query = "SELECT d FROM Domicilio d WHERE d.numerocalle = :numerocalle"),
    @NamedQuery(name = "Domicilio.findByPiso", query = "SELECT d FROM Domicilio d WHERE d.piso = :piso"),
    @NamedQuery(name = "Domicilio.findByDepto", query = "SELECT d FROM Domicilio d WHERE d.depto = :depto"),
    @NamedQuery(name = "Domicilio.findByTorre", query = "SELECT d FROM Domicilio d WHERE d.torre = :torre")})
public class Domicilio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "iddomicilio")
    private Long iddomicilio;
    @Column(name = "calle")
    private String calle;
    @Column(name = "numerocalle")
    private Integer numerocalle;
    @Column(name = "piso")
    private Integer piso;
    @Column(name = "depto")
    private String depto;
    @Column(name = "torre")
    private String torre;
    @OneToMany(mappedBy = "domicilio")
    private Set<Responsable> responsableSet;
    @OneToMany(mappedBy = "domicilio1")
    private Set<Responsable> responsableSet1;
    @OneToMany(mappedBy = "domicilio")
    private Set<Empresametalurgica> empresametalurgicaSet;
    @OneToMany(mappedBy = "domicilio1")
    private Set<Empresametalurgica> empresametalurgicaSet1;
    @OneToMany(mappedBy = "domicilio")
    private Set<Cliente> clienteSet;
    @OneToMany(mappedBy = "domicilio1")
    private Set<Cliente> clienteSet1;
    @OneToMany(mappedBy = "domicilio")
    private Set<Proveedor> proveedorSet;
    @OneToMany(mappedBy = "domicilio1")
    private Set<Proveedor> proveedorSet1;
    @OneToMany(mappedBy = "domicilio")
    private Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet;
    @OneToMany(mappedBy = "domicilio1")
    private Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet1;
    @JoinColumn(name = "barrio", referencedColumnName = "idbarrio")
    @ManyToOne
    private Barrio barrio;
    @JoinColumn(name = "barrio", referencedColumnName = "idbarrio")
    @ManyToOne
    private Barrio barrio1;
    @OneToMany(mappedBy = "domicilio")
    private Set<Empleado> empleadoSet;
    @OneToMany(mappedBy = "domicilio1")
    private Set<Empleado> empleadoSet1;

    public Domicilio() {
    }

    public Domicilio(Long iddomicilio) {
        this.iddomicilio = iddomicilio;
    }

    public Long getIddomicilio() {
        return iddomicilio;
    }

    public void setIddomicilio(Long iddomicilio) {
        this.iddomicilio = iddomicilio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumerocalle() {
        return numerocalle;
    }

    public void setNumerocalle(Integer numerocalle) {
        this.numerocalle = numerocalle;
    }

    public Integer getPiso() {
        return piso;
    }

    public void setPiso(Integer piso) {
        this.piso = piso;
    }

    public String getDepto() {
        return depto;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }

    public String getTorre() {
        return torre;
    }

    public void setTorre(String torre) {
        this.torre = torre;
    }

    public Set<Responsable> getResponsableSet() {
        return responsableSet;
    }

    public void setResponsableSet(Set<Responsable> responsableSet) {
        this.responsableSet = responsableSet;
    }

    public Set<Responsable> getResponsableSet1() {
        return responsableSet1;
    }

    public void setResponsableSet1(Set<Responsable> responsableSet1) {
        this.responsableSet1 = responsableSet1;
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

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public Barrio getBarrio1() {
        return barrio1;
    }

    public void setBarrio1(Barrio barrio1) {
        this.barrio1 = barrio1;
    }

    public Set<Empleado> getEmpleadoSet() {
        return empleadoSet;
    }

    public void setEmpleadoSet(Set<Empleado> empleadoSet) {
        this.empleadoSet = empleadoSet;
    }

    public Set<Empleado> getEmpleadoSet1() {
        return empleadoSet1;
    }

    public void setEmpleadoSet1(Set<Empleado> empleadoSet1) {
        this.empleadoSet1 = empleadoSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddomicilio != null ? iddomicilio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Domicilio)) {
            return false;
        }
        Domicilio other = (Domicilio) object;
        if ((this.iddomicilio == null && other.iddomicilio != null) || (this.iddomicilio != null && !this.iddomicilio.equals(other.iddomicilio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Domicilio[iddomicilio=" + iddomicilio + "]";
    }

}