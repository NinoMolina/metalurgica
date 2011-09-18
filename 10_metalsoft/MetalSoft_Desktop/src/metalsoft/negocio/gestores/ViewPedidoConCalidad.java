/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.util.Date;

/**
 *
 * @author Mariana
 */
public class ViewPedidoConCalidad {

    private int nropedido;
    private int nropedidocotizacioncliente;
    private Date fechapedidocotizacion;
    private Date fecharequeridacotizacion;
    private Date fechaentregaestipulada;
    private boolean espedidoweb;
    private long idpedido;
    private long idestado;

    public ViewPedidoConCalidad() {
    }

    public boolean isEspedidoweb() {
        return espedidoweb;
    }

    public void setEspedidoweb(boolean espedidoweb) {
        this.espedidoweb = espedidoweb;
    }


    public Date getFechaentregaestipulada() {
        return fechaentregaestipulada;
    }

    public void setFechaentregaestipulada(Date fechaentregaestipulada) {
        this.fechaentregaestipulada = fechaentregaestipulada;
    }

    public Date getFechapedidocotizacion() {
        return fechapedidocotizacion;
    }

    public void setFechapedidocotizacion(Date fechapedidocotizacion) {
        this.fechapedidocotizacion = fechapedidocotizacion;
    }

    public Date getFecharequeridacotizacion() {
        return fecharequeridacotizacion;
    }

    public void setFecharequeridacotizacion(Date fecharequeridacotizacion) {
        this.fecharequeridacotizacion = fecharequeridacotizacion;
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

    public int getNropedido() {
        return nropedido;
    }

    public void setNropedido(int nropedido) {
        this.nropedido = nropedido;
    }

    public int getNropedidocotizacioncliente() {
        return nropedidocotizacioncliente;
    }

    public void setNropedidocotizacioncliente(int nropedidocotizacioncliente) {
        this.nropedidocotizacioncliente = nropedidocotizacioncliente;
    }
}
