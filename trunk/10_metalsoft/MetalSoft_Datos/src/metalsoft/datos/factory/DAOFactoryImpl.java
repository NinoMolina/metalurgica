/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:00 ART 2010
*
* DAO-Generator Version 1.2
*
*/
package metalsoft.datos.factory;
import java.math.*;
import java.sql.*;
import java.net.URL;
import java.io.Serializable;
import metalsoft.datos.idao.*;
import metalsoft.datos.dao.*;
public class DAOFactoryImpl implements Serializable,DAOFactory
{
	private static final AccioncalidadDAOImpl  accioncalidaddaoimpl = new AccioncalidadDAOImpl();
	public AccioncalidadDAO createAccioncalidadDAO()
	{
		return accioncalidaddaoimpl;
	}

	private static final AsistenciaDAOImpl  asistenciadaoimpl = new AsistenciaDAOImpl();
	public AsistenciaDAO createAsistenciaDAO()
	{
		return asistenciadaoimpl;
	}

	private static final BarrioDAOImpl  barriodaoimpl = new BarrioDAOImpl();
	public BarrioDAO createBarrioDAO()
	{
		return barriodaoimpl;
	}

	private static final CalendarioDAOImpl  calendariodaoimpl = new CalendarioDAOImpl();
	public CalendarioDAO createCalendarioDAO()
	{
		return calendariodaoimpl;
	}

	private static final CargoDAOImpl  cargodaoimpl = new CargoDAOImpl();
	public CargoDAO createCargoDAO()
	{
		return cargodaoimpl;
	}

	private static final CategoriaDAOImpl  categoriadaoimpl = new CategoriaDAOImpl();
	public CategoriaDAO createCategoriaDAO()
	{
		return categoriadaoimpl;
	}

	private static final ClienteDAOImpl  clientedaoimpl = new ClienteDAOImpl();
	public ClienteDAO createClienteDAO()
	{
		return clientedaoimpl;
	}

	private static final CodigodebarraDAOImpl  codigodebarradaoimpl = new CodigodebarraDAOImpl();
	public CodigodebarraDAO createCodigodebarraDAO()
	{
		return codigodebarradaoimpl;
	}

	private static final CompraDAOImpl  compradaoimpl = new CompraDAOImpl();
	public CompraDAO createCompraDAO()
	{
		return compradaoimpl;
	}

	private static final ComprobantepagoDAOImpl  comprobantepagodaoimpl = new ComprobantepagoDAOImpl();
	public ComprobantepagoDAO createComprobantepagoDAO()
	{
		return comprobantepagodaoimpl;
	}

	private static final CondicionivaDAOImpl  condicionivadaoimpl = new CondicionivaDAOImpl();
	public CondicionivaDAO createCondicionivaDAO()
	{
		return condicionivadaoimpl;
	}

	private static final DetallaplanificacionproduccionDAOImpl  detallaplanificacionproducciondaoimpl = new DetallaplanificacionproduccionDAOImpl();
	public DetallaplanificacionproduccionDAO createDetallaplanificacionproduccionDAO()
	{
		return detallaplanificacionproducciondaoimpl;
	}

	private static final DetallecompraDAOImpl  detallecompradaoimpl = new DetallecompraDAOImpl();
	public DetallecompraDAO createDetallecompraDAO()
	{
		return detallecompradaoimpl;
	}

	private static final DetalleejecucionplanificacionDAOImpl  detalleejecucionplanificaciondaoimpl = new DetalleejecucionplanificacionDAOImpl();
	public DetalleejecucionplanificacionDAO createDetalleejecucionplanificacionDAO()
	{
		return detalleejecucionplanificaciondaoimpl;
	}

	private static final DetalleejecucionplanificacioncalidadDAOImpl  detalleejecucionplanificacioncalidaddaoimpl = new DetalleejecucionplanificacioncalidadDAOImpl();
	public DetalleejecucionplanificacioncalidadDAO createDetalleejecucionplanificacioncalidadDAO()
	{
		return detalleejecucionplanificacioncalidaddaoimpl;
	}

	

