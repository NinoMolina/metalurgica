-- SQL Manager 2007 for PostgreSQL 4.5.0.8
-- ---------------------------------------
-- Host      : localhost
-- Database  : metalsoft
-- Version   : PostgreSQL 8.3.11, compiled by Visual C++ build 1400



--
-- Definition for language plpgsql (OID = 22915) : 
--
--CREATE TRUSTED PROCEDURAL LANGUAGE plpgsql
--   HANDLER "plpgsql_call_handler"
--   VALIDATOR "pg_catalog"."plpgsql_validator";
--
-- Definition for function nvonropedido (OID = 22916) : 
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
-- Definition for function cantpiezasxproducto (OID = 22917) : 
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
-- Definition for function esproductosinalgunaetapa (OID = 22918) : 
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
-- Definition for function cantpiezasdepedido (OID = 22919) : 
--
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
-- Definition for function nvonroproducto (OID = 22920) : 
--
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
-- Definition for function cantpiezasdepedido2 (OID = 22921) : 
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
-- Definition for function nvonropresupuesto (OID = 22922) : 
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
-- Definition for function ingresoxpedido (OID = 22923) : 
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
-- Definition for function nvonrocliente (OID = 22924) : 
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
-- Definition for function nvonromateriaprima (OID = 22925) : 
--
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
-- Definition for function nvonroempleado (OID = 22926) : 
--
CREATE FUNCTION public.nvonroempleado () RETURNS bigint
AS 
$body$
DECLARE
  result BIGINT;
BEGIN
  SELECT (max(p.legajo)+1) INTO result FROM empleado p;
  IF(result IS NULL)THEN
  	result:=1;
  END IF;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function nvonromaquina (OID = 22927) : 
--
CREATE FUNCTION public.nvonromaquina () RETURNS bigint
AS 
$body$
DECLARE
  result BIGINT;
BEGIN
  SELECT (max(p.idmaquina)+1) INTO result FROM maquina p;
  IF(result IS NULL)THEN
  	result:=1;
  END IF;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function nvonroejecucionplanificacion (OID = 22928) : 
--
CREATE FUNCTION public.nvonroejecucionplanificacion () RETURNS bigint
AS 
$body$
DECLARE
  result BIGINT;
BEGIN
  SELECT (max(p.nroejecucionplanificacion)+1) INTO result FROM ejecucionplanificacionproduccion p;
  IF(result IS NULL)THEN
  	result:=1;
  END IF;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function nvonroplanificacionproduccion (OID = 22929) : 
--
CREATE FUNCTION public.nvonroplanificacionproduccion () RETURNS bigint
AS 
$body$
DECLARE
  result BIGINT;
