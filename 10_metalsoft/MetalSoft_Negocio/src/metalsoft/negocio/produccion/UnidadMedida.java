/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.produccion;

/**
 *
 * @author Nino
 */
public class UnidadMedida {

    private String nombre, descripcion;

    public UnidadMedida(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public UnidadMedida() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
