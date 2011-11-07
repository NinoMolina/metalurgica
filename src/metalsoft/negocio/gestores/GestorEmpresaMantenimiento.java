/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.swing.JComboBox;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.jpa.JpaUtil;

import metalsoft.datos.jpa.controller.BarrioJpaController;
import metalsoft.datos.jpa.controller.CondicionivaJpaController;
import metalsoft.datos.jpa.controller.DomicilioJpaController;
import metalsoft.datos.jpa.controller.LocalidadJpaController;
import metalsoft.datos.jpa.controller.ProveedormantenimientomaquinaJpaController;
import metalsoft.datos.jpa.controller.ProvinciaJpaController;
import metalsoft.datos.jpa.controller.ResponsableJpaController;
import metalsoft.datos.jpa.controller.TipodocumentoJpaController;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Provincia;
import metalsoft.datos.jpa.entity.Condicioniva;
import metalsoft.datos.jpa.entity.Tipodocumento;
import metalsoft.datos.jpa.entity.Localidad;
import metalsoft.datos.jpa.entity.Barrio;
import metalsoft.datos.jpa.entity.Domicilio;
import metalsoft.datos.jpa.entity.Proveedormantenimientomaquina;
import metalsoft.datos.jpa.entity.Responsable;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.util.ItemCombo;
/**
 *
 * @author Vicky
 */
public class GestorEmpresaMantenimiento {
    private long idDomicilioEmpresaMetalurgica, idDomicilioResponsable;
    private long idBarrioEmpresaMetalurgica, idLocalidadEmpresaMetalurgica, idProvinciaEmpresaMetalurgica;
    private long idBarrioResponsable, idLocalidadResponsable, idProvinciaResponsable;
    private long idPrioridadEmpresaMetalurgica, idCondicionIva;
    private long idTipoDocResponsable;
    private long idResponsable;
    private long idEmpresaMetalurgica;

    public GestorEmpresaMantenimiento() {
    }

