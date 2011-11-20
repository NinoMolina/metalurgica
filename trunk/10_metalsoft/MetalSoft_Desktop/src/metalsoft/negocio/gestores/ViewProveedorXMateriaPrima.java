/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.MateriaprimaJpaController;
import metalsoft.datos.jpa.controller.ProveedorJpaController;
import metalsoft.datos.jpa.controller.ProveedorxmateriaprimaJpaController;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Materiaprima;
import metalsoft.datos.jpa.entity.Proveedor;
import metalsoft.datos.jpa.entity.Proveedorxmateriaprima;
import metalsoft.util.ItemCombo;

/**
 *
 * @author Nino
 */
public class ViewProveedorXMateriaPrima implements Comparable {

    private long nroproveedor;
    private String razonsocial;
    private String condicioniva;
    private String provincia;
    private String mail;
    private String responsable;
    private double precio;
    private long idproveedor;
    private long idresponsable;
    private long idmateriaprima;
    private String telefono;
    private metalsoft.datos.dbobject.ProveedorxmateriaprimaDB proveedorxmateriaprimaDB;

    public ViewProveedorXMateriaPrima() {
    }

    public String getCondicioniva() {
        return condicioniva;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCondicioniva(String condicioniva) {
        this.condicioniva = condicioniva;
    }

    public long getIdmateriaprima() {
        return idmateriaprima;
    }

    public void setIdmateriaprima(long idmateriaprima) {
        this.idmateriaprima = idmateriaprima;
    }

    public long getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(long idproveedor) {
        this.idproveedor = idproveedor;
    }

    public long getIdresponsable() {
        return idresponsable;
    }

    public void setIdresponsable(long idresponsable) {
        this.idresponsable = idresponsable;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public long getNroproveedor() {
        return nroproveedor;
    }

    public void setNroproveedor(long nroproveedor) {
        this.nroproveedor = nroproveedor;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public int compareTo(Object o) {
        ViewProveedorXMateriaPrima obj = (ViewProveedorXMateriaPrima) o;
        if (this.getIdmateriaprima() == obj.getIdmateriaprima() && this.getIdproveedor() == obj.getIdproveedor()) {
            return 0;
        } else {
            return -1;
        }
    }

    public static int contain(Comparable px, Collection coleccion) {
        Iterator<Comparable> i = coleccion.iterator();
        Comparable x = null;
        int contador = 0;
        while (i.hasNext()) {
            x = i.next();
            if (x.compareTo(px) == 0) {
                return contador;
            }
            contador++;
        }
        return -1;
    }
    private List<Materiaprima> materiaprima;

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

    private Materiaprima searchMateriaprimaById(long id) {
        for (Materiaprima mp : materiaprima) {
            if (mp.getIdmateriaprima() == id) {
                return mp;
            }
        }
        return null;
    }

    public void cargarComboMateriaprima(JComboBox combo) {
        materiaprima = null;
        
        MateriaprimaJpaController controller = new MateriaprimaJpaController(JpaUtil.getEntityManagerFactory());
        materiaprima = controller.findMateriaprimaEntities();
        
//        materiaprima = JpaUtil.getMateriaPrimaByProveedor(idproveedor);
        
        ItemCombo item = null;
        combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
        for (Materiaprima mp : materiaprima) {
            item = new ItemCombo();
            item.setId(String.valueOf(mp.getIdmateriaprima()));
            item.setMostrar(mp.getNombre());
            combo.addItem(item);
        }
        combo.setSelectedIndex(0);
    }
    private List<Proveedor> proveedor;

    public List<Proveedor> getProv() {
        return proveedor;
    }

    public void setProveedor(List<Proveedor> proveedor) {
        this.proveedor = proveedor;
    }

    public Proveedor getProveedorSeleccionado(String id) {
        long idLong = Long.parseLong(id);
        return searchProveedorById(idLong);
    }

    private Proveedor searchProveedorById(long id) {
        for (Proveedor p : proveedor) {
            if (p.getIdproveedor() == id) {
                return p;
            }
        }
        return null;
    }

    public void cargarComboProveedor(JComboBox combo) {
        proveedor = null;
        ProveedorJpaController controller = new ProveedorJpaController(JpaUtil.getEntityManagerFactory());
        proveedor = controller.findProveedorEntities();
        ItemCombo item = null;
        combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
        for (Proveedor p : proveedor) {
            item = new ItemCombo();
            item.setId(String.valueOf(p.getIdproveedor()));
            item.setMostrar(p.getRazonsocial());
            combo.addItem(item);
        }
        combo.setSelectedIndex(0);
    }

    public long asignarMPProveedor(Proveedorxmateriaprima pxm, long idMP, long idProv) {
        ProveedorxmateriaprimaJpaController controller = new ProveedorxmateriaprimaJpaController(JpaUtil.getEntityManagerFactory());
         try {
             Materiaprima MP = searchMateriaprimaById(idMP);
             Proveedor Prov = searchProveedorById(idProv);
             pxm.setMateriaprima(MP);
             pxm.setProveedor(Prov);
             controller.create(pxm);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(ViewProveedorXMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } catch (Exception ex) {
            Logger.getLogger(ViewProveedorXMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 1;
    }
}
