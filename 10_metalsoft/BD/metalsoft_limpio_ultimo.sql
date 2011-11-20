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
-- Definition for function nvonropedido (OID = 128479) : 
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
-- Definition for function cantpiezasxproducto (OID = 128480) : 
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
-- Definition for function esproductosinalgunaetapa (OID = 128481) : 
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
-- Definition for function cantpiezasdepedido (OID = 128482) : 
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
-- Definition for function cantpiezasdepedido2 (OID = 128484) : 
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
-- Definition for function nvonropresupuesto (OID = 128485) : 
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
-- Definition for function ingresoxpedido (OID = 128486) : 
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
-- Definition for function nvonrocliente (OID = 128487) : 
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
-- Definition for function nvonromateriaprima (OID = 128488) : 
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
-- Definition for function nvonroempleado (OID = 128489) : 
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
-- Definition for function nvonromaquina (OID = 128490) : 
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
-- Definition for function nvonroejecucionplanificacion (OID = 128491) : 
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
-- Definition for function nvonroplanificacionproduccion (OID = 128492) : 
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
-- Definition for function nvonroejecucionetapa (OID = 128493) : 
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
-- Definition for function nvonropiezareal (OID = 128494) : 
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
-- Definition for function nvonroempresametalurgica (OID = 128495) : 
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
-- Definition for function nvonropedidomatriz (OID = 128496) : 
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
-- Definition for function nvonroproveedormantenimiento (OID = 128497) : 
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
-- Definition for function nvonroproveedor (OID = 128498) : 
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
-- Definition for function nvonromantenimientopreventivo (OID = 128499) : 
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
-- Definition for function nvonrotrabajotercerizado (OID = 128500) : 
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
-- Definition for function esulejecetapaprod (OID = 128501) : 
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
-- Definition for function esulejecetapadepieza (OID = 128502) : 
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
-- Definition for function horafinprevistaplanifprod (OID = 128503) : 
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
-- Definition for function nvonroplanificacioncalidad (OID = 128504) : 
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
-- Definition for function nvonroproductoreal (OID = 128505) : 
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
-- Definition for function nvonroejecplanifcalidad (OID = 128506) : 
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
-- Definition for function nvonroejecprocalidad (OID = 128507) : 
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
-- Definition for function esulejecprocalpieza (OID = 128508) : 
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
-- Definition for function esulejecprocalcalidad (OID = 128509) : 
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
-- Definition for function nvonromantcorrectivo (OID = 128510) : 
--
CREATE FUNCTION public.nvonromantcorrectivo () RETURNS bigint
AS 
$body$
DECLARE
result BIGINT;
BEGIN
SELECT (max(p.nromantenimientocorrectivo)+1) INTO result FROM mantenimientocorrectivo p;
IF(result IS NULL)THEN
result:=1;
END IF;
RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function cantidadmpenpedido (OID = 128511) : 
--
CREATE FUNCTION public.cantidadmpenpedido (idpedido bigint) RETURNS integer
AS 
$body$
DECLARE
  cantidad INTEGER;
BEGIN
 SELECT sum(dp.cantidad * dpp.cantpiezas * dpp.cantmateriaprima) INTO cantidad
 FROM pedido pe,
      detalleproductopresupuesto dpp,
      presupuesto pre,
      detallepresupuesto dp
 WHERE pe.presupuesto = pre.idpresupuesto AND
       dp.idpresupuesto = pre.idpresupuesto AND
       dp.iddetalle = dpp.iddetallepresupuesto AND
       pe.idpedido = idpedido;
       
  IF(cantidad IS NULL)THEN
      cantidad:=0;
  END IF;
 RETURN cantidad;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function cantidadmpasignada (OID = 128512) : 
--
CREATE FUNCTION public.cantidadmpasignada (idpedido bigint) RETURNS integer
AS 
$body$
DECLARE
  cantidad INTEGER;
BEGIN
 SELECT 
       sum(dmpa.cantidadmp) INTO cantidad
 FROM planificacionproduccion ppro,
      detallempasignada dmpa
 WHERE ppro.idplanificacionproduccion = dmpa.idplanificacionproduccion AND
 		ppro.pedido = idpedido;
 
  IF(cantidad IS NULL)THEN
  	cantidad:=0;
  END IF;
  
 RETURN cantidad;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function cantidadmpenpedido (OID = 128513) : 
--
CREATE FUNCTION public.cantidadmpenpedido (idpedido bigint, idmatprima bigint) RETURNS integer
AS 
$body$
DECLARE
  cantidad INTEGER;
BEGIN
 SELECT sum(dp.cantidad * dpp.cantpiezas * dpp.cantmateriaprima) INTO cantidad
 FROM pedido pe,
      detalleproductopresupuesto dpp,
      presupuesto pre,
      detallepresupuesto dp
 WHERE pe.presupuesto = pre.idpresupuesto AND
       dp.idpresupuesto = pre.idpresupuesto AND
       dp.iddetalle = dpp.iddetallepresupuesto AND
       pe.idpedido = idpedido AND
       dpp.idmateriaprima = idmatprima;
       
  IF(cantidad IS NULL)THEN
      cantidad:=0;
  END IF;
 RETURN cantidad;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function cantidadmpasignada (OID = 128514) : 
--
CREATE FUNCTION public.cantidadmpasignada (idpedido bigint, idmatprima bigint) RETURNS integer
AS 
$body$
DECLARE
  cantidad INTEGER;
BEGIN
 SELECT 
       sum(dmpa.cantidadmp) INTO cantidad
 FROM planificacionproduccion ppro,
      detallempasignada dmpa
 WHERE ppro.idplanificacionproduccion = dmpa.idplanificacionproduccion AND
 		ppro.pedido = idpedido AND
        dmpa.idmateriaprima = idmatprima;
 
  IF(cantidad IS NULL)THEN
  	cantidad:=0;
  END IF;
  
 RETURN cantidad;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function nvonroetapa (OID = 128515) : 
--
CREATE FUNCTION public.nvonroetapa () RETURNS bigint
AS 
$body$
DECLARE
  result BIGINT;
BEGIN
  SELECT (max(p.nroetapaproduccion)+1) INTO result FROM etapadeproduccion p;
  IF(result IS NULL)THEN
   result:=1;
  END IF;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Definition for function nvonroproducto (OID = 131929) : 
--
CREATE FUNCTION public.nvonroproducto () RETURNS bigint
AS 
$body$
DECLARE
  result BIGINT;
BEGIN
  SELECT (max(p.nroproducto)+1) INTO result FROM Producto p;
  IF(result IS NULL)THEN
  	result:=1;
  END IF;
  RETURN result;
END;
$body$
    LANGUAGE plpgsql;