	private static final DetallemantenimientocorrectivoDAOImpl  detallemantenimientocorrectivodaoimpl = new DetallemantenimientocorrectivoDAOImpl();
	public DetallemantenimientocorrectivoDAO createDetallemantenimientocorrectivoDAO()
	{
		return detallemantenimientocorrectivodaoimpl;
	}

	private static final DetallemantenimientopreventivoDAOImpl  detallemantenimientopreventivodaoimpl = new DetallemantenimientopreventivoDAOImpl();
	public DetallemantenimientopreventivoDAO createDetallemantenimientopreventivoDAO()
	{
		return detallemantenimientopreventivodaoimpl;
	}

	private static final DetallepedidoDAOImpl  detallepedidodaoimpl = new DetallepedidoDAOImpl();
	public DetallepedidoDAO createDetallepedidoDAO()
	{
		return detallepedidodaoimpl;
	}

private static final DetallepiezacalidadpresupuestoDAOImpl  detallepiezacalidadpresupuestodaoimpl = new DetallepiezacalidadpresupuestoDAOImpl();
	public DetallepiezacalidadpresupuestoDAO createDetallepiezacalidadpresupuestoDAO()
	{
		return detallepiezacalidadpresupuestodaoimpl;
	}


	private static final DetallepiezapresupuestoDAOImpl  detallepiezapresupuestodaoimpl = new DetallepiezapresupuestoDAOImpl();
	public DetallepiezapresupuestoDAO createDetallepiezapresupuestoDAO()
	{
		return detallepiezapresupuestodaoimpl;
	}

	private static final DetalleplanificacioncalidadDAOImpl  detalleplanificacioncalidaddaoimpl = new DetalleplanificacioncalidadDAOImpl();
	public DetalleplanificacioncalidadDAO createDetalleplanificacioncalidadDAO()
	{
		return detalleplanificacioncalidaddaoimpl;
	}

	private static final DetalleplanprocedimientosDAOImpl  detalleplanprocedimientosdaoimpl = new DetalleplanprocedimientosDAOImpl();
	public DetalleplanprocedimientosDAO createDetalleplanprocedimientosDAO()
	{
		return detalleplanprocedimientosdaoimpl;
	}

	private static final DetalleplanprocesoscalidadDAOImpl  detalleplanprocesoscalidaddaoimpl = new DetalleplanprocesoscalidadDAOImpl();
	public DetalleplanprocesoscalidadDAO createDetalleplanprocesoscalidadDAO()
	{
		return detalleplanprocesoscalidaddaoimpl;
	}

	private static final DetallepresupuestoDAOImpl  detallepresupuestodaoimpl = new DetallepresupuestoDAOImpl();
	public DetallepresupuestoDAO createDetallepresupuestoDAO()
	{
		return detallepresupuestodaoimpl;
	}


	private static final DetalleproductoDAOImpl  detalleproductodaoimpl = new DetalleproductoDAOImpl();
	public DetalleproductoDAO createDetalleproductoDAO()
	{
		return detalleproductodaoimpl;
	}


private static final DetalleproductopresupuestoDAOImpl  detalleproductopresupuestodaoimpl = new DetalleproductopresupuestoDAOImpl();
	public DetalleproductopresupuestoDAO createDetalleproductopresupuestoDAO()
	{
		return detalleproductopresupuestodaoimpl;
	}

	

	private static final DetallereclamoclienteDAOImpl  detallereclamoclientedaoimpl = new DetallereclamoclienteDAOImpl();
	public DetallereclamoclienteDAO createDetallereclamoclienteDAO()
	{
		return detallereclamoclientedaoimpl;
	}

	private static final DetallereclamoempresametalurgicaDAOImpl  detallereclamoempresametalurgicadaoimpl = new DetallereclamoempresametalurgicaDAOImpl();
	public DetallereclamoempresametalurgicaDAO createDetallereclamoempresametalurgicaDAO()
	{
		return detallereclamoempresametalurgicadaoimpl;
	}