    public Proveedormantenimientomaquina buscarEmpresaMantenimiento(long id)
    {
        ProveedormantenimientomaquinaJpaController controller=new ProveedormantenimientomaquinaJpaController(JpaUtil.getEntityManagerFactory());
        Proveedormantenimientomaquina empresa=controller.findProveedormantenimientomaquina(id);
        return empresa;
    }
    public List<Proveedormantenimientomaquina> buscarProveedormantenimientomaquina(String valor) {
        List<Proveedormantenimientomaquina> lstResult=new LinkedList<Proveedormantenimientomaquina>();
        try {
            Query query = JpaUtil.getNamedQuery("Proveedormantenimientomaquina.findLikeRazonsocial");
            query.setParameter("razonsocial", valor+"%");
            lstResult = query.getResultList();

        } catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstResult;
    }
    public void buscarCondicionIva(JComboBox combo) {
        try {
            List<Condicioniva> condiciones = null;
            CondicionivaJpaController controller = new CondicionivaJpaController(JpaUtil.getEntityManagerFactory());
            condiciones = controller.findCondicionivaEntities();
            ItemCombo item = null;
            combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
            for (Condicioniva cond : condiciones) {
                item = new ItemCombo();
                item.setId(String.valueOf(cond.getIdcondicioniva()));
                item.setMostrar(cond.getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void obtenerTipoDocumentos(JComboBox combo) {
        try {
            List<Tipodocumento> tipoDocumentos = null;
            TipodocumentoJpaController controller = new TipodocumentoJpaController(JpaUtil.getEntityManagerFactory());
            tipoDocumentos = controller.findTipodocumentoEntities();
            ItemCombo item = null;
            combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
            for (Tipodocumento tipoDoc : tipoDocumentos) {
                item = new ItemCombo();
                item.setId(String.valueOf(tipoDoc.getIdtipodocumento()));
                item.setMostrar(tipoDoc.getTipo());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void obtenerProvincias(JComboBox combo) {
        try {
            List<Provincia> provincias = null;
            ProvinciaJpaController controller = new ProvinciaJpaController(JpaUtil.getEntityManagerFactory());
            provincias = controller.findProvinciaEntities();
            ItemCombo item = null;
            combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
            for (Provincia prov : provincias) {
                item = new ItemCombo();
                item.setId(String.valueOf(prov.getIdprovincia()));
                item.setMostrar(prov.getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscarLocalidadesDeProvincia(JComboBox combo, long id) {

        try {
            List<Localidad> localidades = null;
            ProvinciaJpaController controller = new ProvinciaJpaController(JpaUtil.getEntityManagerFactory());
            localidades = controller.findProvincia(id).getLocalidadList();
            ItemCombo item = null;
            combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
            for (Localidad loc : localidades) {
                item = new ItemCombo();
                item.setId(String.valueOf(loc.getIdlocalidad()));
                item.setMostrar(loc.getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscarBarriosDeLocalidad(JComboBox combo, long id) {

        try {
            List<Barrio> barrios = null;
            LocalidadJpaController controller = new LocalidadJpaController(JpaUtil.getEntityManagerFactory());
            barrios = controller.findLocalidad(id).getBarrioList();
            ItemCombo item = null;
            combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
            for (Barrio barrio : barrios) {
                item = new ItemCombo();
                item.setId(String.valueOf(barrio.getIdbarrio()));
                item.setMostrar(barrio.getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);

        } catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public long guardarEmpresaMantenimiento(Proveedormantenimientomaquina empresa) {
        ProveedormantenimientomaquinaJpaController controller = new ProveedormantenimientomaquinaJpaController(JpaUtil.getEntityManagerFactory());
        DomicilioJpaController controllerDomicilio = new DomicilioJpaController(JpaUtil.getEntityManagerFactory());
        ResponsableJpaController controllerResponsable = new ResponsableJpaController(JpaUtil.getEntityManagerFactory());
        try {
            controllerDomicilio.create(empresa.getResponsable().getDomicilio());
            controllerResponsable.create(empresa.getResponsable());
            controllerDomicilio.create(empresa.getDomicilio());
            controller.create(empresa);

        } catch (PreexistingEntityException ex) {
            Logger.getLogger(GestorEmpresaMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empresa.getIdproveedormantenimiento();
    }
    public long modificarEmpresaMantenimiento(Proveedormantenimientomaquina empresa)
    {
        ProveedormantenimientomaquinaJpaController controller = new ProveedormantenimientomaquinaJpaController(JpaUtil.getEntityManagerFactory());
        DomicilioJpaController controllerDomicilio = new DomicilioJpaController(JpaUtil.getEntityManagerFactory());
        ResponsableJpaController controllerResponsable = new ResponsableJpaController(JpaUtil.getEntityManagerFactory());
        Domicilio domResp=null;
        Responsable resp=null;
        Domicilio domicilio=null;
        try {

            controllerDomicilio.edit(empresa.getDomicilio());
            controllerDomicilio.edit(empresa.getResponsable().getDomicilio());
            controllerResponsable.edit(empresa.getResponsable());
            controller.edit(empresa);

        } catch (PreexistingEntityException ex) {
            Logger.getLogger(GestorEmpresaMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return empresa.getIdproveedormantenimiento();
    }

    public Localidad buscarLocalidadDeBarrio(Barrio barrio) {

        return barrio.getLocalidad();
    }

    public long generarNvoNroEmpresa() {
        long result=-1;
        PostgreSQLManager pg=null;
        Connection cn=null;
        pg=new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
            result=AccessFunctions.nvoNroProveedorMantenimiento(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpresaMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    public Condicioniva obtenerCondicionIva(long id)
    {
        CondicionivaJpaController controller=new CondicionivaJpaController(JpaUtil.getEntityManagerFactory());
        return controller.findCondicioniva(id);
    }
    public Tipodocumento obtenerTipoDocumento(long id)
    {
        TipodocumentoJpaController controller=new TipodocumentoJpaController(JpaUtil.getEntityManagerFactory());
        return controller.findTipodocumento(id);
    }
    public Barrio obtenerBarrio(long id)
    {
        BarrioJpaController controller=new BarrioJpaController(JpaUtil.getEntityManagerFactory());
        return controller.findBarrio(id);
    }

}
