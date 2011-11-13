/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Principal.java
 *
 * Created on 12/06/2010, 02:19:22
 */
package metalsoft.presentacion;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Timer;
import metalsoft.Main;
import metalsoft.MetalsoftDispatcher;
import metalsoft.beans.JPanelTransparente;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacion;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacioncalidad;
import metalsoft.datos.jpa.entity.Detalleplanificacioncalidad;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import metalsoft.datos.jpa.entity.Ejecucionplanificacioncalidad;
import metalsoft.datos.jpa.entity.Ejecucionplanificacionproduccion;
import metalsoft.negocio.adminusuarios.Rol;
import metalsoft.negocio.adminusuarios.Usuario;
import metalsoft.negocio.gestores.GestorNuevoUsuario;
import metalsoft.util.BarCodeUtil;
import metalsoft.util.Fecha;
import metalsoft.util.MetalsoftProperties;

/**
 *
 * @author Nino
 */
public class Principal extends javax.swing.JFrame {

    private metalsoft.datos.dbobject.UsuarioDB usuario;
    private Rol[] roles;
    private Timer tiempo;
    private Map<Long, Detalleejecucionplanificacion> mapEtapasAtrasadas;
    private Map<Long, Detalleejecucionplanificacioncalidad> mapProcesosCalidadAtrasados;
    private Map<Long, Detalleplanificacionproduccion> mapEtapasListasParaLanzar;
    private Map<Long, Detalleplanificacioncalidad> mapProcesosListosParaLanzar;
    private static Principal vtnPrincipal = null;

    /** Creates new form Principal */
    public Principal(metalsoft.datos.dbobject.UsuarioDB usuario) {
        this.usuario = usuario;
        mapEtapasAtrasadas = new HashMap<Long, Detalleejecucionplanificacion>();
        mapProcesosCalidadAtrasados = new HashMap<Long, Detalleejecucionplanificacioncalidad>();
        mapEtapasListasParaLanzar = new HashMap<Long, Detalleplanificacionproduccion>();
        mapProcesosListosParaLanzar = new HashMap<Long, Detalleplanificacioncalidad>();

        Dimension de = Toolkit.getDefaultToolkit().getScreenSize();
        de.setSize(de.width, de.height - 40);
        this.setResizable(false);
        this.setPreferredSize(de);
        this.setMaximumSize(de);


        System.out.println(this.getPreferredSize());
        System.out.println(this.getMaximumSize());

        initComponents();

        System.out.println(this.getPreferredSize());
        System.out.println(this.getMaximumSize());

        iniciarReloj();

        this.setIconImage(new ImageIcon(getClass().getResource("/metalsoft/presentacion/img/LogoMS7.png")).getImage());

        obtenerRolUsuario(usuario.getIdusuario());

        String rolesTxt = "";
        for (int i = 0; i < roles.length; i++) {
            if ((i + 1) == roles.length) {
                rolesTxt += roles[i].getRol();
            } else {
                rolesTxt += roles[i].getRol() + ",";
            }
        }
        lblRol.setText(rolesTxt);
        lblRol.setToolTipText(rolesTxt);
        lblUsuario.setText(usuario.getUsuario());

        setVisibleComponents(false);
        vtnPrincipal = this;


    }

    public Principal() {
        mapEtapasAtrasadas = new HashMap<Long, Detalleejecucionplanificacion>();
        mapProcesosCalidadAtrasados = new HashMap<Long, Detalleejecucionplanificacioncalidad>();
        mapEtapasListasParaLanzar = new HashMap<Long, Detalleplanificacionproduccion>();
        mapProcesosListosParaLanzar = new HashMap<Long, Detalleplanificacioncalidad>();

        Dimension de = Toolkit.getDefaultToolkit().getScreenSize();
        de.setSize(de.width, de.height - 40);
        this.setResizable(false);
        this.setPreferredSize(de);
        this.setMaximumSize(de);

        System.out.println(this.getPreferredSize());
        System.out.println(this.getMaximumSize());

        initComponents();

        System.out.println(this.getPreferredSize());
        System.out.println(this.getMaximumSize());

        iniciarReloj();

        this.setIconImage(new ImageIcon(getClass().getResource("/metalsoft/presentacion/img/LogoMS7.png")).getImage());

        setVisibleComponents(false);
        vtnPrincipal = this;

    }

    public static void main(String arg[]) {
        Principal p = new Principal();
        p.setVisible(true);
    }

    public static Principal getVtnPrincipal() {
        return vtnPrincipal;
    }

    public static void setVtnPrincipal(Principal vtnPrincipal) {
        Principal.vtnPrincipal = vtnPrincipal;
    }

