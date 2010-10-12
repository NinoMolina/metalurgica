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
public interface DAOFactory
{
	public AccioncalidadDAO createAccioncalidadDAO();

	public AsistenciaDAO createAsistenciaDAO();

	public BarrioDAO createBarrioDAO();

	public CalendarioDAO createCalendarioDAO();

	public CargoDAO createCargoDAO();

	public CategoriaDAO createCategoriaDAO();

	public ClienteDAO createClienteDAO();

	public CodigodebarraDAO createCodigodebarraDAO();

	public CompraDAO createCompraDAO();

	public ComprobantepagoDAO createComprobantepagoDAO();

	public CondicionivaDAO createCondicionivaDAO();

	public DetallaplanificacionproduccionDAO createDetallaplanificacionproduccionDAO();

	public DetallecompraDAO createDetallecompraDAO();

	public DetalleejecucionplanificacionDAO createDetalleejecucionplanificacionDAO();

	public DetalleejecucionplanificacioncalidadDAO createDetalleejecucionplanificacioncalidadDAO();

	//public DetallefacturaDAO createDetallefacturaDAO();

	public DetallemantenimientocorrectivoDAO createDetallemantenimientocorrectivoDAO();

	public DetallemantenimientopreventivoDAO createDetallemantenimientopreventivoDAO();

	public DetallepedidoDAO createDetallepedidoDAO();

public DetallepiezacalidadpresupuestoDAO createDetallepiezacalidadpresupuestoDAO();

	public DetallepiezapresupuestoDAO createDetallepiezapresupuestoDAO();


	public DetalleplanificacioncalidadDAO createDetalleplanificacioncalidadDAO();

	public DetalleplanprocedimientosDAO createDetalleplanprocedimientosDAO();

	public DetalleplanprocesoscalidadDAO createDetalleplanprocesoscalidadDAO();

	public DetallepresupuestoDAO createDetallepresupuestoDAO();

        public DetalleproductopresupuestoDAO createDetalleproductopresupuestoDAO();

	public DetalleproductoDAO createDetalleproductoDAO();

	//public DetalleproductorealDAO createDetalleproductorealDAO();

	public DetallereclamoclienteDAO createDetallereclamoclienteDAO();

	public DetallereclamoempresametalurgicaDAO createDetallereclamoempresametalurgicaDAO();

	public DetallereclamoproveedorDAO createDetallereclamoproveedorDAO();

	public DetalleremitoDAO createDetalleremitoDAO();

	public DetallerequerimientosmateriaprimaDAO createDetallerequerimientosmateriaprimaDAO();

	public DetalletrabajotercerizadoDAO createDetalletrabajotercerizadoDAO();

	public DomicilioDAO createDomicilioDAO();

	public EjecucionetapaproduccionDAO createEjecucionetapaproduccionDAO();

	public EjecucionplanificacioncalidadDAO createEjecucionplanificacioncalidadDAO();

	public EjecucionplanificacionproduccionDAO createEjecucionplanificacionproduccionDAO();

	public EjecucionprocesocalidadDAO createEjecucionprocesocalidadDAO();

	public EmpleadoDAO createEmpleadoDAO();

	public EmpleadoxturnoDAO createEmpleadoxturnoDAO();

	public EmpresametalurgicaDAO createEmpresametalurgicaDAO();

	public EstadoclienteDAO createEstadoclienteDAO();

	public EstadocompraDAO createEstadocompraDAO();

	public EstadodetallecompraDAO createEstadodetallecompraDAO();

	public EstadodetalletrabajotercerizadoDAO createEstadodetalletrabajotercerizadoDAO();

	public EstadoejecetapaprodDAO createEstadoejecetapaprodDAO();

	public EstadoejecplancalidadDAO createEstadoejecplancalidadDAO();

	public EstadoejecplanifpedidoDAO createEstadoejecplanifpedidoDAO();

	public EstadoejecucionprocesocalidadDAO createEstadoejecucionprocesocalidadDAO();

