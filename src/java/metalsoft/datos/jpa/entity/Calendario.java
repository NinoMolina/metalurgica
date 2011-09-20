/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "calendario")
@NamedQueries({
    @NamedQuery(name = "Calendario.findAll", query = "SELECT c FROM Calendario c"),
    @NamedQuery(name = "Calendario.findByDia", query = "SELECT c FROM Calendario c WHERE c.dia = :dia"),
    @NamedQuery(name = "Calendario.findByMes", query = "SELECT c FROM Calendario c WHERE c.mes = :mes"),
    @NamedQuery(name = "Calendario.findByAnio", query = "SELECT c FROM Calendario c WHERE c.anio = :anio"),
    @NamedQuery(name = "Calendario.findById", query = "SELECT c FROM Calendario c WHERE c.id = :id"),
    @NamedQuery(name = "Calendario.findByFecha", query = "SELECT c FROM Calendario c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Calendario.findByTodoeldia", query = "SELECT c FROM Calendario c WHERE c.todoeldia = :todoeldia"),
    @NamedQuery(name = "Calendario.findByHoradesde", query = "SELECT c FROM Calendario c WHERE c.horadesde = :horadesde"),
    @NamedQuery(name = "Calendario.findByHorahasta", query = "SELECT c FROM Calendario c WHERE c.horahasta = :horahasta")})
public class Calendario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "dia")
    private Integer dia;
    @Column(name = "mes")
    private Integer mes;
    @Column(name = "anio")
    private Integer anio;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "calendario_seq")
    @SequenceGenerator(name = "calendario_seq", sequenceName = "calendario_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "todoeldia")
    private Boolean todoeldia;
    @Column(name = "horadesde")
    @Temporal(TemporalType.TIME)
    private Date horadesde;
    @Column(name = "horahasta")
    @Temporal(TemporalType.TIME)
    private Date horahasta;

    public Calendario() {
    }

    public Calendario(Long id) {
        this.id = id;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getTodoeldia() {
        return todoeldia;
    }

    public void setTodoeldia(Boolean todoeldia) {
        this.todoeldia = todoeldia;
    }

    public Date getHoradesde() {
        return horadesde;
    }

    public void setHoradesde(Date horadesde) {
        this.horadesde = horadesde;
    }

    public Date getHorahasta() {
        return horahasta;
    }

    public void setHorahasta(Date horahasta) {
        this.horahasta = horahasta;
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
        if (!(object instanceof Calendario)) {
            return false;
        }
        Calendario other = (Calendario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Calendario[ id=" + id + " ]";
    }
    
}
