/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.math.BigInteger;
import java.util.Date;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Mantenimientopreventivo;
import metalsoft.datos.jpa.entity.Servicio;
import metalsoft.datos.jpa.entity.Tipomaquina;
import metalsoft.datos.jpa.entity.Maquina;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.gestores.GestorMantenimientoPreventivo;
import metalsoft.datos.jpa.controller.MantenimientopreventivoJpaController;
import metalsoft.datos.jpa.controller.TipomaquinaJpaController;
import metalsoft.datos.jpa.controller.ServicioJpaController;
import metalsoft.datos.jpa.controller.MaquinaJpaController;
import metalsoft.datos.jpa.controller.DetallemantenimientopreventivoJpaController;
import metalsoft.datos.jpa.controller.ProveedormantenimientomaquinaJpaController;
import metalsoft.datos.jpa.entity.Detallemantenimientopreventivo;
import metalsoft.datos.jpa.entity.Proveedormantenimientomaquina;
import metalsoft.util.Fecha;

/**
 *
 * @author Lorreine Prescott
 */
public class GestorMantenimientoPreventivo {

    private long IdMantenimientoPreventivo;
    private long Idtipomaquina;
    private long Idmaquina;
    private long Idservicio;

    public GestorMantenimientoPreventivo() {
        
    }

    public Mantenimientopreventivo buscarMantenimientoPreventivo(long id) {
        MantenimientopreventivoJpaController controller = new MantenimientopreventivoJpaController(JpaUtil.getEntityManagerFactory());
        Mantenimientopreventivo mantenimientop = controller.findMantenimientopreventivo(id);
        return mantenimientop;
    }

