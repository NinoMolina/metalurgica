/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "viewetapasxpiezapresupuesto")
@NamedQueries({
    @NamedQuery(name = "Viewetapasxpiezapresupuesto.findAll", query = "SELECT v FROM Viewetapasxpiezapresupuesto v"),
    @NamedQuery(name = "Viewetapasxpiezapresupuesto.findByNroproducto", query = "SELECT v FROM Viewetapasxpiezapresupuesto v WHERE v.nroproducto = :nroproducto"),
    @NamedQuery(name = "Viewetapasxpiezapresupuesto.findByNombreproducto", query = "SELECT v FROM Viewetapasxpiezapresupuesto v WHERE v.nombreproducto = :nombreproducto"),
    @NamedQuery(name = "Viewetapasxpiezapresupuesto.findByCantproducto", query = "SELECT v FROM Viewetapasxpiezapresupuesto v WHERE v.cantproducto = :cantproducto"),
    @NamedQuery(name = "Viewetapasxpiezapresupuesto.findByNombrepieza", query = "SELECT v FROM Viewetapasxpiezapresupuesto v WHERE v.nombrepieza = :nombrepieza"),
    @NamedQuery(name = "Viewetapasxpiezapresupuesto.findByCantpieza", query = "SELECT v FROM Viewetapasxpiezapresupuesto v WHERE v.cantpieza = :cantpieza"),
    @NamedQuery(name = "Viewetapasxpiezapresupuesto.findByNroetapaproduccion", query = "SELECT v FROM Viewetapasxpiezapresupuesto v WHERE v.nroetapaproduccion = :nroetapaproduccion"),
    @NamedQuery(name = "Viewetapasxpiezapresupuesto.findByNombreetapaproduccion", query = "SELECT v FROM Viewetapasxpiezapresupuesto v WHERE v.nombreetapaproduccion = :nombreetapaproduccion"),
    @NamedQuery(name = "Viewetapasxpiezapresupuesto.findByDuracionetapaxpieza", query = "SELECT v FROM Viewetapasxpiezapresupuesto v WHERE v.duracionetapaxpieza = :duracionetapaxpieza"),
    @NamedQuery(name = "Viewetapasxpiezapresupuesto.findByIdpresupuesto", query = "SELECT v FROM Viewetapasxpiezapresupuesto v WHERE v.idpresupuesto = :idpresupuesto"),
    @NamedQuery(name = "Viewetapasxpiezapresupuesto.findByIddetallepresupuesto", query = "SELECT v FROM Viewetapasxpiezapresupuesto v WHERE v.iddetallepresupuesto = :iddetallepresupuesto"),
    @NamedQuery(name = "Viewetapasxpiezapresupuesto.findByIddetalleproductopresupuesto", query = "SELECT v FROM Viewetapasxpiezapresupuesto v WHERE v.iddetalleproductopresupuesto = :iddetalleproductopresupuesto"),
    @NamedQuery(name = "Viewetapasxpiezapresupuesto.findByIddetallepiezapresupuesto", query = "SELECT v FROM Viewetapasxpiezapresupuesto v WHERE v.iddetallepiezapresupuesto = :iddetallepiezapresupuesto"),
    @NamedQuery(name = "Viewetapasxpiezapresupuesto.findByIdproducto", query = "SELECT v FROM Viewetapasxpiezapresupuesto v WHERE v.idproducto = :idproducto"),
    @NamedQuery(name = "Viewetapasxpiezapresupuesto.findByIdpieza", query = "SELECT v FROM Viewetapasxpiezapresupuesto v WHERE v.idpieza = :idpieza"),
    @NamedQuery(name = "Viewetapasxpiezapresupuesto.findByIdetapaproduccion", query = "SELECT v FROM Viewetapasxpiezapresupuesto v WHERE v.idetapaproduccion = :idetapaproduccion")})
public class Viewetapasxpiezapresupuesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "nroproducto")
    private BigInteger nroproducto;
    @Column(name = "nombreproducto")
    private String nombreproducto;
    @Column(name = "cantproducto")
    private Integer cantproducto;
    @Column(name = "nombrepieza")
    private String nombrepieza;
    @Column(name = "cantpieza")
    private Integer cantpieza;
    @Column(name = "nroetapaproduccion")
    private BigInteger nroetapaproduccion;
    @Column(name = "nombreetapaproduccion")
    private String nombreetapaproduccion;
    @Column(name = "duracionetapaxpieza")
    @Temporal(TemporalType.TIME)
    private Date duracionetapaxpieza;
    @Lob
    @Column(name = "duraciontotal")
    private Object duraciontotal;
    @Column(name = "idpresupuesto")
    private BigInteger idpresupuesto;
    @Column(name = "iddetallepresupuesto")
    private BigInteger iddetallepresupuesto;
    @Column(name = "iddetalleproductopresupuesto")
    private BigInteger iddetalleproductopresupuesto;
    @Column(name = "iddetallepiezapresupuesto")
    private BigInteger iddetallepiezapresupuesto;
    @Column(name = "idproducto")
    private BigInteger idproducto;
    @Column(name = "idpieza")
    private BigInteger idpieza;
    @Column(name = "idetapaproduccion")
    private BigInteger idetapaproduccion;

    public Viewetapasxpiezapresupuesto() {
    }

    public BigInteger getNroproducto() {
        return nroproducto;
    }

    public void setNroproducto(BigInteger nroproducto) {
        this.nroproducto = nroproducto;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public Integer getCantproducto() {
        return cantproducto;
    }

    public void setCantproducto(Integer cantproducto) {
        this.cantproducto = cantproducto;
    }

    public String getNombrepieza() {
        return nombrepieza;
    }

    public void setNombrepieza(String nombrepieza) {
        this.nombrepieza = nombrepieza;
    }

    public Integer getCantpieza() {
        return cantpieza;
    }

    public void setCantpieza(Integer cantpieza) {
        this.cantpieza = cantpieza;
    }

    public BigInteger getNroetapaproduccion() {
        return nroetapaproduccion;
    }

    public void setNroetapaproduccion(BigInteger nroetapaproduccion) {
        this.nroetapaproduccion = nroetapaproduccion;
    }

    public String getNombreetapaproduccion() {
        return nombreetapaproduccion;
    }

    public void setNombreetapaproduccion(String nombreetapaproduccion) {
        this.nombreetapaproduccion = nombreetapaproduccion;
    }

    public Date getDuracionetapaxpieza() {
        return duracionetapaxpieza;
    }

    public void setDuracionetapaxpieza(Date duracionetapaxpieza) {
        this.duracionetapaxpieza = duracionetapaxpieza;
    }

    public Object getDuraciontotal() {
        return duraciontotal;
    }

    public void setDuraciontotal(Object duraciontotal) {
        this.duraciontotal = duraciontotal;
    }

    public BigInteger getIdpresupuesto() {
        return idpresupuesto;
    }

    public void setIdpresupuesto(BigInteger idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

    public BigInteger getIddetallepresupuesto() {
        return iddetallepresupuesto;
    }

    public void setIddetallepresupuesto(BigInteger iddetallepresupuesto) {
        this.iddetallepresupuesto = iddetallepresupuesto;
    }

    public BigInteger getIddetalleproductopresupuesto() {
        return iddetalleproductopresupuesto;
    }

    public void setIddetalleproductopresupuesto(BigInteger iddetalleproductopresupuesto) {
        this.iddetalleproductopresupuesto = iddetalleproductopresupuesto;
    }

    public BigInteger getIddetallepiezapresupuesto() {
        return iddetallepiezapresupuesto;
    }

    public void setIddetallepiezapresupuesto(BigInteger iddetallepiezapresupuesto) {
        this.iddetallepiezapresupuesto = iddetallepiezapresupuesto;
    }

    public BigInteger getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(BigInteger idproducto) {
        this.idproducto = idproducto;
    }

    public BigInteger getIdpieza() {
        return idpieza;
    }

    public void setIdpieza(BigInteger idpieza) {
        this.idpieza = idpieza;
    }

    public BigInteger getIdetapaproduccion() {
        return idetapaproduccion;
    }

    public void setIdetapaproduccion(BigInteger idetapaproduccion) {
        this.idetapaproduccion = idetapaproduccion;
    }

}
