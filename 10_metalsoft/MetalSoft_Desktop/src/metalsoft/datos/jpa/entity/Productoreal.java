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
@Table(name = "productoreal")
@NamedQueries({
    @NamedQuery(name = "Productoreal.findAll", query = "SELECT p FROM Productoreal p"),
    @NamedQuery(name = "Productoreal.findByIdproductoreal", query = "SELECT p FROM Productoreal p WHERE p.idproductoreal = :idproductoreal"),
    @NamedQuery(name = "Productoreal.findByNroproducto", query = "SELECT p FROM Productoreal p WHERE p.nroproducto = :nroproducto"),
    @NamedQuery(name = "Productoreal.findByFechaterminacion", query = "SELECT p FROM Productoreal p WHERE p.fechaterminacion = :fechaterminacion"),
    @NamedQuery(name = "Productoreal.findByFechainicioproduccion", query = "SELECT p FROM Productoreal p WHERE p.fechainicioproduccion = :fechainicioproduccion"),
    @NamedQuery(name = "Productoreal.findByIdpieza", query = "SELECT p FROM Productoreal p WHERE p.idpieza = :idpieza"),
    @NamedQuery(name = "Productoreal.findByIdpiezareal", query = "SELECT p FROM Productoreal p WHERE p.idpiezareal = :idpiezareal")})
public class Productoreal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idproductoreal")
    private Long idproductoreal;
    @Column(name = "nroproducto")
    private BigInteger nroproducto;
    @Column(name = "fechaterminacion")
    @Temporal(TemporalType.DATE)
    private Date fechaterminacion;
    @Column(name = "fechainicioproduccion")
    @Temporal(TemporalType.DATE)
    private Date fechainicioproduccion;
    @Column(name = "idpieza")
    private BigInteger idpieza;
    @Column(name = "idpiezareal")
    private BigInteger idpiezareal;
    @JoinColumn(name = "codigobarra", referencedColumnName = "idcodigo")
    @ManyToOne
    private Codigodebarra codigobarra;

    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadoproductoreal estado;

    @JoinColumn(name = "idpedido", referencedColumnName = "idpedido")
    @ManyToOne
    private Pedido idpedido;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoreal")
    private List<Detalleproductoreal> detalleproductorealList;


    public Productoreal() {
    }

    public Productoreal(Long idproductoreal) {
        this.idproductoreal = idproductoreal;
    }

    public Long getIdproductoreal() {
        return idproductoreal;
    }

    public void setIdproductoreal(Long idproductoreal) {
        this.idproductoreal = idproductoreal;
    }

    public BigInteger getNroproducto() {
        return nroproducto;
    }

    public void setNroproducto(BigInteger nroproducto) {
        this.nroproducto = nroproducto;
    }

    public Date getFechaterminacion() {
        return fechaterminacion;
    }

    public void setFechaterminacion(Date fechaterminacion) {
        this.fechaterminacion = fechaterminacion;
    }

    public Date getFechainicioproduccion() {
        return fechainicioproduccion;
    }

    public void setFechainicioproduccion(Date fechainicioproduccion) {
        this.fechainicioproduccion = fechainicioproduccion;
    }

    public BigInteger getIdpieza() {
        return idpieza;
    }

    public void setIdpieza(BigInteger idpieza) {
        this.idpieza = idpieza;
    }

    public BigInteger getIdpiezareal() {
        return idpiezareal;
    }

    public void setIdpiezareal(BigInteger idpiezareal) {
        this.idpiezareal = idpiezareal;
    }

    public Codigodebarra getCodigobarra() {
        return codigobarra;
    }

    public void setCodigobarra(Codigodebarra codigobarra) {
        this.codigobarra = codigobarra;
    }


    public Estadoproductoreal getEstado() {
        return estado;
    }

    public void setEstado(Estadoproductoreal estado) {
        this.estado = estado;
    }



    public Pedido getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Pedido idpedido) {
        this.idpedido = idpedido;
    }


    public List<Detalleproductoreal> getDetalleproductorealList() {
        return detalleproductorealList;
    }

    public void setDetalleproductorealList(List<Detalleproductoreal> detalleproductorealList) {
        this.detalleproductorealList = detalleproductorealList;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproductoreal != null ? idproductoreal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productoreal)) {
            return false;
        }
        Productoreal other = (Productoreal) object;
        if ((this.idproductoreal == null && other.idproductoreal != null) || (this.idproductoreal != null && !this.idproductoreal.equals(other.idproductoreal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Productoreal[idproductoreal=" + idproductoreal + "]";
    }

}