    public void buscarTipoMaquina(JComboBox combo) {
        try {
            List<Tipomaquina> tiposmaquinas = null;
            TipomaquinaJpaController controller = new TipomaquinaJpaController(JpaUtil.getEntityManagerFactory());
            tiposmaquinas = controller.findTipomaquinaEntities();
            ItemCombo item = null;
            combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
            for (Tipomaquina tipomaq : tiposmaquinas) {
                item = new ItemCombo();
                item.setId(String.valueOf(tipomaq.getIdtipomaquina()));
                item.setMostrar(tipomaq.getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorMantenimientoPreventivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscarMaquina(JComboBox combo, long id) {

        try {
            List<Maquina> maquinas = null;
            TipomaquinaJpaController controller = new TipomaquinaJpaController(JpaUtil.getEntityManagerFactory());
            maquinas = controller.findTipomaquina(id).getMaquinaList();
            ItemCombo item = null;
            combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
            for (Maquina maq : maquinas) {
                item = new ItemCombo();
                item.setId(String.valueOf(maq.getIdmaquina()));
                item.setMostrar(maq.getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorMantenimientoPreventivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public long guardarMantenimientoPreventivo(Mantenimientopreventivo mantenimientop, List<Detallemantenimientopreventivo> lista) {
        MantenimientopreventivoJpaController controller = new MantenimientopreventivoJpaController(JpaUtil.getEntityManagerFactory());
        DetallemantenimientopreventivoJpaController controllerDetalle = new DetallemantenimientopreventivoJpaController(JpaUtil.getEntityManagerFactory());
        try {
            controller.create(mantenimientop);
            for (Detallemantenimientopreventivo de : lista) {
                de.setIdmantenimientopreventivo(mantenimientop);
                controllerDetalle.create(de);
            }
        } catch (Exception ex) {
            Logger.getLogger(GestorMantenimientoPreventivo.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return mantenimientop.getIdmantenimientopreventivo();
    }

    public long modificarMantenimientoPreventivo(Mantenimientopreventivo mantenimientop, List<Detallemantenimientopreventivo> listaAgregar, List<Detallemantenimientopreventivo> listaQuitar) {
        MantenimientopreventivoJpaController controller = new MantenimientopreventivoJpaController(JpaUtil.getEntityManagerFactory());
        DetallemantenimientopreventivoJpaController controllerDetalle = new DetallemantenimientopreventivoJpaController(JpaUtil.getEntityManagerFactory());

        try {
            controller.edit(mantenimientop);
            for (Detallemantenimientopreventivo de : listaAgregar) {
                de.setIdmantenimientopreventivo(mantenimientop);
                controllerDetalle.create(de);
            }
            for (Detallemantenimientopreventivo de : listaQuitar) {
                de.setIdmantenimientopreventivo(mantenimientop);
                controllerDetalle.destroy(de.getIddetalle());
            }
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(GestorMantenimientoPreventivo.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (Exception ex) {
            Logger.getLogger(GestorMantenimientoPreventivo.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return mantenimientop.getIdmantenimientopreventivo();
    }

    public long generarNvoNroMantenimientoPreventivo() {
        long result = -1;
        PostgreSQLManager pg = null;
        Connection cn = null;
        pg = new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
            result = AccessFunctions.nvoNroMantenimientoPreventivo(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorMantenimientoPreventivo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorMantenimientoPreventivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public void obtenerTiposMaquinas(JComboBox combo) {
        try {
            List<Tipomaquina> tiposmaquinas = null;
            TipomaquinaJpaController controller = new TipomaquinaJpaController(JpaUtil.getEntityManagerFactory());
            tiposmaquinas = controller.findTipomaquinaEntities();
            ItemCombo item = null;
            combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
            for (Tipomaquina tmaq : tiposmaquinas) {
                item = new ItemCombo();
                item.setId(String.valueOf(tmaq.getIdtipomaquina()));
                item.setMostrar(tmaq.getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorMantenimientoPreventivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void obtenerProveedoresMantenimiento(JComboBox combo) {
        try {
            List<Proveedormantenimientomaquina> proveedores = null;
            ProveedormantenimientomaquinaJpaController controller = new ProveedormantenimientomaquinaJpaController(JpaUtil.getEntityManagerFactory());
            proveedores = controller.findProveedormantenimientomaquinaEntities();
            ItemCombo item = null;
            combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
            for (Proveedormantenimientomaquina prov : proveedores) {
                item = new ItemCombo();
                item.setId(String.valueOf(prov.getIdproveedormantenimiento()));
                item.setMostrar(prov.getRazonsocial());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorMantenimientoPreventivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void obtenerMaquinas(JComboBox combo, long id) {

        try {
            List<Maquina> maquinas = null;
            TipomaquinaJpaController controller = new TipomaquinaJpaController(JpaUtil.getEntityManagerFactory());
            maquinas = controller.findTipomaquina(id).getMaquinaList();
            ItemCombo item = null;
            combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
            for (Maquina maq : maquinas) {
                item = new ItemCombo();
                item.setId(String.valueOf(maq.getIdmaquina()));
                item.setMostrar(maq.getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorMantenimientoPreventivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void obtenerServicios(JComboBox combo) {
        try {
            List<Servicio> servicios = null;
            ServicioJpaController controller = new ServicioJpaController(JpaUtil.getEntityManagerFactory());
            servicios = controller.findServicioEntities();
            ItemCombo item = null;
            combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
            for (Servicio serv : servicios) {
                item = new ItemCombo();
                item.setId(String.valueOf(serv.getIdservicio()));
                item.setMostrar(serv.getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorMantenimientoPreventivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Servicio buscarServicioSeleccionado(long id) {
        ServicioJpaController con = new ServicioJpaController(JpaUtil.getEntityManagerFactory());
        return con.findServicio(id);
    }

    public Maquina obtenerMaquina(long id) {
        MaquinaJpaController controller = new MaquinaJpaController(JpaUtil.getEntityManagerFactory());
        return controller.findMaquina(id);
    }

    public List<Mantenimientopreventivo> buscarEntreFechas(String inicio, String fin) {
        return JpaUtil.getMantenimientopreventivoPorFecha(inicio, fin);
    }

    public List<Mantenimientopreventivo> buscarPorNro(String nro) {
        return JpaUtil.getMantenimientopreventivoPorNroLIKE(nro);
    }

    public List<Detallemantenimientopreventivo> obtenerDetalleDeMantenimiento(String id) {
        return JpaUtil.getDetalleMantenimientopreventivo(id);
    }

    public boolean eliminarMantenimiento(Mantenimientopreventivo man) {
        MantenimientopreventivoJpaController con = new MantenimientopreventivoJpaController(JpaUtil.getEntityManagerFactory());
        DetallemantenimientopreventivoJpaController deCon=new DetallemantenimientopreventivoJpaController(JpaUtil.getEntityManagerFactory());
        try {
            List<Detallemantenimientopreventivo> ListDet=this.obtenerDetalleDeMantenimiento(String.valueOf(man.getIdmantenimientopreventivo()));
            for(Detallemantenimientopreventivo de : ListDet){
                deCon.destroy(de.getIddetalle());
            }
            con.destroy(man.getIdmantenimientopreventivo());
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(GestorMantenimientoPreventivo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(GestorMantenimientoPreventivo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public List<Mantenimientopreventivo> buscarMantenimientosHastaFechaActual() {
        String fecha=Fecha.parseToString(Fecha.fechaActualDate(), "dd/MM/yyyy");
        return JpaUtil.getMantenimientopreventivoHastaFechaActual(fecha);
    }
    
    public Proveedormantenimientomaquina getProveedorById(long id){
        ProveedormantenimientomaquinaJpaController con = new ProveedormantenimientomaquinaJpaController(JpaUtil.getEntityManagerFactory());
        return con.findProveedormantenimientomaquina(id);
    }
    
    public long registrarEnvioMantenimientoPreventivo(Mantenimientopreventivo man) {
        MantenimientopreventivoJpaController controller = new MantenimientopreventivoJpaController(JpaUtil.getEntityManagerFactory());
        
        try {
            controller.edit(man);
            
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(GestorMantenimientoPreventivo.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (Exception ex) {
            Logger.getLogger(GestorMantenimientoPreventivo.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return man.getIdmantenimientopreventivo();
    }
}
