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
import metalsoft.datos.jpa.controller.DetallemantenimientocorrectivoJpaController;
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
import metalsoft.negocio.gestores.GestorMantenimiento;
import metalsoft.datos.jpa.controller.MantenimientopreventivoJpaController;
import metalsoft.datos.jpa.controller.TipomaquinaJpaController;
import metalsoft.datos.jpa.controller.ServicioJpaController;
import metalsoft.datos.jpa.controller.MaquinaJpaController;
import metalsoft.datos.jpa.controller.DetallemantenimientopreventivoJpaController;
import metalsoft.datos.jpa.controller.EmpleadoJpaController;
import metalsoft.datos.jpa.controller.EstadomantpreventivoJpaController;
import metalsoft.datos.jpa.controller.MantenimientocorrectivoJpaController;
import metalsoft.datos.jpa.controller.ProveedormantenimientomaquinaJpaController;
import metalsoft.datos.jpa.controller.RoturaJpaController;
import metalsoft.datos.jpa.entity.Detallemantenimientocorrectivo;
import metalsoft.datos.jpa.entity.Detallemantenimientopreventivo;
import metalsoft.datos.jpa.entity.Empleado;
import metalsoft.datos.jpa.entity.Estadomantpreventivo;
import metalsoft.datos.jpa.entity.Mantenimientocorrectivo;
import metalsoft.datos.jpa.entity.Proveedormantenimientomaquina;
import metalsoft.datos.jpa.entity.Rotura;
import metalsoft.util.Fecha;

/**
 *
 * @author Lorreine Prescott
 */
public class GestorMantenimiento {

    private long IdMantenimientoPreventivo;
    private long Idtipomaquina;
    private long Idmaquina;
    private long Idservicio;

    public GestorMantenimiento() {
        
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
            Logger.getLogger(GestorMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return mantenimientop.getIdmantenimientopreventivo();
    }
    
    public long guardarMantenimientoCorrectivo(Mantenimientocorrectivo mantenimientop, List<Detallemantenimientocorrectivo> lista) {
        MantenimientocorrectivoJpaController controller = new MantenimientocorrectivoJpaController(JpaUtil.getEntityManagerFactory());
        DetallemantenimientocorrectivoJpaController controllerDetalle = new DetallemantenimientocorrectivoJpaController(JpaUtil.getEntityManagerFactory());
        try {
            controller.create(mantenimientop);
            for (Detallemantenimientocorrectivo de : lista) {
                de.setIdmantenimientocorrectivo(mantenimientop);
                controllerDetalle.create(de);
            }
        } catch (Exception ex) {
            Logger.getLogger(GestorMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return mantenimientop.getIdmantenimientocorrectivo();
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
            Logger.getLogger(GestorMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (Exception ex) {
            Logger.getLogger(GestorMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    public long nuevoNroMantenimientoCorrectivo(){
        return AccessFunctions.nvoNroMantenimientoCorrectivo();
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
            Logger.getLogger(GestorMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void obtenerMaquinas(JComboBox combo) {

        try {
            List<Maquina> maquinas = null;
            MaquinaJpaController controller = new MaquinaJpaController(JpaUtil.getEntityManagerFactory());
            maquinas = controller.findMaquinaEntities();
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
            Logger.getLogger(GestorMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public List<Detallemantenimientocorrectivo> obtenerDetalleDeMantenimientoCorrectivo(String id) {
        return JpaUtil.getDetalleMantenimientocorrectivo(id);
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
            Logger.getLogger(GestorMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(GestorMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public List<Mantenimientopreventivo> buscarMantenimientosHastaFechaActual() {
        String fecha=Fecha.parseToString(Fecha.fechaActualDate(), "dd/MM/yyyy");
        return JpaUtil.getMantenimientopreventivoHastaFechaActual(fecha);
    }
    
    public List<Mantenimientopreventivo> buscarMantenimientosEnviadosHastaFechaActual() {
        String fecha=Fecha.parseToString(Fecha.fechaActualDate(), "dd/MM/yyyy");
        return JpaUtil.getMantenimientopreventivoEnviadoHastaFechaActual(fecha);
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
            Logger.getLogger(GestorMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (Exception ex) {
            Logger.getLogger(GestorMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return man.getIdmantenimientopreventivo();
    }
    
    //buscar mantenimientos enviados
    public List<Mantenimientopreventivo> buscarEnviadosEntreFechas(String inicio, String fin) {
        return JpaUtil.getMantenimientopreventivoEnviadoPorFecha(inicio, fin);
    }

    public List<Mantenimientopreventivo> buscarEnviadosPorNro(String nro) {
        return JpaUtil.getMantenimientopreventivoEnviadoPorNroLIKE(nro);
    }
    
    public List<Mantenimientopreventivo> buscarEnviadosPorProveedor(String nro) {
        return JpaUtil.getMantenimientopreventivoEnviadoPorNroProveedorLIKE(nro);
    }

    public List<Mantenimientopreventivo> buscarEnviadosPorMaquina(String nro) {
        return JpaUtil.getMantenimientopreventivoEnviadoPorNroMaquinaLIKE(nro);
    }
    
    public Estadomantpreventivo buscarEstadoByID(long id){
        EstadomantpreventivoJpaController con = new EstadomantpreventivoJpaController(JpaUtil.getEntityManagerFactory());
        return con.findEstadomantpreventivo(id);
    }
    
    public void cargarComboRoturas(JComboBox combo){
        combo.removeAllItems();
        RoturaJpaController con=new RoturaJpaController(JpaUtil.getEntityManagerFactory());
        List<Rotura> list=con.findRoturaEntities();
        combo.addItem(new ItemCombo("-1","--Seleccionar--"));
        for(Rotura m : list){
            combo.addItem(new ItemCombo(String.valueOf(m.getIdrotura()),m.getNombre()));
        }
    }
    public void cargarComboEmpleado(JComboBox combo){
        combo.removeAllItems();
        EmpleadoJpaController con=new EmpleadoJpaController(JpaUtil.getEntityManagerFactory());
        List<Empleado> list=con.findEmpleadoEntities();
        combo.addItem(new ItemCombo("-1","--Seleccionar--"));
        for(Empleado m : list){
            combo.addItem(new ItemCombo(String.valueOf(m.getIdempleado()),m.getNombre()));
        }
    }
    
    public Rotura getRoturaById(long id){
        RoturaJpaController con=new RoturaJpaController(JpaUtil.getEntityManagerFactory());
        return con.findRotura(id);
    }
    
    //buscar mantenimientos correctivos enviados
    public List<Mantenimientocorrectivo> buscarCorrectivosEnviadosEntreFechas(String inicio, String fin) {
        return JpaUtil.getMantenimientocorrectivoEnviadoPorFecha(inicio, fin);
    }

    public List<Mantenimientocorrectivo> buscarCorrectivosEnviadosPorNro(String nro) {
        return JpaUtil.getMantenimientocorrectivoEnviadoPorNroLIKE(nro);
    }
    
    public List<Mantenimientocorrectivo> buscarCorrectivosEnviadosPorProveedor(String nro) {
        return JpaUtil.getMantenimientocorrectivoEnviadoPorNroProveedorLIKE(nro);
    }

    public List<Mantenimientocorrectivo> buscarCorrectivosEnviadosPorMaquina(String nro) {
        return JpaUtil.getMantenimientocorrectivoEnviadoPorNroMaquinaLIKE(nro);
    }
}