BEGIN
  SELECT (max(p.nroplanificacion)+1) INTO result FROM planificacionproduccion p;
  IF(result IS NULL)THEN
  	result:=1;
  END IF;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Structure for table pedido (OID = 22930) : 
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
-- Structure for table planificacioncalidad (OID = 22934) : 
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
-- Structure for table estadopedido (OID = 22938) : 
--
CREATE TABLE public.estadopedido (
    idestado bigint DEFAULT nextval(('"public"."estadopedido_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table factura (OID = 22942) : 
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
-- Structure for table planificacionproduccion (OID = 22946) : 
--
CREATE TABLE public.planificacionproduccion (
    idplanificacionproduccion bigint DEFAULT nextval(('"public"."planificacionproduccion_idplanificacionproduccion_seq"'::text)::regclass) NOT NULL,
    nroplanificacion bigint,
    fechacreacion date,
    observaciones character varying(50),
    fechainicioprevista date,
    fechafinprevista date,
    pedido bigint,
    idestado bigint
) WITH OIDS;
--
-- Structure for table presupuesto (OID = 22950) : 
--
CREATE TABLE public.presupuesto (
    idpresupuesto bigint DEFAULT nextval(('"public"."presupuesto_idpresupuesto_seq"'::text)::regclass) NOT NULL,
    fechapresupuesto date,
    montototal double precision,
    fechavencimiento date,
    nropresupuesto bigint
) WITH OIDS;
--
-- Structure for table plano (OID = 22954) : 
--
CREATE TABLE public.plano (
    idplano bigint DEFAULT nextval(('"public"."plano_idplano_seq"'::text)::regclass) NOT NULL,
    nroplano bigint,
    escala integer,
    pedido bigint,
    imagen bytea[]
) WITH OIDS;
--
-- Structure for table remito (OID = 22961) : 
--
CREATE TABLE public.remito (
    idremito bigint DEFAULT nextval(('"public"."remito_idremito_seq"'::text)::regclass) NOT NULL,
    nroremito bigint,
    fechaemision date,
    pedido bigint,
    estado bigint
) WITH OIDS;
--
-- Structure for table trabajotercerizado (OID = 22965) : 
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
-- Structure for table cliente (OID = 22969) : 
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
-- Structure for table tipoiva (OID = 22973) : 
--
CREATE TABLE public.tipoiva (
    idtipoiva bigint DEFAULT nextval(('"public"."tipoiva_idtipoiva_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table formadepago (OID = 22977) : 
--
CREATE TABLE public.formadepago (
    idformapago bigint DEFAULT nextval(('"public"."formadepago_idformapago_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table usuario (OID = 22981) : 
--
CREATE TABLE public.usuario (
    idusuario bigint DEFAULT nextval(('"public"."usuario_idusuario_seq"'::text)::regclass) NOT NULL,
    usuario character varying(50),
    clave character varying(50)
) WITH OIDS;
--
-- Structure for table estadofactura (OID = 22985) : 
--
CREATE TABLE public.estadofactura (
    idestado bigint DEFAULT nextval(('"public"."estadofactura_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table comprobantepago (OID = 22989) : 
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
-- Structure for table estadotrabajotercerizado (OID = 22993) : 
--
CREATE TABLE public.estadotrabajotercerizado (
    idestado bigint DEFAULT nextval(('"public"."estadotrabajotercerizado_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table empresametalurgica (OID = 22997) : 
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
-- Structure for table prioridad (OID = 23001) : 
--
CREATE TABLE public.prioridad (
    idprioridad bigint DEFAULT nextval(('"public"."prioridad_idprioridad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table estadocliente (OID = 23005) : 
--
CREATE TABLE public.estadocliente (
    idestado bigint DEFAULT nextval(('"public"."estadocliente_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table responsable (OID = 23009) : 
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
-- Structure for table condicioniva (OID = 23013) : 
--
CREATE TABLE public.condicioniva (
    idcondicioniva bigint DEFAULT nextval(('"public"."condicioniva_idcondicioniva_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table domicilio (OID = 23017) : 
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
-- Structure for table rotura (OID = 23021) : 
--
CREATE TABLE public.rotura (
    idrotura bigint DEFAULT nextval(('"public"."rotura_idrotura_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table categoria (OID = 23025) : 
--
CREATE TABLE public.categoria (
    idcategoria bigint DEFAULT nextval(('"public"."categoria_idcategoria_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table cargo (OID = 23029) : 
--
CREATE TABLE public.cargo (
    idcargo bigint DEFAULT nextval(('"public"."cargo_idcargo_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table turno (OID = 23033) : 
--
CREATE TABLE public.turno (
    idturno bigint DEFAULT nextval(('"public"."turno_idturno_seq"'::text)::regclass) NOT NULL,
    horainicio time without time zone,
    horafin time without time zone,
    nombre character varying(20),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table marca (OID = 23037) : 
--
CREATE TABLE public.marca (
    idmarca bigint DEFAULT nextval(('"public"."marca_idmarca_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadomaquina (OID = 23041) : 
--
CREATE TABLE public.estadomaquina (
    idestado bigint DEFAULT nextval(('"public"."estadomaquina_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table tipomaquina (OID = 23045) : 
--
CREATE TABLE public.tipomaquina (
    idtipomaquina bigint DEFAULT nextval(('"public"."tipomaquina_idtipomaquina_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecetapaprod (OID = 23049) : 
--
CREATE TABLE public.estadoejecetapaprod (
    idestado bigint DEFAULT nextval(('"public"."estadoejecetapaprod_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table servicio (OID = 23053) : 
--
CREATE TABLE public.servicio (
    idservicio bigint DEFAULT nextval(('"public"."servicio_idservicio_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table tipodocumento (OID = 23057) : 
--
CREATE TABLE public.tipodocumento (
    idtipodocumento bigint DEFAULT nextval(('"public"."tipodocumento_idtipodocumento_seq"'::text)::regclass) NOT NULL,
    tipo character varying(50),
    nombre character varying(50)
) WITH OIDS;
--
-- Structure for table codigodebarra (OID = 23061) : 
--
CREATE TABLE public.codigodebarra (
    idcodigo bigint DEFAULT nextval(('"public"."codigodebarra_idcodigo_seq"'::text)::regclass) NOT NULL,
    descripcion character varying(50),
    codigo character varying(50)
) WITH OIDS;
--
-- Structure for table estadopiezareal (OID = 23065) : 
--
CREATE TABLE public.estadopiezareal (
    idestado bigint DEFAULT nextval(('"public"."estadopiezareal_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecplanifpedido (OID = 23069) : 
--
CREATE TABLE public.estadoejecplanifpedido (
    idestado bigint DEFAULT nextval(('"public"."estadoejecplanifpedido_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table proveedormantenimientomaquina (OID = 23073) : 
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
-- Structure for table rol (OID = 23077) : 
--
CREATE TABLE public.rol (
    idrol bigint DEFAULT nextval(('"public"."rol_idrol_seq"'::text)::regclass) NOT NULL,
    rol character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table privilegio (OID = 23081) : 
--
CREATE TABLE public.privilegio (
    idprivilegio bigint DEFAULT nextval(('"public"."privilegio_idprivilegio_seq"'::text)::regclass) NOT NULL,
    privilegio character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table accioncalidad (OID = 23085) : 
--
CREATE TABLE public.accioncalidad (
    idaccioncalidad bigint DEFAULT nextval(('"public"."accioncalidad_idaccioncalidad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(150)
) WITH OIDS;
--
-- Structure for table empleado (OID = 23089) : 
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
-- Structure for table proveedor (OID = 23093) : 
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
-- Structure for table estadocompra (OID = 23097) : 
--
CREATE TABLE public.estadocompra (
    idestado bigint DEFAULT nextval(('"public"."estadocompra_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table sesion (OID = 23101) : 
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
-- Structure for table materiaprima (OID = 23105) : 
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
-- Structure for table matriz (OID = 23109) : 
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
-- Structure for table producto (OID = 23113) : 
--
CREATE TABLE public.producto (
    idproducto bigint DEFAULT nextval(('"public"."producto_idproducto_seq"'::text)::regclass) NOT NULL,
    nroproducto bigint,
    nombre character varying(50),
    preciounitario double precision,
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecplancalidad (OID = 23117) : 
--
CREATE TABLE public.estadoejecplancalidad (
    idestado bigint DEFAULT nextval(('"public"."estadoejecplancalidad_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table mantenimientopreventivo (OID = 23121) : 
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
-- Structure for table mantenimientocorrectivo (OID = 23125) : 
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
-- Structure for table etapadeproduccion (OID = 23129) : 
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
-- Structure for table ejecucionplanificacionproduccion (OID = 23133) : 
--
CREATE TABLE public.ejecucionplanificacionproduccion (
    idejecucion bigint DEFAULT nextval(('"public"."ejecucionplanificacionproduccion_idejecucion_seq"'::text)::regclass) NOT NULL,
    idplanificacionproduccion bigint NOT NULL,
    fechainicio date,
    fechafin date,
    horainicio time without time zone,
    horafin time without time zone,
    estado bigint,
    nroejecucionplanificacion bigint
) WITH OIDS;
--
-- Structure for table procesocalidad (OID = 23137) : 
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
-- Structure for table ejecucionplanificacioncalidad (OID = 23141) : 
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
-- Structure for table ejecucionprocesocalidad (OID = 23145) : 
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
-- Structure for table ejecucionetapaproduccion (OID = 23149) : 
--
CREATE TABLE public.ejecucionetapaproduccion (
    id bigint DEFAULT nextval(('"public"."ejecucionetapaproduccion_idejecucion_seq"'::text)::regclass) NOT NULL,
    idetapaproduccion bigint NOT NULL,
    nombre character varying(50),
    totalhorasmaquina time without time zone,
    totalhorashombre time without time zone,
    empleado bigint,
    fechainicio date,
    horainicio time without time zone,
    fechafin date,
    horafin time without time zone,
    observaciones character varying(250),
    estado bigint,
    nroejecucion bigint NOT NULL
) WITH OIDS;
--
-- Structure for table compra (OID = 23153) : 
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
-- Structure for table detallemantenimientocorrectivo (OID = 23157) : 
--
CREATE TABLE public.detallemantenimientocorrectivo (
    idmantenimientocorrectivo bigint DEFAULT nextval(('"public"."detallemantenimientocorrectivo_idmantenimientocorrectivo_seq"'::text)::regclass) NOT NULL,
    iddetalle bigint NOT NULL,
    duracion time without time zone,
    rotura bigint,
    motivorotura character varying(100)
) WITH OIDS;
--
-- Structure for table detallemantenimientopreventivo (OID = 23161) : 
--
CREATE TABLE public.detallemantenimientopreventivo (
    idmantenimientopreventivo bigint DEFAULT nextval(('"public"."detallemantenimientopreventivo_idmantenimientopreventivo_seq"'::text)::regclass) NOT NULL,
    iddetalle bigint NOT NULL,
    duracion time without time zone,
    servicio bigint,
    observaciones character varying(100)
) WITH OIDS;
--
-- Structure for table detalleejecucionplanificacion (OID = 23165) : 
--
CREATE TABLE public.detalleejecucionplanificacion (
    id bigint DEFAULT nextval(('"public"."detalleejecucionplanificacion_iddetalle_seq"'::text)::regclass) NOT NULL,
    idejecucionplanificacionproduccion bigint NOT NULL,
    pieza bigint,
    ejecucionetapa bigint NOT NULL,
    idetapaproduccion bigint,
    piezareal bigint,
    fechainicio date,
    fechafin date,
    horainicio time(0) without time zone,
    horafin time(0) without time zone,
    orden integer
) WITH OIDS;
--
-- Structure for table detalleejecucionplanificacioncalidad (OID = 23169) : 
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
-- Structure for table detalleplanificacionproduccion (OID = 23173) : 
--
CREATE TABLE public.detalleplanificacionproduccion (
    id bigint DEFAULT nextval(('public.detalleplanificacionproduccion_id_seq'::text)::regclass) NOT NULL,
    idplanificacionproduccion bigint NOT NULL,
    iddetalleejecucionplanificacion bigint,
    idetapaproduccion bigint,
    idpieza bigint,
    idempleado bigint,
    fechainicio date,
    horainicio time(0) without time zone,
    fechafin date,
    horafin time(0) without time zone,
    idmaquina bigint,
    orden integer,
    idproducto bigint
) WITH OIDS;
--
-- Structure for table detalleproducto (OID = 23177) : 
--
CREATE TABLE public.detalleproducto (
    iddetalle bigint DEFAULT nextval(('"public"."detalleproducto_iddetalle_seq"'::text)::regclass) NOT NULL,
    idproducto bigint NOT NULL,
    cantidadpiezas integer,
    descripcion character varying(50),
    pieza bigint NOT NULL
) WITH OIDS;
--
-- Structure for table detallepedido (OID = 23181) : 
--
CREATE TABLE public.detallepedido (
    iddetalle bigint DEFAULT nextval(('"public"."detallepedido_iddetalle_seq"'::text)::regclass) NOT NULL,
    idpedido bigint NOT NULL,
    precio double precision,
    cantidad integer,
    producto bigint
) WITH OIDS;
--
-- Structure for table detalletrabajotercerizado (OID = 23185) : 
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
-- Structure for table detalleplanificacioncalidad (OID = 23189) : 
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
-- Structure for table detallecompra (OID = 23193) : 
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
-- Structure for table detalleremito (OID = 23197) : 
--
CREATE TABLE public.detalleremito (
    iddetalle bigint DEFAULT nextval(('"public"."detalleremito_iddetalle_seq"'::text)::regclass) NOT NULL,
    idremito bigint NOT NULL,
    cantidad integer,
    descripcion character varying(50),
    producto bigint
) WITH OIDS;
--
-- Structure for table tiporeclamo (OID = 23201) : 
--
CREATE TABLE public.tiporeclamo (
    idtiporeclamo bigint DEFAULT nextval(('"public"."tiporeclamo_idtiporeclamo_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table reclamoempresametalurgica (OID = 23205) : 
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
-- Structure for table reclamoproveedor (OID = 23209) : 
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
-- Structure for table reclamocliente (OID = 23213) : 
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
-- Structure for table detallereclamocliente (OID = 23217) : 
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
-- Structure for table detallereclamoproveedor (OID = 23221) : 
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
-- Structure for table detallereclamoempresametalurgica (OID = 23225) : 
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
-- Structure for table proveedorxmateriaprima (OID = 23229) : 
--
CREATE TABLE public.proveedorxmateriaprima (
    idproveedor bigint NOT NULL,
    idmateriaprima bigint NOT NULL,
    precio double precision
) WITH OIDS;
--
-- Structure for table maquinaxejecucionetapaproduccion (OID = 23232) : 
--
CREATE TABLE public.maquinaxejecucionetapaproduccion (
    idejecucionetapaproduccion bigint NOT NULL,
    idetapaproduccion bigint NOT NULL,
    idmaquina bigint NOT NULL,
    horasmaquina time without time zone,
    horashombre time without time zone
) WITH OIDS;
--
-- Structure for table maquinaxprocesocalidad (OID = 23235) : 
--
CREATE TABLE public.maquinaxprocesocalidad (
    idprocesocalidad bigint NOT NULL,
    idmaquina bigint NOT NULL,
    duracion time without time zone,
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table piezaxetapadeproduccion (OID = 23238) : 
--
CREATE TABLE public.piezaxetapadeproduccion (
    idpieza bigint NOT NULL,
    idetapaproduccion bigint NOT NULL,
    duracion time without time zone,
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table empleadoxturno (OID = 23241) : 
--
CREATE TABLE public.empleadoxturno (
    idempleado bigint NOT NULL,
    idturno bigint NOT NULL
) WITH OIDS;
--
-- Structure for table provincia (OID = 23244) : 
--
CREATE TABLE public.provincia (
    idprovincia bigint DEFAULT nextval(('"public"."provincia_idprovincia_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50)
) WITH OIDS;
--
-- Structure for table localidad (OID = 23248) : 
--
CREATE TABLE public.localidad (
    idlocalidad bigint DEFAULT nextval(('"public"."localidad_idlocalidad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    provincia bigint
) WITH OIDS;
--
-- Structure for table barrio (OID = 23252) : 
--
CREATE TABLE public.barrio (
    idbarrio bigint DEFAULT nextval(('"public"."barrio_idbarrio_seq"'::text)::regclass) NOT NULL,
    nombre character varying(25),
    codpostal bigint,
    localidad bigint
) WITH OIDS;
--
-- Structure for table usuarioxrol (OID = 23256) : 
--
CREATE TABLE public.usuarioxrol (
    idrol bigint NOT NULL,
    idusuario bigint NOT NULL
) WITH OIDS;
--
-- Structure for table rolxprivilegio (OID = 23259) : 
--
CREATE TABLE public.rolxprivilegio (
    idrol bigint NOT NULL,
    idprivilegio bigint NOT NULL
) WITH OIDS;
--
-- Structure for table planrequerimientosmateriaprima (OID = 23262) : 
--
CREATE TABLE public.planrequerimientosmateriaprima (
    idplanrequerimientosmateriaprima bigint DEFAULT nextval(('"public"."planrequerimientosmateriaprima_idplanrequerimientosmateriaprima_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table planprocedimientos (OID = 23266) : 
--
CREATE TABLE public.planprocedimientos (
    idplanprocedimientos bigint DEFAULT nextval(('"public"."planprocedimientos_idplanprocedimientos_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table planprocesoscalidad (OID = 23270) : 
--
CREATE TABLE public.planprocesoscalidad (
    idplanprocesoscalidad bigint DEFAULT nextval(('"public"."planprocesoscalidad_idplanprocesoscalidad_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table detallerequerimientosmateriaprima (OID = 23274) : 
--
CREATE TABLE public.detallerequerimientosmateriaprima (
    iddetalle bigint DEFAULT nextval(('"public"."detallerequerimientosmateriaprima_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanrequerimientosmateriaprima bigint NOT NULL,
    cantidadmateriaprima integer,
    idpieza bigint,
    idmateriaprima bigint
) WITH OIDS;
--
-- Structure for table detalleplanprocedimientos (OID = 23278) : 
--
CREATE TABLE public.detalleplanprocedimientos (
    iddetalle bigint DEFAULT nextval(('"public"."detalleplanprocedimientos_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanpprocedimientos bigint NOT NULL,
    idpieza bigint,
    idetapaproduccion bigint,
    duracionestimada time without time zone
) WITH OIDS;
--
-- Structure for table detalleplanprocesoscalidad (OID = 23282) : 
--
CREATE TABLE public.detalleplanprocesoscalidad (
    iddetalle bigint DEFAULT nextval(('"public"."detalleplanprocesoscalidad_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanprocesoscalidad bigint NOT NULL,
    idpieza bigint,
    idprocesocalidad bigint,
    duracionestimada time without time zone
) WITH OIDS;
--
-- Structure for table pedidomatriz (OID = 23286) : 
--
CREATE TABLE public.pedidomatriz (
    idpedidomatriz bigint DEFAULT nextval(('"public"."pedidomatriz_idpedidomatriz_seq"'::text)::regclass) NOT NULL,
    nropedidomatriz bigint,
    fechapedidomatriz date,
    idmatriz bigint,
    observaciones character varying(100)
) WITH OIDS;
--
-- Structure for table estadodetallecompra (OID = 23290) : 
--
CREATE TABLE public.estadodetallecompra (
    idestado bigint DEFAULT nextval(('"public"."estadodetallecompra_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadodetalletrabajotercerizado (OID = 23294) : 
--
CREATE TABLE public.estadodetalletrabajotercerizado (
    idestado bigint DEFAULT nextval(('"public"."estadodetalletrabajotercerizado_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecucionprocesocalidad (OID = 23298) : 
--
CREATE TABLE public.estadoejecucionprocesocalidad (
    idestado bigint DEFAULT nextval(('"public"."estadoejecucionprocesocalidad_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table detalleproductoreal (OID = 23302) : 
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
-- Structure for table productoreal (OID = 23306) : 
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
-- Structure for table estadoproductoreal (OID = 23310) : 
--
CREATE TABLE public.estadoproductoreal (
    idestado bigint DEFAULT nextval(('"public"."estadoproductoreal_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table detallefactura (OID = 23314) : 
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
-- Structure for table detallepresupuesto (OID = 23318) : 
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
-- Structure for table estadoremito (OID = 23322) : 
--
CREATE TABLE public.estadoremito (
    idestado bigint DEFAULT nextval(('"public"."estadoremito_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table tipomaterial (OID = 23326) : 
--
CREATE TABLE public.tipomaterial (
    idtipomaterial bigint DEFAULT nextval(('"public"."tipomaterial_idtipomaterial_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Definition for sequence prueba_id_seq (OID = 23330) : 
--
CREATE SEQUENCE public.prueba_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table prueba (OID = 23332) : 
--
CREATE TABLE public.prueba (
    id bigint DEFAULT nextval('prueba_id_seq'::regclass) NOT NULL,
    valor character varying(20)
) WITH OIDS;
ALTER TABLE ONLY public.prueba ALTER COLUMN id SET STATISTICS 0;
ALTER TABLE ONLY public.prueba ALTER COLUMN valor SET STATISTICS 0;
--
-- Definition for sequence usuario_idusuario_seq (OID = 23336) : 
--
CREATE SEQUENCE public.usuario_idusuario_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipomaterial_idtipomaterial_seq (OID = 23338) : 
--
CREATE SEQUENCE public.tipomaterial_idtipomaterial_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence accioncalidad_idaccioncalidad_seq (OID = 23340) : 
--
CREATE SEQUENCE public.accioncalidad_idaccioncalidad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence barrio_idbarrio_seq (OID = 23342) : 
--
CREATE SEQUENCE public.barrio_idbarrio_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence cargo_idcargo_seq (OID = 23344) : 
--
CREATE SEQUENCE public.cargo_idcargo_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence categoria_idcategoria_seq (OID = 23346) : 
--
CREATE SEQUENCE public.categoria_idcategoria_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence cliente_idcliente_seq (OID = 23348) : 
--
CREATE SEQUENCE public.cliente_idcliente_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence codigodebarra_idcodigo_seq (OID = 23350) : 
--
CREATE SEQUENCE public.codigodebarra_idcodigo_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence compra_idcompra_seq (OID = 23352) : 
--
CREATE SEQUENCE public.compra_idcompra_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence comprobantepago_idcomprobantepago_seq (OID = 23354) : 
--
CREATE SEQUENCE public.comprobantepago_idcomprobantepago_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence condicioniva_idcondicioniva_seq (OID = 23356) : 
--
CREATE SEQUENCE public.condicioniva_idcondicioniva_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanificacionproduccion_id_seq (OID = 23358) : 
--
CREATE SEQUENCE public.detalleplanificacionproduccion_id_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallecompra_iddetalle_seq (OID = 23360) : 
--
CREATE SEQUENCE public.detallecompra_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleejecucionplanificacion_iddetalle_seq (OID = 23362) : 
--
CREATE SEQUENCE public.detalleejecucionplanificacion_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleejecucionplanificacioncalidad_iddetalle_seq (OID = 23364) : 
--
CREATE SEQUENCE public.detalleejecucionplanificacioncalidad_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallefactura_iddetalle_seq (OID = 23366) : 
--
CREATE SEQUENCE public.detallefactura_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallemantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 23368) : 
--
CREATE SEQUENCE public.detallemantenimientocorrectivo_idmantenimientocorrectivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallemantenimientopreventivo_idmantenimientopreventivo_seq (OID = 23370) : 
--
CREATE SEQUENCE public.detallemantenimientopreventivo_idmantenimientopreventivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallepedido_iddetalle_seq (OID = 23372) : 
--
CREATE SEQUENCE public.detallepedido_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanificacioncalidad_iddetalle_seq (OID = 23374) : 
--
CREATE SEQUENCE public.detalleplanificacioncalidad_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanprocedimientos_iddetalle_seq (OID = 23376) : 
--
CREATE SEQUENCE public.detalleplanprocedimientos_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanprocesoscalidad_iddetalle_seq (OID = 23378) : 
--
CREATE SEQUENCE public.detalleplanprocesoscalidad_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallepresupuesto_iddetalle_seq (OID = 23380) : 
--
CREATE SEQUENCE public.detallepresupuesto_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleproducto_iddetalle_seq (OID = 23382) : 
--
CREATE SEQUENCE public.detalleproducto_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleproductoreal_iddetalle_seq (OID = 23384) : 
--
CREATE SEQUENCE public.detalleproductoreal_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamocliente_iddetalle_seq (OID = 23386) : 
--
CREATE SEQUENCE public.detallereclamocliente_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamoempresametalurgica_iddetalle_seq (OID = 23388) : 
--
CREATE SEQUENCE public.detallereclamoempresametalurgica_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamoproveedor_iddetalle_seq (OID = 23390) : 
--
CREATE SEQUENCE public.detallereclamoproveedor_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleremito_iddetalle_seq (OID = 23392) : 
--
CREATE SEQUENCE public.detalleremito_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallerequerimientosmateriaprima_iddetalle_seq (OID = 23394) : 
--
CREATE SEQUENCE public.detallerequerimientosmateriaprima_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalletrabajotercerizado_iddetalle_seq (OID = 23396) : 
--
CREATE SEQUENCE public.detalletrabajotercerizado_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence domicilio_iddomicilio_seq (OID = 23398) : 
--
CREATE SEQUENCE public.domicilio_iddomicilio_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionetapaproduccion_idejecucion_seq (OID = 23400) : 
--
CREATE SEQUENCE public.ejecucionetapaproduccion_idejecucion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionplanificacioncalidad_idejecucion_seq (OID = 23402) : 
--
CREATE SEQUENCE public.ejecucionplanificacioncalidad_idejecucion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionplanificacionproduccion_idejecucion_seq (OID = 23404) : 
--
CREATE SEQUENCE public.ejecucionplanificacionproduccion_idejecucion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionprocesocalidad_idejecucion_seq (OID = 23406) : 
--
CREATE SEQUENCE public.ejecucionprocesocalidad_idejecucion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence empleado_idempleado_seq (OID = 23408) : 
--
CREATE SEQUENCE public.empleado_idempleado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence empresametalurgica_idempresametalurgica_seq (OID = 23410) : 
--
CREATE SEQUENCE public.empresametalurgica_idempresametalurgica_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadocliente_idestado_seq (OID = 23412) : 
--
CREATE SEQUENCE public.estadocliente_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadocompra_idestado_seq (OID = 23414) : 
--
CREATE SEQUENCE public.estadocompra_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadodetallecompra_idestado_seq (OID = 23416) : 
--
CREATE SEQUENCE public.estadodetallecompra_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadodetalletrabajotercerizado_idestado_seq (OID = 23418) : 
--
CREATE SEQUENCE public.estadodetalletrabajotercerizado_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecetapaprod_idestado_seq (OID = 23420) : 
--
CREATE SEQUENCE public.estadoejecetapaprod_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecplancalidad_idestado_seq (OID = 23422) : 
--
CREATE SEQUENCE public.estadoejecplancalidad_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecplanifpedido_idestado_seq (OID = 23424) : 
--
CREATE SEQUENCE public.estadoejecplanifpedido_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecucionprocesocalidad_idestado_seq (OID = 23426) : 
--
CREATE SEQUENCE public.estadoejecucionprocesocalidad_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadofactura_idestado_seq (OID = 23428) : 
--
CREATE SEQUENCE public.estadofactura_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadomaquina_idestado_seq (OID = 23430) : 
--
CREATE SEQUENCE public.estadomaquina_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadopedido_idestado_seq (OID = 23432) : 
--
CREATE SEQUENCE public.estadopedido_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadopiezareal_idestado_seq (OID = 23434) : 
--
CREATE SEQUENCE public.estadopiezareal_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoproductoreal_idestado_seq (OID = 23436) : 
--
CREATE SEQUENCE public.estadoproductoreal_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoremito_idestado_seq (OID = 23438) : 
--
CREATE SEQUENCE public.estadoremito_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadotrabajotercerizado_idestado_seq (OID = 23440) : 
--
CREATE SEQUENCE public.estadotrabajotercerizado_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence etapadeproduccion_idetapaproduccion_seq (OID = 23442) : 
--
CREATE SEQUENCE public.etapadeproduccion_idetapaproduccion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence factura_idfactura_seq (OID = 23444) : 
--
CREATE SEQUENCE public.factura_idfactura_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence formadepago_idformapago_seq (OID = 23446) : 
--
CREATE SEQUENCE public.formadepago_idformapago_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence localidad_idlocalidad_seq (OID = 23448) : 
--
CREATE SEQUENCE public.localidad_idlocalidad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence mantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 23450) : 
--
CREATE SEQUENCE public.mantenimientocorrectivo_idmantenimientocorrectivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence mantenimientopreventivo_idmantenimientopreventivo_seq (OID = 23452) : 
--
CREATE SEQUENCE public.mantenimientopreventivo_idmantenimientopreventivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence maquina_idmaquina_seq (OID = 23454) : 
--
CREATE SEQUENCE public.maquina_idmaquina_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence marca_idmarca_seq (OID = 23456) : 
--
CREATE SEQUENCE public.marca_idmarca_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence materiaprima_idmateriaprima_seq (OID = 23458) : 
--
CREATE SEQUENCE public.materiaprima_idmateriaprima_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence matriz_idmatriz_seq (OID = 23460) : 
--
CREATE SEQUENCE public.matriz_idmatriz_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pedido_idpedido_seq (OID = 23462) : 
--
CREATE SEQUENCE public.pedido_idpedido_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pedidomatriz_idpedidomatriz_seq (OID = 23464) : 
--
CREATE SEQUENCE public.pedidomatriz_idpedidomatriz_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pieza_idpieza_seq (OID = 23466) : 
--
CREATE SEQUENCE public.pieza_idpieza_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence piezareal_idpiezareal_seq (OID = 23468) : 
--
CREATE SEQUENCE public.piezareal_idpiezareal_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planificacioncalidad_idplanificacion_seq (OID = 23470) : 
--
CREATE SEQUENCE public.planificacioncalidad_idplanificacion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planificacionproduccion_idplanificacionproduccion_seq (OID = 23472) : 
--
CREATE SEQUENCE public.planificacionproduccion_idplanificacionproduccion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence plano_idplano_seq (OID = 23474) : 
--
CREATE SEQUENCE public.plano_idplano_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planprocedimientos_idplanprocedimientos_seq (OID = 23476) : 
--
CREATE SEQUENCE public.planprocedimientos_idplanprocedimientos_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planprocesoscalidad_idplanprocesoscalidad_seq (OID = 23478) : 
--
CREATE SEQUENCE public.planprocesoscalidad_idplanprocesoscalidad_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planrequerimientosmateriaprima_idplanrequerimientosmateriaprima (OID = 23480) : 
--
CREATE SEQUENCE public.planrequerimientosmateriaprima_idplanrequerimientosmateriaprima
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence presupuesto_idpresupuesto_seq (OID = 23482) : 
--
CREATE SEQUENCE public.presupuesto_idpresupuesto_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence prioridad_idprioridad_seq (OID = 23484) : 
--
CREATE SEQUENCE public.prioridad_idprioridad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence privilegio_idprivilegio_seq (OID = 23486) : 
--
CREATE SEQUENCE public.privilegio_idprivilegio_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence procesocalidad_idprocesocalidad_seq (OID = 23488) : 
--
CREATE SEQUENCE public.procesocalidad_idprocesocalidad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence producto_idproducto_seq (OID = 23490) : 
--
CREATE SEQUENCE public.producto_idproducto_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence productoreal_idproductoreal_seq (OID = 23492) : 
--
CREATE SEQUENCE public.productoreal_idproductoreal_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence proveedor_idproveedor_seq (OID = 23494) : 
--
CREATE SEQUENCE public.proveedor_idproveedor_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence proveedormantenimientomaquina_idproveedormantenimiento_seq (OID = 23496) : 
--
CREATE SEQUENCE public.proveedormantenimientomaquina_idproveedormantenimiento_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence provincia_idprovincia_seq (OID = 23498) : 
--
CREATE SEQUENCE public.provincia_idprovincia_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamocliente_idreclamo_seq (OID = 23500) : 
--
CREATE SEQUENCE public.reclamocliente_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamoempresametalurgica_idreclamo_seq (OID = 23502) : 
--
CREATE SEQUENCE public.reclamoempresametalurgica_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamoproveedor_idreclamo_seq (OID = 23504) : 
--
CREATE SEQUENCE public.reclamoproveedor_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence remito_idremito_seq (OID = 23506) : 
--
CREATE SEQUENCE public.remito_idremito_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence responsable_idresponsable_seq (OID = 23508) : 
--
CREATE SEQUENCE public.responsable_idresponsable_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence rol_idrol_seq (OID = 23510) : 
--
CREATE SEQUENCE public.rol_idrol_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence rotura_idrotura_seq (OID = 23512) : 
--
CREATE SEQUENCE public.rotura_idrotura_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence servicio_idservicio_seq (OID = 23514) : 
--
CREATE SEQUENCE public.servicio_idservicio_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence sesion_idsesion_seq (OID = 23516) : 
--
CREATE SEQUENCE public.sesion_idsesion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipodocumento_idtipodocumento_seq (OID = 23518) : 
--
CREATE SEQUENCE public.tipodocumento_idtipodocumento_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipoiva_idtipoiva_seq (OID = 23520) : 
--
CREATE SEQUENCE public.tipoiva_idtipoiva_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipomaquina_idtipomaquina_seq (OID = 23522) : 
--
CREATE SEQUENCE public.tipomaquina_idtipomaquina_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tiporeclamo_idtiporeclamo_seq (OID = 23524) : 
--
CREATE SEQUENCE public.tiporeclamo_idtiporeclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence trabajotercerizado_idtrabajo_seq (OID = 23526) : 
--
CREATE SEQUENCE public.trabajotercerizado_idtrabajo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence turno_idturno_seq (OID = 23528) : 
--
CREATE SEQUENCE public.turno_idturno_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for view viewdetallepedidocotizacion (OID = 23530) : 
--
CREATE VIEW public.viewdetallepedidocotizacion AS
SELECT p.nroproducto, p.nombre, p.descripcion, dp.cantidad,
    p.preciounitario AS precio, p.idproducto, dp.iddetalle, ped.idpedido
FROM producto p, pedido ped, detallepedido dp
WHERE ((dp.producto = p.idproducto) AND (ped.idpedido = dp.idpedido))
ORDER BY p.nombre;

--
-- Definition for view viewpedidoendetalleprocedimientos (OID = 23534) : 
--
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
-- Definition for sequence unidadmedida_idunidadmedida_seq (OID = 23538) : 
--
CREATE SEQUENCE public.unidadmedida_idunidadmedida_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table unidadmedida (OID = 23540) : 
--
CREATE TABLE public.unidadmedida (
    idunidadmedida bigint DEFAULT nextval('unidadmedida_idunidadmedida_seq'::regclass) NOT NULL,
    nombre character varying(20),
    descripcion character varying(50),
    encm double precision,
    enmm double precision
) WITH OIDS;
ALTER TABLE ONLY public.unidadmedida ALTER COLUMN idunidadmedida SET STATISTICS 0;
ALTER TABLE ONLY public.unidadmedida ALTER COLUMN nombre SET STATISTICS 0;
ALTER TABLE ONLY public.unidadmedida ALTER COLUMN descripcion SET STATISTICS 0;
--
-- Definition for view viewetapadeproduccion (OID = 23544) : 
--
CREATE VIEW public.viewetapadeproduccion AS
SELECT ep.nroetapaproduccion AS numero, ep.nombre, ep.horashombre,
    ep.horasmaquina, ep.duracionestimada, ep.idetapaproduccion AS idetapa
FROM etapadeproduccion ep;

--
-- Definition for sequence detallepiezapresupuesto_iddetalle_seq (OID = 23548) : 
--
CREATE SEQUENCE public.detallepiezapresupuesto_iddetalle_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detallepiezapresupuesto (OID = 23550) : 
--
CREATE TABLE public.detallepiezapresupuesto (
    duracionpiezaxetapa time(6) without time zone,
    iddetalle bigint DEFAULT nextval('detallepiezapresupuesto_iddetalle_seq'::regclass) NOT NULL,
    idetapa bigint,
    iddetalleproductopresupuesto bigint
) WITH OIDS;
ALTER TABLE ONLY public.detallepiezapresupuesto ALTER COLUMN duracionpiezaxetapa SET STATISTICS 0;
ALTER TABLE ONLY public.detallepiezapresupuesto ALTER COLUMN iddetalle SET STATISTICS 0;
--
-- Definition for sequence detalleproductopresupuesto_iddetalle_seq (OID = 23554) : 
--
CREATE SEQUENCE public.detalleproductopresupuesto_iddetalle_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detalleproductopresupuesto (OID = 23556) : 
--
CREATE TABLE public.detalleproductopresupuesto (
    iddetalle bigint DEFAULT nextval('detalleproductopresupuesto_iddetalle_seq'::regclass) NOT NULL,
    iddetallepresupuesto bigint,
    idpieza bigint,
    idmateriaprima bigint,
    cantmateriaprima integer,
    cantpiezas integer,
    preciomateriaprima double precision,
    idproveedor bigint
) WITH OIDS;
ALTER TABLE ONLY public.detalleproductopresupuesto ALTER COLUMN iddetalle SET STATISTICS 0;
--
-- Definition for sequence detallepiezacalidadpresupuesto_iddetalle_seq (OID = 23560) : 
--
CREATE SEQUENCE public.detallepiezacalidadpresupuesto_iddetalle_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detallepiezacalidadpresupuesto (OID = 23562) : 
--
CREATE TABLE public.detallepiezacalidadpresupuesto (
    iddetalle bigint DEFAULT nextval('detallepiezacalidadpresupuesto_iddetalle_seq'::regclass) NOT NULL,
    cantprocesocalidad integer,
    duracionxpieza time without time zone,
    idprocesocalidad bigint,
    iddetalleproductopresupuesto bigint
) WITH OIDS;
--
-- Definition for view viewprocesocalidad (OID = 23566) : 
--
CREATE VIEW public.viewprocesocalidad AS
SELECT pc.nroproceso, pc.nombre AS nombreproceso, pc.duracionestimada,
    pc.herramienta, ac.nombre AS nombreaccioncalidad, pc.idprocesocalidad
FROM procesocalidad pc, accioncalidad ac
WHERE (pc.accioncalidad = ac.idaccioncalidad)
ORDER BY pc.nombre;

--
-- Definition for view viewproveedorxmateriaprima (OID = 23570) : 
--
CREATE VIEW public.viewproveedorxmateriaprima AS
SELECT pro.nroproveedor, pro.razonsocial, civ.nombre AS condicioniva,
    prov.nombre AS provincia, pro.telefono, pro.mail, (((res.nombre)::text
    || ' '::text) || (res.apellido)::text) AS responsable, pxmp.precio,
    pro.idproveedor, res.idresponsable, mat.idmateriaprima
FROM proveedor pro, materiaprima mat, proveedorxmateriaprima pxmp,
    domicilio dom, condicioniva civ, responsable res, barrio bar, localidad
    loc, provincia prov
WHERE ((((((((pxmp.idmateriaprima = mat.idmateriaprima) AND
    (pxmp.idproveedor = pro.idproveedor)) AND (dom.iddomicilio =
    pro.domicilio)) AND (civ.idcondicioniva = pro.condicion)) AND
    (res.idresponsable = pro.responsable)) AND (dom.barrio = bar.idbarrio))
    AND (bar.localidad = loc.idlocalidad)) AND (loc.provincia = prov.idprovincia))
ORDER BY pxmp.precio;

--
-- Definition for view viewmateriaprima (OID = 23575) : 
--
CREATE VIEW public.viewmateriaprima AS
SELECT mp.nombre AS nombremateriaprima, mp.descripcion, mp.alto, mp.ancho,
    mp.largo, udm.nombre AS unidadmedida, tm.nombre AS tipomaterial,
    mp.idmateriaprima
FROM materiaprima mp, unidadmedida udm, tipomaterial tm
WHERE ((mp.unidadmedida = udm.idunidadmedida) AND (mp.tipomaterial =
    tm.idtipomaterial))
ORDER BY mp.nombre;

--
-- Definition for view viewproductopresupuesto (OID = 23579) : 
--
CREATE VIEW public.viewproductopresupuesto AS
SELECT dpre.cantidad AS cantidadproducto, pro.nombre AS nombreproducto,
    pro.preciounitario, (pro.preciounitario * (dpre.cantidad)::double
    precision) AS importe, ped.idpedido, pre.idpresupuesto, dpre.iddetalle
    AS iddetallepresupuesto, pro.idproducto
FROM presupuesto pre, pedido ped, producto pro, detallepresupuesto dpre
WHERE (((ped.presupuesto = pre.idpresupuesto) AND (dpre.idpresupuesto =
    pre.idpresupuesto)) AND (dpre.idproducto = pro.idproducto));

--
-- Definition for sequence calendario_id_seq (OID = 23583) : 
--
CREATE SEQUENCE public.calendario_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table calendario (OID = 23585) : 
--
CREATE TABLE public.calendario (
    dia integer,
    mes integer,
    anio integer,
    id bigint DEFAULT nextval('calendario_id_seq'::regclass) NOT NULL,
    fecha date,
    todoeldia boolean,
    horadesde time(0) without time zone,
    horahasta time(0) without time zone
) WITH OIDS;
--
-- Definition for view viewpedidosnoconfirmados (OID = 23589) : 
--
CREATE VIEW public.viewpedidosnoconfirmados AS
SELECT ped.nropedido, ped.nropedidocotizacioncliente AS nropedcotcli,
    ped.fechapedidocotizacion AS fechapedido, eped.nombre AS estado,
    pre.nropresupuesto, pre.montototal, pre.fechavencimiento AS
    vencimientopresupuesto, cli.razonsocial, ped.idpedido,
    pre.idpresupuesto, eped.idestado, cli.idcliente
FROM pedido ped, cliente cli, presupuesto pre, estadopedido eped
WHERE ((((ped.cliente = cli.idcliente) AND (pre.idpresupuesto =
    ped.presupuesto)) AND (ped.estado = eped.idestado)) AND (eped.idestado = 2))
ORDER BY ped.nropedido;

--
-- Definition for sequence disponibilidadhoraria_id_seq (OID = 23593) : 
--
CREATE SEQUENCE public.disponibilidadhoraria_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table disponibilidadhoraria (OID = 23595) : 
--
CREATE TABLE public.disponibilidadhoraria (
    id bigint DEFAULT nextval('disponibilidadhoraria_id_seq'::regclass) NOT NULL,
    fecha date,
    tiempodisponible time without time zone,
    idempleado bigint
) WITH OIDS;
--
-- Definition for sequence estadoplanificacionproduccion_id_seq (OID = 23599) : 
--
CREATE SEQUENCE public.estadoplanificacionproduccion_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table estadoplanificacionproduccion (OID = 23601) : 
--
CREATE TABLE public.estadoplanificacionproduccion (
    id bigint DEFAULT nextval('estadoplanificacionproduccion_id_seq'::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying(250)
) WITH OIDS;
--
-- Definition for sequence maquina_idmaquina_seq1 (OID = 23608) : 
--
CREATE SEQUENCE public.maquina_idmaquina_seq1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table maquina (OID = 23610) : 
--
CREATE TABLE public.maquina (
    idmaquina bigint DEFAULT nextval('maquina_idmaquina_seq1'::regclass) NOT NULL,
    nombre character varying(50),
    marca bigint,
    descripcion character varying(100),
    estado bigint,
    tipomaquina bigint,
    fechaalta date,
    fechabaja date,
    tiempocapacidadproduccion time(6) without time zone,
    idunidadmedida bigint
) WITH OIDS;
--
-- Definition for sequence piezareal_idpiezareal_seq1 (OID = 23614) : 
--
CREATE SEQUENCE public.piezareal_idpiezareal_seq1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table piezareal (OID = 23616) : 
--
CREATE TABLE public.piezareal (
    idpiezareal bigint DEFAULT nextval('piezareal_idpiezareal_seq1'::regclass) NOT NULL,
    idpieza bigint NOT NULL,
    estado bigint,
    nropieza integer,
    idcodigobarra bigint
) WITH OIDS;
--
-- Definition for sequence pieza_idpieza_seq1 (OID = 23620) : 
--
CREATE SEQUENCE public.pieza_idpieza_seq1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table pieza (OID = 23622) : 
--
CREATE TABLE public.pieza (
    idpieza bigint DEFAULT nextval('pieza_idpieza_seq1'::regclass) NOT NULL,
    nombre character varying(50),
    tipomaterial bigint,
    materiaprima bigint,
    matriz bigint,
    alto numeric(10,3),
    ancho numeric(10,3),
    largo numeric(10,3),
    unidadmedida bigint
) WITH OIDS;
--
-- Definition for sequence detallempasignada_id_seq (OID = 23626) : 
--
CREATE SEQUENCE public.detallempasignada_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detallempasignada (OID = 23628) : 
--
CREATE TABLE public.detallempasignada (
    id bigint DEFAULT nextval('detallempasignada_id_seq'::regclass) NOT NULL,
    idmateriaprima bigint,
    cantidadmp integer,
    idplanificacionproduccion bigint
) WITH OIDS;
--
-- Definition for sequence mpasignadaxpiezareal_id_seq (OID = 23632) : 
--
CREATE SEQUENCE public.mpasignadaxpiezareal_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table mpasignadaxpiezareal (OID = 23634) : 
--
CREATE TABLE public.mpasignadaxpiezareal (
    idpiezareal bigint,
    iddetallempasignada bigint,
    id bigint DEFAULT nextval('mpasignadaxpiezareal_id_seq'::regclass) NOT NULL
) WITH OIDS;
--
-- Definition for view viewcantidadmpasiganda (OID = 23638) : 
--
CREATE VIEW public.viewcantidadmpasiganda AS
SELECT ppro.pedido, dmpa.idmateriaprima, sum(dmpa.cantidadmp) AS cantidad
FROM planificacionproduccion ppro, detallempasignada dmpa
WHERE (ppro.idplanificacionproduccion = dmpa.idplanificacionproduccion)
GROUP BY ppro.pedido, dmpa.idmateriaprima;

--
-- Structure for table asistencia (OID = 23642) : 
--
CREATE TABLE public.asistencia (
    empleado bigint NOT NULL,
    fechaingreso date NOT NULL,
    horaingreso time without time zone NOT NULL,
    horaegreso time without time zone,
    observaciones character varying(100)
) WITH OIDS;
--
-- Definition for view viewpedidosconrecasignados (OID = 23645) : 
--
CREATE VIEW public.viewpedidosconrecasignados AS
SELECT pe.nropedido, pe.nropedidocotizacioncliente, cli.razonsocial,
    pri.nombre AS prioridad, pe.fechaentregaestipulada, pe.idpedido,
    cli.idcliente, pri.idprioridad, pe.presupuesto, pp.idestado
FROM pedido pe, planificacionproduccion pp, cliente cli, prioridad pri
WHERE ((((pe.idpedido = pp.pedido) AND (cli.idcliente = pe.cliente)) AND
    (pri.idprioridad = pe.prioridad)) AND (pe.estado = 5));

--
-- Definition for view viewcantidadmpenpedido (OID = 23649) : 
--
CREATE VIEW public.viewcantidadmpenpedido AS
SELECT pe.idpedido AS pedido, dpp.idmateriaprima, sum(dpp.cantmateriaprima)
    AS cantidad
FROM pedido pe, detalleproductopresupuesto dpp, presupuesto pre,
    detallepresupuesto dp
WHERE (((pe.presupuesto = pre.idpresupuesto) AND (dp.idpresupuesto =
    pre.idpresupuesto)) AND (dp.iddetalle = dpp.iddetallepresupuesto))
GROUP BY pe.idpedido, dpp.idmateriaprima;

--
-- Definition for view viewmpxpiezapresupuesto (OID = 23653) : 
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
    iddetalleproductopresupuesto, pro.idproducto, pi.idpieza,
    mp.idmateriaprima, dpropre.idproveedor
FROM presupuesto p, detallepresupuesto dp, detalleproductopresupuesto
    dpropre, producto pro, pieza pi, materiaprima mp
WHERE (((((p.idpresupuesto = dp.idpresupuesto) AND (dp.idproducto =
    pro.idproducto)) AND (dp.iddetalle = dpropre.iddetallepresupuesto)) AND
    (dpropre.idpieza = pi.idpieza)) AND (dpropre.idmateriaprima =
    mp.idmateriaprima));

--
-- Definition for view viewetapasxpiezapresupuesto (OID = 23657) : 
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
-- Definition for view viewprocalidadxpiezapresupesto (OID = 23661) : 
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
-- Definition for view viewpedidosconmpasignada (OID = 23666) : 
--
CREATE VIEW public.viewpedidosconmpasignada AS
SELECT ped.nropedido, planpro.nroplanificacion AS
    nroplanificacionproduccion, planpro.fechacreacion,
    planpro.fechainicioprevista, planpro.fechafinprevista,
    planpro.observaciones, ped.idpedido, planpro.idplanificacionproduccion
FROM pedido ped, planificacionproduccion planpro
WHERE ((ped.idpedido = planpro.pedido) AND (ped.estado = 3));

--
-- Definition for view viewdetalleproducto (OID = 23670) : 
--
CREATE VIEW public.viewdetalleproducto AS
SELECT pi.nombre AS nombrepieza, dp.descripcion, dp.cantidadpiezas,
    pi.alto, pi.ancho, pi.largo, tm.nombre AS nombretipomaterial,
    pi.idpieza, dp.iddetalle, p.idproducto
FROM producto p, detalleproducto dp, pieza pi, tipomaterial tm
WHERE (((dp.idproducto = p.idproducto) AND (dp.pieza = pi.idpieza)) AND
    (pi.tipomaterial = tm.idtipomaterial))
ORDER BY pi.nombre;

--
-- Definition for view viewpedidosconplanifsinrecursos (OID = 23674) : 
--
CREATE VIEW public.viewpedidosconplanifsinrecursos AS
SELECT ped.nropedido, ped.nropedidocotizacioncliente AS nropedcotcli,
    ped.fechapedidocotizacion AS fechapedido, ped.fechaentregaestipulada,
    eped.nombre AS estado, pre.nropresupuesto, pre.fechapresupuesto,
    pre.montototal, cli.nrocliente, cli.razonsocial, ped.idpedido,
    pre.idpresupuesto, eped.idestado, cli.idcliente
FROM pedido ped, cliente cli, presupuesto pre, estadopedido eped
WHERE (((((ped.cliente = cli.idcliente) AND (pre.idpresupuesto =
    ped.presupuesto)) AND (ped.estado = eped.idestado)) AND (eped.idestado
    = 4)) AND (0 = (
    SELECT count(*) AS count
    FROM planificacionproduccion pla, estadoplanificacionproduccion epla
    WHERE (((pla.idestado = epla.id) AND (epla.id = 1)) AND (ped.idpedido =
        pla.pedido))
    )))
ORDER BY ped.nropedido;

--
-- Definition for view viewpedidosclientesegunestado (OID = 23679) : 
--
CREATE VIEW public.viewpedidosclientesegunestado AS
SELECT p.nropedido, p.nropedidocotizacioncliente, p.fechapedidocotizacion,
    p.fecharequeridacotizacion, p.fechaentregaestipulada, p.espedidoweb,
    ep.nombre AS estado, pr.nombre AS prioridad, c.razonsocial AS cliente,
    p.idpedido, ep.idestado, c.idcliente, c.nrocliente
FROM pedido p, cliente c, estadopedido ep, prioridad pr
WHERE (((p.cliente = c.idcliente) AND (p.estado = ep.idestado)) AND
    (p.prioridad = pr.idprioridad))
ORDER BY p.nropedido;

--
-- Data for table public.estadopedido (OID = 22938) (LIMIT 0,18)
--
INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (1, 'Generado', 'Pedido generado');

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (2, 'Presupuestado', NULL);

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (4, 'Confirmado', NULL);

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (5, 'Planificado', NULL);

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (6, 'En producción', NULL);

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (7, 'En calidad', NULL);

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (8, 'En armado', NULL);

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (9, 'Entregado', NULL);

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (10, 'Cobrado', NULL);

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (11, 'Rechazado', NULL);

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (12, 'Nunca cobrado', NULL);

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (13, 'Vencido', NULL);

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (14, 'Reclamado', NULL);

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (15, 'Cancelado', NULL);

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (16, 'C/Det. Procedimientos', 'Pedido con detalle de procedimientos');

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (17, 'C/Det. Mat. Prima', 'Pedido con detalle de materia prima');

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (18, 'C/Det. Procesos Calidad', 'Pedido con dcetalle de procesos de calidad');

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (3, 'C/Mat. Prima Asignada', 'Asignación de MP a producción por parte del área de Almacenamiento');

--
-- Data for table public.cliente (OID = 22969) (LIMIT 0,3)
--
INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (123, 4, 1, 1, false, 1, 'Nerverland', 1, '4009765', '154098654', 'neverlan@juegos.com', 21, '2010-07-01', NULL, '20-32987653-0', 1, '20-32987653-0');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (145, 5, 2, 2, false, 2, 'Rogio', 2, '4200119', '153876881', 'rogio@argentina.com', 11, '2010-09-04', '2010-07-06', '31-31486700-3', 2, '31-31486700-3');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (7, 6, 3, 2, false, 3, 'Martinez y Cia', 5, '4765123', '155233401', 'maritinez@martinez.com', 4, '2010-10-12', '2010-07-06', '27-27890543-2', 3, '27-27890543-2');

--
-- Data for table public.usuario (OID = 22981) (LIMIT 0,4)
--
INSERT INTO usuario (idusuario, usuario, clave)
VALUES (1, 'Nino', 'Nino');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (2, 'admin', 'admin');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (3, 'Charly', 'charly');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (4, 'Mari', 'mari');

--
-- Data for table public.estadofactura (OID = 22985) (LIMIT 0,1)
--
INSERT INTO estadofactura (idestado, nombre, descripcion)
VALUES (1, 'GENERADO', 'GENERADO');

--
-- Data for table public.prioridad (OID = 23001) (LIMIT 0,3)
--
INSERT INTO prioridad (idprioridad, nombre, descripcion)
VALUES (1, 'Alta', 'Prioridad Alta');

INSERT INTO prioridad (idprioridad, nombre, descripcion)
VALUES (2, 'Baja', 'Prioridad Baja');

INSERT INTO prioridad (idprioridad, nombre, descripcion)
VALUES (3, 'Normal', 'Prioridad Normal');

--
-- Data for table public.estadocliente (OID = 23005) (LIMIT 0,3)
--
INSERT INTO estadocliente (idestado, nombre, descripcion)
VALUES (1, 'Activo', 'Dado de Alta');

INSERT INTO estadocliente (idestado, nombre, descripcion)
VALUES (2, 'Baja', 'Dado de Baja');

INSERT INTO estadocliente (idestado, nombre, descripcion)
VALUES (3, 'Moroso', 'Cliente Moroso, adeuda facturas');

--
-- Data for table public.responsable (OID = 23009) (LIMIT 0,4)
--
INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (6, 'Mariana', 'Enrico', '4678765', 'mari@gmail.com', 18, 31876345, 1, '');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (5, 'Lorena', 'Barale', '4210945', 'lore@gmail.com', 16, 27098567, 1, '');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (2, 'Nino', 'Molina', '4487623', 'nino@gmail.com', 11, 24256786, 1, '47865432');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (1, 'Maria Victoria', 'Merdine', '4980765', 'mv@hotmail.com', 9, 32647263, 1, '4590876');

--
-- Data for table public.condicioniva (OID = 23013) (LIMIT 0,3)
--
INSERT INTO condicioniva (idcondicioniva, nombre, descripcion)
VALUES (1, 'R.Inscripto', 'Responsable Inscripto');

INSERT INTO condicioniva (idcondicioniva, nombre, descripcion)
VALUES (2, 'Monotributista', 'Monotributista');

INSERT INTO condicioniva (idcondicioniva, nombre, descripcion)
VALUES (3, 'C.Final', 'Consumidor Final');

--
-- Data for table public.domicilio (OID = 23017) (LIMIT 0,12)
--
INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (21, 'Tolosa', 6565, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (1, 'Húsares', 1983, 0, '', '', 2);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (2, 'América', 1169, 0, '', '', 3);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (3, 'Don Bosco', 1987, 5, 'D', '1', 2);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (5, 'Cruz roja', 43, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (4, 'Fragueiro', 4545, 1, 'A', '2', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (6, 'Rondeau', 43, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (39, 'Colon', 54, 4, 'B', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (9, 'Arregui', 1169, 0, '', '', 3);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (11, 'Carcano', 5434, 0, '', '', 6);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (16, 'Rio Cosquin', 342, 0, '', '', 4);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (18, 'España', 2078, 2, '', '', 2);

--
-- Data for table public.categoria (OID = 23025) (LIMIT 0,2)
--
INSERT INTO categoria (idcategoria, nombre, descripcion)
VALUES (1, 'operario', 'operario en general');

INSERT INTO categoria (idcategoria, nombre, descripcion)
VALUES (2, 'oficial de planta', 'oficial de planta');

--
-- Data for table public.cargo (OID = 23029) (LIMIT 0,3)
--
INSERT INTO cargo (idcargo, nombre, descripcion)
VALUES (1, 'Tornero', 'operario de tornos');

INSERT INTO cargo (idcargo, nombre, descripcion)
VALUES (2, 'Afilador', 'Realiza afilados');

INSERT INTO cargo (idcargo, nombre, descripcion)
VALUES (3, 'Rectificador', 'rectificador');

--
-- Data for table public.turno (OID = 23033) (LIMIT 0,3)
--
INSERT INTO turno (idturno, horainicio, horafin, nombre, descripcion)
VALUES (1, '00:08:00', '00:12:00', 'MAÑANA', NULL);

INSERT INTO turno (idturno, horainicio, horafin, nombre, descripcion)
VALUES (2, '00:13:00', '00:17:00', 'TARDE', NULL);

INSERT INTO turno (idturno, horainicio, horafin, nombre, descripcion)
VALUES (3, '00:18:00', '00:22:00', 'NOCHE', NULL);

--
-- Data for table public.marca (OID = 23037) (LIMIT 0,1)
--
INSERT INTO marca (idmarca, nombre, descripcion)
VALUES (1, 'Tornomax', 'asdasdas');

--
-- Data for table public.estadomaquina (OID = 23041) (LIMIT 0,1)
--
INSERT INTO estadomaquina (idestado, nombre, descripcion)
VALUES (1, 'Disponible', 'mauqina disponible');

--
-- Data for table public.tipomaquina (OID = 23045) (LIMIT 0,2)
--
INSERT INTO tipomaquina (idtipomaquina, nombre, descripcion)
VALUES (3, 'manual', NULL);

INSERT INTO tipomaquina (idtipomaquina, nombre, descripcion)
VALUES (1, 'mecanica', '');

--
-- Data for table public.tipodocumento (OID = 23057) (LIMIT 0,3)
--
INSERT INTO tipodocumento (idtipodocumento, tipo, nombre)
VALUES (1, 'DNI', 'Documento Nacional de Identidad');

INSERT INTO tipodocumento (idtipodocumento, tipo, nombre)
VALUES (2, 'LE', 'Libreta de Enrolamiento');

INSERT INTO tipodocumento (idtipodocumento, tipo, nombre)
VALUES (3, 'LC', 'Libreta Cívica');

--
-- Data for table public.codigodebarra (OID = 23061) (LIMIT 0,1)
--
INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (1, 'xxx', '1234');

--
-- Data for table public.estadopiezareal (OID = 23065) (LIMIT 0,1)
--
INSERT INTO estadopiezareal (idestado, nombre, descripcion)
VALUES (1, 'INICIADO', NULL);

--
-- Data for table public.estadoejecplanifpedido (OID = 23069) (LIMIT 0,1)
--
INSERT INTO estadoejecplanifpedido (idestado, nombre, descripcion)
VALUES (1, 'INICIADO', 'Se ha lanzado la producción de una planificación');

--
-- Data for table public.rol (OID = 23077) (LIMIT 0,5)
--
INSERT INTO rol (idrol, rol, descripcion)
VALUES (1, 'ADMIN', 'Administrador del Sistema');

INSERT INTO rol (idrol, rol, descripcion)
VALUES (2, 'VENTAS', 'Responsable de Ventas');

INSERT INTO rol (idrol, rol, descripcion)
VALUES (3, 'COMPRAS', 'Responsable de Compras');

INSERT INTO rol (idrol, rol, descripcion)
VALUES (4, 'CALIDAD', 'Responsable de Calidad');

INSERT INTO rol (idrol, rol, descripcion)
VALUES (5, 'ALMACENAMIENTO', 'Responsable de Almacenamiento');

--
-- Data for table public.accioncalidad (OID = 23085) (LIMIT 0,3)
--
INSERT INTO accioncalidad (idaccioncalidad, nombre, descripcion)
VALUES (1, 'Medir', 'Realizar una medición de las dimensiones del objeto');

INSERT INTO accioncalidad (idaccioncalidad, nombre, descripcion)
VALUES (2, 'Contar', 'Realizar el conteo de diferentes aspectos de un objeto');

INSERT INTO accioncalidad (idaccioncalidad, nombre, descripcion)
VALUES (3, 'Observar', 'Realizar una observación general del objeto');

--
-- Data for table public.empleado (OID = 23089) (LIMIT 0,4)
--
INSERT INTO empleado (idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo)
VALUES (3, 256542, '2005-07-03', 'Victoria', 'Merdine', '4352673', 'vicky@hotmail.com', 1, 32456788, 1, 1, 2, NULL, NULL, 1);

INSERT INTO empleado (idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo)
VALUES (1, 234234, '2002-12-01', 'Mariana', 'Enrico', '4672839', 'mari_enrico@gmail.com.ar', 2, 33387654, 1, 1, 4, NULL, NULL, 2);

INSERT INTO empleado (idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo)
VALUES (4, 241236, '2001-09-05', 'Lorena', 'Barale', '4356671', 'lore@hotmail.com', 3, 30675432, 1, 1, 1, NULL, NULL, 3);

INSERT INTO empleado (idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo)
VALUES (2, 234111, '1999-10-10', 'Carlos', 'Castro', '4344425', 'carlos@gmail.com', 4, 31231987, 1, 1, 3, NULL, NULL, 1);

--
-- Data for table public.proveedor (OID = 23093) (LIMIT 0,3)
--
INSERT INTO proveedor (idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit)
VALUES (1, 1, 'Techin', 1, '4354213', '155238976', 'techin@insdustrias.com', 5, '1990-02-01', NULL, '32-32323232-3', 1, '21-21643121-2');

INSERT INTO proveedor (idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit)
VALUES (2, 2, 'MetalArg', 2, '4353545', '543543545', 'metal@metal.com', 2, '2000-10-10', NULL, '32-33333333-3', 1, '32-35690733-3');

INSERT INTO proveedor (idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit)
VALUES (3, 3, 'AcerMax', 5, '4894536', '153234090', 'acermax@argentina.com', 39, '2010-09-14', NULL, '21-76056332-1', 1, '21-76056782-1');

--
-- Data for table public.materiaprima (OID = 23105) (LIMIT 0,6)
--
INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (4, 'PVC', NULL, NULL, 1, 99, '', 2, 2, 100.000, 100.000, 100.000, '6543', 19, 0);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (5, 'Cobre', NULL, NULL, 1, 100, '', 2, 2, 50.000, 50.000, 50.000, '6543', 25, NULL);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (1, 'Acero 1010', NULL, NULL, 1, 86, NULL, 2, 1, 33.420, 50.000, 50.000, '0', 12, 0);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (7, 'Acero 1020', '2010-09-14', NULL, 1, 100, '', 2, 3, 100.000, 100.000, 100.000, '0', 22, 1);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (8, 'Acero 1015', '2010-09-14', NULL, 1, 100, '', 5, 2, 100.000, 100.000, 50.000, '0', 21, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (9, 'Aluminio', '2010-11-01', NULL, 1, 100, '', 4, 1, 100.000, 60.000, 60.000, '0', 30, 3);

--
-- Data for table public.matriz (OID = 23109) (LIMIT 0,3)
--
INSERT INTO matriz (idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial)
VALUES (1, 123, 'MatrizMango', '', NULL, NULL, 1, 4);

INSERT INTO matriz (idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial)
VALUES (4, 5453, 'MatrizPerilla', '', NULL, NULL, 1, 2);

INSERT INTO matriz (idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial)
VALUES (3, 654, 'MatrizVolante', '', NULL, NULL, 7, 1);

--
-- Data for table public.producto (OID = 23113) (LIMIT 0,5)
--
INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (3, 345, 'Volante', 67, '');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (4, 143, 'Mango', 102, '');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (5, 176, 'Bolos', 80, '');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (6, 176, 'Perilla para panel de control', 145, '');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (7, 99, 'Portafiltro', 92, '');

--
-- Data for table public.etapadeproduccion (OID = 23129) (LIMIT 0,5)
--
INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, maquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (1, 2, 'rectificado', '00:00:30', '00:00:30', 1, '00:00:30', '2010-08-16', 2);

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, maquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (3, 12, 'fresado', '00:02:00', '00:02:00', NULL, '00:02:00', '2010-10-10', 2);

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, maquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (4, 15, 'afilado', NULL, '00:20:00', NULL, '00:20:00', '2010-10-10', 2);

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, maquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (5, 11, 'pulido', '00:20:00', '00:20:00', NULL, '00:20:00', '2010-10-10', 2);

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, maquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (2, 3, 'torneado', '00:00:20', '00:00:20', NULL, '00:00:20', '2010-12-12', 1);

--
-- Data for table public.procesocalidad (OID = 23137) (LIMIT 0,2)
--
INSERT INTO procesocalidad (idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad)
VALUES (1, 'Detectar Spatter', 1, NULL, '3', NULL, '00:05:00', '2010-09-06', NULL, 2);

INSERT INTO procesocalidad (idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad)
VALUES (2, 'Rectificación', 2, 'Null', NULL, 'Determinar si el nivel de rectificación es el adecuado', '00:10:00', '2010-08-06', 'calibre', 3);

--
-- Data for table public.detalleproducto (OID = 23177) (LIMIT 0,9)
--
INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (4, 3, 1, '', 8);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (6, 4, 1, '', 6);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (5, 4, 1, '', 11);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (8, 5, 1, '', 12);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (12, 6, 2, '', 9);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (13, 6, 1, '', 10);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (14, 6, 1, '', 11);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (15, 7, 1, '', 13);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (16, 7, 1, '', 10);

--
-- Data for table public.proveedorxmateriaprima (OID = 23229) (LIMIT 0,9)
--
INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (1, 1, 20);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (2, 1, 22);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (1, 4, 25);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (2, 4, 31);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (1, 5, 19);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (1, 7, 24);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (2, 7, 27);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (1, 8, 30);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (2, 8, 28);

--
-- Data for table public.piezaxetapadeproduccion (OID = 23238) (LIMIT 0,1)
--
INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (6, 1, '04:00:00', 'Seteado a mano');

--
-- Data for table public.provincia (OID = 23244) (LIMIT 0,7)
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
-- Data for table public.localidad (OID = 23248) (LIMIT 0,10)
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
-- Data for table public.barrio (OID = 23252) (LIMIT 0,6)
--
INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (1, 'Nva. Córdoba', 5000, 1);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (2, 'Alta Córdoba', 5000, 1);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (3, 'Poeta Lugones', 5220, 2);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (4, 'San Vicente', 5003, 1);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (5, 'Salta', 4008, 5);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (6, 'La Quinta', 3200, 3);

--
-- Data for table public.usuarioxrol (OID = 23256) (LIMIT 0,2)
--
INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (2, 1);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (1, 2);

--
-- Data for table public.estadoremito (OID = 23322) (LIMIT 0,1)
--
INSERT INTO estadoremito (idestado, nombre, descripcion)
VALUES (1, 'GENERADO', 'GENERADO');

--
-- Data for table public.tipomaterial (OID = 23326) (LIMIT 0,3)
--
INSERT INTO tipomaterial (idtipomaterial, nombre, descripcion)
VALUES (2, 'otro mas', 'algun ootr');

INSERT INTO tipomaterial (idtipomaterial, nombre, descripcion)
VALUES (4, 'Chapa', 'Chapa de acero inoxidable');

INSERT INTO tipomaterial (idtipomaterial, nombre, descripcion)
VALUES (5, 'Hierro', 'dsadasdasd');

--
-- Data for table public.unidadmedida (OID = 23540) (LIMIT 0,4)
--
INSERT INTO unidadmedida (idunidadmedida, nombre, descripcion, encm, enmm)
VALUES (1, 'mm3', 'milímetros', 0.1, 1);

INSERT INTO unidadmedida (idunidadmedida, nombre, descripcion, encm, enmm)
VALUES (2, 'cm3', 'centímetros', 1, 10);

INSERT INTO unidadmedida (idunidadmedida, nombre, descripcion, encm, enmm)
VALUES (3, 'mt3', 'metros', 100, 1000);

INSERT INTO unidadmedida (idunidadmedida, nombre, descripcion, encm, enmm)
VALUES (4, 'pulgada3', 'pulgadas', 2.54, 25.4);

--
-- Data for table public.calendario (OID = 23585) (LIMIT 0,7)
--
INSERT INTO calendario (dia, mes, anio, id, fecha, todoeldia, horadesde, horahasta)
VALUES (28, 11, 2010, 2, '2010-11-28', true, NULL, NULL);

INSERT INTO calendario (dia, mes, anio, id, fecha, todoeldia, horadesde, horahasta)
VALUES (13, 10, 2010, 1, '2010-10-13', true, NULL, NULL);

INSERT INTO calendario (dia, mes, anio, id, fecha, todoeldia, horadesde, horahasta)
VALUES (15, 10, 2010, 3, '2010-10-15', true, NULL, NULL);

INSERT INTO calendario (dia, mes, anio, id, fecha, todoeldia, horadesde, horahasta)
VALUES (15, 10, 2010, 4, '2010-10-15', true, NULL, NULL);

INSERT INTO calendario (dia, mes, anio, id, fecha, todoeldia, horadesde, horahasta)
VALUES (30, 10, 2010, 5, '2010-10-30', true, NULL, NULL);

INSERT INTO calendario (dia, mes, anio, id, fecha, todoeldia, horadesde, horahasta)
VALUES (25, 12, 2010, 6, '2010-12-25', true, NULL, NULL);

INSERT INTO calendario (dia, mes, anio, id, fecha, todoeldia, horadesde, horahasta)
VALUES (21, 10, 2010, 7, '2010-10-21', true, NULL, NULL);

--
-- Data for table public.disponibilidadhoraria (OID = 23595) (LIMIT 0,6)
--
INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado)
VALUES (1, '2010-10-10', '01:01:00', 1);

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado)
VALUES (2, '2010-10-11', '01:00:00', 1);

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado)
VALUES (3, '2010-10-12', '02:30:00', 1);

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado)
VALUES (4, '2010-10-13', '04:30:00', 1);

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado)
VALUES (5, '2010-10-14', '03:00:00', 1);

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado)
VALUES (6, '2010-10-15', '08:00:00', 1);

--
-- Data for table public.estadoplanificacionproduccion (OID = 23601) (LIMIT 0,3)
--
INSERT INTO estadoplanificacionproduccion (id, nombre, descripcion)
VALUES (1, 'REC-ASIG', 'Recusos Asignados');

INSERT INTO estadoplanificacionproduccion (id, nombre, descripcion)
VALUES (2, 'MP-ASIG', 'Materia Prima Asignada');

INSERT INTO estadoplanificacionproduccion (id, nombre, descripcion)
VALUES (3, 'GENERADA', 'Estado inicial de la planificación');

--
-- Data for table public.maquina (OID = 23610) (LIMIT 0,4)
--
INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (1, 'Torno', 1, '', 1, 1, '2010-10-10', NULL, '00:03:00', 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (2, 'Fresadora', 1, '', 1, 1, '2010-10-19', NULL, '00:02:00', 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (4, 'Rectificadora', 1, NULL, 1, 1, '2010-11-09', NULL, NULL, 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (3, 'Afiladora', 1, NULL, 1, 3, '2010-11-04', NULL, NULL, 1);

--
-- Data for table public.pieza (OID = 23622) (LIMIT 0,8)
--
INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (6, 'Mango', 2, 1, NULL, 3.560, 5.000, 1.234, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (9, 'Eje', 4, 5, 1, 4.500, 2.000, 2.000, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (10, 'Panel', 4, 5, NULL, 10.000, 3.200, 3.200, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (11, 'Perilla', 2, 4, 1, 3.330, 4.000, 3.300, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (8, 'Volante', 2, 4, 1, 5.550, 7.540, 10.540, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (12, 'Bolos', 4, 1, 1, 10.000, 10.000, 10.000, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (13, 'Bloque', 4, 1, NULL, 5.000, 5.000, 5.000, 1);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (14, 'Engranaje', 2, 1, NULL, 10.000, 5.900, 10.000, 1);

--
-- Definition for index pedido_nropedido_key (OID = 24147) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_nropedido_key UNIQUE (nropedido);
--
-- Definition for index ejecucionplanificacioncalidad_idplanificacioncalidad_key (OID = 24149) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_idplanificacioncalidad_key UNIQUE (idplanificacioncalidad);
--
-- Definition for index ejecucionprocesocalidad_idprocesocalidad_key (OID = 24151) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_idprocesocalidad_key UNIQUE (idprocesocalidad);
--
-- Definition for index proveedorxmateriaprima_idx (OID = 24153) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_idx PRIMARY KEY (idmateriaprima, idproveedor);
--
-- Definition for index maquinaxejecucionetapaproduccion_idx (OID = 24155) : 
--
ALTER TABLE ONLY maquinaxejecucionetapaproduccion
    ADD CONSTRAINT maquinaxejecucionetapaproduccion_idx PRIMARY KEY (idejecucionetapaproduccion, idetapaproduccion, idmaquina);
--
-- Definition for index maquinaxprocesocalidad_idx (OID = 24157) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT maquinaxprocesocalidad_idx PRIMARY KEY (idprocesocalidad, idmaquina);
--
-- Definition for index piezaxetapadeproduccion_idx (OID = 24159) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT piezaxetapadeproduccion_idx PRIMARY KEY (idpieza, idetapaproduccion);
--
-- Definition for index empleadoxturno_idx (OID = 24161) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_idx PRIMARY KEY (idempleado, idturno);
--
-- Definition for index usuarioxrol_pkey (OID = 24163) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_pkey PRIMARY KEY (idrol, idusuario);
--
-- Definition for index rolxprivilegio_pkey (OID = 24165) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_pkey PRIMARY KEY (idrol, idprivilegio);
--
-- Definition for index prueba_pkey (OID = 24167) : 
--
ALTER TABLE ONLY prueba
    ADD CONSTRAINT prueba_pkey PRIMARY KEY (id);
--
-- Definition for index usuario_pkey (OID = 24169) : 
--
ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (idusuario);
--
-- Definition for index factura_fk2 (OID = 24171) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk2 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index comprobantepago_fk1 (OID = 24176) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk1 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index cliente_fk2 (OID = 24181) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk2 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index empleado_fk3 (OID = 24186) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk3 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index sesion_fk (OID = 24191) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT sesion_fk FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index usuarioxrol_fk1 (OID = 24196) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_fk1 FOREIGN KEY (idusuario) REFERENCES usuario(idusuario);
--
-- Definition for index tipomaterial_pkey (OID = 24201) : 
--
ALTER TABLE ONLY tipomaterial
    ADD CONSTRAINT tipomaterial_pkey PRIMARY KEY (idtipomaterial);
--
-- Definition for index accioncalidad_pkey (OID = 24203) : 
--
ALTER TABLE ONLY accioncalidad
    ADD CONSTRAINT accioncalidad_pkey PRIMARY KEY (idaccioncalidad);
--
-- Definition for index procesocalidad_fk (OID = 24205) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT procesocalidad_fk FOREIGN KEY (accioncalidad) REFERENCES accioncalidad(idaccioncalidad);
--
-- Definition for index barrio_pkey (OID = 24210) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT barrio_pkey PRIMARY KEY (idbarrio);
--
-- Definition for index domicilio_fk (OID = 24212) : 
--
ALTER TABLE ONLY domicilio
    ADD CONSTRAINT domicilio_fk FOREIGN KEY (barrio) REFERENCES barrio(idbarrio);
--
-- Definition for index cargo_pkey (OID = 24217) : 
--
ALTER TABLE ONLY cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (idcargo);
--
-- Definition for index empleado_fk4 (OID = 24219) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk4 FOREIGN KEY (cargo) REFERENCES cargo(idcargo);
--
-- Definition for index categoria_pkey (OID = 24224) : 
--
ALTER TABLE ONLY categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (idcategoria);
--
-- Definition for index empleado_fk2 (OID = 24226) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk2 FOREIGN KEY (categoria) REFERENCES categoria(idcategoria);
--
-- Definition for index cliente_pkey (OID = 24231) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (idcliente);
--
-- Definition for index pedido_fk4 (OID = 24233) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk4 FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index reclamocliente_fk1 (OID = 24238) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_fk1 FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index codigodebarra_pkey (OID = 24243) : 
--
ALTER TABLE ONLY codigodebarra
    ADD CONSTRAINT codigodebarra_pkey PRIMARY KEY (idcodigo);
--
-- Definition for index materiaprima_fk1 (OID = 24245) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk1 FOREIGN KEY (codbarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index productoreal_fk2 (OID = 24250) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk2 FOREIGN KEY (codigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index compra_pkey (OID = 24255) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_pkey PRIMARY KEY (idcompra);
--
-- Definition for index detallecompra_fk (OID = 24257) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk FOREIGN KEY (idcompra) REFERENCES compra(idcompra);
--
-- Definition for index reclamoproveedor_fk1 (OID = 24262) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_fk1 FOREIGN KEY (compra) REFERENCES compra(idcompra);
--
-- Definition for index comprobantepago_pkey (OID = 24267) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_pkey PRIMARY KEY (idcomprobantepago);
--
-- Definition for index condicioniva_pkey (OID = 24269) : 
--
ALTER TABLE ONLY condicioniva
    ADD CONSTRAINT condicioniva_pkey PRIMARY KEY (idcondicioniva);
--
-- Definition for index cliente_fk5 (OID = 24271) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk5 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index empresametalurgica_fk2 (OID = 24276) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk2 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index proveedormantenimientomaquina_fk2 (OID = 24281) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk2 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index proveedor_fk2 (OID = 24286) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk2 FOREIGN KEY (condicion) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index detallecompra_idx (OID = 24291) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_idx PRIMARY KEY (iddetalle, idcompra);
--
-- Definition for index detallereclamoproveedor_fk1 (OID = 24293) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_fk1 FOREIGN KEY (iddetallecompra, idcompra) REFERENCES detallecompra(iddetalle, idcompra);
--
-- Definition for index detallecompra_iddetalle_key (OID = 24298) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleejecucionplanificacioncalidad_idx (OID = 24300) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_idx PRIMARY KEY (iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad);
--
-- Definition for index detalleplanificacioncalidad_fk1 (OID = 24302) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk1 FOREIGN KEY (idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, idejecucionplanificacioncalidad) REFERENCES detalleejecucionplanificacioncalidad(iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad);
--
-- Definition for index detalleejecucionplanificacioncalidad_iddetalle_key (OID = 24307) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallefactura_idx (OID = 24309) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_idx PRIMARY KEY (iddetalle, idfactura);
--
-- Definition for index detallefactura_iddetalle_key (OID = 24311) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallemantenimientocorrectivo_pkey (OID = 24313) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_pkey PRIMARY KEY (idmantenimientocorrectivo, iddetalle);
--
-- Definition for index detallemantenimientocorrectivo_idmantenimientocorrectivo_key (OID = 24315) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_idmantenimientocorrectivo_key UNIQUE (idmantenimientocorrectivo);
--
-- Definition for index detallemantenimientopreventivo_pkey (OID = 24317) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_pkey PRIMARY KEY (idmantenimientopreventivo, iddetalle);
--
-- Definition for index detallemantenimientopreventivo_idmantenimientopreventivo_key (OID = 24319) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_idmantenimientopreventivo_key UNIQUE (idmantenimientopreventivo);
--
-- Definition for index detalleplanificacioncalidad_idx (OID = 24321) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_idx PRIMARY KEY (iddetalle, idplanificacioncalidad);
--
-- Definition for index detalleplanificacioncalidad_iddetalle_key (OID = 24323) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleplanprocedimientos_idx (OID = 24325) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_idx PRIMARY KEY (iddetalle, idplanpprocedimientos);
--
-- Definition for index detalleplanprocedimientos_iddetalle_key (OID = 24327) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleplanprocesoscalidad_idx (OID = 24329) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_idx PRIMARY KEY (iddetalle, idplanprocesoscalidad);
--
-- Definition for index detalleplanprocesoscalidad_iddetalle_key (OID = 24331) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleproductoreal_idx (OID = 24333) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_idx PRIMARY KEY (iddetalle, idproductoreal);
--
-- Definition for index detalleproductoreal_iddetalle_key (OID = 24335) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamocliente_idx (OID = 24337) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamocliente_iddetalle_key (OID = 24339) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamoempresametalurgica_idx (OID = 24341) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamoempresametalurgica_iddetalle_key (OID = 24343) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamoproveedor_idx (OID = 24345) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamoproveedor_iddetalle_key (OID = 24347) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleremito_idx (OID = 24349) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_idx PRIMARY KEY (iddetalle, idremito);
--
-- Definition for index detalleremito_iddetalle_key (OID = 24351) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallerequerimientosmateriaprima_idx (OID = 24353) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_idx PRIMARY KEY (iddetalle, idplanrequerimientosmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_iddetalle_key (OID = 24355) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalletrabajotercerizado_idx (OID = 24357) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_idx PRIMARY KEY (iddetalle, idtrabajotercerizado);
--
-- Definition for index detalletrabajotercerizado_iddetalle_key (OID = 24359) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index domicilio_pkey (OID = 24361) : 
--
ALTER TABLE ONLY domicilio
    ADD CONSTRAINT domicilio_pkey PRIMARY KEY (iddomicilio);
--
-- Definition for index cliente_fk4 (OID = 24363) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk4 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index empresametalurgica_fk1 (OID = 24368) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index responsable_fk (OID = 24373) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_fk FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index proveedormantenimientomaquina_fk1 (OID = 24378) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index empleado_fk (OID = 24383) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index proveedor_fk1 (OID = 24388) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index ejecucionetapaproduccion_nroejecucion_key1 (OID = 24393) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_nroejecucion_key1 UNIQUE (nroejecucion);
--
-- Definition for index ejecucionplanificacioncalidad_idejecucion_key (OID = 24395) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_idejecucion_key UNIQUE (idejecucion);
--
-- Definition for index ejecucionplanificacioncalidad_pkey (OID = 24397) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_pkey PRIMARY KEY (idejecucion, idplanificacioncalidad);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk (OID = 24399) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk FOREIGN KEY (idejecucionplanificacioncalidad, idplanificacioncalidad) REFERENCES ejecucionplanificacioncalidad(idejecucion, idplanificacioncalidad);
--
-- Definition for index ejecucionprocesocalidad_nroejecucion_key (OID = 24404) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_nroejecucion_key UNIQUE (idejecucion);
--
-- Definition for index ejecucionprocesocalidad_pkey (OID = 24406) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_pkey PRIMARY KEY (idejecucion, idprocesocalidad);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk1 (OID = 24408) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk1 FOREIGN KEY (ejecucionprocesocalidad, idprocesocalidad) REFERENCES ejecucionprocesocalidad(idejecucion, idprocesocalidad);
--
-- Definition for index empleado_pkey (OID = 24413) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (idempleado);
--
-- Definition for index mantenimientocorrectivo_fk (OID = 24415) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_fk FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index ejecucionetapaproduccion_fk1 (OID = 24420) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk1 FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index detallaplanificacionproduccion_fk3 (OID = 24425) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk3 FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index empleadoxturno_fk (OID = 24430) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_fk FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index empresametalurgica_pkey (OID = 24435) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_pkey PRIMARY KEY (idempresametalurgica);
--
-- Definition for index trabajotercerizado_fk1 (OID = 24437) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk1 FOREIGN KEY (empresa) REFERENCES empresametalurgica(idempresametalurgica);
--
-- Definition for index estadocliente_pkey (OID = 24442) : 
--
ALTER TABLE ONLY estadocliente
    ADD CONSTRAINT estadocliente_pkey PRIMARY KEY (idestado);
--
-- Definition for index cliente_fk1 (OID = 24444) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk1 FOREIGN KEY (estado) REFERENCES estadocliente(idestado);
--
-- Definition for index estadocompra_pkey (OID = 24449) : 
--
ALTER TABLE ONLY estadocompra
    ADD CONSTRAINT estadocompra_pkey PRIMARY KEY (idestado);
--
-- Definition for index compra_fk (OID = 24451) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_fk FOREIGN KEY (estado) REFERENCES estadocompra(idestado);
--
-- Definition for index estadodetallecompra_pkey (OID = 24456) : 
--
ALTER TABLE ONLY estadodetallecompra
    ADD CONSTRAINT estadodetallecompra_pkey PRIMARY KEY (idestado);
--
-- Definition for index detallecompra_fk2 (OID = 24458) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk2 FOREIGN KEY (estado) REFERENCES estadodetallecompra(idestado);
--
-- Definition for index estadodetalletrabajotercerizado_pkey (OID = 24463) : 
--
ALTER TABLE ONLY estadodetalletrabajotercerizado
    ADD CONSTRAINT estadodetalletrabajotercerizado_pkey PRIMARY KEY (idestado);
--
-- Definition for index detalletrabajotercerizado_fk3 (OID = 24465) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk3 FOREIGN KEY (estado) REFERENCES estadodetalletrabajotercerizado(idestado);
--
-- Definition for index estadoejecetapaprod_pkey (OID = 24470) : 
--
ALTER TABLE ONLY estadoejecetapaprod
    ADD CONSTRAINT estadoejecetapaprod_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionetapaproduccion_fk2 (OID = 24472) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk2 FOREIGN KEY (estado) REFERENCES estadoejecetapaprod(idestado);
--
-- Definition for index estadoejecplancalidad_pkey (OID = 24477) : 
--
ALTER TABLE ONLY estadoejecplancalidad
    ADD CONSTRAINT estadoejecplancalidad_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionplanificacioncalidad_fk1 (OID = 24479) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_fk1 FOREIGN KEY (estado) REFERENCES estadoejecplancalidad(idestado);
--
-- Definition for index estadoejecplanifpedido_pkey (OID = 24484) : 
--
ALTER TABLE ONLY estadoejecplanifpedido
    ADD CONSTRAINT estadoejecplanifpedido_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionplanificacionproduccion_fk1 (OID = 24486) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_fk1 FOREIGN KEY (estado) REFERENCES estadoejecplanifpedido(idestado);
--
-- Definition for index estadoejecucionprocesocalidad_pkey (OID = 24491) : 
--
ALTER TABLE ONLY estadoejecucionprocesocalidad
    ADD CONSTRAINT estadoejecucionprocesocalidad_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionprocesocalidad_fk1 (OID = 24493) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_fk1 FOREIGN KEY (estado) REFERENCES estadoejecucionprocesocalidad(idestado);
--
-- Definition for index estadofactura_pkey (OID = 24498) : 
--
ALTER TABLE ONLY estadofactura
    ADD CONSTRAINT estadofactura_pkey PRIMARY KEY (idestado);
--
-- Definition for index factura_fk3 (OID = 24500) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk3 FOREIGN KEY (estado) REFERENCES estadofactura(idestado);
--
-- Definition for index estadomaquina_pkey (OID = 24505) : 
--
ALTER TABLE ONLY estadomaquina
    ADD CONSTRAINT estadomaquina_pkey PRIMARY KEY (idestado);
--
-- Definition for index estadopedido_pkey (OID = 24507) : 
--
ALTER TABLE ONLY estadopedido
    ADD CONSTRAINT estadopedido_pkey PRIMARY KEY (idestado);
--
-- Definition for index pedido_fk (OID = 24509) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk FOREIGN KEY (estado) REFERENCES estadopedido(idestado);
--
-- Definition for index estadopiezareal_pkey (OID = 24514) : 
--
ALTER TABLE ONLY estadopiezareal
    ADD CONSTRAINT estadopiezareal_pkey PRIMARY KEY (idestado);
--
-- Definition for index estadoproductoreal_pkey (OID = 24516) : 
--
ALTER TABLE ONLY estadoproductoreal
    ADD CONSTRAINT estadoproductoreal_pkey PRIMARY KEY (idestado);
--
-- Definition for index productoreal_fk3 (OID = 24518) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk3 FOREIGN KEY (estado) REFERENCES estadoproductoreal(idestado);
--
-- Definition for index estadoremito_pkey (OID = 24523) : 
--
ALTER TABLE ONLY estadoremito
    ADD CONSTRAINT estadoremito_pkey PRIMARY KEY (idestado);
--
-- Definition for index remito_fk1 (OID = 24525) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_fk1 FOREIGN KEY (estado) REFERENCES estadoremito(idestado);
--
-- Definition for index estadotrabajotercerizado_pkey (OID = 24530) : 
--
ALTER TABLE ONLY estadotrabajotercerizado
    ADD CONSTRAINT estadotrabajotercerizado_pkey PRIMARY KEY (idestado);
--
-- Definition for index trabajotercerizado_fk2 (OID = 24532) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk2 FOREIGN KEY (estado) REFERENCES estadotrabajotercerizado(idestado);
--
-- Definition for index etapadeproduccion_pkey (OID = 24537) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_pkey PRIMARY KEY (idetapaproduccion);
--
-- Definition for index ejecucionetapaproduccion_fk (OID = 24539) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detalletrabajotercerizado_fk2 (OID = 24544) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk2 FOREIGN KEY (proceso) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index piezaxetapadeproduccion_fk1 (OID = 24549) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT piezaxetapadeproduccion_fk1 FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detalleplanprocedimientos_fk2 (OID = 24554) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_fk2 FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index factura_pkey (OID = 24559) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_pkey PRIMARY KEY (idfactura);
--
-- Definition for index pedido_fk1 (OID = 24561) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk1 FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index comprobantepago_fk2 (OID = 24566) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk2 FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index detallefactura_fk (OID = 24571) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_fk FOREIGN KEY (idfactura) REFERENCES factura(idfactura);
--
-- Definition for index formadepago_pkey (OID = 24576) : 
--
ALTER TABLE ONLY formadepago
    ADD CONSTRAINT formadepago_pkey PRIMARY KEY (idformapago);
--
-- Definition for index factura_fk1 (OID = 24578) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk1 FOREIGN KEY (formapago) REFERENCES formadepago(idformapago);
--
-- Definition for index comprobantepago_fk (OID = 24583) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk FOREIGN KEY (formadepago) REFERENCES formadepago(idformapago);
--
-- Definition for index localidad_pkey (OID = 24588) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT localidad_pkey PRIMARY KEY (idlocalidad);
--
-- Definition for index barrio_fk (OID = 24590) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT barrio_fk FOREIGN KEY (localidad) REFERENCES localidad(idlocalidad);
--
-- Definition for index mantenimientocorrectivo_pkey (OID = 24595) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_pkey PRIMARY KEY (idmantenimientocorrectivo);
--
-- Definition for index detallemantenimientocorrectivo_fk1 (OID = 24597) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_fk1 FOREIGN KEY (idmantenimientocorrectivo) REFERENCES mantenimientocorrectivo(idmantenimientocorrectivo);
--
-- Definition for index mantenimientopreventivo_pkey (OID = 24602) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_pkey PRIMARY KEY (idmantenimientopreventivo);
--
-- Definition for index detallemantenimientopreventivo_fk (OID = 24604) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_fk FOREIGN KEY (idmantenimientopreventivo) REFERENCES mantenimientopreventivo(idmantenimientopreventivo);
--
-- Definition for index marca_pkey (OID = 24609) : 
--
ALTER TABLE ONLY marca
    ADD CONSTRAINT marca_pkey PRIMARY KEY (idmarca);
--
-- Definition for index materiaprima_pkey (OID = 24611) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_pkey PRIMARY KEY (idmateriaprima);
--
-- Definition for index matriz_fk (OID = 24613) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT matriz_fk FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallecompra_fk1 (OID = 24618) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk1 FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index proveedorxmateriaprima_fk1 (OID = 24623) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_fk1 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_fk2 (OID = 24628) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_fk2 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index matriz_pkey (OID = 24633) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT matriz_pkey PRIMARY KEY (idmatriz);
--
-- Definition for index pedidomatriz_fk (OID = 24635) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT pedidomatriz_fk FOREIGN KEY (idmatriz) REFERENCES matriz(idmatriz);
--
-- Definition for index pedido_pkey (OID = 24640) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (idpedido);
--
-- Definition for index plano_fk (OID = 24642) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT plano_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index remito_fk (OID = 24647) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index trabajotercerizado_fk (OID = 24652) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index planificacionproduccion_fk (OID = 24657) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index planificacioncalidad_fk (OID = 24662) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT planificacioncalidad_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index detallepedido_fk (OID = 24667) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_fk FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index productoreal_fk1 (OID = 24672) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk1 FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index detallefactura_fk1 (OID = 24677) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_fk1 FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index pedidomatriz_idx (OID = 24682) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT pedidomatriz_idx PRIMARY KEY (idpedidomatriz);
--
-- Definition for index planificacioncalidad_pkey (OID = 24684) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT planificacioncalidad_pkey PRIMARY KEY (idplanificacion);
--
-- Definition for index ejecucionplanificacioncalidad_fk (OID = 24686) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_fk FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index detalleplanificacioncalidad_fk (OID = 24691) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index planificacionproduccion_pkey (OID = 24696) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_pkey PRIMARY KEY (idplanificacionproduccion);
--
-- Definition for index ejecucionplanificacionproduccion_fk (OID = 24698) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_fk FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index detallaplanificacionproduccion_fk (OID = 24703) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index plano_pkey (OID = 24708) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT plano_pkey PRIMARY KEY (idplano);
--
-- Definition for index pedido_fk3 (OID = 24710) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk3 FOREIGN KEY (plano) REFERENCES plano(idplano);
--
-- Definition for index planprocedimientos_pkey (OID = 24715) : 
--
ALTER TABLE ONLY planprocedimientos
    ADD CONSTRAINT planprocedimientos_pkey PRIMARY KEY (idplanprocedimientos);
--
-- Definition for index detalleplanprocedimientos_fk (OID = 24717) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_fk FOREIGN KEY (idplanpprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index pedido_fk5 (OID = 24722) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk5 FOREIGN KEY (planprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index planprocesoscalidad_pkey (OID = 24727) : 
--
ALTER TABLE ONLY planprocesoscalidad
    ADD CONSTRAINT planprocesoscalidad_pkey PRIMARY KEY (idplanprocesoscalidad);
--
-- Definition for index detalleplanprocesoscalidad_fk (OID = 24729) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_fk FOREIGN KEY (idplanprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index pedido_fk7 (OID = 24734) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk7 FOREIGN KEY (planprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index planrequerimientosmateriaprima_pkey (OID = 24739) : 
--
ALTER TABLE ONLY planrequerimientosmateriaprima
    ADD CONSTRAINT planrequerimientosmateriaprima_pkey PRIMARY KEY (idplanrequerimientosmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_fk (OID = 24741) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_fk FOREIGN KEY (idplanrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index pedido_fk6 (OID = 24746) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk6 FOREIGN KEY (planrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index presupuesto_pkey (OID = 24751) : 
--
ALTER TABLE ONLY presupuesto
    ADD CONSTRAINT presupuesto_pkey PRIMARY KEY (idpresupuesto);
--
-- Definition for index pedido_fk2 (OID = 24753) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk2 FOREIGN KEY (presupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index detallepresupuesto_fk (OID = 24758) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_fk FOREIGN KEY (idpresupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index prioridad_pkey (OID = 24763) : 
--
ALTER TABLE ONLY prioridad
    ADD CONSTRAINT prioridad_pkey PRIMARY KEY (idprioridad);
--
-- Definition for index cliente_fk (OID = 24765) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index privilegio_pkey (OID = 24770) : 
--
ALTER TABLE ONLY privilegio
    ADD CONSTRAINT privilegio_pkey PRIMARY KEY (idprivilegio);
--
-- Definition for index rolxprivilegio_fk (OID = 24772) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_fk FOREIGN KEY (idprivilegio) REFERENCES privilegio(idprivilegio);
--
-- Definition for index procesocalidad_pkey (OID = 24777) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT procesocalidad_pkey PRIMARY KEY (idprocesocalidad);
--
-- Definition for index ejecucionprocesocalidad_fk (OID = 24779) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_fk FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detalleplanificacioncalidad_fk2 (OID = 24784) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk2 FOREIGN KEY (procesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index maquinaxprocesocalidad_fk (OID = 24789) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT maquinaxprocesocalidad_fk FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detalleplanprocesoscalidad_fk2 (OID = 24794) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_fk2 FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index producto_pkey (OID = 24799) : 
--
ALTER TABLE ONLY producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (idproducto);
--
-- Definition for index detalleproducto_fk (OID = 24801) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_fk FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index detallepedido_fk1 (OID = 24806) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detalleremito_fk1 (OID = 24811) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detallereclamocliente_fk1 (OID = 24816) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detallepresupuesto_fk2 (OID = 24821) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_fk2 FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index productoreal_idx (OID = 24826) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_idx PRIMARY KEY (idproductoreal);
--
-- Definition for index detalleproductoreal_fk (OID = 24828) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_fk FOREIGN KEY (idproductoreal) REFERENCES productoreal(idproductoreal);
--
-- Definition for index proveedor_pkey (OID = 24833) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_pkey PRIMARY KEY (idproveedor);
--
-- Definition for index compra_fk1 (OID = 24835) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_fk1 FOREIGN KEY (proveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index proveedorxmateriaprima_fk (OID = 24840) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_fk FOREIGN KEY (idproveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index proveedormantenimientomaquina_pkey (OID = 24845) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_pkey PRIMARY KEY (idproveedormantenimiento);
--
-- Definition for index mantenimientopreventivo_fk (OID = 24847) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_fk FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index mantenimientocorrectivo_fk1 (OID = 24852) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_fk1 FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index provincia_pkey (OID = 24857) : 
--
ALTER TABLE ONLY provincia
    ADD CONSTRAINT provincia_pkey PRIMARY KEY (idprovincia);
--
-- Definition for index localidad_fk (OID = 24859) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT localidad_fk FOREIGN KEY (provincia) REFERENCES provincia(idprovincia);
--
-- Definition for index reclamocliente_idx (OID = 24864) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_idx PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamocliente_fk (OID = 24866) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_fk FOREIGN KEY (idreclamo) REFERENCES reclamocliente(idreclamo);
--
-- Definition for index reclamoempresametalurgica_pkey (OID = 24871) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_pkey PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamoempresametalurgica_fk (OID = 24873) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_fk FOREIGN KEY (idreclamo) REFERENCES reclamoempresametalurgica(idreclamo);
--
-- Definition for index reclamoproveedor_idx (OID = 24878) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_idx PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamoproveedor_fk (OID = 24880) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_fk FOREIGN KEY (idreclamo) REFERENCES reclamoproveedor(idreclamo);
--
-- Definition for index remito_pkey (OID = 24885) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_pkey PRIMARY KEY (idremito);
--
-- Definition for index detalleremito_fk (OID = 24887) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_fk FOREIGN KEY (idremito) REFERENCES remito(idremito);
--
-- Definition for index responsable_pkey (OID = 24892) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_pkey PRIMARY KEY (idresponsable);
--
-- Definition for index cliente_fk3 (OID = 24894) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk3 FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index empresametalurgica_fk (OID = 24899) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index proveedormantenimientomaquina_fk (OID = 24904) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index proveedor_fk (OID = 24909) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index rol_pkey (OID = 24914) : 
--
ALTER TABLE ONLY rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (idrol);
--
-- Definition for index rolxprivilegio_fk1 (OID = 24916) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_fk1 FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index usuarioxrol_fk (OID = 24921) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_fk FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index rotura_pkey (OID = 24926) : 
--
ALTER TABLE ONLY rotura
    ADD CONSTRAINT rotura_pkey PRIMARY KEY (idrotura);
--
-- Definition for index detallemantenimientocorrectivo_fk (OID = 24928) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_fk FOREIGN KEY (rotura) REFERENCES rotura(idrotura);
--
-- Definition for index servicio_pkey (OID = 24933) : 
--
ALTER TABLE ONLY servicio
    ADD CONSTRAINT servicio_pkey PRIMARY KEY (idservicio);
--
-- Definition for index detallemantenimientopreventivo_fk1 (OID = 24935) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_fk1 FOREIGN KEY (servicio) REFERENCES servicio(idservicio);
--
-- Definition for index sesion_pkey (OID = 24940) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT sesion_pkey PRIMARY KEY (idsesion);
--
-- Definition for index tipodocumento_pkey (OID = 24942) : 
--
ALTER TABLE ONLY tipodocumento
    ADD CONSTRAINT tipodocumento_pkey PRIMARY KEY (idtipodocumento);
--
-- Definition for index responsable_fk1 (OID = 24944) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_fk1 FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index empleado_fk1 (OID = 24949) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk1 FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index tipoiva_pkey (OID = 24954) : 
--
ALTER TABLE ONLY tipoiva
    ADD CONSTRAINT tipoiva_pkey PRIMARY KEY (idtipoiva);
--
-- Definition for index factura_fk (OID = 24956) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk FOREIGN KEY (tipoiva) REFERENCES tipoiva(idtipoiva);
--
-- Definition for index tipomaquina_pkey (OID = 24961) : 
--
ALTER TABLE ONLY tipomaquina
    ADD CONSTRAINT tipomaquina_pkey PRIMARY KEY (idtipomaquina);
--
-- Definition for index tiporeclamo_pkey (OID = 24963) : 
--
ALTER TABLE ONLY tiporeclamo
    ADD CONSTRAINT tiporeclamo_pkey PRIMARY KEY (idtiporeclamo);
--
-- Definition for index reclamoempresametalurgica_fk (OID = 24965) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index reclamoproveedor_fk (OID = 24970) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index reclamocliente_fk (OID = 24975) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index trabajotercerizado_pkey (OID = 24980) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_pkey PRIMARY KEY (idtrabajo);
--
-- Definition for index detalletrabajotercerizado_fk (OID = 24982) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk FOREIGN KEY (idtrabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index reclamoempresametalurgica_fk1 (OID = 24987) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_fk1 FOREIGN KEY (trabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index turno_pkey (OID = 24992) : 
--
ALTER TABLE ONLY turno
    ADD CONSTRAINT turno_pkey PRIMARY KEY (idturno);
--
-- Definition for index empleadoxturno_fk1 (OID = 24994) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_fk1 FOREIGN KEY (idturno) REFERENCES turno(idturno);
--
-- Definition for index materiaprima_fk (OID = 24999) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk FOREIGN KEY (tipomaterial) REFERENCES tipomaterial(idtipomaterial);
--
-- Definition for index detalleproducto_iddetalle_key (OID = 25004) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_iddetalle_key PRIMARY KEY (iddetalle);
ALTER TABLE detalleproducto CLUSTER ON detalleproducto_iddetalle_key;
--
-- Definition for index pedido_fk8 (OID = 25006) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk8 FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index unidadmedida_pkey (OID = 25011) : 
--
ALTER TABLE ONLY unidadmedida
    ADD CONSTRAINT unidadmedida_pkey PRIMARY KEY (idunidadmedida);
--
-- Definition for index etapadeproduccion_fk1 (OID = 25013) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_fk1 FOREIGN KEY (unidaddemedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index materiaprima_fk2 (OID = 25018) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk2 FOREIGN KEY (unidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index detalleproductopresupuesto_pkey (OID = 25023) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT detalleproductopresupuesto_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detallepiezapresupuesto_pkey (OID = 25025) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT detallepiezapresupuesto_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detallepiezapresupuesto_fk (OID = 25027) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT detallepiezapresupuesto_fk FOREIGN KEY (iddetalleproductopresupuesto) REFERENCES detalleproductopresupuesto(iddetalle);
--
-- Definition for index detallepiezapresupuesto_fk1 (OID = 25032) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT detallepiezapresupuesto_fk1 FOREIGN KEY (idetapa) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detallepresupuesto_pkey (OID = 25037) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detallepedido_pkey (OID = 25039) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detalleproductopresupuesto_fk1 (OID = 25041) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT detalleproductopresupuesto_fk1 FOREIGN KEY (iddetallepresupuesto) REFERENCES detallepresupuesto(iddetalle);
--
-- Definition for index detalleproductopresupuesto_fk2 (OID = 25046) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT detalleproductopresupuesto_fk2 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallepiezacalidadpresupuesto_pkey (OID = 25051) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT detallepiezacalidadpresupuesto_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detallepiezacalidadpresupuesto_fk (OID = 25053) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT detallepiezacalidadpresupuesto_fk FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detallepiezacalidadpresupuesto_fk1 (OID = 25058) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT detallepiezacalidadpresupuesto_fk1 FOREIGN KEY (iddetalleproductopresupuesto) REFERENCES detalleproductopresupuesto(iddetalle);
--
-- Definition for index calendario_pkey (OID = 25063) : 
--
ALTER TABLE ONLY calendario
    ADD CONSTRAINT calendario_pkey PRIMARY KEY (id);
--
-- Definition for index disponibilidadhoraria_pkey (OID = 25065) : 
--
ALTER TABLE ONLY disponibilidadhoraria
    ADD CONSTRAINT disponibilidadhoraria_pkey PRIMARY KEY (id);
--
-- Definition for index estadoplanificacionproduccion_pkey (OID = 25067) : 
--
ALTER TABLE ONLY estadoplanificacionproduccion
    ADD CONSTRAINT estadoplanificacionproduccion_pkey PRIMARY KEY (id);
--
-- Definition for index planificacionproduccion_fk1 (OID = 25069) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_fk1 FOREIGN KEY (idestado) REFERENCES estadoplanificacionproduccion(id);
--
-- Definition for index disponibilidadhoraria_fk (OID = 25074) : 
--
ALTER TABLE ONLY disponibilidadhoraria
    ADD CONSTRAINT disponibilidadhoraria_fk FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index detalleplanificacionproduccion_pkey (OID = 25079) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_pkey PRIMARY KEY (id);
--
-- Definition for index detalleplanificacionproduccion_fk (OID = 25081) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index maquina_pkey (OID = 25086) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_pkey PRIMARY KEY (idmaquina);
--
-- Definition for index maquina_fk (OID = 25088) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk FOREIGN KEY (marca) REFERENCES marca(idmarca);
--
-- Definition for index maquina_fk1 (OID = 25093) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk1 FOREIGN KEY (estado) REFERENCES estadomaquina(idestado);
--
-- Definition for index maquina_fk2 (OID = 25098) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk2 FOREIGN KEY (tipomaquina) REFERENCES tipomaquina(idtipomaquina);
--
-- Definition for index maquina_fk3 (OID = 25103) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk3 FOREIGN KEY (idunidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index piezareal_fk1 (OID = 25108) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_fk1 FOREIGN KEY (estado) REFERENCES estadopiezareal(idestado);
--
-- Definition for index piezareal_fk2 (OID = 25113) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_fk2 FOREIGN KEY (idcodigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index pieza_pkey (OID = 25118) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_pkey PRIMARY KEY (idpieza);
--
-- Definition for index pieza_fk (OID = 25120) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk FOREIGN KEY (unidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index pieza_fk1 (OID = 25125) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk1 FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index pieza_fk2 (OID = 25130) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk2 FOREIGN KEY (matriz) REFERENCES matriz(idmatriz);
--
-- Definition for index detallempasignada_pkey (OID = 25135) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT detallempasignada_pkey PRIMARY KEY (id);
--
-- Definition for index detallempasignada_fk (OID = 25137) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT detallempasignada_fk FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallempasignada_fk1 (OID = 25142) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT detallempasignada_fk1 FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index mpasignadaxpiezareal_pkey (OID = 25147) : 
--
ALTER TABLE ONLY mpasignadaxpiezareal
    ADD CONSTRAINT mpasignadaxpiezareal_pkey PRIMARY KEY (id);
--
-- Definition for index mpasignadaxpiezareal_fk1 (OID = 25149) : 
--
ALTER TABLE ONLY mpasignadaxpiezareal
    ADD CONSTRAINT mpasignadaxpiezareal_fk1 FOREIGN KEY (iddetallempasignada) REFERENCES detallempasignada(id);
--
-- Definition for index asistencia_idx (OID = 25154) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_idx PRIMARY KEY (empleado, horaingreso, fechaingreso);
--
-- Definition for index asistencia_fk (OID = 25156) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_fk FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index piezareal_idpiezareal_key (OID = 25161) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_idpiezareal_key PRIMARY KEY (idpiezareal);
--
-- Definition for index detalleplanificacionproduccion_fk1 (OID = 25163) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk1 FOREIGN KEY (idmaquina) REFERENCES maquina(idmaquina);
--
-- Definition for index detalleplanificacionproduccion_fk2 (OID = 25168) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk2 FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleproductopresupuesto_fk (OID = 25173) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT detalleproductopresupuesto_fk FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index ejecucionplanificacionproduccion_idejecucion_key (OID = 25178) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_idejecucion_key PRIMARY KEY (idejecucion);
--
-- Definition for index detalleejecucionplanificacion_iddetalle_key (OID = 25180) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_iddetalle_key PRIMARY KEY (id);
--
-- Definition for index detalleplanificacionproduccion_fk3 (OID = 25182) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk3 FOREIGN KEY (iddetalleejecucionplanificacion) REFERENCES detalleejecucionplanificacion(id);
--
-- Definition for index fk_responsable_domicilio (OID = 25187) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT fk_responsable_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_responsable_tipodocumento (OID = 25192) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT fk_responsable_tipodocumento FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index fk_procesocalidad_accioncalidad (OID = 25197) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT fk_procesocalidad_accioncalidad FOREIGN KEY (accioncalidad) REFERENCES accioncalidad(idaccioncalidad);
--
-- Definition for index fk_detallecompra_estado (OID = 25202) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT fk_detallecompra_estado FOREIGN KEY (estado) REFERENCES estadodetallecompra(idestado);
--
-- Definition for index fk_detallecompra_materiaprima (OID = 25207) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT fk_detallecompra_materiaprima FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detallecompra_idcompra (OID = 25212) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT fk_detallecompra_idcompra FOREIGN KEY (idcompra) REFERENCES compra(idcompra);
--
-- Definition for index fk_reclamoproveedor_tiporeclamo (OID = 25217) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT fk_reclamoproveedor_tiporeclamo FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index fk_reclamoproveedor_compra (OID = 25222) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT fk_reclamoproveedor_compra FOREIGN KEY (compra) REFERENCES compra(idcompra);
--
-- Definition for index fk_empresametalurgica_condicioniva (OID = 25227) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT fk_empresametalurgica_condicioniva FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index fk_empresametalurgica_domicilio (OID = 25232) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT fk_empresametalurgica_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_empresametalurgica_responsable (OID = 25237) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT fk_empresametalurgica_responsable FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index fk_detalleplanprocesoscalidad_idprocesocalidad (OID = 25242) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT fk_detalleplanprocesoscalidad_idprocesocalidad FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index fk_detalleplanprocesoscalidad_idplanprocesoscalidad (OID = 25247) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT fk_detalleplanprocesoscalidad_idplanprocesoscalidad FOREIGN KEY (idplanprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index fk_detalleremito_idremito (OID = 25252) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT fk_detalleremito_idremito FOREIGN KEY (idremito) REFERENCES remito(idremito);
--
-- Definition for index fk_detalleremito_producto (OID = 25257) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT fk_detalleremito_producto FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index fk_detalleplanificacionproduccion_idpieza (OID = 25262) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idpieza FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index fk_detalleplanificacionproduccion_idetapaproduccion (OID = 25267) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idetapaproduccion FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_detalleplanificacionproduccion_idempleado (OID = 25272) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idempleado FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index detalleplanificacionproduccion_iddetalleejecucionplanificacion (OID = 25277) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_iddetalleejecucionplanificacion FOREIGN KEY (iddetalleejecucionplanificacion) REFERENCES detalleejecucionplanificacion(id);
--
-- Definition for index fk_detalleplanificacionproduccion_idmaquina (OID = 25282) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idmaquina FOREIGN KEY (idmaquina) REFERENCES maquina(idmaquina);
--
-- Definition for index fk_detalleplanificacionproduccion_idplanificacionproduccion (OID = 25287) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idplanificacionproduccion FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index fk_ejecucionplanificacioncalidad_estado (OID = 25292) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT fk_ejecucionplanificacioncalidad_estado FOREIGN KEY (estado) REFERENCES estadoejecplancalidad(idestado);
--
-- Definition for index fk_ejecucionplanificacioncalidad_idplanificacioncalidad (OID = 25297) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT fk_ejecucionplanificacioncalidad_idplanificacioncalidad FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index fk_planificacionproduccion_pedido (OID = 25302) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT fk_planificacionproduccion_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_planificacionproduccion_idestado (OID = 25307) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT fk_planificacionproduccion_idestado FOREIGN KEY (idestado) REFERENCES estadoplanificacionproduccion(id);
--
-- Definition for index fk_pedido_estado (OID = 25312) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_estado FOREIGN KEY (estado) REFERENCES estadopedido(idestado);
--
-- Definition for index fk_pedido_planprocesoscalidad (OID = 25317) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_planprocesoscalidad FOREIGN KEY (planprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index fk_pedido_presupuesto (OID = 25322) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_presupuesto FOREIGN KEY (presupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index fk_pedido_planrequerimientosmateriaprima (OID = 25327) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_planrequerimientosmateriaprima FOREIGN KEY (planrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index fk_pedido_planprocedimientos (OID = 25332) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_planprocedimientos FOREIGN KEY (planprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index fk_pedido_plano (OID = 25337) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_plano FOREIGN KEY (plano) REFERENCES plano(idplano);
--
-- Definition for index fk_pedido_prioridad (OID = 25342) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_prioridad FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index fk_pedido_cliente (OID = 25347) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index fk_pedido_factura (OID = 25352) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_factura FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index fk_empleadoxturno_idempleado (OID = 25357) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT fk_empleadoxturno_idempleado FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_empleadoxturno_idturno (OID = 25362) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT fk_empleadoxturno_idturno FOREIGN KEY (idturno) REFERENCES turno(idturno);
--
-- Definition for index fk_detallereclamoempresametalurgica_idreclamo (OID = 25367) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT fk_detallereclamoempresametalurgica_idreclamo FOREIGN KEY (idreclamo) REFERENCES reclamoempresametalurgica(idreclamo);
--
-- Definition for index fk_ejecucionetapaproduccion_empleado (OID = 25372) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT fk_ejecucionetapaproduccion_empleado FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_ejecucionetapaproduccion_estado (OID = 25377) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT fk_ejecucionetapaproduccion_estado FOREIGN KEY (estado) REFERENCES estadoejecetapaprod(idestado);
--
-- Definition for index fk_ejecucionetapaproduccion_idetapaproduccion (OID = 25382) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT fk_ejecucionetapaproduccion_idetapaproduccion FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_compra_proveedor (OID = 25387) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT fk_compra_proveedor FOREIGN KEY (proveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index fk_compra_estado (OID = 25392) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT fk_compra_estado FOREIGN KEY (estado) REFERENCES estadocompra(idestado);
--
-- Definition for index fk_maquinaxprocesocalidad_idprocesocalidad (OID = 25397) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT fk_maquinaxprocesocalidad_idprocesocalidad FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index fk_productoreal_codigobarra (OID = 25402) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT fk_productoreal_codigobarra FOREIGN KEY (codigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index fk_productoreal_idpedido (OID = 25407) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT fk_productoreal_idpedido FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_productoreal_estado (OID = 25412) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT fk_productoreal_estado FOREIGN KEY (estado) REFERENCES estadoproductoreal(idestado);
--
-- Definition for index fk_materiaprima_unidadmedida (OID = 25417) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT fk_materiaprima_unidadmedida FOREIGN KEY (unidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index fk_materiaprima_tipomaterial (OID = 25422) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT fk_materiaprima_tipomaterial FOREIGN KEY (tipomaterial) REFERENCES tipomaterial(idtipomaterial);
--
-- Definition for index fk_materiaprima_codbarra (OID = 25427) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT fk_materiaprima_codbarra FOREIGN KEY (codbarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index fk_cliente_estado (OID = 25432) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_estado FOREIGN KEY (estado) REFERENCES estadocliente(idestado);
--
-- Definition for index fk_cliente_domicilio (OID = 25437) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_cliente_usuario (OID = 25442) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_cliente_condicioniva (OID = 25447) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_condicioniva FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index fk_cliente_responsable (OID = 25452) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_responsable FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index fk_cliente_prioridad (OID = 25457) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_prioridad FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index fk_pieza_matriz (OID = 25462) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT fk_pieza_matriz FOREIGN KEY (matriz) REFERENCES matriz(idmatriz);
--
-- Definition for index fk_pieza_unidadmedida (OID = 25467) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT fk_pieza_unidadmedida FOREIGN KEY (unidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index fk_pieza_materiaprima (OID = 25472) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT fk_pieza_materiaprima FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detallepiezacalidadpresupuesto_idprocesocalidad (OID = 25477) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT fk_detallepiezacalidadpresupuesto_idprocesocalidad FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index fk_detallepiezacalidadpresupuesto_iddetalleproductopresupuesto (OID = 25482) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT fk_detallepiezacalidadpresupuesto_iddetalleproductopresupuesto FOREIGN KEY (iddetalleproductopresupuesto) REFERENCES detalleproductopresupuesto(iddetalle);
--
-- Definition for index fk_detalleproductoreal_idproductoreal (OID = 25487) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT fk_detalleproductoreal_idproductoreal FOREIGN KEY (idproductoreal) REFERENCES productoreal(idproductoreal);
--
-- Definition for index fk_factura_tipoiva (OID = 25492) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_factura_tipoiva FOREIGN KEY (tipoiva) REFERENCES tipoiva(idtipoiva);
--
-- Definition for index fk_factura_formapago (OID = 25497) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_factura_formapago FOREIGN KEY (formapago) REFERENCES formadepago(idformapago);
--
-- Definition for index fk_factura_usuario (OID = 25502) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_factura_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_factura_estado (OID = 25507) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_factura_estado FOREIGN KEY (estado) REFERENCES estadofactura(idestado);
--
-- Definition for index fk_detallerequerimientosmateriaprima_idmateriaprima (OID = 25512) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT fk_detallerequerimientosmateriaprima_idmateriaprima FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index dtallerequerimientosmateriaprimadplanrequerimientosmateriaprima (OID = 25517) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT dtallerequerimientosmateriaprimadplanrequerimientosmateriaprima FOREIGN KEY (idplanrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index fk_plano_pedido (OID = 25522) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT fk_plano_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_usuarioxrol_idrol (OID = 25527) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT fk_usuarioxrol_idrol FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index fk_usuarioxrol_idusuario (OID = 25532) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT fk_usuarioxrol_idusuario FOREIGN KEY (idusuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_trabajotercerizado_empresa (OID = 25537) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT fk_trabajotercerizado_empresa FOREIGN KEY (empresa) REFERENCES empresametalurgica(idempresametalurgica);
--
-- Definition for index fk_trabajotercerizado_estado (OID = 25542) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT fk_trabajotercerizado_estado FOREIGN KEY (estado) REFERENCES estadotrabajotercerizado(idestado);
--
-- Definition for index fk_trabajotercerizado_pedido (OID = 25547) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT fk_trabajotercerizado_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_piezaxetapadeproduccion_idetapaproduccion (OID = 25552) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT fk_piezaxetapadeproduccion_idetapaproduccion FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_proveedor_responsable (OID = 25557) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT fk_proveedor_responsable FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index fk_proveedor_domicilio (OID = 25562) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT fk_proveedor_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_proveedor_condicion (OID = 25567) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT fk_proveedor_condicion FOREIGN KEY (condicion) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index fk_reclamoempresametalurgica_trabajotercerizado (OID = 25572) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT fk_reclamoempresametalurgica_trabajotercerizado FOREIGN KEY (trabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index fk_reclamoempresametalurgica_tiporeclamo (OID = 25577) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT fk_reclamoempresametalurgica_tiporeclamo FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index fk_matriz_materiaprima (OID = 25582) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT fk_matriz_materiaprima FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detalleproducto_idproducto (OID = 25587) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT fk_detalleproducto_idproducto FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index fk_mantenimientocorrectivo_empleado (OID = 25592) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT fk_mantenimientocorrectivo_empleado FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_mantenimientocorrectivo_proveedormantenimiento (OID = 25597) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT fk_mantenimientocorrectivo_proveedormantenimiento FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index fk_piezareal_estado (OID = 25602) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT fk_piezareal_estado FOREIGN KEY (estado) REFERENCES estadopiezareal(idestado);
--
-- Definition for index fk_piezareal_idcodigobarra (OID = 25607) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT fk_piezareal_idcodigobarra FOREIGN KEY (idcodigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index fk_detallempasignada_idmateriaprima (OID = 25612) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT fk_detallempasignada_idmateriaprima FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detallempasignada_idplanificacionproduccion (OID = 25617) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT fk_detallempasignada_idplanificacionproduccion FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index fk_ejecucionprocesocalidad_idprocesocalidad (OID = 25622) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT fk_ejecucionprocesocalidad_idprocesocalidad FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index fk_ejecucionprocesocalidad_estado (OID = 25627) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT fk_ejecucionprocesocalidad_estado FOREIGN KEY (estado) REFERENCES estadoejecucionprocesocalidad(idestado);
--
-- Definition for index fk_pedidomatriz_idmatriz (OID = 25632) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT fk_pedidomatriz_idmatriz FOREIGN KEY (idmatriz) REFERENCES matriz(idmatriz);
--
-- Definition for index fk_maquina_idunidadmedida (OID = 25637) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT fk_maquina_idunidadmedida FOREIGN KEY (idunidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index fk_maquina_estado (OID = 25642) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT fk_maquina_estado FOREIGN KEY (estado) REFERENCES estadomaquina(idestado);
--
-- Definition for index fk_maquina_tipomaquina (OID = 25647) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT fk_maquina_tipomaquina FOREIGN KEY (tipomaquina) REFERENCES tipomaquina(idtipomaquina);
--
-- Definition for index fk_maquina_marca (OID = 25652) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT fk_maquina_marca FOREIGN KEY (marca) REFERENCES marca(idmarca);
--
-- Definition for index fk_planificacioncalidad_pedido (OID = 25657) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT fk_planificacioncalidad_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_detalleplanificacioncalidad_idplanificacioncalidad (OID = 25662) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT fk_detalleplanificacioncalidad_idplanificacioncalidad FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index dtalleplanificacioncalidadddetalleejecucionplanificacioncalidad (OID = 25667) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT dtalleplanificacioncalidadddetalleejecucionplanificacioncalidad FOREIGN KEY (iddetalleejecucionplanificacioncalidad, idejecucionplanificacioncalidad, idplanificacioncalidad) REFERENCES detalleejecucionplanificacioncalidad(idejecucionplanificacioncalidad, idplanificacioncalidad, iddetalle);
--
-- Definition for index fk_detalleplanificacioncalidad_procesocalidad (OID = 25672) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT fk_detalleplanificacioncalidad_procesocalidad FOREIGN KEY (procesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index fk_detallemantenimientocorrectivo_rotura (OID = 25677) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT fk_detallemantenimientocorrectivo_rotura FOREIGN KEY (rotura) REFERENCES rotura(idrotura);
--
-- Definition for index fk_detallemantenimientocorrectivo_idmantenimientocorrectivo (OID = 25682) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT fk_detallemantenimientocorrectivo_idmantenimientocorrectivo FOREIGN KEY (idmantenimientocorrectivo) REFERENCES mantenimientocorrectivo(idmantenimientocorrectivo);
--
-- Definition for index fk_localidad_provincia (OID = 25687) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT fk_localidad_provincia FOREIGN KEY (provincia) REFERENCES provincia(idprovincia);
--
-- Definition for index fk_proveedorxmateriaprima_idmateriaprima (OID = 25692) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT fk_proveedorxmateriaprima_idmateriaprima FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_proveedorxmateriaprima_idproveedor (OID = 25697) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT fk_proveedorxmateriaprima_idproveedor FOREIGN KEY (idproveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index fk_remito_pedido (OID = 25702) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT fk_remito_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_remito_estado (OID = 25707) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT fk_remito_estado FOREIGN KEY (estado) REFERENCES estadoremito(idestado);
--
-- Definition for index fk_detalleejecucionplanificacioncalidad_idplanificacioncalidad (OID = 25712) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT fk_detalleejecucionplanificacioncalidad_idplanificacioncalidad FOREIGN KEY (idplanificacioncalidad, idejecucionplanificacioncalidad) REFERENCES ejecucionplanificacioncalidad(idplanificacioncalidad, idejecucion);
--
-- Definition for index fk_detalleejecucionplanificacioncalidad_idprocesocalidad (OID = 25717) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT fk_detalleejecucionplanificacioncalidad_idprocesocalidad FOREIGN KEY (idprocesocalidad, ejecucionprocesocalidad) REFERENCES ejecucionprocesocalidad(idprocesocalidad, idejecucion);
--
-- Definition for index fk_disponibilidadhoraria_idempleado (OID = 25722) : 
--
ALTER TABLE ONLY disponibilidadhoraria
    ADD CONSTRAINT fk_disponibilidadhoraria_idempleado FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_sesion_usuario (OID = 25727) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT fk_sesion_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_detallereclamoproveedor_idreclamo (OID = 25732) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT fk_detallereclamoproveedor_idreclamo FOREIGN KEY (idreclamo) REFERENCES reclamoproveedor(idreclamo);
--
-- Definition for index fk_detallereclamoproveedor_idcompra (OID = 25737) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT fk_detallereclamoproveedor_idcompra FOREIGN KEY (idcompra, iddetallecompra) REFERENCES detallecompra(idcompra, iddetalle);
--
-- Definition for index fk_barrio_localidad (OID = 25742) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT fk_barrio_localidad FOREIGN KEY (localidad) REFERENCES localidad(idlocalidad);
--
-- Definition for index fk_asistencia_empleado (OID = 25747) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT fk_asistencia_empleado FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_detallemantenimientopreventivo_servicio (OID = 25752) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT fk_detallemantenimientopreventivo_servicio FOREIGN KEY (servicio) REFERENCES servicio(idservicio);
--
-- Definition for index fk_detallemantenimientopreventivo_idmantenimientopreventivo (OID = 25757) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT fk_detallemantenimientopreventivo_idmantenimientopreventivo FOREIGN KEY (idmantenimientopreventivo) REFERENCES mantenimientopreventivo(idmantenimientopreventivo);
--
-- Definition for index fk_etapadeproduccion_unidaddemedida (OID = 25762) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT fk_etapadeproduccion_unidaddemedida FOREIGN KEY (unidaddemedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index fk_detallefactura_idpedido (OID = 25767) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT fk_detallefactura_idpedido FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_detallefactura_idfactura (OID = 25772) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT fk_detallefactura_idfactura FOREIGN KEY (idfactura) REFERENCES factura(idfactura);
--
-- Definition for index fk_detallepedido_producto (OID = 25777) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT fk_detallepedido_producto FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index fk_detallepedido_idpedido (OID = 25782) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT fk_detallepedido_idpedido FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_mantenimientopreventivo_proveedormantenimiento (OID = 25787) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT fk_mantenimientopreventivo_proveedormantenimiento FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index fk_rolxprivilegio_idprivilegio (OID = 25792) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT fk_rolxprivilegio_idprivilegio FOREIGN KEY (idprivilegio) REFERENCES privilegio(idprivilegio);
--
-- Definition for index fk_rolxprivilegio_idrol (OID = 25797) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT fk_rolxprivilegio_idrol FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index fk_detallereclamocliente_producto (OID = 25802) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT fk_detallereclamocliente_producto FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index fk_detallereclamocliente_idreclamo (OID = 25807) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT fk_detallereclamocliente_idreclamo FOREIGN KEY (idreclamo) REFERENCES reclamocliente(idreclamo);
--
-- Definition for index fk_proveedormantenimientomaquina_condicioniva (OID = 25812) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT fk_proveedormantenimientomaquina_condicioniva FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index fk_proveedormantenimientomaquina_domicilio (OID = 25817) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT fk_proveedormantenimientomaquina_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_proveedormantenimientomaquina_responsable (OID = 25822) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT fk_proveedormantenimientomaquina_responsable FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index fk_detallepiezapresupuesto_iddetalleproductopresupuesto (OID = 25827) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT fk_detallepiezapresupuesto_iddetalleproductopresupuesto FOREIGN KEY (iddetalleproductopresupuesto) REFERENCES detalleproductopresupuesto(iddetalle);
--
-- Definition for index fk_detallepiezapresupuesto_idetapa (OID = 25832) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT fk_detallepiezapresupuesto_idetapa FOREIGN KEY (idetapa) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_ejecucionplanificacionproduccion_estado (OID = 25837) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT fk_ejecucionplanificacionproduccion_estado FOREIGN KEY (estado) REFERENCES estadoejecplanifpedido(idestado);
--
-- Definition for index fk_ejecucionplanificacionproduccion_idplanificacionproduccion (OID = 25842) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT fk_ejecucionplanificacionproduccion_idplanificacionproduccion FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index fk_detallepresupuesto_idpresupuesto (OID = 25847) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT fk_detallepresupuesto_idpresupuesto FOREIGN KEY (idpresupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index fk_detallepresupuesto_idproducto (OID = 25852) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT fk_detallepresupuesto_idproducto FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index fk_domicilio_barrio (OID = 25857) : 
--
ALTER TABLE ONLY domicilio
    ADD CONSTRAINT fk_domicilio_barrio FOREIGN KEY (barrio) REFERENCES barrio(idbarrio);
--
-- Definition for index fk_empleado_cargo (OID = 25862) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_cargo FOREIGN KEY (cargo) REFERENCES cargo(idcargo);
--
-- Definition for index fk_empleado_categoria (OID = 25867) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_categoria FOREIGN KEY (categoria) REFERENCES categoria(idcategoria);
--
-- Definition for index fk_empleado_domicilio (OID = 25872) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_empleado_tipodocumento (OID = 25877) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_tipodocumento FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index fk_empleado_usuario (OID = 25882) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_detalleplanprocedimientos_idetapaproduccion (OID = 25887) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT fk_detalleplanprocedimientos_idetapaproduccion FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_detalleplanprocedimientos_idplanpprocedimientos (OID = 25892) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT fk_detalleplanprocedimientos_idplanpprocedimientos FOREIGN KEY (idplanpprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index fk_reclamocliente_cliente (OID = 25897) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT fk_reclamocliente_cliente FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index fk_reclamocliente_tiporeclamo (OID = 25902) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT fk_reclamocliente_tiporeclamo FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index fk_comprobantepago_usuario (OID = 25907) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT fk_comprobantepago_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_comprobantepago_factura (OID = 25912) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT fk_comprobantepago_factura FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index fk_comprobantepago_formadepago (OID = 25917) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT fk_comprobantepago_formadepago FOREIGN KEY (formadepago) REFERENCES formadepago(idformapago);
--
-- Definition for index fk_mpasignadaxpiezareal_iddetallempasignada (OID = 25922) : 
--
ALTER TABLE ONLY mpasignadaxpiezareal
    ADD CONSTRAINT fk_mpasignadaxpiezareal_iddetallempasignada FOREIGN KEY (iddetallempasignada) REFERENCES detallempasignada(id);
--
-- Definition for index fk_detalleproductopresupuesto_idpieza (OID = 25927) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT fk_detalleproductopresupuesto_idpieza FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index fk_detalleproductopresupuesto_iddetallepresupuesto (OID = 25932) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT fk_detalleproductopresupuesto_iddetallepresupuesto FOREIGN KEY (iddetallepresupuesto) REFERENCES detallepresupuesto(iddetalle);
--
-- Definition for index fk_detalleproductopresupuesto_idmateriaprima (OID = 25937) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT fk_detalleproductopresupuesto_idmateriaprima FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detalletrabajotercerizado_estado (OID = 25942) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT fk_detalletrabajotercerizado_estado FOREIGN KEY (estado) REFERENCES estadodetalletrabajotercerizado(idestado);
--
-- Definition for index fk_detalletrabajotercerizado_idtrabajotercerizado (OID = 25947) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT fk_detalletrabajotercerizado_idtrabajotercerizado FOREIGN KEY (idtrabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index fk_detalletrabajotercerizado_proceso (OID = 25952) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT fk_detalletrabajotercerizado_proceso FOREIGN KEY (proceso) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index ejecucionetapaproduccion_pkey (OID = 25957) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_pkey PRIMARY KEY (id);
--
-- Definition for index detalleejecucionplanificacion_fk (OID = 25959) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk FOREIGN KEY (idejecucionplanificacionproduccion) REFERENCES ejecucionplanificacionproduccion(idejecucion);
--
-- Definition for index detalleejecucionplanificacion_fk1 (OID = 25964) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk1 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleejecucionplanificacion_fk2 (OID = 25969) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk2 FOREIGN KEY (ejecucionetapa) REFERENCES ejecucionetapaproduccion(id);
--
-- Definition for index detalleejecucionplanificacion_fk3 (OID = 25974) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk3 FOREIGN KEY (piezareal) REFERENCES piezareal(idpiezareal);
--
-- Definition for index etapadeproduccion_fk (OID = 25979) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_fk FOREIGN KEY (maquina) REFERENCES maquina(idmaquina);
--
-- Definition for index detalleplanificacionproduccion_fk4 (OID = 25984) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk4 FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Data for sequence public.prueba_id_seq (OID = 23330)
--
SELECT pg_catalog.setval('prueba_id_seq', 1, false);
--
-- Data for sequence public.usuario_idusuario_seq (OID = 23336)
--
SELECT pg_catalog.setval('usuario_idusuario_seq', 9, true);
--
-- Data for sequence public.tipomaterial_idtipomaterial_seq (OID = 23338)
--
SELECT pg_catalog.setval('tipomaterial_idtipomaterial_seq', 5, true);
--
-- Data for sequence public.accioncalidad_idaccioncalidad_seq (OID = 23340)
--
SELECT pg_catalog.setval('accioncalidad_idaccioncalidad_seq', 3, true);
--
-- Data for sequence public.barrio_idbarrio_seq (OID = 23342)
--
SELECT pg_catalog.setval('barrio_idbarrio_seq', 6, true);
--
-- Data for sequence public.cargo_idcargo_seq (OID = 23344)
--
SELECT pg_catalog.setval('cargo_idcargo_seq', 6, true);
--
-- Data for sequence public.categoria_idcategoria_seq (OID = 23346)
--
SELECT pg_catalog.setval('categoria_idcategoria_seq', 2, true);
--
-- Data for sequence public.cliente_idcliente_seq (OID = 23348)
--
SELECT pg_catalog.setval('cliente_idcliente_seq', 17, true);
--
-- Data for sequence public.codigodebarra_idcodigo_seq (OID = 23350)
--
SELECT pg_catalog.setval('codigodebarra_idcodigo_seq', 41, true);
--
-- Data for sequence public.compra_idcompra_seq (OID = 23352)
--
SELECT pg_catalog.setval('compra_idcompra_seq', 1, false);
--
-- Data for sequence public.comprobantepago_idcomprobantepago_seq (OID = 23354)
--
SELECT pg_catalog.setval('comprobantepago_idcomprobantepago_seq', 1, false);
--
-- Data for sequence public.condicioniva_idcondicioniva_seq (OID = 23356)
--
SELECT pg_catalog.setval('condicioniva_idcondicioniva_seq', 3, true);
--
-- Data for sequence public.detalleplanificacionproduccion_id_seq (OID = 23358)
--
SELECT pg_catalog.setval('detalleplanificacionproduccion_id_seq', 34, true);
--
-- Data for sequence public.detallecompra_iddetalle_seq (OID = 23360)
--
SELECT pg_catalog.setval('detallecompra_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleejecucionplanificacion_iddetalle_seq (OID = 23362)
--
SELECT pg_catalog.setval('detalleejecucionplanificacion_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleejecucionplanificacioncalidad_iddetalle_seq (OID = 23364)
--
SELECT pg_catalog.setval('detalleejecucionplanificacioncalidad_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallefactura_iddetalle_seq (OID = 23366)
--
SELECT pg_catalog.setval('detallefactura_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallemantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 23368)
--
SELECT pg_catalog.setval('detallemantenimientocorrectivo_idmantenimientocorrectivo_seq', 1, false);
--
-- Data for sequence public.detallemantenimientopreventivo_idmantenimientopreventivo_seq (OID = 23370)
--
SELECT pg_catalog.setval('detallemantenimientopreventivo_idmantenimientopreventivo_seq', 1, false);
--
-- Data for sequence public.detallepedido_iddetalle_seq (OID = 23372)
--
SELECT pg_catalog.setval('detallepedido_iddetalle_seq', 32, true);
--
-- Data for sequence public.detalleplanificacioncalidad_iddetalle_seq (OID = 23374)
--
SELECT pg_catalog.setval('detalleplanificacioncalidad_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleplanprocedimientos_iddetalle_seq (OID = 23376)
--
SELECT pg_catalog.setval('detalleplanprocedimientos_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleplanprocesoscalidad_iddetalle_seq (OID = 23378)
--
SELECT pg_catalog.setval('detalleplanprocesoscalidad_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallepresupuesto_iddetalle_seq (OID = 23380)
--
SELECT pg_catalog.setval('detallepresupuesto_iddetalle_seq', 37, true);
--
-- Data for sequence public.detalleproducto_iddetalle_seq (OID = 23382)
--
SELECT pg_catalog.setval('detalleproducto_iddetalle_seq', 19, true);
--
-- Data for sequence public.detalleproductoreal_iddetalle_seq (OID = 23384)
--
SELECT pg_catalog.setval('detalleproductoreal_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallereclamocliente_iddetalle_seq (OID = 23386)
--
SELECT pg_catalog.setval('detallereclamocliente_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallereclamoempresametalurgica_iddetalle_seq (OID = 23388)
--
SELECT pg_catalog.setval('detallereclamoempresametalurgica_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallereclamoproveedor_iddetalle_seq (OID = 23390)
--
SELECT pg_catalog.setval('detallereclamoproveedor_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleremito_iddetalle_seq (OID = 23392)
--
SELECT pg_catalog.setval('detalleremito_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallerequerimientosmateriaprima_iddetalle_seq (OID = 23394)
--
SELECT pg_catalog.setval('detallerequerimientosmateriaprima_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalletrabajotercerizado_iddetalle_seq (OID = 23396)
--
SELECT pg_catalog.setval('detalletrabajotercerizado_iddetalle_seq', 1, false);
--
-- Data for sequence public.domicilio_iddomicilio_seq (OID = 23398)
--
SELECT pg_catalog.setval('domicilio_iddomicilio_seq', 42, true);
--
-- Data for sequence public.ejecucionetapaproduccion_idejecucion_seq (OID = 23400)
--
SELECT pg_catalog.setval('ejecucionetapaproduccion_idejecucion_seq', 1, false);
--
-- Data for sequence public.ejecucionplanificacioncalidad_idejecucion_seq (OID = 23402)
--
SELECT pg_catalog.setval('ejecucionplanificacioncalidad_idejecucion_seq', 1, false);
--
-- Data for sequence public.ejecucionplanificacionproduccion_idejecucion_seq (OID = 23404)
--
SELECT pg_catalog.setval('ejecucionplanificacionproduccion_idejecucion_seq', 7, true);
--
-- Data for sequence public.ejecucionprocesocalidad_idejecucion_seq (OID = 23406)
--
SELECT pg_catalog.setval('ejecucionprocesocalidad_idejecucion_seq', 1, false);
--
-- Data for sequence public.empleado_idempleado_seq (OID = 23408)
--
SELECT pg_catalog.setval('empleado_idempleado_seq', 4, true);
--
-- Data for sequence public.empresametalurgica_idempresametalurgica_seq (OID = 23410)
--
SELECT pg_catalog.setval('empresametalurgica_idempresametalurgica_seq', 1, false);
--
-- Data for sequence public.estadocliente_idestado_seq (OID = 23412)
--
SELECT pg_catalog.setval('estadocliente_idestado_seq', 4, true);
--
-- Data for sequence public.estadocompra_idestado_seq (OID = 23414)
--
SELECT pg_catalog.setval('estadocompra_idestado_seq', 1, false);
--
-- Data for sequence public.estadodetallecompra_idestado_seq (OID = 23416)
--
SELECT pg_catalog.setval('estadodetallecompra_idestado_seq', 1, false);
--
-- Data for sequence public.estadodetalletrabajotercerizado_idestado_seq (OID = 23418)
--
SELECT pg_catalog.setval('estadodetalletrabajotercerizado_idestado_seq', 1, false);
--
-- Data for sequence public.estadoejecetapaprod_idestado_seq (OID = 23420)
--
SELECT pg_catalog.setval('estadoejecetapaprod_idestado_seq', 1, false);
--
-- Data for sequence public.estadoejecplancalidad_idestado_seq (OID = 23422)
--
SELECT pg_catalog.setval('estadoejecplancalidad_idestado_seq', 1, false);
--
-- Data for sequence public.estadoejecplanifpedido_idestado_seq (OID = 23424)
--
SELECT pg_catalog.setval('estadoejecplanifpedido_idestado_seq', 1, true);
--
-- Data for sequence public.estadoejecucionprocesocalidad_idestado_seq (OID = 23426)
--
SELECT pg_catalog.setval('estadoejecucionprocesocalidad_idestado_seq', 1, false);
--
-- Data for sequence public.estadofactura_idestado_seq (OID = 23428)
--
SELECT pg_catalog.setval('estadofactura_idestado_seq', 1, true);
--
-- Data for sequence public.estadomaquina_idestado_seq (OID = 23430)
--
SELECT pg_catalog.setval('estadomaquina_idestado_seq', 1, true);
--
-- Data for sequence public.estadopedido_idestado_seq (OID = 23432)
--
SELECT pg_catalog.setval('estadopedido_idestado_seq', 18, true);
--
-- Data for sequence public.estadopiezareal_idestado_seq (OID = 23434)
--
SELECT pg_catalog.setval('estadopiezareal_idestado_seq', 1, true);
--
-- Data for sequence public.estadoproductoreal_idestado_seq (OID = 23436)
--
SELECT pg_catalog.setval('estadoproductoreal_idestado_seq', 1, false);
--
-- Data for sequence public.estadoremito_idestado_seq (OID = 23438)
--
SELECT pg_catalog.setval('estadoremito_idestado_seq', 1, true);
--
-- Data for sequence public.estadotrabajotercerizado_idestado_seq (OID = 23440)
--
SELECT pg_catalog.setval('estadotrabajotercerizado_idestado_seq', 1, false);
--
-- Data for sequence public.etapadeproduccion_idetapaproduccion_seq (OID = 23442)
--
SELECT pg_catalog.setval('etapadeproduccion_idetapaproduccion_seq', 5, true);
--
-- Data for sequence public.factura_idfactura_seq (OID = 23444)
--
SELECT pg_catalog.setval('factura_idfactura_seq', 1, false);
--
-- Data for sequence public.formadepago_idformapago_seq (OID = 23446)
--
SELECT pg_catalog.setval('formadepago_idformapago_seq', 1, false);
--
-- Data for sequence public.localidad_idlocalidad_seq (OID = 23448)
--
SELECT pg_catalog.setval('localidad_idlocalidad_seq', 11, true);
--
-- Data for sequence public.mantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 23450)
--
SELECT pg_catalog.setval('mantenimientocorrectivo_idmantenimientocorrectivo_seq', 1, false);
--
-- Data for sequence public.mantenimientopreventivo_idmantenimientopreventivo_seq (OID = 23452)
--
SELECT pg_catalog.setval('mantenimientopreventivo_idmantenimientopreventivo_seq', 1, false);
--
-- Data for sequence public.maquina_idmaquina_seq (OID = 23454)
--
SELECT pg_catalog.setval('maquina_idmaquina_seq', 1, true);
--
-- Data for sequence public.marca_idmarca_seq (OID = 23456)
--
SELECT pg_catalog.setval('marca_idmarca_seq', 1, true);
--
-- Data for sequence public.materiaprima_idmateriaprima_seq (OID = 23458)
--
SELECT pg_catalog.setval('materiaprima_idmateriaprima_seq', 9, true);
--
-- Data for sequence public.matriz_idmatriz_seq (OID = 23460)
--
SELECT pg_catalog.setval('matriz_idmatriz_seq', 4, true);
--
-- Data for sequence public.pedido_idpedido_seq (OID = 23462)
--
SELECT pg_catalog.setval('pedido_idpedido_seq', 25, true);
--
-- Data for sequence public.pedidomatriz_idpedidomatriz_seq (OID = 23464)
--
SELECT pg_catalog.setval('pedidomatriz_idpedidomatriz_seq', 1, false);
--
-- Data for sequence public.pieza_idpieza_seq (OID = 23466)
--
SELECT pg_catalog.setval('pieza_idpieza_seq', 15, true);
--
-- Data for sequence public.piezareal_idpiezareal_seq (OID = 23468)
--
SELECT pg_catalog.setval('piezareal_idpiezareal_seq', 1, false);
--
-- Data for sequence public.planificacioncalidad_idplanificacion_seq (OID = 23470)
--
SELECT pg_catalog.setval('planificacioncalidad_idplanificacion_seq', 1, false);
--
-- Data for sequence public.planificacionproduccion_idplanificacionproduccion_seq (OID = 23472)
--
SELECT pg_catalog.setval('planificacionproduccion_idplanificacionproduccion_seq', 27, true);
--
-- Data for sequence public.plano_idplano_seq (OID = 23474)
--
SELECT pg_catalog.setval('plano_idplano_seq', 1, false);
--
-- Data for sequence public.planprocedimientos_idplanprocedimientos_seq (OID = 23476)
--
SELECT pg_catalog.setval('planprocedimientos_idplanprocedimientos_seq', 1, false);
--
-- Data for sequence public.planprocesoscalidad_idplanprocesoscalidad_seq (OID = 23478)
--
SELECT pg_catalog.setval('planprocesoscalidad_idplanprocesoscalidad_seq', 1, false);
--
-- Data for sequence public.planrequerimientosmateriaprima_idplanrequerimientosmateriaprima (OID = 23480)
--
SELECT pg_catalog.setval('planrequerimientosmateriaprima_idplanrequerimientosmateriaprima', 1, false);
--
-- Data for sequence public.presupuesto_idpresupuesto_seq (OID = 23482)
--
SELECT pg_catalog.setval('presupuesto_idpresupuesto_seq', 38, true);
--
-- Data for sequence public.prioridad_idprioridad_seq (OID = 23484)
--
SELECT pg_catalog.setval('prioridad_idprioridad_seq', 3, true);
--
-- Data for sequence public.privilegio_idprivilegio_seq (OID = 23486)
--
SELECT pg_catalog.setval('privilegio_idprivilegio_seq', 1, false);
--
-- Data for sequence public.procesocalidad_idprocesocalidad_seq (OID = 23488)
--
SELECT pg_catalog.setval('procesocalidad_idprocesocalidad_seq', 2, true);
--
-- Data for sequence public.producto_idproducto_seq (OID = 23490)
--
SELECT pg_catalog.setval('producto_idproducto_seq', 11, true);
--
-- Data for sequence public.productoreal_idproductoreal_seq (OID = 23492)
--
SELECT pg_catalog.setval('productoreal_idproductoreal_seq', 1, false);
--
-- Data for sequence public.proveedor_idproveedor_seq (OID = 23494)
--
SELECT pg_catalog.setval('proveedor_idproveedor_seq', 3, true);
--
-- Data for sequence public.proveedormantenimientomaquina_idproveedormantenimiento_seq (OID = 23496)
--
SELECT pg_catalog.setval('proveedormantenimientomaquina_idproveedormantenimiento_seq', 1, false);
--
-- Data for sequence public.provincia_idprovincia_seq (OID = 23498)
--
SELECT pg_catalog.setval('provincia_idprovincia_seq', 14, true);
--
-- Data for sequence public.reclamocliente_idreclamo_seq (OID = 23500)
--
SELECT pg_catalog.setval('reclamocliente_idreclamo_seq', 1, false);
--
-- Data for sequence public.reclamoempresametalurgica_idreclamo_seq (OID = 23502)
--
SELECT pg_catalog.setval('reclamoempresametalurgica_idreclamo_seq', 1, false);
--
-- Data for sequence public.reclamoproveedor_idreclamo_seq (OID = 23504)
--
SELECT pg_catalog.setval('reclamoproveedor_idreclamo_seq', 1, false);
--
-- Data for sequence public.remito_idremito_seq (OID = 23506)
--
SELECT pg_catalog.setval('remito_idremito_seq', 1, false);
--
-- Data for sequence public.responsable_idresponsable_seq (OID = 23508)
--
SELECT pg_catalog.setval('responsable_idresponsable_seq', 17, true);
--
-- Data for sequence public.rol_idrol_seq (OID = 23510)
--
SELECT pg_catalog.setval('rol_idrol_seq', 5, true);
--
-- Data for sequence public.rotura_idrotura_seq (OID = 23512)
--
SELECT pg_catalog.setval('rotura_idrotura_seq', 1, false);
--
-- Data for sequence public.servicio_idservicio_seq (OID = 23514)
--
SELECT pg_catalog.setval('servicio_idservicio_seq', 1, false);
--
-- Data for sequence public.sesion_idsesion_seq (OID = 23516)
--
SELECT pg_catalog.setval('sesion_idsesion_seq', 1, false);
--
-- Data for sequence public.tipodocumento_idtipodocumento_seq (OID = 23518)
--
SELECT pg_catalog.setval('tipodocumento_idtipodocumento_seq', 3, true);
--
-- Data for sequence public.tipoiva_idtipoiva_seq (OID = 23520)
--
SELECT pg_catalog.setval('tipoiva_idtipoiva_seq', 1, false);
--
-- Data for sequence public.tipomaquina_idtipomaquina_seq (OID = 23522)
--
SELECT pg_catalog.setval('tipomaquina_idtipomaquina_seq', 3, true);
--
-- Data for sequence public.tiporeclamo_idtiporeclamo_seq (OID = 23524)
--
SELECT pg_catalog.setval('tiporeclamo_idtiporeclamo_seq', 1, false);
--
-- Data for sequence public.trabajotercerizado_idtrabajo_seq (OID = 23526)
--
SELECT pg_catalog.setval('trabajotercerizado_idtrabajo_seq', 1, false);
--
-- Data for sequence public.turno_idturno_seq (OID = 23528)
--
SELECT pg_catalog.setval('turno_idturno_seq', 3, true);
--
-- Data for sequence public.unidadmedida_idunidadmedida_seq (OID = 23538)
--
SELECT pg_catalog.setval('unidadmedida_idunidadmedida_seq', 4, true);
--
-- Data for sequence public.detallepiezapresupuesto_iddetalle_seq (OID = 23548)
--
SELECT pg_catalog.setval('detallepiezapresupuesto_iddetalle_seq', 62, true);
--
-- Data for sequence public.detalleproductopresupuesto_iddetalle_seq (OID = 23554)
--
SELECT pg_catalog.setval('detalleproductopresupuesto_iddetalle_seq', 50, true);
--
-- Data for sequence public.detallepiezacalidadpresupuesto_iddetalle_seq (OID = 23560)
--
SELECT pg_catalog.setval('detallepiezacalidadpresupuesto_iddetalle_seq', 44, true);
--
-- Data for sequence public.calendario_id_seq (OID = 23583)
--
SELECT pg_catalog.setval('calendario_id_seq', 7, true);
--
-- Data for sequence public.disponibilidadhoraria_id_seq (OID = 23593)
--
SELECT pg_catalog.setval('disponibilidadhoraria_id_seq', 7, true);
--
-- Data for sequence public.estadoplanificacionproduccion_id_seq (OID = 23599)
--
SELECT pg_catalog.setval('estadoplanificacionproduccion_id_seq', 3, true);
--
-- Data for sequence public.maquina_idmaquina_seq1 (OID = 23608)
--
SELECT pg_catalog.setval('maquina_idmaquina_seq1', 4, true);
--
-- Data for sequence public.piezareal_idpiezareal_seq1 (OID = 23614)
--
SELECT pg_catalog.setval('piezareal_idpiezareal_seq1', 42, true);
--
-- Data for sequence public.pieza_idpieza_seq1 (OID = 23620)
--
SELECT pg_catalog.setval('pieza_idpieza_seq1', 6, true);
--
-- Data for sequence public.detallempasignada_id_seq (OID = 23626)
--
SELECT pg_catalog.setval('detallempasignada_id_seq', 8, true);
--
-- Data for sequence public.mpasignadaxpiezareal_id_seq (OID = 23632)
--
SELECT pg_catalog.setval('mpasignadaxpiezareal_id_seq', 28, true);
--
-- Comments
--
COMMENT ON SCHEMA public IS 'standard public schema';
