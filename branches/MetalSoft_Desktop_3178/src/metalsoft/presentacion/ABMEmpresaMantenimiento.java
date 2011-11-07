/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMEmpresaMantenimiento.java
 *
 * Created on 03/07/2011, 18:12:30
 */

package metalsoft.presentacion;

import java.awt.Graphics;
import java.math.BigInteger;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import metalsoft.datos.jpa.entity.Barrio;
import metalsoft.datos.jpa.entity.Condicioniva;
import metalsoft.datos.jpa.entity.Domicilio;
import metalsoft.datos.jpa.entity.Localidad;
import metalsoft.datos.jpa.entity.Proveedormantenimientomaquina;

import metalsoft.datos.jpa.entity.Responsable;
import metalsoft.datos.jpa.entity.Tipodocumento;
import metalsoft.negocio.gestores.GestorEmpresaMantenimiento;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.util.EnumOpcionesABM;
import metalsoft.util.Fecha;
import metalsoft.util.ItemCombo;

/**
 *
 * @author Vicky
 */
public class ABMEmpresaMantenimiento extends javax.swing.JDialog {

    /** Creates new form ABMEmpresaMantenimiento */
    private GestorEmpresaMantenimiento gestor;
    private Domicilio domicilioEmpresa;
    private Domicilio domicilioResponsable;
    private long idDomicilio;
    private long idResponsable;
    private Responsable responsable;
    private Proveedormantenimientomaquina empresa;
    private EnumOpcionesABM opcion;
    private long idEmpresa;
    public ABMEmpresaMantenimiento() {
        super(Principal.getVtnPrincipal());
        initComponents();
        gestor = new GestorEmpresaMantenimiento();
        cargarComboCondIva();
        cargarComboProvincia(beanDomicilioCliente.getCmbProvincia());
        cargarComboProvincia(beanResponsable.getDomicilioResponsable().getCmbProvincia());
        cargarTipoDocumento();
        addListeners();
        botones.getBtnEliminar().setEnabled(false);
        botones.getBtnGuardar().setEnabled(false);
        botones.getBtnModificar().setEnabled(false);

        setEnableComponents(false);
    }

    private void addListeners() {
        addListenerCmbProvincia();
        addListenerCmbLocalidad();
        addListenerBtnNuevo();
        addListenerBtnGuardar();
        addListenerBtnModificar();
        addListenerBtnBuscar();
        addListenerBtnSalir();
        addListenerBtnEliminar();
    }

    private void addListenerBtnNuevo() {
        botones.getBtnNuevo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
    }

