/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.util.List;
import javax.swing.JComboBox;
import metalsoft.datos.jpa.controller.EmpresametalurgicaJpaController;
import metalsoft.datos.jpa.controller.PiezaJpaController;
import metalsoft.datos.jpa.entity.Empresametalurgica;
import metalsoft.datos.jpa.entity.Pieza;
import metalsoft.util.ItemCombo;

/**
 *
 * @author Mariana
 */
public class GestorReclamo implements Comparable  {

    public GestorReclamo()
      {}


    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private List<Pieza> pieza;

    public List<Pieza> getPieza() {
        return pieza;
    }

    public void setPieza(List<Pieza>pieza) {
        this.pieza = pieza;
    }

    public Pieza getPiezaSeleccionada(String id) {
        long idLong = Long.parseLong(id);
        return searchPiezaById(idLong);
    }

    private Pieza searchPiezaById(long id) {
        for (Pieza p : pieza) {
            if (p.getIdpieza() == id) {
                return p;
            }
        }
        return null;
    }

    public void cargarComboPieza(JComboBox combo) {
        pieza = null;
        PiezaJpaController controller = new PiezaJpaController();
        pieza = controller.findPiezaEntities();
        ItemCombo item = null;
        combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
        for (Pieza p : pieza) {
            item = new ItemCombo();
            item.setId(String.valueOf(p.getIdpieza()));
            item.setMostrar(p.getNombre());
            combo.addItem(item);
        }
        combo.setSelectedIndex(0);
    }

        private List<Empresametalurgica> empresa;

    public List<Empresametalurgica> getEmpresa() {
        return empresa;
    }

    public void setEmpresa(List<Empresametalurgica>empresa) {
        this.empresa = empresa;
    }

    public Empresametalurgica getEmpresaSeleccionada(String id) {
        long idLong = Long.parseLong(id);
        return searchEmpresaById(idLong);
    }

    private Empresametalurgica searchEmpresaById(long id) {
        for (Empresametalurgica emp : empresa) {
            if (emp.getIdempresametalurgica() == id) {
                return emp;
            }
        }
        return null;
    }

    public void cargarComboEmpresa(JComboBox combo) {
        empresa = null;
        EmpresametalurgicaJpaController controller = new EmpresametalurgicaJpaController();
        empresa = controller.findEmpresametalurgicaEntities();
        ItemCombo item = null;
        combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
        for (Empresametalurgica emp : empresa) {
            item = new ItemCombo();
            item.setId(String.valueOf(emp.getIdempresametalurgica()));
            item.setMostrar(emp.getRazonsocial());
            combo.addItem(item);
        }
        combo.setSelectedIndex(0);
    }

}
