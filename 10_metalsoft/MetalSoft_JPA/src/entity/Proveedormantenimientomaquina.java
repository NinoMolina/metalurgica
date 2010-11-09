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
    private Set<Mantenimientocorrectivo> mantenimientocorrectivoSet;
    @OneToMany(mappedBy = "proveedormantenimiento1")
    private Set<Mantenimientocorrectivo> mantenimientocorrectivoSet1;
    @OneToMany(mappedBy = "proveedormantenimiento")
    private Set<Mantenimientopreventivo> mantenimientopreventivoSet;
    @OneToMany(mappedBy = "proveedormantenimiento1")
    private Set<Mantenimientopreventivo> mantenimientopreventivoSet1;
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
    @JoinColumn(name = "responsable", referencedColumnName = "idresponsable")
    @ManyToOne
    private Responsable responsable;
    @JoinColumn(name = "responsable", referencedColumnName = "idresponsable")
    @ManyToOne
    private Responsable responsable1;

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

    public Set<Mantenimientocorrectivo> getMantenimientocorrectivoSet() {
        return mantenimientocorrectivoSet;
    }

    public void setMantenimientocorrectivoSet(Set<Mantenimientocorrectivo> mantenimientocorrectivoSet) {
        this.mantenimientocorrectivoSet = mantenimientocorrectivoSet;
    }

    public Set<Mantenimientocorrectivo> getMantenimientocorrectivoSet1() {
        return mantenimientocorrectivoSet1;
    }

    public void setMantenimientocorrectivoSet1(Set<Mantenimientocorrectivo> mantenimientocorrectivoSet1) {
        this.mantenimientocorrectivoSet1 = mantenimientocorrectivoSet1;
    }

    public Set<Mantenimientopreventivo> getMantenimientopreventivoSet() {
        return mantenimientopreventivoSet;
    }

    public void setMantenimientopreventivoSet(Set<Mantenimientopreventivo> mantenimientopreventivoSet) {
        this.mantenimientopreventivoSet = mantenimientopreventivoSet;
    }

    public Set<Mantenimientopreventivo> getMantenimientopreventivoSet1() {
        return mantenimientopreventivoSet1;
    }

    public void setMantenimientopreventivoSet1(Set<Mantenimientopreventivo> mantenimientopreventivoSet1) {
        this.mantenimientopreventivoSet1 = mantenimientopreventivoSet1;
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
        return "entity.Proveedormantenimientomaquina[idproveedormantenimiento=" + idproveedormantenimiento + "]";
    }

}