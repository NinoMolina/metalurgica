-- SQL Manager 2007 for PostgreSQL 4.5.0.8
-- ---------------------------------------
-- Host      : localhost
-- Database  : metalsoft
-- Version   : PostgreSQL 8.3.11, compiled by Visual C++ build 1400




--
-- Definition for sequence cliente_seq (OID = 18784) : 
--
SET search_path = public, pg_catalog;
CREATE SEQUENCE public.cliente_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence clientedb_seq (OID = 18786) : 
--
CREATE SEQUENCE public.clientedb_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table pedido (OID = 18788) : 
--
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
    idpedido bigint NOT NULL,
    cliente bigint,
    planprocedimientos bigint,
    planrequerimientosmateriaprima bigint,
    planprocesoscalidad bigint
) WITH OIDS;
--
-- Structure for table planificacioncalidad (OID = 18791) : 
--
CREATE TABLE public.planificacioncalidad (
    idplanificacion bigint NOT NULL,
    nroplanificacion bigint,
    fechacreacion date,
    observaciones character varying(50),
    fechainicioprevista date,
    fechafinprevista date,
    pedido bigint
) WITH OIDS;
--
-- Structure for table estadopedido (OID = 18794) : 
--
CREATE TABLE public.estadopedido (
    idestado bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table factura (OID = 18797) : 
--
CREATE TABLE public.factura (
    idfactura bigint NOT NULL,
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
-- Structure for table planificacionproduccion (OID = 18800) : 
--
CREATE TABLE public.planificacionproduccion (
    idplanificacionproduccion bigint NOT NULL,
    nroplanificacion bigint,
    fechacreacion date,
    observaciones character varying(50),
    fechainicioprevista date,
    fechafinprevista date,
    pedido bigint
) WITH OIDS;
--
-- Structure for table presupuesto (OID = 18803) : 
--
CREATE TABLE public.presupuesto (
    idpresupuesto bigint NOT NULL,
    fechapresupuesto date,
    montototal double precision,
    fechavencimiento date
) WITH OIDS;
--
-- Structure for table plano (OID = 18806) : 
--
CREATE TABLE public.plano (
    idplano bigint NOT NULL,
    nroplano bigint,
    escala integer,
    pedido bigint,
    imagen bytea[]
) WITH OIDS;
--
-- Structure for table remito (OID = 18812) : 
--
CREATE TABLE public.remito (
    idremito bigint NOT NULL,
    nroremito bigint,
    fechaemision date,
    pedido bigint
) WITH OIDS;
--
-- Structure for table trabajotercerizado (OID = 18815) : 
--
CREATE TABLE public.trabajotercerizado (
    idtrabajo bigint NOT NULL,
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
-- Structure for table cliente (OID = 18818) : 
--
CREATE TABLE public.cliente (
    nrocliente bigint,
    idcliente bigint NOT NULL,
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
-- Structure for table tipoiva (OID = 18821) : 
--
CREATE TABLE public.tipoiva (
    idtipoiva bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table formadepago (OID = 18824) : 
--
CREATE TABLE public.formadepago (
    idformapago integer NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table usuario (OID = 18827) : 
--
CREATE TABLE public.usuario (
    idusuario bigint NOT NULL,
    usuario character varying(50),
    clave character varying(50)
) WITH OIDS;
--
-- Structure for table estadofactura (OID = 18830) : 
--
CREATE TABLE public.estadofactura (
    idestado bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table comprobantepago (OID = 18833) : 
--
CREATE TABLE public.comprobantepago (
    idcomprobantepago bigint NOT NULL,
    nrocomprobantepago bigint,
    fechaemision date,
    monto double precision,
    formadepago bigint,
    usuario bigint,
    factura bigint
) WITH OIDS;
--
-- Structure for table estadotrabajotercerizado (OID = 18836) : 
--
CREATE TABLE public.estadotrabajotercerizado (
    idestado bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table empresametalurgica (OID = 18839) : 
--
CREATE TABLE public.empresametalurgica (
    idempresametalurgica bigint NOT NULL,
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
-- Structure for table prioridad (OID = 18842) : 
--
CREATE TABLE public.prioridad (
    idprioridad bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table estadocliente (OID = 18845) : 
--
CREATE TABLE public.estadocliente (
    idestado bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table responsable (OID = 18848) : 
--
CREATE TABLE public.responsable (
    idresponsable bigint NOT NULL,
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
-- Structure for table condicioniva (OID = 18851) : 
--
CREATE TABLE public.condicioniva (
    idcondicioniva bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table domicilio (OID = 18854) : 
--
CREATE TABLE public.domicilio (
    iddomicilio bigint NOT NULL,
    calle character varying(50),
    numerocalle integer,
    piso integer,
    depto character varying(5),
    torre character varying(10),
    barrio bigint
) WITH OIDS;
--
-- Structure for table rotura (OID = 18857) : 
--
CREATE TABLE public.rotura (
    idrotura bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table categoria (OID = 18860) : 
--
CREATE TABLE public.categoria (
    idcategoria integer NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table cargo (OID = 18863) : 
--
CREATE TABLE public.cargo (
    idcargo bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table turno (OID = 18866) : 
--
CREATE TABLE public.turno (
    idturno bigint NOT NULL,
    horainicio time without time zone,
    horafin time without time zone,
    nombre character varying(20),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table marca (OID = 18869) : 
--
CREATE TABLE public.marca (
    idmarca bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadomaquina (OID = 18872) : 
--
CREATE TABLE public.estadomaquina (
    idestado bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table tipomaquina (OID = 18875) : 
--
CREATE TABLE public.tipomaquina (
    idtipomaquina bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecetapaprod (OID = 18878) : 
--
CREATE TABLE public.estadoejecetapaprod (
    idestado bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table servicio (OID = 18881) : 
--
CREATE TABLE public.servicio (
    idservicio bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table tipodocumento (OID = 18884) : 
--
CREATE TABLE public.tipodocumento (
    idtipodocumento bigint NOT NULL,
    tipo character varying(50),
    nombre character varying(50)
) WITH OIDS;
--
-- Structure for table codigodebarra (OID = 18887) : 
--
CREATE TABLE public.codigodebarra (
    idcodigo bigint NOT NULL,
    descripcion character varying(50),
    codigo character varying(50)
) WITH OIDS;
--
-- Structure for table estadopiezareal (OID = 18890) : 
--
CREATE TABLE public.estadopiezareal (
    idestado bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecplanifpedido (OID = 18893) : 
--
CREATE TABLE public.estadoejecplanifpedido (
    idestado bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table proveedormantenimientomaquina (OID = 18896) : 
--
CREATE TABLE public.proveedormantenimientomaquina (
    idproveedormantenimiento bigint NOT NULL,
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
-- Structure for table rol (OID = 18899) : 
--
CREATE TABLE public.rol (
    idrol bigint NOT NULL,
    rol character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table privilegio (OID = 18902) : 
--
CREATE TABLE public.privilegio (
    idprivilegio bigint NOT NULL,
    privilegio character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table accioncalidad (OID = 18905) : 
--
CREATE TABLE public.accioncalidad (
    idaccioncalidad bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table empleado (OID = 18908) : 
--
CREATE TABLE public.empleado (
    idempleado bigint NOT NULL,
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
-- Structure for table proveedor (OID = 18911) : 
--
CREATE TABLE public.proveedor (
    idproveedor bigint NOT NULL,
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
-- Structure for table estadocompra (OID = 18914) : 
--
CREATE TABLE public.estadocompra (
    idestado bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table tpomateriaprima (OID = 18917) : 
--
CREATE TABLE public.tpomateriaprima (
    idtipomateriaprima bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table sesion (OID = 18920) : 
--
CREATE TABLE public.sesion (
    idsesion bigint NOT NULL,
    fechainicio date,
    fechafin date,
    horainicio time without time zone,
    horafin time without time zone,
    usuario bigint
) WITH OIDS;
--
-- Structure for table materiaprima (OID = 18923) : 
--
CREATE TABLE public.materiaprima (
    idmateriaprima bigint NOT NULL,
    codproducto bigint,
    nombre character varying(50),
    precio double precision,
    proveedor bigint,
    fechaalta date,
    fechabaja date,
    codbarra bigint
) WITH OIDS;
--
-- Structure for table matriz (OID = 18926) : 
--
CREATE TABLE public.matriz (
    idmatriz bigint NOT NULL,
    codmatriz bigint,
    nombre character varying(50),
    descripcion character varying(50),
    observaciones character varying(100),
    fechacreacion date,
    materiaprima bigint,
    tipomaterial bigint
) WITH OIDS;
--
-- Structure for table tipomaterial (OID = 18929) : 
--
CREATE TABLE public.tipomaterial (
    idtipomaterial bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table pieza (OID = 18932) : 
--
CREATE TABLE public.pieza (
    idpieza bigint NOT NULL,
    nombre character varying(50),
    tipomaterial bigint,
    dimensiones character varying(100),
    materiaprima bigint,
    matriz bigint
) WITH OIDS;
--
-- Structure for table producto (OID = 18935) : 
--
CREATE TABLE public.producto (
    idproducto bigint NOT NULL,
    nroproducto bigint,
    nombre character varying(50),
    stock integer,
    preciounitario double precision,
    descripcion character varying(50),
    codbarra bigint
) WITH OIDS;
--
-- Structure for table estadoejecplancalidad (OID = 18938) : 
--
CREATE TABLE public.estadoejecplancalidad (
    idestado bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table mantenimientopreventivo (OID = 18941) : 
--
CREATE TABLE public.mantenimientopreventivo (
    idmantenimientopreventivo bigint NOT NULL,
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
-- Structure for table mantenimientocorrectivo (OID = 18944) : 
--
CREATE TABLE public.mantenimientocorrectivo (
    idmantenimientocorrectivo bigint NOT NULL,
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
-- Structure for table maquina (OID = 18947) : 
--
CREATE TABLE public.maquina (
    idmaquina bigint NOT NULL,
    nombre character varying(50),
    marca bigint,
    descripcion character varying(100),
    estado bigint,
    tipomaquina bigint
) WITH OIDS;
--
-- Structure for table etapadeproduccion (OID = 18950) : 
--
CREATE TABLE public.etapadeproduccion (
    idetapaproduccion bigint NOT NULL,
    nroetapaproduccion bigint,
    nombre character varying(50),
    horasmaquina time without time zone,
    horashombre time without time zone,
    maquina bigint,
    duracionestimada time without time zone,
    fechacreacion date
) WITH OIDS;
--
-- Structure for table piezareal (OID = 18953) : 
--
CREATE TABLE public.piezareal (
    idpiezareal bigint NOT NULL,
    idpieza bigint NOT NULL,
    estado bigint,
    nropieza integer
) WITH OIDS;
--
-- Structure for table ejecucionplanificacionproduccion (OID = 18956) : 
--
CREATE TABLE public.ejecucionplanificacionproduccion (
    idejecucion bigint NOT NULL,
    idplanificacionproduccion bigint NOT NULL,
    fechainicio date,
    fechafin date,
    horainicio time without time zone,
    horafin time without time zone,
    estado bigint
) WITH OIDS;
--
-- Structure for table procesocalidad (OID = 18959) : 
--
CREATE TABLE public.procesocalidad (
    idprocesocalidad bigint NOT NULL,
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
-- Structure for table ejecucionplanificacioncalidad (OID = 18962) : 
--
CREATE TABLE public.ejecucionplanificacioncalidad (
    idejecucion bigint NOT NULL,
    idplanificacioncalidad bigint NOT NULL,
    fechainicio date,
    fechafin date,
    horainicio time without time zone,
    horafin time without time zone,
    estado bigint
) WITH OIDS;
--
-- Structure for table ejecucionprocesocalidad (OID = 18965) : 
--
CREATE TABLE public.ejecucionprocesocalidad (
    nroejecucion bigint NOT NULL,
    idprocesocalidad bigint NOT NULL,
    duracionreal time without time zone,
    resultado character varying(100),
    estado bigint
) WITH OIDS;
--
-- Structure for table ejecucionetapaproduccion (OID = 18968) : 
--
CREATE TABLE public.ejecucionetapaproduccion (
    nroejecucion bigint NOT NULL,
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
    estado bigint
) WITH OIDS;
--
-- Structure for table compra (OID = 18971) : 
--
CREATE TABLE public.compra (
    idcompra bigint NOT NULL,
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
-- Structure for table asistencia (OID = 18974) : 
--
CREATE TABLE public.asistencia (
    empleado bigint NOT NULL,
    fechaingreso bigint NOT NULL,
    horaingreso time without time zone NOT NULL,
    horaegreso time without time zone,
    observaciones character varying(100)
) WITH OIDS;
--
-- Structure for table detallemantenimientocorrectivo (OID = 18977) : 
--
CREATE TABLE public.detallemantenimientocorrectivo (
    idmantenimientocorrectivo bigint NOT NULL,
    iddetalle bigint NOT NULL,
    duracion time without time zone,
    rotura bigint,
    motivorotura character varying(100)
) WITH OIDS;
--
-- Structure for table detallemantenimientopreventivo (OID = 18980) : 
--
CREATE TABLE public.detallemantenimientopreventivo (
    idmantenimientopreventivo bigint NOT NULL,
    iddetalle bigint NOT NULL,
    duracion time without time zone,
    servicio bigint,
    observaciones character varying(100)
) WITH OIDS;
--
-- Structure for table detalleejecucionplanificacion (OID = 18983) : 
--
CREATE TABLE public.detalleejecucionplanificacion (
    iddetalle bigint NOT NULL,
    idejecucionplanificacionproduccion bigint NOT NULL,
    pieza bigint,
    ejecucionetapa bigint NOT NULL,
    idplanificacionproduccion bigint NOT NULL,
    idetapaproduccion bigint,
    piezareal bigint
) WITH OIDS;
--
-- Structure for table detalleejecucionplanificacioncalidad (OID = 18986) : 
--
CREATE TABLE public.detalleejecucionplanificacioncalidad (
    iddetalle bigint NOT NULL,
    idejecucionplanificacioncalidad bigint NOT NULL,
    idplanificacioncalidad bigint NOT NULL,
    ejecucionprocesocalidad bigint,
    idprocesocalidad bigint,
    pieza bigint,
    piezareal bigint
) WITH OIDS;
--
-- Structure for table detallaplanificacionproduccion (OID = 18989) : 
--
CREATE TABLE public.detallaplanificacionproduccion (
    iddetalle bigint NOT NULL,
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
-- Structure for table detalleproducto (OID = 18992) : 
--
CREATE TABLE public.detalleproducto (
    iddetalle bigint NOT NULL,
    idproducto bigint NOT NULL,
    cantidadpiezas integer,
    descripcion character varying(50),
    pieza bigint
) WITH OIDS;
--
-- Structure for table detallepedido (OID = 18995) : 
--
CREATE TABLE public.detallepedido (
    iddetalle bigint NOT NULL,
    idpedido bigint NOT NULL,
    precio double precision,
    cantidad integer,
    producto bigint
) WITH OIDS;
--
-- Structure for table detalletrabajotercerizado (OID = 18998) : 
--
CREATE TABLE public.detalletrabajotercerizado (
    iddetalle bigint NOT NULL,
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
-- Structure for table detalleplanificacioncalidad (OID = 19001) : 
--
CREATE TABLE public.detalleplanificacioncalidad (
    iddetalle bigint NOT NULL,
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
-- Structure for table detallecompra (OID = 19004) : 
--
CREATE TABLE public.detallecompra (
    iddetalle bigint NOT NULL,
    idcompra bigint NOT NULL,
    cantidad integer,
    materiaprima bigint,
    preciohistorico double precision,
    fecharecepcionparcial date,
    estado bigint
) WITH OIDS;
--
-- Structure for table detalleremito (OID = 19007) : 
--
CREATE TABLE public.detalleremito (
    iddetalle bigint NOT NULL,
    idremito bigint NOT NULL,
    cantidad integer,
    descripcion character varying(50),
    producto bigint
) WITH OIDS;
--
-- Structure for table tiporeclamo (OID = 19010) : 
--
CREATE TABLE public.tiporeclamo (
    idtiporeclamo bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table reclamoempresametalurgica (OID = 19013) : 
--
CREATE TABLE public.reclamoempresametalurgica (
    idreclamo bigint NOT NULL,
    nroreclamo bigint,
    tiporeclamo bigint,
    motivo character varying(50),
    fechareclamo date,
    trabajotercerizado bigint
) WITH OIDS;
--
-- Structure for table reclamoproveedor (OID = 19016) : 
--
CREATE TABLE public.reclamoproveedor (
    idreclamo bigint NOT NULL,
    nroreclamo bigint,
    tiporeclamo bigint,
    motivo character varying(50),
    fechareclamo date,
    compra bigint
) WITH OIDS;
--
-- Structure for table reclamocliente (OID = 19019) : 
--
CREATE TABLE public.reclamocliente (
    idreclamo bigint NOT NULL,
    nroreclamo bigint,
    tiporeclamo bigint,
    motivo character varying(100),
    fechareclamo date,
    cliente bigint
) WITH OIDS;
--
-- Structure for table detallereclamocliente (OID = 19022) : 
--
CREATE TABLE public.detallereclamocliente (
    iddetalle bigint NOT NULL,
    idreclamo bigint NOT NULL,
    cantidad integer,
    descripcion character varying(50),
    motivo character varying(50),
    producto bigint
) WITH OIDS;
--
-- Structure for table detallereclamoproveedor (OID = 19025) : 
--
CREATE TABLE public.detallereclamoproveedor (
    iddetalle bigint NOT NULL,
    idreclamo bigint NOT NULL,
    cantidad integer,
    descripcion character varying(50),
    motivo character varying(50),
    iddetallecompra bigint,
    fechaegreso date,
    idcompra bigint
) WITH OIDS;
--
-- Structure for table detallereclamoempresametalurgica (OID = 19028) : 
--
CREATE TABLE public.detallereclamoempresametalurgica (
    iddetalle bigint NOT NULL,
    idreclamo bigint NOT NULL,
    cantidad integer,
    descripcion character varying(50),
    motivo character varying(50),
    pieza bigint,
    fechaegreso date
) WITH OIDS;
--
-- Structure for table proveedorxmateriaprima (OID = 19031) : 
--
CREATE TABLE public.proveedorxmateriaprima (
    idproveedor bigint NOT NULL,
    idmateriaprima bigint NOT NULL,
    precio double precision
) WITH OIDS;
--
-- Structure for table maquinaxejecucionetapaproduccion (OID = 19034) : 
--
CREATE TABLE public.maquinaxejecucionetapaproduccion (
    idejecucionetapaproduccion bigint NOT NULL,
    idetapaproduccion bigint NOT NULL,
    idmaquina bigint NOT NULL,
    horasmaquina time without time zone,
    horashombre time without time zone
) WITH OIDS;
--
-- Structure for table maquinaxprocesocalidad (OID = 19037) : 
--
CREATE TABLE public.maquinaxprocesocalidad (
    idprocesocalidad bigint NOT NULL,
    idmaquina bigint NOT NULL,
    duracion time without time zone,
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table piezaxetapadeproduccion (OID = 19040) : 
--
CREATE TABLE public.piezaxetapadeproduccion (
    idpieza bigint NOT NULL,
    idetapaproduccion bigint NOT NULL,
    duracion time without time zone,
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table empleadoxturno (OID = 19043) : 
--
CREATE TABLE public.empleadoxturno (
    idempleado bigint NOT NULL,
    idturno bigint NOT NULL
) WITH OIDS;
--
-- Structure for table provincia (OID = 19046) : 
--
CREATE TABLE public.provincia (
    idprovincia bigint NOT NULL,
    nombre character varying(50)
) WITH OIDS;
--
-- Structure for table localidad (OID = 19049) : 
--
CREATE TABLE public.localidad (
    idlocalidad bigint NOT NULL,
    nombre character varying(50),
    provincia bigint
) WITH OIDS;
--
-- Structure for table barrio (OID = 19052) : 
--
CREATE TABLE public.barrio (
    idbarrio bigint NOT NULL,
    nombre bigint,
    codpostal bigint,
    localidad bigint
) WITH OIDS;
--
-- Structure for table usuarioxrol (OID = 19055) : 
--
CREATE TABLE public.usuarioxrol (
    idrol bigint NOT NULL,
    idusuario bigint NOT NULL
) WITH OIDS;
--
-- Structure for table rolxprivilegio (OID = 19058) : 
--
CREATE TABLE public.rolxprivilegio (
    idrol bigint NOT NULL,
    idprivilegio bigint NOT NULL
) WITH OIDS;
--
-- Structure for table planrequerimientosmateriaprima (OID = 19914) : 
--
CREATE TABLE public.planrequerimientosmateriaprima (
    idplanrequerimientosmateriaprima bigint NOT NULL
) WITH OIDS;
--
-- Structure for table planprocedimientos (OID = 19919) : 
--
CREATE TABLE public.planprocedimientos (
    idplanprocedimientos integer NOT NULL
) WITH OIDS;
--
-- Structure for table planprocesoscalidad (OID = 19924) : 
--
CREATE TABLE public.planprocesoscalidad (
    idplanprocesoscalidad integer NOT NULL
) WITH OIDS;
--
-- Structure for table detallerequerimientosmateriaprima (OID = 19929) : 
--
CREATE TABLE public.detallerequerimientosmateriaprima (
    iddetalle bigint NOT NULL,
    idplanrequerimientosmateriaprima bigint NOT NULL,
    cantidadmateriaprima integer,
    idpieza bigint,
    idmateriaprima bigint
) WITH OIDS;
--
-- Structure for table detalleplanprocedimientos (OID = 19949) : 
--
CREATE TABLE public.detalleplanprocedimientos (
    iddetalle bigint NOT NULL,
    idplanpprocedimientos bigint NOT NULL,
    idpieza bigint,
    idetapaproduccion bigint,
    duracionestimada time without time zone
) WITH OIDS;
--
-- Structure for table detalleplanprocesoscalidad (OID = 19969) : 
--
CREATE TABLE public.detalleplanprocesoscalidad (
    iddetalle bigint NOT NULL,
    idplanprocesoscalidad bigint NOT NULL,
    idpieza bigint,
    idprocesocalidad bigint,
    duracionestimada time without time zone
) WITH OIDS;
--
-- Structure for table pedidomatriz (OID = 19989) : 
--
CREATE TABLE public.pedidomatriz (
    idpedidomatriz bigint NOT NULL,
    nropedidomatriz bigint,
    fechapedidomatriz date,
    idmatriz bigint,
    observaciones character varying(100)
) WITH OIDS;
--
-- Structure for table estadodetallecompra (OID = 20014) : 
--
CREATE TABLE public.estadodetallecompra (
    idestado bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadodetalletrabajotercerizado (OID = 20019) : 
--
CREATE TABLE public.estadodetalletrabajotercerizado (
    idestado bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecucionprocesocalidad (OID = 20069) : 
--
CREATE TABLE public.estadoejecucionprocesocalidad (
    idestado bigint NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Data for table public.usuario (OID = 18827) (LIMIT 0,2)
--
INSERT INTO usuario (idusuario, usuario, clave)
VALUES (1, 'nino', 'nino');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (2, 'admin', 'admin');

--
-- Data for table public.rol (OID = 18899) (LIMIT 0,4)
--
INSERT INTO rol (idrol, rol, descripcion)
VALUES (1, 'ADM', 'Administrador del sistema');

INSERT INTO rol (idrol, rol, descripcion)
VALUES (2, 'RCO', 'Responsable de compras');

INSERT INTO rol (idrol, rol, descripcion)
VALUES (3, 'RPR', 'Responsable de produccion');

INSERT INTO rol (idrol, rol, descripcion)
VALUES (4, 'RVE', 'Responsable de ventas');

--
-- Data for table public.privilegio (OID = 18902) (LIMIT 0,2)
--
INSERT INTO privilegio (idprivilegio, privilegio, descripcion)
VALUES (1, 'ALL', 'Acceso total al sistema');

INSERT INTO privilegio (idprivilegio, privilegio, descripcion)
VALUES (2, 'CARGARCLIENTE', 'Poder para dar de alta un nuevo cliente');

--
-- Data for table public.usuarioxrol (OID = 19055) (LIMIT 0,2)
--
INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (1, 1);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (1, 2);

--
-- Data for table public.rolxprivilegio (OID = 19058) (LIMIT 0,2)
--
INSERT INTO rolxprivilegio (idrol, idprivilegio)
VALUES (1, 1);

INSERT INTO rolxprivilegio (idrol, idprivilegio)
VALUES (2, 1);

--
-- Definition for index pedido_nropedido_key (OID = 19073) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_nropedido_key UNIQUE (nropedido);
--
-- Definition for index pedido_pkey (OID = 19075) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (idpedido);
--
-- Definition for index planificacioncalidad_pkey (OID = 19077) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT planificacioncalidad_pkey PRIMARY KEY (idplanificacion);
--
-- Definition for index estadopedido_pkey (OID = 19079) : 
--
ALTER TABLE ONLY estadopedido
    ADD CONSTRAINT estadopedido_pkey PRIMARY KEY (idestado);
--
-- Definition for index factura_pkey (OID = 19081) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_pkey PRIMARY KEY (idfactura);
--
-- Definition for index planificacionproduccion_pkey (OID = 19083) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_pkey PRIMARY KEY (idplanificacionproduccion);
--
-- Definition for index presupuesto_pkey (OID = 19085) : 
--
ALTER TABLE ONLY presupuesto
    ADD CONSTRAINT presupuesto_pkey PRIMARY KEY (idpresupuesto);
--
-- Definition for index plano_pkey (OID = 19087) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT plano_pkey PRIMARY KEY (idplano);
--
-- Definition for index remito_pkey (OID = 19089) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_pkey PRIMARY KEY (idremito);
--
-- Definition for index trabajotercerizado_pkey (OID = 19091) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_pkey PRIMARY KEY (idtrabajo);
--
-- Definition for index pedido_fk (OID = 19093) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk FOREIGN KEY (estado) REFERENCES estadopedido(idestado);
--
-- Definition for index pedido_fk1 (OID = 19098) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk1 FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index pedido_fk2 (OID = 19103) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk2 FOREIGN KEY (presupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index pedido_fk3 (OID = 19108) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk3 FOREIGN KEY (plano) REFERENCES plano(idplano);
--
-- Definition for index cliente_pkey (OID = 19113) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (idcliente);
--
-- Definition for index pedido_fk4 (OID = 19115) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk4 FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index tipoiva_pkey (OID = 19120) : 
--
ALTER TABLE ONLY tipoiva
    ADD CONSTRAINT tipoiva_pkey PRIMARY KEY (idtipoiva);
--
-- Definition for index formadepago_pkey (OID = 19122) : 
--
ALTER TABLE ONLY formadepago
    ADD CONSTRAINT formadepago_pkey PRIMARY KEY (idformapago);
--
-- Definition for index usuario_pkey (OID = 19124) : 
--
ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (idusuario);
--
-- Definition for index estadofactura_pkey (OID = 19126) : 
--
ALTER TABLE ONLY estadofactura
    ADD CONSTRAINT estadofactura_pkey PRIMARY KEY (idestado);
--
-- Definition for index factura_fk (OID = 19128) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk FOREIGN KEY (tipoiva) REFERENCES tipoiva(idtipoiva);
--
-- Definition for index factura_fk1 (OID = 19133) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk1 FOREIGN KEY (formapago) REFERENCES formadepago(idformapago);
--
-- Definition for index factura_fk2 (OID = 19138) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk2 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index factura_fk3 (OID = 19143) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk3 FOREIGN KEY (estado) REFERENCES estadofactura(idestado);
--
-- Definition for index plano_fk (OID = 19148) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT plano_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index remito_fk (OID = 19153) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index trabajotercerizado_fk (OID = 19158) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index planificacionproduccion_fk (OID = 19163) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index planificacioncalidad_fk (OID = 19168) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT planificacioncalidad_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index comprobantepago_pkey (OID = 19173) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_pkey PRIMARY KEY (idcomprobantepago);
--
-- Definition for index comprobantepago_fk (OID = 19175) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk FOREIGN KEY (formadepago) REFERENCES formadepago(idformapago);
--
-- Definition for index comprobantepago_fk1 (OID = 19180) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk1 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index comprobantepago_fk2 (OID = 19185) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk2 FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index estadotrabajotercerizado_pkey (OID = 19190) : 
--
ALTER TABLE ONLY estadotrabajotercerizado
    ADD CONSTRAINT estadotrabajotercerizado_pkey PRIMARY KEY (idestado);
--
-- Definition for index empresametalurgica_pkey (OID = 19192) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_pkey PRIMARY KEY (idempresametalurgica);
--
-- Definition for index trabajotercerizado_fk1 (OID = 19194) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk1 FOREIGN KEY (empresa) REFERENCES empresametalurgica(idempresametalurgica);
--
-- Definition for index trabajotercerizado_fk2 (OID = 19199) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk2 FOREIGN KEY (estado) REFERENCES estadotrabajotercerizado(idestado);
--
-- Definition for index prioridad_pkey (OID = 19204) : 
--
ALTER TABLE ONLY prioridad
    ADD CONSTRAINT prioridad_pkey PRIMARY KEY (idprioridad);
--
-- Definition for index estadocliente_pkey (OID = 19206) : 
--
ALTER TABLE ONLY estadocliente
    ADD CONSTRAINT estadocliente_pkey PRIMARY KEY (idestado);
--
-- Definition for index responsable_pkey (OID = 19208) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_pkey PRIMARY KEY (idresponsable);
--
-- Definition for index condicioniva_pkey (OID = 19210) : 
--
ALTER TABLE ONLY condicioniva
    ADD CONSTRAINT condicioniva_pkey PRIMARY KEY (idcondicioniva);
--
-- Definition for index domicilio_pkey (OID = 19212) : 
--
ALTER TABLE ONLY domicilio
    ADD CONSTRAINT domicilio_pkey PRIMARY KEY (iddomicilio);
--
-- Definition for index cliente_fk (OID = 19214) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index cliente_fk1 (OID = 19219) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk1 FOREIGN KEY (estado) REFERENCES estadocliente(idestado);
--
-- Definition for index cliente_fk2 (OID = 19224) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk2 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index cliente_fk3 (OID = 19229) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk3 FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index cliente_fk4 (OID = 19234) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk4 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index cliente_fk5 (OID = 19239) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk5 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index rotura_pkey (OID = 19244) : 
--
ALTER TABLE ONLY rotura
    ADD CONSTRAINT rotura_pkey PRIMARY KEY (idrotura);
--
-- Definition for index categoria_pkey (OID = 19246) : 
--
ALTER TABLE ONLY categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (idcategoria);
--
-- Definition for index cargo_pkey (OID = 19248) : 
--
ALTER TABLE ONLY cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (idcargo);
--
-- Definition for index turno_pkey (OID = 19250) : 
--
ALTER TABLE ONLY turno
    ADD CONSTRAINT turno_pkey PRIMARY KEY (idturno);
--
-- Definition for index marca_pkey (OID = 19252) : 
--
ALTER TABLE ONLY marca
    ADD CONSTRAINT marca_pkey PRIMARY KEY (idmarca);
--
-- Definition for index estadomaquina_pkey (OID = 19254) : 
--
ALTER TABLE ONLY estadomaquina
    ADD CONSTRAINT estadomaquina_pkey PRIMARY KEY (idestado);
--
-- Definition for index tipomaquina_pkey (OID = 19256) : 
--
ALTER TABLE ONLY tipomaquina
    ADD CONSTRAINT tipomaquina_pkey PRIMARY KEY (idtipomaquina);
--
-- Definition for index estadoejecetapaprod_pkey (OID = 19258) : 
--
ALTER TABLE ONLY estadoejecetapaprod
    ADD CONSTRAINT estadoejecetapaprod_pkey PRIMARY KEY (idestado);
--
-- Definition for index servicio_pkey (OID = 19260) : 
--
ALTER TABLE ONLY servicio
    ADD CONSTRAINT servicio_pkey PRIMARY KEY (idservicio);
--
-- Definition for index tipodocumento_pkey (OID = 19262) : 
--
ALTER TABLE ONLY tipodocumento
    ADD CONSTRAINT tipodocumento_pkey PRIMARY KEY (idtipodocumento);
--
-- Definition for index empresametalurgica_fk (OID = 19264) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index empresametalurgica_fk1 (OID = 19269) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index empresametalurgica_fk2 (OID = 19274) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk2 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index responsable_fk (OID = 19279) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_fk FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index responsable_fk1 (OID = 19284) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_fk1 FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index codigodebarra_pkey (OID = 19289) : 
--
ALTER TABLE ONLY codigodebarra
    ADD CONSTRAINT codigodebarra_pkey PRIMARY KEY (idcodigo);
--
-- Definition for index estadopiezareal_pkey (OID = 19291) : 
--
ALTER TABLE ONLY estadopiezareal
    ADD CONSTRAINT estadopiezareal_pkey PRIMARY KEY (idestado);
--
-- Definition for index estadoejecplanifpedido_pkey (OID = 19293) : 
--
ALTER TABLE ONLY estadoejecplanifpedido
    ADD CONSTRAINT estadoejecplanifpedido_pkey PRIMARY KEY (idestado);
--
-- Definition for index proveedormantenimientomaquina_fk (OID = 19295) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index proveedormantenimientomaquina_fk1 (OID = 19300) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index proveedormantenimientomaquina_fk2 (OID = 19305) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk2 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index rol_pkey (OID = 19310) : 
--
ALTER TABLE ONLY rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (idrol);
--
-- Definition for index privilegio_pkey (OID = 19312) : 
--
ALTER TABLE ONLY privilegio
    ADD CONSTRAINT privilegio_pkey PRIMARY KEY (idprivilegio);
--
-- Definition for index accioncalidad_pkey (OID = 19314) : 
--
ALTER TABLE ONLY accioncalidad
    ADD CONSTRAINT accioncalidad_pkey PRIMARY KEY (idaccioncalidad);
--
-- Definition for index empleado_pkey (OID = 19316) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (idempleado);
--
-- Definition for index empleado_fk (OID = 19318) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index empleado_fk1 (OID = 19323) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk1 FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index empleado_fk2 (OID = 19328) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk2 FOREIGN KEY (categoria) REFERENCES categoria(idcategoria);
--
-- Definition for index empleado_fk3 (OID = 19333) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk3 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index empleado_fk4 (OID = 19338) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk4 FOREIGN KEY (cargo) REFERENCES cargo(idcargo);
--
-- Definition for index proveedor_pkey (OID = 19343) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_pkey PRIMARY KEY (idproveedor);
--
-- Definition for index proveedor_fk (OID = 19345) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index proveedor_fk1 (OID = 19350) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index proveedor_fk2 (OID = 19355) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk2 FOREIGN KEY (condicion) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index estadocompra_pkey (OID = 19360) : 
--
ALTER TABLE ONLY estadocompra
    ADD CONSTRAINT estadocompra_pkey PRIMARY KEY (idestado);
--
-- Definition for index tpomateriaprima_pkey (OID = 19362) : 
--
ALTER TABLE ONLY tpomateriaprima
    ADD CONSTRAINT tpomateriaprima_pkey PRIMARY KEY (idtipomateriaprima);
--
-- Definition for index sesion_pkey (OID = 19364) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT sesion_pkey PRIMARY KEY (idsesion);
--
-- Definition for index sesion_fk (OID = 19366) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT sesion_fk FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index materiaprima_pkey (OID = 19371) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_pkey PRIMARY KEY (idmateriaprima);
--
-- Definition for index materiaprima_fk (OID = 19373) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk FOREIGN KEY (proveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index materiaprima_fk1 (OID = 19378) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk1 FOREIGN KEY (codbarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index matriz_pkey (OID = 19383) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT matriz_pkey PRIMARY KEY (idmatriz);
--
-- Definition for index matriz_fk (OID = 19385) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT matriz_fk FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index tipomaterial_pkey (OID = 19390) : 
--
ALTER TABLE ONLY tipomaterial
    ADD CONSTRAINT tipomaterial_pkey PRIMARY KEY (idtipomaterial);
--
-- Definition for index pieza_pkey (OID = 19392) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_pkey PRIMARY KEY (idpieza);
--
-- Definition for index pieza_fk (OID = 19394) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk FOREIGN KEY (tipomaterial) REFERENCES tipomaterial(idtipomaterial);
--
-- Definition for index pieza_fk1 (OID = 19399) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk1 FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index pieza_fk2 (OID = 19404) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk2 FOREIGN KEY (matriz) REFERENCES matriz(idmatriz);
--
-- Definition for index matriz_fk1 (OID = 19409) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT matriz_fk1 FOREIGN KEY (tipomaterial) REFERENCES tipomaterial(idtipomaterial);
--
-- Definition for index producto_pkey (OID = 19414) : 
--
ALTER TABLE ONLY producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (idproducto);
--
-- Definition for index producto_fk (OID = 19416) : 
--
ALTER TABLE ONLY producto
    ADD CONSTRAINT producto_fk FOREIGN KEY (codbarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index estadoejecplancalidad_pkey (OID = 19421) : 
--
ALTER TABLE ONLY estadoejecplancalidad
    ADD CONSTRAINT estadoejecplancalidad_pkey PRIMARY KEY (idestado);
--
-- Definition for index mantenimientopreventivo_pkey (OID = 19423) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_pkey PRIMARY KEY (idmantenimientopreventivo);
--
-- Definition for index proveedormantenimientomaquina_pkey (OID = 19425) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_pkey PRIMARY KEY (idproveedormantenimiento);
--
-- Definition for index mantenimientopreventivo_fk (OID = 19427) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_fk FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index mantenimientocorrectivo_pkey (OID = 19432) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_pkey PRIMARY KEY (idmantenimientocorrectivo);
--
-- Definition for index mantenimientocorrectivo_fk (OID = 19434) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_fk FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index mantenimientocorrectivo_fk1 (OID = 19439) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_fk1 FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index maquina_pkey (OID = 19444) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_pkey PRIMARY KEY (idmaquina);
--
-- Definition for index maquina_fk (OID = 19446) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk FOREIGN KEY (marca) REFERENCES marca(idmarca);
--
-- Definition for index maquina_fk1 (OID = 19451) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk1 FOREIGN KEY (estado) REFERENCES estadomaquina(idestado);
--
-- Definition for index maquina_fk2 (OID = 19456) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk2 FOREIGN KEY (tipomaquina) REFERENCES tipomaquina(idtipomaquina);
--
-- Definition for index etapadeproduccion_pkey (OID = 19461) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_pkey PRIMARY KEY (idetapaproduccion);
--
-- Definition for index etapadeproduccion_fk (OID = 19463) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_fk FOREIGN KEY (maquina) REFERENCES maquina(idmaquina);
--
-- Definition for index piezareal_pkey (OID = 19468) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_pkey PRIMARY KEY (idpiezareal, idpieza);
--
-- Definition for index piezareal_idpiezareal_key (OID = 19470) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_idpiezareal_key UNIQUE (idpiezareal);
--
-- Definition for index piezareal_idpieza_key (OID = 19472) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_idpieza_key UNIQUE (idpieza);
--
-- Definition for index piezareal_fk (OID = 19474) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_fk FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index piezareal_fk1 (OID = 19479) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_fk1 FOREIGN KEY (estado) REFERENCES estadopiezareal(idestado);
--
-- Definition for index ejecucionplanificacionproduccion_pkey (OID = 19484) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_pkey PRIMARY KEY (idejecucion, idplanificacionproduccion);
--
-- Definition for index ejecucionplanificacionproduccion_idejecucion_key (OID = 19486) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_idejecucion_key UNIQUE (idejecucion);
--
-- Definition for index ejecucionplanificacionproduccion_idplanificacionproduccion_key (OID = 19488) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_idplanificacionproduccion_key UNIQUE (idplanificacionproduccion);
--
-- Definition for index ejecucionplanificacionproduccion_fk (OID = 19490) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_fk FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index ejecucionplanificacionproduccion_fk1 (OID = 19495) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_fk1 FOREIGN KEY (estado) REFERENCES estadoejecplanifpedido(idestado);
--
-- Definition for index procesocalidad_pkey (OID = 19500) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT procesocalidad_pkey PRIMARY KEY (idprocesocalidad);
--
-- Definition for index procesocalidad_fk (OID = 19502) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT procesocalidad_fk FOREIGN KEY (accioncalidad) REFERENCES accioncalidad(idaccioncalidad);
--
-- Definition for index ejecucionplanificacioncalidad_pkey (OID = 19507) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_pkey PRIMARY KEY (idejecucion, idplanificacioncalidad);
--
-- Definition for index ejecucionplanificacioncalidad_idejecucion_key (OID = 19509) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_idejecucion_key UNIQUE (idejecucion);
--
-- Definition for index ejecucionplanificacioncalidad_idplanificacioncalidad_key (OID = 19511) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_idplanificacioncalidad_key UNIQUE (idplanificacioncalidad);
--
-- Definition for index ejecucionplanificacioncalidad_fk (OID = 19513) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_fk FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index ejecucionplanificacioncalidad_fk1 (OID = 19518) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_fk1 FOREIGN KEY (estado) REFERENCES estadoejecplancalidad(idestado);
--
-- Definition for index ejecucionprocesocalidad_pkey (OID = 19523) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_pkey PRIMARY KEY (nroejecucion, idprocesocalidad);
--
-- Definition for index ejecucionprocesocalidad_nroejecucion_key (OID = 19525) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_nroejecucion_key UNIQUE (nroejecucion);
--
-- Definition for index ejecucionprocesocalidad_idprocesocalidad_key (OID = 19527) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_idprocesocalidad_key UNIQUE (idprocesocalidad);
--
-- Definition for index ejecucionprocesocalidad_fk (OID = 19529) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_fk FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index ejecucionetapaproduccion_pkey (OID = 19534) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_pkey PRIMARY KEY (nroejecucion, idetapaproduccion);
--
-- Definition for index ejecucionetapaproduccion_nroejecucion_key (OID = 19536) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_nroejecucion_key UNIQUE (nroejecucion);
--
-- Definition for index ejecucionetapaproduccion_idetapaproduccion_key (OID = 19538) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_idetapaproduccion_key UNIQUE (idetapaproduccion);
--
-- Definition for index ejecucionetapaproduccion_fk (OID = 19540) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index ejecucionetapaproduccion_fk1 (OID = 19545) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk1 FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index ejecucionetapaproduccion_fk2 (OID = 19550) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk2 FOREIGN KEY (estado) REFERENCES estadoejecetapaprod(idestado);
--
-- Definition for index compra_pkey (OID = 19555) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_pkey PRIMARY KEY (idcompra);
--
-- Definition for index compra_fk (OID = 19557) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_fk FOREIGN KEY (estado) REFERENCES estadocompra(idestado);
--
-- Definition for index compra_fk1 (OID = 19562) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_fk1 FOREIGN KEY (proveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index asistencia_pkey (OID = 19567) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_pkey PRIMARY KEY (empleado, fechaingreso, horaingreso);
--
-- Definition for index asistencia_empleado_key (OID = 19569) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_empleado_key UNIQUE (empleado);
--
-- Definition for index asistencia_fechaingreso_key (OID = 19571) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_fechaingreso_key UNIQUE (fechaingreso);
--
-- Definition for index asistencia_horaingreso_key (OID = 19573) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_horaingreso_key UNIQUE (horaingreso);
--
-- Definition for index asistencia_fk (OID = 19575) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_fk FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index detallemantenimientocorrectivo_pkey (OID = 19580) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_pkey PRIMARY KEY (idmantenimientocorrectivo, iddetalle);
--
-- Definition for index detallemantenimientocorrectivo_fk (OID = 19582) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_fk FOREIGN KEY (rotura) REFERENCES rotura(idrotura);
--
-- Definition for index detallemantenimientocorrectivo_fk1 (OID = 19587) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_fk1 FOREIGN KEY (idmantenimientocorrectivo) REFERENCES mantenimientocorrectivo(idmantenimientocorrectivo);
--
-- Definition for index detallemantenimientopreventivo_pkey (OID = 19592) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_pkey PRIMARY KEY (idmantenimientopreventivo, iddetalle);
--
-- Definition for index detallemantenimientopreventivo_fk (OID = 19594) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_fk FOREIGN KEY (idmantenimientopreventivo) REFERENCES mantenimientopreventivo(idmantenimientopreventivo);
--
-- Definition for index detallemantenimientopreventivo_fk1 (OID = 19599) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_fk1 FOREIGN KEY (servicio) REFERENCES servicio(idservicio);
--
-- Definition for index detalleejecucionplanificacion_fk (OID = 19604) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk FOREIGN KEY (idejecucionplanificacionproduccion, idplanificacionproduccion) REFERENCES ejecucionplanificacionproduccion(idejecucion, idplanificacionproduccion);
--
-- Definition for index detalleejecucionplanificacion_fk1 (OID = 19609) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk1 FOREIGN KEY (ejecucionetapa, idetapaproduccion) REFERENCES ejecucionetapaproduccion(nroejecucion, idetapaproduccion);
--
-- Definition for index detalleejecucionplanificacion_idx (OID = 19614) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_idx PRIMARY KEY (iddetalle, idejecucionplanificacionproduccion, idplanificacionproduccion);
--
-- Definition for index detalleejecucionplanificacioncalidad_idx (OID = 19616) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_idx PRIMARY KEY (iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk (OID = 19618) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk FOREIGN KEY (idejecucionplanificacioncalidad, idplanificacioncalidad) REFERENCES ejecucionplanificacioncalidad(idejecucion, idplanificacioncalidad);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk1 (OID = 19623) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk1 FOREIGN KEY (ejecucionprocesocalidad, idprocesocalidad) REFERENCES ejecucionprocesocalidad(nroejecucion, idprocesocalidad);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk2 (OID = 19628) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk2 FOREIGN KEY (pieza, piezareal) REFERENCES piezareal(idpiezareal, idpieza);
--
-- Definition for index detalleejecucionplanificacion_fk2 (OID = 19633) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk2 FOREIGN KEY (piezareal, pieza) REFERENCES piezareal(idpiezareal, idpieza);
--
-- Definition for index detallaplanificacionproduccion_fk (OID = 19638) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index detallaplanificacionproduccion_fk1 (OID = 19643) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk1 FOREIGN KEY (iddetalleejecucionplanificacion, idejecucionplanificacionproduccion, idplanificacionproduccion) REFERENCES detalleejecucionplanificacion(iddetalle, idejecucionplanificacionproduccion, idplanificacionproduccion);
--
-- Definition for index detallaplanificacionproduccion_fk2 (OID = 19648) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk2 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detallaplanificacionproduccion_fk3 (OID = 19653) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk3 FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index detallaplanificacionproduccion_idx (OID = 19658) : 
--
ALTER TABLE ONLY detallaplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_idx PRIMARY KEY (iddetalle, idplanificacionproduccion);
--
-- Definition for index detalleproducto_fk (OID = 19660) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_fk FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index detalleproducto_fk1 (OID = 19665) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_fk1 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleproducto_idx (OID = 19670) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_idx PRIMARY KEY (iddetalle, idproducto);
--
-- Definition for index detallepedido_fk (OID = 19672) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_fk FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index detallepedido_fk1 (OID = 19677) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detallepedido_idx (OID = 19682) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_idx PRIMARY KEY (iddetalle, idpedido);
--
-- Definition for index detalletrabajotercerizado_fk (OID = 19684) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk FOREIGN KEY (idtrabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index detalletrabajotercerizado_fk1 (OID = 19689) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk1 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalletrabajotercerizado_fk2 (OID = 19694) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk2 FOREIGN KEY (proceso) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detalletrabajotercerizado_idx (OID = 19699) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_idx PRIMARY KEY (iddetalle, idtrabajotercerizado);
--
-- Definition for index detalleplanificacioncalidad_fk (OID = 19701) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index detalleplanificacioncalidad_fk1 (OID = 19706) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk1 FOREIGN KEY (idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, idejecucionplanificacioncalidad) REFERENCES detalleejecucionplanificacioncalidad(iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad);
--
-- Definition for index detalleplanificacioncalidad_fk2 (OID = 19711) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk2 FOREIGN KEY (procesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detalleplanificacioncalidad_fk3 (OID = 19716) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk3 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleplanificacioncalidad_idx (OID = 19721) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_idx PRIMARY KEY (iddetalle, idplanificacioncalidad);
--
-- Definition for index detallecompra_fk (OID = 19723) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk FOREIGN KEY (idcompra) REFERENCES compra(idcompra);
--
-- Definition for index detallecompra_fk1 (OID = 19728) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk1 FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallecompra_idx (OID = 19733) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_idx PRIMARY KEY (iddetalle, idcompra);
--
-- Definition for index detalleremito_fk (OID = 19735) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_fk FOREIGN KEY (idremito) REFERENCES remito(idremito);
--
-- Definition for index detalleremito_fk1 (OID = 19740) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detalleremito_idx (OID = 19745) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_idx PRIMARY KEY (iddetalle, idremito);
--
-- Definition for index tiporeclamo_pkey (OID = 19747) : 
--
ALTER TABLE ONLY tiporeclamo
    ADD CONSTRAINT tiporeclamo_pkey PRIMARY KEY (idtiporeclamo);
--
-- Definition for index reclamoempresametalurgica_pkey (OID = 19749) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_pkey PRIMARY KEY (idreclamo);
--
-- Definition for index reclamoempresametalurgica_fk (OID = 19751) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index reclamoempresametalurgica_fk1 (OID = 19756) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_fk1 FOREIGN KEY (trabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index reclamoproveedor_fk (OID = 19761) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index reclamoproveedor_fk1 (OID = 19766) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_fk1 FOREIGN KEY (compra) REFERENCES compra(idcompra);
--
-- Definition for index reclamoproveedor_idx (OID = 19771) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_idx PRIMARY KEY (idreclamo);
--
-- Definition for index reclamocliente_fk (OID = 19773) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index reclamocliente_fk1 (OID = 19778) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_fk1 FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index reclamocliente_idx (OID = 19783) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_idx PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamocliente_fk (OID = 19785) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_fk FOREIGN KEY (idreclamo) REFERENCES reclamocliente(idreclamo);
--
-- Definition for index detallereclamocliente_fk1 (OID = 19790) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detallereclamocliente_idx (OID = 19795) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamoproveedor_fk (OID = 19797) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_fk FOREIGN KEY (idreclamo) REFERENCES reclamoproveedor(idreclamo);
--
-- Definition for index detallereclamoproveedor_idx (OID = 19807) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamoempresametalurgica_fk (OID = 19809) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_fk FOREIGN KEY (idreclamo) REFERENCES reclamoempresametalurgica(idreclamo);
--
-- Definition for index detallereclamoempresametalurgica_idx (OID = 19819) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index proveedorxmateriaprima_fk (OID = 19821) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_fk FOREIGN KEY (idproveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index proveedorxmateriaprima_fk1 (OID = 19826) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_fk1 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index proveedorxmateriaprima_idx (OID = 19831) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_idx PRIMARY KEY (idmateriaprima, idproveedor);
--
-- Definition for index maquinaxejecucionetapaproduccion_fk (OID = 19833) : 
--
ALTER TABLE ONLY maquinaxejecucionetapaproduccion
    ADD CONSTRAINT maquinaxejecucionetapaproduccion_fk FOREIGN KEY (idejecucionetapaproduccion, idetapaproduccion) REFERENCES ejecucionetapaproduccion(nroejecucion, idetapaproduccion);
--
-- Definition for index maquinaxejecucionetapaproduccion_fk1 (OID = 19838) : 
--
ALTER TABLE ONLY maquinaxejecucionetapaproduccion
    ADD CONSTRAINT maquinaxejecucionetapaproduccion_fk1 FOREIGN KEY (idmaquina) REFERENCES maquina(idmaquina);
--
-- Definition for index maquinaxejecucionetapaproduccion_idx (OID = 19843) : 
--
ALTER TABLE ONLY maquinaxejecucionetapaproduccion
    ADD CONSTRAINT maquinaxejecucionetapaproduccion_idx PRIMARY KEY (idejecucionetapaproduccion, idetapaproduccion, idmaquina);
--
-- Definition for index maquinaxprocesocalidad_fk (OID = 19845) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT maquinaxprocesocalidad_fk FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index maquinaxprocesocalidad_fk1 (OID = 19850) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT maquinaxprocesocalidad_fk1 FOREIGN KEY (idmaquina) REFERENCES maquina(idmaquina);
--
-- Definition for index maquinaxprocesocalidad_idx (OID = 19855) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT maquinaxprocesocalidad_idx PRIMARY KEY (idprocesocalidad, idmaquina);
--
-- Definition for index piezaxetapadeproduccion_fk (OID = 19857) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT piezaxetapadeproduccion_fk FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index piezaxetapadeproduccion_fk1 (OID = 19862) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT piezaxetapadeproduccion_fk1 FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index piezaxetapadeproduccion_idx (OID = 19867) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT piezaxetapadeproduccion_idx PRIMARY KEY (idpieza, idetapaproduccion);
--
-- Definition for index empleadoxturno_fk (OID = 19869) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_fk FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index empleadoxturno_fk1 (OID = 19874) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_fk1 FOREIGN KEY (idturno) REFERENCES turno(idturno);
--
-- Definition for index empleadoxturno_idx (OID = 19879) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_idx PRIMARY KEY (idempleado, idturno);
--
-- Definition for index mantenimientocorrectivo_fk2 (OID = 19881) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_fk2 FOREIGN KEY (maquina) REFERENCES maquina(idmaquina);
--
-- Definition for index mantenimientopreventivo_fk1 (OID = 19886) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_fk1 FOREIGN KEY (maquina) REFERENCES maquina(idmaquina);
--
-- Definition for index provincia_pkey (OID = 19891) : 
--
ALTER TABLE ONLY provincia
    ADD CONSTRAINT provincia_pkey PRIMARY KEY (idprovincia);
--
-- Definition for index localidad_pkey (OID = 19893) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT localidad_pkey PRIMARY KEY (idlocalidad);
--
-- Definition for index localidad_fk (OID = 19895) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT localidad_fk FOREIGN KEY (provincia) REFERENCES provincia(idprovincia);
--
-- Definition for index barrio_pkey (OID = 19900) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT barrio_pkey PRIMARY KEY (idbarrio);
--
-- Definition for index barrio_fk (OID = 19902) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT barrio_fk FOREIGN KEY (localidad) REFERENCES localidad(idlocalidad);
--
-- Definition for index usuarioxrol_pkey (OID = 19907) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_pkey PRIMARY KEY (idrol, idusuario);
--
-- Definition for index rolxprivilegio_pkey (OID = 19909) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_pkey PRIMARY KEY (idrol, idprivilegio);
--
-- Definition for index planrequerimientosmateriaprima_pkey (OID = 19917) : 
--
ALTER TABLE ONLY planrequerimientosmateriaprima
    ADD CONSTRAINT planrequerimientosmateriaprima_pkey PRIMARY KEY (idplanrequerimientosmateriaprima);
--
-- Definition for index planprocedimientos_pkey (OID = 19922) : 
--
ALTER TABLE ONLY planprocedimientos
    ADD CONSTRAINT planprocedimientos_pkey PRIMARY KEY (idplanprocedimientos);
--
-- Definition for index planprocesoscalidad_pkey (OID = 19927) : 
--
ALTER TABLE ONLY planprocesoscalidad
    ADD CONSTRAINT planprocesoscalidad_pkey PRIMARY KEY (idplanprocesoscalidad);
--
-- Definition for index detallerequerimientosmateriaprima_fk (OID = 19932) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_fk FOREIGN KEY (idplanrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_fk1 (OID = 19937) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_fk1 FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index detallerequerimientosmateriaprima_fk2 (OID = 19942) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_fk2 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_idx (OID = 19947) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_idx PRIMARY KEY (iddetalle, idplanrequerimientosmateriaprima);
--
-- Definition for index detalleplanprocedimientos_fk (OID = 19952) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_fk FOREIGN KEY (idplanpprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index detalleplanprocedimientos_fk1 (OID = 19957) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_fk1 FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleplanprocedimientos_fk2 (OID = 19962) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_fk2 FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detalleplanprocedimientos_idx (OID = 19967) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_idx PRIMARY KEY (iddetalle, idplanpprocedimientos);
--
-- Definition for index detalleplanprocesoscalidad_fk (OID = 19972) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_fk FOREIGN KEY (idplanprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index detalleplanprocesoscalidad_fk1 (OID = 19977) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_fk1 FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleplanprocesoscalidad_fk2 (OID = 19982) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_fk2 FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detalleplanprocesoscalidad_idx (OID = 19987) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_idx PRIMARY KEY (iddetalle, idplanprocesoscalidad);
--
-- Definition for index pedidomatriz_fk (OID = 19992) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT pedidomatriz_fk FOREIGN KEY (idmatriz) REFERENCES matriz(idmatriz);
--
-- Definition for index pedidomatriz_idx (OID = 19997) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT pedidomatriz_idx PRIMARY KEY (idpedidomatriz);
--
-- Definition for index pedido_fk5 (OID = 19999) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk5 FOREIGN KEY (planprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index pedido_fk6 (OID = 20004) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk6 FOREIGN KEY (planrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index pedido_fk7 (OID = 20009) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk7 FOREIGN KEY (planprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index estadodetallecompra_pkey (OID = 20017) : 
--
ALTER TABLE ONLY estadodetallecompra
    ADD CONSTRAINT estadodetallecompra_pkey PRIMARY KEY (idestado);
--
-- Definition for index estadodetalletrabajotercerizado_pkey (OID = 20022) : 
--
ALTER TABLE ONLY estadodetalletrabajotercerizado
    ADD CONSTRAINT estadodetalletrabajotercerizado_pkey PRIMARY KEY (idestado);
--
-- Definition for index detallecompra_fk2 (OID = 20024) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk2 FOREIGN KEY (estado) REFERENCES estadodetallecompra(idestado);
--
-- Definition for index detalletrabajotercerizado_fk3 (OID = 20034) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk3 FOREIGN KEY (estado) REFERENCES estadodetalletrabajotercerizado(idestado);
--
-- Definition for index detallereclamoempresametalurgica_fk1 (OID = 20039) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_fk1 FOREIGN KEY (pieza) REFERENCES piezareal(idpiezareal);
--
-- Definition for index detallereclamoproveedor_fk1 (OID = 20044) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_fk1 FOREIGN KEY (iddetallecompra, idcompra) REFERENCES detallecompra(iddetalle, idcompra);
--
-- Definition for index rolxprivilegio_fk (OID = 20049) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_fk FOREIGN KEY (idprivilegio) REFERENCES privilegio(idprivilegio);
--
-- Definition for index rolxprivilegio_fk1 (OID = 20054) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_fk1 FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index usuarioxrol_fk (OID = 20059) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_fk FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index usuarioxrol_fk1 (OID = 20064) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_fk1 FOREIGN KEY (idusuario) REFERENCES usuario(idusuario);
--
-- Definition for index estadoejecucionprocesocalidad_pkey (OID = 20072) : 
--
ALTER TABLE ONLY estadoejecucionprocesocalidad
    ADD CONSTRAINT estadoejecucionprocesocalidad_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionprocesocalidad_fk1 (OID = 20074) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_fk1 FOREIGN KEY (estado) REFERENCES estadoejecucionprocesocalidad(idestado);
--
-- Data for sequence public.cliente_seq (OID = 18784)
--
SELECT pg_catalog.setval('cliente_seq', 1, true);
--
-- Data for sequence public.clientedb_seq (OID = 18786)
--
SELECT pg_catalog.setval('clientedb_seq', 4, true);
--
-- Comments
--
COMMENT ON SCHEMA public IS 'standard public schema';
