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
@Table(name = "empresametalurgica")
@NamedQueries({
    @NamedQuery(name = "Empresametalurgica.findAll", query = "SELECT e FROM Empresametalurgica e"),
    @NamedQuery(name = "Empresametalurgica.findByIdempresametalurgica", query = "SELECT e FROM Empresametalurgica e WHERE e.idempresametalurgica = :idempresametalurgica"),
    @NamedQuery(name = "Empresametalurgica.findByNroempresametalurgica", query = "SELECT e FROM Empresametalurgica e WHERE e.nroempresametalurgica = :nroempresametalurgica"),
    @NamedQuery(name = "Empresametalurgica.findByRazonsocial", query = "SELECT e FROM Empresametalurgica e WHERE e.razonsocial = :razonsocial"),
    @NamedQuery(name = "Empresametalurgica.findByTelefono", query = "SELECT e FROM Empresametalurgica e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "Empresametalurgica.findByCelular", query = "SELECT e FROM Empresametalurgica e WHERE e.celular = :celular"),
    @NamedQuery(name = "Empresametalurgica.findByMail", query = "SELECT e FROM Empresametalurgica e WHERE e.mail = :mail"),
    @NamedQuery(name = "Empresametalurgica.findByFechaalta", query = "SELECT e FROM Empresametalurgica e WHERE e.fechaalta = :fechaalta"),
    @NamedQuery(name = "Empresametalurgica.findByFechabaja", query = "SELECT e FROM Empresametalurgica e WHERE e.fechabaja = :fechabaja"),
    @NamedQuery(name = "Empresametalurgica.findByCuil", query = "SELECT e FROM Empresametalurgica e WHERE e.cuil = :cuil"),
    @NamedQuery(name = "Empresametalurgica.findByCuit", query = "SELECT e FROM Empresametalurgica e WHERE e.cuit = :cuit")})
public class Empresametalurgica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresametalurgica_seq")
    @SequenceGenerator(name = "empresametalurgica_seq", sequenceName = "empresametalurgica_idempresametalurgica_seq", allocationSize = 1)
    @Column(name = "idempresametalurgica")
    private Long idempresametalurgica;
    @Column(name = "nroempresametalurgica")
    private BigInteger nroempresametalurgica;
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
    @JoinColumn(name = "responsable", referencedColumnName = "idresponsable")
    @ManyToOne
    private Responsable responsable;
    @JoinColumn(name = "domicilio", referencedColumnName = "iddomicilio")
    @ManyToOne
    private Domicilio domicilio;
    @JoinColumn(name = "condicioniva", referencedColumnName = "idcondicioniva")
    @ManyToOne
    private Condicioniva condicioniva;
    @OneToMany(mappedBy = "empresa")
    private List<Trabajotercerizado> trabajotercerizadoList;

    public Empresametalurgica() {
    }

    public Empresametalurgica(Long idempresametalurgica) {
        this.idempresametalurgica = idempresametalurgica;
    }

    public Long getIdempresametalurgica() {
        return idempresametalurgica;
    }

    public void setIdempresametalurgica(Long idempresametalurgica) {
        this.idempresametalurgica = idempresametalurgica;
    }

    public BigInteger getNroempresametalurgica() {
        return nroempresametalurgica;
    }

    public void setNroempresametalurgica(BigInteger nroempresametalurgica) {
        this.nroempresametalurgica = nroempresametalurgica;
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

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
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

    public List<Trabajotercerizado> getTrabajotercerizadoList() {
        return trabajotercerizadoList;
    }

    public void setTrabajotercerizadoList(List<Trabajotercerizado> trabajotercerizadoList) {
        this.trabajotercerizadoList = trabajotercerizadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idempresametalurgica != null ? idempresametalurgica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresametalurgica)) {
            return false;
        }
        Empresametalurgica other = (Empresametalurgica) object;
        if ((this.idempresametalurgica == null && other.idempresametalurgica != null) || (this.idempresametalurgica != null && !this.idempresametalurgica.equals(other.idempresametalurgica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Empresametalurgica[ idempresametalurgica=" + idempresametalurgica + " ]";
    }
    
}
