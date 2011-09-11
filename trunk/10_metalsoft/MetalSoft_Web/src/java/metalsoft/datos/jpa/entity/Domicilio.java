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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "domicilio_seq")
    @SequenceGenerator(name = "domicilio_seq", sequenceName = "domicilio_iddomicilio_seq", allocationSize = 1)
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
    private List<Responsable> responsableList;
    @OneToMany(mappedBy = "domicilio")
    private List<Empresametalurgica> empresametalurgicaList;
    @OneToMany(mappedBy = "domicilio")
    private List<Cliente> clienteList;
    @OneToMany(mappedBy = "domicilio")
    private List<Proveedor> proveedorList;
    @OneToMany(mappedBy = "domicilio")
    private List<Proveedormantenimientomaquina> proveedormantenimientomaquinaList;
    @JoinColumn(name = "barrio", referencedColumnName = "idbarrio")
    @ManyToOne
    private Barrio barrio;
    @OneToMany(mappedBy = "domicilio")
    private List<Empleado> empleadoList;

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

    public List<Responsable> getResponsableList() {
        return responsableList;
    }

    public void setResponsableList(List<Responsable> responsableList) {
        this.responsableList = responsableList;
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

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
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
        return "metalsoft.datos.jpa.entity.Domicilio[ iddomicilio=" + iddomicilio + " ]";
    }
    
}
