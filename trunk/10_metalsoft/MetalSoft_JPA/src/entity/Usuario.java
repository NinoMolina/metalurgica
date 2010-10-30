/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdusuario", query = "SELECT u FROM Usuario u WHERE u.idusuario = :idusuario"),
    @NamedQuery(name = "Usuario.findByUsuario", query = "SELECT u FROM Usuario u WHERE u.usuario = :usuario"),
    @NamedQuery(name = "Usuario.findByClave", query = "SELECT u FROM Usuario u WHERE u.clave = :clave")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idusuario")
    private Long idusuario;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "clave")
    private String clave;
    @OneToMany(mappedBy = "usuario")
    private Set<Cliente> clienteSet;
    @OneToMany(mappedBy = "usuario1")
    private Set<Cliente> clienteSet1;
    @OneToMany(mappedBy = "usuario")
    private Set<Factura> facturaSet;
    @OneToMany(mappedBy = "usuario1")
    private Set<Factura> facturaSet1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Set<Usuarioxrol> usuarioxrolSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario1")
    private Set<Usuarioxrol> usuarioxrolSet1;
    @OneToMany(mappedBy = "usuario")
    private Set<Sesion> sesionSet;
    @OneToMany(mappedBy = "usuario1")
    private Set<Sesion> sesionSet1;
    @OneToMany(mappedBy = "usuario")
    private Set<Empleado> empleadoSet;
    @OneToMany(mappedBy = "usuario1")
    private Set<Empleado> empleadoSet1;
    @OneToMany(mappedBy = "usuario")
    private Set<Comprobantepago> comprobantepagoSet;
    @OneToMany(mappedBy = "usuario1")
    private Set<Comprobantepago> comprobantepagoSet1;

    public Usuario() {
    }

    public Usuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    public Set<Factura> getFacturaSet() {
        return facturaSet;
    }

    public void setFacturaSet(Set<Factura> facturaSet) {
        this.facturaSet = facturaSet;
    }

    public Set<Factura> getFacturaSet1() {
        return facturaSet1;
    }

    public void setFacturaSet1(Set<Factura> facturaSet1) {
        this.facturaSet1 = facturaSet1;
    }

    public Set<Usuarioxrol> getUsuarioxrolSet() {
        return usuarioxrolSet;
    }

    public void setUsuarioxrolSet(Set<Usuarioxrol> usuarioxrolSet) {
        this.usuarioxrolSet = usuarioxrolSet;
    }

    public Set<Usuarioxrol> getUsuarioxrolSet1() {
        return usuarioxrolSet1;
    }

    public void setUsuarioxrolSet1(Set<Usuarioxrol> usuarioxrolSet1) {
        this.usuarioxrolSet1 = usuarioxrolSet1;
    }

    public Set<Sesion> getSesionSet() {
        return sesionSet;
    }

    public void setSesionSet(Set<Sesion> sesionSet) {
        this.sesionSet = sesionSet;
    }

    public Set<Sesion> getSesionSet1() {
        return sesionSet1;
    }

    public void setSesionSet1(Set<Sesion> sesionSet1) {
        this.sesionSet1 = sesionSet1;
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

    public Set<Comprobantepago> getComprobantepagoSet() {
        return comprobantepagoSet;
    }

    public void setComprobantepagoSet(Set<Comprobantepago> comprobantepagoSet) {
        this.comprobantepagoSet = comprobantepagoSet;
    }

    public Set<Comprobantepago> getComprobantepagoSet1() {
        return comprobantepagoSet1;
    }

    public void setComprobantepagoSet1(Set<Comprobantepago> comprobantepagoSet1) {
        this.comprobantepagoSet1 = comprobantepagoSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Usuario[idusuario=" + idusuario + "]";
    }

}
