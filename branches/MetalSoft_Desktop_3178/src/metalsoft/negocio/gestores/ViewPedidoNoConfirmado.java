/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.util.Date;

/**
 *
 * @author Nino
 */
public class ViewPedidoNoConfirmado {

    private long nropedido;
    private long nropedcotcli;
    private Date fechapedido;
    private String estado;
    private long nropresupuesto;
    private double montototal;
    private Date vencimientopresupuesto;
    private String razonsocial;
    private long idpedido;
    private long idpresupuesto;
    private long idestado;
    private long idcliente;

    public ViewPedidoNoConfirmado() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechapedido() {
        return fechapedido;
    }

    public void setFechapedido(Date fechapedido) {
        this.fechapedido = fechapedido;
    }

    public long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
    }

    public long getIdestado() {
        return idestado;
    }

    public void setIdestado(long idestado) {
        this.idestado = idestado;
    }

    public long getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(long idpedido) {
        this.idpedido = idpedido;
    }

    public long getIdpresupuesto() {
        return idpresupuesto;
    }

    public void setIdpresupuesto(long idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

    public double getMontototal() {
        return montototal;
    }

    public void setMontototal(double montototal) {
        this.montototal = montototal;
    }

    public long getNropedcotcli() {
        return nropedcotcli;
    }

    public void setNropedcotcli(long nropedcotcli) {
        this.nropedcotcli = nropedcotcli;
    }

    public long getNropedido() {
        return nropedido;
    }

    public void setNropedido(long nropedido) {
        this.nropedido = nropedido;
    }

    public long getNropresupuesto() {
        return nropresupuesto;
    }

    public void setNropresupuesto(long nropresupuesto) {
        this.nropresupuesto = nropresupuesto;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public Date getVencimientopresupuesto() {
        return vencimientopresupuesto;
    }

    public void setVencimientopresupuesto(Date vencimientopresupuesto) {
        this.vencimientopresupuesto = vencimientopresupuesto;
    }
}
