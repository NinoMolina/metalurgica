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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
    @SequenceGenerator(name = "cliente_seq", sequenceName = "cliente_idcliente_seq", allocationSize = 1)
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
    private List<Pedido> pedidoList;
    @JoinColumn(name = "usuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "responsable", referencedColumnName = "idresponsable")
    @ManyToOne
    private Responsable responsable;
    @JoinColumn(name = "prioridad", referencedColumnName = "idprioridad")
    @ManyToOne
    private Prioridad prioridad;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadocliente estado;
    @JoinColumn(name = "domicilio", referencedColumnName = "iddomicilio")
    @ManyToOne
    private Domicilio domicilio;
    @JoinColumn(name = "condicioniva", referencedColumnName = "idcondicioniva")
    @ManyToOne
    private Condicioniva condicioniva;
    @OneToMany(mappedBy = "cliente")
    private List<Reclamocliente> reclamoclienteList;

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

    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public Estadocliente getEstado() {
        return estado;
    }

    public void setEstado(Estadocliente estado) {
        this.estado = estado;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Condicioniva getCondicioniva() {
        return condicioniva;
    }

    public void setCondicioniva(Condicioniva condicioniva) {
        this.condicioniva = condicioniva;
    }

    public List<Reclamocliente> getReclamoclienteList() {
        return reclamoclienteList;
    }

    public void setReclamoclienteList(List<Reclamocliente> reclamoclienteList) {
        this.reclamoclienteList = reclamoclienteList;
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
        return "metalsoft.datos.jpa.entity.Cliente[ idcliente=" + idcliente + " ]";
    }
    
}