	private static final DetallereclamoproveedorDAOImpl  detallereclamoproveedordaoimpl = new DetallereclamoproveedorDAOImpl();
	public DetallereclamoproveedorDAO createDetallereclamoproveedorDAO()
	{
		return detallereclamoproveedordaoimpl;
	}

	private static final DetalleremitoDAOImpl  detalleremitodaoimpl = new DetalleremitoDAOImpl();
	public DetalleremitoDAO createDetalleremitoDAO()
	{
		return detalleremitodaoimpl;
	}

	private static final DetallerequerimientosmateriaprimaDAOImpl  detallerequerimientosmateriaprimadaoimpl = new DetallerequerimientosmateriaprimaDAOImpl();
	public DetallerequerimientosmateriaprimaDAO createDetallerequerimientosmateriaprimaDAO()
	{
		return detallerequerimientosmateriaprimadaoimpl;
	}

	private static final DetalletrabajotercerizadoDAOImpl  detalletrabajotercerizadodaoimpl = new DetalletrabajotercerizadoDAOImpl();
	public DetalletrabajotercerizadoDAO createDetalletrabajotercerizadoDAO()
	{
		return detalletrabajotercerizadodaoimpl;
	}

	private static final DomicilioDAOImpl  domiciliodaoimpl = new DomicilioDAOImpl();
	public DomicilioDAO createDomicilioDAO()
	{
		return domiciliodaoimpl;
	}

	private static final EjecucionetapaproduccionDAOImpl  ejecucionetapaproducciondaoimpl = new EjecucionetapaproduccionDAOImpl();
	public EjecucionetapaproduccionDAO createEjecucionetapaproduccionDAO()
	{
		return ejecucionetapaproducciondaoimpl;
	}

	private static final EjecucionplanificacioncalidadDAOImpl  ejecucionplanificacioncalidaddaoimpl = new EjecucionplanificacioncalidadDAOImpl();
	public EjecucionplanificacioncalidadDAO createEjecucionplanificacioncalidadDAO()
	{
		return ejecucionplanificacioncalidaddaoimpl;
	}

	private static final EjecucionplanificacionproduccionDAOImpl  ejecucionplanificacionproducciondaoimpl = new EjecucionplanificacionproduccionDAOImpl();
	public EjecucionplanificacionproduccionDAO createEjecucionplanificacionproduccionDAO()
	{
		return ejecucionplanificacionproducciondaoimpl;
	}

	private static final EjecucionprocesocalidadDAOImpl  ejecucionprocesocalidaddaoimpl = new EjecucionprocesocalidadDAOImpl();
	public EjecucionprocesocalidadDAO createEjecucionprocesocalidadDAO()
	{
		return ejecucionprocesocalidaddaoimpl;
	}

	private static final EmpleadoDAOImpl  empleadodaoimpl = new EmpleadoDAOImpl();
	public EmpleadoDAO createEmpleadoDAO()
	{
		return empleadodaoimpl;
	}

	private static final EmpleadoxturnoDAOImpl  empleadoxturnodaoimpl = new EmpleadoxturnoDAOImpl();
	public EmpleadoxturnoDAO createEmpleadoxturnoDAO()
	{
		return empleadoxturnodaoimpl;
	}

	private static final EmpresametalurgicaDAOImpl  empresametalurgicadaoimpl = new EmpresametalurgicaDAOImpl();
	public EmpresametalurgicaDAO createEmpresametalurgicaDAO()
	{
		return empresametalurgicadaoimpl;
	}

	private static final EstadoclienteDAOImpl  estadoclientedaoimpl = new EstadoclienteDAOImpl();
	public EstadoclienteDAO createEstadoclienteDAO()
	{
		return estadoclientedaoimpl;
	}

	private static final EstadocompraDAOImpl  estadocompradaoimpl = new EstadocompraDAOImpl();
	public EstadocompraDAO createEstadocompraDAO()
	{
		return estadocompradaoimpl;
	}