    private void iniciarReloj() {
        tiempo = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                lblReloj.setText(Fecha.fechaHoraMinutoSegundoActual());
            }
        });
        tiempo.start();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jScrollPane2 = new javax.swing.JScrollPane();
        pnlImagen = new metalsoft.beans.JPanelBackground();
        jPanelTransparente1 = new metalsoft.beans.JPanelTransparente();
        lblReloj = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblRol = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        pnlVentas = new metalsoft.beans.JPanelTransparente();
        btnCobros = new javax.swing.JButton(){

            @Override
            public void paint(Graphics g) {
                try {
                    g.drawImage(ImageIO.read(getClass().getResource("/img/fcobros.png")), 0, 0, getWidth(), getHeight(), this);
                    setContentAreaFilled(false);
                } catch (Exception e) {
                }
                super.paint(g);
            }
        }
        ;
        btnPedidos = new javax.swing.JButton(){

            @Override
            public void paint(Graphics g) {
                try {
                    g.drawImage(ImageIO.read(getClass().getResource("/img/fpedido.png")), 0, 0, getWidth(), getHeight(), this);
                    setContentAreaFilled(false);
                } catch (Exception e) {
                }
                super.paint(g);
            }
        }
        ;
        btnNuevoCliente = new javax.swing.JButton(){

            @Override
            public void paint(Graphics g) {
                try {
                    g.drawImage(ImageIO.read(getClass().getResource("/img/fcliente.png")), 0, 0, getWidth(), getHeight(), this);
                    setContentAreaFilled(false);
                } catch (Exception e) {
                }
                super.paint(g);
            }
        }
        ;
        pnlProduccion = new metalsoft.beans.JPanelTransparente();
        btnEjecutarProduccion = new javax.swing.JButton(){

            @Override
            public void paint(Graphics g) {
                try {
                    g.drawImage(ImageIO.read(getClass().getResource("/img/canstock5501471.jpg")), 0, 0, getWidth(), getHeight(), this);
                    setContentAreaFilled(false);
                } catch (Exception e) {
                }
                super.paint(g);
            }
        };
        btnProduccionEnEjecucion = new javax.swing.JButton() {

            @Override
            public void paint(Graphics g) {
                try {
                    g.drawImage(ImageIO.read(getClass().getResource("/img/fproduccion.png")), 0, 0, getWidth(), getHeight(), this);
                    setContentAreaFilled(false);
                } catch (Exception e) {
                }
                super.paint(g);
            }
        };
        btnEtapasAtrasadas = new javax.swing.JButton() {

            @Override
            public void paint(Graphics g) {
                try {
                    g.drawImage(ImageIO.read(getClass().getResource("/img/canstock7140471.jpg")), 0, 0, getWidth(), getHeight(), this);
                    setContentAreaFilled(false);
                } catch (Exception e) {
                }
                super.paint(g);
            }
        };
        btnEtapasListasParaLanzar = new javax.swing.JButton() {

            @Override
            public void paint(Graphics g) {
                try {
                    g.drawImage(ImageIO.read(getClass().getResource("/img/canstock7013611.jpg")), 0, 0, getWidth(), getHeight(), this);
                    setContentAreaFilled(false);
                } catch (Exception e) {
                }
                super.paint(g);
            }
        };
        jButton1 = new javax.swing.JButton(){

            @Override
            public void paint(Graphics g) {
                try {
                    g.drawImage(ImageIO.read(getClass().getResource("/img/canstock3235192.jpg")), 0, 0, getWidth(), getHeight(), this);
                    setContentAreaFilled(false);
                } catch (Exception e) {
                }
                super.paint(g);
            }
        }
        ;
        pnlCalidad = new metalsoft.beans.JPanelTransparente();
        btnLanzarProcesoCalidad = new javax.swing.JButton(){

            @Override
            public void paint(Graphics g) {
                try {
                    g.drawImage(ImageIO.read(getClass().getResource("/img/fcalidad2.png")), 0, 0, getWidth(), getHeight(), this);
                    setContentAreaFilled(false);
                } catch (Exception e) {
                }
                super.paint(g);
            }
        }
        ;
        btnProcesosCalidadAtrasados = new javax.swing.JButton(){

            @Override
            public void paint(Graphics g) {
                try {
                    g.drawImage(ImageIO.read(getClass().getResource("/img/fcalidad.png")), 0, 0, getWidth(), getHeight(), this);
                    setContentAreaFilled(false);
                } catch (Exception e) {
                }
                super.paint(g);
            }
        }
        ;
        btnControlesCalidadEnEjecucion = new javax.swing.JButton(){

            @Override
            public void paint(Graphics g) {
                try {
                    g.drawImage(ImageIO.read(getClass().getResource("/img/fEncalidad.png")), 0, 0, getWidth(), getHeight(), this);
                    setContentAreaFilled(false);
                } catch (Exception e) {
                }
                super.paint(g);
            }
        }
        ;
        btnProcesosCalidadListosParaLanzar = new javax.swing.JButton(){

            @Override
            public void paint(Graphics g) {
                try {
                    g.drawImage(ImageIO.read(getClass().getResource("/img/canstock4260021.jpg")), 0, 0, getWidth(), getHeight(), this);
                    setContentAreaFilled(false);
                } catch (Exception e) {
                }
                super.paint(g);
            }
        }
        ;
        btnReportes = new javax.swing.JButton(){

            @Override
            public void paint(Graphics g) {
                try {
                    g.drawImage(ImageIO.read(getClass().getResource("/img/canstock4378131.jpg")), 0, 0, getWidth(), getHeight(), this);
                    setContentAreaFilled(false);
                } catch (Exception e) {
                }
                super.paint(g);
            }
        }
        ;
        btnPresupuesto = new javax.swing.JButton(){

            @Override
            public void paint(Graphics g) {
                try {
                    g.drawImage(ImageIO.read(getClass().getResource("/img/canstock4270980.jpg")), 0, 0, getWidth(), getHeight(), this);
                    setContentAreaFilled(false);
                } catch (Exception e) {
                }
                super.paint(g);
            }
        }
        ;
        pnlRegistrarFinalizacion = new metalsoft.beans.JPanelTransparente();
        txtCodigoBarras = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnEnviarFinalizacion = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtComunicacion = new javax.swing.JTextArea();
        pnlRegistrarFinalizacion1 = new metalsoft.beans.JPanelTransparente();
        jLabel1 = new javax.swing.JLabel();
        mbrMenu = new javax.swing.JMenuBar();
        mnuInicio = new javax.swing.JMenu();
        mniNuevoUsuario = new javax.swing.JMenuItem();
        mniCerrarSesion = new javax.swing.JMenuItem();
        mniCambiarContrasenia = new javax.swing.JMenuItem();
        mniAdministrarUsuarios = new javax.swing.JMenuItem();
        mnuCompras = new javax.swing.JMenu();
        mniMateriaPrima = new javax.swing.JMenuItem();
        mniRegistrarPresupuesto = new javax.swing.JMenuItem();
        mniProveedor = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        mniReclamoProveedor = new javax.swing.JMenuItem();
        mniOrdenDeCompra = new javax.swing.JMenuItem();
        mnuVentas = new javax.swing.JMenu();
        mniCliente = new javax.swing.JMenuItem();
        mniRegistrarConfirmacionPedido = new javax.swing.JMenuItem();
        mniEntregaPedido = new javax.swing.JMenuItem();
        mniPedidoCotizacion = new javax.swing.JMenuItem();
        mnuProduccion = new javax.swing.JMenu();
        mniGenerarDetalleEtapas = new javax.swing.JMenuItem();
        mniGenerarDetalleMateriaPrima = new javax.swing.JMenuItem();
        mniLanzarProduccion = new javax.swing.JMenuItem();
        mniMatriz = new javax.swing.JMenuItem();
        mniRegistrarPlanificacionProduccion = new javax.swing.JMenuItem();
        mniTipoMaterial = new javax.swing.JMenuItem();
        mniMaquinaria = new javax.swing.JMenuItem();
        mniPieza = new javax.swing.JMenuItem();
        mniEtapaDeProduccion = new javax.swing.JMenuItem();
        mniProducto = new javax.swing.JMenuItem();
        mniParadaMaquina = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        mnuCalidad = new javax.swing.JMenu();
        mniGenerarDetalleProcedimientosCalidad = new javax.swing.JMenuItem();
        mniRegistrarPlanificacionCalidad = new javax.swing.JMenuItem();
        mniLanzarCalidad = new javax.swing.JMenuItem();
        mnuFinanzas = new javax.swing.JMenu();
        mniCobroPedido = new javax.swing.JMenuItem();
        mniCondicionIva = new javax.swing.JMenuItem();
        mniFormaDePago = new javax.swing.JMenuItem();
        mniConsultarFacturas = new javax.swing.JMenuItem();
        mniConsultarRemitos = new javax.swing.JMenuItem();
        mnuRRHH = new javax.swing.JMenu();
        mniRegistrarAsistencia = new javax.swing.JMenuItem();
        mniRegistrarDiaNoLaboral = new javax.swing.JMenuItem();
        registrarEmpleado = new javax.swing.JMenuItem();
        mniTipoDoc = new javax.swing.JMenuItem();
        mniConfiguracionJornada = new javax.swing.JMenuItem();
        mnuTrabajosTercerizados = new javax.swing.JMenu();
        mniEmpresaMetalurgica = new javax.swing.JMenuItem();
        mniCotizacionTrabajo = new javax.swing.JMenuItem();
        mniRegistrarIngresoCotizacionTrabajo = new javax.swing.JMenuItem();
        mniConfirmarCotizacionDeTrabajosTercerizados = new javax.swing.JMenuItem();
        mniCancelarPedidoDeCotizacion = new javax.swing.JMenuItem();
        mnuMantenimiento = new javax.swing.JMenu();
        mniRotura = new javax.swing.JMenuItem();
        mniServicio = new javax.swing.JMenuItem();
        mniEmpresaMantenimiento = new javax.swing.JMenuItem();
        mniTipoMaquina = new javax.swing.JMenuItem();
        mniMantenimientoPreventivo = new javax.swing.JMenuItem();
        mniRegistrarEnvioManPrev = new javax.swing.JMenuItem();
        mniConsultarEnviosManPrev = new javax.swing.JMenuItem();
        mniEnvioMantenimientoCorrectivo = new javax.swing.JMenuItem();
        mniConsultarEnviosMantenimientosCorrectivos = new javax.swing.JMenuItem();
        mnuAlmacenamiento = new javax.swing.JMenu();
        mniAsignarMPAProduccion = new javax.swing.JMenuItem();
        mniGenerarSolicitud = new javax.swing.JMenuItem();
        mniArmadoPedido = new javax.swing.JMenuItem();
        mnuReportes = new javax.swing.JMenu();
        mniReporteClientes = new javax.swing.JMenuItem();
        mniReportesReclamos = new javax.swing.JMenuItem();
        mniReportePedidos = new javax.swing.JMenuItem();
        mniReporteAusentismo = new javax.swing.JMenuItem();
        mniProveedores = new javax.swing.JMenuItem();
        mniMPrima = new javax.swing.JMenuItem();
        mniCobros = new javax.swing.JMenuItem();
        mniTrabajosTercerizados = new javax.swing.JMenuItem();
        mniEmpleados = new javax.swing.JMenuItem();
        mniMantenimientos = new javax.swing.JMenuItem();
        mniProducción = new javax.swing.JMenuItem();
        mniDefectos = new javax.swing.JMenuItem();
        mnuAyuda = new javax.swing.JMenu();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setForeground(new java.awt.Color(240, 240, 240));

        jScrollPane2.setAutoscrolls(true);

        pnlImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metalsoft/presentacion/img/FondoMetalsoft.jpg"))); // NOI18N

        jPanelTransparente1.setBackground(new java.awt.Color(153, 255, 153));
        jPanelTransparente1.setTran(0.25F);

        lblReloj.setFont(new java.awt.Font("Tahoma", 0, 14));
        lblReloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReloj.setText("...");
        lblReloj.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18));
        jLabel2.setForeground(new java.awt.Color(204, 204, 0));
        jLabel2.setText("Usuario:");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18));
        jLabel3.setForeground(new java.awt.Color(204, 204, 0));
        jLabel3.setText("Rol:");

        lblUsuario.setFont(new java.awt.Font("Calibri", 1, 18));
        lblUsuario.setForeground(new java.awt.Color(227, 233, 255));
        lblUsuario.setText("...");

        lblRol.setFont(new java.awt.Font("Calibri", 1, 18));
        lblRol.setForeground(new java.awt.Color(227, 233, 255));
        lblRol.setText("...");

        javax.swing.GroupLayout jPanelTransparente1Layout = new javax.swing.GroupLayout(jPanelTransparente1);
        jPanelTransparente1.setLayout(jPanelTransparente1Layout);
        jPanelTransparente1Layout.setHorizontalGroup(
            jPanelTransparente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTransparente1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel3)
                .addGap(10, 10, 10)
                .addComponent(lblRol, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblReloj, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanelTransparente1Layout.setVerticalGroup(
            jPanelTransparente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTransparente1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTransparente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblReloj, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(lblRol)
                    .addComponent(jLabel2)
                    .addComponent(lblUsuario))
                .addContainerGap())
        );

        pnlVentas.setBackground(new java.awt.Color(0, 255, 255));
        pnlVentas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ventas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(204, 255, 255))); // NOI18N
        pnlVentas.setPreferredSize(new java.awt.Dimension(450, 200));
        pnlVentas.setTran(0.1F);

        btnCobros.setBackground(new java.awt.Color(255, 255, 255));
        btnCobros.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cobros", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        btnCobros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCobros.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCobros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrosActionPerformed(evt);
            }
        });

        btnPedidos.setBackground(new java.awt.Color(255, 255, 255));
        btnPedidos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pedidos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        btnPedidos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPedidos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedidosActionPerformed(evt);
            }
        });

        btnNuevoCliente.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo Cliente"));
        btnNuevoCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevoCliente.setPreferredSize(new java.awt.Dimension(191, 181));
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlVentasLayout = new javax.swing.GroupLayout(pnlVentas);
        pnlVentas.setLayout(pnlVentasLayout);
        pnlVentasLayout.setHorizontalGroup(
            pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVentasLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnNuevoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnCobros, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlVentasLayout.setVerticalGroup(
            pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCobros, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
        );

        pnlProduccion.setBackground(new java.awt.Color(0, 255, 255));
        pnlProduccion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Producción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(204, 255, 255))); // NOI18N
        pnlProduccion.setPreferredSize(new java.awt.Dimension(450, 200));
        pnlProduccion.setTran(0.1F);

        btnEjecutarProduccion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ejecutar Producción", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        btnEjecutarProduccion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEjecutarProduccion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEjecutarProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarProduccionActionPerformed(evt);
            }
        });

        btnProduccionEnEjecucion.setBorder(javax.swing.BorderFactory.createTitledBorder("En Producción"));
        btnProduccionEnEjecucion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProduccionEnEjecucion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProduccionEnEjecucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProduccionEnEjecucionActionPerformed(evt);
            }
        });

        btnEtapasAtrasadas.setText("<html><font color=red size=+2></font></html>");
        btnEtapasAtrasadas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Etapas Atrasadas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        btnEtapasAtrasadas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEtapasAtrasadas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEtapasAtrasadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEtapasAtrasadasActionPerformed(evt);
            }
        });

        btnEtapasListasParaLanzar.setText("<html><font color=green size=+2></font></html>");
        btnEtapasListasParaLanzar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Etapas A Ejecutar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        btnEtapasListasParaLanzar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEtapasListasParaLanzar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEtapasListasParaLanzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEtapasListasParaLanzarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlProduccionLayout = new javax.swing.GroupLayout(pnlProduccion);
        pnlProduccion.setLayout(pnlProduccionLayout);
        pnlProduccionLayout.setHorizontalGroup(
            pnlProduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProduccionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEjecutarProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnProduccionEnEjecucion, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEtapasAtrasadas, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEtapasListasParaLanzar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlProduccionLayout.setVerticalGroup(
            pnlProduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProduccionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEjecutarProduccion, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(btnEtapasAtrasadas, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(btnEtapasListasParaLanzar, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(btnProduccionEnEjecucion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jButton1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cerrar Sesión", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 12))); // NOI18N
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        pnlCalidad.setBackground(new java.awt.Color(0, 255, 255));
        pnlCalidad.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Calidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(204, 255, 255))); // NOI18N
        pnlCalidad.setPreferredSize(new java.awt.Dimension(450, 200));
        pnlCalidad.setTran(0.1F);

        btnLanzarProcesoCalidad.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ejecutar Control", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        btnLanzarProcesoCalidad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLanzarProcesoCalidad.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLanzarProcesoCalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLanzarProcesoCalidadActionPerformed(evt);
            }
        });

        btnProcesosCalidadAtrasados.setText("<html><font color=red size=+2></font></html>");
        btnProcesosCalidadAtrasados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Procesos Atrasados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        btnProcesosCalidadAtrasados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProcesosCalidadAtrasados.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProcesosCalidadAtrasados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesosCalidadAtrasadosActionPerformed(evt);
            }
        });

        btnControlesCalidadEnEjecucion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "En calidad", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        btnControlesCalidadEnEjecucion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnControlesCalidadEnEjecucion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnControlesCalidadEnEjecucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnControlesCalidadEnEjecucionActionPerformed(evt);
            }
        });

        btnProcesosCalidadListosParaLanzar.setText("<html><font color=green size=+2></font></html>");
        btnProcesosCalidadListosParaLanzar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Procesos A Ejecutar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        btnProcesosCalidadListosParaLanzar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProcesosCalidadListosParaLanzar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProcesosCalidadListosParaLanzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesosCalidadListosParaLanzarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCalidadLayout = new javax.swing.GroupLayout(pnlCalidad);
        pnlCalidad.setLayout(pnlCalidadLayout);
        pnlCalidadLayout.setHorizontalGroup(
            pnlCalidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCalidadLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLanzarProcesoCalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnControlesCalidadEnEjecucion, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnProcesosCalidadAtrasados, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnProcesosCalidadListosParaLanzar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlCalidadLayout.setVerticalGroup(
            pnlCalidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCalidadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCalidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCalidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnProcesosCalidadListosParaLanzar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnProcesosCalidadAtrasados, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnControlesCalidadEnEjecucion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLanzarProcesoCalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        btnReportes.setBackground(new java.awt.Color(255, 255, 255));
        btnReportes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reportes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        btnReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReportes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReportes.setPreferredSize(new java.awt.Dimension(190, 180));
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });

        btnPresupuesto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Presupuesto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        btnPresupuesto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPresupuesto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPresupuesto.setPreferredSize(new java.awt.Dimension(191, 181));
        btnPresupuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPresupuestoActionPerformed(evt);
            }
        });

        pnlRegistrarFinalizacion.setBackground(new java.awt.Color(0, 255, 255));
        pnlRegistrarFinalizacion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registrar Finalización", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(204, 255, 255))); // NOI18N
        pnlRegistrarFinalizacion.setPreferredSize(new java.awt.Dimension(450, 200));
        pnlRegistrarFinalizacion.setTran(0.1F);

        jLabel4.setText("Código de Barras:");

        btnEnviarFinalizacion.setText("Enviar");
        btnEnviarFinalizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarFinalizacionActionPerformed(evt);
            }
        });

        txtComunicacion.setColumns(20);
        txtComunicacion.setEditable(false);
        txtComunicacion.setFont(new java.awt.Font("Monospaced", 1, 14));
        txtComunicacion.setLineWrap(true);
        txtComunicacion.setRows(5);
        txtComunicacion.setWrapStyleWord(true);
        txtComunicacion.setOpaque(false);
        jScrollPane1.setViewportView(txtComunicacion);

        javax.swing.GroupLayout pnlRegistrarFinalizacionLayout = new javax.swing.GroupLayout(pnlRegistrarFinalizacion);
        pnlRegistrarFinalizacion.setLayout(pnlRegistrarFinalizacionLayout);
        pnlRegistrarFinalizacionLayout.setHorizontalGroup(
            pnlRegistrarFinalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistrarFinalizacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRegistrarFinalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                    .addGroup(pnlRegistrarFinalizacionLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoBarras, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEnviarFinalizacion)))
                .addContainerGap())
        );
        pnlRegistrarFinalizacionLayout.setVerticalGroup(
            pnlRegistrarFinalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistrarFinalizacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRegistrarFinalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnviarFinalizacion))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRegistrarFinalizacion1.setBackground(new java.awt.Color(0, 255, 255));
        pnlRegistrarFinalizacion1.setPreferredSize(new java.awt.Dimension(450, 200));
        pnlRegistrarFinalizacion1.setTran(0.0F);
        pnlRegistrarFinalizacion1.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("OCR A Extended", 1, 36));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.animado2a.gif"))); // NOI18N
        pnlRegistrarFinalizacion1.add(jLabel1, java.awt.BorderLayout.LINE_END);

        javax.swing.GroupLayout pnlImagenLayout = new javax.swing.GroupLayout(pnlImagen);
        pnlImagen.setLayout(pnlImagenLayout);
        pnlImagenLayout.setHorizontalGroup(
            pnlImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlImagenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlImagenLayout.createSequentialGroup()
                        .addGroup(pnlImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlImagenLayout.createSequentialGroup()
                                .addComponent(pnlProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(pnlCalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlImagenLayout.createSequentialGroup()
                                .addComponent(pnlVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlRegistrarFinalizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addGroup(pnlImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnPresupuesto, 0, 0, Short.MAX_VALUE))
                            .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanelTransparente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1283, Short.MAX_VALUE)
                    .addComponent(pnlRegistrarFinalizacion1, javax.swing.GroupLayout.DEFAULT_SIZE, 1283, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 1303, Short.MAX_VALUE)
        );
        pnlImagenLayout.setVerticalGroup(
            pnlImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImagenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelTransparente1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlImagenLayout.createSequentialGroup()
                        .addGroup(pnlImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pnlCalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pnlVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlRegistrarFinalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlImagenLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlRegistrarFinalizacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(pnlImagen);

        mnuInicio.setText("Inicio");

        mniNuevoUsuario.setText("Nuevo Usuario");
        mniNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniNuevoUsuarioActionPerformed(evt);
            }
        });
        mnuInicio.add(mniNuevoUsuario);

        mniCerrarSesion.setText("Cerrar Sesión");
        mniCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCerrarSesionActionPerformed(evt);
            }
        });
        mnuInicio.add(mniCerrarSesion);

        mniCambiarContrasenia.setText("Cambiar Contraseña");
        mniCambiarContrasenia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCambiarContraseniaActionPerformed(evt);
            }
        });
        mnuInicio.add(mniCambiarContrasenia);

        mniAdministrarUsuarios.setText("Administrar Usuarios");
        mniAdministrarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniAdministrarUsuariosActionPerformed(evt);
            }
        });
        mnuInicio.add(mniAdministrarUsuarios);

        mbrMenu.add(mnuInicio);

        mnuCompras.setText("Compras");

        mniMateriaPrima.setText("Materia Prima");
        mniMateriaPrima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniMateriaPrimaActionPerformed(evt);
            }
        });
        mnuCompras.add(mniMateriaPrima);

        mniRegistrarPresupuesto.setText("Registrar Presupuesto");
        mniRegistrarPresupuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRegistrarPresupuestoActionPerformed(evt);
            }
        });
        mnuCompras.add(mniRegistrarPresupuesto);

        mniProveedor.setText("Administrar Proveedores");
        mniProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniProveedorActionPerformed(evt);
            }
        });
        mnuCompras.add(mniProveedor);

        jMenuItem3.setText("Asignar Materia Prima a Proveedor");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        mnuCompras.add(jMenuItem3);

        mniReclamoProveedor.setText("Reclamo a Proveedor");
        mniReclamoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniReclamoProveedorActionPerformed(evt);
            }
        });
        mnuCompras.add(mniReclamoProveedor);

        mniOrdenDeCompra.setText("Órden de Compra");
        mniOrdenDeCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniOrdenDeCompraActionPerformed(evt);
            }
        });
        mnuCompras.add(mniOrdenDeCompra);

        mbrMenu.add(mnuCompras);

        mnuVentas.setText("Ventas");
        mnuVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuVentasActionPerformed(evt);
            }
        });

        mniCliente.setText("Administrar Clientes");
        mniCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniClienteActionPerformed(evt);
            }
        });
        mnuVentas.add(mniCliente);

        mniRegistrarConfirmacionPedido.setText("Registrar Confirmación de Pedido");
        mniRegistrarConfirmacionPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRegistrarConfirmacionPedidoActionPerformed(evt);
            }
        });
        mnuVentas.add(mniRegistrarConfirmacionPedido);

        mniEntregaPedido.setText("Registrar Entrega de Pedido");
        mniEntregaPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniEntregaPedidoActionPerformed(evt);
            }
        });
        mnuVentas.add(mniEntregaPedido);

        mniPedidoCotizacion.setText("Pedido de Cotización");
        mniPedidoCotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniPedidoCotizacionActionPerformed(evt);
            }
        });
        mnuVentas.add(mniPedidoCotizacion);

        mbrMenu.add(mnuVentas);

        mnuProduccion.setText("Producción");

        mniGenerarDetalleEtapas.setText("Generar Detalle Etapas de Producción");
        mniGenerarDetalleEtapas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniGenerarDetalleEtapasActionPerformed(evt);
            }
        });
        mnuProduccion.add(mniGenerarDetalleEtapas);

        mniGenerarDetalleMateriaPrima.setText("Generar Detalle Materia Prima");
        mniGenerarDetalleMateriaPrima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniGenerarDetalleMateriaPrimaActionPerformed(evt);
            }
        });
        mnuProduccion.add(mniGenerarDetalleMateriaPrima);

        mniLanzarProduccion.setText("Ejecutar Producción");
        mniLanzarProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniLanzarProduccionActionPerformed(evt);
            }
        });
        mnuProduccion.add(mniLanzarProduccion);

        mniMatriz.setText("Matriz");
        mniMatriz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniMatrizActionPerformed(evt);
            }
        });
        mnuProduccion.add(mniMatriz);

        mniRegistrarPlanificacionProduccion.setText("Registrar Planificación de Producción");
        mniRegistrarPlanificacionProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRegistrarPlanificacionProduccionActionPerformed(evt);
            }
        });
        mnuProduccion.add(mniRegistrarPlanificacionProduccion);

        mniTipoMaterial.setText("Tipo Material");
        mniTipoMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTipoMaterialActionPerformed(evt);
            }
        });
        mnuProduccion.add(mniTipoMaterial);

        mniMaquinaria.setText("Maquinaria");
        mniMaquinaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniMaquinariaActionPerformed(evt);
            }
        });
        mnuProduccion.add(mniMaquinaria);

        mniPieza.setText("Pieza");
        mniPieza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniPiezaActionPerformed(evt);
            }
        });
        mnuProduccion.add(mniPieza);

        mniEtapaDeProduccion.setText("Etapa de Producción");
        mniEtapaDeProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniEtapaDeProduccionActionPerformed(evt);
            }
        });
        mnuProduccion.add(mniEtapaDeProduccion);

        mniProducto.setText("Producto");
        mniProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniProductoActionPerformed(evt);
            }
        });
        mnuProduccion.add(mniProducto);

        mniParadaMaquina.setText("Registrar Parada de Máquina");
        mniParadaMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniParadaMaquinaActionPerformed(evt);
            }
        });
        mnuProduccion.add(mniParadaMaquina);

        jMenuItem4.setText("Listado Materia Prima Necesaria");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        mnuProduccion.add(jMenuItem4);

        mbrMenu.add(mnuProduccion);

        mnuCalidad.setText("Control de Calidad");

        mniGenerarDetalleProcedimientosCalidad.setText("Generar Detalle Procedimientos Calidad");
        mniGenerarDetalleProcedimientosCalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniGenerarDetalleProcedimientosCalidadActionPerformed(evt);
            }
        });
        mnuCalidad.add(mniGenerarDetalleProcedimientosCalidad);

        mniRegistrarPlanificacionCalidad.setText("Registrar Planificación Calidad");
        mniRegistrarPlanificacionCalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRegistrarPlanificacionCalidadActionPerformed(evt);
            }
        });
        mnuCalidad.add(mniRegistrarPlanificacionCalidad);

        mniLanzarCalidad.setText("Ejecutar Control de Calidad");
        mniLanzarCalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniLanzarCalidadActionPerformed(evt);
            }
        });
        mnuCalidad.add(mniLanzarCalidad);

        mbrMenu.add(mnuCalidad);

        mnuFinanzas.setText("Finanzas");

        mniCobroPedido.setText("Registrar Cobro de Pedido");
        mniCobroPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCobroPedidoActionPerformed(evt);
            }
        });
        mnuFinanzas.add(mniCobroPedido);

        mniCondicionIva.setText("Condición Iva");
        mniCondicionIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCondicionIvaActionPerformed(evt);
            }
        });
        mnuFinanzas.add(mniCondicionIva);

        mniFormaDePago.setText("Forma de Pago");
        mniFormaDePago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniFormaDePagoActionPerformed(evt);
            }
        });
        mnuFinanzas.add(mniFormaDePago);

        mniConsultarFacturas.setText("Consultar Facturas");
        mniConsultarFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniConsultarFacturasActionPerformed(evt);
            }
        });
        mnuFinanzas.add(mniConsultarFacturas);

        mniConsultarRemitos.setText("Consultar Remitos");
        mniConsultarRemitos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniConsultarRemitosActionPerformed(evt);
            }
        });
        mnuFinanzas.add(mniConsultarRemitos);

        mbrMenu.add(mnuFinanzas);

        mnuRRHH.setText("RRHH");

        mniRegistrarAsistencia.setText("Registrar Asistencia Empleados");
        mniRegistrarAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRegistrarAsistenciaActionPerformed(evt);
            }
        });
        mnuRRHH.add(mniRegistrarAsistencia);

        mniRegistrarDiaNoLaboral.setText("Registrar Día no Laboral");
        mniRegistrarDiaNoLaboral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRegistrarDiaNoLaboralActionPerformed(evt);
            }
        });
        mnuRRHH.add(mniRegistrarDiaNoLaboral);

        registrarEmpleado.setText("Registrar Empleado");
        registrarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarEmpleadoActionPerformed(evt);
            }
        });
        mnuRRHH.add(registrarEmpleado);

        mniTipoDoc.setText("Tipo de Documento");
        mniTipoDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTipoDocActionPerformed(evt);
            }
        });
        mnuRRHH.add(mniTipoDoc);

        mniConfiguracionJornada.setText("Configuración Jornada Laboral");
        mniConfiguracionJornada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniConfiguracionJornadaActionPerformed(evt);
            }
        });
        mnuRRHH.add(mniConfiguracionJornada);

        mbrMenu.add(mnuRRHH);

        mnuTrabajosTercerizados.setText("Trabajos Tercerizados");
        mnuTrabajosTercerizados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTrabajosTercerizadosActionPerformed(evt);
            }
        });

        mniEmpresaMetalurgica.setText("Administrar Empresas Metalúrgicas");
        mniEmpresaMetalurgica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniEmpresaMetalurgicaActionPerformed(evt);
            }
        });
        mnuTrabajosTercerizados.add(mniEmpresaMetalurgica);

        mniCotizacionTrabajo.setText("Registrar Pedido de Cotización de Trabajo");
        mniCotizacionTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCotizacionTrabajoActionPerformed(evt);
            }
        });
        mnuTrabajosTercerizados.add(mniCotizacionTrabajo);

        mniRegistrarIngresoCotizacionTrabajo.setText("Registrar Envío/Llegada de Cotización de Trabajo");
        mniRegistrarIngresoCotizacionTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRegistrarIngresoCotizacionTrabajoActionPerformed(evt);
            }
        });
        mnuTrabajosTercerizados.add(mniRegistrarIngresoCotizacionTrabajo);

        mniConfirmarCotizacionDeTrabajosTercerizados.setText("Confirmar Trabajo");
        mniConfirmarCotizacionDeTrabajosTercerizados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniConfirmarCotizacionDeTrabajosTercerizadosActionPerformed(evt);
            }
        });
        mnuTrabajosTercerizados.add(mniConfirmarCotizacionDeTrabajosTercerizados);

        mniCancelarPedidoDeCotizacion.setText("Cancelar Pedido de Cotización de Trabajo");
        mniCancelarPedidoDeCotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCancelarPedidoDeCotizacionActionPerformed(evt);
            }
        });
        mnuTrabajosTercerizados.add(mniCancelarPedidoDeCotizacion);

        mbrMenu.add(mnuTrabajosTercerizados);

        mnuMantenimiento.setText("Mantenimientos");

        mniRotura.setText("Rotura");
        mniRotura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRoturaActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mniRotura);

        mniServicio.setText("Servicio de Máquina");
        mniServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniServicioActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mniServicio);

        mniEmpresaMantenimiento.setText("Administrar Empresas de Mantenimiento");
        mniEmpresaMantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniEmpresaMantenimientoActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mniEmpresaMantenimiento);

        mniTipoMaquina.setText("Tipo Máquina");
        mniTipoMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTipoMaquinaActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mniTipoMaquina);

        mniMantenimientoPreventivo.setText("Mantenimiento Preventivo");
        mniMantenimientoPreventivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniMantenimientoPreventivoActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mniMantenimientoPreventivo);

        mniRegistrarEnvioManPrev.setText("Registrar envío a Mantenimiento Preventivo");
        mniRegistrarEnvioManPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRegistrarEnvioManPrevActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mniRegistrarEnvioManPrev);

        mniConsultarEnviosManPrev.setText("Consultar envíos a Mantenimientos Preventivos");
        mniConsultarEnviosManPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniConsultarEnviosManPrevActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mniConsultarEnviosManPrev);

        mniEnvioMantenimientoCorrectivo.setText("Registrar envío a Mantenimiento Correctivo");
        mniEnvioMantenimientoCorrectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniEnvioMantenimientoCorrectivoActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mniEnvioMantenimientoCorrectivo);

        mniConsultarEnviosMantenimientosCorrectivos.setText("Consultar envíos a Mantenimientos Correctivos");
        mniConsultarEnviosMantenimientosCorrectivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniConsultarEnviosMantenimientosCorrectivosActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mniConsultarEnviosMantenimientosCorrectivos);

        mbrMenu.add(mnuMantenimiento);

        mnuAlmacenamiento.setText("Almacenamiento");

        mniAsignarMPAProduccion.setText("Asignar Materia Prima a Producción");
        mniAsignarMPAProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniAsignarMPAProduccionActionPerformed(evt);
            }
        });
        mnuAlmacenamiento.add(mniAsignarMPAProduccion);

        mniGenerarSolicitud.setText("Generar Solicitud de Reclamo");
        mniGenerarSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniGenerarSolicitudActionPerformed(evt);
            }
        });
        mnuAlmacenamiento.add(mniGenerarSolicitud);

        mniArmadoPedido.setText("Registrar Armado de Pedido");
        mniArmadoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniArmadoPedidoActionPerformed(evt);
            }
        });
        mnuAlmacenamiento.add(mniArmadoPedido);

        mbrMenu.add(mnuAlmacenamiento);

        mnuReportes.setText("Reportes");

        mniReporteClientes.setText("Clientes Empresa");
        mniReporteClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniReporteClientesActionPerformed(evt);
            }
        });
        mnuReportes.add(mniReporteClientes);

        mniReportesReclamos.setText("Reclamos");
        mniReportesReclamos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniReportesReclamosActionPerformed(evt);
            }
        });
        mnuReportes.add(mniReportesReclamos);

        mniReportePedidos.setText("Pedidos");
        mniReportePedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniReportePedidosActionPerformed(evt);
            }
        });
        mnuReportes.add(mniReportePedidos);

        mniReporteAusentismo.setText("Ausentismo");
        mniReporteAusentismo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniReporteAusentismoActionPerformed(evt);
            }
        });
        mnuReportes.add(mniReporteAusentismo);

        mniProveedores.setText("Proveedores");
        mniProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniProveedoresActionPerformed(evt);
            }
        });
        mnuReportes.add(mniProveedores);

        mniMPrima.setText("Materia Prima");
        mniMPrima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniMPrimaActionPerformed(evt);
            }
        });
        mnuReportes.add(mniMPrima);

        mniCobros.setText("Cobros");
        mniCobros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCobrosActionPerformed(evt);
            }
        });
        mnuReportes.add(mniCobros);

        mniTrabajosTercerizados.setText("Trabajos Tercerizados");
        mniTrabajosTercerizados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTrabajosTercerizadosActionPerformed(evt);
            }
        });
        mnuReportes.add(mniTrabajosTercerizados);

        mniEmpleados.setText("Empleados");
        mniEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniEmpleadosActionPerformed(evt);
            }
        });
        mnuReportes.add(mniEmpleados);

        mniMantenimientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniMantenimientosActionPerformed(evt);
            }
        });
        mnuReportes.add(mniMantenimientos);

        mniProducción.setText("Producción");
        mnuReportes.add(mniProducción);

        mniDefectos.setText("Defectos");
        mnuReportes.add(mniDefectos);

        mbrMenu.add(mnuReportes);

        mnuAyuda.setText("Ayuda");
        mnuAyuda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuAyudaMouseClicked(evt);
            }
        });
        mbrMenu.add(mnuAyuda);

        setJMenuBar(mbrMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1305, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mniClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniClienteActionPerformed
        try {
            JFrameManager.crearVentana(ABMCliente.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniClienteActionPerformed

    private void mniProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniProductoActionPerformed
        try {
            JFrameManager.crearVentana(ABMProducto.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniProductoActionPerformed

    private void mnuVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuVentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuVentasActionPerformed

    private void mniPedidoCotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniPedidoCotizacionActionPerformed
        try {
            JFrameManager.crearVentana(ABMPedidoPresupuesto.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniPedidoCotizacionActionPerformed

    private void mniPiezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniPiezaActionPerformed
        try {
            JFrameManager.crearVentana(ABMPieza.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniPiezaActionPerformed

    private void mniEtapaDeProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniEtapaDeProduccionActionPerformed
        try {
            JFrameManager.crearVentana(ABMEtapaDeProduccion.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniEtapaDeProduccionActionPerformed

    private void mniTipoMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTipoMaterialActionPerformed
        try {
            JFrameManager.crearVentana(ABMTipoMaterial.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniTipoMaterialActionPerformed

    private void mniMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniMatrizActionPerformed
        try {
            JFrameManager.crearVentana(ABMMatriz.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniMatrizActionPerformed

    private void mniGenerarDetalleEtapasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGenerarDetalleEtapasActionPerformed
        try {
            JFrameManager.crearVentana(GenerarDetalleEtapasProduccion.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniGenerarDetalleEtapasActionPerformed

    private void mniMateriaPrimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniMateriaPrimaActionPerformed
        try {
            JFrameManager.crearVentana(ABMMateriaPrima.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniMateriaPrimaActionPerformed

    private void mniGenerarDetalleProcedimientosCalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGenerarDetalleProcedimientosCalidadActionPerformed
        try {
            JFrameManager.crearVentana(GenerarDetalleProcesosCalidad.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniGenerarDetalleProcedimientosCalidadActionPerformed

    private void mniGenerarDetalleMateriaPrimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGenerarDetalleMateriaPrimaActionPerformed
        try {
            JFrameManager.crearVentana(GenerarDetalleMateriaPrima.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniGenerarDetalleMateriaPrimaActionPerformed

    private void mniProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniProveedorActionPerformed
        try {
            JFrameManager.crearVentana(ABMProveedor.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniProveedorActionPerformed

    private void mniRegistrarPresupuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRegistrarPresupuestoActionPerformed
        try {
            JFrameManager.crearVentana(RegistrarPresupuesto.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniRegistrarPresupuestoActionPerformed

    private void mniEmpresaMetalurgicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniEmpresaMetalurgicaActionPerformed
        try {
            JFrameManager.crearVentana(ABMEmpresaMetalurgica.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniEmpresaMetalurgicaActionPerformed

    private void mniRegistrarDiaNoLaboralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRegistrarDiaNoLaboralActionPerformed
        try {
            JFrameManager.crearVentana(RegistrarDiaNoLaboral.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniRegistrarDiaNoLaboralActionPerformed

    private void mniRegistrarConfirmacionPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRegistrarConfirmacionPedidoActionPerformed
        try {
            JFrameManager.crearVentana(RegistrarConfirmacionPedido.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniRegistrarConfirmacionPedidoActionPerformed

    private void registrarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarEmpleadoActionPerformed
        try {
            JFrameManager.crearVentana(ABMEmpleado.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_registrarEmpleadoActionPerformed

    private void mniRegistrarPlanificacionProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRegistrarPlanificacionProduccionActionPerformed
        try {
            JFrameManager.crearVentana(RegistrarPlanificacionProduccion.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniRegistrarPlanificacionProduccionActionPerformed

    private void mniLanzarProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniLanzarProduccionActionPerformed
        try {
            JFrameManager.crearVentana(RegistrarLanzamientoProduccion.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniLanzarProduccionActionPerformed

    private void mniRegistrarAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRegistrarAsistenciaActionPerformed
        try {
            JFrameManager.crearVentana(RegistrarAsistencia.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniRegistrarAsistenciaActionPerformed

    private void mniAsignarMPAProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniAsignarMPAProduccionActionPerformed
        try {
            JFrameManager.crearVentana(AsignarMateriaPrimaAProduccion.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniAsignarMPAProduccionActionPerformed

    private void mniMaquinariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniMaquinariaActionPerformed
        try {
            JFrameManager.crearVentana(ABMMaquina.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniMaquinariaActionPerformed

    private void mniEntregaPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniEntregaPedidoActionPerformed
        try {
            JFrameManager.crearVentana(RegistrarEntregaPedido.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniEntregaPedidoActionPerformed

    private void mniCobroPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCobroPedidoActionPerformed
        try {
            JFrameManager.crearVentana(RegistrarCobroPedido.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniCobroPedidoActionPerformed

    private void mniCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCerrarSesionActionPerformed
        //System.exit(0);
        MetalsoftDispatcher.detenerTodosLosHilos();
        AbrirSesion p = new AbrirSesion();
        p.setVisible(true);
        p.setLocationRelativeTo(null);

        dispose();
    }//GEN-LAST:event_mniCerrarSesionActionPerformed

    private void mniNuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniNuevoUsuarioActionPerformed
        try {
            JFrameManager.crearVentana(NuevoUsuario.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniNuevoUsuarioActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            JFrameManager.crearVentana(AsignarMateriaPrimaAProveedor.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void mniRoturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRoturaActionPerformed
        try {
            JFrameManager.crearVentana(ABMRotura.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_mniRoturaActionPerformed

    private void mniServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniServicioActionPerformed
        // TODO add your handling code here:
        try {
            JFrameManager.crearVentana(ABMServicioDeMaquina.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniServicioActionPerformed

    private void mniEmpresaMantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniEmpresaMantenimientoActionPerformed
        // TODO add your handling code here:
        try {
            JFrameManager.crearVentana(ABMEmpresaMantenimiento.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniEmpresaMantenimientoActionPerformed

    private void mniTipoMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTipoMaquinaActionPerformed
        // TODO add your handling code here:
        try {
            JFrameManager.crearVentana(ABMTipoMaquina.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniTipoMaquinaActionPerformed

    private void mniMantenimientoPreventivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniMantenimientoPreventivoActionPerformed
        // TODO add your handling code here:
        try {
            JFrameManager.crearVentana(ABMMantenimientoPreventivo.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniMantenimientoPreventivoActionPerformed

    private void mniCondicionIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCondicionIvaActionPerformed
        try {
            JFrameManager.crearVentana(ABMCondicionIva.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniCondicionIvaActionPerformed

    private void mniFormaDePagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniFormaDePagoActionPerformed
        try {
            JFrameManager.crearVentana(ABMFormaDePago.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniFormaDePagoActionPerformed

    private void mniCotizacionTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCotizacionTrabajoActionPerformed
        try {
            JFrameManager.crearVentana(RegistrarPedidoCotizacionDeTrabajo.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniCotizacionTrabajoActionPerformed

    private void mniRegistrarIngresoCotizacionTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRegistrarIngresoCotizacionTrabajoActionPerformed
        try {
            JFrameManager.crearVentana(RegistrarIngresoCotizacionTrabajo.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_mniRegistrarIngresoCotizacionTrabajoActionPerformed

    private void mniConfirmarCotizacionDeTrabajosTercerizadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniConfirmarCotizacionDeTrabajosTercerizadosActionPerformed
        try {
            JFrameManager.crearVentana(RegistrarConfirmacionTrabajoTercerizado.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_mniConfirmarCotizacionDeTrabajosTercerizadosActionPerformed

    private void mnuTrabajosTercerizadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTrabajosTercerizadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuTrabajosTercerizadosActionPerformed

    private void mniCancelarPedidoDeCotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCancelarPedidoDeCotizacionActionPerformed
        try {
            JFrameManager.crearVentana(RegistrarCancelacionDeTrabajosTercerizados.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniCancelarPedidoDeCotizacionActionPerformed

    private void mniCambiarContraseniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCambiarContraseniaActionPerformed
        CambiarContrasenia v = null;
        try {

            v = new CambiarContrasenia();
            GestorNuevoUsuario gestor = new GestorNuevoUsuario();
            v.setUsuario(gestor.buscarUsuario(usuario.getIdusuario()));
            JFrameManager.centrarYMostrarVentana(v);
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniCambiarContraseniaActionPerformed

    private void mniAdministrarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniAdministrarUsuariosActionPerformed

        try {
            JFrameManager.crearVentana(AdministrarUsuario.class.getName());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniAdministrarUsuariosActionPerformed

    private void mniConsultarFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniConsultarFacturasActionPerformed
        try {
            JFrameManager.crearVentana(ConsultarFacturas.class.getName());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniConsultarFacturasActionPerformed

    private void mniConsultarRemitosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniConsultarRemitosActionPerformed
        try {
            JFrameManager.crearVentana(ConsultarRemitos.class.getName());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniConsultarRemitosActionPerformed

    private void mniTipoDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTipoDocActionPerformed
        try {
            JFrameManager.crearVentana(ABMTipoDocumento.class.getName());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniTipoDocActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            JFrameManager.crearVentana(ConsultarListadoMateriaPrimaAComprar.class.getName());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

private void mniRegistrarPlanificacionCalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRegistrarPlanificacionCalidadActionPerformed
    try {
        JFrameManager.crearVentana(RegistrarPlanificacionCalidad.class.getName());
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mniRegistrarPlanificacionCalidadActionPerformed

private void mniLanzarCalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniLanzarCalidadActionPerformed
    try {
        JFrameManager.crearVentana(RegistrarLanzamientoCalidad.class.getName());
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mniLanzarCalidadActionPerformed

private void btnEjecutarProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarProduccionActionPerformed
    try {
        JFrameManager.crearVentana(RegistrarLanzamientoProduccion.class.getName());
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_btnEjecutarProduccionActionPerformed
private void mniReporteClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniReporteClientesActionPerformed

    try {
        JFrameManager.crearVentana(Reportes.class.getName());

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mniReporteClientesActionPerformed

private void mniReportePedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniReportePedidosActionPerformed

    try {
        JFrameManager.crearVentana(ReportesPedidos.class.getName());

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mniReportePedidosActionPerformed

private void mniReportesReclamosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniReportesReclamosActionPerformed

    try {
        JFrameManager.crearVentana(ReportesReclamos.class.getName());

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mniReportesReclamosActionPerformed

private void mniGenerarSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGenerarSolicitudActionPerformed
    try {
        JFrameManager.crearVentana(GenerarSolicitudReclamo.class.getName());

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mniGenerarSolicitudActionPerformed

private void mniReclamoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniReclamoProveedorActionPerformed
    try {
        JFrameManager.crearVentana(RegistrarReclamoProveedor.class.getName());

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mniReclamoProveedorActionPerformed

private void mniArmadoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniArmadoPedidoActionPerformed
    try {
        JFrameManager.crearVentana(RegistrarArmadoPedido.class.getName());

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mniArmadoPedidoActionPerformed

private void mniOrdenDeCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniOrdenDeCompraActionPerformed
    try {
        JFrameManager.crearVentana(ABMOrdenDeCompra.class.getName());

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mniOrdenDeCompraActionPerformed

private void mniReporteAusentismoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniReporteAusentismoActionPerformed
    try {
        JFrameManager.crearVentana(ReporteAusentismo.class.getName());

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mniReporteAusentismoActionPerformed

private void mniMPrimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniMPrimaActionPerformed
    try {
        JFrameManager.crearVentana(ReportesMateriasPrimas.class.getName());

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mniMPrimaActionPerformed

private void mniProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniProveedoresActionPerformed

    try {
        JFrameManager.crearVentana(ReportesProveedores.class.getName());

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mniProveedoresActionPerformed

private void mniCobrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCobrosActionPerformed
    try {
        JFrameManager.crearVentana(ReportesCobros.class.getName());

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }

}//GEN-LAST:event_mniCobrosActionPerformed

private void mniTrabajosTercerizadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTrabajosTercerizadosActionPerformed

    try {
        JFrameManager.crearVentana(ReportesTrabajosTercerizados.class.getName());

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mniTrabajosTercerizadosActionPerformed

private void mniEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniEmpleadosActionPerformed

    try {
        JFrameManager.crearVentana(ReportesEmpleados.class.getName());

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mniEmpleadosActionPerformed

private void mnuAyudaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuAyudaMouseClicked
    String path = "C:\\metalsoft\\Ayuda\\metalsoft_tmphhp\\Welcome.html";
    Main.ejecutarAyuda(path);
}//GEN-LAST:event_mnuAyudaMouseClicked

private void mniMantenimientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniMantenimientosActionPerformed

    try {
        JFrameManager.crearVentana(ReportesMaquinas.class.getName());

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mniMantenimientosActionPerformed

private void btnProcesosCalidadAtrasadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesosCalidadAtrasadosActionPerformed
    ProcesosCalidadAtrasados pca = new ProcesosCalidadAtrasados();
    pca.setProcesosAtrasados(mapProcesosCalidadAtrasados);
    JFrameManager.centrarYMostrarVentana(pca);
}//GEN-LAST:event_btnProcesosCalidadAtrasadosActionPerformed

private void btnPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedidosActionPerformed
    try {
        JFrameManager.crearVentana(ABMPedidoPresupuesto.class.getName());
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_btnPedidosActionPerformed

private void btnCobrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrosActionPerformed
    try {
        JFrameManager.crearVentana(RegistrarCobroPedido.class.getName());
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_btnCobrosActionPerformed

private void btnEtapasAtrasadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEtapasAtrasadasActionPerformed
    EtapasProduccionAtrasadas epa = new EtapasProduccionAtrasadas();
    epa.setEtapasAtrasadas(mapEtapasAtrasadas);
    JFrameManager.centrarYMostrarVentana(epa);

}//GEN-LAST:event_btnEtapasAtrasadasActionPerformed

private void btnProduccionEnEjecucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProduccionEnEjecucionActionPerformed
    try {
        JFrameManager.crearVentana(PlanificacionProduccionEnEjecucion.class.getName());
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_btnProduccionEnEjecucionActionPerformed

private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
    try {
        JFrameManager.crearVentana(BotonRapidoReportes.class.getName());
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_btnReportesActionPerformed

private void btnEtapasListasParaLanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEtapasListasParaLanzarActionPerformed
    EtapasProduccionListasParaLanzar epll = new EtapasProduccionListasParaLanzar();
    epll.setEtapasALanzar(mapEtapasListasParaLanzar);
    EtapasProduccionListasParaLanzar.setVtnPrincipal(vtnPrincipal);
    JFrameManager.centrarYMostrarVentana(epll);

}//GEN-LAST:event_btnEtapasListasParaLanzarActionPerformed

private void btnProcesosCalidadListosParaLanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesosCalidadListosParaLanzarActionPerformed
    ProcesosCalidadListosParaLanzar pcll = new ProcesosCalidadListosParaLanzar();
    pcll.setProcesosALanzar(mapProcesosListosParaLanzar);
    ProcesosCalidadListosParaLanzar.setVtnPrincipal(vtnPrincipal);
    JFrameManager.centrarYMostrarVentana(pcll);
}//GEN-LAST:event_btnProcesosCalidadListosParaLanzarActionPerformed

private void btnPresupuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPresupuestoActionPerformed

    try {
        JFrameManager.crearVentana(BotonRapidoPresupuesto.class.getName());
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_btnPresupuestoActionPerformed

private void mniRegistrarEnvioManPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRegistrarEnvioManPrevActionPerformed

    try {
        JFrameManager.crearVentana(RegistrarEnvioMantenimientoPreventivo.class.getName());
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mniRegistrarEnvioManPrevActionPerformed

private void mniConsultarEnviosManPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniConsultarEnviosManPrevActionPerformed
    try {
        JFrameManager.crearVentana(ConsultarEnvioMantenimientoPreventivo.class.getName());
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mniConsultarEnviosManPrevActionPerformed

private void btnEnviarFinalizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarFinalizacionActionPerformed
    Socket cliente = null;
    ObjectInputStream ois = null;
    ObjectOutputStream oos = null;
    try {
        System.out.println("INFO: Conectando con el servidor...");
        txtComunicacion.append(System.getProperty("line.separator"));
        txtComunicacion.append("#########  " + Fecha.parseToStringFechaHora(Fecha.fechaActualDate()) + "  #########");
        txtComunicacion.append("\nINFO: Conectando con el servidor...");

        String codigoBarra = txtCodigoBarras.getText();

        String parts[] = codigoBarra.split(Pattern.quote("-"));

        String ip = null;
        String puerto = null;

        if (parts[1].equals(String.valueOf(BarCodeUtil.COD_EJECUCION_PROCESO_CALIDAD))) {
            ip = MetalsoftProperties.getProperty(MetalsoftProperties.IP_FIN_PROCESO_CALIDAD);
            puerto = MetalsoftProperties.getProperty(MetalsoftProperties.PUERTO_FIN_PROCESO_CALIDAD);
        } else if (parts[1].equals(String.valueOf(BarCodeUtil.COD_EJECUCION_ETAPA_PRODUCCION))) {
            ip = MetalsoftProperties.getProperty(MetalsoftProperties.IP_FIN_ETAPA);
            puerto = MetalsoftProperties.getProperty(MetalsoftProperties.PUERTO_FIN_ETAPA);
        }

        cliente = new Socket(ip, Integer.parseInt(puerto));

        oos = new ObjectOutputStream(cliente.getOutputStream());
        ois = new ObjectInputStream(cliente.getInputStream());

        System.out.println("\nINFO: Enviando datos");
        txtComunicacion.append("\nINFO: Enviando datos");

        oos.writeObject(txtCodigoBarras.getText());

        System.out.println("\nINFO: Recibiendo respuesta");
        txtComunicacion.append("\nINFO: Recibiendo respuesta");

        String respuesta = (String) ois.readObject();
        txtComunicacion.append("\nINFO: Respuesta --> " + respuesta);

        System.out.println("\nINFO: Fin envio de datos");
        txtComunicacion.append("\nINFO: Fin envio de datos");

    } catch (UnknownHostException ex) {
        ex.printStackTrace();
    } catch (IOException ex) {
        ex.printStackTrace();
    } catch (ClassNotFoundException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (ois != null) {
                ois.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            if (oos != null) {
                oos.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            if (cliente != null) {
                cliente.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}//GEN-LAST:event_btnEnviarFinalizacionActionPerformed

private void mniConfiguracionJornadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniConfiguracionJornadaActionPerformed
    try {
        JFrameManager.crearVentana(ConfiguracionJornada.class.getName());
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mniConfiguracionJornadaActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    AbrirSesion p = new AbrirSesion();
    p.setVisible(true);
    p.setLocationRelativeTo(null);
    dispose();
}//GEN-LAST:event_jButton1ActionPerformed

private void btnNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClienteActionPerformed
    try {
        JFrameManager.crearVentana(ABMCliente.class.getName());
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_btnNuevoClienteActionPerformed

private void btnLanzarProcesoCalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanzarProcesoCalidadActionPerformed
    try {
        JFrameManager.crearVentana(RegistrarLanzamientoCalidad.class.getName());
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_btnLanzarProcesoCalidadActionPerformed

private void mniParadaMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniParadaMaquinaActionPerformed
    try {
        JFrameManager.crearVentana(RegistrarParadaDeMaquina.class.getName());
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mniParadaMaquinaActionPerformed

private void mniEnvioMantenimientoCorrectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniEnvioMantenimientoCorrectivoActionPerformed
    try {
        JFrameManager.crearVentana(RegistrarEnvioMantenimientoCorrectivo.class.getName());
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mniEnvioMantenimientoCorrectivoActionPerformed

private void mniConsultarEnviosMantenimientosCorrectivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniConsultarEnviosMantenimientosCorrectivosActionPerformed
    try {
        JFrameManager.crearVentana(ConsultarEnvioMantenimientoCorrectivo.class.getName());
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mniConsultarEnviosMantenimientosCorrectivosActionPerformed
private void btnControlesCalidadEnEjecucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnControlesCalidadEnEjecucionActionPerformed
    try {
        JFrameManager.crearVentana(PlanificacionCalidadEnEjecucion.class.getName());
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_btnControlesCalidadEnEjecucionActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCobros;
    private javax.swing.JButton btnControlesCalidadEnEjecucion;
    private javax.swing.JButton btnEjecutarProduccion;
    private javax.swing.JButton btnEnviarFinalizacion;
    private javax.swing.JButton btnEtapasAtrasadas;
    private javax.swing.JButton btnEtapasListasParaLanzar;
    private javax.swing.JButton btnLanzarProcesoCalidad;
    private javax.swing.JButton btnNuevoCliente;
    private javax.swing.JButton btnPedidos;
    private javax.swing.JButton btnPresupuesto;
    private javax.swing.JButton btnProcesosCalidadAtrasados;
    private javax.swing.JButton btnProcesosCalidadListosParaLanzar;
    private javax.swing.JButton btnProduccionEnEjecucion;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private metalsoft.beans.JPanelTransparente jPanelTransparente1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblReloj;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JMenuBar mbrMenu;
    private javax.swing.JMenuItem mniAdministrarUsuarios;
    private javax.swing.JMenuItem mniArmadoPedido;
    private javax.swing.JMenuItem mniAsignarMPAProduccion;
    private javax.swing.JMenuItem mniCambiarContrasenia;
    private javax.swing.JMenuItem mniCancelarPedidoDeCotizacion;
    private javax.swing.JMenuItem mniCerrarSesion;
    private javax.swing.JMenuItem mniCliente;
    private javax.swing.JMenuItem mniCobroPedido;
    private javax.swing.JMenuItem mniCobros;
    private javax.swing.JMenuItem mniCondicionIva;
    private javax.swing.JMenuItem mniConfiguracionJornada;
    private javax.swing.JMenuItem mniConfirmarCotizacionDeTrabajosTercerizados;
    private javax.swing.JMenuItem mniConsultarEnviosManPrev;
    private javax.swing.JMenuItem mniConsultarEnviosMantenimientosCorrectivos;
    private javax.swing.JMenuItem mniConsultarFacturas;
    private javax.swing.JMenuItem mniConsultarRemitos;
    private javax.swing.JMenuItem mniCotizacionTrabajo;
    private javax.swing.JMenuItem mniDefectos;
    private javax.swing.JMenuItem mniEmpleados;
    private javax.swing.JMenuItem mniEmpresaMantenimiento;
    private javax.swing.JMenuItem mniEmpresaMetalurgica;
    private javax.swing.JMenuItem mniEntregaPedido;
    private javax.swing.JMenuItem mniEnvioMantenimientoCorrectivo;
    private javax.swing.JMenuItem mniEtapaDeProduccion;
    private javax.swing.JMenuItem mniFormaDePago;
    private javax.swing.JMenuItem mniGenerarDetalleEtapas;
    private javax.swing.JMenuItem mniGenerarDetalleMateriaPrima;
    private javax.swing.JMenuItem mniGenerarDetalleProcedimientosCalidad;
    private javax.swing.JMenuItem mniGenerarSolicitud;
    private javax.swing.JMenuItem mniLanzarCalidad;
    private javax.swing.JMenuItem mniLanzarProduccion;
    private javax.swing.JMenuItem mniMPrima;
    private javax.swing.JMenuItem mniMantenimientoPreventivo;
    private javax.swing.JMenuItem mniMantenimientos;
    private javax.swing.JMenuItem mniMaquinaria;
    private javax.swing.JMenuItem mniMateriaPrima;
    private javax.swing.JMenuItem mniMatriz;
    private javax.swing.JMenuItem mniNuevoUsuario;
    private javax.swing.JMenuItem mniOrdenDeCompra;
    private javax.swing.JMenuItem mniParadaMaquina;
    private javax.swing.JMenuItem mniPedidoCotizacion;
    private javax.swing.JMenuItem mniPieza;
    private javax.swing.JMenuItem mniProducción;
    private javax.swing.JMenuItem mniProducto;
    private javax.swing.JMenuItem mniProveedor;
    private javax.swing.JMenuItem mniProveedores;
    private javax.swing.JMenuItem mniReclamoProveedor;
    private javax.swing.JMenuItem mniRegistrarAsistencia;
    private javax.swing.JMenuItem mniRegistrarConfirmacionPedido;
    private javax.swing.JMenuItem mniRegistrarDiaNoLaboral;
    private javax.swing.JMenuItem mniRegistrarEnvioManPrev;
    private javax.swing.JMenuItem mniRegistrarIngresoCotizacionTrabajo;
    private javax.swing.JMenuItem mniRegistrarPlanificacionCalidad;
    private javax.swing.JMenuItem mniRegistrarPlanificacionProduccion;
    private javax.swing.JMenuItem mniRegistrarPresupuesto;
    private javax.swing.JMenuItem mniReporteAusentismo;
    private javax.swing.JMenuItem mniReporteClientes;
    private javax.swing.JMenuItem mniReportePedidos;
    private javax.swing.JMenuItem mniReportesReclamos;
    private javax.swing.JMenuItem mniRotura;
    private javax.swing.JMenuItem mniServicio;
    private javax.swing.JMenuItem mniTipoDoc;
    private javax.swing.JMenuItem mniTipoMaquina;
    private javax.swing.JMenuItem mniTipoMaterial;
    private javax.swing.JMenuItem mniTrabajosTercerizados;
    private javax.swing.JMenu mnuAlmacenamiento;
    private javax.swing.JMenu mnuAyuda;
    private javax.swing.JMenu mnuCalidad;
    private javax.swing.JMenu mnuCompras;
    private javax.swing.JMenu mnuFinanzas;
    private javax.swing.JMenu mnuInicio;
    private javax.swing.JMenu mnuMantenimiento;
    private javax.swing.JMenu mnuProduccion;
    private javax.swing.JMenu mnuRRHH;
    private javax.swing.JMenu mnuReportes;
    private javax.swing.JMenu mnuTrabajosTercerizados;
    private javax.swing.JMenu mnuVentas;
    private metalsoft.beans.JPanelTransparente pnlCalidad;
    private metalsoft.beans.JPanelBackground pnlImagen;
    private metalsoft.beans.JPanelTransparente pnlProduccion;
    private metalsoft.beans.JPanelTransparente pnlRegistrarFinalizacion;
    private metalsoft.beans.JPanelTransparente pnlRegistrarFinalizacion1;
    private metalsoft.beans.JPanelTransparente pnlVentas;
    private javax.swing.JMenuItem registrarEmpleado;
    private javax.swing.JTextField txtCodigoBarras;
    private javax.swing.JTextArea txtComunicacion;
    // End of variables declaration//GEN-END:variables

    public void obtenerRolUsuario(long idUsuario) {
        roles = Usuario.obtenerRoles(idUsuario);
        setTitle("METALSOFT - INICIO [Rol: " + roles[0].getRol() + "]");
    }

    public void alertaEtapaNoFinalizada(Ejecucionplanificacionproduccion ejecucionplanificacionproduccion, Detalleejecucionplanificacion detalleejecucionplanificacion) {

        String inicioHtml = "<html><font color=red size=+2>";
        String finHtml = "</font></html>";
        int lengthInicio = (inicioHtml + finHtml).length();

        if (!mapEtapasAtrasadas.containsKey(detalleejecucionplanificacion.getId())) {

            mapEtapasAtrasadas.put(detalleejecucionplanificacion.getId(), detalleejecucionplanificacion);

            String txtBoton = btnEtapasAtrasadas.getText();

            if (lengthInicio == txtBoton.length()) {
                btnEtapasAtrasadas.setText(inicioHtml + "(1)" + finHtml);
            } else {
                String num = txtBoton.substring(inicioHtml.length() + 1, txtBoton.length() - finHtml.length() - 1);
                int numero = Integer.parseInt(num);
                btnEtapasAtrasadas.setText(inicioHtml + "(" + (numero + 1) + ")" + finHtml);
            }

        }
    }

    public void eliminarEtapaNoFinalizada(Long id) {
        if (mapEtapasAtrasadas.containsKey(id)) {
            mapEtapasAtrasadas.remove(id);
            restarEtapaAtrasada();
        }
    }

    public void eliminarProcesoCalidadNoFinalizado(Long id) {
        if (mapProcesosCalidadAtrasados.containsKey(id)) {
            mapProcesosCalidadAtrasados.remove(id);
            restarProcesoCalidadAtrasado();
        }
    }

    public void eliminarEtapaALanzar(Long id) {
        if (mapEtapasListasParaLanzar.containsKey(id)) {
            mapEtapasListasParaLanzar.remove(id);
            restarEtapaListaParaLanzar();
        }
    }

    public void eliminarProcesoALanzar(Long id) {
        if (mapProcesosListosParaLanzar.containsKey(id)) {
            mapProcesosListosParaLanzar.remove(id);
            restarProcesoListoParaLanzar();
        }
    }

    private void restarProcesoListoParaLanzar() {

        String inicioHtml = "<html><font color=green size=+2>";
        String finHtml = "</font></html>";
        int lengthInicio = (inicioHtml + finHtml).length();

        String txtBoton = btnProcesosCalidadListosParaLanzar.getText();

        if (lengthInicio != txtBoton.length()) {

            String num = txtBoton.substring(inicioHtml.length() + 1, txtBoton.length() - finHtml.length() - 1);

            int numero = Integer.parseInt(num);

            if ((numero - 1) == 0) {
                btnProcesosCalidadListosParaLanzar.setText(inicioHtml + finHtml);
            } else {
                btnProcesosCalidadListosParaLanzar.setText(inicioHtml + "(" + (numero - 1) + ")" + finHtml);
            }

        }
    }

    private void restarEtapaListaParaLanzar() {

        String inicioHtml = "<html><font color=green size=+2>";
        String finHtml = "</font></html>";
        int lengthInicio = (inicioHtml + finHtml).length();

        String txtBoton = btnEtapasListasParaLanzar.getText();

        if (lengthInicio != txtBoton.length()) {

            String num = txtBoton.substring(inicioHtml.length() + 1, txtBoton.length() - finHtml.length() - 1);

            int numero = Integer.parseInt(num);

            if ((numero - 1) == 0) {
                btnEtapasListasParaLanzar.setText(inicioHtml + finHtml);
            } else {
                btnEtapasListasParaLanzar.setText(inicioHtml + "(" + (numero - 1) + ")" + finHtml);
            }

        }
    }

    private void restarProcesoCalidadAtrasado() {
        String inicioHtml = "<html><font color=red size=+2>";
        String finHtml = "</font></html>";
        int lengthInicio = (inicioHtml + finHtml).length();

        String txtBoton = btnProcesosCalidadAtrasados.getText();

        if (lengthInicio != txtBoton.length()) {
            String num = txtBoton.substring(inicioHtml.length() + 1, txtBoton.length() - finHtml.length() - 1);
            int numero = Integer.parseInt(num);
            if ((numero - 1) == 0) {
                btnProcesosCalidadAtrasados.setText(inicioHtml + finHtml);
            } else {
                btnProcesosCalidadAtrasados.setText(inicioHtml + "(" + (numero - 1) + ")" + finHtml);
            }

        }

    }

    private void restarEtapaAtrasada() {
        String inicioHtml = "<html><font color=red size=+2>";
        String finHtml = "</font></html>";
        int lengthInicio = (inicioHtml + finHtml).length();

        String txtBoton = btnEtapasAtrasadas.getText();

        if (lengthInicio != txtBoton.length()) {
            String num = txtBoton.substring(inicioHtml.length() + 1, txtBoton.length() - finHtml.length() - 1);
            int numero = Integer.parseInt(num);
            if ((numero - 1) == 0) {
                btnEtapasAtrasadas.setText(inicioHtml + finHtml);
            } else {
                btnEtapasAtrasadas.setText(inicioHtml + "(" + (numero - 1) + ")" + finHtml);
            }


        }

    }

    public void alertaProcesoCalidadNoFinalizado(Ejecucionplanificacioncalidad ejecucionplanificacioncalidad, Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad) {

        String inicioHtml = "<html><font color=red size=+2>";
        String finHtml = "</font></html>";
        int lengthInicio = (inicioHtml + finHtml).length();

        if (!mapProcesosCalidadAtrasados.containsKey(detalleejecucionplanificacioncalidad.getIddetalle())) {

            mapProcesosCalidadAtrasados.put(detalleejecucionplanificacioncalidad.getIddetalle(), detalleejecucionplanificacioncalidad);

            String txtBoton = btnProcesosCalidadAtrasados.getText();

            if (lengthInicio == txtBoton.length()) {
                btnProcesosCalidadAtrasados.setText(inicioHtml + "(1)" + finHtml);
            } else {
                String num = txtBoton.substring(inicioHtml.length() + 1, txtBoton.length() - finHtml.length() - 1);
                int numero = Integer.parseInt(num);
                btnProcesosCalidadAtrasados.setText(inicioHtml + "(" + (numero + 1) + ")" + finHtml);
            }

        }
    }

    public void alertaEtapaListaParaLanzar(Detalleplanificacionproduccion detalleplanificacionproduccion) {
        String inicioHtml = "<html><font color=green size=+2>";
        String finHtml = "</font></html>";
        int lengthInicio = (inicioHtml + finHtml).length();

        if (!mapEtapasListasParaLanzar.containsKey(detalleplanificacionproduccion.getId())) {

            mapEtapasListasParaLanzar.put(detalleplanificacionproduccion.getId(), detalleplanificacionproduccion);

            String txtBoton = btnEtapasListasParaLanzar.getText();

            if (lengthInicio == txtBoton.length()) {
                btnEtapasListasParaLanzar.setText(inicioHtml + "(1)" + finHtml);
            } else {
                String num = txtBoton.substring(inicioHtml.length() + 1, txtBoton.length() - finHtml.length() - 1);
                int numero = Integer.parseInt(num);
                btnEtapasListasParaLanzar.setText(inicioHtml + "(" + (numero + 1) + ")" + finHtml);
            }
        }
    }

    public void alertaProcesoListoParaLanzar(Detalleplanificacioncalidad detalleplanificacioncalidad) {
        String inicioHtml = "<html><font color=green size=+2>";
        String finHtml = "</font></html>";
        int lengthInicio = (inicioHtml + finHtml).length();

        if (!mapProcesosListosParaLanzar.containsKey(detalleplanificacioncalidad.getIddetalle())) {

            mapProcesosListosParaLanzar.put(detalleplanificacioncalidad.getIddetalle(), detalleplanificacioncalidad);

            String txtBoton = btnProcesosCalidadListosParaLanzar.getText();

            if (lengthInicio == txtBoton.length()) {
                btnProcesosCalidadListosParaLanzar.setText(inicioHtml + "(1)" + finHtml);
            } else {
                String num = txtBoton.substring(inicioHtml.length() + 1, txtBoton.length() - finHtml.length() - 1);
                int numero = Integer.parseInt(num);
                btnProcesosCalidadListosParaLanzar.setText(inicioHtml + "(" + (numero + 1) + ")" + finHtml);
            }
        }
    }

    /*
     * ################################ GETERS AND SETERS ################################
     */
    public JButton getBtnCobros() {
        return btnCobros;
    }

    public void setBtnCobros(JButton btnCobros) {
        this.btnCobros = btnCobros;
    }

    public JButton getBtnControlesCalidadEnEjecucion() {
        return btnControlesCalidadEnEjecucion;
    }

    public void setBtnControlesCalidadEnEjecucion(JButton btnControlesCalidadEnEjecucion) {
        this.btnControlesCalidadEnEjecucion = btnControlesCalidadEnEjecucion;
    }

    public JButton getBtnEjecutarProduccion() {
        return btnEjecutarProduccion;
    }

    public void setBtnEjecutarProduccion(JButton btnEjecutarProduccion) {
        this.btnEjecutarProduccion = btnEjecutarProduccion;
    }

    public JButton getBtnEtapasAtrasadas() {
        return btnEtapasAtrasadas;
    }

    public void setBtnEtapasAtrasadas(JButton btnEtapasAtrasadas) {
        this.btnEtapasAtrasadas = btnEtapasAtrasadas;
    }

    public JButton getBtnEtapasListasParaLanzar() {
        return btnEtapasListasParaLanzar;
    }

    public void setBtnEtapasListasParaLanzar(JButton btnEtapasListasParaLanzar) {
        this.btnEtapasListasParaLanzar = btnEtapasListasParaLanzar;
    }

    public JButton getBtnLanzarProcesoCalidad() {
        return btnLanzarProcesoCalidad;
    }

    public void setBtnLanzarProcesoCalidad(JButton btnLanzarProcesoCalidad) {
        this.btnLanzarProcesoCalidad = btnLanzarProcesoCalidad;
    }

    public JButton getBtnNuevoCliente() {
        return btnNuevoCliente;
    }

    public void setBtnNuevoCliente(JButton btnNuevoCliente) {
        this.btnNuevoCliente = btnNuevoCliente;
    }

    public JButton getBtnPedidos() {
        return btnPedidos;
    }

    public void setBtnPedidos(JButton btnPedidos) {
        this.btnPedidos = btnPedidos;
    }

    public JButton getBtnPresupuesto() {
        return btnPresupuesto;
    }

    public void setBtnPresupuesto(JButton btnPresupuesto) {
        this.btnPresupuesto = btnPresupuesto;
    }

    public JButton getBtnProcesosCalidadAtrasados() {
        return btnProcesosCalidadAtrasados;
    }

    public void setBtnProcesosCalidadAtrasados(JButton btnProcesosCalidadAtrasados) {
        this.btnProcesosCalidadAtrasados = btnProcesosCalidadAtrasados;
    }

    public JButton getBtnProcesosCalidadListosParaLanzar() {
        return btnProcesosCalidadListosParaLanzar;
    }

    public void setBtnProcesosCalidadListosParaLanzar(JButton btnProcesosCalidadListosParaLanzar) {
        this.btnProcesosCalidadListosParaLanzar = btnProcesosCalidadListosParaLanzar;
    }

    public JButton getBtnProduccionEnEjecucion() {
        return btnProduccionEnEjecucion;
    }

    public void setBtnProduccionEnEjecucion(JButton btnProduccionEnEjecucion) {
        this.btnProduccionEnEjecucion = btnProduccionEnEjecucion;
    }

    public JButton getBtnReportes() {
        return btnReportes;
    }

    public void setBtnReportes(JButton btnReportes) {
        this.btnReportes = btnReportes;
    }

    public JMenuItem getMniAdministrarUsuarios() {
        return mniAdministrarUsuarios;
    }

    public void setMniAdministrarUsuarios(JMenuItem mniAdministrarUsuarios) {
        this.mniAdministrarUsuarios = mniAdministrarUsuarios;
    }

    public JMenuItem getMniArmadoPedido() {
        return mniArmadoPedido;
    }

    public void setMniArmadoPedido(JMenuItem mniArmadoPedido) {
        this.mniArmadoPedido = mniArmadoPedido;
    }

    public JMenuItem getMniAsignarMPAProduccion() {
        return mniAsignarMPAProduccion;
    }

    public void setMniAsignarMPAProduccion(JMenuItem mniAsignarMPAProduccion) {
        this.mniAsignarMPAProduccion = mniAsignarMPAProduccion;
    }

    public JMenuItem getMniCambiarContrasenia() {
        return mniCambiarContrasenia;
    }

    public void setMniCambiarContrasenia(JMenuItem mniCambiarContrasenia) {
        this.mniCambiarContrasenia = mniCambiarContrasenia;
    }

    public JMenuItem getMniCancelarPedidoDeCotizacion() {
        return mniCancelarPedidoDeCotizacion;
    }

    public void setMniCancelarPedidoDeCotizacion(JMenuItem mniCancelarPedidoDeCotizacion) {
        this.mniCancelarPedidoDeCotizacion = mniCancelarPedidoDeCotizacion;
    }

    public JMenuItem getMniCerrarSesion() {
        return mniCerrarSesion;
    }

    public void setMniCerrarSesion(JMenuItem mniCerrarSesion) {
        this.mniCerrarSesion = mniCerrarSesion;
    }

    public JMenuItem getMniCliente() {
        return mniCliente;
    }

    public void setMniCliente(JMenuItem mniCliente) {
        this.mniCliente = mniCliente;
    }

    public JMenuItem getMniCobroPedido() {
        return mniCobroPedido;
    }

    public void setMniCobroPedido(JMenuItem mniCobroPedido) {
        this.mniCobroPedido = mniCobroPedido;
    }

    public JMenuItem getMniCobros() {
        return mniCobros;
    }

    public void setMniCobros(JMenuItem mniCobros) {
        this.mniCobros = mniCobros;
    }

    public JMenuItem getMniCondicionIva() {
        return mniCondicionIva;
    }

    public void setMniCondicionIva(JMenuItem mniCondicionIva) {
        this.mniCondicionIva = mniCondicionIva;
    }

    public JMenuItem getMniConfirmarCotizacionDeTrabajosTercerizados() {
        return mniConfirmarCotizacionDeTrabajosTercerizados;
    }

    public void setMniConfirmarCotizacionDeTrabajosTercerizados(JMenuItem mniConfirmarCotizacionDeTrabajosTercerizados) {
        this.mniConfirmarCotizacionDeTrabajosTercerizados = mniConfirmarCotizacionDeTrabajosTercerizados;
    }

    public JMenuItem getMniConsultarEnviosManPrev() {
        return mniConsultarEnviosManPrev;
    }

    public void setMniConsultarEnviosManPrev(JMenuItem mniConsultarEnviosManPrev) {
        this.mniConsultarEnviosManPrev = mniConsultarEnviosManPrev;
    }

    public JMenuItem getMniConsultarFacturas() {
        return mniConsultarFacturas;
    }

    public void setMniConsultarFacturas(JMenuItem mniConsultarFacturas) {
        this.mniConsultarFacturas = mniConsultarFacturas;
    }

    public JMenuItem getMniConsultarRemitos() {
        return mniConsultarRemitos;
    }

    public void setMniConsultarRemitos(JMenuItem mniConsultarRemitos) {
        this.mniConsultarRemitos = mniConsultarRemitos;
    }

    public JMenuItem getMniCotizacionTrabajo() {
        return mniCotizacionTrabajo;
    }

    public void setMniCotizacionTrabajo(JMenuItem mniCotizacionTrabajo) {
        this.mniCotizacionTrabajo = mniCotizacionTrabajo;
    }

    public JMenuItem getMniEmpleados() {
        return mniEmpleados;
    }

    public void setMniEmpleados(JMenuItem mniEmpleados) {
        this.mniEmpleados = mniEmpleados;
    }

    public JMenuItem getMniEmpresaMantenimiento() {
        return mniEmpresaMantenimiento;
    }

    public void setMniEmpresaMantenimiento(JMenuItem mniEmpresaMantenimiento) {
        this.mniEmpresaMantenimiento = mniEmpresaMantenimiento;
    }

    public JMenuItem getMniEmpresaMetalurgica() {
        return mniEmpresaMetalurgica;
    }

    public void setMniEmpresaMetalurgica(JMenuItem mniEmpresaMetalurgica) {
        this.mniEmpresaMetalurgica = mniEmpresaMetalurgica;
    }

    public JMenuItem getMniEntregaPedido() {
        return mniEntregaPedido;
    }

    public void setMniEntregaPedido(JMenuItem mniEntregaPedido) {
        this.mniEntregaPedido = mniEntregaPedido;
    }

    public JMenuItem getMniEtapaDeProduccion() {
        return mniEtapaDeProduccion;
    }

    public void setMniEtapaDeProduccion(JMenuItem mniEtapaDeProduccion) {
        this.mniEtapaDeProduccion = mniEtapaDeProduccion;
    }

    public JMenuItem getMniFormaDePago() {
        return mniFormaDePago;
    }

    public void setMniFormaDePago(JMenuItem mniFormaDePago) {
        this.mniFormaDePago = mniFormaDePago;
    }

    public JMenuItem getMniGenerarDetalleMateriaPrima() {
        return mniGenerarDetalleMateriaPrima;
    }

    public void setMniGenerarDetalleMateriaPrima(JMenuItem mniGenerarDetalleMateriaPrima) {
        this.mniGenerarDetalleMateriaPrima = mniGenerarDetalleMateriaPrima;
    }

    public JMenuItem getMniGenerarDetalleProcedimientosCalidad() {
        return mniGenerarDetalleProcedimientosCalidad;
    }

    public void setMniGenerarDetalleProcedimientosCalidad(JMenuItem mniGenerarDetalleProcedimientosCalidad) {
        this.mniGenerarDetalleProcedimientosCalidad = mniGenerarDetalleProcedimientosCalidad;
    }

    public JMenuItem getMniGenerarSolicitud() {
        return mniGenerarSolicitud;
    }

    public void setMniGenerarSolicitud(JMenuItem mniGenerarSolicitud) {
        this.mniGenerarSolicitud = mniGenerarSolicitud;
    }

    public JMenuItem getMniLanzarCalidad() {
        return mniLanzarCalidad;
    }

    public void setMniLanzarCalidad(JMenuItem mniLanzarCalidad) {
        this.mniLanzarCalidad = mniLanzarCalidad;
    }

    public JMenuItem getMniLanzarProduccion() {
        return mniLanzarProduccion;
    }

    public void setMniLanzarProduccion(JMenuItem mniLanzarProduccion) {
        this.mniLanzarProduccion = mniLanzarProduccion;
    }

    public JMenuItem getMniMPrima() {
        return mniMPrima;
    }

    public void setMniMPrima(JMenuItem mniMPrima) {
        this.mniMPrima = mniMPrima;
    }

    public JMenuItem getMniMantenimientoPreventivo() {
        return mniMantenimientoPreventivo;
    }

    public void setMniMantenimientoPreventivo(JMenuItem mniMantenimientoPreventivo) {
        this.mniMantenimientoPreventivo = mniMantenimientoPreventivo;
    }

    public JMenuItem getMniMantenimientos() {
        return mniMantenimientos;
    }

    public void setMniMantenimientos(JMenuItem mniMantenimientos) {
        this.mniMantenimientos = mniMantenimientos;
    }

    public JMenuItem getMniMateriaPrima() {
        return mniMateriaPrima;
    }

    public void setMniMateriaPrima(JMenuItem mniMateriaPrima) {
        this.mniMateriaPrima = mniMateriaPrima;
    }

    public JMenuItem getMniMatriz() {
        return mniMatriz;
    }

    public void setMniMatriz(JMenuItem mniMatriz) {
        this.mniMatriz = mniMatriz;
    }

    public JMenuItem getMniNuevoUsuario() {
        return mniNuevoUsuario;
    }

    public void setMniNuevoUsuario(JMenuItem mniNuevoUsuario) {
        this.mniNuevoUsuario = mniNuevoUsuario;
    }

    public JMenuItem getMniOrdenDeCompra() {
        return mniOrdenDeCompra;
    }

    public void setMniOrdenDeCompra(JMenuItem mniOrdenDeCompra) {
        this.mniOrdenDeCompra = mniOrdenDeCompra;
    }

    public JMenuItem getMniPieza() {
        return mniPieza;
    }

    public void setMniPieza(JMenuItem mniPieza) {
        this.mniPieza = mniPieza;
    }

    public JMenuItem getMniPresupuesto() {
        return mniPedidoCotizacion;
    }

    public void setMniPedidoCotizacion(JMenuItem mniPedidoCotizacion) {
        this.mniPedidoCotizacion = mniPedidoCotizacion;
    }

    public JMenuItem getMniProducto() {
        return mniProducto;
    }

    public void setMniProducto(JMenuItem mniProducto) {
        this.mniProducto = mniProducto;
    }

    public JMenuItem getMniProveedor() {
        return mniProveedor;
    }

    public void setMniProveedor(JMenuItem mniProveedor) {
        this.mniProveedor = mniProveedor;
    }

    public JMenuItem getMniProveedores() {
        return mniProveedores;
    }

    public void setMniProveedores(JMenuItem mniProveedores) {
        this.mniProveedores = mniProveedores;
    }

    public JMenuItem getMniReclamoProveedor() {
        return mniReclamoProveedor;
    }

    public void setMniReclamoProveedor(JMenuItem mniReclamoProveedor) {
        this.mniReclamoProveedor = mniReclamoProveedor;
    }

    public JMenuItem getMniRegistrarAsistencia() {
        return mniRegistrarAsistencia;
    }

    public void setMniRegistrarAsistencia(JMenuItem mniRegistrarAsistencia) {
        this.mniRegistrarAsistencia = mniRegistrarAsistencia;
    }

    public JMenuItem getMniRegistrarConfirmacionPedido() {
        return mniRegistrarConfirmacionPedido;
    }

    public void setMniRegistrarConfirmacionPedido(JMenuItem mniRegistrarConfirmacionPedido) {
        this.mniRegistrarConfirmacionPedido = mniRegistrarConfirmacionPedido;
    }

    public JMenuItem getMniRegistrarDiaNoLaboral() {
        return mniRegistrarDiaNoLaboral;
    }

    public void setMniRegistrarDiaNoLaboral(JMenuItem mniRegistrarDiaNoLaboral) {
        this.mniRegistrarDiaNoLaboral = mniRegistrarDiaNoLaboral;
    }

    public JMenuItem getMniRegistrarEnvioManPrev() {
        return mniRegistrarEnvioManPrev;
    }

    public void setMniRegistrarEnvioManPrev(JMenuItem mniRegistrarEnvioManPrev) {
        this.mniRegistrarEnvioManPrev = mniRegistrarEnvioManPrev;
    }

    public JMenuItem getMniRegistrarIngresoCotizacionTrabajo() {
        return mniRegistrarIngresoCotizacionTrabajo;
    }

    public void setMniRegistrarIngresoCotizacionTrabajo(JMenuItem mniRegistrarIngresoCotizacionTrabajo) {
        this.mniRegistrarIngresoCotizacionTrabajo = mniRegistrarIngresoCotizacionTrabajo;
    }

    public JMenuItem getMniRegistrarPlanificacionCalidad() {
        return mniRegistrarPlanificacionCalidad;
    }

    public void setMniRegistrarPlanificacionCalidad(JMenuItem mniRegistrarPlanificacionCalidad) {
        this.mniRegistrarPlanificacionCalidad = mniRegistrarPlanificacionCalidad;
    }

    public JMenuItem getMniRegistrarPresupuesto() {
        return mniRegistrarPresupuesto;
    }

    public void setMniRegistrarPresupuesto(JMenuItem mniRegistrarPresupuesto) {
        this.mniRegistrarPresupuesto = mniRegistrarPresupuesto;
    }

    public JMenuItem getMniReporteAusentismo() {
        return mniReporteAusentismo;
    }

    public void setMniReporteAusentismo(JMenuItem mniReporteAusentismo) {
        this.mniReporteAusentismo = mniReporteAusentismo;
    }

    public JMenuItem getMniReporteClientes() {
        return mniReporteClientes;
    }

    public void setMniReporteClientes(JMenuItem mniReporteClientes) {
        this.mniReporteClientes = mniReporteClientes;
    }

    public JMenuItem getMniReportePedidos() {
        return mniReportePedidos;
    }

    public void setMniReportePedidos(JMenuItem mniReportePedidos) {
        this.mniReportePedidos = mniReportePedidos;
    }

    public JMenuItem getMniReportesReclamos() {
        return mniReportesReclamos;
    }

    public void setMniReportesReclamos(JMenuItem mniReportesReclamos) {
        this.mniReportesReclamos = mniReportesReclamos;
    }

    public JMenuItem getMniRotura() {
        return mniRotura;
    }

    public void setMniRotura(JMenuItem mniRotura) {
        this.mniRotura = mniRotura;
    }

    public JMenuItem getMniServicio() {
        return mniServicio;
    }

    public void setMniServicio(JMenuItem mniServicio) {
        this.mniServicio = mniServicio;
    }

    public JMenuItem getMniTipoDoc() {
        return mniTipoDoc;
    }

    public void setMniTipoDoc(JMenuItem mniTipoDoc) {
        this.mniTipoDoc = mniTipoDoc;
    }

    public JMenuItem getMniTipoMaquina() {
        return mniTipoMaquina;
    }

    public void setMniTipoMaquina(JMenuItem mniTipoMaquina) {
        this.mniTipoMaquina = mniTipoMaquina;
    }

    public JMenuItem getMniTipoMaterial() {
        return mniTipoMaterial;
    }

    public void setMniTipoMaterial(JMenuItem mniTipoMaterial) {
        this.mniTipoMaterial = mniTipoMaterial;
    }

    public JMenuItem getMniTrabajosTercerizados() {
        return mniTrabajosTercerizados;
    }

    public void setMniTrabajosTercerizados(JMenuItem mniTrabajosTercerizados) {
        this.mniTrabajosTercerizados = mniTrabajosTercerizados;
    }

    public JMenu getMnuAlmacenamiento() {
        return mnuAlmacenamiento;
    }

    public void setMnuAlmacenamiento(JMenu mnuAlmacenamiento) {
        this.mnuAlmacenamiento = mnuAlmacenamiento;
    }

    public JMenu getMnuAyuda() {
        return mnuAyuda;
    }

    public void setMnuAyuda(JMenu mnuAyuda) {
        this.mnuAyuda = mnuAyuda;
    }

    public JMenu getMnuCalidad() {
        return mnuCalidad;
    }

    public void setMnuCalidad(JMenu mnuCalidad) {
        this.mnuCalidad = mnuCalidad;
    }

    public JMenu getMnuCompras() {
        return mnuCompras;
    }

    public void setMnuCompras(JMenu mnuCompras) {
        this.mnuCompras = mnuCompras;
    }

    public JMenu getMnuFinanzas() {
        return mnuFinanzas;
    }

    public void setMnuFinanzas(JMenu mnuFinanzas) {
        this.mnuFinanzas = mnuFinanzas;
    }

    public JMenu getMnuInicio() {
        return mnuInicio;
    }

    public void setMnuInicio(JMenu mnuInicio) {
        this.mnuInicio = mnuInicio;
    }

    public JMenu getMnuMantenimiento() {
        return mnuMantenimiento;
    }

    public void setMnuMantenimiento(JMenu mnuMantenimiento) {
        this.mnuMantenimiento = mnuMantenimiento;
    }

    public JMenu getMnuProduccion() {
        return mnuProduccion;
    }

    public void setMnuProduccion(JMenu mnuProduccion) {
        this.mnuProduccion = mnuProduccion;
    }

    public JMenu getMnuRRHH() {
        return mnuRRHH;
    }

    public void setMnuRRHH(JMenu mnuRRHH) {
        this.mnuRRHH = mnuRRHH;
    }

    public JMenu getMnuReportes() {
        return mnuReportes;
    }

    public void setMnuReportes(JMenu mnuReportes) {
        this.mnuReportes = mnuReportes;
    }

    public JMenu getMnuTrabajosTercerizados() {
        return mnuTrabajosTercerizados;
    }

    public void setMnuTrabajosTercerizados(JMenu mnuTrabajosTercerizados) {
        this.mnuTrabajosTercerizados = mnuTrabajosTercerizados;
    }

    public JMenu getMnuVentas() {
        return mnuVentas;
    }

    public void setMnuVentas(JMenu mnuVentas) {
        this.mnuVentas = mnuVentas;
    }

    public JPanelTransparente getPnlCalidad() {
        return pnlCalidad;
    }

    public void setPnlCalidad(JPanelTransparente pnlCalidad) {
        this.pnlCalidad = pnlCalidad;
    }

    public JPanelTransparente getPnlProduccion() {
        return pnlProduccion;
    }

    public void setPnlProduccion(JPanelTransparente pnlProduccion) {
        this.pnlProduccion = pnlProduccion;
    }

    public JPanelTransparente getPnlVentas() {
        return pnlVentas;
    }

    public void setPnlVentas(JPanelTransparente pnlVentas) {
        this.pnlVentas = pnlVentas;
    }

    public JPanelTransparente getPnlRegistrarFinalizacion() {
        return pnlRegistrarFinalizacion;
    }

    public void setPnlRegistrarFinalizacion(JPanelTransparente pnlRegistrarFinalizacion) {
        this.pnlRegistrarFinalizacion = pnlRegistrarFinalizacion;
    }

    public JMenuItem getMniConfiguracionJornada() {
        return mniConfiguracionJornada;
    }

    public void setMniConfiguracionJornada(JMenuItem mniConfiguracionJornada) {
        this.mniConfiguracionJornada = mniConfiguracionJornada;
    }

    public JMenuItem getMniGenerarDetalleEtapas() {
        return mniGenerarDetalleEtapas;
    }

    public void setMniGenerarDetalleEtapas(JMenuItem mniGenerarDetalleEtapas) {
        this.mniGenerarDetalleEtapas = mniGenerarDetalleEtapas;
    }

    public JMenuItem getMniMaquinaria() {
        return mniMaquinaria;
    }

    public JMenuItem getMniRegistrarPlanificacionProduccion() {
        return mniRegistrarPlanificacionProduccion;
    }

    public void setMniRegistrarPlanificacionProduccion(JMenuItem mniRegistrarPlanificacionProduccion) {
        this.mniRegistrarPlanificacionProduccion = mniRegistrarPlanificacionProduccion;
    }

    public void setMniMaquinaria(JMenuItem mniMaquinaria) {
        this.mniMaquinaria = mniMaquinaria;
    }

    public void setVisibleComponents(boolean b) {
        pnlCalidad.setVisible(b);
        pnlProduccion.setVisible(b);
        pnlVentas.setVisible(b);
        pnlRegistrarFinalizacion.setVisible(b);
        mnuAlmacenamiento.setVisible(b);
        mnuCalidad.setVisible(b);
        mnuCompras.setVisible(b);
        mnuFinanzas.setVisible(b);
        mnuMantenimiento.setVisible(b);
        mnuProduccion.setVisible(b);
        mnuRRHH.setVisible(b);
        mnuReportes.setVisible(b);
        mnuTrabajosTercerizados.setVisible(b);
        mnuVentas.setVisible(b);

        setVisibleItemsMenuProduccion(b);
        setVisibleItemsMenuInicio(b);

        mniCerrarSesion.setVisible(true);
        mniCambiarContrasenia.setVisible(true);
    }

    public void setVisibleItemsMenuProduccion(boolean b) {
        mniEtapaDeProduccion.setVisible(b);
        mniProducto.setVisible(b);
        mniPieza.setVisible(b);
        mniMaquinaria.setVisible(b);
        mniTipoMaterial.setVisible(b);
        mniRegistrarPlanificacionProduccion.setVisible(b);
        mniLanzarProduccion.setVisible(b);
        mniMatriz.setVisible(b);
        mniGenerarDetalleMateriaPrima.setVisible(b);
        mniGenerarDetalleEtapas.setVisible(b);
    }

    public void setVisibleItemsMenuInicio(boolean b) {
        mniCerrarSesion.setVisible(b);
        mniAdministrarUsuarios.setVisible(b);
        mniCambiarContrasenia.setVisible(b);
        mniNuevoUsuario.setVisible(b);
    }
}