--
-- Structure for table pedido (OID = 128516) : 
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
    motivocancelacion character varying,
    espedidoweb boolean,
    nropedidocotizacioncliente integer DEFAULT 0,
    fecharegpedcotiz date,
    idpedido bigint DEFAULT nextval(('"public"."pedido_idpedido_seq"'::text)::regclass) NOT NULL,
    cliente bigint,
    planprocedimientos bigint,
    planrequerimientosmateriaprima bigint,
    planprocesoscalidad bigint,
    prioridad bigint NOT NULL,
    observaciones character varying
) WITH OIDS;
--
-- Structure for table planificacioncalidad (OID = 128524) : 
--
CREATE TABLE public.planificacioncalidad (
    idplanificacion bigint DEFAULT nextval(('"public"."planificacioncalidad_idplanificacion_seq"'::text)::regclass) NOT NULL,
    nroplanificacion bigint,
    fechacreacion date,
    observaciones character varying,
    fechainicioprevista date,
    fechafinprevista date,
    pedido bigint
) WITH OIDS;
--
-- Structure for table estadopedido (OID = 128531) : 
--
CREATE TABLE public.estadopedido (
    idestado bigint DEFAULT nextval(('"public"."estadopedido_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table factura (OID = 128538) : 
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
    tipofactura character varying,
    montototal double precision
) WITH OIDS;
--
-- Structure for table planificacionproduccion (OID = 128545) : 
--
CREATE TABLE public.planificacionproduccion (
    idplanificacionproduccion bigint DEFAULT nextval(('"public"."planificacionproduccion_idplanificacionproduccion_seq"'::text)::regclass) NOT NULL,
    nroplanificacion bigint,
    fechacreacion date,
    observaciones character varying,
    fechainicioprevista date,
    fechafinprevista date,
    pedido bigint,
    idestado bigint
) WITH OIDS;
--
-- Structure for table presupuesto (OID = 128552) : 
--
CREATE TABLE public.presupuesto (
    idpresupuesto bigint DEFAULT nextval(('"public"."presupuesto_idpresupuesto_seq"'::text)::regclass) NOT NULL,
    fechapresupuesto date,
    montototal double precision,
    fechavencimiento date,
    nropresupuesto bigint,
    gananciaadicional double precision DEFAULT 0
) WITH OIDS;
--
-- Structure for table plano (OID = 128557) : 
--
CREATE TABLE public.plano (
    idplano bigint DEFAULT nextval(('"public"."plano_idplano_seq"'::text)::regclass) NOT NULL,
    nroplano bigint,
    escala integer,
    pedido bigint,
    imagen bytea[]
) WITH OIDS;
--
-- Structure for table remito (OID = 128564) : 
--
CREATE TABLE public.remito (
    idremito bigint DEFAULT nextval(('"public"."remito_idremito_seq"'::text)::regclass) NOT NULL,
    nroremito bigint,
    fechaemision date,
    pedido bigint,
    estado bigint
) WITH OIDS;
--
-- Structure for table trabajotercerizado (OID = 128568) : 
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
    motivocancelacion character varying,
    empresa bigint,
    estado integer,
    pedido bigint,
    fechadelingresocotizacion date,
    montototal double precision
) WITH OIDS;
--
-- Structure for table cliente (OID = 128575) : 
--
CREATE TABLE public.cliente (
    nrocliente bigint,
    idcliente bigint DEFAULT nextval(('"public"."cliente_idcliente_seq"'::text)::regclass) NOT NULL,
    prioridad bigint,
    estado bigint,
    esmoroso boolean,
    usuario bigint,
    razonsocial character varying,
    responsable bigint,
    telefono character varying,
    celular character varying,
    mail character varying,
    domicilio bigint,
    fechaalta date,
    fechabaja date,
    cuil character varying,
    condicioniva bigint,
    cuit character varying
) WITH OIDS;
--
-- Structure for table tipoiva (OID = 128582) : 
--
CREATE TABLE public.tipoiva (
    idtipoiva bigint DEFAULT nextval(('"public"."tipoiva_idtipoiva_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table formadepago (OID = 128589) : 
--
CREATE TABLE public.formadepago (
    idformapago bigint DEFAULT nextval(('"public"."formadepago_idformapago_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table usuario (OID = 128596) : 
--
CREATE TABLE public.usuario (
    idusuario bigint DEFAULT nextval(('"public"."usuario_idusuario_seq"'::text)::regclass) NOT NULL,
    usuario character varying,
    clave character varying
) WITH OIDS;
--
-- Structure for table estadofactura (OID = 128603) : 
--
CREATE TABLE public.estadofactura (
    idestado bigint DEFAULT nextval(('"public"."estadofactura_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table comprobantepago (OID = 128610) : 
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
-- Structure for table estadotrabajotercerizado (OID = 128614) : 
--
CREATE TABLE public.estadotrabajotercerizado (
    idestado bigint DEFAULT nextval(('"public"."estadotrabajotercerizado_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table empresametalurgica (OID = 128621) : 
--
CREATE TABLE public.empresametalurgica (
    idempresametalurgica bigint DEFAULT nextval(('"public"."empresametalurgica_idempresametalurgica_seq"'::text)::regclass) NOT NULL,
    nroempresametalurgica bigint,
    razonsocial character varying,
    responsable bigint,
    telefono character varying,
    celular character varying,
    mail character varying,
    domicilio bigint,
    fechaalta date,
    fechabaja date,
    cuil character varying,
    condicioniva bigint,
    cuit character varying(30)
) WITH OIDS;
--
-- Structure for table prioridad (OID = 128628) : 
--
CREATE TABLE public.prioridad (
    idprioridad bigint DEFAULT nextval(('"public"."prioridad_idprioridad_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table estadocliente (OID = 128635) : 
--
CREATE TABLE public.estadocliente (
    idestado bigint DEFAULT nextval(('"public"."estadocliente_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table responsable (OID = 128642) : 
--
CREATE TABLE public.responsable (
    idresponsable bigint DEFAULT nextval(('"public"."responsable_idresponsable_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    apellido character varying,
    telefono character varying,
    email character varying,
    domicilio bigint,
    nrodocumento integer,
    tipodocumento bigint,
    fax character varying
) WITH OIDS;
--
-- Structure for table condicioniva (OID = 128649) : 
--
CREATE TABLE public.condicioniva (
    idcondicioniva bigint DEFAULT nextval(('"public"."condicioniva_idcondicioniva_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table domicilio (OID = 128656) : 
--
CREATE TABLE public.domicilio (
    iddomicilio bigint DEFAULT nextval(('"public"."domicilio_iddomicilio_seq"'::text)::regclass) NOT NULL,
    calle character varying,
    numerocalle integer,
    piso integer,
    depto character varying(5),
    torre character varying(10),
    barrio bigint
) WITH OIDS;
--
-- Structure for table rotura (OID = 128663) : 
--
CREATE TABLE public.rotura (
    idrotura bigint DEFAULT nextval(('"public"."rotura_idrotura_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table categoria (OID = 128670) : 
--
CREATE TABLE public.categoria (
    idcategoria bigint DEFAULT nextval(('"public"."categoria_idcategoria_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table cargo (OID = 128677) : 
--
CREATE TABLE public.cargo (
    idcargo bigint DEFAULT nextval(('"public"."cargo_idcargo_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table turno (OID = 128684) : 
--
CREATE TABLE public.turno (
    idturno bigint DEFAULT nextval(('"public"."turno_idturno_seq"'::text)::regclass) NOT NULL,
    horainicio time without time zone,
    horafin time without time zone,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table marca (OID = 128691) : 
--
CREATE TABLE public.marca (
    idmarca bigint DEFAULT nextval(('"public"."marca_idmarca_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table estadomaquina (OID = 128698) : 
--
CREATE TABLE public.estadomaquina (
    idestado bigint DEFAULT nextval(('"public"."estadomaquina_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table tipomaquina (OID = 128705) : 
--
CREATE TABLE public.tipomaquina (
    idtipomaquina bigint DEFAULT nextval(('"public"."tipomaquina_idtipomaquina_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table estadoejecetapaprod (OID = 128712) : 
--
CREATE TABLE public.estadoejecetapaprod (
    idestado bigint DEFAULT nextval(('"public"."estadoejecetapaprod_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table servicio (OID = 128719) : 
--
CREATE TABLE public.servicio (
    idservicio bigint DEFAULT nextval(('"public"."servicio_idservicio_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table tipodocumento (OID = 128726) : 
--
CREATE TABLE public.tipodocumento (
    idtipodocumento bigint DEFAULT nextval(('"public"."tipodocumento_idtipodocumento_seq"'::text)::regclass) NOT NULL,
    tipo character varying(50),
    nombre character varying(50)
) WITH OIDS;
--
-- Structure for table codigodebarra (OID = 128730) : 
--
CREATE TABLE public.codigodebarra (
    idcodigo bigint DEFAULT nextval(('"public"."codigodebarra_idcodigo_seq"'::text)::regclass) NOT NULL,
    descripcion character varying(50),
    codigo character varying(100)
) WITH OIDS;
--
-- Structure for table estadopiezareal (OID = 128734) : 
--
CREATE TABLE public.estadopiezareal (
    idestado bigint DEFAULT nextval(('"public"."estadopiezareal_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecplanifpedido (OID = 128738) : 
--
CREATE TABLE public.estadoejecplanifpedido (
    idestado bigint DEFAULT nextval(('"public"."estadoejecplanifpedido_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table proveedormantenimientomaquina (OID = 128742) : 
--
CREATE TABLE public.proveedormantenimientomaquina (
    idproveedormantenimiento bigint DEFAULT nextval(('"public"."proveedormantenimientomaquina_idproveedormantenimiento_seq"'::text)::regclass) NOT NULL,
    nroproveedor bigint,
    razonsocial character varying,
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
-- Structure for table rol (OID = 128749) : 
--
CREATE TABLE public.rol (
    idrol bigint DEFAULT nextval(('"public"."rol_idrol_seq"'::text)::regclass) NOT NULL,
    rol character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Structure for table privilegio (OID = 128756) : 
--
CREATE TABLE public.privilegio (
    idprivilegio bigint DEFAULT nextval(('"public"."privilegio_idprivilegio_seq"'::text)::regclass) NOT NULL,
    privilegio character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Structure for table accioncalidad (OID = 128763) : 
--
CREATE TABLE public.accioncalidad (
    idaccioncalidad bigint DEFAULT nextval(('"public"."accioncalidad_idaccioncalidad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Structure for table empleado (OID = 128770) : 
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
-- Structure for table proveedor (OID = 128774) : 
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
-- Structure for table estadocompra (OID = 128778) : 
--
CREATE TABLE public.estadocompra (
    idestado bigint DEFAULT nextval(('"public"."estadocompra_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Structure for table sesion (OID = 128785) : 
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
-- Structure for table materiaprima (OID = 128789) : 
--
CREATE TABLE public.materiaprima (
    idmateriaprima bigint DEFAULT nextval(('"public"."materiaprima_idmateriaprima_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    fechaalta date,
    fechabaja date,
    codbarra bigint,
    stock bigint,
    descripcion character varying,
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
-- Structure for table matriz (OID = 128796) : 
--
CREATE TABLE public.matriz (
    idmatriz bigint DEFAULT nextval(('"public"."matriz_idmatriz_seq"'::text)::regclass) NOT NULL,
    codmatriz bigint,
    nombre character varying(50),
    descripcion character varying,
    observaciones character varying,
    fechacreacion date,
    materiaprima bigint,
    tipomaterial bigint
) WITH OIDS;
--
-- Structure for table producto (OID = 128803) : 
--
CREATE TABLE public.producto (
    idproducto bigint DEFAULT nextval(('"public"."producto_idproducto_seq"'::text)::regclass) NOT NULL,
    nroproducto bigint,
    nombre character varying(50),
    preciounitario double precision,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table estadoejecplancalidad (OID = 128810) : 
--
CREATE TABLE public.estadoejecplancalidad (
    idestado bigint DEFAULT nextval(('"public"."estadoejecplancalidad_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Structure for table mantenimientopreventivo (OID = 128817) : 
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
    duraciontotal integer,
    estado bigint
) WITH OIDS;
--
-- Structure for table mantenimientocorrectivo (OID = 128821) : 
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
-- Structure for table etapadeproduccion (OID = 128825) : 
--
CREATE TABLE public.etapadeproduccion (
    idetapaproduccion bigint DEFAULT nextval(('"public"."etapadeproduccion_idetapaproduccion_seq"'::text)::regclass) NOT NULL,
    nroetapaproduccion bigint,
    nombre character varying(50),
    horasmaquina time without time zone,
    horashombre time without time zone,
    tipomaquina bigint,
    duracionestimada time without time zone,
    fechacreacion date,
    unidaddemedida bigint
) WITH OIDS;
--
-- Structure for table ejecucionplanificacionproduccion (OID = 128829) : 
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
-- Structure for table procesocalidad (OID = 128836) : 
--
CREATE TABLE public.procesocalidad (
    idprocesocalidad bigint DEFAULT nextval(('"public"."procesocalidad_idprocesocalidad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    nroproceso bigint,
    especificacion character varying,
    tolerancia character varying,
    descripcion character varying,
    duracionestimada time without time zone,
    fechacreacion date,
    herramienta character varying,
    accioncalidad bigint
) WITH OIDS;
--
-- Structure for table ejecucionplanificacioncalidad (OID = 128840) : 
--
CREATE TABLE public.ejecucionplanificacioncalidad (
    idejecucion bigint DEFAULT nextval(('"public"."ejecucionplanificacioncalidad_idejecucion_seq"'::text)::regclass) NOT NULL,
    idplanificacioncalidad bigint,
    fechainicio date,
    fechafin date,
    horainicio time without time zone,
    horafin time without time zone,
    estado bigint,
    nroejecucionplanificacioncalidad bigint,
    novedades character varying
) WITH OIDS;
--
-- Structure for table ejecucionprocesocalidad (OID = 128847) : 
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
    observacion character varying,
    empleado bigint,
    maquina bigint
) WITH OIDS;
--
-- Structure for table ejecucionetapaproduccion (OID = 128854) : 
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
    observaciones character varying,
    estado bigint,
    nroejecucion bigint NOT NULL,
    maquina bigint
) WITH OIDS;
--
-- Structure for table compra (OID = 128861) : 
--
CREATE TABLE public.compra (
    idcompra bigint DEFAULT nextval(('"public"."compra_idcompra_seq"'::text)::regclass) NOT NULL,
    nrocompra bigint,
    fechacompra date,
    vigencia date,
    documentoremito bigint,
    preciototal double precision,
    estado bigint,
    motivo character varying,
    proveedor bigint
) WITH OIDS;
--
-- Structure for table detallemantenimientocorrectivo (OID = 128868) : 
--
CREATE TABLE public.detallemantenimientocorrectivo (
    idmantenimientocorrectivo bigint,
    iddetalle bigint DEFAULT nextval(('"public"."detallemantenimientocorrectivo_iddetalle_seq"'::text)::regclass) NOT NULL,
    rotura bigint,
    motivorotura character varying,
    duracion integer
) WITH OIDS;
--
-- Structure for table detallemantenimientopreventivo (OID = 128875) : 
--
CREATE TABLE public.detallemantenimientopreventivo (
    idmantenimientopreventivo bigint NOT NULL,
    iddetalle bigint DEFAULT nextval(('"public"."detallemantenimientopreventivo_iddetalle_seq"'::text)::regclass) NOT NULL,
    servicio bigint,
    observaciones character varying,
    duracion integer
) WITH OIDS;
--
-- Structure for table detalleejecucionplanificacion (OID = 128882) : 
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
-- Structure for table detalleejecucionplanificacioncalidad (OID = 128886) : 
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
-- Structure for table detalleplanificacionproduccion (OID = 128890) : 
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
    indexproducto integer,
    detalleanterior bigint,
    indexpieza integer
) WITH OIDS;
--
-- Structure for table detalleproducto (OID = 128894) : 
--
CREATE TABLE public.detalleproducto (
    iddetalle bigint DEFAULT nextval(('"public"."detalleproducto_iddetalle_seq"'::text)::regclass) NOT NULL,
    idproducto bigint NOT NULL,
    cantidadpiezas integer,
    descripcion character varying(50),
    pieza bigint NOT NULL
) WITH OIDS;
--
-- Structure for table detallepedido (OID = 128898) : 
--
CREATE TABLE public.detallepedido (
    iddetalle bigint DEFAULT nextval(('"public"."detallepedido_iddetalle_seq"'::text)::regclass) NOT NULL,
    idpedido bigint NOT NULL,
    precio double precision,
    cantidad integer,
    producto bigint
) WITH OIDS;
--
-- Structure for table detalletrabajotercerizado (OID = 128902) : 
--
CREATE TABLE public.detalletrabajotercerizado (
    iddetalle bigint DEFAULT nextval(('"public"."detalletrabajotercerizado_iddetalle_seq"'::text)::regclass) NOT NULL,
    idtrabajotercerizado bigint NOT NULL,
    montoparcial double precision,
    cantidad integer,
    descripcion character varying,
    fechaenvioreal date,
    fechaentregareal date,
    pieza bigint,
    proceso bigint,
    estado bigint
) WITH OIDS;
--
-- Structure for table detalleplanificacioncalidad (OID = 128909) : 
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
    orden integer,
    detalleanterior bigint,
    indexproducto integer,
    indexpieza integer
) WITH OIDS;
--
-- Structure for table detallecompra (OID = 128913) : 
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
-- Structure for table detalleremito (OID = 128917) : 
--
CREATE TABLE public.detalleremito (
    iddetalle bigint DEFAULT nextval(('"public"."detalleremito_iddetalle_seq"'::text)::regclass) NOT NULL,
    idremito bigint NOT NULL,
    cantidad integer,
    descripcion character varying,
    producto bigint
) WITH OIDS;
--
-- Structure for table tiporeclamo (OID = 128924) : 
--
CREATE TABLE public.tiporeclamo (
    idtiporeclamo bigint DEFAULT nextval(('"public"."tiporeclamo_idtiporeclamo_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Structure for table reclamoempresametalurgica (OID = 128931) : 
--
CREATE TABLE public.reclamoempresametalurgica (
    idreclamo bigint DEFAULT nextval(('"public"."reclamoempresametalurgica_idreclamo_seq"'::text)::regclass) NOT NULL,
    nroreclamo bigint,
    tiporeclamo bigint,
    motivo character varying,
    fechareclamo date,
    trabajotercerizado bigint,
    idestadoreclamo bigint
) WITH OIDS;
--
-- Structure for table reclamoproveedor (OID = 128938) : 
--
CREATE TABLE public.reclamoproveedor (
    idreclamo bigint DEFAULT nextval(('"public"."reclamoproveedor_idreclamo_seq"'::text)::regclass) NOT NULL,
    nroreclamo bigint,
    tiporeclamo bigint,
    motivo character varying,
    fechareclamo date,
    compra bigint,
    idestadoreclamo bigint
) WITH OIDS;
--
-- Structure for table reclamocliente (OID = 128945) : 
--
CREATE TABLE public.reclamocliente (
    idreclamo bigint DEFAULT nextval(('"public"."reclamocliente_idreclamo_seq"'::text)::regclass) NOT NULL,
    nroreclamo bigint,
    tiporeclamo bigint,
    motivo character varying,
    fechareclamo date,
    cliente bigint,
    idestadoreclamo bigint
) WITH OIDS;
--
-- Structure for table detallereclamocliente (OID = 128952) : 
--
CREATE TABLE public.detallereclamocliente (
    iddetalle bigint DEFAULT nextval(('"public"."detallereclamocliente_iddetalle_seq"'::text)::regclass) NOT NULL,
    idreclamo bigint NOT NULL,
    cantidad integer,
    descripcion character varying,
    motivo character varying,
    producto bigint
) WITH OIDS;
--
-- Structure for table detallereclamoproveedor (OID = 128959) : 
--
CREATE TABLE public.detallereclamoproveedor (
    iddetalle bigint DEFAULT nextval(('"public"."detallereclamoproveedor_iddetalle_seq"'::text)::regclass) NOT NULL,
    idreclamo bigint NOT NULL,
    cantidad integer,
    descripcion character varying,
    motivo character varying,
    iddetallecompra bigint,
    fechaegreso date,
    idcompra bigint
) WITH OIDS;
--
-- Structure for table detallereclamoempresametalurgica (OID = 128966) : 
--
CREATE TABLE public.detallereclamoempresametalurgica (
    iddetalle bigint DEFAULT nextval(('"public"."detallereclamoempresametalurgica_iddetalle_seq"'::text)::regclass) NOT NULL,
    idreclamo bigint NOT NULL,
    cantidad integer,
    descripcion character varying,
    motivo character varying,
    pieza bigint,
    fechaegreso date
) WITH OIDS;
--
-- Structure for table proveedorxmateriaprima (OID = 128973) : 
--
CREATE TABLE public.proveedorxmateriaprima (
    idproveedor bigint NOT NULL,
    idmateriaprima bigint NOT NULL,
    precio double precision
) WITH OIDS;
--
-- Structure for table maquinaxejecucionetapaproduccion (OID = 128976) : 
--
CREATE TABLE public.maquinaxejecucionetapaproduccion (
    idejecucionetapaproduccion bigint NOT NULL,
    idetapaproduccion bigint NOT NULL,
    idmaquina bigint NOT NULL,
    horasmaquina time without time zone,
    horashombre time without time zone
) WITH OIDS;
--
-- Structure for table maquinaxprocesocalidad (OID = 128979) : 
--
CREATE TABLE public.maquinaxprocesocalidad (
    idprocesocalidad bigint NOT NULL,
    idmaquina bigint NOT NULL,
    duracion time without time zone,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table piezaxetapadeproduccion (OID = 128985) : 
--
CREATE TABLE public.piezaxetapadeproduccion (
    idpieza bigint NOT NULL,
    idetapaproduccion bigint NOT NULL,
    duracion time without time zone,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table empleadoxturno (OID = 128991) : 
--
CREATE TABLE public.empleadoxturno (
    idempleado bigint NOT NULL,
    idturno bigint NOT NULL
) WITH OIDS;
--
-- Structure for table provincia (OID = 128994) : 
--
CREATE TABLE public.provincia (
    idprovincia bigint DEFAULT nextval(('"public"."provincia_idprovincia_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50)
) WITH OIDS;
--
-- Structure for table localidad (OID = 128998) : 
--
CREATE TABLE public.localidad (
    idlocalidad bigint DEFAULT nextval(('"public"."localidad_idlocalidad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    provincia bigint
) WITH OIDS;
--
-- Structure for table barrio (OID = 129002) : 
--
CREATE TABLE public.barrio (
    idbarrio bigint DEFAULT nextval(('"public"."barrio_idbarrio_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    codpostal bigint,
    localidad bigint
) WITH OIDS;
--
-- Structure for table usuarioxrol (OID = 129006) : 
--
CREATE TABLE public.usuarioxrol (
    idrol bigint NOT NULL,
    idusuario bigint NOT NULL
) WITH OIDS;
--
-- Structure for table rolxprivilegio (OID = 129009) : 
--
CREATE TABLE public.rolxprivilegio (
    idrol bigint NOT NULL,
    idprivilegio bigint NOT NULL
) WITH OIDS;
--
-- Structure for table planrequerimientosmateriaprima (OID = 129012) : 
--
CREATE TABLE public.planrequerimientosmateriaprima (
    idplanrequerimientosmateriaprima bigint DEFAULT nextval(('"public"."planrequerimientosmateriaprima_idplanrequerimientosmateriaprima_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table planprocedimientos (OID = 129016) : 
--
CREATE TABLE public.planprocedimientos (
    idplanprocedimientos bigint DEFAULT nextval(('"public"."planprocedimientos_idplanprocedimientos_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table planprocesoscalidad (OID = 129020) : 
--
CREATE TABLE public.planprocesoscalidad (
    idplanprocesoscalidad bigint DEFAULT nextval(('"public"."planprocesoscalidad_idplanprocesoscalidad_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table detallerequerimientosmateriaprima (OID = 129024) : 
--
CREATE TABLE public.detallerequerimientosmateriaprima (
    iddetalle bigint DEFAULT nextval(('"public"."detallerequerimientosmateriaprima_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanrequerimientosmateriaprima bigint NOT NULL,
    cantidadmateriaprima integer,
    idpieza bigint,
    idmateriaprima bigint
) WITH OIDS;
--
-- Structure for table detalleplanprocedimientos (OID = 129028) : 
--
CREATE TABLE public.detalleplanprocedimientos (
    iddetalle bigint DEFAULT nextval(('"public"."detalleplanprocedimientos_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanpprocedimientos bigint NOT NULL,
    idpieza bigint,
    idetapaproduccion bigint,
    duracionestimada time without time zone
) WITH OIDS;
--
-- Structure for table detalleplanprocesoscalidad (OID = 129032) : 
--
CREATE TABLE public.detalleplanprocesoscalidad (
    iddetalle bigint DEFAULT nextval(('"public"."detalleplanprocesoscalidad_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanprocesoscalidad bigint NOT NULL,
    idpieza bigint,
    idprocesocalidad bigint,
    duracionestimada time without time zone
) WITH OIDS;
--
-- Structure for table pedidomatriz (OID = 129036) : 
--
CREATE TABLE public.pedidomatriz (
    idpedidomatriz bigint DEFAULT nextval(('"public"."pedidomatriz_idpedidomatriz_seq"'::text)::regclass) NOT NULL,
    nropedidomatriz bigint,
    fechapedidomatriz date,
    idmatriz bigint,
    observaciones character varying
) WITH OIDS;
--
-- Structure for table estadodetallecompra (OID = 129043) : 
--
CREATE TABLE public.estadodetallecompra (
    idestado bigint DEFAULT nextval(('"public"."estadodetallecompra_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Structure for table estadodetalletrabajotercerizado (OID = 129050) : 
--
CREATE TABLE public.estadodetalletrabajotercerizado (
    idestado bigint DEFAULT nextval(('"public"."estadodetalletrabajotercerizado_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Structure for table estadoejecucionprocesocalidad (OID = 129057) : 
--
CREATE TABLE public.estadoejecucionprocesocalidad (
    idestado bigint DEFAULT nextval(('"public"."estadoejecucionprocesocalidad_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Structure for table detalleproductoreal (OID = 129064) : 
--
CREATE TABLE public.detalleproductoreal (
    iddetalle bigint DEFAULT nextval(('"public"."detalleproductoreal_iddetalle_seq"'::text)::regclass) NOT NULL,
    idproductoreal bigint NOT NULL,
    "cantidadPiezas" integer,
    descripcion character varying,
    idpiezareal bigint,
    detalleproducto bigint
) WITH OIDS;
--
-- Structure for table productoreal (OID = 129071) : 
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
-- Structure for table estadoproductoreal (OID = 129075) : 
--
CREATE TABLE public.estadoproductoreal (
    idestado bigint DEFAULT nextval(('"public"."estadoproductoreal_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Structure for table detallefactura (OID = 129082) : 
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
-- Structure for table detallepresupuesto (OID = 129086) : 
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
-- Structure for table estadoremito (OID = 129090) : 
--
CREATE TABLE public.estadoremito (
    idestado bigint DEFAULT nextval(('"public"."estadoremito_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Structure for table tipomaterial (OID = 129097) : 
--
CREATE TABLE public.tipomaterial (
    idtipomaterial bigint DEFAULT nextval(('"public"."tipomaterial_idtipomaterial_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Definition for sequence prueba_id_seq (OID = 129104) : 
--
CREATE SEQUENCE public.prueba_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table prueba (OID = 129106) : 
--
CREATE TABLE public.prueba (
    id bigint DEFAULT nextval('prueba_id_seq'::regclass) NOT NULL,
    valor character varying(20)
) WITH OIDS;
ALTER TABLE ONLY public.prueba ALTER COLUMN id SET STATISTICS 0;
ALTER TABLE ONLY public.prueba ALTER COLUMN valor SET STATISTICS 0;
--
-- Definition for sequence usuario_idusuario_seq (OID = 129110) : 
--
CREATE SEQUENCE public.usuario_idusuario_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipomaterial_idtipomaterial_seq (OID = 129112) : 
--
CREATE SEQUENCE public.tipomaterial_idtipomaterial_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence accioncalidad_idaccioncalidad_seq (OID = 129114) : 
--
CREATE SEQUENCE public.accioncalidad_idaccioncalidad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence barrio_idbarrio_seq (OID = 129116) : 
--
CREATE SEQUENCE public.barrio_idbarrio_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence cargo_idcargo_seq (OID = 129118) : 
--
CREATE SEQUENCE public.cargo_idcargo_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence categoria_idcategoria_seq (OID = 129120) : 
--
CREATE SEQUENCE public.categoria_idcategoria_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence cliente_idcliente_seq (OID = 129122) : 
--
CREATE SEQUENCE public.cliente_idcliente_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence codigodebarra_idcodigo_seq (OID = 129124) : 
--
CREATE SEQUENCE public.codigodebarra_idcodigo_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence compra_idcompra_seq (OID = 129126) : 
--
CREATE SEQUENCE public.compra_idcompra_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence comprobantepago_idcomprobantepago_seq (OID = 129128) : 
--
CREATE SEQUENCE public.comprobantepago_idcomprobantepago_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence condicioniva_idcondicioniva_seq (OID = 129130) : 
--
CREATE SEQUENCE public.condicioniva_idcondicioniva_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanificacionproduccion_id_seq (OID = 129132) : 
--
CREATE SEQUENCE public.detalleplanificacionproduccion_id_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallecompra_iddetalle_seq (OID = 129134) : 
--
CREATE SEQUENCE public.detallecompra_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleejecucionplanificacion_iddetalle_seq (OID = 129136) : 
--
CREATE SEQUENCE public.detalleejecucionplanificacion_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleejecucionplanificacioncalidad_iddetalle_seq (OID = 129138) : 
--
CREATE SEQUENCE public.detalleejecucionplanificacioncalidad_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallefactura_iddetalle_seq (OID = 129140) : 
--
CREATE SEQUENCE public.detallefactura_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallemantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 129142) : 
--
CREATE SEQUENCE public.detallemantenimientocorrectivo_idmantenimientocorrectivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallemantenimientopreventivo_idmantenimientopreventivo_seq (OID = 129144) : 
--
CREATE SEQUENCE public.detallemantenimientopreventivo_idmantenimientopreventivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallepedido_iddetalle_seq (OID = 129146) : 
--
CREATE SEQUENCE public.detallepedido_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanificacioncalidad_iddetalle_seq (OID = 129148) : 
--
CREATE SEQUENCE public.detalleplanificacioncalidad_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanprocedimientos_iddetalle_seq (OID = 129150) : 
--
CREATE SEQUENCE public.detalleplanprocedimientos_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanprocesoscalidad_iddetalle_seq (OID = 129152) : 
--
CREATE SEQUENCE public.detalleplanprocesoscalidad_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallepresupuesto_iddetalle_seq (OID = 129154) : 
--
CREATE SEQUENCE public.detallepresupuesto_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleproducto_iddetalle_seq (OID = 129156) : 
--
CREATE SEQUENCE public.detalleproducto_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleproductoreal_iddetalle_seq (OID = 129158) : 
--
CREATE SEQUENCE public.detalleproductoreal_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamocliente_iddetalle_seq (OID = 129160) : 
--
CREATE SEQUENCE public.detallereclamocliente_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamoempresametalurgica_iddetalle_seq (OID = 129162) : 
--
CREATE SEQUENCE public.detallereclamoempresametalurgica_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamoproveedor_iddetalle_seq (OID = 129164) : 
--
CREATE SEQUENCE public.detallereclamoproveedor_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleremito_iddetalle_seq (OID = 129166) : 
--
CREATE SEQUENCE public.detalleremito_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallerequerimientosmateriaprima_iddetalle_seq (OID = 129168) : 
--
CREATE SEQUENCE public.detallerequerimientosmateriaprima_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalletrabajotercerizado_iddetalle_seq (OID = 129170) : 
--
CREATE SEQUENCE public.detalletrabajotercerizado_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence domicilio_iddomicilio_seq (OID = 129172) : 
--
CREATE SEQUENCE public.domicilio_iddomicilio_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionetapaproduccion_idejecucion_seq (OID = 129174) : 
--
CREATE SEQUENCE public.ejecucionetapaproduccion_idejecucion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionplanificacioncalidad_idejecucion_seq (OID = 129176) : 
--
CREATE SEQUENCE public.ejecucionplanificacioncalidad_idejecucion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionplanificacionproduccion_idejecucion_seq (OID = 129178) : 
--
CREATE SEQUENCE public.ejecucionplanificacionproduccion_idejecucion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionprocesocalidad_idejecucion_seq (OID = 129180) : 
--
CREATE SEQUENCE public.ejecucionprocesocalidad_idejecucion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence empleado_idempleado_seq (OID = 129182) : 
--
CREATE SEQUENCE public.empleado_idempleado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence empresametalurgica_idempresametalurgica_seq (OID = 129184) : 
--
CREATE SEQUENCE public.empresametalurgica_idempresametalurgica_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadocliente_idestado_seq (OID = 129186) : 
--
CREATE SEQUENCE public.estadocliente_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadocompra_idestado_seq (OID = 129188) : 
--
CREATE SEQUENCE public.estadocompra_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadodetallecompra_idestado_seq (OID = 129190) : 
--
CREATE SEQUENCE public.estadodetallecompra_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadodetalletrabajotercerizado_idestado_seq (OID = 129192) : 
--
CREATE SEQUENCE public.estadodetalletrabajotercerizado_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecetapaprod_idestado_seq (OID = 129194) : 
--
CREATE SEQUENCE public.estadoejecetapaprod_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecplancalidad_idestado_seq (OID = 129196) : 
--
CREATE SEQUENCE public.estadoejecplancalidad_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecplanifpedido_idestado_seq (OID = 129198) : 
--
CREATE SEQUENCE public.estadoejecplanifpedido_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecucionprocesocalidad_idestado_seq (OID = 129200) : 
--
CREATE SEQUENCE public.estadoejecucionprocesocalidad_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadofactura_idestado_seq (OID = 129202) : 
--
CREATE SEQUENCE public.estadofactura_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadomaquina_idestado_seq (OID = 129204) : 
--
CREATE SEQUENCE public.estadomaquina_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadopedido_idestado_seq (OID = 129206) : 
--
CREATE SEQUENCE public.estadopedido_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadopiezareal_idestado_seq (OID = 129208) : 
--
CREATE SEQUENCE public.estadopiezareal_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoproductoreal_idestado_seq (OID = 129210) : 
--
CREATE SEQUENCE public.estadoproductoreal_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoremito_idestado_seq (OID = 129212) : 
--
CREATE SEQUENCE public.estadoremito_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadotrabajotercerizado_idestado_seq (OID = 129214) : 
--
CREATE SEQUENCE public.estadotrabajotercerizado_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence etapadeproduccion_idetapaproduccion_seq (OID = 129216) : 
--
CREATE SEQUENCE public.etapadeproduccion_idetapaproduccion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence factura_idfactura_seq (OID = 129218) : 
--
CREATE SEQUENCE public.factura_idfactura_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence formadepago_idformapago_seq (OID = 129220) : 
--
CREATE SEQUENCE public.formadepago_idformapago_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence localidad_idlocalidad_seq (OID = 129222) : 
--
CREATE SEQUENCE public.localidad_idlocalidad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence mantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 129224) : 
--
CREATE SEQUENCE public.mantenimientocorrectivo_idmantenimientocorrectivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence mantenimientopreventivo_idmantenimientopreventivo_seq (OID = 129226) : 
--
CREATE SEQUENCE public.mantenimientopreventivo_idmantenimientopreventivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence maquina_idmaquina_seq (OID = 129228) : 
--
CREATE SEQUENCE public.maquina_idmaquina_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence marca_idmarca_seq (OID = 129230) : 
--
CREATE SEQUENCE public.marca_idmarca_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence materiaprima_idmateriaprima_seq (OID = 129232) : 
--
CREATE SEQUENCE public.materiaprima_idmateriaprima_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence matriz_idmatriz_seq (OID = 129234) : 
--
CREATE SEQUENCE public.matriz_idmatriz_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pedido_idpedido_seq (OID = 129236) : 
--
CREATE SEQUENCE public.pedido_idpedido_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pedidomatriz_idpedidomatriz_seq (OID = 129238) : 
--
CREATE SEQUENCE public.pedidomatriz_idpedidomatriz_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pieza_idpieza_seq (OID = 129240) : 
--
CREATE SEQUENCE public.pieza_idpieza_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence piezareal_idpiezareal_seq (OID = 129242) : 
--
CREATE SEQUENCE public.piezareal_idpiezareal_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planificacioncalidad_idplanificacion_seq (OID = 129244) : 
--
CREATE SEQUENCE public.planificacioncalidad_idplanificacion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planificacionproduccion_idplanificacionproduccion_seq (OID = 129246) : 
--
CREATE SEQUENCE public.planificacionproduccion_idplanificacionproduccion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence plano_idplano_seq (OID = 129248) : 
--
CREATE SEQUENCE public.plano_idplano_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planprocedimientos_idplanprocedimientos_seq (OID = 129250) : 
--
CREATE SEQUENCE public.planprocedimientos_idplanprocedimientos_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planprocesoscalidad_idplanprocesoscalidad_seq (OID = 129252) : 
--
CREATE SEQUENCE public.planprocesoscalidad_idplanprocesoscalidad_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planrequerimientosmateriaprima_idplanrequerimientosmateriaprima (OID = 129254) : 
--
CREATE SEQUENCE public.planrequerimientosmateriaprima_idplanrequerimientosmateriaprima
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence presupuesto_idpresupuesto_seq (OID = 129256) : 
--
CREATE SEQUENCE public.presupuesto_idpresupuesto_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence prioridad_idprioridad_seq (OID = 129258) : 
--
CREATE SEQUENCE public.prioridad_idprioridad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence privilegio_idprivilegio_seq (OID = 129260) : 
--
CREATE SEQUENCE public.privilegio_idprivilegio_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence procesocalidad_idprocesocalidad_seq (OID = 129262) : 
--
CREATE SEQUENCE public.procesocalidad_idprocesocalidad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence producto_idproducto_seq (OID = 129264) : 
--
CREATE SEQUENCE public.producto_idproducto_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence productoreal_idproductoreal_seq (OID = 129266) : 
--
CREATE SEQUENCE public.productoreal_idproductoreal_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence proveedor_idproveedor_seq (OID = 129268) : 
--
CREATE SEQUENCE public.proveedor_idproveedor_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence proveedormantenimientomaquina_idproveedormantenimiento_seq (OID = 129270) : 
--
CREATE SEQUENCE public.proveedormantenimientomaquina_idproveedormantenimiento_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence provincia_idprovincia_seq (OID = 129272) : 
--
CREATE SEQUENCE public.provincia_idprovincia_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamocliente_idreclamo_seq (OID = 129274) : 
--
CREATE SEQUENCE public.reclamocliente_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamoempresametalurgica_idreclamo_seq (OID = 129276) : 
--
CREATE SEQUENCE public.reclamoempresametalurgica_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamoproveedor_idreclamo_seq (OID = 129278) : 
--
CREATE SEQUENCE public.reclamoproveedor_idreclamo_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence remito_idremito_seq (OID = 129280) : 
--
CREATE SEQUENCE public.remito_idremito_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence responsable_idresponsable_seq (OID = 129282) : 
--
CREATE SEQUENCE public.responsable_idresponsable_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence rol_idrol_seq (OID = 129284) : 
--
CREATE SEQUENCE public.rol_idrol_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence rotura_idrotura_seq (OID = 129286) : 
--
CREATE SEQUENCE public.rotura_idrotura_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence servicio_idservicio_seq (OID = 129288) : 
--
CREATE SEQUENCE public.servicio_idservicio_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence sesion_idsesion_seq (OID = 129290) : 
--
CREATE SEQUENCE public.sesion_idsesion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipodocumento_idtipodocumento_seq (OID = 129292) : 
--
CREATE SEQUENCE public.tipodocumento_idtipodocumento_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipoiva_idtipoiva_seq (OID = 129294) : 
--
CREATE SEQUENCE public.tipoiva_idtipoiva_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipomaquina_idtipomaquina_seq (OID = 129296) : 
--
CREATE SEQUENCE public.tipomaquina_idtipomaquina_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tiporeclamo_idtiporeclamo_seq (OID = 129298) : 
--
CREATE SEQUENCE public.tiporeclamo_idtiporeclamo_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence trabajotercerizado_idtrabajo_seq (OID = 129300) : 
--
CREATE SEQUENCE public.trabajotercerizado_idtrabajo_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence turno_idturno_seq (OID = 129302) : 
--
CREATE SEQUENCE public.turno_idturno_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for view viewdetallepedidocotizacion (OID = 129304) : 
--
CREATE VIEW public.viewdetallepedidocotizacion AS
SELECT p.nroproducto, p.nombre, p.descripcion, dp.cantidad,
    p.preciounitario AS precio, p.idproducto, dp.iddetalle, ped.idpedido
FROM producto p, pedido ped, detallepedido dp
WHERE ((dp.producto = p.idproducto) AND (ped.idpedido = dp.idpedido))
ORDER BY p.nombre;

--
-- Definition for view viewpedidoendetalleprocedimientos (OID = 129308) : 
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
-- Definition for sequence unidadmedida_idunidadmedida_seq (OID = 129312) : 
--
CREATE SEQUENCE public.unidadmedida_idunidadmedida_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table unidadmedida (OID = 129314) : 
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
-- Definition for view viewetapadeproduccion (OID = 129318) : 
--
CREATE VIEW public.viewetapadeproduccion AS
SELECT ep.nroetapaproduccion AS numero, ep.nombre, ep.horashombre,
    ep.horasmaquina, ep.duracionestimada, ep.idetapaproduccion AS idetapa
FROM etapadeproduccion ep;

--
-- Definition for sequence detallepiezapresupuesto_iddetalle_seq (OID = 129322) : 
--
CREATE SEQUENCE public.detallepiezapresupuesto_iddetalle_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detallepiezapresupuesto (OID = 129324) : 
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
-- Definition for sequence detalleproductopresupuesto_iddetalle_seq (OID = 129328) : 
--
CREATE SEQUENCE public.detalleproductopresupuesto_iddetalle_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detalleproductopresupuesto (OID = 129330) : 
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
-- Definition for sequence detallepiezacalidadpresupuesto_iddetalle_seq (OID = 129334) : 
--
CREATE SEQUENCE public.detallepiezacalidadpresupuesto_iddetalle_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detallepiezacalidadpresupuesto (OID = 129336) : 
--
CREATE TABLE public.detallepiezacalidadpresupuesto (
    iddetalle bigint DEFAULT nextval('detallepiezacalidadpresupuesto_iddetalle_seq'::regclass) NOT NULL,
    cantprocesocalidad integer,
    duracionxpieza time without time zone,
    idprocesocalidad bigint,
    iddetalleproductopresupuesto bigint
) WITH OIDS;
--
-- Definition for view viewproveedorxmateriaprima (OID = 129344) : 
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
-- Definition for view viewmateriaprima (OID = 129349) : 
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
-- Definition for view viewproductopresupuesto (OID = 129353) : 
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
-- Definition for sequence calendario_id_seq (OID = 129357) : 
--
CREATE SEQUENCE public.calendario_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table calendario (OID = 129359) : 
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
-- Definition for view viewpedidosnoconfirmados (OID = 129363) : 
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
-- Definition for sequence disponibilidadhoraria_id_seq (OID = 129367) : 
--
CREATE SEQUENCE public.disponibilidadhoraria_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table disponibilidadhoraria (OID = 129369) : 
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
-- Definition for sequence estadoplanificacionproduccion_id_seq (OID = 129373) : 
--
CREATE SEQUENCE public.estadoplanificacionproduccion_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table estadoplanificacionproduccion (OID = 129375) : 
--
CREATE TABLE public.estadoplanificacionproduccion (
    id bigint DEFAULT nextval('estadoplanificacionproduccion_id_seq'::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying(250)
) WITH OIDS;
--
-- Definition for sequence maquina_idmaquina_seq1 (OID = 129382) : 
--
CREATE SEQUENCE public.maquina_idmaquina_seq1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table maquina (OID = 129384) : 
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
-- Definition for sequence piezareal_idpiezareal_seq1 (OID = 129388) : 
--
CREATE SEQUENCE public.piezareal_idpiezareal_seq1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table piezareal (OID = 129390) : 
--
CREATE TABLE public.piezareal (
    idpiezareal bigint DEFAULT nextval('piezareal_idpiezareal_seq1'::regclass) NOT NULL,
    idpieza bigint NOT NULL,
    estado bigint,
    nropieza integer,
    idcodigobarra bigint
) WITH OIDS;
--
-- Definition for sequence pieza_idpieza_seq1 (OID = 129394) : 
--
CREATE SEQUENCE public.pieza_idpieza_seq1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table pieza (OID = 129396) : 
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
-- Definition for sequence detallempasignada_id_seq (OID = 129400) : 
--
CREATE SEQUENCE public.detallempasignada_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detallempasignada (OID = 129402) : 
--
CREATE TABLE public.detallempasignada (
    id bigint DEFAULT nextval('detallempasignada_id_seq'::regclass) NOT NULL,
    idmateriaprima bigint,
    cantidadmp integer,
    idplanificacionproduccion bigint
) WITH OIDS;
--
-- Definition for sequence mpasignadaxpiezareal_id_seq (OID = 129406) : 
--
CREATE SEQUENCE public.mpasignadaxpiezareal_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table mpasignadaxpiezareal (OID = 129408) : 
--
CREATE TABLE public.mpasignadaxpiezareal (
    idpiezareal bigint,
    iddetallempasignada bigint,
    id bigint DEFAULT nextval('mpasignadaxpiezareal_id_seq'::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table asistencia (OID = 129412) : 
--
CREATE TABLE public.asistencia (
    empleado bigint NOT NULL,
    fechaingreso date NOT NULL,
    horaingreso time without time zone NOT NULL,
    horaegreso time without time zone,
    observaciones character varying(100)
) WITH OIDS;
--
-- Definition for view viewmpxpiezapresupuesto (OID = 129415) : 
--
CREATE VIEW public.viewmpxpiezapresupuesto AS
SELECT pro.nroproducto, pro.nombre AS nombreproducto, dp.cantidad AS
    cantproducto, pi.nombre AS nombrepieza, dpropre.cantpiezas AS
    cantpieza, mp.nombre AS nombremateriaprima, dpropre.preciomateriaprima,
    dpropre.cantmateriaprima, ((dpropre.cantmateriaprima *
    dpropre.cantpiezas) * dp.cantidad) AS canttotal,
    ((dpropre.preciomateriaprima * (dpropre.cantmateriaprima)::double
    precision) * (dp.cantidad)::double precision) AS preciototal,
    p.idpresupuesto, dp.iddetalle AS iddetallepresupuesto,
    dpropre.iddetalle AS iddetalleproductopresupuesto, pro.idproducto,
    pi.idpieza, mp.idmateriaprima, dpropre.idproveedor
FROM presupuesto p, detallepresupuesto dp, detalleproductopresupuesto
    dpropre, producto pro, pieza pi, materiaprima mp
WHERE (((((p.idpresupuesto = dp.idpresupuesto) AND (dp.idproducto =
    pro.idproducto)) AND (dp.iddetalle = dpropre.iddetallepresupuesto)) AND
    (dpropre.idpieza = pi.idpieza)) AND (dpropre.idmateriaprima =
    mp.idmateriaprima));

--
-- Definition for view viewetapasxpiezapresupuesto (OID = 129419) : 
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
-- Definition for view viewprocalidadxpiezapresupesto (OID = 129423) : 
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
-- Definition for view viewpedidosconmpasignada (OID = 129428) : 
--
CREATE VIEW public.viewpedidosconmpasignada AS
SELECT ped.nropedido, planpro.nroplanificacion AS
    nroplanificacionproduccion, planpro.fechacreacion,
    planpro.fechainicioprevista, planpro.fechafinprevista,
    planpro.observaciones, ped.idpedido, planpro.idplanificacionproduccion
FROM pedido ped, planificacionproduccion planpro
WHERE ((ped.idpedido = planpro.pedido) AND (ped.estado = 3));

--
-- Definition for view viewdetalleproducto (OID = 129432) : 
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
-- Definition for view viewpedidosconplanifsinrecursos (OID = 129436) : 
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
-- Definition for view viewpedidosclientesegunestado (OID = 129441) : 
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
-- Definition for sequence reclamoempresamantenimiento_idreclamo_seq (OID = 129445) : 
--
CREATE SEQUENCE public.reclamoempresamantenimiento_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table reclamoempresamantenimiento (OID = 129447) : 
--
CREATE TABLE public.reclamoempresamantenimiento (
    idreclamo bigint DEFAULT nextval('reclamoempresamantenimiento_idreclamo_seq'::regclass) NOT NULL,
    nroreclamo bigint,
    tiporeclamo bigint,
    motivo character varying(50),
    fechareclamo date,
    trabajotercerizado bigint,
    idestadoreclamo bigint,
    mantenimientopreventivo bigint,
    mantenimientocorrectivo bigint
) WITH OIDS;
--
-- Definition for sequence detallereclamoempresamantenimiento_iddetalle_seq (OID = 129451) : 
--
CREATE SEQUENCE public.detallereclamoempresamantenimiento_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detallereclamoempresamantenimiento (OID = 129453) : 
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
-- Definition for sequence estadoreclamo_idestadoreclamo_seq (OID = 129457) : 
--
CREATE SEQUENCE public.estadoreclamo_idestadoreclamo_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table estadoreclamo (OID = 129459) : 
--
CREATE TABLE public.estadoreclamo (
    idestadoreclamo bigint DEFAULT nextval('estadoreclamo_idestadoreclamo_seq'::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(200)
) WITH OIDS;
--
-- Definition for view viewpedidosconrecasignados (OID = 129463) : 
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
-- Definition for view viewpedidosproduccionfin (OID = 129471) : 
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
-- Definition for sequence detallemantenimientopreventivo_iddetalle_seq (OID = 129475) : 
--
CREATE SEQUENCE public.detallemantenimientopreventivo_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table estadomantpreventivo (OID = 129477) : 
--
CREATE TABLE public.estadomantpreventivo (
    idestado bigint DEFAULT nextval(('"public"."estadomantpreventivo_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying
) WITH OIDS;
ALTER TABLE ONLY public.estadomantpreventivo ALTER COLUMN idestado SET STATISTICS 0;
ALTER TABLE ONLY public.estadomantpreventivo ALTER COLUMN nombre SET STATISTICS 0;
ALTER TABLE ONLY public.estadomantpreventivo ALTER COLUMN descripcion SET STATISTICS 0;
--
-- Definition for sequence estadomantpreventivo_idestado_seq (OID = 129484) : 
--
CREATE SEQUENCE public.estadomantpreventivo_idestado_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallemantenimientocorrectivo_iddetalle_seq (OID = 129486) : 
--
CREATE SEQUENCE public.detallemantenimientocorrectivo_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for view viewcantidadmpenpedido (OID = 129488) : 
--
CREATE VIEW public.viewcantidadmpenpedido AS
SELECT sum(((dp.cantidad * dpp.cantpiezas) * dpp.cantmateriaprima)) AS cantidad
FROM pedido pe, detalleproductopresupuesto dpp, presupuesto pre,
    detallepresupuesto dp
WHERE (((pe.presupuesto = pre.idpresupuesto) AND (dp.idpresupuesto =
    pre.idpresupuesto)) AND (dp.iddetalle = dpp.iddetallepresupuesto));

--
-- Definition for view viewcantidadmpasiganda (OID = 129492) : 
--
CREATE VIEW public.viewcantidadmpasiganda AS
SELECT sum(dmpa.cantidadmp) AS cantidad
FROM planificacionproduccion ppro, detallempasignada dmpa
WHERE (ppro.idplanificacionproduccion = dmpa.idplanificacionproduccion);

--
-- Definition for view viewpresupuestoparafactura (OID = 129496) : 
--
CREATE VIEW public.viewpresupuestoparafactura AS
SELECT p.nroproducto, p.nombre, p.descripcion, dpre.cantidad, dpre.precio,
    p.idproducto, dp.iddetalle, ped.idpedido, pre.idpresupuesto,
    dpre.iddetalle AS iddetallepresupuesto
FROM producto p, pedido ped, detallepedido dp, presupuesto pre,
    detallepresupuesto dpre
WHERE (((((dp.producto = p.idproducto) AND (ped.idpedido = dp.idpedido))
    AND (ped.presupuesto = pre.idpresupuesto)) AND (pre.idpresupuesto =
    dpre.idpresupuesto)) AND (dpre.idproducto = p.idproducto))
ORDER BY p.nombre;

--
-- Definition for view viewpiezarealparacalidad (OID = 129500) : 
--
CREATE VIEW public.viewpiezarealparacalidad AS
SELECT pir.nropieza, p.nropedido, pro.nroproducto, pir.idpiezareal,
    p.idpedido, pi.idpieza, pro.idproducto, dpp.indexpieza, dpp.indexproducto
FROM detalleejecucionplanificacion dep, detalleplanificacionproduccion dpp,
    ejecucionplanificacionproduccion epp, planificacionproduccion pp,
    pedido p, piezareal pir, pieza pi, producto pro
WHERE (((((((dep.id = dpp.iddetalleejecucionplanificacion) AND
    (dep.piezareal = pir.idpiezareal)) AND (dpp.idpieza = pi.idpieza)) AND
    (dpp.idproducto = pro.idproducto)) AND
    (dep.idejecucionplanificacionproduccion = epp.idejecucion)) AND
    (epp.idplanificacionproduccion = pp.idplanificacionproduccion)) AND
    (pp.pedido = p.idpedido))
GROUP BY p.nropedido, p.idpedido, pro.nroproducto, pir.nropieza,
    pir.idpiezareal, pi.idpieza, pro.idproducto, dpp.indexpieza, dpp.indexproducto;

--
-- Definition for view viewprocesocalidad (OID = 132006) : 
--
CREATE VIEW public.viewprocesocalidad AS
SELECT pc.nroproceso, pc.nombre AS nombreproceso, pc.duracionestimada,
    pc.herramienta, ac.nombre AS nombreaccioncalidad, pc.idprocesocalidad
FROM procesocalidad pc, accioncalidad ac
WHERE (pc.accioncalidad = ac.idaccioncalidad)
ORDER BY pc.nombre;

--
-- Definition for view viewpedidosconplanificacionproduccion (OID = 132013) : 
--
CREATE VIEW public.viewpedidosconplanificacionproduccion AS
SELECT pe.nropedido, pe.nropedidocotizacioncliente, cli.razonsocial,
    pri.nombre AS prioridad, pe.fechaentregaestipulada,
    pp.fechafinprevista, pe.fechapedidocotizacion AS fechapedido,
    pp.idplanificacionproduccion, pe.idpedido, cli.idcliente,
    pri.idprioridad, pe.presupuesto, pp.idestado
FROM pedido pe, planificacionproduccion pp, cliente cli, prioridad pri
WHERE ((((pe.idpedido = pp.pedido) AND (cli.idcliente = pe.cliente)) AND
    (pri.idprioridad = pe.prioridad)) AND (pe.estado = 5));

--
-- Data for table public.estadopedido (OID = 128531) (LIMIT 0,19)
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
-- Data for table public.cliente (OID = 128575) (LIMIT 0,8)
--
INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (1, 26, 3, 1, false, 16, 'Starbene', 33, '0351-4837482', '158376283', 'starbene@starbene.com.ar', 74, '2011-11-18', NULL, NULL, 1, '26-83773627-3');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (2, 27, 3, 1, false, 18, 'Benito Roggio', 34, '0351-4736283', '', 'benito.roggio@gmail.com', 76, '2011-11-18', NULL, NULL, 1, '27-3847629-8');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (3, 28, 3, 1, false, 19, 'Material Ferroviario', 35, '0351-3746283', '', 'materialferroviario@gmail.com', 78, '2011-11-18', NULL, NULL, 1, '26-39284758-6');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (4, 29, 3, 1, false, 20, 'Neverland', 36, '03525-384928', '15-398475', 'neverland@neverland.com.ar', 80, '2011-11-18', NULL, NULL, 1, '32-3849382-6');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (5, 30, 3, 1, false, 21, 'Tamse', 37, '0351-4837283', '', 'tamse@gmail.com', 82, '2011-11-18', NULL, NULL, 1, '26-38284928-6');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (6, 31, 3, 1, false, 22, 'Nemak Argentina', 38, '0352-393485', '', 'nemakargentina@gmail.com', 84, '2011-11-18', NULL, NULL, 1, '26-43849584-4');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (7, 32, 3, 1, false, 23, 'Ramondelli  y Aime SRL', 39, '0351-4837492', '', 'ramondelli@gmail.com', 86, '2011-11-18', NULL, NULL, 1, '26-37483928-6');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (8, 33, 3, 1, false, 24, 'Alberto Boretto', 40, '0351-4837283', '', 'boretto@gmail.com', 88, '2011-11-18', NULL, NULL, 1, '29-34958234-8');

--
-- Data for table public.formadepago (OID = 128589) (LIMIT 0,2)
--
INSERT INTO formadepago (idformapago, nombre, descripcion)
VALUES (1, 'CONTADO', 'EFECTIVO');

INSERT INTO formadepago (idformapago, nombre, descripcion)
VALUES (2, 'CTA CTE', 'Cuenta corriente');

--
-- Data for table public.usuario (OID = 128596) (LIMIT 0,16)
--
INSERT INTO usuario (idusuario, usuario, clave)
VALUES (1, 'admin', 'admin');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (16, 'Starbene', '26-83773627-3');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (18, 'Benito Roggio', '27-3847629-8');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (19, 'Material Ferroviario', '26-39284758-6');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (20, 'Neverland', '32-3849382-6');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (21, 'Tamse', '26-38284928-6');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (22, 'Nemak Argentina', '26-43849584-4');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (23, 'Ramondelli  y Aime SRL', '26-37483928-6');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (24, 'Alberto Boretto', '29-34958234-8');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (25, 'Riquelme', '28726384');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (26, 'Lopez', '24748392');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (27, 'Fernández', '27384374');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (28, 'Barale', '11904502');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (29, 'Aguilera', '33847362');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (30, 'Arganiaraz', '26372846');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (31, 'Rodriguez', '1234');

--
-- Data for table public.estadofactura (OID = 128603) (LIMIT 0,2)
--
INSERT INTO estadofactura (idestado, nombre, descripcion)
VALUES (1, 'GENERADO', 'GENERADO');

INSERT INTO estadofactura (idestado, nombre, descripcion)
VALUES (2, 'COBRADO', 'cobrado');

--
-- Data for table public.estadotrabajotercerizado (OID = 128614) (LIMIT 0,14)
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
-- Data for table public.empresametalurgica (OID = 128621) (LIMIT 0,4)
--
INSERT INTO empresametalurgica (idempresametalurgica, nroempresametalurgica, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (2, 1, 'Fabietti', 41, '0354-839482', '', 'fabietti@gmail.com', 91, '2011-11-19', NULL, NULL, 1, '26-38598384-7');

INSERT INTO empresametalurgica (idempresametalurgica, nroempresametalurgica, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (3, 2, 'Dantra', 42, '0351-58473847', '', 'dantra@gmail.com', 93, '2011-11-19', NULL, NULL, 1, '29-38475637-4');

INSERT INTO empresametalurgica (idempresametalurgica, nroempresametalurgica, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (4, 3, 'Sudosilo', 43, '03535-8349582', '', 'sudosilo@gmail.com', 95, '2011-11-19', NULL, NULL, 1, '28-83746382-5');

INSERT INTO empresametalurgica (idempresametalurgica, nroempresametalurgica, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (5, 4, 'Inducrom ', 44, '0351-4857384', '', 'inducrom@hotmail.com', 97, '2011-11-19', NULL, NULL, 1, '26-47582738-');

--
-- Data for table public.prioridad (OID = 128628) (LIMIT 0,3)
--
INSERT INTO prioridad (idprioridad, nombre, descripcion)
VALUES (1, 'Alta', 'Prioridad Alta');

INSERT INTO prioridad (idprioridad, nombre, descripcion)
VALUES (2, 'Baja', 'Prioridad Baja');

INSERT INTO prioridad (idprioridad, nombre, descripcion)
VALUES (3, 'Normal', 'Prioridad Normal');

--
-- Data for table public.estadocliente (OID = 128635) (LIMIT 0,3)
--
INSERT INTO estadocliente (idestado, nombre, descripcion)
VALUES (1, 'ACTIVO', 'Dado de Alta');

INSERT INTO estadocliente (idestado, nombre, descripcion)
VALUES (2, 'BAJA', 'Dado de Baja');

INSERT INTO estadocliente (idestado, nombre, descripcion)
VALUES (3, 'MOROSO', 'Cliente Moroso, adeuda facturas');

--
-- Data for table public.responsable (OID = 128642) (LIMIT 0,17)
--
INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (28, 'Alberto', 'Dominguez', '0351-2347612', 'a.dominguez@gmail.com', 65, 14222356, 1, '');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (29, 'Sergio', 'Alonzo', '0351-4562830', 's.alonzo@gmail.com', 67, 20398472, 1, '');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (30, 'Lucas Fernando', 'Riera', '0354-438291', 'lf.riera@gmail.com', 69, 26837948, 1, '');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (31, 'Anabel', 'Lisandro', '0351-4839284', 'a.lisandro@gmail.com', 71, 29387467, 1, '');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (32, 'Roberto', 'Hernández', '0351-4637827', 'r.hernandez@gmail.com', 73, 28938443, 1, '');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (33, 'Federico', 'Rubinstein', '0351-3928394', 'f.rubinstein@satarbene.com', 75, 30928748, 1, '');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (34, 'Benito', 'Roggio', '0351-4736283', 'benito.roggio@gmail.com', 77, 16456753, 1, '');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (35, 'Juan', 'Taselli', '0351-4839229', 'j.taselli@gmail.com', 79, 26182738, 1, '');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (36, 'Cristian', 'Delgado', '03525-374829', 'c.delgado@gmail.com', 81, 23414235, 1, '');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (37, 'Alejandro', 'Wuayar', '0351-2934857', 'a.wuayar@gmail.com', 83, 37462849, 1, '');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (38, 'Ricardo', 'Fiorentino', '', 'r.fiorentino@gmail.com', 85, 23547758, 1, '');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (39, 'Ariel', 'Rojas', '0351-4837294', 'a.rojas@gmail.com', 87, 32748372, 1, '');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (40, 'Alberto', 'Boretto', '0351-4837283', 'boretto@gmail.com', 89, 29384722, 1, '');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (41, ' Diego', 'De Torre', '0354-48372948', 'diego.detorre@gmail.com', 90, 30294837, 1, '');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (42, 'Rafael', 'D’angelo', '0351-3485729', 'rafael.dangelo@gmail.com', 92, 29384756, 1, '');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (43, 'Rosario', 'Tafario', '03535-4783927', 'rosario.tafario@gmail.com', 94, 27384027, 1, '');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (44, 'Roberto', 'Ceballos', '0351-3847293', 'roberto.ceballos@hotmail.com', 96, 28374918, 1, '');

--
-- Data for table public.condicioniva (OID = 128649) (LIMIT 0,3)
--
INSERT INTO condicioniva (idcondicioniva, nombre, descripcion)
VALUES (1, 'R.Inscripto', 'Responsable Inscripto');

INSERT INTO condicioniva (idcondicioniva, nombre, descripcion)
VALUES (2, 'Monotributista', 'Monotributista');

INSERT INTO condicioniva (idcondicioniva, nombre, descripcion)
VALUES (3, 'C.Final', 'Consumidor Final');

--
-- Data for table public.domicilio (OID = 128656) (LIMIT 0,58)
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
VALUES (48, 'Caceres', 444, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (54, 'Rondeau', 455, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (63, 'Lavalleja', 455, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (64, 'Corrientes  y Yapeyú', 32, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (65, 'independencia', 400, 0, '', '', 9);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (66, 'Lorenzo Barcalá', 432, 0, '', '', 2);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (67, 'América', 3000, 0, '', '', 3);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (68, 'Av Ohiggins', 0, 0, '', '', 4);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (69, '25 de mayo', 62, 0, '', '', 6);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (70, 'Arellano', 332, 0, '', '', 74);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (71, 'las heras', 376, 0, '', '', 3);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (72, 'Argandoña', 654, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (73, 'avellaneda', 20, 0, '', '', 74);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (74, 'David Luque', 519, 0, '', '', 75);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (75, 'Patria', 837, 0, '', '', 75);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (76, 'Independencia', 800, 0, '', '', 8);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (77, 'Independencia', 800, 0, '', '', 8);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (78, 'Camino Interfábrica', 223, 0, '', '', 74);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (79, 'Independencia', 565, 0, '', '', 2);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (80, 'tucuman', 287, 0, '', '', 3);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (81, 'Castulo Peña', 55, 0, '', '', 3);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (82, 'Roque Peña', 223, 0, '', '', 74);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (83, 'Avellaneda', 34, 0, '', '', 8);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (84, 'Dean Funes', 347, 0, '', '', 35);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (85, 'Dean Funes', 804, 0, '', '', 35);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (86, 'richieri', 342, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (87, 'corrientes', 372, 0, '', '', 2);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (88, 'Patria', 389, 0, '', '', 75);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (89, 'Patria', 389, 0, '', '', 75);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (90, 'richieri', 34, 0, '', '', 19);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (91, 'necochea', 250, 9, '6', '', 19);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (92, 'Avellaneda', 849, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (93, 'Avellaneda', 34, 0, '', '', 73);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (94, 'jujuy', 348, 0, '', '', 58);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (95, 'La Rioja', 345, 0, '', '', 58);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (96, 'Patria', 3829, 0, '', '', 75);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (97, 'Obispo Trejo', 1738, 0, '', '', 2);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (98, 'Mailvinas', 2000, 0, '', '', 3);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (99, '25 de mayo', 34, 0, '', '', 8);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (100, 'Roque Perez', 2543, 0, '', '', 6);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (101, 'Tucuman', 394, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (102, 'Entre Rios', 287, 0, '', '', 2);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (103, 'Tucuman', 584, 0, '', '', 8);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (104, 'Pasaje Ochoa', 43, 0, '', '', 74);

--
-- Data for table public.rotura (OID = 128663) (LIMIT 0,7)
--
INSERT INTO rotura (idrotura, nombre, descripcion)
VALUES (1, 'Engranaje', 'Rotura de engranaje');

INSERT INTO rotura (idrotura, nombre, descripcion)
VALUES (2, 'Tornillo', 'Rotura de tornillo o tornillo doblado.');

INSERT INTO rotura (idrotura, nombre, descripcion)
VALUES (3, 'Eje', 'Rotura de Eje');

INSERT INTO rotura (idrotura, nombre, descripcion)
VALUES (4, 'Husillo', 'Rotura de husillos');

INSERT INTO rotura (idrotura, nombre, descripcion)
VALUES (5, 'Corte de correa', 'Corte de correa');

INSERT INTO rotura (idrotura, nombre, descripcion)
VALUES (6, 'Ruleman', 'Rotura de ruleman');

INSERT INTO rotura (idrotura, nombre, descripcion)
VALUES (7, 'Motor', 'Rotura de Motor');

--
-- Data for table public.categoria (OID = 128670) (LIMIT 0,4)
--
INSERT INTO categoria (idcategoria, nombre, descripcion)
VALUES (2, 'Afilador', '');

INSERT INTO categoria (idcategoria, nombre, descripcion)
VALUES (1, 'Tornero', '');

INSERT INTO categoria (idcategoria, nombre, descripcion)
VALUES (5, 'Administrativo', ' ');

INSERT INTO categoria (idcategoria, nombre, descripcion)
VALUES (3, 'Rectificador', ' ');

--
-- Data for table public.cargo (OID = 128677) (LIMIT 0,9)
--
INSERT INTO cargo (idcargo, nombre, descripcion)
VALUES (2, 'Operario Especializado', '');

INSERT INTO cargo (idcargo, nombre, descripcion)
VALUES (4, 'Oficial', '');

INSERT INTO cargo (idcargo, nombre, descripcion)
VALUES (5, 'Medio oficial', '');

INSERT INTO cargo (idcargo, nombre, descripcion)
VALUES (3, 'Oficial de calidad', '');

INSERT INTO cargo (idcargo, nombre, descripcion)
VALUES (11, 'Compras', ' ');

INSERT INTO cargo (idcargo, nombre, descripcion)
VALUES (10, 'Gerente', ' ');

INSERT INTO cargo (idcargo, nombre, descripcion)
VALUES (9, 'RRHH', ' ');

INSERT INTO cargo (idcargo, nombre, descripcion)
VALUES (8, 'Ventas', ' ');

INSERT INTO cargo (idcargo, nombre, descripcion)
VALUES (12, 'Finanzas', ' ');

--
-- Data for table public.turno (OID = 128684) (LIMIT 0,3)
--
INSERT INTO turno (idturno, horainicio, horafin, nombre, descripcion)
VALUES (1, '00:08:00', '00:12:00', 'MAÑANA', '');

INSERT INTO turno (idturno, horainicio, horafin, nombre, descripcion)
VALUES (2, '00:13:00', '00:17:00', 'TARDE', '');

INSERT INTO turno (idturno, horainicio, horafin, nombre, descripcion)
VALUES (3, '00:18:00', '00:22:00', 'NOCHE', '');

--
-- Data for table public.marca (OID = 128691) (LIMIT 0,3)
--
INSERT INTO marca (idmarca, nombre, descripcion)
VALUES (3, 'Jones Shipman', 'Internacional');

INSERT INTO marca (idmarca, nombre, descripcion)
VALUES (2, 'Bridgeport', 'Internacional');

INSERT INTO marca (idmarca, nombre, descripcion)
VALUES (1, 'Tornomax', 'Nacional');

--
-- Data for table public.estadomaquina (OID = 128698) (LIMIT 0,1)
--
INSERT INTO estadomaquina (idestado, nombre, descripcion)
VALUES (1, 'Disponible', 'mauqina disponible');

--
-- Data for table public.tipomaquina (OID = 128705) (LIMIT 0,6)
--
INSERT INTO tipomaquina (idtipomaquina, nombre, descripcion)
VALUES (1, 'TORNO', '');

INSERT INTO tipomaquina (idtipomaquina, nombre, descripcion)
VALUES (3, 'FRESADORA', '');

INSERT INTO tipomaquina (idtipomaquina, nombre, descripcion)
VALUES (4, 'AFILADORA', '');

INSERT INTO tipomaquina (idtipomaquina, nombre, descripcion)
VALUES (5, 'RECTIFICADORA', '');

INSERT INTO tipomaquina (idtipomaquina, nombre, descripcion)
VALUES (2, 'SOLDADORA', '');

INSERT INTO tipomaquina (idtipomaquina, nombre, descripcion)
VALUES (6, 'PERFORADORA', '');

--
-- Data for table public.estadoejecetapaprod (OID = 128712) (LIMIT 0,6)
--
INSERT INTO estadoejecetapaprod (idestado, nombre, descripcion)
VALUES (1, 'GENERADA', '');

INSERT INTO estadoejecetapaprod (idestado, nombre, descripcion)
VALUES (3, 'DETENIDA', '');

INSERT INTO estadoejecetapaprod (idestado, nombre, descripcion)
VALUES (4, 'FINALIZADA', '');

INSERT INTO estadoejecetapaprod (idestado, nombre, descripcion)
VALUES (5, 'REPLANIFICADA', '');

INSERT INTO estadoejecetapaprod (idestado, nombre, descripcion)
VALUES (6, 'CANCELADA', '');

INSERT INTO estadoejecetapaprod (idestado, nombre, descripcion)
VALUES (2, 'EN EJECUCION', '');

--
-- Data for table public.tipodocumento (OID = 128726) (LIMIT 0,3)
--
INSERT INTO tipodocumento (idtipodocumento, tipo, nombre)
VALUES (1, 'DNI', 'Documento Nacional de Identidad');

INSERT INTO tipodocumento (idtipodocumento, tipo, nombre)
VALUES (2, 'LE', 'Libreta de Enrolamiento');

INSERT INTO tipodocumento (idtipodocumento, tipo, nombre)
VALUES (3, 'LC', 'Libreta Cívica');

--
-- Data for table public.estadopiezareal (OID = 128734) (LIMIT 0,1)
--
INSERT INTO estadopiezareal (idestado, nombre, descripcion)
VALUES (1, 'INICIADO', '');

--
-- Data for table public.estadoejecplanifpedido (OID = 128738) (LIMIT 0,4)
--
INSERT INTO estadoejecplanifpedido (idestado, nombre, descripcion)
VALUES (1, 'ENEJECUCION', 'Se ha lanzado la producción de una planificación');

INSERT INTO estadoejecplanifpedido (idestado, nombre, descripcion)
VALUES (2, 'DETENIDA', '');

INSERT INTO estadoejecplanifpedido (idestado, nombre, descripcion)
VALUES (3, 'REPLANIFICADA', '');

INSERT INTO estadoejecplanifpedido (idestado, nombre, descripcion)
VALUES (4, 'FINALIZADA', '');

--
-- Data for table public.rol (OID = 128749) (LIMIT 0,12)
--
INSERT INTO rol (idrol, rol, descripcion)
VALUES (1, 'ADMIN', 'Administrador del Sistema');

INSERT INTO rol (idrol, rol, descripcion)
VALUES (8, 'CLIENTE', 'Cliente de la empresa');

INSERT INTO rol (idrol, rol, descripcion)
VALUES (7, 'OPERARIO PRODUCCION', 'Operario de Producción');

INSERT INTO rol (idrol, rol, descripcion)
VALUES (9, 'OPERARIO CALIDAD', 'Operario de Calidad');

INSERT INTO rol (idrol, rol, descripcion)
VALUES (4, 'RESP. CALIDAD', 'Responsable de Calidad');

INSERT INTO rol (idrol, rol, descripcion)
VALUES (6, 'RESP. PRODUCCION', 'Responsable de Produccion');

INSERT INTO rol (idrol, rol, descripcion)
VALUES (3, 'RESP. COMPRAS', 'Responsable de Compras');

INSERT INTO rol (idrol, rol, descripcion)
VALUES (2, 'RESP. VENTAS', 'Responsable de Ventas');

INSERT INTO rol (idrol, rol, descripcion)
VALUES (5, 'RESP. ALMACENAMIENTO', 'Responsable de Almacenamiento');

INSERT INTO rol (idrol, rol, descripcion)
VALUES (10, 'RESP. FINANZAS', 'Responsable de Finanzas');

INSERT INTO rol (idrol, rol, descripcion)
VALUES (11, 'RESP. MANTENIMIENTO', 'Resonsable de Mantenimiento');

INSERT INTO rol (idrol, rol, descripcion)
VALUES (12, 'RESP. REC. HUMANOS', 'Responsable de Recursos Humanos');

--
-- Data for table public.accioncalidad (OID = 128763) (LIMIT 0,4)
--
INSERT INTO accioncalidad (idaccioncalidad, nombre, descripcion)
VALUES (1, 'Medir', 'Realizar una medición de las dimensiones del objeto');

INSERT INTO accioncalidad (idaccioncalidad, nombre, descripcion)
VALUES (2, 'Contar', 'Realizar el conteo de diferentes aspectos de un objeto');

INSERT INTO accioncalidad (idaccioncalidad, nombre, descripcion)
VALUES (3, 'Observar', 'Realizar una observación general del objeto');

INSERT INTO accioncalidad (idaccioncalidad, nombre, descripcion)
VALUES (4, 'Ajuste de piezas', 'Se prueba que las piezas que se unen, se puedan juntar y separar sin problemas.');

--
-- Data for table public.empleado (OID = 128770) (LIMIT 0,7)
--
INSERT INTO empleado (idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo)
VALUES (6, 1, '2011-11-19', 'Carlos', 'Riquelme', '0351-4573829', 'carlosriquelme@hotmail.com', 98, 28726384, 1, 3, 25, NULL, '', 3);

INSERT INTO empleado (idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo)
VALUES (7, 2, '2011-11-19', 'Arturo', 'Lopez', '0352-4837293', 'arturo.lopez@gmail.com', 99, 24748392, 1, 3, 26, NULL, '', 2);

INSERT INTO empleado (idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo)
VALUES (8, 3, '2011-11-19', 'Alicia', 'Fernández', '0351-4837485', 'alicia.fernandes@gmail.com', 100, 27384374, 1, 5, 27, NULL, '', 11);

INSERT INTO empleado (idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo)
VALUES (9, 4, '2011-11-19', 'Oscar', 'Barale', '0351-4837495', 'oscar.barale@gmail.com', 101, 11904502, 1, 5, 28, NULL, '', 10);

INSERT INTO empleado (idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo)
VALUES (10, 5, '2011-11-19', 'Hernán', 'Aguilera', '0351-4837493', 'hernan.aguilera@gmail.com', 102, 33847362, 1, 2, 29, NULL, '', 3);

INSERT INTO empleado (idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo)
VALUES (11, 6, '2011-11-19', 'Roberto', 'Arganiaraz', '0351-4738273', 'roberto.arganiaraz@gmail.com', 103, 26372846, 1, 5, 30, NULL, '', 8);

INSERT INTO empleado (idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo)
VALUES (12, 7, '2011-11-19', 'Alvaro', 'Rodriguez', '0351-4734857', 'alvaro.rodriguez@gmail.com', 104, 17478374, 1, 2, 31, NULL, '', 2);

--
-- Data for table public.proveedor (OID = 128774) (LIMIT 0,5)
--
INSERT INTO proveedor (idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit)
VALUES (4, 1, 'Ferrus SRL', 28, '0351-4827652', '15-3827648', 'ferrussrl@gmail.com', 64, '2011-11-18', NULL, NULL, 1, '26-22384736-7');

INSERT INTO proveedor (idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit)
VALUES (5, 2, 'Amiangraf', 29, '0351-3992883', '', 'amiangraf@gmail.com', 66, '2011-11-18', NULL, NULL, 1, '27-2238847-3');

INSERT INTO proveedor (idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit)
VALUES (6, 3, 'Empremet', 30, '0351-4398572', '15-2384759', 'empremet@gmail.com', 68, '2011-11-18', NULL, NULL, 1, '26-29938478-7');

INSERT INTO proveedor (idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit)
VALUES (7, 4, 'Del Metal', 31, '0351-3847859', '15-2883994', 'delmetal@gmail.com', 70, '2011-11-18', NULL, NULL, 1, '27-38470323-3');

INSERT INTO proveedor (idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit)
VALUES (8, 5, 'Multicentro', 32, '0351-4677464', '15-4326538', 'multicentro@gmail.com', 72, '2011-11-18', NULL, NULL, 1, '32-74837263-7');

--
-- Data for table public.estadocompra (OID = 128778) (LIMIT 0,10)
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
-- Data for table public.materiaprima (OID = 128789) (LIMIT 0,18)
--
INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (13, 'Resina Acetal ', '2011-11-18', NULL, NULL, 50, 'Viene en planchuela, plastico Resia Acetal', 4, 2, 100.000, 100.000, 100.000, '0', NULL, 1);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (14, 'Polipropileno', '2011-11-19', NULL, NULL, 50, 'Plastico Polipopileno que viene en planchuela', 4, 2, 100.000, 100.000, 100.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (15, 'Grillón', '2011-11-19', NULL, NULL, 50, 'Plastico Grillón que viene en planchuela', 4, 2, 100.000, 100.000, 100.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (16, 'Teflón', '2011-11-19', NULL, NULL, 50, 'Plastico Teflón que viene en planchuela', 4, 2, 100.000, 100.000, 100.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (17, 'Acero 1020', '2011-11-19', NULL, NULL, 50, 'Acero 1020. Se puede pedir en planchuelas, en redondo, cuadrados o hexagonales', 2, 2, 100.000, 60.000, 60.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (18, 'Acero 1010', '2011-11-19', NULL, NULL, 50, 'Acero 1010. Se puede pedir en planchuelas, en redondo, cuadrados o hexagonales', 2, 2, 100.000, 60.000, 60.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (19, 'Acero 1045', '2011-11-19', NULL, NULL, 50, 'Acero 1045. Se puede pedir en planchuelas, en redondo, cuadrados o hexagonales', 2, 2, 100.000, 60.000, 60.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (20, 'Acero 8620', '2011-11-19', NULL, NULL, 50, 'Acero 8620. Se puede pedir en planchuelas, en redondo, cuadrados o hexagonales', 2, 2, 100.000, 60.000, 60.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (21, 'Acero 4140', '2011-11-19', NULL, NULL, 50, 'Acero 4140. Se puede pedir en planchuelas, en redondo, cuadrados o hexagonales', 2, 2, 100.000, 60.000, 60.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (22, 'Acero 1112', '2011-11-19', NULL, NULL, 50, 'Acero 1112. Se puede pedir en planchuelas, en redondo, cuadrados o hexagonales', 2, 2, 100.000, 60.000, 60.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (23, 'Acero Inoxidable', '2011-11-19', NULL, NULL, 50, 'Acero Inoxidable. Se puede pedir en planchuelas, en redondo, cuadrados o hexagonales', 2, 2, 100.000, 60.000, 60.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (24, 'Acero Inoxidable 304', '2011-11-19', NULL, NULL, 50, 'Acero Inoxidable 304. Se puede pedir en planchuelas, en redondo, cuadrados o hexagonales', 2, 2, 50.000, 50.000, 50.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (25, 'Acero Inoxidable 420', '2011-11-19', NULL, NULL, 50, 'Acero Inoxidable 420. Se puede pedir en planchuelas, en redondo, cuadrados o hexagonales', 2, 2, 50.000, 50.000, 50.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (26, 'Latón', '2011-11-19', NULL, NULL, 50, 'Bronce Latón.Viene en planchuelas, macizo, caño o hexagono', 7, 2, 50.000, 50.000, 50.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (27, 'Sae 63', '2011-11-19', NULL, NULL, 50, 'Bronce Sae 63.Viene en planchuelas, macizo, caño o hexagono', 7, 2, 50.000, 50.000, 50.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (28, 'Sae 65', '2011-11-19', NULL, NULL, 50, 'Bronce Sae 65.Viene en planchuelas, macizo, caño o hexagono', 7, 2, 50.000, 50.000, 50.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (29, 'Bronce al aluminio', '2011-11-19', NULL, NULL, 50, 'Bronce  al aluminio.Viene en planchuelas, macizo, caño o hexagono', 7, 2, 50.000, 50.000, 50.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (30, 'Camplo', '2011-11-19', NULL, NULL, 50, 'Aluminio Camplo. Viene en cuadrado, redondo, planchuela ', 8, 2, 50.000, 50.000, 50.000, '0', NULL, 2);

--
-- Data for table public.matriz (OID = 128796) (LIMIT 0,2)
--
INSERT INTO matriz (idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial)
VALUES (6, 2, 'Volante', 'Matriz para un volante de auto de juego', NULL, NULL, 16, 4);

INSERT INTO matriz (idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial)
VALUES (7, 3, 'Perilla', 'matriz para hacer una perilla', NULL, NULL, 14, 4);

--
-- Data for table public.producto (OID = 128803) (LIMIT 0,13)
--
INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (13, 1, 'Buje de 200', 150, 'Buje de acero de 200');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (14, 2, 'Buje de 3', 30, 'lleva 1 pieza de buje de 3');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (15, 3, 'Buje 5', 50, 'Lleva 1 pieza buje de 5');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (16, 4, 'Buje de 100', 100, 'Lleva una pieza buje de 100');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (17, 5, 'Portafiltro', 200, 'Lleva una pieza portafiltro de polipropileno');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (18, 6, 'Mango Facial', 216, 'cuerpo ( con resina), electrodos (acero)');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (19, 7, 'Mango', 168, 'lleva tanques,viceras, pletinas de resina ');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (20, 8, 'Volante', 120, 'Lleva una pieza volante');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (21, 9, 'Tornillo', 10, 'Tornillo especial');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (22, 10, 'Taco', 50, 'Taco hecho a medida');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (23, 11, 'Eje', 61, 'Eje que se puede hacer de cualquier materia prima');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (24, 12, 'Tuerca', 15, 'Tuerca hecha a medida especial');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (25, 13, 'Perilla', 180, 'Lleva una pieza perilla de plastico');

--
-- Data for table public.estadoejecplancalidad (OID = 128810) (LIMIT 0,4)
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
-- Data for table public.etapadeproduccion (OID = 128825) (LIMIT 0,9)
--
INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, tipomaquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (2, 3, 'Torneado', '00:00:20', '00:00:20', NULL, '00:00:05', '2010-12-12', 1);

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, tipomaquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (1, 2, 'Rectificado', '00:00:30', '00:00:30', NULL, '00:00:05', '2010-08-16', 2);

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, tipomaquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (5, 11, 'Pulido', '00:20:00', '00:20:00', NULL, '00:00:08', '2010-10-10', 2);

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, tipomaquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (4, 15, 'Afilado', '00:01:00', '00:20:00', NULL, '00:00:07', '2010-10-10', 2);

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, tipomaquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (3, 12, 'Fresado', '00:02:00', '00:02:00', NULL, '00:00:06', '2010-10-10', 2);

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, tipomaquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (6, 16, 'Agujereado', '00:09:00', '00:09:00', NULL, '00:00:10', '2011-09-20', 2);

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, tipomaquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (23, 18, 'Tratamiento Térmico', NULL, NULL, NULL, '00:00:10', '2011-11-19', 2);

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, tipomaquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (24, 19, 'Cromado', NULL, NULL, NULL, '00:00:08', '2011-11-19', 2);

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, tipomaquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (22, 17, 'Baño Antioxidante', NULL, NULL, NULL, '00:00:09', '2011-11-19', 2);

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, tipomaquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (25, 20, 'Corte', NULL, NULL, 6, '00:00:10', '2011-11-20', 2);

--
-- Data for table public.procesocalidad (OID = 128836) (LIMIT 0,6)
--
INSERT INTO procesocalidad (idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad)
VALUES (3, 'Composición de piezas', 2, 'Probar separar y unir las piezas de un producto 2 veces.', '0', 'Las piezas deben poder separarse y unirse sin ningun tipo de problema', '00:15:00', '2011-11-19', 'Herramientas de roscar', 4);

INSERT INTO procesocalidad (idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad)
VALUES (6, 'Medición de extremos', 3, 'medir de extremo a extremo la distancia del producto, debe ser la especficada', '+-1mm', 'Se miden todos los extremos del producto.', '00:25:00', '2011-11-19', 'Calibre comun y calibre digital
', 1);

INSERT INTO procesocalidad (idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad)
VALUES (1, 'Detectar Spatter', 1, 'Observar que no hayan spatter en el producto', '3', NULL, '00:20:00', '2010-09-06', NULL, 2);

INSERT INTO procesocalidad (idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad)
VALUES (8, 'Medición de Tornillos', 4, 'Se debe medir cada tornillo, el cabezal y el cuerpo', '0mm', 'Un tornillo tiene cabeza y cuerpo, deben medir lo especificado', '00:17:00', '2011-11-19', 'Micròmetros 0- 25, Micròmetros 25 a 50 y Micròmetros 50- 75
', 1);

INSERT INTO procesocalidad (idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad)
VALUES (9, 'Detección de imperfecciones', 5, 'Observar que no hayan rayones, desviaciones ni curvaturas no especificadas', 'N/A', 'El producto no debe tener imperfecciones de ningun tipo', '00:15:00', '2011-11-19', 'Ninguno', 3);

INSERT INTO procesocalidad (idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad)
VALUES (10, 'Contar Tuercas', 6, 'Se cuenta que la cantidad de tuercas sean las especificadas', 'N/A', 'Se cuenta que la cantidad de tuercas sean las especificadas', '00:20:00', '2011-11-19', 'Ninguna', 2);

--
-- Data for table public.detalleproducto (OID = 128894) (LIMIT 0,16)
--
INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (21, 13, 1, 'lleva 1 buje de 200', 11);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (22, 14, 1, '', 8);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (23, 15, 1, '', 9);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (24, 16, 1, '', 10);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (25, 17, 1, '', 12);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (26, 18, 1, '', 22);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (27, 18, 1, '', 23);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (28, 19, 1, '', 13);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (29, 19, 1, '', 14);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (30, 19, 1, '', 15);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (31, 20, 1, '', 16);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (32, 21, 1, '', 17);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (33, 22, 1, '', 18);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (34, 23, 1, '', 19);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (35, 24, 1, '', 20);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (36, 25, 1, '', 21);

--
-- Data for table public.tiporeclamo (OID = 128924) (LIMIT 0,3)
--
INSERT INTO tiporeclamo (idtiporeclamo, nombre, descripcion)
VALUES (1, 'RECLAMO PROVEEDOR', 'Reclamo que se realiza a un Proveedor por materia prima defectuosa');

INSERT INTO tiporeclamo (idtiporeclamo, nombre, descripcion)
VALUES (2, 'RECLAMO CLIENTE', 'Reclamo que se recibe de un Cliente por inconformidad de un pedido');

INSERT INTO tiporeclamo (idtiporeclamo, nombre, descripcion)
VALUES (3, 'RECLAMO EMPRESA METALURGICA', 'Reclamo que se realiza a una empresa tercerizada por inconformidades');

--
-- Data for table public.piezaxetapadeproduccion (OID = 128985) (LIMIT 0,23)
--
INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (8, 2, NULL, NULL);

INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (8, 6, NULL, NULL);

INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (9, 2, NULL, NULL);

INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (9, 6, NULL, NULL);

INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (10, 2, NULL, NULL);

INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (10, 6, NULL, NULL);

INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (11, 2, NULL, NULL);

INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (11, 6, NULL, NULL);

INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (12, 2, NULL, NULL);

INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (13, 6, NULL, NULL);

INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (13, 1, NULL, NULL);

INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (14, 1, NULL, NULL);

INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (15, 3, NULL, NULL);

INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (16, 2, NULL, NULL);

INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (17, 2, NULL, NULL);

INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (18, 1, NULL, NULL);

INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (19, 2, NULL, NULL);

INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (19, 5, NULL, NULL);

INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (20, 1, NULL, NULL);

INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (20, 2, NULL, NULL);

INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (21, 2, NULL, NULL);

INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (22, 2, NULL, NULL);

INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (23, 3, NULL, NULL);

--
-- Data for table public.empleadoxturno (OID = 128991) (LIMIT 0,14)
--
INSERT INTO empleadoxturno (idempleado, idturno)
VALUES (6, 1);

INSERT INTO empleadoxturno (idempleado, idturno)
VALUES (6, 2);

INSERT INTO empleadoxturno (idempleado, idturno)
VALUES (7, 1);

INSERT INTO empleadoxturno (idempleado, idturno)
VALUES (7, 2);

INSERT INTO empleadoxturno (idempleado, idturno)
VALUES (8, 1);

INSERT INTO empleadoxturno (idempleado, idturno)
VALUES (8, 2);

INSERT INTO empleadoxturno (idempleado, idturno)
VALUES (9, 1);

INSERT INTO empleadoxturno (idempleado, idturno)
VALUES (9, 2);

INSERT INTO empleadoxturno (idempleado, idturno)
VALUES (10, 1);

INSERT INTO empleadoxturno (idempleado, idturno)
VALUES (10, 2);

INSERT INTO empleadoxturno (idempleado, idturno)
VALUES (11, 1);

INSERT INTO empleadoxturno (idempleado, idturno)
VALUES (11, 2);

INSERT INTO empleadoxturno (idempleado, idturno)
VALUES (12, 1);

INSERT INTO empleadoxturno (idempleado, idturno)
VALUES (12, 2);

--
-- Data for table public.provincia (OID = 128994) (LIMIT 0,7)
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
-- Data for table public.localidad (OID = 128998) (LIMIT 0,35)
--
INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (1, 'Córdoba', 1);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (2, 'Jesús María', 1);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (3, 'Carlos Paz', 1);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (4, 'Juarez Celman', 1);

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

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (11, 'Posadas', 3);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (12, 'Oberá', 3);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (13, 'Ituzaingó', 3);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (14, 'Candelaria', 3);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (15, 'Apóstoles', 3);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (16, 'San Fernando del Valle', 4);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (17, 'Fiambala', 4);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (18, 'La Paz', 4);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (19, 'Santa Maria', 4);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (20, 'Belen', 4);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (21, 'Santa Maria', 4);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (22, 'San Salvador de Jujuy', 5);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (23, 'Aguas Calientes', 5);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (24, 'Libertador San Martin', 5);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (25, 'Tumbaya', 5);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (26, 'San Pedro de Jujuy', 5);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (27, 'Purmamarca', 5);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (28, 'Santa Fe Capital', 6);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (29, 'Avellaneda', 6);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (30, 'Rosario', 6);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (31, 'Sunchales', 6);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (32, 'Santa Rosa La Pampa', 7);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (33, 'Jacinto Arauz', 7);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (34, 'Santa Isabel', 7);

INSERT INTO localidad (idlocalidad, nombre, provincia)
VALUES (35, 'General Acha', 7);

--
-- Data for table public.barrio (OID = 129002) (LIMIT 0,75)
--
INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (1, 'San Vicente', 5000, 1);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (2, 'Centro', 5000, 1);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (3, 'Poeta Lugones', 5220, 2);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (4, 'Camino San Carlos', 5003, 1);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (5, 'Salta', 4008, 5);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (6, 'La Quinta', 3200, 3);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (7, 'Alberdi', 4326, 3);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (8, 'Centro', 3453, 4);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (9, 'Justo Gomez', 4443, 4);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (10, 'Las Lilas', 6654, 5);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (11, 'San Martín', 2324, 5);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (12, 'Crisol', 1231, 6);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (13, 'La Florida', 7634, 6);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (14, 'Tucaya', 2131, 7);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (15, 'Las Rosas', 4562, 7);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (16, 'Belgrano', 3242, 8);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (17, 'Parma', 7688, 8);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (18, 'Villa Cabello', 6745, 9);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (19, 'Las Flores', 5634, 9);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (20, 'Sarmiento', 6757, 10);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (21, 'Gral. Paz', 7868, 10);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (22, 'San Jorge', 1725, 11);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (23, 'Quilmes', 4364, 11);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (24, 'Santa Marita', 6354, 12);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (25, '27 de Octubre', 3434, 12);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (26, 'Los Cocos', 2346, 13);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (27, 'Yaquinta', 7686, 13);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (28, 'Yapeyu', 4562, 14);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (29, 'Los Cobres', 2357, 14);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (30, 'La Plata', 9876, 15);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (31, 'Tulumba', 9542, 15);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (32, 'Alianza', 8524, 16);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (33, 'San Ferro', 4537, 16);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (34, 'Ferroviarios', 2583, 17);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (35, 'Húsares', 8523, 17);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (36, 'Santa Lucia', 5602, 18);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (37, 'Las violetas', 3460, 18);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (38, 'Las Rosas', 2355, 19);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (39, 'Necochea', 2355, 19);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (40, 'Santa Catalina', 8524, 19);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (41, 'Catalinas Sur', 4577, 20);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (42, 'Los Moyes', 5793, 20);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (43, 'Los Andes', 5633, 21);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (44, '27 de Abril', 3443, 21);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (45, '25 de Mayo', 2323, 22);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (46, 'Blas Parera', 1212, 22);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (47, 'Los cocos', 5152, 23);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (48, 'Ascochinga', 6131, 23);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (49, 'Cabral', 6324, 24);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (50, 'San Justo', 4724, 24);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (51, 'Guemes', 8542, 25);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (52, 'España', 3472, 25);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (53, 'Francia', 9345, 26);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (54, 'Sarmiento', 7345, 26);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (55, 'La toma', 2422, 27);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (56, 'Florencia', 4874, 27);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (57, 'Seminario', 5645, 28);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (58, 'El Huerto', 7686, 28);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (59, 'Las Flores', 9679, 29);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (60, 'America', 9777, 29);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (61, 'Totoral', 3455, 30);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (62, 'San Jerónimo', 6432, 30);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (63, 'Salta', 3222, 31);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (64, 'Juan B. Justo', 1264, 31);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (65, 'El Nogal', 4112, 32);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (66, 'Jardin', 6386, 32);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (67, 'Maipu', 8642, 33);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (68, 'Jockey', 8561, 33);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (69, 'La Falda', 8451, 34);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (70, 'Italia', 8651, 34);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (71, 'San Antonio', 5677, 35);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (72, 'Los Arrabales', 9876, 35);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (73, 'San Vicente', 5003, 1);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (74, 'San Martin', 5003, 1);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (75, 'Gral. Paz', 5003, 1);

--
-- Data for table public.usuarioxrol (OID = 129006) (LIMIT 0,19)
--
INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (1, 1);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (8, 16);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (8, 18);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (8, 19);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (8, 20);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (8, 21);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (8, 22);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (8, 23);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (8, 24);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (6, 26);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (3, 27);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (1, 28);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (4, 29);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (12, 27);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (10, 27);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (2, 27);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (7, 31);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (9, 31);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (7, 30);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (9, 30);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (7, 25);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (7, 29);

--
-- Data for table public.estadodetallecompra (OID = 129043) (LIMIT 0,8)
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
-- Data for table public.estadodetalletrabajotercerizado (OID = 129050) (LIMIT 0,9)
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
-- Data for table public.estadoejecucionprocesocalidad (OID = 129057) (LIMIT 0,6)
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
-- Data for table public.estadoproductoreal (OID = 129075) (LIMIT 0,11)
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
-- Data for table public.estadoremito (OID = 129090) (LIMIT 0,1)
--
INSERT INTO estadoremito (idestado, nombre, descripcion)
VALUES (1, 'GENERADO', 'GENERADO');

--
-- Data for table public.tipomaterial (OID = 129097) (LIMIT 0,5)
--
INSERT INTO tipomaterial (idtipomaterial, nombre, descripcion)
VALUES (2, 'ACERO', '');

INSERT INTO tipomaterial (idtipomaterial, nombre, descripcion)
VALUES (4, 'PLASTICO', '');

INSERT INTO tipomaterial (idtipomaterial, nombre, descripcion)
VALUES (5, 'METAL', '');

INSERT INTO tipomaterial (idtipomaterial, nombre, descripcion)
VALUES (7, 'BRONCE', ' ');

INSERT INTO tipomaterial (idtipomaterial, nombre, descripcion)
VALUES (8, 'ALUMINIO', ' ');

--
-- Data for table public.unidadmedida (OID = 129314) (LIMIT 0,4)
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
-- Data for table public.estadoplanificacionproduccion (OID = 129375) (LIMIT 0,3)
--
INSERT INTO estadoplanificacionproduccion (id, nombre, descripcion)
VALUES (1, 'REC-ASIG', 'Recusos Asignados');

INSERT INTO estadoplanificacionproduccion (id, nombre, descripcion)
VALUES (2, 'MP-ASIG', 'Materia Prima Asignada');

INSERT INTO estadoplanificacionproduccion (id, nombre, descripcion)
VALUES (3, 'GENERADA', 'Estado inicial de la planificación');

--
-- Data for table public.maquina (OID = 129384) (LIMIT 0,16)
--
INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (1, 'TORNO PARALELO-1', 1, 'Torno Paralelo', 1, 1, '2010-10-10', NULL, '00:03:00', 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (9, 'TORNO PARALELO-2', 1, NULL, 1, 1, '2010-10-19', NULL, '00:02:00', 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (10, 'TORNO PARALELO-3', 1, NULL, 1, 1, '2010-10-19', NULL, '00:03:00', 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (11, 'TORNO PARALELO-4', 1, NULL, 1, 1, '2010-10-19', NULL, '00:03:00', 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (2, 'RECTIFICADORA PLANETARIA', 2, '', 1, 3, '2010-10-19', NULL, '00:02:00', 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (7, 'RECTIFICADORA UNIVERSAL', 2, NULL, 1, 3, '2010-10-19', NULL, '00:02:00', 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (8, 'FRESADORA UNIVERSAL-2', 2, NULL, 1, 3, '2010-10-19', NULL, '00:02:00', 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (6, 'FRESADORA UNIVERSAL-1', 2, NULL, 1, 3, '2010-10-19', NULL, '00:02:00', 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (3, 'AFILADORA', 3, NULL, 1, 4, '2010-11-04', NULL, NULL, 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (4, 'FRESADORA UNIVERSAL-3', 3, NULL, 1, 5, '2010-11-09', NULL, NULL, 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (12, 'AGUJEREADORE COMUN-1', 3, NULL, 1, 5, '2010-11-09', NULL, NULL, 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (13, 'AGUJEREADORE COMUN-2', 3, NULL, 1, 5, '2010-11-09', NULL, NULL, 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (14, 'AGUJEREADORE DE COLUMNA-1', 3, NULL, 1, 5, '2010-11-09', NULL, NULL, 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (15, 'AGUJEREADORE DE COLUMNA-2', 3, NULL, 1, 5, '2010-11-09', NULL, NULL, 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (16, 'SOLDADORA ELECTRICA', 3, NULL, 1, 5, '2010-11-09', NULL, NULL, 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (17, 'SOLDADURA AUTOGENA', 3, NULL, 1, 5, '2010-11-09', NULL, NULL, 1);

--
-- Data for table public.pieza (OID = 129396) (LIMIT 0,16)
--
INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (8, 'Buje de 3', 4, 15, NULL, 8.000, 3.000, 3.000, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (9, 'Buje de 5', 4, 15, NULL, 5.000, 5.000, 5.000, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (10, 'Buje de 100', 4, 14, NULL, 7.000, 10.000, 10.000, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (11, 'Buje de 200', 4, 14, NULL, 9.000, 10.000, 10.000, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (12, 'Portafiltro', 4, 14, NULL, 10.000, 5.900, 10.000, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (13, 'Tanque', 4, 13, NULL, 10.000, 5.900, 10.000, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (14, 'Vicera', 4, 13, NULL, 15.000, 5.000, 11.000, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (15, 'Plentina', 4, 13, NULL, 15.000, 5.000, 5.000, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (17, 'Tornillo', 2, 19, NULL, 10.000, 2.000, 5.000, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (18, 'Taco', 2, 18, NULL, 10.000, 5.000, 5.000, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (19, 'Eje', 7, 27, NULL, 10.000, 5.000, 15.000, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (20, 'Tuerca', 2, 23, NULL, 5.000, 2.000, 5.000, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (21, 'Perilla', 4, 15, NULL, 20.000, 5.000, 5.000, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (23, 'Electrodo', 2, 24, NULL, 5.000, 5.000, 5.000, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (22, 'Cuerpo de Mango', 4, 13, NULL, 20.000, 5.000, 10.000, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (16, 'Volante', 2, 17, 6, 10.000, 5.900, 10.000, NULL);

--
-- Data for table public.estadoreclamo (OID = 129459) (LIMIT 0,5)
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
-- Data for table public.estadomantpreventivo (OID = 129477) (LIMIT 0,6)
--
INSERT INTO estadomantpreventivo (idestado, nombre, descripcion)
VALUES (1, 'Generado', NULL);

INSERT INTO estadomantpreventivo (idestado, nombre, descripcion)
VALUES (2, 'En Ejecucion', NULL);

INSERT INTO estadomantpreventivo (idestado, nombre, descripcion)
VALUES (3, 'Realizado', NULL);

INSERT INTO estadomantpreventivo (idestado, nombre, descripcion)
VALUES (4, 'Finalizado', NULL);

INSERT INTO estadomantpreventivo (idestado, nombre, descripcion)
VALUES (5, 'Omitido', NULL);

INSERT INTO estadomantpreventivo (idestado, nombre, descripcion)
VALUES (6, 'Reclamado', NULL);

--
-- Data for the `public.proveedorxmateriaprima` table  (Records 1 - 35)
--
INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (4, 13, 40);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (4, 14, 45);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (4, 15, 55);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (4, 16, 35);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (7, 17, 60);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (7, 18, 60);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (7, 19, 66);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (7, 13, 45);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (7, 14, 55);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (7, 15, 63);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (4, 17, 64);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (4, 18, 63);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (4, 19, 71);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (5, 20, 44);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (6, 20, 46);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (5, 21, 39);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (6, 21, 38);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (6, 22, 67);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (5, 22, 60);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (5, 23, 55);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (6, 23, 47);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (5, 24, 53);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (6, 24, 50);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (7, 25, 67);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (8, 25, 66);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (7, 26, 55);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (8, 26, 54);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (7, 27, 45);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (8, 27, 48);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (7, 28, 49);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (8, 28, 47);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (7, 29, 55);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (8, 29, 50);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (7, 30, 47);

INSERT INTO "public"."proveedorxmateriaprima" ("idproveedor", "idmateriaprima", "precio")
VALUES (8, 30, 56);

--
-- Definition for index pedido_nropedido_key (OID = 129921) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_nropedido_key UNIQUE (nropedido);
--
-- Definition for index proveedorxmateriaprima_idx (OID = 129923) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_idx PRIMARY KEY (idmateriaprima, idproveedor);
--
-- Definition for index maquinaxejecucionetapaproduccion_idx (OID = 129925) : 
--
ALTER TABLE ONLY maquinaxejecucionetapaproduccion
    ADD CONSTRAINT maquinaxejecucionetapaproduccion_idx PRIMARY KEY (idejecucionetapaproduccion, idetapaproduccion, idmaquina);
--
-- Definition for index maquinaxprocesocalidad_idx (OID = 129927) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT maquinaxprocesocalidad_idx PRIMARY KEY (idprocesocalidad, idmaquina);
--
-- Definition for index piezaxetapadeproduccion_idx (OID = 129929) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT piezaxetapadeproduccion_idx PRIMARY KEY (idpieza, idetapaproduccion);
--
-- Definition for index empleadoxturno_idx (OID = 129931) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_idx PRIMARY KEY (idempleado, idturno);
--
-- Definition for index usuarioxrol_pkey (OID = 129933) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_pkey PRIMARY KEY (idrol, idusuario);
--
-- Definition for index rolxprivilegio_pkey (OID = 129935) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_pkey PRIMARY KEY (idrol, idprivilegio);
--
-- Definition for index prueba_pkey (OID = 129937) : 
--
ALTER TABLE ONLY prueba
    ADD CONSTRAINT prueba_pkey PRIMARY KEY (id);
--
-- Definition for index usuario_pkey (OID = 129939) : 
--
ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (idusuario);
--
-- Definition for index factura_fk2 (OID = 129941) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk2 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index comprobantepago_fk1 (OID = 129946) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk1 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index cliente_fk2 (OID = 129951) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk2 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index empleado_fk3 (OID = 129956) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk3 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index sesion_fk (OID = 129961) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT sesion_fk FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index usuarioxrol_fk1 (OID = 129966) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_fk1 FOREIGN KEY (idusuario) REFERENCES usuario(idusuario);
--
-- Definition for index tipomaterial_pkey (OID = 129971) : 
--
ALTER TABLE ONLY tipomaterial
    ADD CONSTRAINT tipomaterial_pkey PRIMARY KEY (idtipomaterial);
--
-- Definition for index accioncalidad_pkey (OID = 129973) : 
--
ALTER TABLE ONLY accioncalidad
    ADD CONSTRAINT accioncalidad_pkey PRIMARY KEY (idaccioncalidad);
--
-- Definition for index procesocalidad_fk (OID = 129975) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT procesocalidad_fk FOREIGN KEY (accioncalidad) REFERENCES accioncalidad(idaccioncalidad);
--
-- Definition for index barrio_pkey (OID = 129980) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT barrio_pkey PRIMARY KEY (idbarrio);
--
-- Definition for index domicilio_fk (OID = 129982) : 
--
ALTER TABLE ONLY domicilio
    ADD CONSTRAINT domicilio_fk FOREIGN KEY (barrio) REFERENCES barrio(idbarrio);
--
-- Definition for index cargo_pkey (OID = 129987) : 
--
ALTER TABLE ONLY cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (idcargo);
--
-- Definition for index empleado_fk4 (OID = 129989) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk4 FOREIGN KEY (cargo) REFERENCES cargo(idcargo);
--
-- Definition for index categoria_pkey (OID = 129994) : 
--
ALTER TABLE ONLY categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (idcategoria);
--
-- Definition for index empleado_fk2 (OID = 129996) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk2 FOREIGN KEY (categoria) REFERENCES categoria(idcategoria);
--
-- Definition for index cliente_pkey (OID = 130001) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (idcliente);
--
-- Definition for index pedido_fk4 (OID = 130003) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk4 FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index reclamocliente_fk1 (OID = 130008) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_fk1 FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index codigodebarra_pkey (OID = 130013) : 
--
ALTER TABLE ONLY codigodebarra
    ADD CONSTRAINT codigodebarra_pkey PRIMARY KEY (idcodigo);
--
-- Definition for index materiaprima_fk1 (OID = 130015) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk1 FOREIGN KEY (codbarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index productoreal_fk2 (OID = 130020) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk2 FOREIGN KEY (codigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index compra_pkey (OID = 130025) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_pkey PRIMARY KEY (idcompra);
--
-- Definition for index detallecompra_fk (OID = 130027) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk FOREIGN KEY (idcompra) REFERENCES compra(idcompra);
--
-- Definition for index reclamoproveedor_fk1 (OID = 130032) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_fk1 FOREIGN KEY (compra) REFERENCES compra(idcompra);
--
-- Definition for index comprobantepago_pkey (OID = 130037) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_pkey PRIMARY KEY (idcomprobantepago);
--
-- Definition for index condicioniva_pkey (OID = 130039) : 
--
ALTER TABLE ONLY condicioniva
    ADD CONSTRAINT condicioniva_pkey PRIMARY KEY (idcondicioniva);
--
-- Definition for index cliente_fk5 (OID = 130041) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk5 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index empresametalurgica_fk2 (OID = 130046) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk2 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index proveedormantenimientomaquina_fk2 (OID = 130051) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk2 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index proveedor_fk2 (OID = 130056) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk2 FOREIGN KEY (condicion) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index detallecompra_idx (OID = 130061) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_idx PRIMARY KEY (iddetalle, idcompra);
--
-- Definition for index detallereclamoproveedor_fk1 (OID = 130063) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_fk1 FOREIGN KEY (iddetallecompra, idcompra) REFERENCES detallecompra(iddetalle, idcompra);
--
-- Definition for index detallecompra_iddetalle_key (OID = 130068) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallefactura_idx (OID = 130070) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_idx PRIMARY KEY (iddetalle, idfactura);
--
-- Definition for index detallefactura_iddetalle_key (OID = 130072) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleplanprocedimientos_idx (OID = 130074) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_idx PRIMARY KEY (iddetalle, idplanpprocedimientos);
--
-- Definition for index detalleplanprocedimientos_iddetalle_key (OID = 130076) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleplanprocesoscalidad_idx (OID = 130078) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_idx PRIMARY KEY (iddetalle, idplanprocesoscalidad);
--
-- Definition for index detalleplanprocesoscalidad_iddetalle_key (OID = 130080) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamocliente_idx (OID = 130082) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamocliente_iddetalle_key (OID = 130084) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamoempresametalurgica_idx (OID = 130086) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamoempresametalurgica_iddetalle_key (OID = 130088) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamoproveedor_idx (OID = 130090) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamoproveedor_iddetalle_key (OID = 130092) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleremito_idx (OID = 130094) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_idx PRIMARY KEY (iddetalle, idremito);
--
-- Definition for index detalleremito_iddetalle_key (OID = 130096) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallerequerimientosmateriaprima_idx (OID = 130098) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_idx PRIMARY KEY (iddetalle, idplanrequerimientosmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_iddetalle_key (OID = 130100) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index domicilio_pkey (OID = 130102) : 
--
ALTER TABLE ONLY domicilio
    ADD CONSTRAINT domicilio_pkey PRIMARY KEY (iddomicilio);
--
-- Definition for index cliente_fk4 (OID = 130104) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk4 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index empresametalurgica_fk1 (OID = 130109) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index responsable_fk (OID = 130114) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_fk FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index proveedormantenimientomaquina_fk1 (OID = 130119) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index empleado_fk (OID = 130124) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index proveedor_fk1 (OID = 130129) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index ejecucionetapaproduccion_nroejecucion_key1 (OID = 130134) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_nroejecucion_key1 UNIQUE (nroejecucion);
--
-- Definition for index empleado_pkey (OID = 130136) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (idempleado);
--
-- Definition for index mantenimientocorrectivo_fk (OID = 130138) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_fk FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index ejecucionetapaproduccion_fk1 (OID = 130143) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk1 FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index detallaplanificacionproduccion_fk3 (OID = 130148) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk3 FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index empleadoxturno_fk (OID = 130153) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_fk FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index empresametalurgica_pkey (OID = 130158) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_pkey PRIMARY KEY (idempresametalurgica);
--
-- Definition for index trabajotercerizado_fk1 (OID = 130160) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk1 FOREIGN KEY (empresa) REFERENCES empresametalurgica(idempresametalurgica);
--
-- Definition for index estadocliente_pkey (OID = 130165) : 
--
ALTER TABLE ONLY estadocliente
    ADD CONSTRAINT estadocliente_pkey PRIMARY KEY (idestado);
--
-- Definition for index cliente_fk1 (OID = 130167) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk1 FOREIGN KEY (estado) REFERENCES estadocliente(idestado);
--
-- Definition for index estadocompra_pkey (OID = 130172) : 
--
ALTER TABLE ONLY estadocompra
    ADD CONSTRAINT estadocompra_pkey PRIMARY KEY (idestado);
--
-- Definition for index compra_fk (OID = 130174) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_fk FOREIGN KEY (estado) REFERENCES estadocompra(idestado);
--
-- Definition for index estadodetallecompra_pkey (OID = 130179) : 
--
ALTER TABLE ONLY estadodetallecompra
    ADD CONSTRAINT estadodetallecompra_pkey PRIMARY KEY (idestado);
--
-- Definition for index detallecompra_fk2 (OID = 130181) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk2 FOREIGN KEY (estado) REFERENCES estadodetallecompra(idestado);
--
-- Definition for index estadodetalletrabajotercerizado_pkey (OID = 130186) : 
--
ALTER TABLE ONLY estadodetalletrabajotercerizado
    ADD CONSTRAINT estadodetalletrabajotercerizado_pkey PRIMARY KEY (idestado);
--
-- Definition for index detalletrabajotercerizado_fk3 (OID = 130188) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk3 FOREIGN KEY (estado) REFERENCES estadodetalletrabajotercerizado(idestado);
--
-- Definition for index estadoejecetapaprod_pkey (OID = 130193) : 
--
ALTER TABLE ONLY estadoejecetapaprod
    ADD CONSTRAINT estadoejecetapaprod_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionetapaproduccion_fk2 (OID = 130195) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk2 FOREIGN KEY (estado) REFERENCES estadoejecetapaprod(idestado);
--
-- Definition for index estadoejecplancalidad_pkey (OID = 130200) : 
--
ALTER TABLE ONLY estadoejecplancalidad
    ADD CONSTRAINT estadoejecplancalidad_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionplanificacioncalidad_fk1 (OID = 130202) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_fk1 FOREIGN KEY (estado) REFERENCES estadoejecplancalidad(idestado);
--
-- Definition for index estadoejecplanifpedido_pkey (OID = 130207) : 
--
ALTER TABLE ONLY estadoejecplanifpedido
    ADD CONSTRAINT estadoejecplanifpedido_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionplanificacionproduccion_fk1 (OID = 130209) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_fk1 FOREIGN KEY (estado) REFERENCES estadoejecplanifpedido(idestado);
--
-- Definition for index estadoejecucionprocesocalidad_pkey (OID = 130214) : 
--
ALTER TABLE ONLY estadoejecucionprocesocalidad
    ADD CONSTRAINT estadoejecucionprocesocalidad_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionprocesocalidad_fk1 (OID = 130216) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_fk1 FOREIGN KEY (estado) REFERENCES estadoejecucionprocesocalidad(idestado);
--
-- Definition for index estadofactura_pkey (OID = 130221) : 
--
ALTER TABLE ONLY estadofactura
    ADD CONSTRAINT estadofactura_pkey PRIMARY KEY (idestado);
--
-- Definition for index factura_fk3 (OID = 130223) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk3 FOREIGN KEY (estado) REFERENCES estadofactura(idestado);
--
-- Definition for index estadomaquina_pkey (OID = 130228) : 
--
ALTER TABLE ONLY estadomaquina
    ADD CONSTRAINT estadomaquina_pkey PRIMARY KEY (idestado);
--
-- Definition for index estadopedido_pkey (OID = 130230) : 
--
ALTER TABLE ONLY estadopedido
    ADD CONSTRAINT estadopedido_pkey PRIMARY KEY (idestado);
--
-- Definition for index pedido_fk (OID = 130232) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk FOREIGN KEY (estado) REFERENCES estadopedido(idestado);
--
-- Definition for index estadopiezareal_pkey (OID = 130237) : 
--
ALTER TABLE ONLY estadopiezareal
    ADD CONSTRAINT estadopiezareal_pkey PRIMARY KEY (idestado);
--
-- Definition for index estadoproductoreal_pkey (OID = 130239) : 
--
ALTER TABLE ONLY estadoproductoreal
    ADD CONSTRAINT estadoproductoreal_pkey PRIMARY KEY (idestado);
--
-- Definition for index productoreal_fk3 (OID = 130241) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk3 FOREIGN KEY (estado) REFERENCES estadoproductoreal(idestado);
--
-- Definition for index estadoremito_pkey (OID = 130246) : 
--
ALTER TABLE ONLY estadoremito
    ADD CONSTRAINT estadoremito_pkey PRIMARY KEY (idestado);
--
-- Definition for index remito_fk1 (OID = 130248) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_fk1 FOREIGN KEY (estado) REFERENCES estadoremito(idestado);
--
-- Definition for index estadotrabajotercerizado_pkey (OID = 130253) : 
--
ALTER TABLE ONLY estadotrabajotercerizado
    ADD CONSTRAINT estadotrabajotercerizado_pkey PRIMARY KEY (idestado);
--
-- Definition for index trabajotercerizado_fk2 (OID = 130255) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk2 FOREIGN KEY (estado) REFERENCES estadotrabajotercerizado(idestado);
--
-- Definition for index etapadeproduccion_pkey (OID = 130260) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_pkey PRIMARY KEY (idetapaproduccion);
--
-- Definition for index ejecucionetapaproduccion_fk (OID = 130262) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detalletrabajotercerizado_fk2 (OID = 130267) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk2 FOREIGN KEY (proceso) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index piezaxetapadeproduccion_fk1 (OID = 130272) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT piezaxetapadeproduccion_fk1 FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detalleplanprocedimientos_fk2 (OID = 130277) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_fk2 FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index factura_pkey (OID = 130282) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_pkey PRIMARY KEY (idfactura);
--
-- Definition for index pedido_fk1 (OID = 130284) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk1 FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index comprobantepago_fk2 (OID = 130289) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk2 FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index detallefactura_fk (OID = 130294) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_fk FOREIGN KEY (idfactura) REFERENCES factura(idfactura);
--
-- Definition for index formadepago_pkey (OID = 130299) : 
--
ALTER TABLE ONLY formadepago
    ADD CONSTRAINT formadepago_pkey PRIMARY KEY (idformapago);
--
-- Definition for index factura_fk1 (OID = 130301) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk1 FOREIGN KEY (formapago) REFERENCES formadepago(idformapago);
--
-- Definition for index comprobantepago_fk (OID = 130306) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk FOREIGN KEY (formadepago) REFERENCES formadepago(idformapago);
--
-- Definition for index localidad_pkey (OID = 130311) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT localidad_pkey PRIMARY KEY (idlocalidad);
--
-- Definition for index barrio_fk (OID = 130313) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT barrio_fk FOREIGN KEY (localidad) REFERENCES localidad(idlocalidad);
--
-- Definition for index mantenimientocorrectivo_pkey (OID = 130318) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_pkey PRIMARY KEY (idmantenimientocorrectivo);
--
-- Definition for index detallemantenimientocorrectivo_fk1 (OID = 130320) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_fk1 FOREIGN KEY (idmantenimientocorrectivo) REFERENCES mantenimientocorrectivo(idmantenimientocorrectivo);
--
-- Definition for index mantenimientopreventivo_pkey (OID = 130325) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_pkey PRIMARY KEY (idmantenimientopreventivo);
--
-- Definition for index marca_pkey (OID = 130327) : 
--
ALTER TABLE ONLY marca
    ADD CONSTRAINT marca_pkey PRIMARY KEY (idmarca);
--
-- Definition for index materiaprima_pkey (OID = 130329) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_pkey PRIMARY KEY (idmateriaprima);
--
-- Definition for index matriz_fk (OID = 130331) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT matriz_fk FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallecompra_fk1 (OID = 130336) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk1 FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index proveedorxmateriaprima_fk1 (OID = 130341) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_fk1 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_fk2 (OID = 130346) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_fk2 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index matriz_pkey (OID = 130351) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT matriz_pkey PRIMARY KEY (idmatriz);
--
-- Definition for index pedidomatriz_fk (OID = 130353) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT pedidomatriz_fk FOREIGN KEY (idmatriz) REFERENCES matriz(idmatriz);
--
-- Definition for index pedido_pkey (OID = 130358) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (idpedido);
--
-- Definition for index plano_fk (OID = 130360) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT plano_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index remito_fk (OID = 130365) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index trabajotercerizado_fk (OID = 130370) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index planificacionproduccion_fk (OID = 130375) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index planificacioncalidad_fk (OID = 130380) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT planificacioncalidad_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index detallepedido_fk (OID = 130385) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_fk FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index productoreal_fk1 (OID = 130390) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk1 FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index detallefactura_fk1 (OID = 130395) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_fk1 FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index pedidomatriz_idx (OID = 130400) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT pedidomatriz_idx PRIMARY KEY (idpedidomatriz);
--
-- Definition for index planificacioncalidad_pkey (OID = 130402) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT planificacioncalidad_pkey PRIMARY KEY (idplanificacion);
--
-- Definition for index ejecucionplanificacioncalidad_fk (OID = 130404) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_fk FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index detalleplanificacioncalidad_fk (OID = 130409) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index planificacionproduccion_pkey (OID = 130414) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_pkey PRIMARY KEY (idplanificacionproduccion);
--
-- Definition for index ejecucionplanificacionproduccion_fk (OID = 130416) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_fk FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index detallaplanificacionproduccion_fk (OID = 130421) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index plano_pkey (OID = 130426) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT plano_pkey PRIMARY KEY (idplano);
--
-- Definition for index planprocedimientos_pkey (OID = 130428) : 
--
ALTER TABLE ONLY planprocedimientos
    ADD CONSTRAINT planprocedimientos_pkey PRIMARY KEY (idplanprocedimientos);
--
-- Definition for index detalleplanprocedimientos_fk (OID = 130430) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_fk FOREIGN KEY (idplanpprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index pedido_fk5 (OID = 130435) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk5 FOREIGN KEY (planprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index planprocesoscalidad_pkey (OID = 130440) : 
--
ALTER TABLE ONLY planprocesoscalidad
    ADD CONSTRAINT planprocesoscalidad_pkey PRIMARY KEY (idplanprocesoscalidad);
--
-- Definition for index detalleplanprocesoscalidad_fk (OID = 130442) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_fk FOREIGN KEY (idplanprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index pedido_fk7 (OID = 130447) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk7 FOREIGN KEY (planprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index planrequerimientosmateriaprima_pkey (OID = 130452) : 
--
ALTER TABLE ONLY planrequerimientosmateriaprima
    ADD CONSTRAINT planrequerimientosmateriaprima_pkey PRIMARY KEY (idplanrequerimientosmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_fk (OID = 130454) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_fk FOREIGN KEY (idplanrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index pedido_fk6 (OID = 130459) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk6 FOREIGN KEY (planrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index presupuesto_pkey (OID = 130464) : 
--
ALTER TABLE ONLY presupuesto
    ADD CONSTRAINT presupuesto_pkey PRIMARY KEY (idpresupuesto);
--
-- Definition for index pedido_fk2 (OID = 130466) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk2 FOREIGN KEY (presupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index detallepresupuesto_fk (OID = 130471) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_fk FOREIGN KEY (idpresupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index prioridad_pkey (OID = 130476) : 
--
ALTER TABLE ONLY prioridad
    ADD CONSTRAINT prioridad_pkey PRIMARY KEY (idprioridad);
--
-- Definition for index cliente_fk (OID = 130478) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index privilegio_pkey (OID = 130483) : 
--
ALTER TABLE ONLY privilegio
    ADD CONSTRAINT privilegio_pkey PRIMARY KEY (idprivilegio);
--
-- Definition for index rolxprivilegio_fk (OID = 130485) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_fk FOREIGN KEY (idprivilegio) REFERENCES privilegio(idprivilegio);
--
-- Definition for index procesocalidad_pkey (OID = 130490) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT procesocalidad_pkey PRIMARY KEY (idprocesocalidad);
--
-- Definition for index detalleplanificacioncalidad_fk2 (OID = 130492) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk2 FOREIGN KEY (procesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index maquinaxprocesocalidad_fk (OID = 130497) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT maquinaxprocesocalidad_fk FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detalleplanprocesoscalidad_fk2 (OID = 130502) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_fk2 FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index producto_pkey (OID = 130507) : 
--
ALTER TABLE ONLY producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (idproducto);
--
-- Definition for index detalleproducto_fk (OID = 130509) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_fk FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index detallepedido_fk1 (OID = 130514) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detalleremito_fk1 (OID = 130519) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detallereclamocliente_fk1 (OID = 130524) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detallepresupuesto_fk2 (OID = 130529) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_fk2 FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index productoreal_idx (OID = 130534) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_idx PRIMARY KEY (idproductoreal);
--
-- Definition for index detalleproductoreal_fk (OID = 130536) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_fk FOREIGN KEY (idproductoreal) REFERENCES productoreal(idproductoreal);
--
-- Definition for index proveedor_pkey (OID = 130541) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_pkey PRIMARY KEY (idproveedor);
--
-- Definition for index compra_fk1 (OID = 130543) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_fk1 FOREIGN KEY (proveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index proveedorxmateriaprima_fk (OID = 130548) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_fk FOREIGN KEY (idproveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index proveedormantenimientomaquina_pkey (OID = 130553) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_pkey PRIMARY KEY (idproveedormantenimiento);
--
-- Definition for index mantenimientopreventivo_fk (OID = 130555) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_fk FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index mantenimientocorrectivo_fk1 (OID = 130560) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_fk1 FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index provincia_pkey (OID = 130565) : 
--
ALTER TABLE ONLY provincia
    ADD CONSTRAINT provincia_pkey PRIMARY KEY (idprovincia);
--
-- Definition for index localidad_fk (OID = 130567) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT localidad_fk FOREIGN KEY (provincia) REFERENCES provincia(idprovincia);
--
-- Definition for index reclamocliente_idx (OID = 130572) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_idx PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamocliente_fk (OID = 130574) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_fk FOREIGN KEY (idreclamo) REFERENCES reclamocliente(idreclamo);
--
-- Definition for index reclamoempresametalurgica_pkey (OID = 130579) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_pkey PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamoempresametalurgica_fk (OID = 130581) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_fk FOREIGN KEY (idreclamo) REFERENCES reclamoempresametalurgica(idreclamo);
--
-- Definition for index reclamoproveedor_idx (OID = 130586) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_idx PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamoproveedor_fk (OID = 130588) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_fk FOREIGN KEY (idreclamo) REFERENCES reclamoproveedor(idreclamo);
--
-- Definition for index remito_pkey (OID = 130593) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_pkey PRIMARY KEY (idremito);
--
-- Definition for index detalleremito_fk (OID = 130595) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_fk FOREIGN KEY (idremito) REFERENCES remito(idremito);
--
-- Definition for index responsable_pkey (OID = 130600) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_pkey PRIMARY KEY (idresponsable);
--
-- Definition for index cliente_fk3 (OID = 130602) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk3 FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index empresametalurgica_fk (OID = 130607) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index proveedormantenimientomaquina_fk (OID = 130612) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index proveedor_fk (OID = 130617) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index rol_pkey (OID = 130622) : 
--
ALTER TABLE ONLY rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (idrol);
--
-- Definition for index rolxprivilegio_fk1 (OID = 130624) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_fk1 FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index usuarioxrol_fk (OID = 130629) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_fk FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index rotura_pkey (OID = 130634) : 
--
ALTER TABLE ONLY rotura
    ADD CONSTRAINT rotura_pkey PRIMARY KEY (idrotura);
--
-- Definition for index detallemantenimientocorrectivo_fk (OID = 130636) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_fk FOREIGN KEY (rotura) REFERENCES rotura(idrotura);
--
-- Definition for index servicio_pkey (OID = 130641) : 
--
ALTER TABLE ONLY servicio
    ADD CONSTRAINT servicio_pkey PRIMARY KEY (idservicio);
--
-- Definition for index sesion_pkey (OID = 130643) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT sesion_pkey PRIMARY KEY (idsesion);
--
-- Definition for index tipodocumento_pkey (OID = 130645) : 
--
ALTER TABLE ONLY tipodocumento
    ADD CONSTRAINT tipodocumento_pkey PRIMARY KEY (idtipodocumento);
--
-- Definition for index responsable_fk1 (OID = 130647) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_fk1 FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index empleado_fk1 (OID = 130652) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk1 FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index tipoiva_pkey (OID = 130657) : 
--
ALTER TABLE ONLY tipoiva
    ADD CONSTRAINT tipoiva_pkey PRIMARY KEY (idtipoiva);
--
-- Definition for index factura_fk (OID = 130659) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk FOREIGN KEY (tipoiva) REFERENCES tipoiva(idtipoiva);
--
-- Definition for index tipomaquina_pkey (OID = 130664) : 
--
ALTER TABLE ONLY tipomaquina
    ADD CONSTRAINT tipomaquina_pkey PRIMARY KEY (idtipomaquina);
--
-- Definition for index tiporeclamo_pkey (OID = 130666) : 
--
ALTER TABLE ONLY tiporeclamo
    ADD CONSTRAINT tiporeclamo_pkey PRIMARY KEY (idtiporeclamo);
--
-- Definition for index reclamoempresametalurgica_fk (OID = 130668) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index reclamoproveedor_fk (OID = 130673) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index reclamocliente_fk (OID = 130678) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index trabajotercerizado_pkey (OID = 130683) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_pkey PRIMARY KEY (idtrabajo);
--
-- Definition for index detalletrabajotercerizado_fk (OID = 130685) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk FOREIGN KEY (idtrabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index reclamoempresametalurgica_fk1 (OID = 130690) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_fk1 FOREIGN KEY (trabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index turno_pkey (OID = 130695) : 
--
ALTER TABLE ONLY turno
    ADD CONSTRAINT turno_pkey PRIMARY KEY (idturno);
--
-- Definition for index empleadoxturno_fk1 (OID = 130697) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_fk1 FOREIGN KEY (idturno) REFERENCES turno(idturno);
--
-- Definition for index materiaprima_fk (OID = 130702) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk FOREIGN KEY (tipomaterial) REFERENCES tipomaterial(idtipomaterial);
--
-- Definition for index detalleproducto_iddetalle_key (OID = 130707) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_iddetalle_key PRIMARY KEY (iddetalle);
ALTER TABLE detalleproducto CLUSTER ON detalleproducto_iddetalle_key;
--
-- Definition for index pedido_fk8 (OID = 130709) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk8 FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index unidadmedida_pkey (OID = 130714) : 
--
ALTER TABLE ONLY unidadmedida
    ADD CONSTRAINT unidadmedida_pkey PRIMARY KEY (idunidadmedida);
--
-- Definition for index etapadeproduccion_fk1 (OID = 130716) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_fk1 FOREIGN KEY (unidaddemedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index materiaprima_fk2 (OID = 130721) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk2 FOREIGN KEY (unidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index detalleproductopresupuesto_pkey (OID = 130726) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT detalleproductopresupuesto_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detallepiezapresupuesto_pkey (OID = 130728) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT detallepiezapresupuesto_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detallepiezapresupuesto_fk (OID = 130730) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT detallepiezapresupuesto_fk FOREIGN KEY (iddetalleproductopresupuesto) REFERENCES detalleproductopresupuesto(iddetalle);
--
-- Definition for index detallepiezapresupuesto_fk1 (OID = 130735) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT detallepiezapresupuesto_fk1 FOREIGN KEY (idetapa) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detallepresupuesto_pkey (OID = 130740) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detallepedido_pkey (OID = 130742) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detalleproductopresupuesto_fk1 (OID = 130744) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT detalleproductopresupuesto_fk1 FOREIGN KEY (iddetallepresupuesto) REFERENCES detallepresupuesto(iddetalle);
--
-- Definition for index detalleproductopresupuesto_fk2 (OID = 130749) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT detalleproductopresupuesto_fk2 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallepiezacalidadpresupuesto_pkey (OID = 130754) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT detallepiezacalidadpresupuesto_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detallepiezacalidadpresupuesto_fk (OID = 130756) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT detallepiezacalidadpresupuesto_fk FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detallepiezacalidadpresupuesto_fk1 (OID = 130761) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT detallepiezacalidadpresupuesto_fk1 FOREIGN KEY (iddetalleproductopresupuesto) REFERENCES detalleproductopresupuesto(iddetalle);
--
-- Definition for index calendario_pkey (OID = 130766) : 
--
ALTER TABLE ONLY calendario
    ADD CONSTRAINT calendario_pkey PRIMARY KEY (id);
--
-- Definition for index disponibilidadhoraria_pkey (OID = 130768) : 
--
ALTER TABLE ONLY disponibilidadhoraria
    ADD CONSTRAINT disponibilidadhoraria_pkey PRIMARY KEY (id);
--
-- Definition for index estadoplanificacionproduccion_pkey (OID = 130770) : 
--
ALTER TABLE ONLY estadoplanificacionproduccion
    ADD CONSTRAINT estadoplanificacionproduccion_pkey PRIMARY KEY (id);
--
-- Definition for index planificacionproduccion_fk1 (OID = 130772) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_fk1 FOREIGN KEY (idestado) REFERENCES estadoplanificacionproduccion(id);
--
-- Definition for index disponibilidadhoraria_fk (OID = 130777) : 
--
ALTER TABLE ONLY disponibilidadhoraria
    ADD CONSTRAINT disponibilidadhoraria_fk FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index detalleplanificacionproduccion_pkey (OID = 130782) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_pkey PRIMARY KEY (id);
--
-- Definition for index detalleplanificacionproduccion_fk (OID = 130784) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index maquina_pkey (OID = 130789) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_pkey PRIMARY KEY (idmaquina);
--
-- Definition for index maquina_fk (OID = 130791) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk FOREIGN KEY (marca) REFERENCES marca(idmarca);
--
-- Definition for index maquina_fk1 (OID = 130796) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk1 FOREIGN KEY (estado) REFERENCES estadomaquina(idestado);
--
-- Definition for index maquina_fk2 (OID = 130801) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk2 FOREIGN KEY (tipomaquina) REFERENCES tipomaquina(idtipomaquina);
--
-- Definition for index maquina_fk3 (OID = 130806) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk3 FOREIGN KEY (idunidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index piezareal_fk1 (OID = 130811) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_fk1 FOREIGN KEY (estado) REFERENCES estadopiezareal(idestado);
--
-- Definition for index piezareal_fk2 (OID = 130816) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_fk2 FOREIGN KEY (idcodigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index pieza_pkey (OID = 130821) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_pkey PRIMARY KEY (idpieza);
--
-- Definition for index pieza_fk (OID = 130823) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk FOREIGN KEY (unidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index pieza_fk1 (OID = 130828) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk1 FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index pieza_fk2 (OID = 130833) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk2 FOREIGN KEY (matriz) REFERENCES matriz(idmatriz);
--
-- Definition for index detallempasignada_pkey (OID = 130838) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT detallempasignada_pkey PRIMARY KEY (id);
--
-- Definition for index detallempasignada_fk (OID = 130840) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT detallempasignada_fk FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallempasignada_fk1 (OID = 130845) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT detallempasignada_fk1 FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index mpasignadaxpiezareal_pkey (OID = 130850) : 
--
ALTER TABLE ONLY mpasignadaxpiezareal
    ADD CONSTRAINT mpasignadaxpiezareal_pkey PRIMARY KEY (id);
--
-- Definition for index mpasignadaxpiezareal_fk1 (OID = 130852) : 
--
ALTER TABLE ONLY mpasignadaxpiezareal
    ADD CONSTRAINT mpasignadaxpiezareal_fk1 FOREIGN KEY (iddetallempasignada) REFERENCES detallempasignada(id);
--
-- Definition for index asistencia_idx (OID = 130857) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_idx PRIMARY KEY (empleado, horaingreso, fechaingreso);
--
-- Definition for index asistencia_fk (OID = 130859) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_fk FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index piezareal_idpiezareal_key (OID = 130864) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_idpiezareal_key PRIMARY KEY (idpiezareal);
--
-- Definition for index detalleplanificacionproduccion_fk1 (OID = 130866) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk1 FOREIGN KEY (idmaquina) REFERENCES maquina(idmaquina);
--
-- Definition for index detalleplanificacionproduccion_fk2 (OID = 130871) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk2 FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleproductopresupuesto_fk (OID = 130876) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT detalleproductopresupuesto_fk FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index ejecucionplanificacionproduccion_idejecucion_key (OID = 130881) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_idejecucion_key PRIMARY KEY (idejecucion);
--
-- Definition for index detalleejecucionplanificacion_iddetalle_key (OID = 130883) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_iddetalle_key PRIMARY KEY (id);
--
-- Definition for index detalleplanificacionproduccion_fk3 (OID = 130885) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk3 FOREIGN KEY (iddetalleejecucionplanificacion) REFERENCES detalleejecucionplanificacion(id);
--
-- Definition for index fk_responsable_domicilio (OID = 130890) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT fk_responsable_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_responsable_tipodocumento (OID = 130895) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT fk_responsable_tipodocumento FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index fk_procesocalidad_accioncalidad (OID = 130900) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT fk_procesocalidad_accioncalidad FOREIGN KEY (accioncalidad) REFERENCES accioncalidad(idaccioncalidad);
--
-- Definition for index fk_detallecompra_estado (OID = 130905) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT fk_detallecompra_estado FOREIGN KEY (estado) REFERENCES estadodetallecompra(idestado);
--
-- Definition for index fk_detallecompra_materiaprima (OID = 130910) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT fk_detallecompra_materiaprima FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detallecompra_idcompra (OID = 130915) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT fk_detallecompra_idcompra FOREIGN KEY (idcompra) REFERENCES compra(idcompra);
--
-- Definition for index fk_reclamoproveedor_tiporeclamo (OID = 130920) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT fk_reclamoproveedor_tiporeclamo FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index fk_reclamoproveedor_compra (OID = 130925) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT fk_reclamoproveedor_compra FOREIGN KEY (compra) REFERENCES compra(idcompra);
--
-- Definition for index fk_empresametalurgica_condicioniva (OID = 130930) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT fk_empresametalurgica_condicioniva FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index fk_empresametalurgica_domicilio (OID = 130935) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT fk_empresametalurgica_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_empresametalurgica_responsable (OID = 130940) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT fk_empresametalurgica_responsable FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index fk_detalleplanprocesoscalidad_idprocesocalidad (OID = 130945) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT fk_detalleplanprocesoscalidad_idprocesocalidad FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index fk_detalleplanprocesoscalidad_idplanprocesoscalidad (OID = 130950) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT fk_detalleplanprocesoscalidad_idplanprocesoscalidad FOREIGN KEY (idplanprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index fk_detalleremito_idremito (OID = 130955) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT fk_detalleremito_idremito FOREIGN KEY (idremito) REFERENCES remito(idremito);
--
-- Definition for index fk_detalleremito_producto (OID = 130960) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT fk_detalleremito_producto FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index fk_detalleplanificacionproduccion_idpieza (OID = 130965) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idpieza FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index fk_detalleplanificacionproduccion_idetapaproduccion (OID = 130970) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idetapaproduccion FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_detalleplanificacionproduccion_idempleado (OID = 130975) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idempleado FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index detalleplanificacionproduccion_iddetalleejecucionplanificacion (OID = 130980) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_iddetalleejecucionplanificacion FOREIGN KEY (iddetalleejecucionplanificacion) REFERENCES detalleejecucionplanificacion(id);
--
-- Definition for index fk_detalleplanificacionproduccion_idmaquina (OID = 130985) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idmaquina FOREIGN KEY (idmaquina) REFERENCES maquina(idmaquina);
--
-- Definition for index fk_detalleplanificacionproduccion_idplanificacionproduccion (OID = 130990) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idplanificacionproduccion FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index fk_ejecucionplanificacioncalidad_estado (OID = 130995) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT fk_ejecucionplanificacioncalidad_estado FOREIGN KEY (estado) REFERENCES estadoejecplancalidad(idestado);
--
-- Definition for index fk_ejecucionplanificacioncalidad_idplanificacioncalidad (OID = 131000) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT fk_ejecucionplanificacioncalidad_idplanificacioncalidad FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index fk_planificacionproduccion_pedido (OID = 131005) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT fk_planificacionproduccion_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_planificacionproduccion_idestado (OID = 131010) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT fk_planificacionproduccion_idestado FOREIGN KEY (idestado) REFERENCES estadoplanificacionproduccion(id);
--
-- Definition for index fk_pedido_estado (OID = 131015) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_estado FOREIGN KEY (estado) REFERENCES estadopedido(idestado);
--
-- Definition for index fk_pedido_planprocesoscalidad (OID = 131020) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_planprocesoscalidad FOREIGN KEY (planprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index fk_pedido_presupuesto (OID = 131025) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_presupuesto FOREIGN KEY (presupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index fk_pedido_planrequerimientosmateriaprima (OID = 131030) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_planrequerimientosmateriaprima FOREIGN KEY (planrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index fk_pedido_planprocedimientos (OID = 131035) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_planprocedimientos FOREIGN KEY (planprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index fk_pedido_prioridad (OID = 131040) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_prioridad FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index fk_pedido_cliente (OID = 131045) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index fk_pedido_factura (OID = 131050) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_factura FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index fk_empleadoxturno_idempleado (OID = 131055) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT fk_empleadoxturno_idempleado FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_empleadoxturno_idturno (OID = 131060) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT fk_empleadoxturno_idturno FOREIGN KEY (idturno) REFERENCES turno(idturno);
--
-- Definition for index fk_ejecucionetapaproduccion_empleado (OID = 131065) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT fk_ejecucionetapaproduccion_empleado FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_ejecucionetapaproduccion_estado (OID = 131070) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT fk_ejecucionetapaproduccion_estado FOREIGN KEY (estado) REFERENCES estadoejecetapaprod(idestado);
--
-- Definition for index fk_ejecucionetapaproduccion_idetapaproduccion (OID = 131075) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT fk_ejecucionetapaproduccion_idetapaproduccion FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_compra_proveedor (OID = 131080) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT fk_compra_proveedor FOREIGN KEY (proveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index fk_compra_estado (OID = 131085) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT fk_compra_estado FOREIGN KEY (estado) REFERENCES estadocompra(idestado);
--
-- Definition for index fk_maquinaxprocesocalidad_idprocesocalidad (OID = 131090) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT fk_maquinaxprocesocalidad_idprocesocalidad FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index fk_productoreal_codigobarra (OID = 131095) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT fk_productoreal_codigobarra FOREIGN KEY (codigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index fk_productoreal_idpedido (OID = 131100) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT fk_productoreal_idpedido FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_productoreal_estado (OID = 131105) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT fk_productoreal_estado FOREIGN KEY (estado) REFERENCES estadoproductoreal(idestado);
--
-- Definition for index fk_materiaprima_unidadmedida (OID = 131110) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT fk_materiaprima_unidadmedida FOREIGN KEY (unidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index fk_materiaprima_tipomaterial (OID = 131115) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT fk_materiaprima_tipomaterial FOREIGN KEY (tipomaterial) REFERENCES tipomaterial(idtipomaterial);
--
-- Definition for index fk_materiaprima_codbarra (OID = 131120) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT fk_materiaprima_codbarra FOREIGN KEY (codbarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index fk_cliente_estado (OID = 131125) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_estado FOREIGN KEY (estado) REFERENCES estadocliente(idestado);
--
-- Definition for index fk_cliente_domicilio (OID = 131130) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_cliente_usuario (OID = 131135) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_cliente_condicioniva (OID = 131140) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_condicioniva FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index fk_cliente_responsable (OID = 131145) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_responsable FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index fk_cliente_prioridad (OID = 131150) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_prioridad FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index fk_pieza_matriz (OID = 131155) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT fk_pieza_matriz FOREIGN KEY (matriz) REFERENCES matriz(idmatriz);
--
-- Definition for index fk_pieza_unidadmedida (OID = 131160) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT fk_pieza_unidadmedida FOREIGN KEY (unidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index fk_pieza_materiaprima (OID = 131165) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT fk_pieza_materiaprima FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detallepiezacalidadpresupuesto_idprocesocalidad (OID = 131170) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT fk_detallepiezacalidadpresupuesto_idprocesocalidad FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index fk_detallepiezacalidadpresupuesto_iddetalleproductopresupuesto (OID = 131175) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT fk_detallepiezacalidadpresupuesto_iddetalleproductopresupuesto FOREIGN KEY (iddetalleproductopresupuesto) REFERENCES detalleproductopresupuesto(iddetalle);
--
-- Definition for index fk_detalleproductoreal_idproductoreal (OID = 131180) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT fk_detalleproductoreal_idproductoreal FOREIGN KEY (idproductoreal) REFERENCES productoreal(idproductoreal);
--
-- Definition for index fk_factura_tipoiva (OID = 131185) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_factura_tipoiva FOREIGN KEY (tipoiva) REFERENCES tipoiva(idtipoiva);
--
-- Definition for index fk_factura_formapago (OID = 131190) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_factura_formapago FOREIGN KEY (formapago) REFERENCES formadepago(idformapago);
--
-- Definition for index fk_factura_usuario (OID = 131195) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_factura_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_factura_estado (OID = 131200) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_factura_estado FOREIGN KEY (estado) REFERENCES estadofactura(idestado);
--
-- Definition for index fk_detallerequerimientosmateriaprima_idmateriaprima (OID = 131205) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT fk_detallerequerimientosmateriaprima_idmateriaprima FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index dtallerequerimientosmateriaprimadplanrequerimientosmateriaprima (OID = 131210) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT dtallerequerimientosmateriaprimadplanrequerimientosmateriaprima FOREIGN KEY (idplanrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index fk_plano_pedido (OID = 131215) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT fk_plano_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_usuarioxrol_idrol (OID = 131220) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT fk_usuarioxrol_idrol FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index fk_usuarioxrol_idusuario (OID = 131225) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT fk_usuarioxrol_idusuario FOREIGN KEY (idusuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_trabajotercerizado_empresa (OID = 131230) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT fk_trabajotercerizado_empresa FOREIGN KEY (empresa) REFERENCES empresametalurgica(idempresametalurgica);
--
-- Definition for index fk_trabajotercerizado_estado (OID = 131235) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT fk_trabajotercerizado_estado FOREIGN KEY (estado) REFERENCES estadotrabajotercerizado(idestado);
--
-- Definition for index fk_trabajotercerizado_pedido (OID = 131240) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT fk_trabajotercerizado_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_piezaxetapadeproduccion_idetapaproduccion (OID = 131245) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT fk_piezaxetapadeproduccion_idetapaproduccion FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_proveedor_responsable (OID = 131250) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT fk_proveedor_responsable FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index fk_proveedor_domicilio (OID = 131255) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT fk_proveedor_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_proveedor_condicion (OID = 131260) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT fk_proveedor_condicion FOREIGN KEY (condicion) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index fk_reclamoempresametalurgica_trabajotercerizado (OID = 131265) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT fk_reclamoempresametalurgica_trabajotercerizado FOREIGN KEY (trabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index fk_reclamoempresametalurgica_tiporeclamo (OID = 131270) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT fk_reclamoempresametalurgica_tiporeclamo FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index fk_matriz_materiaprima (OID = 131275) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT fk_matriz_materiaprima FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detalleproducto_idproducto (OID = 131280) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT fk_detalleproducto_idproducto FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index fk_mantenimientocorrectivo_empleado (OID = 131285) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT fk_mantenimientocorrectivo_empleado FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_mantenimientocorrectivo_proveedormantenimiento (OID = 131290) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT fk_mantenimientocorrectivo_proveedormantenimiento FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index fk_piezareal_estado (OID = 131295) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT fk_piezareal_estado FOREIGN KEY (estado) REFERENCES estadopiezareal(idestado);
--
-- Definition for index fk_piezareal_idcodigobarra (OID = 131300) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT fk_piezareal_idcodigobarra FOREIGN KEY (idcodigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index fk_detallempasignada_idmateriaprima (OID = 131305) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT fk_detallempasignada_idmateriaprima FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detallempasignada_idplanificacionproduccion (OID = 131310) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT fk_detallempasignada_idplanificacionproduccion FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index fk_ejecucionprocesocalidad_estado (OID = 131315) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT fk_ejecucionprocesocalidad_estado FOREIGN KEY (estado) REFERENCES estadoejecucionprocesocalidad(idestado);
--
-- Definition for index fk_pedidomatriz_idmatriz (OID = 131320) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT fk_pedidomatriz_idmatriz FOREIGN KEY (idmatriz) REFERENCES matriz(idmatriz);
--
-- Definition for index fk_maquina_idunidadmedida (OID = 131325) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT fk_maquina_idunidadmedida FOREIGN KEY (idunidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index fk_maquina_estado (OID = 131330) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT fk_maquina_estado FOREIGN KEY (estado) REFERENCES estadomaquina(idestado);
--
-- Definition for index fk_maquina_tipomaquina (OID = 131335) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT fk_maquina_tipomaquina FOREIGN KEY (tipomaquina) REFERENCES tipomaquina(idtipomaquina);
--
-- Definition for index fk_maquina_marca (OID = 131340) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT fk_maquina_marca FOREIGN KEY (marca) REFERENCES marca(idmarca);
--
-- Definition for index fk_planificacioncalidad_pedido (OID = 131345) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT fk_planificacioncalidad_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_detalleplanificacioncalidad_idplanificacioncalidad (OID = 131350) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT fk_detalleplanificacioncalidad_idplanificacioncalidad FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index fk_detalleplanificacioncalidad_procesocalidad (OID = 131355) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT fk_detalleplanificacioncalidad_procesocalidad FOREIGN KEY (procesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index fk_detallemantenimientocorrectivo_rotura (OID = 131360) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT fk_detallemantenimientocorrectivo_rotura FOREIGN KEY (rotura) REFERENCES rotura(idrotura);
--
-- Definition for index fk_detallemantenimientocorrectivo_idmantenimientocorrectivo (OID = 131365) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT fk_detallemantenimientocorrectivo_idmantenimientocorrectivo FOREIGN KEY (idmantenimientocorrectivo) REFERENCES mantenimientocorrectivo(idmantenimientocorrectivo);
--
-- Definition for index fk_localidad_provincia (OID = 131370) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT fk_localidad_provincia FOREIGN KEY (provincia) REFERENCES provincia(idprovincia);
--
-- Definition for index fk_proveedorxmateriaprima_idmateriaprima (OID = 131375) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT fk_proveedorxmateriaprima_idmateriaprima FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_proveedorxmateriaprima_idproveedor (OID = 131380) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT fk_proveedorxmateriaprima_idproveedor FOREIGN KEY (idproveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index fk_remito_pedido (OID = 131385) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT fk_remito_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_remito_estado (OID = 131390) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT fk_remito_estado FOREIGN KEY (estado) REFERENCES estadoremito(idestado);
--
-- Definition for index fk_disponibilidadhoraria_idempleado (OID = 131395) : 
--
ALTER TABLE ONLY disponibilidadhoraria
    ADD CONSTRAINT fk_disponibilidadhoraria_idempleado FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_sesion_usuario (OID = 131400) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT fk_sesion_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_detallereclamoproveedor_idreclamo (OID = 131405) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT fk_detallereclamoproveedor_idreclamo FOREIGN KEY (idreclamo) REFERENCES reclamoproveedor(idreclamo);
--
-- Definition for index fk_detallereclamoproveedor_idcompra (OID = 131410) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT fk_detallereclamoproveedor_idcompra FOREIGN KEY (idcompra, iddetallecompra) REFERENCES detallecompra(idcompra, iddetalle);
--
-- Definition for index fk_barrio_localidad (OID = 131415) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT fk_barrio_localidad FOREIGN KEY (localidad) REFERENCES localidad(idlocalidad);
--
-- Definition for index fk_asistencia_empleado (OID = 131420) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT fk_asistencia_empleado FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_etapadeproduccion_unidaddemedida (OID = 131425) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT fk_etapadeproduccion_unidaddemedida FOREIGN KEY (unidaddemedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index fk_detallefactura_idpedido (OID = 131430) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT fk_detallefactura_idpedido FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_detallefactura_idfactura (OID = 131435) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT fk_detallefactura_idfactura FOREIGN KEY (idfactura) REFERENCES factura(idfactura);
--
-- Definition for index fk_detallepedido_producto (OID = 131440) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT fk_detallepedido_producto FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index fk_detallepedido_idpedido (OID = 131445) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT fk_detallepedido_idpedido FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_mantenimientopreventivo_proveedormantenimiento (OID = 131450) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT fk_mantenimientopreventivo_proveedormantenimiento FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index fk_rolxprivilegio_idprivilegio (OID = 131455) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT fk_rolxprivilegio_idprivilegio FOREIGN KEY (idprivilegio) REFERENCES privilegio(idprivilegio);
--
-- Definition for index fk_rolxprivilegio_idrol (OID = 131460) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT fk_rolxprivilegio_idrol FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index fk_detallereclamocliente_producto (OID = 131465) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT fk_detallereclamocliente_producto FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index fk_detallereclamocliente_idreclamo (OID = 131470) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT fk_detallereclamocliente_idreclamo FOREIGN KEY (idreclamo) REFERENCES reclamocliente(idreclamo);
--
-- Definition for index fk_proveedormantenimientomaquina_condicioniva (OID = 131475) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT fk_proveedormantenimientomaquina_condicioniva FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index fk_proveedormantenimientomaquina_domicilio (OID = 131480) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT fk_proveedormantenimientomaquina_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_proveedormantenimientomaquina_responsable (OID = 131485) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT fk_proveedormantenimientomaquina_responsable FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index fk_detallepiezapresupuesto_iddetalleproductopresupuesto (OID = 131490) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT fk_detallepiezapresupuesto_iddetalleproductopresupuesto FOREIGN KEY (iddetalleproductopresupuesto) REFERENCES detalleproductopresupuesto(iddetalle);
--
-- Definition for index fk_detallepiezapresupuesto_idetapa (OID = 131495) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT fk_detallepiezapresupuesto_idetapa FOREIGN KEY (idetapa) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_ejecucionplanificacionproduccion_estado (OID = 131500) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT fk_ejecucionplanificacionproduccion_estado FOREIGN KEY (estado) REFERENCES estadoejecplanifpedido(idestado);
--
-- Definition for index fk_ejecucionplanificacionproduccion_idplanificacionproduccion (OID = 131505) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT fk_ejecucionplanificacionproduccion_idplanificacionproduccion FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index fk_detallepresupuesto_idpresupuesto (OID = 131510) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT fk_detallepresupuesto_idpresupuesto FOREIGN KEY (idpresupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index fk_detallepresupuesto_idproducto (OID = 131515) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT fk_detallepresupuesto_idproducto FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index fk_domicilio_barrio (OID = 131520) : 
--
ALTER TABLE ONLY domicilio
    ADD CONSTRAINT fk_domicilio_barrio FOREIGN KEY (barrio) REFERENCES barrio(idbarrio);
--
-- Definition for index fk_empleado_cargo (OID = 131525) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_cargo FOREIGN KEY (cargo) REFERENCES cargo(idcargo);
--
-- Definition for index fk_empleado_categoria (OID = 131530) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_categoria FOREIGN KEY (categoria) REFERENCES categoria(idcategoria);
--
-- Definition for index fk_empleado_domicilio (OID = 131535) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_empleado_tipodocumento (OID = 131540) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_tipodocumento FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index fk_empleado_usuario (OID = 131545) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_detalleplanprocedimientos_idetapaproduccion (OID = 131550) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT fk_detalleplanprocedimientos_idetapaproduccion FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_detalleplanprocedimientos_idplanpprocedimientos (OID = 131555) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT fk_detalleplanprocedimientos_idplanpprocedimientos FOREIGN KEY (idplanpprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index fk_reclamocliente_cliente (OID = 131560) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT fk_reclamocliente_cliente FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index fk_reclamocliente_tiporeclamo (OID = 131565) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT fk_reclamocliente_tiporeclamo FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index fk_comprobantepago_usuario (OID = 131570) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT fk_comprobantepago_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_comprobantepago_factura (OID = 131575) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT fk_comprobantepago_factura FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index fk_comprobantepago_formadepago (OID = 131580) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT fk_comprobantepago_formadepago FOREIGN KEY (formadepago) REFERENCES formadepago(idformapago);
--
-- Definition for index fk_mpasignadaxpiezareal_iddetallempasignada (OID = 131585) : 
--
ALTER TABLE ONLY mpasignadaxpiezareal
    ADD CONSTRAINT fk_mpasignadaxpiezareal_iddetallempasignada FOREIGN KEY (iddetallempasignada) REFERENCES detallempasignada(id);
--
-- Definition for index fk_detalleproductopresupuesto_idpieza (OID = 131590) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT fk_detalleproductopresupuesto_idpieza FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index fk_detalleproductopresupuesto_iddetallepresupuesto (OID = 131595) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT fk_detalleproductopresupuesto_iddetallepresupuesto FOREIGN KEY (iddetallepresupuesto) REFERENCES detallepresupuesto(iddetalle);
--
-- Definition for index fk_detalleproductopresupuesto_idmateriaprima (OID = 131600) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT fk_detalleproductopresupuesto_idmateriaprima FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detalletrabajotercerizado_estado (OID = 131605) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT fk_detalletrabajotercerizado_estado FOREIGN KEY (estado) REFERENCES estadodetalletrabajotercerizado(idestado);
--
-- Definition for index fk_detalletrabajotercerizado_idtrabajotercerizado (OID = 131610) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT fk_detalletrabajotercerizado_idtrabajotercerizado FOREIGN KEY (idtrabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index fk_detalletrabajotercerizado_proceso (OID = 131615) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT fk_detalletrabajotercerizado_proceso FOREIGN KEY (proceso) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index ejecucionetapaproduccion_pkey (OID = 131620) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_pkey PRIMARY KEY (id);
--
-- Definition for index detalleejecucionplanificacion_fk (OID = 131622) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk FOREIGN KEY (idejecucionplanificacionproduccion) REFERENCES ejecucionplanificacionproduccion(idejecucion);
--
-- Definition for index detalleejecucionplanificacion_fk1 (OID = 131627) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk1 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleejecucionplanificacion_fk2 (OID = 131632) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk2 FOREIGN KEY (ejecucionetapa) REFERENCES ejecucionetapaproduccion(id);
--
-- Definition for index detalleejecucionplanificacion_fk3 (OID = 131637) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk3 FOREIGN KEY (piezareal) REFERENCES piezareal(idpiezareal);
--
-- Definition for index detalleplanificacionproduccion_fk4 (OID = 131642) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk4 FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index reclamoempresamantenimiento_pkey (OID = 131647) : 
--
ALTER TABLE ONLY reclamoempresamantenimiento
    ADD CONSTRAINT reclamoempresamantenimiento_pkey PRIMARY KEY (idreclamo);
--
-- Definition for index fk_reclamoempresamantenimiento_tiporeclamo (OID = 131649) : 
--
ALTER TABLE ONLY reclamoempresamantenimiento
    ADD CONSTRAINT fk_reclamoempresamantenimiento_tiporeclamo FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index fk_reclamoempresamantenimiento_trabajotercerizado (OID = 131654) : 
--
ALTER TABLE ONLY reclamoempresamantenimiento
    ADD CONSTRAINT fk_reclamoempresamantenimiento_trabajotercerizado FOREIGN KEY (trabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index reclamoempresamantenimiento_fk (OID = 131659) : 
--
ALTER TABLE ONLY reclamoempresamantenimiento
    ADD CONSTRAINT reclamoempresamantenimiento_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index reclamoempresamantenimiento_fk1 (OID = 131664) : 
--
ALTER TABLE ONLY reclamoempresamantenimiento
    ADD CONSTRAINT reclamoempresamantenimiento_fk1 FOREIGN KEY (trabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index detallereclamoempresamantenimiento_idx (OID = 131669) : 
--
ALTER TABLE ONLY detallereclamoempresamantenimiento
    ADD CONSTRAINT detallereclamoempresamantenimiento_idx PRIMARY KEY (iddetalle);
--
-- Definition for index detallereclamoempresamantenimiento_fk (OID = 131671) : 
--
ALTER TABLE ONLY detallereclamoempresamantenimiento
    ADD CONSTRAINT detallereclamoempresamantenimiento_fk FOREIGN KEY (idreclamo) REFERENCES reclamoempresamantenimiento(idreclamo);
--
-- Definition for index matriz_fk1 (OID = 131676) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT matriz_fk1 FOREIGN KEY (tipomaterial) REFERENCES tipomaterial(idtipomaterial);
--
-- Definition for index detalleejecucionplanificacion_fk4 (OID = 131681) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk4 FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index estadoreclamo_pkey (OID = 131686) : 
--
ALTER TABLE ONLY estadoreclamo
    ADD CONSTRAINT estadoreclamo_pkey PRIMARY KEY (idestadoreclamo);
--
-- Definition for index reclamocliente_fk2 (OID = 131688) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_fk2 FOREIGN KEY (idestadoreclamo) REFERENCES estadoreclamo(idestadoreclamo);
--
-- Definition for index reclamoempresamantenimiento_fk2 (OID = 131693) : 
--
ALTER TABLE ONLY reclamoempresamantenimiento
    ADD CONSTRAINT reclamoempresamantenimiento_fk2 FOREIGN KEY (idestadoreclamo) REFERENCES estadoreclamo(idestadoreclamo);
--
-- Definition for index reclamoempresametalurgica_fk2 (OID = 131698) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_fk2 FOREIGN KEY (idestadoreclamo) REFERENCES estadoreclamo(idestadoreclamo);
--
-- Definition for index reclamoproveedor_fk2 (OID = 131703) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_fk2 FOREIGN KEY (idestadoreclamo) REFERENCES estadoreclamo(idestadoreclamo);
--
-- Definition for index mpasignadaxpiezareal_fk (OID = 131708) : 
--
ALTER TABLE ONLY mpasignadaxpiezareal
    ADD CONSTRAINT mpasignadaxpiezareal_fk FOREIGN KEY (idpiezareal) REFERENCES piezareal(idpiezareal);
--
-- Definition for index detalletrabajotercerizado_iddetalle_key (OID = 131713) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_iddetalle_key PRIMARY KEY (iddetalle);
--
-- Definition for index detallereclamoempresamantenimiento_fk1 (OID = 131715) : 
--
ALTER TABLE ONLY detallereclamoempresamantenimiento
    ADD CONSTRAINT detallereclamoempresamantenimiento_fk1 FOREIGN KEY (iddetalletrabajo) REFERENCES detalletrabajotercerizado(iddetalle);
--
-- Definition for index detallereclamoempresamantenimiento_fk2 (OID = 131720) : 
--
ALTER TABLE ONLY detallereclamoempresamantenimiento
    ADD CONSTRAINT detallereclamoempresamantenimiento_fk2 FOREIGN KEY (idtrabajo) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index detalleplanificacioncalidad_iddetalle_key (OID = 131725) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_iddetalle_key PRIMARY KEY (iddetalle);
--
-- Definition for index detalleplanificacioncalidad_fk3 (OID = 131727) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk3 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleplanificacioncalidad_fk4 (OID = 131732) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk4 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detalleplanificacioncalidad_fk1 (OID = 131737) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk1 FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index detalleplanificacioncalidad_fk5 (OID = 131742) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk5 FOREIGN KEY (maquina) REFERENCES maquina(idmaquina);
--
-- Definition for index detalleejecucionplanificacioncalidad_iddetalle_key (OID = 131747) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_iddetalle_key PRIMARY KEY (iddetalle);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk2 (OID = 131749) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk2 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk3 (OID = 131754) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk3 FOREIGN KEY (piezareal) REFERENCES piezareal(idpiezareal);
--
-- Definition for index ejecucionplanificacioncalidad_idejecucion_key (OID = 131759) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_idejecucion_key PRIMARY KEY (idejecucion);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk (OID = 131761) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk FOREIGN KEY (idejecucionplanificacioncalidad) REFERENCES ejecucionplanificacioncalidad(idejecucion);
--
-- Definition for index ejecucionprocesocalidad_pkey (OID = 131766) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_pkey PRIMARY KEY (idejecucion);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk1 (OID = 131768) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk1 FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk4 (OID = 131773) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk4 FOREIGN KEY (ejecucionprocesocalidad) REFERENCES ejecucionprocesocalidad(idejecucion);
--
-- Definition for index detalleproductoreal_pkey (OID = 131778) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detalleproductoreal_fk1 (OID = 131780) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_fk1 FOREIGN KEY (idpiezareal) REFERENCES piezareal(idpiezareal);
--
-- Definition for index piezareal_fk (OID = 131785) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_fk FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index productoreal_fk (OID = 131790) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detalleproductoreal_fk2 (OID = 131795) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_fk2 FOREIGN KEY (detalleproducto) REFERENCES detalleproducto(iddetalle);
--
-- Definition for index ejecucionprocesocalidad_fk (OID = 131800) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_fk FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index detalleplanificacioncalidad_fk6 (OID = 131805) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk6 FOREIGN KEY (iddetalleejecucionplanificacioncalidad) REFERENCES detalleejecucionplanificacioncalidad(iddetalle);
--
-- Definition for index detalleplanificacionproduccion_fk5 (OID = 131810) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk5 FOREIGN KEY (detalleanterior) REFERENCES detalleplanificacionproduccion(id);
--
-- Definition for index ejecucionetapaproduccion_fk3 (OID = 131815) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk3 FOREIGN KEY (maquina) REFERENCES maquina(idmaquina);
--
-- Definition for index detallemantenimientopreventivo_pkey (OID = 131820) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detallemantenimientopreventivo_fk (OID = 131822) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_fk FOREIGN KEY (idmantenimientopreventivo) REFERENCES mantenimientopreventivo(idmantenimientopreventivo);
--
-- Definition for index detallemantenimientopreventivo_fk1 (OID = 131827) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_fk1 FOREIGN KEY (servicio) REFERENCES servicio(idservicio);
--
-- Definition for index detalleplanificacioncalidad_fk7 (OID = 131832) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk7 FOREIGN KEY (detalleanterior) REFERENCES detalleplanificacioncalidad(iddetalle);
--
-- Definition for index ejecucionprocesocalidad_fk2 (OID = 131837) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_fk2 FOREIGN KEY (maquina) REFERENCES maquina(idmaquina);
--
-- Definition for index reclamoempresamantenimiento_fk3 (OID = 131842) : 
--
ALTER TABLE ONLY reclamoempresamantenimiento
    ADD CONSTRAINT reclamoempresamantenimiento_fk3 FOREIGN KEY (mantenimientopreventivo) REFERENCES mantenimientopreventivo(idmantenimientopreventivo);
--
-- Definition for index reclamoempresamantenimiento_fk4 (OID = 131847) : 
--
ALTER TABLE ONLY reclamoempresamantenimiento
    ADD CONSTRAINT reclamoempresamantenimiento_fk4 FOREIGN KEY (mantenimientocorrectivo) REFERENCES mantenimientocorrectivo(idmantenimientocorrectivo);
--
-- Definition for index estadomantpreventivo_pkey (OID = 131852) : 
--
ALTER TABLE ONLY estadomantpreventivo
    ADD CONSTRAINT estadomantpreventivo_pkey PRIMARY KEY (idestado);
--
-- Definition for index mantenimientopreventivo_fk1 (OID = 131854) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_fk1 FOREIGN KEY (estado) REFERENCES estadomantpreventivo(idestado);
--
-- Definition for index detallemantenimientocorrectivo_pkey (OID = 131859) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index etapadeproduccion_fk (OID = 131861) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_fk FOREIGN KEY (tipomaquina) REFERENCES tipomaquina(idtipomaquina);
--
-- Data for sequence public.prueba_id_seq (OID = 129104)
--
SELECT pg_catalog.setval('prueba_id_seq', 1, false);
--
-- Data for sequence public.usuario_idusuario_seq (OID = 129110)
--
SELECT pg_catalog.setval('usuario_idusuario_seq', 31, true);
--
-- Data for sequence public.tipomaterial_idtipomaterial_seq (OID = 129112)
--
SELECT pg_catalog.setval('tipomaterial_idtipomaterial_seq', 8, true);
--
-- Data for sequence public.accioncalidad_idaccioncalidad_seq (OID = 129114)
--
SELECT pg_catalog.setval('accioncalidad_idaccioncalidad_seq', 4, true);
--
-- Data for sequence public.barrio_idbarrio_seq (OID = 129116)
--
SELECT pg_catalog.setval('barrio_idbarrio_seq', 85, true);
--
-- Data for sequence public.cargo_idcargo_seq (OID = 129118)
--
SELECT pg_catalog.setval('cargo_idcargo_seq', 13, true);
--
-- Data for sequence public.categoria_idcategoria_seq (OID = 129120)
--
SELECT pg_catalog.setval('categoria_idcategoria_seq', 5, true);
--
-- Data for sequence public.cliente_idcliente_seq (OID = 129122)
--
SELECT pg_catalog.setval('cliente_idcliente_seq', 33, true);
--
-- Data for sequence public.codigodebarra_idcodigo_seq (OID = 129124)
--
SELECT pg_catalog.setval('codigodebarra_idcodigo_seq', 212, true);
--
-- Data for sequence public.compra_idcompra_seq (OID = 129126)
--
SELECT pg_catalog.setval('compra_idcompra_seq', 2, true);
--
-- Data for sequence public.comprobantepago_idcomprobantepago_seq (OID = 129128)
--
SELECT pg_catalog.setval('comprobantepago_idcomprobantepago_seq', 13, true);
--
-- Data for sequence public.condicioniva_idcondicioniva_seq (OID = 129130)
--
SELECT pg_catalog.setval('condicioniva_idcondicioniva_seq', 3, true);
--
-- Data for sequence public.detalleplanificacionproduccion_id_seq (OID = 129132)
--
SELECT pg_catalog.setval('detalleplanificacionproduccion_id_seq', 224, true);
--
-- Data for sequence public.detallecompra_iddetalle_seq (OID = 129134)
--
SELECT pg_catalog.setval('detallecompra_iddetalle_seq', 3, true);
--
-- Data for sequence public.detalleejecucionplanificacion_iddetalle_seq (OID = 129136)
--
SELECT pg_catalog.setval('detalleejecucionplanificacion_iddetalle_seq', 93, true);
--
-- Data for sequence public.detalleejecucionplanificacioncalidad_iddetalle_seq (OID = 129138)
--
SELECT pg_catalog.setval('detalleejecucionplanificacioncalidad_iddetalle_seq', 39, true);
--
-- Data for sequence public.detallefactura_iddetalle_seq (OID = 129140)
--
SELECT pg_catalog.setval('detallefactura_iddetalle_seq', 14, true);
--
-- Data for sequence public.detallemantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 129142)
--
SELECT pg_catalog.setval('detallemantenimientocorrectivo_idmantenimientocorrectivo_seq', 1, false);
--
-- Data for sequence public.detallemantenimientopreventivo_idmantenimientopreventivo_seq (OID = 129144)
--
SELECT pg_catalog.setval('detallemantenimientopreventivo_idmantenimientopreventivo_seq', 1, false);
--
-- Data for sequence public.detallepedido_iddetalle_seq (OID = 129146)
--
SELECT pg_catalog.setval('detallepedido_iddetalle_seq', 88, true);
--
-- Data for sequence public.detalleplanificacioncalidad_iddetalle_seq (OID = 129148)
--
SELECT pg_catalog.setval('detalleplanificacioncalidad_iddetalle_seq', 77, true);
--
-- Data for sequence public.detalleplanprocedimientos_iddetalle_seq (OID = 129150)
--
SELECT pg_catalog.setval('detalleplanprocedimientos_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleplanprocesoscalidad_iddetalle_seq (OID = 129152)
--
SELECT pg_catalog.setval('detalleplanprocesoscalidad_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallepresupuesto_iddetalle_seq (OID = 129154)
--
SELECT pg_catalog.setval('detallepresupuesto_iddetalle_seq', 99, true);
--
-- Data for sequence public.detalleproducto_iddetalle_seq (OID = 129156)
--
SELECT pg_catalog.setval('detalleproducto_iddetalle_seq', 36, true);
--
-- Data for sequence public.detalleproductoreal_iddetalle_seq (OID = 129158)
--
SELECT pg_catalog.setval('detalleproductoreal_iddetalle_seq', 63, true);
--
-- Data for sequence public.detallereclamocliente_iddetalle_seq (OID = 129160)
--
SELECT pg_catalog.setval('detallereclamocliente_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallereclamoempresametalurgica_iddetalle_seq (OID = 129162)
--
SELECT pg_catalog.setval('detallereclamoempresametalurgica_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallereclamoproveedor_iddetalle_seq (OID = 129164)
--
SELECT pg_catalog.setval('detallereclamoproveedor_iddetalle_seq', 1, true);
--
-- Data for sequence public.detalleremito_iddetalle_seq (OID = 129166)
--
SELECT pg_catalog.setval('detalleremito_iddetalle_seq', 18, true);
--
-- Data for sequence public.detallerequerimientosmateriaprima_iddetalle_seq (OID = 129168)
--
SELECT pg_catalog.setval('detallerequerimientosmateriaprima_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalletrabajotercerizado_iddetalle_seq (OID = 129170)
--
SELECT pg_catalog.setval('detalletrabajotercerizado_iddetalle_seq', 2, true);
--
-- Data for sequence public.domicilio_iddomicilio_seq (OID = 129172)
--
SELECT pg_catalog.setval('domicilio_iddomicilio_seq', 104, true);
--
-- Data for sequence public.ejecucionetapaproduccion_idejecucion_seq (OID = 129174)
--
SELECT pg_catalog.setval('ejecucionetapaproduccion_idejecucion_seq', 93, true);
--
-- Data for sequence public.ejecucionplanificacioncalidad_idejecucion_seq (OID = 129176)
--
SELECT pg_catalog.setval('ejecucionplanificacioncalidad_idejecucion_seq', 12, true);
--
-- Data for sequence public.ejecucionplanificacionproduccion_idejecucion_seq (OID = 129178)
--
SELECT pg_catalog.setval('ejecucionplanificacionproduccion_idejecucion_seq', 55, true);
--
-- Data for sequence public.ejecucionprocesocalidad_idejecucion_seq (OID = 129180)
--
SELECT pg_catalog.setval('ejecucionprocesocalidad_idejecucion_seq', 39, true);
--
-- Data for sequence public.empleado_idempleado_seq (OID = 129182)
--
SELECT pg_catalog.setval('empleado_idempleado_seq', 12, true);
--
-- Data for sequence public.empresametalurgica_idempresametalurgica_seq (OID = 129184)
--
SELECT pg_catalog.setval('empresametalurgica_idempresametalurgica_seq', 5, true);
--
-- Data for sequence public.estadocliente_idestado_seq (OID = 129186)
--
SELECT pg_catalog.setval('estadocliente_idestado_seq', 4, true);
--
-- Data for sequence public.estadocompra_idestado_seq (OID = 129188)
--
SELECT pg_catalog.setval('estadocompra_idestado_seq', 10, true);
--
-- Data for sequence public.estadodetallecompra_idestado_seq (OID = 129190)
--
SELECT pg_catalog.setval('estadodetallecompra_idestado_seq', 8, true);
--
-- Data for sequence public.estadodetalletrabajotercerizado_idestado_seq (OID = 129192)
--
SELECT pg_catalog.setval('estadodetalletrabajotercerizado_idestado_seq', 9, true);
--
-- Data for sequence public.estadoejecetapaprod_idestado_seq (OID = 129194)
--
SELECT pg_catalog.setval('estadoejecetapaprod_idestado_seq', 6, true);
--
-- Data for sequence public.estadoejecplancalidad_idestado_seq (OID = 129196)
--
SELECT pg_catalog.setval('estadoejecplancalidad_idestado_seq', 5, true);
--
-- Data for sequence public.estadoejecplanifpedido_idestado_seq (OID = 129198)
--
SELECT pg_catalog.setval('estadoejecplanifpedido_idestado_seq', 7, true);
--
-- Data for sequence public.estadoejecucionprocesocalidad_idestado_seq (OID = 129200)
--
SELECT pg_catalog.setval('estadoejecucionprocesocalidad_idestado_seq', 7, true);
--
-- Data for sequence public.estadofactura_idestado_seq (OID = 129202)
--
SELECT pg_catalog.setval('estadofactura_idestado_seq', 2, true);
--
-- Data for sequence public.estadomaquina_idestado_seq (OID = 129204)
--
SELECT pg_catalog.setval('estadomaquina_idestado_seq', 1, true);
--
-- Data for sequence public.estadopedido_idestado_seq (OID = 129206)
--
SELECT pg_catalog.setval('estadopedido_idestado_seq', 19, true);
--
-- Data for sequence public.estadopiezareal_idestado_seq (OID = 129208)
--
SELECT pg_catalog.setval('estadopiezareal_idestado_seq', 1, true);
--
-- Data for sequence public.estadoproductoreal_idestado_seq (OID = 129210)
--
SELECT pg_catalog.setval('estadoproductoreal_idestado_seq', 11, true);
--
-- Data for sequence public.estadoremito_idestado_seq (OID = 129212)
--
SELECT pg_catalog.setval('estadoremito_idestado_seq', 1, true);
--
-- Data for sequence public.estadotrabajotercerizado_idestado_seq (OID = 129214)
--
SELECT pg_catalog.setval('estadotrabajotercerizado_idestado_seq', 14, true);
--
-- Data for sequence public.etapadeproduccion_idetapaproduccion_seq (OID = 129216)
--
SELECT pg_catalog.setval('etapadeproduccion_idetapaproduccion_seq', 24, true);
--
-- Data for sequence public.factura_idfactura_seq (OID = 129218)
--
SELECT pg_catalog.setval('factura_idfactura_seq', 8, true);
--
-- Data for sequence public.formadepago_idformapago_seq (OID = 129220)
--
SELECT pg_catalog.setval('formadepago_idformapago_seq', 3, true);
--
-- Data for sequence public.localidad_idlocalidad_seq (OID = 129222)
--
SELECT pg_catalog.setval('localidad_idlocalidad_seq', 50, true);
--
-- Data for sequence public.mantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 129224)
--
SELECT pg_catalog.setval('mantenimientocorrectivo_idmantenimientocorrectivo_seq', 1, false);
--
-- Data for sequence public.mantenimientopreventivo_idmantenimientopreventivo_seq (OID = 129226)
--
SELECT pg_catalog.setval('mantenimientopreventivo_idmantenimientopreventivo_seq', 1, false);
--
-- Data for sequence public.maquina_idmaquina_seq (OID = 129228)
--
SELECT pg_catalog.setval('maquina_idmaquina_seq', 20, true);
--
-- Data for sequence public.marca_idmarca_seq (OID = 129230)
--
SELECT pg_catalog.setval('marca_idmarca_seq', 3, true);
--
-- Data for sequence public.materiaprima_idmateriaprima_seq (OID = 129232)
--
SELECT pg_catalog.setval('materiaprima_idmateriaprima_seq', 31, true);
--
-- Data for sequence public.matriz_idmatriz_seq (OID = 129234)
--
SELECT pg_catalog.setval('matriz_idmatriz_seq', 7, true);
--
-- Data for sequence public.pedido_idpedido_seq (OID = 129236)
--
SELECT pg_catalog.setval('pedido_idpedido_seq', 64, true);
--
-- Data for sequence public.pedidomatriz_idpedidomatriz_seq (OID = 129238)
--
SELECT pg_catalog.setval('pedidomatriz_idpedidomatriz_seq', 1, false);
--
-- Data for sequence public.pieza_idpieza_seq (OID = 129240)
--
SELECT pg_catalog.setval('pieza_idpieza_seq', 15, true);
--
-- Data for sequence public.piezareal_idpiezareal_seq (OID = 129242)
--
SELECT pg_catalog.setval('piezareal_idpiezareal_seq', 1, false);
--
-- Data for sequence public.planificacioncalidad_idplanificacion_seq (OID = 129244)
--
SELECT pg_catalog.setval('planificacioncalidad_idplanificacion_seq', 19, true);
--
-- Data for sequence public.planificacionproduccion_idplanificacionproduccion_seq (OID = 129246)
--
SELECT pg_catalog.setval('planificacionproduccion_idplanificacionproduccion_seq', 84, true);
--
-- Data for sequence public.plano_idplano_seq (OID = 129248)
--
SELECT pg_catalog.setval('plano_idplano_seq', 1, false);
--
-- Data for sequence public.planprocedimientos_idplanprocedimientos_seq (OID = 129250)
--
SELECT pg_catalog.setval('planprocedimientos_idplanprocedimientos_seq', 1, false);
--
-- Data for sequence public.planprocesoscalidad_idplanprocesoscalidad_seq (OID = 129252)
--
SELECT pg_catalog.setval('planprocesoscalidad_idplanprocesoscalidad_seq', 1, false);
--
-- Data for sequence public.planrequerimientosmateriaprima_idplanrequerimientosmateriaprima (OID = 129254)
--
SELECT pg_catalog.setval('planrequerimientosmateriaprima_idplanrequerimientosmateriaprima', 1, false);
--
-- Data for sequence public.presupuesto_idpresupuesto_seq (OID = 129256)
--
SELECT pg_catalog.setval('presupuesto_idpresupuesto_seq', 78, true);
--
-- Data for sequence public.prioridad_idprioridad_seq (OID = 129258)
--
SELECT pg_catalog.setval('prioridad_idprioridad_seq', 3, true);
--
-- Data for sequence public.privilegio_idprivilegio_seq (OID = 129260)
--
SELECT pg_catalog.setval('privilegio_idprivilegio_seq', 1, false);
--
-- Data for sequence public.procesocalidad_idprocesocalidad_seq (OID = 129262)
--
SELECT pg_catalog.setval('procesocalidad_idprocesocalidad_seq', 10, true);
--
-- Data for sequence public.producto_idproducto_seq (OID = 129264)
--
SELECT pg_catalog.setval('producto_idproducto_seq', 25, true);
--
-- Data for sequence public.productoreal_idproductoreal_seq (OID = 129266)
--
SELECT pg_catalog.setval('productoreal_idproductoreal_seq', 27, true);
--
-- Data for sequence public.proveedor_idproveedor_seq (OID = 129268)
--
SELECT pg_catalog.setval('proveedor_idproveedor_seq', 8, true);
--
-- Data for sequence public.proveedormantenimientomaquina_idproveedormantenimiento_seq (OID = 129270)
--
SELECT pg_catalog.setval('proveedormantenimientomaquina_idproveedormantenimiento_seq', 1, false);
--
-- Data for sequence public.provincia_idprovincia_seq (OID = 129272)
--
SELECT pg_catalog.setval('provincia_idprovincia_seq', 14, true);
--
-- Data for sequence public.reclamocliente_idreclamo_seq (OID = 129274)
--
SELECT pg_catalog.setval('reclamocliente_idreclamo_seq', 1, false);
--
-- Data for sequence public.reclamoempresametalurgica_idreclamo_seq (OID = 129276)
--
SELECT pg_catalog.setval('reclamoempresametalurgica_idreclamo_seq', 1, false);
--
-- Data for sequence public.reclamoproveedor_idreclamo_seq (OID = 129278)
--
SELECT pg_catalog.setval('reclamoproveedor_idreclamo_seq', 2, true);
--
-- Data for sequence public.remito_idremito_seq (OID = 129280)
--
SELECT pg_catalog.setval('remito_idremito_seq', 11, true);
--
-- Data for sequence public.responsable_idresponsable_seq (OID = 129282)
--
SELECT pg_catalog.setval('responsable_idresponsable_seq', 44, true);
--
-- Data for sequence public.rol_idrol_seq (OID = 129284)
--
SELECT pg_catalog.setval('rol_idrol_seq', 12, true);
--
-- Data for sequence public.rotura_idrotura_seq (OID = 129286)
--
SELECT pg_catalog.setval('rotura_idrotura_seq', 7, true);
--
-- Data for sequence public.servicio_idservicio_seq (OID = 129288)
--
SELECT pg_catalog.setval('servicio_idservicio_seq', 1, false);
--
-- Data for sequence public.sesion_idsesion_seq (OID = 129290)
--
SELECT pg_catalog.setval('sesion_idsesion_seq', 1, false);
--
-- Data for sequence public.tipodocumento_idtipodocumento_seq (OID = 129292)
--
SELECT pg_catalog.setval('tipodocumento_idtipodocumento_seq', 3, true);
--
-- Data for sequence public.tipoiva_idtipoiva_seq (OID = 129294)
--
SELECT pg_catalog.setval('tipoiva_idtipoiva_seq', 1, false);
--
-- Data for sequence public.tipomaquina_idtipomaquina_seq (OID = 129296)
--
SELECT pg_catalog.setval('tipomaquina_idtipomaquina_seq', 6, true);
--
-- Data for sequence public.tiporeclamo_idtiporeclamo_seq (OID = 129298)
--
SELECT pg_catalog.setval('tiporeclamo_idtiporeclamo_seq', 1, true);
--
-- Data for sequence public.trabajotercerizado_idtrabajo_seq (OID = 129300)
--
SELECT pg_catalog.setval('trabajotercerizado_idtrabajo_seq', 2, true);
--
-- Data for sequence public.turno_idturno_seq (OID = 129302)
--
SELECT pg_catalog.setval('turno_idturno_seq', 3, true);
--
-- Data for sequence public.unidadmedida_idunidadmedida_seq (OID = 129312)
--
SELECT pg_catalog.setval('unidadmedida_idunidadmedida_seq', 4, true);
--
-- Data for sequence public.detallepiezapresupuesto_iddetalle_seq (OID = 129322)
--
SELECT pg_catalog.setval('detallepiezapresupuesto_iddetalle_seq', 184, true);
--
-- Data for sequence public.detalleproductopresupuesto_iddetalle_seq (OID = 129328)
--
SELECT pg_catalog.setval('detalleproductopresupuesto_iddetalle_seq', 160, true);
--
-- Data for sequence public.detallepiezacalidadpresupuesto_iddetalle_seq (OID = 129334)
--
SELECT pg_catalog.setval('detallepiezacalidadpresupuesto_iddetalle_seq', 146, true);
--
-- Data for sequence public.calendario_id_seq (OID = 129357)
--
SELECT pg_catalog.setval('calendario_id_seq', 12, true);
--
-- Data for sequence public.disponibilidadhoraria_id_seq (OID = 129367)
--
SELECT pg_catalog.setval('disponibilidadhoraria_id_seq', 267, true);
--
-- Data for sequence public.estadoplanificacionproduccion_id_seq (OID = 129373)
--
SELECT pg_catalog.setval('estadoplanificacionproduccion_id_seq', 3, true);
--
-- Data for sequence public.maquina_idmaquina_seq1 (OID = 129382)
--
SELECT pg_catalog.setval('maquina_idmaquina_seq1', 12, true);
--
-- Data for sequence public.piezareal_idpiezareal_seq1 (OID = 129388)
--
SELECT pg_catalog.setval('piezareal_idpiezareal_seq1', 184, true);
--
-- Data for sequence public.pieza_idpieza_seq1 (OID = 129394)
--
SELECT pg_catalog.setval('pieza_idpieza_seq1', 23, true);
--
-- Data for sequence public.detallempasignada_id_seq (OID = 129400)
--
SELECT pg_catalog.setval('detallempasignada_id_seq', 86, true);
--
-- Data for sequence public.mpasignadaxpiezareal_id_seq (OID = 129406)
--
SELECT pg_catalog.setval('mpasignadaxpiezareal_id_seq', 169, true);
--
-- Data for sequence public.reclamoempresamantenimiento_idreclamo_seq (OID = 129445)
--
SELECT pg_catalog.setval('reclamoempresamantenimiento_idreclamo_seq', 1, false);
--
-- Data for sequence public.detallereclamoempresamantenimiento_iddetalle_seq (OID = 129451)
--
SELECT pg_catalog.setval('detallereclamoempresamantenimiento_iddetalle_seq', 1, false);
--
-- Data for sequence public.estadoreclamo_idestadoreclamo_seq (OID = 129457)
--
SELECT pg_catalog.setval('estadoreclamo_idestadoreclamo_seq', 5, true);
--
-- Data for sequence public.detallemantenimientopreventivo_iddetalle_seq (OID = 129475)
--
SELECT pg_catalog.setval('detallemantenimientopreventivo_iddetalle_seq', 1, false);
--
-- Data for sequence public.estadomantpreventivo_idestado_seq (OID = 129484)
--
SELECT pg_catalog.setval('estadomantpreventivo_idestado_seq', 6, true);
--
-- Data for sequence public.detallemantenimientocorrectivo_iddetalle_seq (OID = 129486)
--
SELECT pg_catalog.setval('detallemantenimientocorrectivo_iddetalle_seq', 1, false);
--
-- Comments
--
COMMENT ON SCHEMA public IS 'standard public schema';