	private static final EstadodetallecompraDAOImpl  estadodetallecompradaoimpl = new EstadodetallecompraDAOImpl();
	public EstadodetallecompraDAO createEstadodetallecompraDAO()
	{
		return estadodetallecompradaoimpl;
	}

	private static final EstadodetalletrabajotercerizadoDAOImpl  estadodetalletrabajotercerizadodaoimpl = new EstadodetalletrabajotercerizadoDAOImpl();
	public EstadodetalletrabajotercerizadoDAO createEstadodetalletrabajotercerizadoDAO()
	{
		return estadodetalletrabajotercerizadodaoimpl;
	}

	private static final EstadoejecetapaprodDAOImpl  estadoejecetapaproddaoimpl = new EstadoejecetapaprodDAOImpl();
	public EstadoejecetapaprodDAO createEstadoejecetapaprodDAO()
	{
		return estadoejecetapaproddaoimpl;
	}

	private static final EstadoejecplancalidadDAOImpl  estadoejecplancalidaddaoimpl = new EstadoejecplancalidadDAOImpl();
	public EstadoejecplancalidadDAO createEstadoejecplancalidadDAO()
	{
		return estadoejecplancalidaddaoimpl;
	}

	private static final EstadoejecplanifpedidoDAOImpl  estadoejecplanifpedidodaoimpl = new EstadoejecplanifpedidoDAOImpl();
	public EstadoejecplanifpedidoDAO createEstadoejecplanifpedidoDAO()
	{
		return estadoejecplanifpedidodaoimpl;
	}

	private static final EstadoejecucionprocesocalidadDAOImpl  estadoejecucionprocesocalidaddaoimpl = new EstadoejecucionprocesocalidadDAOImpl();
	public EstadoejecucionprocesocalidadDAO createEstadoejecucionprocesocalidadDAO()
	{
		return estadoejecucionprocesocalidaddaoimpl;
	}

	private static final EstadofacturaDAOImpl  estadofacturadaoimpl = new EstadofacturaDAOImpl();
	public EstadofacturaDAO createEstadofacturaDAO()
	{
		return estadofacturadaoimpl;
	}

	private static final EstadomaquinaDAOImpl  estadomaquinadaoimpl = new EstadomaquinaDAOImpl();
	public EstadomaquinaDAO createEstadomaquinaDAO()
	{
		return estadomaquinadaoimpl;
	}

	private static final EstadopedidoDAOImpl  estadopedidodaoimpl = new EstadopedidoDAOImpl();
	public EstadopedidoDAO createEstadopedidoDAO()
	{
		return estadopedidodaoimpl;
	}

	private static final EstadopiezarealDAOImpl  estadopiezarealdaoimpl = new EstadopiezarealDAOImpl();
	public EstadopiezarealDAO createEstadopiezarealDAO()
	{
		return estadopiezarealdaoimpl;
	}

	
	private static final EstadotrabajotercerizadoDAOImpl  estadotrabajotercerizadodaoimpl = new EstadotrabajotercerizadoDAOImpl();
	public EstadotrabajotercerizadoDAO createEstadotrabajotercerizadoDAO()
	{
		return estadotrabajotercerizadodaoimpl;
	}

	private static final EtapadeproduccionDAOImpl  etapadeproducciondaoimpl = new EtapadeproduccionDAOImpl();
	public EtapadeproduccionDAO createEtapadeproduccionDAO()
	{
		return etapadeproducciondaoimpl;
	}

	private static final FacturaDAOImpl  facturadaoimpl = new FacturaDAOImpl();
	public FacturaDAO createFacturaDAO()
	{
		return facturadaoimpl;
	}

	private static final FormadepagoDAOImpl  formadepagodaoimpl = new FormadepagoDAOImpl();
	public FormadepagoDAO createFormadepagoDAO()
	{
		return formadepagodaoimpl;
	}