    private void addListenerBtnEliminar() {
        botones.getBtnEliminar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        if (JOptionPane.showConfirmDialog(this,"Esta seguro que desea dar de baja la empresa?") == JOptionPane.OK_OPTION) {
            opcion = EnumOpcionesABM.ELIMINAR;
            empresa.setFechabaja(Fecha.fechaActualDate());
            idEmpresa = gestor.modificarEmpresaMantenimiento(empresa);
            JOptionPane.showMessageDialog(this, "La Empresa Metalurgica se dio de baja correctamente");
            limpiarCampos();
            botones.getBtnGuardar().setEnabled(false);
            botones.getBtnModificar().setEnabled(false);
            botones.getBtnEliminar().setEnabled(false);
        }
    }

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
        setEnableComponents(true);
        empresa = new Proveedormantenimientomaquina();
        limpiarCampos();
        opcion = EnumOpcionesABM.NUEVO;
        long nroCli = gestor.generarNvoNroEmpresa();
        lblNroCliente.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PROVEEDOR, nroCli));
        txtFechaAlta.setDate(Fecha.fechaActualDate());
        botones.getBtnGuardar().setEnabled(true);
        botones.getBtnEliminar().setEnabled(false);
        botones.getBtnModificar().setEnabled(false);
    }

    private void addListenerBtnGuardar() {
        botones.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
    }

    private void addListenerBtnModificar() {
        botones.getBtnModificar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
    }

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {
        opcion = EnumOpcionesABM.MODIFICAR;
        setEnableComponents(true);
        botones.getBtnGuardar().setEnabled(true);
        botones.getBtnModificar().setEnabled(false);
        botones.getBtnEliminar().setEnabled(false);
    }

    private void addListenerBtnBuscar() {
        botones.getBtnBuscar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        opcion = EnumOpcionesABM.BUSCAR;
        ABMEmpresaMantenimiento_Buscar buscar = null;
        buscar = new ABMEmpresaMantenimiento_Buscar(this);
        buscar.setVentana(this);
        buscar.setGestor(gestor);

        JFrameManager.centrarYMostrarVentana(buscar);
    }

    private void addListenerBtnSalir() {
        botones.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        if (!esValido()) {
            JOptionPane.showMessageDialog(this, "No se han ingresado todos los datos requeridos");
            return;
        }

        idEmpresa = -1;

        switch (opcion) {
            case NUEVO:
                empresa = nuevaEmpresa();
                idEmpresa = gestor.guardarEmpresaMantenimiento(empresa);
                break;
            case MODIFICAR:
                empresa = modificarEmpresa();
                idEmpresa = gestor.modificarEmpresaMantenimiento(empresa);
                break;
            default:
                break;
        }
        opcion = EnumOpcionesABM.GUARDAR;

        if (idEmpresa > 0) {
            JOptionPane.showMessageDialog(this, "La Empresa de Mantenimiento se guardó correctamente");
            botones.getBtnGuardar().setEnabled(false);
            botones.getBtnModificar().setEnabled(false);
            botones.getBtnEliminar().setEnabled(false);
            setEnableComponents(false);

        } else {
            JOptionPane.showMessageDialog(this, "No se pudo guardar la Empresa  de Mantenimiento");
        }
    }

    public Proveedormantenimientomaquina modificarEmpresa() {


        long idCondIva = Long.parseLong(((ItemCombo) cmbCondicionIVA.getSelectedItem()).getId());
        Condicioniva condIVA = gestor.obtenerCondicionIva(idCondIva);
        long idBarrioCliente = Long.parseLong(((ItemCombo) beanDomicilioCliente.getCmbBarrio().getSelectedItem()).getId());
        Barrio barrioCliente = gestor.obtenerBarrio(idBarrioCliente);
        long idLocalidadCliente = Long.parseLong(((ItemCombo) beanDomicilioCliente.getCmbLocalidad().getSelectedItem()).getId());
        long idProvinciaCliente = Long.parseLong(((ItemCombo) beanDomicilioCliente.getCmbProvincia().getSelectedItem()).getId());
        long idBarrioResponsable = Long.parseLong(((ItemCombo) beanResponsable.getDomicilioResponsable().getCmbBarrio().getSelectedItem()).getId());
        Barrio barrioResponsable = gestor.obtenerBarrio(idBarrioResponsable);
        long idLocalidadResponsable = Long.parseLong(((ItemCombo) beanResponsable.getDomicilioResponsable().getCmbLocalidad().getSelectedItem()).getId());
        long idProvinciaResponsable = Long.parseLong(((ItemCombo) beanResponsable.getDomicilioResponsable().getCmbProvincia().getSelectedItem()).getId());
        long idTipoDocResponsable = Long.parseLong(((ItemCombo) beanResponsable.getCmbTipoDoc().getSelectedItem()).getId());
        Tipodocumento tipodocResp = gestor.obtenerTipoDocumento(idTipoDocResponsable);

        //domicilio empresa
        domicilioEmpresa.setCalle(beanDomicilioCliente.getTxtCalle().getText());
        domicilioEmpresa.setDepto(beanDomicilioCliente.getTxtDepto().getText());
        domicilioEmpresa.setNumerocalle(Integer.parseInt(beanDomicilioCliente.getTxtNumero().getText()));
        domicilioEmpresa.setPiso(Integer.parseInt(String.valueOf(beanDomicilioCliente.getSldPiso().getValue())));
        domicilioEmpresa.setTorre(beanDomicilioCliente.getTxtTorre().getText());
        domicilioEmpresa.setBarrio(barrioCliente);

        //Responsable
        responsable.setApellido(beanResponsable.getTxtApellido().getText());
        responsable.setEmail(beanResponsable.getTxtEmail().getText());
        responsable.setFax(beanResponsable.getTxtFax().getText());
        responsable.setNombre(beanResponsable.getTxtNombre().getText());
        responsable.setNrodocumento(Integer.parseInt(beanResponsable.getTxtNroDoc().getText()));
        responsable.setTelefono(beanResponsable.getTxtTelefono().getText());
        responsable.setTipodocumento(tipodocResp);

        //domicilio responsable
        domicilioResponsable.setCalle(beanResponsable.getDomicilioResponsable().getTxtCalle().getText());
        domicilioResponsable.setDepto(beanResponsable.getDomicilioResponsable().getTxtDepto().getText());
        domicilioResponsable.setNumerocalle(Integer.parseInt(beanResponsable.getDomicilioResponsable().getTxtNumero().getText()));
        domicilioResponsable.setPiso(Integer.parseInt(String.valueOf(beanResponsable.getDomicilioResponsable().getSldPiso().getValue())));
        domicilioResponsable.setTorre(beanResponsable.getDomicilioResponsable().getTxtTorre().getText());
        domicilioResponsable.setBarrio(barrioResponsable);

        responsable.setDomicilio(domicilioResponsable);

        String cuitCli = txtCUIT.getText();
        String celCli = txtCelular.getText();
        Date fecAltaCli = txtFechaAlta.getDate();
        Date fecBajaCli = txtFechaBaja.getDate();
        String mailCli = txtMail.getText();
        long nro = NumerosAMostrar.getNumeroLong(lblNroCliente.getText());
        String nroCli = String.valueOf(nro);
        String razonCli = txtRazonSocial.getText();
        String telCli = txtTelefono.getText();
        empresa.setNroproveedor(BigInteger.valueOf(nro));
        empresa.setCelular(celCli);
        empresa.setCuit(cuitCli);
        empresa.setFechaalta(fecAltaCli);
        empresa.setFechabaja(fecBajaCli);
        empresa.setMail(mailCli);
        empresa.setRazonsocial(razonCli);
        empresa.setTelefono(telCli);
        empresa.setCondicioniva(condIVA);

        empresa.setResponsable(responsable);
        empresa.setDomicilio(domicilioEmpresa);

        return empresa;
    }

    public Proveedormantenimientomaquina nuevaEmpresa() {

        empresa = new Proveedormantenimientomaquina();
        long idCondIva = Long.parseLong(((ItemCombo) cmbCondicionIVA.getSelectedItem()).getId());
        Condicioniva condIVA = gestor.obtenerCondicionIva(idCondIva);
        long idBarrioCliente = Long.parseLong(((ItemCombo) beanDomicilioCliente.getCmbBarrio().getSelectedItem()).getId());
        Barrio barrioCliente = gestor.obtenerBarrio(idBarrioCliente);
        long idLocalidadCliente = Long.parseLong(((ItemCombo) beanDomicilioCliente.getCmbLocalidad().getSelectedItem()).getId());
        long idProvinciaCliente = Long.parseLong(((ItemCombo) beanDomicilioCliente.getCmbProvincia().getSelectedItem()).getId());
        long idBarrioResponsable = Long.parseLong(((ItemCombo) beanResponsable.getDomicilioResponsable().getCmbBarrio().getSelectedItem()).getId());
        Barrio barrioResponsable = gestor.obtenerBarrio(idBarrioResponsable);
        long idLocalidadResponsable = Long.parseLong(((ItemCombo) beanResponsable.getDomicilioResponsable().getCmbLocalidad().getSelectedItem()).getId());
        long idProvinciaResponsable = Long.parseLong(((ItemCombo) beanResponsable.getDomicilioResponsable().getCmbProvincia().getSelectedItem()).getId());
        long idTipoDocResponsable = Long.parseLong(((ItemCombo) beanResponsable.getCmbTipoDoc().getSelectedItem()).getId());
        Tipodocumento tipodocResp = gestor.obtenerTipoDocumento(idTipoDocResponsable);
        String calle = beanDomicilioCliente.getTxtCalle().getText();
        String depto = beanDomicilioCliente.getTxtDepto().getText();
        String nroCalle = beanDomicilioCliente.getTxtNumero().getText();
        String piso = String.valueOf(beanDomicilioCliente.getSldPiso().getValue());
        String torre = beanDomicilioCliente.getTxtTorre().getText();
        //creo domicilio Empresa sin referencias
        domicilioEmpresa = crearDomicilio(calle, depto, nroCalle, piso, torre, barrioCliente);

        String apeResp = beanResponsable.getTxtApellido().getText();
        String emaResp = beanResponsable.getTxtEmail().getText();
        String faxResp = beanResponsable.getTxtFax().getText();
        String nomResp = beanResponsable.getTxtNombre().getText();
        String nrdResp = beanResponsable.getTxtNroDoc().getText();
        String telResp = beanResponsable.getTxtTelefono().getText();
        //creo responsable sin referencias
        responsable = crearResponsable(apeResp, emaResp, faxResp, nomResp, nrdResp, telResp);
        responsable.setTipodocumento(tipodocResp);
        calle = beanResponsable.getDomicilioResponsable().getTxtCalle().getText();
        depto = beanResponsable.getDomicilioResponsable().getTxtDepto().getText();
        nroCalle = beanResponsable.getDomicilioResponsable().getTxtNumero().getText();
        piso = String.valueOf(beanResponsable.getDomicilioResponsable().getSldPiso().getValue());
        torre = beanResponsable.getDomicilioResponsable().getTxtTorre().getText();
        //creo domicilio responsable sin referencias
        domicilioResponsable = crearDomicilio(calle, depto, nroCalle, piso, torre, barrioResponsable);
        responsable.setDomicilio(domicilioResponsable);

        String cuitCli = txtCUIT.getText();
        String celCli = txtCelular.getText();
        Date fecAltaCli = txtFechaAlta.getDate();
        Date fecBajaCli = txtFechaBaja.getDate();
        String mailCli = txtMail.getText();
        long nro = NumerosAMostrar.getNumeroLong(lblNroCliente.getText());
        String razonCli = txtRazonSocial.getText();
        String telCli = txtTelefono.getText();
        empresa.setNroproveedor(BigInteger.valueOf(nro));
        empresa.setCelular(celCli);
        empresa.setCuit(cuitCli);
        empresa.setFechaalta(fecAltaCli);
        empresa.setFechabaja(fecBajaCli);
        empresa.setMail(mailCli);
        empresa.setRazonsocial(razonCli);
        empresa.setTelefono(telCli);
        empresa.setCondicioniva(condIVA);

        empresa.setResponsable(responsable);
        empresa.setDomicilio(domicilioEmpresa);

        return empresa;
    }

    private boolean esValido() {
        boolean result = true;
        if (txtRazonSocial.getText().compareTo("") == 0) {
            result = false;
        }
        if (txtCUIT.getText().compareTo("") == 0) {
            result = false;
        }
        if (cmbCondicionIVA.getSelectedIndex() <= 0) {
            result = false;
        }
        if (beanDomicilioCliente.getCmbBarrio().getSelectedIndex() <= 0) {
            result = false;
        }
        if (beanDomicilioCliente.getCmbLocalidad().getSelectedIndex() <= 0) {
            result = false;
        }
        if (beanDomicilioCliente.getCmbProvincia().getSelectedIndex() <= 0) {
            result = false;
        }
        if (beanDomicilioCliente.getTxtCalle().getText().compareTo("") == 0) {
            result = false;
        }
        if (beanDomicilioCliente.getTxtNumero().getText().compareTo("") == 0) {
            result = false;
        }

        if (beanResponsable.getTxtNombre().getText().compareTo("") == 0) {
            result = false;
        }
        if (beanResponsable.getTxtApellido().getText().compareTo("") == 0) {
            result = false;
        }
        if (beanResponsable.getTxtNroDoc().getText().compareTo("") == 0) {
            result = false;
        }
        if (beanResponsable.getCmbTipoDoc().getSelectedIndex() <= 0) {
            result = false;
        }
        if (beanResponsable.getDomicilioResponsable().getCmbBarrio().getSelectedIndex() <= 0) {
            result = false;
        }
        if (beanResponsable.getDomicilioResponsable().getCmbLocalidad().getSelectedIndex() <= 0) {
            result = false;
        }
        if (beanResponsable.getDomicilioResponsable().getCmbProvincia().getSelectedIndex() <= 0) {
            result = false;
        }
        if (beanResponsable.getDomicilioResponsable().getTxtCalle().getText().compareTo("") == 0) {
            result = false;
        }
        if (beanResponsable.getDomicilioResponsable().getTxtNumero().getText().compareTo("") == 0) {
            result = false;
        }

        return result;
    }

    private void setEnableComponents(boolean b) {
        txtCUIT.setEnabled(b);
        txtCelular.setEnabled(b);
        txtFechaAlta.setEnabled(b);
        txtFechaBaja.setEnabled(b);
        txtMail.setEnabled(b);
        txtRazonSocial.setEnabled(b);
        txtTelefono.setEnabled(b);
        cmbCondicionIVA.setEnabled(b);
        beanDomicilioCliente.setEnabled(b);
        beanResponsable.setEnabled(b);
        beanDomicilioCliente.getBtnAgregarBarrio().setEnabled(b);
        beanDomicilioCliente.getBtnAgregarLocalidad().setEnabled(b);
        beanDomicilioCliente.getBtnAgregarProvincia().setEnabled(b);
        beanResponsable.getDomicilioResponsable().getBtnAgregarBarrio().setEnabled(b);
        beanResponsable.getDomicilioResponsable().getBtnAgregarLocalidad().setEnabled(b);
        beanResponsable.getDomicilioResponsable().getBtnAgregarProvincia().setEnabled(b);
    }

    private void limpiarCampos() {
        txtCUIT.setText("");
        txtCelular.setText("");
        txtFechaAlta.setDate(Fecha.fechaActualDate());
        txtFechaBaja.setDate(null);
        txtMail.setText("");
        lblNroCliente.setText("");
        txtRazonSocial.setText("");
        txtTelefono.setText("");
        cmbCondicionIVA.setSelectedIndex(0);

        beanDomicilioCliente.limpiarCampos();
        beanResponsable.limpiarCampos();
    }

    public long getIdCliente() {
        return idEmpresa;
    }

    public void setIdEmpresa(long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    private void addListenerCmbProvincia() {
        beanDomicilioCliente.getCmbProvincia().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProvinciaActionPerformed(evt);
            }
        });
        beanResponsable.getDomicilioResponsable().getCmbProvincia().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProvinciaDomResponsableActionPerformed(evt);
            }
        });
    }

    private void addListenerCmbLocalidad() {
        beanDomicilioCliente.getCmbLocalidad().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLocalidadActionPerformed(evt);
            }
        });
        beanResponsable.getDomicilioResponsable().getCmbLocalidad().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLocalidadDomResponsableActionPerformed(evt);
            }
        });
    }

    private void cmbProvinciaDomResponsableActionPerformed(java.awt.event.ActionEvent evt) {
        JComboBox cmbProvincia = beanResponsable.getDomicilioResponsable().getCmbProvincia();
        JComboBox cmbLocalidad = beanResponsable.getDomicilioResponsable().getCmbLocalidad();
        JComboBox cmbBarrio = beanResponsable.getDomicilioResponsable().getCmbBarrio();
        cmbLocalidad.removeAllItems();
        cmbBarrio.removeAllItems();
        if (cmbProvincia.getSelectedIndex() > 0) {
            String indexString = ((ItemCombo) cmbProvincia.getSelectedItem()).getId();
            int index = Integer.parseInt(indexString);
            gestor.buscarLocalidadesDeProvincia(cmbLocalidad, index);
        }
    }

    private void cmbProvinciaActionPerformed(java.awt.event.ActionEvent evt) {
        JComboBox cmbProvincia = beanDomicilioCliente.getCmbProvincia();
        JComboBox cmbLocalidad = beanDomicilioCliente.getCmbLocalidad();
        JComboBox cmbBarrio = beanDomicilioCliente.getCmbBarrio();
        cmbLocalidad.removeAllItems();
        cmbBarrio.removeAllItems();
        if (cmbProvincia.getSelectedIndex() > 0) {
            String indexString = ((ItemCombo) cmbProvincia.getSelectedItem()).getId();
            int index = Integer.parseInt(indexString);
            gestor.buscarLocalidadesDeProvincia(cmbLocalidad, index);
        }
    }

    private void cmbLocalidadDomResponsableActionPerformed(java.awt.event.ActionEvent evt) {
        JComboBox cmbLocalidad = beanResponsable.getDomicilioResponsable().getCmbLocalidad();
        JComboBox cmbBarrio = beanResponsable.getDomicilioResponsable().getCmbBarrio();
        cmbBarrio.removeAllItems();
        if (cmbLocalidad.getSelectedIndex() > 0) {
            String indexString = ((ItemCombo) cmbLocalidad.getSelectedItem()).getId();
            int index = Integer.parseInt(indexString);
            gestor.buscarBarriosDeLocalidad(cmbBarrio, index);
        }
    }

    private void cmbLocalidadActionPerformed(java.awt.event.ActionEvent evt) {
        JComboBox cmbLocalidad = beanDomicilioCliente.getCmbLocalidad();
        JComboBox cmbBarrio = beanDomicilioCliente.getCmbBarrio();
        cmbBarrio.removeAllItems();
        if (cmbLocalidad.getSelectedIndex() > 0) {
            String indexString = ((ItemCombo) cmbLocalidad.getSelectedItem()).getId();
            int index = Integer.parseInt(indexString);
            gestor.buscarBarriosDeLocalidad(cmbBarrio, index);
        }
    }

    private Domicilio crearDomicilio(String calle, String depto, String nroCalle, String piso, String torre, Barrio barrio) {
        Domicilio dom = new Domicilio();
        dom.setCalle(calle);
        dom.setDepto(depto);
        dom.setNumerocalle(Integer.parseInt(nroCalle));
        dom.setPiso(Integer.parseInt(piso));
        dom.setTorre(torre);
        dom.setBarrio(barrio);

        return dom;
    }

    private void cargarComboCondIva() {
        cmbCondicionIVA.removeAllItems();
        gestor.buscarCondicionIva(cmbCondicionIVA);
    }

    public void mostrarDatosDomicilio() {

        String prov = domicilioEmpresa.getBarrio().getLocalidad().getProvincia().getNombre();
        String barrio = domicilioEmpresa.getBarrio().getNombre();
        String localidad = domicilioEmpresa.getBarrio().getLocalidad().getNombre();
        String calle = domicilioEmpresa.getCalle();
        int nro = domicilioEmpresa.getNumerocalle();
        int piso = domicilioEmpresa.getPiso();
        String torre = domicilioEmpresa.getTorre();
        String numero = String.valueOf(domicilioEmpresa.getNumerocalle());
        beanDomicilioCliente.getTxtCalle().setText(calle);
        beanDomicilioCliente.getTxtNumero().setText(String.valueOf(nro));
        beanDomicilioCliente.getTxtTorre().setText(torre);
        beanDomicilioCliente.getSldPiso().setValue(piso);
    }

    public void mostrarDatosResponsable() {
        String nombre = responsable.getNombre();
        String apellido = responsable.getApellido();
    }

    public void cargarComboProvincia(JComboBox cmb) {
        cmb.removeAllItems();
        gestor.obtenerProvincias(cmb);
    }

    private Responsable crearResponsable(String apellido, String email, String fax, String nombre, String nroDoc, String telefono) {
        Responsable resp = new Responsable();
        resp.setApellido(apellido);
        resp.setEmail(email);
        resp.setFax(fax);
        resp.setNombre(nombre);
        resp.setNrodocumento(Integer.parseInt(nroDoc));
        resp.setTelefono(telefono);
        return resp;
    }

    private void cargarTipoDocumento() {
        beanResponsable.getCmbTipoDoc().removeAllItems();
        gestor.obtenerTipoDocumentos(beanResponsable.getCmbTipoDoc());
    }

    public void empresaSeleccionado() {
        empresa = gestor.buscarEmpresaMantenimiento(idEmpresa);
        mostrarDatosCliente();
        domicilioEmpresa = empresa.getDomicilio();
        responsable = empresa.getResponsable();
        domicilioResponsable = responsable.getDomicilio();
        setEnableComponents(false);
        botones.getBtnModificar().setEnabled(true);
        botones.getBtnGuardar().setEnabled(false);
        botones.getBtnEliminar().setEnabled(true);
        beanDomicilioCliente.getBtnAgregarBarrio().setEnabled(false);
        beanDomicilioCliente.getBtnAgregarLocalidad().setEnabled(false);
        beanDomicilioCliente.getBtnAgregarProvincia().setEnabled(false);
        beanResponsable.getDomicilioResponsable().getBtnAgregarBarrio().setEnabled(false);
        beanResponsable.getDomicilioResponsable().getBtnAgregarLocalidad().setEnabled(false);
        beanResponsable.getDomicilioResponsable().getBtnAgregarProvincia().setEnabled(false);
    }

    private void mostrarDatosCliente() {
        cargarComboCondIva();
        cargarComboProvincia(beanDomicilioCliente.getCmbProvincia());
        cargarComboProvincia(beanResponsable.getDomicilioResponsable().getCmbProvincia());
//        cargarComboLocalidad(beanDomicilioCliente.getCmbLocalidad());
//        cargarComboLocalidad(beanResponsable.getDomicilioResponsable().getCmbLocalidad());
//        cargarComboBarrio(beanDomicilioCliente.getCmbBarrio());
//        cargarComboBarrio(beanResponsable.getDomicilioResponsable().getCmbBarrio());
        cargarTipoDocumento();
        setDatosCliente();
    }

    private void setDatosCliente() {
        txtCUIT.setText(empresa.getCuit());
        txtCelular.setText(empresa.getCelular());

        if (empresa.getFechaalta() == null) {
            txtFechaAlta.setDate(null);
        } else {

            txtFechaAlta.setDate(empresa.getFechaalta());
        }

        if (empresa.getFechabaja() == null) {
            txtFechaBaja.setDate(null);
        } else {
            txtFechaBaja.setDate(empresa.getFechabaja());
        }
        txtMail.setText(empresa.getMail());
        lblNroCliente.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PROVEEDOR, Integer.parseInt(String.valueOf(empresa.getNroproveedor()))));
        txtRazonSocial.setText(empresa.getRazonsocial());
        txtTelefono.setText(empresa.getTelefono());

        setItemComboSeleccionado(cmbCondicionIVA, empresa.getCondicioniva().getIdcondicioniva());

        setDatosDomicilio(beanDomicilioCliente, empresa.getDomicilio());

        setDatosResponsable(empresa.getResponsable(), empresa.getResponsable().getDomicilio());
    }

    private void setDatosDomicilio(metalsoft.beans.Domicilio beanDom, Domicilio domDB) {
        beanDom.getTxtCalle().setText(domDB.getCalle());
        beanDom.getTxtDepto().setText(domDB.getDepto());
        beanDom.getTxtNumero().setText(String.valueOf(domDB.getNumerocalle()));
        beanDom.getTxtTorre().setText(domDB.getTorre());


        JComboBox cmbBarrio = beanDom.getCmbBarrio();
        JComboBox cmbLocalidad = beanDom.getCmbLocalidad();
        JComboBox cmbProvincia = beanDom.getCmbProvincia();

        Localidad locDB = gestor.buscarLocalidadDeBarrio(domDB.getBarrio());

        setItemComboSeleccionado(cmbProvincia, locDB.getProvincia().getIdprovincia());
        setItemComboSeleccionado(cmbLocalidad, locDB.getIdlocalidad());
        setItemComboSeleccionado(cmbBarrio, domDB.getBarrio().getIdbarrio());
    }

    private void setItemComboSeleccionado(JComboBox cmb, long id) {
        int length = cmb.getItemCount();
        ItemCombo item = null;
        for (int i = 0; i < length; i++) {
            item = (ItemCombo) cmb.getItemAt(i);
            if (Long.parseLong(item.getId()) == id) {
                cmb.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setDatosResponsable(Responsable respDB, Domicilio domRespDB) {
        beanResponsable.getTxtApellido().setText(respDB.getApellido());
        beanResponsable.getTxtEmail().setText(respDB.getEmail());
        beanResponsable.getTxtFax().setText(respDB.getFax());
        beanResponsable.getTxtNombre().setText(respDB.getNombre());
        beanResponsable.getTxtNroDoc().setText(String.valueOf(respDB.getNrodocumento()));
        beanResponsable.getTxtTelefono().setText(respDB.getTelefono());

        setItemComboSeleccionado(beanResponsable.getCmbTipoDoc(), respDB.getTipodocumento().getIdtipodocumento());

        metalsoft.beans.Domicilio beanDom = beanResponsable.getDomicilioResponsable();
        setDatosDomicilio(beanDom, domRespDB);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botones = new metalsoft.beans.ABM_Botones();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtRazonSocial = new javax.swing.JTextField();
        txtCUIT = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtMail = new javax.swing.JTextField();
        cmbCondicionIVA = new javax.swing.JComboBox();
        beanDomicilioCliente = new metalsoft.beans.Domicilio();
        beanResponsable = new metalsoft.beans.Responsable();
        lblNroCliente = new javax.swing.JLabel();
        txtFechaAlta = new com.toedter.calendar.JDateChooser();
        txtFechaBaja = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel(){

            @Override
            public void paint(Graphics g) {
                try {
                    g.drawImage(ImageIO.read(getClass().getResource("/img/fondopantallas2.png")), 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                }
                super.paint(g);
            }
        }
        ;

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Empresa de Mantenimiento");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Administrar Empresa Mantenimiento"));

        jLabel1.setText("Nro. Empresa:");

        jLabel2.setText("Condicón IVA:");

        jLabel3.setText("Razón Social:");

        jLabel5.setText("Fecha Alta:");

        jLabel6.setText("CUIT:");

        jLabel9.setText("Teléfono:");

        jLabel11.setText("Celular:");

        jLabel12.setText("Mail:");

        jLabel13.setText("Fecha Baja:");

        txtMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMailActionPerformed(evt);
            }
        });

        beanDomicilioCliente.setBorder(javax.swing.BorderFactory.createTitledBorder("Domicilio Empresa"));

        beanResponsable.setBorder(javax.swing.BorderFactory.createTitledBorder("Responsable"));

        lblNroCliente.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblNroCliente.setText("...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNroCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCUIT, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(9, 9, 9)
                        .addComponent(txtRazonSocial, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFechaBaja, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(6, 6, 6)
                        .addComponent(txtFechaAlta, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbCondicionIVA, 0, 75, Short.MAX_VALUE))
                    .addComponent(beanDomicilioCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beanResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(beanResponsable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(lblNroCliente))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCUIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbCondicionIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5)
                            .addComponent(txtFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel13)
                            .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(beanDomicilioCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botones, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botones, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMailActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtMailActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ABMEmpresaMantenimiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.Domicilio beanDomicilioCliente;
    private metalsoft.beans.Responsable beanResponsable;
    private metalsoft.beans.ABM_Botones botones;
    private javax.swing.JComboBox cmbCondicionIVA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblNroCliente;
    private javax.swing.JTextField txtCUIT;
    private javax.swing.JTextField txtCelular;
    private com.toedter.calendar.JDateChooser txtFechaAlta;
    private com.toedter.calendar.JDateChooser txtFechaBaja;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

}
