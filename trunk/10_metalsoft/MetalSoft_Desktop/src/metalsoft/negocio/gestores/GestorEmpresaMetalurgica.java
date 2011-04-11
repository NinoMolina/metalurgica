/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import metalsoft.datos.dbobject.LocalidadDB;

import metalsoft.datos.exception.BarrioException;
import metalsoft.datos.exception.LocalidadException;
import metalsoft.datos.jpa.controller.BarrioJpaController;
import metalsoft.datos.jpa.controller.CondicionivaJpaController;
import metalsoft.datos.jpa.controller.DomicilioJpaController;
import metalsoft.datos.jpa.controller.EmpresametalurgicaJpaController;
import metalsoft.datos.jpa.controller.LocalidadJpaController;
import metalsoft.datos.jpa.controller.ProvinciaJpaController;
import metalsoft.datos.jpa.controller.ResponsableJpaController;
import metalsoft.datos.jpa.controller.RolJpaController;
import metalsoft.datos.jpa.controller.TipodocumentoJpaController;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Provincia;
import metalsoft.datos.jpa.entity.Condicioniva;
import metalsoft.datos.jpa.entity.Tipodocumento;
import metalsoft.datos.jpa.entity.Localidad;
import metalsoft.datos.jpa.entity.Barrio;
import metalsoft.datos.jpa.entity.Domicilio;
import metalsoft.datos.jpa.entity.Empresametalurgica;
import metalsoft.datos.jpa.entity.Responsable;
import metalsoft.negocio.access.AccessEmpresaMetalurgica;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.gestores.Parser;

/**
 *
 * @author Lorreine Prescott
 */
public class GestorEmpresaMetalurgica {

    private long idDomicilioEmpresaMetalurgica, idDomicilioResponsable;
    private long idBarrioEmpresaMetalurgica, idLocalidadEmpresaMetalurgica, idProvinciaEmpresaMetalurgica;
    private long idBarrioResponsable, idLocalidadResponsable, idProvinciaResponsable;
    private long idPrioridadEmpresaMetalurgica, idCondicionIva;
    private long idTipoDocResponsable;
    private long idResponsable;
    private long idEmpresaMetalurgica;

    public GestorEmpresaMetalurgica() {
    }

    public Empresametalurgica buscarEmpresaMetalurgica(long id)
    {
        EmpresametalurgicaJpaController controller=new EmpresametalurgicaJpaController();
        Empresametalurgica empresa=controller.findEmpresametalurgica(id);
        return empresa;
    }
    public void buscarCondicionIva(JComboBox combo) {
        try {
            List<Condicioniva> condiciones = null;
            CondicionivaJpaController controller = new CondicionivaJpaController();
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
            TipodocumentoJpaController controller = new TipodocumentoJpaController();
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
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void obtenerProvincias(JComboBox combo) {
        try {
            List<Provincia> provincias = null;
            ProvinciaJpaController controller = new ProvinciaJpaController();
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
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscarLocalidadesDeProvincia(JComboBox combo, long id) {

        try {
            List<Localidad> localidades = null;
            ProvinciaJpaController controller = new ProvinciaJpaController();
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
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscarBarriosDeLocalidad(JComboBox combo, long id) {

        try {
            List<Barrio> barrios = null;
            LocalidadJpaController controller = new LocalidadJpaController();
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
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public long guardarEmpresaMetalurgica(Empresametalurgica empresa) {
        EmpresametalurgicaJpaController controller = new EmpresametalurgicaJpaController();
        DomicilioJpaController controllerDomicilio = new DomicilioJpaController();
        ResponsableJpaController controllerResponsable = new ResponsableJpaController();
        try {
            controllerDomicilio.create(empresa.getResponsable().getDomicilio());
            controllerResponsable.create(empresa.getResponsable());
            controllerDomicilio.create(empresa.getDomicilio());
            controller.create(empresa);

        } catch (PreexistingEntityException ex) {
            Logger.getLogger(GestorNuevoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GestorNuevoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empresa.getIdempresametalurgica();
    }
    public long modificarEmpresaMetalurgica(Empresametalurgica empresa)
    {
        EmpresametalurgicaJpaController controller = new EmpresametalurgicaJpaController();
        DomicilioJpaController controllerDomicilio = new DomicilioJpaController();
        ResponsableJpaController controllerResponsable = new ResponsableJpaController();
        Domicilio domResp=null;
        Responsable resp=null;
        Domicilio domicilio=null;
        try {
            domResp=controllerDomicilio.findDomicilio(empresa.getResponsable().getDomicilio().getIddomicilio());
            resp=controllerResponsable.findResponsable(empresa.getResponsable().getIdresponsable());
            domicilio=controllerDomicilio.findDomicilio(empresa.getDomicilio().getIddomicilio());

            if(domResp==null) controllerDomicilio.create(empresa.getResponsable().getDomicilio());
            if(resp==null) controllerResponsable.create(empresa.getResponsable());
            if(domicilio==null) controllerDomicilio.create(empresa.getDomicilio());
            controller.edit(empresa);

        } catch (PreexistingEntityException ex) {
            Logger.getLogger(GestorNuevoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GestorNuevoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empresa.getIdempresametalurgica();
    }

    public Localidad buscarLocalidadDeBarrio(Barrio barrio) {

        return barrio.getLocalidad();
    }
}