	private static final LocalidadDAOImpl  localidaddaoimpl = new LocalidadDAOImpl();
	public LocalidadDAO createLocalidadDAO()
	{
		return localidaddaoimpl;
	}

	private static final MantenimientocorrectivoDAOImpl  mantenimientocorrectivodaoimpl = new MantenimientocorrectivoDAOImpl();
	public MantenimientocorrectivoDAO createMantenimientocorrectivoDAO()
	{
		return mantenimientocorrectivodaoimpl;
	}

	private static final MantenimientopreventivoDAOImpl  mantenimientopreventivodaoimpl = new MantenimientopreventivoDAOImpl();
	public MantenimientopreventivoDAO createMantenimientopreventivoDAO()
	{
		return mantenimientopreventivodaoimpl;
	}

	private static final MaquinaDAOImpl  maquinadaoimpl = new MaquinaDAOImpl();
	public MaquinaDAO createMaquinaDAO()
	{
		return maquinadaoimpl;
	}

	private static final MaquinaxejecucionetapaproduccionDAOImpl  maquinaxejecucionetapaproducciondaoimpl = new MaquinaxejecucionetapaproduccionDAOImpl();
	public MaquinaxejecucionetapaproduccionDAO createMaquinaxejecucionetapaproduccionDAO()
	{
		return maquinaxejecucionetapaproducciondaoimpl;
	}

	private static final MaquinaxprocesocalidadDAOImpl  maquinaxprocesocalidaddaoimpl = new MaquinaxprocesocalidadDAOImpl();
	public MaquinaxprocesocalidadDAO createMaquinaxprocesocalidadDAO()
	{
		return maquinaxprocesocalidaddaoimpl;
	}

	private static final MarcaDAOImpl  marcadaoimpl = new MarcaDAOImpl();
	public MarcaDAO createMarcaDAO()
	{
		return marcadaoimpl;
	}

	private static final MateriaprimaDAOImpl  materiaprimadaoimpl = new MateriaprimaDAOImpl();
	public MateriaprimaDAO createMateriaprimaDAO()
	{
		return materiaprimadaoimpl;
	}

	private static final MatrizDAOImpl  matrizdaoimpl = new MatrizDAOImpl();
	public MatrizDAO createMatrizDAO()
	{
		return matrizdaoimpl;
	}

	private static final PedidoDAOImpl  pedidodaoimpl = new PedidoDAOImpl();
	public PedidoDAO createPedidoDAO()
	{
		return pedidodaoimpl;
	}

	private static final PedidomatrizDAOImpl  pedidomatrizdaoimpl = new PedidomatrizDAOImpl();
	public PedidomatrizDAO createPedidomatrizDAO()
	{
		return pedidomatrizdaoimpl;
	}

	private static final PiezaDAOImpl  piezadaoimpl = new PiezaDAOImpl();
	public PiezaDAO createPiezaDAO()
	{
		return piezadaoimpl;
	}

	private static final PiezarealDAOImpl  piezarealdaoimpl = new PiezarealDAOImpl();
	public PiezarealDAO createPiezarealDAO()
	{
		return piezarealdaoimpl;
	}

	private static final PiezaxetapadeproduccionDAOImpl  piezaxetapadeproducciondaoimpl = new PiezaxetapadeproduccionDAOImpl();
	public PiezaxetapadeproduccionDAO createPiezaxetapadeproduccionDAO()
	{
		return piezaxetapadeproducciondaoimpl;
	}

	private static final PlanificacioncalidadDAOImpl  planificacioncalidaddaoimpl = new PlanificacioncalidadDAOImpl();
	public PlanificacioncalidadDAO createPlanificacioncalidadDAO()
	{
		return planificacioncalidaddaoimpl;
	}

	private static final PlanificacionproduccionDAOImpl  planificacionproducciondaoimpl = new PlanificacionproduccionDAOImpl();
	public PlanificacionproduccionDAO createPlanificacionproduccionDAO()
	{
		return planificacionproducciondaoimpl;
	}

