-- SQL Manager 2007 for PostgreSQL 4.5.0.8
-- ---------------------------------------
-- Host      : localhost
-- Database  : metalsoft
-- Version   : PostgreSQL 8.3.11, compiled by Visual C++ build 1400



--
-- Definition for language plpgsql (OID = 16386) : 
--
/*
CREATE TRUSTED PROCEDURAL LANGUAGE plpgsql
   HANDLER "plpgsql_call_handler"
   VALIDATOR "pg_catalog"."plpgsql_validator";
*/
--
-- Definition for function nvonropedido (OID = 81582) : 
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
-- Definition for function cantpiezasxproducto (OID = 81583) : 
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
-- Definition for function esproductosinalgunaetapa (OID = 81584) : 
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
-- Definition for function cantpiezasdepedido (OID = 81585) : 
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
-- Definition for function nvonroproducto (OID = 81586) : 
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
-- Definition for function cantpiezasdepedido2 (OID = 81587) : 
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
-- Definition for function nvonropresupuesto (OID = 81588) : 
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
-- Definition for function ingresoxpedido (OID = 81589) : 
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
-- Definition for function nvonrocliente (OID = 81590) : 
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
-- Definition for function nvonromateriaprima (OID = 81591) : 
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
-- Definition for function nvonroempleado (OID = 81592) : 
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
-- Definition for function nvonromaquina (OID = 81593) : 
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
-- Definition for function nvonroejecucionplanificacion (OID = 81594) : 
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
-- Definition for function nvonroplanificacionproduccion (OID = 81595) : 
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
-- Definition for function nvonroejecucionetapa (OID = 84555) : 
--
CREATE FUNCTION public.nvonroejecucionetapa () RETURNS bigint
AS 
$body$
DECLARE
  result BIGINT;
BEGIN
  SELECT (max(p.nroejecucion)+1) INTO result FROM ejecucionetapaproduccion p;
  IF(result IS NULL)THEN
  	result:=1;
  END IF;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function nvonropiezareal (OID = 84597) : 
--
CREATE FUNCTION public.nvonropiezareal () RETURNS bigint
AS 
$body$
DECLARE
  result BIGINT;
BEGIN
  SELECT (max(p.nropieza)+1) INTO result FROM piezareal p;
  IF(result IS NULL)THEN
  	result:=1;
  END IF;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function nvonroempresametalurgica (OID = 84806) : 
--
CREATE FUNCTION public.nvonroempresametalurgica () RETURNS bigint
AS 
$body$
DECLARE
  result BIGINT;
BEGIN
  SELECT (max(p.nroempresametalurgica)+1) INTO result FROM empresametalurgica p;
  IF(result IS NULL)THEN
  	result:=1;
  END IF;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function nvonropedidomatriz (OID = 84807) : 
--
CREATE FUNCTION public.nvonropedidomatriz () RETURNS bigint
AS 
$body$
DECLARE
  result BIGINT;
BEGIN
  SELECT (max(p.nropedidomatriz)+1) INTO result FROM pedidomatriz p;
  IF(result IS NULL)THEN
  	result:=1;
  END IF;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function nvonroproveedormantenimiento (OID = 84947) : 
--
CREATE FUNCTION public.nvonroproveedormantenimiento () RETURNS bigint
AS 
$body$
DECLARE
  result BIGINT;
BEGIN
  SELECT (max(p.nroproveedor)+1) INTO result FROM proveedormantenimientomaquina p;
  IF(result IS NULL)THEN
  result:=1;
  END IF;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function nvonroproveedor (OID = 84948) : 
--
CREATE FUNCTION public.nvonroproveedor () RETURNS bigint
AS 
$body$
DECLARE
  result BIGINT;
BEGIN
  SELECT (max(p.nroproveedor)+1) INTO result FROM proveedor p;
  IF(result IS NULL)THEN
  result:=1;
  END IF;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function nvonromantenimientopreventivo (OID = 84949) : 
--
CREATE FUNCTION public.nvonromantenimientopreventivo () RETURNS bigint
AS 
$body$
DECLARE
  result BIGINT;
BEGIN
  SELECT (max(p.nromantenimietno)+1) INTO result FROM mantenimientopreventivo p;
  IF(result IS NULL)THEN
  result:=1;
  END IF;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function nvonrotrabajotercerizado (OID = 84954) : 
--
CREATE FUNCTION public.nvonrotrabajotercerizado () RETURNS bigint
AS 
$body$
DECLARE
result BIGINT;
BEGIN
SELECT (max(p.nrotrabajotercerizado)+1) INTO result FROM trabajotercerizado p;
IF(result IS NULL)THEN
result:=1;
END IF;
RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function esulejecetapaprod (OID = 85105) : 
--
CREATE FUNCTION public.esulejecetapaprod (idejecucionproduccion bigint) RETURNS boolean
AS 
$body$
DECLARE
  result BOOLEAN;
  cantidadEtapas INTEGER;
  cantidadEtapasFinalizadas INTEGER;
BEGIN

	SELECT count(*) INTO cantidadEtapas
  	FROM ejecucionplanificacionproduccion epp, detalleejecucionplanificacion dep,
  	ejecucionetapaproduccion eep
  	WHERE epp.idejecucion = dep.idejecucionplanificacionproduccion AND
    epp.idejecucion = idejecucionproduccion AND
  	dep.ejecucionetapa = eep.id;


    SELECT count(*) INTO cantidadEtapasFinalizadas
    FROM ejecucionplanificacionproduccion epp, detalleejecucionplanificacion dep,
    ejecucionetapaproduccion eep, estadoejecetapaprod eeep
    WHERE epp.idejecucion = dep.idejecucionplanificacionproduccion AND
    dep.ejecucionetapa = eep.id AND eep.estado = eeep.idestado AND
    epp.idejecucion = idejecucionproduccion AND
    eeep.idestado = 4;

	IF(cantidadEtapas = (cantidadEtapasFinalizadas + 1)) THEN
		result:=TRUE;
    ELSE
    	result:=FALSE;
    END IF;

	RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function esulejecetapadepieza (OID = 85106) : 
--
CREATE FUNCTION public.esulejecetapadepieza (idejecucionproduccion bigint, idproducto bigint, idpieza bigint) RETURNS boolean
AS 
$body$
DECLARE
  result boolean;
  cantidadEtapasPorPieza integer;
  cantidadEtapasPorPiezaFinalizadas integer;
BEGIN
	SELECT count(*) INTO cantidadEtapasPorPieza 
    FROM detalleejecucionplanificacion dep,
    detalleplanificacionproduccion dpp,
    ejecucionplanificacionproduccion epp
    WHERE epp.idejecucion = dep.idejecucionplanificacionproduccion AND
    epp.idejecucion = idejecucionproduccion AND
    dep.id = dpp.iddetalleejecucionplanificacion AND
    dep.pieza = idpieza AND dpp.idproducto =idproducto;
    
    SELECT count(*) INTO cantidadEtapasPorPiezaFinalizadas 
    FROM detalleejecucionplanificacion dep,
    detalleplanificacionproduccion dpp,
    ejecucionplanificacionproduccion epp, 
    ejecucionetapaproduccion eep,
    estadoejecetapaprod eeep
    WHERE epp.idejecucion = dep.idejecucionplanificacionproduccion AND
    dep.ejecucionetapa = eep.id AND eep.estado = eeep.idestado AND
    eeep.idestado = 4 AND
    epp.idejecucion = idejecucionproduccion AND
    dep.id = dpp.iddetalleejecucionplanificacion AND
    dep.pieza = idpieza AND dpp.idproducto =idproducto;
    
    IF (cantidadEtapasPorPieza = (cantidadEtapasPorPiezaFinalizadas + 1)) THEN
    	result := TRUE;
    ELSE
    	result := FALSE;
    END IF;
    
    RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function horafinprevistaplanifprod (OID = 85221) : 
--
CREATE FUNCTION public.horafinprevistaplanifprod (idplanificacionproduccion bigint) RETURNS time without time zone
AS 
$body$
DECLARE
  result TIME;
BEGIN
  
  SELECT max(dpp.horafin) INTO result
  FROM planificacionproduccion pp, detalleplanificacionproduccion dpp
  WHERE pp.idplanificacionproduccion = dpp.idplanificacionproduccion AND
  pp.idplanificacionproduccion = idplanificacionproduccion AND
  pp.fechafinprevista = dpp.fechafin;
    
  return result;
  
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function nvonroplanificacioncalidad (OID = 85271) : 
--
CREATE FUNCTION public.nvonroplanificacioncalidad () RETURNS bigint
AS 
$body$
DECLARE
  result BIGINT;
BEGIN
  SELECT (max(p.nroplanificacion)+1) INTO result FROM planificacioncalidad p;
  IF(result IS NULL)THEN
  	result:=1;
  END IF;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function nvonroproductoreal (OID = 85288) : 
--
CREATE FUNCTION public.nvonroproductoreal () RETURNS bigint
AS 
$body$
DECLARE
  result BIGINT;
BEGIN
  SELECT (max(p.nroproducto)+1) INTO result FROM productoreal p;
  IF(result IS NULL)THEN
  	result:=1;
  END IF;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function nvonroejecplanifcalidad (OID = 85293) : 
--
CREATE FUNCTION public.nvonroejecplanifcalidad () RETURNS bigint
AS 
$body$
DECLARE
  result BIGINT;
BEGIN
  SELECT (max(p.nroejecucionplanificacioncalidad)+1) INTO result FROM ejecucionplanificacioncalidad p;
  IF(result IS NULL)THEN
  	result:=1;
  END IF;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function nvonroejecprocalidad (OID = 85299) : 
--
CREATE FUNCTION public.nvonroejecprocalidad () RETURNS bigint
AS 
$body$
DECLARE
  result BIGINT;
BEGIN
  SELECT (max(p.nroejecucion)+1) INTO result FROM ejecucionprocesocalidad p;
  IF(result IS NULL)THEN
  	result:=1;
  END IF;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function esulejecprocalpieza (OID = 85310) : 
--
CREATE FUNCTION public.esulejecprocalpieza (idejecucioncalidad bigint, idproducto bigint, idpieza bigint) RETURNS boolean
AS 
$body$
DECLARE
  result boolean;
  cantidadProcCalidadPorPieza integer;
  cantidadProcCalidadPorPiezaFinalizadas integer;
BEGIN
	SELECT count(*) INTO cantidadProcCalidadPorPieza 
    FROM detalleejecucionplanificacioncalidad depc,
    detalleplanificacioncalidad dpc,
    ejecucionplanificacioncalidad epc
    WHERE epc.idejecucion = depc.idejecucionplanificacioncalidad AND
    epc.idejecucion = idejecucioncalidad AND
    depc.iddetalle = dpc.iddetalleejecucionplanificacioncalidad AND
    depc.pieza = idpieza AND dpc.producto =idproducto;
    
    SELECT count(*) INTO cantidadProcCalidadPorPiezaFinalizadas 
    FROM detalleejecucionplanificacioncalidad dep,
    detalleplanificacioncalidad dpp,
    ejecucionplanificacioncalidad epp, 
    ejecucionprocesocalidad eep,
    estadoejecucionprocesocalidad eeep
    WHERE epp.idejecucion = dep.idejecucionplanificacioncalidad AND
    dep.ejecucionprocesocalidad = eep.idejecucion AND eep.estado = eeep.idestado AND
    eeep.idestado = 4 AND
    epp.idejecucion = idejecucioncalidad AND
    dep.iddetalle = dpp.iddetalleejecucionplanificacioncalidad AND
    dep.pieza = idpieza AND dpp.producto = idproducto;
    
    IF (cantidadProcCalidadPorPieza = (cantidadProcCalidadPorPiezaFinalizadas + 1)) THEN
    	result := TRUE;
    ELSE
    	result := FALSE;
    END IF;
    
    RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function esulejecprocalcalidad (OID = 85391) : 
--
CREATE FUNCTION public.esulejecprocalcalidad (idejecucioncalidad bigint) RETURNS boolean
AS 
$body$
DECLARE
  result BOOLEAN;
  cantidadProcesosCalidad INTEGER;
  cantidadProcesosCalidadFinalizados INTEGER;
