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
@Table(name = "matriz")
@NamedQueries({
    @NamedQuery(name = "Matriz.findAll", query = "SELECT m FROM Matriz m"),
    @NamedQuery(name = "Matriz.findByIdmatriz", query = "SELECT m FROM Matriz m WHERE m.idmatriz = :idmatriz"),
    @NamedQuery(name = "Matriz.findByCodmatriz", query = "SELECT m FROM Matriz m WHERE m.codmatriz = :codmatriz"),
    @NamedQuery(name = "Matriz.findByNombre", query = "SELECT m FROM Matriz m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Matriz.findByDescripcion", query = "SELECT m FROM Matriz m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Matriz.findByObservaciones", query = "SELECT m FROM Matriz m WHERE m.observaciones = :observaciones"),
    @NamedQuery(name = "Matriz.findByFechacreacion", query = "SELECT m FROM Matriz m WHERE m.fechacreacion = :fechacreacion")})
public class Matriz implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matriz_seq")
    @SequenceGenerator(name = "matriz_seq", sequenceName = "matriz_idmatriz_seq", allocationSize = 1)
    @Column(name = "idmatriz")
    private Long idmatriz;
    @Column(name = "codmatriz")
    private BigInteger codmatriz;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.DATE)
    private Date fechacreacion;
    @OneToMany(mappedBy = "matriz")
    private List<Pieza> piezaList;
    @JoinColumn(name = "tipomaterial", referencedColumnName = "idtipomaterial")
    @ManyToOne
    private Tipomaterial tipomaterial;
    @JoinColumn(name = "materiaprima", referencedColumnName = "idmateriaprima")
    @ManyToOne
    private Materiaprima materiaprima;
    @OneToMany(mappedBy = "idmatriz")
    private List<Pedidomatriz> pedidomatrizList;

    public Matriz() {
    }

    public Matriz(Long idmatriz) {
        this.idmatriz = idmatriz;
    }

    public Long getIdmatriz() {
        return idmatriz;
    }

    public void setIdmatriz(Long idmatriz) {
        this.idmatriz = idmatriz;
    }

    public BigInteger getCodmatriz() {
        return codmatriz;
    }

    public void setCodmatriz(BigInteger codmatriz) {
        this.codmatriz = codmatriz;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public List<Pieza> getPiezaList() {
        return piezaList;
    }

    public void setPiezaList(List<Pieza> piezaList) {
        this.piezaList = piezaList;
    }

    public Tipomaterial getTipomaterial() {
        return tipomaterial;
    }

    public void setTipomaterial(Tipomaterial tipomaterial) {
        this.tipomaterial = tipomaterial;
    }

    public Materiaprima getMateriaprima() {
        return materiaprima;
    }

    public void setMateriaprima(Materiaprima materiaprima) {
        this.materiaprima = materiaprima;
    }

    public List<Pedidomatriz> getPedidomatrizList() {
        return pedidomatrizList;
    }

    public void setPedidomatrizList(List<Pedidomatriz> pedidomatrizList) {
        this.pedidomatrizList = pedidomatrizList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmatriz != null ? idmatriz.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matriz)) {
            return false;
        }
        Matriz other = (Matriz) object;
        if ((this.idmatriz == null && other.idmatriz != null) || (this.idmatriz != null && !this.idmatriz.equals(other.idmatriz))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Matriz[ idmatriz=" + idmatriz + " ]";
    }
    
}