	private static final PlanoDAOImpl  planodaoimpl = new PlanoDAOImpl();
	public PlanoDAO createPlanoDAO()
	{
		return planodaoimpl;
	}

	private static final PlanprocedimientosDAOImpl  planprocedimientosdaoimpl = new PlanprocedimientosDAOImpl();
	public PlanprocedimientosDAO createPlanprocedimientosDAO()
	{
		return planprocedimientosdaoimpl;
	}

	private static final PlanprocesoscalidadDAOImpl  planprocesoscalidaddaoimpl = new PlanprocesoscalidadDAOImpl();
	public PlanprocesoscalidadDAO createPlanprocesoscalidadDAO()
	{
		return planprocesoscalidaddaoimpl;
	}

	private static final PlanrequerimientosmateriaprimaDAOImpl  planrequerimientosmateriaprimadaoimpl = new PlanrequerimientosmateriaprimaDAOImpl();
	public PlanrequerimientosmateriaprimaDAO createPlanrequerimientosmateriaprimaDAO()
	{
		return planrequerimientosmateriaprimadaoimpl;
	}

	private static final PresupuestoDAOImpl  presupuestodaoimpl = new PresupuestoDAOImpl();
	public PresupuestoDAO createPresupuestoDAO()
	{
		return presupuestodaoimpl;
	}

	private static final PrioridadDAOImpl  prioridaddaoimpl = new PrioridadDAOImpl();
	public PrioridadDAO createPrioridadDAO()
	{
		return prioridaddaoimpl;
	}

	private static final PrivilegioDAOImpl  privilegiodaoimpl = new PrivilegioDAOImpl();
	public PrivilegioDAO createPrivilegioDAO()
	{
		return privilegiodaoimpl;
	}

	private static final ProcesocalidadDAOImpl  procesocalidaddaoimpl = new ProcesocalidadDAOImpl();
	public ProcesocalidadDAO createProcesocalidadDAO()
	{
		return procesocalidaddaoimpl;
	}

	private static final ProductoDAOImpl  productodaoimpl = new ProductoDAOImpl();
	public ProductoDAO createProductoDAO()
	{
		return productodaoimpl;
	}

		private static final ProductorealDAOImpl  productorealdaoimpl = new ProductorealDAOImpl();
	public ProductorealDAO createProductorealDAO()
	{
		return productorealdaoimpl;
	}

	private static final ProveedorDAOImpl  proveedordaoimpl = new ProveedorDAOImpl();
	public ProveedorDAO createProveedorDAO()
	{
		return proveedordaoimpl;
	}

	private static final ProveedormantenimientomaquinaDAOImpl  proveedormantenimientomaquinadaoimpl = new ProveedormantenimientomaquinaDAOImpl();
	public ProveedormantenimientomaquinaDAO createProveedormantenimientomaquinaDAO()
	{
		return proveedormantenimientomaquinadaoimpl;
	}

	private static final ProveedorxmateriaprimaDAOImpl  proveedorxmateriaprimadaoimpl = new ProveedorxmateriaprimaDAOImpl();
	public ProveedorxmateriaprimaDAO createProveedorxmateriaprimaDAO()
	{
		return proveedorxmateriaprimadaoimpl;
	}

	private static final ProvinciaDAOImpl  provinciadaoimpl = new ProvinciaDAOImpl();
	public ProvinciaDAO createProvinciaDAO()
	{
		return provinciadaoimpl;
	}

	

	private static final ReclamoclienteDAOImpl  reclamoclientedaoimpl = new ReclamoclienteDAOImpl();
	public ReclamoclienteDAO createReclamoclienteDAO()
	{
		return reclamoclientedaoimpl;
	}

	private static final ReclamoempresametalurgicaDAOImpl  reclamoempresametalurgicadaoimpl = new ReclamoempresametalurgicaDAOImpl();
	public ReclamoempresametalurgicaDAO createReclamoempresametalurgicaDAO()
	{
		return reclamoempresametalurgicadaoimpl;
	}

