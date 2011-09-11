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
@Table(name = "condicioniva")
@NamedQueries({
    @NamedQuery(name = "Condicioniva.findAll", query = "SELECT c FROM Condicioniva c"),
    @NamedQuery(name = "Condicioniva.findByIdcondicioniva", query = "SELECT c FROM Condicioniva c WHERE c.idcondicioniva = :idcondicioniva"),
    @NamedQuery(name = "Condicioniva.findByNombre", query = "SELECT c FROM Condicioniva c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Condicioniva.findByDescripcion", query = "SELECT c FROM Condicioniva c WHERE c.descripcion = :descripcion")})
public class Condicioniva implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "condicioniva_seq")
    @SequenceGenerator(name = "condicioniva_seq", sequenceName = "condicioniva_idcondicioniva_seq", allocationSize = 1)
    @Column(name = "idcondicioniva")
    private Long idcondicioniva;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "condicioniva")
    private List<Empresametalurgica> empresametalurgicaList;
    @OneToMany(mappedBy = "condicioniva")
    private List<Cliente> clienteList;
    @OneToMany(mappedBy = "condicion")
    private List<Proveedor> proveedorList;
    @OneToMany(mappedBy = "condicioniva")
    private List<Proveedormantenimientomaquina> proveedormantenimientomaquinaList;

    public Condicioniva() {
    }

    public Condicioniva(Long idcondicioniva) {
        this.idcondicioniva = idcondicioniva;
    }

    public Long getIdcondicioniva() {
        return idcondicioniva;
    }

    public void setIdcondicioniva(Long idcondicioniva) {
        this.idcondicioniva = idcondicioniva;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idcondicioniva != null ? idcondicioniva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Condicioniva)) {
            return false;
        }
        Condicioniva other = (Condicioniva) object;
        if ((this.idcondicioniva == null && other.idcondicioniva != null) || (this.idcondicioniva != null && !this.idcondicioniva.equals(other.idcondicioniva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Condicioniva[ idcondicioniva=" + idcondicioniva + " ]";
    }
    
}
