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
@Table(name = "cliente")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByNrocliente", query = "SELECT c FROM Cliente c WHERE c.nrocliente = :nrocliente"),
    @NamedQuery(name = "Cliente.findByIdcliente", query = "SELECT c FROM Cliente c WHERE c.idcliente = :idcliente"),
    @NamedQuery(name = "Cliente.findByEsmoroso", query = "SELECT c FROM Cliente c WHERE c.esmoroso = :esmoroso"),
    @NamedQuery(name = "Cliente.findByRazonsocial", query = "SELECT c FROM Cliente c WHERE c.razonsocial = :razonsocial"),
    @NamedQuery(name = "Cliente.findByTelefono", query = "SELECT c FROM Cliente c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Cliente.findByCelular", query = "SELECT c FROM Cliente c WHERE c.celular = :celular"),
    @NamedQuery(name = "Cliente.findByMail", query = "SELECT c FROM Cliente c WHERE c.mail = :mail"),
    @NamedQuery(name = "Cliente.findByFechaalta", query = "SELECT c FROM Cliente c WHERE c.fechaalta = :fechaalta"),
    @NamedQuery(name = "Cliente.findByFechabaja", query = "SELECT c FROM Cliente c WHERE c.fechabaja = :fechabaja"),
    @NamedQuery(name = "Cliente.findByCuil", query = "SELECT c FROM Cliente c WHERE c.cuil = :cuil"),
    @NamedQuery(name = "Cliente.findByCuit", query = "SELECT c FROM Cliente c WHERE c.cuit = :cuit")})
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "nrocliente")
    private BigInteger nrocliente;
    @Id
    @Basic(optional = false)
    @Column(name = "idcliente")
    private Long idcliente;
    @Column(name = "esmoroso")
    private Boolean esmoroso;
    @Column(name = "razonsocial")
    private String razonsocial;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "celular")
    private String celular;
    @Column(name = "mail")
    private String mail;
    @Column(name = "fechaalta")
    @Temporal(TemporalType.DATE)
    private Date fechaalta;
    @Column(name = "fechabaja")
    @Temporal(TemporalType.DATE)
    private Date fechabaja;
    @Column(name = "cuil")
    private String cuil;
    @Column(name = "cuit")
    private String cuit;
    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> pedidoSet;
    @OneToMany(mappedBy = "cliente1")
    private Set<Pedido> pedidoSet1;
    @JoinColumn(name = "condicioniva", referencedColumnName = "idcondicioniva")
    @ManyToOne
    private Condicioniva condicioniva;
    @JoinColumn(name = "condicioniva", referencedColumnName = "idcondicioniva")
    @ManyToOne
    private Condicioniva condicioniva1;
    @JoinColumn(name = "domicilio", referencedColumnName = "iddomicilio")
    @ManyToOne
    private Domicilio domicilio;
    @JoinColumn(name = "domicilio", referencedColumnName = "iddomicilio")
    @ManyToOne
    private Domicilio domicilio1;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadocliente estado;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadocliente estado1;
    @JoinColumn(name = "prioridad", referencedColumnName = "idprioridad")
    @ManyToOne
    private Prioridad prioridad;
    @JoinColumn(name = "prioridad", referencedColumnName = "idprioridad")
    @ManyToOne
    private Prioridad prioridad1;
    @JoinColumn(name = "responsable", referencedColumnName = "idresponsable")
    @ManyToOne
    private Responsable responsable;
    @JoinColumn(name = "responsable", referencedColumnName = "idresponsable")
    @ManyToOne
    private Responsable responsable1;
    @JoinColumn(name = "usuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "usuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuario1;
    @OneToMany(mappedBy = "cliente")
    private Set<Reclamocliente> reclamoclienteSet;
    @OneToMany(mappedBy = "cliente1")
    private Set<Reclamocliente> reclamoclienteSet1;

    public Cliente() {
    }

    public Cliente(Long idcliente) {
        this.idcliente = idcliente;
    }

    public BigInteger getNrocliente() {
        return nrocliente;
    }

    public void setNrocliente(BigInteger nrocliente) {
        this.nrocliente = nrocliente;
    }

    public Long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Long idcliente) {
        this.idcliente = idcliente;
    }

    public Boolean getEsmoroso() {
        return esmoroso;
    }

    public void setEsmoroso(Boolean esmoroso) {
        this.esmoroso = esmoroso;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getFechaalta() {
        return fechaalta;
    }

    public void setFechaalta(Date fechaalta) {
        this.fechaalta = fechaalta;
    }

    public Date getFechabaja() {
        return fechabaja;
    }

    public void setFechabaja(Date fechabaja) {
        this.fechabaja = fechabaja;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public Set<Pedido> getPedidoSet() {
        return pedidoSet;
    }

    public void setPedidoSet(Set<Pedido> pedidoSet) {
        this.pedidoSet = pedidoSet;
    }

    public Set<Pedido> getPedidoSet1() {
        return pedidoSet1;
    }

    public void setPedidoSet1(Set<Pedido> pedidoSet1) {
        this.pedidoSet1 = pedidoSet1;
    }

    public Condicioniva getCondicioniva() {
        return condicioniva;
    }

    public void setCondicioniva(Condicioniva condicioniva) {
        this.condicioniva = condicioniva;
    }

    public Condicioniva getCondicioniva1() {
        return condicioniva1;
    }

    public void setCondicioniva1(Condicioniva condicioniva1) {
        this.condicioniva1 = condicioniva1;
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

    public Estadocliente getEstado() {
        return estado;
    }

    public void setEstado(Estadocliente estado) {
        this.estado = estado;
    }

    public Estadocliente getEstado1() {
        return estado1;
    }

    public void setEstado1(Estadocliente estado1) {
        this.estado1 = estado1;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public Prioridad getPrioridad1() {
        return prioridad1;
    }

    public void setPrioridad1(Prioridad prioridad1) {
        this.prioridad1 = prioridad1;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public Responsable getResponsable1() {
        return responsable1;
    }

    public void setResponsable1(Responsable responsable1) {
        this.responsable1 = responsable1;
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

    public Set<Reclamocliente> getReclamoclienteSet() {
        return reclamoclienteSet;
    }

    public void setReclamoclienteSet(Set<Reclamocliente> reclamoclienteSet) {
        this.reclamoclienteSet = reclamoclienteSet;
    }

    public Set<Reclamocliente> getReclamoclienteSet1() {
        return reclamoclienteSet1;
    }

    public void setReclamoclienteSet1(Set<Reclamocliente> reclamoclienteSet1) {
        this.reclamoclienteSet1 = reclamoclienteSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Cliente[idcliente=" + idcliente + "]";
    }

}
