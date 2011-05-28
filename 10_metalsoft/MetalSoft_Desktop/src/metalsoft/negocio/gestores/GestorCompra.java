/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JList;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.jpa.entity.Compra;
import metalsoft.datos.jpa.entity.Materiaprima;

/**
 *
 * @author Mariana
 */
public class GestorCompra implements IBuscador {

    private List<Materiaprima> materiaprima;
    private String numeroCompra;

    public List<Materiaprima> getMP() {
        return materiaprima;
    }

    public void setMateriaprima(List<Materiaprima> materiaprima) {
        this.materiaprima = materiaprima;
    }

    public Materiaprima getMateriaprimaSeleccionada(String id) {
        long idLong = Long.parseLong(id);
        return searchMateriaprimaById(idLong);
    }

    public Materiaprima searchMateriaprimaById(long id) {
        for (Materiaprima mp : materiaprima) {
            if (mp.getIdmateriaprima() == id) {
                return mp;
            }
        }
        return null;
    }

    public JList getList(String className) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public JComboBox getCombo(String className) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBusqueda(Object[] obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long registrarOrden() {
        Compra cmpra = new Compra();
        cmpra.setNrocompra(Integer.parseInt(numeroCompra));
        return 1;
    }

}
