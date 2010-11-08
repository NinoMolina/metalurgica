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
import javax.persistence.CascadeType;
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
@Table(name = "proveedor")
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findByIdproveedor", query = "SELECT p FROM Proveedor p WHERE p.idproveedor = :idproveedor"),
    @NamedQuery(name = "Proveedor.findByNroproveedor", query = "SELECT p FROM Proveedor p WHERE p.nroproveedor = :nroproveedor"),
    @NamedQuery(name = "Proveedor.findByRazonsocial", query = "SELECT p FROM Proveedor p WHERE p.razonsocial = :razonsocial"),
    @NamedQuery(name = "Proveedor.findByTelefono", query = "SELECT p FROM Proveedor p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Proveedor.findByCelular", query = "SELECT p FROM Proveedor p WHERE p.celular = :celular"),
    @NamedQuery(name = "Proveedor.findByMail", query = "SELECT p FROM Proveedor p WHERE p.mail = :mail"),
    @NamedQuery(name = "Proveedor.findByFechaalta", query = "SELECT p FROM Proveedor p WHERE p.fechaalta = :fechaalta"),
    @NamedQuery(name = "Proveedor.findByFechabaja", query = "SELECT p FROM Proveedor p WHERE p.fechabaja = :fechabaja"),
    @NamedQuery(name = "Proveedor.findByCuil", query = "SELECT p FROM Proveedor p WHERE p.cuil = :cuil"),
    @NamedQuery(name = "Proveedor.findByCuit", query = "SELECT p FROM Proveedor p WHERE p.cuit = :cuit")})
public class Proveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idproveedor")
    private Long idproveedor;
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
    @OneToMany(mappedBy = "proveedor")
    private Set<Compra> compraSet;
    @OneToMany(mappedBy = "proveedor1")
    private Set<Compra> compraSet1;
    @JoinColumn(name = "condicion", referencedColumnName = "idcondicioniva")
    @ManyToOne
    private Condicioniva condicion;
    @JoinColumn(name = "condicion", referencedColumnName = "idcondicioniva")
    @ManyToOne
    private Condicioniva condicion1;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor")
    private Set<Proveedorxmateriaprima> proveedorxmateriaprimaSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor1")
    private Set<Proveedorxmateriaprima> proveedorxmateriaprimaSet1;

    public Proveedor() {
    }

    public Proveedor(Long idproveedor) {
        this.idproveedor = idproveedor;
    }

    public Long getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(Long idproveedor) {
        this.idproveedor = idproveedor;
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

    public Set<Compra> getCompraSet() {
        return compraSet;
    }

    public void setCompraSet(Set<Compra> compraSet) {
        this.compraSet = compraSet;
    }

    public Set<Compra> getCompraSet1() {
        return compraSet1;
    }

    public void setCompraSet1(Set<Compra> compraSet1) {
        this.compraSet1 = compraSet1;
    }

    public Condicioniva getCondicion() {
        return condicion;
    }

    public void setCondicion(Condicioniva condicion) {
        this.condicion = condicion;
    }

    public Condicioniva getCondicion1() {
        return condicion1;
    }

    public void setCondicion1(Condicioniva condicion1) {
        this.condicion1 = condicion1;
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

    public Set<Proveedorxmateriaprima> getProveedorxmateriaprimaSet() {
        return proveedorxmateriaprimaSet;
    }

    public void setProveedorxmateriaprimaSet(Set<Proveedorxmateriaprima> proveedorxmateriaprimaSet) {
        this.proveedorxmateriaprimaSet = proveedorxmateriaprimaSet;
    }

    public Set<Proveedorxmateriaprima> getProveedorxmateriaprimaSet1() {
        return proveedorxmateriaprimaSet1;
    }

    public void setProveedorxmateriaprimaSet1(Set<Proveedorxmateriaprima> proveedorxmateriaprimaSet1) {
        this.proveedorxmateriaprimaSet1 = proveedorxmateriaprimaSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproveedor != null ? idproveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.idproveedor == null && other.idproveedor != null) || (this.idproveedor != null && !this.idproveedor.equals(other.idproveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Proveedor[idproveedor=" + idproveedor + "]";
    }

}