	public EstadofacturaDAO createEstadofacturaDAO();

	public EstadomaquinaDAO createEstadomaquinaDAO();

	public EstadopedidoDAO createEstadopedidoDAO();

	public EstadopiezarealDAO createEstadopiezarealDAO();

	//public EstadoproductorealDAO createEstadoproductorealDAO();

	//public EstadoremitoDAO createEstadoremitoDAO();

	public EstadotrabajotercerizadoDAO createEstadotrabajotercerizadoDAO();

	public EtapadeproduccionDAO createEtapadeproduccionDAO();

	public FacturaDAO createFacturaDAO();

	public FormadepagoDAO createFormadepagoDAO();

	public LocalidadDAO createLocalidadDAO();

	public MantenimientocorrectivoDAO createMantenimientocorrectivoDAO();

	public MantenimientopreventivoDAO createMantenimientopreventivoDAO();

	public MaquinaDAO createMaquinaDAO();

	public MaquinaxejecucionetapaproduccionDAO createMaquinaxejecucionetapaproduccionDAO();

	public MaquinaxprocesocalidadDAO createMaquinaxprocesocalidadDAO();

	public MarcaDAO createMarcaDAO();

	public MateriaprimaDAO createMateriaprimaDAO();

	public MatrizDAO createMatrizDAO();

	public PedidoDAO createPedidoDAO();

	public PedidomatrizDAO createPedidomatrizDAO();

	public PiezaDAO createPiezaDAO();

	public PiezarealDAO createPiezarealDAO();

	public PiezaxetapadeproduccionDAO createPiezaxetapadeproduccionDAO();

	public PlanificacioncalidadDAO createPlanificacioncalidadDAO();

	public PlanificacionproduccionDAO createPlanificacionproduccionDAO();

	public PlanoDAO createPlanoDAO();

	public PlanprocedimientosDAO createPlanprocedimientosDAO();

	public PlanprocesoscalidadDAO createPlanprocesoscalidadDAO();

	public PlanrequerimientosmateriaprimaDAO createPlanrequerimientosmateriaprimaDAO();

	public PresupuestoDAO createPresupuestoDAO();

	public PrioridadDAO createPrioridadDAO();

	public PrivilegioDAO createPrivilegioDAO();

	public ProcesocalidadDAO createProcesocalidadDAO();

	public ProductoDAO createProductoDAO();

	public ProductorealDAO createProductorealDAO();

	public ProveedorDAO createProveedorDAO();

	public ProveedormantenimientomaquinaDAO createProveedormantenimientomaquinaDAO();

	public ProveedorxmateriaprimaDAO createProveedorxmateriaprimaDAO();

	public ProvinciaDAO createProvinciaDAO();

	//public PruebaDAO createPruebaDAO();

	public ReclamoclienteDAO createReclamoclienteDAO();

	public ReclamoempresametalurgicaDAO createReclamoempresametalurgicaDAO();

	public ReclamoproveedorDAO createReclamoproveedorDAO();

	public RemitoDAO createRemitoDAO();

	public ResponsableDAO createResponsableDAO();

	public RolDAO createRolDAO();

	public RolxprivilegioDAO createRolxprivilegioDAO();

	public RoturaDAO createRoturaDAO();

	public ServicioDAO createServicioDAO();

	public SesionDAO createSesionDAO();

	public TipodocumentoDAO createTipodocumentoDAO();

	public TipoivaDAO createTipoivaDAO();

	public TipomaquinaDAO createTipomaquinaDAO();

	public TipomaterialDAO createTipomaterialDAO();

	public TiporeclamoDAO createTiporeclamoDAO();

	public TrabajotercerizadoDAO createTrabajotercerizadoDAO();

	public TurnoDAO createTurnoDAO();

	public UnidadmedidaDAO createUnidadmedidaDAO();

	public UsuarioDAO createUsuarioDAO();

	public UsuarioxrolDAO createUsuarioxrolDAO();

}
