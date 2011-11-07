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
@Table(name = "proveedormantenimientomaquina")
@NamedQueries({
    @NamedQuery(name = "Proveedormantenimientomaquina.findAll", query = "SELECT p FROM Proveedormantenimientomaquina p"),
    @NamedQuery(name = "Proveedormantenimientomaquina.findByIdproveedormantenimiento", query = "SELECT p FROM Proveedormantenimientomaquina p WHERE p.idproveedormantenimiento = :idproveedormantenimiento"),
    @NamedQuery(name = "Proveedormantenimientomaquina.findByNroproveedor", query = "SELECT p FROM Proveedormantenimientomaquina p WHERE p.nroproveedor = :nroproveedor"),
    @NamedQuery(name = "Proveedormantenimientomaquina.findByRazonsocial", query = "SELECT p FROM Proveedormantenimientomaquina p WHERE p.razonsocial = :razonsocial"),
    @NamedQuery(name = "Proveedormantenimientomaquina.findByTelefono", query = "SELECT p FROM Proveedormantenimientomaquina p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Proveedormantenimientomaquina.findByCelular", query = "SELECT p FROM Proveedormantenimientomaquina p WHERE p.celular = :celular"),
    @NamedQuery(name = "Proveedormantenimientomaquina.findByMail", query = "SELECT p FROM Proveedormantenimientomaquina p WHERE p.mail = :mail"),
    @NamedQuery(name = "Proveedormantenimientomaquina.findByFechaalta", query = "SELECT p FROM Proveedormantenimientomaquina p WHERE p.fechaalta = :fechaalta"),
    @NamedQuery(name = "Proveedormantenimientomaquina.findByFechabaja", query = "SELECT p FROM Proveedormantenimientomaquina p WHERE p.fechabaja = :fechabaja"),
    @NamedQuery(name = "Proveedormantenimientomaquina.findByCuil", query = "SELECT p FROM Proveedormantenimientomaquina p WHERE p.cuil = :cuil"),
    @NamedQuery(name = "Proveedormantenimientomaquina.findByCuit", query = "SELECT p FROM Proveedormantenimientomaquina p WHERE p.cuit = :cuit")})
public class Proveedormantenimientomaquina implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idproveedormantenimiento")
    private Long idproveedormantenimiento;
    @Column(name = "nroproveedor")
    private BigInteger nroproveedor;
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
    @OneToMany(mappedBy = "proveedormantenimiento")
    private List<Mantenimientocorrectivo> mantenimientocorrectivoList;
    @OneToMany(mappedBy = "proveedormantenimiento")
    private List<Mantenimientopreventivo> mantenimientopreventivoList;
    @JoinColumn(name = "responsable", referencedColumnName = "idresponsable")
    @ManyToOne
    private Responsable responsable;
    @JoinColumn(name = "domicilio", referencedColumnName = "iddomicilio")
    @ManyToOne
    private Domicilio domicilio;
    @JoinColumn(name = "condicioniva", referencedColumnName = "idcondicioniva")
    @ManyToOne
    private Condicioniva condicioniva;

    public Proveedormantenimientomaquina() {
    }

    public Proveedormantenimientomaquina(Long idproveedormantenimiento) {
        this.idproveedormantenimiento = idproveedormantenimiento;
    }

    public Long getIdproveedormantenimiento() {
        return idproveedormantenimiento;
    }

    public void setIdproveedormantenimiento(Long idproveedormantenimiento) {
        this.idproveedormantenimiento = idproveedormantenimiento;
    }

    public BigInteger getNroproveedor() {
        return nroproveedor;
    }

    public void setNroproveedor(BigInteger nroproveedor) {
        this.nroproveedor = nroproveedor;
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

    public List<Mantenimientocorrectivo> getMantenimientocorrectivoList() {
        return mantenimientocorrectivoList;
    }

    public void setMantenimientocorrectivoList(List<Mantenimientocorrectivo> mantenimientocorrectivoList) {
        this.mantenimientocorrectivoList = mantenimientocorrectivoList;
    }

    public List<Mantenimientopreventivo> getMantenimientopreventivoList() {
        return mantenimientopreventivoList;
    }

    public void setMantenimientopreventivoList(List<Mantenimientopreventivo> mantenimientopreventivoList) {
        this.mantenimientopreventivoList = mantenimientopreventivoList;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproveedormantenimiento != null ? idproveedormantenimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedormantenimientomaquina)) {
            return false;
        }
        Proveedormantenimientomaquina other = (Proveedormantenimientomaquina) object;
        if ((this.idproveedormantenimiento == null && other.idproveedormantenimiento != null) || (this.idproveedormantenimiento != null && !this.idproveedormantenimiento.equals(other.idproveedormantenimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Proveedormantenimientomaquina[ idproveedormantenimiento=" + idproveedormantenimiento + " ]";
    }
    
}
