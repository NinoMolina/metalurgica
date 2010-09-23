-- SQL Manager 2007 for PostgreSQL 4.5.0.8
-- ---------------------------------------
-- Host      : localhost
-- Database  : metalsoft
-- Version   : PostgreSQL 8.3.11, compiled by Visual C++ build 1400


--
-- Definition for function nvonroproducto (OID = 38975) : 
--
SET search_path = public, pg_catalog;
CREATE FUNCTION public.nvonroproducto () RETURNS character varying
AS 
$body$
DECLARE
  result "varchar"(20);
  valor "varchar"(20);
BEGIN
	result:='PROD-';
    
  SELECT (max(p.nroproducto)+1) INTO valor FROM Producto p;
  IF(result IS NULL)THEN
  	result:='PROD-1';
  END IF;
  RETURN textcat(result,valor);
END;
$body$
    LANGUAGE plpgsql;

--
-- Definition for function nvonropedido (OID = 27216) : 
--
SET search_path = public, pg_catalog;
SET check_function_bodies = false;
CREATE FUNCTION public.nvonropedido () RETURNS integer
AS 
$body$
DECLARE
  result INTEGER;
BEGIN
  SELECT (max(p.nropedido)+1) INTO result FROM Pedido p;
  IF(result IS NULL)THEN
  	result:=1;
  END IF;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;

--
-- Definition for function nvonromateriaprima (OID = 60957) : 
--
SET search_path = public, pg_catalog;
CREATE FUNCTION public.nvonromateriaprima () RETURNS bigint
AS 
$body$
DECLARE
  result BIGINT;
BEGIN
  SELECT (max(p.nromateriaprima)+1) INTO result FROM materiaprima p;
  IF(result IS NULL)THEN
  	result:=1;
  END IF;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;

--
-- Definition for function cantpiezasxproducto (OID = 27991) : 
--
CREATE FUNCTION public.cantpiezasxproducto (id bigint) RETURNS integer
AS 
$body$
DECLARE
  result INTEGER;
  valor BIGINT;
BEGIN
valor:=id;
  SELECT sum(dp.cantidadpiezas) INTO result
  FROM detalleproducto dp
  WHERE dp.idproducto=valor;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function esproductosinalgunaetapa (OID = 28033) : 
--
CREATE FUNCTION public.esproductosinalgunaetapa (idproducto bigint) RETURNS boolean
AS 
$body$
DECLARE
  cantPiezasConEtapa INTEGER;
  cantPiezas INTEGER;
BEGIN
	SELECT count(DISTINCT pi.idpieza) INTO cantPiezasConEtapa
  	FROM detalleproducto dpro, pieza pi, piezaxetapadeproduccion pxep
    WHERE idproducto = dpro.idproducto AND dpro.pieza = pi.idpieza AND
           pi.idpieza = pxep.idpieza;
	SELECT count(DISTINCT dpro.iddetalle) INTO cantPiezas
    FROM detalleproducto dpro
	WHERE dpro.idproducto = idproducto;
    
    IF cantPiezasConEtapa<cantPiezas THEN
    	RETURN TRUE;
    ELSE
    	RETURN FALSE;
    END IF;
END;
$body$
    LANGUAGE plpgsql;

--
-- Structure for table pedido (OID = 27217) : 
--
CREATE TABLE public.pedido (
    nropedido bigint NOT NULL,
    fechaconfirmacionpedido date,
    fechaentregaestipulada date,
    fechapedidocotizacion date,
    fechacancelacion date,
    fechaentregareal date,
    estado bigint NOT NULL,
    factura bigint,
    presupuesto bigint,
    fecharequeridacotizacion date,
    plano bigint,
    motivocancelacion character varying(50),
    espedidoweb boolean,
    nropedidocotizacioncliente integer,
    fecharegpedcotiz date,
    idpedido bigint DEFAULT nextval(('"public"."pedido_idpedido_seq"'::text)::regclass) NOT NULL,
    cliente bigint,
    planprocedimientos bigint,
    planrequerimientosmateriaprima bigint,
    planprocesoscalidad bigint,
    prioridad bigint NOT NULL
) WITH OIDS;


