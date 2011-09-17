/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.util;

/**
 *
 * @author Nino
 */
public class ItemCombo {

    private String mostrar;
    private String id;

    public ItemCombo(){}

    public ItemCombo(String id, String mostrar)
    {
        this.id=id;
        this.mostrar=mostrar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMostrar() {
        return mostrar;
    }

    public void setMostrar(String mostrar) {
        this.mostrar = mostrar;
    }

    @Override
    public String toString()
    {
        return getMostrar();
    }
}