BEGIN

	SELECT count(*) INTO cantidadProcesosCalidad
  	FROM ejecucionplanificacioncalidad epp, detalleejecucionplanificacioncalidad dep,
  	ejecucionprocesocalidad eep
  	WHERE epp.idejecucion = dep.idejecucionplanificacioncalidad AND
    epp.idejecucion = idejecucioncalidad AND
  	dep.ejecucionprocesocalidad = eep.idejecucion;


    SELECT count(*) INTO cantidadProcesosCalidadFinalizados
    FROM ejecucionplanificacioncalidad epp, detalleejecucionplanificacioncalidad dep,
    ejecucionprocesocalidad eep, estadoejecucionprocesocalidad eeep
    WHERE epp.idejecucion = dep.idejecucionplanificacioncalidad AND
    dep.ejecucionprocesocalidad = eep.idejecucion AND eep.estado = eeep.idestado AND
    epp.idejecucion = idejecucioncalidad AND
    eeep.idestado = 4;

	IF(cantidadProcesosCalidad = (cantidadProcesosCalidadFinalizados + 1)) THEN
		result:=TRUE;
    ELSE
    	result:=FALSE;
    END IF;

	RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Structure for table pedido (OID = 81596) : 
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
-- Structure for table planificacioncalidad (OID = 81600) : 
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
-- Structure for table estadopedido (OID = 81604) : 
--
CREATE TABLE public.estadopedido (
    idestado bigint DEFAULT nextval(('"public"."estadopedido_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table factura (OID = 81608) : 
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
    tipofactura character varying(50),
    montototal double precision
) WITH OIDS;
--
-- Structure for table planificacionproduccion (OID = 81612) : 
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
-- Structure for table presupuesto (OID = 81616) : 
--
CREATE TABLE public.presupuesto (
    idpresupuesto bigint DEFAULT nextval(('"public"."presupuesto_idpresupuesto_seq"'::text)::regclass) NOT NULL,
    fechapresupuesto date,
    montototal double precision,
    fechavencimiento date,
    nropresupuesto bigint
) WITH OIDS;
--
-- Structure for table plano (OID = 81620) : 
--
CREATE TABLE public.plano (
    idplano bigint DEFAULT nextval(('"public"."plano_idplano_seq"'::text)::regclass) NOT NULL,
    nroplano bigint,
    escala integer,
    pedido bigint,
    imagen bytea[]
) WITH OIDS;
--
-- Structure for table remito (OID = 81627) : 
--
CREATE TABLE public.remito (
    idremito bigint DEFAULT nextval(('"public"."remito_idremito_seq"'::text)::regclass) NOT NULL,
    nroremito bigint,
    fechaemision date,
    pedido bigint,
    estado bigint
) WITH OIDS;
--
-- Structure for table trabajotercerizado (OID = 81631) : 
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
    pedido bigint,
    fechadelingresocotizacion date,
    montototal double precision
) WITH OIDS;
--
-- Structure for table cliente (OID = 81635) : 
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
-- Structure for table tipoiva (OID = 81639) : 
--
CREATE TABLE public.tipoiva (
    idtipoiva bigint DEFAULT nextval(('"public"."tipoiva_idtipoiva_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table formadepago (OID = 81643) : 
--
CREATE TABLE public.formadepago (
    idformapago bigint DEFAULT nextval(('"public"."formadepago_idformapago_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table usuario (OID = 81647) : 
--
CREATE TABLE public.usuario (
    idusuario bigint DEFAULT nextval(('"public"."usuario_idusuario_seq"'::text)::regclass) NOT NULL,
    usuario character varying(50),
    clave character varying(50)
) WITH OIDS;
--
-- Structure for table estadofactura (OID = 81651) : 
--
CREATE TABLE public.estadofactura (
    idestado bigint DEFAULT nextval(('"public"."estadofactura_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table comprobantepago (OID = 81655) : 
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
-- Structure for table estadotrabajotercerizado (OID = 81659) : 
--
CREATE TABLE public.estadotrabajotercerizado (
    idestado bigint DEFAULT nextval(('"public"."estadotrabajotercerizado_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table empresametalurgica (OID = 81663) : 
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
-- Structure for table prioridad (OID = 81667) : 
--
CREATE TABLE public.prioridad (
    idprioridad bigint DEFAULT nextval(('"public"."prioridad_idprioridad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table estadocliente (OID = 81671) : 
--
CREATE TABLE public.estadocliente (
    idestado bigint DEFAULT nextval(('"public"."estadocliente_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table responsable (OID = 81675) : 
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
-- Structure for table condicioniva (OID = 81679) : 
--
CREATE TABLE public.condicioniva (
    idcondicioniva bigint DEFAULT nextval(('"public"."condicioniva_idcondicioniva_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table domicilio (OID = 81683) : 
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
-- Structure for table rotura (OID = 81687) : 
--
CREATE TABLE public.rotura (
    idrotura bigint DEFAULT nextval(('"public"."rotura_idrotura_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table categoria (OID = 81691) : 
--
CREATE TABLE public.categoria (
    idcategoria bigint DEFAULT nextval(('"public"."categoria_idcategoria_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table cargo (OID = 81695) : 
--
CREATE TABLE public.cargo (
    idcargo bigint DEFAULT nextval(('"public"."cargo_idcargo_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table turno (OID = 81699) : 
--
CREATE TABLE public.turno (
    idturno bigint DEFAULT nextval(('"public"."turno_idturno_seq"'::text)::regclass) NOT NULL,
    horainicio time without time zone,
    horafin time without time zone,
    nombre character varying(20),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table marca (OID = 81703) : 
--
CREATE TABLE public.marca (
    idmarca bigint DEFAULT nextval(('"public"."marca_idmarca_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadomaquina (OID = 81707) : 
--
CREATE TABLE public.estadomaquina (
    idestado bigint DEFAULT nextval(('"public"."estadomaquina_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table tipomaquina (OID = 81711) : 
--
CREATE TABLE public.tipomaquina (
    idtipomaquina bigint DEFAULT nextval(('"public"."tipomaquina_idtipomaquina_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecetapaprod (OID = 81715) : 
--
CREATE TABLE public.estadoejecetapaprod (
    idestado bigint DEFAULT nextval(('"public"."estadoejecetapaprod_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table servicio (OID = 81719) : 
--
CREATE TABLE public.servicio (
    idservicio bigint DEFAULT nextval(('"public"."servicio_idservicio_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table tipodocumento (OID = 81723) : 
--
CREATE TABLE public.tipodocumento (
    idtipodocumento bigint DEFAULT nextval(('"public"."tipodocumento_idtipodocumento_seq"'::text)::regclass) NOT NULL,
    tipo character varying(50),
    nombre character varying(50)
) WITH OIDS;
--
-- Structure for table codigodebarra (OID = 81727) : 
--
CREATE TABLE public.codigodebarra (
    idcodigo bigint DEFAULT nextval(('"public"."codigodebarra_idcodigo_seq"'::text)::regclass) NOT NULL,
    descripcion character varying(50),
    codigo character varying(100)
) WITH OIDS;
--
-- Structure for table estadopiezareal (OID = 81731) : 
--
CREATE TABLE public.estadopiezareal (
    idestado bigint DEFAULT nextval(('"public"."estadopiezareal_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecplanifpedido (OID = 81735) : 
--
CREATE TABLE public.estadoejecplanifpedido (
    idestado bigint DEFAULT nextval(('"public"."estadoejecplanifpedido_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table proveedormantenimientomaquina (OID = 81739) : 
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
-- Structure for table rol (OID = 81743) : 
--
CREATE TABLE public.rol (
    idrol bigint DEFAULT nextval(('"public"."rol_idrol_seq"'::text)::regclass) NOT NULL,
    rol character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table privilegio (OID = 81747) : 
--
CREATE TABLE public.privilegio (
    idprivilegio bigint DEFAULT nextval(('"public"."privilegio_idprivilegio_seq"'::text)::regclass) NOT NULL,
    privilegio character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table accioncalidad (OID = 81751) : 
--
CREATE TABLE public.accioncalidad (
    idaccioncalidad bigint DEFAULT nextval(('"public"."accioncalidad_idaccioncalidad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(150)
) WITH OIDS;
--
-- Structure for table empleado (OID = 81755) : 
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
-- Structure for table proveedor (OID = 81759) : 
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
-- Structure for table estadocompra (OID = 81763) : 
--
CREATE TABLE public.estadocompra (
    idestado bigint DEFAULT nextval(('"public"."estadocompra_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table sesion (OID = 81767) : 
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
-- Structure for table materiaprima (OID = 81771) : 
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
-- Structure for table matriz (OID = 81775) : 
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
-- Structure for table producto (OID = 81779) : 
--
CREATE TABLE public.producto (
    idproducto bigint DEFAULT nextval(('"public"."producto_idproducto_seq"'::text)::regclass) NOT NULL,
    nroproducto bigint,
    nombre character varying(50),
    preciounitario double precision,
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecplancalidad (OID = 81783) : 
--
CREATE TABLE public.estadoejecplancalidad (
    idestado bigint DEFAULT nextval(('"public"."estadoejecplancalidad_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table mantenimientopreventivo (OID = 81787) : 
--
CREATE TABLE public.mantenimientopreventivo (
    idmantenimientopreventivo bigint DEFAULT nextval(('"public"."mantenimientopreventivo_idmantenimientopreventivo_seq"'::text)::regclass) NOT NULL,
    fechamantenimientoprevisto date,
    fechaenviomantenimiento date,
    horaenviomantenimiento time without time zone,
    nromantenimietno bigint,
    fechafinmantenimientoreal date,
    proveedormantenimiento bigint,
    maquina bigint,
    periodo bigint,
    duraciontotal time without time zone
) WITH OIDS;
--
-- Structure for table mantenimientocorrectivo (OID = 81791) : 
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
-- Structure for table etapadeproduccion (OID = 81795) : 
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
-- Structure for table ejecucionplanificacionproduccion (OID = 81799) : 
--
CREATE TABLE public.ejecucionplanificacionproduccion (
    idejecucion bigint DEFAULT nextval(('"public"."ejecucionplanificacionproduccion_idejecucion_seq"'::text)::regclass) NOT NULL,
    idplanificacionproduccion bigint NOT NULL,
    fechainicio date,
    fechafin date,
    horainicio time without time zone,
    horafin time without time zone,
    estado bigint,
    nroejecucionplanificacion bigint,
    novedades character varying
) WITH OIDS;
--
-- Structure for table procesocalidad (OID = 81803) : 
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
-- Structure for table ejecucionplanificacioncalidad (OID = 81807) : 
--
CREATE TABLE public.ejecucionplanificacioncalidad (
    idejecucion bigint DEFAULT nextval(('"public"."ejecucionplanificacioncalidad_idejecucion_seq"'::text)::regclass) NOT NULL,
    idplanificacioncalidad bigint,
    fechainicio date,
    fechafin date,
    horainicio time without time zone,
    horafin time without time zone,
    estado bigint,
    nroejecucionplanificacioncalidad bigint
) WITH OIDS;
--
-- Structure for table ejecucionprocesocalidad (OID = 81811) : 
--
CREATE TABLE public.ejecucionprocesocalidad (
    idejecucion bigint DEFAULT nextval(('"public"."ejecucionprocesocalidad_idejecucion_seq"'::text)::regclass) NOT NULL,
    resultado character varying(100),
    estado bigint,
    nroejecucion bigint,
    fechafin date,
    fechainicio date,
    horainicio time(0) without time zone,
    horafin time(0) without time zone,
    nombre character varying(100),
    observacion character varying(250),
    empleado bigint
) WITH OIDS;
--
-- Structure for table ejecucionetapaproduccion (OID = 81815) : 
--
CREATE TABLE public.ejecucionetapaproduccion (
    id bigint DEFAULT nextval(('"public"."ejecucionetapaproduccion_idejecucion_seq"'::text)::regclass) NOT NULL,
    idetapaproduccion bigint NOT NULL,
    nombre character varying(50),
    totalhorasmaquina character varying(50),
    totalhorashombre character varying(50),
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
-- Structure for table compra (OID = 81819) : 
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
-- Structure for table detallemantenimientocorrectivo (OID = 81823) : 
--
CREATE TABLE public.detallemantenimientocorrectivo (
    idmantenimientocorrectivo bigint DEFAULT nextval(('"public"."detallemantenimientocorrectivo_idmantenimientocorrectivo_seq"'::text)::regclass) NOT NULL,
    iddetalle bigint NOT NULL,
    duracion time without time zone,
    rotura bigint,
    motivorotura character varying(100)
) WITH OIDS;
--
-- Structure for table detallemantenimientopreventivo (OID = 81827) : 
--
CREATE TABLE public.detallemantenimientopreventivo (
    idmantenimientopreventivo bigint DEFAULT nextval(('"public"."detallemantenimientopreventivo_idmantenimientopreventivo_seq"'::text)::regclass) NOT NULL,
    iddetalle bigint NOT NULL,
    duracion time without time zone,
    servicio bigint,
    observaciones character varying(100)
) WITH OIDS;
--
-- Structure for table detalleejecucionplanificacion (OID = 81831) : 
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
-- Structure for table detalleejecucionplanificacioncalidad (OID = 81835) : 
--
CREATE TABLE public.detalleejecucionplanificacioncalidad (
    iddetalle bigint DEFAULT nextval(('"public"."detalleejecucionplanificacioncalidad_iddetalle_seq"'::text)::regclass) NOT NULL,
    idejecucionplanificacioncalidad bigint NOT NULL,
    ejecucionprocesocalidad bigint,
    idprocesocalidad bigint,
    pieza bigint,
    piezareal bigint,
    orden integer,
    fechainicio date,
    horainicio time without time zone,
    fechafin date,
    horafin time without time zone
) WITH OIDS;
--
-- Structure for table detalleplanificacionproduccion (OID = 81839) : 
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
    idproducto bigint,
    indexproducto integer
) WITH OIDS;
--
-- Structure for table detalleproducto (OID = 81843) : 
--
CREATE TABLE public.detalleproducto (
    iddetalle bigint DEFAULT nextval(('"public"."detalleproducto_iddetalle_seq"'::text)::regclass) NOT NULL,
    idproducto bigint NOT NULL,
    cantidadpiezas integer,
    descripcion character varying(50),
    pieza bigint NOT NULL
) WITH OIDS;
--
-- Structure for table detallepedido (OID = 81847) : 
--
CREATE TABLE public.detallepedido (
    iddetalle bigint DEFAULT nextval(('"public"."detallepedido_iddetalle_seq"'::text)::regclass) NOT NULL,
    idpedido bigint NOT NULL,
    precio double precision,
    cantidad integer,
    producto bigint
) WITH OIDS;
--
-- Structure for table detalletrabajotercerizado (OID = 81851) : 
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
-- Structure for table detalleplanificacioncalidad (OID = 81855) : 
--
CREATE TABLE public.detalleplanificacioncalidad (
    iddetalle bigint DEFAULT nextval(('"public"."detalleplanificacioncalidad_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanificacioncalidad bigint NOT NULL,
    iddetalleejecucionplanificacioncalidad bigint,
    fechainicio date,
    fechafin date,
    horainicio time without time zone,
    horafin time without time zone,
    procesocalidad bigint,
    pieza bigint,
    producto bigint,
    empleado bigint,
    maquina bigint,
    orden integer
) WITH OIDS;
--
-- Structure for table detallecompra (OID = 81859) : 
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
-- Structure for table detalleremito (OID = 81863) : 
--
CREATE TABLE public.detalleremito (
    iddetalle bigint DEFAULT nextval(('"public"."detalleremito_iddetalle_seq"'::text)::regclass) NOT NULL,
    idremito bigint NOT NULL,
    cantidad integer,
    descripcion character varying(50),
    producto bigint
) WITH OIDS;
--
-- Structure for table tiporeclamo (OID = 81867) : 
--
CREATE TABLE public.tiporeclamo (
    idtiporeclamo bigint DEFAULT nextval(('"public"."tiporeclamo_idtiporeclamo_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table reclamoempresametalurgica (OID = 81871) : 
--
CREATE TABLE public.reclamoempresametalurgica (
    idreclamo bigint DEFAULT nextval(('"public"."reclamoempresametalurgica_idreclamo_seq"'::text)::regclass) NOT NULL,
    nroreclamo bigint,
    tiporeclamo bigint,
    motivo character varying(50),
    fechareclamo date,
    trabajotercerizado bigint,
    idestadoreclamo bigint
) WITH OIDS;
--
-- Structure for table reclamoproveedor (OID = 81875) : 
--
CREATE TABLE public.reclamoproveedor (
    idreclamo bigint DEFAULT nextval(('"public"."reclamoproveedor_idreclamo_seq"'::text)::regclass) NOT NULL,
    nroreclamo bigint,
    tiporeclamo bigint,
    motivo character varying(50),
    fechareclamo date,
    compra bigint,
    idestadoreclamo bigint
) WITH OIDS;
--
-- Structure for table reclamocliente (OID = 81879) : 
--
CREATE TABLE public.reclamocliente (
    idreclamo bigint DEFAULT nextval(('"public"."reclamocliente_idreclamo_seq"'::text)::regclass) NOT NULL,
    nroreclamo bigint,
    tiporeclamo bigint,
    motivo character varying(100),
    fechareclamo date,
    cliente bigint,
    idestadoreclamo bigint
) WITH OIDS;
--
-- Structure for table detallereclamocliente (OID = 81883) : 
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
-- Structure for table detallereclamoproveedor (OID = 81887) : 
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
-- Structure for table detallereclamoempresametalurgica (OID = 81891) : 
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
-- Structure for table proveedorxmateriaprima (OID = 81895) : 
--
CREATE TABLE public.proveedorxmateriaprima (
    idproveedor bigint NOT NULL,
    idmateriaprima bigint NOT NULL,
    precio double precision
) WITH OIDS;
--
-- Structure for table maquinaxejecucionetapaproduccion (OID = 81898) : 
--
CREATE TABLE public.maquinaxejecucionetapaproduccion (
    idejecucionetapaproduccion bigint NOT NULL,
    idetapaproduccion bigint NOT NULL,
    idmaquina bigint NOT NULL,
    horasmaquina time without time zone,
    horashombre time without time zone
) WITH OIDS;
--
-- Structure for table maquinaxprocesocalidad (OID = 81901) : 
--
CREATE TABLE public.maquinaxprocesocalidad (
    idprocesocalidad bigint NOT NULL,
    idmaquina bigint NOT NULL,
    duracion time without time zone,
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table piezaxetapadeproduccion (OID = 81904) : 
--
CREATE TABLE public.piezaxetapadeproduccion (
    idpieza bigint NOT NULL,
    idetapaproduccion bigint NOT NULL,
    duracion time without time zone,
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table empleadoxturno (OID = 81907) : 
--
CREATE TABLE public.empleadoxturno (
    idempleado bigint NOT NULL,
    idturno bigint NOT NULL
) WITH OIDS;
--
-- Structure for table provincia (OID = 81910) : 
--
CREATE TABLE public.provincia (
    idprovincia bigint DEFAULT nextval(('"public"."provincia_idprovincia_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50)
) WITH OIDS;
--
-- Structure for table localidad (OID = 81914) : 
--
CREATE TABLE public.localidad (
    idlocalidad bigint DEFAULT nextval(('"public"."localidad_idlocalidad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    provincia bigint
) WITH OIDS;
--
-- Structure for table barrio (OID = 81918) : 
--
CREATE TABLE public.barrio (
    idbarrio bigint DEFAULT nextval(('"public"."barrio_idbarrio_seq"'::text)::regclass) NOT NULL,
    nombre character varying(25),
    codpostal bigint,
    localidad bigint
) WITH OIDS;
--
-- Structure for table usuarioxrol (OID = 81922) : 
--
CREATE TABLE public.usuarioxrol (
    idrol bigint NOT NULL,
    idusuario bigint NOT NULL
) WITH OIDS;
--
-- Structure for table rolxprivilegio (OID = 81925) : 
--
CREATE TABLE public.rolxprivilegio (
    idrol bigint NOT NULL,
    idprivilegio bigint NOT NULL
) WITH OIDS;
--
-- Structure for table planrequerimientosmateriaprima (OID = 81928) : 
--
CREATE TABLE public.planrequerimientosmateriaprima (
    idplanrequerimientosmateriaprima bigint DEFAULT nextval(('"public"."planrequerimientosmateriaprima_idplanrequerimientosmateriaprima_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table planprocedimientos (OID = 81932) : 
--
CREATE TABLE public.planprocedimientos (
    idplanprocedimientos bigint DEFAULT nextval(('"public"."planprocedimientos_idplanprocedimientos_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table planprocesoscalidad (OID = 81936) : 
--
CREATE TABLE public.planprocesoscalidad (
    idplanprocesoscalidad bigint DEFAULT nextval(('"public"."planprocesoscalidad_idplanprocesoscalidad_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table detallerequerimientosmateriaprima (OID = 81940) : 
--
CREATE TABLE public.detallerequerimientosmateriaprima (
    iddetalle bigint DEFAULT nextval(('"public"."detallerequerimientosmateriaprima_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanrequerimientosmateriaprima bigint NOT NULL,
    cantidadmateriaprima integer,
    idpieza bigint,
    idmateriaprima bigint
) WITH OIDS;
--
-- Structure for table detalleplanprocedimientos (OID = 81944) : 
--
CREATE TABLE public.detalleplanprocedimientos (
    iddetalle bigint DEFAULT nextval(('"public"."detalleplanprocedimientos_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanpprocedimientos bigint NOT NULL,
    idpieza bigint,
    idetapaproduccion bigint,
    duracionestimada time without time zone
) WITH OIDS;
--
-- Structure for table detalleplanprocesoscalidad (OID = 81948) : 
--
CREATE TABLE public.detalleplanprocesoscalidad (
    iddetalle bigint DEFAULT nextval(('"public"."detalleplanprocesoscalidad_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanprocesoscalidad bigint NOT NULL,
    idpieza bigint,
    idprocesocalidad bigint,
    duracionestimada time without time zone
) WITH OIDS;
--
-- Structure for table pedidomatriz (OID = 81952) : 
--
CREATE TABLE public.pedidomatriz (
    idpedidomatriz bigint DEFAULT nextval(('"public"."pedidomatriz_idpedidomatriz_seq"'::text)::regclass) NOT NULL,
    nropedidomatriz bigint,
    fechapedidomatriz date,
    idmatriz bigint,
    observaciones character varying(100)
) WITH OIDS;
--
-- Structure for table estadodetallecompra (OID = 81956) : 
--
CREATE TABLE public.estadodetallecompra (
    idestado bigint DEFAULT nextval(('"public"."estadodetallecompra_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadodetalletrabajotercerizado (OID = 81960) : 
--
CREATE TABLE public.estadodetalletrabajotercerizado (
    idestado bigint DEFAULT nextval(('"public"."estadodetalletrabajotercerizado_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecucionprocesocalidad (OID = 81964) : 
--
CREATE TABLE public.estadoejecucionprocesocalidad (
    idestado bigint DEFAULT nextval(('"public"."estadoejecucionprocesocalidad_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table detalleproductoreal (OID = 81968) : 
--
CREATE TABLE public.detalleproductoreal (
    iddetalle bigint DEFAULT nextval(('"public"."detalleproductoreal_iddetalle_seq"'::text)::regclass) NOT NULL,
    idproductoreal bigint NOT NULL,
    "cantidadPiezas" integer,
    descripcion character varying(50),
    idpiezareal bigint,
    detalleproducto bigint
) WITH OIDS;
--
-- Structure for table productoreal (OID = 81972) : 
--
CREATE TABLE public.productoreal (
    idproductoreal bigint DEFAULT nextval(('"public"."productoreal_idproductoreal_seq"'::text)::regclass) NOT NULL,
    nroproducto bigint,
    fechaterminacion date,
    fechainicioproduccion date,
    idpedido bigint,
    codigobarra bigint,
    estado bigint,
    producto bigint
) WITH OIDS;
--
-- Structure for table estadoproductoreal (OID = 81976) : 
--
CREATE TABLE public.estadoproductoreal (
    idestado bigint DEFAULT nextval(('"public"."estadoproductoreal_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table detallefactura (OID = 81980) : 
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
-- Structure for table detallepresupuesto (OID = 81984) : 
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
-- Structure for table estadoremito (OID = 81988) : 
--
CREATE TABLE public.estadoremito (
    idestado bigint DEFAULT nextval(('"public"."estadoremito_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table tipomaterial (OID = 81992) : 
--
CREATE TABLE public.tipomaterial (
    idtipomaterial bigint DEFAULT nextval(('"public"."tipomaterial_idtipomaterial_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Definition for sequence prueba_id_seq (OID = 81996) : 
--
CREATE SEQUENCE public.prueba_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table prueba (OID = 81998) : 
--
CREATE TABLE public.prueba (
    id bigint DEFAULT nextval('prueba_id_seq'::regclass) NOT NULL,
    valor character varying(20)
) WITH OIDS;
ALTER TABLE ONLY public.prueba ALTER COLUMN id SET STATISTICS 0;
ALTER TABLE ONLY public.prueba ALTER COLUMN valor SET STATISTICS 0;
--
-- Definition for sequence usuario_idusuario_seq (OID = 82002) : 
--
CREATE SEQUENCE public.usuario_idusuario_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipomaterial_idtipomaterial_seq (OID = 82004) : 
--
CREATE SEQUENCE public.tipomaterial_idtipomaterial_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence accioncalidad_idaccioncalidad_seq (OID = 82006) : 
--
CREATE SEQUENCE public.accioncalidad_idaccioncalidad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence barrio_idbarrio_seq (OID = 82008) : 
--
CREATE SEQUENCE public.barrio_idbarrio_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence cargo_idcargo_seq (OID = 82010) : 
--
CREATE SEQUENCE public.cargo_idcargo_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence categoria_idcategoria_seq (OID = 82012) : 
--
CREATE SEQUENCE public.categoria_idcategoria_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence cliente_idcliente_seq (OID = 82014) : 
--
CREATE SEQUENCE public.cliente_idcliente_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence codigodebarra_idcodigo_seq (OID = 82016) : 
--
CREATE SEQUENCE public.codigodebarra_idcodigo_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence compra_idcompra_seq (OID = 82018) : 
--
CREATE SEQUENCE public.compra_idcompra_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence comprobantepago_idcomprobantepago_seq (OID = 82020) : 
--
CREATE SEQUENCE public.comprobantepago_idcomprobantepago_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence condicioniva_idcondicioniva_seq (OID = 82022) : 
--
CREATE SEQUENCE public.condicioniva_idcondicioniva_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanificacionproduccion_id_seq (OID = 82024) : 
--
CREATE SEQUENCE public.detalleplanificacionproduccion_id_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallecompra_iddetalle_seq (OID = 82026) : 
--
CREATE SEQUENCE public.detallecompra_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleejecucionplanificacion_iddetalle_seq (OID = 82028) : 
--
CREATE SEQUENCE public.detalleejecucionplanificacion_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleejecucionplanificacioncalidad_iddetalle_seq (OID = 82030) : 
--
CREATE SEQUENCE public.detalleejecucionplanificacioncalidad_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallefactura_iddetalle_seq (OID = 82032) : 
--
CREATE SEQUENCE public.detallefactura_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallemantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 82034) : 
--
CREATE SEQUENCE public.detallemantenimientocorrectivo_idmantenimientocorrectivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallemantenimientopreventivo_idmantenimientopreventivo_seq (OID = 82036) : 
--
CREATE SEQUENCE public.detallemantenimientopreventivo_idmantenimientopreventivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallepedido_iddetalle_seq (OID = 82038) : 
--
CREATE SEQUENCE public.detallepedido_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanificacioncalidad_iddetalle_seq (OID = 82040) : 
--
CREATE SEQUENCE public.detalleplanificacioncalidad_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanprocedimientos_iddetalle_seq (OID = 82042) : 
--
CREATE SEQUENCE public.detalleplanprocedimientos_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanprocesoscalidad_iddetalle_seq (OID = 82044) : 
--
CREATE SEQUENCE public.detalleplanprocesoscalidad_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallepresupuesto_iddetalle_seq (OID = 82046) : 
--
CREATE SEQUENCE public.detallepresupuesto_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleproducto_iddetalle_seq (OID = 82048) : 
--
CREATE SEQUENCE public.detalleproducto_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleproductoreal_iddetalle_seq (OID = 82050) : 
--
CREATE SEQUENCE public.detalleproductoreal_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamocliente_iddetalle_seq (OID = 82052) : 
--
CREATE SEQUENCE public.detallereclamocliente_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamoempresametalurgica_iddetalle_seq (OID = 82054) : 
--
CREATE SEQUENCE public.detallereclamoempresametalurgica_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamoproveedor_iddetalle_seq (OID = 82056) : 
--
CREATE SEQUENCE public.detallereclamoproveedor_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleremito_iddetalle_seq (OID = 82058) : 
--
CREATE SEQUENCE public.detalleremito_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallerequerimientosmateriaprima_iddetalle_seq (OID = 82060) : 
--
CREATE SEQUENCE public.detallerequerimientosmateriaprima_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalletrabajotercerizado_iddetalle_seq (OID = 82062) : 
--
CREATE SEQUENCE public.detalletrabajotercerizado_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence domicilio_iddomicilio_seq (OID = 82064) : 
--
CREATE SEQUENCE public.domicilio_iddomicilio_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionetapaproduccion_idejecucion_seq (OID = 82066) : 
--
CREATE SEQUENCE public.ejecucionetapaproduccion_idejecucion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionplanificacioncalidad_idejecucion_seq (OID = 82068) : 
--
CREATE SEQUENCE public.ejecucionplanificacioncalidad_idejecucion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionplanificacionproduccion_idejecucion_seq (OID = 82070) : 
--
CREATE SEQUENCE public.ejecucionplanificacionproduccion_idejecucion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionprocesocalidad_idejecucion_seq (OID = 82072) : 
--
CREATE SEQUENCE public.ejecucionprocesocalidad_idejecucion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence empleado_idempleado_seq (OID = 82074) : 
--
CREATE SEQUENCE public.empleado_idempleado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence empresametalurgica_idempresametalurgica_seq (OID = 82076) : 
--
CREATE SEQUENCE public.empresametalurgica_idempresametalurgica_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadocliente_idestado_seq (OID = 82078) : 
--
CREATE SEQUENCE public.estadocliente_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadocompra_idestado_seq (OID = 82080) : 
--
CREATE SEQUENCE public.estadocompra_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadodetallecompra_idestado_seq (OID = 82082) : 
--
CREATE SEQUENCE public.estadodetallecompra_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadodetalletrabajotercerizado_idestado_seq (OID = 82084) : 
--
CREATE SEQUENCE public.estadodetalletrabajotercerizado_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecetapaprod_idestado_seq (OID = 82086) : 
--
CREATE SEQUENCE public.estadoejecetapaprod_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecplancalidad_idestado_seq (OID = 82088) : 
--
CREATE SEQUENCE public.estadoejecplancalidad_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecplanifpedido_idestado_seq (OID = 82090) : 
--
CREATE SEQUENCE public.estadoejecplanifpedido_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecucionprocesocalidad_idestado_seq (OID = 82092) : 
--
CREATE SEQUENCE public.estadoejecucionprocesocalidad_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadofactura_idestado_seq (OID = 82094) : 
--
CREATE SEQUENCE public.estadofactura_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadomaquina_idestado_seq (OID = 82096) : 
--
CREATE SEQUENCE public.estadomaquina_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadopedido_idestado_seq (OID = 82098) : 
--
CREATE SEQUENCE public.estadopedido_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadopiezareal_idestado_seq (OID = 82100) : 
--
CREATE SEQUENCE public.estadopiezareal_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoproductoreal_idestado_seq (OID = 82102) : 
--
CREATE SEQUENCE public.estadoproductoreal_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoremito_idestado_seq (OID = 82104) : 
--
CREATE SEQUENCE public.estadoremito_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadotrabajotercerizado_idestado_seq (OID = 82106) : 
--
CREATE SEQUENCE public.estadotrabajotercerizado_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence etapadeproduccion_idetapaproduccion_seq (OID = 82108) : 
--
CREATE SEQUENCE public.etapadeproduccion_idetapaproduccion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence factura_idfactura_seq (OID = 82110) : 
--
CREATE SEQUENCE public.factura_idfactura_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence formadepago_idformapago_seq (OID = 82112) : 
--
CREATE SEQUENCE public.formadepago_idformapago_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence localidad_idlocalidad_seq (OID = 82114) : 
--
CREATE SEQUENCE public.localidad_idlocalidad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence mantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 82116) : 
--
CREATE SEQUENCE public.mantenimientocorrectivo_idmantenimientocorrectivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence mantenimientopreventivo_idmantenimientopreventivo_seq (OID = 82118) : 
--
CREATE SEQUENCE public.mantenimientopreventivo_idmantenimientopreventivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence maquina_idmaquina_seq (OID = 82120) : 
--
CREATE SEQUENCE public.maquina_idmaquina_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence marca_idmarca_seq (OID = 82122) : 
--
CREATE SEQUENCE public.marca_idmarca_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence materiaprima_idmateriaprima_seq (OID = 82124) : 
--
CREATE SEQUENCE public.materiaprima_idmateriaprima_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence matriz_idmatriz_seq (OID = 82126) : 
--
CREATE SEQUENCE public.matriz_idmatriz_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pedido_idpedido_seq (OID = 82128) : 
--
CREATE SEQUENCE public.pedido_idpedido_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pedidomatriz_idpedidomatriz_seq (OID = 82130) : 
--
CREATE SEQUENCE public.pedidomatriz_idpedidomatriz_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pieza_idpieza_seq (OID = 82132) : 
--
CREATE SEQUENCE public.pieza_idpieza_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence piezareal_idpiezareal_seq (OID = 82134) : 
--
CREATE SEQUENCE public.piezareal_idpiezareal_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planificacioncalidad_idplanificacion_seq (OID = 82136) : 
--
CREATE SEQUENCE public.planificacioncalidad_idplanificacion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planificacionproduccion_idplanificacionproduccion_seq (OID = 82138) : 
--
CREATE SEQUENCE public.planificacionproduccion_idplanificacionproduccion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence plano_idplano_seq (OID = 82140) : 
--
CREATE SEQUENCE public.plano_idplano_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planprocedimientos_idplanprocedimientos_seq (OID = 82142) : 
--
CREATE SEQUENCE public.planprocedimientos_idplanprocedimientos_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planprocesoscalidad_idplanprocesoscalidad_seq (OID = 82144) : 
--
CREATE SEQUENCE public.planprocesoscalidad_idplanprocesoscalidad_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planrequerimientosmateriaprima_idplanrequerimientosmateriaprima (OID = 82146) : 
--
CREATE SEQUENCE public.planrequerimientosmateriaprima_idplanrequerimientosmateriaprima
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence presupuesto_idpresupuesto_seq (OID = 82148) : 
--
CREATE SEQUENCE public.presupuesto_idpresupuesto_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence prioridad_idprioridad_seq (OID = 82150) : 
--
CREATE SEQUENCE public.prioridad_idprioridad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence privilegio_idprivilegio_seq (OID = 82152) : 
--
CREATE SEQUENCE public.privilegio_idprivilegio_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence procesocalidad_idprocesocalidad_seq (OID = 82154) : 
--
CREATE SEQUENCE public.procesocalidad_idprocesocalidad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence producto_idproducto_seq (OID = 82156) : 
--
CREATE SEQUENCE public.producto_idproducto_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence productoreal_idproductoreal_seq (OID = 82158) : 
--
CREATE SEQUENCE public.productoreal_idproductoreal_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence proveedor_idproveedor_seq (OID = 82160) : 
--
CREATE SEQUENCE public.proveedor_idproveedor_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence proveedormantenimientomaquina_idproveedormantenimiento_seq (OID = 82162) : 
--
CREATE SEQUENCE public.proveedormantenimientomaquina_idproveedormantenimiento_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence provincia_idprovincia_seq (OID = 82164) : 
--
CREATE SEQUENCE public.provincia_idprovincia_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamocliente_idreclamo_seq (OID = 82166) : 
--
CREATE SEQUENCE public.reclamocliente_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamoempresametalurgica_idreclamo_seq (OID = 82168) : 
--
CREATE SEQUENCE public.reclamoempresametalurgica_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamoproveedor_idreclamo_seq (OID = 82170) : 
--
CREATE SEQUENCE public.reclamoproveedor_idreclamo_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence remito_idremito_seq (OID = 82172) : 
--
CREATE SEQUENCE public.remito_idremito_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence responsable_idresponsable_seq (OID = 82174) : 
--
CREATE SEQUENCE public.responsable_idresponsable_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence rol_idrol_seq (OID = 82176) : 
--
CREATE SEQUENCE public.rol_idrol_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence rotura_idrotura_seq (OID = 82178) : 
--
CREATE SEQUENCE public.rotura_idrotura_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence servicio_idservicio_seq (OID = 82180) : 
--
CREATE SEQUENCE public.servicio_idservicio_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence sesion_idsesion_seq (OID = 82182) : 
--
CREATE SEQUENCE public.sesion_idsesion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipodocumento_idtipodocumento_seq (OID = 82184) : 
--
CREATE SEQUENCE public.tipodocumento_idtipodocumento_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipoiva_idtipoiva_seq (OID = 82186) : 
--
CREATE SEQUENCE public.tipoiva_idtipoiva_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipomaquina_idtipomaquina_seq (OID = 82188) : 
--
CREATE SEQUENCE public.tipomaquina_idtipomaquina_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tiporeclamo_idtiporeclamo_seq (OID = 82190) : 
--
CREATE SEQUENCE public.tiporeclamo_idtiporeclamo_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence trabajotercerizado_idtrabajo_seq (OID = 82192) : 
--
CREATE SEQUENCE public.trabajotercerizado_idtrabajo_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence turno_idturno_seq (OID = 82194) : 
--
CREATE SEQUENCE public.turno_idturno_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for view viewdetallepedidocotizacion (OID = 82196) : 
--
CREATE VIEW public.viewdetallepedidocotizacion AS
SELECT p.nroproducto, p.nombre, p.descripcion, dp.cantidad,
    p.preciounitario AS precio, p.idproducto, dp.iddetalle, ped.idpedido
FROM producto p, pedido ped, detallepedido dp
WHERE ((dp.producto = p.idproducto) AND (ped.idpedido = dp.idpedido))
ORDER BY p.nombre;

--
-- Definition for view viewpedidoendetalleprocedimientos (OID = 82200) : 
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
-- Definition for sequence unidadmedida_idunidadmedida_seq (OID = 82204) : 
--
CREATE SEQUENCE public.unidadmedida_idunidadmedida_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table unidadmedida (OID = 82206) : 
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
-- Definition for view viewetapadeproduccion (OID = 82210) : 
--
CREATE VIEW public.viewetapadeproduccion AS
SELECT ep.nroetapaproduccion AS numero, ep.nombre, ep.horashombre,
    ep.horasmaquina, ep.duracionestimada, ep.idetapaproduccion AS idetapa
FROM etapadeproduccion ep;

--
-- Definition for sequence detallepiezapresupuesto_iddetalle_seq (OID = 82214) : 
--
CREATE SEQUENCE public.detallepiezapresupuesto_iddetalle_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detallepiezapresupuesto (OID = 82216) : 
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
-- Definition for sequence detalleproductopresupuesto_iddetalle_seq (OID = 82220) : 
--
CREATE SEQUENCE public.detalleproductopresupuesto_iddetalle_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detalleproductopresupuesto (OID = 82222) : 
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
-- Definition for sequence detallepiezacalidadpresupuesto_iddetalle_seq (OID = 82226) : 
--
CREATE SEQUENCE public.detallepiezacalidadpresupuesto_iddetalle_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detallepiezacalidadpresupuesto (OID = 82228) : 
--
CREATE TABLE public.detallepiezacalidadpresupuesto (
    iddetalle bigint DEFAULT nextval('detallepiezacalidadpresupuesto_iddetalle_seq'::regclass) NOT NULL,
    cantprocesocalidad integer,
    duracionxpieza time without time zone,
    idprocesocalidad bigint,
    iddetalleproductopresupuesto bigint
) WITH OIDS;
--
-- Definition for view viewprocesocalidad (OID = 82232) : 
--
CREATE VIEW public.viewprocesocalidad AS
SELECT pc.nroproceso, pc.nombre AS nombreproceso, pc.duracionestimada,
    pc.herramienta, ac.nombre AS nombreaccioncalidad, pc.idprocesocalidad
FROM procesocalidad pc, accioncalidad ac
WHERE (pc.accioncalidad = ac.idaccioncalidad)
ORDER BY pc.nombre;

--
-- Definition for view viewproveedorxmateriaprima (OID = 82236) : 
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
-- Definition for view viewmateriaprima (OID = 82241) : 
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
-- Definition for view viewproductopresupuesto (OID = 82245) : 
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
-- Definition for sequence calendario_id_seq (OID = 82249) : 
--
CREATE SEQUENCE public.calendario_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table calendario (OID = 82251) : 
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
-- Definition for view viewpedidosnoconfirmados (OID = 82255) : 
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
-- Definition for sequence disponibilidadhoraria_id_seq (OID = 82259) : 
--
CREATE SEQUENCE public.disponibilidadhoraria_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table disponibilidadhoraria (OID = 82261) : 
--
CREATE TABLE public.disponibilidadhoraria (
    id bigint DEFAULT nextval('disponibilidadhoraria_id_seq'::regclass) NOT NULL,
    fecha date,
    tiempodisponible time without time zone,
    idempleado bigint,
    horainicio time without time zone,
    horafin time without time zone
) WITH OIDS;
--
-- Definition for sequence estadoplanificacionproduccion_id_seq (OID = 82265) : 
--
CREATE SEQUENCE public.estadoplanificacionproduccion_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table estadoplanificacionproduccion (OID = 82267) : 
--
CREATE TABLE public.estadoplanificacionproduccion (
    id bigint DEFAULT nextval('estadoplanificacionproduccion_id_seq'::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying(250)
) WITH OIDS;
--
-- Definition for sequence maquina_idmaquina_seq1 (OID = 82274) : 
--
CREATE SEQUENCE public.maquina_idmaquina_seq1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table maquina (OID = 82276) : 
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
-- Definition for sequence piezareal_idpiezareal_seq1 (OID = 82280) : 
--
CREATE SEQUENCE public.piezareal_idpiezareal_seq1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table piezareal (OID = 82282) : 
--
CREATE TABLE public.piezareal (
    idpiezareal bigint DEFAULT nextval('piezareal_idpiezareal_seq1'::regclass) NOT NULL,
    idpieza bigint NOT NULL,
    estado bigint,
    nropieza integer,
    idcodigobarra bigint
) WITH OIDS;
--
-- Definition for sequence pieza_idpieza_seq1 (OID = 82286) : 
--
CREATE SEQUENCE public.pieza_idpieza_seq1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table pieza (OID = 82288) : 
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
-- Definition for sequence detallempasignada_id_seq (OID = 82292) : 
--
CREATE SEQUENCE public.detallempasignada_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detallempasignada (OID = 82294) : 
--
CREATE TABLE public.detallempasignada (
    id bigint DEFAULT nextval('detallempasignada_id_seq'::regclass) NOT NULL,
    idmateriaprima bigint,
    cantidadmp integer,
    idplanificacionproduccion bigint
) WITH OIDS;
--
-- Definition for sequence mpasignadaxpiezareal_id_seq (OID = 82298) : 
--
CREATE SEQUENCE public.mpasignadaxpiezareal_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table mpasignadaxpiezareal (OID = 82300) : 
--
CREATE TABLE public.mpasignadaxpiezareal (
    idpiezareal bigint,
    iddetallempasignada bigint,
    id bigint DEFAULT nextval('mpasignadaxpiezareal_id_seq'::regclass) NOT NULL
) WITH OIDS;
--
-- Definition for view viewcantidadmpasiganda (OID = 82304) : 
--
CREATE VIEW public.viewcantidadmpasiganda AS
SELECT ppro.pedido, dmpa.idmateriaprima, sum(dmpa.cantidadmp) AS cantidad
FROM planificacionproduccion ppro, detallempasignada dmpa
WHERE (ppro.idplanificacionproduccion = dmpa.idplanificacionproduccion)
GROUP BY ppro.pedido, dmpa.idmateriaprima;

--
-- Structure for table asistencia (OID = 82308) : 
--
CREATE TABLE public.asistencia (
    empleado bigint NOT NULL,
    fechaingreso date NOT NULL,
    horaingreso time without time zone NOT NULL,
    horaegreso time without time zone,
    observaciones character varying(100)
) WITH OIDS;
--
-- Definition for view viewcantidadmpenpedido (OID = 82315) : 
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
-- Definition for view viewmpxpiezapresupuesto (OID = 82319) : 
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
-- Definition for view viewetapasxpiezapresupuesto (OID = 82323) : 
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
-- Definition for view viewprocalidadxpiezapresupesto (OID = 82327) : 
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
-- Definition for view viewpedidosconmpasignada (OID = 82332) : 
--
CREATE VIEW public.viewpedidosconmpasignada AS
SELECT ped.nropedido, planpro.nroplanificacion AS
    nroplanificacionproduccion, planpro.fechacreacion,
    planpro.fechainicioprevista, planpro.fechafinprevista,
    planpro.observaciones, ped.idpedido, planpro.idplanificacionproduccion
FROM pedido ped, planificacionproduccion planpro
WHERE ((ped.idpedido = planpro.pedido) AND (ped.estado = 3));

--
-- Definition for view viewdetalleproducto (OID = 82336) : 
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
-- Definition for view viewpedidosconplanifsinrecursos (OID = 82340) : 
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
-- Definition for view viewpedidosclientesegunestado (OID = 82345) : 
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
-- Definition for sequence reclamoempresamantenimiento_idreclamo_seq (OID = 84750) : 
--
CREATE SEQUENCE public.reclamoempresamantenimiento_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table reclamoempresamantenimiento (OID = 84752) : 
--
CREATE TABLE public.reclamoempresamantenimiento (
    idreclamo bigint DEFAULT nextval('reclamoempresamantenimiento_idreclamo_seq'::regclass) NOT NULL,
    nroreclamo bigint,
    tiporeclamo bigint,
    motivo character varying(50),
    fechareclamo date,
    trabajotercerizado bigint,
    idestadoreclamo bigint
) WITH OIDS;
--
-- Definition for sequence detallereclamoempresamantenimiento_iddetalle_seq (OID = 84793) : 
--
CREATE SEQUENCE public.detallereclamoempresamantenimiento_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detallereclamoempresamantenimiento (OID = 84795) : 
--
CREATE TABLE public.detallereclamoempresamantenimiento (
    iddetalle bigint DEFAULT nextval('detallereclamoempresamantenimiento_iddetalle_seq'::regclass) NOT NULL,
    idreclamo bigint NOT NULL,
    cantidad integer,
    descripcion character varying(50),
    motivo character varying(50),
    pieza bigint,
    fechaegreso date,
    iddetalletrabajo bigint,
    idtrabajo bigint
) WITH OIDS;
--
-- Definition for sequence estadoreclamo_idestadoreclamo_seq (OID = 84886) : 
--
CREATE SEQUENCE public.estadoreclamo_idestadoreclamo_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table estadoreclamo (OID = 84888) : 
--
CREATE TABLE public.estadoreclamo (
    idestadoreclamo bigint DEFAULT nextval('estadoreclamo_idestadoreclamo_seq'::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(200)
) WITH OIDS;
--
-- Definition for view viewpedidosconrecasignados (OID = 85118) : 
--
CREATE VIEW public.viewpedidosconrecasignados AS
SELECT pe.nropedido, pe.nropedidocotizacioncliente, cli.razonsocial,
    pri.nombre AS prioridad, pe.fechaentregaestipulada, pe.idpedido,
    cli.idcliente, pri.idprioridad, pe.presupuesto, pp.idestado
FROM pedido pe, planificacionproduccion pp, planificacioncalidad pc,
    cliente cli, prioridad pri
WHERE (((((pe.idpedido = pp.pedido) AND (pe.idpedido = pc.pedido)) AND
    (cli.idcliente = pe.cliente)) AND (pri.idprioridad = pe.prioridad)) AND
    (pe.estado = 19));

--
-- Definition for view viewpedidosconplanificacionproduccion (OID = 85217) : 
--
CREATE VIEW public.viewpedidosconplanificacionproduccion AS
SELECT pe.nropedido, pe.nropedidocotizacioncliente, cli.razonsocial,
    pri.nombre AS prioridad, pe.fechaentregaestipulada,
    pp.fechafinprevista, pp.idplanificacionproduccion, pe.idpedido,
    cli.idcliente, pri.idprioridad, pe.presupuesto, pp.idestado
FROM pedido pe, planificacionproduccion pp, cliente cli, prioridad pri
WHERE ((((pe.idpedido = pp.pedido) AND (cli.idcliente = pe.cliente)) AND
    (pri.idprioridad = pe.prioridad)) AND (pe.estado = 5));

--
-- Definition for view viewpedidosproduccionfin (OID = 85289) : 
--
CREATE VIEW public.viewpedidosproduccionfin AS
SELECT p.nropedido, pc.nroplanificacion AS nroplanificacionproduccion,
    pc.fechacreacion, pc.fechainicioprevista, pc.fechafinprevista,
    pc.observaciones, p.idpedido, pc.idplanificacion AS
    idplanificacioncalidad, pp.idplanificacionproduccion, epp.idejecucion
    AS idejecplanifproduccion
FROM pedido p, planificacioncalidad pc, ejecucionplanificacionproduccion
    epp, planificacionproduccion pp, cliente c
WHERE ((((((p.cliente = c.idcliente) AND (p.idpedido = pc.pedido)) AND
    (p.idpedido = pp.pedido)) AND (pp.idplanificacionproduccion =
    epp.idplanificacionproduccion)) AND (epp.estado = 4)) AND (p.estado = 6));

--
-- Data for table public.pedido (OID = 81596) (LIMIT 0,18)
--
INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (9, NULL, '2011-06-25', '2011-06-20', NULL, NULL, 5, NULL, 54, '2011-06-11', '', false, 2342432, '2011-06-20', 36, 5, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (8, NULL, '2011-06-12', '2011-06-11', NULL, NULL, 5, NULL, 53, '2011-06-09', '', false, 3434534, '2011-06-11', 35, 5, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (7, NULL, '2011-06-11', '2011-06-09', NULL, NULL, 5, NULL, 52, '2011-06-17', '', false, 4523544, '2011-06-09', 34, 5, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (17, NULL, NULL, '2011-09-20', NULL, NULL, 4, NULL, NULL, '2011-09-20', NULL, true, NULL, '2011-09-20', 45, 4, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (16, NULL, '2011-09-26', '2011-09-19', NULL, '2011-09-20', 4, 4, 63, '2011-09-23', '', false, 5676, '2011-09-19', 44, 5, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (14, NULL, '2011-09-06', '2011-09-04', NULL, NULL, 4, NULL, 61, '2011-09-15', '', false, 34645, '2011-09-04', 41, 4, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (4, NULL, '2011-04-04', '2011-04-03', NULL, NULL, 4, NULL, 49, '2011-04-22', '', false, 239864, '2011-04-03', 31, 23, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (15, NULL, '2011-09-19', '2011-09-19', NULL, NULL, 4, NULL, 62, '2011-09-24', '', false, 43554345, '2011-09-19', 43, 5, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (18, NULL, '2011-09-22', '2011-09-20', NULL, '2011-09-26', 4, 5, 64, '2011-09-29', '', false, 454545, '2011-09-20', 46, 24, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (10, NULL, '2011-07-19', '2011-07-18', NULL, NULL, 6, NULL, 55, '2011-07-20', '', false, 345345, '2011-07-18', 37, 4, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (6, NULL, '2011-04-29', '2011-04-25', NULL, NULL, 6, NULL, 51, '2011-04-23', '', false, 324234, '2011-04-25', 33, 5, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (19, NULL, NULL, '2011-09-26', NULL, NULL, 4, NULL, NULL, '2011-09-30', NULL, true, NULL, '2011-09-26', 47, 4, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (12, NULL, '2011-08-23', '2011-08-22', NULL, NULL, 4, NULL, 57, '2011-08-31', '', false, 342, '2011-08-22', 39, 5, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (11, NULL, '2011-08-22', '2011-08-21', NULL, NULL, 4, NULL, 56, '2011-08-17', '', false, 234234, '2011-08-21', 38, 4, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (3, NULL, '2010-11-10', '2010-11-09', NULL, NULL, 4, NULL, 48, '2010-11-19', '', false, 56743, '2010-11-09', 30, 22, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (2, NULL, '2010-11-10', '2010-11-08', NULL, '2010-11-09', 4, 3, 40, '2010-11-19', '', false, 33617, '2010-11-08', 27, 4, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (13, NULL, '2011-09-05', '2011-09-04', NULL, NULL, 4, NULL, 59, '2011-09-15', '', false, 234234, '2011-09-04', 40, 4, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (5, '2011-04-22', '2011-04-11', '2011-04-10', NULL, NULL, 7, NULL, 50, '2011-04-11', '', false, 324242, '2011-04-10', 32, 5, NULL, NULL, NULL, 3);

--
-- Data for table public.planificacioncalidad (OID = 81600) (LIMIT 0,4)
--
INSERT INTO planificacioncalidad (idplanificacion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido)
VALUES (1, 1, '2011-09-05', 'carapachinchi', '2011-09-05', '2011-09-05', 32);

INSERT INTO planificacioncalidad (idplanificacion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido)
VALUES (2, 2, '2011-09-19', '', '2011-09-19', '2011-09-19', 43);

INSERT INTO planificacioncalidad (idplanificacion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido)
VALUES (3, 3, '2011-09-19', '', '2011-09-21', '2011-09-22', 44);

INSERT INTO planificacioncalidad (idplanificacion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido)
VALUES (4, 4, '2011-09-20', '', '2011-09-22', '2011-09-22', 46);

--
-- Data for table public.estadopedido (OID = 81604) (LIMIT 0,19)
--
INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (1, 'Generado', 'Pedido generado');

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (2, 'Presupuestado', NULL);

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (4, 'Confirmado', NULL);

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (6, 'En producción', NULL);

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (7, 'En calidad', NULL);

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

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (8, 'Armado', NULL);

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (5, 'Planificado Produccion', 'Pedido con planificacion de produccion realizada');

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (19, 'Planificado Calidad', 'Pedido con planificacion de calidad realizada');

--
-- Data for table public.factura (OID = 81608) (LIMIT 0,3)
--
INSERT INTO factura (idfactura, nrofactura, fechaemision, tipoiva, fecharealcobro, formapago, fechavencimiento, usuario, estado, tipofactura, montototal)
VALUES (3, 3, '2010-11-09', NULL, NULL, 1, '2010-11-16', NULL, 2, 'A', NULL);

INSERT INTO factura (idfactura, nrofactura, fechaemision, tipoiva, fecharealcobro, formapago, fechavencimiento, usuario, estado, tipofactura, montototal)
VALUES (4, 4, '2011-09-20', NULL, NULL, 1, '2011-09-20', NULL, 2, 'A', NULL);

INSERT INTO factura (idfactura, nrofactura, fechaemision, tipoiva, fecharealcobro, formapago, fechavencimiento, usuario, estado, tipofactura, montototal)
VALUES (5, 5, '2011-09-26', NULL, NULL, 1, '2011-09-26', NULL, 2, 'A', NULL);

--
-- Data for table public.planificacionproduccion (OID = 81612) (LIMIT 0,22)
--
INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (29, 2, '2010-11-08', 'Todo esta de acuerdo a lo planeado', '2010-11-09', '2010-11-09', 27, 2);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (31, 3, '2010-11-09', 'Esta todo segun lo planificado', '2010-11-09', '2010-11-10', 30, 2);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (33, 4, '2011-04-10', 'Observacion', '2011-04-11', '2011-04-11', 31, 1);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (34, 5, '2011-04-10', 'cvbvc', '2011-04-11', '2011-04-11', 30, 1);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (37, 8, '2011-06-10', 'dfgvsdfgdfsgsdg', '2011-06-10', '2011-06-10', 34, 2);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (36, 7, '2011-04-25', 'Observacion', '2011-04-25', '2011-04-27', 33, 2);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (38, 9, '2011-07-17', 'ghnjyunyny', '2011-07-18', '2011-07-20', 36, 1);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (39, 10, '2011-07-17', '', '2011-07-18', '2011-07-19', 35, 1);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (40, 11, '2011-07-18', '', '2011-07-18', '2011-07-18', 34, 1);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (41, 12, '2011-07-18', '', '2011-07-18', '2011-07-19', 37, 2);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (42, 13, '2011-08-22', '', '2011-08-22', '2011-08-22', 38, 2);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (43, 14, '2011-08-22', '', '2011-08-22', '2011-08-22', 39, 2);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (35, 6, '2011-04-10', '', '2011-04-11', '2011-04-12', 27, 2);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (44, 15, '2011-08-22', '', '2011-08-22', '2011-08-23', 32, 2);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (45, 16, '2011-09-04', '', '2011-09-05', '2011-09-06', 40, 1);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (46, 17, '2011-09-04', '', '2011-09-05', '2011-09-05', 27, 1);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (50, 18, '2011-09-05', '', '2011-09-05', '2011-09-05', 41, 1);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (51, 19, '2011-09-19', 'asfasfadsfasfas', '2011-09-19', '2011-09-19', 43, 2);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (52, 20, '2011-09-19', 'kjkkkkkkkkkkkkkkk', '2011-09-20', '2011-09-21', 44, 2);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (53, 21, '2011-09-20', '', '2011-09-20', '2011-09-22', 46, 2);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (54, 22, '2011-10-02', '', '2011-10-03', '2011-10-04', 44, 1);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (55, 23, '2011-10-06', '', '2011-10-07', '2011-10-07', 43, 1);

--
-- Data for table public.presupuesto (OID = 81616) (LIMIT 0,17)
--
INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (40, '2010-11-08', 204.49, '2010-11-15', 2);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (48, '2010-11-09', 204.49, '2010-11-16', 3);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (49, '2011-04-03', 111.32, '2011-04-10', 4);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (50, '2011-04-10', 175.45, '2011-04-17', 5);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (51, '2011-04-25', 536.03, '2011-05-02', 6);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (52, '2011-06-09', 286.77, '2011-06-16', 7);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (53, '2011-06-11', 204.49, '2011-06-18', 8);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (54, '2011-06-20', 177.87, '2011-06-27', 9);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (55, '2011-07-18', 358.16, '2011-07-25', 10);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (56, '2011-08-21', 111.32, '2011-08-28', 11);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (57, '2011-08-22', 111.32, '2011-08-29', 12);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (58, NULL, 0, NULL, 13);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (59, '2011-09-04', 111.32, '2011-09-11', 14);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (61, '2011-09-04', 111.32, '2011-09-11', 15);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (62, '2011-09-19', 111.32, '2011-09-26', 16);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (63, '2011-09-19', 398.09, '2011-09-26', 17);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (64, '2011-09-20', 111.32, '2011-09-27', 18);

--
-- Data for table public.remito (OID = 81627) (LIMIT 0,3)
--
INSERT INTO remito (idremito, nroremito, fechaemision, pedido, estado)
VALUES (3, 3, '2010-11-09', 27, 1);

INSERT INTO remito (idremito, nroremito, fechaemision, pedido, estado)
VALUES (4, 4, '2011-09-20', 44, 1);

INSERT INTO remito (idremito, nroremito, fechaemision, pedido, estado)
VALUES (5, 5, '2011-09-26', 46, 1);

--
-- Data for table public.trabajotercerizado (OID = 81631) (LIMIT 0,1)
--
INSERT INTO trabajotercerizado (idtrabajo, nrotrabajotercerizado, fechapedidocotizacion, fechaentregaestipulada, fechaconfirmaciontrabajo, fechacancelacion, fechaentregareal, fechaenvioaempresa, motivocancelacion, empresa, estado, pedido, fechadelingresocotizacion, montototal)
VALUES (1, 1, '2011-09-20', NULL, '2011-09-20', NULL, NULL, '2011-09-20', NULL, 1, 4, 35, '2011-09-20', 55);

--
-- Data for table public.cliente (OID = 81635) (LIMIT 0,6)
--
INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (123, 4, 1, 1, false, 1, 'NEVERLAND', 1, '4009765', '154098654', 'neverlan@juegos.com', 21, '2010-07-01', NULL, '20-32987653-0', 1, '20-32987653-0');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (7, 6, 3, 2, false, 3, 'MARTINEZ Y CIA', 5, '4765123', '155233401', 'maritinez@martinez.com', 4, '2010-10-12', '2010-07-06', '27-27890543-2', 3, '27-27890543-2');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (147, 23, NULL, NULL, false, NULL, 'tregdgfdsg', 24, '3453534', '453534', '5343453', 55, '2011-03-02', NULL, NULL, NULL, '45354353');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (145, 5, 2, 2, true, 2, 'ROGIO', 2, '4200119', '153876881', 'rogio@argentina.com', 11, '2010-09-04', '2010-07-06', '31-31486700-3', 2, '31-31486700-3');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (146, 22, 3, 1, true, 4, 'STARBENE', 23, '4567676', '155657678', 'info@gmail.com', 53, '2010-11-09', NULL, NULL, 1, '30-35647574-2');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (148, 24, 3, 1, false, 13, 'RINCON DE METAL', 26, '4566767', '154353445', 'mail@gmail.com', 60, '2011-09-20', NULL, NULL, 1, '23345543');

--
-- Data for table public.formadepago (OID = 81643) (LIMIT 0,2)
--
INSERT INTO formadepago (idformapago, nombre, descripcion)
VALUES (1, 'CONTADO', 'EFECTIVO');

INSERT INTO formadepago (idformapago, nombre, descripcion)
VALUES (2, 'CTA CTE', 'Cuenta corriente');

--
-- Data for table public.usuario (OID = 81647) (LIMIT 0,8)
--
INSERT INTO usuario (idusuario, usuario, clave)
VALUES (1, 'Nino', 'Nino');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (2, 'admin', 'admin');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (3, 'Charly', 'charly');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (4, 'Mari', 'mari');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (11, 'maticarrizo', 'maticarrizo');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (12, 'nino', 'nino');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (13, 'carlos', 'carlos');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (14, 'admin2', 'admin2');

--
-- Data for table public.estadofactura (OID = 81651) (LIMIT 0,2)
--
INSERT INTO estadofactura (idestado, nombre, descripcion)
VALUES (1, 'GENERADO', 'GENERADO');

INSERT INTO estadofactura (idestado, nombre, descripcion)
VALUES (2, 'COBRADO', 'cobrado');

--
-- Data for table public.comprobantepago (OID = 81655) (LIMIT 0,6)
--
INSERT INTO comprobantepago (idcomprobantepago, nrocomprobantepago, fechaemision, monto, formadepago, usuario, factura)
VALUES (5, 5, '2010-11-09', 100, 1, NULL, 3);

INSERT INTO comprobantepago (idcomprobantepago, nrocomprobantepago, fechaemision, monto, formadepago, usuario, factura)
VALUES (6, 6, '2010-11-09', 69, 1, NULL, 3);

INSERT INTO comprobantepago (idcomprobantepago, nrocomprobantepago, fechaemision, monto, formadepago, usuario, factura)
VALUES (7, 7, '2011-09-20', 100, 1, NULL, 4);

INSERT INTO comprobantepago (idcomprobantepago, nrocomprobantepago, fechaemision, monto, formadepago, usuario, factura)
VALUES (8, 8, '2011-09-20', 229, 1, NULL, 4);

INSERT INTO comprobantepago (idcomprobantepago, nrocomprobantepago, fechaemision, monto, formadepago, usuario, factura)
VALUES (9, 9, '2011-09-26', 90, 1, NULL, 5);

INSERT INTO comprobantepago (idcomprobantepago, nrocomprobantepago, fechaemision, monto, formadepago, usuario, factura)
VALUES (10, 10, '2011-09-26', 2, 1, NULL, 5);

--
-- Data for table public.estadotrabajotercerizado (OID = 81659) (LIMIT 0,14)
--
INSERT INTO estadotrabajotercerizado (idestado, nombre, descripcion)
VALUES (1, 'GENERADO', 'Generado y no enviado');

INSERT INTO estadotrabajotercerizado (idestado, nombre, descripcion)
VALUES (2, 'EnEsperaPresupuesto', NULL);

INSERT INTO estadotrabajotercerizado (idestado, nombre, descripcion)
VALUES (3, 'Presupuestado', NULL);

INSERT INTO estadotrabajotercerizado (idestado, nombre, descripcion)
VALUES (4, 'Confirmado', NULL);

INSERT INTO estadotrabajotercerizado (idestado, nombre, descripcion)
VALUES (5, 'Cancelado', NULL);

INSERT INTO estadotrabajotercerizado (idestado, nombre, descripcion)
VALUES (6, 'PlanificadoParaControlDeCalidad', NULL);

INSERT INTO estadotrabajotercerizado (idestado, nombre, descripcion)
VALUES (7, 'EnEmpresaMetalurgica', NULL);

INSERT INTO estadotrabajotercerizado (idestado, nombre, descripcion)
VALUES (8, 'RecibidoTotal', NULL);

INSERT INTO estadotrabajotercerizado (idestado, nombre, descripcion)
VALUES (9, 'RecibidoParcial', NULL);

INSERT INTO estadotrabajotercerizado (idestado, nombre, descripcion)
VALUES (10, 'EnCalidad', NULL);

INSERT INTO estadotrabajotercerizado (idestado, nombre, descripcion)
VALUES (11, 'EnSolicitudReclamo', NULL);

INSERT INTO estadotrabajotercerizado (idestado, nombre, descripcion)
VALUES (12, 'Aceptado', NULL);

INSERT INTO estadotrabajotercerizado (idestado, nombre, descripcion)
VALUES (13, 'Reclamado', NULL);

INSERT INTO estadotrabajotercerizado (idestado, nombre, descripcion)
VALUES (14, 'Realizado', NULL);

--
-- Data for table public.empresametalurgica (OID = 81663) (LIMIT 0,1)
--
INSERT INTO empresametalurgica (idempresametalurgica, nroempresametalurgica, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (1, 1, 'dddddddd', 25, '435345', '4535', 'fgsdefwe', 59, '2011-09-20', NULL, NULL, 1, '345345534');

--
-- Data for table public.prioridad (OID = 81667) (LIMIT 0,3)
--
INSERT INTO prioridad (idprioridad, nombre, descripcion)
VALUES (1, 'Alta', 'Prioridad Alta');

INSERT INTO prioridad (idprioridad, nombre, descripcion)
VALUES (2, 'Baja', 'Prioridad Baja');

INSERT INTO prioridad (idprioridad, nombre, descripcion)
VALUES (3, 'Normal', 'Prioridad Normal');

--
-- Data for table public.estadocliente (OID = 81671) (LIMIT 0,3)
--
INSERT INTO estadocliente (idestado, nombre, descripcion)
VALUES (1, 'ACTIVO', 'Dado de Alta');

INSERT INTO estadocliente (idestado, nombre, descripcion)
VALUES (2, 'BAJA', 'Dado de Baja');

INSERT INTO estadocliente (idestado, nombre, descripcion)
VALUES (3, 'MOROSO', 'Cliente Moroso, adeuda facturas');

--
-- Data for table public.responsable (OID = 81675) (LIMIT 0,12)
--
INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (6, 'Mariana', 'Enrico', '4678765', 'mari@gmail.com', 18, 31876345, 1, '');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (5, 'Lorena', 'Barale', '4210945', 'lore@gmail.com', 16, 27098567, 1, '');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (2, 'Nino', 'Molina', '4487623', 'nino@gmail.com', 11, 24256786, 1, '47865432');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (1, 'Maria Victoria', 'Merdine', '4980765', 'mv@hotmail.com', 9, 32647263, 1, '4590876');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (18, 'Néstor', 'García', '4725538', 'nestor@starbene.com', 44, 25332128, 1, '4725522');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (19, 'caksjc', 'dkjfhskjd', '46377728', 'ads@gmail.com', 46, 345435554, 1, '473662378');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (20, 'jksdbfjkdsf', 'oviedo', '4445254', 'carlos@gmail.com', 48, 65663434, 1, '4445245');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (22, 'kjhkjh', 'kjhkjh', '87687', 'kjhkjh', 52, 8787678, 1, '876876');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (23, 'Carlos', 'Oviedo', '4578934', 'carlos@gmail.com', 54, 25987654, 1, '4578934');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (24, 'gedgrtgrtg', 'gtrgtrg', '3454353', 'gtgrgtr', 56, 543534543, NULL, '534534');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (25, 'gggggg', 'ggggggg', '43434', 'ggggggggg', 58, 4343, 1, '4343');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (26, 'Juan', 'Perez', '4556365', 'perez@gmail.com', 61, 22565888, 1, '4654564');

--
-- Data for table public.condicioniva (OID = 81679) (LIMIT 0,3)
--
INSERT INTO condicioniva (idcondicioniva, nombre, descripcion)
VALUES (1, 'R.Inscripto', 'Responsable Inscripto');

INSERT INTO condicioniva (idcondicioniva, nombre, descripcion)
VALUES (2, 'Monotributista', 'Monotributista');

INSERT INTO condicioniva (idcondicioniva, nombre, descripcion)
VALUES (3, 'C.Final', 'Consumidor Final');

--
-- Data for table public.domicilio (OID = 81683) (LIMIT 0,29)
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

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (43, 'Bedoya', 2215, 0, '', '', 2);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (44, 'Fragueiro', 2127, 0, '', '', 2);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (45, 'sclkacjnlkas', 32, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (46, 'fdsfdsfsf', 43, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (47, 'ksajhdkjsa', 345, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (48, 'Caceres', 444, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (51, 'calle', 543, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (52, 'cdsfsf', 43, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (53, 'Calle', 455, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (54, 'Rondeau', 455, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (55, 'rteterte', 345, 0, '', '', NULL);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (56, 'vfdvdfvd', 543, 0, '', '', NULL);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (57, 'Calle', 56, 1, 'd', '2', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (58, 'rfff', 43, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (59, 'fewfwef', 43, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (60, 'Calle', 1, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (61, 'Calle', 2, 0, '', '', 1);

--
-- Data for table public.categoria (OID = 81691) (LIMIT 0,2)
--
INSERT INTO categoria (idcategoria, nombre, descripcion)
VALUES (1, 'operario', 'operario en general');

INSERT INTO categoria (idcategoria, nombre, descripcion)
VALUES (2, 'oficial de planta', 'oficial de planta');

--
-- Data for table public.cargo (OID = 81695) (LIMIT 0,3)
--
INSERT INTO cargo (idcargo, nombre, descripcion)
VALUES (1, 'Tornero', 'operario de tornos');

INSERT INTO cargo (idcargo, nombre, descripcion)
VALUES (2, 'Afilador', 'Realiza afilados');

INSERT INTO cargo (idcargo, nombre, descripcion)
VALUES (3, 'Rectificador', 'rectificador');

--
-- Data for table public.turno (OID = 81699) (LIMIT 0,3)
--
INSERT INTO turno (idturno, horainicio, horafin, nombre, descripcion)
VALUES (1, '00:08:00', '00:12:00', 'MAÑANA', NULL);

INSERT INTO turno (idturno, horainicio, horafin, nombre, descripcion)
VALUES (2, '00:13:00', '00:17:00', 'TARDE', NULL);

INSERT INTO turno (idturno, horainicio, horafin, nombre, descripcion)
VALUES (3, '00:18:00', '00:22:00', 'NOCHE', NULL);

--
-- Data for table public.marca (OID = 81703) (LIMIT 0,3)
--
INSERT INTO marca (idmarca, nombre, descripcion)
VALUES (3, 'Jones Shipman', 'Internacional');

INSERT INTO marca (idmarca, nombre, descripcion)
VALUES (2, 'Bridgeport', 'Internacional');

INSERT INTO marca (idmarca, nombre, descripcion)
VALUES (1, 'Tornomax', 'Nacional');

--
-- Data for table public.estadomaquina (OID = 81707) (LIMIT 0,1)
--
INSERT INTO estadomaquina (idestado, nombre, descripcion)
VALUES (1, 'Disponible', 'mauqina disponible');

--
-- Data for table public.tipomaquina (OID = 81711) (LIMIT 0,5)
--
INSERT INTO tipomaquina (idtipomaquina, nombre, descripcion)
VALUES (1, 'TORNO', '');

INSERT INTO tipomaquina (idtipomaquina, nombre, descripcion)
VALUES (3, 'FRESADORA', NULL);

INSERT INTO tipomaquina (idtipomaquina, nombre, descripcion)
VALUES (4, 'AFILADORA', NULL);

INSERT INTO tipomaquina (idtipomaquina, nombre, descripcion)
VALUES (5, 'RECTIFICADORA', NULL);

INSERT INTO tipomaquina (idtipomaquina, nombre, descripcion)
VALUES (2, 'SOLDADORA', NULL);

--
-- Data for table public.estadoejecetapaprod (OID = 81715) (LIMIT 0,6)
--
INSERT INTO estadoejecetapaprod (idestado, nombre, descripcion)
VALUES (1, 'GENERADA', NULL);

INSERT INTO estadoejecetapaprod (idestado, nombre, descripcion)
VALUES (2, 'ENEJECUCION', NULL);

INSERT INTO estadoejecetapaprod (idestado, nombre, descripcion)
VALUES (3, 'DETENIDA', NULL);

INSERT INTO estadoejecetapaprod (idestado, nombre, descripcion)
VALUES (4, 'FINALIZADA', NULL);

INSERT INTO estadoejecetapaprod (idestado, nombre, descripcion)
VALUES (5, 'REPLANIFICADA', NULL);

INSERT INTO estadoejecetapaprod (idestado, nombre, descripcion)
VALUES (6, 'CANCELADA', NULL);

--
-- Data for table public.tipodocumento (OID = 81723) (LIMIT 0,3)
--
INSERT INTO tipodocumento (idtipodocumento, tipo, nombre)
VALUES (1, 'DNI', 'Documento Nacional de Identidad');

INSERT INTO tipodocumento (idtipodocumento, tipo, nombre)
VALUES (2, 'LE', 'Libreta de Enrolamiento');

INSERT INTO tipodocumento (idtipodocumento, tipo, nombre)
VALUES (3, 'LC', 'Libreta Cívica');

--
-- Data for table public.codigodebarra (OID = 81727) (LIMIT 0,63)
--
INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (1, 'xxx', '1234');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (42, 'Volante', 'PIE43');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (43, 'Perilla', 'PIE44');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (44, 'Mango', 'PIE45');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (45, 'Volante', 'PIE46');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (46, 'Perilla', 'PIE47');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (47, 'Mango', 'PIE48');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (48, 'Volante', 'PIE49');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (49, 'Perilla', 'PIE50');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (50, 'Mango', 'PIE51');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (51, 'Volante', 'PIE52');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (52, 'Perilla', 'PIE53');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (53, 'Mango', 'PIE54');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (54, 'Perilla', 'PIE55');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (55, 'Panel', 'PIE56');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (56, 'Eje', 'PIE57');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (57, 'Eje', 'PIE58');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (58, NULL, '<$cod>-1</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (59, NULL, '<$cod>-1</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (60, NULL, '<$cod>-1</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (61, NULL, '<$cod>12</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (62, NULL, '<$cod>13</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (63, NULL, '<$cod>14</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (64, NULL, '<$cod>14</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (65, NULL, '<$cod>15</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (66, NULL, '<$cod>16</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (67, NULL, '<$cod>17</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (68, NULL, '<$cod>18</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (69, NULL, '<$cod>19</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (70, NULL, '<$cod>20</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (71, NULL, '<$cod>21</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (72, NULL, '<$cod>22</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (73, NULL, '<$cod>23</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (74, NULL, '<$cod>24</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (75, NULL, '<$cod>25</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (76, NULL, '<$cod>26</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (77, NULL, '<$cod>27</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (78, NULL, '<$cod>28</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (79, NULL, '<$cod>29</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (80, NULL, '<$cod>30</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (81, NULL, '<$cod>31</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (82, NULL, '<$cod>32</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (83, NULL, '<$cod>33</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (84, NULL, '<$cod>33</$cod>');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (85, NULL, 'mscb-3-34');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (86, NULL, 'mscb-3-35');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (87, NULL, 'mscb-3-35');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (88, NULL, 'mscb-3-35');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (89, NULL, 'mscb-3-35');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (90, NULL, 'mscb-1-1');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (91, NULL, 'mscb-1-1');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (92, NULL, 'mscb-1-2');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (93, NULL, 'mscb-3-36');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (94, NULL, 'mscb-3-37');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (95, NULL, 'mscb-3-38');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (96, NULL, 'mscb-3-39');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (97, NULL, 'mscb-3-40');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (98, NULL, 'mscb-3-40');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (99, NULL, 'mscb-1-3');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (100, NULL, 'mscb-1-3');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (101, NULL, 'mscb-3-41');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (102, NULL, 'mscb-3-42');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (103, NULL, 'mscb-1-4');

--
-- Data for table public.estadopiezareal (OID = 81731) (LIMIT 0,1)
--
INSERT INTO estadopiezareal (idestado, nombre, descripcion)
VALUES (1, 'INICIADO', NULL);

--
-- Data for table public.estadoejecplanifpedido (OID = 81735) (LIMIT 0,4)
--
INSERT INTO estadoejecplanifpedido (idestado, nombre, descripcion)
VALUES (1, 'ENEJECUCION', 'Se ha lanzado la producción de una planificación');

INSERT INTO estadoejecplanifpedido (idestado, nombre, descripcion)
VALUES (2, 'DETENIDA', NULL);

INSERT INTO estadoejecplanifpedido (idestado, nombre, descripcion)
VALUES (3, 'REPLANIFICADA', NULL);

INSERT INTO estadoejecplanifpedido (idestado, nombre, descripcion)
VALUES (4, 'FINALIZADA', NULL);

--
-- Data for table public.rol (OID = 81743) (LIMIT 0,8)
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

INSERT INTO rol (idrol, rol, descripcion)
VALUES (6, 'PRODUCCION', 'Responsable de Produccion');

INSERT INTO rol (idrol, rol, descripcion)
VALUES (7, 'OPERARIO', NULL);

INSERT INTO rol (idrol, rol, descripcion)
VALUES (8, 'CLIENTE', 'Cliente de la empresa');

--
-- Data for table public.accioncalidad (OID = 81751) (LIMIT 0,3)
--
INSERT INTO accioncalidad (idaccioncalidad, nombre, descripcion)
VALUES (1, 'Medir', 'Realizar una medición de las dimensiones del objeto');

INSERT INTO accioncalidad (idaccioncalidad, nombre, descripcion)
VALUES (2, 'Contar', 'Realizar el conteo de diferentes aspectos de un objeto');

INSERT INTO accioncalidad (idaccioncalidad, nombre, descripcion)
VALUES (3, 'Observar', 'Realizar una observación general del objeto');

--
-- Data for table public.empleado (OID = 81755) (LIMIT 0,5)
--
INSERT INTO empleado (idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo)
VALUES (2, 234111, '1999-10-10', 'CARLOS', 'CASTRO', '4344425', 'carlos@gmail.com', 4, 31231987, 1, 1, 3, NULL, NULL, 1);

INSERT INTO empleado (idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo)
VALUES (1, 234234, '2002-12-01', 'SERGIO', 'ALTAMIRANO', '4672839', 'mari_enrico@gmail.com.ar', 2, 33387654, 1, 1, 4, NULL, NULL, 2);

INSERT INTO empleado (idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo)
VALUES (4, 241236, '2001-09-05', 'SANTIAGO', 'AGUERO', '4356671', 'lore@hotmail.com', 3, 30675432, 1, 1, 1, NULL, NULL, 3);

INSERT INTO empleado (idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo)
VALUES (3, 256542, '2005-07-03', 'ROBERTO', 'RUIZ', '4352673', 'vicky@hotmail.com', 1, 32456788, 1, 1, 2, NULL, NULL, 1);

INSERT INTO empleado (idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo)
VALUES (5, 256543, '2011-09-15', 'Pedro', 'Juan', '4789309', 'pppp@gmail.com', 57, 22654535, 1, 2, NULL, NULL, '', 2);

--
-- Data for table public.proveedor (OID = 81759) (LIMIT 0,3)
--
INSERT INTO proveedor (idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit)
VALUES (1, 1, 'Techin', 1, '4354213', '155238976', 'techin@insdustrias.com', 5, '1990-02-01', NULL, '32-32323232-3', 1, '21-21643121-2');

INSERT INTO proveedor (idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit)
VALUES (2, 2, 'MetalArg', 2, '4353545', '543543545', 'metal@metal.com', 2, '2000-10-10', NULL, '32-33333333-3', 1, '32-35690733-3');

INSERT INTO proveedor (idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit)
VALUES (3, 3, 'AcerMax', 5, '4894536', '153234090', 'acermax@argentina.com', 39, '2010-09-14', NULL, '21-76056332-1', 1, '21-76056782-1');

--
-- Data for table public.estadocompra (OID = 81763) (LIMIT 0,10)
--
INSERT INTO estadocompra (idestado, nombre, descripcion)
VALUES (1, 'GENERADA', NULL);

INSERT INTO estadocompra (idestado, nombre, descripcion)
VALUES (2, 'ENVIADA A PROVEEDOR', NULL);

INSERT INTO estadocompra (idestado, nombre, descripcion)
VALUES (3, 'RECIBIDA TOTAL', NULL);

INSERT INTO estadocompra (idestado, nombre, descripcion)
VALUES (4, 'ACEPTADA', NULL);

INSERT INTO estadocompra (idestado, nombre, descripcion)
VALUES (5, 'FINALIZADA', NULL);

INSERT INTO estadocompra (idestado, nombre, descripcion)
VALUES (6, 'CANCELADA', NULL);

INSERT INTO estadocompra (idestado, nombre, descripcion)
VALUES (7, 'RECIBIDA PARCIAL', NULL);

INSERT INTO estadocompra (idestado, nombre, descripcion)
VALUES (8, 'EN SOLICITUD RECLAMO', NULL);

INSERT INTO estadocompra (idestado, nombre, descripcion)
VALUES (9, 'RECLAMADA', NULL);

INSERT INTO estadocompra (idestado, nombre, descripcion)
VALUES (10, 'RECHAZADA', NULL);

--
-- Data for table public.materiaprima (OID = 81771) (LIMIT 0,7)
--
INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (7, 'Acero 1020', '2010-09-14', NULL, 1, 100, '', 2, 3, 100.000, 100.000, 100.000, '0', 22, 1);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (8, 'Acero 1015', '2010-09-14', NULL, 1, 100, '', 2, 2, 100.000, 100.000, 50.000, '0', 21, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (9, 'Aluminio', '2010-11-01', NULL, 1, 100, '', 5, 1, 100.000, 60.000, 60.000, '0', 30, 3);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (4, 'PVC', '2010-09-14', NULL, 1, 82, '', 4, 2, 100.000, 100.000, 100.000, '6543', 19, 0);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (10, 'aluminio 1414', '2011-09-20', NULL, 1, 345, 'aluminio 1414', 2, 2, 100.000, 100.000, 100.000, '0', NULL, 4);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (1, 'Acero 1010', '2010-09-14', NULL, 1, 71, NULL, 2, 1, 33.420, 50.000, 50.000, '0', 12, 0);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (5, 'Cobre', '2010-09-14', NULL, 1, 84, '', 5, 2, 50.000, 50.000, 50.000, '6543', 25, 0);

--
-- Data for table public.matriz (OID = 81775) (LIMIT 0,4)
--
INSERT INTO matriz (idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial)
VALUES (1, 123, 'MatrizMango', '', NULL, NULL, 1, 4);

INSERT INTO matriz (idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial)
VALUES (4, 5453, 'MatrizPerilla', '', NULL, NULL, 1, 2);

INSERT INTO matriz (idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial)
VALUES (3, 654, 'MatrizVolante', '', NULL, NULL, 7, 5);

INSERT INTO matriz (idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial)
VALUES (5, 23424, 'MAtriz2342', '', NULL, NULL, 10, 2);

--
-- Data for table public.producto (OID = 81779) (LIMIT 0,6)
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

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (12, 346, 'Producto 1010', 50, '');

--
-- Data for table public.estadoejecplancalidad (OID = 81783) (LIMIT 0,4)
--
INSERT INTO estadoejecplancalidad (idestado, nombre, descripcion)
VALUES (1, 'ENEJECUCION', NULL);

INSERT INTO estadoejecplancalidad (idestado, nombre, descripcion)
VALUES (2, 'DETENIDA', NULL);

INSERT INTO estadoejecplancalidad (idestado, nombre, descripcion)
VALUES (3, 'REPLANIFICADA', NULL);

INSERT INTO estadoejecplancalidad (idestado, nombre, descripcion)
VALUES (4, 'FINALIZADA', NULL);

--
-- Data for table public.etapadeproduccion (OID = 81795) (LIMIT 0,6)
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

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, maquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (6, 456, 'Etapa32', NULL, NULL, 10, NULL, '2011-09-20', 2);

--
-- Data for table public.ejecucionplanificacionproduccion (OID = 81799) (LIMIT 0,20)
--
INSERT INTO ejecucionplanificacionproduccion (idejecucion, idplanificacionproduccion, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacion, novedades)
VALUES (9, 29, '2010-11-08', NULL, '23:52:39.163', NULL, 1, NULL, NULL);

INSERT INTO ejecucionplanificacionproduccion (idejecucion, idplanificacionproduccion, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacion, novedades)
VALUES (11, 31, '2010-11-09', NULL, '10:36:07.927', NULL, 1, NULL, NULL);

INSERT INTO ejecucionplanificacionproduccion (idejecucion, idplanificacionproduccion, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacion, novedades)
VALUES (17, 41, NULL, NULL, '01:20:56', NULL, 1, 1, NULL);

INSERT INTO ejecucionplanificacionproduccion (idejecucion, idplanificacionproduccion, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacion, novedades)
VALUES (18, 36, '2011-08-13', NULL, '17:29:10', NULL, 1, 2, NULL);

INSERT INTO ejecucionplanificacionproduccion (idejecucion, idplanificacionproduccion, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacion, novedades)
VALUES (19, 42, '2011-08-22', NULL, '00:12:36', NULL, 1, 3, NULL);

INSERT INTO ejecucionplanificacionproduccion (idejecucion, idplanificacionproduccion, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacion, novedades)
VALUES (21, 44, '2011-08-22', NULL, '01:50:52', NULL, 4, 5, NULL);

INSERT INTO ejecucionplanificacionproduccion (idejecucion, idplanificacionproduccion, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacion, novedades)
VALUES (20, 43, '2011-08-22', NULL, '01:16:25', NULL, 4, 4, NULL);

INSERT INTO ejecucionplanificacionproduccion (idejecucion, idplanificacionproduccion, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacion, novedades)
VALUES (22, 51, '2011-09-19', NULL, '03:57:27', NULL, 1, 6, NULL);

INSERT INTO ejecucionplanificacionproduccion (idejecucion, idplanificacionproduccion, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacion, novedades)
VALUES (23, 51, '2011-09-19', NULL, '03:59:46', NULL, 1, 7, NULL);

INSERT INTO ejecucionplanificacionproduccion (idejecucion, idplanificacionproduccion, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacion, novedades)
VALUES (24, 51, '2011-09-19', NULL, '04:01:19', NULL, 1, 8, NULL);

INSERT INTO ejecucionplanificacionproduccion (idejecucion, idplanificacionproduccion, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacion, novedades)
VALUES (25, 51, '2011-09-19', NULL, '04:02:34', NULL, 1, 9, NULL);

INSERT INTO ejecucionplanificacionproduccion (idejecucion, idplanificacionproduccion, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacion, novedades)
VALUES (26, 51, '2011-09-19', NULL, '04:06:42', NULL, 1, 10, NULL);

INSERT INTO ejecucionplanificacionproduccion (idejecucion, idplanificacionproduccion, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacion, novedades)
VALUES (27, 51, '2011-09-19', NULL, '04:08:32', NULL, 1, 11, NULL);

INSERT INTO ejecucionplanificacionproduccion (idejecucion, idplanificacionproduccion, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacion, novedades)
VALUES (28, 51, '2011-09-19', NULL, '04:10:40', NULL, 1, 12, NULL);

INSERT INTO ejecucionplanificacionproduccion (idejecucion, idplanificacionproduccion, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacion, novedades)
VALUES (29, 51, '2011-09-19', NULL, '04:13:23', NULL, 1, 13, NULL);

INSERT INTO ejecucionplanificacionproduccion (idejecucion, idplanificacionproduccion, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacion, novedades)
VALUES (30, 51, '2011-09-19', NULL, '04:16:14', NULL, 1, 14, NULL);

INSERT INTO ejecucionplanificacionproduccion (idejecucion, idplanificacionproduccion, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacion, novedades)
VALUES (31, 51, '2011-09-19', NULL, '04:19:07', NULL, 1, 15, NULL);

INSERT INTO ejecucionplanificacionproduccion (idejecucion, idplanificacionproduccion, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacion, novedades)
VALUES (32, 51, '2011-09-19', NULL, '04:27:42', NULL, 1, 16, NULL);

INSERT INTO ejecucionplanificacionproduccion (idejecucion, idplanificacionproduccion, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacion, novedades)
VALUES (33, 52, '2011-09-19', '2011-09-19', '22:55:23', '23:26:51', 4, 17, NULL);

INSERT INTO ejecucionplanificacionproduccion (idejecucion, idplanificacionproduccion, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacion, novedades)
VALUES (34, 53, '2011-09-20', '2011-09-26', '10:14:57', '14:45:18', 4, 18, NULL);

--
-- Data for table public.procesocalidad (OID = 81803) (LIMIT 0,2)
--
INSERT INTO procesocalidad (idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad)
VALUES (1, 'Detectar Spatter', 1, NULL, '3', NULL, '00:05:00', '2010-09-06', NULL, 2);

INSERT INTO procesocalidad (idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad)
VALUES (2, 'Rectificación', 2, 'Null', NULL, 'Determinar si el nivel de rectificación es el adecuado', '00:10:00', '2010-08-06', 'calibre', 1);

--
-- Data for table public.ejecucionplanificacioncalidad (OID = 81807) (LIMIT 0,3)
--
INSERT INTO ejecucionplanificacioncalidad (idejecucion, idplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacioncalidad)
VALUES (1, 1, '2011-09-18', NULL, '03:51:23', NULL, 1, 1);

INSERT INTO ejecucionplanificacioncalidad (idejecucion, idplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacioncalidad)
VALUES (2, 3, '2011-09-19', '2011-09-20', '23:30:50', '00:07:49', 4, 2);

INSERT INTO ejecucionplanificacioncalidad (idejecucion, idplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacioncalidad)
VALUES (3, 4, '2011-09-26', '2011-09-26', '15:00:16', '15:01:56', 4, 3);

--
-- Data for table public.ejecucionprocesocalidad (OID = 81811) (LIMIT 0,9)
--
INSERT INTO ejecucionprocesocalidad (idejecucion, resultado, estado, nroejecucion, fechafin, fechainicio, horainicio, horafin, nombre, observacion, empleado)
VALUES (1, NULL, 4, 1, '2011-09-19', '2011-09-18', '03:51:23', '21:29:11', NULL, NULL, 2);

INSERT INTO ejecucionprocesocalidad (idejecucion, resultado, estado, nroejecucion, fechafin, fechainicio, horainicio, horafin, nombre, observacion, empleado)
VALUES (2, NULL, 4, 2, '2011-09-19', '2011-09-18', '03:51:23', '21:45:28', NULL, NULL, 4);

INSERT INTO ejecucionprocesocalidad (idejecucion, resultado, estado, nroejecucion, fechafin, fechainicio, horainicio, horafin, nombre, observacion, empleado)
VALUES (3, NULL, 4, 3, '2011-09-20', '2011-09-19', '23:30:51', '00:07:24', NULL, NULL, 2);

INSERT INTO ejecucionprocesocalidad (idejecucion, resultado, estado, nroejecucion, fechafin, fechainicio, horainicio, horafin, nombre, observacion, empleado)
VALUES (4, NULL, 4, 4, '2011-09-20', '2011-09-19', '23:30:51', '00:07:34', NULL, NULL, 1);

INSERT INTO ejecucionprocesocalidad (idejecucion, resultado, estado, nroejecucion, fechafin, fechainicio, horainicio, horafin, nombre, observacion, empleado)
VALUES (5, NULL, 4, 5, '2011-09-20', '2011-09-19', '23:30:51', '00:07:37', NULL, NULL, 4);

INSERT INTO ejecucionprocesocalidad (idejecucion, resultado, estado, nroejecucion, fechafin, fechainicio, horainicio, horafin, nombre, observacion, empleado)
VALUES (6, NULL, 4, 6, '2011-09-20', '2011-09-19', '23:30:51', '00:07:41', NULL, NULL, 3);

INSERT INTO ejecucionprocesocalidad (idejecucion, resultado, estado, nroejecucion, fechafin, fechainicio, horainicio, horafin, nombre, observacion, empleado)
VALUES (7, NULL, 4, 7, '2011-09-20', '2011-09-19', '23:30:51', '00:07:49', NULL, NULL, 2);

INSERT INTO ejecucionprocesocalidad (idejecucion, resultado, estado, nroejecucion, fechafin, fechainicio, horainicio, horafin, nombre, observacion, empleado)
VALUES (8, NULL, 4, 8, '2011-09-26', '2011-09-26', '15:00:17', '15:01:51', NULL, NULL, 4);

INSERT INTO ejecucionprocesocalidad (idejecucion, resultado, estado, nroejecucion, fechafin, fechainicio, horainicio, horafin, nombre, observacion, empleado)
VALUES (9, NULL, 4, 9, '2011-09-26', '2011-09-26', '15:00:17', '15:01:56', NULL, NULL, 5);

--
-- Data for table public.ejecucionetapaproduccion (OID = 81815) (LIMIT 0,33)
--
INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (3, 3, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, 1, 2);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (4, 1, NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, 2, 3);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (5, 1, NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, 1, 4);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (10, 1, NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, 1, 9);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (2, 5, NULL, NULL, '12:33:21', 2, NULL, NULL, NULL, NULL, NULL, 2, 1);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (6, 1, NULL, NULL, '248:33:12', 2, '2011-08-13', '17:29:23', '2011-08-23', '01:33:12', NULL, 4, 5);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (12, 3, NULL, NULL, '24:50:53', 3, '2011-08-22', '01:51:00', '2011-08-23', '01:50:53', NULL, 4, 11);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (9, 3, NULL, NULL, '24:54:45', 2, '2011-08-22', '01:36:04', '2011-08-23', '01:54:45', NULL, 4, 8);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (7, 3, NULL, NULL, '25:58:27', 2, '2011-08-22', '00:12:40', '2011-08-23', '01:58:27', NULL, 4, 6);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (11, 4, NULL, NULL, '426:14:54', 4, '2011-08-22', '01:50:59', '2011-09-08', '19:14:54', NULL, 4, 10);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (13, 1, NULL, NULL, '24:42:27', 1, '2011-08-22', '01:51:01', '2011-08-23', '01:42:27', NULL, 2, 12);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (8, 1, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, 2, 7);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (14, 3, NULL, NULL, NULL, 4, '2011-09-19', '04:06:43', NULL, NULL, NULL, 2, 13);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (15, 3, NULL, NULL, NULL, 4, '2011-09-19', '04:08:32', NULL, NULL, NULL, 2, 14);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (16, 1, NULL, NULL, NULL, 2, '2011-09-19', '04:08:32', NULL, NULL, NULL, 2, 15);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (17, 3, NULL, NULL, NULL, 4, '2011-09-19', '04:10:56', NULL, NULL, NULL, 2, 16);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (18, 1, NULL, NULL, NULL, 2, '2011-09-19', '04:10:56', NULL, NULL, NULL, 2, 17);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (19, 3, NULL, NULL, NULL, 4, '2011-09-19', '04:13:24', NULL, NULL, NULL, 2, 18);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (20, 1, NULL, NULL, NULL, 2, '2011-09-19', '04:13:24', NULL, NULL, NULL, 2, 19);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (21, 3, NULL, NULL, NULL, 4, '2011-09-19', '04:16:14', NULL, NULL, NULL, 2, 20);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (22, 1, NULL, NULL, NULL, 2, '2011-09-19', '04:16:15', NULL, NULL, NULL, 2, 21);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (23, 3, NULL, NULL, NULL, 4, '2011-09-19', '04:19:08', NULL, NULL, NULL, 2, 22);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (24, 1, NULL, NULL, NULL, 2, '2011-09-19', '04:19:08', NULL, NULL, NULL, 2, 23);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (25, 3, NULL, NULL, NULL, 4, '2011-09-19', '04:27:42', NULL, NULL, NULL, 2, 24);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (26, 1, NULL, NULL, NULL, 2, '2011-09-19', '04:27:43', NULL, NULL, NULL, 2, 25);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (27, 1, NULL, NULL, '1:1:56', 2, '2011-09-19', '22:55:24', '2011-09-19', '23:01:56', NULL, 4, 26);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (28, 1, NULL, NULL, '1:25:55', 1, '2011-09-19', '22:55:24', '2011-09-19', '23:25:55', NULL, 4, 27);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (29, 1, NULL, NULL, '1:26:39', 4, '2011-09-19', '22:55:24', '2011-09-19', '23:26:39', NULL, 4, 28);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (30, 5, NULL, NULL, '1:26:44', 3, '2011-09-19', '22:55:24', '2011-09-19', '23:26:44', NULL, 4, 29);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (31, 4, NULL, NULL, '1:26:51', 2, '2011-09-19', '22:55:25', '2011-09-19', '23:26:51', NULL, 4, 30);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (32, 1, NULL, NULL, '148:39:10', 1, '2011-09-20', '10:14:58', '2011-09-26', '14:39:10', NULL, 4, 31);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (34, 1, NULL, NULL, '148:41:34', 5, '2011-09-20', '10:14:58', '2011-09-26', '14:41:34', NULL, 4, 33);

INSERT INTO ejecucionetapaproduccion (id, idetapaproduccion, nombre, totalhorasmaquina, totalhorashombre, empleado, fechainicio, horainicio, fechafin, horafin, observaciones, estado, nroejecucion)
VALUES (33, 4, NULL, NULL, '146:45:18', 4, '2011-09-20', '12:00:00', '2011-09-26', '14:45:18', NULL, 4, 32);

--
-- Data for table public.compra (OID = 81819) (LIMIT 0,1)
--
INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (2, 0, '2011-09-20', '2011-11-20', 0, 0, 1, '', 2);

--
-- Data for table public.detalleejecucionplanificacion (OID = 81831) (LIMIT 0,33)
--
INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (1, 17, 10, 2, 5, NULL, NULL, NULL, NULL, NULL, 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (2, 17, 13, 3, 3, NULL, NULL, NULL, NULL, NULL, 2);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (3, 17, 6, 4, 1, NULL, NULL, NULL, NULL, NULL, 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (4, 17, 11, 5, 1, NULL, NULL, NULL, NULL, NULL, 2);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (7, 19, 13, 8, 1, NULL, NULL, NULL, NULL, NULL, 2);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (9, 20, 13, 10, 1, 80, NULL, NULL, NULL, NULL, 2);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (5, 18, 8, 6, 1, 71, '2011-08-13', '2011-08-23', '17:29:23', '01:33:12', 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (11, 21, 10, 12, 3, 82, '2011-08-22', '2011-08-23', '01:51:00', '01:50:53', 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (8, 20, 10, 9, 3, 79, '2011-08-22', '2011-08-23', '01:36:04', '01:54:45', 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (6, 19, 10, 7, 3, NULL, '2011-08-22', '2011-08-23', '00:12:40', '01:58:27', 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (10, 21, 11, 11, 4, 81, '2011-08-22', '2011-09-08', '01:50:59', '19:14:54', 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (12, 21, 9, 13, 1, 83, '2011-09-11', '2011-08-23', '21:21:52', '01:42:27', 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (13, 21, 9, 8, 1, 83, '2011-09-11', NULL, '21:34:16', NULL, 2);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (14, 27, 10, 15, 3, 89, '2011-09-19', NULL, '04:08:32', NULL, 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (15, 27, 13, 16, 1, 85, '2011-09-19', NULL, '04:08:32', NULL, 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (16, 28, 10, 17, 3, 89, '2011-09-19', NULL, '04:10:56', NULL, 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (17, 28, 13, 18, 1, 85, '2011-09-19', NULL, '04:10:56', NULL, 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (18, 29, 10, 19, 3, 89, '2011-09-19', NULL, '04:13:24', NULL, 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (19, 29, 13, 20, 1, 85, '2011-09-19', NULL, '04:13:24', NULL, 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (20, 30, 10, 21, 3, 89, '2011-09-19', NULL, '04:16:14', NULL, 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (21, 30, 13, 22, 1, 85, '2011-09-19', NULL, '04:16:15', NULL, 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (22, 31, 10, 23, 3, 89, '2011-09-19', NULL, '04:19:08', NULL, 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (23, 31, 13, 24, 1, 85, '2011-09-19', NULL, '04:19:08', NULL, 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (24, 32, 10, 25, 3, 89, '2011-09-19', NULL, '04:27:42', NULL, 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (25, 32, 13, 26, 1, 85, '2011-09-19', NULL, '04:27:43', NULL, 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (26, 33, 9, 27, 1, NULL, '2011-09-19', '2011-09-19', '22:55:24', '23:01:56', 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (27, 33, 11, 28, 1, NULL, '2011-09-19', '2011-09-19', '22:55:24', '23:25:55', 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (28, 33, 10, 29, 1, NULL, '2011-09-19', '2011-09-19', '22:55:24', '23:26:39', 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (29, 33, 10, 30, 5, NULL, '2011-09-19', '2011-09-19', '22:55:24', '23:26:44', 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (30, 33, 13, 31, 4, NULL, '2011-09-19', '2011-09-19', '22:55:25', '23:26:51', 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (32, 34, 13, 32, 1, NULL, '2011-09-20', '2011-09-26', '10:14:58', '14:39:10', 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (34, 34, 10, 34, 1, NULL, '2011-09-20', '2011-09-26', '10:14:58', '14:41:34', 1);

INSERT INTO detalleejecucionplanificacion (id, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idetapaproduccion, piezareal, fechainicio, fechafin, horainicio, horafin, orden)
VALUES (33, 34, 13, 33, 4, NULL, '2011-09-26', '2011-09-26', '14:39:06', '14:45:18', 2);

--
-- Data for table public.detalleejecucionplanificacioncalidad (OID = 81835) (LIMIT 0,9)
--
INSERT INTO detalleejecucionplanificacioncalidad (iddetalle, idejecucionplanificacioncalidad, ejecucionprocesocalidad, idprocesocalidad, pieza, piezareal, orden, fechainicio, horainicio, fechafin, horafin)
VALUES (1, 1, 1, 1, 13, NULL, 1, '2011-09-18', '03:51:23', '2011-09-19', '21:29:11');

INSERT INTO detalleejecucionplanificacioncalidad (iddetalle, idejecucionplanificacioncalidad, ejecucionprocesocalidad, idprocesocalidad, pieza, piezareal, orden, fechainicio, horainicio, fechafin, horafin)
VALUES (2, 1, 2, 2, 10, NULL, 1, '2011-09-18', '03:51:23', '2011-09-19', '21:45:28');

INSERT INTO detalleejecucionplanificacioncalidad (iddetalle, idejecucionplanificacioncalidad, ejecucionprocesocalidad, idprocesocalidad, pieza, piezareal, orden, fechainicio, horainicio, fechafin, horafin)
VALUES (3, 2, 3, 1, 9, NULL, 1, '2011-09-19', '23:30:51', '2011-09-20', '00:07:24');

INSERT INTO detalleejecucionplanificacioncalidad (iddetalle, idejecucionplanificacioncalidad, ejecucionprocesocalidad, idprocesocalidad, pieza, piezareal, orden, fechainicio, horainicio, fechafin, horafin)
VALUES (4, 2, 4, 1, 11, NULL, 1, '2011-09-19', '23:30:51', '2011-09-20', '00:07:34');

INSERT INTO detalleejecucionplanificacioncalidad (iddetalle, idejecucionplanificacioncalidad, ejecucionprocesocalidad, idprocesocalidad, pieza, piezareal, orden, fechainicio, horainicio, fechafin, horafin)
VALUES (5, 2, 5, 1, 10, NULL, 1, '2011-09-19', '23:30:51', '2011-09-20', '00:07:37');

INSERT INTO detalleejecucionplanificacioncalidad (iddetalle, idejecucionplanificacioncalidad, ejecucionprocesocalidad, idprocesocalidad, pieza, piezareal, orden, fechainicio, horainicio, fechafin, horafin)
VALUES (6, 2, 6, 2, 10, NULL, 1, '2011-09-19', '23:30:51', '2011-09-20', '00:07:41');

INSERT INTO detalleejecucionplanificacioncalidad (iddetalle, idejecucionplanificacioncalidad, ejecucionprocesocalidad, idprocesocalidad, pieza, piezareal, orden, fechainicio, horainicio, fechafin, horafin)
VALUES (7, 2, 7, 1, 13, NULL, 1, '2011-09-19', '23:30:51', '2011-09-20', '00:07:49');

INSERT INTO detalleejecucionplanificacioncalidad (iddetalle, idejecucionplanificacioncalidad, ejecucionprocesocalidad, idprocesocalidad, pieza, piezareal, orden, fechainicio, horainicio, fechafin, horafin)
VALUES (8, 3, 8, 1, 13, NULL, 1, '2011-09-26', '15:00:17', '2011-09-26', '15:01:51');

INSERT INTO detalleejecucionplanificacioncalidad (iddetalle, idejecucionplanificacioncalidad, ejecucionprocesocalidad, idprocesocalidad, pieza, piezareal, orden, fechainicio, horainicio, fechafin, horafin)
VALUES (9, 3, 9, 2, 10, NULL, 1, '2011-09-26', '15:00:17', '2011-09-26', '15:01:56');

--
-- Data for table public.detalleplanificacionproduccion (OID = 81839) (LIMIT 0,53)
--
INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (38, 29, NULL, 1, 11, 4, '2010-11-09', '12:20:36', '2010-11-09', '12:41:36', 1, 2, 4, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (39, 29, NULL, 1, 6, 1, '2010-11-09', '11:55:36', '2010-11-09', '12:05:36', 1, 1, 4, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (40, 29, NULL, 1, 8, 2, '2010-11-09', '08:00:36', '2010-11-09', '11:40:36', 1, 1, 3, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (44, 31, NULL, 4, 8, 3, '2010-11-10', '06:43:12', '2010-11-10', '09:44:12', 3, 2, 3, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (45, 31, NULL, 1, 8, 3, '2010-11-09', '11:48:12', '2010-11-10', '06:28:12', 1, 1, 3, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (46, 31, NULL, 1, 6, 4, '2010-11-09', '11:23:12', '2010-11-09', '11:33:12', 1, 2, 4, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (47, 31, NULL, 1, 11, 1, '2010-11-09', '10:47:12', '2010-11-09', '11:08:12', 1, 1, 4, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (48, 33, NULL, 1, 13, 2, '2011-04-11', '08:00:12', '2011-04-11', '09:02:12', 1, 1, 7, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (49, 34, NULL, 1, 11, 2, '2011-04-11', '08:00:37', '2011-04-11', '08:21:37', 1, 1, 4, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (50, 35, NULL, 4, 11, 2, '2011-04-11', '08:00:31', '2011-04-12', '13:39:31', 1, 1, 6, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (52, 37, NULL, 1, 13, 2, '2011-06-10', '08:00:35', '2011-06-10', '09:02:35', 1, 1, 7, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (53, 38, NULL, 2, 8, 2, '2011-07-18', '08:00:00', '2011-07-18', '10:27:00', 1, 1, 3, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (54, 39, NULL, 1, 6, 2, '2011-07-18', '08:00:24', '2011-07-18', '08:10:24', NULL, 1, 4, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (55, 40, NULL, 1, 13, NULL, '2011-07-18', '08:00:17', '2011-07-18', '09:02:17', NULL, 1, 7, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (56, 40, NULL, 1, 10, NULL, '2011-07-18', '08:00:17', '2011-07-18', '08:51:17', NULL, 2, 7, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (57, 40, NULL, 1, 11, NULL, '2011-07-18', '08:00:17', '2011-07-18', '08:21:17', NULL, 1, 6, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (58, 40, NULL, 1, 10, NULL, '2011-07-18', '08:00:17', '2011-07-18', '08:51:17', NULL, 2, 6, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (59, 40, NULL, 1, 9, NULL, '2011-07-18', '08:00:17', '2011-07-18', '08:09:17', NULL, 3, 6, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (60, 41, NULL, 5, 10, 2, '2011-07-18', '08:00:29', '2011-07-19', '09:08:29', NULL, 1, 7, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (61, 41, NULL, 3, 13, 1, '2011-07-18', '08:00:29', '2011-07-18', '12:10:29', NULL, 2, 7, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (62, 41, NULL, 1, 6, 4, '2011-07-18', '08:00:29', '2011-07-18', '08:10:29', NULL, 1, 4, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (63, 41, NULL, 1, 11, 3, '2011-07-18', '08:00:29', '2011-07-18', '08:21:29', NULL, 2, 4, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (51, 36, 5, 1, 8, 2, '2011-04-25', '08:00:42', '2011-04-25', '11:40:42', 1, 1, 3, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (64, 42, 6, 3, 10, 2, '2011-08-22', '08:00:48', '2011-08-22', '11:24:48', 1, 1, 7, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (65, 42, 7, 1, 13, 1, '2011-08-22', '08:00:49', '2011-08-22', '09:02:49', 9, 2, 7, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (66, 43, 8, 3, 10, 2, '2011-08-22', '08:00:29', '2011-08-22', '11:24:29', 2, 1, 7, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (67, 43, 9, 1, 13, 4, '2011-08-22', '08:00:29', '2011-08-22', '09:02:29', 4, 2, 7, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (68, 44, 10, 4, 11, 4, '2011-08-22', '08:00:31', '2011-08-23', '13:39:31', 10, 1, 6, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (69, 44, 11, 3, 10, 3, '2011-08-22', '08:00:31', '2011-08-22', '11:24:31', 8, 1, 6, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (70, 44, 12, 1, 9, 1, '2011-08-22', '08:00:31', '2011-08-22', '08:09:31', 4, 1, 6, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (71, 45, NULL, 1, 13, 2, '2011-09-05', '08:00:58', '2011-09-05', '09:02:58', 1, 1, 7, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (72, 45, NULL, 4, 10, 1, '2011-09-05', '08:00:58', '2011-09-06', '09:08:58', 8, 1, 7, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (73, 46, NULL, 1, 8, 4, '2011-09-05', '08:00:07', '2011-09-05', '11:40:07', 1, 1, 3, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (74, 46, NULL, 1, 11, 3, '2011-09-05', '08:00:07', '2011-09-05', '08:21:07', 7, 1, 4, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (75, 46, NULL, 1, 6, 2, '2011-09-05', '08:00:07', '2011-09-05', '08:10:07', 3, 1, 4, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (84, 44, 13, 1, 9, 1, '2011-09-05', '08:00:00', '2011-09-05', '12:00:00', 4, 2, 6, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (89, 52, 26, 1, 9, 2, '2011-09-20', '08:00:04', '2011-09-20', '08:09:04', 1, 1, 6, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (90, 52, 27, 1, 11, 1, '2011-09-20', '08:00:04', '2011-09-20', '08:21:04', 10, 1, 6, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (91, 52, 28, 1, 10, 4, '2011-09-20', '08:00:04', '2011-09-20', '08:51:04', 2, 1, 6, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (92, 52, 29, 5, 10, 3, '2011-09-20', '08:00:04', '2011-09-21', '09:08:04', 8, 1, 7, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (93, 52, 30, 4, 13, 2, '2011-09-20', '09:00:00', '2011-09-21', '17:40:00', 11, 1, 7, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (94, 53, 32, 1, 13, 1, '2011-09-20', '10:08:00', '2011-09-20', '11:10:00', 10, 1, 7, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (95, 53, 33, 4, 13, 4, '2011-09-20', '11:25:59', '2011-09-22', '11:05:59', 7, 2, 7, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (96, 53, 34, 1, 10, 5, '2011-09-20', '10:08:59', '2011-09-20', '10:59:59', 3, 1, 7, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (83, 51, 24, 3, 10, 4, '2011-09-05', '08:00:34', '2011-09-05', '11:24:34', 6, 1, 7, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (82, 51, 25, 1, 13, 2, '2011-09-05', '08:00:34', '2011-09-05', '09:02:34', 9, 1, 7, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (97, 54, NULL, 1, 9, 2, '2011-10-03', '08:00:00', '2011-10-03', '08:09:00', 1, 1, 6, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (98, 54, NULL, 1, 11, 1, '2011-10-03', '08:21:00', '2011-10-03', '08:21:28', 9, 1, 6, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (99, 54, NULL, 1, 10, 1, '2011-10-03', '08:21:00', '2011-10-03', '09:12:00', 10, 1, 6, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (100, 54, NULL, 5, 10, 1, '2011-10-03', '09:12:00', '2011-10-04', '10:20:00', 11, 1, 7, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (101, 54, NULL, 4, 13, 4, '2011-10-03', '08:00:49', '2011-10-04', '16:40:49', 2, 1, 7, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (102, 55, NULL, 1, 13, 1, '2011-10-07', '08:00:56', '2011-10-07', '09:02:56', 9, 1, 7, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto)
VALUES (103, 55, NULL, 3, 10, 1, '2011-10-07', '09:02:00', '2011-10-07', '12:26:00', 2, 1, 7, 0);

--
-- Data for table public.detalleproducto (OID = 81843) (LIMIT 0,10)
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

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (20, 12, 1, '', 7);

--
-- Data for table public.detallepedido (OID = 81847) (LIMIT 0,28)
--
INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (35, 27, 67, 1, 3);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (36, 27, 102, 1, 4);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (40, 30, 102, 1, 4);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (41, 30, 67, 1, 3);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (42, 31, 92, 1, 7);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (43, 32, 145, 1, 6);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (44, 33, 67, 1, 3);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (45, 33, 102, 2, 4);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (46, 33, 80, 1, 5);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (47, 33, 92, 1, 7);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (48, 34, 92, 1, 7);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (49, 34, 145, 1, 6);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (50, 35, 102, 1, 4);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (51, 35, 67, 1, 3);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (52, 36, 67, 1, 3);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (53, 36, 80, 1, 5);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (54, 37, 92, 1, 7);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (55, 37, 102, 2, 4);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (56, 38, 92, 1, 7);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (57, 39, 92, 1, 7);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (58, 40, 92, 1, 7);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (59, 41, 92, 1, 7);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (60, 43, 92, 1, 7);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (61, 44, 145, 1, 6);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (62, 44, 92, 2, 7);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (63, 45, NULL, 1, 4);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (64, 46, 92, 1, 7);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (65, 47, NULL, 1, 4);

--
-- Data for table public.detalletrabajotercerizado (OID = 81851) (LIMIT 0,2)
--
INSERT INTO detalletrabajotercerizado (iddetalle, idtrabajotercerizado, montoparcial, cantidad, descripcion, fechaenvioreal, fechaentregareal, pieza, proceso, estado)
VALUES (1, 1, 54, 1, NULL, NULL, NULL, 6, 6, 1);

INSERT INTO detalletrabajotercerizado (iddetalle, idtrabajotercerizado, montoparcial, cantidad, descripcion, fechaenvioreal, fechaentregareal, pieza, proceso, estado)
VALUES (2, 1, 55, 1, NULL, NULL, NULL, 6, 3, 1);

--
-- Data for table public.detalleplanificacioncalidad (OID = 81855) (LIMIT 0,11)
--
INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden)
VALUES (1, 1, 1, '2011-09-05', '2011-09-05', '11:39:34', '11:44:34', 1, 13, 7, 2, NULL, 1);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden)
VALUES (2, 1, 2, '2011-09-05', '2011-09-05', '11:39:34', '11:49:34', 2, 10, 7, 4, NULL, 1);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden)
VALUES (3, 2, NULL, '2011-09-19', '2011-09-19', '11:39:00', '11:44:00', 1, 13, 7, 2, 1, 1);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden)
VALUES (4, 2, NULL, '2011-09-19', '2011-09-19', '11:39:00', '11:44:00', 1, 10, 7, 4, NULL, 1);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden)
VALUES (5, 3, 3, '2011-09-21', '2011-09-22', '17:55:00', '09:00:00', 1, 9, 6, 2, NULL, 1);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden)
VALUES (6, 3, 4, '2011-09-21', '2011-09-22', '17:55:00', '09:00:00', 1, 11, 6, 1, NULL, 1);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden)
VALUES (7, 3, 5, '2011-09-21', '2011-09-22', '17:55:00', '09:00:00', 1, 10, 6, 4, NULL, 1);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden)
VALUES (8, 3, 6, '2011-09-21', '2011-09-22', '17:55:00', '09:05:00', 2, 10, 7, 3, NULL, 1);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden)
VALUES (9, 3, 7, '2011-09-22', '2011-09-22', '09:00:00', '09:05:00', 1, 13, 7, 2, NULL, 1);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden)
VALUES (10, 4, 8, '2011-09-22', '2011-09-22', '11:20:59', '11:25:59', 1, 13, 7, 4, 10, 1);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden)
VALUES (11, 4, 9, '2011-09-22', '2011-09-22', '11:20:59', '11:30:59', 2, 10, 7, 5, NULL, 1);

--
-- Data for table public.detallecompra (OID = 81859) (LIMIT 0,2)
--
INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (2, 2, 1234567, 10, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (3, 2, 2345, 4, 0, NULL, 1);

--
-- Data for table public.detalleremito (OID = 81863) (LIMIT 0,5)
--
INSERT INTO detalleremito (iddetalle, idremito, cantidad, descripcion, producto)
VALUES (5, 3, 1, '', 4);

INSERT INTO detalleremito (iddetalle, idremito, cantidad, descripcion, producto)
VALUES (6, 3, 1, '', 3);

INSERT INTO detalleremito (iddetalle, idremito, cantidad, descripcion, producto)
VALUES (7, 4, 1, '', 6);

INSERT INTO detalleremito (iddetalle, idremito, cantidad, descripcion, producto)
VALUES (8, 4, 2, '', 7);

INSERT INTO detalleremito (iddetalle, idremito, cantidad, descripcion, producto)
VALUES (9, 5, 1, '', 7);

--
-- Data for table public.tiporeclamo (OID = 81867) (LIMIT 0,1)
--
INSERT INTO tiporeclamo (idtiporeclamo, nombre, descripcion)
VALUES (1, 'RECLAMO', NULL);

--
-- Data for table public.reclamoproveedor (OID = 81875) (LIMIT 0,1)
--
INSERT INTO reclamoproveedor (idreclamo, nroreclamo, tiporeclamo, motivo, fechareclamo, compra, idestadoreclamo)
VALUES (2, 0, 1, 'jbkj', '2011-09-10', 2, 2);

--
-- Data for table public.detallereclamoproveedor (OID = 81887) (LIMIT 0,1)
--
INSERT INTO detallereclamoproveedor (iddetalle, idreclamo, cantidad, descripcion, motivo, iddetallecompra, fechaegreso, idcompra)
VALUES (1, 2, 1234567, 'aluminio 1414', 'no se', 2, '2011-09-20', 2);

--
-- Data for table public.proveedorxmateriaprima (OID = 81895) (LIMIT 0,10)
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

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (3, 8, 1);

--
-- Data for table public.piezaxetapadeproduccion (OID = 81904) (LIMIT 0,1)
--
INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (6, 1, '04:00:00', 'Seteado a mano');

--
-- Data for table public.empleadoxturno (OID = 81907) (LIMIT 0,2)
--
INSERT INTO empleadoxturno (idempleado, idturno)
VALUES (5, 1);

INSERT INTO empleadoxturno (idempleado, idturno)
VALUES (5, 2);

--
-- Data for table public.provincia (OID = 81910) (LIMIT 0,7)
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
-- Data for table public.localidad (OID = 81914) (LIMIT 0,10)
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
-- Data for table public.barrio (OID = 81918) (LIMIT 0,6)
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
-- Data for table public.usuarioxrol (OID = 81922) (LIMIT 0,9)
--
INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (1, 2);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (1, 11);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (5, 11);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (1, 12);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (4, 12);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (7, 13);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (1, 14);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (1, 4);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (8, 1);

--
-- Data for table public.estadodetallecompra (OID = 81956) (LIMIT 0,8)
--
INSERT INTO estadodetallecompra (idestado, nombre, descripcion)
VALUES (1, 'GENERADA', NULL);

INSERT INTO estadodetallecompra (idestado, nombre, descripcion)
VALUES (2, 'RECIBIDA', NULL);

INSERT INTO estadodetallecompra (idestado, nombre, descripcion)
VALUES (3, 'EN CALIDAD', NULL);

INSERT INTO estadodetallecompra (idestado, nombre, descripcion)
VALUES (4, 'ACEPTADA', NULL);

INSERT INTO estadodetallecompra (idestado, nombre, descripcion)
VALUES (5, 'FINALIZADA', NULL);

INSERT INTO estadodetallecompra (idestado, nombre, descripcion)
VALUES (6, 'EN SOLICITUD RECLAMO', NULL);

INSERT INTO estadodetallecompra (idestado, nombre, descripcion)
VALUES (7, 'RECLAMADA', NULL);

INSERT INTO estadodetallecompra (idestado, nombre, descripcion)
VALUES (8, 'RECHAZADA', NULL);

--
-- Data for table public.estadodetalletrabajotercerizado (OID = 81960) (LIMIT 0,9)
--
INSERT INTO estadodetalletrabajotercerizado (idestado, nombre, descripcion)
VALUES (1, 'GENERADO', NULL);

INSERT INTO estadodetalletrabajotercerizado (idestado, nombre, descripcion)
VALUES (2, 'EnEmpresaMetalurgica', NULL);

INSERT INTO estadodetalletrabajotercerizado (idestado, nombre, descripcion)
VALUES (3, 'Recibido', NULL);

INSERT INTO estadodetalletrabajotercerizado (idestado, nombre, descripcion)
VALUES (4, 'EnSolicitudReclamo', NULL);

INSERT INTO estadodetalletrabajotercerizado (idestado, nombre, descripcion)
VALUES (5, 'EnCalidad', NULL);

INSERT INTO estadodetalletrabajotercerizado (idestado, nombre, descripcion)
VALUES (6, 'Reclamado', NULL);

INSERT INTO estadodetalletrabajotercerizado (idestado, nombre, descripcion)
VALUES (7, 'Aceptado', NULL);

INSERT INTO estadodetalletrabajotercerizado (idestado, nombre, descripcion)
VALUES (8, 'Rechazado', NULL);

INSERT INTO estadodetalletrabajotercerizado (idestado, nombre, descripcion)
VALUES (9, 'Realizado', NULL);

--
-- Data for table public.estadoejecucionprocesocalidad (OID = 81964) (LIMIT 0,6)
--
INSERT INTO estadoejecucionprocesocalidad (idestado, nombre, descripcion)
VALUES (1, 'GENERADA', NULL);

INSERT INTO estadoejecucionprocesocalidad (idestado, nombre, descripcion)
VALUES (2, 'ENEJECUCION', NULL);

INSERT INTO estadoejecucionprocesocalidad (idestado, nombre, descripcion)
VALUES (3, 'DETENIDA', NULL);

INSERT INTO estadoejecucionprocesocalidad (idestado, nombre, descripcion)
VALUES (4, 'FINALIZADA', NULL);

INSERT INTO estadoejecucionprocesocalidad (idestado, nombre, descripcion)
VALUES (5, 'REPLANIFICADA', NULL);

INSERT INTO estadoejecucionprocesocalidad (idestado, nombre, descripcion)
VALUES (6, 'CANCELADA', NULL);

--
-- Data for table public.detalleproductoreal (OID = 81968) (LIMIT 0,10)
--
INSERT INTO detalleproductoreal (iddetalle, idproductoreal, "cantidadPiezas", descripcion, idpiezareal, detalleproducto)
VALUES (2, 2, NULL, NULL, 89, NULL);

INSERT INTO detalleproductoreal (iddetalle, idproductoreal, "cantidadPiezas", descripcion, idpiezareal, detalleproducto)
VALUES (3, 2, NULL, NULL, 85, NULL);

INSERT INTO detalleproductoreal (iddetalle, idproductoreal, "cantidadPiezas", descripcion, idpiezareal, detalleproducto)
VALUES (4, 3, NULL, NULL, NULL, NULL);

INSERT INTO detalleproductoreal (iddetalle, idproductoreal, "cantidadPiezas", descripcion, idpiezareal, detalleproducto)
VALUES (5, 3, NULL, NULL, NULL, NULL);

INSERT INTO detalleproductoreal (iddetalle, idproductoreal, "cantidadPiezas", descripcion, idpiezareal, detalleproducto)
VALUES (6, 4, NULL, NULL, NULL, NULL);

INSERT INTO detalleproductoreal (iddetalle, idproductoreal, "cantidadPiezas", descripcion, idpiezareal, detalleproducto)
VALUES (7, 4, NULL, NULL, NULL, NULL);

INSERT INTO detalleproductoreal (iddetalle, idproductoreal, "cantidadPiezas", descripcion, idpiezareal, detalleproducto)
VALUES (8, 4, NULL, NULL, NULL, NULL);

INSERT INTO detalleproductoreal (iddetalle, idproductoreal, "cantidadPiezas", descripcion, idpiezareal, detalleproducto)
VALUES (9, 5, NULL, NULL, NULL, NULL);

INSERT INTO detalleproductoreal (iddetalle, idproductoreal, "cantidadPiezas", descripcion, idpiezareal, detalleproducto)
VALUES (10, 5, NULL, NULL, NULL, NULL);

INSERT INTO detalleproductoreal (iddetalle, idproductoreal, "cantidadPiezas", descripcion, idpiezareal, detalleproducto)
VALUES (11, 5, NULL, NULL, NULL, NULL);

--
-- Data for table public.productoreal (OID = 81972) (LIMIT 0,5)
--
INSERT INTO productoreal (idproductoreal, nroproducto, fechaterminacion, fechainicioproduccion, idpedido, codigobarra, estado, producto)
VALUES (1, 1, NULL, NULL, 43, 91, NULL, 7);

INSERT INTO productoreal (idproductoreal, nroproducto, fechaterminacion, fechainicioproduccion, idpedido, codigobarra, estado, producto)
VALUES (2, 2, NULL, NULL, 43, 92, NULL, 7);

INSERT INTO productoreal (idproductoreal, nroproducto, fechaterminacion, fechainicioproduccion, idpedido, codigobarra, estado, producto)
VALUES (4, 3, NULL, NULL, 44, 100, 4, 6);

INSERT INTO productoreal (idproductoreal, nroproducto, fechaterminacion, fechainicioproduccion, idpedido, codigobarra, estado, producto)
VALUES (3, 3, NULL, NULL, 44, 99, 4, 7);

INSERT INTO productoreal (idproductoreal, nroproducto, fechaterminacion, fechainicioproduccion, idpedido, codigobarra, estado, producto)
VALUES (5, 4, NULL, NULL, 46, 103, 4, 7);

--
-- Data for table public.estadoproductoreal (OID = 81976) (LIMIT 0,11)
--
INSERT INTO estadoproductoreal (idestado, nombre, descripcion)
VALUES (1, 'GENERADO', NULL);

INSERT INTO estadoproductoreal (idestado, nombre, descripcion)
VALUES (2, 'EN PRODUCCION', NULL);

INSERT INTO estadoproductoreal (idestado, nombre, descripcion)
VALUES (3, 'EN ALMACEN', NULL);

INSERT INTO estadoproductoreal (idestado, nombre, descripcion)
VALUES (4, 'EN CALIDAD', NULL);

INSERT INTO estadoproductoreal (idestado, nombre, descripcion)
VALUES (5, 'EN TRABAJO TERCERIZADO', NULL);

INSERT INTO estadoproductoreal (idestado, nombre, descripcion)
VALUES (6, 'EN ARMADO', NULL);

INSERT INTO estadoproductoreal (idestado, nombre, descripcion)
VALUES (7, 'LISTO PARA ENTREGAR', NULL);

INSERT INTO estadoproductoreal (idestado, nombre, descripcion)
VALUES (8, 'ENTREGADO', NULL);

INSERT INTO estadoproductoreal (idestado, nombre, descripcion)
VALUES (9, 'RECLAMADO', NULL);

INSERT INTO estadoproductoreal (idestado, nombre, descripcion)
VALUES (10, 'RECHAZADO', NULL);

INSERT INTO estadoproductoreal (idestado, nombre, descripcion)
VALUES (11, 'SCRAP', NULL);

--
-- Data for table public.detallefactura (OID = 81980) (LIMIT 0,5)
--
INSERT INTO detallefactura (iddetalle, idfactura, idpedido, montoparcial, cantidad, iddetallepedido)
VALUES (5, 3, 27, 102, 1, 36);

INSERT INTO detallefactura (iddetalle, idfactura, idpedido, montoparcial, cantidad, iddetallepedido)
VALUES (6, 3, 27, 67, 1, 35);

INSERT INTO detallefactura (iddetalle, idfactura, idpedido, montoparcial, cantidad, iddetallepedido)
VALUES (7, 4, 44, 145, 1, 61);

INSERT INTO detallefactura (iddetalle, idfactura, idpedido, montoparcial, cantidad, iddetallepedido)
VALUES (8, 4, 44, 184, 2, 62);

INSERT INTO detallefactura (iddetalle, idfactura, idpedido, montoparcial, cantidad, iddetallepedido)
VALUES (9, 5, 46, 92, 1, 64);

--
-- Data for table public.detallepresupuesto (OID = 81984) (LIMIT 0,27)
--
INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (40, 40, 35, 3, 1, 67);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (41, 40, 36, 4, 1, 102);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (55, 48, 40, 4, 1, 102);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (56, 48, 41, 3, 1, 67);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (57, 49, 42, 7, 1, 92);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (58, 50, 43, 6, 1, 145);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (59, 51, 44, 3, 1, 67);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (60, 51, 45, 4, 2, 102);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (61, 51, 46, 5, 1, 80);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (62, 51, 47, 7, 1, 92);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (63, 52, 48, 7, 1, 92);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (64, 52, 49, 6, 1, 145);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (65, 53, 50, 4, 1, 102);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (66, 53, 51, 3, 1, 67);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (67, 54, 52, 3, 1, 67);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (68, 54, 53, 5, 1, 80);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (69, 55, 54, 7, 1, 92);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (70, 55, 55, 4, 2, 102);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (71, 56, 56, 7, 1, 92);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (72, 57, 57, 7, 1, 92);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (73, 58, 58, 7, 1, 92);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (74, 59, 58, 7, 1, 92);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (76, 61, 59, 7, 1, 92);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (77, 62, 60, 7, 1, 92);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (78, 63, 61, 6, 1, 145);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (79, 63, 62, 7, 2, 92);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (80, 64, 64, 7, 1, 92);

--
-- Data for table public.estadoremito (OID = 81988) (LIMIT 0,1)
--
INSERT INTO estadoremito (idestado, nombre, descripcion)
VALUES (1, 'GENERADO', 'GENERADO');

--
-- Data for table public.tipomaterial (OID = 81992) (LIMIT 0,3)
--
INSERT INTO tipomaterial (idtipomaterial, nombre, descripcion)
VALUES (2, 'ACERO', '');

INSERT INTO tipomaterial (idtipomaterial, nombre, descripcion)
VALUES (4, 'PLASTICO', '');

INSERT INTO tipomaterial (idtipomaterial, nombre, descripcion)
VALUES (5, 'METAL', '');

--
-- Data for table public.unidadmedida (OID = 82206) (LIMIT 0,4)
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
-- Data for table public.detallepiezapresupuesto (OID = 82216) (LIMIT 0,54)
--
INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('03:40:32', 66, 1, 54);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:10:58', 67, 1, 55);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:21:58', 68, 1, 56);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:10:58', 93, 1, 79);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:21:58', 94, 1, 80);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('03:40:32', 95, 1, 81);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('03:01:00', 96, 4, 81);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('03:24:00', 97, 3, 82);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('01:02:30', 98, 1, 83);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:09:00', 99, 1, 84);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('03:24:00', 100, 3, 85);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('14:39:00', 101, 4, 86);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('03:40:32', 102, 1, 87);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:10:58', 103, 1, 88);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('01:27:00', 104, 3, 89);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('09:20:00', 105, 3, 90);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('10:08:00', 106, 4, 91);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('01:02:30', 107, 1, 92);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:51:12', 108, 1, 93);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('01:02:30', 109, 1, 94);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:09:00', 110, 1, 95);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:51:12', 111, 1, 96);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:21:58', 112, 1, 97);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:10:58', 113, 1, 98);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:21:58', 114, 1, 99);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('14:42:00', 115, 3, 100);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('02:27:01', 116, 2, 101);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('03:01:00', 117, 5, 101);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('08:20:00', 118, 1, 102);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('09:20:00', 119, 3, 102);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('21:20:00', 120, 4, 102);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('10:08:00', 121, 5, 103);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('04:10:00', 122, 3, 104);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:10:58', 123, 1, 105);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:21:58', 124, 1, 106);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('03:24:00', 125, 3, 107);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('01:02:30', 126, 1, 108);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('03:24:00', 127, 3, 109);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('01:02:30', 128, 1, 110);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('03:24:00', 129, 3, 111);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('10:08:00', 130, 4, 112);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('01:02:30', 131, 1, 113);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('03:24:00', 134, 3, 116);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('01:02:30', 135, 1, 117);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('03:24:00', 136, 3, 118);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('01:02:30', 137, 1, 119);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:09:00', 138, 1, 120);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:51:12', 139, 1, 121);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:21:58', 140, 1, 122);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('10:08:00', 141, 5, 123);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('17:40:00', 142, 4, 124);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:51:12', 143, 1, 125);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('01:02:30', 144, 1, 126);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('17:40:00', 145, 4, 126);

--
-- Data for table public.detalleproductopresupuesto (OID = 82222) (LIMIT 0,49)
--
INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (54, 40, 8, 4, 1, 1, 25, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (56, 41, 11, 4, 1, 1, 25, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (55, 41, 6, 1, 1, 1, 20, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (103, 69, 10, 5, 1, 1, 19, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (104, 69, 13, 1, 1, 1, 20, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (105, 70, 6, 1, 1, 1, 20, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (81, 56, 8, 4, 1, 1, 25, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (80, 55, 11, 4, 1, 1, 25, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (79, 55, 6, 1, 1, 1, 20, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (106, 70, 11, 4, 1, 1, 25, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (83, 57, 13, 1, 1, 1, 20, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (82, 57, 10, 5, 1, 1, 19, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (123, 79, 10, 5, 1, 1, 19, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (120, 78, 9, 5, 1, 2, 19, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (86, 58, 11, 4, 1, 1, 25, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (85, 58, 10, 5, 1, 1, 19, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (84, 58, 9, 5, 1, 2, 19, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (107, 71, 10, 5, 1, 1, 19, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (108, 71, 13, 1, 1, 1, 20, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (87, 59, 8, 4, 1, 1, 25, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (89, 60, 11, 4, 1, 1, 25, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (88, 60, 6, 1, 1, 1, 20, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (90, 61, 12, 1, 1, 1, 20, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (92, 62, 13, 1, 1, 1, 20, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (91, 62, 10, 5, 1, 1, 19, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (109, 72, 10, 5, 1, 1, 19, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (110, 72, 13, 1, 1, 1, 20, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (111, 73, 10, NULL, 0, 1, 0, 0);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (94, 63, 13, 1, 1, 1, 20, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (97, 64, 11, 4, 1, 1, 25, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (93, 63, 10, 5, 1, 1, 19, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (96, 64, 10, 5, 1, 1, 19, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (95, 64, 9, 5, 1, 2, 19, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (113, 74, 13, 1, 1, 1, 20, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (98, 65, 6, 1, 1, 1, 20, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (100, 66, 8, 4, 1, 1, 25, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (99, 65, 11, 4, 1, 1, 25, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (112, 74, 10, 5, 1, 1, 19, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (102, 68, 12, 1, 1, 1, 20, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (101, 67, 8, 4, 1, 1, 25, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (126, 80, 13, 1, 1, 1, 22, 2);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (125, 80, 10, 5, 1, 1, 19, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (117, 76, 13, 1, 1, 1, 20, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (116, 76, 10, 5, 1, 1, 19, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (119, 77, 13, 1, 1, 1, 20, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (118, 77, 10, 5, 1, 1, 19, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (122, 78, 11, 4, 1, 1, 25, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (124, 79, 13, 1, 1, 1, 20, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (121, 78, 10, 5, 1, 1, 19, 1);

--
-- Data for table public.detallepiezacalidadpresupuesto (OID = 82228) (LIMIT 0,52)
--
INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (48, 1, '00:10:00', 2, 54);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (49, 1, '00:10:00', 2, 54);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (50, 1, '00:10:00', 2, 55);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (51, 1, '00:10:00', 2, 56);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (52, 1, '00:10:00', 2, 54);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (53, 1, '00:10:00', 2, 55);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (54, 1, '00:10:00', 2, 56);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (66, 1, '00:10:00', 2, 79);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (67, 1, '00:10:00', 2, 80);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (68, 1, '00:05:00', 1, 81);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (69, 1, '00:05:00', 1, 82);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (70, 1, '00:10:00', 2, 83);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (71, 1, '00:05:00', 1, 84);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (72, 1, '00:05:00', 1, 85);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (73, 1, '00:05:00', 1, 86);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (74, 1, '00:05:00', 1, 87);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (75, 1, '00:05:00', 1, 88);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (76, 1, '00:05:00', 1, 89);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (77, 1, '00:05:00', 1, 90);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (78, 1, '00:05:00', 1, 91);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (79, 1, '00:05:00', 1, 92);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (80, 1, '00:05:00', 1, 93);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (81, 1, '00:05:00', 1, 94);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (82, 1, '00:05:00', 1, 95);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (83, 1, '00:05:00', 1, 96);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (84, 1, '00:05:00', 1, 97);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (85, 1, '00:05:00', 1, 98);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (86, 1, '00:05:00', 1, 99);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (87, 1, '00:10:00', 2, 100);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (88, 1, '00:10:00', 2, 101);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (89, 1, '00:05:00', 1, 102);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (90, 1, '00:05:00', 1, 103);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (91, 1, '00:10:00', 2, 104);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (92, 1, '00:05:00', 1, 105);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (93, 1, '00:10:00', 2, 106);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (94, 1, '00:05:00', 1, 107);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (95, 1, '00:05:00', 1, 108);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (96, 1, '00:05:00', 1, 109);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (97, 1, '00:05:00', 1, 110);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (99, 1, '00:10:00', 2, 112);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (100, 1, '00:05:00', 1, 113);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (101, 1, '00:10:00', 2, 116);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (102, 1, '00:05:00', 1, 117);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (103, 1, '00:05:00', 1, 118);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (104, 1, '00:05:00', 1, 119);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (105, 1, '00:05:00', 1, 120);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (106, 1, '00:05:00', 1, 121);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (107, 1, '00:05:00', 1, 122);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (108, 1, '00:10:00', 2, 123);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (109, 1, '00:05:00', 1, 124);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (110, 1, '00:10:00', 2, 125);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (111, 1, '00:05:00', 1, 126);

--
-- Data for table public.calendario (OID = 82251) (LIMIT 0,11)
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

INSERT INTO calendario (dia, mes, anio, id, fecha, todoeldia, horadesde, horahasta)
VALUES (15, 2, 2009, 9, '2011-03-02', true, '06:41:03', '06:41:04');

INSERT INTO calendario (dia, mes, anio, id, fecha, todoeldia, horadesde, horahasta)
VALUES (24, 3, 2011, 10, '2011-03-24', true, NULL, NULL);

INSERT INTO calendario (dia, mes, anio, id, fecha, todoeldia, horadesde, horahasta)
VALUES (25, 3, 2011, 11, '2011-03-25', true, NULL, NULL);

INSERT INTO calendario (dia, mes, anio, id, fecha, todoeldia, horadesde, horahasta)
VALUES (21, 9, 2011, 12, '2011-09-21', true, NULL, NULL);

--
-- Data for table public.disponibilidadhoraria (OID = 82261) (LIMIT 0,12)
--
INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (9, '2011-10-02', NULL, 2, '08:30:00', '09:00:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (12, '2011-10-03', NULL, NULL, '08:00:00', '08:09:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (13, '2011-10-03', NULL, NULL, '08:21:00', '08:21:28');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (14, '2011-10-03', NULL, NULL, '08:21:00', '09:12:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (15, '2011-10-03', NULL, NULL, '09:12:00', '17:00:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (16, '2011-10-04', NULL, NULL, '08:00:00', '10:20:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (17, '2011-10-03', NULL, NULL, '08:00:49', '17:00:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (18, '2011-10-04', NULL, NULL, '08:00:00', '16:40:49');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (10, '2011-10-02', NULL, 2, '08:00:00', '08:29:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (11, '2011-10-02', NULL, 2, '12:00:00', '18:00:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (19, '2011-10-07', NULL, 1, '08:00:56', '09:02:56');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (20, '2011-10-07', NULL, 1, '09:02:00', '12:26:00');

--
-- Data for table public.estadoplanificacionproduccion (OID = 82267) (LIMIT 0,3)
--
INSERT INTO estadoplanificacionproduccion (id, nombre, descripcion)
VALUES (1, 'REC-ASIG', 'Recusos Asignados');

INSERT INTO estadoplanificacionproduccion (id, nombre, descripcion)
VALUES (2, 'MP-ASIG', 'Materia Prima Asignada');

INSERT INTO estadoplanificacionproduccion (id, nombre, descripcion)
VALUES (3, 'GENERADA', 'Estado inicial de la planificación');

--
-- Data for table public.maquina (OID = 82276) (LIMIT 0,11)
--
INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (1, 'TORNO-1', 1, '', 1, 1, '2010-10-10', NULL, '00:03:00', 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (9, 'TORNO-2', 1, NULL, 1, 1, '2010-10-19', NULL, '00:02:00', 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (10, 'TORNO-3', 1, NULL, 1, 1, '2010-10-19', NULL, '00:03:00', 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (11, 'TORNO-4', 1, NULL, 1, 1, '2010-10-19', NULL, '00:03:00', 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (2, 'FRESADORA PLANETARIA-1', 2, '', 1, 3, '2010-10-19', NULL, '00:02:00', 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (7, 'FRESADORA UNIVERSAL-2', 2, NULL, 1, 3, '2010-10-19', NULL, '00:02:00', 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (8, 'FRESADORA PLANETARIA-2', 2, NULL, 1, 3, '2010-10-19', NULL, '00:02:00', 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (6, 'FRESADORA UNIVERSAL-1', 2, NULL, 1, 3, '2010-10-19', NULL, '00:02:00', 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (3, 'AFILADORA', 3, NULL, 1, 4, '2010-11-04', NULL, NULL, 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (4, 'RECTIFICADORA', 3, NULL, 1, 5, '2010-11-09', NULL, NULL, 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (12, 'hhhhhh', 3, 'iyg', 1, 1, '2011-09-20', NULL, '00:05:00', 2);

--
-- Data for table public.piezareal (OID = 82282) (LIMIT 0,50)
--
INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (43, 8, 1, 8, 42);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (44, 11, 1, 11, 43);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (45, 6, 1, 6, 44);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (46, 8, 1, 8, 45);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (47, 11, 1, 11, 46);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (48, 6, 1, 6, 47);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (49, 8, 1, 8, 48);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (50, 11, 1, 11, 49);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (51, 6, 1, 6, 50);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (52, 8, 1, 8, 51);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (53, 11, 1, 11, 52);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (54, 6, 1, 6, 53);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (55, 11, 1, 11, 54);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (56, 10, 1, 10, 55);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (57, 9, 1, 9, 56);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (58, 9, 1, 9, 57);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (61, 10, 1, 12, 61);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (62, 10, 1, 13, 62);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (63, 9, 1, 14, 63);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (64, 9, 1, 14, 64);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (65, 13, 1, 15, 65);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (66, 11, 1, 16, 66);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (67, 10, 1, 17, 67);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (68, 13, 1, 18, 68);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (69, 12, 1, 19, 69);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (70, 6, 1, 20, 70);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (71, 8, 1, 21, 71);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (72, 11, 1, 22, 72);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (73, 10, 1, 23, 73);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (74, 13, 1, 24, 74);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (75, 6, 1, 25, 75);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (76, 11, 1, 26, 76);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (77, 10, 1, 27, 77);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (78, 13, 1, 28, 78);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (79, 10, 1, 29, 79);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (80, 13, 1, 30, 80);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (81, 11, 1, 31, 81);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (82, 10, 1, 32, 82);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (83, 9, 1, 33, 83);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (84, 9, 1, 33, 84);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (85, 13, 1, 34, 85);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (89, 10, 1, 35, 89);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (90, 11, 1, 36, 93);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (91, 13, 1, 37, 94);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (92, 10, 1, 38, 95);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (93, 10, 1, 39, 96);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (94, 9, 1, 40, 97);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (95, 9, 1, 40, 98);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (96, 13, 1, 41, 101);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (97, 10, 1, 42, 102);

--
-- Data for table public.pieza (OID = 82288) (LIMIT 0,9)
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

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (7, 'Pieza 1010', 2, 10, NULL, 10.000, 50.000, 50.000, NULL);

--
-- Data for table public.detallempasignada (OID = 82294) (LIMIT 0,28)
--
INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (11, 4, 2, 29);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (12, 1, 1, 29);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (15, 4, 2, 31);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (16, 1, 1, 31);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (17, 4, 1, 35);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (18, 5, 2, 35);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (21, 5, 3, 37);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (22, 1, 1, 37);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (23, 4, 1, 37);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (24, 5, 1, 36);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (25, 1, 3, 36);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (26, 4, 2, 36);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (27, 5, 1, 41);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (28, 1, 2, 41);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (29, 4, 1, 41);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (30, 5, 1, 42);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (31, 1, 1, 42);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (32, 5, 1, 43);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (33, 1, 1, 43);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (34, 4, 1, 44);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (35, 5, 2, 44);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (36, 1, 1, 51);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (40, 5, 1, 51);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (41, 4, 1, 52);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (42, 1, 1, 52);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (43, 5, 3, 52);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (44, 1, 1, 53);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (45, 5, 1, 53);

--
-- Data for table public.mpasignadaxpiezareal (OID = 82300) (LIMIT 0,44)
--
INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (46, 11, 32);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (47, 11, 33);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (48, 12, 34);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (52, 15, 38);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (53, 15, 39);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (54, 16, 40);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (55, 17, 41);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (56, 18, 42);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (57, 18, 43);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (58, 18, 44);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (61, 21, 47);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (62, 21, 48);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (63, 21, 49);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (64, 21, 50);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (65, 22, 51);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (66, 23, 52);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (67, 24, 53);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (68, 25, 54);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (69, 25, 55);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (70, 25, 56);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (71, 26, 57);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (72, 26, 58);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (73, 27, 59);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (74, 28, 60);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (75, 28, 61);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (76, 29, 62);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (77, 30, 63);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (78, 31, 64);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (79, 32, 65);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (80, 33, 66);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (81, 34, 68);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (82, 35, 69);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (83, 35, 70);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (84, 35, 71);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (85, 36, 72);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (89, 40, 76);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (90, 41, 77);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (91, 42, 78);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (92, 43, 79);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (93, 43, 80);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (94, 43, 81);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (95, 43, 82);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (96, 44, 83);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (97, 45, 84);

--
-- Data for table public.asistencia (OID = 82308) (LIMIT 0,1)
--
INSERT INTO asistencia (empleado, fechaingreso, horaingreso, horaegreso, observaciones)
VALUES (2, '2011-09-20', '00:00:00', NULL, NULL);

--
-- Data for table public.estadoreclamo (OID = 84888) (LIMIT 0,5)
--
INSERT INTO estadoreclamo (idestadoreclamo, nombre, descripcion)
VALUES (1, 'GENERADO', NULL);

INSERT INTO estadoreclamo (idestadoreclamo, nombre, descripcion)
VALUES (2, 'ENVIADO', NULL);

INSERT INTO estadoreclamo (idestadoreclamo, nombre, descripcion)
VALUES (3, 'FINALIZADO', NULL);

INSERT INTO estadoreclamo (idestadoreclamo, nombre, descripcion)
VALUES (4, 'NUNCA RESUELTO', NULL);

INSERT INTO estadoreclamo (idestadoreclamo, nombre, descripcion)
VALUES (5, 'SOLICITUD GENERADA', NULL);

--
-- Definition for index pedido_nropedido_key (OID = 82607) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_nropedido_key UNIQUE (nropedido);
--
-- Definition for index proveedorxmateriaprima_idx (OID = 82613) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_idx PRIMARY KEY (idmateriaprima, idproveedor);
--
-- Definition for index maquinaxejecucionetapaproduccion_idx (OID = 82615) : 
--
ALTER TABLE ONLY maquinaxejecucionetapaproduccion
    ADD CONSTRAINT maquinaxejecucionetapaproduccion_idx PRIMARY KEY (idejecucionetapaproduccion, idetapaproduccion, idmaquina);
--
-- Definition for index maquinaxprocesocalidad_idx (OID = 82617) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT maquinaxprocesocalidad_idx PRIMARY KEY (idprocesocalidad, idmaquina);
--
-- Definition for index piezaxetapadeproduccion_idx (OID = 82619) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT piezaxetapadeproduccion_idx PRIMARY KEY (idpieza, idetapaproduccion);
--
-- Definition for index empleadoxturno_idx (OID = 82621) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_idx PRIMARY KEY (idempleado, idturno);
--
-- Definition for index usuarioxrol_pkey (OID = 82623) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_pkey PRIMARY KEY (idrol, idusuario);
--
-- Definition for index rolxprivilegio_pkey (OID = 82625) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_pkey PRIMARY KEY (idrol, idprivilegio);
--
-- Definition for index prueba_pkey (OID = 82627) : 
--
ALTER TABLE ONLY prueba
    ADD CONSTRAINT prueba_pkey PRIMARY KEY (id);
--
-- Definition for index usuario_pkey (OID = 82629) : 
--
ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (idusuario);
--
-- Definition for index factura_fk2 (OID = 82631) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk2 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index comprobantepago_fk1 (OID = 82636) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk1 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index cliente_fk2 (OID = 82641) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk2 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index empleado_fk3 (OID = 82646) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk3 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index sesion_fk (OID = 82651) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT sesion_fk FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index usuarioxrol_fk1 (OID = 82656) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_fk1 FOREIGN KEY (idusuario) REFERENCES usuario(idusuario);
--
-- Definition for index tipomaterial_pkey (OID = 82661) : 
--
ALTER TABLE ONLY tipomaterial
    ADD CONSTRAINT tipomaterial_pkey PRIMARY KEY (idtipomaterial);
--
-- Definition for index accioncalidad_pkey (OID = 82663) : 
--
ALTER TABLE ONLY accioncalidad
    ADD CONSTRAINT accioncalidad_pkey PRIMARY KEY (idaccioncalidad);
--
-- Definition for index procesocalidad_fk (OID = 82665) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT procesocalidad_fk FOREIGN KEY (accioncalidad) REFERENCES accioncalidad(idaccioncalidad);
--
-- Definition for index barrio_pkey (OID = 82670) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT barrio_pkey PRIMARY KEY (idbarrio);
--
-- Definition for index domicilio_fk (OID = 82672) : 
--
ALTER TABLE ONLY domicilio
    ADD CONSTRAINT domicilio_fk FOREIGN KEY (barrio) REFERENCES barrio(idbarrio);
--
-- Definition for index cargo_pkey (OID = 82677) : 
--
ALTER TABLE ONLY cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (idcargo);
--
-- Definition for index empleado_fk4 (OID = 82679) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk4 FOREIGN KEY (cargo) REFERENCES cargo(idcargo);
--
-- Definition for index categoria_pkey (OID = 82684) : 
--
ALTER TABLE ONLY categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (idcategoria);
--
-- Definition for index empleado_fk2 (OID = 82686) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk2 FOREIGN KEY (categoria) REFERENCES categoria(idcategoria);
--
-- Definition for index cliente_pkey (OID = 82691) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (idcliente);
--
-- Definition for index pedido_fk4 (OID = 82693) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk4 FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index reclamocliente_fk1 (OID = 82698) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_fk1 FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index codigodebarra_pkey (OID = 82703) : 
--
ALTER TABLE ONLY codigodebarra
    ADD CONSTRAINT codigodebarra_pkey PRIMARY KEY (idcodigo);
--
-- Definition for index materiaprima_fk1 (OID = 82705) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk1 FOREIGN KEY (codbarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index productoreal_fk2 (OID = 82710) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk2 FOREIGN KEY (codigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index compra_pkey (OID = 82715) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_pkey PRIMARY KEY (idcompra);
--
-- Definition for index detallecompra_fk (OID = 82717) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk FOREIGN KEY (idcompra) REFERENCES compra(idcompra);
--
-- Definition for index reclamoproveedor_fk1 (OID = 82722) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_fk1 FOREIGN KEY (compra) REFERENCES compra(idcompra);
--
-- Definition for index comprobantepago_pkey (OID = 82727) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_pkey PRIMARY KEY (idcomprobantepago);
--
-- Definition for index condicioniva_pkey (OID = 82729) : 
--
ALTER TABLE ONLY condicioniva
    ADD CONSTRAINT condicioniva_pkey PRIMARY KEY (idcondicioniva);
--
-- Definition for index cliente_fk5 (OID = 82731) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk5 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index empresametalurgica_fk2 (OID = 82736) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk2 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index proveedormantenimientomaquina_fk2 (OID = 82741) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk2 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index proveedor_fk2 (OID = 82746) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk2 FOREIGN KEY (condicion) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index detallecompra_idx (OID = 82751) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_idx PRIMARY KEY (iddetalle, idcompra);
--
-- Definition for index detallereclamoproveedor_fk1 (OID = 82753) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_fk1 FOREIGN KEY (iddetallecompra, idcompra) REFERENCES detallecompra(iddetalle, idcompra);
--
-- Definition for index detallecompra_iddetalle_key (OID = 82758) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallefactura_idx (OID = 82769) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_idx PRIMARY KEY (iddetalle, idfactura);
--
-- Definition for index detallefactura_iddetalle_key (OID = 82771) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallemantenimientocorrectivo_pkey (OID = 82773) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_pkey PRIMARY KEY (idmantenimientocorrectivo, iddetalle);
--
-- Definition for index detallemantenimientocorrectivo_idmantenimientocorrectivo_key (OID = 82775) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_idmantenimientocorrectivo_key UNIQUE (idmantenimientocorrectivo);
--
-- Definition for index detallemantenimientopreventivo_pkey (OID = 82777) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_pkey PRIMARY KEY (idmantenimientopreventivo, iddetalle);
--
-- Definition for index detallemantenimientopreventivo_idmantenimientopreventivo_key (OID = 82779) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_idmantenimientopreventivo_key UNIQUE (idmantenimientopreventivo);
--
-- Definition for index detalleplanprocedimientos_idx (OID = 82785) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_idx PRIMARY KEY (iddetalle, idplanpprocedimientos);
--
-- Definition for index detalleplanprocedimientos_iddetalle_key (OID = 82787) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleplanprocesoscalidad_idx (OID = 82789) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_idx PRIMARY KEY (iddetalle, idplanprocesoscalidad);
--
-- Definition for index detalleplanprocesoscalidad_iddetalle_key (OID = 82791) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamocliente_idx (OID = 82797) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamocliente_iddetalle_key (OID = 82799) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamoempresametalurgica_idx (OID = 82801) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamoempresametalurgica_iddetalle_key (OID = 82803) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamoproveedor_idx (OID = 82805) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamoproveedor_iddetalle_key (OID = 82807) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleremito_idx (OID = 82809) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_idx PRIMARY KEY (iddetalle, idremito);
--
-- Definition for index detalleremito_iddetalle_key (OID = 82811) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallerequerimientosmateriaprima_idx (OID = 82813) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_idx PRIMARY KEY (iddetalle, idplanrequerimientosmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_iddetalle_key (OID = 82815) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index domicilio_pkey (OID = 82821) : 
--
ALTER TABLE ONLY domicilio
    ADD CONSTRAINT domicilio_pkey PRIMARY KEY (iddomicilio);
--
-- Definition for index cliente_fk4 (OID = 82823) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk4 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index empresametalurgica_fk1 (OID = 82828) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index responsable_fk (OID = 82833) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_fk FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index proveedormantenimientomaquina_fk1 (OID = 82838) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index empleado_fk (OID = 82843) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index proveedor_fk1 (OID = 82848) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index ejecucionetapaproduccion_nroejecucion_key1 (OID = 82853) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_nroejecucion_key1 UNIQUE (nroejecucion);
--
-- Definition for index empleado_pkey (OID = 82873) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (idempleado);
--
-- Definition for index mantenimientocorrectivo_fk (OID = 82875) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_fk FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index ejecucionetapaproduccion_fk1 (OID = 82880) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk1 FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index detallaplanificacionproduccion_fk3 (OID = 82885) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk3 FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index empleadoxturno_fk (OID = 82890) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_fk FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index empresametalurgica_pkey (OID = 82895) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_pkey PRIMARY KEY (idempresametalurgica);
--
-- Definition for index trabajotercerizado_fk1 (OID = 82897) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk1 FOREIGN KEY (empresa) REFERENCES empresametalurgica(idempresametalurgica);
--
-- Definition for index estadocliente_pkey (OID = 82902) : 
--
ALTER TABLE ONLY estadocliente
    ADD CONSTRAINT estadocliente_pkey PRIMARY KEY (idestado);
--
-- Definition for index cliente_fk1 (OID = 82904) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk1 FOREIGN KEY (estado) REFERENCES estadocliente(idestado);
--
-- Definition for index estadocompra_pkey (OID = 82909) : 
--
ALTER TABLE ONLY estadocompra
    ADD CONSTRAINT estadocompra_pkey PRIMARY KEY (idestado);
--
-- Definition for index compra_fk (OID = 82911) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_fk FOREIGN KEY (estado) REFERENCES estadocompra(idestado);
--
-- Definition for index estadodetallecompra_pkey (OID = 82916) : 
--
ALTER TABLE ONLY estadodetallecompra
    ADD CONSTRAINT estadodetallecompra_pkey PRIMARY KEY (idestado);
--
-- Definition for index detallecompra_fk2 (OID = 82918) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk2 FOREIGN KEY (estado) REFERENCES estadodetallecompra(idestado);
--
-- Definition for index estadodetalletrabajotercerizado_pkey (OID = 82923) : 
--
ALTER TABLE ONLY estadodetalletrabajotercerizado
    ADD CONSTRAINT estadodetalletrabajotercerizado_pkey PRIMARY KEY (idestado);
--
-- Definition for index detalletrabajotercerizado_fk3 (OID = 82925) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk3 FOREIGN KEY (estado) REFERENCES estadodetalletrabajotercerizado(idestado);
--
-- Definition for index estadoejecetapaprod_pkey (OID = 82930) : 
--
ALTER TABLE ONLY estadoejecetapaprod
    ADD CONSTRAINT estadoejecetapaprod_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionetapaproduccion_fk2 (OID = 82932) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk2 FOREIGN KEY (estado) REFERENCES estadoejecetapaprod(idestado);
--
-- Definition for index estadoejecplancalidad_pkey (OID = 82937) : 
--
ALTER TABLE ONLY estadoejecplancalidad
    ADD CONSTRAINT estadoejecplancalidad_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionplanificacioncalidad_fk1 (OID = 82939) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_fk1 FOREIGN KEY (estado) REFERENCES estadoejecplancalidad(idestado);
--
-- Definition for index estadoejecplanifpedido_pkey (OID = 82944) : 
--
ALTER TABLE ONLY estadoejecplanifpedido
    ADD CONSTRAINT estadoejecplanifpedido_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionplanificacionproduccion_fk1 (OID = 82946) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_fk1 FOREIGN KEY (estado) REFERENCES estadoejecplanifpedido(idestado);
--
-- Definition for index estadoejecucionprocesocalidad_pkey (OID = 82951) : 
--
ALTER TABLE ONLY estadoejecucionprocesocalidad
    ADD CONSTRAINT estadoejecucionprocesocalidad_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionprocesocalidad_fk1 (OID = 82953) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_fk1 FOREIGN KEY (estado) REFERENCES estadoejecucionprocesocalidad(idestado);
--
-- Definition for index estadofactura_pkey (OID = 82958) : 
--
ALTER TABLE ONLY estadofactura
    ADD CONSTRAINT estadofactura_pkey PRIMARY KEY (idestado);
--
-- Definition for index factura_fk3 (OID = 82960) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk3 FOREIGN KEY (estado) REFERENCES estadofactura(idestado);
--
-- Definition for index estadomaquina_pkey (OID = 82965) : 
--
ALTER TABLE ONLY estadomaquina
    ADD CONSTRAINT estadomaquina_pkey PRIMARY KEY (idestado);
--
-- Definition for index estadopedido_pkey (OID = 82967) : 
--
ALTER TABLE ONLY estadopedido
    ADD CONSTRAINT estadopedido_pkey PRIMARY KEY (idestado);
--
-- Definition for index pedido_fk (OID = 82969) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk FOREIGN KEY (estado) REFERENCES estadopedido(idestado);
--
-- Definition for index estadopiezareal_pkey (OID = 82974) : 
--
ALTER TABLE ONLY estadopiezareal
    ADD CONSTRAINT estadopiezareal_pkey PRIMARY KEY (idestado);
--
-- Definition for index estadoproductoreal_pkey (OID = 82976) : 
--
ALTER TABLE ONLY estadoproductoreal
    ADD CONSTRAINT estadoproductoreal_pkey PRIMARY KEY (idestado);
--
-- Definition for index productoreal_fk3 (OID = 82978) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk3 FOREIGN KEY (estado) REFERENCES estadoproductoreal(idestado);
--
-- Definition for index estadoremito_pkey (OID = 82983) : 
--
ALTER TABLE ONLY estadoremito
    ADD CONSTRAINT estadoremito_pkey PRIMARY KEY (idestado);
--
-- Definition for index remito_fk1 (OID = 82985) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_fk1 FOREIGN KEY (estado) REFERENCES estadoremito(idestado);
--
-- Definition for index estadotrabajotercerizado_pkey (OID = 82990) : 
--
ALTER TABLE ONLY estadotrabajotercerizado
    ADD CONSTRAINT estadotrabajotercerizado_pkey PRIMARY KEY (idestado);
--
-- Definition for index trabajotercerizado_fk2 (OID = 82992) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk2 FOREIGN KEY (estado) REFERENCES estadotrabajotercerizado(idestado);
--
-- Definition for index etapadeproduccion_pkey (OID = 82997) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_pkey PRIMARY KEY (idetapaproduccion);
--
-- Definition for index ejecucionetapaproduccion_fk (OID = 82999) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detalletrabajotercerizado_fk2 (OID = 83004) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk2 FOREIGN KEY (proceso) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index piezaxetapadeproduccion_fk1 (OID = 83009) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT piezaxetapadeproduccion_fk1 FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detalleplanprocedimientos_fk2 (OID = 83014) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_fk2 FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index factura_pkey (OID = 83019) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_pkey PRIMARY KEY (idfactura);
--
-- Definition for index pedido_fk1 (OID = 83021) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk1 FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index comprobantepago_fk2 (OID = 83026) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk2 FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index detallefactura_fk (OID = 83031) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_fk FOREIGN KEY (idfactura) REFERENCES factura(idfactura);
--
-- Definition for index formadepago_pkey (OID = 83036) : 
--
ALTER TABLE ONLY formadepago
    ADD CONSTRAINT formadepago_pkey PRIMARY KEY (idformapago);
--
-- Definition for index factura_fk1 (OID = 83038) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk1 FOREIGN KEY (formapago) REFERENCES formadepago(idformapago);
--
-- Definition for index comprobantepago_fk (OID = 83043) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk FOREIGN KEY (formadepago) REFERENCES formadepago(idformapago);
--
-- Definition for index localidad_pkey (OID = 83048) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT localidad_pkey PRIMARY KEY (idlocalidad);
--
-- Definition for index barrio_fk (OID = 83050) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT barrio_fk FOREIGN KEY (localidad) REFERENCES localidad(idlocalidad);
--
-- Definition for index mantenimientocorrectivo_pkey (OID = 83055) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_pkey PRIMARY KEY (idmantenimientocorrectivo);
--
-- Definition for index detallemantenimientocorrectivo_fk1 (OID = 83057) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_fk1 FOREIGN KEY (idmantenimientocorrectivo) REFERENCES mantenimientocorrectivo(idmantenimientocorrectivo);
--
-- Definition for index mantenimientopreventivo_pkey (OID = 83062) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_pkey PRIMARY KEY (idmantenimientopreventivo);
--
-- Definition for index detallemantenimientopreventivo_fk (OID = 83064) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_fk FOREIGN KEY (idmantenimientopreventivo) REFERENCES mantenimientopreventivo(idmantenimientopreventivo);
--
-- Definition for index marca_pkey (OID = 83069) : 
--
ALTER TABLE ONLY marca
    ADD CONSTRAINT marca_pkey PRIMARY KEY (idmarca);
--
-- Definition for index materiaprima_pkey (OID = 83071) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_pkey PRIMARY KEY (idmateriaprima);
--
-- Definition for index matriz_fk (OID = 83073) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT matriz_fk FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallecompra_fk1 (OID = 83078) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk1 FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index proveedorxmateriaprima_fk1 (OID = 83083) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_fk1 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_fk2 (OID = 83088) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_fk2 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index matriz_pkey (OID = 83093) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT matriz_pkey PRIMARY KEY (idmatriz);
--
-- Definition for index pedidomatriz_fk (OID = 83095) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT pedidomatriz_fk FOREIGN KEY (idmatriz) REFERENCES matriz(idmatriz);
--
-- Definition for index pedido_pkey (OID = 83100) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (idpedido);
--
-- Definition for index plano_fk (OID = 83102) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT plano_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index remito_fk (OID = 83107) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index trabajotercerizado_fk (OID = 83112) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index planificacionproduccion_fk (OID = 83117) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index planificacioncalidad_fk (OID = 83122) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT planificacioncalidad_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index detallepedido_fk (OID = 83127) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_fk FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index productoreal_fk1 (OID = 83132) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk1 FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index detallefactura_fk1 (OID = 83137) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_fk1 FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index pedidomatriz_idx (OID = 83142) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT pedidomatriz_idx PRIMARY KEY (idpedidomatriz);
--
-- Definition for index planificacioncalidad_pkey (OID = 83144) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT planificacioncalidad_pkey PRIMARY KEY (idplanificacion);
--
-- Definition for index ejecucionplanificacioncalidad_fk (OID = 83146) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_fk FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index detalleplanificacioncalidad_fk (OID = 83151) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index planificacionproduccion_pkey (OID = 83156) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_pkey PRIMARY KEY (idplanificacionproduccion);
--
-- Definition for index ejecucionplanificacionproduccion_fk (OID = 83158) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_fk FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index detallaplanificacionproduccion_fk (OID = 83163) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index plano_pkey (OID = 83168) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT plano_pkey PRIMARY KEY (idplano);
--
-- Definition for index planprocedimientos_pkey (OID = 83175) : 
--
ALTER TABLE ONLY planprocedimientos
    ADD CONSTRAINT planprocedimientos_pkey PRIMARY KEY (idplanprocedimientos);
--
-- Definition for index detalleplanprocedimientos_fk (OID = 83177) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_fk FOREIGN KEY (idplanpprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index pedido_fk5 (OID = 83182) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk5 FOREIGN KEY (planprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index planprocesoscalidad_pkey (OID = 83187) : 
--
ALTER TABLE ONLY planprocesoscalidad
    ADD CONSTRAINT planprocesoscalidad_pkey PRIMARY KEY (idplanprocesoscalidad);
--
-- Definition for index detalleplanprocesoscalidad_fk (OID = 83189) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_fk FOREIGN KEY (idplanprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index pedido_fk7 (OID = 83194) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk7 FOREIGN KEY (planprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index planrequerimientosmateriaprima_pkey (OID = 83199) : 
--
ALTER TABLE ONLY planrequerimientosmateriaprima
    ADD CONSTRAINT planrequerimientosmateriaprima_pkey PRIMARY KEY (idplanrequerimientosmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_fk (OID = 83201) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_fk FOREIGN KEY (idplanrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index pedido_fk6 (OID = 83206) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk6 FOREIGN KEY (planrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index presupuesto_pkey (OID = 83211) : 
--
ALTER TABLE ONLY presupuesto
    ADD CONSTRAINT presupuesto_pkey PRIMARY KEY (idpresupuesto);
--
-- Definition for index pedido_fk2 (OID = 83213) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk2 FOREIGN KEY (presupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index detallepresupuesto_fk (OID = 83218) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_fk FOREIGN KEY (idpresupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index prioridad_pkey (OID = 83223) : 
--
ALTER TABLE ONLY prioridad
    ADD CONSTRAINT prioridad_pkey PRIMARY KEY (idprioridad);
--
-- Definition for index cliente_fk (OID = 83225) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index privilegio_pkey (OID = 83230) : 
--
ALTER TABLE ONLY privilegio
    ADD CONSTRAINT privilegio_pkey PRIMARY KEY (idprivilegio);
--
-- Definition for index rolxprivilegio_fk (OID = 83232) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_fk FOREIGN KEY (idprivilegio) REFERENCES privilegio(idprivilegio);
--
-- Definition for index procesocalidad_pkey (OID = 83237) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT procesocalidad_pkey PRIMARY KEY (idprocesocalidad);
--
-- Definition for index detalleplanificacioncalidad_fk2 (OID = 83244) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk2 FOREIGN KEY (procesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index maquinaxprocesocalidad_fk (OID = 83249) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT maquinaxprocesocalidad_fk FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detalleplanprocesoscalidad_fk2 (OID = 83254) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_fk2 FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index producto_pkey (OID = 83259) : 
--
ALTER TABLE ONLY producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (idproducto);
--
-- Definition for index detalleproducto_fk (OID = 83261) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_fk FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index detallepedido_fk1 (OID = 83266) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detalleremito_fk1 (OID = 83271) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detallereclamocliente_fk1 (OID = 83276) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detallepresupuesto_fk2 (OID = 83281) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_fk2 FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index productoreal_idx (OID = 83286) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_idx PRIMARY KEY (idproductoreal);
--
-- Definition for index detalleproductoreal_fk (OID = 83288) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_fk FOREIGN KEY (idproductoreal) REFERENCES productoreal(idproductoreal);
--
-- Definition for index proveedor_pkey (OID = 83293) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_pkey PRIMARY KEY (idproveedor);
--
-- Definition for index compra_fk1 (OID = 83295) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_fk1 FOREIGN KEY (proveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index proveedorxmateriaprima_fk (OID = 83300) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_fk FOREIGN KEY (idproveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index proveedormantenimientomaquina_pkey (OID = 83305) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_pkey PRIMARY KEY (idproveedormantenimiento);
--
-- Definition for index mantenimientopreventivo_fk (OID = 83307) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_fk FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index mantenimientocorrectivo_fk1 (OID = 83312) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_fk1 FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index provincia_pkey (OID = 83317) : 
--
ALTER TABLE ONLY provincia
    ADD CONSTRAINT provincia_pkey PRIMARY KEY (idprovincia);
--
-- Definition for index localidad_fk (OID = 83319) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT localidad_fk FOREIGN KEY (provincia) REFERENCES provincia(idprovincia);
--
-- Definition for index reclamocliente_idx (OID = 83324) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_idx PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamocliente_fk (OID = 83326) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_fk FOREIGN KEY (idreclamo) REFERENCES reclamocliente(idreclamo);
--
-- Definition for index reclamoempresametalurgica_pkey (OID = 83331) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_pkey PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamoempresametalurgica_fk (OID = 83333) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_fk FOREIGN KEY (idreclamo) REFERENCES reclamoempresametalurgica(idreclamo);
--
-- Definition for index reclamoproveedor_idx (OID = 83338) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_idx PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamoproveedor_fk (OID = 83340) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_fk FOREIGN KEY (idreclamo) REFERENCES reclamoproveedor(idreclamo);
--
-- Definition for index remito_pkey (OID = 83345) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_pkey PRIMARY KEY (idremito);
--
-- Definition for index detalleremito_fk (OID = 83347) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_fk FOREIGN KEY (idremito) REFERENCES remito(idremito);
--
-- Definition for index responsable_pkey (OID = 83352) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_pkey PRIMARY KEY (idresponsable);
--
-- Definition for index cliente_fk3 (OID = 83354) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk3 FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index empresametalurgica_fk (OID = 83359) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index proveedormantenimientomaquina_fk (OID = 83364) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index proveedor_fk (OID = 83369) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index rol_pkey (OID = 83374) : 
--
ALTER TABLE ONLY rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (idrol);
--
-- Definition for index rolxprivilegio_fk1 (OID = 83376) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_fk1 FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index usuarioxrol_fk (OID = 83381) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_fk FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index rotura_pkey (OID = 83386) : 
--
ALTER TABLE ONLY rotura
    ADD CONSTRAINT rotura_pkey PRIMARY KEY (idrotura);
--
-- Definition for index detallemantenimientocorrectivo_fk (OID = 83388) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_fk FOREIGN KEY (rotura) REFERENCES rotura(idrotura);
--
-- Definition for index servicio_pkey (OID = 83393) : 
--
ALTER TABLE ONLY servicio
    ADD CONSTRAINT servicio_pkey PRIMARY KEY (idservicio);
--
-- Definition for index detallemantenimientopreventivo_fk1 (OID = 83395) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_fk1 FOREIGN KEY (servicio) REFERENCES servicio(idservicio);
--
-- Definition for index sesion_pkey (OID = 83400) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT sesion_pkey PRIMARY KEY (idsesion);
--
-- Definition for index tipodocumento_pkey (OID = 83402) : 
--
ALTER TABLE ONLY tipodocumento
    ADD CONSTRAINT tipodocumento_pkey PRIMARY KEY (idtipodocumento);
--
-- Definition for index responsable_fk1 (OID = 83404) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_fk1 FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index empleado_fk1 (OID = 83409) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk1 FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index tipoiva_pkey (OID = 83414) : 
--
ALTER TABLE ONLY tipoiva
    ADD CONSTRAINT tipoiva_pkey PRIMARY KEY (idtipoiva);
--
-- Definition for index factura_fk (OID = 83416) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk FOREIGN KEY (tipoiva) REFERENCES tipoiva(idtipoiva);
--
-- Definition for index tipomaquina_pkey (OID = 83421) : 
--
ALTER TABLE ONLY tipomaquina
    ADD CONSTRAINT tipomaquina_pkey PRIMARY KEY (idtipomaquina);
--
-- Definition for index tiporeclamo_pkey (OID = 83423) : 
--
ALTER TABLE ONLY tiporeclamo
    ADD CONSTRAINT tiporeclamo_pkey PRIMARY KEY (idtiporeclamo);
--
-- Definition for index reclamoempresametalurgica_fk (OID = 83425) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index reclamoproveedor_fk (OID = 83430) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index reclamocliente_fk (OID = 83435) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index trabajotercerizado_pkey (OID = 83440) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_pkey PRIMARY KEY (idtrabajo);
--
-- Definition for index detalletrabajotercerizado_fk (OID = 83442) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk FOREIGN KEY (idtrabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index reclamoempresametalurgica_fk1 (OID = 83447) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_fk1 FOREIGN KEY (trabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index turno_pkey (OID = 83452) : 
--
ALTER TABLE ONLY turno
    ADD CONSTRAINT turno_pkey PRIMARY KEY (idturno);
--
-- Definition for index empleadoxturno_fk1 (OID = 83454) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_fk1 FOREIGN KEY (idturno) REFERENCES turno(idturno);
--
-- Definition for index materiaprima_fk (OID = 83459) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk FOREIGN KEY (tipomaterial) REFERENCES tipomaterial(idtipomaterial);
--
-- Definition for index detalleproducto_iddetalle_key (OID = 83464) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_iddetalle_key PRIMARY KEY (iddetalle);
ALTER TABLE detalleproducto CLUSTER ON detalleproducto_iddetalle_key;
--
-- Definition for index pedido_fk8 (OID = 83466) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk8 FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index unidadmedida_pkey (OID = 83471) : 
--
ALTER TABLE ONLY unidadmedida
    ADD CONSTRAINT unidadmedida_pkey PRIMARY KEY (idunidadmedida);
--
-- Definition for index etapadeproduccion_fk1 (OID = 83473) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_fk1 FOREIGN KEY (unidaddemedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index materiaprima_fk2 (OID = 83478) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk2 FOREIGN KEY (unidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index detalleproductopresupuesto_pkey (OID = 83483) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT detalleproductopresupuesto_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detallepiezapresupuesto_pkey (OID = 83485) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT detallepiezapresupuesto_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detallepiezapresupuesto_fk (OID = 83487) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT detallepiezapresupuesto_fk FOREIGN KEY (iddetalleproductopresupuesto) REFERENCES detalleproductopresupuesto(iddetalle);
--
-- Definition for index detallepiezapresupuesto_fk1 (OID = 83492) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT detallepiezapresupuesto_fk1 FOREIGN KEY (idetapa) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detallepresupuesto_pkey (OID = 83497) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detallepedido_pkey (OID = 83499) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detalleproductopresupuesto_fk1 (OID = 83501) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT detalleproductopresupuesto_fk1 FOREIGN KEY (iddetallepresupuesto) REFERENCES detallepresupuesto(iddetalle);
--
-- Definition for index detalleproductopresupuesto_fk2 (OID = 83506) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT detalleproductopresupuesto_fk2 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallepiezacalidadpresupuesto_pkey (OID = 83511) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT detallepiezacalidadpresupuesto_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detallepiezacalidadpresupuesto_fk (OID = 83513) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT detallepiezacalidadpresupuesto_fk FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detallepiezacalidadpresupuesto_fk1 (OID = 83518) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT detallepiezacalidadpresupuesto_fk1 FOREIGN KEY (iddetalleproductopresupuesto) REFERENCES detalleproductopresupuesto(iddetalle);
--
-- Definition for index calendario_pkey (OID = 83523) : 
--
ALTER TABLE ONLY calendario
    ADD CONSTRAINT calendario_pkey PRIMARY KEY (id);
--
-- Definition for index disponibilidadhoraria_pkey (OID = 83525) : 
--
ALTER TABLE ONLY disponibilidadhoraria
    ADD CONSTRAINT disponibilidadhoraria_pkey PRIMARY KEY (id);
--
-- Definition for index estadoplanificacionproduccion_pkey (OID = 83527) : 
--
ALTER TABLE ONLY estadoplanificacionproduccion
    ADD CONSTRAINT estadoplanificacionproduccion_pkey PRIMARY KEY (id);
--
-- Definition for index planificacionproduccion_fk1 (OID = 83529) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_fk1 FOREIGN KEY (idestado) REFERENCES estadoplanificacionproduccion(id);
--
-- Definition for index disponibilidadhoraria_fk (OID = 83534) : 
--
ALTER TABLE ONLY disponibilidadhoraria
    ADD CONSTRAINT disponibilidadhoraria_fk FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index detalleplanificacionproduccion_pkey (OID = 83539) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_pkey PRIMARY KEY (id);
--
-- Definition for index detalleplanificacionproduccion_fk (OID = 83541) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index maquina_pkey (OID = 83546) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_pkey PRIMARY KEY (idmaquina);
--
-- Definition for index maquina_fk (OID = 83548) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk FOREIGN KEY (marca) REFERENCES marca(idmarca);
--
-- Definition for index maquina_fk1 (OID = 83553) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk1 FOREIGN KEY (estado) REFERENCES estadomaquina(idestado);
--
-- Definition for index maquina_fk2 (OID = 83558) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk2 FOREIGN KEY (tipomaquina) REFERENCES tipomaquina(idtipomaquina);
--
-- Definition for index maquina_fk3 (OID = 83563) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk3 FOREIGN KEY (idunidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index piezareal_fk1 (OID = 83568) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_fk1 FOREIGN KEY (estado) REFERENCES estadopiezareal(idestado);
--
-- Definition for index piezareal_fk2 (OID = 83573) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_fk2 FOREIGN KEY (idcodigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index pieza_pkey (OID = 83578) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_pkey PRIMARY KEY (idpieza);
--
-- Definition for index pieza_fk (OID = 83580) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk FOREIGN KEY (unidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index pieza_fk1 (OID = 83585) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk1 FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index pieza_fk2 (OID = 83590) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk2 FOREIGN KEY (matriz) REFERENCES matriz(idmatriz);
--
-- Definition for index detallempasignada_pkey (OID = 83595) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT detallempasignada_pkey PRIMARY KEY (id);
--
-- Definition for index detallempasignada_fk (OID = 83597) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT detallempasignada_fk FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallempasignada_fk1 (OID = 83602) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT detallempasignada_fk1 FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index mpasignadaxpiezareal_pkey (OID = 83607) : 
--
ALTER TABLE ONLY mpasignadaxpiezareal
    ADD CONSTRAINT mpasignadaxpiezareal_pkey PRIMARY KEY (id);
--
-- Definition for index mpasignadaxpiezareal_fk1 (OID = 83609) : 
--
ALTER TABLE ONLY mpasignadaxpiezareal
    ADD CONSTRAINT mpasignadaxpiezareal_fk1 FOREIGN KEY (iddetallempasignada) REFERENCES detallempasignada(id);
--
-- Definition for index asistencia_idx (OID = 83614) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_idx PRIMARY KEY (empleado, horaingreso, fechaingreso);
--
-- Definition for index asistencia_fk (OID = 83616) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_fk FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index piezareal_idpiezareal_key (OID = 83621) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_idpiezareal_key PRIMARY KEY (idpiezareal);
--
-- Definition for index detalleplanificacionproduccion_fk1 (OID = 83623) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk1 FOREIGN KEY (idmaquina) REFERENCES maquina(idmaquina);
--
-- Definition for index detalleplanificacionproduccion_fk2 (OID = 83628) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk2 FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleproductopresupuesto_fk (OID = 83633) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT detalleproductopresupuesto_fk FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index ejecucionplanificacionproduccion_idejecucion_key (OID = 83638) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_idejecucion_key PRIMARY KEY (idejecucion);
--
-- Definition for index detalleejecucionplanificacion_iddetalle_key (OID = 83640) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_iddetalle_key PRIMARY KEY (id);
--
-- Definition for index detalleplanificacionproduccion_fk3 (OID = 83642) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk3 FOREIGN KEY (iddetalleejecucionplanificacion) REFERENCES detalleejecucionplanificacion(id);
--
-- Definition for index fk_responsable_domicilio (OID = 83647) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT fk_responsable_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_responsable_tipodocumento (OID = 83652) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT fk_responsable_tipodocumento FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index fk_procesocalidad_accioncalidad (OID = 83657) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT fk_procesocalidad_accioncalidad FOREIGN KEY (accioncalidad) REFERENCES accioncalidad(idaccioncalidad);
--
-- Definition for index fk_detallecompra_estado (OID = 83662) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT fk_detallecompra_estado FOREIGN KEY (estado) REFERENCES estadodetallecompra(idestado);
--
-- Definition for index fk_detallecompra_materiaprima (OID = 83667) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT fk_detallecompra_materiaprima FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detallecompra_idcompra (OID = 83672) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT fk_detallecompra_idcompra FOREIGN KEY (idcompra) REFERENCES compra(idcompra);
--
-- Definition for index fk_reclamoproveedor_tiporeclamo (OID = 83677) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT fk_reclamoproveedor_tiporeclamo FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index fk_reclamoproveedor_compra (OID = 83682) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT fk_reclamoproveedor_compra FOREIGN KEY (compra) REFERENCES compra(idcompra);
--
-- Definition for index fk_empresametalurgica_condicioniva (OID = 83687) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT fk_empresametalurgica_condicioniva FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index fk_empresametalurgica_domicilio (OID = 83692) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT fk_empresametalurgica_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_empresametalurgica_responsable (OID = 83697) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT fk_empresametalurgica_responsable FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index fk_detalleplanprocesoscalidad_idprocesocalidad (OID = 83702) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT fk_detalleplanprocesoscalidad_idprocesocalidad FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index fk_detalleplanprocesoscalidad_idplanprocesoscalidad (OID = 83707) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT fk_detalleplanprocesoscalidad_idplanprocesoscalidad FOREIGN KEY (idplanprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index fk_detalleremito_idremito (OID = 83712) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT fk_detalleremito_idremito FOREIGN KEY (idremito) REFERENCES remito(idremito);
--
-- Definition for index fk_detalleremito_producto (OID = 83717) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT fk_detalleremito_producto FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index fk_detalleplanificacionproduccion_idpieza (OID = 83722) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idpieza FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index fk_detalleplanificacionproduccion_idetapaproduccion (OID = 83727) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idetapaproduccion FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_detalleplanificacionproduccion_idempleado (OID = 83732) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idempleado FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index detalleplanificacionproduccion_iddetalleejecucionplanificacion (OID = 83737) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_iddetalleejecucionplanificacion FOREIGN KEY (iddetalleejecucionplanificacion) REFERENCES detalleejecucionplanificacion(id);
--
-- Definition for index fk_detalleplanificacionproduccion_idmaquina (OID = 83742) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idmaquina FOREIGN KEY (idmaquina) REFERENCES maquina(idmaquina);
--
-- Definition for index fk_detalleplanificacionproduccion_idplanificacionproduccion (OID = 83747) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idplanificacionproduccion FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index fk_ejecucionplanificacioncalidad_estado (OID = 83752) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT fk_ejecucionplanificacioncalidad_estado FOREIGN KEY (estado) REFERENCES estadoejecplancalidad(idestado);
--
-- Definition for index fk_ejecucionplanificacioncalidad_idplanificacioncalidad (OID = 83757) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT fk_ejecucionplanificacioncalidad_idplanificacioncalidad FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index fk_planificacionproduccion_pedido (OID = 83762) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT fk_planificacionproduccion_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_planificacionproduccion_idestado (OID = 83767) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT fk_planificacionproduccion_idestado FOREIGN KEY (idestado) REFERENCES estadoplanificacionproduccion(id);
--
-- Definition for index fk_pedido_estado (OID = 83772) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_estado FOREIGN KEY (estado) REFERENCES estadopedido(idestado);
--
-- Definition for index fk_pedido_planprocesoscalidad (OID = 83777) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_planprocesoscalidad FOREIGN KEY (planprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index fk_pedido_presupuesto (OID = 83782) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_presupuesto FOREIGN KEY (presupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index fk_pedido_planrequerimientosmateriaprima (OID = 83787) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_planrequerimientosmateriaprima FOREIGN KEY (planrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index fk_pedido_planprocedimientos (OID = 83792) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_planprocedimientos FOREIGN KEY (planprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index fk_pedido_prioridad (OID = 83802) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_prioridad FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index fk_pedido_cliente (OID = 83807) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index fk_pedido_factura (OID = 83812) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_factura FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index fk_empleadoxturno_idempleado (OID = 83817) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT fk_empleadoxturno_idempleado FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_empleadoxturno_idturno (OID = 83822) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT fk_empleadoxturno_idturno FOREIGN KEY (idturno) REFERENCES turno(idturno);
--
-- Definition for index fk_ejecucionetapaproduccion_empleado (OID = 83832) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT fk_ejecucionetapaproduccion_empleado FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_ejecucionetapaproduccion_estado (OID = 83837) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT fk_ejecucionetapaproduccion_estado FOREIGN KEY (estado) REFERENCES estadoejecetapaprod(idestado);
--
-- Definition for index fk_ejecucionetapaproduccion_idetapaproduccion (OID = 83842) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT fk_ejecucionetapaproduccion_idetapaproduccion FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_compra_proveedor (OID = 83847) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT fk_compra_proveedor FOREIGN KEY (proveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index fk_compra_estado (OID = 83852) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT fk_compra_estado FOREIGN KEY (estado) REFERENCES estadocompra(idestado);
--
-- Definition for index fk_maquinaxprocesocalidad_idprocesocalidad (OID = 83857) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT fk_maquinaxprocesocalidad_idprocesocalidad FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index fk_productoreal_codigobarra (OID = 83862) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT fk_productoreal_codigobarra FOREIGN KEY (codigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index fk_productoreal_idpedido (OID = 83867) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT fk_productoreal_idpedido FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_productoreal_estado (OID = 83872) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT fk_productoreal_estado FOREIGN KEY (estado) REFERENCES estadoproductoreal(idestado);
--
-- Definition for index fk_materiaprima_unidadmedida (OID = 83877) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT fk_materiaprima_unidadmedida FOREIGN KEY (unidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index fk_materiaprima_tipomaterial (OID = 83882) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT fk_materiaprima_tipomaterial FOREIGN KEY (tipomaterial) REFERENCES tipomaterial(idtipomaterial);
--
-- Definition for index fk_materiaprima_codbarra (OID = 83887) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT fk_materiaprima_codbarra FOREIGN KEY (codbarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index fk_cliente_estado (OID = 83892) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_estado FOREIGN KEY (estado) REFERENCES estadocliente(idestado);
--
-- Definition for index fk_cliente_domicilio (OID = 83897) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_cliente_usuario (OID = 83902) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_cliente_condicioniva (OID = 83907) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_condicioniva FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index fk_cliente_responsable (OID = 83912) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_responsable FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index fk_cliente_prioridad (OID = 83917) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_prioridad FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index fk_pieza_matriz (OID = 83922) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT fk_pieza_matriz FOREIGN KEY (matriz) REFERENCES matriz(idmatriz);
--
-- Definition for index fk_pieza_unidadmedida (OID = 83927) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT fk_pieza_unidadmedida FOREIGN KEY (unidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index fk_pieza_materiaprima (OID = 83932) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT fk_pieza_materiaprima FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detallepiezacalidadpresupuesto_idprocesocalidad (OID = 83937) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT fk_detallepiezacalidadpresupuesto_idprocesocalidad FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index fk_detallepiezacalidadpresupuesto_iddetalleproductopresupuesto (OID = 83942) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT fk_detallepiezacalidadpresupuesto_iddetalleproductopresupuesto FOREIGN KEY (iddetalleproductopresupuesto) REFERENCES detalleproductopresupuesto(iddetalle);
--
-- Definition for index fk_detalleproductoreal_idproductoreal (OID = 83947) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT fk_detalleproductoreal_idproductoreal FOREIGN KEY (idproductoreal) REFERENCES productoreal(idproductoreal);
--
-- Definition for index fk_factura_tipoiva (OID = 83952) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_factura_tipoiva FOREIGN KEY (tipoiva) REFERENCES tipoiva(idtipoiva);
--
-- Definition for index fk_factura_formapago (OID = 83957) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_factura_formapago FOREIGN KEY (formapago) REFERENCES formadepago(idformapago);
--
-- Definition for index fk_factura_usuario (OID = 83962) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_factura_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_factura_estado (OID = 83967) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_factura_estado FOREIGN KEY (estado) REFERENCES estadofactura(idestado);
--
-- Definition for index fk_detallerequerimientosmateriaprima_idmateriaprima (OID = 83972) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT fk_detallerequerimientosmateriaprima_idmateriaprima FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index dtallerequerimientosmateriaprimadplanrequerimientosmateriaprima (OID = 83977) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT dtallerequerimientosmateriaprimadplanrequerimientosmateriaprima FOREIGN KEY (idplanrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index fk_plano_pedido (OID = 83982) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT fk_plano_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_usuarioxrol_idrol (OID = 83987) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT fk_usuarioxrol_idrol FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index fk_usuarioxrol_idusuario (OID = 83992) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT fk_usuarioxrol_idusuario FOREIGN KEY (idusuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_trabajotercerizado_empresa (OID = 83997) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT fk_trabajotercerizado_empresa FOREIGN KEY (empresa) REFERENCES empresametalurgica(idempresametalurgica);
--
-- Definition for index fk_trabajotercerizado_estado (OID = 84002) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT fk_trabajotercerizado_estado FOREIGN KEY (estado) REFERENCES estadotrabajotercerizado(idestado);
--
-- Definition for index fk_trabajotercerizado_pedido (OID = 84007) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT fk_trabajotercerizado_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_piezaxetapadeproduccion_idetapaproduccion (OID = 84012) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT fk_piezaxetapadeproduccion_idetapaproduccion FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_proveedor_responsable (OID = 84017) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT fk_proveedor_responsable FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index fk_proveedor_domicilio (OID = 84022) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT fk_proveedor_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_proveedor_condicion (OID = 84027) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT fk_proveedor_condicion FOREIGN KEY (condicion) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index fk_reclamoempresametalurgica_trabajotercerizado (OID = 84032) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT fk_reclamoempresametalurgica_trabajotercerizado FOREIGN KEY (trabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index fk_reclamoempresametalurgica_tiporeclamo (OID = 84037) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT fk_reclamoempresametalurgica_tiporeclamo FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index fk_matriz_materiaprima (OID = 84042) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT fk_matriz_materiaprima FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detalleproducto_idproducto (OID = 84047) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT fk_detalleproducto_idproducto FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index fk_mantenimientocorrectivo_empleado (OID = 84052) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT fk_mantenimientocorrectivo_empleado FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_mantenimientocorrectivo_proveedormantenimiento (OID = 84057) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT fk_mantenimientocorrectivo_proveedormantenimiento FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index fk_piezareal_estado (OID = 84062) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT fk_piezareal_estado FOREIGN KEY (estado) REFERENCES estadopiezareal(idestado);
--
-- Definition for index fk_piezareal_idcodigobarra (OID = 84067) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT fk_piezareal_idcodigobarra FOREIGN KEY (idcodigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index fk_detallempasignada_idmateriaprima (OID = 84072) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT fk_detallempasignada_idmateriaprima FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detallempasignada_idplanificacionproduccion (OID = 84077) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT fk_detallempasignada_idplanificacionproduccion FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index fk_ejecucionprocesocalidad_estado (OID = 84087) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT fk_ejecucionprocesocalidad_estado FOREIGN KEY (estado) REFERENCES estadoejecucionprocesocalidad(idestado);
--
-- Definition for index fk_pedidomatriz_idmatriz (OID = 84092) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT fk_pedidomatriz_idmatriz FOREIGN KEY (idmatriz) REFERENCES matriz(idmatriz);
--
-- Definition for index fk_maquina_idunidadmedida (OID = 84097) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT fk_maquina_idunidadmedida FOREIGN KEY (idunidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index fk_maquina_estado (OID = 84102) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT fk_maquina_estado FOREIGN KEY (estado) REFERENCES estadomaquina(idestado);
--
-- Definition for index fk_maquina_tipomaquina (OID = 84107) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT fk_maquina_tipomaquina FOREIGN KEY (tipomaquina) REFERENCES tipomaquina(idtipomaquina);
--
-- Definition for index fk_maquina_marca (OID = 84112) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT fk_maquina_marca FOREIGN KEY (marca) REFERENCES marca(idmarca);
--
-- Definition for index fk_planificacioncalidad_pedido (OID = 84117) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT fk_planificacioncalidad_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_detalleplanificacioncalidad_idplanificacioncalidad (OID = 84122) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT fk_detalleplanificacioncalidad_idplanificacioncalidad FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index fk_detalleplanificacioncalidad_procesocalidad (OID = 84132) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT fk_detalleplanificacioncalidad_procesocalidad FOREIGN KEY (procesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index fk_detallemantenimientocorrectivo_rotura (OID = 84137) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT fk_detallemantenimientocorrectivo_rotura FOREIGN KEY (rotura) REFERENCES rotura(idrotura);
--
-- Definition for index fk_detallemantenimientocorrectivo_idmantenimientocorrectivo (OID = 84142) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT fk_detallemantenimientocorrectivo_idmantenimientocorrectivo FOREIGN KEY (idmantenimientocorrectivo) REFERENCES mantenimientocorrectivo(idmantenimientocorrectivo);
--
-- Definition for index fk_localidad_provincia (OID = 84147) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT fk_localidad_provincia FOREIGN KEY (provincia) REFERENCES provincia(idprovincia);
--
-- Definition for index fk_proveedorxmateriaprima_idmateriaprima (OID = 84152) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT fk_proveedorxmateriaprima_idmateriaprima FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_proveedorxmateriaprima_idproveedor (OID = 84157) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT fk_proveedorxmateriaprima_idproveedor FOREIGN KEY (idproveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index fk_remito_pedido (OID = 84162) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT fk_remito_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_remito_estado (OID = 84167) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT fk_remito_estado FOREIGN KEY (estado) REFERENCES estadoremito(idestado);
--
-- Definition for index fk_disponibilidadhoraria_idempleado (OID = 84182) : 
--
ALTER TABLE ONLY disponibilidadhoraria
    ADD CONSTRAINT fk_disponibilidadhoraria_idempleado FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_sesion_usuario (OID = 84187) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT fk_sesion_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_detallereclamoproveedor_idreclamo (OID = 84192) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT fk_detallereclamoproveedor_idreclamo FOREIGN KEY (idreclamo) REFERENCES reclamoproveedor(idreclamo);
--
-- Definition for index fk_detallereclamoproveedor_idcompra (OID = 84197) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT fk_detallereclamoproveedor_idcompra FOREIGN KEY (idcompra, iddetallecompra) REFERENCES detallecompra(idcompra, iddetalle);
--
-- Definition for index fk_barrio_localidad (OID = 84202) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT fk_barrio_localidad FOREIGN KEY (localidad) REFERENCES localidad(idlocalidad);
--
-- Definition for index fk_asistencia_empleado (OID = 84207) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT fk_asistencia_empleado FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_detallemantenimientopreventivo_servicio (OID = 84212) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT fk_detallemantenimientopreventivo_servicio FOREIGN KEY (servicio) REFERENCES servicio(idservicio);
--
-- Definition for index fk_detallemantenimientopreventivo_idmantenimientopreventivo (OID = 84217) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT fk_detallemantenimientopreventivo_idmantenimientopreventivo FOREIGN KEY (idmantenimientopreventivo) REFERENCES mantenimientopreventivo(idmantenimientopreventivo);
--
-- Definition for index fk_etapadeproduccion_unidaddemedida (OID = 84222) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT fk_etapadeproduccion_unidaddemedida FOREIGN KEY (unidaddemedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index fk_detallefactura_idpedido (OID = 84227) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT fk_detallefactura_idpedido FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_detallefactura_idfactura (OID = 84232) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT fk_detallefactura_idfactura FOREIGN KEY (idfactura) REFERENCES factura(idfactura);
--
-- Definition for index fk_detallepedido_producto (OID = 84237) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT fk_detallepedido_producto FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index fk_detallepedido_idpedido (OID = 84242) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT fk_detallepedido_idpedido FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_mantenimientopreventivo_proveedormantenimiento (OID = 84247) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT fk_mantenimientopreventivo_proveedormantenimiento FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index fk_rolxprivilegio_idprivilegio (OID = 84252) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT fk_rolxprivilegio_idprivilegio FOREIGN KEY (idprivilegio) REFERENCES privilegio(idprivilegio);
--
-- Definition for index fk_rolxprivilegio_idrol (OID = 84257) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT fk_rolxprivilegio_idrol FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index fk_detallereclamocliente_producto (OID = 84262) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT fk_detallereclamocliente_producto FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index fk_detallereclamocliente_idreclamo (OID = 84267) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT fk_detallereclamocliente_idreclamo FOREIGN KEY (idreclamo) REFERENCES reclamocliente(idreclamo);
--
-- Definition for index fk_proveedormantenimientomaquina_condicioniva (OID = 84272) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT fk_proveedormantenimientomaquina_condicioniva FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index fk_proveedormantenimientomaquina_domicilio (OID = 84277) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT fk_proveedormantenimientomaquina_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_proveedormantenimientomaquina_responsable (OID = 84282) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT fk_proveedormantenimientomaquina_responsable FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index fk_detallepiezapresupuesto_iddetalleproductopresupuesto (OID = 84287) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT fk_detallepiezapresupuesto_iddetalleproductopresupuesto FOREIGN KEY (iddetalleproductopresupuesto) REFERENCES detalleproductopresupuesto(iddetalle);
--
-- Definition for index fk_detallepiezapresupuesto_idetapa (OID = 84292) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT fk_detallepiezapresupuesto_idetapa FOREIGN KEY (idetapa) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_ejecucionplanificacionproduccion_estado (OID = 84297) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT fk_ejecucionplanificacionproduccion_estado FOREIGN KEY (estado) REFERENCES estadoejecplanifpedido(idestado);
--
-- Definition for index fk_ejecucionplanificacionproduccion_idplanificacionproduccion (OID = 84302) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT fk_ejecucionplanificacionproduccion_idplanificacionproduccion FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index fk_detallepresupuesto_idpresupuesto (OID = 84307) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT fk_detallepresupuesto_idpresupuesto FOREIGN KEY (idpresupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index fk_detallepresupuesto_idproducto (OID = 84312) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT fk_detallepresupuesto_idproducto FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index fk_domicilio_barrio (OID = 84317) : 
--
ALTER TABLE ONLY domicilio
    ADD CONSTRAINT fk_domicilio_barrio FOREIGN KEY (barrio) REFERENCES barrio(idbarrio);
--
-- Definition for index fk_empleado_cargo (OID = 84322) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_cargo FOREIGN KEY (cargo) REFERENCES cargo(idcargo);
--
-- Definition for index fk_empleado_categoria (OID = 84327) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_categoria FOREIGN KEY (categoria) REFERENCES categoria(idcategoria);
--
-- Definition for index fk_empleado_domicilio (OID = 84332) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_empleado_tipodocumento (OID = 84337) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_tipodocumento FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index fk_empleado_usuario (OID = 84342) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_detalleplanprocedimientos_idetapaproduccion (OID = 84347) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT fk_detalleplanprocedimientos_idetapaproduccion FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_detalleplanprocedimientos_idplanpprocedimientos (OID = 84352) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT fk_detalleplanprocedimientos_idplanpprocedimientos FOREIGN KEY (idplanpprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index fk_reclamocliente_cliente (OID = 84357) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT fk_reclamocliente_cliente FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index fk_reclamocliente_tiporeclamo (OID = 84362) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT fk_reclamocliente_tiporeclamo FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index fk_comprobantepago_usuario (OID = 84367) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT fk_comprobantepago_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_comprobantepago_factura (OID = 84372) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT fk_comprobantepago_factura FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index fk_comprobantepago_formadepago (OID = 84377) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT fk_comprobantepago_formadepago FOREIGN KEY (formadepago) REFERENCES formadepago(idformapago);
--
-- Definition for index fk_mpasignadaxpiezareal_iddetallempasignada (OID = 84382) : 
--
ALTER TABLE ONLY mpasignadaxpiezareal
    ADD CONSTRAINT fk_mpasignadaxpiezareal_iddetallempasignada FOREIGN KEY (iddetallempasignada) REFERENCES detallempasignada(id);
--
-- Definition for index fk_detalleproductopresupuesto_idpieza (OID = 84387) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT fk_detalleproductopresupuesto_idpieza FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index fk_detalleproductopresupuesto_iddetallepresupuesto (OID = 84392) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT fk_detalleproductopresupuesto_iddetallepresupuesto FOREIGN KEY (iddetallepresupuesto) REFERENCES detallepresupuesto(iddetalle);
--
-- Definition for index fk_detalleproductopresupuesto_idmateriaprima (OID = 84397) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT fk_detalleproductopresupuesto_idmateriaprima FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detalletrabajotercerizado_estado (OID = 84402) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT fk_detalletrabajotercerizado_estado FOREIGN KEY (estado) REFERENCES estadodetalletrabajotercerizado(idestado);
--
-- Definition for index fk_detalletrabajotercerizado_idtrabajotercerizado (OID = 84407) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT fk_detalletrabajotercerizado_idtrabajotercerizado FOREIGN KEY (idtrabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index fk_detalletrabajotercerizado_proceso (OID = 84412) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT fk_detalletrabajotercerizado_proceso FOREIGN KEY (proceso) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index ejecucionetapaproduccion_pkey (OID = 84417) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_pkey PRIMARY KEY (id);
--
-- Definition for index detalleejecucionplanificacion_fk (OID = 84419) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk FOREIGN KEY (idejecucionplanificacionproduccion) REFERENCES ejecucionplanificacionproduccion(idejecucion);
--
-- Definition for index detalleejecucionplanificacion_fk1 (OID = 84424) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk1 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleejecucionplanificacion_fk2 (OID = 84429) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk2 FOREIGN KEY (ejecucionetapa) REFERENCES ejecucionetapaproduccion(id);
--
-- Definition for index detalleejecucionplanificacion_fk3 (OID = 84434) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk3 FOREIGN KEY (piezareal) REFERENCES piezareal(idpiezareal);
--
-- Definition for index etapadeproduccion_fk (OID = 84439) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_fk FOREIGN KEY (maquina) REFERENCES maquina(idmaquina);
--
-- Definition for index detalleplanificacionproduccion_fk4 (OID = 84444) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk4 FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index reclamoempresamantenimiento_pkey (OID = 84756) : 
--
ALTER TABLE ONLY reclamoempresamantenimiento
    ADD CONSTRAINT reclamoempresamantenimiento_pkey PRIMARY KEY (idreclamo);
--
-- Definition for index fk_reclamoempresamantenimiento_tiporeclamo (OID = 84758) : 
--
ALTER TABLE ONLY reclamoempresamantenimiento
    ADD CONSTRAINT fk_reclamoempresamantenimiento_tiporeclamo FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index fk_reclamoempresamantenimiento_trabajotercerizado (OID = 84763) : 
--
ALTER TABLE ONLY reclamoempresamantenimiento
    ADD CONSTRAINT fk_reclamoempresamantenimiento_trabajotercerizado FOREIGN KEY (trabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index reclamoempresamantenimiento_fk (OID = 84768) : 
--
ALTER TABLE ONLY reclamoempresamantenimiento
    ADD CONSTRAINT reclamoempresamantenimiento_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index reclamoempresamantenimiento_fk1 (OID = 84773) : 
--
ALTER TABLE ONLY reclamoempresamantenimiento
    ADD CONSTRAINT reclamoempresamantenimiento_fk1 FOREIGN KEY (trabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index detallereclamoempresamantenimiento_idx (OID = 84799) : 
--
ALTER TABLE ONLY detallereclamoempresamantenimiento
    ADD CONSTRAINT detallereclamoempresamantenimiento_idx PRIMARY KEY (iddetalle);
--
-- Definition for index detallereclamoempresamantenimiento_fk (OID = 84801) : 
--
ALTER TABLE ONLY detallereclamoempresamantenimiento
    ADD CONSTRAINT detallereclamoempresamantenimiento_fk FOREIGN KEY (idreclamo) REFERENCES reclamoempresamantenimiento(idreclamo);
--
-- Definition for index matriz_fk1 (OID = 84813) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT matriz_fk1 FOREIGN KEY (tipomaterial) REFERENCES tipomaterial(idtipomaterial);
--
-- Definition for index detalleejecucionplanificacion_fk4 (OID = 84868) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk4 FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index estadoreclamo_pkey (OID = 84892) : 
--
ALTER TABLE ONLY estadoreclamo
    ADD CONSTRAINT estadoreclamo_pkey PRIMARY KEY (idestadoreclamo);
--
-- Definition for index reclamocliente_fk2 (OID = 84917) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_fk2 FOREIGN KEY (idestadoreclamo) REFERENCES estadoreclamo(idestadoreclamo);
--
-- Definition for index reclamoempresamantenimiento_fk2 (OID = 84922) : 
--
ALTER TABLE ONLY reclamoempresamantenimiento
    ADD CONSTRAINT reclamoempresamantenimiento_fk2 FOREIGN KEY (idestadoreclamo) REFERENCES estadoreclamo(idestadoreclamo);
--
-- Definition for index reclamoempresametalurgica_fk2 (OID = 84927) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_fk2 FOREIGN KEY (idestadoreclamo) REFERENCES estadoreclamo(idestadoreclamo);
--
-- Definition for index reclamoproveedor_fk2 (OID = 84932) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_fk2 FOREIGN KEY (idestadoreclamo) REFERENCES estadoreclamo(idestadoreclamo);
--
-- Definition for index mpasignadaxpiezareal_fk (OID = 84937) : 
--
ALTER TABLE ONLY mpasignadaxpiezareal
    ADD CONSTRAINT mpasignadaxpiezareal_fk FOREIGN KEY (idpiezareal) REFERENCES piezareal(idpiezareal);
--
-- Definition for index detalletrabajotercerizado_iddetalle_key (OID = 85032) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_iddetalle_key PRIMARY KEY (iddetalle);
--
-- Definition for index detallereclamoempresamantenimiento_fk1 (OID = 85070) : 
--
ALTER TABLE ONLY detallereclamoempresamantenimiento
    ADD CONSTRAINT detallereclamoempresamantenimiento_fk1 FOREIGN KEY (iddetalletrabajo) REFERENCES detalletrabajotercerizado(iddetalle);
--
-- Definition for index detallereclamoempresamantenimiento_fk2 (OID = 85075) : 
--
ALTER TABLE ONLY detallereclamoempresamantenimiento
    ADD CONSTRAINT detallereclamoempresamantenimiento_fk2 FOREIGN KEY (idtrabajo) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index detalleplanificacioncalidad_iddetalle_key (OID = 85122) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_iddetalle_key PRIMARY KEY (iddetalle);
--
-- Definition for index detalleplanificacioncalidad_fk3 (OID = 85124) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk3 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleplanificacioncalidad_fk4 (OID = 85129) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk4 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detalleplanificacioncalidad_fk1 (OID = 85134) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk1 FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index detalleplanificacioncalidad_fk5 (OID = 85139) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk5 FOREIGN KEY (maquina) REFERENCES maquina(idmaquina);
--
-- Definition for index detalleejecucionplanificacioncalidad_iddetalle_key (OID = 85149) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_iddetalle_key PRIMARY KEY (iddetalle);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk2 (OID = 85161) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk2 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk3 (OID = 85166) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk3 FOREIGN KEY (piezareal) REFERENCES piezareal(idpiezareal);
--
-- Definition for index ejecucionplanificacioncalidad_idejecucion_key (OID = 85171) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_idejecucion_key PRIMARY KEY (idejecucion);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk (OID = 85173) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk FOREIGN KEY (idejecucionplanificacioncalidad) REFERENCES ejecucionplanificacioncalidad(idejecucion);
--
-- Definition for index ejecucionprocesocalidad_pkey (OID = 85178) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_pkey PRIMARY KEY (idejecucion);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk1 (OID = 85185) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk1 FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk4 (OID = 85190) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk4 FOREIGN KEY (ejecucionprocesocalidad) REFERENCES ejecucionprocesocalidad(idejecucion);
--
-- Definition for index detalleproductoreal_pkey (OID = 85195) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detalleproductoreal_fk1 (OID = 85197) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_fk1 FOREIGN KEY (idpiezareal) REFERENCES piezareal(idpiezareal);
--
-- Definition for index piezareal_fk (OID = 85202) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_fk FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index productoreal_fk (OID = 85207) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detalleproductoreal_fk2 (OID = 85212) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_fk2 FOREIGN KEY (detalleproducto) REFERENCES detalleproducto(iddetalle);
--
-- Definition for index ejecucionprocesocalidad_fk (OID = 85294) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_fk FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index detalleplanificacioncalidad_fk6 (OID = 85300) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk6 FOREIGN KEY (iddetalleejecucionplanificacioncalidad) REFERENCES detalleejecucionplanificacioncalidad(iddetalle);
--
-- Data for sequence public.prueba_id_seq (OID = 81996)
--
SELECT pg_catalog.setval('prueba_id_seq', 1, false);
--
-- Data for sequence public.usuario_idusuario_seq (OID = 82002)
--
SELECT pg_catalog.setval('usuario_idusuario_seq', 14, true);
--
-- Data for sequence public.tipomaterial_idtipomaterial_seq (OID = 82004)
--
SELECT pg_catalog.setval('tipomaterial_idtipomaterial_seq', 6, true);
--
-- Data for sequence public.accioncalidad_idaccioncalidad_seq (OID = 82006)
--
SELECT pg_catalog.setval('accioncalidad_idaccioncalidad_seq', 3, true);
--
-- Data for sequence public.barrio_idbarrio_seq (OID = 82008)
--
SELECT pg_catalog.setval('barrio_idbarrio_seq', 6, true);
--
-- Data for sequence public.cargo_idcargo_seq (OID = 82010)
--
SELECT pg_catalog.setval('cargo_idcargo_seq', 6, true);
--
-- Data for sequence public.categoria_idcategoria_seq (OID = 82012)
--
SELECT pg_catalog.setval('categoria_idcategoria_seq', 2, true);
--
-- Data for sequence public.cliente_idcliente_seq (OID = 82014)
--
SELECT pg_catalog.setval('cliente_idcliente_seq', 24, true);
--
-- Data for sequence public.codigodebarra_idcodigo_seq (OID = 82016)
--
SELECT pg_catalog.setval('codigodebarra_idcodigo_seq', 103, true);
--
-- Data for sequence public.compra_idcompra_seq (OID = 82018)
--
SELECT pg_catalog.setval('compra_idcompra_seq', 2, true);
--
-- Data for sequence public.comprobantepago_idcomprobantepago_seq (OID = 82020)
--
SELECT pg_catalog.setval('comprobantepago_idcomprobantepago_seq', 10, true);
--
-- Data for sequence public.condicioniva_idcondicioniva_seq (OID = 82022)
--
SELECT pg_catalog.setval('condicioniva_idcondicioniva_seq', 3, true);
--
-- Data for sequence public.detalleplanificacionproduccion_id_seq (OID = 82024)
--
SELECT pg_catalog.setval('detalleplanificacionproduccion_id_seq', 103, true);
--
-- Data for sequence public.detallecompra_iddetalle_seq (OID = 82026)
--
SELECT pg_catalog.setval('detallecompra_iddetalle_seq', 3, true);
--
-- Data for sequence public.detalleejecucionplanificacion_iddetalle_seq (OID = 82028)
--
SELECT pg_catalog.setval('detalleejecucionplanificacion_iddetalle_seq', 34, true);
--
-- Data for sequence public.detalleejecucionplanificacioncalidad_iddetalle_seq (OID = 82030)
--
SELECT pg_catalog.setval('detalleejecucionplanificacioncalidad_iddetalle_seq', 9, true);
--
-- Data for sequence public.detallefactura_iddetalle_seq (OID = 82032)
--
SELECT pg_catalog.setval('detallefactura_iddetalle_seq', 9, true);
--
-- Data for sequence public.detallemantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 82034)
--
SELECT pg_catalog.setval('detallemantenimientocorrectivo_idmantenimientocorrectivo_seq', 1, false);
--
-- Data for sequence public.detallemantenimientopreventivo_idmantenimientopreventivo_seq (OID = 82036)
--
SELECT pg_catalog.setval('detallemantenimientopreventivo_idmantenimientopreventivo_seq', 1, false);
--
-- Data for sequence public.detallepedido_iddetalle_seq (OID = 82038)
--
SELECT pg_catalog.setval('detallepedido_iddetalle_seq', 65, true);
--
-- Data for sequence public.detalleplanificacioncalidad_iddetalle_seq (OID = 82040)
--
SELECT pg_catalog.setval('detalleplanificacioncalidad_iddetalle_seq', 11, true);
--
-- Data for sequence public.detalleplanprocedimientos_iddetalle_seq (OID = 82042)
--
SELECT pg_catalog.setval('detalleplanprocedimientos_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleplanprocesoscalidad_iddetalle_seq (OID = 82044)
--
SELECT pg_catalog.setval('detalleplanprocesoscalidad_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallepresupuesto_iddetalle_seq (OID = 82046)
--
SELECT pg_catalog.setval('detallepresupuesto_iddetalle_seq', 80, true);
--
-- Data for sequence public.detalleproducto_iddetalle_seq (OID = 82048)
--
SELECT pg_catalog.setval('detalleproducto_iddetalle_seq', 20, true);
--
-- Data for sequence public.detalleproductoreal_iddetalle_seq (OID = 82050)
--
SELECT pg_catalog.setval('detalleproductoreal_iddetalle_seq', 11, true);
--
-- Data for sequence public.detallereclamocliente_iddetalle_seq (OID = 82052)
--
SELECT pg_catalog.setval('detallereclamocliente_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallereclamoempresametalurgica_iddetalle_seq (OID = 82054)
--
SELECT pg_catalog.setval('detallereclamoempresametalurgica_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallereclamoproveedor_iddetalle_seq (OID = 82056)
--
SELECT pg_catalog.setval('detallereclamoproveedor_iddetalle_seq', 1, true);
--
-- Data for sequence public.detalleremito_iddetalle_seq (OID = 82058)
--
SELECT pg_catalog.setval('detalleremito_iddetalle_seq', 9, true);
--
-- Data for sequence public.detallerequerimientosmateriaprima_iddetalle_seq (OID = 82060)
--
SELECT pg_catalog.setval('detallerequerimientosmateriaprima_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalletrabajotercerizado_iddetalle_seq (OID = 82062)
--
SELECT pg_catalog.setval('detalletrabajotercerizado_iddetalle_seq', 2, true);
--
-- Data for sequence public.domicilio_iddomicilio_seq (OID = 82064)
--
SELECT pg_catalog.setval('domicilio_iddomicilio_seq', 61, true);
--
-- Data for sequence public.ejecucionetapaproduccion_idejecucion_seq (OID = 82066)
--
SELECT pg_catalog.setval('ejecucionetapaproduccion_idejecucion_seq', 34, true);
--
-- Data for sequence public.ejecucionplanificacioncalidad_idejecucion_seq (OID = 82068)
--
SELECT pg_catalog.setval('ejecucionplanificacioncalidad_idejecucion_seq', 3, true);
--
-- Data for sequence public.ejecucionplanificacionproduccion_idejecucion_seq (OID = 82070)
--
SELECT pg_catalog.setval('ejecucionplanificacionproduccion_idejecucion_seq', 34, true);
--
-- Data for sequence public.ejecucionprocesocalidad_idejecucion_seq (OID = 82072)
--
SELECT pg_catalog.setval('ejecucionprocesocalidad_idejecucion_seq', 9, true);
--
-- Data for sequence public.empleado_idempleado_seq (OID = 82074)
--
SELECT pg_catalog.setval('empleado_idempleado_seq', 5, true);
--
-- Data for sequence public.empresametalurgica_idempresametalurgica_seq (OID = 82076)
--
SELECT pg_catalog.setval('empresametalurgica_idempresametalurgica_seq', 1, true);
--
-- Data for sequence public.estadocliente_idestado_seq (OID = 82078)
--
SELECT pg_catalog.setval('estadocliente_idestado_seq', 4, true);
--
-- Data for sequence public.estadocompra_idestado_seq (OID = 82080)
--
SELECT pg_catalog.setval('estadocompra_idestado_seq', 10, true);
--
-- Data for sequence public.estadodetallecompra_idestado_seq (OID = 82082)
--
SELECT pg_catalog.setval('estadodetallecompra_idestado_seq', 8, true);
--
-- Data for sequence public.estadodetalletrabajotercerizado_idestado_seq (OID = 82084)
--
SELECT pg_catalog.setval('estadodetalletrabajotercerizado_idestado_seq', 9, true);
--
-- Data for sequence public.estadoejecetapaprod_idestado_seq (OID = 82086)
--
SELECT pg_catalog.setval('estadoejecetapaprod_idestado_seq', 6, true);
--
-- Data for sequence public.estadoejecplancalidad_idestado_seq (OID = 82088)
--
SELECT pg_catalog.setval('estadoejecplancalidad_idestado_seq', 5, true);
--
-- Data for sequence public.estadoejecplanifpedido_idestado_seq (OID = 82090)
--
SELECT pg_catalog.setval('estadoejecplanifpedido_idestado_seq', 7, true);
--
-- Data for sequence public.estadoejecucionprocesocalidad_idestado_seq (OID = 82092)
--
SELECT pg_catalog.setval('estadoejecucionprocesocalidad_idestado_seq', 7, true);
--
-- Data for sequence public.estadofactura_idestado_seq (OID = 82094)
--
SELECT pg_catalog.setval('estadofactura_idestado_seq', 2, true);
--
-- Data for sequence public.estadomaquina_idestado_seq (OID = 82096)
--
SELECT pg_catalog.setval('estadomaquina_idestado_seq', 1, true);
--
-- Data for sequence public.estadopedido_idestado_seq (OID = 82098)
--
SELECT pg_catalog.setval('estadopedido_idestado_seq', 19, true);
--
-- Data for sequence public.estadopiezareal_idestado_seq (OID = 82100)
--
SELECT pg_catalog.setval('estadopiezareal_idestado_seq', 1, true);
--
-- Data for sequence public.estadoproductoreal_idestado_seq (OID = 82102)
--
SELECT pg_catalog.setval('estadoproductoreal_idestado_seq', 11, true);
--
-- Data for sequence public.estadoremito_idestado_seq (OID = 82104)
--
SELECT pg_catalog.setval('estadoremito_idestado_seq', 1, true);
--
-- Data for sequence public.estadotrabajotercerizado_idestado_seq (OID = 82106)
--
SELECT pg_catalog.setval('estadotrabajotercerizado_idestado_seq', 14, true);
--
-- Data for sequence public.etapadeproduccion_idetapaproduccion_seq (OID = 82108)
--
SELECT pg_catalog.setval('etapadeproduccion_idetapaproduccion_seq', 6, true);
--
-- Data for sequence public.factura_idfactura_seq (OID = 82110)
--
SELECT pg_catalog.setval('factura_idfactura_seq', 5, true);
--
-- Data for sequence public.formadepago_idformapago_seq (OID = 82112)
--
SELECT pg_catalog.setval('formadepago_idformapago_seq', 3, true);
--
-- Data for sequence public.localidad_idlocalidad_seq (OID = 82114)
--
SELECT pg_catalog.setval('localidad_idlocalidad_seq', 11, true);
--
-- Data for sequence public.mantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 82116)
--
SELECT pg_catalog.setval('mantenimientocorrectivo_idmantenimientocorrectivo_seq', 1, false);
--
-- Data for sequence public.mantenimientopreventivo_idmantenimientopreventivo_seq (OID = 82118)
--
SELECT pg_catalog.setval('mantenimientopreventivo_idmantenimientopreventivo_seq', 1, false);
--
-- Data for sequence public.maquina_idmaquina_seq (OID = 82120)
--
SELECT pg_catalog.setval('maquina_idmaquina_seq', 1, true);
--
-- Data for sequence public.marca_idmarca_seq (OID = 82122)
--
SELECT pg_catalog.setval('marca_idmarca_seq', 3, true);
--
-- Data for sequence public.materiaprima_idmateriaprima_seq (OID = 82124)
--
SELECT pg_catalog.setval('materiaprima_idmateriaprima_seq', 10, true);
--
-- Data for sequence public.matriz_idmatriz_seq (OID = 82126)
--
SELECT pg_catalog.setval('matriz_idmatriz_seq', 5, true);
--
-- Data for sequence public.pedido_idpedido_seq (OID = 82128)
--
SELECT pg_catalog.setval('pedido_idpedido_seq', 47, true);
--
-- Data for sequence public.pedidomatriz_idpedidomatriz_seq (OID = 82130)
--
SELECT pg_catalog.setval('pedidomatriz_idpedidomatriz_seq', 1, false);
--
-- Data for sequence public.pieza_idpieza_seq (OID = 82132)
--
SELECT pg_catalog.setval('pieza_idpieza_seq', 15, true);
--
-- Data for sequence public.piezareal_idpiezareal_seq (OID = 82134)
--
SELECT pg_catalog.setval('piezareal_idpiezareal_seq', 1, false);
--
-- Data for sequence public.planificacioncalidad_idplanificacion_seq (OID = 82136)
--
SELECT pg_catalog.setval('planificacioncalidad_idplanificacion_seq', 4, true);
--
-- Data for sequence public.planificacionproduccion_idplanificacionproduccion_seq (OID = 82138)
--
SELECT pg_catalog.setval('planificacionproduccion_idplanificacionproduccion_seq', 55, true);
--
-- Data for sequence public.plano_idplano_seq (OID = 82140)
--
SELECT pg_catalog.setval('plano_idplano_seq', 1, false);
--
-- Data for sequence public.planprocedimientos_idplanprocedimientos_seq (OID = 82142)
--
SELECT pg_catalog.setval('planprocedimientos_idplanprocedimientos_seq', 1, false);
--
-- Data for sequence public.planprocesoscalidad_idplanprocesoscalidad_seq (OID = 82144)
--
SELECT pg_catalog.setval('planprocesoscalidad_idplanprocesoscalidad_seq', 1, false);
--
-- Data for sequence public.planrequerimientosmateriaprima_idplanrequerimientosmateriaprima (OID = 82146)
--
SELECT pg_catalog.setval('planrequerimientosmateriaprima_idplanrequerimientosmateriaprima', 1, false);
--
-- Data for sequence public.presupuesto_idpresupuesto_seq (OID = 82148)
--
SELECT pg_catalog.setval('presupuesto_idpresupuesto_seq', 64, true);
--
-- Data for sequence public.prioridad_idprioridad_seq (OID = 82150)
--
SELECT pg_catalog.setval('prioridad_idprioridad_seq', 3, true);
--
-- Data for sequence public.privilegio_idprivilegio_seq (OID = 82152)
--
SELECT pg_catalog.setval('privilegio_idprivilegio_seq', 1, false);
--
-- Data for sequence public.procesocalidad_idprocesocalidad_seq (OID = 82154)
--
SELECT pg_catalog.setval('procesocalidad_idprocesocalidad_seq', 2, true);
--
-- Data for sequence public.producto_idproducto_seq (OID = 82156)
--
SELECT pg_catalog.setval('producto_idproducto_seq', 12, true);
--
-- Data for sequence public.productoreal_idproductoreal_seq (OID = 82158)
--
SELECT pg_catalog.setval('productoreal_idproductoreal_seq', 5, true);
--
-- Data for sequence public.proveedor_idproveedor_seq (OID = 82160)
--
SELECT pg_catalog.setval('proveedor_idproveedor_seq', 3, true);
--
-- Data for sequence public.proveedormantenimientomaquina_idproveedormantenimiento_seq (OID = 82162)
--
SELECT pg_catalog.setval('proveedormantenimientomaquina_idproveedormantenimiento_seq', 1, false);
--
-- Data for sequence public.provincia_idprovincia_seq (OID = 82164)
--
SELECT pg_catalog.setval('provincia_idprovincia_seq', 14, true);
--
-- Data for sequence public.reclamocliente_idreclamo_seq (OID = 82166)
--
SELECT pg_catalog.setval('reclamocliente_idreclamo_seq', 1, false);
--
-- Data for sequence public.reclamoempresametalurgica_idreclamo_seq (OID = 82168)
--
SELECT pg_catalog.setval('reclamoempresametalurgica_idreclamo_seq', 1, false);
--
-- Data for sequence public.reclamoproveedor_idreclamo_seq (OID = 82170)
--
SELECT pg_catalog.setval('reclamoproveedor_idreclamo_seq', 2, true);
--
-- Data for sequence public.remito_idremito_seq (OID = 82172)
--
SELECT pg_catalog.setval('remito_idremito_seq', 5, true);
--
-- Data for sequence public.responsable_idresponsable_seq (OID = 82174)
--
SELECT pg_catalog.setval('responsable_idresponsable_seq', 26, true);
--
-- Data for sequence public.rol_idrol_seq (OID = 82176)
--
SELECT pg_catalog.setval('rol_idrol_seq', 8, true);
--
-- Data for sequence public.rotura_idrotura_seq (OID = 82178)
--
SELECT pg_catalog.setval('rotura_idrotura_seq', 1, false);
--
-- Data for sequence public.servicio_idservicio_seq (OID = 82180)
--
SELECT pg_catalog.setval('servicio_idservicio_seq', 1, false);
--
-- Data for sequence public.sesion_idsesion_seq (OID = 82182)
--
SELECT pg_catalog.setval('sesion_idsesion_seq', 1, false);
--
-- Data for sequence public.tipodocumento_idtipodocumento_seq (OID = 82184)
--
SELECT pg_catalog.setval('tipodocumento_idtipodocumento_seq', 3, true);
--
-- Data for sequence public.tipoiva_idtipoiva_seq (OID = 82186)
--
SELECT pg_catalog.setval('tipoiva_idtipoiva_seq', 1, false);
--
-- Data for sequence public.tipomaquina_idtipomaquina_seq (OID = 82188)
--
SELECT pg_catalog.setval('tipomaquina_idtipomaquina_seq', 6, true);
--
-- Data for sequence public.tiporeclamo_idtiporeclamo_seq (OID = 82190)
--
SELECT pg_catalog.setval('tiporeclamo_idtiporeclamo_seq', 1, true);
--
-- Data for sequence public.trabajotercerizado_idtrabajo_seq (OID = 82192)
--
SELECT pg_catalog.setval('trabajotercerizado_idtrabajo_seq', 1, true);
--
-- Data for sequence public.turno_idturno_seq (OID = 82194)
--
SELECT pg_catalog.setval('turno_idturno_seq', 3, true);
--
-- Data for sequence public.unidadmedida_idunidadmedida_seq (OID = 82204)
--
SELECT pg_catalog.setval('unidadmedida_idunidadmedida_seq', 4, true);
--
-- Data for sequence public.detallepiezapresupuesto_iddetalle_seq (OID = 82214)
--
SELECT pg_catalog.setval('detallepiezapresupuesto_iddetalle_seq', 145, true);
--
-- Data for sequence public.detalleproductopresupuesto_iddetalle_seq (OID = 82220)
--
SELECT pg_catalog.setval('detalleproductopresupuesto_iddetalle_seq', 126, true);
--
-- Data for sequence public.detallepiezacalidadpresupuesto_iddetalle_seq (OID = 82226)
--
SELECT pg_catalog.setval('detallepiezacalidadpresupuesto_iddetalle_seq', 111, true);
--
-- Data for sequence public.calendario_id_seq (OID = 82249)
--
SELECT pg_catalog.setval('calendario_id_seq', 12, true);
--
-- Data for sequence public.disponibilidadhoraria_id_seq (OID = 82259)
--
SELECT pg_catalog.setval('disponibilidadhoraria_id_seq', 20, true);
--
-- Data for sequence public.estadoplanificacionproduccion_id_seq (OID = 82265)
--
SELECT pg_catalog.setval('estadoplanificacionproduccion_id_seq', 3, true);
--
-- Data for sequence public.maquina_idmaquina_seq1 (OID = 82274)
--
SELECT pg_catalog.setval('maquina_idmaquina_seq1', 12, true);
--
-- Data for sequence public.piezareal_idpiezareal_seq1 (OID = 82280)
--
SELECT pg_catalog.setval('piezareal_idpiezareal_seq1', 97, true);
--
-- Data for sequence public.pieza_idpieza_seq1 (OID = 82286)
--
SELECT pg_catalog.setval('pieza_idpieza_seq1', 7, true);
--
-- Data for sequence public.detallempasignada_id_seq (OID = 82292)
--
SELECT pg_catalog.setval('detallempasignada_id_seq', 45, true);
--
-- Data for sequence public.mpasignadaxpiezareal_id_seq (OID = 82298)
--
SELECT pg_catalog.setval('mpasignadaxpiezareal_id_seq', 84, true);
--
-- Data for sequence public.reclamoempresamantenimiento_idreclamo_seq (OID = 84750)
--
SELECT pg_catalog.setval('reclamoempresamantenimiento_idreclamo_seq', 1, false);
--
-- Data for sequence public.detallereclamoempresamantenimiento_iddetalle_seq (OID = 84793)
--
SELECT pg_catalog.setval('detallereclamoempresamantenimiento_iddetalle_seq', 1, false);
--
-- Data for sequence public.estadoreclamo_idestadoreclamo_seq (OID = 84886)
--
SELECT pg_catalog.setval('estadoreclamo_idestadoreclamo_seq', 5, true);
--
-- Comments
--
COMMENT ON SCHEMA public IS 'standard public schema';
