-- SQL Manager 2007 for PostgreSQL 4.5.0.8
-- ---------------------------------------
-- Host      : localhost
-- Database  : metalsoft
-- Version   : PostgreSQL 8.3.11, compiled by Visual C++ build 1400



--
-- Structure for table pedido (OID = 19241) : 
--
SET search_path = public, pg_catalog;
CREATE TABLE public.pedido (
    nropedido bigint NOT NULL,
    fechaconfirmacionpedido date,
    fechaentregaestipulada date,
    fechapedidocotizacion date,
    fechacancelacion date,
    fechaentregareal date,
    priodidad character varying(50),
    estado bigint,
    factura bigint,
    presupuesto bigint,
    fecharequeridacotizacion date,
    plano bigint,
    motivocancelacion character varying(50),
    espedidoweb boolean,
    nropedidocotizacioncliente integer,
    fecharegpedcotiz date,
    idpedido integer DEFAULT nextval(('"public"."pedido_idpedido_seq"'::text)::regclass) NOT NULL,
    cliente bigint,
    planprocedimientos bigint,
    planrequerimientosmateriaprima bigint,
    planprocesoscalidad bigint
) WITH OIDS;
--
-- Structure for table planificacioncalidad (OID = 19244) : 
--
CREATE TABLE public.planificacioncalidad (
    idplanificacion integer DEFAULT nextval(('"public"."planificacioncalidad_idplanificacion_seq"'::text)::regclass) NOT NULL,
    nroplanificacion bigint,
    fechacreacion date,
    observaciones character varying(50),
    fechainicioprevista date,
    fechafinprevista date,
    pedido bigint
) WITH OIDS;
--
-- Structure for table estadopedido (OID = 19247) : 
--
CREATE TABLE public.estadopedido (
    idestado integer DEFAULT nextval(('"public"."estadopedido_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table factura (OID = 19250) : 
--
CREATE TABLE public.factura (
    idfactura integer DEFAULT nextval(('"public"."factura_idfactura_seq"'::text)::regclass) NOT NULL,
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
-- Structure for table planificacionproduccion (OID = 19253) : 
--
CREATE TABLE public.planificacionproduccion (
    idplanificacionproduccion integer DEFAULT nextval(('"public"."planificacionproduccion_idplanificacionproduccion_seq"'::text)::regclass) NOT NULL,
    nroplanificacion bigint,
    fechacreacion date,
    observaciones character varying(50),
    fechainicioprevista date,
    fechafinprevista date,
    pedido bigint
) WITH OIDS;
--
-- Structure for table presupuesto (OID = 19256) : 
--
CREATE TABLE public.presupuesto (
    idpresupuesto integer DEFAULT nextval(('"public"."presupuesto_idpresupuesto_seq"'::text)::regclass) NOT NULL,
    fechapresupuesto date,
    montototal double precision,
    fechavencimiento date
) WITH OIDS;
--
-- Structure for table plano (OID = 19259) : 
--
CREATE TABLE public.plano (
    idplano integer DEFAULT nextval(('"public"."plano_idplano_seq"'::text)::regclass) NOT NULL,
    nroplano bigint,
    escala integer,
    pedido bigint,
    imagen bytea[]
) WITH OIDS;
--
-- Structure for table remito (OID = 19265) : 
--
CREATE TABLE public.remito (
    idremito integer DEFAULT nextval(('"public"."remito_idremito_seq"'::text)::regclass) NOT NULL,
    nroremito bigint,
    fechaemision date,
    pedido bigint,
    estado bigint
) WITH OIDS;
--
-- Structure for table trabajotercerizado (OID = 19268) : 
--
CREATE TABLE public.trabajotercerizado (
    idtrabajo integer DEFAULT nextval(('"public"."trabajotercerizado_idtrabajo_seq"'::text)::regclass) NOT NULL,
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
-- Structure for table cliente (OID = 19271) : 
--
CREATE TABLE public.cliente (
    nrocliente bigint,
    idcliente integer DEFAULT nextval(('"public"."cliente_idcliente_seq"'::text)::regclass) NOT NULL,
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
    "CUIL" character varying(50),
    condicioniva bigint,
    cuit character varying(50)
) WITH OIDS;
--
-- Structure for table tipoiva (OID = 19274) : 
--
CREATE TABLE public.tipoiva (
    idtipoiva integer DEFAULT nextval(('"public"."tipoiva_idtipoiva_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table formadepago (OID = 19277) : 
--
CREATE TABLE public.formadepago (
    idformapago integer DEFAULT nextval(('"public"."formadepago_idformapago_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table usuario (OID = 19280) : 
--
CREATE TABLE public.usuario (
    idusuario integer DEFAULT nextval(('"public"."usuario_idusuario_seq"'::text)::regclass) NOT NULL,
    usuario character varying(50),
    clave character varying(50)
) WITH OIDS;
--
-- Structure for table estadofactura (OID = 19283) : 
--
CREATE TABLE public.estadofactura (
    idestado integer DEFAULT nextval(('"public"."estadofactura_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table comprobantepago (OID = 19286) : 
--
CREATE TABLE public.comprobantepago (
    idcomprobantepago integer DEFAULT nextval(('"public"."comprobantepago_idcomprobantepago_seq"'::text)::regclass) NOT NULL,
    nrocomprobantepago bigint,
    fechaemision date,
    monto double precision,
    formadepago bigint,
    usuario bigint,
    factura bigint
) WITH OIDS;
--
-- Structure for table estadotrabajotercerizado (OID = 19289) : 
--
CREATE TABLE public.estadotrabajotercerizado (
    idestado integer DEFAULT nextval(('"public"."estadotrabajotercerizado_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table empresametalurgica (OID = 19292) : 
--
CREATE TABLE public.empresametalurgica (
    idempresametalurgica integer DEFAULT nextval(('"public"."empresametalurgica_idempresametalurgica_seq"'::text)::regclass) NOT NULL,
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
-- Structure for table prioridad (OID = 19295) : 
--
CREATE TABLE public.prioridad (
    idprioridad integer DEFAULT nextval(('"public"."prioridad_idprioridad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table estadocliente (OID = 19298) : 
--
CREATE TABLE public.estadocliente (
    idestado integer DEFAULT nextval(('"public"."estadocliente_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table responsable (OID = 19301) : 
--
CREATE TABLE public.responsable (
    idresponsable integer DEFAULT nextval(('"public"."responsable_idresponsable_seq"'::text)::regclass) NOT NULL,
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
-- Structure for table condicioniva (OID = 19304) : 
--
CREATE TABLE public.condicioniva (
    idcondicioniva integer DEFAULT nextval(('"public"."condicioniva_idcondicioniva_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table domicilio (OID = 19307) : 
--
CREATE TABLE public.domicilio (
    iddomicilio integer DEFAULT nextval(('"public"."domicilio_iddomicilio_seq"'::text)::regclass) NOT NULL,
    calle character varying(50),
    numerocalle integer,
    piso integer,
    depto character varying(5),
    torre character varying(10),
    barrio bigint
) WITH OIDS;
--
-- Structure for table rotura (OID = 19310) : 
--
CREATE TABLE public.rotura (
    idrotura integer DEFAULT nextval(('"public"."rotura_idrotura_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table categoria (OID = 19313) : 
--
CREATE TABLE public.categoria (
    idcategoria integer DEFAULT nextval(('"public"."categoria_idcategoria_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table cargo (OID = 19316) : 
--
CREATE TABLE public.cargo (
    idcargo integer DEFAULT nextval(('"public"."cargo_idcargo_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table turno (OID = 19319) : 
--
CREATE TABLE public.turno (
    idturno integer DEFAULT nextval(('"public"."turno_idturno_seq"'::text)::regclass) NOT NULL,
    horainicio time without time zone,
    horafin time without time zone,
    nombre character varying(20),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table marca (OID = 19322) : 
--
CREATE TABLE public.marca (
    idmarca integer DEFAULT nextval(('"public"."marca_idmarca_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadomaquina (OID = 19325) : 
--
CREATE TABLE public.estadomaquina (
    idestado integer DEFAULT nextval(('"public"."estadomaquina_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table tipomaquina (OID = 19328) : 
--
CREATE TABLE public.tipomaquina (
    idtipomaquina integer DEFAULT nextval(('"public"."tipomaquina_idtipomaquina_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecetapaprod (OID = 19331) : 
--
CREATE TABLE public.estadoejecetapaprod (
    idestado integer DEFAULT nextval(('"public"."estadoejecetapaprod_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table servicio (OID = 19334) : 
--
CREATE TABLE public.servicio (
    idservicio integer DEFAULT nextval(('"public"."servicio_idservicio_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table tipodocumento (OID = 19337) : 
--
CREATE TABLE public.tipodocumento (
    idtipodocumento integer DEFAULT nextval(('"public"."tipodocumento_idtipodocumento_seq"'::text)::regclass) NOT NULL,
    tipo character varying(50),
    nombre character varying(50)
) WITH OIDS;
--
-- Structure for table codigodebarra (OID = 19340) : 
--
CREATE TABLE public.codigodebarra (
    idcodigo integer DEFAULT nextval(('"public"."codigodebarra_idcodigo_seq"'::text)::regclass) NOT NULL,
    descripcion character varying(50),
    codigo character varying(50)
) WITH OIDS;
--
-- Structure for table estadopiezareal (OID = 19343) : 
--
CREATE TABLE public.estadopiezareal (
    idestado integer DEFAULT nextval(('"public"."estadopiezareal_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecplanifpedido (OID = 19346) : 
--
CREATE TABLE public.estadoejecplanifpedido (
    idestado integer DEFAULT nextval(('"public"."estadoejecplanifpedido_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table proveedormantenimientomaquina (OID = 19349) : 
--
CREATE TABLE public.proveedormantenimientomaquina (
    idproveedormantenimiento integer DEFAULT nextval(('"public"."proveedormantenimientomaquina_idproveedormantenimiento_seq"'::text)::regclass) NOT NULL,
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
-- Structure for table rol (OID = 19352) : 
--
CREATE TABLE public.rol (
    idrol integer DEFAULT nextval(('"public"."rol_idrol_seq"'::text)::regclass) NOT NULL,
    rol character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table privilegio (OID = 19355) : 
--
CREATE TABLE public.privilegio (
    idprivilegio integer DEFAULT nextval(('"public"."privilegio_idprivilegio_seq"'::text)::regclass) NOT NULL,
    privilegio character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table accioncalidad (OID = 19358) : 
--
CREATE TABLE public.accioncalidad (
    idaccioncalidad integer DEFAULT nextval(('"public"."accioncalidad_idaccioncalidad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table empleado (OID = 19361) : 
--
CREATE TABLE public.empleado (
    idempleado integer DEFAULT nextval(('"public"."empleado_idempleado_seq"'::text)::regclass) NOT NULL,
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
-- Structure for table proveedor (OID = 19364) : 
--
CREATE TABLE public.proveedor (
    idproveedor integer DEFAULT nextval(('"public"."proveedor_idproveedor_seq"'::text)::regclass) NOT NULL,
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
-- Structure for table estadocompra (OID = 19367) : 
--
CREATE TABLE public.estadocompra (
    idestado integer DEFAULT nextval(('"public"."estadocompra_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table sesion (OID = 19373) : 
--
CREATE TABLE public.sesion (
    idsesion integer DEFAULT nextval(('"public"."sesion_idsesion_seq"'::text)::regclass) NOT NULL,
    fechainicio date,
    fechafin date,
    horainicio time without time zone,
    horafin time without time zone,
    usuario bigint
) WITH OIDS;
--
-- Structure for table materiaprima (OID = 19376) : 
--
CREATE TABLE public.materiaprima (
    idmateriaprima integer DEFAULT nextval(('"public"."materiaprima_idmateriaprima_seq"'::text)::regclass) NOT NULL,
    codproducto bigint,
    nombre character varying(50),
    precio double precision,
    proveedor bigint,
    fechaalta date,
    fechabaja date,
    codbarra bigint
) WITH OIDS;
--
-- Structure for table matriz (OID = 19379) : 
--
CREATE TABLE public.matriz (
    idmatriz integer DEFAULT nextval(('"public"."matriz_idmatriz_seq"'::text)::regclass) NOT NULL,
    codmatriz bigint,
    nombre character varying(50),
    descripcion character varying(50),
    observaciones character varying(100),
    fechacreacion date,
    materiaprima bigint,
    tipomaterial bigint
) WITH OIDS;
--
-- Structure for table pieza (OID = 19385) : 
--
CREATE TABLE public.pieza (
    idpieza integer DEFAULT nextval(('"public"."pieza_idpieza_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    tipomaterial bigint,
    dimensiones character varying(100),
    materiaprima bigint,
    matriz bigint
) WITH OIDS;
--
-- Structure for table producto (OID = 19388) : 
--
CREATE TABLE public.producto (
    idproducto integer DEFAULT nextval(('"public"."producto_idproducto_seq"'::text)::regclass) NOT NULL,
    nroproducto bigint,
    nombre character varying(50),
    stock integer,
    preciounitario double precision,
    descripcion character varying(50),
    codbarra bigint
) WITH OIDS;
--
-- Structure for table estadoejecplancalidad (OID = 19391) : 
--
CREATE TABLE public.estadoejecplancalidad (
    idestado integer DEFAULT nextval(('"public"."estadoejecplancalidad_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table mantenimientopreventivo (OID = 19394) : 
--
CREATE TABLE public.mantenimientopreventivo (
    idmantenimientopreventivo integer DEFAULT nextval(('"public"."mantenimientopreventivo_idmantenimientopreventivo_seq"'::text)::regclass) NOT NULL,
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
-- Structure for table mantenimientocorrectivo (OID = 19397) : 
--
CREATE TABLE public.mantenimientocorrectivo (
    idmantenimientocorrectivo integer DEFAULT nextval(('"public"."mantenimientocorrectivo_idmantenimientocorrectivo_seq"'::text)::regclass) NOT NULL,
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
-- Structure for table maquina (OID = 19400) : 
--
CREATE TABLE public.maquina (
    idmaquina integer DEFAULT nextval(('"public"."maquina_idmaquina_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    marca bigint,
    descripcion character varying(100),
    estado bigint,
    tipomaquina bigint
) WITH OIDS;
--
-- Structure for table etapadeproduccion (OID = 19403) : 
--
CREATE TABLE public.etapadeproduccion (
    idetapaproduccion integer DEFAULT nextval(('"public"."etapadeproduccion_idetapaproduccion_seq"'::text)::regclass) NOT NULL,
    nroetapaproduccion bigint,
    nombre character varying(50),
    horasmaquina time without time zone,
    horashombre time without time zone,
    maquina bigint,
    duracionestimada time without time zone,
    fechacreacion date
) WITH OIDS;
--
-- Structure for table piezareal (OID = 19406) : 
--
CREATE TABLE public.piezareal (
    idpiezareal integer DEFAULT nextval(('"public"."piezareal_idpiezareal_seq"'::text)::regclass) NOT NULL,
    idpieza bigint NOT NULL,
    estado bigint,
    nropieza integer
) WITH OIDS;
--
-- Structure for table ejecucionplanificacionproduccion (OID = 19409) : 
--
CREATE TABLE public.ejecucionplanificacionproduccion (
    idejecucion integer DEFAULT nextval(('"public"."ejecucionplanificacionproduccion_idejecucion_seq"'::text)::regclass) NOT NULL,
    idplanificacionproduccion bigint NOT NULL,
    fechainicio date,
    fechafin date,
    horainicio time without time zone,
    horafin time without time zone,
    estado bigint
) WITH OIDS;
--
-- Structure for table procesocalidad (OID = 19412) : 
--
CREATE TABLE public.procesocalidad (
    idprocesocalidad integer DEFAULT nextval(('"public"."procesocalidad_idprocesocalidad_seq"'::text)::regclass) NOT NULL,
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
-- Structure for table ejecucionplanificacioncalidad (OID = 19415) : 
--
CREATE TABLE public.ejecucionplanificacioncalidad (
    idejecucion integer DEFAULT nextval(('"public"."ejecucionplanificacioncalidad_idejecucion_seq"'::text)::regclass) NOT NULL,
    idplanificacioncalidad bigint NOT NULL,
    fechainicio date,
    fechafin date,
    horainicio time without time zone,
    horafin time without time zone,
    estado bigint
) WITH OIDS;
--
-- Structure for table ejecucionprocesocalidad (OID = 19418) : 
--
CREATE TABLE public.ejecucionprocesocalidad (
    idejecucion integer DEFAULT nextval(('"public"."ejecucionprocesocalidad_idejecucion_seq"'::text)::regclass) NOT NULL,
    idprocesocalidad bigint NOT NULL,
    duracionreal time without time zone,
    resultado character varying(100),
    estado bigint,
    nroejecucion bigint
) WITH OIDS;
--
-- Structure for table ejecucionetapaproduccion (OID = 19421) : 
--
CREATE TABLE public.ejecucionetapaproduccion (
    idejecucion integer DEFAULT nextval(('"public"."ejecucionetapaproduccion_idejecucion_seq"'::text)::regclass) NOT NULL,
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
-- Structure for table compra (OID = 19424) : 
--
CREATE TABLE public.compra (
    idcompra integer DEFAULT nextval(('"public"."compra_idcompra_seq"'::text)::regclass) NOT NULL,
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
-- Structure for table asistencia (OID = 19427) : 
--
CREATE TABLE public.asistencia (
    empleado bigint NOT NULL,
    fechaingreso bigint NOT NULL,
    horaingreso time without time zone NOT NULL,
    horaegreso time without time zone,
    observaciones character varying(100)
) WITH OIDS;
--
-- Structure for table detallemantenimientocorrectivo (OID = 19430) : 
--
CREATE TABLE public.detallemantenimientocorrectivo (
    idmantenimientocorrectivo integer DEFAULT nextval(('"public"."detallemantenimientocorrectivo_idmantenimientocorrectivo_seq"'::text)::regclass) NOT NULL,
    iddetalle bigint NOT NULL,
    duracion time without time zone,
    rotura bigint,
    motivorotura character varying(100)
) WITH OIDS;
--
-- Structure for table detallemantenimientopreventivo (OID = 19433) : 
--
CREATE TABLE public.detallemantenimientopreventivo (
    idmantenimientopreventivo integer DEFAULT nextval(('"public"."detallemantenimientopreventivo_idmantenimientopreventivo_seq"'::text)::regclass) NOT NULL,
    iddetalle bigint NOT NULL,
    duracion time without time zone,
    servicio bigint,
    observaciones character varying(100)
) WITH OIDS;
--
-- Structure for table detalleejecucionplanificacion (OID = 19436) : 
--
CREATE TABLE public.detalleejecucionplanificacion (
    iddetalle integer DEFAULT nextval(('"public"."detalleejecucionplanificacion_iddetalle_seq"'::text)::regclass) NOT NULL,
    idejecucionplanificacionproduccion bigint NOT NULL,
    pieza bigint,
    ejecucionetapa bigint NOT NULL,
    idplanificacionproduccion bigint NOT NULL,
    idetapaproduccion bigint,
    piezareal bigint
) WITH OIDS;
--
-- Structure for table detalleejecucionplanificacioncalidad (OID = 19439) : 
--
CREATE TABLE public.detalleejecucionplanificacioncalidad (
    iddetalle integer DEFAULT nextval(('"public"."detalleejecucionplanificacioncalidad_iddetalle_seq"'::text)::regclass) NOT NULL,
    idejecucionplanificacioncalidad bigint NOT NULL,
    idplanificacioncalidad bigint NOT NULL,
    ejecucionprocesocalidad bigint,
    idprocesocalidad bigint,
    pieza bigint,
    piezareal bigint
) WITH OIDS;
--
-- Structure for table detallaplanificacionproduccion (OID = 19442) : 
--
CREATE TABLE public.detallaplanificacionproduccion (
    iddetalle integer DEFAULT nextval(('"public"."detallaplanificacionproduccion_iddetalle_seq"'::text)::regclass) NOT NULL,
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
-- Structure for table detalleproducto (OID = 19445) : 
--
CREATE TABLE public.detalleproducto (
    iddetalle integer DEFAULT nextval(('"public"."detalleproducto_iddetalle_seq"'::text)::regclass) NOT NULL,
    idproducto bigint NOT NULL,
    cantidadpiezas integer,
    descripcion character varying(50),
    pieza bigint
) WITH OIDS;
--
-- Structure for table detallepedido (OID = 19448) : 
--
CREATE TABLE public.detallepedido (
    iddetalle integer DEFAULT nextval(('"public"."detallepedido_iddetalle_seq"'::text)::regclass) NOT NULL,
    idpedido bigint NOT NULL,
    precio double precision,
    cantidad integer,
    producto bigint
) WITH OIDS;
--
-- Structure for table detalletrabajotercerizado (OID = 19451) : 
--
CREATE TABLE public.detalletrabajotercerizado (
    iddetalle integer DEFAULT nextval(('"public"."detalletrabajotercerizado_iddetalle_seq"'::text)::regclass) NOT NULL,
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
-- Structure for table detalleplanificacioncalidad (OID = 19454) : 
--
CREATE TABLE public.detalleplanificacioncalidad (
    iddetalle integer DEFAULT nextval(('"public"."detalleplanificacioncalidad_iddetalle_seq"'::text)::regclass) NOT NULL,
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
-- Structure for table detallecompra (OID = 19457) : 
--
CREATE TABLE public.detallecompra (
    iddetalle integer DEFAULT nextval(('"public"."detallecompra_iddetalle_seq"'::text)::regclass) NOT NULL,
    idcompra bigint NOT NULL,
    cantidad integer,
    materiaprima bigint,
    preciohistorico double precision,
    fecharecepcionparcial date,
    estado bigint
) WITH OIDS;
--
-- Structure for table detalleremito (OID = 19460) : 
--
CREATE TABLE public.detalleremito (
    iddetalle integer DEFAULT nextval(('"public"."detalleremito_iddetalle_seq"'::text)::regclass) NOT NULL,
    idremito bigint NOT NULL,
    cantidad integer,
    descripcion character varying(50),
    producto bigint
) WITH OIDS;
--
-- Structure for table tiporeclamo (OID = 19463) : 
--
CREATE TABLE public.tiporeclamo (
    idtiporeclamo integer DEFAULT nextval(('"public"."tiporeclamo_idtiporeclamo_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table reclamoempresametalurgica (OID = 19466) : 
--
CREATE TABLE public.reclamoempresametalurgica (
    idreclamo integer DEFAULT nextval(('"public"."reclamoempresametalurgica_idreclamo_seq"'::text)::regclass) NOT NULL,
    nroreclamo bigint,
    tiporeclamo bigint,
    motivo character varying(50),
    fechareclamo date,
    trabajotercerizado bigint
) WITH OIDS;
--
-- Structure for table reclamoproveedor (OID = 19469) : 
--
CREATE TABLE public.reclamoproveedor (
    idreclamo integer DEFAULT nextval(('"public"."reclamoproveedor_idreclamo_seq"'::text)::regclass) NOT NULL,
    nroreclamo bigint,
    tiporeclamo bigint,
    motivo character varying(50),
    fechareclamo date,
    compra bigint
) WITH OIDS;
--
-- Structure for table reclamocliente (OID = 19472) : 
--
CREATE TABLE public.reclamocliente (
    idreclamo integer DEFAULT nextval(('"public"."reclamocliente_idreclamo_seq"'::text)::regclass) NOT NULL,
    nroreclamo bigint,
    tiporeclamo bigint,
    motivo character varying(100),
    fechareclamo date,
    cliente bigint
) WITH OIDS;
--
-- Structure for table detallereclamocliente (OID = 19475) : 
--
CREATE TABLE public.detallereclamocliente (
    iddetalle integer DEFAULT nextval(('"public"."detallereclamocliente_iddetalle_seq"'::text)::regclass) NOT NULL,
    idreclamo bigint NOT NULL,
    cantidad integer,
    descripcion character varying(50),
    motivo character varying(50),
    producto bigint
) WITH OIDS;
--
-- Structure for table detallereclamoproveedor (OID = 19478) : 
--
CREATE TABLE public.detallereclamoproveedor (
    iddetalle integer DEFAULT nextval(('"public"."detallereclamoproveedor_iddetalle_seq"'::text)::regclass) NOT NULL,
    idreclamo bigint NOT NULL,
    cantidad integer,
    descripcion character varying(50),
    motivo character varying(50),
    iddetallecompra bigint,
    fechaegreso date,
    idcompra bigint
) WITH OIDS;
--
-- Structure for table detallereclamoempresametalurgica (OID = 19481) : 
--
CREATE TABLE public.detallereclamoempresametalurgica (
    iddetalle integer DEFAULT nextval(('"public"."detallereclamoempresametalurgica_iddetalle_seq"'::text)::regclass) NOT NULL,
    idreclamo bigint NOT NULL,
    cantidad integer,
    descripcion character varying(50),
    motivo character varying(50),
    pieza bigint,
    fechaegreso date
) WITH OIDS;
--
-- Structure for table proveedorxmateriaprima (OID = 19484) : 
--
CREATE TABLE public.proveedorxmateriaprima (
    idproveedor bigint NOT NULL,
    idmateriaprima bigint NOT NULL,
    precio double precision
) WITH OIDS;
--
-- Structure for table maquinaxejecucionetapaproduccion (OID = 19487) : 
--
CREATE TABLE public.maquinaxejecucionetapaproduccion (
    idejecucionetapaproduccion bigint NOT NULL,
    idetapaproduccion bigint NOT NULL,
    idmaquina bigint NOT NULL,
    horasmaquina time without time zone,
    horashombre time without time zone
) WITH OIDS;
--
-- Structure for table maquinaxprocesocalidad (OID = 19490) : 
--
CREATE TABLE public.maquinaxprocesocalidad (
    idprocesocalidad bigint NOT NULL,
    idmaquina bigint NOT NULL,
    duracion time without time zone,
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table piezaxetapadeproduccion (OID = 19493) : 
--
CREATE TABLE public.piezaxetapadeproduccion (
    idpieza bigint NOT NULL,
    idetapaproduccion bigint NOT NULL,
    duracion time without time zone,
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table empleadoxturno (OID = 19496) : 
--
CREATE TABLE public.empleadoxturno (
    idempleado bigint NOT NULL,
    idturno bigint NOT NULL
) WITH OIDS;
--
-- Structure for table provincia (OID = 19499) : 
--
CREATE TABLE public.provincia (
    idprovincia integer DEFAULT nextval(('"public"."provincia_idprovincia_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50)
) WITH OIDS;
--
-- Structure for table localidad (OID = 19502) : 
--
CREATE TABLE public.localidad (
    idlocalidad integer DEFAULT nextval(('"public"."localidad_idlocalidad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    provincia bigint
) WITH OIDS;
--
-- Structure for table barrio (OID = 19505) : 
--
CREATE TABLE public.barrio (
    idbarrio integer DEFAULT nextval(('"public"."barrio_idbarrio_seq"'::text)::regclass) NOT NULL,
    nombre bigint,
    codpostal bigint,
    localidad bigint
) WITH OIDS;
--
-- Structure for table usuarioxrol (OID = 19508) : 
--
CREATE TABLE public.usuarioxrol (
    idrol bigint NOT NULL,
    idusuario bigint NOT NULL
) WITH OIDS;
--
-- Structure for table rolxprivilegio (OID = 19511) : 
--
CREATE TABLE public.rolxprivilegio (
    idrol bigint NOT NULL,
    idprivilegio bigint NOT NULL
) WITH OIDS;
--
-- Structure for table planrequerimientosmateriaprima (OID = 19514) : 
--
CREATE TABLE public.planrequerimientosmateriaprima (
    idplanrequerimientosmateriaprima integer DEFAULT nextval(('"public"."planrequerimientosmateriaprima_idplanrequerimientosmateriaprima_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table planprocedimientos (OID = 19517) : 
--
CREATE TABLE public.planprocedimientos (
    idplanprocedimientos integer DEFAULT nextval(('"public"."planprocedimientos_idplanprocedimientos_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table planprocesoscalidad (OID = 19520) : 
--
CREATE TABLE public.planprocesoscalidad (
    idplanprocesoscalidad integer DEFAULT nextval(('"public"."planprocesoscalidad_idplanprocesoscalidad_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table detallerequerimientosmateriaprima (OID = 19523) : 
--
CREATE TABLE public.detallerequerimientosmateriaprima (
    iddetalle integer DEFAULT nextval(('"public"."detallerequerimientosmateriaprima_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanrequerimientosmateriaprima bigint NOT NULL,
    cantidadmateriaprima integer,
    idpieza bigint,
    idmateriaprima bigint
) WITH OIDS;
--
-- Structure for table detalleplanprocedimientos (OID = 19526) : 
--
CREATE TABLE public.detalleplanprocedimientos (
    iddetalle integer DEFAULT nextval(('"public"."detalleplanprocedimientos_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanpprocedimientos bigint NOT NULL,
    idpieza bigint,
    idetapaproduccion bigint,
    duracionestimada time without time zone
) WITH OIDS;
--
-- Structure for table detalleplanprocesoscalidad (OID = 19529) : 
--
CREATE TABLE public.detalleplanprocesoscalidad (
    iddetalle integer DEFAULT nextval(('"public"."detalleplanprocesoscalidad_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanprocesoscalidad bigint NOT NULL,
    idpieza bigint,
    idprocesocalidad bigint,
    duracionestimada time without time zone
) WITH OIDS;
--
-- Structure for table pedidomatriz (OID = 19532) : 
--
CREATE TABLE public.pedidomatriz (
    idpedidomatriz integer DEFAULT nextval(('"public"."pedidomatriz_idpedidomatriz_seq"'::text)::regclass) NOT NULL,
    nropedidomatriz bigint,
    fechapedidomatriz date,
    idmatriz bigint,
    observaciones character varying(100)
) WITH OIDS;
--
-- Structure for table estadodetallecompra (OID = 19535) : 
--
CREATE TABLE public.estadodetallecompra (
    idestado integer DEFAULT nextval(('"public"."estadodetallecompra_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadodetalletrabajotercerizado (OID = 19538) : 
--
CREATE TABLE public.estadodetalletrabajotercerizado (
    idestado integer DEFAULT nextval(('"public"."estadodetalletrabajotercerizado_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecucionprocesocalidad (OID = 19541) : 
--
CREATE TABLE public.estadoejecucionprocesocalidad (
    idestado integer DEFAULT nextval(('"public"."estadoejecucionprocesocalidad_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table detalleproductoreal (OID = 19544) : 
--
CREATE TABLE public.detalleproductoreal (
    iddetalle integer DEFAULT nextval(('"public"."detalleproductoreal_iddetalle_seq"'::text)::regclass) NOT NULL,
    idproductoreal bigint NOT NULL,
    idpieza bigint,
    "cantidadPiezas" integer,
    descripcion character varying(50),
    idpiezareal bigint
) WITH OIDS;
--
-- Structure for table productoreal (OID = 19547) : 
--
CREATE TABLE public.productoreal (
    idproductoreal integer DEFAULT nextval(('"public"."productoreal_idproductoreal_seq"'::text)::regclass) NOT NULL,
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
-- Structure for table estadoproductoreal (OID = 19550) : 
--
CREATE TABLE public.estadoproductoreal (
    idestado integer DEFAULT nextval(('"public"."estadoproductoreal_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table detallefactura (OID = 19553) : 
--
CREATE TABLE public.detallefactura (
    iddetalle integer DEFAULT nextval(('"public"."detallefactura_iddetalle_seq"'::text)::regclass) NOT NULL,
    idfactura bigint NOT NULL,
    idpedido bigint,
    montoparcial double precision,
    cantidad integer,
    iddetallepedido bigint
) WITH OIDS;
--
-- Structure for table detallepresupuesto (OID = 19556) : 
--
CREATE TABLE public.detallepresupuesto (
    iddetalle integer DEFAULT nextval(('"public"."detallepresupuesto_iddetalle_seq"'::text)::regclass) NOT NULL,
    idpresupuesto bigint NOT NULL,
    iddetallepedido bigint,
    idpedido bigint,
    idproducto bigint,
    cantidad integer,
    precio double precision
) WITH OIDS;
--
-- Structure for table estadoremito (OID = 19559) : 
--
CREATE TABLE public.estadoremito (
    idestado integer DEFAULT nextval(('"public"."estadoremito_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table tipomaterial (OID = 20619) : 
--
CREATE TABLE public.tipomaterial (
    idtipomaterial integer DEFAULT nextval(('"public"."tipomaterial_idtipomaterial_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Definition for sequence prueba_id_seq (OID = 20628) : 
--
CREATE SEQUENCE public.prueba_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table prueba (OID = 20630) : 
--
CREATE TABLE public.prueba (
    id integer DEFAULT nextval('prueba_id_seq'::regclass) NOT NULL,
    valor character varying(20)
) WITH OIDS;
ALTER TABLE ONLY public.prueba ALTER COLUMN id SET STATISTICS 0;
ALTER TABLE ONLY public.prueba ALTER COLUMN valor SET STATISTICS 0;
--
-- Definition for sequence usuario_idusuario_seq (OID = 20644) : 
--
CREATE SEQUENCE public.usuario_idusuario_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipomaterial_idtipomaterial_seq (OID = 20683) : 
--
CREATE SEQUENCE public.tipomaterial_idtipomaterial_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence accioncalidad_idaccioncalidad_seq (OID = 20696) : 
--
CREATE SEQUENCE public.accioncalidad_idaccioncalidad_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence barrio_idbarrio_seq (OID = 20710) : 
--
CREATE SEQUENCE public.barrio_idbarrio_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence cargo_idcargo_seq (OID = 20724) : 
--
CREATE SEQUENCE public.cargo_idcargo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence categoria_idcategoria_seq (OID = 20738) : 
--
CREATE SEQUENCE public.categoria_idcategoria_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence cliente_idcliente_seq (OID = 20752) : 
--
CREATE SEQUENCE public.cliente_idcliente_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence codigodebarra_idcodigo_seq (OID = 20771) : 
--
CREATE SEQUENCE public.codigodebarra_idcodigo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence compra_idcompra_seq (OID = 20795) : 
--
CREATE SEQUENCE public.compra_idcompra_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence comprobantepago_idcomprobantepago_seq (OID = 20814) : 
--
CREATE SEQUENCE public.comprobantepago_idcomprobantepago_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence condicioniva_idcondicioniva_seq (OID = 20823) : 
--
CREATE SEQUENCE public.condicioniva_idcondicioniva_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallaplanificacionproduccion_iddetalle_seq (OID = 20852) : 
--
CREATE SEQUENCE public.detallaplanificacionproduccion_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallecompra_iddetalle_seq (OID = 20863) : 
--
CREATE SEQUENCE public.detallecompra_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleejecucionplanificacion_iddetalle_seq (OID = 20879) : 
--
CREATE SEQUENCE public.detalleejecucionplanificacion_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleejecucionplanificacioncalidad_iddetalle_seq (OID = 20895) : 
--
CREATE SEQUENCE public.detalleejecucionplanificacioncalidad_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallefactura_iddetalle_seq (OID = 20911) : 
--
CREATE SEQUENCE public.detallefactura_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallemantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 20922) : 
--
CREATE SEQUENCE public.detallemantenimientocorrectivo_idmantenimientocorrectivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallemantenimientopreventivo_idmantenimientopreventivo_seq (OID = 20938) : 
--
CREATE SEQUENCE public.detallemantenimientopreventivo_idmantenimientopreventivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallepedido_iddetalle_seq (OID = 20954) : 
--
CREATE SEQUENCE public.detallepedido_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanificacioncalidad_iddetalle_seq (OID = 20975) : 
--
CREATE SEQUENCE public.detalleplanificacioncalidad_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanprocedimientos_iddetalle_seq (OID = 20986) : 
--
CREATE SEQUENCE public.detalleplanprocedimientos_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanprocesoscalidad_iddetalle_seq (OID = 20997) : 
--
CREATE SEQUENCE public.detalleplanprocesoscalidad_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallepresupuesto_iddetalle_seq (OID = 21008) : 
--
CREATE SEQUENCE public.detallepresupuesto_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleproducto_iddetalle_seq (OID = 21019) : 
--
CREATE SEQUENCE public.detalleproducto_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleproductoreal_iddetalle_seq (OID = 21030) : 
--
CREATE SEQUENCE public.detalleproductoreal_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamocliente_iddetalle_seq (OID = 21041) : 
--
CREATE SEQUENCE public.detallereclamocliente_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamoempresametalurgica_iddetalle_seq (OID = 21052) : 
--
CREATE SEQUENCE public.detallereclamoempresametalurgica_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamoproveedor_iddetalle_seq (OID = 21063) : 
--
CREATE SEQUENCE public.detallereclamoproveedor_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleremito_iddetalle_seq (OID = 21074) : 
--
CREATE SEQUENCE public.detalleremito_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallerequerimientosmateriaprima_iddetalle_seq (OID = 21085) : 
--
CREATE SEQUENCE public.detallerequerimientosmateriaprima_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalletrabajotercerizado_iddetalle_seq (OID = 21096) : 
--
CREATE SEQUENCE public.detalletrabajotercerizado_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence domicilio_iddomicilio_seq (OID = 21107) : 
--
CREATE SEQUENCE public.domicilio_iddomicilio_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionetapaproduccion_idejecucion_seq (OID = 21156) : 
--
CREATE SEQUENCE public.ejecucionetapaproduccion_idejecucion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionplanificacioncalidad_idejecucion_seq (OID = 21181) : 
--
CREATE SEQUENCE public.ejecucionplanificacioncalidad_idejecucion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionplanificacionproduccion_idejecucion_seq (OID = 21199) : 
--
CREATE SEQUENCE public.ejecucionplanificacionproduccion_idejecucion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionprocesocalidad_idejecucion_seq (OID = 21217) : 
--
CREATE SEQUENCE public.ejecucionprocesocalidad_idejecucion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence empleado_idempleado_seq (OID = 21235) : 
--
CREATE SEQUENCE public.empleado_idempleado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence empresametalurgica_idempresametalurgica_seq (OID = 21269) : 
--
CREATE SEQUENCE public.empresametalurgica_idempresametalurgica_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadocliente_idestado_seq (OID = 21283) : 
--
CREATE SEQUENCE public.estadocliente_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadocompra_idestado_seq (OID = 21297) : 
--
CREATE SEQUENCE public.estadocompra_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadodetallecompra_idestado_seq (OID = 21311) : 
--
CREATE SEQUENCE public.estadodetallecompra_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadodetalletrabajotercerizado_idestado_seq (OID = 21325) : 
--
CREATE SEQUENCE public.estadodetalletrabajotercerizado_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecetapaprod_idestado_seq (OID = 21339) : 
--
CREATE SEQUENCE public.estadoejecetapaprod_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecplancalidad_idestado_seq (OID = 21353) : 
--
CREATE SEQUENCE public.estadoejecplancalidad_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecplanifpedido_idestado_seq (OID = 21367) : 
--
CREATE SEQUENCE public.estadoejecplanifpedido_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecucionprocesocalidad_idestado_seq (OID = 21381) : 
--
CREATE SEQUENCE public.estadoejecucionprocesocalidad_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadofactura_idestado_seq (OID = 21395) : 
--
CREATE SEQUENCE public.estadofactura_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadomaquina_idestado_seq (OID = 21409) : 
--
CREATE SEQUENCE public.estadomaquina_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadopedido_idestado_seq (OID = 21423) : 
--
CREATE SEQUENCE public.estadopedido_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadopiezareal_idestado_seq (OID = 21437) : 
--
CREATE SEQUENCE public.estadopiezareal_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoproductoreal_idestado_seq (OID = 21451) : 
--
CREATE SEQUENCE public.estadoproductoreal_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoremito_idestado_seq (OID = 21465) : 
--
CREATE SEQUENCE public.estadoremito_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadotrabajotercerizado_idestado_seq (OID = 21479) : 
--
CREATE SEQUENCE public.estadotrabajotercerizado_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence etapadeproduccion_idetapaproduccion_seq (OID = 21493) : 
--
CREATE SEQUENCE public.etapadeproduccion_idetapaproduccion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence factura_idfactura_seq (OID = 21522) : 
--
CREATE SEQUENCE public.factura_idfactura_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence formadepago_idformapago_seq (OID = 21546) : 
--
CREATE SEQUENCE public.formadepago_idformapago_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence localidad_idlocalidad_seq (OID = 21565) : 
--
CREATE SEQUENCE public.localidad_idlocalidad_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence mantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 21579) : 
--
CREATE SEQUENCE public.mantenimientocorrectivo_idmantenimientocorrectivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence mantenimientopreventivo_idmantenimientopreventivo_seq (OID = 21593) : 
--
CREATE SEQUENCE public.mantenimientopreventivo_idmantenimientopreventivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence maquina_idmaquina_seq (OID = 21607) : 
--
CREATE SEQUENCE public.maquina_idmaquina_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence marca_idmarca_seq (OID = 21641) : 
--
CREATE SEQUENCE public.marca_idmarca_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence materiaprima_idmateriaprima_seq (OID = 21655) : 
--
CREATE SEQUENCE public.materiaprima_idmateriaprima_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence matriz_idmatriz_seq (OID = 21689) : 
--
CREATE SEQUENCE public.matriz_idmatriz_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pedido_idpedido_seq (OID = 21708) : 
--
CREATE SEQUENCE public.pedido_idpedido_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pedidomatriz_idpedidomatriz_seq (OID = 21758) : 
--
CREATE SEQUENCE public.pedidomatriz_idpedidomatriz_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pieza_idpieza_seq (OID = 21767) : 
--
CREATE SEQUENCE public.pieza_idpieza_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence piezareal_idpiezareal_seq (OID = 21821) : 
--
CREATE SEQUENCE public.piezareal_idpiezareal_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planificacioncalidad_idplanificacion_seq (OID = 21859) : 
--
CREATE SEQUENCE public.planificacioncalidad_idplanificacion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planificacionproduccion_idplanificacionproduccion_seq (OID = 21878) : 
--
CREATE SEQUENCE public.planificacionproduccion_idplanificacionproduccion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence plano_idplano_seq (OID = 21897) : 
--
CREATE SEQUENCE public.plano_idplano_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planprocedimientos_idplanprocedimientos_seq (OID = 21914) : 
--
CREATE SEQUENCE public.planprocedimientos_idplanprocedimientos_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planprocesoscalidad_idplanprocesoscalidad_seq (OID = 21933) : 
--
CREATE SEQUENCE public.planprocesoscalidad_idplanprocesoscalidad_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planrequerimientosmateriaprima_idplanrequerimientosmateriaprima (OID = 21952) : 
--
CREATE SEQUENCE public.planrequerimientosmateriaprima_idplanrequerimientosmateriaprima
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence presupuesto_idpresupuesto_seq (OID = 21971) : 
--
CREATE SEQUENCE public.presupuesto_idpresupuesto_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence prioridad_idprioridad_seq (OID = 21990) : 
--
CREATE SEQUENCE public.prioridad_idprioridad_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence privilegio_idprivilegio_seq (OID = 22004) : 
--
CREATE SEQUENCE public.privilegio_idprivilegio_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence procesocalidad_idprocesocalidad_seq (OID = 22018) : 
--
CREATE SEQUENCE public.procesocalidad_idprocesocalidad_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence producto_idproducto_seq (OID = 22047) : 
--
CREATE SEQUENCE public.producto_idproducto_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence productoreal_idproductoreal_seq (OID = 22081) : 
--
CREATE SEQUENCE public.productoreal_idproductoreal_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence proveedor_idproveedor_seq (OID = 22095) : 
--
CREATE SEQUENCE public.proveedor_idproveedor_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence proveedormantenimientomaquina_idproveedormantenimiento_seq (OID = 22119) : 
--
CREATE SEQUENCE public.proveedormantenimientomaquina_idproveedormantenimiento_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence provincia_idprovincia_seq (OID = 22138) : 
--
CREATE SEQUENCE public.provincia_idprovincia_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamocliente_idreclamo_seq (OID = 22152) : 
--
CREATE SEQUENCE public.reclamocliente_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamoempresametalurgica_idreclamo_seq (OID = 22166) : 
--
CREATE SEQUENCE public.reclamoempresametalurgica_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamoproveedor_idreclamo_seq (OID = 22180) : 
--
CREATE SEQUENCE public.reclamoproveedor_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence remito_idremito_seq (OID = 22194) : 
--
CREATE SEQUENCE public.remito_idremito_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence responsable_idresponsable_seq (OID = 22208) : 
--
CREATE SEQUENCE public.responsable_idresponsable_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence rol_idrol_seq (OID = 22237) : 
--
CREATE SEQUENCE public.rol_idrol_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence rotura_idrotura_seq (OID = 22256) : 
--
CREATE SEQUENCE public.rotura_idrotura_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence servicio_idservicio_seq (OID = 22270) : 
--
CREATE SEQUENCE public.servicio_idservicio_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence sesion_idsesion_seq (OID = 22284) : 
--
CREATE SEQUENCE public.sesion_idsesion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipodocumento_idtipodocumento_seq (OID = 22293) : 
--
CREATE SEQUENCE public.tipodocumento_idtipodocumento_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipoiva_idtipoiva_seq (OID = 22312) : 
--
CREATE SEQUENCE public.tipoiva_idtipoiva_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipomaquina_idtipomaquina_seq (OID = 22326) : 
--
CREATE SEQUENCE public.tipomaquina_idtipomaquina_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tiporeclamo_idtiporeclamo_seq (OID = 22340) : 
--
CREATE SEQUENCE public.tiporeclamo_idtiporeclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence trabajotercerizado_idtrabajo_seq (OID = 22364) : 
--
CREATE SEQUENCE public.trabajotercerizado_idtrabajo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence turno_idturno_seq (OID = 22383) : 
--
CREATE SEQUENCE public.turno_idturno_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for index pedido_nropedido_key (OID = 19574) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_nropedido_key UNIQUE (nropedido);
--
-- Definition for index piezareal_idpieza_key (OID = 19973) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_idpieza_key UNIQUE (idpieza);
--
-- Definition for index ejecucionplanificacionproduccion_idplanificacionproduccion_key (OID = 19989) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_idplanificacionproduccion_key UNIQUE (idplanificacionproduccion);
--
-- Definition for index ejecucionplanificacioncalidad_idplanificacioncalidad_key (OID = 20012) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_idplanificacioncalidad_key UNIQUE (idplanificacioncalidad);
--
-- Definition for index ejecucionprocesocalidad_idprocesocalidad_key (OID = 20028) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_idprocesocalidad_key UNIQUE (idprocesocalidad);
--
-- Definition for index ejecucionetapaproduccion_idetapaproduccion_key (OID = 20039) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_idetapaproduccion_key UNIQUE (idetapaproduccion);
--
-- Definition for index asistencia_pkey (OID = 20068) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_pkey PRIMARY KEY (empleado, fechaingreso, horaingreso);
--
-- Definition for index asistencia_empleado_key (OID = 20070) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_empleado_key UNIQUE (empleado);
--
-- Definition for index asistencia_fechaingreso_key (OID = 20072) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_fechaingreso_key UNIQUE (fechaingreso);
--
-- Definition for index asistencia_horaingreso_key (OID = 20074) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_horaingreso_key UNIQUE (horaingreso);
--
-- Definition for index proveedorxmateriaprima_idx (OID = 20322) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_idx PRIMARY KEY (idmateriaprima, idproveedor);
--
-- Definition for index maquinaxejecucionetapaproduccion_idx (OID = 20334) : 
--
ALTER TABLE ONLY maquinaxejecucionetapaproduccion
    ADD CONSTRAINT maquinaxejecucionetapaproduccion_idx PRIMARY KEY (idejecucionetapaproduccion, idetapaproduccion, idmaquina);
--
-- Definition for index maquinaxprocesocalidad_idx (OID = 20346) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT maquinaxprocesocalidad_idx PRIMARY KEY (idprocesocalidad, idmaquina);
--
-- Definition for index piezaxetapadeproduccion_idx (OID = 20358) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT piezaxetapadeproduccion_idx PRIMARY KEY (idpieza, idetapaproduccion);
--
-- Definition for index empleadoxturno_idx (OID = 20370) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_idx PRIMARY KEY (idempleado, idturno);
--
-- Definition for index usuarioxrol_pkey (OID = 20398) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_pkey PRIMARY KEY (idrol, idusuario);
--
-- Definition for index rolxprivilegio_pkey (OID = 20400) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_pkey PRIMARY KEY (idrol, idprivilegio);
--
-- Definition for index prueba_pkey (OID = 20634) : 
--
ALTER TABLE ONLY prueba
    ADD CONSTRAINT prueba_pkey PRIMARY KEY (id);
--
-- Definition for index usuario_pkey (OID = 20646) : 
--
ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (idusuario);
--
-- Definition for index factura_fk2 (OID = 20648) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk2 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index comprobantepago_fk1 (OID = 20653) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk1 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index cliente_fk2 (OID = 20658) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk2 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index empleado_fk3 (OID = 20663) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk3 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index sesion_fk (OID = 20668) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT sesion_fk FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index usuarioxrol_fk1 (OID = 20673) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_fk1 FOREIGN KEY (idusuario) REFERENCES usuario(idusuario);
--
-- Definition for index tipomaterial_pkey (OID = 20685) : 
--
ALTER TABLE ONLY tipomaterial
    ADD CONSTRAINT tipomaterial_pkey PRIMARY KEY (idtipomaterial);
--
-- Definition for index accioncalidad_pkey (OID = 20698) : 
--
ALTER TABLE ONLY accioncalidad
    ADD CONSTRAINT accioncalidad_pkey PRIMARY KEY (idaccioncalidad);
--
-- Definition for index procesocalidad_fk (OID = 20700) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT procesocalidad_fk FOREIGN KEY (accioncalidad) REFERENCES accioncalidad(idaccioncalidad);
--
-- Definition for index barrio_pkey (OID = 20712) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT barrio_pkey PRIMARY KEY (idbarrio);
--
-- Definition for index domicilio_fk (OID = 20714) : 
--
ALTER TABLE ONLY domicilio
    ADD CONSTRAINT domicilio_fk FOREIGN KEY (barrio) REFERENCES barrio(idbarrio);
--
-- Definition for index cargo_pkey (OID = 20726) : 
--
ALTER TABLE ONLY cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (idcargo);
--
-- Definition for index empleado_fk4 (OID = 20728) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk4 FOREIGN KEY (cargo) REFERENCES cargo(idcargo);
--
-- Definition for index categoria_pkey (OID = 20740) : 
--
ALTER TABLE ONLY categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (idcategoria);
--
-- Definition for index empleado_fk2 (OID = 20742) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk2 FOREIGN KEY (categoria) REFERENCES categoria(idcategoria);
--
-- Definition for index cliente_pkey (OID = 20754) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (idcliente);
--
-- Definition for index pedido_fk4 (OID = 20756) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk4 FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index reclamocliente_fk1 (OID = 20761) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_fk1 FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index codigodebarra_pkey (OID = 20773) : 
--
ALTER TABLE ONLY codigodebarra
    ADD CONSTRAINT codigodebarra_pkey PRIMARY KEY (idcodigo);
--
-- Definition for index materiaprima_fk1 (OID = 20775) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk1 FOREIGN KEY (codbarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index producto_fk (OID = 20780) : 
--
ALTER TABLE ONLY producto
    ADD CONSTRAINT producto_fk FOREIGN KEY (codbarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index productoreal_fk2 (OID = 20785) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk2 FOREIGN KEY (codigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index compra_pkey (OID = 20797) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_pkey PRIMARY KEY (idcompra);
--
-- Definition for index detallecompra_fk (OID = 20799) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk FOREIGN KEY (idcompra) REFERENCES compra(idcompra);
--
-- Definition for index reclamoproveedor_fk1 (OID = 20804) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_fk1 FOREIGN KEY (compra) REFERENCES compra(idcompra);
--
-- Definition for index comprobantepago_pkey (OID = 20816) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_pkey PRIMARY KEY (idcomprobantepago);
--
-- Definition for index condicioniva_pkey (OID = 20825) : 
--
ALTER TABLE ONLY condicioniva
    ADD CONSTRAINT condicioniva_pkey PRIMARY KEY (idcondicioniva);
--
-- Definition for index cliente_fk5 (OID = 20827) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk5 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index empresametalurgica_fk2 (OID = 20832) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk2 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index proveedormantenimientomaquina_fk2 (OID = 20837) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk2 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index proveedor_fk2 (OID = 20842) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk2 FOREIGN KEY (condicion) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index detallaplanificacionproduccion_idx (OID = 20854) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_idx PRIMARY KEY (iddetalle, idplanificacionproduccion);
--
-- Definition for index detallaplanificacionproduccion_iddetalle_key (OID = 20861) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallecompra_idx (OID = 20865) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_idx PRIMARY KEY (iddetalle, idcompra);
--
-- Definition for index detallereclamoproveedor_fk1 (OID = 20867) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_fk1 FOREIGN KEY (iddetallecompra, idcompra) REFERENCES detallecompra(iddetalle, idcompra);
--
-- Definition for index detallecompra_iddetalle_key (OID = 20877) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleejecucionplanificacion_idx (OID = 20881) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_idx PRIMARY KEY (iddetalle, idejecucionplanificacionproduccion, idplanificacionproduccion);
--
-- Definition for index detallaplanificacionproduccion_fk1 (OID = 20883) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk1 FOREIGN KEY (iddetalleejecucionplanificacion, idejecucionplanificacionproduccion, idplanificacionproduccion) REFERENCES detalleejecucionplanificacion(iddetalle, idejecucionplanificacionproduccion, idplanificacionproduccion);
--
-- Definition for index detalleejecucionplanificacion_iddetalle_key (OID = 20893) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleejecucionplanificacioncalidad_idx (OID = 20897) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_idx PRIMARY KEY (iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad);
--
-- Definition for index detalleplanificacioncalidad_fk1 (OID = 20899) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk1 FOREIGN KEY (idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, idejecucionplanificacioncalidad) REFERENCES detalleejecucionplanificacioncalidad(iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad);
--
-- Definition for index detalleejecucionplanificacioncalidad_iddetalle_key (OID = 20909) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallefactura_idx (OID = 20913) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_idx PRIMARY KEY (iddetalle, idfactura);
--
-- Definition for index detallefactura_iddetalle_key (OID = 20920) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallemantenimientocorrectivo_pkey (OID = 20924) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_pkey PRIMARY KEY (idmantenimientocorrectivo, iddetalle);
--
-- Definition for index detallemantenimientocorrectivo_idmantenimientocorrectivo_key (OID = 20936) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_idmantenimientocorrectivo_key UNIQUE (idmantenimientocorrectivo);
--
-- Definition for index detallemantenimientopreventivo_pkey (OID = 20940) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_pkey PRIMARY KEY (idmantenimientopreventivo, iddetalle);
--
-- Definition for index detallemantenimientopreventivo_idmantenimientopreventivo_key (OID = 20952) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_idmantenimientopreventivo_key UNIQUE (idmantenimientopreventivo);
--
-- Definition for index detallepedido_idx (OID = 20956) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_idx PRIMARY KEY (iddetalle, idpedido);
--
-- Definition for index detallefactura_fk2 (OID = 20958) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_fk2 FOREIGN KEY (idpedido, iddetallepedido) REFERENCES detallepedido(iddetalle, idpedido);
--
-- Definition for index detallepresupuesto_fk1 (OID = 20963) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_fk1 FOREIGN KEY (iddetallepedido, idpedido) REFERENCES detallepedido(iddetalle, idpedido);
--
-- Definition for index detallepedido_iddetalle_key (OID = 20973) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleplanificacioncalidad_idx (OID = 20977) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_idx PRIMARY KEY (iddetalle, idplanificacioncalidad);
--
-- Definition for index detalleplanificacioncalidad_iddetalle_key (OID = 20984) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleplanprocedimientos_idx (OID = 20988) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_idx PRIMARY KEY (iddetalle, idplanpprocedimientos);
--
-- Definition for index detalleplanprocedimientos_iddetalle_key (OID = 20995) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleplanprocesoscalidad_idx (OID = 20999) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_idx PRIMARY KEY (iddetalle, idplanprocesoscalidad);
--
-- Definition for index detalleplanprocesoscalidad_iddetalle_key (OID = 21006) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallepresupuesto_idx (OID = 21010) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_idx PRIMARY KEY (iddetalle, idpresupuesto);
--
-- Definition for index detallepresupuesto_iddetalle_key (OID = 21017) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleproducto_idx (OID = 21021) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_idx PRIMARY KEY (iddetalle, idproducto);
--
-- Definition for index detalleproducto_iddetalle_key (OID = 21028) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleproductoreal_idx (OID = 21032) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_idx PRIMARY KEY (iddetalle, idproductoreal);
--
-- Definition for index detalleproductoreal_iddetalle_key (OID = 21039) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamocliente_idx (OID = 21043) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamocliente_iddetalle_key (OID = 21050) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamoempresametalurgica_idx (OID = 21054) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamoempresametalurgica_iddetalle_key (OID = 21061) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamoproveedor_idx (OID = 21065) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamoproveedor_iddetalle_key (OID = 21072) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleremito_idx (OID = 21076) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_idx PRIMARY KEY (iddetalle, idremito);
--
-- Definition for index detalleremito_iddetalle_key (OID = 21083) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallerequerimientosmateriaprima_idx (OID = 21087) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_idx PRIMARY KEY (iddetalle, idplanrequerimientosmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_iddetalle_key (OID = 21094) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalletrabajotercerizado_idx (OID = 21098) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_idx PRIMARY KEY (iddetalle, idtrabajotercerizado);
--
-- Definition for index detalletrabajotercerizado_iddetalle_key (OID = 21105) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index domicilio_pkey (OID = 21109) : 
--
ALTER TABLE ONLY domicilio
    ADD CONSTRAINT domicilio_pkey PRIMARY KEY (iddomicilio);
--
-- Definition for index cliente_fk4 (OID = 21111) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk4 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index empresametalurgica_fk1 (OID = 21116) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index responsable_fk (OID = 21121) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_fk FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index proveedormantenimientomaquina_fk1 (OID = 21126) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index empleado_fk (OID = 21131) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index proveedor_fk1 (OID = 21136) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index ejecucionetapaproduccion_nroejecucion_key (OID = 21158) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_nroejecucion_key UNIQUE (idejecucion);
--
-- Definition for index ejecucionetapaproduccion_pkey (OID = 21160) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_pkey PRIMARY KEY (idejecucion, idetapaproduccion);
--
-- Definition for index detalleejecucionplanificacion_fk1 (OID = 21162) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk1 FOREIGN KEY (ejecucionetapa, idetapaproduccion) REFERENCES ejecucionetapaproduccion(idejecucion, idetapaproduccion);
--
-- Definition for index maquinaxejecucionetapaproduccion_fk (OID = 21167) : 
--
ALTER TABLE ONLY maquinaxejecucionetapaproduccion
    ADD CONSTRAINT maquinaxejecucionetapaproduccion_fk FOREIGN KEY (idejecucionetapaproduccion, idetapaproduccion) REFERENCES ejecucionetapaproduccion(idejecucion, idetapaproduccion);
--
-- Definition for index ejecucionetapaproduccion_nroejecucion_key1 (OID = 21179) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_nroejecucion_key1 UNIQUE (nroejecucion);
--
-- Definition for index ejecucionplanificacioncalidad_idejecucion_key (OID = 21183) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_idejecucion_key UNIQUE (idejecucion);
--
-- Definition for index ejecucionplanificacioncalidad_pkey (OID = 21185) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_pkey PRIMARY KEY (idejecucion, idplanificacioncalidad);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk (OID = 21187) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk FOREIGN KEY (idejecucionplanificacioncalidad, idplanificacioncalidad) REFERENCES ejecucionplanificacioncalidad(idejecucion, idplanificacioncalidad);
--
-- Definition for index ejecucionplanificacionproduccion_idejecucion_key (OID = 21201) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_idejecucion_key UNIQUE (idejecucion);
--
-- Definition for index ejecucionplanificacionproduccion_pkey (OID = 21203) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_pkey PRIMARY KEY (idejecucion, idplanificacionproduccion);
--
-- Definition for index detalleejecucionplanificacion_fk (OID = 21205) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk FOREIGN KEY (idejecucionplanificacionproduccion, idplanificacionproduccion) REFERENCES ejecucionplanificacionproduccion(idejecucion, idplanificacionproduccion);
--
-- Definition for index ejecucionprocesocalidad_nroejecucion_key (OID = 21219) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_nroejecucion_key UNIQUE (idejecucion);
--
-- Definition for index ejecucionprocesocalidad_pkey (OID = 21221) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_pkey PRIMARY KEY (idejecucion, idprocesocalidad);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk1 (OID = 21223) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk1 FOREIGN KEY (ejecucionprocesocalidad, idprocesocalidad) REFERENCES ejecucionprocesocalidad(idejecucion, idprocesocalidad);
--
-- Definition for index empleado_pkey (OID = 21237) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (idempleado);
--
-- Definition for index mantenimientocorrectivo_fk (OID = 21239) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_fk FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index ejecucionetapaproduccion_fk1 (OID = 21244) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk1 FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index asistencia_fk (OID = 21249) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_fk FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index detallaplanificacionproduccion_fk3 (OID = 21254) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk3 FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index empleadoxturno_fk (OID = 21259) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_fk FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index empresametalurgica_pkey (OID = 21271) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_pkey PRIMARY KEY (idempresametalurgica);
--
-- Definition for index trabajotercerizado_fk1 (OID = 21273) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk1 FOREIGN KEY (empresa) REFERENCES empresametalurgica(idempresametalurgica);
--
-- Definition for index estadocliente_pkey (OID = 21285) : 
--
ALTER TABLE ONLY estadocliente
    ADD CONSTRAINT estadocliente_pkey PRIMARY KEY (idestado);
--
-- Definition for index cliente_fk1 (OID = 21287) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk1 FOREIGN KEY (estado) REFERENCES estadocliente(idestado);
--
-- Definition for index estadocompra_pkey (OID = 21299) : 
--
ALTER TABLE ONLY estadocompra
    ADD CONSTRAINT estadocompra_pkey PRIMARY KEY (idestado);
--
-- Definition for index compra_fk (OID = 21301) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_fk FOREIGN KEY (estado) REFERENCES estadocompra(idestado);
--
-- Definition for index estadodetallecompra_pkey (OID = 21313) : 
--
ALTER TABLE ONLY estadodetallecompra
    ADD CONSTRAINT estadodetallecompra_pkey PRIMARY KEY (idestado);
--
-- Definition for index detallecompra_fk2 (OID = 21315) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk2 FOREIGN KEY (estado) REFERENCES estadodetallecompra(idestado);
--
-- Definition for index estadodetalletrabajotercerizado_pkey (OID = 21327) : 
--
ALTER TABLE ONLY estadodetalletrabajotercerizado
    ADD CONSTRAINT estadodetalletrabajotercerizado_pkey PRIMARY KEY (idestado);
--
-- Definition for index detalletrabajotercerizado_fk3 (OID = 21329) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk3 FOREIGN KEY (estado) REFERENCES estadodetalletrabajotercerizado(idestado);
--
-- Definition for index estadoejecetapaprod_pkey (OID = 21341) : 
--
ALTER TABLE ONLY estadoejecetapaprod
    ADD CONSTRAINT estadoejecetapaprod_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionetapaproduccion_fk2 (OID = 21343) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk2 FOREIGN KEY (estado) REFERENCES estadoejecetapaprod(idestado);
--
-- Definition for index estadoejecplancalidad_pkey (OID = 21355) : 
--
ALTER TABLE ONLY estadoejecplancalidad
    ADD CONSTRAINT estadoejecplancalidad_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionplanificacioncalidad_fk1 (OID = 21357) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_fk1 FOREIGN KEY (estado) REFERENCES estadoejecplancalidad(idestado);
--
-- Definition for index estadoejecplanifpedido_pkey (OID = 21369) : 
--
ALTER TABLE ONLY estadoejecplanifpedido
    ADD CONSTRAINT estadoejecplanifpedido_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionplanificacionproduccion_fk1 (OID = 21371) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_fk1 FOREIGN KEY (estado) REFERENCES estadoejecplanifpedido(idestado);
--
-- Definition for index estadoejecucionprocesocalidad_pkey (OID = 21383) : 
--
ALTER TABLE ONLY estadoejecucionprocesocalidad
    ADD CONSTRAINT estadoejecucionprocesocalidad_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionprocesocalidad_fk1 (OID = 21385) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_fk1 FOREIGN KEY (estado) REFERENCES estadoejecucionprocesocalidad(idestado);
--
-- Definition for index estadofactura_pkey (OID = 21397) : 
--
ALTER TABLE ONLY estadofactura
    ADD CONSTRAINT estadofactura_pkey PRIMARY KEY (idestado);
--
-- Definition for index factura_fk3 (OID = 21399) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk3 FOREIGN KEY (estado) REFERENCES estadofactura(idestado);
--
-- Definition for index estadomaquina_pkey (OID = 21411) : 
--
ALTER TABLE ONLY estadomaquina
    ADD CONSTRAINT estadomaquina_pkey PRIMARY KEY (idestado);
--
-- Definition for index maquina_fk1 (OID = 21413) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk1 FOREIGN KEY (estado) REFERENCES estadomaquina(idestado);
--
-- Definition for index estadopedido_pkey (OID = 21425) : 
--
ALTER TABLE ONLY estadopedido
    ADD CONSTRAINT estadopedido_pkey PRIMARY KEY (idestado);
--
-- Definition for index pedido_fk (OID = 21427) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk FOREIGN KEY (estado) REFERENCES estadopedido(idestado);
--
-- Definition for index estadopiezareal_pkey (OID = 21439) : 
--
ALTER TABLE ONLY estadopiezareal
    ADD CONSTRAINT estadopiezareal_pkey PRIMARY KEY (idestado);
--
-- Definition for index piezareal_fk1 (OID = 21441) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_fk1 FOREIGN KEY (estado) REFERENCES estadopiezareal(idestado);
--
-- Definition for index estadoproductoreal_pkey (OID = 21453) : 
--
ALTER TABLE ONLY estadoproductoreal
    ADD CONSTRAINT estadoproductoreal_pkey PRIMARY KEY (idestado);
--
-- Definition for index productoreal_fk3 (OID = 21455) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk3 FOREIGN KEY (estado) REFERENCES estadoproductoreal(idestado);
--
-- Definition for index estadoremito_pkey (OID = 21467) : 
--
ALTER TABLE ONLY estadoremito
    ADD CONSTRAINT estadoremito_pkey PRIMARY KEY (idestado);
--
-- Definition for index remito_fk1 (OID = 21469) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_fk1 FOREIGN KEY (estado) REFERENCES estadoremito(idestado);
--
-- Definition for index estadotrabajotercerizado_pkey (OID = 21481) : 
--
ALTER TABLE ONLY estadotrabajotercerizado
    ADD CONSTRAINT estadotrabajotercerizado_pkey PRIMARY KEY (idestado);
--
-- Definition for index trabajotercerizado_fk2 (OID = 21483) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk2 FOREIGN KEY (estado) REFERENCES estadotrabajotercerizado(idestado);
--
-- Definition for index etapadeproduccion_pkey (OID = 21495) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_pkey PRIMARY KEY (idetapaproduccion);
--
-- Definition for index ejecucionetapaproduccion_fk (OID = 21497) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detalletrabajotercerizado_fk2 (OID = 21502) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk2 FOREIGN KEY (proceso) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index piezaxetapadeproduccion_fk1 (OID = 21507) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT piezaxetapadeproduccion_fk1 FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detalleplanprocedimientos_fk2 (OID = 21512) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_fk2 FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index factura_pkey (OID = 21524) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_pkey PRIMARY KEY (idfactura);
--
-- Definition for index pedido_fk1 (OID = 21526) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk1 FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index comprobantepago_fk2 (OID = 21531) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk2 FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index detallefactura_fk (OID = 21536) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_fk FOREIGN KEY (idfactura) REFERENCES factura(idfactura);
--
-- Definition for index formadepago_pkey (OID = 21548) : 
--
ALTER TABLE ONLY formadepago
    ADD CONSTRAINT formadepago_pkey PRIMARY KEY (idformapago);
--
-- Definition for index factura_fk1 (OID = 21550) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk1 FOREIGN KEY (formapago) REFERENCES formadepago(idformapago);
--
-- Definition for index comprobantepago_fk (OID = 21555) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk FOREIGN KEY (formadepago) REFERENCES formadepago(idformapago);
--
-- Definition for index localidad_pkey (OID = 21567) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT localidad_pkey PRIMARY KEY (idlocalidad);
--
-- Definition for index barrio_fk (OID = 21569) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT barrio_fk FOREIGN KEY (localidad) REFERENCES localidad(idlocalidad);
--
-- Definition for index mantenimientocorrectivo_pkey (OID = 21581) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_pkey PRIMARY KEY (idmantenimientocorrectivo);
--
-- Definition for index detallemantenimientocorrectivo_fk1 (OID = 21583) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_fk1 FOREIGN KEY (idmantenimientocorrectivo) REFERENCES mantenimientocorrectivo(idmantenimientocorrectivo);
--
-- Definition for index mantenimientopreventivo_pkey (OID = 21595) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_pkey PRIMARY KEY (idmantenimientopreventivo);
--
-- Definition for index detallemantenimientopreventivo_fk (OID = 21597) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_fk FOREIGN KEY (idmantenimientopreventivo) REFERENCES mantenimientopreventivo(idmantenimientopreventivo);
--
-- Definition for index maquina_pkey (OID = 21609) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_pkey PRIMARY KEY (idmaquina);
--
-- Definition for index etapadeproduccion_fk (OID = 21611) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_fk FOREIGN KEY (maquina) REFERENCES maquina(idmaquina);
--
-- Definition for index maquinaxejecucionetapaproduccion_fk1 (OID = 21616) : 
--
ALTER TABLE ONLY maquinaxejecucionetapaproduccion
    ADD CONSTRAINT maquinaxejecucionetapaproduccion_fk1 FOREIGN KEY (idmaquina) REFERENCES maquina(idmaquina);
--
-- Definition for index maquinaxprocesocalidad_fk1 (OID = 21621) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT maquinaxprocesocalidad_fk1 FOREIGN KEY (idmaquina) REFERENCES maquina(idmaquina);
--
-- Definition for index mantenimientocorrectivo_fk2 (OID = 21626) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_fk2 FOREIGN KEY (maquina) REFERENCES maquina(idmaquina);
--
-- Definition for index mantenimientopreventivo_fk1 (OID = 21631) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_fk1 FOREIGN KEY (maquina) REFERENCES maquina(idmaquina);
--
-- Definition for index marca_pkey (OID = 21643) : 
--
ALTER TABLE ONLY marca
    ADD CONSTRAINT marca_pkey PRIMARY KEY (idmarca);
--
-- Definition for index maquina_fk (OID = 21645) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk FOREIGN KEY (marca) REFERENCES marca(idmarca);
--
-- Definition for index materiaprima_pkey (OID = 21657) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_pkey PRIMARY KEY (idmateriaprima);
--
-- Definition for index matriz_fk (OID = 21659) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT matriz_fk FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index pieza_fk1 (OID = 21664) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk1 FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallecompra_fk1 (OID = 21669) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk1 FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index proveedorxmateriaprima_fk1 (OID = 21674) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_fk1 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_fk2 (OID = 21679) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_fk2 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index matriz_pkey (OID = 21691) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT matriz_pkey PRIMARY KEY (idmatriz);
--
-- Definition for index pieza_fk2 (OID = 21693) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk2 FOREIGN KEY (matriz) REFERENCES matriz(idmatriz);
--
-- Definition for index pedidomatriz_fk (OID = 21698) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT pedidomatriz_fk FOREIGN KEY (idmatriz) REFERENCES matriz(idmatriz);
--
-- Definition for index pedido_pkey (OID = 21710) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (idpedido);
--
-- Definition for index plano_fk (OID = 21712) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT plano_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index remito_fk (OID = 21717) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index trabajotercerizado_fk (OID = 21722) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index planificacionproduccion_fk (OID = 21727) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index planificacioncalidad_fk (OID = 21732) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT planificacioncalidad_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index detallepedido_fk (OID = 21737) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_fk FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index productoreal_fk1 (OID = 21742) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk1 FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index detallefactura_fk1 (OID = 21747) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_fk1 FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index pedidomatriz_idx (OID = 21760) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT pedidomatriz_idx PRIMARY KEY (idpedidomatriz);
--
-- Definition for index pieza_pkey (OID = 21769) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_pkey PRIMARY KEY (idpieza);
--
-- Definition for index piezareal_fk (OID = 21771) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_fk FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index detallaplanificacionproduccion_fk2 (OID = 21776) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk2 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleproducto_fk1 (OID = 21781) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_fk1 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalletrabajotercerizado_fk1 (OID = 21786) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk1 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleplanificacioncalidad_fk3 (OID = 21791) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk3 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index piezaxetapadeproduccion_fk (OID = 21796) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT piezaxetapadeproduccion_fk FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index detallerequerimientosmateriaprima_fk1 (OID = 21801) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_fk1 FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleplanprocedimientos_fk1 (OID = 21806) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_fk1 FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleplanprocesoscalidad_fk1 (OID = 21811) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_fk1 FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index piezareal_idpiezareal_key (OID = 21823) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_idpiezareal_key UNIQUE (idpiezareal);
--
-- Definition for index piezareal_pkey (OID = 21825) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_pkey PRIMARY KEY (idpiezareal, idpieza);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk2 (OID = 21827) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk2 FOREIGN KEY (pieza, piezareal) REFERENCES piezareal(idpiezareal, idpieza);
--
-- Definition for index detalleejecucionplanificacion_fk2 (OID = 21832) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk2 FOREIGN KEY (piezareal, pieza) REFERENCES piezareal(idpiezareal, idpieza);
--
-- Definition for index detallereclamoempresametalurgica_fk1 (OID = 21837) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_fk1 FOREIGN KEY (pieza) REFERENCES piezareal(idpiezareal);
--
-- Definition for index detalleproductoreal_fk1 (OID = 21842) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_fk1 FOREIGN KEY (idpieza, idpiezareal) REFERENCES piezareal(idpiezareal, idpieza);
--
-- Definition for index productoreal_fk (OID = 21847) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk FOREIGN KEY (idpieza, idpiezareal) REFERENCES piezareal(idpiezareal, idpieza);
--
-- Definition for index planificacioncalidad_pkey (OID = 21861) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT planificacioncalidad_pkey PRIMARY KEY (idplanificacion);
--
-- Definition for index ejecucionplanificacioncalidad_fk (OID = 21863) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_fk FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index detalleplanificacioncalidad_fk (OID = 21868) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index planificacionproduccion_pkey (OID = 21880) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_pkey PRIMARY KEY (idplanificacionproduccion);
--
-- Definition for index ejecucionplanificacionproduccion_fk (OID = 21882) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_fk FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index detallaplanificacionproduccion_fk (OID = 21887) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index plano_pkey (OID = 21899) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT plano_pkey PRIMARY KEY (idplano);
--
-- Definition for index pedido_fk3 (OID = 21901) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk3 FOREIGN KEY (plano) REFERENCES plano(idplano);
--
-- Definition for index planprocedimientos_pkey (OID = 21916) : 
--
ALTER TABLE ONLY planprocedimientos
    ADD CONSTRAINT planprocedimientos_pkey PRIMARY KEY (idplanprocedimientos);
--
-- Definition for index detalleplanprocedimientos_fk (OID = 21918) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_fk FOREIGN KEY (idplanpprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index pedido_fk5 (OID = 21923) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk5 FOREIGN KEY (planprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index planprocesoscalidad_pkey (OID = 21935) : 
--
ALTER TABLE ONLY planprocesoscalidad
    ADD CONSTRAINT planprocesoscalidad_pkey PRIMARY KEY (idplanprocesoscalidad);
--
-- Definition for index detalleplanprocesoscalidad_fk (OID = 21937) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_fk FOREIGN KEY (idplanprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index pedido_fk7 (OID = 21942) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk7 FOREIGN KEY (planprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index planrequerimientosmateriaprima_pkey (OID = 21954) : 
--
ALTER TABLE ONLY planrequerimientosmateriaprima
    ADD CONSTRAINT planrequerimientosmateriaprima_pkey PRIMARY KEY (idplanrequerimientosmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_fk (OID = 21956) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_fk FOREIGN KEY (idplanrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index pedido_fk6 (OID = 21961) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk6 FOREIGN KEY (planrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index presupuesto_pkey (OID = 21973) : 
--
ALTER TABLE ONLY presupuesto
    ADD CONSTRAINT presupuesto_pkey PRIMARY KEY (idpresupuesto);
--
-- Definition for index pedido_fk2 (OID = 21975) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk2 FOREIGN KEY (presupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index detallepresupuesto_fk (OID = 21980) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_fk FOREIGN KEY (idpresupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index prioridad_pkey (OID = 21992) : 
--
ALTER TABLE ONLY prioridad
    ADD CONSTRAINT prioridad_pkey PRIMARY KEY (idprioridad);
--
-- Definition for index cliente_fk (OID = 21994) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index privilegio_pkey (OID = 22006) : 
--
ALTER TABLE ONLY privilegio
    ADD CONSTRAINT privilegio_pkey PRIMARY KEY (idprivilegio);
--
-- Definition for index rolxprivilegio_fk (OID = 22008) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_fk FOREIGN KEY (idprivilegio) REFERENCES privilegio(idprivilegio);
--
-- Definition for index procesocalidad_pkey (OID = 22020) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT procesocalidad_pkey PRIMARY KEY (idprocesocalidad);
--
-- Definition for index ejecucionprocesocalidad_fk (OID = 22022) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_fk FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detalleplanificacioncalidad_fk2 (OID = 22027) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk2 FOREIGN KEY (procesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index maquinaxprocesocalidad_fk (OID = 22032) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT maquinaxprocesocalidad_fk FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detalleplanprocesoscalidad_fk2 (OID = 22037) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_fk2 FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index producto_pkey (OID = 22049) : 
--
ALTER TABLE ONLY producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (idproducto);
--
-- Definition for index detalleproducto_fk (OID = 22051) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_fk FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index detallepedido_fk1 (OID = 22056) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detalleremito_fk1 (OID = 22061) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detallereclamocliente_fk1 (OID = 22066) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detallepresupuesto_fk2 (OID = 22071) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_fk2 FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index productoreal_idx (OID = 22083) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_idx PRIMARY KEY (idproductoreal);
--
-- Definition for index detalleproductoreal_fk (OID = 22085) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_fk FOREIGN KEY (idproductoreal) REFERENCES productoreal(idproductoreal);
--
-- Definition for index proveedor_pkey (OID = 22097) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_pkey PRIMARY KEY (idproveedor);
--
-- Definition for index materiaprima_fk (OID = 22099) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk FOREIGN KEY (proveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index compra_fk1 (OID = 22104) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_fk1 FOREIGN KEY (proveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index proveedorxmateriaprima_fk (OID = 22109) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_fk FOREIGN KEY (idproveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index proveedormantenimientomaquina_pkey (OID = 22121) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_pkey PRIMARY KEY (idproveedormantenimiento);
--
-- Definition for index mantenimientopreventivo_fk (OID = 22123) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_fk FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index mantenimientocorrectivo_fk1 (OID = 22128) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_fk1 FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index provincia_pkey (OID = 22140) : 
--
ALTER TABLE ONLY provincia
    ADD CONSTRAINT provincia_pkey PRIMARY KEY (idprovincia);
--
-- Definition for index localidad_fk (OID = 22142) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT localidad_fk FOREIGN KEY (provincia) REFERENCES provincia(idprovincia);
--
-- Definition for index reclamocliente_idx (OID = 22154) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_idx PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamocliente_fk (OID = 22156) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_fk FOREIGN KEY (idreclamo) REFERENCES reclamocliente(idreclamo);
--
-- Definition for index reclamoempresametalurgica_pkey (OID = 22168) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_pkey PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamoempresametalurgica_fk (OID = 22170) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_fk FOREIGN KEY (idreclamo) REFERENCES reclamoempresametalurgica(idreclamo);
--
-- Definition for index reclamoproveedor_idx (OID = 22182) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_idx PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamoproveedor_fk (OID = 22184) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_fk FOREIGN KEY (idreclamo) REFERENCES reclamoproveedor(idreclamo);
--
-- Definition for index remito_pkey (OID = 22196) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_pkey PRIMARY KEY (idremito);
--
-- Definition for index detalleremito_fk (OID = 22198) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_fk FOREIGN KEY (idremito) REFERENCES remito(idremito);
--
-- Definition for index responsable_pkey (OID = 22210) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_pkey PRIMARY KEY (idresponsable);
--
-- Definition for index cliente_fk3 (OID = 22212) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk3 FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index empresametalurgica_fk (OID = 22217) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index proveedormantenimientomaquina_fk (OID = 22222) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index proveedor_fk (OID = 22227) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index rol_pkey (OID = 22239) : 
--
ALTER TABLE ONLY rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (idrol);
--
-- Definition for index rolxprivilegio_fk1 (OID = 22241) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_fk1 FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index usuarioxrol_fk (OID = 22246) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_fk FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index rotura_pkey (OID = 22258) : 
--
ALTER TABLE ONLY rotura
    ADD CONSTRAINT rotura_pkey PRIMARY KEY (idrotura);
--
-- Definition for index detallemantenimientocorrectivo_fk (OID = 22260) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_fk FOREIGN KEY (rotura) REFERENCES rotura(idrotura);
--
-- Definition for index servicio_pkey (OID = 22272) : 
--
ALTER TABLE ONLY servicio
    ADD CONSTRAINT servicio_pkey PRIMARY KEY (idservicio);
--
-- Definition for index detallemantenimientopreventivo_fk1 (OID = 22274) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_fk1 FOREIGN KEY (servicio) REFERENCES servicio(idservicio);
--
-- Definition for index sesion_pkey (OID = 22286) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT sesion_pkey PRIMARY KEY (idsesion);
--
-- Definition for index tipodocumento_pkey (OID = 22295) : 
--
ALTER TABLE ONLY tipodocumento
    ADD CONSTRAINT tipodocumento_pkey PRIMARY KEY (idtipodocumento);
--
-- Definition for index responsable_fk1 (OID = 22297) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_fk1 FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index empleado_fk1 (OID = 22302) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk1 FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index tipoiva_pkey (OID = 22314) : 
--
ALTER TABLE ONLY tipoiva
    ADD CONSTRAINT tipoiva_pkey PRIMARY KEY (idtipoiva);
--
-- Definition for index factura_fk (OID = 22316) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk FOREIGN KEY (tipoiva) REFERENCES tipoiva(idtipoiva);
--
-- Definition for index tipomaquina_pkey (OID = 22328) : 
--
ALTER TABLE ONLY tipomaquina
    ADD CONSTRAINT tipomaquina_pkey PRIMARY KEY (idtipomaquina);
--
-- Definition for index maquina_fk2 (OID = 22330) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk2 FOREIGN KEY (tipomaquina) REFERENCES tipomaquina(idtipomaquina);
--
-- Definition for index tiporeclamo_pkey (OID = 22342) : 
--
ALTER TABLE ONLY tiporeclamo
    ADD CONSTRAINT tiporeclamo_pkey PRIMARY KEY (idtiporeclamo);
--
-- Definition for index reclamoempresametalurgica_fk (OID = 22344) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index reclamoproveedor_fk (OID = 22349) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index reclamocliente_fk (OID = 22354) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index trabajotercerizado_pkey (OID = 22366) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_pkey PRIMARY KEY (idtrabajo);
--
-- Definition for index detalletrabajotercerizado_fk (OID = 22368) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk FOREIGN KEY (idtrabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index reclamoempresametalurgica_fk1 (OID = 22373) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_fk1 FOREIGN KEY (trabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index turno_pkey (OID = 22385) : 
--
ALTER TABLE ONLY turno
    ADD CONSTRAINT turno_pkey PRIMARY KEY (idturno);
--
-- Definition for index empleadoxturno_fk1 (OID = 22387) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_fk1 FOREIGN KEY (idturno) REFERENCES turno(idturno);
--
-- Comments
--
COMMENT ON SCHEMA public IS 'standard public schema';
