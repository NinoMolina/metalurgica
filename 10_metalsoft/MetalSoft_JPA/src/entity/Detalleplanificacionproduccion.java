/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "detalleplanificacionproduccion")
@NamedQueries({
    @NamedQuery(name = "Detalleplanificacionproduccion.findAll", query = "SELECT d FROM Detalleplanificacionproduccion d"),
    @NamedQuery(name = "Detalleplanificacionproduccion.findById", query = "SELECT d FROM Detalleplanificacionproduccion d WHERE d.id = :id"),
    @NamedQuery(name = "Detalleplanificacionproduccion.findByFechainicio", query = "SELECT d FROM Detalleplanificacionproduccion d WHERE d.fechainicio = :fechainicio"),
    @NamedQuery(name = "Detalleplanificacionproduccion.findByHorainicio", query = "SELECT d FROM Detalleplanificacionproduccion d WHERE d.horainicio = :horainicio"),
    @NamedQuery(name = "Detalleplanificacionproduccion.findByFechafin", query = "SELECT d FROM Detalleplanificacionproduccion d WHERE d.fechafin = :fechafin"),
    @NamedQuery(name = "Detalleplanificacionproduccion.findByHorafin", query = "SELECT d FROM Detalleplanificacionproduccion d WHERE d.horafin = :horafin")})
public class Detalleplanificacionproduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="detalleplanificacionproduccion_id_seq", sequenceName="detalleplanificacionproduccion_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="detalleplanificacionproduccion_id_seq")
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="orden")
    private int orden;
    @Column(name = "fechainicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Column(name = "horainicio")
    @Temporal(TemporalType.TIME)
    private Date horainicio;
    @Column(name = "fechafin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @Column(name = "horafin")
    @Temporal(TemporalType.TIME)
    private Date horafin;
    @JoinColumn(name = "iddetalleejecucionplanificacion", referencedColumnName = "id")
    @ManyToOne
    private Detalleejecucionplanificacion iddetalleejecucionplanificacion;
    @JoinColumn(name = "idempleado", referencedColumnName = "idempleado")
    @ManyToOne
    private Empleado idempleado;
    @JoinColumn(name = "idetapaproduccion", referencedColumnName = "idetapaproduccion")
    @ManyToOne
    private Etapadeproduccion idetapaproduccion;
    @JoinColumn(name = "idmaquina", referencedColumnName = "idmaquina")
    @ManyToOne
    private Maquina idmaquina;
    @JoinColumn(name = "idpieza", referencedColumnName = "idpieza")
    @ManyToOne
    private Pieza idpieza;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne
    private Producto idproducto;
    @JoinColumn(name = "idplanificacionproduccion", referencedColumnName = "idplanificacionproduccion")
    @ManyToOne(optional = false)
    private Planificacionproduccion idplanificacionproduccion;

    public Detalleplanificacionproduccion() {
    }

    public Detalleplanificacionproduccion(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }



    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(Date horainicio) {
        this.horainicio = horainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Date getHorafin() {
        return horafin;
    }

    public void setHorafin(Date horafin) {
        this.horafin = horafin;
    }

    public Detalleejecucionplanificacion getIddetalleejecucionplanificacion() {
        return iddetalleejecucionplanificacion;
    }

    public void setIddetalleejecucionplanificacion(Detalleejecucionplanificacion iddetalleejecucionplanificacion) {
        this.iddetalleejecucionplanificacion = iddetalleejecucionplanificacion;
    }

    public Empleado getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Empleado idempleado) {
        this.idempleado = idempleado;
    }

    public Etapadeproduccion getIdetapaproduccion() {
        return idetapaproduccion;
    }

    public void setIdetapaproduccion(Etapadeproduccion idetapaproduccion) {
        this.idetapaproduccion = idetapaproduccion;
    }

    public Maquina getIdmaquina() {
        return idmaquina;
    }

    public void setIdmaquina(Maquina idmaquina) {
        this.idmaquina = idmaquina;
    }

    public Pieza getIdpieza() {
        return idpieza;
    }

    public void setIdpieza(Pieza idpieza) {
        this.idpieza = idpieza;
    }

    public Producto getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Producto idproducto) {
        this.idproducto = idproducto;
    }

    public Planificacionproduccion getIdplanificacionproduccion() {
        return idplanificacionproduccion;
    }

    public void setIdplanificacionproduccion(Planificacionproduccion idplanificacionproduccion) {
        this.idplanificacionproduccion = idplanificacionproduccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleplanificacionproduccion)) {
            return false;
        }
        Detalleplanificacionproduccion other = (Detalleplanificacionproduccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        if(this.id==null && other.id==null){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Detalleplanificacionproduccion[id=" + id + "]";
    }

}