	private static final ReclamoproveedorDAOImpl  reclamoproveedordaoimpl = new ReclamoproveedorDAOImpl();
	public ReclamoproveedorDAO createReclamoproveedorDAO()
	{
		return reclamoproveedordaoimpl;
	}

	private static final RemitoDAOImpl  remitodaoimpl = new RemitoDAOImpl();
	public RemitoDAO createRemitoDAO()
	{
		return remitodaoimpl;
	}

	private static final ResponsableDAOImpl  responsabledaoimpl = new ResponsableDAOImpl();
	public ResponsableDAO createResponsableDAO()
	{
		return responsabledaoimpl;
	}

	private static final RolDAOImpl  roldaoimpl = new RolDAOImpl();
	public RolDAO createRolDAO()
	{
		return roldaoimpl;
	}

	private static final RolxprivilegioDAOImpl  rolxprivilegiodaoimpl = new RolxprivilegioDAOImpl();
	public RolxprivilegioDAO createRolxprivilegioDAO()
	{
		return rolxprivilegiodaoimpl;
	}

	private static final RoturaDAOImpl  roturadaoimpl = new RoturaDAOImpl();
	public RoturaDAO createRoturaDAO()
	{
		return roturadaoimpl;
	}

	private static final ServicioDAOImpl  serviciodaoimpl = new ServicioDAOImpl();
	public ServicioDAO createServicioDAO()
	{
		return serviciodaoimpl;
	}

	private static final SesionDAOImpl  sesiondaoimpl = new SesionDAOImpl();
	public SesionDAO createSesionDAO()
	{
		return sesiondaoimpl;
	}

	private static final TipodocumentoDAOImpl  tipodocumentodaoimpl = new TipodocumentoDAOImpl();
	public TipodocumentoDAO createTipodocumentoDAO()
	{
		return tipodocumentodaoimpl;
	}

	private static final TipoivaDAOImpl  tipoivadaoimpl = new TipoivaDAOImpl();
	public TipoivaDAO createTipoivaDAO()
	{
		return tipoivadaoimpl;
	}

	private static final TipomaquinaDAOImpl  tipomaquinadaoimpl = new TipomaquinaDAOImpl();
	public TipomaquinaDAO createTipomaquinaDAO()
	{
		return tipomaquinadaoimpl;
	}

	private static final TipomaterialDAOImpl  tipomaterialdaoimpl = new TipomaterialDAOImpl();
	public TipomaterialDAO createTipomaterialDAO()
	{
		return tipomaterialdaoimpl;
	}

	private static final TiporeclamoDAOImpl  tiporeclamodaoimpl = new TiporeclamoDAOImpl();
	public TiporeclamoDAO createTiporeclamoDAO()
	{
		return tiporeclamodaoimpl;
	}

	private static final TrabajotercerizadoDAOImpl  trabajotercerizadodaoimpl = new TrabajotercerizadoDAOImpl();
	public TrabajotercerizadoDAO createTrabajotercerizadoDAO()
	{
		return trabajotercerizadodaoimpl;
	}

	private static final TurnoDAOImpl  turnodaoimpl = new TurnoDAOImpl();
	public TurnoDAO createTurnoDAO()
	{
		return turnodaoimpl;
	}

		private static final UnidadmedidaDAOImpl  unidadmedidadaoimpl = new UnidadmedidaDAOImpl();
	public UnidadmedidaDAO createUnidadmedidaDAO()
	{
		return unidadmedidadaoimpl;
	}

	private static final UsuarioDAOImpl  usuariodaoimpl = new UsuarioDAOImpl();
	public UsuarioDAO createUsuarioDAO()
	{
		return usuariodaoimpl;
	}

	private static final UsuarioxrolDAOImpl  usuarioxroldaoimpl = new UsuarioxrolDAOImpl();
	public UsuarioxrolDAO createUsuarioxrolDAO()
	{
		return usuarioxroldaoimpl;
	}


}
