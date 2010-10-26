/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "viewproveedorxmateriaprima")
@NamedQueries({
    @NamedQuery(name = "Viewproveedorxmateriaprima.findAll", query = "SELECT v FROM Viewproveedorxmateriaprima v"),
    @NamedQuery(name = "Viewproveedorxmateriaprima.findByNroproveedor", query = "SELECT v FROM Viewproveedorxmateriaprima v WHERE v.nroproveedor = :nroproveedor"),
    @NamedQuery(name = "Viewproveedorxmateriaprima.findByRazonsocial", query = "SELECT v FROM Viewproveedorxmateriaprima v WHERE v.razonsocial = :razonsocial"),
    @NamedQuery(name = "Viewproveedorxmateriaprima.findByCondicioniva", query = "SELECT v FROM Viewproveedorxmateriaprima v WHERE v.condicioniva = :condicioniva"),
    @NamedQuery(name = "Viewproveedorxmateriaprima.findByProvincia", query = "SELECT v FROM Viewproveedorxmateriaprima v WHERE v.provincia = :provincia"),
    @NamedQuery(name = "Viewproveedorxmateriaprima.findByTelefono", query = "SELECT v FROM Viewproveedorxmateriaprima v WHERE v.telefono = :telefono"),
    @NamedQuery(name = "Viewproveedorxmateriaprima.findByMail", query = "SELECT v FROM Viewproveedorxmateriaprima v WHERE v.mail = :mail"),
    @NamedQuery(name = "Viewproveedorxmateriaprima.findByResponsable", query = "SELECT v FROM Viewproveedorxmateriaprima v WHERE v.responsable = :responsable"),
    @NamedQuery(name = "Viewproveedorxmateriaprima.findByPrecio", query = "SELECT v FROM Viewproveedorxmateriaprima v WHERE v.precio = :precio"),
    @NamedQuery(name = "Viewproveedorxmateriaprima.findByIdproveedor", query = "SELECT v FROM Viewproveedorxmateriaprima v WHERE v.idproveedor = :idproveedor"),
    @NamedQuery(name = "Viewproveedorxmateriaprima.findByIdresponsable", query = "SELECT v FROM Viewproveedorxmateriaprima v WHERE v.idresponsable = :idresponsable"),
    @NamedQuery(name = "Viewproveedorxmateriaprima.findByIdmateriaprima", query = "SELECT v FROM Viewproveedorxmateriaprima v WHERE v.idmateriaprima = :idmateriaprima")})
public class Viewproveedorxmateriaprima implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "nroproveedor")
    private BigInteger nroproveedor;
    @Column(name = "razonsocial")
    private String razonsocial;
    @Column(name = "condicioniva")
    private String condicioniva;
    @Column(name = "provincia")
    private String provincia;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "mail")
    private String mail;
    @Column(name = "responsable")
    private String responsable;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "idproveedor")
    private BigInteger idproveedor;
    @Column(name = "idresponsable")
    private BigInteger idresponsable;
    @Column(name = "idmateriaprima")
    private BigInteger idmateriaprima;

    public Viewproveedorxmateriaprima() {
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

    public String getCondicioniva() {
        return condicioniva;
    }

    public void setCondicioniva(String condicioniva) {
        this.condicioniva = condicioniva;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public BigInteger getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(BigInteger idproveedor) {
        this.idproveedor = idproveedor;
    }

    public BigInteger getIdresponsable() {
        return idresponsable;
    }

    public void setIdresponsable(BigInteger idresponsable) {
        this.idresponsable = idresponsable;
    }

    public BigInteger getIdmateriaprima() {
        return idmateriaprima;
    }

    public void setIdmateriaprima(BigInteger idmateriaprima) {
        this.idmateriaprima = idmateriaprima;
    }

}
