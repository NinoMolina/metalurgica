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
--
-- Definition for function nvonropedido (OID = 147964) : 
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
-- Definition for function cantpiezasxproducto (OID = 147965) : 
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
-- Definition for function esproductosinalgunaetapa (OID = 147966) : 
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
-- Definition for function cantpiezasdepedido (OID = 147967) : 
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
-- Definition for function cantpiezasdepedido2 (OID = 147968) : 
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
-- Definition for function nvonropresupuesto (OID = 147969) : 
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
-- Definition for function ingresoxpedido (OID = 147970) : 
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
-- Definition for function nvonrocliente (OID = 147971) : 
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
-- Definition for function nvonromateriaprima (OID = 147972) : 
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
-- Definition for function nvonroempleado (OID = 147973) : 
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
-- Definition for function nvonromaquina (OID = 147974) : 
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
-- Definition for function nvonroejecucionplanificacion (OID = 147975) : 
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
-- Definition for function nvonroplanificacionproduccion (OID = 147976) : 
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
-- Definition for function nvonroejecucionetapa (OID = 147977) : 
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
-- Definition for function nvonropiezareal (OID = 147978) : 
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
-- Definition for function nvonroempresametalurgica (OID = 147979) : 
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
-- Definition for function nvonropedidomatriz (OID = 147980) : 
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
-- Definition for function nvonroproveedormantenimiento (OID = 147981) : 
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
-- Definition for function nvonroproveedor (OID = 147982) : 
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
-- Definition for function nvonromantenimientopreventivo (OID = 147983) : 
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
-- Definition for function nvonrotrabajotercerizado (OID = 147984) : 
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
-- Definition for function esulejecetapaprod (OID = 147985) : 
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
-- Definition for function esulejecetapadepieza (OID = 147986) : 
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
-- Definition for function horafinprevistaplanifprod (OID = 147987) : 
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
-- Definition for function nvonroplanificacioncalidad (OID = 147988) : 
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
-- Definition for function nvonroproductoreal (OID = 147989) : 
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
-- Definition for function nvonroejecplanifcalidad (OID = 147990) : 
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
-- Definition for function nvonroejecprocalidad (OID = 147991) : 
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
-- Definition for function esulejecprocalpieza (OID = 147992) : 
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
-- Definition for function esulejecprocalcalidad (OID = 147993) : 
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
-- Definition for function nvonromantcorrectivo (OID = 147994) : 
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
-- Definition for function cantidadmpenpedido (OID = 147995) : 
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
-- Definition for function cantidadmpasignada (OID = 147996) : 
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
-- Definition for function cantidadmpenpedido (OID = 147997) : 
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
-- Definition for function cantidadmpasignada (OID = 147998) : 
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
-- Definition for function nvonroetapa (OID = 147999) : 
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
-- Definition for function nvonroproducto (OID = 148000) : 
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
-- Structure for table pedido (OID = 148001) : 
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
-- Structure for table planificacioncalidad (OID = 148009) : 
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
-- Structure for table estadopedido (OID = 148016) : 
--
CREATE TABLE public.estadopedido (
    idestado bigint DEFAULT nextval(('"public"."estadopedido_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table factura (OID = 148023) : 
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
-- Structure for table planificacionproduccion (OID = 148030) : 
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
-- Structure for table presupuesto (OID = 148037) : 
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
-- Structure for table plano (OID = 148042) : 
--
CREATE TABLE public.plano (
    idplano bigint DEFAULT nextval(('"public"."plano_idplano_seq"'::text)::regclass) NOT NULL,
    nroplano bigint,
    escala integer,
    pedido bigint,
    imagen bytea[]
) WITH OIDS;
--
-- Structure for table remito (OID = 148049) : 
--
CREATE TABLE public.remito (
    idremito bigint DEFAULT nextval(('"public"."remito_idremito_seq"'::text)::regclass) NOT NULL,
    nroremito bigint,
    fechaemision date,
    pedido bigint,
    estado bigint
) WITH OIDS;
--
-- Structure for table trabajotercerizado (OID = 148053) : 
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
-- Structure for table cliente (OID = 148060) : 
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
-- Structure for table tipoiva (OID = 148067) : 
--
CREATE TABLE public.tipoiva (
    idtipoiva bigint DEFAULT nextval(('"public"."tipoiva_idtipoiva_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table formadepago (OID = 148074) : 
--
CREATE TABLE public.formadepago (
    idformapago bigint DEFAULT nextval(('"public"."formadepago_idformapago_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table usuario (OID = 148081) : 
--
CREATE TABLE public.usuario (
    idusuario bigint DEFAULT nextval(('"public"."usuario_idusuario_seq"'::text)::regclass) NOT NULL,
    usuario character varying,
    clave character varying
) WITH OIDS;
--
-- Structure for table estadofactura (OID = 148088) : 
--
CREATE TABLE public.estadofactura (
    idestado bigint DEFAULT nextval(('"public"."estadofactura_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table comprobantepago (OID = 148095) : 
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
-- Structure for table estadotrabajotercerizado (OID = 148099) : 
--
CREATE TABLE public.estadotrabajotercerizado (
    idestado bigint DEFAULT nextval(('"public"."estadotrabajotercerizado_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table empresametalurgica (OID = 148106) : 
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
-- Structure for table prioridad (OID = 148113) : 
--
CREATE TABLE public.prioridad (
    idprioridad bigint DEFAULT nextval(('"public"."prioridad_idprioridad_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table estadocliente (OID = 148120) : 
--
CREATE TABLE public.estadocliente (
    idestado bigint DEFAULT nextval(('"public"."estadocliente_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table responsable (OID = 148127) : 
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
-- Structure for table condicioniva (OID = 148134) : 
--
CREATE TABLE public.condicioniva (
    idcondicioniva bigint DEFAULT nextval(('"public"."condicioniva_idcondicioniva_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table domicilio (OID = 148141) : 
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
-- Structure for table rotura (OID = 148148) : 
--
CREATE TABLE public.rotura (
    idrotura bigint DEFAULT nextval(('"public"."rotura_idrotura_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table categoria (OID = 148155) : 
--
CREATE TABLE public.categoria (
    idcategoria bigint DEFAULT nextval(('"public"."categoria_idcategoria_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table cargo (OID = 148162) : 
--
CREATE TABLE public.cargo (
    idcargo bigint DEFAULT nextval(('"public"."cargo_idcargo_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table turno (OID = 148169) : 
--
CREATE TABLE public.turno (
    idturno bigint DEFAULT nextval(('"public"."turno_idturno_seq"'::text)::regclass) NOT NULL,
    horainicio time without time zone,
    horafin time without time zone,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table marca (OID = 148176) : 
--
CREATE TABLE public.marca (
    idmarca bigint DEFAULT nextval(('"public"."marca_idmarca_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table estadomaquina (OID = 148183) : 
--
CREATE TABLE public.estadomaquina (
    idestado bigint DEFAULT nextval(('"public"."estadomaquina_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table tipomaquina (OID = 148190) : 
--
CREATE TABLE public.tipomaquina (
    idtipomaquina bigint DEFAULT nextval(('"public"."tipomaquina_idtipomaquina_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table estadoejecetapaprod (OID = 148197) : 
--
CREATE TABLE public.estadoejecetapaprod (
    idestado bigint DEFAULT nextval(('"public"."estadoejecetapaprod_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table servicio (OID = 148204) : 
--
CREATE TABLE public.servicio (
    idservicio bigint DEFAULT nextval(('"public"."servicio_idservicio_seq"'::text)::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table tipodocumento (OID = 148211) : 
--
CREATE TABLE public.tipodocumento (
    idtipodocumento bigint DEFAULT nextval(('"public"."tipodocumento_idtipodocumento_seq"'::text)::regclass) NOT NULL,
    tipo character varying(50),
    nombre character varying(50)
) WITH OIDS;
--
-- Structure for table codigodebarra (OID = 148215) : 
--
CREATE TABLE public.codigodebarra (
    idcodigo bigint DEFAULT nextval(('"public"."codigodebarra_idcodigo_seq"'::text)::regclass) NOT NULL,
    descripcion character varying(50),
    codigo character varying(100)
) WITH OIDS;
--
-- Structure for table estadopiezareal (OID = 148219) : 
--
CREATE TABLE public.estadopiezareal (
    idestado bigint DEFAULT nextval(('"public"."estadopiezareal_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecplanifpedido (OID = 148223) : 
--
CREATE TABLE public.estadoejecplanifpedido (
    idestado bigint DEFAULT nextval(('"public"."estadoejecplanifpedido_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table proveedormantenimientomaquina (OID = 148227) : 
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
-- Structure for table rol (OID = 148234) : 
--
CREATE TABLE public.rol (
    idrol bigint DEFAULT nextval(('"public"."rol_idrol_seq"'::text)::regclass) NOT NULL,
    rol character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Structure for table privilegio (OID = 148241) : 
--
CREATE TABLE public.privilegio (
    idprivilegio bigint DEFAULT nextval(('"public"."privilegio_idprivilegio_seq"'::text)::regclass) NOT NULL,
    privilegio character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Structure for table accioncalidad (OID = 148248) : 
--
CREATE TABLE public.accioncalidad (
    idaccioncalidad bigint DEFAULT nextval(('"public"."accioncalidad_idaccioncalidad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Structure for table empleado (OID = 148255) : 
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
-- Structure for table proveedor (OID = 148259) : 
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
-- Structure for table estadocompra (OID = 148263) : 
--
CREATE TABLE public.estadocompra (
    idestado bigint DEFAULT nextval(('"public"."estadocompra_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Structure for table sesion (OID = 148270) : 
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
-- Structure for table materiaprima (OID = 148274) : 
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
-- Structure for table matriz (OID = 148281) : 
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
-- Structure for table producto (OID = 148288) : 
--
CREATE TABLE public.producto (
    idproducto bigint DEFAULT nextval(('"public"."producto_idproducto_seq"'::text)::regclass) NOT NULL,
    nroproducto bigint,
    nombre character varying(50),
    preciounitario double precision,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table estadoejecplancalidad (OID = 148295) : 
--
CREATE TABLE public.estadoejecplancalidad (
    idestado bigint DEFAULT nextval(('"public"."estadoejecplancalidad_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Structure for table mantenimientopreventivo (OID = 148302) : 
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
-- Structure for table mantenimientocorrectivo (OID = 148306) : 
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
-- Structure for table etapadeproduccion (OID = 148310) : 
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
-- Structure for table ejecucionplanificacionproduccion (OID = 148314) : 
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
-- Structure for table procesocalidad (OID = 148321) : 
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
-- Structure for table ejecucionplanificacioncalidad (OID = 148328) : 
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
-- Structure for table ejecucionprocesocalidad (OID = 148335) : 
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
-- Structure for table ejecucionetapaproduccion (OID = 148342) : 
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
-- Structure for table compra (OID = 148349) : 
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
-- Structure for table detallemantenimientocorrectivo (OID = 148356) : 
--
CREATE TABLE public.detallemantenimientocorrectivo (
    idmantenimientocorrectivo bigint,
    iddetalle bigint DEFAULT nextval(('"public"."detallemantenimientocorrectivo_iddetalle_seq"'::text)::regclass) NOT NULL,
    rotura bigint,
    motivorotura character varying,
    duracion integer
) WITH OIDS;
--
-- Structure for table detallemantenimientopreventivo (OID = 148363) : 
--
CREATE TABLE public.detallemantenimientopreventivo (
    idmantenimientopreventivo bigint NOT NULL,
    iddetalle bigint DEFAULT nextval(('"public"."detallemantenimientopreventivo_iddetalle_seq"'::text)::regclass) NOT NULL,
    servicio bigint,
    observaciones character varying,
    duracion integer
) WITH OIDS;
--
-- Structure for table detalleejecucionplanificacion (OID = 148370) : 
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
-- Structure for table detalleejecucionplanificacioncalidad (OID = 148374) : 
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
-- Structure for table detalleplanificacionproduccion (OID = 148378) : 
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
-- Structure for table detalleproducto (OID = 148382) : 
--
CREATE TABLE public.detalleproducto (
    iddetalle bigint DEFAULT nextval(('"public"."detalleproducto_iddetalle_seq"'::text)::regclass) NOT NULL,
    idproducto bigint NOT NULL,
    cantidadpiezas integer,
    descripcion character varying(50),
    pieza bigint NOT NULL
) WITH OIDS;
--
-- Structure for table detallepedido (OID = 148386) : 
--
CREATE TABLE public.detallepedido (
    iddetalle bigint DEFAULT nextval(('"public"."detallepedido_iddetalle_seq"'::text)::regclass) NOT NULL,
    idpedido bigint NOT NULL,
    precio double precision,
    cantidad integer,
    producto bigint
) WITH OIDS;
--
-- Structure for table detalletrabajotercerizado (OID = 148390) : 
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
-- Structure for table detalleplanificacioncalidad (OID = 148397) : 
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
-- Structure for table detallecompra (OID = 148401) : 
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
-- Structure for table detalleremito (OID = 148405) : 
--
CREATE TABLE public.detalleremito (
    iddetalle bigint DEFAULT nextval(('"public"."detalleremito_iddetalle_seq"'::text)::regclass) NOT NULL,
    idremito bigint NOT NULL,
    cantidad integer,
    descripcion character varying,
    producto bigint
) WITH OIDS;
--
-- Structure for table tiporeclamo (OID = 148412) : 
--
CREATE TABLE public.tiporeclamo (
    idtiporeclamo bigint DEFAULT nextval(('"public"."tiporeclamo_idtiporeclamo_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Structure for table reclamoempresametalurgica (OID = 148419) : 
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
-- Structure for table reclamoproveedor (OID = 148426) : 
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
-- Structure for table reclamocliente (OID = 148433) : 
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
-- Structure for table detallereclamocliente (OID = 148440) : 
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
-- Structure for table detallereclamoproveedor (OID = 148447) : 
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
-- Structure for table detallereclamoempresametalurgica (OID = 148454) : 
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
-- Structure for table proveedorxmateriaprima (OID = 148461) : 
--
CREATE TABLE public.proveedorxmateriaprima (
    idproveedor bigint NOT NULL,
    idmateriaprima bigint NOT NULL,
    precio double precision
) WITH OIDS;
--
-- Structure for table maquinaxejecucionetapaproduccion (OID = 148464) : 
--
CREATE TABLE public.maquinaxejecucionetapaproduccion (
    idejecucionetapaproduccion bigint NOT NULL,
    idetapaproduccion bigint NOT NULL,
    idmaquina bigint NOT NULL,
    horasmaquina time without time zone,
    horashombre time without time zone
) WITH OIDS;
--
-- Structure for table maquinaxprocesocalidad (OID = 148467) : 
--
CREATE TABLE public.maquinaxprocesocalidad (
    idprocesocalidad bigint NOT NULL,
    idmaquina bigint NOT NULL,
    duracion time without time zone,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table piezaxetapadeproduccion (OID = 148473) : 
--
CREATE TABLE public.piezaxetapadeproduccion (
    idpieza bigint NOT NULL,
    idetapaproduccion bigint NOT NULL,
    duracion time without time zone,
    descripcion character varying
) WITH OIDS;
--
-- Structure for table empleadoxturno (OID = 148479) : 
--
CREATE TABLE public.empleadoxturno (
    idempleado bigint NOT NULL,
    idturno bigint NOT NULL
) WITH OIDS;
--
-- Structure for table provincia (OID = 148482) : 
--
CREATE TABLE public.provincia (
    idprovincia bigint DEFAULT nextval(('"public"."provincia_idprovincia_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50)
) WITH OIDS;
--
-- Structure for table localidad (OID = 148486) : 
--
CREATE TABLE public.localidad (
    idlocalidad bigint DEFAULT nextval(('"public"."localidad_idlocalidad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    provincia bigint
) WITH OIDS;
--
-- Structure for table barrio (OID = 148490) : 
--
CREATE TABLE public.barrio (
    idbarrio bigint DEFAULT nextval(('"public"."barrio_idbarrio_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    codpostal bigint,
    localidad bigint
) WITH OIDS;
--
-- Structure for table usuarioxrol (OID = 148494) : 
--
CREATE TABLE public.usuarioxrol (
    idrol bigint NOT NULL,
    idusuario bigint NOT NULL
) WITH OIDS;
--
-- Structure for table rolxprivilegio (OID = 148497) : 
--
CREATE TABLE public.rolxprivilegio (
    idrol bigint NOT NULL,
    idprivilegio bigint NOT NULL
) WITH OIDS;
--
-- Structure for table planrequerimientosmateriaprima (OID = 148500) : 
--
CREATE TABLE public.planrequerimientosmateriaprima (
    idplanrequerimientosmateriaprima bigint DEFAULT nextval(('"public"."planrequerimientosmateriaprima_idplanrequerimientosmateriaprima_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table planprocedimientos (OID = 148504) : 
--
CREATE TABLE public.planprocedimientos (
    idplanprocedimientos bigint DEFAULT nextval(('"public"."planprocedimientos_idplanprocedimientos_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table planprocesoscalidad (OID = 148508) : 
--
CREATE TABLE public.planprocesoscalidad (
    idplanprocesoscalidad bigint DEFAULT nextval(('"public"."planprocesoscalidad_idplanprocesoscalidad_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table detallerequerimientosmateriaprima (OID = 148512) : 
--
CREATE TABLE public.detallerequerimientosmateriaprima (
    iddetalle bigint DEFAULT nextval(('"public"."detallerequerimientosmateriaprima_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanrequerimientosmateriaprima bigint NOT NULL,
    cantidadmateriaprima integer,
    idpieza bigint,
    idmateriaprima bigint
) WITH OIDS;
--
-- Structure for table detalleplanprocedimientos (OID = 148516) : 
--
CREATE TABLE public.detalleplanprocedimientos (
    iddetalle bigint DEFAULT nextval(('"public"."detalleplanprocedimientos_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanpprocedimientos bigint NOT NULL,
    idpieza bigint,
    idetapaproduccion bigint,
    duracionestimada time without time zone
) WITH OIDS;
--
-- Structure for table detalleplanprocesoscalidad (OID = 148520) : 
--
CREATE TABLE public.detalleplanprocesoscalidad (
    iddetalle bigint DEFAULT nextval(('"public"."detalleplanprocesoscalidad_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanprocesoscalidad bigint NOT NULL,
    idpieza bigint,
    idprocesocalidad bigint,
    duracionestimada time without time zone
) WITH OIDS;
--
-- Structure for table pedidomatriz (OID = 148524) : 
--
CREATE TABLE public.pedidomatriz (
    idpedidomatriz bigint DEFAULT nextval(('"public"."pedidomatriz_idpedidomatriz_seq"'::text)::regclass) NOT NULL,
    nropedidomatriz bigint,
    fechapedidomatriz date,
    idmatriz bigint,
    observaciones character varying
) WITH OIDS;
--
-- Structure for table estadodetallecompra (OID = 148531) : 
--
CREATE TABLE public.estadodetallecompra (
    idestado bigint DEFAULT nextval(('"public"."estadodetallecompra_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Structure for table estadodetalletrabajotercerizado (OID = 148538) : 
--
CREATE TABLE public.estadodetalletrabajotercerizado (
    idestado bigint DEFAULT nextval(('"public"."estadodetalletrabajotercerizado_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Structure for table estadoejecucionprocesocalidad (OID = 148545) : 
--
CREATE TABLE public.estadoejecucionprocesocalidad (
    idestado bigint DEFAULT nextval(('"public"."estadoejecucionprocesocalidad_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Structure for table detalleproductoreal (OID = 148552) : 
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
-- Structure for table productoreal (OID = 148559) : 
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
-- Structure for table estadoproductoreal (OID = 148563) : 
--
CREATE TABLE public.estadoproductoreal (
    idestado bigint DEFAULT nextval(('"public"."estadoproductoreal_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Structure for table detallefactura (OID = 148570) : 
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
-- Structure for table detallepresupuesto (OID = 148574) : 
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
-- Structure for table estadoremito (OID = 148578) : 
--
CREATE TABLE public.estadoremito (
    idestado bigint DEFAULT nextval(('"public"."estadoremito_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Structure for table tipomaterial (OID = 148585) : 
--
CREATE TABLE public.tipomaterial (
    idtipomaterial bigint DEFAULT nextval(('"public"."tipomaterial_idtipomaterial_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying
) WITH OIDS;
--
-- Definition for sequence prueba_id_seq (OID = 148592) : 
--
CREATE SEQUENCE public.prueba_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table prueba (OID = 148594) : 
--
CREATE TABLE public.prueba (
    id bigint DEFAULT nextval('prueba_id_seq'::regclass) NOT NULL,
    valor character varying(20)
) WITH OIDS;
ALTER TABLE ONLY public.prueba ALTER COLUMN id SET STATISTICS 0;
ALTER TABLE ONLY public.prueba ALTER COLUMN valor SET STATISTICS 0;
--
-- Definition for sequence usuario_idusuario_seq (OID = 148598) : 
--
CREATE SEQUENCE public.usuario_idusuario_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipomaterial_idtipomaterial_seq (OID = 148600) : 
--
CREATE SEQUENCE public.tipomaterial_idtipomaterial_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence accioncalidad_idaccioncalidad_seq (OID = 148602) : 
--
CREATE SEQUENCE public.accioncalidad_idaccioncalidad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence barrio_idbarrio_seq (OID = 148604) : 
--
CREATE SEQUENCE public.barrio_idbarrio_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence cargo_idcargo_seq (OID = 148606) : 
--
CREATE SEQUENCE public.cargo_idcargo_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence categoria_idcategoria_seq (OID = 148608) : 
--
CREATE SEQUENCE public.categoria_idcategoria_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence cliente_idcliente_seq (OID = 148610) : 
--
CREATE SEQUENCE public.cliente_idcliente_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence codigodebarra_idcodigo_seq (OID = 148612) : 
--
CREATE SEQUENCE public.codigodebarra_idcodigo_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence compra_idcompra_seq (OID = 148614) : 
--
CREATE SEQUENCE public.compra_idcompra_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence comprobantepago_idcomprobantepago_seq (OID = 148616) : 
--
CREATE SEQUENCE public.comprobantepago_idcomprobantepago_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence condicioniva_idcondicioniva_seq (OID = 148618) : 
--
CREATE SEQUENCE public.condicioniva_idcondicioniva_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanificacionproduccion_id_seq (OID = 148620) : 
--
CREATE SEQUENCE public.detalleplanificacionproduccion_id_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallecompra_iddetalle_seq (OID = 148622) : 
--
CREATE SEQUENCE public.detallecompra_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleejecucionplanificacion_iddetalle_seq (OID = 148624) : 
--
CREATE SEQUENCE public.detalleejecucionplanificacion_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleejecucionplanificacioncalidad_iddetalle_seq (OID = 148626) : 
--
CREATE SEQUENCE public.detalleejecucionplanificacioncalidad_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallefactura_iddetalle_seq (OID = 148628) : 
--
CREATE SEQUENCE public.detallefactura_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallemantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 148630) : 
--
CREATE SEQUENCE public.detallemantenimientocorrectivo_idmantenimientocorrectivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallemantenimientopreventivo_idmantenimientopreventivo_seq (OID = 148632) : 
--
CREATE SEQUENCE public.detallemantenimientopreventivo_idmantenimientopreventivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallepedido_iddetalle_seq (OID = 148634) : 
--
CREATE SEQUENCE public.detallepedido_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanificacioncalidad_iddetalle_seq (OID = 148636) : 
--
CREATE SEQUENCE public.detalleplanificacioncalidad_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanprocedimientos_iddetalle_seq (OID = 148638) : 
--
CREATE SEQUENCE public.detalleplanprocedimientos_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanprocesoscalidad_iddetalle_seq (OID = 148640) : 
--
CREATE SEQUENCE public.detalleplanprocesoscalidad_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallepresupuesto_iddetalle_seq (OID = 148642) : 
--
CREATE SEQUENCE public.detallepresupuesto_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleproducto_iddetalle_seq (OID = 148644) : 
--
CREATE SEQUENCE public.detalleproducto_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleproductoreal_iddetalle_seq (OID = 148646) : 
--
CREATE SEQUENCE public.detalleproductoreal_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamocliente_iddetalle_seq (OID = 148648) : 
--
CREATE SEQUENCE public.detallereclamocliente_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamoempresametalurgica_iddetalle_seq (OID = 148650) : 
--
CREATE SEQUENCE public.detallereclamoempresametalurgica_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamoproveedor_iddetalle_seq (OID = 148652) : 
--
CREATE SEQUENCE public.detallereclamoproveedor_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleremito_iddetalle_seq (OID = 148654) : 
--
CREATE SEQUENCE public.detalleremito_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallerequerimientosmateriaprima_iddetalle_seq (OID = 148656) : 
--
CREATE SEQUENCE public.detallerequerimientosmateriaprima_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalletrabajotercerizado_iddetalle_seq (OID = 148658) : 
--
CREATE SEQUENCE public.detalletrabajotercerizado_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence domicilio_iddomicilio_seq (OID = 148660) : 
--
CREATE SEQUENCE public.domicilio_iddomicilio_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionetapaproduccion_idejecucion_seq (OID = 148662) : 
--
CREATE SEQUENCE public.ejecucionetapaproduccion_idejecucion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionplanificacioncalidad_idejecucion_seq (OID = 148664) : 
--
CREATE SEQUENCE public.ejecucionplanificacioncalidad_idejecucion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionplanificacionproduccion_idejecucion_seq (OID = 148666) : 
--
CREATE SEQUENCE public.ejecucionplanificacionproduccion_idejecucion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionprocesocalidad_idejecucion_seq (OID = 148668) : 
--
CREATE SEQUENCE public.ejecucionprocesocalidad_idejecucion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence empleado_idempleado_seq (OID = 148670) : 
--
CREATE SEQUENCE public.empleado_idempleado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence empresametalurgica_idempresametalurgica_seq (OID = 148672) : 
--
CREATE SEQUENCE public.empresametalurgica_idempresametalurgica_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadocliente_idestado_seq (OID = 148674) : 
--
CREATE SEQUENCE public.estadocliente_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadocompra_idestado_seq (OID = 148676) : 
--
CREATE SEQUENCE public.estadocompra_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadodetallecompra_idestado_seq (OID = 148678) : 
--
CREATE SEQUENCE public.estadodetallecompra_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadodetalletrabajotercerizado_idestado_seq (OID = 148680) : 
--
CREATE SEQUENCE public.estadodetalletrabajotercerizado_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecetapaprod_idestado_seq (OID = 148682) : 
--
CREATE SEQUENCE public.estadoejecetapaprod_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecplancalidad_idestado_seq (OID = 148684) : 
--
CREATE SEQUENCE public.estadoejecplancalidad_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecplanifpedido_idestado_seq (OID = 148686) : 
--
CREATE SEQUENCE public.estadoejecplanifpedido_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecucionprocesocalidad_idestado_seq (OID = 148688) : 
--
CREATE SEQUENCE public.estadoejecucionprocesocalidad_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadofactura_idestado_seq (OID = 148690) : 
--
CREATE SEQUENCE public.estadofactura_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadomaquina_idestado_seq (OID = 148692) : 
--
CREATE SEQUENCE public.estadomaquina_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadopedido_idestado_seq (OID = 148694) : 
--
CREATE SEQUENCE public.estadopedido_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadopiezareal_idestado_seq (OID = 148696) : 
--
CREATE SEQUENCE public.estadopiezareal_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoproductoreal_idestado_seq (OID = 148698) : 
--
CREATE SEQUENCE public.estadoproductoreal_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoremito_idestado_seq (OID = 148700) : 
--
CREATE SEQUENCE public.estadoremito_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadotrabajotercerizado_idestado_seq (OID = 148702) : 
--
CREATE SEQUENCE public.estadotrabajotercerizado_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence etapadeproduccion_idetapaproduccion_seq (OID = 148704) : 
--
CREATE SEQUENCE public.etapadeproduccion_idetapaproduccion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence factura_idfactura_seq (OID = 148706) : 
--
CREATE SEQUENCE public.factura_idfactura_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence formadepago_idformapago_seq (OID = 148708) : 
--
CREATE SEQUENCE public.formadepago_idformapago_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence localidad_idlocalidad_seq (OID = 148710) : 
--
CREATE SEQUENCE public.localidad_idlocalidad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence mantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 148712) : 
--
CREATE SEQUENCE public.mantenimientocorrectivo_idmantenimientocorrectivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence mantenimientopreventivo_idmantenimientopreventivo_seq (OID = 148714) : 
--
CREATE SEQUENCE public.mantenimientopreventivo_idmantenimientopreventivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence maquina_idmaquina_seq (OID = 148716) : 
--
CREATE SEQUENCE public.maquina_idmaquina_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence marca_idmarca_seq (OID = 148718) : 
--
CREATE SEQUENCE public.marca_idmarca_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence materiaprima_idmateriaprima_seq (OID = 148720) : 
--
CREATE SEQUENCE public.materiaprima_idmateriaprima_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence matriz_idmatriz_seq (OID = 148722) : 
--
CREATE SEQUENCE public.matriz_idmatriz_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pedido_idpedido_seq (OID = 148724) : 
--
CREATE SEQUENCE public.pedido_idpedido_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pedidomatriz_idpedidomatriz_seq (OID = 148726) : 
--
CREATE SEQUENCE public.pedidomatriz_idpedidomatriz_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pieza_idpieza_seq (OID = 148728) : 
--
CREATE SEQUENCE public.pieza_idpieza_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence piezareal_idpiezareal_seq (OID = 148730) : 
--
CREATE SEQUENCE public.piezareal_idpiezareal_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planificacioncalidad_idplanificacion_seq (OID = 148732) : 
--
CREATE SEQUENCE public.planificacioncalidad_idplanificacion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planificacionproduccion_idplanificacionproduccion_seq (OID = 148734) : 
--
CREATE SEQUENCE public.planificacionproduccion_idplanificacionproduccion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence plano_idplano_seq (OID = 148736) : 
--
CREATE SEQUENCE public.plano_idplano_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planprocedimientos_idplanprocedimientos_seq (OID = 148738) : 
--
CREATE SEQUENCE public.planprocedimientos_idplanprocedimientos_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planprocesoscalidad_idplanprocesoscalidad_seq (OID = 148740) : 
--
CREATE SEQUENCE public.planprocesoscalidad_idplanprocesoscalidad_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planrequerimientosmateriaprima_idplanrequerimientosmateriaprima (OID = 148742) : 
--
CREATE SEQUENCE public.planrequerimientosmateriaprima_idplanrequerimientosmateriaprima
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence presupuesto_idpresupuesto_seq (OID = 148744) : 
--
CREATE SEQUENCE public.presupuesto_idpresupuesto_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence prioridad_idprioridad_seq (OID = 148746) : 
--
CREATE SEQUENCE public.prioridad_idprioridad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence privilegio_idprivilegio_seq (OID = 148748) : 
--
CREATE SEQUENCE public.privilegio_idprivilegio_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence procesocalidad_idprocesocalidad_seq (OID = 148750) : 
--
CREATE SEQUENCE public.procesocalidad_idprocesocalidad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence producto_idproducto_seq (OID = 148752) : 
--
CREATE SEQUENCE public.producto_idproducto_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence productoreal_idproductoreal_seq (OID = 148754) : 
--
CREATE SEQUENCE public.productoreal_idproductoreal_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence proveedor_idproveedor_seq (OID = 148756) : 
--
CREATE SEQUENCE public.proveedor_idproveedor_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence proveedormantenimientomaquina_idproveedormantenimiento_seq (OID = 148758) : 
--
CREATE SEQUENCE public.proveedormantenimientomaquina_idproveedormantenimiento_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence provincia_idprovincia_seq (OID = 148760) : 
--
CREATE SEQUENCE public.provincia_idprovincia_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamocliente_idreclamo_seq (OID = 148762) : 
--
CREATE SEQUENCE public.reclamocliente_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamoempresametalurgica_idreclamo_seq (OID = 148764) : 
--
CREATE SEQUENCE public.reclamoempresametalurgica_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamoproveedor_idreclamo_seq (OID = 148766) : 
--
CREATE SEQUENCE public.reclamoproveedor_idreclamo_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence remito_idremito_seq (OID = 148768) : 
--
CREATE SEQUENCE public.remito_idremito_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence responsable_idresponsable_seq (OID = 148770) : 
--
CREATE SEQUENCE public.responsable_idresponsable_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence rol_idrol_seq (OID = 148772) : 
--
CREATE SEQUENCE public.rol_idrol_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence rotura_idrotura_seq (OID = 148774) : 
--
CREATE SEQUENCE public.rotura_idrotura_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence servicio_idservicio_seq (OID = 148776) : 
--
CREATE SEQUENCE public.servicio_idservicio_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence sesion_idsesion_seq (OID = 148778) : 
--
CREATE SEQUENCE public.sesion_idsesion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipodocumento_idtipodocumento_seq (OID = 148780) : 
--
CREATE SEQUENCE public.tipodocumento_idtipodocumento_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipoiva_idtipoiva_seq (OID = 148782) : 
--
CREATE SEQUENCE public.tipoiva_idtipoiva_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipomaquina_idtipomaquina_seq (OID = 148784) : 
--
CREATE SEQUENCE public.tipomaquina_idtipomaquina_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tiporeclamo_idtiporeclamo_seq (OID = 148786) : 
--
CREATE SEQUENCE public.tiporeclamo_idtiporeclamo_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence trabajotercerizado_idtrabajo_seq (OID = 148788) : 
--
CREATE SEQUENCE public.trabajotercerizado_idtrabajo_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence turno_idturno_seq (OID = 148790) : 
--
CREATE SEQUENCE public.turno_idturno_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for view viewdetallepedidocotizacion (OID = 148792) : 
--
CREATE VIEW public.viewdetallepedidocotizacion AS
SELECT p.nroproducto, p.nombre, p.descripcion, dp.cantidad,
    p.preciounitario AS precio, p.idproducto, dp.iddetalle, ped.idpedido
FROM producto p, pedido ped, detallepedido dp
WHERE ((dp.producto = p.idproducto) AND (ped.idpedido = dp.idpedido))
ORDER BY p.nombre;

--
-- Definition for view viewpedidoendetalleprocedimientos (OID = 148796) : 
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
-- Definition for sequence unidadmedida_idunidadmedida_seq (OID = 148800) : 
--
CREATE SEQUENCE public.unidadmedida_idunidadmedida_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table unidadmedida (OID = 148802) : 
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
-- Definition for view viewetapadeproduccion (OID = 148806) : 
--
CREATE VIEW public.viewetapadeproduccion AS
SELECT ep.nroetapaproduccion AS numero, ep.nombre, ep.horashombre,
    ep.horasmaquina, ep.duracionestimada, ep.idetapaproduccion AS idetapa
FROM etapadeproduccion ep;

--
-- Definition for sequence detallepiezapresupuesto_iddetalle_seq (OID = 148810) : 
--
CREATE SEQUENCE public.detallepiezapresupuesto_iddetalle_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detallepiezapresupuesto (OID = 148812) : 
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
-- Definition for sequence detalleproductopresupuesto_iddetalle_seq (OID = 148816) : 
--
CREATE SEQUENCE public.detalleproductopresupuesto_iddetalle_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detalleproductopresupuesto (OID = 148818) : 
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
-- Definition for sequence detallepiezacalidadpresupuesto_iddetalle_seq (OID = 148822) : 
--
CREATE SEQUENCE public.detallepiezacalidadpresupuesto_iddetalle_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detallepiezacalidadpresupuesto (OID = 148824) : 
--
CREATE TABLE public.detallepiezacalidadpresupuesto (
    iddetalle bigint DEFAULT nextval('detallepiezacalidadpresupuesto_iddetalle_seq'::regclass) NOT NULL,
    cantprocesocalidad integer,
    duracionxpieza time without time zone,
    idprocesocalidad bigint,
    iddetalleproductopresupuesto bigint
) WITH OIDS;
--
-- Definition for view viewproveedorxmateriaprima (OID = 148828) : 
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
-- Definition for view viewmateriaprima (OID = 148833) : 
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
-- Definition for view viewproductopresupuesto (OID = 148837) : 
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
-- Definition for sequence calendario_id_seq (OID = 148841) : 
--
CREATE SEQUENCE public.calendario_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table calendario (OID = 148843) : 
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
-- Definition for view viewpedidosnoconfirmados (OID = 148847) : 
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
-- Definition for sequence disponibilidadhoraria_id_seq (OID = 148851) : 
--
CREATE SEQUENCE public.disponibilidadhoraria_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table disponibilidadhoraria (OID = 148853) : 
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
-- Definition for sequence estadoplanificacionproduccion_id_seq (OID = 148857) : 
--
CREATE SEQUENCE public.estadoplanificacionproduccion_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table estadoplanificacionproduccion (OID = 148859) : 
--
CREATE TABLE public.estadoplanificacionproduccion (
    id bigint DEFAULT nextval('estadoplanificacionproduccion_id_seq'::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying(250)
) WITH OIDS;
--
-- Definition for sequence maquina_idmaquina_seq1 (OID = 148866) : 
--
CREATE SEQUENCE public.maquina_idmaquina_seq1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table maquina (OID = 148868) : 
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
-- Definition for sequence piezareal_idpiezareal_seq1 (OID = 148872) : 
--
CREATE SEQUENCE public.piezareal_idpiezareal_seq1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table piezareal (OID = 148874) : 
--
CREATE TABLE public.piezareal (
    idpiezareal bigint DEFAULT nextval('piezareal_idpiezareal_seq1'::regclass) NOT NULL,
    idpieza bigint NOT NULL,
    estado bigint,
    nropieza integer,
    idcodigobarra bigint
) WITH OIDS;
--
-- Definition for sequence pieza_idpieza_seq1 (OID = 148878) : 
--
CREATE SEQUENCE public.pieza_idpieza_seq1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table pieza (OID = 148880) : 
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
-- Definition for sequence detallempasignada_id_seq (OID = 148884) : 
--
CREATE SEQUENCE public.detallempasignada_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detallempasignada (OID = 148886) : 
--
CREATE TABLE public.detallempasignada (
    id bigint DEFAULT nextval('detallempasignada_id_seq'::regclass) NOT NULL,
    idmateriaprima bigint,
    cantidadmp integer,
    idplanificacionproduccion bigint
) WITH OIDS;
--
-- Definition for sequence mpasignadaxpiezareal_id_seq (OID = 148890) : 
--
CREATE SEQUENCE public.mpasignadaxpiezareal_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table mpasignadaxpiezareal (OID = 148892) : 
--
CREATE TABLE public.mpasignadaxpiezareal (
    idpiezareal bigint,
    iddetallempasignada bigint,
    id bigint DEFAULT nextval('mpasignadaxpiezareal_id_seq'::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table asistencia (OID = 148896) : 
--
CREATE TABLE public.asistencia (
    empleado bigint NOT NULL,
    fechaingreso date NOT NULL,
    horaingreso time without time zone NOT NULL,
    horaegreso time without time zone,
    observaciones character varying(100)
) WITH OIDS;
--
-- Definition for view viewmpxpiezapresupuesto (OID = 148899) : 
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
-- Definition for view viewetapasxpiezapresupuesto (OID = 148903) : 
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
-- Definition for view viewprocalidadxpiezapresupesto (OID = 148907) : 
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
-- Definition for view viewpedidosconmpasignada (OID = 148912) : 
--
CREATE VIEW public.viewpedidosconmpasignada AS
SELECT ped.nropedido, planpro.nroplanificacion AS
    nroplanificacionproduccion, planpro.fechacreacion,
    planpro.fechainicioprevista, planpro.fechafinprevista,
    planpro.observaciones, ped.idpedido, planpro.idplanificacionproduccion
FROM pedido ped, planificacionproduccion planpro
WHERE ((ped.idpedido = planpro.pedido) AND (ped.estado = 3));

--
-- Definition for view viewdetalleproducto (OID = 148916) : 
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
-- Definition for view viewpedidosconplanifsinrecursos (OID = 148920) : 
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
-- Definition for view viewpedidosclientesegunestado (OID = 148925) : 
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
-- Definition for sequence reclamoempresamantenimiento_idreclamo_seq (OID = 148929) : 
--
CREATE SEQUENCE public.reclamoempresamantenimiento_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table reclamoempresamantenimiento (OID = 148931) : 
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
-- Definition for sequence detallereclamoempresamantenimiento_iddetalle_seq (OID = 148935) : 
--
CREATE SEQUENCE public.detallereclamoempresamantenimiento_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detallereclamoempresamantenimiento (OID = 148937) : 
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
-- Definition for sequence estadoreclamo_idestadoreclamo_seq (OID = 148941) : 
--
CREATE SEQUENCE public.estadoreclamo_idestadoreclamo_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table estadoreclamo (OID = 148943) : 
--
CREATE TABLE public.estadoreclamo (
    idestadoreclamo bigint DEFAULT nextval('estadoreclamo_idestadoreclamo_seq'::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(200)
) WITH OIDS;
--
-- Definition for view viewpedidosconrecasignados (OID = 148947) : 
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
-- Definition for view viewpedidosproduccionfin (OID = 148951) : 
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
-- Definition for sequence detallemantenimientopreventivo_iddetalle_seq (OID = 148955) : 
--
CREATE SEQUENCE public.detallemantenimientopreventivo_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table estadomantpreventivo (OID = 148957) : 
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
-- Definition for sequence estadomantpreventivo_idestado_seq (OID = 148964) : 
--
CREATE SEQUENCE public.estadomantpreventivo_idestado_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallemantenimientocorrectivo_iddetalle_seq (OID = 148966) : 
--
CREATE SEQUENCE public.detallemantenimientocorrectivo_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for view viewcantidadmpenpedido (OID = 148968) : 
--
CREATE VIEW public.viewcantidadmpenpedido AS
SELECT sum(((dp.cantidad * dpp.cantpiezas) * dpp.cantmateriaprima)) AS cantidad
FROM pedido pe, detalleproductopresupuesto dpp, presupuesto pre,
    detallepresupuesto dp
WHERE (((pe.presupuesto = pre.idpresupuesto) AND (dp.idpresupuesto =
    pre.idpresupuesto)) AND (dp.iddetalle = dpp.iddetallepresupuesto));

--
-- Definition for view viewcantidadmpasiganda (OID = 148972) : 
--
CREATE VIEW public.viewcantidadmpasiganda AS
SELECT sum(dmpa.cantidadmp) AS cantidad
FROM planificacionproduccion ppro, detallempasignada dmpa
WHERE (ppro.idplanificacionproduccion = dmpa.idplanificacionproduccion);

--
-- Definition for view viewpresupuestoparafactura (OID = 148976) : 
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
-- Definition for view viewpiezarealparacalidad (OID = 148980) : 
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
-- Definition for view viewprocesocalidad (OID = 148985) : 
--
CREATE VIEW public.viewprocesocalidad AS
SELECT pc.nroproceso, pc.nombre AS nombreproceso, pc.duracionestimada,
    pc.herramienta, ac.nombre AS nombreaccioncalidad, pc.idprocesocalidad
FROM procesocalidad pc, accioncalidad ac
WHERE (pc.accioncalidad = ac.idaccioncalidad)
ORDER BY pc.nombre;

--
-- Definition for view viewpedidosconplanificacionproduccion (OID = 148989) : 
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
-- Data for table public.pedido (OID = 148001) (LIMIT 0,8)
--
INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad, observaciones)
VALUES (3, '2011-11-21', '2011-12-21', '2011-11-20', NULL, NULL, 3, NULL, 137, '2011-12-28', '', false, 3244, '2011-11-20', 67, 31, NULL, NULL, NULL, 3, NULL);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad, observaciones)
VALUES (7, NULL, '2011-12-28', '2011-11-20', NULL, NULL, 2, NULL, 141, '2012-03-20', '', false, 3434, '2011-11-20', 71, 33, NULL, NULL, NULL, 3, NULL);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad, observaciones)
VALUES (4, '2011-11-21', '2012-01-24', '2011-11-20', NULL, NULL, 4, NULL, 138, '2011-12-30', '', false, 3454, '2011-11-20', 68, 29, NULL, NULL, NULL, 3, NULL);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad, observaciones)
VALUES (2, NULL, NULL, '2011-11-20', NULL, NULL, 16, NULL, 136, '2011-11-22', '', false, 4532, '2011-11-20', 66, 27, NULL, NULL, NULL, 3, NULL);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad, observaciones)
VALUES (6, NULL, NULL, '2011-11-20', NULL, NULL, 17, NULL, 140, '2012-02-29', '', false, 4545, '2011-11-20', 70, 32, NULL, NULL, NULL, 3, NULL);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad, observaciones)
VALUES (1, '2011-11-21', '2011-11-30', '2011-11-20', NULL, NULL, 3, NULL, 135, '2011-11-22', '', false, 4353, '2011-11-20', 65, 26, NULL, NULL, NULL, 3, NULL);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad, observaciones)
VALUES (8, NULL, NULL, '2011-11-20', NULL, NULL, 1, NULL, NULL, '2012-01-16', '', false, 2323, '2011-11-20', 72, 29, NULL, NULL, NULL, 3, NULL);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad, observaciones)
VALUES (5, '2011-11-21', '2011-11-25', '2011-11-20', NULL, NULL, 4, NULL, 139, '2012-01-25', '', false, 3434, '2011-11-20', 69, 30, NULL, NULL, NULL, 3, NULL);

--
-- Data for table public.planificacioncalidad (OID = 148009) (LIMIT 0,2)
--
INSERT INTO planificacioncalidad (idplanificacion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido)
VALUES (20, 1, '2011-11-21', '', '2011-11-27', '2011-11-29', 65);

INSERT INTO planificacioncalidad (idplanificacion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido)
VALUES (21, 2, '2011-11-21', '', '2011-11-29', '2011-11-29', 67);

--
-- Data for table public.estadopedido (OID = 148016) (LIMIT 0,19)
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
-- Data for table public.planificacionproduccion (OID = 148030) (LIMIT 0,2)
--
INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (89, 5, '2011-11-21', '', '2011-11-21', '2011-11-27', 65, 2);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (90, 6, '2011-11-21', '', '2011-11-22', '2011-11-29', 67, 2);

--
-- Data for table public.presupuesto (OID = 148037) (LIMIT 0,7)
--
INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto, gananciaadicional)
VALUES (136, NULL, 0, NULL, 58, 0);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto, gananciaadicional)
VALUES (140, NULL, 0, NULL, 62, 0);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto, gananciaadicional)
VALUES (135, '2011-11-21', 11499.84, '2011-11-28', 57, 0);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto, gananciaadicional)
VALUES (137, '2011-11-21', 2662, '2011-11-28', 59, 0);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto, gananciaadicional)
VALUES (139, '2011-11-21', 405.96, '2011-11-28', 61, 0);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto, gananciaadicional)
VALUES (138, '2011-11-21', 2236.08, '2011-11-28', 60, 0);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto, gananciaadicional)
VALUES (141, '2011-11-21', 3630, '2011-11-28', 63, 0);

--
-- Data for table public.cliente (OID = 148060) (LIMIT 0,8)
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
-- Data for table public.formadepago (OID = 148074) (LIMIT 0,2)
--
INSERT INTO formadepago (idformapago, nombre, descripcion)
VALUES (1, 'CONTADO', 'EFECTIVO');

INSERT INTO formadepago (idformapago, nombre, descripcion)
VALUES (2, 'CTA CTE', 'Cuenta corriente');

--
-- Data for table public.usuario (OID = 148081) (LIMIT 0,16)
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
-- Data for table public.estadofactura (OID = 148088) (LIMIT 0,2)
--
INSERT INTO estadofactura (idestado, nombre, descripcion)
VALUES (1, 'GENERADO', 'GENERADO');

INSERT INTO estadofactura (idestado, nombre, descripcion)
VALUES (2, 'COBRADO', 'cobrado');

--
-- Data for table public.estadotrabajotercerizado (OID = 148099) (LIMIT 0,14)
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
-- Data for table public.empresametalurgica (OID = 148106) (LIMIT 0,4)
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
-- Data for table public.prioridad (OID = 148113) (LIMIT 0,3)
--
INSERT INTO prioridad (idprioridad, nombre, descripcion)
VALUES (1, 'Alta', 'Prioridad Alta');

INSERT INTO prioridad (idprioridad, nombre, descripcion)
VALUES (2, 'Baja', 'Prioridad Baja');

INSERT INTO prioridad (idprioridad, nombre, descripcion)
VALUES (3, 'Normal', 'Prioridad Normal');

--
-- Data for table public.estadocliente (OID = 148120) (LIMIT 0,3)
--
INSERT INTO estadocliente (idestado, nombre, descripcion)
VALUES (1, 'ACTIVO', 'Dado de Alta');

INSERT INTO estadocliente (idestado, nombre, descripcion)
VALUES (2, 'BAJA', 'Dado de Baja');

INSERT INTO estadocliente (idestado, nombre, descripcion)
VALUES (3, 'MOROSO', 'Cliente Moroso, adeuda facturas');

--
-- Data for table public.responsable (OID = 148127) (LIMIT 0,17)
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
-- Data for table public.condicioniva (OID = 148134) (LIMIT 0,3)
--
INSERT INTO condicioniva (idcondicioniva, nombre, descripcion)
VALUES (1, 'R.Inscripto', 'Responsable Inscripto');

INSERT INTO condicioniva (idcondicioniva, nombre, descripcion)
VALUES (2, 'Monotributista', 'Monotributista');

INSERT INTO condicioniva (idcondicioniva, nombre, descripcion)
VALUES (3, 'C.Final', 'Consumidor Final');

--
-- Data for table public.domicilio (OID = 148141) (LIMIT 0,58)
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
-- Data for table public.rotura (OID = 148148) (LIMIT 0,7)
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
-- Data for table public.categoria (OID = 148155) (LIMIT 0,4)
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
-- Data for table public.cargo (OID = 148162) (LIMIT 0,9)
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
-- Data for table public.turno (OID = 148169) (LIMIT 0,3)
--
INSERT INTO turno (idturno, horainicio, horafin, nombre, descripcion)
VALUES (1, '00:08:00', '00:12:00', 'MAÑANA', '');

INSERT INTO turno (idturno, horainicio, horafin, nombre, descripcion)
VALUES (2, '00:13:00', '00:17:00', 'TARDE', '');

INSERT INTO turno (idturno, horainicio, horafin, nombre, descripcion)
VALUES (3, '00:18:00', '00:22:00', 'NOCHE', '');

--
-- Data for table public.marca (OID = 148176) (LIMIT 0,3)
--
INSERT INTO marca (idmarca, nombre, descripcion)
VALUES (3, 'Jones Shipman', 'Internacional');

INSERT INTO marca (idmarca, nombre, descripcion)
VALUES (2, 'Bridgeport', 'Internacional');

INSERT INTO marca (idmarca, nombre, descripcion)
VALUES (1, 'Tornomax', 'Nacional');

--
-- Data for table public.estadomaquina (OID = 148183) (LIMIT 0,1)
--
INSERT INTO estadomaquina (idestado, nombre, descripcion)
VALUES (1, 'Disponible', 'mauqina disponible');

--
-- Data for table public.tipomaquina (OID = 148190) (LIMIT 0,6)
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
-- Data for table public.estadoejecetapaprod (OID = 148197) (LIMIT 0,6)
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
-- Data for table public.tipodocumento (OID = 148211) (LIMIT 0,3)
--
INSERT INTO tipodocumento (idtipodocumento, tipo, nombre)
VALUES (1, 'DNI', 'Documento Nacional de Identidad');

INSERT INTO tipodocumento (idtipodocumento, tipo, nombre)
VALUES (2, 'LE', 'Libreta de Enrolamiento');

INSERT INTO tipodocumento (idtipodocumento, tipo, nombre)
VALUES (3, 'LC', 'Libreta Cívica');

--
-- Data for table public.codigodebarra (OID = 148215) (LIMIT 0,90)
--
INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (213, NULL, 'mscb-3-1');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (214, NULL, 'mscb-3-2');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (215, NULL, 'mscb-3-3');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (216, NULL, 'mscb-3-4');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (217, NULL, 'mscb-3-5');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (218, NULL, 'mscb-3-6');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (219, NULL, 'mscb-3-7');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (220, NULL, 'mscb-3-8');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (221, NULL, 'mscb-3-9');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (222, NULL, 'mscb-3-10');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (223, NULL, 'mscb-3-11');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (224, NULL, 'mscb-3-12');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (225, NULL, 'mscb-3-13');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (226, NULL, 'mscb-3-14');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (227, NULL, 'mscb-3-15');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (228, NULL, 'mscb-3-16');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (229, NULL, 'mscb-3-17');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (230, NULL, 'mscb-3-18');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (231, NULL, 'mscb-3-19');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (232, NULL, 'mscb-3-20');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (233, NULL, 'mscb-3-21');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (234, NULL, 'mscb-3-22');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (235, NULL, 'mscb-3-23');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (236, NULL, 'mscb-3-24');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (237, NULL, 'mscb-3-25');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (238, NULL, 'mscb-3-26');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (239, NULL, 'mscb-3-27');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (240, NULL, 'mscb-3-28');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (241, NULL, 'mscb-3-29');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (242, NULL, 'mscb-3-30');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (243, NULL, 'mscb-3-31');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (244, NULL, 'mscb-3-32');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (245, NULL, 'mscb-3-33');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (246, NULL, 'mscb-3-34');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (247, NULL, 'mscb-3-35');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (248, NULL, 'mscb-3-36');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (249, NULL, 'mscb-3-37');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (250, NULL, 'mscb-3-38');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (251, NULL, 'mscb-3-39');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (252, NULL, 'mscb-3-40');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (253, NULL, 'mscb-3-41');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (254, NULL, 'mscb-3-42');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (255, NULL, 'mscb-3-43');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (256, NULL, 'mscb-3-44');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (257, NULL, 'mscb-3-45');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (258, NULL, 'mscb-3-46');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (259, NULL, 'mscb-3-47');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (260, NULL, 'mscb-3-48');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (261, NULL, 'mscb-3-49');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (262, NULL, 'mscb-3-50');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (263, NULL, 'mscb-3-51');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (264, NULL, 'mscb-3-52');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (265, NULL, 'mscb-3-53');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (266, NULL, 'mscb-3-54');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (267, NULL, 'mscb-3-55');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (268, NULL, 'mscb-3-56');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (269, NULL, 'mscb-3-57');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (270, NULL, 'mscb-3-58');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (271, NULL, 'mscb-3-59');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (272, NULL, 'mscb-3-60');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (273, NULL, 'mscb-3-61');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (274, NULL, 'mscb-3-62');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (275, NULL, 'mscb-3-63');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (276, NULL, 'mscb-3-64');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (277, NULL, 'mscb-3-65');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (278, NULL, 'mscb-3-66');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (279, NULL, 'mscb-3-67');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (280, NULL, 'mscb-3-68');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (281, NULL, 'mscb-3-69');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (282, NULL, 'mscb-3-70');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (283, NULL, 'mscb-3-71');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (284, NULL, 'mscb-3-72');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (285, NULL, 'mscb-3-73');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (286, NULL, 'mscb-3-74');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (287, NULL, 'mscb-3-75');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (288, NULL, 'mscb-3-76');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (289, NULL, 'mscb-3-77');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (290, NULL, 'mscb-3-78');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (291, NULL, 'mscb-3-79');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (292, NULL, 'mscb-3-80');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (293, NULL, 'mscb-3-81');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (294, NULL, 'mscb-3-82');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (295, NULL, 'mscb-3-83');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (296, NULL, 'mscb-3-84');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (297, NULL, 'mscb-3-85');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (298, NULL, 'mscb-3-86');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (299, NULL, 'mscb-3-87');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (300, NULL, 'mscb-3-88');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (301, NULL, 'mscb-3-89');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (302, NULL, 'mscb-3-90');

--
-- Data for table public.estadopiezareal (OID = 148219) (LIMIT 0,1)
--
INSERT INTO estadopiezareal (idestado, nombre, descripcion)
VALUES (1, 'INICIADO', '');

--
-- Data for table public.estadoejecplanifpedido (OID = 148223) (LIMIT 0,4)
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
-- Data for table public.rol (OID = 148234) (LIMIT 0,12)
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
-- Data for table public.accioncalidad (OID = 148248) (LIMIT 0,4)
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
-- Data for table public.empleado (OID = 148255) (LIMIT 0,7)
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
-- Data for table public.proveedor (OID = 148259) (LIMIT 0,5)
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
-- Data for table public.estadocompra (OID = 148263) (LIMIT 0,10)
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
-- Data for table public.materiaprima (OID = 148274) (LIMIT 0,18)
--
INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (23, 'Acero Inoxidable', '2011-11-19', NULL, NULL, 50, 'Acero Inoxidable. Se puede pedir en planchuelas, en redondo, cuadrados o hexagonales', 2, 2, 100.000, 60.000, 60.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (24, 'Acero Inoxidable 304', '2011-11-19', NULL, NULL, 10, 'Acero Inoxidable 304. Se puede pedir en planchuelas, en redondo, cuadrados o hexagonales', 2, 2, 50.000, 50.000, 50.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (15, 'Grillón', '2011-11-19', NULL, NULL, 34, 'Plastico Grillón que viene en planchuela', 4, 2, 100.000, 100.000, 100.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (16, 'Teflón', '2011-11-19', NULL, NULL, 45, 'Plastico Teflón que viene en planchuela', 4, 2, 100.000, 100.000, 100.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (17, 'Acero 1020', '2011-11-19', NULL, NULL, 34, 'Acero 1020. Se puede pedir en planchuelas, en redondo, cuadrados o hexagonales', 2, 2, 100.000, 60.000, 60.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (18, 'Acero 1010', '2011-11-19', NULL, NULL, 55, 'Acero 1010. Se puede pedir en planchuelas, en redondo, cuadrados o hexagonales', 2, 2, 100.000, 60.000, 60.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (19, 'Acero 1045', '2011-11-19', NULL, NULL, 25, 'Acero 1045. Se puede pedir en planchuelas, en redondo, cuadrados o hexagonales', 2, 2, 100.000, 60.000, 60.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (20, 'Acero 8620', '2011-11-19', NULL, NULL, 44, 'Acero 8620. Se puede pedir en planchuelas, en redondo, cuadrados o hexagonales', 2, 2, 100.000, 60.000, 60.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (21, 'Acero 4140', '2011-11-19', NULL, NULL, 25, 'Acero 4140. Se puede pedir en planchuelas, en redondo, cuadrados o hexagonales', 2, 2, 100.000, 60.000, 60.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (22, 'Acero 1112', '2011-11-19', NULL, NULL, 55, 'Acero 1112. Se puede pedir en planchuelas, en redondo, cuadrados o hexagonales', 2, 2, 100.000, 60.000, 60.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (25, 'Acero Inoxidable 420', '2011-11-19', NULL, NULL, 45, 'Acero Inoxidable 420. Se puede pedir en planchuelas, en redondo, cuadrados o hexagonales', 2, 2, 50.000, 50.000, 50.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (26, 'Latón', '2011-11-19', NULL, NULL, 66, 'Bronce Latón.Viene en planchuelas, macizo, caño o hexagono', 7, 2, 50.000, 50.000, 50.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (27, 'Sae 63', '2011-11-19', NULL, NULL, 54, 'Bronce Sae 63.Viene en planchuelas, macizo, caño o hexagono', 7, 2, 50.000, 50.000, 50.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (28, 'Sae 65', '2011-11-19', NULL, NULL, 3, 'Bronce Sae 65.Viene en planchuelas, macizo, caño o hexagono', 7, 2, 50.000, 50.000, 50.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (29, 'Bronce al aluminio', '2011-11-19', NULL, NULL, 33, 'Bronce  al aluminio.Viene en planchuelas, macizo, caño o hexagono', 7, 2, 50.000, 50.000, 50.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (30, 'Camplo', '2011-11-19', NULL, NULL, 26, 'Aluminio Camplo. Viene en cuadrado, redondo, planchuela ', 8, 2, 50.000, 50.000, 50.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (13, 'Resina Acetal ', '2011-11-18', NULL, NULL, 0, 'Viene en planchuela, plastico Resia Acetal', 4, 2, 100.000, 100.000, 100.000, '0', NULL, 1);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (14, 'Polipropileno', '2011-11-19', NULL, NULL, 1, 'Plastico Polipopileno que viene en planchuela', 4, 2, 100.000, 100.000, 100.000, '0', NULL, 2);

--
-- Data for table public.matriz (OID = 148281) (LIMIT 0,2)
--
INSERT INTO matriz (idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial)
VALUES (6, 2, 'Volante', 'Matriz para un volante de auto de juego', NULL, NULL, 16, 4);

INSERT INTO matriz (idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial)
VALUES (7, 3, 'Perilla', 'matriz para hacer una perilla', NULL, NULL, 14, 4);

--
-- Data for table public.producto (OID = 148288) (LIMIT 0,13)
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
-- Data for table public.estadoejecplancalidad (OID = 148295) (LIMIT 0,4)
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
-- Data for table public.etapadeproduccion (OID = 148310) (LIMIT 0,10)
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
-- Data for table public.procesocalidad (OID = 148321) (LIMIT 0,6)
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
-- Data for table public.compra (OID = 148349) (LIMIT 0,24)
--
INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (3, 1, '2011-11-21', '2012-01-21', 0, 832, 1, '', 4);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (4, 2, '2011-11-21', '2012-01-21', 0, 2365, 1, '', 5);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (5, 3, '2011-11-21', '2012-01-21', 0, 2211, 1, '', 6);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (6, 4, '2011-11-21', '2012-01-21', 0, 1100, 1, '', 8);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (7, 5, '2011-11-21', '2012-01-21', 0, 1904, 1, '', 8);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (8, 6, '2011-11-04', '2012-01-04', 0, 55, 1, '', 4);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (9, 7, '2011-11-04', '2012-01-04', 0, 44, 1, '', 5);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (10, 8, '2011-11-04', '2012-01-04', 0, 67, 1, '', 6);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (11, 9, '2011-11-04', '2012-01-04', 0, 60, 1, '', 7);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (12, 10, '2011-11-04', '2012-01-04', 0, 60, 1, '', 5);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (13, 11, '2011-11-04', '2012-01-04', 0, 39, 1, '', 5);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (14, 12, '2011-11-04', '2012-01-04', 0, 35, 1, '', 4);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (15, 13, '2011-11-04', '2012-01-04', 0, 54, 1, '', 8);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (16, 14, '2011-11-04', '2012-01-04', 0, 55, 1, '', 7);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (17, 15, '2011-11-04', '2012-01-04', 0, 44, 1, '', 5);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (18, 16, '2011-11-15', '2012-01-15', 0, 40, 1, '', 4);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (19, 17, '2011-11-15', '2012-01-15', 0, 55, 1, '', 5);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (20, 18, '2011-11-15', '2012-01-15', 0, 44, 1, '', 5);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (21, 19, '2011-11-15', '2012-01-15', 0, 46, 1, '', 6);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (22, 20, '2011-11-15', '2012-01-15', 0, 44, 1, '', 5);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (23, 21, '2011-11-15', '2012-01-15', 0, 53, 1, '', 5);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (24, 22, '2011-11-15', '2012-01-15', 0, 67, 1, '', 6);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (25, 23, '2011-11-15', '2012-01-15', 0, 50, 1, '', 6);

INSERT INTO compra (idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor)
VALUES (26, 24, '2011-11-15', '2012-01-15', 0, 50, 1, '', 8);

--
-- Data for table public.detalleplanificacionproduccion (OID = 148378) (LIMIT 0,90)
--
INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (315, 89, NULL, 2, 22, 6, '2011-11-21', '14:08:43', '2011-11-21', '15:31:43', 1, 1, 18, 0, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (316, 89, NULL, 3, 23, 10, '2011-11-21', '14:11:28', '2011-11-21', '14:23:28', 8, 1, 18, 0, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (317, 89, NULL, 2, 22, 6, '2011-11-21', '15:31:00', '2011-11-21', '16:54:00', 9, 1, 18, 1, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (318, 89, NULL, 3, 23, 10, '2011-11-21', '14:23:00', '2011-11-21', '14:35:00', 6, 1, 18, 1, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (319, 89, NULL, 2, 22, 6, '2011-11-21', '16:54:00', '2011-11-22', '09:17:00', 9, 1, 18, 2, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (320, 89, NULL, 3, 23, 10, '2011-11-21', '14:35:00', '2011-11-21', '14:47:00', 6, 1, 18, 2, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (321, 89, NULL, 2, 22, 6, '2011-11-22', '09:17:00', '2011-11-22', '10:40:00', 9, 1, 18, 3, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (322, 89, NULL, 3, 23, 10, '2011-11-21', '14:47:00', '2011-11-21', '14:59:00', 6, 1, 18, 3, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (323, 89, NULL, 2, 22, 6, '2011-11-22', '10:40:00', '2011-11-22', '12:03:00', 9, 1, 18, 4, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (324, 89, NULL, 3, 23, 10, '2011-11-21', '14:59:00', '2011-11-21', '15:11:00', 6, 1, 18, 4, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (325, 89, NULL, 2, 22, 6, '2011-11-22', '12:03:00', '2011-11-22', '13:26:00', 9, 1, 18, 5, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (326, 89, NULL, 3, 23, 10, '2011-11-21', '15:11:00', '2011-11-21', '15:23:00', 6, 1, 18, 5, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (327, 89, NULL, 2, 22, 6, '2011-11-22', '13:26:00', '2011-11-22', '14:49:00', 9, 1, 18, 6, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (328, 89, NULL, 3, 23, 10, '2011-11-21', '15:23:00', '2011-11-21', '15:35:00', 6, 1, 18, 6, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (329, 89, NULL, 2, 22, 6, '2011-11-22', '14:49:00', '2011-11-22', '16:12:00', 9, 1, 18, 7, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (330, 89, NULL, 3, 23, 10, '2011-11-21', '15:35:00', '2011-11-21', '15:47:00', 6, 1, 18, 7, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (331, 89, NULL, 2, 22, 6, '2011-11-22', '16:12:00', '2011-11-23', '08:35:00', 9, 1, 18, 8, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (332, 89, NULL, 3, 23, 10, '2011-11-21', '15:47:00', '2011-11-21', '15:59:00', 6, 1, 18, 8, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (333, 89, NULL, 2, 22, 6, '2011-11-23', '08:35:00', '2011-11-23', '09:58:00', 9, 1, 18, 9, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (334, 89, NULL, 3, 23, 10, '2011-11-21', '15:59:00', '2011-11-21', '16:11:00', 6, 1, 18, 9, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (335, 89, NULL, 2, 22, 6, '2011-11-23', '09:58:00', '2011-11-23', '11:21:00', 9, 1, 18, 10, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (336, 89, NULL, 3, 23, 10, '2011-11-21', '16:11:00', '2011-11-21', '16:23:00', 6, 1, 18, 10, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (337, 89, NULL, 2, 22, 6, '2011-11-23', '11:21:00', '2011-11-23', '12:44:00', 9, 1, 18, 11, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (338, 89, NULL, 3, 23, 10, '2011-11-21', '16:23:00', '2011-11-21', '16:35:00', 6, 1, 18, 11, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (339, 89, NULL, 2, 22, 6, '2011-11-23', '12:44:00', '2011-11-23', '14:07:00', 9, 1, 18, 12, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (340, 89, NULL, 3, 23, 10, '2011-11-21', '16:35:00', '2011-11-21', '16:47:00', 6, 1, 18, 12, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (341, 89, NULL, 2, 22, 6, '2011-11-23', '14:07:00', '2011-11-23', '15:30:00', 9, 1, 18, 13, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (342, 89, NULL, 3, 23, 10, '2011-11-21', '16:47:00', '2011-11-21', '16:59:00', 6, 1, 18, 13, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (343, 89, NULL, 2, 22, 6, '2011-11-23', '15:30:00', '2011-11-23', '16:53:00', 9, 1, 18, 14, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (344, 89, NULL, 3, 23, 10, '2011-11-21', '16:59:00', '2011-11-22', '08:11:00', 6, 1, 18, 14, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (345, 89, NULL, 2, 22, 6, '2011-11-23', '16:53:00', '2011-11-24', '09:16:00', 9, 1, 18, 15, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (346, 89, NULL, 3, 23, 10, '2011-11-22', '08:11:00', '2011-11-22', '08:23:00', 6, 1, 18, 15, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (347, 89, NULL, 2, 22, 6, '2011-11-24', '09:16:00', '2011-11-24', '10:39:00', 9, 1, 18, 16, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (348, 89, NULL, 3, 23, 10, '2011-11-22', '08:23:00', '2011-11-22', '08:35:00', 6, 1, 18, 16, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (349, 89, NULL, 2, 22, 6, '2011-11-24', '10:39:00', '2011-11-24', '12:02:00', 9, 1, 18, 17, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (350, 89, NULL, 3, 23, 10, '2011-11-22', '08:35:00', '2011-11-22', '08:47:00', 6, 1, 18, 17, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (351, 89, NULL, 2, 22, 6, '2011-11-24', '12:02:00', '2011-11-24', '13:25:00', 9, 1, 18, 18, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (352, 89, NULL, 3, 23, 10, '2011-11-22', '08:47:00', '2011-11-22', '08:59:00', 6, 1, 18, 18, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (353, 89, NULL, 2, 22, 6, '2011-11-24', '13:25:00', '2011-11-24', '14:48:00', 9, 1, 18, 19, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (354, 89, NULL, 3, 23, 10, '2011-11-22', '08:59:00', '2011-11-22', '09:11:00', 8, 1, 18, 19, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (355, 89, NULL, 2, 22, 6, '2011-11-24', '14:48:00', '2011-11-24', '16:11:00', 9, 1, 18, 20, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (356, 89, NULL, 3, 23, 10, '2011-11-22', '09:11:00', '2011-11-22', '09:23:00', 6, 1, 18, 20, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (357, 89, NULL, 2, 22, 6, '2011-11-24', '16:11:00', '2011-11-25', '08:34:00', 9, 1, 18, 21, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (358, 89, NULL, 3, 23, 10, '2011-11-22', '09:23:00', '2011-11-22', '09:35:00', 8, 1, 18, 21, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (359, 89, NULL, 2, 22, 6, '2011-11-25', '08:34:00', '2011-11-25', '09:57:00', 9, 1, 18, 22, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (360, 89, NULL, 3, 23, 10, '2011-11-22', '09:35:00', '2011-11-22', '09:47:00', 8, 1, 18, 22, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (361, 89, NULL, 2, 22, 6, '2011-11-25', '09:57:00', '2011-11-25', '11:20:00', 9, 1, 18, 23, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (362, 89, NULL, 3, 23, 10, '2011-11-22', '09:47:00', '2011-11-22', '09:59:00', 8, 1, 18, 23, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (363, 89, NULL, 2, 22, 6, '2011-11-25', '11:20:00', '2011-11-25', '12:43:00', 9, 1, 18, 24, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (364, 89, NULL, 3, 23, 10, '2011-11-22', '09:59:00', '2011-11-22', '10:11:00', 8, 1, 18, 24, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (365, 89, NULL, 2, 22, 6, '2011-11-25', '12:43:00', '2011-11-25', '14:06:00', 9, 1, 18, 25, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (366, 89, NULL, 3, 23, 10, '2011-11-22', '10:11:00', '2011-11-22', '10:23:00', 8, 1, 18, 25, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (367, 89, NULL, 2, 22, 6, '2011-11-25', '14:06:00', '2011-11-25', '15:29:00', 9, 1, 18, 26, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (368, 89, NULL, 3, 23, 10, '2011-11-22', '10:23:00', '2011-11-22', '10:35:00', 8, 1, 18, 26, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (369, 89, NULL, 2, 22, 6, '2011-11-25', '15:29:00', '2011-11-25', '16:52:00', 9, 1, 18, 27, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (370, 89, NULL, 3, 23, 10, '2011-11-22', '10:35:00', '2011-11-22', '10:47:00', 8, 1, 18, 27, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (371, 89, NULL, 2, 22, 6, '2011-11-25', '16:52:00', '2011-11-26', '09:15:00', 1, 1, 18, 28, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (372, 89, NULL, 3, 23, 10, '2011-11-22', '10:47:00', '2011-11-22', '10:59:00', 8, 1, 18, 28, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (373, 89, NULL, 2, 22, 6, '2011-11-26', '09:15:00', '2011-11-26', '10:38:00', 9, 1, 18, 29, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (374, 89, NULL, 3, 23, 10, '2011-11-22', '10:59:00', '2011-11-22', '11:11:00', 8, 1, 18, 29, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (375, 89, NULL, 2, 22, 6, '2011-11-26', '10:38:00', '2011-11-26', '12:01:00', 1, 1, 18, 30, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (376, 89, NULL, 3, 23, 10, '2011-11-22', '11:11:00', '2011-11-22', '11:23:00', 8, 1, 18, 30, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (377, 89, NULL, 2, 22, 6, '2011-11-26', '12:01:00', '2011-11-26', '13:24:00', 1, 1, 18, 31, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (378, 89, NULL, 3, 23, 10, '2011-11-22', '11:23:00', '2011-11-22', '11:35:00', 8, 1, 18, 31, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (379, 89, NULL, 2, 22, 6, '2011-11-26', '13:24:00', '2011-11-26', '14:47:00', 1, 1, 18, 32, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (380, 89, NULL, 3, 23, 10, '2011-11-22', '11:35:00', '2011-11-22', '11:47:00', 8, 1, 18, 32, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (381, 89, NULL, 2, 22, 6, '2011-11-26', '14:47:00', '2011-11-26', '16:10:00', 1, 1, 18, 33, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (382, 89, NULL, 3, 23, 10, '2011-11-22', '11:47:00', '2011-11-22', '11:59:00', 8, 1, 18, 33, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (383, 89, NULL, 2, 22, 6, '2011-11-26', '16:10:00', '2011-11-27', '08:33:00', 1, 1, 18, 34, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (384, 89, NULL, 3, 23, 10, '2011-11-22', '11:59:00', '2011-11-22', '12:11:00', 8, 1, 18, 34, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (385, 89, NULL, 2, 22, 6, '2011-11-27', '08:33:00', '2011-11-27', '09:56:00', 1, 1, 18, 35, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (386, 89, NULL, 3, 23, 10, '2011-11-22', '12:11:00', '2011-11-22', '12:23:00', 8, 1, 18, 35, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (387, 89, NULL, 2, 22, 6, '2011-11-27', '09:56:00', '2011-11-27', '11:19:00', 1, 1, 18, 36, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (388, 89, NULL, 3, 23, 10, '2011-11-22', '12:23:00', '2011-11-22', '12:35:00', 8, 1, 18, 36, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (389, 89, NULL, 2, 22, 6, '2011-11-27', '11:19:00', '2011-11-27', '12:42:00', 1, 1, 18, 37, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (390, 89, NULL, 3, 23, 10, '2011-11-22', '12:35:00', '2011-11-22', '12:47:00', 8, 1, 18, 37, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (391, 89, NULL, 2, 22, 6, '2011-11-27', '12:42:00', '2011-11-27', '14:05:00', 1, 1, 18, 38, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (392, 89, NULL, 3, 23, 10, '2011-11-22', '12:47:00', '2011-11-22', '12:59:00', 8, 1, 18, 38, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (393, 89, NULL, 2, 22, 6, '2011-11-27', '14:05:00', '2011-11-27', '15:28:00', 1, 1, 18, 39, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (394, 89, NULL, 3, 23, 10, '2011-11-22', '12:59:00', '2011-11-22', '13:11:00', 8, 1, 18, 39, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (395, 90, NULL, 2, 12, 12, '2011-11-28', '16:28:00', '2011-11-29', '08:17:00', 11, 1, 17, 0, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (396, 90, NULL, 2, 12, 11, '2011-11-29', '10:48:00', '2011-11-29', '11:37:00', 10, 1, 17, 1, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (397, 90, NULL, 2, 12, 12, '2011-11-29', '08:17:00', '2011-11-29', '09:06:00', 11, 1, 17, 2, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (398, 90, NULL, 2, 12, 11, '2011-11-29', '11:37:00', '2011-11-29', '12:26:00', 10, 1, 17, 3, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (399, 90, NULL, 2, 12, 10, '2011-11-22', '13:11:00', '2011-11-22', '14:00:00', 11, 1, 17, 4, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (400, 90, NULL, 2, 12, 12, '2011-11-29', '10:44:00', '2011-11-29', '11:33:00', 9, 1, 17, 5, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (401, 90, NULL, 2, 12, 10, '2011-11-22', '14:00:00', '2011-11-22', '14:49:00', 11, 1, 17, 6, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (402, 90, NULL, 2, 12, 12, '2011-11-29', '09:55:00', '2011-11-29', '10:44:00', 10, 1, 17, 7, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (403, 90, NULL, 2, 12, 10, '2011-11-22', '14:49:00', '2011-11-22', '15:38:00', 11, 1, 17, 8, NULL, 0);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto, indexproducto, detalleanterior, indexpieza)
VALUES (404, 90, NULL, 2, 12, 12, '2011-11-29', '09:06:00', '2011-11-29', '09:55:00', 9, 1, 17, 9, NULL, 0);

--
-- Data for table public.detalleproducto (OID = 148382) (LIMIT 0,16)
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
-- Data for table public.detallepedido (OID = 148386) (LIMIT 0,9)
--
INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (89, 65, 216, 40, 18);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (90, 66, 168, 10, 19);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (91, 67, 200, 10, 17);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (92, 68, 168, 10, 19);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (93, 69, 61, 5, 23);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (94, 70, 10, 5, 21);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (95, 70, 15, 5, 24);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (96, 71, 200, 15, 17);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (97, 72, 168, 10, 19);

--
-- Data for table public.detalleplanificacioncalidad (OID = 148397) (LIMIT 0,90)
--
INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (78, 20, NULL, '2011-11-27', '2011-11-27', '15:28:00', '15:43:00', 3, 22, 18, 12, NULL, 1, NULL, 0, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (79, 20, NULL, '2011-11-27', '2011-11-27', '15:28:00', '15:48:00', 1, 23, 18, 11, NULL, 1, NULL, 0, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (80, 20, NULL, '2011-11-27', '2011-11-27', '15:43:00', '15:58:00', 3, 22, 18, 12, NULL, 1, NULL, 1, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (81, 20, NULL, '2011-11-27', '2011-11-27', '15:48:00', '16:08:00', 1, 23, 18, 11, NULL, 1, NULL, 1, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (82, 20, NULL, '2011-11-27', '2011-11-27', '15:58:00', '16:13:00', 3, 22, 18, 12, NULL, 1, NULL, 2, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (83, 20, NULL, '2011-11-27', '2011-11-27', '16:08:00', '16:28:00', 1, 23, 18, 11, NULL, 1, NULL, 2, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (84, 20, NULL, '2011-11-27', '2011-11-27', '16:13:00', '16:28:00', 3, 22, 18, 12, NULL, 1, NULL, 3, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (85, 20, NULL, '2011-11-27', '2011-11-27', '16:28:00', '16:48:00', 1, 23, 18, 11, NULL, 1, NULL, 3, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (86, 20, NULL, '2011-11-27', '2011-11-27', '16:28:00', '16:43:00', 3, 22, 18, 12, NULL, 1, NULL, 4, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (87, 20, NULL, '2011-11-27', '2011-11-28', '17:00:00', '08:08:00', 1, 23, 18, 11, NULL, 1, NULL, 4, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (88, 20, NULL, '2011-11-27', '2011-11-27', '16:43:00', '16:58:00', 3, 22, 18, 12, NULL, 1, NULL, 5, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (89, 20, NULL, '2011-11-28', '2011-11-28', '08:08:00', '08:28:00', 1, 23, 18, 11, NULL, 1, NULL, 5, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (90, 20, NULL, '2011-11-27', '2011-11-28', '17:00:00', '08:13:00', 3, 22, 18, 12, NULL, 1, NULL, 6, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (91, 20, NULL, '2011-11-28', '2011-11-28', '08:28:00', '08:48:00', 1, 23, 18, 11, NULL, 1, NULL, 6, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (92, 20, NULL, '2011-11-28', '2011-11-28', '08:13:00', '08:28:00', 3, 22, 18, 12, NULL, 1, NULL, 7, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (93, 20, NULL, '2011-11-28', '2011-11-28', '08:48:00', '09:08:00', 1, 23, 18, 11, NULL, 1, NULL, 7, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (94, 20, NULL, '2011-11-28', '2011-11-28', '08:28:00', '08:43:00', 3, 22, 18, 12, NULL, 1, NULL, 8, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (95, 20, NULL, '2011-11-28', '2011-11-28', '09:08:00', '09:28:00', 1, 23, 18, 11, NULL, 1, NULL, 8, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (96, 20, NULL, '2011-11-28', '2011-11-28', '08:43:00', '08:58:00', 3, 22, 18, 12, NULL, 1, NULL, 9, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (97, 20, NULL, '2011-11-28', '2011-11-28', '09:28:00', '09:48:00', 1, 23, 18, 11, NULL, 1, NULL, 9, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (98, 20, NULL, '2011-11-28', '2011-11-28', '08:58:00', '09:13:00', 3, 22, 18, 12, NULL, 1, NULL, 10, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (99, 20, NULL, '2011-11-28', '2011-11-28', '09:48:00', '10:08:00', 1, 23, 18, 11, NULL, 1, NULL, 10, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (100, 20, NULL, '2011-11-28', '2011-11-28', '09:13:00', '09:28:00', 3, 22, 18, 12, NULL, 1, NULL, 11, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (101, 20, NULL, '2011-11-28', '2011-11-28', '10:08:00', '10:28:00', 1, 23, 18, 11, NULL, 1, NULL, 11, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (102, 20, NULL, '2011-11-28', '2011-11-28', '09:28:00', '09:43:00', 3, 22, 18, 12, NULL, 1, NULL, 12, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (103, 20, NULL, '2011-11-28', '2011-11-28', '10:28:00', '10:48:00', 1, 23, 18, 11, NULL, 1, NULL, 12, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (104, 20, NULL, '2011-11-28', '2011-11-28', '16:13:00', '16:28:00', 3, 22, 18, 12, NULL, 1, NULL, 13, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (105, 20, NULL, '2011-11-28', '2011-11-28', '10:48:00', '11:08:00', 1, 23, 18, 11, NULL, 1, NULL, 13, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (106, 20, NULL, '2011-11-28', '2011-11-28', '09:43:00', '09:58:00', 3, 22, 18, 12, NULL, 1, NULL, 14, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (107, 20, NULL, '2011-11-28', '2011-11-28', '11:08:00', '11:28:00', 1, 23, 18, 11, NULL, 1, NULL, 14, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (108, 20, NULL, '2011-11-28', '2011-11-28', '09:58:00', '10:13:00', 3, 22, 18, 12, NULL, 1, NULL, 15, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (109, 20, NULL, '2011-11-28', '2011-11-28', '11:28:00', '11:48:00', 1, 23, 18, 11, NULL, 1, NULL, 15, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (110, 20, NULL, '2011-11-28', '2011-11-28', '10:13:00', '10:28:00', 3, 22, 18, 12, NULL, 1, NULL, 16, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (111, 20, NULL, '2011-11-28', '2011-11-28', '11:48:00', '12:08:00', 1, 23, 18, 11, NULL, 1, NULL, 16, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (112, 20, NULL, '2011-11-28', '2011-11-28', '10:28:00', '10:43:00', 3, 22, 18, 12, NULL, 1, NULL, 17, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (113, 20, NULL, '2011-11-28', '2011-11-28', '12:08:00', '12:28:00', 1, 23, 18, 11, NULL, 1, NULL, 17, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (114, 20, NULL, '2011-11-28', '2011-11-28', '10:43:00', '10:58:00', 3, 22, 18, 12, NULL, 1, NULL, 18, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (115, 20, NULL, '2011-11-28', '2011-11-28', '12:28:00', '12:48:00', 1, 23, 18, 11, NULL, 1, NULL, 18, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (116, 20, NULL, '2011-11-28', '2011-11-28', '10:58:00', '11:13:00', 3, 22, 18, 12, NULL, 1, NULL, 19, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (117, 20, NULL, '2011-11-28', '2011-11-28', '12:48:00', '13:08:00', 1, 23, 18, 11, NULL, 1, NULL, 19, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (118, 20, NULL, '2011-11-28', '2011-11-28', '11:13:00', '11:28:00', 3, 22, 18, 12, NULL, 1, NULL, 20, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (119, 20, NULL, '2011-11-28', '2011-11-28', '13:08:00', '13:28:00', 1, 23, 18, 11, NULL, 1, NULL, 20, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (120, 20, NULL, '2011-11-28', '2011-11-28', '11:28:00', '11:43:00', 3, 22, 18, 12, NULL, 1, NULL, 21, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (121, 20, NULL, '2011-11-28', '2011-11-28', '13:28:00', '13:48:00', 1, 23, 18, 11, NULL, 1, NULL, 21, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (122, 20, NULL, '2011-11-28', '2011-11-28', '11:43:00', '11:58:00', 3, 22, 18, 12, NULL, 1, NULL, 22, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (123, 20, NULL, '2011-11-28', '2011-11-28', '13:48:00', '14:08:00', 1, 23, 18, 11, NULL, 1, NULL, 22, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (124, 20, NULL, '2011-11-28', '2011-11-28', '11:58:00', '12:13:00', 3, 22, 18, 12, NULL, 1, NULL, 23, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (125, 20, NULL, '2011-11-28', '2011-11-28', '14:08:00', '14:28:00', 1, 23, 18, 11, NULL, 1, NULL, 23, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (126, 20, NULL, '2011-11-28', '2011-11-28', '12:13:00', '12:28:00', 3, 22, 18, 12, NULL, 1, NULL, 24, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (127, 20, NULL, '2011-11-28', '2011-11-28', '14:28:00', '14:48:00', 1, 23, 18, 11, NULL, 1, NULL, 24, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (128, 20, NULL, '2011-11-28', '2011-11-28', '12:28:00', '12:43:00', 3, 22, 18, 12, NULL, 1, NULL, 25, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (129, 20, NULL, '2011-11-28', '2011-11-28', '14:48:00', '15:08:00', 1, 23, 18, 11, NULL, 1, NULL, 25, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (130, 20, NULL, '2011-11-28', '2011-11-28', '12:43:00', '12:58:00', 3, 22, 18, 12, NULL, 1, NULL, 26, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (131, 20, NULL, '2011-11-28', '2011-11-28', '15:08:00', '15:28:00', 1, 23, 18, 11, NULL, 1, NULL, 26, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (132, 20, NULL, '2011-11-28', '2011-11-28', '12:58:00', '13:13:00', 3, 22, 18, 12, NULL, 1, NULL, 27, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (133, 20, NULL, '2011-11-28', '2011-11-28', '15:28:00', '15:48:00', 1, 23, 18, 11, NULL, 1, NULL, 27, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (134, 20, NULL, '2011-11-28', '2011-11-28', '13:13:00', '13:28:00', 3, 22, 18, 12, NULL, 1, NULL, 28, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (135, 20, NULL, '2011-11-28', '2011-11-28', '15:48:00', '16:08:00', 1, 23, 18, 11, NULL, 1, NULL, 28, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (136, 20, NULL, '2011-11-28', '2011-11-28', '13:28:00', '13:43:00', 3, 22, 18, 12, NULL, 1, NULL, 29, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (137, 20, NULL, '2011-11-28', '2011-11-28', '16:08:00', '16:28:00', 1, 23, 18, 11, NULL, 1, NULL, 29, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (138, 20, NULL, '2011-11-28', '2011-11-28', '13:43:00', '13:58:00', 3, 22, 18, 12, NULL, 1, NULL, 30, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (139, 20, NULL, '2011-11-28', '2011-11-28', '16:28:00', '16:48:00', 1, 23, 18, 11, NULL, 1, NULL, 30, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (140, 20, NULL, '2011-11-28', '2011-11-28', '13:58:00', '14:13:00', 3, 22, 18, 12, NULL, 1, NULL, 31, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (141, 20, NULL, '2011-11-28', '2011-11-29', '17:00:00', '08:08:00', 1, 23, 18, 11, NULL, 1, NULL, 31, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (142, 20, NULL, '2011-11-28', '2011-11-28', '14:13:00', '14:28:00', 3, 22, 18, 12, NULL, 1, NULL, 32, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (143, 20, NULL, '2011-11-29', '2011-11-29', '08:08:00', '08:28:00', 1, 23, 18, 11, NULL, 1, NULL, 32, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (144, 20, NULL, '2011-11-28', '2011-11-28', '14:28:00', '14:43:00', 3, 22, 18, 12, NULL, 1, NULL, 33, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (145, 20, NULL, '2011-11-29', '2011-11-29', '08:28:00', '08:48:00', 1, 23, 18, 11, NULL, 1, NULL, 33, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (146, 20, NULL, '2011-11-28', '2011-11-28', '14:43:00', '14:58:00', 3, 22, 18, 12, NULL, 1, NULL, 34, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (147, 20, NULL, '2011-11-29', '2011-11-29', '08:48:00', '09:08:00', 1, 23, 18, 11, NULL, 1, NULL, 34, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (148, 20, NULL, '2011-11-28', '2011-11-28', '14:58:00', '15:13:00', 3, 22, 18, 12, NULL, 1, NULL, 35, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (149, 20, NULL, '2011-11-29', '2011-11-29', '09:08:00', '09:28:00', 1, 23, 18, 11, NULL, 1, NULL, 35, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (150, 20, NULL, '2011-11-28', '2011-11-28', '15:13:00', '15:28:00', 3, 22, 18, 12, NULL, 1, NULL, 36, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (151, 20, NULL, '2011-11-29', '2011-11-29', '09:28:00', '09:48:00', 1, 23, 18, 11, NULL, 1, NULL, 36, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (152, 20, NULL, '2011-11-28', '2011-11-28', '15:28:00', '15:43:00', 3, 22, 18, 12, NULL, 1, NULL, 37, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (153, 20, NULL, '2011-11-29', '2011-11-29', '09:48:00', '10:08:00', 1, 23, 18, 11, NULL, 1, NULL, 37, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (154, 20, NULL, '2011-11-28', '2011-11-28', '15:43:00', '15:58:00', 3, 22, 18, 12, NULL, 1, NULL, 38, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (155, 20, NULL, '2011-11-29', '2011-11-29', '10:08:00', '10:28:00', 1, 23, 18, 11, NULL, 1, NULL, 38, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (156, 20, NULL, '2011-11-28', '2011-11-28', '15:58:00', '16:13:00', 3, 22, 18, 12, NULL, 1, NULL, 39, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (157, 20, NULL, '2011-11-29', '2011-11-29', '10:28:00', '10:48:00', 1, 23, 18, 11, NULL, 1, NULL, 39, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (158, 21, NULL, '2011-11-29', '2011-11-29', '11:33:00', '11:48:00', 9, 12, 17, 12, NULL, 1, NULL, 0, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (159, 21, NULL, '2011-11-29', '2011-11-29', '12:26:00', '12:41:00', 9, 12, 17, 11, NULL, 1, NULL, 1, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (160, 21, NULL, '2011-11-29', '2011-11-29', '11:48:00', '12:03:00', 9, 12, 17, 12, NULL, 1, NULL, 2, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (161, 21, NULL, '2011-11-29', '2011-11-29', '12:41:00', '12:56:00', 9, 12, 17, 11, NULL, 1, NULL, 3, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (162, 21, NULL, '2011-11-29', '2011-11-29', '12:03:00', '12:18:00', 9, 12, 17, 12, NULL, 1, NULL, 4, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (163, 21, NULL, '2011-11-29', '2011-11-29', '12:56:00', '13:11:00', 9, 12, 17, 11, NULL, 1, NULL, 5, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (164, 21, NULL, '2011-11-29', '2011-11-29', '12:18:00', '12:33:00', 9, 12, 17, 12, NULL, 1, NULL, 6, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (165, 21, NULL, '2011-11-29', '2011-11-29', '13:11:00', '13:26:00', 9, 12, 17, 11, NULL, 1, NULL, 7, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (166, 21, NULL, '2011-11-29', '2011-11-29', '12:33:00', '12:48:00', 9, 12, 17, 12, NULL, 1, NULL, 8, 0);

INSERT INTO detalleplanificacioncalidad (iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, fechainicio, fechafin, horainicio, horafin, procesocalidad, pieza, producto, empleado, maquina, orden, detalleanterior, indexproducto, indexpieza)
VALUES (167, 21, NULL, '2011-11-29', '2011-11-29', '13:26:00', '13:41:00', 9, 12, 17, 11, NULL, 1, NULL, 9, 0);

--
-- Data for table public.detallecompra (OID = 148401) (LIMIT 0,24)
--
INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (4, 3, 13, 17, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (5, 4, 43, 23, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (6, 5, 33, 22, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (7, 6, 22, 29, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (8, 7, 34, 30, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (9, 8, 1, 15, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (10, 9, 1, 20, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (11, 10, 1, 22, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (12, 11, 1, 17, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (13, 12, 1, 22, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (14, 13, 1, 21, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (15, 14, 1, 16, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (16, 15, 1, 26, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (17, 16, 1, 14, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (18, 17, 1, 20, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (19, 18, 1, 13, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (20, 19, 1, 23, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (21, 20, 1, 20, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (22, 21, 1, 20, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (23, 22, 1, 20, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (24, 23, 1, 24, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (25, 24, 1, 22, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (26, 25, 1, 24, 0, NULL, 1);

INSERT INTO detallecompra (iddetalle, idcompra, cantidad, materiaprima, preciohistorico, fecharecepcionparcial, estado)
VALUES (27, 26, 1, 29, 0, NULL, 1);

--
-- Data for table public.tiporeclamo (OID = 148412) (LIMIT 0,3)
--
INSERT INTO tiporeclamo (idtiporeclamo, nombre, descripcion)
VALUES (1, 'RECLAMO PROVEEDOR', 'Reclamo que se realiza a un Proveedor por materia prima defectuosa');

INSERT INTO tiporeclamo (idtiporeclamo, nombre, descripcion)
VALUES (2, 'RECLAMO CLIENTE', 'Reclamo que se recibe de un Cliente por inconformidad de un pedido');

INSERT INTO tiporeclamo (idtiporeclamo, nombre, descripcion)
VALUES (3, 'RECLAMO EMPRESA METALURGICA', 'Reclamo que se realiza a una empresa tercerizada por inconformidades');

--
-- Data for table public.proveedorxmateriaprima (OID = 148461) (LIMIT 0,35)
--
INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (4, 13, 40);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (4, 14, 45);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (4, 15, 55);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (4, 16, 35);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (7, 17, 60);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (7, 18, 60);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (7, 19, 66);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (7, 13, 45);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (7, 14, 55);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (7, 15, 63);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (4, 17, 64);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (4, 18, 63);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (4, 19, 71);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (5, 20, 44);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (6, 20, 46);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (5, 21, 39);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (6, 21, 38);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (6, 22, 67);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (5, 22, 60);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (5, 23, 55);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (6, 23, 47);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (5, 24, 53);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (6, 24, 50);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (7, 25, 67);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (8, 25, 66);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (7, 26, 55);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (8, 26, 54);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (7, 27, 45);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (8, 27, 48);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (7, 28, 49);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (8, 28, 47);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (7, 29, 55);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (8, 29, 50);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (7, 30, 47);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (8, 30, 56);

--
-- Data for table public.piezaxetapadeproduccion (OID = 148473) (LIMIT 0,23)
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
-- Data for table public.empleadoxturno (OID = 148479) (LIMIT 0,14)
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
-- Data for table public.provincia (OID = 148482) (LIMIT 0,7)
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
-- Data for table public.localidad (OID = 148486) (LIMIT 0,35)
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
-- Data for table public.barrio (OID = 148490) (LIMIT 0,75)
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
-- Data for table public.usuarioxrol (OID = 148494) (LIMIT 0,22)
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
-- Data for table public.estadodetallecompra (OID = 148531) (LIMIT 0,8)
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
-- Data for table public.estadodetalletrabajotercerizado (OID = 148538) (LIMIT 0,9)
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
-- Data for table public.estadoejecucionprocesocalidad (OID = 148545) (LIMIT 0,6)
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
-- Data for table public.estadoproductoreal (OID = 148563) (LIMIT 0,11)
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
-- Data for table public.detallepresupuesto (OID = 148574) (LIMIT 0,8)
--
INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (161, 136, 90, 19, 10, 168);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (165, 140, 94, 21, 5, 10);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (166, 140, 95, 24, 5, 15);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (167, 141, 96, 17, 15, 200);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (160, 135, 89, 18, 40, 237.6);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (162, 137, 91, 17, 10, 220);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (164, 139, 93, 23, 5, 67.1);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (163, 138, 92, 19, 10, 184.8);

--
-- Data for table public.estadoremito (OID = 148578) (LIMIT 0,1)
--
INSERT INTO estadoremito (idestado, nombre, descripcion)
VALUES (1, 'GENERADO', 'GENERADO');

--
-- Data for table public.tipomaterial (OID = 148585) (LIMIT 0,5)
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
-- Data for table public.unidadmedida (OID = 148802) (LIMIT 0,4)
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
-- Data for table public.detallepiezapresupuesto (OID = 148812) (LIMIT 0,15)
--
INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('01:23:20', 345, 2, 267);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:12:30', 346, 3, 268);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('01:38:20', 347, 6, 269);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('01:08:45', 348, 1, 270);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:37:30', 349, 3, 271);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:49:10', 350, 2, 272);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:49:10', 351, 1, 273);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('01:38:20', 352, 6, 273);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('01:08:45', 353, 1, 274);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:37:30', 354, 3, 275);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('01:02:30', 355, 2, 276);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('01:40:00', 356, 5, 276);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:08:20', 357, 2, 277);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:04:10', 358, 2, 278);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:49:10', 359, 2, 279);

--
-- Data for table public.detalleproductopresupuesto (OID = 148818) (LIMIT 0,13)
--
INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (277, 165, 17, 19, 1, 1, 0, 0);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (278, 166, 20, 23, 1, 1, 0, 0);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (267, 160, 22, 13, 1, 1, 40, 4);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (268, 160, 23, 24, 1, 1, 50, 6);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (272, 162, 12, 14, 1, 1, 45, 4);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (276, 164, 19, 27, 1, 1, 45, 7);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (275, 163, 15, 13, 1, 1, 40, 4);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (274, 163, 14, 13, 1, 1, 40, 4);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (273, 163, 13, 13, 1, 1, 40, 4);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (279, 167, 12, 14, 1, 1, 45, 4);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (269, 161, 13, NULL, 0, 1, 0, 0);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (270, 161, 14, NULL, 0, 1, 0, 0);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (271, 161, 15, NULL, 0, 1, 0, 0);

--
-- Data for table public.detallepiezacalidadpresupuesto (OID = 148824) (LIMIT 0,8)
--
INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (193, 1, '00:15:00', 3, 267);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (194, 1, '00:20:00', 1, 268);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (195, 1, '00:15:00', 9, 272);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (196, 1, '00:15:00', 9, 276);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (197, 1, '00:15:00', 9, 279);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (198, 1, '00:15:00', 9, 273);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (199, 1, '00:15:00', 9, 274);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (200, 1, '00:15:00', 9, 275);

--
-- Data for table public.disponibilidadhoraria (OID = 148853) (LIMIT 0,191)
--
INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (365, '2011-11-21', NULL, 6, '14:08:43', '15:31:43');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (366, '2011-11-21', NULL, 10, '14:11:28', '14:23:28');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (367, '2011-11-21', NULL, 6, '15:31:00', '16:54:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (368, '2011-11-21', NULL, 10, '14:23:00', '14:35:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (369, '2011-11-21', NULL, 6, '16:54:00', '17:00:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (370, '2011-11-22', NULL, 6, '08:00:00', '09:17:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (371, '2011-11-21', NULL, 10, '14:35:00', '14:47:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (372, '2011-11-22', NULL, 6, '09:17:00', '10:40:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (373, '2011-11-21', NULL, 10, '14:47:00', '14:59:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (374, '2011-11-22', NULL, 6, '10:40:00', '12:03:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (375, '2011-11-21', NULL, 10, '14:59:00', '15:11:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (376, '2011-11-22', NULL, 6, '12:03:00', '13:26:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (377, '2011-11-21', NULL, 10, '15:11:00', '15:23:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (378, '2011-11-22', NULL, 6, '13:26:00', '14:49:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (379, '2011-11-21', NULL, 10, '15:23:00', '15:35:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (380, '2011-11-22', NULL, 6, '14:49:00', '16:12:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (381, '2011-11-21', NULL, 10, '15:35:00', '15:47:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (382, '2011-11-22', NULL, 6, '16:12:00', '17:00:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (383, '2011-11-23', NULL, 6, '08:00:00', '08:35:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (384, '2011-11-21', NULL, 10, '15:47:00', '15:59:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (385, '2011-11-23', NULL, 6, '08:35:00', '09:58:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (386, '2011-11-21', NULL, 10, '15:59:00', '16:11:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (387, '2011-11-23', NULL, 6, '09:58:00', '11:21:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (388, '2011-11-21', NULL, 10, '16:11:00', '16:23:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (389, '2011-11-23', NULL, 6, '11:21:00', '12:44:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (390, '2011-11-21', NULL, 10, '16:23:00', '16:35:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (391, '2011-11-23', NULL, 6, '12:44:00', '14:07:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (392, '2011-11-21', NULL, 10, '16:35:00', '16:47:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (393, '2011-11-23', NULL, 6, '14:07:00', '15:30:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (394, '2011-11-21', NULL, 10, '16:47:00', '16:59:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (395, '2011-11-23', NULL, 6, '15:30:00', '16:53:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (396, '2011-11-21', NULL, 10, '16:59:00', '17:00:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (397, '2011-11-22', NULL, 10, '08:00:00', '08:11:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (398, '2011-11-23', NULL, 6, '16:53:00', '17:00:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (399, '2011-11-24', NULL, 6, '08:00:00', '09:16:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (400, '2011-11-22', NULL, 10, '08:11:00', '08:23:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (401, '2011-11-24', NULL, 6, '09:16:00', '10:39:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (402, '2011-11-22', NULL, 10, '08:23:00', '08:35:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (403, '2011-11-24', NULL, 6, '10:39:00', '12:02:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (404, '2011-11-22', NULL, 10, '08:35:00', '08:47:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (405, '2011-11-24', NULL, 6, '12:02:00', '13:25:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (406, '2011-11-22', NULL, 10, '08:47:00', '08:59:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (407, '2011-11-24', NULL, 6, '13:25:00', '14:48:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (408, '2011-11-22', NULL, 10, '08:59:00', '09:11:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (409, '2011-11-24', NULL, 6, '14:48:00', '16:11:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (410, '2011-11-22', NULL, 10, '09:11:00', '09:23:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (411, '2011-11-24', NULL, 6, '16:11:00', '17:00:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (412, '2011-11-25', NULL, 6, '08:00:00', '08:34:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (413, '2011-11-22', NULL, 10, '09:23:00', '09:35:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (414, '2011-11-25', NULL, 6, '08:34:00', '09:57:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (415, '2011-11-22', NULL, 10, '09:35:00', '09:47:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (416, '2011-11-25', NULL, 6, '09:57:00', '11:20:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (417, '2011-11-22', NULL, 10, '09:47:00', '09:59:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (418, '2011-11-25', NULL, 6, '11:20:00', '12:43:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (419, '2011-11-22', NULL, 10, '09:59:00', '10:11:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (420, '2011-11-25', NULL, 6, '12:43:00', '14:06:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (421, '2011-11-22', NULL, 10, '10:11:00', '10:23:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (422, '2011-11-25', NULL, 6, '14:06:00', '15:29:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (423, '2011-11-22', NULL, 10, '10:23:00', '10:35:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (424, '2011-11-25', NULL, 6, '15:29:00', '16:52:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (425, '2011-11-22', NULL, 10, '10:35:00', '10:47:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (426, '2011-11-25', NULL, 6, '16:52:00', '17:00:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (427, '2011-11-26', NULL, 6, '08:00:00', '09:15:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (428, '2011-11-22', NULL, 10, '10:47:00', '10:59:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (429, '2011-11-26', NULL, 6, '09:15:00', '10:38:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (430, '2011-11-22', NULL, 10, '10:59:00', '11:11:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (431, '2011-11-26', NULL, 6, '10:38:00', '12:01:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (432, '2011-11-22', NULL, 10, '11:11:00', '11:23:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (433, '2011-11-26', NULL, 6, '12:01:00', '13:24:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (434, '2011-11-22', NULL, 10, '11:23:00', '11:35:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (435, '2011-11-26', NULL, 6, '13:24:00', '14:47:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (436, '2011-11-22', NULL, 10, '11:35:00', '11:47:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (437, '2011-11-26', NULL, 6, '14:47:00', '16:10:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (438, '2011-11-22', NULL, 10, '11:47:00', '11:59:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (439, '2011-11-26', NULL, 6, '16:10:00', '17:00:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (440, '2011-11-27', NULL, 6, '08:00:00', '08:33:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (441, '2011-11-22', NULL, 10, '11:59:00', '12:11:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (442, '2011-11-27', NULL, 6, '08:33:00', '09:56:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (443, '2011-11-22', NULL, 10, '12:11:00', '12:23:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (444, '2011-11-27', NULL, 6, '09:56:00', '11:19:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (445, '2011-11-22', NULL, 10, '12:23:00', '12:35:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (446, '2011-11-27', NULL, 6, '11:19:00', '12:42:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (447, '2011-11-22', NULL, 10, '12:35:00', '12:47:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (448, '2011-11-27', NULL, 6, '12:42:00', '14:05:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (449, '2011-11-22', NULL, 10, '12:47:00', '12:59:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (450, '2011-11-27', NULL, 6, '14:05:00', '15:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (451, '2011-11-22', NULL, 10, '12:59:00', '13:11:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (452, '2011-11-27', NULL, 12, '15:28:00', '15:43:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (453, '2011-11-27', NULL, 11, '15:28:00', '15:48:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (454, '2011-11-27', NULL, 12, '15:43:00', '15:58:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (455, '2011-11-27', NULL, 11, '15:48:00', '16:08:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (456, '2011-11-27', NULL, 12, '15:58:00', '16:13:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (457, '2011-11-27', NULL, 11, '16:08:00', '16:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (458, '2011-11-27', NULL, 12, '16:13:00', '16:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (459, '2011-11-27', NULL, 11, '16:28:00', '16:48:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (460, '2011-11-27', NULL, 12, '16:28:00', '16:43:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (461, '2011-11-27', NULL, 11, '17:00:00', '17:00:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (462, '2011-11-28', NULL, 11, '08:00:00', '08:08:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (463, '2011-11-27', NULL, 12, '16:43:00', '16:58:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (464, '2011-11-28', NULL, 11, '08:08:00', '08:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (465, '2011-11-27', NULL, 12, '17:00:00', '17:00:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (466, '2011-11-28', NULL, 12, '08:00:00', '08:13:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (467, '2011-11-28', NULL, 11, '08:28:00', '08:48:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (468, '2011-11-28', NULL, 12, '08:13:00', '08:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (469, '2011-11-28', NULL, 11, '08:48:00', '09:08:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (470, '2011-11-28', NULL, 12, '08:28:00', '08:43:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (471, '2011-11-28', NULL, 11, '09:08:00', '09:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (472, '2011-11-28', NULL, 12, '08:43:00', '08:58:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (473, '2011-11-28', NULL, 11, '09:28:00', '09:48:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (474, '2011-11-28', NULL, 12, '08:58:00', '09:13:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (475, '2011-11-28', NULL, 11, '09:48:00', '10:08:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (476, '2011-11-28', NULL, 12, '09:13:00', '09:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (477, '2011-11-28', NULL, 11, '10:08:00', '10:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (478, '2011-11-28', NULL, 12, '09:28:00', '09:43:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (479, '2011-11-28', NULL, 11, '10:28:00', '10:48:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (480, '2011-11-28', NULL, 12, '16:13:00', '16:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (481, '2011-11-28', NULL, 11, '10:48:00', '11:08:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (482, '2011-11-28', NULL, 12, '09:43:00', '09:58:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (483, '2011-11-28', NULL, 11, '11:08:00', '11:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (484, '2011-11-28', NULL, 12, '09:58:00', '10:13:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (485, '2011-11-28', NULL, 11, '11:28:00', '11:48:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (486, '2011-11-28', NULL, 12, '10:13:00', '10:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (487, '2011-11-28', NULL, 11, '11:48:00', '12:08:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (488, '2011-11-28', NULL, 12, '10:28:00', '10:43:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (489, '2011-11-28', NULL, 11, '12:08:00', '12:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (490, '2011-11-28', NULL, 12, '10:43:00', '10:58:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (491, '2011-11-28', NULL, 11, '12:28:00', '12:48:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (492, '2011-11-28', NULL, 12, '10:58:00', '11:13:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (493, '2011-11-28', NULL, 11, '12:48:00', '13:08:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (494, '2011-11-28', NULL, 12, '11:13:00', '11:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (495, '2011-11-28', NULL, 11, '13:08:00', '13:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (496, '2011-11-28', NULL, 12, '11:28:00', '11:43:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (497, '2011-11-28', NULL, 11, '13:28:00', '13:48:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (498, '2011-11-28', NULL, 12, '11:43:00', '11:58:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (499, '2011-11-28', NULL, 11, '13:48:00', '14:08:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (500, '2011-11-28', NULL, 12, '11:58:00', '12:13:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (501, '2011-11-28', NULL, 11, '14:08:00', '14:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (502, '2011-11-28', NULL, 12, '12:13:00', '12:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (503, '2011-11-28', NULL, 11, '14:28:00', '14:48:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (504, '2011-11-28', NULL, 12, '12:28:00', '12:43:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (505, '2011-11-28', NULL, 11, '14:48:00', '15:08:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (506, '2011-11-28', NULL, 12, '12:43:00', '12:58:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (507, '2011-11-28', NULL, 11, '15:08:00', '15:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (508, '2011-11-28', NULL, 12, '12:58:00', '13:13:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (509, '2011-11-28', NULL, 11, '15:28:00', '15:48:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (510, '2011-11-28', NULL, 12, '13:13:00', '13:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (511, '2011-11-28', NULL, 11, '15:48:00', '16:08:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (512, '2011-11-28', NULL, 12, '13:28:00', '13:43:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (513, '2011-11-28', NULL, 11, '16:08:00', '16:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (514, '2011-11-28', NULL, 12, '13:43:00', '13:58:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (515, '2011-11-28', NULL, 11, '16:28:00', '16:48:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (516, '2011-11-28', NULL, 12, '13:58:00', '14:13:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (517, '2011-11-28', NULL, 11, '17:00:00', '17:00:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (518, '2011-11-29', NULL, 11, '08:00:00', '08:08:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (519, '2011-11-28', NULL, 12, '14:13:00', '14:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (520, '2011-11-29', NULL, 11, '08:08:00', '08:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (521, '2011-11-28', NULL, 12, '14:28:00', '14:43:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (522, '2011-11-29', NULL, 11, '08:28:00', '08:48:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (523, '2011-11-28', NULL, 12, '14:43:00', '14:58:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (524, '2011-11-29', NULL, 11, '08:48:00', '09:08:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (525, '2011-11-28', NULL, 12, '14:58:00', '15:13:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (526, '2011-11-29', NULL, 11, '09:08:00', '09:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (527, '2011-11-28', NULL, 12, '15:13:00', '15:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (528, '2011-11-29', NULL, 11, '09:28:00', '09:48:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (529, '2011-11-28', NULL, 12, '15:28:00', '15:43:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (530, '2011-11-29', NULL, 11, '09:48:00', '10:08:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (531, '2011-11-28', NULL, 12, '15:43:00', '15:58:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (532, '2011-11-29', NULL, 11, '10:08:00', '10:28:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (533, '2011-11-28', NULL, 12, '15:58:00', '16:13:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (534, '2011-11-29', NULL, 11, '10:28:00', '10:48:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (535, '2011-11-28', NULL, 12, '16:28:00', '17:00:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (536, '2011-11-29', NULL, 12, '08:00:00', '08:17:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (537, '2011-11-29', NULL, 11, '10:48:00', '11:37:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (538, '2011-11-29', NULL, 12, '08:17:00', '09:06:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (539, '2011-11-29', NULL, 11, '11:37:00', '12:26:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (540, '2011-11-22', NULL, 10, '13:11:00', '14:00:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (541, '2011-11-29', NULL, 12, '10:44:00', '11:33:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (542, '2011-11-22', NULL, 10, '14:00:00', '14:49:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (543, '2011-11-29', NULL, 12, '09:55:00', '10:44:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (544, '2011-11-22', NULL, 10, '14:49:00', '15:38:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (545, '2011-11-29', NULL, 12, '09:06:00', '09:55:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (546, '2011-11-29', NULL, 12, '11:33:00', '11:48:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (547, '2011-11-29', NULL, 11, '12:26:00', '12:41:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (548, '2011-11-29', NULL, 12, '11:48:00', '12:03:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (549, '2011-11-29', NULL, 11, '12:41:00', '12:56:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (550, '2011-11-29', NULL, 12, '12:03:00', '12:18:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (551, '2011-11-29', NULL, 11, '12:56:00', '13:11:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (552, '2011-11-29', NULL, 12, '12:18:00', '12:33:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (553, '2011-11-29', NULL, 11, '13:11:00', '13:26:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (554, '2011-11-29', NULL, 12, '12:33:00', '12:48:00');

INSERT INTO disponibilidadhoraria (id, fecha, tiempodisponible, idempleado, horainicio, horafin)
VALUES (555, '2011-11-29', NULL, 11, '13:26:00', '13:41:00');

--
-- Data for table public.estadoplanificacionproduccion (OID = 148859) (LIMIT 0,3)
--
INSERT INTO estadoplanificacionproduccion (id, nombre, descripcion)
VALUES (1, 'REC-ASIG', 'Recusos Asignados');

INSERT INTO estadoplanificacionproduccion (id, nombre, descripcion)
VALUES (2, 'MP-ASIG', 'Materia Prima Asignada');

INSERT INTO estadoplanificacionproduccion (id, nombre, descripcion)
VALUES (3, 'GENERADA', 'Estado inicial de la planificación');

--
-- Data for table public.maquina (OID = 148868) (LIMIT 0,16)
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
-- Data for table public.piezareal (OID = 148874) (LIMIT 0,90)
--
INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (185, 22, 1, 1, 213);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (186, 22, 1, 2, 214);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (187, 22, 1, 3, 215);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (188, 22, 1, 4, 216);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (189, 22, 1, 5, 217);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (190, 22, 1, 6, 218);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (191, 22, 1, 7, 219);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (192, 22, 1, 8, 220);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (193, 22, 1, 9, 221);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (194, 22, 1, 10, 222);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (195, 22, 1, 11, 223);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (196, 22, 1, 12, 224);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (197, 22, 1, 13, 225);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (198, 22, 1, 14, 226);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (199, 22, 1, 15, 227);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (200, 22, 1, 16, 228);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (201, 22, 1, 17, 229);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (202, 22, 1, 18, 230);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (203, 22, 1, 19, 231);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (204, 22, 1, 20, 232);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (205, 22, 1, 21, 233);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (206, 22, 1, 22, 234);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (207, 22, 1, 23, 235);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (208, 22, 1, 24, 236);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (209, 22, 1, 25, 237);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (210, 22, 1, 26, 238);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (211, 22, 1, 27, 239);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (212, 22, 1, 28, 240);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (213, 22, 1, 29, 241);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (214, 22, 1, 30, 242);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (215, 22, 1, 31, 243);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (216, 22, 1, 32, 244);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (217, 22, 1, 33, 245);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (218, 22, 1, 34, 246);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (219, 22, 1, 35, 247);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (220, 22, 1, 36, 248);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (221, 22, 1, 37, 249);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (222, 22, 1, 38, 250);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (223, 22, 1, 39, 251);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (224, 22, 1, 40, 252);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (225, 23, 1, 41, 253);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (226, 23, 1, 42, 254);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (227, 23, 1, 43, 255);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (228, 23, 1, 44, 256);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (229, 23, 1, 45, 257);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (230, 23, 1, 46, 258);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (231, 23, 1, 47, 259);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (232, 23, 1, 48, 260);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (233, 23, 1, 49, 261);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (234, 23, 1, 50, 262);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (235, 23, 1, 51, 263);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (236, 23, 1, 52, 264);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (237, 23, 1, 53, 265);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (238, 23, 1, 54, 266);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (239, 23, 1, 55, 267);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (240, 23, 1, 56, 268);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (241, 23, 1, 57, 269);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (242, 23, 1, 58, 270);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (243, 23, 1, 59, 271);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (244, 23, 1, 60, 272);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (245, 23, 1, 61, 273);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (246, 23, 1, 62, 274);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (247, 23, 1, 63, 275);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (248, 23, 1, 64, 276);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (249, 23, 1, 65, 277);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (250, 23, 1, 66, 278);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (251, 23, 1, 67, 279);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (252, 23, 1, 68, 280);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (253, 23, 1, 69, 281);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (254, 23, 1, 70, 282);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (255, 23, 1, 71, 283);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (256, 23, 1, 72, 284);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (257, 23, 1, 73, 285);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (258, 23, 1, 74, 286);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (259, 23, 1, 75, 287);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (260, 23, 1, 76, 288);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (261, 23, 1, 77, 289);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (262, 23, 1, 78, 290);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (263, 23, 1, 79, 291);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (264, 23, 1, 80, 292);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (265, 12, 1, 81, 293);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (266, 12, 1, 82, 294);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (267, 12, 1, 83, 295);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (268, 12, 1, 84, 296);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (269, 12, 1, 85, 297);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (270, 12, 1, 86, 298);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (271, 12, 1, 87, 299);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (272, 12, 1, 88, 300);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (273, 12, 1, 89, 301);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (274, 12, 1, 90, 302);

--
-- Data for table public.pieza (OID = 148880) (LIMIT 0,16)
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
-- Data for table public.detallempasignada (OID = 148886) (LIMIT 0,3)
--
INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (87, 13, 40, 89);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (88, 24, 40, 89);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (89, 14, 10, 90);

--
-- Data for table public.mpasignadaxpiezareal (OID = 148892) (LIMIT 0,90)
--
INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (185, 87, 170);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (186, 87, 171);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (187, 87, 172);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (188, 87, 173);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (189, 87, 174);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (190, 87, 175);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (191, 87, 176);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (192, 87, 177);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (193, 87, 178);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (194, 87, 179);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (195, 87, 180);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (196, 87, 181);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (197, 87, 182);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (198, 87, 183);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (199, 87, 184);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (200, 87, 185);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (201, 87, 186);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (202, 87, 187);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (203, 87, 188);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (204, 87, 189);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (205, 87, 190);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (206, 87, 191);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (207, 87, 192);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (208, 87, 193);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (209, 87, 194);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (210, 87, 195);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (211, 87, 196);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (212, 87, 197);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (213, 87, 198);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (214, 87, 199);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (215, 87, 200);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (216, 87, 201);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (217, 87, 202);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (218, 87, 203);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (219, 87, 204);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (220, 87, 205);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (221, 87, 206);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (222, 87, 207);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (223, 87, 208);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (224, 87, 209);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (225, 88, 210);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (226, 88, 211);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (227, 88, 212);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (228, 88, 213);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (229, 88, 214);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (230, 88, 215);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (231, 88, 216);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (232, 88, 217);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (233, 88, 218);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (234, 88, 219);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (235, 88, 220);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (236, 88, 221);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (237, 88, 222);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (238, 88, 223);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (239, 88, 224);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (240, 88, 225);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (241, 88, 226);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (242, 88, 227);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (243, 88, 228);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (244, 88, 229);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (245, 88, 230);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (246, 88, 231);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (247, 88, 232);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (248, 88, 233);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (249, 88, 234);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (250, 88, 235);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (251, 88, 236);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (252, 88, 237);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (253, 88, 238);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (254, 88, 239);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (255, 88, 240);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (256, 88, 241);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (257, 88, 242);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (258, 88, 243);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (259, 88, 244);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (260, 88, 245);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (261, 88, 246);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (262, 88, 247);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (263, 88, 248);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (264, 88, 249);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (265, 89, 250);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (266, 89, 251);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (267, 89, 252);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (268, 89, 253);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (269, 89, 254);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (270, 89, 255);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (271, 89, 256);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (272, 89, 257);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (273, 89, 258);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (274, 89, 259);

--
-- Data for table public.estadoreclamo (OID = 148943) (LIMIT 0,5)
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
-- Data for table public.estadomantpreventivo (OID = 148957) (LIMIT 0,6)
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
-- Definition for index pedido_nropedido_key (OID = 149600) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_nropedido_key UNIQUE (nropedido);
--
-- Definition for index proveedorxmateriaprima_idx (OID = 149602) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_idx PRIMARY KEY (idmateriaprima, idproveedor);
--
-- Definition for index maquinaxejecucionetapaproduccion_idx (OID = 149604) : 
--
ALTER TABLE ONLY maquinaxejecucionetapaproduccion
    ADD CONSTRAINT maquinaxejecucionetapaproduccion_idx PRIMARY KEY (idejecucionetapaproduccion, idetapaproduccion, idmaquina);
--
-- Definition for index maquinaxprocesocalidad_idx (OID = 149606) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT maquinaxprocesocalidad_idx PRIMARY KEY (idprocesocalidad, idmaquina);
--
-- Definition for index piezaxetapadeproduccion_idx (OID = 149608) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT piezaxetapadeproduccion_idx PRIMARY KEY (idpieza, idetapaproduccion);
--
-- Definition for index empleadoxturno_idx (OID = 149610) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_idx PRIMARY KEY (idempleado, idturno);
--
-- Definition for index usuarioxrol_pkey (OID = 149612) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_pkey PRIMARY KEY (idrol, idusuario);
--
-- Definition for index rolxprivilegio_pkey (OID = 149614) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_pkey PRIMARY KEY (idrol, idprivilegio);
--
-- Definition for index prueba_pkey (OID = 149616) : 
--
ALTER TABLE ONLY prueba
    ADD CONSTRAINT prueba_pkey PRIMARY KEY (id);
--
-- Definition for index usuario_pkey (OID = 149618) : 
--
ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (idusuario);
--
-- Definition for index factura_fk2 (OID = 149620) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk2 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index comprobantepago_fk1 (OID = 149625) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk1 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index cliente_fk2 (OID = 149630) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk2 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index empleado_fk3 (OID = 149635) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk3 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index sesion_fk (OID = 149640) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT sesion_fk FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index usuarioxrol_fk1 (OID = 149645) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_fk1 FOREIGN KEY (idusuario) REFERENCES usuario(idusuario);
--
-- Definition for index tipomaterial_pkey (OID = 149650) : 
--
ALTER TABLE ONLY tipomaterial
    ADD CONSTRAINT tipomaterial_pkey PRIMARY KEY (idtipomaterial);
--
-- Definition for index accioncalidad_pkey (OID = 149652) : 
--
ALTER TABLE ONLY accioncalidad
    ADD CONSTRAINT accioncalidad_pkey PRIMARY KEY (idaccioncalidad);
--
-- Definition for index procesocalidad_fk (OID = 149654) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT procesocalidad_fk FOREIGN KEY (accioncalidad) REFERENCES accioncalidad(idaccioncalidad);
--
-- Definition for index barrio_pkey (OID = 149659) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT barrio_pkey PRIMARY KEY (idbarrio);
--
-- Definition for index domicilio_fk (OID = 149661) : 
--
ALTER TABLE ONLY domicilio
    ADD CONSTRAINT domicilio_fk FOREIGN KEY (barrio) REFERENCES barrio(idbarrio);
--
-- Definition for index cargo_pkey (OID = 149666) : 
--
ALTER TABLE ONLY cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (idcargo);
--
-- Definition for index empleado_fk4 (OID = 149668) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk4 FOREIGN KEY (cargo) REFERENCES cargo(idcargo);
--
-- Definition for index categoria_pkey (OID = 149673) : 
--
ALTER TABLE ONLY categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (idcategoria);
--
-- Definition for index empleado_fk2 (OID = 149675) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk2 FOREIGN KEY (categoria) REFERENCES categoria(idcategoria);
--
-- Definition for index cliente_pkey (OID = 149680) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (idcliente);
--
-- Definition for index pedido_fk4 (OID = 149682) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk4 FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index reclamocliente_fk1 (OID = 149687) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_fk1 FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index codigodebarra_pkey (OID = 149692) : 
--
ALTER TABLE ONLY codigodebarra
    ADD CONSTRAINT codigodebarra_pkey PRIMARY KEY (idcodigo);
--
-- Definition for index materiaprima_fk1 (OID = 149694) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk1 FOREIGN KEY (codbarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index productoreal_fk2 (OID = 149699) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk2 FOREIGN KEY (codigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index compra_pkey (OID = 149704) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_pkey PRIMARY KEY (idcompra);
--
-- Definition for index detallecompra_fk (OID = 149706) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk FOREIGN KEY (idcompra) REFERENCES compra(idcompra);
--
-- Definition for index reclamoproveedor_fk1 (OID = 149711) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_fk1 FOREIGN KEY (compra) REFERENCES compra(idcompra);
--
-- Definition for index comprobantepago_pkey (OID = 149716) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_pkey PRIMARY KEY (idcomprobantepago);
--
-- Definition for index condicioniva_pkey (OID = 149718) : 
--
ALTER TABLE ONLY condicioniva
    ADD CONSTRAINT condicioniva_pkey PRIMARY KEY (idcondicioniva);
--
-- Definition for index cliente_fk5 (OID = 149720) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk5 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index empresametalurgica_fk2 (OID = 149725) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk2 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index proveedormantenimientomaquina_fk2 (OID = 149730) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk2 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index proveedor_fk2 (OID = 149735) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk2 FOREIGN KEY (condicion) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index detallecompra_idx (OID = 149740) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_idx PRIMARY KEY (iddetalle, idcompra);
--
-- Definition for index detallereclamoproveedor_fk1 (OID = 149742) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_fk1 FOREIGN KEY (iddetallecompra, idcompra) REFERENCES detallecompra(iddetalle, idcompra);
--
-- Definition for index detallecompra_iddetalle_key (OID = 149747) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallefactura_idx (OID = 149749) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_idx PRIMARY KEY (iddetalle, idfactura);
--
-- Definition for index detallefactura_iddetalle_key (OID = 149751) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleplanprocedimientos_idx (OID = 149753) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_idx PRIMARY KEY (iddetalle, idplanpprocedimientos);
--
-- Definition for index detalleplanprocedimientos_iddetalle_key (OID = 149755) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleplanprocesoscalidad_idx (OID = 149757) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_idx PRIMARY KEY (iddetalle, idplanprocesoscalidad);
--
-- Definition for index detalleplanprocesoscalidad_iddetalle_key (OID = 149759) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamocliente_idx (OID = 149761) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamocliente_iddetalle_key (OID = 149763) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamoempresametalurgica_idx (OID = 149765) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamoempresametalurgica_iddetalle_key (OID = 149767) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamoproveedor_idx (OID = 149769) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamoproveedor_iddetalle_key (OID = 149771) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleremito_idx (OID = 149773) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_idx PRIMARY KEY (iddetalle, idremito);
--
-- Definition for index detalleremito_iddetalle_key (OID = 149775) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallerequerimientosmateriaprima_idx (OID = 149777) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_idx PRIMARY KEY (iddetalle, idplanrequerimientosmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_iddetalle_key (OID = 149779) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index domicilio_pkey (OID = 149781) : 
--
ALTER TABLE ONLY domicilio
    ADD CONSTRAINT domicilio_pkey PRIMARY KEY (iddomicilio);
--
-- Definition for index cliente_fk4 (OID = 149783) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk4 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index empresametalurgica_fk1 (OID = 149788) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index responsable_fk (OID = 149793) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_fk FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index proveedormantenimientomaquina_fk1 (OID = 149798) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index empleado_fk (OID = 149803) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index proveedor_fk1 (OID = 149808) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index ejecucionetapaproduccion_nroejecucion_key1 (OID = 149813) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_nroejecucion_key1 UNIQUE (nroejecucion);
--
-- Definition for index empleado_pkey (OID = 149815) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (idempleado);
--
-- Definition for index mantenimientocorrectivo_fk (OID = 149817) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_fk FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index ejecucionetapaproduccion_fk1 (OID = 149822) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk1 FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index detallaplanificacionproduccion_fk3 (OID = 149827) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk3 FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index empleadoxturno_fk (OID = 149832) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_fk FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index empresametalurgica_pkey (OID = 149837) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_pkey PRIMARY KEY (idempresametalurgica);
--
-- Definition for index trabajotercerizado_fk1 (OID = 149839) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk1 FOREIGN KEY (empresa) REFERENCES empresametalurgica(idempresametalurgica);
--
-- Definition for index estadocliente_pkey (OID = 149844) : 
--
ALTER TABLE ONLY estadocliente
    ADD CONSTRAINT estadocliente_pkey PRIMARY KEY (idestado);
--
-- Definition for index cliente_fk1 (OID = 149846) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk1 FOREIGN KEY (estado) REFERENCES estadocliente(idestado);
--
-- Definition for index estadocompra_pkey (OID = 149851) : 
--
ALTER TABLE ONLY estadocompra
    ADD CONSTRAINT estadocompra_pkey PRIMARY KEY (idestado);
--
-- Definition for index compra_fk (OID = 149853) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_fk FOREIGN KEY (estado) REFERENCES estadocompra(idestado);
--
-- Definition for index estadodetallecompra_pkey (OID = 149858) : 
--
ALTER TABLE ONLY estadodetallecompra
    ADD CONSTRAINT estadodetallecompra_pkey PRIMARY KEY (idestado);
--
-- Definition for index detallecompra_fk2 (OID = 149860) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk2 FOREIGN KEY (estado) REFERENCES estadodetallecompra(idestado);
--
-- Definition for index estadodetalletrabajotercerizado_pkey (OID = 149865) : 
--
ALTER TABLE ONLY estadodetalletrabajotercerizado
    ADD CONSTRAINT estadodetalletrabajotercerizado_pkey PRIMARY KEY (idestado);
--
-- Definition for index detalletrabajotercerizado_fk3 (OID = 149867) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk3 FOREIGN KEY (estado) REFERENCES estadodetalletrabajotercerizado(idestado);
--
-- Definition for index estadoejecetapaprod_pkey (OID = 149872) : 
--
ALTER TABLE ONLY estadoejecetapaprod
    ADD CONSTRAINT estadoejecetapaprod_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionetapaproduccion_fk2 (OID = 149874) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk2 FOREIGN KEY (estado) REFERENCES estadoejecetapaprod(idestado);
--
-- Definition for index estadoejecplancalidad_pkey (OID = 149879) : 
--
ALTER TABLE ONLY estadoejecplancalidad
    ADD CONSTRAINT estadoejecplancalidad_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionplanificacioncalidad_fk1 (OID = 149881) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_fk1 FOREIGN KEY (estado) REFERENCES estadoejecplancalidad(idestado);
--
-- Definition for index estadoejecplanifpedido_pkey (OID = 149886) : 
--
ALTER TABLE ONLY estadoejecplanifpedido
    ADD CONSTRAINT estadoejecplanifpedido_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionplanificacionproduccion_fk1 (OID = 149888) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_fk1 FOREIGN KEY (estado) REFERENCES estadoejecplanifpedido(idestado);
--
-- Definition for index estadoejecucionprocesocalidad_pkey (OID = 149893) : 
--
ALTER TABLE ONLY estadoejecucionprocesocalidad
    ADD CONSTRAINT estadoejecucionprocesocalidad_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionprocesocalidad_fk1 (OID = 149895) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_fk1 FOREIGN KEY (estado) REFERENCES estadoejecucionprocesocalidad(idestado);
--
-- Definition for index estadofactura_pkey (OID = 149900) : 
--
ALTER TABLE ONLY estadofactura
    ADD CONSTRAINT estadofactura_pkey PRIMARY KEY (idestado);
--
-- Definition for index factura_fk3 (OID = 149902) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk3 FOREIGN KEY (estado) REFERENCES estadofactura(idestado);
--
-- Definition for index estadomaquina_pkey (OID = 149907) : 
--
ALTER TABLE ONLY estadomaquina
    ADD CONSTRAINT estadomaquina_pkey PRIMARY KEY (idestado);
--
-- Definition for index estadopedido_pkey (OID = 149909) : 
--
ALTER TABLE ONLY estadopedido
    ADD CONSTRAINT estadopedido_pkey PRIMARY KEY (idestado);
--
-- Definition for index pedido_fk (OID = 149911) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk FOREIGN KEY (estado) REFERENCES estadopedido(idestado);
--
-- Definition for index estadopiezareal_pkey (OID = 149916) : 
--
ALTER TABLE ONLY estadopiezareal
    ADD CONSTRAINT estadopiezareal_pkey PRIMARY KEY (idestado);
--
-- Definition for index estadoproductoreal_pkey (OID = 149918) : 
--
ALTER TABLE ONLY estadoproductoreal
    ADD CONSTRAINT estadoproductoreal_pkey PRIMARY KEY (idestado);
--
-- Definition for index productoreal_fk3 (OID = 149920) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk3 FOREIGN KEY (estado) REFERENCES estadoproductoreal(idestado);
--
-- Definition for index estadoremito_pkey (OID = 149925) : 
--
ALTER TABLE ONLY estadoremito
    ADD CONSTRAINT estadoremito_pkey PRIMARY KEY (idestado);
--
-- Definition for index remito_fk1 (OID = 149927) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_fk1 FOREIGN KEY (estado) REFERENCES estadoremito(idestado);
--
-- Definition for index estadotrabajotercerizado_pkey (OID = 149932) : 
--
ALTER TABLE ONLY estadotrabajotercerizado
    ADD CONSTRAINT estadotrabajotercerizado_pkey PRIMARY KEY (idestado);
--
-- Definition for index trabajotercerizado_fk2 (OID = 149934) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk2 FOREIGN KEY (estado) REFERENCES estadotrabajotercerizado(idestado);
--
-- Definition for index etapadeproduccion_pkey (OID = 149939) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_pkey PRIMARY KEY (idetapaproduccion);
--
-- Definition for index ejecucionetapaproduccion_fk (OID = 149941) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detalletrabajotercerizado_fk2 (OID = 149946) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk2 FOREIGN KEY (proceso) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index piezaxetapadeproduccion_fk1 (OID = 149951) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT piezaxetapadeproduccion_fk1 FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detalleplanprocedimientos_fk2 (OID = 149956) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_fk2 FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index factura_pkey (OID = 149961) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_pkey PRIMARY KEY (idfactura);
--
-- Definition for index pedido_fk1 (OID = 149963) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk1 FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index comprobantepago_fk2 (OID = 149968) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk2 FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index detallefactura_fk (OID = 149973) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_fk FOREIGN KEY (idfactura) REFERENCES factura(idfactura);
--
-- Definition for index formadepago_pkey (OID = 149978) : 
--
ALTER TABLE ONLY formadepago
    ADD CONSTRAINT formadepago_pkey PRIMARY KEY (idformapago);
--
-- Definition for index factura_fk1 (OID = 149980) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk1 FOREIGN KEY (formapago) REFERENCES formadepago(idformapago);
--
-- Definition for index comprobantepago_fk (OID = 149985) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk FOREIGN KEY (formadepago) REFERENCES formadepago(idformapago);
--
-- Definition for index localidad_pkey (OID = 149990) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT localidad_pkey PRIMARY KEY (idlocalidad);
--
-- Definition for index barrio_fk (OID = 149992) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT barrio_fk FOREIGN KEY (localidad) REFERENCES localidad(idlocalidad);
--
-- Definition for index mantenimientocorrectivo_pkey (OID = 149997) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_pkey PRIMARY KEY (idmantenimientocorrectivo);
--
-- Definition for index detallemantenimientocorrectivo_fk1 (OID = 149999) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_fk1 FOREIGN KEY (idmantenimientocorrectivo) REFERENCES mantenimientocorrectivo(idmantenimientocorrectivo);
--
-- Definition for index mantenimientopreventivo_pkey (OID = 150004) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_pkey PRIMARY KEY (idmantenimientopreventivo);
--
-- Definition for index marca_pkey (OID = 150006) : 
--
ALTER TABLE ONLY marca
    ADD CONSTRAINT marca_pkey PRIMARY KEY (idmarca);
--
-- Definition for index materiaprima_pkey (OID = 150008) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_pkey PRIMARY KEY (idmateriaprima);
--
-- Definition for index matriz_fk (OID = 150010) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT matriz_fk FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallecompra_fk1 (OID = 150015) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk1 FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index proveedorxmateriaprima_fk1 (OID = 150020) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_fk1 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_fk2 (OID = 150025) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_fk2 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index matriz_pkey (OID = 150030) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT matriz_pkey PRIMARY KEY (idmatriz);
--
-- Definition for index pedidomatriz_fk (OID = 150032) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT pedidomatriz_fk FOREIGN KEY (idmatriz) REFERENCES matriz(idmatriz);
--
-- Definition for index pedido_pkey (OID = 150037) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (idpedido);
--
-- Definition for index plano_fk (OID = 150039) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT plano_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index remito_fk (OID = 150044) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index trabajotercerizado_fk (OID = 150049) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index planificacionproduccion_fk (OID = 150054) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index planificacioncalidad_fk (OID = 150059) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT planificacioncalidad_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index detallepedido_fk (OID = 150064) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_fk FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index productoreal_fk1 (OID = 150069) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk1 FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index detallefactura_fk1 (OID = 150074) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_fk1 FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index pedidomatriz_idx (OID = 150079) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT pedidomatriz_idx PRIMARY KEY (idpedidomatriz);
--
-- Definition for index planificacioncalidad_pkey (OID = 150081) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT planificacioncalidad_pkey PRIMARY KEY (idplanificacion);
--
-- Definition for index ejecucionplanificacioncalidad_fk (OID = 150083) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_fk FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index detalleplanificacioncalidad_fk (OID = 150088) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index planificacionproduccion_pkey (OID = 150093) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_pkey PRIMARY KEY (idplanificacionproduccion);
--
-- Definition for index ejecucionplanificacionproduccion_fk (OID = 150095) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_fk FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index detallaplanificacionproduccion_fk (OID = 150100) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index plano_pkey (OID = 150105) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT plano_pkey PRIMARY KEY (idplano);
--
-- Definition for index planprocedimientos_pkey (OID = 150107) : 
--
ALTER TABLE ONLY planprocedimientos
    ADD CONSTRAINT planprocedimientos_pkey PRIMARY KEY (idplanprocedimientos);
--
-- Definition for index detalleplanprocedimientos_fk (OID = 150109) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_fk FOREIGN KEY (idplanpprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index pedido_fk5 (OID = 150114) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk5 FOREIGN KEY (planprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index planprocesoscalidad_pkey (OID = 150119) : 
--
ALTER TABLE ONLY planprocesoscalidad
    ADD CONSTRAINT planprocesoscalidad_pkey PRIMARY KEY (idplanprocesoscalidad);
--
-- Definition for index detalleplanprocesoscalidad_fk (OID = 150121) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_fk FOREIGN KEY (idplanprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index pedido_fk7 (OID = 150126) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk7 FOREIGN KEY (planprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index planrequerimientosmateriaprima_pkey (OID = 150131) : 
--
ALTER TABLE ONLY planrequerimientosmateriaprima
    ADD CONSTRAINT planrequerimientosmateriaprima_pkey PRIMARY KEY (idplanrequerimientosmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_fk (OID = 150133) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_fk FOREIGN KEY (idplanrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index pedido_fk6 (OID = 150138) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk6 FOREIGN KEY (planrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index presupuesto_pkey (OID = 150143) : 
--
ALTER TABLE ONLY presupuesto
    ADD CONSTRAINT presupuesto_pkey PRIMARY KEY (idpresupuesto);
--
-- Definition for index pedido_fk2 (OID = 150145) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk2 FOREIGN KEY (presupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index detallepresupuesto_fk (OID = 150150) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_fk FOREIGN KEY (idpresupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index prioridad_pkey (OID = 150155) : 
--
ALTER TABLE ONLY prioridad
    ADD CONSTRAINT prioridad_pkey PRIMARY KEY (idprioridad);
--
-- Definition for index cliente_fk (OID = 150157) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index privilegio_pkey (OID = 150162) : 
--
ALTER TABLE ONLY privilegio
    ADD CONSTRAINT privilegio_pkey PRIMARY KEY (idprivilegio);
--
-- Definition for index rolxprivilegio_fk (OID = 150164) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_fk FOREIGN KEY (idprivilegio) REFERENCES privilegio(idprivilegio);
--
-- Definition for index procesocalidad_pkey (OID = 150169) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT procesocalidad_pkey PRIMARY KEY (idprocesocalidad);
--
-- Definition for index detalleplanificacioncalidad_fk2 (OID = 150171) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk2 FOREIGN KEY (procesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index maquinaxprocesocalidad_fk (OID = 150176) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT maquinaxprocesocalidad_fk FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detalleplanprocesoscalidad_fk2 (OID = 150181) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_fk2 FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index producto_pkey (OID = 150186) : 
--
ALTER TABLE ONLY producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (idproducto);
--
-- Definition for index detalleproducto_fk (OID = 150188) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_fk FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index detallepedido_fk1 (OID = 150193) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detalleremito_fk1 (OID = 150198) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detallereclamocliente_fk1 (OID = 150203) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detallepresupuesto_fk2 (OID = 150208) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_fk2 FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index productoreal_idx (OID = 150213) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_idx PRIMARY KEY (idproductoreal);
--
-- Definition for index detalleproductoreal_fk (OID = 150215) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_fk FOREIGN KEY (idproductoreal) REFERENCES productoreal(idproductoreal);
--
-- Definition for index proveedor_pkey (OID = 150220) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_pkey PRIMARY KEY (idproveedor);
--
-- Definition for index compra_fk1 (OID = 150222) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_fk1 FOREIGN KEY (proveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index proveedorxmateriaprima_fk (OID = 150227) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_fk FOREIGN KEY (idproveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index proveedormantenimientomaquina_pkey (OID = 150232) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_pkey PRIMARY KEY (idproveedormantenimiento);
--
-- Definition for index mantenimientopreventivo_fk (OID = 150234) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_fk FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index mantenimientocorrectivo_fk1 (OID = 150239) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_fk1 FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index provincia_pkey (OID = 150244) : 
--
ALTER TABLE ONLY provincia
    ADD CONSTRAINT provincia_pkey PRIMARY KEY (idprovincia);
--
-- Definition for index localidad_fk (OID = 150246) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT localidad_fk FOREIGN KEY (provincia) REFERENCES provincia(idprovincia);
--
-- Definition for index reclamocliente_idx (OID = 150251) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_idx PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamocliente_fk (OID = 150253) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_fk FOREIGN KEY (idreclamo) REFERENCES reclamocliente(idreclamo);
--
-- Definition for index reclamoempresametalurgica_pkey (OID = 150258) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_pkey PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamoempresametalurgica_fk (OID = 150260) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_fk FOREIGN KEY (idreclamo) REFERENCES reclamoempresametalurgica(idreclamo);
--
-- Definition for index reclamoproveedor_idx (OID = 150265) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_idx PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamoproveedor_fk (OID = 150267) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_fk FOREIGN KEY (idreclamo) REFERENCES reclamoproveedor(idreclamo);
--
-- Definition for index remito_pkey (OID = 150272) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_pkey PRIMARY KEY (idremito);
--
-- Definition for index detalleremito_fk (OID = 150274) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_fk FOREIGN KEY (idremito) REFERENCES remito(idremito);
--
-- Definition for index responsable_pkey (OID = 150279) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_pkey PRIMARY KEY (idresponsable);
--
-- Definition for index cliente_fk3 (OID = 150281) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk3 FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index empresametalurgica_fk (OID = 150286) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index proveedormantenimientomaquina_fk (OID = 150291) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index proveedor_fk (OID = 150296) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index rol_pkey (OID = 150301) : 
--
ALTER TABLE ONLY rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (idrol);
--
-- Definition for index rolxprivilegio_fk1 (OID = 150303) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_fk1 FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index usuarioxrol_fk (OID = 150308) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_fk FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index rotura_pkey (OID = 150313) : 
--
ALTER TABLE ONLY rotura
    ADD CONSTRAINT rotura_pkey PRIMARY KEY (idrotura);
--
-- Definition for index detallemantenimientocorrectivo_fk (OID = 150315) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_fk FOREIGN KEY (rotura) REFERENCES rotura(idrotura);
--
-- Definition for index servicio_pkey (OID = 150320) : 
--
ALTER TABLE ONLY servicio
    ADD CONSTRAINT servicio_pkey PRIMARY KEY (idservicio);
--
-- Definition for index sesion_pkey (OID = 150322) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT sesion_pkey PRIMARY KEY (idsesion);
--
-- Definition for index tipodocumento_pkey (OID = 150324) : 
--
ALTER TABLE ONLY tipodocumento
    ADD CONSTRAINT tipodocumento_pkey PRIMARY KEY (idtipodocumento);
--
-- Definition for index responsable_fk1 (OID = 150326) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_fk1 FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index empleado_fk1 (OID = 150331) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk1 FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index tipoiva_pkey (OID = 150336) : 
--
ALTER TABLE ONLY tipoiva
    ADD CONSTRAINT tipoiva_pkey PRIMARY KEY (idtipoiva);
--
-- Definition for index factura_fk (OID = 150338) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk FOREIGN KEY (tipoiva) REFERENCES tipoiva(idtipoiva);
--
-- Definition for index tipomaquina_pkey (OID = 150343) : 
--
ALTER TABLE ONLY tipomaquina
    ADD CONSTRAINT tipomaquina_pkey PRIMARY KEY (idtipomaquina);
--
-- Definition for index tiporeclamo_pkey (OID = 150345) : 
--
ALTER TABLE ONLY tiporeclamo
    ADD CONSTRAINT tiporeclamo_pkey PRIMARY KEY (idtiporeclamo);
--
-- Definition for index reclamoempresametalurgica_fk (OID = 150347) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index reclamoproveedor_fk (OID = 150352) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index reclamocliente_fk (OID = 150357) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index trabajotercerizado_pkey (OID = 150362) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_pkey PRIMARY KEY (idtrabajo);
--
-- Definition for index detalletrabajotercerizado_fk (OID = 150364) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk FOREIGN KEY (idtrabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index reclamoempresametalurgica_fk1 (OID = 150369) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_fk1 FOREIGN KEY (trabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index turno_pkey (OID = 150374) : 
--
ALTER TABLE ONLY turno
    ADD CONSTRAINT turno_pkey PRIMARY KEY (idturno);
--
-- Definition for index empleadoxturno_fk1 (OID = 150376) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_fk1 FOREIGN KEY (idturno) REFERENCES turno(idturno);
--
-- Definition for index materiaprima_fk (OID = 150381) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk FOREIGN KEY (tipomaterial) REFERENCES tipomaterial(idtipomaterial);
--
-- Definition for index detalleproducto_iddetalle_key (OID = 150386) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_iddetalle_key PRIMARY KEY (iddetalle);
ALTER TABLE detalleproducto CLUSTER ON detalleproducto_iddetalle_key;
--
-- Definition for index pedido_fk8 (OID = 150388) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk8 FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index unidadmedida_pkey (OID = 150393) : 
--
ALTER TABLE ONLY unidadmedida
    ADD CONSTRAINT unidadmedida_pkey PRIMARY KEY (idunidadmedida);
--
-- Definition for index etapadeproduccion_fk1 (OID = 150395) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_fk1 FOREIGN KEY (unidaddemedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index materiaprima_fk2 (OID = 150400) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk2 FOREIGN KEY (unidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index detalleproductopresupuesto_pkey (OID = 150405) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT detalleproductopresupuesto_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detallepiezapresupuesto_pkey (OID = 150407) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT detallepiezapresupuesto_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detallepiezapresupuesto_fk (OID = 150409) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT detallepiezapresupuesto_fk FOREIGN KEY (iddetalleproductopresupuesto) REFERENCES detalleproductopresupuesto(iddetalle);
--
-- Definition for index detallepiezapresupuesto_fk1 (OID = 150414) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT detallepiezapresupuesto_fk1 FOREIGN KEY (idetapa) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detallepresupuesto_pkey (OID = 150419) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detallepedido_pkey (OID = 150421) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detalleproductopresupuesto_fk1 (OID = 150423) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT detalleproductopresupuesto_fk1 FOREIGN KEY (iddetallepresupuesto) REFERENCES detallepresupuesto(iddetalle);
--
-- Definition for index detalleproductopresupuesto_fk2 (OID = 150428) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT detalleproductopresupuesto_fk2 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallepiezacalidadpresupuesto_pkey (OID = 150433) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT detallepiezacalidadpresupuesto_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detallepiezacalidadpresupuesto_fk (OID = 150435) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT detallepiezacalidadpresupuesto_fk FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detallepiezacalidadpresupuesto_fk1 (OID = 150440) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT detallepiezacalidadpresupuesto_fk1 FOREIGN KEY (iddetalleproductopresupuesto) REFERENCES detalleproductopresupuesto(iddetalle);
--
-- Definition for index calendario_pkey (OID = 150445) : 
--
ALTER TABLE ONLY calendario
    ADD CONSTRAINT calendario_pkey PRIMARY KEY (id);
--
-- Definition for index disponibilidadhoraria_pkey (OID = 150447) : 
--
ALTER TABLE ONLY disponibilidadhoraria
    ADD CONSTRAINT disponibilidadhoraria_pkey PRIMARY KEY (id);
--
-- Definition for index estadoplanificacionproduccion_pkey (OID = 150449) : 
--
ALTER TABLE ONLY estadoplanificacionproduccion
    ADD CONSTRAINT estadoplanificacionproduccion_pkey PRIMARY KEY (id);
--
-- Definition for index planificacionproduccion_fk1 (OID = 150451) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_fk1 FOREIGN KEY (idestado) REFERENCES estadoplanificacionproduccion(id);
--
-- Definition for index disponibilidadhoraria_fk (OID = 150456) : 
--
ALTER TABLE ONLY disponibilidadhoraria
    ADD CONSTRAINT disponibilidadhoraria_fk FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index detalleplanificacionproduccion_pkey (OID = 150461) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_pkey PRIMARY KEY (id);
--
-- Definition for index detalleplanificacionproduccion_fk (OID = 150463) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index maquina_pkey (OID = 150468) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_pkey PRIMARY KEY (idmaquina);
--
-- Definition for index maquina_fk (OID = 150470) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk FOREIGN KEY (marca) REFERENCES marca(idmarca);
--
-- Definition for index maquina_fk1 (OID = 150475) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk1 FOREIGN KEY (estado) REFERENCES estadomaquina(idestado);
--
-- Definition for index maquina_fk2 (OID = 150480) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk2 FOREIGN KEY (tipomaquina) REFERENCES tipomaquina(idtipomaquina);
--
-- Definition for index maquina_fk3 (OID = 150485) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk3 FOREIGN KEY (idunidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index piezareal_fk1 (OID = 150490) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_fk1 FOREIGN KEY (estado) REFERENCES estadopiezareal(idestado);
--
-- Definition for index piezareal_fk2 (OID = 150495) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_fk2 FOREIGN KEY (idcodigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index pieza_pkey (OID = 150500) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_pkey PRIMARY KEY (idpieza);
--
-- Definition for index pieza_fk (OID = 150502) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk FOREIGN KEY (unidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index pieza_fk1 (OID = 150507) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk1 FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index pieza_fk2 (OID = 150512) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk2 FOREIGN KEY (matriz) REFERENCES matriz(idmatriz);
--
-- Definition for index detallempasignada_pkey (OID = 150517) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT detallempasignada_pkey PRIMARY KEY (id);
--
-- Definition for index detallempasignada_fk (OID = 150519) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT detallempasignada_fk FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallempasignada_fk1 (OID = 150524) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT detallempasignada_fk1 FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index mpasignadaxpiezareal_pkey (OID = 150529) : 
--
ALTER TABLE ONLY mpasignadaxpiezareal
    ADD CONSTRAINT mpasignadaxpiezareal_pkey PRIMARY KEY (id);
--
-- Definition for index mpasignadaxpiezareal_fk1 (OID = 150531) : 
--
ALTER TABLE ONLY mpasignadaxpiezareal
    ADD CONSTRAINT mpasignadaxpiezareal_fk1 FOREIGN KEY (iddetallempasignada) REFERENCES detallempasignada(id);
--
-- Definition for index asistencia_idx (OID = 150536) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_idx PRIMARY KEY (empleado, horaingreso, fechaingreso);
--
-- Definition for index asistencia_fk (OID = 150538) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_fk FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index piezareal_idpiezareal_key (OID = 150543) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_idpiezareal_key PRIMARY KEY (idpiezareal);
--
-- Definition for index detalleplanificacionproduccion_fk1 (OID = 150545) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk1 FOREIGN KEY (idmaquina) REFERENCES maquina(idmaquina);
--
-- Definition for index detalleplanificacionproduccion_fk2 (OID = 150550) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk2 FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleproductopresupuesto_fk (OID = 150555) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT detalleproductopresupuesto_fk FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index ejecucionplanificacionproduccion_idejecucion_key (OID = 150560) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_idejecucion_key PRIMARY KEY (idejecucion);
--
-- Definition for index detalleejecucionplanificacion_iddetalle_key (OID = 150562) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_iddetalle_key PRIMARY KEY (id);
--
-- Definition for index detalleplanificacionproduccion_fk3 (OID = 150564) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk3 FOREIGN KEY (iddetalleejecucionplanificacion) REFERENCES detalleejecucionplanificacion(id);
--
-- Definition for index fk_responsable_domicilio (OID = 150569) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT fk_responsable_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_responsable_tipodocumento (OID = 150574) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT fk_responsable_tipodocumento FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index fk_procesocalidad_accioncalidad (OID = 150579) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT fk_procesocalidad_accioncalidad FOREIGN KEY (accioncalidad) REFERENCES accioncalidad(idaccioncalidad);
--
-- Definition for index fk_detallecompra_estado (OID = 150584) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT fk_detallecompra_estado FOREIGN KEY (estado) REFERENCES estadodetallecompra(idestado);
--
-- Definition for index fk_detallecompra_materiaprima (OID = 150589) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT fk_detallecompra_materiaprima FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detallecompra_idcompra (OID = 150594) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT fk_detallecompra_idcompra FOREIGN KEY (idcompra) REFERENCES compra(idcompra);
--
-- Definition for index fk_reclamoproveedor_tiporeclamo (OID = 150599) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT fk_reclamoproveedor_tiporeclamo FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index fk_reclamoproveedor_compra (OID = 150604) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT fk_reclamoproveedor_compra FOREIGN KEY (compra) REFERENCES compra(idcompra);
--
-- Definition for index fk_empresametalurgica_condicioniva (OID = 150609) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT fk_empresametalurgica_condicioniva FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index fk_empresametalurgica_domicilio (OID = 150614) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT fk_empresametalurgica_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_empresametalurgica_responsable (OID = 150619) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT fk_empresametalurgica_responsable FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index fk_detalleplanprocesoscalidad_idprocesocalidad (OID = 150624) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT fk_detalleplanprocesoscalidad_idprocesocalidad FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index fk_detalleplanprocesoscalidad_idplanprocesoscalidad (OID = 150629) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT fk_detalleplanprocesoscalidad_idplanprocesoscalidad FOREIGN KEY (idplanprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index fk_detalleremito_idremito (OID = 150634) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT fk_detalleremito_idremito FOREIGN KEY (idremito) REFERENCES remito(idremito);
--
-- Definition for index fk_detalleremito_producto (OID = 150639) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT fk_detalleremito_producto FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index fk_detalleplanificacionproduccion_idpieza (OID = 150644) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idpieza FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index fk_detalleplanificacionproduccion_idetapaproduccion (OID = 150649) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idetapaproduccion FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_detalleplanificacionproduccion_idempleado (OID = 150654) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idempleado FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index detalleplanificacionproduccion_iddetalleejecucionplanificacion (OID = 150659) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_iddetalleejecucionplanificacion FOREIGN KEY (iddetalleejecucionplanificacion) REFERENCES detalleejecucionplanificacion(id);
--
-- Definition for index fk_detalleplanificacionproduccion_idmaquina (OID = 150664) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idmaquina FOREIGN KEY (idmaquina) REFERENCES maquina(idmaquina);
--
-- Definition for index fk_detalleplanificacionproduccion_idplanificacionproduccion (OID = 150669) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idplanificacionproduccion FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index fk_ejecucionplanificacioncalidad_estado (OID = 150674) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT fk_ejecucionplanificacioncalidad_estado FOREIGN KEY (estado) REFERENCES estadoejecplancalidad(idestado);
--
-- Definition for index fk_ejecucionplanificacioncalidad_idplanificacioncalidad (OID = 150679) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT fk_ejecucionplanificacioncalidad_idplanificacioncalidad FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index fk_planificacionproduccion_pedido (OID = 150684) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT fk_planificacionproduccion_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_planificacionproduccion_idestado (OID = 150689) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT fk_planificacionproduccion_idestado FOREIGN KEY (idestado) REFERENCES estadoplanificacionproduccion(id);
--
-- Definition for index fk_pedido_estado (OID = 150694) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_estado FOREIGN KEY (estado) REFERENCES estadopedido(idestado);
--
-- Definition for index fk_pedido_planprocesoscalidad (OID = 150699) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_planprocesoscalidad FOREIGN KEY (planprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index fk_pedido_presupuesto (OID = 150704) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_presupuesto FOREIGN KEY (presupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index fk_pedido_planrequerimientosmateriaprima (OID = 150709) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_planrequerimientosmateriaprima FOREIGN KEY (planrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index fk_pedido_planprocedimientos (OID = 150714) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_planprocedimientos FOREIGN KEY (planprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index fk_pedido_prioridad (OID = 150719) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_prioridad FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index fk_pedido_cliente (OID = 150724) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index fk_pedido_factura (OID = 150729) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_factura FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index fk_empleadoxturno_idempleado (OID = 150734) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT fk_empleadoxturno_idempleado FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_empleadoxturno_idturno (OID = 150739) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT fk_empleadoxturno_idturno FOREIGN KEY (idturno) REFERENCES turno(idturno);
--
-- Definition for index fk_ejecucionetapaproduccion_empleado (OID = 150744) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT fk_ejecucionetapaproduccion_empleado FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_ejecucionetapaproduccion_estado (OID = 150749) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT fk_ejecucionetapaproduccion_estado FOREIGN KEY (estado) REFERENCES estadoejecetapaprod(idestado);
--
-- Definition for index fk_ejecucionetapaproduccion_idetapaproduccion (OID = 150754) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT fk_ejecucionetapaproduccion_idetapaproduccion FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_compra_proveedor (OID = 150759) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT fk_compra_proveedor FOREIGN KEY (proveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index fk_compra_estado (OID = 150764) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT fk_compra_estado FOREIGN KEY (estado) REFERENCES estadocompra(idestado);
--
-- Definition for index fk_maquinaxprocesocalidad_idprocesocalidad (OID = 150769) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT fk_maquinaxprocesocalidad_idprocesocalidad FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index fk_productoreal_codigobarra (OID = 150774) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT fk_productoreal_codigobarra FOREIGN KEY (codigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index fk_productoreal_idpedido (OID = 150779) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT fk_productoreal_idpedido FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_productoreal_estado (OID = 150784) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT fk_productoreal_estado FOREIGN KEY (estado) REFERENCES estadoproductoreal(idestado);
--
-- Definition for index fk_materiaprima_unidadmedida (OID = 150789) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT fk_materiaprima_unidadmedida FOREIGN KEY (unidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index fk_materiaprima_tipomaterial (OID = 150794) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT fk_materiaprima_tipomaterial FOREIGN KEY (tipomaterial) REFERENCES tipomaterial(idtipomaterial);
--
-- Definition for index fk_materiaprima_codbarra (OID = 150799) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT fk_materiaprima_codbarra FOREIGN KEY (codbarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index fk_cliente_estado (OID = 150804) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_estado FOREIGN KEY (estado) REFERENCES estadocliente(idestado);
--
-- Definition for index fk_cliente_domicilio (OID = 150809) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_cliente_usuario (OID = 150814) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_cliente_condicioniva (OID = 150819) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_condicioniva FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index fk_cliente_responsable (OID = 150824) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_responsable FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index fk_cliente_prioridad (OID = 150829) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_prioridad FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index fk_pieza_matriz (OID = 150834) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT fk_pieza_matriz FOREIGN KEY (matriz) REFERENCES matriz(idmatriz);
--
-- Definition for index fk_pieza_unidadmedida (OID = 150839) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT fk_pieza_unidadmedida FOREIGN KEY (unidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index fk_pieza_materiaprima (OID = 150844) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT fk_pieza_materiaprima FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detallepiezacalidadpresupuesto_idprocesocalidad (OID = 150849) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT fk_detallepiezacalidadpresupuesto_idprocesocalidad FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index fk_detallepiezacalidadpresupuesto_iddetalleproductopresupuesto (OID = 150854) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT fk_detallepiezacalidadpresupuesto_iddetalleproductopresupuesto FOREIGN KEY (iddetalleproductopresupuesto) REFERENCES detalleproductopresupuesto(iddetalle);
--
-- Definition for index fk_detalleproductoreal_idproductoreal (OID = 150859) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT fk_detalleproductoreal_idproductoreal FOREIGN KEY (idproductoreal) REFERENCES productoreal(idproductoreal);
--
-- Definition for index fk_factura_tipoiva (OID = 150864) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_factura_tipoiva FOREIGN KEY (tipoiva) REFERENCES tipoiva(idtipoiva);
--
-- Definition for index fk_factura_formapago (OID = 150869) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_factura_formapago FOREIGN KEY (formapago) REFERENCES formadepago(idformapago);
--
-- Definition for index fk_factura_usuario (OID = 150874) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_factura_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_factura_estado (OID = 150879) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_factura_estado FOREIGN KEY (estado) REFERENCES estadofactura(idestado);
--
-- Definition for index fk_detallerequerimientosmateriaprima_idmateriaprima (OID = 150884) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT fk_detallerequerimientosmateriaprima_idmateriaprima FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index dtallerequerimientosmateriaprimadplanrequerimientosmateriaprima (OID = 150889) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT dtallerequerimientosmateriaprimadplanrequerimientosmateriaprima FOREIGN KEY (idplanrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index fk_plano_pedido (OID = 150894) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT fk_plano_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_usuarioxrol_idrol (OID = 150899) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT fk_usuarioxrol_idrol FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index fk_usuarioxrol_idusuario (OID = 150904) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT fk_usuarioxrol_idusuario FOREIGN KEY (idusuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_trabajotercerizado_empresa (OID = 150909) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT fk_trabajotercerizado_empresa FOREIGN KEY (empresa) REFERENCES empresametalurgica(idempresametalurgica);
--
-- Definition for index fk_trabajotercerizado_estado (OID = 150914) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT fk_trabajotercerizado_estado FOREIGN KEY (estado) REFERENCES estadotrabajotercerizado(idestado);
--
-- Definition for index fk_trabajotercerizado_pedido (OID = 150919) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT fk_trabajotercerizado_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_piezaxetapadeproduccion_idetapaproduccion (OID = 150924) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT fk_piezaxetapadeproduccion_idetapaproduccion FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_proveedor_responsable (OID = 150929) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT fk_proveedor_responsable FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index fk_proveedor_domicilio (OID = 150934) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT fk_proveedor_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_proveedor_condicion (OID = 150939) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT fk_proveedor_condicion FOREIGN KEY (condicion) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index fk_reclamoempresametalurgica_trabajotercerizado (OID = 150944) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT fk_reclamoempresametalurgica_trabajotercerizado FOREIGN KEY (trabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index fk_reclamoempresametalurgica_tiporeclamo (OID = 150949) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT fk_reclamoempresametalurgica_tiporeclamo FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index fk_matriz_materiaprima (OID = 150954) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT fk_matriz_materiaprima FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detalleproducto_idproducto (OID = 150959) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT fk_detalleproducto_idproducto FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index fk_mantenimientocorrectivo_empleado (OID = 150964) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT fk_mantenimientocorrectivo_empleado FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_mantenimientocorrectivo_proveedormantenimiento (OID = 150969) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT fk_mantenimientocorrectivo_proveedormantenimiento FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index fk_piezareal_estado (OID = 150974) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT fk_piezareal_estado FOREIGN KEY (estado) REFERENCES estadopiezareal(idestado);
--
-- Definition for index fk_piezareal_idcodigobarra (OID = 150979) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT fk_piezareal_idcodigobarra FOREIGN KEY (idcodigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index fk_detallempasignada_idmateriaprima (OID = 150984) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT fk_detallempasignada_idmateriaprima FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detallempasignada_idplanificacionproduccion (OID = 150989) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT fk_detallempasignada_idplanificacionproduccion FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index fk_ejecucionprocesocalidad_estado (OID = 150994) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT fk_ejecucionprocesocalidad_estado FOREIGN KEY (estado) REFERENCES estadoejecucionprocesocalidad(idestado);
--
-- Definition for index fk_pedidomatriz_idmatriz (OID = 150999) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT fk_pedidomatriz_idmatriz FOREIGN KEY (idmatriz) REFERENCES matriz(idmatriz);
--
-- Definition for index fk_maquina_idunidadmedida (OID = 151004) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT fk_maquina_idunidadmedida FOREIGN KEY (idunidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index fk_maquina_estado (OID = 151009) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT fk_maquina_estado FOREIGN KEY (estado) REFERENCES estadomaquina(idestado);
--
-- Definition for index fk_maquina_tipomaquina (OID = 151014) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT fk_maquina_tipomaquina FOREIGN KEY (tipomaquina) REFERENCES tipomaquina(idtipomaquina);
--
-- Definition for index fk_maquina_marca (OID = 151019) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT fk_maquina_marca FOREIGN KEY (marca) REFERENCES marca(idmarca);
--
-- Definition for index fk_planificacioncalidad_pedido (OID = 151024) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT fk_planificacioncalidad_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_detalleplanificacioncalidad_idplanificacioncalidad (OID = 151029) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT fk_detalleplanificacioncalidad_idplanificacioncalidad FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index fk_detalleplanificacioncalidad_procesocalidad (OID = 151034) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT fk_detalleplanificacioncalidad_procesocalidad FOREIGN KEY (procesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index fk_detallemantenimientocorrectivo_rotura (OID = 151039) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT fk_detallemantenimientocorrectivo_rotura FOREIGN KEY (rotura) REFERENCES rotura(idrotura);
--
-- Definition for index fk_detallemantenimientocorrectivo_idmantenimientocorrectivo (OID = 151044) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT fk_detallemantenimientocorrectivo_idmantenimientocorrectivo FOREIGN KEY (idmantenimientocorrectivo) REFERENCES mantenimientocorrectivo(idmantenimientocorrectivo);
--
-- Definition for index fk_localidad_provincia (OID = 151049) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT fk_localidad_provincia FOREIGN KEY (provincia) REFERENCES provincia(idprovincia);
--
-- Definition for index fk_proveedorxmateriaprima_idmateriaprima (OID = 151054) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT fk_proveedorxmateriaprima_idmateriaprima FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_proveedorxmateriaprima_idproveedor (OID = 151059) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT fk_proveedorxmateriaprima_idproveedor FOREIGN KEY (idproveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index fk_remito_pedido (OID = 151064) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT fk_remito_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_remito_estado (OID = 151069) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT fk_remito_estado FOREIGN KEY (estado) REFERENCES estadoremito(idestado);
--
-- Definition for index fk_disponibilidadhoraria_idempleado (OID = 151074) : 
--
ALTER TABLE ONLY disponibilidadhoraria
    ADD CONSTRAINT fk_disponibilidadhoraria_idempleado FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_sesion_usuario (OID = 151079) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT fk_sesion_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_detallereclamoproveedor_idreclamo (OID = 151084) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT fk_detallereclamoproveedor_idreclamo FOREIGN KEY (idreclamo) REFERENCES reclamoproveedor(idreclamo);
--
-- Definition for index fk_detallereclamoproveedor_idcompra (OID = 151089) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT fk_detallereclamoproveedor_idcompra FOREIGN KEY (idcompra, iddetallecompra) REFERENCES detallecompra(idcompra, iddetalle);
--
-- Definition for index fk_barrio_localidad (OID = 151094) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT fk_barrio_localidad FOREIGN KEY (localidad) REFERENCES localidad(idlocalidad);
--
-- Definition for index fk_asistencia_empleado (OID = 151099) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT fk_asistencia_empleado FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_etapadeproduccion_unidaddemedida (OID = 151104) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT fk_etapadeproduccion_unidaddemedida FOREIGN KEY (unidaddemedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index fk_detallefactura_idpedido (OID = 151109) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT fk_detallefactura_idpedido FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_detallefactura_idfactura (OID = 151114) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT fk_detallefactura_idfactura FOREIGN KEY (idfactura) REFERENCES factura(idfactura);
--
-- Definition for index fk_detallepedido_producto (OID = 151119) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT fk_detallepedido_producto FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index fk_detallepedido_idpedido (OID = 151124) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT fk_detallepedido_idpedido FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_mantenimientopreventivo_proveedormantenimiento (OID = 151129) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT fk_mantenimientopreventivo_proveedormantenimiento FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index fk_rolxprivilegio_idprivilegio (OID = 151134) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT fk_rolxprivilegio_idprivilegio FOREIGN KEY (idprivilegio) REFERENCES privilegio(idprivilegio);
--
-- Definition for index fk_rolxprivilegio_idrol (OID = 151139) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT fk_rolxprivilegio_idrol FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index fk_detallereclamocliente_producto (OID = 151144) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT fk_detallereclamocliente_producto FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index fk_detallereclamocliente_idreclamo (OID = 151149) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT fk_detallereclamocliente_idreclamo FOREIGN KEY (idreclamo) REFERENCES reclamocliente(idreclamo);
--
-- Definition for index fk_proveedormantenimientomaquina_condicioniva (OID = 151154) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT fk_proveedormantenimientomaquina_condicioniva FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index fk_proveedormantenimientomaquina_domicilio (OID = 151159) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT fk_proveedormantenimientomaquina_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_proveedormantenimientomaquina_responsable (OID = 151164) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT fk_proveedormantenimientomaquina_responsable FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index fk_detallepiezapresupuesto_iddetalleproductopresupuesto (OID = 151169) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT fk_detallepiezapresupuesto_iddetalleproductopresupuesto FOREIGN KEY (iddetalleproductopresupuesto) REFERENCES detalleproductopresupuesto(iddetalle);
--
-- Definition for index fk_detallepiezapresupuesto_idetapa (OID = 151174) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT fk_detallepiezapresupuesto_idetapa FOREIGN KEY (idetapa) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_ejecucionplanificacionproduccion_estado (OID = 151179) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT fk_ejecucionplanificacionproduccion_estado FOREIGN KEY (estado) REFERENCES estadoejecplanifpedido(idestado);
--
-- Definition for index fk_ejecucionplanificacionproduccion_idplanificacionproduccion (OID = 151184) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT fk_ejecucionplanificacionproduccion_idplanificacionproduccion FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index fk_detallepresupuesto_idpresupuesto (OID = 151189) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT fk_detallepresupuesto_idpresupuesto FOREIGN KEY (idpresupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index fk_detallepresupuesto_idproducto (OID = 151194) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT fk_detallepresupuesto_idproducto FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index fk_domicilio_barrio (OID = 151199) : 
--
ALTER TABLE ONLY domicilio
    ADD CONSTRAINT fk_domicilio_barrio FOREIGN KEY (barrio) REFERENCES barrio(idbarrio);
--
-- Definition for index fk_empleado_cargo (OID = 151204) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_cargo FOREIGN KEY (cargo) REFERENCES cargo(idcargo);
--
-- Definition for index fk_empleado_categoria (OID = 151209) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_categoria FOREIGN KEY (categoria) REFERENCES categoria(idcategoria);
--
-- Definition for index fk_empleado_domicilio (OID = 151214) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_empleado_tipodocumento (OID = 151219) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_tipodocumento FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index fk_empleado_usuario (OID = 151224) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_detalleplanprocedimientos_idetapaproduccion (OID = 151229) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT fk_detalleplanprocedimientos_idetapaproduccion FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_detalleplanprocedimientos_idplanpprocedimientos (OID = 151234) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT fk_detalleplanprocedimientos_idplanpprocedimientos FOREIGN KEY (idplanpprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index fk_reclamocliente_cliente (OID = 151239) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT fk_reclamocliente_cliente FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index fk_reclamocliente_tiporeclamo (OID = 151244) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT fk_reclamocliente_tiporeclamo FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index fk_comprobantepago_usuario (OID = 151249) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT fk_comprobantepago_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_comprobantepago_factura (OID = 151254) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT fk_comprobantepago_factura FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index fk_comprobantepago_formadepago (OID = 151259) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT fk_comprobantepago_formadepago FOREIGN KEY (formadepago) REFERENCES formadepago(idformapago);
--
-- Definition for index fk_mpasignadaxpiezareal_iddetallempasignada (OID = 151264) : 
--
ALTER TABLE ONLY mpasignadaxpiezareal
    ADD CONSTRAINT fk_mpasignadaxpiezareal_iddetallempasignada FOREIGN KEY (iddetallempasignada) REFERENCES detallempasignada(id);
--
-- Definition for index fk_detalleproductopresupuesto_idpieza (OID = 151269) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT fk_detalleproductopresupuesto_idpieza FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index fk_detalleproductopresupuesto_iddetallepresupuesto (OID = 151274) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT fk_detalleproductopresupuesto_iddetallepresupuesto FOREIGN KEY (iddetallepresupuesto) REFERENCES detallepresupuesto(iddetalle);
--
-- Definition for index fk_detalleproductopresupuesto_idmateriaprima (OID = 151279) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT fk_detalleproductopresupuesto_idmateriaprima FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detalletrabajotercerizado_estado (OID = 151284) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT fk_detalletrabajotercerizado_estado FOREIGN KEY (estado) REFERENCES estadodetalletrabajotercerizado(idestado);
--
-- Definition for index fk_detalletrabajotercerizado_idtrabajotercerizado (OID = 151289) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT fk_detalletrabajotercerizado_idtrabajotercerizado FOREIGN KEY (idtrabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index fk_detalletrabajotercerizado_proceso (OID = 151294) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT fk_detalletrabajotercerizado_proceso FOREIGN KEY (proceso) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index ejecucionetapaproduccion_pkey (OID = 151299) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_pkey PRIMARY KEY (id);
--
-- Definition for index detalleejecucionplanificacion_fk (OID = 151301) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk FOREIGN KEY (idejecucionplanificacionproduccion) REFERENCES ejecucionplanificacionproduccion(idejecucion);
--
-- Definition for index detalleejecucionplanificacion_fk1 (OID = 151306) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk1 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleejecucionplanificacion_fk2 (OID = 151311) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk2 FOREIGN KEY (ejecucionetapa) REFERENCES ejecucionetapaproduccion(id);
--
-- Definition for index detalleejecucionplanificacion_fk3 (OID = 151316) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk3 FOREIGN KEY (piezareal) REFERENCES piezareal(idpiezareal);
--
-- Definition for index detalleplanificacionproduccion_fk4 (OID = 151321) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk4 FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index reclamoempresamantenimiento_pkey (OID = 151326) : 
--
ALTER TABLE ONLY reclamoempresamantenimiento
    ADD CONSTRAINT reclamoempresamantenimiento_pkey PRIMARY KEY (idreclamo);
--
-- Definition for index fk_reclamoempresamantenimiento_tiporeclamo (OID = 151328) : 
--
ALTER TABLE ONLY reclamoempresamantenimiento
    ADD CONSTRAINT fk_reclamoempresamantenimiento_tiporeclamo FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index fk_reclamoempresamantenimiento_trabajotercerizado (OID = 151333) : 
--
ALTER TABLE ONLY reclamoempresamantenimiento
    ADD CONSTRAINT fk_reclamoempresamantenimiento_trabajotercerizado FOREIGN KEY (trabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index reclamoempresamantenimiento_fk (OID = 151338) : 
--
ALTER TABLE ONLY reclamoempresamantenimiento
    ADD CONSTRAINT reclamoempresamantenimiento_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index reclamoempresamantenimiento_fk1 (OID = 151343) : 
--
ALTER TABLE ONLY reclamoempresamantenimiento
    ADD CONSTRAINT reclamoempresamantenimiento_fk1 FOREIGN KEY (trabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index detallereclamoempresamantenimiento_idx (OID = 151348) : 
--
ALTER TABLE ONLY detallereclamoempresamantenimiento
    ADD CONSTRAINT detallereclamoempresamantenimiento_idx PRIMARY KEY (iddetalle);
--
-- Definition for index detallereclamoempresamantenimiento_fk (OID = 151350) : 
--
ALTER TABLE ONLY detallereclamoempresamantenimiento
    ADD CONSTRAINT detallereclamoempresamantenimiento_fk FOREIGN KEY (idreclamo) REFERENCES reclamoempresamantenimiento(idreclamo);
--
-- Definition for index matriz_fk1 (OID = 151355) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT matriz_fk1 FOREIGN KEY (tipomaterial) REFERENCES tipomaterial(idtipomaterial);
--
-- Definition for index detalleejecucionplanificacion_fk4 (OID = 151360) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk4 FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index estadoreclamo_pkey (OID = 151365) : 
--
ALTER TABLE ONLY estadoreclamo
    ADD CONSTRAINT estadoreclamo_pkey PRIMARY KEY (idestadoreclamo);
--
-- Definition for index reclamocliente_fk2 (OID = 151367) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_fk2 FOREIGN KEY (idestadoreclamo) REFERENCES estadoreclamo(idestadoreclamo);
--
-- Definition for index reclamoempresamantenimiento_fk2 (OID = 151372) : 
--
ALTER TABLE ONLY reclamoempresamantenimiento
    ADD CONSTRAINT reclamoempresamantenimiento_fk2 FOREIGN KEY (idestadoreclamo) REFERENCES estadoreclamo(idestadoreclamo);
--
-- Definition for index reclamoempresametalurgica_fk2 (OID = 151377) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_fk2 FOREIGN KEY (idestadoreclamo) REFERENCES estadoreclamo(idestadoreclamo);
--
-- Definition for index reclamoproveedor_fk2 (OID = 151382) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_fk2 FOREIGN KEY (idestadoreclamo) REFERENCES estadoreclamo(idestadoreclamo);
--
-- Definition for index mpasignadaxpiezareal_fk (OID = 151387) : 
--
ALTER TABLE ONLY mpasignadaxpiezareal
    ADD CONSTRAINT mpasignadaxpiezareal_fk FOREIGN KEY (idpiezareal) REFERENCES piezareal(idpiezareal);
--
-- Definition for index detalletrabajotercerizado_iddetalle_key (OID = 151392) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_iddetalle_key PRIMARY KEY (iddetalle);
--
-- Definition for index detallereclamoempresamantenimiento_fk1 (OID = 151394) : 
--
ALTER TABLE ONLY detallereclamoempresamantenimiento
    ADD CONSTRAINT detallereclamoempresamantenimiento_fk1 FOREIGN KEY (iddetalletrabajo) REFERENCES detalletrabajotercerizado(iddetalle);
--
-- Definition for index detallereclamoempresamantenimiento_fk2 (OID = 151399) : 
--
ALTER TABLE ONLY detallereclamoempresamantenimiento
    ADD CONSTRAINT detallereclamoempresamantenimiento_fk2 FOREIGN KEY (idtrabajo) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index detalleplanificacioncalidad_iddetalle_key (OID = 151404) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_iddetalle_key PRIMARY KEY (iddetalle);
--
-- Definition for index detalleplanificacioncalidad_fk3 (OID = 151406) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk3 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleplanificacioncalidad_fk4 (OID = 151411) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk4 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detalleplanificacioncalidad_fk1 (OID = 151416) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk1 FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index detalleplanificacioncalidad_fk5 (OID = 151421) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk5 FOREIGN KEY (maquina) REFERENCES maquina(idmaquina);
--
-- Definition for index detalleejecucionplanificacioncalidad_iddetalle_key (OID = 151426) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_iddetalle_key PRIMARY KEY (iddetalle);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk2 (OID = 151428) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk2 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk3 (OID = 151433) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk3 FOREIGN KEY (piezareal) REFERENCES piezareal(idpiezareal);
--
-- Definition for index ejecucionplanificacioncalidad_idejecucion_key (OID = 151438) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_idejecucion_key PRIMARY KEY (idejecucion);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk (OID = 151440) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk FOREIGN KEY (idejecucionplanificacioncalidad) REFERENCES ejecucionplanificacioncalidad(idejecucion);
--
-- Definition for index ejecucionprocesocalidad_pkey (OID = 151445) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_pkey PRIMARY KEY (idejecucion);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk1 (OID = 151447) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk1 FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk4 (OID = 151452) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk4 FOREIGN KEY (ejecucionprocesocalidad) REFERENCES ejecucionprocesocalidad(idejecucion);
--
-- Definition for index detalleproductoreal_pkey (OID = 151457) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detalleproductoreal_fk1 (OID = 151459) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_fk1 FOREIGN KEY (idpiezareal) REFERENCES piezareal(idpiezareal);
--
-- Definition for index piezareal_fk (OID = 151464) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_fk FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index productoreal_fk (OID = 151469) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detalleproductoreal_fk2 (OID = 151474) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_fk2 FOREIGN KEY (detalleproducto) REFERENCES detalleproducto(iddetalle);
--
-- Definition for index ejecucionprocesocalidad_fk (OID = 151479) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_fk FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index detalleplanificacioncalidad_fk6 (OID = 151484) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk6 FOREIGN KEY (iddetalleejecucionplanificacioncalidad) REFERENCES detalleejecucionplanificacioncalidad(iddetalle);
--
-- Definition for index detalleplanificacionproduccion_fk5 (OID = 151489) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk5 FOREIGN KEY (detalleanterior) REFERENCES detalleplanificacionproduccion(id);
--
-- Definition for index ejecucionetapaproduccion_fk3 (OID = 151494) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk3 FOREIGN KEY (maquina) REFERENCES maquina(idmaquina);
--
-- Definition for index detallemantenimientopreventivo_pkey (OID = 151499) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detallemantenimientopreventivo_fk (OID = 151501) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_fk FOREIGN KEY (idmantenimientopreventivo) REFERENCES mantenimientopreventivo(idmantenimientopreventivo);
--
-- Definition for index detallemantenimientopreventivo_fk1 (OID = 151506) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_fk1 FOREIGN KEY (servicio) REFERENCES servicio(idservicio);
--
-- Definition for index detalleplanificacioncalidad_fk7 (OID = 151511) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk7 FOREIGN KEY (detalleanterior) REFERENCES detalleplanificacioncalidad(iddetalle);
--
-- Definition for index ejecucionprocesocalidad_fk2 (OID = 151516) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_fk2 FOREIGN KEY (maquina) REFERENCES maquina(idmaquina);
--
-- Definition for index reclamoempresamantenimiento_fk3 (OID = 151521) : 
--
ALTER TABLE ONLY reclamoempresamantenimiento
    ADD CONSTRAINT reclamoempresamantenimiento_fk3 FOREIGN KEY (mantenimientopreventivo) REFERENCES mantenimientopreventivo(idmantenimientopreventivo);
--
-- Definition for index reclamoempresamantenimiento_fk4 (OID = 151526) : 
--
ALTER TABLE ONLY reclamoempresamantenimiento
    ADD CONSTRAINT reclamoempresamantenimiento_fk4 FOREIGN KEY (mantenimientocorrectivo) REFERENCES mantenimientocorrectivo(idmantenimientocorrectivo);
--
-- Definition for index estadomantpreventivo_pkey (OID = 151531) : 
--
ALTER TABLE ONLY estadomantpreventivo
    ADD CONSTRAINT estadomantpreventivo_pkey PRIMARY KEY (idestado);
--
-- Definition for index mantenimientopreventivo_fk1 (OID = 151533) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_fk1 FOREIGN KEY (estado) REFERENCES estadomantpreventivo(idestado);
--
-- Definition for index detallemantenimientocorrectivo_pkey (OID = 151538) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index etapadeproduccion_fk (OID = 151540) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_fk FOREIGN KEY (tipomaquina) REFERENCES tipomaquina(idtipomaquina);
--
-- Data for sequence public.prueba_id_seq (OID = 148592)
--
SELECT pg_catalog.setval('prueba_id_seq', 1, false);
--
-- Data for sequence public.usuario_idusuario_seq (OID = 148598)
--
SELECT pg_catalog.setval('usuario_idusuario_seq', 31, true);
--
-- Data for sequence public.tipomaterial_idtipomaterial_seq (OID = 148600)
--
SELECT pg_catalog.setval('tipomaterial_idtipomaterial_seq', 8, true);
--
-- Data for sequence public.accioncalidad_idaccioncalidad_seq (OID = 148602)
--
SELECT pg_catalog.setval('accioncalidad_idaccioncalidad_seq', 4, true);
--
-- Data for sequence public.barrio_idbarrio_seq (OID = 148604)
--
SELECT pg_catalog.setval('barrio_idbarrio_seq', 85, true);
--
-- Data for sequence public.cargo_idcargo_seq (OID = 148606)
--
SELECT pg_catalog.setval('cargo_idcargo_seq', 13, true);
--
-- Data for sequence public.categoria_idcategoria_seq (OID = 148608)
--
SELECT pg_catalog.setval('categoria_idcategoria_seq', 5, true);
--
-- Data for sequence public.cliente_idcliente_seq (OID = 148610)
--
SELECT pg_catalog.setval('cliente_idcliente_seq', 33, true);
--
-- Data for sequence public.codigodebarra_idcodigo_seq (OID = 148612)
--
SELECT pg_catalog.setval('codigodebarra_idcodigo_seq', 302, true);
--
-- Data for sequence public.compra_idcompra_seq (OID = 148614)
--
SELECT pg_catalog.setval('compra_idcompra_seq', 26, true);
--
-- Data for sequence public.comprobantepago_idcomprobantepago_seq (OID = 148616)
--
SELECT pg_catalog.setval('comprobantepago_idcomprobantepago_seq', 13, true);
--
-- Data for sequence public.condicioniva_idcondicioniva_seq (OID = 148618)
--
SELECT pg_catalog.setval('condicioniva_idcondicioniva_seq', 3, true);
--
-- Data for sequence public.detalleplanificacionproduccion_id_seq (OID = 148620)
--
SELECT pg_catalog.setval('detalleplanificacionproduccion_id_seq', 404, true);
--
-- Data for sequence public.detallecompra_iddetalle_seq (OID = 148622)
--
SELECT pg_catalog.setval('detallecompra_iddetalle_seq', 27, true);
--
-- Data for sequence public.detalleejecucionplanificacion_iddetalle_seq (OID = 148624)
--
SELECT pg_catalog.setval('detalleejecucionplanificacion_iddetalle_seq', 93, true);
--
-- Data for sequence public.detalleejecucionplanificacioncalidad_iddetalle_seq (OID = 148626)
--
SELECT pg_catalog.setval('detalleejecucionplanificacioncalidad_iddetalle_seq', 39, true);
--
-- Data for sequence public.detallefactura_iddetalle_seq (OID = 148628)
--
SELECT pg_catalog.setval('detallefactura_iddetalle_seq', 14, true);
--
-- Data for sequence public.detallemantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 148630)
--
SELECT pg_catalog.setval('detallemantenimientocorrectivo_idmantenimientocorrectivo_seq', 1, false);
--
-- Data for sequence public.detallemantenimientopreventivo_idmantenimientopreventivo_seq (OID = 148632)
--
SELECT pg_catalog.setval('detallemantenimientopreventivo_idmantenimientopreventivo_seq', 1, false);
--
-- Data for sequence public.detallepedido_iddetalle_seq (OID = 148634)
--
SELECT pg_catalog.setval('detallepedido_iddetalle_seq', 97, true);
--
-- Data for sequence public.detalleplanificacioncalidad_iddetalle_seq (OID = 148636)
--
SELECT pg_catalog.setval('detalleplanificacioncalidad_iddetalle_seq', 167, true);
--
-- Data for sequence public.detalleplanprocedimientos_iddetalle_seq (OID = 148638)
--
SELECT pg_catalog.setval('detalleplanprocedimientos_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleplanprocesoscalidad_iddetalle_seq (OID = 148640)
--
SELECT pg_catalog.setval('detalleplanprocesoscalidad_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallepresupuesto_iddetalle_seq (OID = 148642)
--
SELECT pg_catalog.setval('detallepresupuesto_iddetalle_seq', 167, true);
--
-- Data for sequence public.detalleproducto_iddetalle_seq (OID = 148644)
--
SELECT pg_catalog.setval('detalleproducto_iddetalle_seq', 36, true);
--
-- Data for sequence public.detalleproductoreal_iddetalle_seq (OID = 148646)
--
SELECT pg_catalog.setval('detalleproductoreal_iddetalle_seq', 63, true);
--
-- Data for sequence public.detallereclamocliente_iddetalle_seq (OID = 148648)
--
SELECT pg_catalog.setval('detallereclamocliente_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallereclamoempresametalurgica_iddetalle_seq (OID = 148650)
--
SELECT pg_catalog.setval('detallereclamoempresametalurgica_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallereclamoproveedor_iddetalle_seq (OID = 148652)
--
SELECT pg_catalog.setval('detallereclamoproveedor_iddetalle_seq', 1, true);
--
-- Data for sequence public.detalleremito_iddetalle_seq (OID = 148654)
--
SELECT pg_catalog.setval('detalleremito_iddetalle_seq', 18, true);
--
-- Data for sequence public.detallerequerimientosmateriaprima_iddetalle_seq (OID = 148656)
--
SELECT pg_catalog.setval('detallerequerimientosmateriaprima_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalletrabajotercerizado_iddetalle_seq (OID = 148658)
--
SELECT pg_catalog.setval('detalletrabajotercerizado_iddetalle_seq', 2, true);
--
-- Data for sequence public.domicilio_iddomicilio_seq (OID = 148660)
--
SELECT pg_catalog.setval('domicilio_iddomicilio_seq', 104, true);
--
-- Data for sequence public.ejecucionetapaproduccion_idejecucion_seq (OID = 148662)
--
SELECT pg_catalog.setval('ejecucionetapaproduccion_idejecucion_seq', 93, true);
--
-- Data for sequence public.ejecucionplanificacioncalidad_idejecucion_seq (OID = 148664)
--
SELECT pg_catalog.setval('ejecucionplanificacioncalidad_idejecucion_seq', 12, true);
--
-- Data for sequence public.ejecucionplanificacionproduccion_idejecucion_seq (OID = 148666)
--
SELECT pg_catalog.setval('ejecucionplanificacionproduccion_idejecucion_seq', 55, true);
--
-- Data for sequence public.ejecucionprocesocalidad_idejecucion_seq (OID = 148668)
--
SELECT pg_catalog.setval('ejecucionprocesocalidad_idejecucion_seq', 39, true);
--
-- Data for sequence public.empleado_idempleado_seq (OID = 148670)
--
SELECT pg_catalog.setval('empleado_idempleado_seq', 12, true);
--
-- Data for sequence public.empresametalurgica_idempresametalurgica_seq (OID = 148672)
--
SELECT pg_catalog.setval('empresametalurgica_idempresametalurgica_seq', 5, true);
--
-- Data for sequence public.estadocliente_idestado_seq (OID = 148674)
--
SELECT pg_catalog.setval('estadocliente_idestado_seq', 4, true);
--
-- Data for sequence public.estadocompra_idestado_seq (OID = 148676)
--
SELECT pg_catalog.setval('estadocompra_idestado_seq', 10, true);
--
-- Data for sequence public.estadodetallecompra_idestado_seq (OID = 148678)
--
SELECT pg_catalog.setval('estadodetallecompra_idestado_seq', 8, true);
--
-- Data for sequence public.estadodetalletrabajotercerizado_idestado_seq (OID = 148680)
--
SELECT pg_catalog.setval('estadodetalletrabajotercerizado_idestado_seq', 9, true);
--
-- Data for sequence public.estadoejecetapaprod_idestado_seq (OID = 148682)
--
SELECT pg_catalog.setval('estadoejecetapaprod_idestado_seq', 6, true);
--
-- Data for sequence public.estadoejecplancalidad_idestado_seq (OID = 148684)
--
SELECT pg_catalog.setval('estadoejecplancalidad_idestado_seq', 5, true);
--
-- Data for sequence public.estadoejecplanifpedido_idestado_seq (OID = 148686)
--
SELECT pg_catalog.setval('estadoejecplanifpedido_idestado_seq', 7, true);
--
-- Data for sequence public.estadoejecucionprocesocalidad_idestado_seq (OID = 148688)
--
SELECT pg_catalog.setval('estadoejecucionprocesocalidad_idestado_seq', 7, true);
--
-- Data for sequence public.estadofactura_idestado_seq (OID = 148690)
--
SELECT pg_catalog.setval('estadofactura_idestado_seq', 2, true);
--
-- Data for sequence public.estadomaquina_idestado_seq (OID = 148692)
--
SELECT pg_catalog.setval('estadomaquina_idestado_seq', 1, true);
--
-- Data for sequence public.estadopedido_idestado_seq (OID = 148694)
--
SELECT pg_catalog.setval('estadopedido_idestado_seq', 19, true);
--
-- Data for sequence public.estadopiezareal_idestado_seq (OID = 148696)
--
SELECT pg_catalog.setval('estadopiezareal_idestado_seq', 1, true);
--
-- Data for sequence public.estadoproductoreal_idestado_seq (OID = 148698)
--
SELECT pg_catalog.setval('estadoproductoreal_idestado_seq', 11, true);
--
-- Data for sequence public.estadoremito_idestado_seq (OID = 148700)
--
SELECT pg_catalog.setval('estadoremito_idestado_seq', 1, true);
--
-- Data for sequence public.estadotrabajotercerizado_idestado_seq (OID = 148702)
--
SELECT pg_catalog.setval('estadotrabajotercerizado_idestado_seq', 14, true);
--
-- Data for sequence public.etapadeproduccion_idetapaproduccion_seq (OID = 148704)
--
SELECT pg_catalog.setval('etapadeproduccion_idetapaproduccion_seq', 24, true);
--
-- Data for sequence public.factura_idfactura_seq (OID = 148706)
--
SELECT pg_catalog.setval('factura_idfactura_seq', 8, true);
--
-- Data for sequence public.formadepago_idformapago_seq (OID = 148708)
--
SELECT pg_catalog.setval('formadepago_idformapago_seq', 3, true);
--
-- Data for sequence public.localidad_idlocalidad_seq (OID = 148710)
--
SELECT pg_catalog.setval('localidad_idlocalidad_seq', 50, true);
--
-- Data for sequence public.mantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 148712)
--
SELECT pg_catalog.setval('mantenimientocorrectivo_idmantenimientocorrectivo_seq', 1, false);
--
-- Data for sequence public.mantenimientopreventivo_idmantenimientopreventivo_seq (OID = 148714)
--
SELECT pg_catalog.setval('mantenimientopreventivo_idmantenimientopreventivo_seq', 1, false);
--
-- Data for sequence public.maquina_idmaquina_seq (OID = 148716)
--
SELECT pg_catalog.setval('maquina_idmaquina_seq', 20, true);
--
-- Data for sequence public.marca_idmarca_seq (OID = 148718)
--
SELECT pg_catalog.setval('marca_idmarca_seq', 3, true);
--
-- Data for sequence public.materiaprima_idmateriaprima_seq (OID = 148720)
--
SELECT pg_catalog.setval('materiaprima_idmateriaprima_seq', 31, true);
--
-- Data for sequence public.matriz_idmatriz_seq (OID = 148722)
--
SELECT pg_catalog.setval('matriz_idmatriz_seq', 7, true);
--
-- Data for sequence public.pedido_idpedido_seq (OID = 148724)
--
SELECT pg_catalog.setval('pedido_idpedido_seq', 72, true);
--
-- Data for sequence public.pedidomatriz_idpedidomatriz_seq (OID = 148726)
--
SELECT pg_catalog.setval('pedidomatriz_idpedidomatriz_seq', 1, false);
--
-- Data for sequence public.pieza_idpieza_seq (OID = 148728)
--
SELECT pg_catalog.setval('pieza_idpieza_seq', 15, true);
--
-- Data for sequence public.piezareal_idpiezareal_seq (OID = 148730)
--
SELECT pg_catalog.setval('piezareal_idpiezareal_seq', 1, false);
--
-- Data for sequence public.planificacioncalidad_idplanificacion_seq (OID = 148732)
--
SELECT pg_catalog.setval('planificacioncalidad_idplanificacion_seq', 21, true);
--
-- Data for sequence public.planificacionproduccion_idplanificacionproduccion_seq (OID = 148734)
--
SELECT pg_catalog.setval('planificacionproduccion_idplanificacionproduccion_seq', 90, true);
--
-- Data for sequence public.plano_idplano_seq (OID = 148736)
--
SELECT pg_catalog.setval('plano_idplano_seq', 1, false);
--
-- Data for sequence public.planprocedimientos_idplanprocedimientos_seq (OID = 148738)
--
SELECT pg_catalog.setval('planprocedimientos_idplanprocedimientos_seq', 1, false);
--
-- Data for sequence public.planprocesoscalidad_idplanprocesoscalidad_seq (OID = 148740)
--
SELECT pg_catalog.setval('planprocesoscalidad_idplanprocesoscalidad_seq', 1, false);
--
-- Data for sequence public.planrequerimientosmateriaprima_idplanrequerimientosmateriaprima (OID = 148742)
--
SELECT pg_catalog.setval('planrequerimientosmateriaprima_idplanrequerimientosmateriaprima', 1, false);
--
-- Data for sequence public.presupuesto_idpresupuesto_seq (OID = 148744)
--
SELECT pg_catalog.setval('presupuesto_idpresupuesto_seq', 141, true);
--
-- Data for sequence public.prioridad_idprioridad_seq (OID = 148746)
--
SELECT pg_catalog.setval('prioridad_idprioridad_seq', 3, true);
--
-- Data for sequence public.privilegio_idprivilegio_seq (OID = 148748)
--
SELECT pg_catalog.setval('privilegio_idprivilegio_seq', 1, false);
--
-- Data for sequence public.procesocalidad_idprocesocalidad_seq (OID = 148750)
--
SELECT pg_catalog.setval('procesocalidad_idprocesocalidad_seq', 10, true);
--
-- Data for sequence public.producto_idproducto_seq (OID = 148752)
--
SELECT pg_catalog.setval('producto_idproducto_seq', 25, true);
--
-- Data for sequence public.productoreal_idproductoreal_seq (OID = 148754)
--
SELECT pg_catalog.setval('productoreal_idproductoreal_seq', 27, true);
--
-- Data for sequence public.proveedor_idproveedor_seq (OID = 148756)
--
SELECT pg_catalog.setval('proveedor_idproveedor_seq', 8, true);
--
-- Data for sequence public.proveedormantenimientomaquina_idproveedormantenimiento_seq (OID = 148758)
--
SELECT pg_catalog.setval('proveedormantenimientomaquina_idproveedormantenimiento_seq', 1, false);
--
-- Data for sequence public.provincia_idprovincia_seq (OID = 148760)
--
SELECT pg_catalog.setval('provincia_idprovincia_seq', 14, true);
--
-- Data for sequence public.reclamocliente_idreclamo_seq (OID = 148762)
--
SELECT pg_catalog.setval('reclamocliente_idreclamo_seq', 1, false);
--
-- Data for sequence public.reclamoempresametalurgica_idreclamo_seq (OID = 148764)
--
SELECT pg_catalog.setval('reclamoempresametalurgica_idreclamo_seq', 1, false);
--
-- Data for sequence public.reclamoproveedor_idreclamo_seq (OID = 148766)
--
SELECT pg_catalog.setval('reclamoproveedor_idreclamo_seq', 2, true);
--
-- Data for sequence public.remito_idremito_seq (OID = 148768)
--
SELECT pg_catalog.setval('remito_idremito_seq', 11, true);
--
-- Data for sequence public.responsable_idresponsable_seq (OID = 148770)
--
SELECT pg_catalog.setval('responsable_idresponsable_seq', 44, true);
--
-- Data for sequence public.rol_idrol_seq (OID = 148772)
--
SELECT pg_catalog.setval('rol_idrol_seq', 12, true);
--
-- Data for sequence public.rotura_idrotura_seq (OID = 148774)
--
SELECT pg_catalog.setval('rotura_idrotura_seq', 7, true);
--
-- Data for sequence public.servicio_idservicio_seq (OID = 148776)
--
SELECT pg_catalog.setval('servicio_idservicio_seq', 1, false);
--
-- Data for sequence public.sesion_idsesion_seq (OID = 148778)
--
SELECT pg_catalog.setval('sesion_idsesion_seq', 1, false);
--
-- Data for sequence public.tipodocumento_idtipodocumento_seq (OID = 148780)
--
SELECT pg_catalog.setval('tipodocumento_idtipodocumento_seq', 3, true);
--
-- Data for sequence public.tipoiva_idtipoiva_seq (OID = 148782)
--
SELECT pg_catalog.setval('tipoiva_idtipoiva_seq', 1, false);
--
-- Data for sequence public.tipomaquina_idtipomaquina_seq (OID = 148784)
--
SELECT pg_catalog.setval('tipomaquina_idtipomaquina_seq', 6, true);
--
-- Data for sequence public.tiporeclamo_idtiporeclamo_seq (OID = 148786)
--
SELECT pg_catalog.setval('tiporeclamo_idtiporeclamo_seq', 1, true);
--
-- Data for sequence public.trabajotercerizado_idtrabajo_seq (OID = 148788)
--
SELECT pg_catalog.setval('trabajotercerizado_idtrabajo_seq', 2, true);
--
-- Data for sequence public.turno_idturno_seq (OID = 148790)
--
SELECT pg_catalog.setval('turno_idturno_seq', 3, true);
--
-- Data for sequence public.unidadmedida_idunidadmedida_seq (OID = 148800)
--
SELECT pg_catalog.setval('unidadmedida_idunidadmedida_seq', 4, true);
--
-- Data for sequence public.detallepiezapresupuesto_iddetalle_seq (OID = 148810)
--
SELECT pg_catalog.setval('detallepiezapresupuesto_iddetalle_seq', 359, true);
--
-- Data for sequence public.detalleproductopresupuesto_iddetalle_seq (OID = 148816)
--
SELECT pg_catalog.setval('detalleproductopresupuesto_iddetalle_seq', 279, true);
--
-- Data for sequence public.detallepiezacalidadpresupuesto_iddetalle_seq (OID = 148822)
--
SELECT pg_catalog.setval('detallepiezacalidadpresupuesto_iddetalle_seq', 200, true);
--
-- Data for sequence public.calendario_id_seq (OID = 148841)
--
SELECT pg_catalog.setval('calendario_id_seq', 12, true);
--
-- Data for sequence public.disponibilidadhoraria_id_seq (OID = 148851)
--
SELECT pg_catalog.setval('disponibilidadhoraria_id_seq', 555, true);
--
-- Data for sequence public.estadoplanificacionproduccion_id_seq (OID = 148857)
--
SELECT pg_catalog.setval('estadoplanificacionproduccion_id_seq', 3, true);
--
-- Data for sequence public.maquina_idmaquina_seq1 (OID = 148866)
--
SELECT pg_catalog.setval('maquina_idmaquina_seq1', 12, true);
--
-- Data for sequence public.piezareal_idpiezareal_seq1 (OID = 148872)
--
SELECT pg_catalog.setval('piezareal_idpiezareal_seq1', 274, true);
--
-- Data for sequence public.pieza_idpieza_seq1 (OID = 148878)
--
SELECT pg_catalog.setval('pieza_idpieza_seq1', 23, true);
--
-- Data for sequence public.detallempasignada_id_seq (OID = 148884)
--
SELECT pg_catalog.setval('detallempasignada_id_seq', 89, true);
--
-- Data for sequence public.mpasignadaxpiezareal_id_seq (OID = 148890)
--
SELECT pg_catalog.setval('mpasignadaxpiezareal_id_seq', 259, true);
--
-- Data for sequence public.reclamoempresamantenimiento_idreclamo_seq (OID = 148929)
--
SELECT pg_catalog.setval('reclamoempresamantenimiento_idreclamo_seq', 1, false);
--
-- Data for sequence public.detallereclamoempresamantenimiento_iddetalle_seq (OID = 148935)
--
SELECT pg_catalog.setval('detallereclamoempresamantenimiento_iddetalle_seq', 1, false);
--
-- Data for sequence public.estadoreclamo_idestadoreclamo_seq (OID = 148941)
--
SELECT pg_catalog.setval('estadoreclamo_idestadoreclamo_seq', 5, true);
--
-- Data for sequence public.detallemantenimientopreventivo_iddetalle_seq (OID = 148955)
--
SELECT pg_catalog.setval('detallemantenimientopreventivo_iddetalle_seq', 1, false);
--
-- Data for sequence public.estadomantpreventivo_idestado_seq (OID = 148964)
--
SELECT pg_catalog.setval('estadomantpreventivo_idestado_seq', 6, true);
--
-- Data for sequence public.detallemantenimientocorrectivo_iddetalle_seq (OID = 148966)
--
SELECT pg_catalog.setval('detallemantenimientocorrectivo_iddetalle_seq', 1, false);
--
-- Comments
--
COMMENT ON SCHEMA public IS 'standard public schema';
