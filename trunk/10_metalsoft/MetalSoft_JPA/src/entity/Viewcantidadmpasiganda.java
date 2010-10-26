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
@Table(name = "viewcantidadmpasiganda")
@NamedQueries({
    @NamedQuery(name = "Viewcantidadmpasiganda.findAll", query = "SELECT v FROM Viewcantidadmpasiganda v"),
    @NamedQuery(name = "Viewcantidadmpasiganda.findByPedido", query = "SELECT v FROM Viewcantidadmpasiganda v WHERE v.pedido = :pedido"),
    @NamedQuery(name = "Viewcantidadmpasiganda.findByIdmateriaprima", query = "SELECT v FROM Viewcantidadmpasiganda v WHERE v.idmateriaprima = :idmateriaprima"),
    @NamedQuery(name = "Viewcantidadmpasiganda.findByCantidad", query = "SELECT v FROM Viewcantidadmpasiganda v WHERE v.cantidad = :cantidad")})
public class Viewcantidadmpasiganda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "pedido")
    private BigInteger pedido;
    @Column(name = "idmateriaprima")
    private BigInteger idmateriaprima;
    @Column(name = "cantidad")
    private BigInteger cantidad;

    public Viewcantidadmpasiganda() {
    }

    public BigInteger getPedido() {
        return pedido;
    }

    public void setPedido(BigInteger pedido) {
        this.pedido = pedido;
    }

    public BigInteger getIdmateriaprima() {
        return idmateriaprima;
    }

    public void setIdmateriaprima(BigInteger idmateriaprima) {
        this.idmateriaprima = idmateriaprima;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

}
