-- SQL Manager 2007 for PostgreSQL 4.5.0.8
-- ---------------------------------------
-- Host      : localhost
-- Database  : metalsoft
-- Version   : PostgreSQL 8.3.11, compiled by Visual C++ build 1400



--
-- Definition for language plpgsql (OID = 16386) : 
--
CREATE TRUSTED PROCEDURAL LANGUAGE plpgsql
   HANDLER "plpgsql_call_handler"
   VALIDATOR "pg_catalog"."plpgsql_validator";
SET check_function_bodies = false;
--
-- Structure for table pedido (OID = 23597) : 
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
    idpedido bigint DEFAULT nextval(('"public"."pedido_idpedido_seq"'::text)::regclass) NOT NULL,
    cliente bigint,
    planprocedimientos bigint,
    planrequerimientosmateriaprima bigint,
    planprocesoscalidad bigint
) WITH OIDS;
--
-- Structure for table planificacioncalidad (OID = 23601) : 
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
-- Structure for table estadopedido (OID = 23605) : 
--
CREATE TABLE public.estadopedido (
    idestado bigint DEFAULT nextval(('"public"."estadopedido_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table factura (OID = 23609) : 
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
-- Structure for table planificacionproduccion (OID = 23613) : 
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
-- Structure for table presupuesto (OID = 23617) : 
--
CREATE TABLE public.presupuesto (
    idpresupuesto bigint DEFAULT nextval(('"public"."presupuesto_idpresupuesto_seq"'::text)::regclass) NOT NULL,
    fechapresupuesto date,
    montototal double precision,
    fechavencimiento date
) WITH OIDS;
--
-- Structure for table plano (OID = 23621) : 
--
CREATE TABLE public.plano (
    idplano bigint DEFAULT nextval(('"public"."plano_idplano_seq"'::text)::regclass) NOT NULL,
    nroplano bigint,
    escala integer,
    pedido bigint,
    imagen bytea[]
) WITH OIDS;
--
-- Structure for table remito (OID = 23628) : 
--
CREATE TABLE public.remito (
    idremito bigint DEFAULT nextval(('"public"."remito_idremito_seq"'::text)::regclass) NOT NULL,
    nroremito bigint,
    fechaemision date,
    pedido bigint,
    estado bigint
) WITH OIDS;
--
-- Structure for table trabajotercerizado (OID = 23632) : 
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
-- Structure for table cliente (OID = 23636) : 
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
-- Structure for table tipoiva (OID = 23640) : 
--
CREATE TABLE public.tipoiva (
    idtipoiva bigint DEFAULT nextval(('"public"."tipoiva_idtipoiva_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table formadepago (OID = 23644) : 
--
CREATE TABLE public.formadepago (
    idformapago bigint DEFAULT nextval(('"public"."formadepago_idformapago_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table usuario (OID = 23648) : 
--
CREATE TABLE public.usuario (
    idusuario bigint DEFAULT nextval(('"public"."usuario_idusuario_seq"'::text)::regclass) NOT NULL,
    usuario character varying(50),
    clave character varying(50)
) WITH OIDS;
--
-- Structure for table estadofactura (OID = 23652) : 
--
CREATE TABLE public.estadofactura (
    idestado bigint DEFAULT nextval(('"public"."estadofactura_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table comprobantepago (OID = 23656) : 
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
-- Structure for table estadotrabajotercerizado (OID = 23660) : 
--
CREATE TABLE public.estadotrabajotercerizado (
    idestado bigint DEFAULT nextval(('"public"."estadotrabajotercerizado_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table empresametalurgica (OID = 23664) : 
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
-- Structure for table prioridad (OID = 23668) : 
--
CREATE TABLE public.prioridad (
    idprioridad bigint DEFAULT nextval(('"public"."prioridad_idprioridad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table estadocliente (OID = 23672) : 
--
CREATE TABLE public.estadocliente (
    idestado bigint DEFAULT nextval(('"public"."estadocliente_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table responsable (OID = 23676) : 
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
-- Structure for table condicioniva (OID = 23680) : 
--
CREATE TABLE public.condicioniva (
    idcondicioniva bigint DEFAULT nextval(('"public"."condicioniva_idcondicioniva_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table domicilio (OID = 23684) : 
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
-- Structure for table rotura (OID = 23688) : 
--
CREATE TABLE public.rotura (
    idrotura bigint DEFAULT nextval(('"public"."rotura_idrotura_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table categoria (OID = 23692) : 
--
CREATE TABLE public.categoria (
    idcategoria bigint DEFAULT nextval(('"public"."categoria_idcategoria_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table cargo (OID = 23696) : 
--
CREATE TABLE public.cargo (
    idcargo bigint DEFAULT nextval(('"public"."cargo_idcargo_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table turno (OID = 23700) : 
--
CREATE TABLE public.turno (
    idturno bigint DEFAULT nextval(('"public"."turno_idturno_seq"'::text)::regclass) NOT NULL,
    horainicio time without time zone,
    horafin time without time zone,
    nombre character varying(20),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table marca (OID = 23704) : 
--
CREATE TABLE public.marca (
    idmarca bigint DEFAULT nextval(('"public"."marca_idmarca_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadomaquina (OID = 23708) : 
--
CREATE TABLE public.estadomaquina (
    idestado bigint DEFAULT nextval(('"public"."estadomaquina_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table tipomaquina (OID = 23712) : 
--
CREATE TABLE public.tipomaquina (
    idtipomaquina bigint DEFAULT nextval(('"public"."tipomaquina_idtipomaquina_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecetapaprod (OID = 23716) : 
--
CREATE TABLE public.estadoejecetapaprod (
    idestado bigint DEFAULT nextval(('"public"."estadoejecetapaprod_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table servicio (OID = 23720) : 
--
CREATE TABLE public.servicio (
    idservicio bigint DEFAULT nextval(('"public"."servicio_idservicio_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table tipodocumento (OID = 23724) : 
--
CREATE TABLE public.tipodocumento (
    idtipodocumento bigint DEFAULT nextval(('"public"."tipodocumento_idtipodocumento_seq"'::text)::regclass) NOT NULL,
    tipo character varying(50),
    nombre character varying(50)
) WITH OIDS;
--
-- Structure for table codigodebarra (OID = 23728) : 
--
CREATE TABLE public.codigodebarra (
    idcodigo bigint DEFAULT nextval(('"public"."codigodebarra_idcodigo_seq"'::text)::regclass) NOT NULL,
    descripcion character varying(50),
    codigo character varying(50)
) WITH OIDS;
--
-- Structure for table estadopiezareal (OID = 23732) : 
--
CREATE TABLE public.estadopiezareal (
    idestado bigint DEFAULT nextval(('"public"."estadopiezareal_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecplanifpedido (OID = 23736) : 
--
CREATE TABLE public.estadoejecplanifpedido (
    idestado bigint DEFAULT nextval(('"public"."estadoejecplanifpedido_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table proveedormantenimientomaquina (OID = 23740) : 
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
-- Structure for table rol (OID = 23744) : 
--
CREATE TABLE public.rol (
    idrol bigint DEFAULT nextval(('"public"."rol_idrol_seq"'::text)::regclass) NOT NULL,
    rol character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table privilegio (OID = 23748) : 
--
CREATE TABLE public.privilegio (
    idprivilegio bigint DEFAULT nextval(('"public"."privilegio_idprivilegio_seq"'::text)::regclass) NOT NULL,
    privilegio character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table accioncalidad (OID = 23752) : 
--
CREATE TABLE public.accioncalidad (
    idaccioncalidad bigint DEFAULT nextval(('"public"."accioncalidad_idaccioncalidad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table empleado (OID = 23756) : 
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
-- Structure for table proveedor (OID = 23760) : 
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
-- Structure for table estadocompra (OID = 23764) : 
--
CREATE TABLE public.estadocompra (
    idestado bigint DEFAULT nextval(('"public"."estadocompra_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table sesion (OID = 23768) : 
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
-- Structure for table materiaprima (OID = 23772) : 
--
CREATE TABLE public.materiaprima (
    idmateriaprima bigint DEFAULT nextval(('"public"."materiaprima_idmateriaprima_seq"'::text)::regclass) NOT NULL,
    codproducto bigint,
    nombre character varying(50),
    fechaalta date,
    fechabaja date,
    codbarra bigint,
    dimensiones character varying(20),
    stock bigint,
    unidaddemedida character varying(20),
    descripcion character varying(100),
    tipomaterial bigint
) WITH OIDS;
--
-- Structure for table matriz (OID = 23776) : 
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
-- Structure for table pieza (OID = 23780) : 
--
CREATE TABLE public.pieza (
    idpieza bigint DEFAULT nextval(('"public"."pieza_idpieza_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    tipomaterial bigint,
    dimensiones character varying(100),
    materiaprima bigint,
    matriz bigint
) WITH OIDS;
--
-- Structure for table producto (OID = 23784) : 
--
CREATE TABLE public.producto (
    idproducto bigint DEFAULT nextval(('"public"."producto_idproducto_seq"'::text)::regclass) NOT NULL,
    nroproducto bigint,
    nombre character varying(50),
    preciounitario double precision,
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecplancalidad (OID = 23788) : 
--
CREATE TABLE public.estadoejecplancalidad (
    idestado bigint DEFAULT nextval(('"public"."estadoejecplancalidad_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table mantenimientopreventivo (OID = 23792) : 
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
-- Structure for table mantenimientocorrectivo (OID = 23796) : 
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
-- Structure for table maquina (OID = 23800) : 
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
-- Structure for table etapadeproduccion (OID = 23804) : 
--
CREATE TABLE public.etapadeproduccion (
    idetapaproduccion bigint DEFAULT nextval(('"public"."etapadeproduccion_idetapaproduccion_seq"'::text)::regclass) NOT NULL,
    nroetapaproduccion bigint,
    nombre character varying(50),
    horasmaquina time without time zone,
    horashombre time without time zone,
    maquina bigint,
    duracionestimada time without time zone,
    fechacreacion date
) WITH OIDS;
--
-- Structure for table piezareal (OID = 23808) : 
--
CREATE TABLE public.piezareal (
    idpiezareal bigint DEFAULT nextval(('"public"."piezareal_idpiezareal_seq"'::text)::regclass) NOT NULL,
    idpieza bigint NOT NULL,
    estado bigint,
    nropieza integer
) WITH OIDS;
--
-- Structure for table ejecucionplanificacionproduccion (OID = 23812) : 
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
-- Structure for table procesocalidad (OID = 23816) : 
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
-- Structure for table ejecucionplanificacioncalidad (OID = 23820) : 
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
-- Structure for table ejecucionprocesocalidad (OID = 23824) : 
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
-- Structure for table ejecucionetapaproduccion (OID = 23828) : 
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
-- Structure for table compra (OID = 23832) : 
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
-- Structure for table asistencia (OID = 23836) : 
--
CREATE TABLE public.asistencia (
    empleado bigint NOT NULL,
    fechaingreso bigint NOT NULL,
    horaingreso time without time zone NOT NULL,
    horaegreso time without time zone,
    observaciones character varying(100)
) WITH OIDS;
--
-- Structure for table detallemantenimientocorrectivo (OID = 23839) : 
--
CREATE TABLE public.detallemantenimientocorrectivo (
    idmantenimientocorrectivo bigint DEFAULT nextval(('"public"."detallemantenimientocorrectivo_idmantenimientocorrectivo_seq"'::text)::regclass) NOT NULL,
    iddetalle bigint NOT NULL,
    duracion time without time zone,
    rotura bigint,
    motivorotura character varying(100)
) WITH OIDS;
--
-- Structure for table detallemantenimientopreventivo (OID = 23843) : 
--
CREATE TABLE public.detallemantenimientopreventivo (
    idmantenimientopreventivo bigint DEFAULT nextval(('"public"."detallemantenimientopreventivo_idmantenimientopreventivo_seq"'::text)::regclass) NOT NULL,
    iddetalle bigint NOT NULL,
    duracion time without time zone,
    servicio bigint,
    observaciones character varying(100)
) WITH OIDS;
--
-- Structure for table detalleejecucionplanificacion (OID = 23847) : 
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
-- Structure for table detalleejecucionplanificacioncalidad (OID = 23851) : 
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
-- Structure for table detallaplanificacionproduccion (OID = 23855) : 
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
-- Structure for table detalleproducto (OID = 23859) : 
--
CREATE TABLE public.detalleproducto (
    iddetalle bigint DEFAULT nextval(('"public"."detalleproducto_iddetalle_seq"'::text)::regclass) NOT NULL,
    idproducto bigint NOT NULL,
    cantidadpiezas integer,
    descripcion character varying(50),
    pieza bigint
) WITH OIDS;
--
-- Structure for table detallepedido (OID = 23863) : 
--
CREATE TABLE public.detallepedido (
    iddetalle bigint DEFAULT nextval(('"public"."detallepedido_iddetalle_seq"'::text)::regclass) NOT NULL,
    idpedido bigint NOT NULL,
    precio double precision,
    cantidad integer,
    producto bigint
) WITH OIDS;
--
-- Structure for table detalletrabajotercerizado (OID = 23867) : 
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
-- Structure for table detalleplanificacioncalidad (OID = 23871) : 
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
-- Structure for table detallecompra (OID = 23875) : 
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
-- Structure for table detalleremito (OID = 23879) : 
--
CREATE TABLE public.detalleremito (
    iddetalle bigint DEFAULT nextval(('"public"."detalleremito_iddetalle_seq"'::text)::regclass) NOT NULL,
    idremito bigint NOT NULL,
    cantidad integer,
    descripcion character varying(50),
    producto bigint
) WITH OIDS;
--
-- Structure for table tiporeclamo (OID = 23883) : 
--
CREATE TABLE public.tiporeclamo (
    idtiporeclamo bigint DEFAULT nextval(('"public"."tiporeclamo_idtiporeclamo_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table reclamoempresametalurgica (OID = 23887) : 
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
-- Structure for table reclamoproveedor (OID = 23891) : 
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
-- Structure for table reclamocliente (OID = 23895) : 
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
-- Structure for table detallereclamocliente (OID = 23899) : 
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
-- Structure for table detallereclamoproveedor (OID = 23903) : 
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
-- Structure for table detallereclamoempresametalurgica (OID = 23907) : 
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
-- Structure for table proveedorxmateriaprima (OID = 23911) : 
--
CREATE TABLE public.proveedorxmateriaprima (
    idproveedor bigint NOT NULL,
    idmateriaprima bigint NOT NULL,
    precio double precision
) WITH OIDS;
--
-- Structure for table maquinaxejecucionetapaproduccion (OID = 23914) : 
--
CREATE TABLE public.maquinaxejecucionetapaproduccion (
    idejecucionetapaproduccion bigint NOT NULL,
    idetapaproduccion bigint NOT NULL,
    idmaquina bigint NOT NULL,
    horasmaquina time without time zone,
    horashombre time without time zone
) WITH OIDS;
--
-- Structure for table maquinaxprocesocalidad (OID = 23917) : 
--
CREATE TABLE public.maquinaxprocesocalidad (
    idprocesocalidad bigint NOT NULL,
    idmaquina bigint NOT NULL,
    duracion time without time zone,
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table piezaxetapadeproduccion (OID = 23920) : 
--
CREATE TABLE public.piezaxetapadeproduccion (
    idpieza bigint NOT NULL,
    idetapaproduccion bigint NOT NULL,
    duracion time without time zone,
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table empleadoxturno (OID = 23923) : 
--
CREATE TABLE public.empleadoxturno (
    idempleado bigint NOT NULL,
    idturno bigint NOT NULL
) WITH OIDS;
--
-- Structure for table provincia (OID = 23926) : 
--
CREATE TABLE public.provincia (
    idprovincia bigint DEFAULT nextval(('"public"."provincia_idprovincia_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50)
) WITH OIDS;
--
-- Structure for table localidad (OID = 23930) : 
--
CREATE TABLE public.localidad (
    idlocalidad bigint DEFAULT nextval(('"public"."localidad_idlocalidad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    provincia bigint
) WITH OIDS;
--
-- Structure for table barrio (OID = 23934) : 
--
CREATE TABLE public.barrio (
    idbarrio bigint DEFAULT nextval(('"public"."barrio_idbarrio_seq"'::text)::regclass) NOT NULL,
    nombre character varying(25),
    codpostal bigint,
    localidad bigint
) WITH OIDS;
--
-- Structure for table usuarioxrol (OID = 23938) : 
--
CREATE TABLE public.usuarioxrol (
    idrol bigint NOT NULL,
    idusuario bigint NOT NULL
) WITH OIDS;
--
-- Structure for table rolxprivilegio (OID = 23941) : 
--
CREATE TABLE public.rolxprivilegio (
    idrol bigint NOT NULL,
    idprivilegio bigint NOT NULL
) WITH OIDS;
--
-- Structure for table planrequerimientosmateriaprima (OID = 23944) : 
--
CREATE TABLE public.planrequerimientosmateriaprima (
    idplanrequerimientosmateriaprima bigint DEFAULT nextval(('"public"."planrequerimientosmateriaprima_idplanrequerimientosmateriaprima_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table planprocedimientos (OID = 23948) : 
--
CREATE TABLE public.planprocedimientos (
    idplanprocedimientos bigint DEFAULT nextval(('"public"."planprocedimientos_idplanprocedimientos_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table planprocesoscalidad (OID = 23952) : 
--
CREATE TABLE public.planprocesoscalidad (
    idplanprocesoscalidad bigint DEFAULT nextval(('"public"."planprocesoscalidad_idplanprocesoscalidad_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table detallerequerimientosmateriaprima (OID = 23956) : 
--
CREATE TABLE public.detallerequerimientosmateriaprima (
    iddetalle bigint DEFAULT nextval(('"public"."detallerequerimientosmateriaprima_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanrequerimientosmateriaprima bigint NOT NULL,
    cantidadmateriaprima integer,
    idpieza bigint,
    idmateriaprima bigint
) WITH OIDS;
--
-- Structure for table detalleplanprocedimientos (OID = 23960) : 
--
CREATE TABLE public.detalleplanprocedimientos (
    iddetalle bigint DEFAULT nextval(('"public"."detalleplanprocedimientos_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanpprocedimientos bigint NOT NULL,
    idpieza bigint,
    idetapaproduccion bigint,
    duracionestimada time without time zone
) WITH OIDS;
--
-- Structure for table detalleplanprocesoscalidad (OID = 23964) : 
--
CREATE TABLE public.detalleplanprocesoscalidad (
    iddetalle bigint DEFAULT nextval(('"public"."detalleplanprocesoscalidad_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanprocesoscalidad bigint NOT NULL,
    idpieza bigint,
    idprocesocalidad bigint,
    duracionestimada time without time zone
) WITH OIDS;
--
-- Structure for table pedidomatriz (OID = 23968) : 
--
CREATE TABLE public.pedidomatriz (
    idpedidomatriz bigint DEFAULT nextval(('"public"."pedidomatriz_idpedidomatriz_seq"'::text)::regclass) NOT NULL,
    nropedidomatriz bigint,
    fechapedidomatriz date,
    idmatriz bigint,
    observaciones character varying(100)
) WITH OIDS;
--
-- Structure for table estadodetallecompra (OID = 23972) : 
--
CREATE TABLE public.estadodetallecompra (
    idestado bigint DEFAULT nextval(('"public"."estadodetallecompra_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadodetalletrabajotercerizado (OID = 23976) : 
--
CREATE TABLE public.estadodetalletrabajotercerizado (
    idestado bigint DEFAULT nextval(('"public"."estadodetalletrabajotercerizado_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecucionprocesocalidad (OID = 23980) : 
--
CREATE TABLE public.estadoejecucionprocesocalidad (
    idestado bigint DEFAULT nextval(('"public"."estadoejecucionprocesocalidad_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table detalleproductoreal (OID = 23984) : 
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
-- Structure for table productoreal (OID = 23988) : 
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
-- Structure for table estadoproductoreal (OID = 23992) : 
--
CREATE TABLE public.estadoproductoreal (
    idestado bigint DEFAULT nextval(('"public"."estadoproductoreal_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table detallefactura (OID = 23996) : 
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
-- Structure for table detallepresupuesto (OID = 24000) : 
--
CREATE TABLE public.detallepresupuesto (
    iddetalle bigint DEFAULT nextval(('"public"."detallepresupuesto_iddetalle_seq"'::text)::regclass) NOT NULL,
    idpresupuesto bigint NOT NULL,
    iddetallepedido bigint,
    idpedido bigint,
    idproducto bigint,
    cantidad integer,
    precio double precision
) WITH OIDS;
--
-- Structure for table estadoremito (OID = 24004) : 
--
CREATE TABLE public.estadoremito (
    idestado bigint DEFAULT nextval(('"public"."estadoremito_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table tipomaterial (OID = 24008) : 
--
CREATE TABLE public.tipomaterial (
    idtipomaterial bigint DEFAULT nextval(('"public"."tipomaterial_idtipomaterial_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Definition for sequence prueba_id_seq (OID = 24012) : 
--
CREATE SEQUENCE public.prueba_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table prueba (OID = 24014) : 
--
CREATE TABLE public.prueba (
    id bigint DEFAULT nextval('prueba_id_seq'::regclass) NOT NULL,
    valor character varying(20)
) WITH OIDS;
ALTER TABLE ONLY public.prueba ALTER COLUMN id SET STATISTICS 0;
ALTER TABLE ONLY public.prueba ALTER COLUMN valor SET STATISTICS 0;
--
-- Definition for sequence usuario_idusuario_seq (OID = 24018) : 
--
CREATE SEQUENCE public.usuario_idusuario_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipomaterial_idtipomaterial_seq (OID = 24020) : 
--
CREATE SEQUENCE public.tipomaterial_idtipomaterial_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence accioncalidad_idaccioncalidad_seq (OID = 24022) : 
--
CREATE SEQUENCE public.accioncalidad_idaccioncalidad_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence barrio_idbarrio_seq (OID = 24024) : 
--
CREATE SEQUENCE public.barrio_idbarrio_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence cargo_idcargo_seq (OID = 24026) : 
--
CREATE SEQUENCE public.cargo_idcargo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence categoria_idcategoria_seq (OID = 24028) : 
--
CREATE SEQUENCE public.categoria_idcategoria_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence cliente_idcliente_seq (OID = 24030) : 
--
CREATE SEQUENCE public.cliente_idcliente_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence codigodebarra_idcodigo_seq (OID = 24032) : 
--
CREATE SEQUENCE public.codigodebarra_idcodigo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence compra_idcompra_seq (OID = 24034) : 
--
CREATE SEQUENCE public.compra_idcompra_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence comprobantepago_idcomprobantepago_seq (OID = 24036) : 
--
CREATE SEQUENCE public.comprobantepago_idcomprobantepago_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence condicioniva_idcondicioniva_seq (OID = 24038) : 
--
CREATE SEQUENCE public.condicioniva_idcondicioniva_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallaplanificacionproduccion_iddetalle_seq (OID = 24040) : 
--
CREATE SEQUENCE public.detallaplanificacionproduccion_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallecompra_iddetalle_seq (OID = 24042) : 
--
CREATE SEQUENCE public.detallecompra_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleejecucionplanificacion_iddetalle_seq (OID = 24044) : 
--
CREATE SEQUENCE public.detalleejecucionplanificacion_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleejecucionplanificacioncalidad_iddetalle_seq (OID = 24046) : 
--
CREATE SEQUENCE public.detalleejecucionplanificacioncalidad_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallefactura_iddetalle_seq (OID = 24048) : 
--
CREATE SEQUENCE public.detallefactura_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallemantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 24050) : 
--
CREATE SEQUENCE public.detallemantenimientocorrectivo_idmantenimientocorrectivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallemantenimientopreventivo_idmantenimientopreventivo_seq (OID = 24052) : 
--
CREATE SEQUENCE public.detallemantenimientopreventivo_idmantenimientopreventivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallepedido_iddetalle_seq (OID = 24054) : 
--
CREATE SEQUENCE public.detallepedido_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanificacioncalidad_iddetalle_seq (OID = 24056) : 
--
CREATE SEQUENCE public.detalleplanificacioncalidad_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanprocedimientos_iddetalle_seq (OID = 24058) : 
--
CREATE SEQUENCE public.detalleplanprocedimientos_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanprocesoscalidad_iddetalle_seq (OID = 24060) : 
--
CREATE SEQUENCE public.detalleplanprocesoscalidad_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallepresupuesto_iddetalle_seq (OID = 24062) : 
--
CREATE SEQUENCE public.detallepresupuesto_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleproducto_iddetalle_seq (OID = 24064) : 
--
CREATE SEQUENCE public.detalleproducto_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleproductoreal_iddetalle_seq (OID = 24066) : 
--
CREATE SEQUENCE public.detalleproductoreal_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamocliente_iddetalle_seq (OID = 24068) : 
--
CREATE SEQUENCE public.detallereclamocliente_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamoempresametalurgica_iddetalle_seq (OID = 24070) : 
--
CREATE SEQUENCE public.detallereclamoempresametalurgica_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamoproveedor_iddetalle_seq (OID = 24072) : 
--
CREATE SEQUENCE public.detallereclamoproveedor_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleremito_iddetalle_seq (OID = 24074) : 
--
CREATE SEQUENCE public.detalleremito_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallerequerimientosmateriaprima_iddetalle_seq (OID = 24076) : 
--
CREATE SEQUENCE public.detallerequerimientosmateriaprima_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalletrabajotercerizado_iddetalle_seq (OID = 24078) : 
--
CREATE SEQUENCE public.detalletrabajotercerizado_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence domicilio_iddomicilio_seq (OID = 24080) : 
--
CREATE SEQUENCE public.domicilio_iddomicilio_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionetapaproduccion_idejecucion_seq (OID = 24082) : 
--
CREATE SEQUENCE public.ejecucionetapaproduccion_idejecucion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionplanificacioncalidad_idejecucion_seq (OID = 24084) : 
--
CREATE SEQUENCE public.ejecucionplanificacioncalidad_idejecucion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionplanificacionproduccion_idejecucion_seq (OID = 24086) : 
--
CREATE SEQUENCE public.ejecucionplanificacionproduccion_idejecucion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionprocesocalidad_idejecucion_seq (OID = 24088) : 
--
CREATE SEQUENCE public.ejecucionprocesocalidad_idejecucion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence empleado_idempleado_seq (OID = 24090) : 
--
CREATE SEQUENCE public.empleado_idempleado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence empresametalurgica_idempresametalurgica_seq (OID = 24092) : 
--
CREATE SEQUENCE public.empresametalurgica_idempresametalurgica_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadocliente_idestado_seq (OID = 24094) : 
--
CREATE SEQUENCE public.estadocliente_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadocompra_idestado_seq (OID = 24096) : 
--
CREATE SEQUENCE public.estadocompra_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadodetallecompra_idestado_seq (OID = 24098) : 
--
CREATE SEQUENCE public.estadodetallecompra_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadodetalletrabajotercerizado_idestado_seq (OID = 24100) : 
--
CREATE SEQUENCE public.estadodetalletrabajotercerizado_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecetapaprod_idestado_seq (OID = 24102) : 
--
CREATE SEQUENCE public.estadoejecetapaprod_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecplancalidad_idestado_seq (OID = 24104) : 
--
CREATE SEQUENCE public.estadoejecplancalidad_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecplanifpedido_idestado_seq (OID = 24106) : 
--
CREATE SEQUENCE public.estadoejecplanifpedido_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecucionprocesocalidad_idestado_seq (OID = 24108) : 
--
CREATE SEQUENCE public.estadoejecucionprocesocalidad_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadofactura_idestado_seq (OID = 24110) : 
--
CREATE SEQUENCE public.estadofactura_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadomaquina_idestado_seq (OID = 24112) : 
--
CREATE SEQUENCE public.estadomaquina_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadopedido_idestado_seq (OID = 24114) : 
--
CREATE SEQUENCE public.estadopedido_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadopiezareal_idestado_seq (OID = 24116) : 
--
CREATE SEQUENCE public.estadopiezareal_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoproductoreal_idestado_seq (OID = 24118) : 
--
CREATE SEQUENCE public.estadoproductoreal_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoremito_idestado_seq (OID = 24120) : 
--
CREATE SEQUENCE public.estadoremito_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadotrabajotercerizado_idestado_seq (OID = 24122) : 
--
CREATE SEQUENCE public.estadotrabajotercerizado_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence etapadeproduccion_idetapaproduccion_seq (OID = 24124) : 
--
CREATE SEQUENCE public.etapadeproduccion_idetapaproduccion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence factura_idfactura_seq (OID = 24126) : 
--
CREATE SEQUENCE public.factura_idfactura_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence formadepago_idformapago_seq (OID = 24128) : 
--
CREATE SEQUENCE public.formadepago_idformapago_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence localidad_idlocalidad_seq (OID = 24130) : 
--
CREATE SEQUENCE public.localidad_idlocalidad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence mantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 24132) : 
--
CREATE SEQUENCE public.mantenimientocorrectivo_idmantenimientocorrectivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence mantenimientopreventivo_idmantenimientopreventivo_seq (OID = 24134) : 
--
CREATE SEQUENCE public.mantenimientopreventivo_idmantenimientopreventivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence maquina_idmaquina_seq (OID = 24136) : 
--
CREATE SEQUENCE public.maquina_idmaquina_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence marca_idmarca_seq (OID = 24138) : 
--
CREATE SEQUENCE public.marca_idmarca_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence materiaprima_idmateriaprima_seq (OID = 24140) : 
--
CREATE SEQUENCE public.materiaprima_idmateriaprima_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence matriz_idmatriz_seq (OID = 24142) : 
--
CREATE SEQUENCE public.matriz_idmatriz_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pedido_idpedido_seq (OID = 24144) : 
--
CREATE SEQUENCE public.pedido_idpedido_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pedidomatriz_idpedidomatriz_seq (OID = 24146) : 
--
CREATE SEQUENCE public.pedidomatriz_idpedidomatriz_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pieza_idpieza_seq (OID = 24148) : 
--
CREATE SEQUENCE public.pieza_idpieza_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence piezareal_idpiezareal_seq (OID = 24150) : 
--
CREATE SEQUENCE public.piezareal_idpiezareal_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planificacioncalidad_idplanificacion_seq (OID = 24152) : 
--
CREATE SEQUENCE public.planificacioncalidad_idplanificacion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planificacionproduccion_idplanificacionproduccion_seq (OID = 24154) : 
--
CREATE SEQUENCE public.planificacionproduccion_idplanificacionproduccion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence plano_idplano_seq (OID = 24156) : 
--
CREATE SEQUENCE public.plano_idplano_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planprocedimientos_idplanprocedimientos_seq (OID = 24158) : 
--
CREATE SEQUENCE public.planprocedimientos_idplanprocedimientos_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planprocesoscalidad_idplanprocesoscalidad_seq (OID = 24160) : 
--
CREATE SEQUENCE public.planprocesoscalidad_idplanprocesoscalidad_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planrequerimientosmateriaprima_idplanrequerimientosmateriaprima (OID = 24162) : 
--
CREATE SEQUENCE public.planrequerimientosmateriaprima_idplanrequerimientosmateriaprima
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence presupuesto_idpresupuesto_seq (OID = 24164) : 
--
CREATE SEQUENCE public.presupuesto_idpresupuesto_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence prioridad_idprioridad_seq (OID = 24166) : 
--
CREATE SEQUENCE public.prioridad_idprioridad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence privilegio_idprivilegio_seq (OID = 24168) : 
--
CREATE SEQUENCE public.privilegio_idprivilegio_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence procesocalidad_idprocesocalidad_seq (OID = 24170) : 
--
CREATE SEQUENCE public.procesocalidad_idprocesocalidad_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence producto_idproducto_seq (OID = 24172) : 
--
CREATE SEQUENCE public.producto_idproducto_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence productoreal_idproductoreal_seq (OID = 24174) : 
--
CREATE SEQUENCE public.productoreal_idproductoreal_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence proveedor_idproveedor_seq (OID = 24176) : 
--
CREATE SEQUENCE public.proveedor_idproveedor_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence proveedormantenimientomaquina_idproveedormantenimiento_seq (OID = 24178) : 
--
CREATE SEQUENCE public.proveedormantenimientomaquina_idproveedormantenimiento_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence provincia_idprovincia_seq (OID = 24180) : 
--
CREATE SEQUENCE public.provincia_idprovincia_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamocliente_idreclamo_seq (OID = 24182) : 
--
CREATE SEQUENCE public.reclamocliente_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamoempresametalurgica_idreclamo_seq (OID = 24184) : 
--
CREATE SEQUENCE public.reclamoempresametalurgica_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamoproveedor_idreclamo_seq (OID = 24186) : 
--
CREATE SEQUENCE public.reclamoproveedor_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence remito_idremito_seq (OID = 24188) : 
--
CREATE SEQUENCE public.remito_idremito_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence responsable_idresponsable_seq (OID = 24190) : 
--
CREATE SEQUENCE public.responsable_idresponsable_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence rol_idrol_seq (OID = 24192) : 
--
CREATE SEQUENCE public.rol_idrol_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence rotura_idrotura_seq (OID = 24194) : 
--
CREATE SEQUENCE public.rotura_idrotura_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence servicio_idservicio_seq (OID = 24196) : 
--
CREATE SEQUENCE public.servicio_idservicio_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence sesion_idsesion_seq (OID = 24198) : 
--
CREATE SEQUENCE public.sesion_idsesion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipodocumento_idtipodocumento_seq (OID = 24200) : 
--
CREATE SEQUENCE public.tipodocumento_idtipodocumento_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipoiva_idtipoiva_seq (OID = 24202) : 
--
CREATE SEQUENCE public.tipoiva_idtipoiva_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipomaquina_idtipomaquina_seq (OID = 24204) : 
--
CREATE SEQUENCE public.tipomaquina_idtipomaquina_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tiporeclamo_idtiporeclamo_seq (OID = 24206) : 
--
CREATE SEQUENCE public.tiporeclamo_idtiporeclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence trabajotercerizado_idtrabajo_seq (OID = 24208) : 
--
CREATE SEQUENCE public.trabajotercerizado_idtrabajo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence turno_idturno_seq (OID = 24210) : 
--
CREATE SEQUENCE public.turno_idturno_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for view viewdetalleproducto (OID = 24212) : 
--
CREATE VIEW public.viewdetalleproducto AS
SELECT pi.nombre AS nombrepieza, dp.descripcion, dp.cantidadpiezas,
    pi.dimensiones, tm.nombre AS nombretipomaterial, pi.idpieza,
    dp.iddetalle, p.idproducto
FROM producto p, detalleproducto dp, pieza pi, tipomaterial tm
WHERE (((dp.idproducto = p.idproducto) AND (dp.pieza = pi.idpieza)) AND
    (pi.tipomaterial = tm.idtipomaterial))
ORDER BY pi.nombre;

--
-- Data for table public.cliente (OID = 23636) (LIMIT 0,9)
--
INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (123, 4, 2, 2, false, 1, 'razon social', 1, 'telefono', 'celular', 'mail', 1, '2010-07-01', NULL, NULL, 2, 'cuit');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (5, 8, 1, 1, false, 1, 'tg', 6, '5', '5', '5ggtr', 17, '2010-07-01', NULL, NULL, 3, '5');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (56556, 9, 1, 1, false, 1, 'ghtrhtr', 7, '5465465', '656546', 'hghytrh', 19, '2010-07-06', NULL, NULL, 1, '6546546');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (123, 5, 2, 2, false, 1, 'razon social', 1, 'telefono', 'celular', 'mail', 1, '2010-07-01', '2010-07-06', NULL, 2, 'cuit');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (9876, 10, 3, 1, false, 1, 'BaraleLorena', 8, '34534534', '345354354', 'gtrtrbgrtbr', 21, '2010-07-06', NULL, NULL, 3, '98437843');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (87364, 12, 1, 1, false, 1, 'granos', 10, '4355345345', '435345345435', 'fef@grjhgktrg.com', 25, '2010-07-15', NULL, NULL, 1, '3532543543');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (4444, 6, 2, 2, false, 1, 'razon social', 1, 'telefono', 'celular', 'mail', 1, '2010-07-01', '2010-07-06', NULL, 2, 'cuit');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (5463, 13, 1, 1, false, 1, '546346', 12, '6564', '3464563', '45645', 29, '2010-07-15', NULL, NULL, 1, '5634643');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (4352, 14, 1, 1, false, 1, 'gkonweroivn', 13, '56365654', '56436456', 'htrhrthe', 31, '2010-07-27', NULL, NULL, 1, '5464564');

--
-- Data for table public.usuario (OID = 23648) (LIMIT 0,2)
--
INSERT INTO usuario (idusuario, usuario, clave)
VALUES (1, 'Nino', 'Nino');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (2, 'admin', 'admin');

--
-- Data for table public.prioridad (OID = 23668) (LIMIT 0,3)
--
INSERT INTO prioridad (idprioridad, nombre, descripcion)
VALUES (1, 'Alta', 'Prioridad Alta');

INSERT INTO prioridad (idprioridad, nombre, descripcion)
VALUES (2, 'Baja', 'Prioridad Baja');

INSERT INTO prioridad (idprioridad, nombre, descripcion)
VALUES (3, 'Normal', 'Prioridad Normal');

--
-- Data for table public.estadocliente (OID = 23672) (LIMIT 0,3)
--
INSERT INTO estadocliente (idestado, nombre, descripcion)
VALUES (1, 'Activo', 'Dado de Alta');

INSERT INTO estadocliente (idestado, nombre, descripcion)
VALUES (2, 'Baja', 'Dado de Baja');

INSERT INTO estadocliente (idestado, nombre, descripcion)
VALUES (3, 'Moroso', 'Cliente Moroso, adeuda facturas');

--
-- Data for table public.responsable (OID = 23676) (LIMIT 0,11)
--
INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (2, 'Nino', 'Molina', '346264', 'dsiuhfhdfis', 10, 98375498, 1, '87687678');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (3, 'fdsgs', 'hjbjhb', '897897', 'kjbjkb', 12, 876876, 1, '987987');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (4, 'fgsdfg', 'dfgdfg', '43545', 'dfgdfgds', 14, 54545, 1, '545454');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (5, 'ssssss', 'ssss', '44444', 'sssss', 16, 4444, 1, '4444');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (6, 'r', 'r', '3', 'r', 18, 3, 1, '3');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (7, 'nhgfnhg', 'nnnnnnn', '56546', 'hyttfntynt', 20, 654656, 1, '656546');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (8, 'Alguno', 'Otro', '5665', 'nhgfngf', 22, 6556, 1, '5665');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (10, 'carlos', 'bertoni', '32423', 'cb@gmail.com', 26, 56343643, 1, '3424324');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (1, 'Maria Victoria', 'Merdine', '2983462734', 'mv@hotmail.com', 9, 732647263, 1, '326427342');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (12, '654365', '654364', '6543654', '653464', 30, 6436456, 1, '75765');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (13, 'vjrnewlivn', 'rjivebnwiv', '435', 'rivjwbneivl', 32, 546334, 1, '65346');

--
-- Data for table public.condicioniva (OID = 23680) (LIMIT 0,3)
--
INSERT INTO condicioniva (idcondicioniva, nombre, descripcion)
VALUES (1, 'R.Inscripto', 'Responsable Inscripto');

INSERT INTO condicioniva (idcondicioniva, nombre, descripcion)
VALUES (2, 'Monotributista', 'Monotributista');

INSERT INTO condicioniva (idcondicioniva, nombre, descripcion)
VALUES (3, 'C.Final', 'Consumidor Final');

--
-- Data for table public.domicilio (OID = 23684) (LIMIT 0,28)
--
INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (2, 'américa', 1169, 0, '', '', 3);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (3, 'don bosco', 1987, 5, 'D', '1', 2);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (4, 'frefrewrf', 4545, 1, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (5, 'rfefesfsef', 43, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (6, 'fewafwaf', 43, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (7, 'sakjdhjkah', 234, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (8, 'sacsd', 34, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (10, 'regrtgrtg', 3454, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (11, 'fdsgfsdgdfsg', 5434, 0, '', '', 2);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (12, '4565', 566454, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (13, '54654', 564654, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (14, '54545', 5454, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (15, '5454', 54, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (16, 'gttttt', 55555, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (17, 'gtr', 65, 0, '', '', 2);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (18, '4', 4, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (19, 'htrhythty', 56565, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (20, 'htrhytt', 7777, 0, '', '', 3);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (21, 'Tolosa', 6565, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (22, 'América', 1169, 0, 'D', '2', 3);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (25, 'fgesrgef', 4534534, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (26, 'frefewrf', 98, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (1, 'Húsares', 1983, 0, '', '', 2);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (9, 'america', 1169, 0, '', '', 3);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (29, '6543643', 65464, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (30, '65346', 436436, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (31, 'htrehtreh', 546, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (32, 'grewrge', 54, 0, '', '', 1);

--
-- Data for table public.tipodocumento (OID = 23724) (LIMIT 0,3)
--
INSERT INTO tipodocumento (idtipodocumento, tipo, nombre)
VALUES (1, 'DNI', 'Documento Nacional de Identidad');

INSERT INTO tipodocumento (idtipodocumento, tipo, nombre)
VALUES (2, 'LE', 'Libreta de Enrolamiento');

INSERT INTO tipodocumento (idtipodocumento, tipo, nombre)
VALUES (3, 'LC', 'Libreta Cívica');

--
-- Data for table public.materiaprima (OID = 23772) (LIMIT 0,1)
--
INSERT INTO materiaprima (idmateriaprima, codproducto, nombre, fechaalta, fechabaja, codbarra, dimensiones, stock, unidaddemedida, descripcion, tipomaterial)
VALUES (1, 123, 'Acero', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2);

--
-- Data for table public.matriz (OID = 23776) (LIMIT 0,1)
--
INSERT INTO matriz (idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial)
VALUES (1, 123, 'Matriz1', 'Matriz	', NULL, NULL, 1, 4);

--
-- Data for table public.pieza (OID = 23780) (LIMIT 0,4)
--
INSERT INTO pieza (idpieza, nombre, tipomaterial, dimensiones, materiaprima, matriz)
VALUES (6, 'Mango', 2, 'fsertsertgser', NULL, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, dimensiones, materiaprima, matriz)
VALUES (9, 'Eje', 4, '3,4,6', NULL, 1);

INSERT INTO pieza (idpieza, nombre, tipomaterial, dimensiones, materiaprima, matriz)
VALUES (10, 'Panel', 4, '7,3,4', NULL, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, dimensiones, materiaprima, matriz)
VALUES (8, 'volante2', 2, '', 1, 1);

--
-- Data for table public.producto (OID = 23784) (LIMIT 0,3)
--
INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (3, 345, 'ggggggggg', 3453, 'gggggggggg');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (4, 123456, 'vvvvvvvvv', 7654, 'gggggggggg');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (5, 8765, 'Instrumentos', 342, 'momomomomoomomo
cdsacscsdcds
cdsvfdvfdvdv');

--
-- Data for table public.etapadeproduccion (OID = 23804) (LIMIT 0,5)
--
INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, maquina, duracionestimada, fechacreacion)
VALUES (2, 4, 'soldado', '04:00:00', '04:00:00', NULL, '04:00:00', NULL);

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, maquina, duracionestimada, fechacreacion)
VALUES (3, 5, 'lijado', '08:00:00', '05:00:00', NULL, '08:00:00', NULL);

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, maquina, duracionestimada, fechacreacion)
VALUES (4, 6, 'hola', '08:00:00', '06:00:00', NULL, '08:00:00', NULL);

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, maquina, duracionestimada, fechacreacion)
VALUES (5, 234567, 'nino', '04:00:00', '03:00:00', NULL, '04:00:00', '2010-08-24');

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, maquina, duracionestimada, fechacreacion)
VALUES (6, 34323, 'rectificacion', '02:00:00', '03:00:00', NULL, '04:00:00', '2010-08-24');

--
-- Data for table public.detalleproducto (OID = 23859) (LIMIT 0,7)
--
INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (4, 3, 1, '', 8);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (5, 4, 1, '', 8);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (6, 4, 1, 'hgfhjgf', 6);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (7, 5, 1, '', 10);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (8, 5, 1, 'eje central', 9);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (9, 5, 3, 'fdsef', 8);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (10, 5, 5, 'fffffff', 6);

--
-- Data for table public.provincia (OID = 23926) (LIMIT 0,7)
--
INSERT INTO provincia (idprovincia, nombre)
VALUES (1, 'Córdoba');

INSERT INTO provincia (idprovincia, nombre)
VALUES (2, 'Buenos Aires');

INSERT INTO provincia (idprovincia, nombre)
VALUES (3, 'Misiones');

INSERT INTO provincia (idprovincia, nombre)
VALUES (4, 'Catamarca');

INSERT INTO provincia (idprovincia, nombre)
VALUES (5, 'Jujuy');

INSERT INTO provincia (idprovincia, nombre)
VALUES (6, 'Santa Fe');

INSERT INTO provincia (idprovincia, nombre)
VALUES (7, 'La Pampa');

--
-- Data for table public.localidad (OID = 23930) (LIMIT 0,10)
--
INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (1, 'Córdoba', 1);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (2, 'Jesús María', 1);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (3, 'Carlos Paz', 1);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (4, 'V. General Belgrano', 1);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (5, 'Río Ceballos', 1);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (6, 'Ituzaingó', 2);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (7, 'Quilmes', 2);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (8, 'Tigre', 2);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (9, 'Pilar', 2);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (10, 'Bahía Blanca', 2);

--
-- Data for table public.barrio (OID = 23934) (LIMIT 0,3)
--
INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (1, 'Nva. Córdoba', 5000, 1);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (2, 'Alta Córdoba', 5000, 1);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (3, 'Latinoamérica', 5220, 2);

--
-- Data for table public.tipomaterial (OID = 24008) (LIMIT 0,2)
--
INSERT INTO tipomaterial (idtipomaterial, nombre, descripcion)
VALUES (2, 'otro mas', 'algun ootr');

INSERT INTO tipomaterial (idtipomaterial, nombre, descripcion)
VALUES (4, 'Chapa', 'Chapa de acero inoxidable');

--
-- Definition for index pedido_nropedido_key (OID = 24316) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_nropedido_key UNIQUE (nropedido);
--
-- Definition for index piezareal_idpieza_key (OID = 24318) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_idpieza_key UNIQUE (idpieza);
--
-- Definition for index ejecucionplanificacionproduccion_idplanificacionproduccion_key (OID = 24320) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_idplanificacionproduccion_key UNIQUE (idplanificacionproduccion);
--
-- Definition for index ejecucionplanificacioncalidad_idplanificacioncalidad_key (OID = 24322) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_idplanificacioncalidad_key UNIQUE (idplanificacioncalidad);
--
-- Definition for index ejecucionprocesocalidad_idprocesocalidad_key (OID = 24324) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_idprocesocalidad_key UNIQUE (idprocesocalidad);
--
-- Definition for index ejecucionetapaproduccion_idetapaproduccion_key (OID = 24326) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_idetapaproduccion_key UNIQUE (idetapaproduccion);
--
-- Definition for index asistencia_pkey (OID = 24328) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_pkey PRIMARY KEY (empleado, fechaingreso, horaingreso);
--
-- Definition for index asistencia_empleado_key (OID = 24330) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_empleado_key UNIQUE (empleado);
--
-- Definition for index asistencia_fechaingreso_key (OID = 24332) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_fechaingreso_key UNIQUE (fechaingreso);
--
-- Definition for index asistencia_horaingreso_key (OID = 24334) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_horaingreso_key UNIQUE (horaingreso);
--
-- Definition for index proveedorxmateriaprima_idx (OID = 24336) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_idx PRIMARY KEY (idmateriaprima, idproveedor);
--
-- Definition for index maquinaxejecucionetapaproduccion_idx (OID = 24338) : 
--
ALTER TABLE ONLY maquinaxejecucionetapaproduccion
    ADD CONSTRAINT maquinaxejecucionetapaproduccion_idx PRIMARY KEY (idejecucionetapaproduccion, idetapaproduccion, idmaquina);
--
-- Definition for index maquinaxprocesocalidad_idx (OID = 24340) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT maquinaxprocesocalidad_idx PRIMARY KEY (idprocesocalidad, idmaquina);
--
-- Definition for index piezaxetapadeproduccion_idx (OID = 24342) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT piezaxetapadeproduccion_idx PRIMARY KEY (idpieza, idetapaproduccion);
--
-- Definition for index empleadoxturno_idx (OID = 24344) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_idx PRIMARY KEY (idempleado, idturno);
--
-- Definition for index usuarioxrol_pkey (OID = 24346) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_pkey PRIMARY KEY (idrol, idusuario);
--
-- Definition for index rolxprivilegio_pkey (OID = 24348) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_pkey PRIMARY KEY (idrol, idprivilegio);
--
-- Definition for index prueba_pkey (OID = 24350) : 
--
ALTER TABLE ONLY prueba
    ADD CONSTRAINT prueba_pkey PRIMARY KEY (id);
--
-- Definition for index usuario_pkey (OID = 24352) : 
--
ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (idusuario);
--
-- Definition for index factura_fk2 (OID = 24354) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk2 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index comprobantepago_fk1 (OID = 24359) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk1 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index cliente_fk2 (OID = 24364) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk2 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index empleado_fk3 (OID = 24369) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk3 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index sesion_fk (OID = 24374) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT sesion_fk FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index usuarioxrol_fk1 (OID = 24379) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_fk1 FOREIGN KEY (idusuario) REFERENCES usuario(idusuario);
--
-- Definition for index tipomaterial_pkey (OID = 24384) : 
--
ALTER TABLE ONLY tipomaterial
    ADD CONSTRAINT tipomaterial_pkey PRIMARY KEY (idtipomaterial);
--
-- Definition for index accioncalidad_pkey (OID = 24386) : 
--
ALTER TABLE ONLY accioncalidad
    ADD CONSTRAINT accioncalidad_pkey PRIMARY KEY (idaccioncalidad);
--
-- Definition for index procesocalidad_fk (OID = 24388) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT procesocalidad_fk FOREIGN KEY (accioncalidad) REFERENCES accioncalidad(idaccioncalidad);
--
-- Definition for index barrio_pkey (OID = 24393) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT barrio_pkey PRIMARY KEY (idbarrio);
--
-- Definition for index domicilio_fk (OID = 24395) : 
--
ALTER TABLE ONLY domicilio
    ADD CONSTRAINT domicilio_fk FOREIGN KEY (barrio) REFERENCES barrio(idbarrio);
--
-- Definition for index cargo_pkey (OID = 24400) : 
--
ALTER TABLE ONLY cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (idcargo);
--
-- Definition for index empleado_fk4 (OID = 24402) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk4 FOREIGN KEY (cargo) REFERENCES cargo(idcargo);
--
-- Definition for index categoria_pkey (OID = 24407) : 
--
ALTER TABLE ONLY categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (idcategoria);
--
-- Definition for index empleado_fk2 (OID = 24409) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk2 FOREIGN KEY (categoria) REFERENCES categoria(idcategoria);
--
-- Definition for index cliente_pkey (OID = 24414) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (idcliente);
--
-- Definition for index pedido_fk4 (OID = 24416) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk4 FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index reclamocliente_fk1 (OID = 24421) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_fk1 FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index codigodebarra_pkey (OID = 24426) : 
--
ALTER TABLE ONLY codigodebarra
    ADD CONSTRAINT codigodebarra_pkey PRIMARY KEY (idcodigo);
--
-- Definition for index materiaprima_fk1 (OID = 24428) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk1 FOREIGN KEY (codbarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index productoreal_fk2 (OID = 24433) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk2 FOREIGN KEY (codigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index compra_pkey (OID = 24438) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_pkey PRIMARY KEY (idcompra);
--
-- Definition for index detallecompra_fk (OID = 24440) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk FOREIGN KEY (idcompra) REFERENCES compra(idcompra);
--
-- Definition for index reclamoproveedor_fk1 (OID = 24445) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_fk1 FOREIGN KEY (compra) REFERENCES compra(idcompra);
--
-- Definition for index comprobantepago_pkey (OID = 24450) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_pkey PRIMARY KEY (idcomprobantepago);
--
-- Definition for index condicioniva_pkey (OID = 24452) : 
--
ALTER TABLE ONLY condicioniva
    ADD CONSTRAINT condicioniva_pkey PRIMARY KEY (idcondicioniva);
--
-- Definition for index cliente_fk5 (OID = 24454) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk5 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index empresametalurgica_fk2 (OID = 24459) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk2 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index proveedormantenimientomaquina_fk2 (OID = 24464) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk2 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index proveedor_fk2 (OID = 24469) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk2 FOREIGN KEY (condicion) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index detallaplanificacionproduccion_idx (OID = 24474) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_idx PRIMARY KEY (iddetalle, idplanificacionproduccion);
--
-- Definition for index detallaplanificacionproduccion_iddetalle_key (OID = 24476) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallecompra_idx (OID = 24478) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_idx PRIMARY KEY (iddetalle, idcompra);
--
-- Definition for index detallereclamoproveedor_fk1 (OID = 24480) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_fk1 FOREIGN KEY (iddetallecompra, idcompra) REFERENCES detallecompra(iddetalle, idcompra);
--
-- Definition for index detallecompra_iddetalle_key (OID = 24485) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleejecucionplanificacion_idx (OID = 24487) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_idx PRIMARY KEY (iddetalle, idejecucionplanificacionproduccion, idplanificacionproduccion);
--
-- Definition for index detallaplanificacionproduccion_fk1 (OID = 24489) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk1 FOREIGN KEY (iddetalleejecucionplanificacion, idejecucionplanificacionproduccion, idplanificacionproduccion) REFERENCES detalleejecucionplanificacion(iddetalle, idejecucionplanificacionproduccion, idplanificacionproduccion);
--
-- Definition for index detalleejecucionplanificacion_iddetalle_key (OID = 24494) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleejecucionplanificacioncalidad_idx (OID = 24496) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_idx PRIMARY KEY (iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad);
--
-- Definition for index detalleplanificacioncalidad_fk1 (OID = 24498) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk1 FOREIGN KEY (idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, idejecucionplanificacioncalidad) REFERENCES detalleejecucionplanificacioncalidad(iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad);
--
-- Definition for index detalleejecucionplanificacioncalidad_iddetalle_key (OID = 24503) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallefactura_idx (OID = 24505) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_idx PRIMARY KEY (iddetalle, idfactura);
--
-- Definition for index detallefactura_iddetalle_key (OID = 24507) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallemantenimientocorrectivo_pkey (OID = 24509) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_pkey PRIMARY KEY (idmantenimientocorrectivo, iddetalle);
--
-- Definition for index detallemantenimientocorrectivo_idmantenimientocorrectivo_key (OID = 24511) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_idmantenimientocorrectivo_key UNIQUE (idmantenimientocorrectivo);
--
-- Definition for index detallemantenimientopreventivo_pkey (OID = 24513) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_pkey PRIMARY KEY (idmantenimientopreventivo, iddetalle);
--
-- Definition for index detallemantenimientopreventivo_idmantenimientopreventivo_key (OID = 24515) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_idmantenimientopreventivo_key UNIQUE (idmantenimientopreventivo);
--
-- Definition for index detallepedido_idx (OID = 24517) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_idx PRIMARY KEY (iddetalle, idpedido);
--
-- Definition for index detallefactura_fk2 (OID = 24519) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_fk2 FOREIGN KEY (idpedido, iddetallepedido) REFERENCES detallepedido(iddetalle, idpedido);
--
-- Definition for index detallepresupuesto_fk1 (OID = 24524) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_fk1 FOREIGN KEY (iddetallepedido, idpedido) REFERENCES detallepedido(iddetalle, idpedido);
--
-- Definition for index detallepedido_iddetalle_key (OID = 24529) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleplanificacioncalidad_idx (OID = 24531) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_idx PRIMARY KEY (iddetalle, idplanificacioncalidad);
--
-- Definition for index detalleplanificacioncalidad_iddetalle_key (OID = 24533) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleplanprocedimientos_idx (OID = 24535) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_idx PRIMARY KEY (iddetalle, idplanpprocedimientos);
--
-- Definition for index detalleplanprocedimientos_iddetalle_key (OID = 24537) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleplanprocesoscalidad_idx (OID = 24539) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_idx PRIMARY KEY (iddetalle, idplanprocesoscalidad);
--
-- Definition for index detalleplanprocesoscalidad_iddetalle_key (OID = 24541) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallepresupuesto_idx (OID = 24543) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_idx PRIMARY KEY (iddetalle, idpresupuesto);
--
-- Definition for index detallepresupuesto_iddetalle_key (OID = 24545) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleproducto_idx (OID = 24547) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_idx PRIMARY KEY (iddetalle, idproducto);
--
-- Definition for index detalleproducto_iddetalle_key (OID = 24549) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleproductoreal_idx (OID = 24551) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_idx PRIMARY KEY (iddetalle, idproductoreal);
--
-- Definition for index detalleproductoreal_iddetalle_key (OID = 24553) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamocliente_idx (OID = 24555) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamocliente_iddetalle_key (OID = 24557) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamoempresametalurgica_idx (OID = 24559) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamoempresametalurgica_iddetalle_key (OID = 24561) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamoproveedor_idx (OID = 24563) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamoproveedor_iddetalle_key (OID = 24565) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleremito_idx (OID = 24567) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_idx PRIMARY KEY (iddetalle, idremito);
--
-- Definition for index detalleremito_iddetalle_key (OID = 24569) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallerequerimientosmateriaprima_idx (OID = 24571) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_idx PRIMARY KEY (iddetalle, idplanrequerimientosmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_iddetalle_key (OID = 24573) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalletrabajotercerizado_idx (OID = 24575) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_idx PRIMARY KEY (iddetalle, idtrabajotercerizado);
--
-- Definition for index detalletrabajotercerizado_iddetalle_key (OID = 24577) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index domicilio_pkey (OID = 24579) : 
--
ALTER TABLE ONLY domicilio
    ADD CONSTRAINT domicilio_pkey PRIMARY KEY (iddomicilio);
--
-- Definition for index cliente_fk4 (OID = 24581) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk4 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index empresametalurgica_fk1 (OID = 24586) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index responsable_fk (OID = 24591) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_fk FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index proveedormantenimientomaquina_fk1 (OID = 24596) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index empleado_fk (OID = 24601) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index proveedor_fk1 (OID = 24606) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index ejecucionetapaproduccion_nroejecucion_key (OID = 24611) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_nroejecucion_key UNIQUE (idejecucion);
--
-- Definition for index ejecucionetapaproduccion_pkey (OID = 24613) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_pkey PRIMARY KEY (idejecucion, idetapaproduccion);
--
-- Definition for index detalleejecucionplanificacion_fk1 (OID = 24615) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk1 FOREIGN KEY (ejecucionetapa, idetapaproduccion) REFERENCES ejecucionetapaproduccion(idejecucion, idetapaproduccion);
--
-- Definition for index maquinaxejecucionetapaproduccion_fk (OID = 24620) : 
--
ALTER TABLE ONLY maquinaxejecucionetapaproduccion
    ADD CONSTRAINT maquinaxejecucionetapaproduccion_fk FOREIGN KEY (idejecucionetapaproduccion, idetapaproduccion) REFERENCES ejecucionetapaproduccion(idejecucion, idetapaproduccion);
--
-- Definition for index ejecucionetapaproduccion_nroejecucion_key1 (OID = 24625) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_nroejecucion_key1 UNIQUE (nroejecucion);
--
-- Definition for index ejecucionplanificacioncalidad_idejecucion_key (OID = 24627) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_idejecucion_key UNIQUE (idejecucion);
--
-- Definition for index ejecucionplanificacioncalidad_pkey (OID = 24629) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_pkey PRIMARY KEY (idejecucion, idplanificacioncalidad);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk (OID = 24631) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk FOREIGN KEY (idejecucionplanificacioncalidad, idplanificacioncalidad) REFERENCES ejecucionplanificacioncalidad(idejecucion, idplanificacioncalidad);
--
-- Definition for index ejecucionplanificacionproduccion_idejecucion_key (OID = 24636) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_idejecucion_key UNIQUE (idejecucion);
--
-- Definition for index ejecucionplanificacionproduccion_pkey (OID = 24638) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_pkey PRIMARY KEY (idejecucion, idplanificacionproduccion);
--
-- Definition for index detalleejecucionplanificacion_fk (OID = 24640) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk FOREIGN KEY (idejecucionplanificacionproduccion, idplanificacionproduccion) REFERENCES ejecucionplanificacionproduccion(idejecucion, idplanificacionproduccion);
--
-- Definition for index ejecucionprocesocalidad_nroejecucion_key (OID = 24645) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_nroejecucion_key UNIQUE (idejecucion);
--
-- Definition for index ejecucionprocesocalidad_pkey (OID = 24647) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_pkey PRIMARY KEY (idejecucion, idprocesocalidad);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk1 (OID = 24649) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk1 FOREIGN KEY (ejecucionprocesocalidad, idprocesocalidad) REFERENCES ejecucionprocesocalidad(idejecucion, idprocesocalidad);
--
-- Definition for index empleado_pkey (OID = 24654) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (idempleado);
--
-- Definition for index mantenimientocorrectivo_fk (OID = 24656) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_fk FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index ejecucionetapaproduccion_fk1 (OID = 24661) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk1 FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index asistencia_fk (OID = 24666) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_fk FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index detallaplanificacionproduccion_fk3 (OID = 24671) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk3 FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index empleadoxturno_fk (OID = 24676) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_fk FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index empresametalurgica_pkey (OID = 24681) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_pkey PRIMARY KEY (idempresametalurgica);
--
-- Definition for index trabajotercerizado_fk1 (OID = 24683) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk1 FOREIGN KEY (empresa) REFERENCES empresametalurgica(idempresametalurgica);
--
-- Definition for index estadocliente_pkey (OID = 24688) : 
--
ALTER TABLE ONLY estadocliente
    ADD CONSTRAINT estadocliente_pkey PRIMARY KEY (idestado);
--
-- Definition for index cliente_fk1 (OID = 24690) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk1 FOREIGN KEY (estado) REFERENCES estadocliente(idestado);
--
-- Definition for index estadocompra_pkey (OID = 24695) : 
--
ALTER TABLE ONLY estadocompra
    ADD CONSTRAINT estadocompra_pkey PRIMARY KEY (idestado);
--
-- Definition for index compra_fk (OID = 24697) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_fk FOREIGN KEY (estado) REFERENCES estadocompra(idestado);
--
-- Definition for index estadodetallecompra_pkey (OID = 24702) : 
--
ALTER TABLE ONLY estadodetallecompra
    ADD CONSTRAINT estadodetallecompra_pkey PRIMARY KEY (idestado);
--
-- Definition for index detallecompra_fk2 (OID = 24704) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk2 FOREIGN KEY (estado) REFERENCES estadodetallecompra(idestado);
--
-- Definition for index estadodetalletrabajotercerizado_pkey (OID = 24709) : 
--
ALTER TABLE ONLY estadodetalletrabajotercerizado
    ADD CONSTRAINT estadodetalletrabajotercerizado_pkey PRIMARY KEY (idestado);
--
-- Definition for index detalletrabajotercerizado_fk3 (OID = 24711) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk3 FOREIGN KEY (estado) REFERENCES estadodetalletrabajotercerizado(idestado);
--
-- Definition for index estadoejecetapaprod_pkey (OID = 24716) : 
--
ALTER TABLE ONLY estadoejecetapaprod
    ADD CONSTRAINT estadoejecetapaprod_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionetapaproduccion_fk2 (OID = 24718) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk2 FOREIGN KEY (estado) REFERENCES estadoejecetapaprod(idestado);
--
-- Definition for index estadoejecplancalidad_pkey (OID = 24723) : 
--
ALTER TABLE ONLY estadoejecplancalidad
    ADD CONSTRAINT estadoejecplancalidad_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionplanificacioncalidad_fk1 (OID = 24725) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_fk1 FOREIGN KEY (estado) REFERENCES estadoejecplancalidad(idestado);
--
-- Definition for index estadoejecplanifpedido_pkey (OID = 24730) : 
--
ALTER TABLE ONLY estadoejecplanifpedido
    ADD CONSTRAINT estadoejecplanifpedido_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionplanificacionproduccion_fk1 (OID = 24732) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_fk1 FOREIGN KEY (estado) REFERENCES estadoejecplanifpedido(idestado);
--
-- Definition for index estadoejecucionprocesocalidad_pkey (OID = 24737) : 
--
ALTER TABLE ONLY estadoejecucionprocesocalidad
    ADD CONSTRAINT estadoejecucionprocesocalidad_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionprocesocalidad_fk1 (OID = 24739) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_fk1 FOREIGN KEY (estado) REFERENCES estadoejecucionprocesocalidad(idestado);
--
-- Definition for index estadofactura_pkey (OID = 24744) : 
--
ALTER TABLE ONLY estadofactura
    ADD CONSTRAINT estadofactura_pkey PRIMARY KEY (idestado);
--
-- Definition for index factura_fk3 (OID = 24746) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk3 FOREIGN KEY (estado) REFERENCES estadofactura(idestado);
--
-- Definition for index estadomaquina_pkey (OID = 24751) : 
--
ALTER TABLE ONLY estadomaquina
    ADD CONSTRAINT estadomaquina_pkey PRIMARY KEY (idestado);
--
-- Definition for index maquina_fk1 (OID = 24753) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk1 FOREIGN KEY (estado) REFERENCES estadomaquina(idestado);
--
-- Definition for index estadopedido_pkey (OID = 24758) : 
--
ALTER TABLE ONLY estadopedido
    ADD CONSTRAINT estadopedido_pkey PRIMARY KEY (idestado);
--
-- Definition for index pedido_fk (OID = 24760) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk FOREIGN KEY (estado) REFERENCES estadopedido(idestado);
--
-- Definition for index estadopiezareal_pkey (OID = 24765) : 
--
ALTER TABLE ONLY estadopiezareal
    ADD CONSTRAINT estadopiezareal_pkey PRIMARY KEY (idestado);
--
-- Definition for index piezareal_fk1 (OID = 24767) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_fk1 FOREIGN KEY (estado) REFERENCES estadopiezareal(idestado);
--
-- Definition for index estadoproductoreal_pkey (OID = 24772) : 
--
ALTER TABLE ONLY estadoproductoreal
    ADD CONSTRAINT estadoproductoreal_pkey PRIMARY KEY (idestado);
--
-- Definition for index productoreal_fk3 (OID = 24774) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk3 FOREIGN KEY (estado) REFERENCES estadoproductoreal(idestado);
--
-- Definition for index estadoremito_pkey (OID = 24779) : 
--
ALTER TABLE ONLY estadoremito
    ADD CONSTRAINT estadoremito_pkey PRIMARY KEY (idestado);
--
-- Definition for index remito_fk1 (OID = 24781) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_fk1 FOREIGN KEY (estado) REFERENCES estadoremito(idestado);
--
-- Definition for index estadotrabajotercerizado_pkey (OID = 24786) : 
--
ALTER TABLE ONLY estadotrabajotercerizado
    ADD CONSTRAINT estadotrabajotercerizado_pkey PRIMARY KEY (idestado);
--
-- Definition for index trabajotercerizado_fk2 (OID = 24788) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk2 FOREIGN KEY (estado) REFERENCES estadotrabajotercerizado(idestado);
--
-- Definition for index etapadeproduccion_pkey (OID = 24793) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_pkey PRIMARY KEY (idetapaproduccion);
--
-- Definition for index ejecucionetapaproduccion_fk (OID = 24795) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detalletrabajotercerizado_fk2 (OID = 24800) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk2 FOREIGN KEY (proceso) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index piezaxetapadeproduccion_fk1 (OID = 24805) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT piezaxetapadeproduccion_fk1 FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detalleplanprocedimientos_fk2 (OID = 24810) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_fk2 FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index factura_pkey (OID = 24815) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_pkey PRIMARY KEY (idfactura);
--
-- Definition for index pedido_fk1 (OID = 24817) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk1 FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index comprobantepago_fk2 (OID = 24822) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk2 FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index detallefactura_fk (OID = 24827) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_fk FOREIGN KEY (idfactura) REFERENCES factura(idfactura);
--
-- Definition for index formadepago_pkey (OID = 24832) : 
--
ALTER TABLE ONLY formadepago
    ADD CONSTRAINT formadepago_pkey PRIMARY KEY (idformapago);
--
-- Definition for index factura_fk1 (OID = 24834) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk1 FOREIGN KEY (formapago) REFERENCES formadepago(idformapago);
--
-- Definition for index comprobantepago_fk (OID = 24839) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk FOREIGN KEY (formadepago) REFERENCES formadepago(idformapago);
--
-- Definition for index localidad_pkey (OID = 24844) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT localidad_pkey PRIMARY KEY (idlocalidad);
--
-- Definition for index barrio_fk (OID = 24846) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT barrio_fk FOREIGN KEY (localidad) REFERENCES localidad(idlocalidad);
--
-- Definition for index mantenimientocorrectivo_pkey (OID = 24851) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_pkey PRIMARY KEY (idmantenimientocorrectivo);
--
-- Definition for index detallemantenimientocorrectivo_fk1 (OID = 24853) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_fk1 FOREIGN KEY (idmantenimientocorrectivo) REFERENCES mantenimientocorrectivo(idmantenimientocorrectivo);
--
-- Definition for index mantenimientopreventivo_pkey (OID = 24858) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_pkey PRIMARY KEY (idmantenimientopreventivo);
--
-- Definition for index detallemantenimientopreventivo_fk (OID = 24860) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_fk FOREIGN KEY (idmantenimientopreventivo) REFERENCES mantenimientopreventivo(idmantenimientopreventivo);
--
-- Definition for index maquina_pkey (OID = 24865) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_pkey PRIMARY KEY (idmaquina);
--
-- Definition for index etapadeproduccion_fk (OID = 24867) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_fk FOREIGN KEY (maquina) REFERENCES maquina(idmaquina);
--
-- Definition for index maquinaxejecucionetapaproduccion_fk1 (OID = 24872) : 
--
ALTER TABLE ONLY maquinaxejecucionetapaproduccion
    ADD CONSTRAINT maquinaxejecucionetapaproduccion_fk1 FOREIGN KEY (idmaquina) REFERENCES maquina(idmaquina);
--
-- Definition for index maquinaxprocesocalidad_fk1 (OID = 24877) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT maquinaxprocesocalidad_fk1 FOREIGN KEY (idmaquina) REFERENCES maquina(idmaquina);
--
-- Definition for index mantenimientocorrectivo_fk2 (OID = 24882) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_fk2 FOREIGN KEY (maquina) REFERENCES maquina(idmaquina);
--
-- Definition for index mantenimientopreventivo_fk1 (OID = 24887) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_fk1 FOREIGN KEY (maquina) REFERENCES maquina(idmaquina);
--
-- Definition for index marca_pkey (OID = 24892) : 
--
ALTER TABLE ONLY marca
    ADD CONSTRAINT marca_pkey PRIMARY KEY (idmarca);
--
-- Definition for index maquina_fk (OID = 24894) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk FOREIGN KEY (marca) REFERENCES marca(idmarca);
--
-- Definition for index materiaprima_pkey (OID = 24899) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_pkey PRIMARY KEY (idmateriaprima);
--
-- Definition for index matriz_fk (OID = 24901) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT matriz_fk FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index pieza_fk1 (OID = 24906) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk1 FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallecompra_fk1 (OID = 24911) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk1 FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index proveedorxmateriaprima_fk1 (OID = 24916) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_fk1 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_fk2 (OID = 24921) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_fk2 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index matriz_pkey (OID = 24926) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT matriz_pkey PRIMARY KEY (idmatriz);
--
-- Definition for index pieza_fk2 (OID = 24928) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk2 FOREIGN KEY (matriz) REFERENCES matriz(idmatriz);
--
-- Definition for index pedidomatriz_fk (OID = 24933) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT pedidomatriz_fk FOREIGN KEY (idmatriz) REFERENCES matriz(idmatriz);
--
-- Definition for index pedido_pkey (OID = 24938) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (idpedido);
--
-- Definition for index plano_fk (OID = 24940) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT plano_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index remito_fk (OID = 24945) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index trabajotercerizado_fk (OID = 24950) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index planificacionproduccion_fk (OID = 24955) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index planificacioncalidad_fk (OID = 24960) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT planificacioncalidad_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index detallepedido_fk (OID = 24965) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_fk FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index productoreal_fk1 (OID = 24970) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk1 FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index detallefactura_fk1 (OID = 24975) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_fk1 FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index pedidomatriz_idx (OID = 24980) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT pedidomatriz_idx PRIMARY KEY (idpedidomatriz);
--
-- Definition for index pieza_pkey (OID = 24982) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_pkey PRIMARY KEY (idpieza);
--
-- Definition for index piezareal_fk (OID = 24984) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_fk FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index detallaplanificacionproduccion_fk2 (OID = 24989) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk2 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleproducto_fk1 (OID = 24994) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_fk1 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalletrabajotercerizado_fk1 (OID = 24999) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk1 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleplanificacioncalidad_fk3 (OID = 25004) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk3 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index piezaxetapadeproduccion_fk (OID = 25009) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT piezaxetapadeproduccion_fk FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index detallerequerimientosmateriaprima_fk1 (OID = 25014) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_fk1 FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleplanprocedimientos_fk1 (OID = 25019) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_fk1 FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleplanprocesoscalidad_fk1 (OID = 25024) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_fk1 FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index piezareal_idpiezareal_key (OID = 25029) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_idpiezareal_key UNIQUE (idpiezareal);
--
-- Definition for index piezareal_pkey (OID = 25031) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_pkey PRIMARY KEY (idpiezareal, idpieza);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk2 (OID = 25033) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk2 FOREIGN KEY (pieza, piezareal) REFERENCES piezareal(idpiezareal, idpieza);
--
-- Definition for index detalleejecucionplanificacion_fk2 (OID = 25038) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk2 FOREIGN KEY (piezareal, pieza) REFERENCES piezareal(idpiezareal, idpieza);
--
-- Definition for index detallereclamoempresametalurgica_fk1 (OID = 25043) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_fk1 FOREIGN KEY (pieza) REFERENCES piezareal(idpiezareal);
--
-- Definition for index detalleproductoreal_fk1 (OID = 25048) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_fk1 FOREIGN KEY (idpieza, idpiezareal) REFERENCES piezareal(idpiezareal, idpieza);
--
-- Definition for index productoreal_fk (OID = 25053) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk FOREIGN KEY (idpieza, idpiezareal) REFERENCES piezareal(idpiezareal, idpieza);
--
-- Definition for index planificacioncalidad_pkey (OID = 25058) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT planificacioncalidad_pkey PRIMARY KEY (idplanificacion);
--
-- Definition for index ejecucionplanificacioncalidad_fk (OID = 25060) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_fk FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index detalleplanificacioncalidad_fk (OID = 25065) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index planificacionproduccion_pkey (OID = 25070) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_pkey PRIMARY KEY (idplanificacionproduccion);
--
-- Definition for index ejecucionplanificacionproduccion_fk (OID = 25072) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_fk FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index detallaplanificacionproduccion_fk (OID = 25077) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index plano_pkey (OID = 25082) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT plano_pkey PRIMARY KEY (idplano);
--
-- Definition for index pedido_fk3 (OID = 25084) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk3 FOREIGN KEY (plano) REFERENCES plano(idplano);
--
-- Definition for index planprocedimientos_pkey (OID = 25089) : 
--
ALTER TABLE ONLY planprocedimientos
    ADD CONSTRAINT planprocedimientos_pkey PRIMARY KEY (idplanprocedimientos);
--
-- Definition for index detalleplanprocedimientos_fk (OID = 25091) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_fk FOREIGN KEY (idplanpprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index pedido_fk5 (OID = 25096) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk5 FOREIGN KEY (planprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index planprocesoscalidad_pkey (OID = 25101) : 
--
ALTER TABLE ONLY planprocesoscalidad
    ADD CONSTRAINT planprocesoscalidad_pkey PRIMARY KEY (idplanprocesoscalidad);
--
-- Definition for index detalleplanprocesoscalidad_fk (OID = 25103) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_fk FOREIGN KEY (idplanprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index pedido_fk7 (OID = 25108) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk7 FOREIGN KEY (planprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index planrequerimientosmateriaprima_pkey (OID = 25113) : 
--
ALTER TABLE ONLY planrequerimientosmateriaprima
    ADD CONSTRAINT planrequerimientosmateriaprima_pkey PRIMARY KEY (idplanrequerimientosmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_fk (OID = 25115) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_fk FOREIGN KEY (idplanrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index pedido_fk6 (OID = 25120) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk6 FOREIGN KEY (planrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index presupuesto_pkey (OID = 25125) : 
--
ALTER TABLE ONLY presupuesto
    ADD CONSTRAINT presupuesto_pkey PRIMARY KEY (idpresupuesto);
--
-- Definition for index pedido_fk2 (OID = 25127) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk2 FOREIGN KEY (presupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index detallepresupuesto_fk (OID = 25132) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_fk FOREIGN KEY (idpresupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index prioridad_pkey (OID = 25137) : 
--
ALTER TABLE ONLY prioridad
    ADD CONSTRAINT prioridad_pkey PRIMARY KEY (idprioridad);
--
-- Definition for index cliente_fk (OID = 25139) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index privilegio_pkey (OID = 25144) : 
--
ALTER TABLE ONLY privilegio
    ADD CONSTRAINT privilegio_pkey PRIMARY KEY (idprivilegio);
--
-- Definition for index rolxprivilegio_fk (OID = 25146) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_fk FOREIGN KEY (idprivilegio) REFERENCES privilegio(idprivilegio);
--
-- Definition for index procesocalidad_pkey (OID = 25151) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT procesocalidad_pkey PRIMARY KEY (idprocesocalidad);
--
-- Definition for index ejecucionprocesocalidad_fk (OID = 25153) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_fk FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detalleplanificacioncalidad_fk2 (OID = 25158) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk2 FOREIGN KEY (procesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index maquinaxprocesocalidad_fk (OID = 25163) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT maquinaxprocesocalidad_fk FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detalleplanprocesoscalidad_fk2 (OID = 25168) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_fk2 FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index producto_pkey (OID = 25173) : 
--
ALTER TABLE ONLY producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (idproducto);
--
-- Definition for index detalleproducto_fk (OID = 25175) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_fk FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index detallepedido_fk1 (OID = 25180) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detalleremito_fk1 (OID = 25185) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detallereclamocliente_fk1 (OID = 25190) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detallepresupuesto_fk2 (OID = 25195) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_fk2 FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index productoreal_idx (OID = 25200) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_idx PRIMARY KEY (idproductoreal);
--
-- Definition for index detalleproductoreal_fk (OID = 25202) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_fk FOREIGN KEY (idproductoreal) REFERENCES productoreal(idproductoreal);
--
-- Definition for index proveedor_pkey (OID = 25207) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_pkey PRIMARY KEY (idproveedor);
--
-- Definition for index compra_fk1 (OID = 25209) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_fk1 FOREIGN KEY (proveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index proveedorxmateriaprima_fk (OID = 25214) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_fk FOREIGN KEY (idproveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index proveedormantenimientomaquina_pkey (OID = 25219) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_pkey PRIMARY KEY (idproveedormantenimiento);
--
-- Definition for index mantenimientopreventivo_fk (OID = 25221) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_fk FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index mantenimientocorrectivo_fk1 (OID = 25226) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_fk1 FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index provincia_pkey (OID = 25231) : 
--
ALTER TABLE ONLY provincia
    ADD CONSTRAINT provincia_pkey PRIMARY KEY (idprovincia);
--
-- Definition for index localidad_fk (OID = 25233) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT localidad_fk FOREIGN KEY (provincia) REFERENCES provincia(idprovincia);
--
-- Definition for index reclamocliente_idx (OID = 25238) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_idx PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamocliente_fk (OID = 25240) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_fk FOREIGN KEY (idreclamo) REFERENCES reclamocliente(idreclamo);
--
-- Definition for index reclamoempresametalurgica_pkey (OID = 25245) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_pkey PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamoempresametalurgica_fk (OID = 25247) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_fk FOREIGN KEY (idreclamo) REFERENCES reclamoempresametalurgica(idreclamo);
--
-- Definition for index reclamoproveedor_idx (OID = 25252) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_idx PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamoproveedor_fk (OID = 25254) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_fk FOREIGN KEY (idreclamo) REFERENCES reclamoproveedor(idreclamo);
--
-- Definition for index remito_pkey (OID = 25259) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_pkey PRIMARY KEY (idremito);
--
-- Definition for index detalleremito_fk (OID = 25261) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_fk FOREIGN KEY (idremito) REFERENCES remito(idremito);
--
-- Definition for index responsable_pkey (OID = 25266) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_pkey PRIMARY KEY (idresponsable);
--
-- Definition for index cliente_fk3 (OID = 25268) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk3 FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index empresametalurgica_fk (OID = 25273) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index proveedormantenimientomaquina_fk (OID = 25278) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index proveedor_fk (OID = 25283) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index rol_pkey (OID = 25288) : 
--
ALTER TABLE ONLY rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (idrol);
--
-- Definition for index rolxprivilegio_fk1 (OID = 25290) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_fk1 FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index usuarioxrol_fk (OID = 25295) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_fk FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index rotura_pkey (OID = 25300) : 
--
ALTER TABLE ONLY rotura
    ADD CONSTRAINT rotura_pkey PRIMARY KEY (idrotura);
--
-- Definition for index detallemantenimientocorrectivo_fk (OID = 25302) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_fk FOREIGN KEY (rotura) REFERENCES rotura(idrotura);
--
-- Definition for index servicio_pkey (OID = 25307) : 
--
ALTER TABLE ONLY servicio
    ADD CONSTRAINT servicio_pkey PRIMARY KEY (idservicio);
--
-- Definition for index detallemantenimientopreventivo_fk1 (OID = 25309) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_fk1 FOREIGN KEY (servicio) REFERENCES servicio(idservicio);
--
-- Definition for index sesion_pkey (OID = 25314) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT sesion_pkey PRIMARY KEY (idsesion);
--
-- Definition for index tipodocumento_pkey (OID = 25316) : 
--
ALTER TABLE ONLY tipodocumento
    ADD CONSTRAINT tipodocumento_pkey PRIMARY KEY (idtipodocumento);
--
-- Definition for index responsable_fk1 (OID = 25318) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_fk1 FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index empleado_fk1 (OID = 25323) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk1 FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index tipoiva_pkey (OID = 25328) : 
--
ALTER TABLE ONLY tipoiva
    ADD CONSTRAINT tipoiva_pkey PRIMARY KEY (idtipoiva);
--
-- Definition for index factura_fk (OID = 25330) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk FOREIGN KEY (tipoiva) REFERENCES tipoiva(idtipoiva);
--
-- Definition for index tipomaquina_pkey (OID = 25335) : 
--
ALTER TABLE ONLY tipomaquina
    ADD CONSTRAINT tipomaquina_pkey PRIMARY KEY (idtipomaquina);
--
-- Definition for index maquina_fk2 (OID = 25337) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk2 FOREIGN KEY (tipomaquina) REFERENCES tipomaquina(idtipomaquina);
--
-- Definition for index tiporeclamo_pkey (OID = 25342) : 
--
ALTER TABLE ONLY tiporeclamo
    ADD CONSTRAINT tiporeclamo_pkey PRIMARY KEY (idtiporeclamo);
--
-- Definition for index reclamoempresametalurgica_fk (OID = 25344) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index reclamoproveedor_fk (OID = 25349) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index reclamocliente_fk (OID = 25354) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index trabajotercerizado_pkey (OID = 25359) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_pkey PRIMARY KEY (idtrabajo);
--
-- Definition for index detalletrabajotercerizado_fk (OID = 25361) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk FOREIGN KEY (idtrabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index reclamoempresametalurgica_fk1 (OID = 25366) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_fk1 FOREIGN KEY (trabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index turno_pkey (OID = 25371) : 
--
ALTER TABLE ONLY turno
    ADD CONSTRAINT turno_pkey PRIMARY KEY (idturno);
--
-- Definition for index empleadoxturno_fk1 (OID = 25373) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_fk1 FOREIGN KEY (idturno) REFERENCES turno(idturno);
--
-- Definition for index materiaprima_fk (OID = 25378) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk FOREIGN KEY (tipomaterial) REFERENCES tipomaterial(idtipomaterial);
--
-- Data for sequence public.prueba_id_seq (OID = 24012)
--
SELECT pg_catalog.setval('prueba_id_seq', 1, false);
--
-- Data for sequence public.usuario_idusuario_seq (OID = 24018)
--
SELECT pg_catalog.setval('usuario_idusuario_seq', 6, true);
--
-- Data for sequence public.tipomaterial_idtipomaterial_seq (OID = 24020)
--
SELECT pg_catalog.setval('tipomaterial_idtipomaterial_seq', 4, true);
--
-- Data for sequence public.accioncalidad_idaccioncalidad_seq (OID = 24022)
--
SELECT pg_catalog.setval('accioncalidad_idaccioncalidad_seq', 1, false);
--
-- Data for sequence public.barrio_idbarrio_seq (OID = 24024)
--
SELECT pg_catalog.setval('barrio_idbarrio_seq', 3, true);
--
-- Data for sequence public.cargo_idcargo_seq (OID = 24026)
--
SELECT pg_catalog.setval('cargo_idcargo_seq', 1, false);
--
-- Data for sequence public.categoria_idcategoria_seq (OID = 24028)
--
SELECT pg_catalog.setval('categoria_idcategoria_seq', 1, false);
--
-- Data for sequence public.cliente_idcliente_seq (OID = 24030)
--
SELECT pg_catalog.setval('cliente_idcliente_seq', 14, true);
--
-- Data for sequence public.codigodebarra_idcodigo_seq (OID = 24032)
--
SELECT pg_catalog.setval('codigodebarra_idcodigo_seq', 1, false);
--
-- Data for sequence public.compra_idcompra_seq (OID = 24034)
--
SELECT pg_catalog.setval('compra_idcompra_seq', 1, false);
--
-- Data for sequence public.comprobantepago_idcomprobantepago_seq (OID = 24036)
--
SELECT pg_catalog.setval('comprobantepago_idcomprobantepago_seq', 1, false);
--
-- Data for sequence public.condicioniva_idcondicioniva_seq (OID = 24038)
--
SELECT pg_catalog.setval('condicioniva_idcondicioniva_seq', 3, true);
--
-- Data for sequence public.detallaplanificacionproduccion_iddetalle_seq (OID = 24040)
--
SELECT pg_catalog.setval('detallaplanificacionproduccion_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallecompra_iddetalle_seq (OID = 24042)
--
SELECT pg_catalog.setval('detallecompra_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleejecucionplanificacion_iddetalle_seq (OID = 24044)
--
SELECT pg_catalog.setval('detalleejecucionplanificacion_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleejecucionplanificacioncalidad_iddetalle_seq (OID = 24046)
--
SELECT pg_catalog.setval('detalleejecucionplanificacioncalidad_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallefactura_iddetalle_seq (OID = 24048)
--
SELECT pg_catalog.setval('detallefactura_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallemantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 24050)
--
SELECT pg_catalog.setval('detallemantenimientocorrectivo_idmantenimientocorrectivo_seq', 1, false);
--
-- Data for sequence public.detallemantenimientopreventivo_idmantenimientopreventivo_seq (OID = 24052)
--
SELECT pg_catalog.setval('detallemantenimientopreventivo_idmantenimientopreventivo_seq', 1, false);
--
-- Data for sequence public.detallepedido_iddetalle_seq (OID = 24054)
--
SELECT pg_catalog.setval('detallepedido_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleplanificacioncalidad_iddetalle_seq (OID = 24056)
--
SELECT pg_catalog.setval('detalleplanificacioncalidad_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleplanprocedimientos_iddetalle_seq (OID = 24058)
--
SELECT pg_catalog.setval('detalleplanprocedimientos_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleplanprocesoscalidad_iddetalle_seq (OID = 24060)
--
SELECT pg_catalog.setval('detalleplanprocesoscalidad_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallepresupuesto_iddetalle_seq (OID = 24062)
--
SELECT pg_catalog.setval('detallepresupuesto_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleproducto_iddetalle_seq (OID = 24064)
--
SELECT pg_catalog.setval('detalleproducto_iddetalle_seq', 10, true);
--
-- Data for sequence public.detalleproductoreal_iddetalle_seq (OID = 24066)
--
SELECT pg_catalog.setval('detalleproductoreal_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallereclamocliente_iddetalle_seq (OID = 24068)
--
SELECT pg_catalog.setval('detallereclamocliente_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallereclamoempresametalurgica_iddetalle_seq (OID = 24070)
--
SELECT pg_catalog.setval('detallereclamoempresametalurgica_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallereclamoproveedor_iddetalle_seq (OID = 24072)
--
SELECT pg_catalog.setval('detallereclamoproveedor_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleremito_iddetalle_seq (OID = 24074)
--
SELECT pg_catalog.setval('detalleremito_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallerequerimientosmateriaprima_iddetalle_seq (OID = 24076)
--
SELECT pg_catalog.setval('detallerequerimientosmateriaprima_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalletrabajotercerizado_iddetalle_seq (OID = 24078)
--
SELECT pg_catalog.setval('detalletrabajotercerizado_iddetalle_seq', 1, false);
--
-- Data for sequence public.domicilio_iddomicilio_seq (OID = 24080)
--
SELECT pg_catalog.setval('domicilio_iddomicilio_seq', 32, true);
--
-- Data for sequence public.ejecucionetapaproduccion_idejecucion_seq (OID = 24082)
--
SELECT pg_catalog.setval('ejecucionetapaproduccion_idejecucion_seq', 1, false);
--
-- Data for sequence public.ejecucionplanificacioncalidad_idejecucion_seq (OID = 24084)
--
SELECT pg_catalog.setval('ejecucionplanificacioncalidad_idejecucion_seq', 1, false);
--
-- Data for sequence public.ejecucionplanificacionproduccion_idejecucion_seq (OID = 24086)
--
SELECT pg_catalog.setval('ejecucionplanificacionproduccion_idejecucion_seq', 1, false);
--
-- Data for sequence public.ejecucionprocesocalidad_idejecucion_seq (OID = 24088)
--
SELECT pg_catalog.setval('ejecucionprocesocalidad_idejecucion_seq', 1, false);
--
-- Data for sequence public.empleado_idempleado_seq (OID = 24090)
--
SELECT pg_catalog.setval('empleado_idempleado_seq', 1, false);
--
-- Data for sequence public.empresametalurgica_idempresametalurgica_seq (OID = 24092)
--
SELECT pg_catalog.setval('empresametalurgica_idempresametalurgica_seq', 1, false);
--
-- Data for sequence public.estadocliente_idestado_seq (OID = 24094)
--
SELECT pg_catalog.setval('estadocliente_idestado_seq', 4, true);
--
-- Data for sequence public.estadocompra_idestado_seq (OID = 24096)
--
SELECT pg_catalog.setval('estadocompra_idestado_seq', 1, false);
--
-- Data for sequence public.estadodetallecompra_idestado_seq (OID = 24098)
--
SELECT pg_catalog.setval('estadodetallecompra_idestado_seq', 1, false);
--
-- Data for sequence public.estadodetalletrabajotercerizado_idestado_seq (OID = 24100)
--
SELECT pg_catalog.setval('estadodetalletrabajotercerizado_idestado_seq', 1, false);
--
-- Data for sequence public.estadoejecetapaprod_idestado_seq (OID = 24102)
--
SELECT pg_catalog.setval('estadoejecetapaprod_idestado_seq', 1, false);
--
-- Data for sequence public.estadoejecplancalidad_idestado_seq (OID = 24104)
--
SELECT pg_catalog.setval('estadoejecplancalidad_idestado_seq', 1, false);
--
-- Data for sequence public.estadoejecplanifpedido_idestado_seq (OID = 24106)
--
SELECT pg_catalog.setval('estadoejecplanifpedido_idestado_seq', 1, false);
--
-- Data for sequence public.estadoejecucionprocesocalidad_idestado_seq (OID = 24108)
--
SELECT pg_catalog.setval('estadoejecucionprocesocalidad_idestado_seq', 1, false);
--
-- Data for sequence public.estadofactura_idestado_seq (OID = 24110)
--
SELECT pg_catalog.setval('estadofactura_idestado_seq', 1, false);
--
-- Data for sequence public.estadomaquina_idestado_seq (OID = 24112)
--
SELECT pg_catalog.setval('estadomaquina_idestado_seq', 1, false);
--
-- Data for sequence public.estadopedido_idestado_seq (OID = 24114)
--
SELECT pg_catalog.setval('estadopedido_idestado_seq', 1, false);
--
-- Data for sequence public.estadopiezareal_idestado_seq (OID = 24116)
--
SELECT pg_catalog.setval('estadopiezareal_idestado_seq', 1, false);
--
-- Data for sequence public.estadoproductoreal_idestado_seq (OID = 24118)
--
SELECT pg_catalog.setval('estadoproductoreal_idestado_seq', 1, false);
--
-- Data for sequence public.estadoremito_idestado_seq (OID = 24120)
--
SELECT pg_catalog.setval('estadoremito_idestado_seq', 1, false);
--
-- Data for sequence public.estadotrabajotercerizado_idestado_seq (OID = 24122)
--
SELECT pg_catalog.setval('estadotrabajotercerizado_idestado_seq', 1, false);
--
-- Data for sequence public.etapadeproduccion_idetapaproduccion_seq (OID = 24124)
--
SELECT pg_catalog.setval('etapadeproduccion_idetapaproduccion_seq', 6, true);
--
-- Data for sequence public.factura_idfactura_seq (OID = 24126)
--
SELECT pg_catalog.setval('factura_idfactura_seq', 1, false);
--
-- Data for sequence public.formadepago_idformapago_seq (OID = 24128)
--
SELECT pg_catalog.setval('formadepago_idformapago_seq', 1, false);
--
-- Data for sequence public.localidad_idlocalidad_seq (OID = 24130)
--
SELECT pg_catalog.setval('localidad_idlocalidad_seq', 11, true);
--
-- Data for sequence public.mantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 24132)
--
SELECT pg_catalog.setval('mantenimientocorrectivo_idmantenimientocorrectivo_seq', 1, false);
--
-- Data for sequence public.mantenimientopreventivo_idmantenimientopreventivo_seq (OID = 24134)
--
SELECT pg_catalog.setval('mantenimientopreventivo_idmantenimientopreventivo_seq', 1, false);
--
-- Data for sequence public.maquina_idmaquina_seq (OID = 24136)
--
SELECT pg_catalog.setval('maquina_idmaquina_seq', 1, false);
--
-- Data for sequence public.marca_idmarca_seq (OID = 24138)
--
SELECT pg_catalog.setval('marca_idmarca_seq', 1, false);
--
-- Data for sequence public.materiaprima_idmateriaprima_seq (OID = 24140)
--
SELECT pg_catalog.setval('materiaprima_idmateriaprima_seq', 1, true);
--
-- Data for sequence public.matriz_idmatriz_seq (OID = 24142)
--
SELECT pg_catalog.setval('matriz_idmatriz_seq', 2, true);
--
-- Data for sequence public.pedido_idpedido_seq (OID = 24144)
--
SELECT pg_catalog.setval('pedido_idpedido_seq', 1, false);
--
-- Data for sequence public.pedidomatriz_idpedidomatriz_seq (OID = 24146)
--
SELECT pg_catalog.setval('pedidomatriz_idpedidomatriz_seq', 1, false);
--
-- Data for sequence public.pieza_idpieza_seq (OID = 24148)
--
SELECT pg_catalog.setval('pieza_idpieza_seq', 14, true);
--
-- Data for sequence public.piezareal_idpiezareal_seq (OID = 24150)
--
SELECT pg_catalog.setval('piezareal_idpiezareal_seq', 1, false);
--
-- Data for sequence public.planificacioncalidad_idplanificacion_seq (OID = 24152)
--
SELECT pg_catalog.setval('planificacioncalidad_idplanificacion_seq', 1, false);
--
-- Data for sequence public.planificacionproduccion_idplanificacionproduccion_seq (OID = 24154)
--
SELECT pg_catalog.setval('planificacionproduccion_idplanificacionproduccion_seq', 1, false);
--
-- Data for sequence public.plano_idplano_seq (OID = 24156)
--
SELECT pg_catalog.setval('plano_idplano_seq', 1, false);
--
-- Data for sequence public.planprocedimientos_idplanprocedimientos_seq (OID = 24158)
--
SELECT pg_catalog.setval('planprocedimientos_idplanprocedimientos_seq', 1, false);
--
-- Data for sequence public.planprocesoscalidad_idplanprocesoscalidad_seq (OID = 24160)
--
SELECT pg_catalog.setval('planprocesoscalidad_idplanprocesoscalidad_seq', 1, false);
--
-- Data for sequence public.planrequerimientosmateriaprima_idplanrequerimientosmateriaprima (OID = 24162)
--
SELECT pg_catalog.setval('planrequerimientosmateriaprima_idplanrequerimientosmateriaprima', 1, false);
--
-- Data for sequence public.presupuesto_idpresupuesto_seq (OID = 24164)
--
SELECT pg_catalog.setval('presupuesto_idpresupuesto_seq', 1, false);
--
-- Data for sequence public.prioridad_idprioridad_seq (OID = 24166)
--
SELECT pg_catalog.setval('prioridad_idprioridad_seq', 3, true);
--
-- Data for sequence public.privilegio_idprivilegio_seq (OID = 24168)
--
SELECT pg_catalog.setval('privilegio_idprivilegio_seq', 1, false);
--
-- Data for sequence public.procesocalidad_idprocesocalidad_seq (OID = 24170)
--
SELECT pg_catalog.setval('procesocalidad_idprocesocalidad_seq', 1, false);
--
-- Data for sequence public.producto_idproducto_seq (OID = 24172)
--
SELECT pg_catalog.setval('producto_idproducto_seq', 5, true);
--
-- Data for sequence public.productoreal_idproductoreal_seq (OID = 24174)
--
SELECT pg_catalog.setval('productoreal_idproductoreal_seq', 1, false);
--
-- Data for sequence public.proveedor_idproveedor_seq (OID = 24176)
--
SELECT pg_catalog.setval('proveedor_idproveedor_seq', 1, false);
--
-- Data for sequence public.proveedormantenimientomaquina_idproveedormantenimiento_seq (OID = 24178)
--
SELECT pg_catalog.setval('proveedormantenimientomaquina_idproveedormantenimiento_seq', 1, false);
--
-- Data for sequence public.provincia_idprovincia_seq (OID = 24180)
--
SELECT pg_catalog.setval('provincia_idprovincia_seq', 14, true);
--
-- Data for sequence public.reclamocliente_idreclamo_seq (OID = 24182)
--
SELECT pg_catalog.setval('reclamocliente_idreclamo_seq', 1, false);
--
-- Data for sequence public.reclamoempresametalurgica_idreclamo_seq (OID = 24184)
--
SELECT pg_catalog.setval('reclamoempresametalurgica_idreclamo_seq', 1, false);
--
-- Data for sequence public.reclamoproveedor_idreclamo_seq (OID = 24186)
--
SELECT pg_catalog.setval('reclamoproveedor_idreclamo_seq', 1, false);
--
-- Data for sequence public.remito_idremito_seq (OID = 24188)
--
SELECT pg_catalog.setval('remito_idremito_seq', 1, false);
--
-- Data for sequence public.responsable_idresponsable_seq (OID = 24190)
--
SELECT pg_catalog.setval('responsable_idresponsable_seq', 13, true);
--
-- Data for sequence public.rol_idrol_seq (OID = 24192)
--
SELECT pg_catalog.setval('rol_idrol_seq', 1, false);
--
-- Data for sequence public.rotura_idrotura_seq (OID = 24194)
--
SELECT pg_catalog.setval('rotura_idrotura_seq', 1, false);
--
-- Data for sequence public.servicio_idservicio_seq (OID = 24196)
--
SELECT pg_catalog.setval('servicio_idservicio_seq', 1, false);
--
-- Data for sequence public.sesion_idsesion_seq (OID = 24198)
--
SELECT pg_catalog.setval('sesion_idsesion_seq', 1, false);
--
-- Data for sequence public.tipodocumento_idtipodocumento_seq (OID = 24200)
--
SELECT pg_catalog.setval('tipodocumento_idtipodocumento_seq', 3, true);
--
-- Data for sequence public.tipoiva_idtipoiva_seq (OID = 24202)
--
SELECT pg_catalog.setval('tipoiva_idtipoiva_seq', 1, false);
--
-- Data for sequence public.tipomaquina_idtipomaquina_seq (OID = 24204)
--
SELECT pg_catalog.setval('tipomaquina_idtipomaquina_seq', 1, false);
--
-- Data for sequence public.tiporeclamo_idtiporeclamo_seq (OID = 24206)
--
SELECT pg_catalog.setval('tiporeclamo_idtiporeclamo_seq', 1, false);
--
-- Data for sequence public.trabajotercerizado_idtrabajo_seq (OID = 24208)
--
SELECT pg_catalog.setval('trabajotercerizado_idtrabajo_seq', 1, false);
--
-- Data for sequence public.turno_idturno_seq (OID = 24210)
--
SELECT pg_catalog.setval('turno_idturno_seq', 1, false);
--
-- Comments
--
COMMENT ON SCHEMA public IS 'standard public schema';