--
-- Structure for table planificacioncalidad (OID = 27221) : 
--
CREATE TABLE public.planificacioncalidad (
    idplanificacion bigint DEFAULT nextval(('"public"."planificacioncalidad_idplanificacion_seq"'::text)::regclass) NOT NULL,
    nroplanificacion bigint,
    fechacreacion date,
    observaciones character varying(50),
    fechainicioprevista date,
    fechafinprevista date,
    pedido bigint
) WITH OIDS;
--
-- Structure for table estadopedido (OID = 27225) : 
--
CREATE TABLE public.estadopedido (
    idestado bigint DEFAULT nextval(('"public"."estadopedido_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table factura (OID = 27229) : 
--
CREATE TABLE public.factura (
    idfactura bigint DEFAULT nextval(('"public"."factura_idfactura_seq"'::text)::regclass) NOT NULL,
    nrofactura bigint,
    fechaemision date,
    tipoiva bigint,
    fecharealcobro date,
    formapago bigint,
    fechavencimiento date,
    usuario bigint,
    estado bigint,
    tipofactura character varying(50)
) WITH OIDS;
--
-- Structure for table planificacionproduccion (OID = 27233) : 
--
CREATE TABLE public.planificacionproduccion (
    idplanificacionproduccion bigint DEFAULT nextval(('"public"."planificacionproduccion_idplanificacionproduccion_seq"'::text)::regclass) NOT NULL,
    nroplanificacion bigint,
    fechacreacion date,
    observaciones character varying(50),
    fechainicioprevista date,
    fechafinprevista date,
    pedido bigint
) WITH OIDS;
--
-- Structure for table presupuesto (OID = 27237) : 
--
CREATE TABLE public.presupuesto (
    idpresupuesto bigint DEFAULT nextval(('"public"."presupuesto_idpresupuesto_seq"'::text)::regclass) NOT NULL,
    fechapresupuesto date,
    montototal double precision,
    fechavencimiento date
) WITH OIDS;
--
-- Structure for table plano (OID = 27241) : 
--
CREATE TABLE public.plano (
    idplano bigint DEFAULT nextval(('"public"."plano_idplano_seq"'::text)::regclass) NOT NULL,
    nroplano bigint,
    escala integer,
    pedido bigint,
    imagen bytea[]
) WITH OIDS;
--
-- Structure for table remito (OID = 27248) : 
--
CREATE TABLE public.remito (
    idremito bigint DEFAULT nextval(('"public"."remito_idremito_seq"'::text)::regclass) NOT NULL,
    nroremito bigint,
    fechaemision date,
    pedido bigint,
    estado bigint
) WITH OIDS;
--
-- Structure for table trabajotercerizado (OID = 27252) : 
--
CREATE TABLE public.trabajotercerizado (
    idtrabajo bigint DEFAULT nextval(('"public"."trabajotercerizado_idtrabajo_seq"'::text)::regclass) NOT NULL,
    nrotrabajotercerizado bigint,
    fechapedidocotizacion date,
    fechaentregaestipulada date,
    fechaconfirmaciontrabajo date,
    fechacancelacion date,
    fechaentregareal date,
    fechaenvioaempresa date,
    motivocancelacion character varying(100),
    empresa bigint,
    estado integer,
    pedido bigint
) WITH OIDS;
--
-- Structure for table cliente (OID = 27256) : 
--
CREATE TABLE public.cliente (
    nrocliente bigint,
    idcliente bigint DEFAULT nextval(('"public"."cliente_idcliente_seq"'::text)::regclass) NOT NULL,
    prioridad bigint,
    estado bigint,
    esmoroso boolean,
    usuario bigint,
    razonsocial character varying(50),
    responsable bigint,
    telefono character varying(50),
    celular character varying(50),
    mail character varying(50),
    domicilio bigint,
    fechaalta date,
    fechabaja date,
    cuil character varying(50),
    condicioniva bigint,
    cuit character varying(50)
) WITH OIDS;
--
-- Structure for table tipoiva (OID = 27260) : 
--
CREATE TABLE public.tipoiva (
    idtipoiva bigint DEFAULT nextval(('"public"."tipoiva_idtipoiva_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table formadepago (OID = 27264) : 
--
CREATE TABLE public.formadepago (
    idformapago bigint DEFAULT nextval(('"public"."formadepago_idformapago_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table usuario (OID = 27268) : 
--
CREATE TABLE public.usuario (
    idusuario bigint DEFAULT nextval(('"public"."usuario_idusuario_seq"'::text)::regclass) NOT NULL,
    usuario character varying(50),
    clave character varying(50)
) WITH OIDS;
--
-- Structure for table estadofactura (OID = 27272) : 
--
CREATE TABLE public.estadofactura (
    idestado bigint DEFAULT nextval(('"public"."estadofactura_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table comprobantepago (OID = 27276) : 
--
CREATE TABLE public.comprobantepago (
    idcomprobantepago bigint DEFAULT nextval(('"public"."comprobantepago_idcomprobantepago_seq"'::text)::regclass) NOT NULL,
    nrocomprobantepago bigint,
    fechaemision date,
    monto double precision,
    formadepago bigint,
    usuario bigint,
    factura bigint
) WITH OIDS;
--
-- Structure for table estadotrabajotercerizado (OID = 27280) : 
--
CREATE TABLE public.estadotrabajotercerizado (
    idestado bigint DEFAULT nextval(('"public"."estadotrabajotercerizado_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table empresametalurgica (OID = 27284) : 
--
CREATE TABLE public.empresametalurgica (
    idempresametalurgica bigint DEFAULT nextval(('"public"."empresametalurgica_idempresametalurgica_seq"'::text)::regclass) NOT NULL,
    nroempresametalurgica bigint,
    razonsocial character varying(50),
    responsable bigint,
    telefono character varying(25),
    celular character varying(25),
    mail character varying(50),
    domicilio bigint,
    fechaalta date,
    fechabaja date,
    cuil character varying(20),
    condicioniva bigint,
    cuit character varying(30)
) WITH OIDS;
--
-- Structure for table prioridad (OID = 27288) : 
--
CREATE TABLE public.prioridad (
    idprioridad bigint DEFAULT nextval(('"public"."prioridad_idprioridad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table estadocliente (OID = 27292) : 
--
CREATE TABLE public.estadocliente (
    idestado bigint DEFAULT nextval(('"public"."estadocliente_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table responsable (OID = 27296) : 
--
CREATE TABLE public.responsable (
    idresponsable bigint DEFAULT nextval(('"public"."responsable_idresponsable_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    apellido character varying(50),
    telefono character varying(50),
    email character varying(50),
    domicilio bigint,
    nrodocumento integer,
    tipodocumento bigint,
    fax character varying(50)
) WITH OIDS;
--
-- Structure for table condicioniva (OID = 27300) : 
--
CREATE TABLE public.condicioniva (
    idcondicioniva bigint DEFAULT nextval(('"public"."condicioniva_idcondicioniva_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table domicilio (OID = 27304) : 
--
CREATE TABLE public.domicilio (
    iddomicilio bigint DEFAULT nextval(('"public"."domicilio_iddomicilio_seq"'::text)::regclass) NOT NULL,
    calle character varying(50),
    numerocalle integer,
    piso integer,
    depto character varying(5),
    torre character varying(10),
    barrio bigint
) WITH OIDS;
--
-- Structure for table rotura (OID = 27308) : 
--
CREATE TABLE public.rotura (
    idrotura bigint DEFAULT nextval(('"public"."rotura_idrotura_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table categoria (OID = 27312) : 
--
CREATE TABLE public.categoria (
    idcategoria bigint DEFAULT nextval(('"public"."categoria_idcategoria_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table cargo (OID = 27316) : 
--
CREATE TABLE public.cargo (
    idcargo bigint DEFAULT nextval(('"public"."cargo_idcargo_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table turno (OID = 27320) : 
--
CREATE TABLE public.turno (
    idturno bigint DEFAULT nextval(('"public"."turno_idturno_seq"'::text)::regclass) NOT NULL,
    horainicio time without time zone,
    horafin time without time zone,
    nombre character varying(20),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table marca (OID = 27324) : 
--
CREATE TABLE public.marca (
    idmarca bigint DEFAULT nextval(('"public"."marca_idmarca_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadomaquina (OID = 27328) : 
--
CREATE TABLE public.estadomaquina (
    idestado bigint DEFAULT nextval(('"public"."estadomaquina_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table tipomaquina (OID = 27332) : 
--
CREATE TABLE public.tipomaquina (
    idtipomaquina bigint DEFAULT nextval(('"public"."tipomaquina_idtipomaquina_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecetapaprod (OID = 27336) : 
--
CREATE TABLE public.estadoejecetapaprod (
    idestado bigint DEFAULT nextval(('"public"."estadoejecetapaprod_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table servicio (OID = 27340) : 
--
CREATE TABLE public.servicio (
    idservicio bigint DEFAULT nextval(('"public"."servicio_idservicio_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table tipodocumento (OID = 27344) : 
--
CREATE TABLE public.tipodocumento (
    idtipodocumento bigint DEFAULT nextval(('"public"."tipodocumento_idtipodocumento_seq"'::text)::regclass) NOT NULL,
    tipo character varying(50),
    nombre character varying(50)
) WITH OIDS;
--
-- Structure for table codigodebarra (OID = 27348) : 
--
CREATE TABLE public.codigodebarra (
    idcodigo bigint DEFAULT nextval(('"public"."codigodebarra_idcodigo_seq"'::text)::regclass) NOT NULL,
    descripcion character varying(50),
    codigo character varying(50)
) WITH OIDS;
--
-- Structure for table estadopiezareal (OID = 27352) : 
--
CREATE TABLE public.estadopiezareal (
    idestado bigint DEFAULT nextval(('"public"."estadopiezareal_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecplanifpedido (OID = 27356) : 
--
CREATE TABLE public.estadoejecplanifpedido (
    idestado bigint DEFAULT nextval(('"public"."estadoejecplanifpedido_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table proveedormantenimientomaquina (OID = 27360) : 
--
CREATE TABLE public.proveedormantenimientomaquina (
    idproveedormantenimiento bigint DEFAULT nextval(('"public"."proveedormantenimientomaquina_idproveedormantenimiento_seq"'::text)::regclass) NOT NULL,
    nroproveedor bigint,
    razonsocial character varying(50),
    responsable bigint,
    telefono character varying(50),
    celular character varying(50),
    mail character varying(50),
    domicilio bigint,
    fechaalta date,
    fechabaja date,
    cuil character varying(50),
    condicioniva bigint,
    cuit character varying(50)
) WITH OIDS;
--
-- Structure for table rol (OID = 27364) : 
--
CREATE TABLE public.rol (
    idrol bigint DEFAULT nextval(('"public"."rol_idrol_seq"'::text)::regclass) NOT NULL,
    rol character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table privilegio (OID = 27368) : 
--
CREATE TABLE public.privilegio (
    idprivilegio bigint DEFAULT nextval(('"public"."privilegio_idprivilegio_seq"'::text)::regclass) NOT NULL,
    privilegio character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table accioncalidad (OID = 27372) : 
--
CREATE TABLE public.accioncalidad (
    idaccioncalidad bigint DEFAULT nextval(('"public"."accioncalidad_idaccioncalidad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(250)
) WITH OIDS;
--
-- Structure for table empleado (OID = 27376) : 
--
CREATE TABLE public.empleado (
    idempleado bigint DEFAULT nextval(('"public"."empleado_idempleado_seq"'::text)::regclass) NOT NULL,
    legajo bigint,
    fechaingreso date,
    nombre character varying(50),
    apellido character varying(50),
    telefono character varying(50),
    email character varying(50),
    domicilio bigint,
    nrodocumento integer,
    tipodocumento bigint,
    categoria bigint,
    usuario bigint,
    fechaegreso date,
    motivoegreso character varying(100),
    cargo bigint
) WITH OIDS;
--
-- Structure for table proveedor (OID = 27380) : 
--
CREATE TABLE public.proveedor (
    idproveedor bigint DEFAULT nextval(('"public"."proveedor_idproveedor_seq"'::text)::regclass) NOT NULL,
    nroproveedor bigint,
    razonsocial character varying(50),
    responsable bigint,
    telefono character varying(50),
    celular character varying(50),
    mail character varying(50),
    domicilio bigint,
    fechaalta date,
    fechabaja date,
    cuil character varying(50),
    condicion bigint,
    cuit character varying(50)
) WITH OIDS;
--
-- Structure for table estadocompra (OID = 27384) : 
--
CREATE TABLE public.estadocompra (
    idestado bigint DEFAULT nextval(('"public"."estadocompra_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table sesion (OID = 27388) : 
--
CREATE TABLE public.sesion (
    idsesion bigint DEFAULT nextval(('"public"."sesion_idsesion_seq"'::text)::regclass) NOT NULL,
    fechainicio date,
    fechafin date,
    horainicio time without time zone,
    horafin time without time zone,
    usuario bigint
) WITH OIDS;
--
-- Structure for table materiaprima (OID = 28844) : 
--
CREATE TABLE public.materiaprima (
    idmateriaprima bigint DEFAULT nextval(('"public"."materiaprima_idmateriaprima_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    fechaalta date,
    fechabaja date,
    codbarra bigint,
    stock bigint,
    descripcion character varying(100),
    tipomaterial bigint,
    unidadmedida bigint,
    alto numeric(10,3),
    largo numeric(10,3),
    ancho numeric(10,3),
    codproducto character varying(100),
    precio double precision,
    nromateriaprima bigint
) WITH OIDS;
--
-- Structure for table matriz (OID = 27396) : 
--
CREATE TABLE public.matriz (
    idmatriz bigint DEFAULT nextval(('"public"."matriz_idmatriz_seq"'::text)::regclass) NOT NULL,
    codmatriz bigint,
    nombre character varying(50),
    descripcion character varying(50),
    observaciones character varying(100),
    fechacreacion date,
    materiaprima bigint,
    tipomaterial bigint
) WITH OIDS;
--
-- Structure for table pieza (OID = 27400) : 
--
CREATE TABLE public.pieza (
    idpieza bigint DEFAULT nextval(('"public"."pieza_idpieza_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    tipomaterial bigint,
    materiaprima bigint,
    matriz bigint,
    alto numeric(10,3),
    ancho numeric(10,3),
    largo numeric(10,3)
) WITH OIDS;
--
-- Structure for table producto (OID = 27404) : 
--
CREATE TABLE public.producto (
    idproducto bigint DEFAULT nextval(('"public"."producto_idproducto_seq"'::text)::regclass) NOT NULL,
    nroproducto bigint,
    nombre character varying(50),
    preciounitario double precision,
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecplancalidad (OID = 27408) : 
--
CREATE TABLE public.estadoejecplancalidad (
    idestado bigint DEFAULT nextval(('"public"."estadoejecplancalidad_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table mantenimientopreventivo (OID = 27412) : 
--
CREATE TABLE public.mantenimientopreventivo (
    idmantenimientopreventivo bigint DEFAULT nextval(('"public"."mantenimientopreventivo_idmantenimientopreventivo_seq"'::text)::regclass) NOT NULL,
    fechamantenimientoprevisto date,
    fechaenviomantenimiento date,
    horaenviomantenimiento time without time zone,
    periodo character varying(50),
    nromantenimietno bigint,
    fechafinmantenimientoreal date,
    proveedormantenimiento bigint,
    maquina bigint
) WITH OIDS;
--
-- Structure for table mantenimientocorrectivo (OID = 27416) : 
--
CREATE TABLE public.mantenimientocorrectivo (
    idmantenimientocorrectivo bigint DEFAULT nextval(('"public"."mantenimientocorrectivo_idmantenimientocorrectivo_seq"'::text)::regclass) NOT NULL,
    fechaenviomantenimiento date,
    horaenviomantenimiento time without time zone,
    nromantenimientocorrectivo bigint,
    empleado bigint,
    nrocomprobantearreglo bigint,
    proveedormantenimiento bigint,
    fechafinmantenimientoreal date,
    maquina bigint
) WITH OIDS;
--
-- Structure for table maquina (OID = 27420) : 
--
CREATE TABLE public.maquina (
    idmaquina bigint DEFAULT nextval(('"public"."maquina_idmaquina_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    marca bigint,
    descripcion character varying(100),
    estado bigint,
    tipomaquina bigint
) WITH OIDS;
--
-- Structure for table etapadeproduccion (OID = 27424) : 
--
CREATE TABLE public.etapadeproduccion (
    idetapaproduccion bigint DEFAULT nextval(('"public"."etapadeproduccion_idetapaproduccion_seq"'::text)::regclass) NOT NULL,
    nroetapaproduccion bigint,
    nombre character varying(50),
    horasmaquina time without time zone,
    horashombre time without time zone,
    maquina bigint,
    duracionestimada time without time zone,
    fechacreacion date,
    unidaddemedida bigint
) WITH OIDS;
--
-- Structure for table piezareal (OID = 27428) : 
--
CREATE TABLE public.piezareal (
    idpiezareal bigint DEFAULT nextval(('"public"."piezareal_idpiezareal_seq"'::text)::regclass) NOT NULL,
    idpieza bigint NOT NULL,
    estado bigint,
    nropieza integer
) WITH OIDS;
--
-- Structure for table ejecucionplanificacionproduccion (OID = 27432) : 
--
CREATE TABLE public.ejecucionplanificacionproduccion (
    idejecucion bigint DEFAULT nextval(('"public"."ejecucionplanificacionproduccion_idejecucion_seq"'::text)::regclass) NOT NULL,
    idplanificacionproduccion bigint NOT NULL,
    fechainicio date,
    fechafin date,
    horainicio time without time zone,
    horafin time without time zone,
    estado bigint
) WITH OIDS;
--
-- Structure for table procesocalidad (OID = 27436) : 
--
CREATE TABLE public.procesocalidad (
    idprocesocalidad bigint DEFAULT nextval(('"public"."procesocalidad_idprocesocalidad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    nroproceso bigint,
    especificacion character varying(100),
    tolerancia character varying(100),
    descripcion character varying(100),
    duracionestimada time without time zone,
    fechacreacion date,
    herramienta character varying(50),
    accioncalidad bigint
) WITH OIDS;
--
-- Structure for table ejecucionplanificacioncalidad (OID = 27440) : 
--
CREATE TABLE public.ejecucionplanificacioncalidad (
    idejecucion bigint DEFAULT nextval(('"public"."ejecucionplanificacioncalidad_idejecucion_seq"'::text)::regclass) NOT NULL,
    idplanificacioncalidad bigint NOT NULL,
    fechainicio date,
    fechafin date,
    horainicio time without time zone,
    horafin time without time zone,
    estado bigint
) WITH OIDS;
--
-- Structure for table ejecucionprocesocalidad (OID = 27444) : 
--
CREATE TABLE public.ejecucionprocesocalidad (
    idejecucion bigint DEFAULT nextval(('"public"."ejecucionprocesocalidad_idejecucion_seq"'::text)::regclass) NOT NULL,
    idprocesocalidad bigint NOT NULL,
    duracionreal time without time zone,
    resultado character varying(100),
    estado bigint,
    nroejecucion bigint
) WITH OIDS;
--
-- Structure for table ejecucionetapaproduccion (OID = 27448) : 
--
CREATE TABLE public.ejecucionetapaproduccion (
    idejecucion bigint DEFAULT nextval(('"public"."ejecucionetapaproduccion_idejecucion_seq"'::text)::regclass) NOT NULL,
    idetapaproduccion bigint NOT NULL,
    nombre character varying(50),
    totalhorasmaquina time without time zone,
    totalhorashombre time without time zone,
    empleado bigint,
    fechainicio date,
    horainicio time without time zone,
    fechafin date,
    horafin time without time zone,
    observaciones character varying(100),
    estado bigint,
    nroejecucion bigint NOT NULL
) WITH OIDS;
--
-- Structure for table compra (OID = 27452) : 
--
CREATE TABLE public.compra (
    idcompra bigint DEFAULT nextval(('"public"."compra_idcompra_seq"'::text)::regclass) NOT NULL,
    nrocompra bigint,
    fechacompra date,
    vigencia date,
    documentoremito bigint,
    preciototal double precision,
    estado bigint,
    motivo character varying(100),
    proveedor bigint
) WITH OIDS;
--
-- Structure for table asistencia (OID = 27456) : 
--
CREATE TABLE public.asistencia (
    empleado bigint NOT NULL,
    fechaingreso bigint NOT NULL,
    horaingreso time without time zone NOT NULL,
    horaegreso time without time zone,
    observaciones character varying(100)
) WITH OIDS;
--
-- Structure for table detallemantenimientocorrectivo (OID = 27459) : 
--
CREATE TABLE public.detallemantenimientocorrectivo (
    idmantenimientocorrectivo bigint DEFAULT nextval(('"public"."detallemantenimientocorrectivo_idmantenimientocorrectivo_seq"'::text)::regclass) NOT NULL,
    iddetalle bigint NOT NULL,
    duracion time without time zone,
    rotura bigint,
    motivorotura character varying(100)
) WITH OIDS;
--
-- Structure for table detallemantenimientopreventivo (OID = 27463) : 
--
CREATE TABLE public.detallemantenimientopreventivo (
    idmantenimientopreventivo bigint DEFAULT nextval(('"public"."detallemantenimientopreventivo_idmantenimientopreventivo_seq"'::text)::regclass) NOT NULL,
    iddetalle bigint NOT NULL,
    duracion time without time zone,
    servicio bigint,
    observaciones character varying(100)
) WITH OIDS;
--
-- Structure for table detalleejecucionplanificacion (OID = 27467) : 
--
CREATE TABLE public.detalleejecucionplanificacion (
    iddetalle bigint DEFAULT nextval(('"public"."detalleejecucionplanificacion_iddetalle_seq"'::text)::regclass) NOT NULL,
    idejecucionplanificacionproduccion bigint NOT NULL,
    pieza bigint,
    ejecucionetapa bigint NOT NULL,
    idplanificacionproduccion bigint NOT NULL,
    idetapaproduccion bigint,
    piezareal bigint
) WITH OIDS;
--
-- Structure for table detalleejecucionplanificacioncalidad (OID = 27471) : 
--
CREATE TABLE public.detalleejecucionplanificacioncalidad (
    iddetalle bigint DEFAULT nextval(('"public"."detalleejecucionplanificacioncalidad_iddetalle_seq"'::text)::regclass) NOT NULL,
    idejecucionplanificacioncalidad bigint NOT NULL,
    idplanificacioncalidad bigint NOT NULL,
    ejecucionprocesocalidad bigint,
    idprocesocalidad bigint,
    pieza bigint,
    piezareal bigint
) WITH OIDS;
--
-- Structure for table detallaplanificacionproduccion (OID = 27475) : 
--
CREATE TABLE public.detallaplanificacionproduccion (
    iddetalle bigint DEFAULT nextval(('"public"."detallaplanificacionproduccion_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanificacionproduccion bigint NOT NULL,
    iddetalleejecucionplanificacion bigint,
    idejecucionplanificacionproduccion bigint,
    pieza bigint,
    empleado bigint,
    fechainicio date,
    horainicio time(0) without time zone,
    fechafin date,
    horafin time(0) without time zone
) WITH OIDS;
--
-- Structure for table detalleproducto (OID = 27479) : 
--
CREATE TABLE public.detalleproducto (
    iddetalle bigint DEFAULT nextval(('"public"."detalleproducto_iddetalle_seq"'::text)::regclass) NOT NULL,
    idproducto bigint NOT NULL,
    cantidadpiezas integer,
    descripcion character varying(50),
    pieza bigint NOT NULL
) WITH OIDS;
--
-- Structure for table detallepedido (OID = 27483) : 
--
CREATE TABLE public.detallepedido (
    iddetalle bigint DEFAULT nextval(('"public"."detallepedido_iddetalle_seq"'::text)::regclass) NOT NULL,
    idpedido bigint NOT NULL,
    precio double precision,
    cantidad integer,
    producto bigint
) WITH OIDS;
--
-- Structure for table detalletrabajotercerizado (OID = 27487) : 
--
CREATE TABLE public.detalletrabajotercerizado (
    iddetalle bigint DEFAULT nextval(('"public"."detalletrabajotercerizado_iddetalle_seq"'::text)::regclass) NOT NULL,
    idtrabajotercerizado bigint NOT NULL,
    montoparcial double precision,
    cantidad integer,
    descripcion character varying(50),
    fechaenvioreal date,
    fechaentregareal date,
    pieza bigint,
    proceso bigint,
    estado bigint
) WITH OIDS;
--
-- Structure for table detalleplanificacioncalidad (OID = 27491) : 
--
CREATE TABLE public.detalleplanificacioncalidad (
    iddetalle bigint DEFAULT nextval(('"public"."detalleplanificacioncalidad_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanificacioncalidad bigint NOT NULL,
    iddetalleejecucionplanificacioncalidad bigint,
    idejecucionplanificacioncalidad bigint,
    fechainicioplan date,
    fechafinplan date,
    horainicioplan time without time zone,
    horafinplan time without time zone,
    procesocalidad bigint,
    pieza bigint
) WITH OIDS;
--
-- Structure for table detallecompra (OID = 27495) : 
--
CREATE TABLE public.detallecompra (
    iddetalle bigint DEFAULT nextval(('"public"."detallecompra_iddetalle_seq"'::text)::regclass) NOT NULL,
    idcompra bigint NOT NULL,
    cantidad integer,
    materiaprima bigint,
    preciohistorico double precision,
    fecharecepcionparcial date,
    estado bigint
) WITH OIDS;
--
-- Structure for table detalleremito (OID = 27499) : 
--
CREATE TABLE public.detalleremito (
    iddetalle bigint DEFAULT nextval(('"public"."detalleremito_iddetalle_seq"'::text)::regclass) NOT NULL,
    idremito bigint NOT NULL,
    cantidad integer,
    descripcion character varying(50),
    producto bigint
) WITH OIDS;
--
-- Structure for table tiporeclamo (OID = 27503) : 
--
CREATE TABLE public.tiporeclamo (
    idtiporeclamo bigint DEFAULT nextval(('"public"."tiporeclamo_idtiporeclamo_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table reclamoempresametalurgica (OID = 27507) : 
--
CREATE TABLE public.reclamoempresametalurgica (
    idreclamo bigint DEFAULT nextval(('"public"."reclamoempresametalurgica_idreclamo_seq"'::text)::regclass) NOT NULL,
    nroreclamo bigint,
    tiporeclamo bigint,
    motivo character varying(50),
    fechareclamo date,
    trabajotercerizado bigint
) WITH OIDS;
--
-- Structure for table reclamoproveedor (OID = 27511) : 
--
CREATE TABLE public.reclamoproveedor (
    idreclamo bigint DEFAULT nextval(('"public"."reclamoproveedor_idreclamo_seq"'::text)::regclass) NOT NULL,
    nroreclamo bigint,
    tiporeclamo bigint,
    motivo character varying(50),
    fechareclamo date,
    compra bigint
) WITH OIDS;
--
-- Structure for table reclamocliente (OID = 27515) : 
--
CREATE TABLE public.reclamocliente (
    idreclamo bigint DEFAULT nextval(('"public"."reclamocliente_idreclamo_seq"'::text)::regclass) NOT NULL,
    nroreclamo bigint,
    tiporeclamo bigint,
    motivo character varying(100),
    fechareclamo date,
    cliente bigint
) WITH OIDS;
--
-- Structure for table detallereclamocliente (OID = 27519) : 
--
CREATE TABLE public.detallereclamocliente (
    iddetalle bigint DEFAULT nextval(('"public"."detallereclamocliente_iddetalle_seq"'::text)::regclass) NOT NULL,
    idreclamo bigint NOT NULL,
    cantidad integer,
    descripcion character varying(50),
    motivo character varying(50),
    producto bigint
) WITH OIDS;
--
-- Structure for table detallereclamoproveedor (OID = 27523) : 
--
CREATE TABLE public.detallereclamoproveedor (
    iddetalle bigint DEFAULT nextval(('"public"."detallereclamoproveedor_iddetalle_seq"'::text)::regclass) NOT NULL,
    idreclamo bigint NOT NULL,
    cantidad integer,
    descripcion character varying(50),
    motivo character varying(50),
    iddetallecompra bigint,
    fechaegreso date,
    idcompra bigint
) WITH OIDS;
--
-- Structure for table detallereclamoempresametalurgica (OID = 27527) : 
--
CREATE TABLE public.detallereclamoempresametalurgica (
    iddetalle bigint DEFAULT nextval(('"public"."detallereclamoempresametalurgica_iddetalle_seq"'::text)::regclass) NOT NULL,
    idreclamo bigint NOT NULL,
    cantidad integer,
    descripcion character varying(50),
    motivo character varying(50),
    pieza bigint,
    fechaegreso date
) WITH OIDS;
--
-- Structure for table proveedorxmateriaprima (OID = 27531) : 
--
CREATE TABLE public.proveedorxmateriaprima (
    idproveedor bigint NOT NULL,
    idmateriaprima bigint NOT NULL,
    precio double precision
) WITH OIDS;
--
-- Structure for table maquinaxejecucionetapaproduccion (OID = 27534) : 
--
CREATE TABLE public.maquinaxejecucionetapaproduccion (
    idejecucionetapaproduccion bigint NOT NULL,
    idetapaproduccion bigint NOT NULL,
    idmaquina bigint NOT NULL,
    horasmaquina time without time zone,
    horashombre time without time zone
) WITH OIDS;
--
-- Structure for table maquinaxprocesocalidad (OID = 27537) : 
--
CREATE TABLE public.maquinaxprocesocalidad (
    idprocesocalidad bigint NOT NULL,
    idmaquina bigint NOT NULL,
    duracion time without time zone,
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table piezaxetapadeproduccion (OID = 27540) : 
--
CREATE TABLE public.piezaxetapadeproduccion (
    idpieza bigint NOT NULL,
    idetapaproduccion bigint NOT NULL,
    duracion time without time zone,
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table empleadoxturno (OID = 27543) : 
--
CREATE TABLE public.empleadoxturno (
    idempleado bigint NOT NULL,
    idturno bigint NOT NULL
) WITH OIDS;
--
-- Structure for table provincia (OID = 27546) : 
--
CREATE TABLE public.provincia (
    idprovincia bigint DEFAULT nextval(('"public"."provincia_idprovincia_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50)
) WITH OIDS;
--
-- Structure for table localidad (OID = 27550) : 
--
CREATE TABLE public.localidad (
    idlocalidad bigint DEFAULT nextval(('"public"."localidad_idlocalidad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    provincia bigint
) WITH OIDS;
--
-- Structure for table barrio (OID = 27554) : 
--
CREATE TABLE public.barrio (
    idbarrio bigint DEFAULT nextval(('"public"."barrio_idbarrio_seq"'::text)::regclass) NOT NULL,
    nombre character varying(25),
    codpostal bigint,
    localidad bigint
) WITH OIDS;
--
-- Structure for table usuarioxrol (OID = 27558) : 
--
CREATE TABLE public.usuarioxrol (
    idrol bigint NOT NULL,
    idusuario bigint NOT NULL
) WITH OIDS;
--
-- Structure for table rolxprivilegio (OID = 27561) : 
--
CREATE TABLE public.rolxprivilegio (
    idrol bigint NOT NULL,
    idprivilegio bigint NOT NULL
) WITH OIDS;
--
-- Structure for table planrequerimientosmateriaprima (OID = 27564) : 
--
CREATE TABLE public.planrequerimientosmateriaprima (
    idplanrequerimientosmateriaprima bigint DEFAULT nextval(('"public"."planrequerimientosmateriaprima_idplanrequerimientosmateriaprima_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table planprocedimientos (OID = 27568) : 
--
CREATE TABLE public.planprocedimientos (
    idplanprocedimientos bigint DEFAULT nextval(('"public"."planprocedimientos_idplanprocedimientos_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table planprocesoscalidad (OID = 27572) : 
--
CREATE TABLE public.planprocesoscalidad (
    idplanprocesoscalidad bigint DEFAULT nextval(('"public"."planprocesoscalidad_idplanprocesoscalidad_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table detallerequerimientosmateriaprima (OID = 27576) : 
--
CREATE TABLE public.detallerequerimientosmateriaprima (
    iddetalle bigint DEFAULT nextval(('"public"."detallerequerimientosmateriaprima_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanrequerimientosmateriaprima bigint NOT NULL,
    cantidadmateriaprima integer,
    idpieza bigint,
    idmateriaprima bigint
) WITH OIDS;
--
-- Structure for table detalleplanprocedimientos (OID = 27580) : 
--
CREATE TABLE public.detalleplanprocedimientos (
    iddetalle bigint DEFAULT nextval(('"public"."detalleplanprocedimientos_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanpprocedimientos bigint NOT NULL,
    idpieza bigint,
    idetapaproduccion bigint,
    duracionestimada time without time zone
) WITH OIDS;
--
-- Structure for table detalleplanprocesoscalidad (OID = 27584) : 
--
CREATE TABLE public.detalleplanprocesoscalidad (
    iddetalle bigint DEFAULT nextval(('"public"."detalleplanprocesoscalidad_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanprocesoscalidad bigint NOT NULL,
    idpieza bigint,
    idprocesocalidad bigint,
    duracionestimada time without time zone
) WITH OIDS;
--
-- Structure for table pedidomatriz (OID = 27588) : 
--
CREATE TABLE public.pedidomatriz (
    idpedidomatriz bigint DEFAULT nextval(('"public"."pedidomatriz_idpedidomatriz_seq"'::text)::regclass) NOT NULL,
    nropedidomatriz bigint,
    fechapedidomatriz date,
    idmatriz bigint,
    observaciones character varying(100)
) WITH OIDS;
--
-- Structure for table estadodetallecompra (OID = 27592) : 
--
CREATE TABLE public.estadodetallecompra (
    idestado bigint DEFAULT nextval(('"public"."estadodetallecompra_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadodetalletrabajotercerizado (OID = 27596) : 
--
CREATE TABLE public.estadodetalletrabajotercerizado (
    idestado bigint DEFAULT nextval(('"public"."estadodetalletrabajotercerizado_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecucionprocesocalidad (OID = 27600) : 
--
CREATE TABLE public.estadoejecucionprocesocalidad (
    idestado bigint DEFAULT nextval(('"public"."estadoejecucionprocesocalidad_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table detalleproductoreal (OID = 27604) : 
--
CREATE TABLE public.detalleproductoreal (
    iddetalle bigint DEFAULT nextval(('"public"."detalleproductoreal_iddetalle_seq"'::text)::regclass) NOT NULL,
    idproductoreal bigint NOT NULL,
    idpieza bigint,
    "cantidadPiezas" integer,
    descripcion character varying(50),
    idpiezareal bigint
) WITH OIDS;
--
-- Structure for table productoreal (OID = 27608) : 
--
CREATE TABLE public.productoreal (
    idproductoreal bigint DEFAULT nextval(('"public"."productoreal_idproductoreal_seq"'::text)::regclass) NOT NULL,
    nroproducto bigint,
    fechaterminacion date,
    fechainicioproduccion date,
    idpieza bigint,
    idpedido bigint,
    codigobarra bigint,
    estado bigint,
    idpiezareal bigint
) WITH OIDS;
--
-- Structure for table estadoproductoreal (OID = 27612) : 
--
CREATE TABLE public.estadoproductoreal (
    idestado bigint DEFAULT nextval(('"public"."estadoproductoreal_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table detallefactura (OID = 27616) : 
--
CREATE TABLE public.detallefactura (
    iddetalle bigint DEFAULT nextval(('"public"."detallefactura_iddetalle_seq"'::text)::regclass) NOT NULL,
    idfactura bigint NOT NULL,
    idpedido bigint,
    montoparcial double precision,
    cantidad integer,
    iddetallepedido bigint
) WITH OIDS;
--
-- Structure for table detallepresupuesto (OID = 29072) : 
--
CREATE TABLE public.detallepresupuesto (
    iddetalle bigint DEFAULT nextval(('"public"."detallepresupuesto_iddetalle_seq"'::text)::regclass) NOT NULL,
    idpresupuesto bigint NOT NULL,
    iddetallepedido bigint,
    idproducto bigint,
    cantidad integer,
    precio double precision
) WITH OIDS;

--
-- Structure for table detallepiezapresupuesto (OID = 38728) : 
--
CREATE TABLE public.detallepiezapresupuesto (
    duracionpiezaxetapa time(6) without time zone,
    iddetalle bigint DEFAULT nextval(('"public"."detallepiezapresupuesto_iddetalle_seq"'::text)::regclass) NOT NULL,
    idetapa bigint,
    iddetalleproductopresupuesto bigint
) WITH OIDS;
ALTER TABLE ONLY public.detallepiezapresupuesto ALTER COLUMN duracionpiezaxetapa SET STATISTICS 0;
ALTER TABLE ONLY public.detallepiezapresupuesto ALTER COLUMN iddetalle SET STATISTICS 0;

--
-- Definition for sequence detalleproductopresupuesto_iddetalle_seq (OID = 38740) : 
--
CREATE SEQUENCE public.detalleproductopresupuesto_iddetalle_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detalleproductopresupuesto (OID = 38742) : 
--
CREATE TABLE public.detalleproductopresupuesto (
    iddetalle bigint DEFAULT nextval('detalleproductopresupuesto_iddetalle_seq'::regclass) NOT NULL,
    iddetallepresupuesto bigint,
    idpieza bigint,
    idmateriaprima bigint,
    cantmateriaprima integer,
    cantpiezas integer,
    preciomateriaprima double precision
) WITH OIDS;
ALTER TABLE ONLY public.detalleproductopresupuesto ALTER COLUMN iddetalle SET STATISTICS 0;


--
-- Structure for table detallepiezacalidadpresupuesto (OID = 38928) : 
--
CREATE TABLE public.detallepiezacalidadpresupuesto (
    iddetalle bigint DEFAULT nextval(('"public"."detallepiezacalidadpresupuesto_iddetalle_seq"'::text)::regclass) NOT NULL,
    cantprocesocalidad integer,
    duracionxpieza time without time zone,
    idprocesocalidad bigint,
    iddetalleproductopresupuesto bigint
) WITH OIDS;

--
-- Structure for table estadoremito (OID = 27624) : 
--
CREATE TABLE public.estadoremito (
    idestado bigint DEFAULT nextval(('"public"."estadoremito_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table tipomaterial (OID = 27628) : 
--
CREATE TABLE public.tipomaterial (
    idtipomaterial bigint DEFAULT nextval(('"public"."tipomaterial_idtipomaterial_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;


--
-- Definition for sequence prueba_id_seq (OID = 27632) : 
--
CREATE SEQUENCE public.prueba_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table prueba (OID = 27634) : 
--
CREATE TABLE public.prueba (
    id bigint DEFAULT nextval('prueba_id_seq'::regclass) NOT NULL,
    valor character varying(20)
) WITH OIDS;
ALTER TABLE ONLY public.prueba ALTER COLUMN id SET STATISTICS 0;
ALTER TABLE ONLY public.prueba ALTER COLUMN valor SET STATISTICS 0;
--
-- Definition for sequence usuario_idusuario_seq (OID = 27638) : 
--
CREATE SEQUENCE public.usuario_idusuario_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipomaterial_idtipomaterial_seq (OID = 27640) : 
--
CREATE SEQUENCE public.tipomaterial_idtipomaterial_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence accioncalidad_idaccioncalidad_seq (OID = 27642) : 
--
CREATE SEQUENCE public.accioncalidad_idaccioncalidad_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence barrio_idbarrio_seq (OID = 27644) : 
--
CREATE SEQUENCE public.barrio_idbarrio_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence cargo_idcargo_seq (OID = 27646) : 
--
CREATE SEQUENCE public.cargo_idcargo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence categoria_idcategoria_seq (OID = 27648) : 
--
CREATE SEQUENCE public.categoria_idcategoria_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence cliente_idcliente_seq (OID = 27650) : 
--
CREATE SEQUENCE public.cliente_idcliente_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence codigodebarra_idcodigo_seq (OID = 27652) : 
--
CREATE SEQUENCE public.codigodebarra_idcodigo_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence compra_idcompra_seq (OID = 27654) : 
--
CREATE SEQUENCE public.compra_idcompra_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence comprobantepago_idcomprobantepago_seq (OID = 27656) : 
--
CREATE SEQUENCE public.comprobantepago_idcomprobantepago_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence condicioniva_idcondicioniva_seq (OID = 27658) : 
--
CREATE SEQUENCE public.condicioniva_idcondicioniva_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallaplanificacionproduccion_iddetalle_seq (OID = 27660) : 
--
CREATE SEQUENCE public.detallaplanificacionproduccion_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallecompra_iddetalle_seq (OID = 27662) : 
--
CREATE SEQUENCE public.detallecompra_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleejecucionplanificacion_iddetalle_seq (OID = 27664) : 
--
CREATE SEQUENCE public.detalleejecucionplanificacion_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleejecucionplanificacioncalidad_iddetalle_seq (OID = 27666) : 
--
CREATE SEQUENCE public.detalleejecucionplanificacioncalidad_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallefactura_iddetalle_seq (OID = 27668) : 
--
CREATE SEQUENCE public.detallefactura_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallemantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 27670) : 
--
CREATE SEQUENCE public.detallemantenimientocorrectivo_idmantenimientocorrectivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallemantenimientopreventivo_idmantenimientopreventivo_seq (OID = 27672) : 
--
CREATE SEQUENCE public.detallemantenimientopreventivo_idmantenimientopreventivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallepedido_iddetalle_seq (OID = 27674) : 
--
CREATE SEQUENCE public.detallepedido_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanificacioncalidad_iddetalle_seq (OID = 27676) : 
--
CREATE SEQUENCE public.detalleplanificacioncalidad_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanprocedimientos_iddetalle_seq (OID = 27678) : 
--
CREATE SEQUENCE public.detalleplanprocedimientos_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanprocesoscalidad_iddetalle_seq (OID = 27680) : 
--
CREATE SEQUENCE public.detalleplanprocesoscalidad_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
    
--
-- Definition for sequence detallepiezapresupuesto_iddetalle_seq (OID = 38726) : 
--
CREATE SEQUENCE public.detallepiezapresupuesto_iddetalle_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallepiezacalidadpresupuesto_iddetalle_seq (OID = 38926) : 
--
CREATE SEQUENCE public.detallepiezacalidadpresupuesto_iddetalle_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

--
-- Definition for sequence detallepresupuesto_iddetalle_seq (OID = 27682) : 
--
CREATE SEQUENCE public.detallepresupuesto_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleproducto_iddetalle_seq (OID = 27684) : 
--
CREATE SEQUENCE public.detalleproducto_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleproductoreal_iddetalle_seq (OID = 27686) : 
--
CREATE SEQUENCE public.detalleproductoreal_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamocliente_iddetalle_seq (OID = 27688) : 
--
CREATE SEQUENCE public.detallereclamocliente_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamoempresametalurgica_iddetalle_seq (OID = 27690) : 
--
CREATE SEQUENCE public.detallereclamoempresametalurgica_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamoproveedor_iddetalle_seq (OID = 27692) : 
--
CREATE SEQUENCE public.detallereclamoproveedor_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleremito_iddetalle_seq (OID = 27694) : 
--
CREATE SEQUENCE public.detalleremito_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallerequerimientosmateriaprima_iddetalle_seq (OID = 27696) : 
--
CREATE SEQUENCE public.detallerequerimientosmateriaprima_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalletrabajotercerizado_iddetalle_seq (OID = 27698) : 
--
CREATE SEQUENCE public.detalletrabajotercerizado_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence domicilio_iddomicilio_seq (OID = 27700) : 
--
CREATE SEQUENCE public.domicilio_iddomicilio_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionetapaproduccion_idejecucion_seq (OID = 27702) : 
--
CREATE SEQUENCE public.ejecucionetapaproduccion_idejecucion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionplanificacioncalidad_idejecucion_seq (OID = 27704) : 
--
CREATE SEQUENCE public.ejecucionplanificacioncalidad_idejecucion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionplanificacionproduccion_idejecucion_seq (OID = 27706) : 
--
CREATE SEQUENCE public.ejecucionplanificacionproduccion_idejecucion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionprocesocalidad_idejecucion_seq (OID = 27708) : 
--
CREATE SEQUENCE public.ejecucionprocesocalidad_idejecucion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence empleado_idempleado_seq (OID = 27710) : 
--
CREATE SEQUENCE public.empleado_idempleado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence empresametalurgica_idempresametalurgica_seq (OID = 27712) : 
--
CREATE SEQUENCE public.empresametalurgica_idempresametalurgica_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadocliente_idestado_seq (OID = 27714) : 
--
CREATE SEQUENCE public.estadocliente_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadocompra_idestado_seq (OID = 27716) : 
--
CREATE SEQUENCE public.estadocompra_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadodetallecompra_idestado_seq (OID = 27718) : 
--
CREATE SEQUENCE public.estadodetallecompra_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadodetalletrabajotercerizado_idestado_seq (OID = 27720) : 
--
CREATE SEQUENCE public.estadodetalletrabajotercerizado_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecetapaprod_idestado_seq (OID = 27722) : 
--
CREATE SEQUENCE public.estadoejecetapaprod_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecplancalidad_idestado_seq (OID = 27724) : 
--
CREATE SEQUENCE public.estadoejecplancalidad_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecplanifpedido_idestado_seq (OID = 27726) : 
--
CREATE SEQUENCE public.estadoejecplanifpedido_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecucionprocesocalidad_idestado_seq (OID = 27728) : 
--
CREATE SEQUENCE public.estadoejecucionprocesocalidad_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadofactura_idestado_seq (OID = 27730) : 
--
CREATE SEQUENCE public.estadofactura_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadomaquina_idestado_seq (OID = 27732) : 
--
CREATE SEQUENCE public.estadomaquina_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadopedido_idestado_seq (OID = 27734) : 
--
CREATE SEQUENCE public.estadopedido_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadopiezareal_idestado_seq (OID = 27736) : 
--
CREATE SEQUENCE public.estadopiezareal_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoproductoreal_idestado_seq (OID = 27738) : 
--
CREATE SEQUENCE public.estadoproductoreal_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoremito_idestado_seq (OID = 27740) : 
--
CREATE SEQUENCE public.estadoremito_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadotrabajotercerizado_idestado_seq (OID = 27742) : 
--
CREATE SEQUENCE public.estadotrabajotercerizado_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence etapadeproduccion_idetapaproduccion_seq (OID = 27744) : 
--
CREATE SEQUENCE public.etapadeproduccion_idetapaproduccion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence factura_idfactura_seq (OID = 27746) : 
--
CREATE SEQUENCE public.factura_idfactura_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence formadepago_idformapago_seq (OID = 27748) : 
--
CREATE SEQUENCE public.formadepago_idformapago_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence localidad_idlocalidad_seq (OID = 27750) : 
--
CREATE SEQUENCE public.localidad_idlocalidad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence mantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 27752) : 
--
CREATE SEQUENCE public.mantenimientocorrectivo_idmantenimientocorrectivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence mantenimientopreventivo_idmantenimientopreventivo_seq (OID = 27754) : 
--
CREATE SEQUENCE public.mantenimientopreventivo_idmantenimientopreventivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence maquina_idmaquina_seq (OID = 27756) : 
--
CREATE SEQUENCE public.maquina_idmaquina_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence marca_idmarca_seq (OID = 27758) : 
--
CREATE SEQUENCE public.marca_idmarca_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence materiaprima_idmateriaprima_seq (OID = 27760) : 
--
CREATE SEQUENCE public.materiaprima_idmateriaprima_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence matriz_idmatriz_seq (OID = 27762) : 
--
CREATE SEQUENCE public.matriz_idmatriz_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pedido_idpedido_seq (OID = 27764) : 
--
CREATE SEQUENCE public.pedido_idpedido_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pedidomatriz_idpedidomatriz_seq (OID = 27766) : 
--
CREATE SEQUENCE public.pedidomatriz_idpedidomatriz_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pieza_idpieza_seq (OID = 27768) : 
--
CREATE SEQUENCE public.pieza_idpieza_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence piezareal_idpiezareal_seq (OID = 27770) : 
--
CREATE SEQUENCE public.piezareal_idpiezareal_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planificacioncalidad_idplanificacion_seq (OID = 27772) : 
--
CREATE SEQUENCE public.planificacioncalidad_idplanificacion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planificacionproduccion_idplanificacionproduccion_seq (OID = 27774) : 
--
CREATE SEQUENCE public.planificacionproduccion_idplanificacionproduccion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence plano_idplano_seq (OID = 27776) : 
--
CREATE SEQUENCE public.plano_idplano_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planprocedimientos_idplanprocedimientos_seq (OID = 27778) : 
--
CREATE SEQUENCE public.planprocedimientos_idplanprocedimientos_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planprocesoscalidad_idplanprocesoscalidad_seq (OID = 27780) : 
--
CREATE SEQUENCE public.planprocesoscalidad_idplanprocesoscalidad_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planrequerimientosmateriaprima_idplanrequerimientosmateriaprima (OID = 27782) : 
--
CREATE SEQUENCE public.planrequerimientosmateriaprima_idplanrequerimientosmateriaprima
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence presupuesto_idpresupuesto_seq (OID = 27784) : 
--
CREATE SEQUENCE public.presupuesto_idpresupuesto_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence prioridad_idprioridad_seq (OID = 27786) : 
--
CREATE SEQUENCE public.prioridad_idprioridad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence privilegio_idprivilegio_seq (OID = 27788) : 
--
CREATE SEQUENCE public.privilegio_idprivilegio_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence procesocalidad_idprocesocalidad_seq (OID = 27790) : 
--
CREATE SEQUENCE public.procesocalidad_idprocesocalidad_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence producto_idproducto_seq (OID = 27792) : 
--
CREATE SEQUENCE public.producto_idproducto_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence productoreal_idproductoreal_seq (OID = 27794) : 
--
CREATE SEQUENCE public.productoreal_idproductoreal_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence proveedor_idproveedor_seq (OID = 27796) : 
--
CREATE SEQUENCE public.proveedor_idproveedor_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence proveedormantenimientomaquina_idproveedormantenimiento_seq (OID = 27798) : 
--
CREATE SEQUENCE public.proveedormantenimientomaquina_idproveedormantenimiento_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence provincia_idprovincia_seq (OID = 27800) : 
--
CREATE SEQUENCE public.provincia_idprovincia_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamocliente_idreclamo_seq (OID = 27802) : 
--
CREATE SEQUENCE public.reclamocliente_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamoempresametalurgica_idreclamo_seq (OID = 27804) : 
--
CREATE SEQUENCE public.reclamoempresametalurgica_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamoproveedor_idreclamo_seq (OID = 27806) : 
--
CREATE SEQUENCE public.reclamoproveedor_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence remito_idremito_seq (OID = 27808) : 
--
CREATE SEQUENCE public.remito_idremito_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence responsable_idresponsable_seq (OID = 27810) : 
--
CREATE SEQUENCE public.responsable_idresponsable_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence rol_idrol_seq (OID = 27812) : 
--
CREATE SEQUENCE public.rol_idrol_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence rotura_idrotura_seq (OID = 27814) : 
--
CREATE SEQUENCE public.rotura_idrotura_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence servicio_idservicio_seq (OID = 27816) : 
--
CREATE SEQUENCE public.servicio_idservicio_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence sesion_idsesion_seq (OID = 27818) : 
--
CREATE SEQUENCE public.sesion_idsesion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipodocumento_idtipodocumento_seq (OID = 27820) : 
--
CREATE SEQUENCE public.tipodocumento_idtipodocumento_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipoiva_idtipoiva_seq (OID = 27822) : 
--
CREATE SEQUENCE public.tipoiva_idtipoiva_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipomaquina_idtipomaquina_seq (OID = 27824) : 
--
CREATE SEQUENCE public.tipomaquina_idtipomaquina_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tiporeclamo_idtiporeclamo_seq (OID = 27826) : 
--
CREATE SEQUENCE public.tiporeclamo_idtiporeclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence trabajotercerizado_idtrabajo_seq (OID = 27828) : 
--
CREATE SEQUENCE public.trabajotercerizado_idtrabajo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence turno_idturno_seq (OID = 27830) : 
--
CREATE SEQUENCE public.turno_idturno_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;

--
-- Structure for table unidadmedida (OID = 29302) : 
--
CREATE TABLE public.unidadmedida (
    idunidadmedida bigint DEFAULT nextval(('"public"."unidadmedida_idunidadmedida_seq"'::text)::regclass) NOT NULL,
    nombre character varying(20),
    descripcion character varying(50),
    encm double precision,
    enmm double precision
) WITH OIDS;
ALTER TABLE ONLY public.unidadmedida ALTER COLUMN idunidadmedida SET STATISTICS 0;
ALTER TABLE ONLY public.unidadmedida ALTER COLUMN nombre SET STATISTICS 0;
ALTER TABLE ONLY public.unidadmedida ALTER COLUMN descripcion SET STATISTICS 0;
--
-- Definition for view viewdetallepedidocotizacion (OID = 27836) : 
--
CREATE VIEW public.viewdetallepedidocotizacion AS
SELECT p.nroproducto, p.nombre, p.descripcion, dp.cantidad,
    p.preciounitario AS precio, p.idproducto, dp.iddetalle, ped.idpedido
FROM producto p, pedido ped, detallepedido dp
WHERE ((dp.producto = p.idproducto) AND (ped.idpedido = dp.idpedido))
ORDER BY p.nombre;

--
-- Definition for view viewprocesocalidad (OID = 38958) : 
--
CREATE VIEW public.viewprocesocalidad AS
SELECT pc.nroproceso, pc.nombre AS nombreproceso, pc.duracionestimada,
    pc.herramienta, ac.nombre AS nombreaccioncalidad, pc.idprocesocalidad
FROM procesocalidad pc, accioncalidad ac
WHERE (pc.accioncalidad = ac.idaccioncalidad)
ORDER BY pc.nombre;

--
-- Definition for view viewmateriaprima (OID = 38836) : 
--
CREATE VIEW public.viewmateriaprima AS
SELECT mp.nombre AS nombremateriaprima, mp.descripcion, mp.alto, mp.ancho,
    mp.largo, udm.nombre AS unidadmedida, tm.nombre AS tipomaterial,
    mp.idmateriaprima
FROM materiaprima mp, unidadmedida udm, tipomaterial tm
WHERE ((mp.unidadmedida = udm.idunidadmedida) AND (mp.tipomaterial =
    tm.idtipomaterial))
ORDER BY mp.nombre;


CREATE VIEW public.viewpedidoendetalleprocedimientos AS
SELECT p.nropedido, p.nropedidocotizacioncliente, p.fechapedidocotizacion,
    p.fecharequeridacotizacion, p.fechaentregaestipulada, p.espedidoweb,
    ep.nombre AS estado, pr.nombre AS prioridad, c.razonsocial AS cliente,
    p.idpedido, ep.idestado
FROM pedido p, cliente c, estadopedido ep, prioridad pr
WHERE (((p.cliente = c.idcliente) AND (p.estado = ep.idestado)) AND
    (p.prioridad = pr.idprioridad))
ORDER BY p.nropedido;

--
-- Definition for view viewdetalleproducto (OID = 26785) : 
--
CREATE VIEW public.viewdetalleproducto AS
SELECT pi.nombre AS nombrepieza, dp.descripcion, dp.cantidadpiezas,
    pi.alto,pi.ancho,pi.largo, tm.nombre AS nombretipomaterial, pi.idpieza,
    dp.iddetalle, p.idproducto
FROM producto p, detalleproducto dp, pieza pi, tipomaterial tm
WHERE (((dp.idproducto = p.idproducto) AND (dp.pieza = pi.idpieza)) AND
    (pi.tipomaterial = tm.idtipomaterial))
ORDER BY pi.nombre;

--
-- Definition for function cantpiezasdepedido (OID = 38917) : 
--
SET search_path = public, pg_catalog;
SET check_function_bodies = false;
CREATE FUNCTION public.cantpiezasdepedido (idpedido bigint) RETURNS integer
AS 
$body$
DECLARE
  result INTEGER;
  valor BIGINT;
BEGIN
  valor:=idpedido;
  SELECT sum(dpropre.cantpiezas) INTO result
  FROM pedido p, presupuesto pre, detallepresupuesto dpre, detalleproductopresupuesto dpropre
  WHERE p.idpedido=valor AND p.presupuesto=pre.idpresupuesto AND dpre.idpresupuesto=pre.idpresupuesto
  		AND dpropre.iddetallepresupuesto=dpre.iddetalle;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function cantpiezasdepedido2 (OID = 57234) : 
--
CREATE FUNCTION public.cantpiezasdepedido2 (idpedido bigint) RETURNS integer
AS 
$body$
DECLARE
  result INTEGER;
  valor BIGINT;
BEGIN
	valor:=idpedido;
    SELECT count(dpro.iddetalle) INTO result
    FROM pedido p,detallepedido dp, producto pro, detalleproducto dpro
    WHERE p.idpedido=valor AND p.idpedido=dp.idpedido AND dp.producto=pro.idproducto AND pro.idproducto=dpro.idproducto;
    RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function nvonropresupuesto (OID = 57236) : 
--
CREATE FUNCTION public.nvonropresupuesto () RETURNS bigint
AS 
$body$
DECLARE
  result BIGINT;
BEGIN
  SELECT (max(p.nropresupuesto)+1) INTO result FROM Presupuesto p;
  IF(result IS NULL)THEN
  	result:=1;
  END IF;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function ingresoxpedido (OID = 57296) : 
--
CREATE FUNCTION public.ingresoxpedido (idpedido bigint) RETURNS double precision
AS 
$body$
DECLARE
	result DOUBLE PRECISION;
BEGIN
	result:=0;
	SELECT sum(dpre.cantidad*dpre.precio) INTO result
    FROM pedido ped,presupuesto pre,detallepresupuesto dpre
    WHERE ped.idpedido=idpedido
    		AND ped.presupuesto=pre.idpresupuesto
            AND pre.idpresupuesto=dpre.idpresupuesto;
    RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function nvonrocliente (OID = 57313) : 
--
CREATE FUNCTION public.nvonrocliente () RETURNS bigint
AS 
$body$
DECLARE
  result BIGINT;
BEGIN
  SELECT (max(p.nrocliente)+1) INTO result FROM cliente p;
  IF(result IS NULL)THEN
  	result:=1;
  END IF;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for view viewetapasxpiezapresupuesto (OID = 57241) : 
--
CREATE VIEW public.viewetapasxpiezapresupuesto AS
SELECT pro.nroproducto, pro.nombre AS nombreproducto, dp.cantidad AS
    cantproducto, pi.nombre AS nombrepieza, dpropre.cantpiezas AS
    cantpieza, ep.nroetapaproduccion, ep.nombre AS nombreetapaproduccion,
    dpipre.duracionpiezaxetapa AS duracionetapaxpieza, (((dp.cantidad *
    dpropre.cantpiezas))::double precision *
    (dpipre.duracionpiezaxetapa)::interval) AS duraciontotal,
    p.idpresupuesto, dp.iddetalle AS iddetallepresupuesto,
    dpropre.iddetalle AS iddetalleproductopresupuesto, dpipre.iddetalle AS
    iddetallepiezapresupuesto, pro.idproducto, pi.idpieza, ep.idetapaproduccion
FROM presupuesto p, detallepresupuesto dp, detalleproductopresupuesto
    dpropre, detallepiezapresupuesto dpipre, producto pro, pieza pi,
    etapadeproduccion ep
WHERE ((((((p.idpresupuesto = dp.idpresupuesto) AND (dp.idproducto =
    pro.idproducto)) AND (dp.iddetalle = dpropre.iddetallepresupuesto)) AND
    (dpropre.iddetalle = dpipre.iddetalleproductopresupuesto)) AND
    (dpropre.idpieza = pi.idpieza)) AND (dpipre.idetapa = ep.idetapaproduccion));

--
-- Definition for view viewprocalidadxpiezapresupesto (OID = 57262) : 
--
CREATE VIEW public.viewprocalidadxpiezapresupesto AS
SELECT pro.nroproducto, pro.nombre AS nombreproducto, dp.cantidad AS
    cantproducto, pi.nombre AS nombrepieza, dpropre.cantpiezas AS
    cantpieza, pc.nroproceso AS nroprocesocalidad, pc.nombre AS
    nombreprocesocalidad, dpicalpre.cantprocesocalidad,
    dpicalpre.duracionxpieza AS duracionprocalidadxpieza, ((((dp.cantidad *
    dpropre.cantpiezas) * dpicalpre.cantprocesocalidad))::double precision
    * (dpicalpre.duracionxpieza)::interval) AS duraciontotal,
    p.idpresupuesto, dp.iddetalle AS iddetallepresupuesto,
    dpropre.iddetalle AS iddetalleproductopresupuesto, dpicalpre.iddetalle
    AS iddetallepiezacalidadpresupuesto, pro.idproducto, pi.idpieza,
    pc.idprocesocalidad
FROM presupuesto p, detallepresupuesto dp, detalleproductopresupuesto
    dpropre, detallepiezacalidadpresupuesto dpicalpre, producto pro, pieza
    pi, procesocalidad pc
WHERE ((((((p.idpresupuesto = dp.idpresupuesto) AND (dp.idproducto =
    pro.idproducto)) AND (dp.iddetalle = dpropre.iddetallepresupuesto)) AND
    (dpropre.iddetalle = dpicalpre.iddetalleproductopresupuesto)) AND
    (dpropre.idpieza = pi.idpieza)) AND (dpicalpre.idprocesocalidad =
    pc.idprocesocalidad));

--
-- Definition for view viewmpxpiezapresupuesto (OID = 57275) : 
--
CREATE VIEW public.viewmpxpiezapresupuesto AS
SELECT pro.nroproducto, pro.nombre AS nombreproducto, dp.cantidad AS
    cantproducto, pi.nombre AS nombrepieza, dpropre.cantpiezas AS
    cantpieza, mp.nombre AS nombremateriaprima, dpropre.preciomateriaprima,
    dpropre.cantmateriaprima, (dpropre.cantmateriaprima * dp.cantidad) AS
    canttotal, ((dpropre.preciomateriaprima *
    (dpropre.cantmateriaprima)::double precision) * (dp.cantidad)::double
    precision) AS preciototal, p.idpresupuesto, dp.iddetalle AS
    iddetallepresupuesto, dpropre.iddetalle AS
    iddetalleproductopresupuesto, pro.idproducto, pi.idpieza, mp.idmateriaprima
FROM presupuesto p, detallepresupuesto dp, detalleproductopresupuesto
    dpropre, producto pro, pieza pi, materiaprima mp
WHERE (((((p.idpresupuesto = dp.idpresupuesto) AND (dp.idproducto =
    pro.idproducto)) AND (dp.iddetalle = dpropre.iddetallepresupuesto)) AND
    (dpropre.idpieza = pi.idpieza)) AND (dpropre.idmateriaprima =
    mp.idmateriaprima));

--
-- Definition for view viewetapadeproduccion (OID = 28025) : 
--
CREATE VIEW public.viewetapadeproduccion AS
SELECT ep.nroetapaproduccion AS numero, ep.nombre, ep.idetapaproduccion AS idetapa
FROM etapadeproduccion ep;

--
-- Definition for sequence unidadmedida_idunidadmedida_seq (OID = 27840) : 
--
CREATE SEQUENCE public.unidadmedida_idunidadmedida_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

--
-- Definition for index pedido_nropedido_key (OID = 27966) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_nropedido_key UNIQUE (nropedido);
--
-- Definition for index piezareal_idpieza_key (OID = 27968) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_idpieza_key UNIQUE (idpieza);
--
-- Definition for index ejecucionplanificacionproduccion_idplanificacionproduccion_key (OID = 27970) : 
--
--ALTER TABLE ONLY ejecucionplanificacionproduccion
--    ADD CONSTRAINT ejecucionplanificacionproduccion_idplanificacionproduccion_key UNIQUE (idplanificacionproduccion);
--
-- Definition for index ejecucionplanificacioncalidad_idplanificacioncalidad_key (OID = 27972) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_idplanificacioncalidad_key UNIQUE (idplanificacioncalidad);
--
-- Definition for index ejecucionprocesocalidad_idprocesocalidad_key (OID = 27974) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_idprocesocalidad_key UNIQUE (idprocesocalidad);
--
-- Definition for index ejecucionetapaproduccion_idetapaproduccion_key (OID = 27976) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_idetapaproduccion_key UNIQUE (idetapaproduccion);
--
-- Definition for index asistencia_pkey (OID = 27978) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_pkey PRIMARY KEY (empleado, fechaingreso, horaingreso);
--
-- Definition for index asistencia_empleado_key (OID = 27980) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_empleado_key UNIQUE (empleado);
--
-- Definition for index asistencia_fechaingreso_key (OID = 27982) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_fechaingreso_key UNIQUE (fechaingreso);
--
-- Definition for index asistencia_horaingreso_key (OID = 27984) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_horaingreso_key UNIQUE (horaingreso);
--
-- Definition for index proveedorxmateriaprima_idx (OID = 27986) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_idx PRIMARY KEY (idmateriaprima, idproveedor);
--
-- Definition for index maquinaxejecucionetapaproduccion_idx (OID = 27988) : 
--
ALTER TABLE ONLY maquinaxejecucionetapaproduccion
    ADD CONSTRAINT maquinaxejecucionetapaproduccion_idx PRIMARY KEY (idejecucionetapaproduccion, idetapaproduccion, idmaquina);
--
-- Definition for index maquinaxprocesocalidad_idx (OID = 27990) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT maquinaxprocesocalidad_idx PRIMARY KEY (idprocesocalidad, idmaquina);
--
-- Definition for index piezaxetapadeproduccion_idx (OID = 27992) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT piezaxetapadeproduccion_idx PRIMARY KEY (idpieza, idetapaproduccion);
--
-- Definition for index empleadoxturno_idx (OID = 27994) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_idx PRIMARY KEY (idempleado, idturno);
--
-- Definition for index usuarioxrol_pkey (OID = 27996) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_pkey PRIMARY KEY (idrol, idusuario);
--
-- Definition for index rolxprivilegio_pkey (OID = 27998) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_pkey PRIMARY KEY (idrol, idprivilegio);
--
-- Definition for index prueba_pkey (OID = 28000) : 
--
ALTER TABLE ONLY prueba
    ADD CONSTRAINT prueba_pkey PRIMARY KEY (id);
--
-- Definition for index usuario_pkey (OID = 28002) : 
--
ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (idusuario);
--
-- Definition for index factura_fk2 (OID = 28004) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk2 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index comprobantepago_fk1 (OID = 28009) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk1 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index cliente_fk2 (OID = 28014) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk2 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index empleado_fk3 (OID = 28019) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk3 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index sesion_fk (OID = 28024) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT sesion_fk FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index usuarioxrol_fk1 (OID = 28029) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_fk1 FOREIGN KEY (idusuario) REFERENCES usuario(idusuario);
--
-- Definition for index tipomaterial_pkey (OID = 28034) : 
--
ALTER TABLE ONLY tipomaterial
    ADD CONSTRAINT tipomaterial_pkey PRIMARY KEY (idtipomaterial);
--
-- Definition for index accioncalidad_pkey (OID = 28036) : 
--
ALTER TABLE ONLY accioncalidad
    ADD CONSTRAINT accioncalidad_pkey PRIMARY KEY (idaccioncalidad);
--
-- Definition for index procesocalidad_fk (OID = 28038) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT procesocalidad_fk FOREIGN KEY (accioncalidad) REFERENCES accioncalidad(idaccioncalidad);
--
-- Definition for index barrio_pkey (OID = 28043) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT barrio_pkey PRIMARY KEY (idbarrio);
--
-- Definition for index domicilio_fk (OID = 28045) : 
--
ALTER TABLE ONLY domicilio
    ADD CONSTRAINT domicilio_fk FOREIGN KEY (barrio) REFERENCES barrio(idbarrio);
--
-- Definition for index cargo_pkey (OID = 28050) : 
--
ALTER TABLE ONLY cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (idcargo);
--
-- Definition for index empleado_fk4 (OID = 28052) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk4 FOREIGN KEY (cargo) REFERENCES cargo(idcargo);
--
-- Definition for index categoria_pkey (OID = 28057) : 
--
ALTER TABLE ONLY categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (idcategoria);
--
-- Definition for index empleado_fk2 (OID = 28059) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk2 FOREIGN KEY (categoria) REFERENCES categoria(idcategoria);
--
-- Definition for index cliente_pkey (OID = 28064) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (idcliente);
--
-- Definition for index pedido_fk4 (OID = 28066) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk4 FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index reclamocliente_fk1 (OID = 28071) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_fk1 FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index codigodebarra_pkey (OID = 28076) : 
--
ALTER TABLE ONLY codigodebarra
    ADD CONSTRAINT codigodebarra_pkey PRIMARY KEY (idcodigo);
--
-- Definition for index materiaprima_fk1 (OID = 28078) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk1 FOREIGN KEY (codbarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index productoreal_fk2 (OID = 28083) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk2 FOREIGN KEY (codigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index compra_pkey (OID = 28088) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_pkey PRIMARY KEY (idcompra);
--
-- Definition for index detallecompra_fk (OID = 28090) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk FOREIGN KEY (idcompra) REFERENCES compra(idcompra);
--
-- Definition for index reclamoproveedor_fk1 (OID = 28095) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_fk1 FOREIGN KEY (compra) REFERENCES compra(idcompra);
--
-- Definition for index comprobantepago_pkey (OID = 28100) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_pkey PRIMARY KEY (idcomprobantepago);
--
-- Definition for index condicioniva_pkey (OID = 28102) : 
--
ALTER TABLE ONLY condicioniva
    ADD CONSTRAINT condicioniva_pkey PRIMARY KEY (idcondicioniva);
--
-- Definition for index cliente_fk5 (OID = 28104) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk5 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index empresametalurgica_fk2 (OID = 28109) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk2 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index proveedormantenimientomaquina_fk2 (OID = 28114) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk2 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index proveedor_fk2 (OID = 28119) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk2 FOREIGN KEY (condicion) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index detallaplanificacionproduccion_idx (OID = 28124) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_idx PRIMARY KEY (iddetalle, idplanificacionproduccion);
--
-- Definition for index detallaplanificacionproduccion_iddetalle_key (OID = 28126) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallecompra_idx (OID = 28128) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_idx PRIMARY KEY (iddetalle, idcompra);
--
-- Definition for index detallereclamoproveedor_fk1 (OID = 28130) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_fk1 FOREIGN KEY (iddetallecompra, idcompra) REFERENCES detallecompra(iddetalle, idcompra);
--
-- Definition for index detallecompra_iddetalle_key (OID = 28135) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleejecucionplanificacion_idx (OID = 28137) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_idx PRIMARY KEY (iddetalle, idejecucionplanificacionproduccion, idplanificacionproduccion);
--
-- Definition for index detallaplanificacionproduccion_fk1 (OID = 28139) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk1 FOREIGN KEY (iddetalleejecucionplanificacion, idejecucionplanificacionproduccion, idplanificacionproduccion) REFERENCES detalleejecucionplanificacion(iddetalle, idejecucionplanificacionproduccion, idplanificacionproduccion);
--
-- Definition for index detalleejecucionplanificacion_iddetalle_key (OID = 28144) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleejecucionplanificacioncalidad_idx (OID = 28146) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_idx PRIMARY KEY (iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad);
--
-- Definition for index detalleplanificacioncalidad_fk1 (OID = 28148) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk1 FOREIGN KEY (idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, idejecucionplanificacioncalidad) REFERENCES detalleejecucionplanificacioncalidad(iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad);
--
-- Definition for index detalleejecucionplanificacioncalidad_iddetalle_key (OID = 28153) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallefactura_idx (OID = 28155) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_idx PRIMARY KEY (iddetalle, idfactura);
--
-- Definition for index detallefactura_iddetalle_key (OID = 28157) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallemantenimientocorrectivo_pkey (OID = 28159) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_pkey PRIMARY KEY (idmantenimientocorrectivo, iddetalle);
--
-- Definition for index detallemantenimientocorrectivo_idmantenimientocorrectivo_key (OID = 28161) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_idmantenimientocorrectivo_key UNIQUE (idmantenimientocorrectivo);
--
-- Definition for index detallemantenimientopreventivo_pkey (OID = 28163) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_pkey PRIMARY KEY (idmantenimientopreventivo, iddetalle);
--
-- Definition for index detallemantenimientopreventivo_idmantenimientopreventivo_key (OID = 28165) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_idmantenimientopreventivo_key UNIQUE (idmantenimientopreventivo);
--
-- Definition for index detallepedido_idx (OID = 28167) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_idx PRIMARY KEY (iddetalle, idpedido);
--
-- Definition for index detallefactura_fk2 (OID = 28169) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_fk2 FOREIGN KEY (idpedido, iddetallepedido) REFERENCES detallepedido(iddetalle, idpedido);
--
-- Definition for index detallepresupuesto_fk1 (OID = 28174) : 
--
--ALTER TABLE ONLY detallepresupuesto
--    ADD CONSTRAINT detallepresupuesto_fk1 FOREIGN KEY (iddetallepedido) REFERENCES detallepedido(iddetalle);
--
-- Definition for index detallepedido_iddetalle_key (OID = 28179) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleplanificacioncalidad_idx (OID = 28181) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_idx PRIMARY KEY (iddetalle, idplanificacioncalidad);
--
-- Definition for index detalleplanificacioncalidad_iddetalle_key (OID = 28183) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleplanprocedimientos_idx (OID = 28185) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_idx PRIMARY KEY (iddetalle, idplanpprocedimientos);
--
-- Definition for index detalleplanprocedimientos_iddetalle_key (OID = 28187) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleplanprocesoscalidad_idx (OID = 28189) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_idx PRIMARY KEY (iddetalle, idplanprocesoscalidad);
--
-- Definition for index detalleplanprocesoscalidad_iddetalle_key (OID = 28191) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallepresupuesto_idx (OID = 28193) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_idx PRIMARY KEY (iddetalle, idpresupuesto);
--
-- Definition for index detallepresupuesto_iddetalle_key (OID = 28195) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleproductoreal_idx (OID = 28197) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_idx PRIMARY KEY (iddetalle, idproductoreal);
--
-- Definition for index detalleproductoreal_iddetalle_key (OID = 28199) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamocliente_idx (OID = 28201) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamocliente_iddetalle_key (OID = 28203) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamoempresametalurgica_idx (OID = 28205) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamoempresametalurgica_iddetalle_key (OID = 28207) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamoproveedor_idx (OID = 28209) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamoproveedor_iddetalle_key (OID = 28211) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleremito_idx (OID = 28213) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_idx PRIMARY KEY (iddetalle, idremito);
--
-- Definition for index detalleremito_iddetalle_key (OID = 28215) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallerequerimientosmateriaprima_idx (OID = 28217) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_idx PRIMARY KEY (iddetalle, idplanrequerimientosmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_iddetalle_key (OID = 28219) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalletrabajotercerizado_idx (OID = 28221) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_idx PRIMARY KEY (iddetalle, idtrabajotercerizado);
--
-- Definition for index detalletrabajotercerizado_iddetalle_key (OID = 28223) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index domicilio_pkey (OID = 28225) : 
--
ALTER TABLE ONLY domicilio
    ADD CONSTRAINT domicilio_pkey PRIMARY KEY (iddomicilio);
--
-- Definition for index cliente_fk4 (OID = 28227) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk4 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index empresametalurgica_fk1 (OID = 28232) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index responsable_fk (OID = 28237) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_fk FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index proveedormantenimientomaquina_fk1 (OID = 28242) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index empleado_fk (OID = 28247) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index proveedor_fk1 (OID = 28252) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index ejecucionetapaproduccion_nroejecucion_key (OID = 28257) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_nroejecucion_key UNIQUE (idejecucion);
--
-- Definition for index ejecucionetapaproduccion_pkey (OID = 28259) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_pkey PRIMARY KEY (idejecucion, idetapaproduccion);
--
-- Definition for index detalleejecucionplanificacion_fk1 (OID = 28261) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk1 FOREIGN KEY (ejecucionetapa, idetapaproduccion) REFERENCES ejecucionetapaproduccion(idejecucion, idetapaproduccion);
--
-- Definition for index maquinaxejecucionetapaproduccion_fk (OID = 28266) : 
--
ALTER TABLE ONLY maquinaxejecucionetapaproduccion
    ADD CONSTRAINT maquinaxejecucionetapaproduccion_fk FOREIGN KEY (idejecucionetapaproduccion, idetapaproduccion) REFERENCES ejecucionetapaproduccion(idejecucion, idetapaproduccion);
--
-- Definition for index ejecucionetapaproduccion_nroejecucion_key1 (OID = 28271) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_nroejecucion_key1 UNIQUE (nroejecucion);
--
-- Definition for index ejecucionplanificacioncalidad_idejecucion_key (OID = 28273) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_idejecucion_key UNIQUE (idejecucion);
--
-- Definition for index ejecucionplanificacioncalidad_pkey (OID = 28275) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_pkey PRIMARY KEY (idejecucion, idplanificacioncalidad);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk (OID = 28277) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk FOREIGN KEY (idejecucionplanificacioncalidad, idplanificacioncalidad) REFERENCES ejecucionplanificacioncalidad(idejecucion, idplanificacioncalidad);
--
-- Definition for index ejecucionplanificacionproduccion_idejecucion_key (OID = 28282) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_idejecucion_key UNIQUE (idejecucion);
--
-- Definition for index ejecucionplanificacionproduccion_pkey (OID = 28284) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_pkey PRIMARY KEY (idejecucion, idplanificacionproduccion);
--
-- Definition for index detalleejecucionplanificacion_fk (OID = 28286) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk FOREIGN KEY (idejecucionplanificacionproduccion, idplanificacionproduccion) REFERENCES ejecucionplanificacionproduccion(idejecucion, idplanificacionproduccion);
--
-- Definition for index ejecucionprocesocalidad_nroejecucion_key (OID = 28291) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_nroejecucion_key UNIQUE (idejecucion);
--
-- Definition for index ejecucionprocesocalidad_pkey (OID = 28293) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_pkey PRIMARY KEY (idejecucion, idprocesocalidad);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk1 (OID = 28295) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk1 FOREIGN KEY (ejecucionprocesocalidad, idprocesocalidad) REFERENCES ejecucionprocesocalidad(idejecucion, idprocesocalidad);
--
-- Definition for index empleado_pkey (OID = 28300) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (idempleado);
--
-- Definition for index mantenimientocorrectivo_fk (OID = 28302) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_fk FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index ejecucionetapaproduccion_fk1 (OID = 28307) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk1 FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index asistencia_fk (OID = 28312) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_fk FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index detallaplanificacionproduccion_fk3 (OID = 28317) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk3 FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index empleadoxturno_fk (OID = 28322) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_fk FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index empresametalurgica_pkey (OID = 28327) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_pkey PRIMARY KEY (idempresametalurgica);
--
-- Definition for index trabajotercerizado_fk1 (OID = 28329) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk1 FOREIGN KEY (empresa) REFERENCES empresametalurgica(idempresametalurgica);
--
-- Definition for index estadocliente_pkey (OID = 28334) : 
--
ALTER TABLE ONLY estadocliente
    ADD CONSTRAINT estadocliente_pkey PRIMARY KEY (idestado);
--
-- Definition for index cliente_fk1 (OID = 28336) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk1 FOREIGN KEY (estado) REFERENCES estadocliente(idestado);
--
-- Definition for index estadocompra_pkey (OID = 28341) : 
--
ALTER TABLE ONLY estadocompra
    ADD CONSTRAINT estadocompra_pkey PRIMARY KEY (idestado);
--
-- Definition for index compra_fk (OID = 28343) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_fk FOREIGN KEY (estado) REFERENCES estadocompra(idestado);
--
-- Definition for index estadodetallecompra_pkey (OID = 28348) : 
--
ALTER TABLE ONLY estadodetallecompra
    ADD CONSTRAINT estadodetallecompra_pkey PRIMARY KEY (idestado);
--
-- Definition for index detallecompra_fk2 (OID = 28350) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk2 FOREIGN KEY (estado) REFERENCES estadodetallecompra(idestado);
--
-- Definition for index estadodetalletrabajotercerizado_pkey (OID = 28355) : 
--
ALTER TABLE ONLY estadodetalletrabajotercerizado
    ADD CONSTRAINT estadodetalletrabajotercerizado_pkey PRIMARY KEY (idestado);
--
-- Definition for index detalletrabajotercerizado_fk3 (OID = 28357) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk3 FOREIGN KEY (estado) REFERENCES estadodetalletrabajotercerizado(idestado);
--
-- Definition for index estadoejecetapaprod_pkey (OID = 28362) : 
--
ALTER TABLE ONLY estadoejecetapaprod
    ADD CONSTRAINT estadoejecetapaprod_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionetapaproduccion_fk2 (OID = 28364) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk2 FOREIGN KEY (estado) REFERENCES estadoejecetapaprod(idestado);
--
-- Definition for index estadoejecplancalidad_pkey (OID = 28369) : 
--
ALTER TABLE ONLY estadoejecplancalidad
    ADD CONSTRAINT estadoejecplancalidad_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionplanificacioncalidad_fk1 (OID = 28371) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_fk1 FOREIGN KEY (estado) REFERENCES estadoejecplancalidad(idestado);
--
-- Definition for index estadoejecplanifpedido_pkey (OID = 28376) : 
--
ALTER TABLE ONLY estadoejecplanifpedido
    ADD CONSTRAINT estadoejecplanifpedido_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionplanificacionproduccion_fk1 (OID = 28378) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_fk1 FOREIGN KEY (estado) REFERENCES estadoejecplanifpedido(idestado);
--
-- Definition for index estadoejecucionprocesocalidad_pkey (OID = 28383) : 
--
ALTER TABLE ONLY estadoejecucionprocesocalidad
    ADD CONSTRAINT estadoejecucionprocesocalidad_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionprocesocalidad_fk1 (OID = 28385) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_fk1 FOREIGN KEY (estado) REFERENCES estadoejecucionprocesocalidad(idestado);
--
-- Definition for index estadofactura_pkey (OID = 28390) : 
--
ALTER TABLE ONLY estadofactura
    ADD CONSTRAINT estadofactura_pkey PRIMARY KEY (idestado);
--
-- Definition for index factura_fk3 (OID = 28392) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk3 FOREIGN KEY (estado) REFERENCES estadofactura(idestado);
--
-- Definition for index estadomaquina_pkey (OID = 28397) : 
--
ALTER TABLE ONLY estadomaquina
    ADD CONSTRAINT estadomaquina_pkey PRIMARY KEY (idestado);
--
-- Definition for index maquina_fk1 (OID = 28399) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk1 FOREIGN KEY (estado) REFERENCES estadomaquina(idestado);
--
-- Definition for index estadopedido_pkey (OID = 28404) : 
--
ALTER TABLE ONLY estadopedido
    ADD CONSTRAINT estadopedido_pkey PRIMARY KEY (idestado);
--
-- Definition for index pedido_fk (OID = 28406) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk FOREIGN KEY (estado) REFERENCES estadopedido(idestado);
--
-- Definition for index estadopiezareal_pkey (OID = 28411) : 
--
ALTER TABLE ONLY estadopiezareal
    ADD CONSTRAINT estadopiezareal_pkey PRIMARY KEY (idestado);
--
-- Definition for index piezareal_fk1 (OID = 28413) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_fk1 FOREIGN KEY (estado) REFERENCES estadopiezareal(idestado);
--
-- Definition for index estadoproductoreal_pkey (OID = 28418) : 
--
ALTER TABLE ONLY estadoproductoreal
    ADD CONSTRAINT estadoproductoreal_pkey PRIMARY KEY (idestado);
--
-- Definition for index productoreal_fk3 (OID = 28420) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk3 FOREIGN KEY (estado) REFERENCES estadoproductoreal(idestado);
--
-- Definition for index estadoremito_pkey (OID = 28425) : 
--
ALTER TABLE ONLY estadoremito
    ADD CONSTRAINT estadoremito_pkey PRIMARY KEY (idestado);
--
-- Definition for index remito_fk1 (OID = 28427) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_fk1 FOREIGN KEY (estado) REFERENCES estadoremito(idestado);
--
-- Definition for index estadotrabajotercerizado_pkey (OID = 28432) : 
--
ALTER TABLE ONLY estadotrabajotercerizado
    ADD CONSTRAINT estadotrabajotercerizado_pkey PRIMARY KEY (idestado);
--
-- Definition for index trabajotercerizado_fk2 (OID = 28434) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk2 FOREIGN KEY (estado) REFERENCES estadotrabajotercerizado(idestado);
--
-- Definition for index etapadeproduccion_pkey (OID = 28439) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_pkey PRIMARY KEY (idetapaproduccion);
--
-- Definition for index ejecucionetapaproduccion_fk (OID = 28441) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detalletrabajotercerizado_fk2 (OID = 28446) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk2 FOREIGN KEY (proceso) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index piezaxetapadeproduccion_fk1 (OID = 28451) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT piezaxetapadeproduccion_fk1 FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detalleplanprocedimientos_fk2 (OID = 28456) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_fk2 FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index factura_pkey (OID = 28461) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_pkey PRIMARY KEY (idfactura);
--
-- Definition for index pedido_fk1 (OID = 28463) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk1 FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index comprobantepago_fk2 (OID = 28468) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk2 FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index detallefactura_fk (OID = 28473) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_fk FOREIGN KEY (idfactura) REFERENCES factura(idfactura);
--
-- Definition for index formadepago_pkey (OID = 28478) : 
--
ALTER TABLE ONLY formadepago
    ADD CONSTRAINT formadepago_pkey PRIMARY KEY (idformapago);
--
-- Definition for index factura_fk1 (OID = 28480) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk1 FOREIGN KEY (formapago) REFERENCES formadepago(idformapago);
--
-- Definition for index comprobantepago_fk (OID = 28485) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk FOREIGN KEY (formadepago) REFERENCES formadepago(idformapago);
--
-- Definition for index localidad_pkey (OID = 28490) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT localidad_pkey PRIMARY KEY (idlocalidad);
--
-- Definition for index barrio_fk (OID = 28492) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT barrio_fk FOREIGN KEY (localidad) REFERENCES localidad(idlocalidad);
--
-- Definition for index mantenimientocorrectivo_pkey (OID = 28497) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_pkey PRIMARY KEY (idmantenimientocorrectivo);
--
-- Definition for index detallemantenimientocorrectivo_fk1 (OID = 28499) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_fk1 FOREIGN KEY (idmantenimientocorrectivo) REFERENCES mantenimientocorrectivo(idmantenimientocorrectivo);
--
-- Definition for index mantenimientopreventivo_pkey (OID = 28504) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_pkey PRIMARY KEY (idmantenimientopreventivo);
--
-- Definition for index detallemantenimientopreventivo_fk (OID = 28506) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_fk FOREIGN KEY (idmantenimientopreventivo) REFERENCES mantenimientopreventivo(idmantenimientopreventivo);
--
-- Definition for index maquina_pkey (OID = 28511) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_pkey PRIMARY KEY (idmaquina);
--
-- Definition for index etapadeproduccion_fk (OID = 28513) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_fk FOREIGN KEY (maquina) REFERENCES maquina(idmaquina);
--
-- Definition for index maquinaxejecucionetapaproduccion_fk1 (OID = 28518) : 
--
ALTER TABLE ONLY maquinaxejecucionetapaproduccion
    ADD CONSTRAINT maquinaxejecucionetapaproduccion_fk1 FOREIGN KEY (idmaquina) REFERENCES maquina(idmaquina);
--
-- Definition for index maquinaxprocesocalidad_fk1 (OID = 28523) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT maquinaxprocesocalidad_fk1 FOREIGN KEY (idmaquina) REFERENCES maquina(idmaquina);
--
-- Definition for index mantenimientocorrectivo_fk2 (OID = 28528) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_fk2 FOREIGN KEY (maquina) REFERENCES maquina(idmaquina);
--
-- Definition for index mantenimientopreventivo_fk1 (OID = 28533) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_fk1 FOREIGN KEY (maquina) REFERENCES maquina(idmaquina);
--
-- Definition for index marca_pkey (OID = 28538) : 
--
ALTER TABLE ONLY marca
    ADD CONSTRAINT marca_pkey PRIMARY KEY (idmarca);
--
-- Definition for index maquina_fk (OID = 28540) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk FOREIGN KEY (marca) REFERENCES marca(idmarca);
--
-- Definition for index materiaprima_pkey (OID = 28545) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_pkey PRIMARY KEY (idmateriaprima);
--
-- Definition for index matriz_fk (OID = 28547) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT matriz_fk FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index pieza_fk1 (OID = 28552) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk1 FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallecompra_fk1 (OID = 28557) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk1 FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index proveedorxmateriaprima_fk1 (OID = 28562) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_fk1 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_fk2 (OID = 28567) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_fk2 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index matriz_pkey (OID = 28572) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT matriz_pkey PRIMARY KEY (idmatriz);
--
-- Definition for index pieza_fk2 (OID = 28574) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk2 FOREIGN KEY (matriz) REFERENCES matriz(idmatriz);
--
-- Definition for index pedidomatriz_fk (OID = 28579) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT pedidomatriz_fk FOREIGN KEY (idmatriz) REFERENCES matriz(idmatriz);
--
-- Definition for index pedido_pkey (OID = 28584) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (idpedido);
--
-- Definition for index plano_fk (OID = 28586) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT plano_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index remito_fk (OID = 28591) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index trabajotercerizado_fk (OID = 28596) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index planificacionproduccion_fk (OID = 28601) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index planificacioncalidad_fk (OID = 28606) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT planificacioncalidad_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index detallepedido_fk (OID = 28611) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_fk FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index productoreal_fk1 (OID = 28616) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk1 FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index detallefactura_fk1 (OID = 28621) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_fk1 FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index pedidomatriz_idx (OID = 28626) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT pedidomatriz_idx PRIMARY KEY (idpedidomatriz);
--
-- Definition for index pieza_pkey (OID = 28628) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_pkey PRIMARY KEY (idpieza);
--
-- Definition for index piezareal_fk (OID = 28630) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_fk FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index detallaplanificacionproduccion_fk2 (OID = 28635) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk2 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleproducto_fk1 (OID = 28640) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_fk1 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalletrabajotercerizado_fk1 (OID = 28645) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk1 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleplanificacioncalidad_fk3 (OID = 28650) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk3 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index piezaxetapadeproduccion_fk (OID = 28655) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT piezaxetapadeproduccion_fk FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index detallerequerimientosmateriaprima_fk1 (OID = 28660) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_fk1 FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleplanprocedimientos_fk1 (OID = 28665) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_fk1 FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleplanprocesoscalidad_fk1 (OID = 28670) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_fk1 FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index piezareal_idpiezareal_key (OID = 28675) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_idpiezareal_key UNIQUE (idpiezareal);
--
-- Definition for index piezareal_pkey (OID = 28677) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_pkey PRIMARY KEY (idpiezareal, idpieza);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk2 (OID = 28679) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk2 FOREIGN KEY (pieza, piezareal) REFERENCES piezareal(idpiezareal, idpieza);
--
-- Definition for index detalleejecucionplanificacion_fk2 (OID = 28684) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk2 FOREIGN KEY (piezareal, pieza) REFERENCES piezareal(idpiezareal, idpieza);
--
-- Definition for index detallereclamoempresametalurgica_fk1 (OID = 28689) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_fk1 FOREIGN KEY (pieza) REFERENCES piezareal(idpiezareal);
--
-- Definition for index detalleproductoreal_fk1 (OID = 28694) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_fk1 FOREIGN KEY (idpieza, idpiezareal) REFERENCES piezareal(idpiezareal, idpieza);
--
-- Definition for index productoreal_fk (OID = 28699) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk FOREIGN KEY (idpieza, idpiezareal) REFERENCES piezareal(idpiezareal, idpieza);
--
-- Definition for index planificacioncalidad_pkey (OID = 28704) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT planificacioncalidad_pkey PRIMARY KEY (idplanificacion);
--
-- Definition for index ejecucionplanificacioncalidad_fk (OID = 28706) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_fk FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index detalleplanificacioncalidad_fk (OID = 28711) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index planificacionproduccion_pkey (OID = 28716) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_pkey PRIMARY KEY (idplanificacionproduccion);
--
-- Definition for index ejecucionplanificacionproduccion_fk (OID = 28718) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_fk FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index detallaplanificacionproduccion_fk (OID = 28723) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index plano_pkey (OID = 28728) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT plano_pkey PRIMARY KEY (idplano);
--
-- Definition for index pedido_fk3 (OID = 28730) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk3 FOREIGN KEY (plano) REFERENCES plano(idplano);
--
-- Definition for index planprocedimientos_pkey (OID = 28735) : 
--
ALTER TABLE ONLY planprocedimientos
    ADD CONSTRAINT planprocedimientos_pkey PRIMARY KEY (idplanprocedimientos);
--
-- Definition for index detalleplanprocedimientos_fk (OID = 28737) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_fk FOREIGN KEY (idplanpprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index pedido_fk5 (OID = 28742) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk5 FOREIGN KEY (planprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index planprocesoscalidad_pkey (OID = 28747) : 
--
ALTER TABLE ONLY planprocesoscalidad
    ADD CONSTRAINT planprocesoscalidad_pkey PRIMARY KEY (idplanprocesoscalidad);
--
-- Definition for index detalleplanprocesoscalidad_fk (OID = 28749) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_fk FOREIGN KEY (idplanprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index pedido_fk7 (OID = 28754) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk7 FOREIGN KEY (planprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index planrequerimientosmateriaprima_pkey (OID = 28759) : 
--
ALTER TABLE ONLY planrequerimientosmateriaprima
    ADD CONSTRAINT planrequerimientosmateriaprima_pkey PRIMARY KEY (idplanrequerimientosmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_fk (OID = 28761) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_fk FOREIGN KEY (idplanrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index pedido_fk6 (OID = 28766) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk6 FOREIGN KEY (planrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index presupuesto_pkey (OID = 28771) : 
--
ALTER TABLE ONLY presupuesto
    ADD CONSTRAINT presupuesto_pkey PRIMARY KEY (idpresupuesto);
--
-- Definition for index pedido_fk2 (OID = 28773) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk2 FOREIGN KEY (presupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index detallepresupuesto_fk (OID = 28778) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_fk FOREIGN KEY (idpresupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index prioridad_pkey (OID = 28783) : 
--
ALTER TABLE ONLY prioridad
    ADD CONSTRAINT prioridad_pkey PRIMARY KEY (idprioridad);
--
-- Definition for index cliente_fk (OID = 28785) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index privilegio_pkey (OID = 28790) : 
--
ALTER TABLE ONLY privilegio
    ADD CONSTRAINT privilegio_pkey PRIMARY KEY (idprivilegio);
--
-- Definition for index rolxprivilegio_fk (OID = 28792) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_fk FOREIGN KEY (idprivilegio) REFERENCES privilegio(idprivilegio);
--
-- Definition for index procesocalidad_pkey (OID = 28797) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT procesocalidad_pkey PRIMARY KEY (idprocesocalidad);
--
-- Definition for index ejecucionprocesocalidad_fk (OID = 28799) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_fk FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detalleplanificacioncalidad_fk2 (OID = 28804) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk2 FOREIGN KEY (procesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index maquinaxprocesocalidad_fk (OID = 28809) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT maquinaxprocesocalidad_fk FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detalleplanprocesoscalidad_fk2 (OID = 28814) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_fk2 FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index producto_pkey (OID = 28819) : 
--
ALTER TABLE ONLY producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (idproducto);
--
-- Definition for index detalleproducto_fk (OID = 28821) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_fk FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index detallepedido_fk1 (OID = 28826) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detalleremito_fk1 (OID = 28831) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detallereclamocliente_fk1 (OID = 28836) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detallepresupuesto_fk2 (OID = 28841) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_fk2 FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index productoreal_idx (OID = 28846) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_idx PRIMARY KEY (idproductoreal);
--
-- Definition for index detalleproductoreal_fk (OID = 28848) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_fk FOREIGN KEY (idproductoreal) REFERENCES productoreal(idproductoreal);
--
-- Definition for index proveedor_pkey (OID = 28853) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_pkey PRIMARY KEY (idproveedor);
--
-- Definition for index compra_fk1 (OID = 28855) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_fk1 FOREIGN KEY (proveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index proveedorxmateriaprima_fk (OID = 28860) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_fk FOREIGN KEY (idproveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index proveedormantenimientomaquina_pkey (OID = 28865) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_pkey PRIMARY KEY (idproveedormantenimiento);
--
-- Definition for index mantenimientopreventivo_fk (OID = 28867) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_fk FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index mantenimientocorrectivo_fk1 (OID = 28872) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_fk1 FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index provincia_pkey (OID = 28877) : 
--
ALTER TABLE ONLY provincia
    ADD CONSTRAINT provincia_pkey PRIMARY KEY (idprovincia);
--
-- Definition for index localidad_fk (OID = 28879) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT localidad_fk FOREIGN KEY (provincia) REFERENCES provincia(idprovincia);
--
-- Definition for index reclamocliente_idx (OID = 28884) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_idx PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamocliente_fk (OID = 28886) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_fk FOREIGN KEY (idreclamo) REFERENCES reclamocliente(idreclamo);
--
-- Definition for index reclamoempresametalurgica_pkey (OID = 28891) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_pkey PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamoempresametalurgica_fk (OID = 28893) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_fk FOREIGN KEY (idreclamo) REFERENCES reclamoempresametalurgica(idreclamo);
--
-- Definition for index reclamoproveedor_idx (OID = 28898) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_idx PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamoproveedor_fk (OID = 28900) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_fk FOREIGN KEY (idreclamo) REFERENCES reclamoproveedor(idreclamo);
--
-- Definition for index remito_pkey (OID = 28905) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_pkey PRIMARY KEY (idremito);
--
-- Definition for index detalleremito_fk (OID = 28907) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_fk FOREIGN KEY (idremito) REFERENCES remito(idremito);
--
-- Definition for index responsable_pkey (OID = 28912) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_pkey PRIMARY KEY (idresponsable);
--
-- Definition for index cliente_fk3 (OID = 28914) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk3 FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index empresametalurgica_fk (OID = 28919) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index proveedormantenimientomaquina_fk (OID = 28924) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index proveedor_fk (OID = 28929) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index rol_pkey (OID = 28934) : 
--
ALTER TABLE ONLY rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (idrol);
--
-- Definition for index rolxprivilegio_fk1 (OID = 28936) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_fk1 FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index usuarioxrol_fk (OID = 28941) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_fk FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index rotura_pkey (OID = 28946) : 
--
ALTER TABLE ONLY rotura
    ADD CONSTRAINT rotura_pkey PRIMARY KEY (idrotura);
--
-- Definition for index detallemantenimientocorrectivo_fk (OID = 28948) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_fk FOREIGN KEY (rotura) REFERENCES rotura(idrotura);
--
-- Definition for index servicio_pkey (OID = 28953) : 
--
ALTER TABLE ONLY servicio
    ADD CONSTRAINT servicio_pkey PRIMARY KEY (idservicio);
--
-- Definition for index detallemantenimientopreventivo_fk1 (OID = 28955) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_fk1 FOREIGN KEY (servicio) REFERENCES servicio(idservicio);
--
-- Definition for index sesion_pkey (OID = 28960) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT sesion_pkey PRIMARY KEY (idsesion);
--
-- Definition for index tipodocumento_pkey (OID = 28962) : 
--
ALTER TABLE ONLY tipodocumento
    ADD CONSTRAINT tipodocumento_pkey PRIMARY KEY (idtipodocumento);
--
-- Definition for index responsable_fk1 (OID = 28964) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_fk1 FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index empleado_fk1 (OID = 28969) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk1 FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index tipoiva_pkey (OID = 28974) : 
--
ALTER TABLE ONLY tipoiva
    ADD CONSTRAINT tipoiva_pkey PRIMARY KEY (idtipoiva);
--
-- Definition for index factura_fk (OID = 28976) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk FOREIGN KEY (tipoiva) REFERENCES tipoiva(idtipoiva);
--
-- Definition for index tipomaquina_pkey (OID = 28981) : 
--
ALTER TABLE ONLY tipomaquina
    ADD CONSTRAINT tipomaquina_pkey PRIMARY KEY (idtipomaquina);
--
-- Definition for index maquina_fk2 (OID = 28983) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk2 FOREIGN KEY (tipomaquina) REFERENCES tipomaquina(idtipomaquina);
--
-- Definition for index tiporeclamo_pkey (OID = 28988) : 
--
ALTER TABLE ONLY tiporeclamo
    ADD CONSTRAINT tiporeclamo_pkey PRIMARY KEY (idtiporeclamo);
--
-- Definition for index reclamoempresametalurgica_fk (OID = 28990) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index reclamoproveedor_fk (OID = 28995) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index reclamocliente_fk (OID = 29000) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index trabajotercerizado_pkey (OID = 29005) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_pkey PRIMARY KEY (idtrabajo);
--
-- Definition for index detalletrabajotercerizado_fk (OID = 29007) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk FOREIGN KEY (idtrabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index reclamoempresametalurgica_fk1 (OID = 29012) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_fk1 FOREIGN KEY (trabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index turno_pkey (OID = 29017) : 
--
ALTER TABLE ONLY turno
    ADD CONSTRAINT turno_pkey PRIMARY KEY (idturno);
--
-- Definition for index empleadoxturno_fk1 (OID = 29019) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_fk1 FOREIGN KEY (idturno) REFERENCES turno(idturno);
--
-- Definition for index materiaprima_fk (OID = 29024) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk FOREIGN KEY (tipomaterial) REFERENCES tipomaterial(idtipomaterial);
--
-- Definition for index detalleproducto_iddetalle_key (OID = 29029) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_iddetalle_key PRIMARY KEY (iddetalle);
ALTER TABLE detalleproducto CLUSTER ON detalleproducto_iddetalle_key;
--
-- Definition for index pedido_fk8 (OID = 29031) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk8 FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index unidadmedida_pkey (OID = 29036) : 
--
ALTER TABLE ONLY unidadmedida
    ADD CONSTRAINT unidadmedida_pkey PRIMARY KEY (idunidadmedida);
--
-- Definition for index etapadeproduccion_fk1 (OID = 29038) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_fk1 FOREIGN KEY (unidaddemedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index materiaprima_fk2 (OID = 29045) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk2 FOREIGN KEY (unidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index detallepresupuesto_fk (OID = 30118) : 
--
--ALTER TABLE ONLY detallepresupuesto
--    ADD CONSTRAINT detallepresupuesto_fk FOREIGN KEY (idpresupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index detallepresupuesto_fk2 (OID = 30181) : 
--
--ALTER TABLE ONLY detallepresupuesto
--    ADD CONSTRAINT detallepresupuesto_fk2 FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index unidadmedida_pkey (OID = 30376) : 
--
--ALTER TABLE ONLY unidadmedida
--  ADD CONSTRAINT unidadmedida_pkey PRIMARY KEY (idunidadmedida);
--
-- Definition for index detalleproductopresupuesto_pkey (OID = 38746) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT detalleproductopresupuesto_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detallepiezapresupuesto_pkey (OID = 38748) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT detallepiezapresupuesto_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detallepiezapresupuesto_fk (OID = 38750) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT detallepiezapresupuesto_fk FOREIGN KEY (iddetalleproductopresupuesto) REFERENCES detalleproductopresupuesto(iddetalle);
--
-- Definition for index detallepiezapresupuesto_fk1 (OID = 38755) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT detallepiezapresupuesto_fk1 FOREIGN KEY (idetapa) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detalleproductopresupuesto_fk (OID = 38760) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT detalleproductopresupuesto_fk FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index detallepresupuesto_pkey (OID = 38770) : 
--
--ALTER TABLE ONLY detallepresupuesto
--    ADD CONSTRAINT detallepresupuesto_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detalleproductopresupuesto_fk1 (OID = 38774) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT detalleproductopresupuesto_fk1 FOREIGN KEY (iddetallepresupuesto) REFERENCES detallepresupuesto(iddetalle);
--
-- Definition for index detalleproductopresupuesto_fk2 (OID = 38843) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT detalleproductopresupuesto_fk2 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallepiezacalidadpresupuesto_pkey (OID = 38932) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT detallepiezacalidadpresupuesto_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detallepiezacalidadpresupuesto_fk (OID = 38934) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT detallepiezacalidadpresupuesto_fk FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detallepiezacalidadpresupuesto_fk1 (OID = 38939) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT detallepiezacalidadpresupuesto_fk1 FOREIGN KEY (iddetalleproductopresupuesto) REFERENCES detalleproductopresupuesto(iddetalle);
--
-- Comments
--
COMMENT ON SCHEMA public IS 'standard public schema';
