-- SQL Manager 2007 for PostgreSQL 4.5.0.8
-- ---------------------------------------
-- Host      : localhost
-- Database  : metalsoft
-- Version   : PostgreSQL 8.3.11, compiled by Visual C++ build 1400



--
-- Definition for language plpgsql (OID = 16386) : 
--
/*CREATE TRUSTED PROCEDURAL LANGUAGE plpgsql
   HANDLER "plpgsql_call_handler"
   VALIDATOR "pg_catalog"."plpgsql_validator";*/
--
-- Definition for function nvonropedido (OID = 28666) : 
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
-- Definition for function cantpiezasxproducto (OID = 28667) : 
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
-- Definition for function esproductosinalgunaetapa (OID = 28668) : 
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
-- Definition for function cantpiezasdepedido (OID = 38917) : 
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
-- Definition for function nvonroproducto (OID = 38975) : 
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
-- Definition for function nvonromateriaprima (OID = 60957) : 
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
-- Definition for function nvonroempleado (OID = 63304) : 
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
-- Definition for function nvonromaquina (OID = 63305) : 
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
-- Definition for function nvonroejecucionplanificacion (OID = 69230) : 
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
-- Definition for function nvonroplanificacionproduccion (OID = 72423) : 
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
-- Structure for table pedido (OID = 28669) : 
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
-- Structure for table planificacioncalidad (OID = 28673) : 
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
-- Structure for table estadopedido (OID = 28677) : 
--
CREATE TABLE public.estadopedido (
    idestado bigint DEFAULT nextval(('"public"."estadopedido_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table factura (OID = 28681) : 
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
-- Structure for table planificacionproduccion (OID = 28685) : 
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
-- Structure for table presupuesto (OID = 28689) : 
--
CREATE TABLE public.presupuesto (
    idpresupuesto bigint DEFAULT nextval(('"public"."presupuesto_idpresupuesto_seq"'::text)::regclass) NOT NULL,
    fechapresupuesto date,
    montototal double precision,
    fechavencimiento date,
    nropresupuesto bigint
) WITH OIDS;
--
-- Structure for table plano (OID = 28693) : 
--
CREATE TABLE public.plano (
    idplano bigint DEFAULT nextval(('"public"."plano_idplano_seq"'::text)::regclass) NOT NULL,
    nroplano bigint,
    escala integer,
    pedido bigint,
    imagen bytea[]
) WITH OIDS;
--
-- Structure for table remito (OID = 28700) : 
--
CREATE TABLE public.remito (
    idremito bigint DEFAULT nextval(('"public"."remito_idremito_seq"'::text)::regclass) NOT NULL,
    nroremito bigint,
    fechaemision date,
    pedido bigint,
    estado bigint
) WITH OIDS;
--
-- Structure for table trabajotercerizado (OID = 28704) : 
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
-- Structure for table cliente (OID = 28708) : 
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
-- Structure for table tipoiva (OID = 28712) : 
--
CREATE TABLE public.tipoiva (
    idtipoiva bigint DEFAULT nextval(('"public"."tipoiva_idtipoiva_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table formadepago (OID = 28716) : 
--
CREATE TABLE public.formadepago (
    idformapago bigint DEFAULT nextval(('"public"."formadepago_idformapago_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table usuario (OID = 28720) : 
--
CREATE TABLE public.usuario (
    idusuario bigint DEFAULT nextval(('"public"."usuario_idusuario_seq"'::text)::regclass) NOT NULL,
    usuario character varying(50),
    clave character varying(50)
) WITH OIDS;
--
-- Structure for table estadofactura (OID = 28724) : 
--
CREATE TABLE public.estadofactura (
    idestado bigint DEFAULT nextval(('"public"."estadofactura_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table comprobantepago (OID = 28728) : 
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
-- Structure for table estadotrabajotercerizado (OID = 28732) : 
--
CREATE TABLE public.estadotrabajotercerizado (
    idestado bigint DEFAULT nextval(('"public"."estadotrabajotercerizado_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table empresametalurgica (OID = 28736) : 
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
-- Structure for table prioridad (OID = 28740) : 
--
CREATE TABLE public.prioridad (
    idprioridad bigint DEFAULT nextval(('"public"."prioridad_idprioridad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table estadocliente (OID = 28744) : 
--
CREATE TABLE public.estadocliente (
    idestado bigint DEFAULT nextval(('"public"."estadocliente_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table responsable (OID = 28748) : 
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
-- Structure for table condicioniva (OID = 28752) : 
--
CREATE TABLE public.condicioniva (
    idcondicioniva bigint DEFAULT nextval(('"public"."condicioniva_idcondicioniva_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table domicilio (OID = 28756) : 
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
-- Structure for table rotura (OID = 28760) : 
--
CREATE TABLE public.rotura (
    idrotura bigint DEFAULT nextval(('"public"."rotura_idrotura_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table categoria (OID = 28764) : 
--
CREATE TABLE public.categoria (
    idcategoria bigint DEFAULT nextval(('"public"."categoria_idcategoria_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table cargo (OID = 28768) : 
--
CREATE TABLE public.cargo (
    idcargo bigint DEFAULT nextval(('"public"."cargo_idcargo_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table turno (OID = 28772) : 
--
CREATE TABLE public.turno (
    idturno bigint DEFAULT nextval(('"public"."turno_idturno_seq"'::text)::regclass) NOT NULL,
    horainicio time without time zone,
    horafin time without time zone,
    nombre character varying(20),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table marca (OID = 28776) : 
--
CREATE TABLE public.marca (
    idmarca bigint DEFAULT nextval(('"public"."marca_idmarca_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadomaquina (OID = 28780) : 
--
CREATE TABLE public.estadomaquina (
    idestado bigint DEFAULT nextval(('"public"."estadomaquina_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table tipomaquina (OID = 28784) : 
--
CREATE TABLE public.tipomaquina (
    idtipomaquina bigint DEFAULT nextval(('"public"."tipomaquina_idtipomaquina_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecetapaprod (OID = 28788) : 
--
CREATE TABLE public.estadoejecetapaprod (
    idestado bigint DEFAULT nextval(('"public"."estadoejecetapaprod_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table servicio (OID = 28792) : 
--
CREATE TABLE public.servicio (
    idservicio bigint DEFAULT nextval(('"public"."servicio_idservicio_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table tipodocumento (OID = 28796) : 
--
CREATE TABLE public.tipodocumento (
    idtipodocumento bigint DEFAULT nextval(('"public"."tipodocumento_idtipodocumento_seq"'::text)::regclass) NOT NULL,
    tipo character varying(50),
    nombre character varying(50)
) WITH OIDS;
--
-- Structure for table codigodebarra (OID = 28800) : 
--
CREATE TABLE public.codigodebarra (
    idcodigo bigint DEFAULT nextval(('"public"."codigodebarra_idcodigo_seq"'::text)::regclass) NOT NULL,
    descripcion character varying(50),
    codigo character varying(50)
) WITH OIDS;
--
-- Structure for table estadopiezareal (OID = 28804) : 
--
CREATE TABLE public.estadopiezareal (
    idestado bigint DEFAULT nextval(('"public"."estadopiezareal_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecplanifpedido (OID = 28808) : 
--
CREATE TABLE public.estadoejecplanifpedido (
    idestado bigint DEFAULT nextval(('"public"."estadoejecplanifpedido_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table proveedormantenimientomaquina (OID = 28812) : 
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
-- Structure for table rol (OID = 28816) : 
--
CREATE TABLE public.rol (
    idrol bigint DEFAULT nextval(('"public"."rol_idrol_seq"'::text)::regclass) NOT NULL,
    rol character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table privilegio (OID = 28820) : 
--
CREATE TABLE public.privilegio (
    idprivilegio bigint DEFAULT nextval(('"public"."privilegio_idprivilegio_seq"'::text)::regclass) NOT NULL,
    privilegio character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table accioncalidad (OID = 28824) : 
--
CREATE TABLE public.accioncalidad (
    idaccioncalidad bigint DEFAULT nextval(('"public"."accioncalidad_idaccioncalidad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(150)
) WITH OIDS;
--
-- Structure for table empleado (OID = 28828) : 
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
-- Structure for table proveedor (OID = 28832) : 
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
-- Structure for table estadocompra (OID = 28836) : 
--
CREATE TABLE public.estadocompra (
    idestado bigint DEFAULT nextval(('"public"."estadocompra_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table sesion (OID = 28840) : 
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
-- Structure for table matriz (OID = 28848) : 
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
-- Structure for table producto (OID = 28856) : 
--
CREATE TABLE public.producto (
    idproducto bigint DEFAULT nextval(('"public"."producto_idproducto_seq"'::text)::regclass) NOT NULL,
    nroproducto bigint,
    nombre character varying(50),
    preciounitario double precision,
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecplancalidad (OID = 28860) : 
--
CREATE TABLE public.estadoejecplancalidad (
    idestado bigint DEFAULT nextval(('"public"."estadoejecplancalidad_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table mantenimientopreventivo (OID = 28864) : 
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
-- Structure for table mantenimientocorrectivo (OID = 28868) : 
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
-- Structure for table etapadeproduccion (OID = 28876) : 
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
-- Structure for table ejecucionplanificacionproduccion (OID = 28884) : 
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
-- Structure for table procesocalidad (OID = 28888) : 
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
-- Structure for table ejecucionplanificacioncalidad (OID = 28892) : 
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
-- Structure for table ejecucionprocesocalidad (OID = 28896) : 
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
-- Structure for table ejecucionetapaproduccion (OID = 28900) : 
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
-- Structure for table compra (OID = 28904) : 
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
-- Structure for table detallemantenimientocorrectivo (OID = 28911) : 
--
CREATE TABLE public.detallemantenimientocorrectivo (
    idmantenimientocorrectivo bigint DEFAULT nextval(('"public"."detallemantenimientocorrectivo_idmantenimientocorrectivo_seq"'::text)::regclass) NOT NULL,
    iddetalle bigint NOT NULL,
    duracion time without time zone,
    rotura bigint,
    motivorotura character varying(100)
) WITH OIDS;
--
-- Structure for table detallemantenimientopreventivo (OID = 28915) : 
--
CREATE TABLE public.detallemantenimientopreventivo (
    idmantenimientopreventivo bigint DEFAULT nextval(('"public"."detallemantenimientopreventivo_idmantenimientopreventivo_seq"'::text)::regclass) NOT NULL,
    iddetalle bigint NOT NULL,
    duracion time without time zone,
    servicio bigint,
    observaciones character varying(100)
) WITH OIDS;
--
-- Structure for table detalleejecucionplanificacion (OID = 28919) : 
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
-- Structure for table detalleejecucionplanificacioncalidad (OID = 28923) : 
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
-- Structure for table detalleplanificacionproduccion (OID = 28927) : 
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
-- Structure for table detalleproducto (OID = 28931) : 
--
CREATE TABLE public.detalleproducto (
    iddetalle bigint DEFAULT nextval(('"public"."detalleproducto_iddetalle_seq"'::text)::regclass) NOT NULL,
    idproducto bigint NOT NULL,
    cantidadpiezas integer,
    descripcion character varying(50),
    pieza bigint NOT NULL
) WITH OIDS;
--
-- Structure for table detallepedido (OID = 28935) : 
--
CREATE TABLE public.detallepedido (
    iddetalle bigint DEFAULT nextval(('"public"."detallepedido_iddetalle_seq"'::text)::regclass) NOT NULL,
    idpedido bigint NOT NULL,
    precio double precision,
    cantidad integer,
    producto bigint
) WITH OIDS;
--
-- Structure for table detalletrabajotercerizado (OID = 28939) : 
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
-- Structure for table detalleplanificacioncalidad (OID = 28943) : 
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
-- Structure for table detallecompra (OID = 28947) : 
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
-- Structure for table detalleremito (OID = 28951) : 
--
CREATE TABLE public.detalleremito (
    iddetalle bigint DEFAULT nextval(('"public"."detalleremito_iddetalle_seq"'::text)::regclass) NOT NULL,
    idremito bigint NOT NULL,
    cantidad integer,
    descripcion character varying(50),
    producto bigint
) WITH OIDS;
--
-- Structure for table tiporeclamo (OID = 28955) : 
--
CREATE TABLE public.tiporeclamo (
    idtiporeclamo bigint DEFAULT nextval(('"public"."tiporeclamo_idtiporeclamo_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table reclamoempresametalurgica (OID = 28959) : 
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
-- Structure for table reclamoproveedor (OID = 28963) : 
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
-- Structure for table reclamocliente (OID = 28967) : 
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
-- Structure for table detallereclamocliente (OID = 28971) : 
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
-- Structure for table detallereclamoproveedor (OID = 28975) : 
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
-- Structure for table detallereclamoempresametalurgica (OID = 28979) : 
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
-- Structure for table proveedorxmateriaprima (OID = 28983) : 
--
CREATE TABLE public.proveedorxmateriaprima (
    idproveedor bigint NOT NULL,
    idmateriaprima bigint NOT NULL,
    precio double precision
) WITH OIDS;
--
-- Structure for table maquinaxejecucionetapaproduccion (OID = 28986) : 
--
CREATE TABLE public.maquinaxejecucionetapaproduccion (
    idejecucionetapaproduccion bigint NOT NULL,
    idetapaproduccion bigint NOT NULL,
    idmaquina bigint NOT NULL,
    horasmaquina time without time zone,
    horashombre time without time zone
) WITH OIDS;
--
-- Structure for table maquinaxprocesocalidad (OID = 28989) : 
--
CREATE TABLE public.maquinaxprocesocalidad (
    idprocesocalidad bigint NOT NULL,
    idmaquina bigint NOT NULL,
    duracion time without time zone,
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table piezaxetapadeproduccion (OID = 28992) : 
--
CREATE TABLE public.piezaxetapadeproduccion (
    idpieza bigint NOT NULL,
    idetapaproduccion bigint NOT NULL,
    duracion time without time zone,
    descripcion character varying(100)
) WITH OIDS;
--
-- Structure for table empleadoxturno (OID = 28995) : 
--
CREATE TABLE public.empleadoxturno (
    idempleado bigint NOT NULL,
    idturno bigint NOT NULL
) WITH OIDS;
--
-- Structure for table provincia (OID = 28998) : 
--
CREATE TABLE public.provincia (
    idprovincia bigint DEFAULT nextval(('"public"."provincia_idprovincia_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50)
) WITH OIDS;
--
-- Structure for table localidad (OID = 29002) : 
--
CREATE TABLE public.localidad (
    idlocalidad bigint DEFAULT nextval(('"public"."localidad_idlocalidad_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    provincia bigint
) WITH OIDS;
--
-- Structure for table barrio (OID = 29006) : 
--
CREATE TABLE public.barrio (
    idbarrio bigint DEFAULT nextval(('"public"."barrio_idbarrio_seq"'::text)::regclass) NOT NULL,
    nombre character varying(25),
    codpostal bigint,
    localidad bigint
) WITH OIDS;
--
-- Structure for table usuarioxrol (OID = 29010) : 
--
CREATE TABLE public.usuarioxrol (
    idrol bigint NOT NULL,
    idusuario bigint NOT NULL
) WITH OIDS;
--
-- Structure for table rolxprivilegio (OID = 29013) : 
--
CREATE TABLE public.rolxprivilegio (
    idrol bigint NOT NULL,
    idprivilegio bigint NOT NULL
) WITH OIDS;
--
-- Structure for table planrequerimientosmateriaprima (OID = 29016) : 
--
CREATE TABLE public.planrequerimientosmateriaprima (
    idplanrequerimientosmateriaprima bigint DEFAULT nextval(('"public"."planrequerimientosmateriaprima_idplanrequerimientosmateriaprima_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table planprocedimientos (OID = 29020) : 
--
CREATE TABLE public.planprocedimientos (
    idplanprocedimientos bigint DEFAULT nextval(('"public"."planprocedimientos_idplanprocedimientos_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table planprocesoscalidad (OID = 29024) : 
--
CREATE TABLE public.planprocesoscalidad (
    idplanprocesoscalidad bigint DEFAULT nextval(('"public"."planprocesoscalidad_idplanprocesoscalidad_seq"'::text)::regclass) NOT NULL
) WITH OIDS;
--
-- Structure for table detallerequerimientosmateriaprima (OID = 29028) : 
--
CREATE TABLE public.detallerequerimientosmateriaprima (
    iddetalle bigint DEFAULT nextval(('"public"."detallerequerimientosmateriaprima_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanrequerimientosmateriaprima bigint NOT NULL,
    cantidadmateriaprima integer,
    idpieza bigint,
    idmateriaprima bigint
) WITH OIDS;
--
-- Structure for table detalleplanprocedimientos (OID = 29032) : 
--
CREATE TABLE public.detalleplanprocedimientos (
    iddetalle bigint DEFAULT nextval(('"public"."detalleplanprocedimientos_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanpprocedimientos bigint NOT NULL,
    idpieza bigint,
    idetapaproduccion bigint,
    duracionestimada time without time zone
) WITH OIDS;
--
-- Structure for table detalleplanprocesoscalidad (OID = 29036) : 
--
CREATE TABLE public.detalleplanprocesoscalidad (
    iddetalle bigint DEFAULT nextval(('"public"."detalleplanprocesoscalidad_iddetalle_seq"'::text)::regclass) NOT NULL,
    idplanprocesoscalidad bigint NOT NULL,
    idpieza bigint,
    idprocesocalidad bigint,
    duracionestimada time without time zone
) WITH OIDS;
--
-- Structure for table pedidomatriz (OID = 29040) : 
--
CREATE TABLE public.pedidomatriz (
    idpedidomatriz bigint DEFAULT nextval(('"public"."pedidomatriz_idpedidomatriz_seq"'::text)::regclass) NOT NULL,
    nropedidomatriz bigint,
    fechapedidomatriz date,
    idmatriz bigint,
    observaciones character varying(100)
) WITH OIDS;
--
-- Structure for table estadodetallecompra (OID = 29044) : 
--
CREATE TABLE public.estadodetallecompra (
    idestado bigint DEFAULT nextval(('"public"."estadodetallecompra_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadodetalletrabajotercerizado (OID = 29048) : 
--
CREATE TABLE public.estadodetalletrabajotercerizado (
    idestado bigint DEFAULT nextval(('"public"."estadodetalletrabajotercerizado_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table estadoejecucionprocesocalidad (OID = 29052) : 
--
CREATE TABLE public.estadoejecucionprocesocalidad (
    idestado bigint DEFAULT nextval(('"public"."estadoejecucionprocesocalidad_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table detalleproductoreal (OID = 29056) : 
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
-- Structure for table productoreal (OID = 29060) : 
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
-- Structure for table estadoproductoreal (OID = 29064) : 
--
CREATE TABLE public.estadoproductoreal (
    idestado bigint DEFAULT nextval(('"public"."estadoproductoreal_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table detallefactura (OID = 29068) : 
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
-- Structure for table estadoremito (OID = 29076) : 
--
CREATE TABLE public.estadoremito (
    idestado bigint DEFAULT nextval(('"public"."estadoremito_idestado_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Structure for table tipomaterial (OID = 29080) : 
--
CREATE TABLE public.tipomaterial (
    idtipomaterial bigint DEFAULT nextval(('"public"."tipomaterial_idtipomaterial_seq"'::text)::regclass) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50)
) WITH OIDS;
--
-- Definition for sequence prueba_id_seq (OID = 29084) : 
--
CREATE SEQUENCE public.prueba_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table prueba (OID = 29086) : 
--
CREATE TABLE public.prueba (
    id bigint DEFAULT nextval('prueba_id_seq'::regclass) NOT NULL,
    valor character varying(20)
) WITH OIDS;
ALTER TABLE ONLY public.prueba ALTER COLUMN id SET STATISTICS 0;
ALTER TABLE ONLY public.prueba ALTER COLUMN valor SET STATISTICS 0;
--
-- Definition for sequence usuario_idusuario_seq (OID = 29090) : 
--
CREATE SEQUENCE public.usuario_idusuario_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipomaterial_idtipomaterial_seq (OID = 29092) : 
--
CREATE SEQUENCE public.tipomaterial_idtipomaterial_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence accioncalidad_idaccioncalidad_seq (OID = 29094) : 
--
CREATE SEQUENCE public.accioncalidad_idaccioncalidad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence barrio_idbarrio_seq (OID = 29096) : 
--
CREATE SEQUENCE public.barrio_idbarrio_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence cargo_idcargo_seq (OID = 29098) : 
--
CREATE SEQUENCE public.cargo_idcargo_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence categoria_idcategoria_seq (OID = 29100) : 
--
CREATE SEQUENCE public.categoria_idcategoria_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence cliente_idcliente_seq (OID = 29102) : 
--
CREATE SEQUENCE public.cliente_idcliente_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence codigodebarra_idcodigo_seq (OID = 29104) : 
--
CREATE SEQUENCE public.codigodebarra_idcodigo_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence compra_idcompra_seq (OID = 29106) : 
--
CREATE SEQUENCE public.compra_idcompra_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence comprobantepago_idcomprobantepago_seq (OID = 29108) : 
--
CREATE SEQUENCE public.comprobantepago_idcomprobantepago_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence condicioniva_idcondicioniva_seq (OID = 29110) : 
--
CREATE SEQUENCE public.condicioniva_idcondicioniva_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanificacionproduccion_id_seq (OID = 29112) : 
--
CREATE SEQUENCE public.detalleplanificacionproduccion_id_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallecompra_iddetalle_seq (OID = 29114) : 
--
CREATE SEQUENCE public.detallecompra_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleejecucionplanificacion_iddetalle_seq (OID = 29116) : 
--
CREATE SEQUENCE public.detalleejecucionplanificacion_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleejecucionplanificacioncalidad_iddetalle_seq (OID = 29118) : 
--
CREATE SEQUENCE public.detalleejecucionplanificacioncalidad_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallefactura_iddetalle_seq (OID = 29120) : 
--
CREATE SEQUENCE public.detallefactura_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallemantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 29122) : 
--
CREATE SEQUENCE public.detallemantenimientocorrectivo_idmantenimientocorrectivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallemantenimientopreventivo_idmantenimientopreventivo_seq (OID = 29124) : 
--
CREATE SEQUENCE public.detallemantenimientopreventivo_idmantenimientopreventivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallepedido_iddetalle_seq (OID = 29126) : 
--
CREATE SEQUENCE public.detallepedido_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanificacioncalidad_iddetalle_seq (OID = 29128) : 
--
CREATE SEQUENCE public.detalleplanificacioncalidad_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanprocedimientos_iddetalle_seq (OID = 29130) : 
--
CREATE SEQUENCE public.detalleplanprocedimientos_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleplanprocesoscalidad_iddetalle_seq (OID = 29132) : 
--
CREATE SEQUENCE public.detalleplanprocesoscalidad_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallepresupuesto_iddetalle_seq (OID = 29134) : 
--
CREATE SEQUENCE public.detallepresupuesto_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleproducto_iddetalle_seq (OID = 29136) : 
--
CREATE SEQUENCE public.detalleproducto_iddetalle_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleproductoreal_iddetalle_seq (OID = 29138) : 
--
CREATE SEQUENCE public.detalleproductoreal_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamocliente_iddetalle_seq (OID = 29140) : 
--
CREATE SEQUENCE public.detallereclamocliente_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamoempresametalurgica_iddetalle_seq (OID = 29142) : 
--
CREATE SEQUENCE public.detallereclamoempresametalurgica_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallereclamoproveedor_iddetalle_seq (OID = 29144) : 
--
CREATE SEQUENCE public.detallereclamoproveedor_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalleremito_iddetalle_seq (OID = 29146) : 
--
CREATE SEQUENCE public.detalleremito_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detallerequerimientosmateriaprima_iddetalle_seq (OID = 29148) : 
--
CREATE SEQUENCE public.detallerequerimientosmateriaprima_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence detalletrabajotercerizado_iddetalle_seq (OID = 29150) : 
--
CREATE SEQUENCE public.detalletrabajotercerizado_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence domicilio_iddomicilio_seq (OID = 29152) : 
--
CREATE SEQUENCE public.domicilio_iddomicilio_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionetapaproduccion_idejecucion_seq (OID = 29154) : 
--
CREATE SEQUENCE public.ejecucionetapaproduccion_idejecucion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionplanificacioncalidad_idejecucion_seq (OID = 29156) : 
--
CREATE SEQUENCE public.ejecucionplanificacioncalidad_idejecucion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionplanificacionproduccion_idejecucion_seq (OID = 29158) : 
--
CREATE SEQUENCE public.ejecucionplanificacionproduccion_idejecucion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence ejecucionprocesocalidad_idejecucion_seq (OID = 29160) : 
--
CREATE SEQUENCE public.ejecucionprocesocalidad_idejecucion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence empleado_idempleado_seq (OID = 29162) : 
--
CREATE SEQUENCE public.empleado_idempleado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence empresametalurgica_idempresametalurgica_seq (OID = 29164) : 
--
CREATE SEQUENCE public.empresametalurgica_idempresametalurgica_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadocliente_idestado_seq (OID = 29166) : 
--
CREATE SEQUENCE public.estadocliente_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadocompra_idestado_seq (OID = 29168) : 
--
CREATE SEQUENCE public.estadocompra_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadodetallecompra_idestado_seq (OID = 29170) : 
--
CREATE SEQUENCE public.estadodetallecompra_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadodetalletrabajotercerizado_idestado_seq (OID = 29172) : 
--
CREATE SEQUENCE public.estadodetalletrabajotercerizado_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecetapaprod_idestado_seq (OID = 29174) : 
--
CREATE SEQUENCE public.estadoejecetapaprod_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecplancalidad_idestado_seq (OID = 29176) : 
--
CREATE SEQUENCE public.estadoejecplancalidad_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecplanifpedido_idestado_seq (OID = 29178) : 
--
CREATE SEQUENCE public.estadoejecplanifpedido_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoejecucionprocesocalidad_idestado_seq (OID = 29180) : 
--
CREATE SEQUENCE public.estadoejecucionprocesocalidad_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadofactura_idestado_seq (OID = 29182) : 
--
CREATE SEQUENCE public.estadofactura_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadomaquina_idestado_seq (OID = 29184) : 
--
CREATE SEQUENCE public.estadomaquina_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadopedido_idestado_seq (OID = 29186) : 
--
CREATE SEQUENCE public.estadopedido_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadopiezareal_idestado_seq (OID = 29188) : 
--
CREATE SEQUENCE public.estadopiezareal_idestado_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoproductoreal_idestado_seq (OID = 29190) : 
--
CREATE SEQUENCE public.estadoproductoreal_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadoremito_idestado_seq (OID = 29192) : 
--
CREATE SEQUENCE public.estadoremito_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence estadotrabajotercerizado_idestado_seq (OID = 29194) : 
--
CREATE SEQUENCE public.estadotrabajotercerizado_idestado_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence etapadeproduccion_idetapaproduccion_seq (OID = 29196) : 
--
CREATE SEQUENCE public.etapadeproduccion_idetapaproduccion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence factura_idfactura_seq (OID = 29198) : 
--
CREATE SEQUENCE public.factura_idfactura_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence formadepago_idformapago_seq (OID = 29200) : 
--
CREATE SEQUENCE public.formadepago_idformapago_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence localidad_idlocalidad_seq (OID = 29202) : 
--
CREATE SEQUENCE public.localidad_idlocalidad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence mantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 29204) : 
--
CREATE SEQUENCE public.mantenimientocorrectivo_idmantenimientocorrectivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence mantenimientopreventivo_idmantenimientopreventivo_seq (OID = 29206) : 
--
CREATE SEQUENCE public.mantenimientopreventivo_idmantenimientopreventivo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence maquina_idmaquina_seq (OID = 29208) : 
--
CREATE SEQUENCE public.maquina_idmaquina_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence marca_idmarca_seq (OID = 29210) : 
--
CREATE SEQUENCE public.marca_idmarca_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence materiaprima_idmateriaprima_seq (OID = 29212) : 
--
CREATE SEQUENCE public.materiaprima_idmateriaprima_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence matriz_idmatriz_seq (OID = 29214) : 
--
CREATE SEQUENCE public.matriz_idmatriz_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pedido_idpedido_seq (OID = 29216) : 
--
CREATE SEQUENCE public.pedido_idpedido_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pedidomatriz_idpedidomatriz_seq (OID = 29218) : 
--
CREATE SEQUENCE public.pedidomatriz_idpedidomatriz_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence pieza_idpieza_seq (OID = 29220) : 
--
CREATE SEQUENCE public.pieza_idpieza_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence piezareal_idpiezareal_seq (OID = 29222) : 
--
CREATE SEQUENCE public.piezareal_idpiezareal_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planificacioncalidad_idplanificacion_seq (OID = 29224) : 
--
CREATE SEQUENCE public.planificacioncalidad_idplanificacion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planificacionproduccion_idplanificacionproduccion_seq (OID = 29226) : 
--
CREATE SEQUENCE public.planificacionproduccion_idplanificacionproduccion_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence plano_idplano_seq (OID = 29228) : 
--
CREATE SEQUENCE public.plano_idplano_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planprocedimientos_idplanprocedimientos_seq (OID = 29230) : 
--
CREATE SEQUENCE public.planprocedimientos_idplanprocedimientos_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planprocesoscalidad_idplanprocesoscalidad_seq (OID = 29232) : 
--
CREATE SEQUENCE public.planprocesoscalidad_idplanprocesoscalidad_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence planrequerimientosmateriaprima_idplanrequerimientosmateriaprima (OID = 29234) : 
--
CREATE SEQUENCE public.planrequerimientosmateriaprima_idplanrequerimientosmateriaprima
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence presupuesto_idpresupuesto_seq (OID = 29236) : 
--
CREATE SEQUENCE public.presupuesto_idpresupuesto_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence prioridad_idprioridad_seq (OID = 29238) : 
--
CREATE SEQUENCE public.prioridad_idprioridad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence privilegio_idprivilegio_seq (OID = 29240) : 
--
CREATE SEQUENCE public.privilegio_idprivilegio_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence procesocalidad_idprocesocalidad_seq (OID = 29242) : 
--
CREATE SEQUENCE public.procesocalidad_idprocesocalidad_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence producto_idproducto_seq (OID = 29244) : 
--
CREATE SEQUENCE public.producto_idproducto_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence productoreal_idproductoreal_seq (OID = 29246) : 
--
CREATE SEQUENCE public.productoreal_idproductoreal_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence proveedor_idproveedor_seq (OID = 29248) : 
--
CREATE SEQUENCE public.proveedor_idproveedor_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence proveedormantenimientomaquina_idproveedormantenimiento_seq (OID = 29250) : 
--
CREATE SEQUENCE public.proveedormantenimientomaquina_idproveedormantenimiento_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence provincia_idprovincia_seq (OID = 29252) : 
--
CREATE SEQUENCE public.provincia_idprovincia_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamocliente_idreclamo_seq (OID = 29254) : 
--
CREATE SEQUENCE public.reclamocliente_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamoempresametalurgica_idreclamo_seq (OID = 29256) : 
--
CREATE SEQUENCE public.reclamoempresametalurgica_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence reclamoproveedor_idreclamo_seq (OID = 29258) : 
--
CREATE SEQUENCE public.reclamoproveedor_idreclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence remito_idremito_seq (OID = 29260) : 
--
CREATE SEQUENCE public.remito_idremito_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence responsable_idresponsable_seq (OID = 29262) : 
--
CREATE SEQUENCE public.responsable_idresponsable_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence rol_idrol_seq (OID = 29264) : 
--
CREATE SEQUENCE public.rol_idrol_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence rotura_idrotura_seq (OID = 29266) : 
--
CREATE SEQUENCE public.rotura_idrotura_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence servicio_idservicio_seq (OID = 29268) : 
--
CREATE SEQUENCE public.servicio_idservicio_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence sesion_idsesion_seq (OID = 29270) : 
--
CREATE SEQUENCE public.sesion_idsesion_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipodocumento_idtipodocumento_seq (OID = 29272) : 
--
CREATE SEQUENCE public.tipodocumento_idtipodocumento_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipoiva_idtipoiva_seq (OID = 29274) : 
--
CREATE SEQUENCE public.tipoiva_idtipoiva_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tipomaquina_idtipomaquina_seq (OID = 29276) : 
--
CREATE SEQUENCE public.tipomaquina_idtipomaquina_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence tiporeclamo_idtiporeclamo_seq (OID = 29278) : 
--
CREATE SEQUENCE public.tiporeclamo_idtiporeclamo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence trabajotercerizado_idtrabajo_seq (OID = 29280) : 
--
CREATE SEQUENCE public.trabajotercerizado_idtrabajo_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence turno_idturno_seq (OID = 29282) : 
--
CREATE SEQUENCE public.turno_idturno_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    NO MINVALUE
    CACHE 1;
--
-- Definition for view viewdetallepedidocotizacion (OID = 29284) : 
--
CREATE VIEW public.viewdetallepedidocotizacion AS
SELECT p.nroproducto, p.nombre, p.descripcion, dp.cantidad,
    p.preciounitario AS precio, p.idproducto, dp.iddetalle, ped.idpedido
FROM producto p, pedido ped, detallepedido dp
WHERE ((dp.producto = p.idproducto) AND (ped.idpedido = dp.idpedido))
ORDER BY p.nombre;

--
-- Definition for view viewpedidoendetalleprocedimientos (OID = 29288) : 
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
-- Definition for sequence unidadmedida_idunidadmedida_seq (OID = 29300) : 
--
CREATE SEQUENCE public.unidadmedida_idunidadmedida_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table unidadmedida (OID = 29302) : 
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
-- Definition for view viewetapadeproduccion (OID = 38722) : 
--
CREATE VIEW public.viewetapadeproduccion AS
SELECT ep.nroetapaproduccion AS numero, ep.nombre, ep.horashombre,
    ep.horasmaquina, ep.duracionestimada, ep.idetapaproduccion AS idetapa
FROM etapadeproduccion ep;

--
-- Definition for sequence detallepiezapresupuesto_iddetalle_seq (OID = 38726) : 
--
CREATE SEQUENCE public.detallepiezapresupuesto_iddetalle_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detallepiezapresupuesto (OID = 38728) : 
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
    preciomateriaprima double precision,
    idproveedor bigint
) WITH OIDS;
ALTER TABLE ONLY public.detalleproductopresupuesto ALTER COLUMN iddetalle SET STATISTICS 0;
--
-- Definition for sequence detallepiezacalidadpresupuesto_iddetalle_seq (OID = 38926) : 
--
CREATE SEQUENCE public.detallepiezacalidadpresupuesto_iddetalle_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detallepiezacalidadpresupuesto (OID = 38928) : 
--
CREATE TABLE public.detallepiezacalidadpresupuesto (
    iddetalle bigint DEFAULT nextval('detallepiezacalidadpresupuesto_iddetalle_seq'::regclass) NOT NULL,
    cantprocesocalidad integer,
    duracionxpieza time without time zone,
    idprocesocalidad bigint,
    iddetalleproductopresupuesto bigint
) WITH OIDS;
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
-- Definition for view viewproveedorxmateriaprima (OID = 60979) : 
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
-- Definition for view viewmateriaprima (OID = 60988) : 
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
-- Definition for view viewproductopresupuesto (OID = 61037) : 
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
-- Definition for sequence calendario_id_seq (OID = 63172) : 
--
CREATE SEQUENCE public.calendario_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table calendario (OID = 63174) : 
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
-- Definition for view viewpedidosnoconfirmados (OID = 63198) : 
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
-- Definition for sequence disponibilidadhoraria_id_seq (OID = 63209) : 
--
CREATE SEQUENCE public.disponibilidadhoraria_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table disponibilidadhoraria (OID = 63211) : 
--
CREATE TABLE public.disponibilidadhoraria (
    id bigint DEFAULT nextval('disponibilidadhoraria_id_seq'::regclass) NOT NULL,
    fecha date,
    tiempodisponible time without time zone,
    idempleado bigint
) WITH OIDS;
--
-- Definition for sequence estadoplanificacionproduccion_id_seq (OID = 63240) : 
--
CREATE SEQUENCE public.estadoplanificacionproduccion_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table estadoplanificacionproduccion (OID = 63242) : 
--
CREATE TABLE public.estadoplanificacionproduccion (
    id bigint DEFAULT nextval('estadoplanificacionproduccion_id_seq'::regclass) NOT NULL,
    nombre character varying,
    descripcion character varying(250)
) WITH OIDS;
--
-- Definition for sequence maquina_idmaquina_seq1 (OID = 63308) : 
--
CREATE SEQUENCE public.maquina_idmaquina_seq1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table maquina (OID = 63310) : 
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
-- Definition for sequence piezareal_idpiezareal_seq1 (OID = 63336) : 
--
CREATE SEQUENCE public.piezareal_idpiezareal_seq1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table piezareal (OID = 63338) : 
--
CREATE TABLE public.piezareal (
    idpiezareal bigint DEFAULT nextval('piezareal_idpiezareal_seq1'::regclass) NOT NULL,
    idpieza bigint NOT NULL,
    estado bigint,
    nropieza integer,
    idcodigobarra bigint
) WITH OIDS;
--
-- Definition for sequence pieza_idpieza_seq1 (OID = 63363) : 
--
CREATE SEQUENCE public.pieza_idpieza_seq1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table pieza (OID = 63365) : 
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
-- Definition for sequence detallempasignada_id_seq (OID = 63386) : 
--
CREATE SEQUENCE public.detallempasignada_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table detallempasignada (OID = 63388) : 
--
CREATE TABLE public.detallempasignada (
    id bigint DEFAULT nextval('detallempasignada_id_seq'::regclass) NOT NULL,
    idmateriaprima bigint,
    cantidadmp integer,
    idplanificacionproduccion bigint
) WITH OIDS;
--
-- Definition for sequence mpasignadaxpiezareal_id_seq (OID = 63404) : 
--
CREATE SEQUENCE public.mpasignadaxpiezareal_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table mpasignadaxpiezareal (OID = 63406) : 
--
CREATE TABLE public.mpasignadaxpiezareal (
    idpiezareal bigint,
    iddetallempasignada bigint,
    id bigint DEFAULT nextval('mpasignadaxpiezareal_id_seq'::regclass) NOT NULL
) WITH OIDS;
--
-- Definition for view viewcantidadmpasiganda (OID = 63426) : 
--
CREATE VIEW public.viewcantidadmpasiganda AS
SELECT ppro.pedido, dmpa.idmateriaprima, sum(dmpa.cantidadmp) AS cantidad
FROM planificacionproduccion ppro, detallempasignada dmpa
WHERE (ppro.idplanificacionproduccion = dmpa.idplanificacionproduccion)
GROUP BY ppro.pedido, dmpa.idmateriaprima;

--
-- Structure for table asistencia (OID = 63462) : 
--
CREATE TABLE public.asistencia (
    empleado bigint NOT NULL,
    fechaingreso date NOT NULL,
    horaingreso time without time zone NOT NULL,
    horaegreso time without time zone,
    observaciones character varying(100)
) WITH OIDS;
--
-- Definition for view viewpedidosconrecasignados (OID = 66356) : 
--
CREATE VIEW public.viewpedidosconrecasignados AS
SELECT pe.nropedido, pe.nropedidocotizacioncliente, cli.razonsocial,
    pri.nombre AS prioridad, pe.fechaentregaestipulada, pe.idpedido,
    cli.idcliente, pri.idprioridad, pe.presupuesto, pp.idestado
FROM pedido pe, planificacionproduccion pp, cliente cli, prioridad pri
WHERE ((((pe.idpedido = pp.pedido) AND (cli.idcliente = pe.cliente)) AND
    (pri.idprioridad = pe.prioridad)) AND (pe.estado = 5));

--
-- Definition for view viewcantidadmpenpedido (OID = 66360) : 
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
-- Definition for view viewmpxpiezapresupuesto (OID = 69275) : 
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
-- Definition for view viewetapasxpiezapresupuesto (OID = 69279) : 
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
-- Definition for view viewprocalidadxpiezapresupesto (OID = 69283) : 
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
-- Definition for view viewpedidosconmpasignada (OID = 69306) : 
--
CREATE VIEW public.viewpedidosconmpasignada AS
SELECT ped.nropedido, planpro.nroplanificacion AS
    nroplanificacionproduccion, planpro.fechacreacion,
    planpro.fechainicioprevista, planpro.fechafinprevista,
    planpro.observaciones, ped.idpedido, planpro.idplanificacionproduccion
FROM pedido ped, planificacionproduccion planpro
WHERE ((ped.idpedido = planpro.pedido) AND (ped.estado = 3));

--
-- Definition for view viewdetalleproducto (OID = 69317) : 
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
-- Definition for view viewpedidosconplanifsinrecursos (OID = 69326) : 
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
-- Definition for view viewpedidosclientesegunestado (OID = 72488) : 
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
-- Data for table public.pedido (OID = 28669) (LIMIT 0,7)
--
INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, plano, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (2, NULL, '2010-10-21', '2010-10-19', NULL, NULL, 6, NULL, 33, '2010-10-27', NULL, '', false, 324242, '2010-10-19', 20, 10, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, plano, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (3, NULL, '2010-10-21', '2010-10-19', NULL, NULL, 6, NULL, 34, '2010-10-27', NULL, '', false, 675765, '2010-10-19', 21, 10, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, plano, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (1, NULL, '2010-10-20', '2010-10-19', NULL, NULL, 4, NULL, 32, '2010-10-20', NULL, '', false, 435353, '2010-10-19', 19, 10, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, plano, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (4, NULL, '2010-10-26', '2010-10-26', NULL, NULL, 4, NULL, 35, '2010-10-30', NULL, '', false, 4363223, '2010-10-26', 22, 10, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, plano, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (6, NULL, '2010-11-05', '2010-10-30', NULL, NULL, 4, NULL, 37, '2010-11-10', NULL, '', false, 234324, '2010-10-30', 24, 10, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, plano, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (5, NULL, '2010-10-31', '2010-10-30', NULL, NULL, 5, NULL, 36, '2010-11-10', NULL, '', false, 3466243, '2010-10-30', 23, 10, NULL, NULL, NULL, 3);

INSERT INTO pedido (nropedido, fechaconfirmacionpedido, fechaentregaestipulada, fechapedidocotizacion, fechacancelacion, fechaentregareal, estado, factura, presupuesto, fecharequeridacotizacion, plano, motivocancelacion, espedidoweb, nropedidocotizacioncliente, fecharegpedcotiz, idpedido, cliente, planprocedimientos, planrequerimientosmateriaprima, planprocesoscalidad, prioridad)
VALUES (7, NULL, '2010-11-07', '2010-11-07', NULL, NULL, 4, NULL, 38, '2010-11-12', NULL, '', false, 34545, '2010-11-07', 25, 10, NULL, NULL, NULL, 3);

--
-- Data for table public.estadopedido (OID = 28677) (LIMIT 0,18)
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
-- Data for table public.planificacionproduccion (OID = 28685) (LIMIT 0,5)
--
INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (14, 1, '2010-10-19', NULL, '2010-11-25', '2010-11-26', 19, 1);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (15, 2, '2010-10-19', NULL, '2010-11-25', '2010-11-26', 20, 1);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (16, 3, '2010-10-19', NULL, '2010-11-25', '2010-12-29', 21, 1);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (18, 4, '2010-11-05', NULL, '2010-11-05', '2010-11-05', 22, 1);

INSERT INTO planificacionproduccion (idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido, idestado)
VALUES (27, 5, '2010-11-05', '', '2010-11-05', '2010-11-06', 23, 1);

--
-- Data for table public.presupuesto (OID = 28689) (LIMIT 0,20)
--
INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (26, '2010-09-14', 484.3, '2010-09-20', 8);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (21, '2010-09-14', 9261.34, '2010-09-20', 3);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (23, '2010-09-13', 9261.34, '2010-09-20', 5);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (27, NULL, 0, NULL, 9);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (25, '2010-10-03', 16226.1, '2010-09-21', 7);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (24, '2010-09-14', 9261.34, '2010-09-21', 6);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (28, '2010-09-15', 1478.62, '2010-09-21', 10);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (29, '2010-09-15', 605, '2010-09-21', 11);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (22, '2010-10-04', 9261.34, '2010-10-11', 4);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (20, '2010-10-04', 4178.13, '2010-10-11', 2);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (30, NULL, 0, NULL, 12);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (19, '2010-10-18', 1093.84, '2010-10-25', 1);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (31, '2010-10-19', 416.24, '2010-10-26', 13);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (32, '2010-10-19', 416.24, '2010-10-26', 14);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (33, '2010-10-19', 416.24, '2010-10-26', 15);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (34, '2010-10-19', 416.24, '2010-10-26', 16);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (35, '2010-10-26', 416.24, '2010-11-02', 17);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (36, '2010-10-31', 2083.62, '2010-11-06', 18);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (37, '2010-10-30', 416.24, '2010-11-06', 19);

INSERT INTO presupuesto (idpresupuesto, fechapresupuesto, montototal, fechavencimiento, nropresupuesto)
VALUES (38, '2010-11-07', 38.72, '2010-11-14', 20);

--
-- Data for table public.cliente (OID = 28708) (LIMIT 0,11)
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

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (2434, 15, 1, 1, false, 1, 'PROFE', 14, '454545', '545454', 'jhgchwc@jdshcd.com', 33, '2010-08-31', NULL, NULL, 1, '545454545');

INSERT INTO cliente (nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit)
VALUES (32544444, 16, 3, 1, false, 1, 'MATERIALFERROVIARIO SRL', 15, '555555', '4535345', 'frewfef@gmail.com', 35, '2010-09-13', NULL, NULL, 1, '2354435234');

--
-- Data for table public.usuario (OID = 28720) (LIMIT 0,2)
--
INSERT INTO usuario (idusuario, usuario, clave)
VALUES (1, 'Nino', 'Nino');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (2, 'admin', 'admin');

--
-- Data for table public.prioridad (OID = 28740) (LIMIT 0,3)
--
INSERT INTO prioridad (idprioridad, nombre, descripcion)
VALUES (1, 'Alta', 'Prioridad Alta');

INSERT INTO prioridad (idprioridad, nombre, descripcion)
VALUES (2, 'Baja', 'Prioridad Baja');

INSERT INTO prioridad (idprioridad, nombre, descripcion)
VALUES (3, 'Normal', 'Prioridad Normal');

--
-- Data for table public.estadocliente (OID = 28744) (LIMIT 0,3)
--
INSERT INTO estadocliente (idestado, nombre, descripcion)
VALUES (1, 'Activo', 'Dado de Alta');

INSERT INTO estadocliente (idestado, nombre, descripcion)
VALUES (2, 'Baja', 'Dado de Baja');

INSERT INTO estadocliente (idestado, nombre, descripcion)
VALUES (3, 'Moroso', 'Cliente Moroso, adeuda facturas');

--
-- Data for table public.responsable (OID = 28748) (LIMIT 0,14)
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

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (14, 'dhwexx', 'xdewxde', '34545', 'xdewxex', 34, 435435, 1, '5454354');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (15, 'grtgrgte', 'tgregreg', '4535435', 'gfregregt', 36, 5435435, 1, '54353');

INSERT INTO responsable (idresponsable, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, fax)
VALUES (17, 'grdge', 'gesgreg', '45435', '45345', 40, 4535, 1, '345345');

--
-- Data for table public.condicioniva (OID = 28752) (LIMIT 0,3)
--
INSERT INTO condicioniva (idcondicioniva, nombre, descripcion)
VALUES (1, 'R.Inscripto', 'Responsable Inscripto');

INSERT INTO condicioniva (idcondicioniva, nombre, descripcion)
VALUES (2, 'Monotributista', 'Monotributista');

INSERT INTO condicioniva (idcondicioniva, nombre, descripcion)
VALUES (3, 'C.Final', 'Consumidor Final');

--
-- Data for table public.domicilio (OID = 28756) (LIMIT 0,34)
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

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (33, 'dsxqwx', 34, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (34, 'ewrferwfew', 43, 0, '', '', 3);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (35, 'gbvgfbdf', 4545, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (36, 'gtregregrg', 5445, 0, '', '', 2);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (39, 'gfsg', 54, 0, '', '', 1);

INSERT INTO domicilio (iddomicilio, calle, numerocalle, piso, depto, torre, barrio)
VALUES (40, 'gesgreg', 45, 0, '', '', 1);

--
-- Data for table public.categoria (OID = 28764) (LIMIT 0,1)
--
INSERT INTO categoria (idcategoria, nombre, descripcion)
VALUES (1, 'tornero', 'torneria en general');

--
-- Data for table public.cargo (OID = 28768) (LIMIT 0,1)
--
INSERT INTO cargo (idcargo, nombre, descripcion)
VALUES (1, 'operario', 'fhkjsdhf');

--
-- Data for table public.turno (OID = 28772) (LIMIT 0,3)
--
INSERT INTO turno (idturno, horainicio, horafin, nombre, descripcion)
VALUES (1, '00:08:00', '00:12:00', 'MAÑANA', NULL);

INSERT INTO turno (idturno, horainicio, horafin, nombre, descripcion)
VALUES (2, '00:13:00', '00:17:00', 'TARDE', NULL);

INSERT INTO turno (idturno, horainicio, horafin, nombre, descripcion)
VALUES (3, '00:18:00', '00:22:00', 'NOCHE', NULL);

--
-- Data for table public.marca (OID = 28776) (LIMIT 0,1)
--
INSERT INTO marca (idmarca, nombre, descripcion)
VALUES (1, 'Tornomax', 'asdasdas');

--
-- Data for table public.estadomaquina (OID = 28780) (LIMIT 0,1)
--
INSERT INTO estadomaquina (idestado, nombre, descripcion)
VALUES (1, 'Disponible', 'mauqina disponible');

--
-- Data for table public.tipomaquina (OID = 28784) (LIMIT 0,1)
--
INSERT INTO tipomaquina (idtipomaquina, nombre, descripcion)
VALUES (1, 'torno', 'dsadasd');

--
-- Data for table public.tipodocumento (OID = 28796) (LIMIT 0,3)
--
INSERT INTO tipodocumento (idtipodocumento, tipo, nombre)
VALUES (1, 'DNI', 'Documento Nacional de Identidad');

INSERT INTO tipodocumento (idtipodocumento, tipo, nombre)
VALUES (2, 'LE', 'Libreta de Enrolamiento');

INSERT INTO tipodocumento (idtipodocumento, tipo, nombre)
VALUES (3, 'LC', 'Libreta Cívica');

--
-- Data for table public.codigodebarra (OID = 28800) (LIMIT 0,16)
--
INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (1, 'dsfvdsv', '1234');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (2, 'Mango', 'PIE3');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (3, 'Mango', 'PIE4');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (4, 'Mango', 'PIE5');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (5, 'Mango', 'PIE6');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (6, 'rerererererere', 'PIE7');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (7, 'rerererererere', 'PIE8');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (9, 'wwwwwwwwwwww', 'PIE10');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (10, 'wwwwwwwwwwww', 'PIE11');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (35, 'rerererererere', 'PIE36');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (36, 'rerererererere', 'PIE37');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (37, 'rerererererere', 'PIE38');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (38, 'rerererererere', 'PIE39');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (39, 'rerererererere', 'PIE40');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (40, 'wwwwwwwwwwww', 'PIE41');

INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (41, 'wwwwwwwwwwww', 'PIE42');

--
-- Data for table public.estadopiezareal (OID = 28804) (LIMIT 0,1)
--
INSERT INTO estadopiezareal (idestado, nombre, descripcion)
VALUES (1, 'INICIADO', NULL);

--
-- Data for table public.estadoejecplanifpedido (OID = 28808) (LIMIT 0,1)
--
INSERT INTO estadoejecplanifpedido (idestado, nombre, descripcion)
VALUES (1, 'INICIADO', 'Se ha lanzado la producción de una planificación');

--
-- Data for table public.rol (OID = 28816) (LIMIT 0,5)
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
-- Data for table public.accioncalidad (OID = 28824) (LIMIT 0,3)
--
INSERT INTO accioncalidad (idaccioncalidad, nombre, descripcion)
VALUES (1, 'Medir', 'Realizar una medición de las dimensiones del objeto');

INSERT INTO accioncalidad (idaccioncalidad, nombre, descripcion)
VALUES (2, 'Contar', 'Realizar el conteo de diferentes aspectos de un objeto');

INSERT INTO accioncalidad (idaccioncalidad, nombre, descripcion)
VALUES (3, 'Observar', 'Realizar una observación general del objeto');

--
-- Data for table public.empleado (OID = 28828) (LIMIT 0,2)
--
INSERT INTO empleado (idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo)
VALUES (1, 234234, '1999-10-10', 'Mariana', 'Enrico', '23894623', 'marichapita@decoca.com.ar', 1, 234234432, 1, 1, 1, NULL, NULL, 1);

INSERT INTO empleado (idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo)
VALUES (2, 234111, '1999-10-10', 'Carlos', 'Castro', '23434442', 'carlos@gmail.com', 1, 23443235, 1, 1, 1, NULL, NULL, 1);

--
-- Data for table public.proveedor (OID = 28832) (LIMIT 0,3)
--
INSERT INTO proveedor (idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit)
VALUES (1, 1, 'Techin', 1, '345345345', '3243534534', 'fsdfgsdfgdsfg', 1, '1990-10-10', NULL, '32-32323232-3', 1, '21-21212121-2');

INSERT INTO proveedor (idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit)
VALUES (2, 2, 'MetalArg', 2, '4353545', '543543545', 'gfdsgdfgdg', 2, '2000-10-10', NULL, '32-33333333-3', 1, '32-33333333-3');

INSERT INTO proveedor (idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit)
VALUES (3, 34545, 'fgdsfgdsfg', 17, '345345', '435345', '34534', 39, '2010-09-14', NULL, NULL, 1, '453543');

--
-- Data for table public.materiaprima (OID = 28844) (LIMIT 0,6)
--
INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (5, 'Cobre', NULL, NULL, 1, 100, 'jhtfhnfchn', 2, 2, 50.000, 50.000, 50.000, '6543', 25, NULL);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (7, 'mp1', '2010-09-14', NULL, 1, 100, '', 2, 2, 100.000, 100.000, 100.000, '0', NULL, 1);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (8, 'MateriaPrima2', '2010-09-14', NULL, 1, 100, '', 5, 2, 100.000, 100.000, 50.000, '0', NULL, 2);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (9, 'MatPrima3', '2010-11-01', NULL, 1, 100, 'fwefwefw fwe fwe fwf ewf  e', 4, 2, 100.000, 60.000, 60.000, '0', NULL, 3);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (1, 'Acero', NULL, NULL, 4, 86, NULL, 2, 2, 33.420, 50.000, 50.000, '0', 12, 0);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto, precio, nromateriaprima)
VALUES (4, 'PVC', NULL, NULL, 1, 99, 'jhtfhnfchn', 2, 2, 100.000, 100.000, 100.000, '6543', 19, 0);

--
-- Data for table public.matriz (OID = 28848) (LIMIT 0,3)
--
INSERT INTO matriz (idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial)
VALUES (1, 123, 'Matriz1', 'Matriz	', NULL, NULL, 1, 4);

INSERT INTO matriz (idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial)
VALUES (3, 345325, 'MAT-HIERRO', 'dasdasdadsad', NULL, NULL, 7, 5);

INSERT INTO matriz (idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial)
VALUES (4, 5453, 'Matriz2', 'ewfwerfw', NULL, NULL, 1, 2);

--
-- Data for table public.producto (OID = 28856) (LIMIT 0,9)
--
INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (3, 345, 'ggggggggg', 3453, 'gggggggggg');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (4, 123456, 'vvvvvvvvv', 7654, 'gggggggggg');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (5, 8765, 'Instrumentos', 894, 'BBBBBBBBBBBB');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (6, 765334, 'Mouse', 10, 'MMMMMMMMMMMMMMM
MMM
MM
MMMMMMMMMMMMM');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (7, 768768, 'prueba1', 32, '');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (8, 768769, 'producto1', 400.25, '');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (9, 768770, 'Pro Con Tuer Cos', 344, '');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (10, 768771, 'pro1', 1222, '');

INSERT INTO producto (idproducto, nroproducto, nombre, preciounitario, descripcion)
VALUES (11, 768772, 'Producto2', 500, '');

--
-- Data for table public.etapadeproduccion (OID = 28876) (LIMIT 0,5)
--
INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, maquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (2, 3, 'ractificacion', '00:00:20', '00:00:20', NULL, '00:00:20', '2010-12-12', NULL);

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, maquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (3, 2314234, 'Torneado Circular', '00:02:00', '00:02:00', NULL, '00:02:00', '2010-10-10', 2);

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, maquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (4, 23453, 'Etapa2', NULL, '00:20:00', NULL, '00:20:00', '2010-10-10', 2);

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, maquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (5, 2342, 'Etapa3', '00:20:00', '00:20:00', NULL, '00:20:00', '2010-10-10', 2);

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, maquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (1, 2, 'soldar', '00:00:30', '00:00:30', 1, '00:00:30', '2010-08-16', 2);

--
-- Data for table public.ejecucionplanificacionproduccion (OID = 28884) (LIMIT 0,2)
--
INSERT INTO ejecucionplanificacionproduccion (idejecucion, idplanificacionproduccion, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacion)
VALUES (6, 15, '2010-10-19', NULL, '06:12:21.663', NULL, 1, NULL);

INSERT INTO ejecucionplanificacionproduccion (idejecucion, idplanificacionproduccion, fechainicio, fechafin, horainicio, horafin, estado, nroejecucionplanificacion)
VALUES (7, 16, '2010-10-19', NULL, '11:49:28.174', NULL, 1, NULL);

--
-- Data for table public.procesocalidad (OID = 28888) (LIMIT 0,2)
--
INSERT INTO procesocalidad (idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad)
VALUES (2, 'Rectificación', 2, 'Null', NULL, 'Determinar si el nivel de rectificación es el adecuado', '00:10:00', '2010-08-06', 'Lupa', 3);

INSERT INTO procesocalidad (idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad)
VALUES (1, 'Detectar Spatter', 1, NULL, '3', NULL, '00:05:00', '2010-09-06', NULL, 2);

--
-- Data for table public.detalleplanificacionproduccion (OID = 28927) (LIMIT 0,7)
--
INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto)
VALUES (32, 27, NULL, 1, 15, 1, '2010-11-06', '16:50:20', '2010-11-06', '17:12:20', 1, 2, 11);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto)
VALUES (33, 27, NULL, 1, 14, 2, '2010-11-06', '11:40:20', '2010-11-06', '16:35:20', 1, 1, 11);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto)
VALUES (34, 27, NULL, 1, 14, 1, '2010-11-05', '15:30:20', '2010-11-06', '11:25:20', 1, 1, 10);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto)
VALUES (6, 16, NULL, 1, 6, 1, '2010-11-25', '15:00:00', '2010-12-22', '10:00:00', 2, NULL, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto)
VALUES (5, 15, NULL, 1, 6, 1, '2010-11-01', '13:00:00', '2010-11-01', '19:00:00', 2, NULL, NULL);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto)
VALUES (9, 18, NULL, 1, 6, 2, '2010-11-05', '08:00:55', '2010-11-05', '08:10:55', 1, 1, 9);

INSERT INTO detalleplanificacionproduccion (id, idplanificacionproduccion, iddetalleejecucionplanificacion, idetapaproduccion, idpieza, idempleado, fechainicio, horainicio, fechafin, horafin, idmaquina, orden, idproducto)
VALUES (7, 16, NULL, 2, 6, 1, '2010-12-22', '10:00:00', '2010-12-23', '15:00:00', 1, 1, NULL);

--
-- Data for table public.detalleproducto (OID = 28931) (LIMIT 0,14)
--
INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (4, 3, 1, '', 8);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (5, 4, 1, '', 8);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (6, 4, 1, 'hgfhjgf', 6);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (8, 5, 1, 'eje central', 9);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (9, 5, 3, 'fdsef', 8);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (10, 5, 5, 'fffffff', 6);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (12, 6, 1, 'desfsrf', 6);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (13, 6, 1, 'dasdsd', 8);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (14, 7, 1, '', 8);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (15, 8, 1, '', 13);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (16, 9, 1, '', 6);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (17, 10, 2, '', 14);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (18, 11, 2, 'PiezaPrueba', 15);

INSERT INTO detalleproducto (iddetalle, idproducto, cantidadpiezas, descripcion, pieza)
VALUES (19, 11, 1, '', 14);

--
-- Data for table public.detallepedido (OID = 28935) (LIMIT 0,8)
--
INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (25, 19, 344, 1, 9);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (26, 20, 344, 1, 9);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (27, 21, 344, 1, 9);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (28, 22, 344, 1, 9);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (29, 23, 1222, 1, 10);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (30, 23, 500, 1, 11);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (31, 24, 344, 1, 9);

INSERT INTO detallepedido (iddetalle, idpedido, precio, cantidad, producto)
VALUES (32, 25, 32, 1, 7);

--
-- Data for table public.proveedorxmateriaprima (OID = 28983) (LIMIT 0,9)
--
INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (1, 1, 50);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (2, 1, 45);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (1, 4, 80);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (2, 4, 75);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (1, 5, 40);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (2, 7, 40);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (1, 7, 43);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (1, 8, 59);

INSERT INTO proveedorxmateriaprima (idproveedor, idmateriaprima, precio)
VALUES (2, 8, 60);

--
-- Data for table public.piezaxetapadeproduccion (OID = 28992) (LIMIT 0,1)
--
INSERT INTO piezaxetapadeproduccion (idpieza, idetapaproduccion, duracion, descripcion)
VALUES (6, 1, '04:00:00', 'Seteado a mano');

--
-- Data for table public.provincia (OID = 28998) (LIMIT 0,7)
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
-- Data for table public.localidad (OID = 29002) (LIMIT 0,10)
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
-- Data for table public.barrio (OID = 29006) (LIMIT 0,3)
--
INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (1, 'Nva. Córdoba', 5000, 1);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (2, 'Alta Córdoba', 5000, 1);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (3, 'Latinoamérica', 5220, 2);

--
-- Data for table public.usuarioxrol (OID = 29010) (LIMIT 0,2)
--
INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (2, 1);

INSERT INTO usuarioxrol (idrol, idusuario)
VALUES (1, 2);

--
-- Data for table public.detallepresupuesto (OID = 29072) (LIMIT 0,22)
--
INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (16, 19, 13, 5, 1, 894);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (17, 19, 14, 6, 1, 10);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (18, 20, 15, 3, 1, 3453);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (19, 21, 16, 4, 1, 7654);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (20, 22, 17, 4, 1, 7654);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (21, 23, 18, 4, 1, 7654);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (22, 24, 19, 4, 1, 7654);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (23, 25, 20, 5, 15, 894);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (24, 26, 21, 8, 1, 400.25);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (25, 27, 16, 4, 1, 7654);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (26, 28, 22, 10, 1, 1222);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (27, 29, 23, 11, 1, 500);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (28, 30, 21, 8, 1, 400.25);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (29, 31, 24, 9, 1, 344);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (30, 32, 25, 9, 1, 344);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (31, 33, 26, 9, 1, 344);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (32, 34, 27, 9, 1, 344);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (33, 35, 28, 9, 1, 344);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (34, 36, 29, 10, 1, 1222);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (35, 36, 30, 11, 1, 500);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (36, 37, 31, 9, 1, 344);

INSERT INTO detallepresupuesto (iddetalle, idpresupuesto, iddetallepedido, idproducto, cantidad, precio)
VALUES (37, 38, 32, 7, 1, 32);

--
-- Data for table public.tipomaterial (OID = 29080) (LIMIT 0,3)
--
INSERT INTO tipomaterial (idtipomaterial, nombre, descripcion)
VALUES (2, 'otro mas', 'algun ootr');

INSERT INTO tipomaterial (idtipomaterial, nombre, descripcion)
VALUES (4, 'Chapa', 'Chapa de acero inoxidable');

INSERT INTO tipomaterial (idtipomaterial, nombre, descripcion)
VALUES (5, 'Hierro', 'dsadasdasd');

--
-- Data for table public.unidadmedida (OID = 29302) (LIMIT 0,4)
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
-- Data for table public.detallepiezapresupuesto (OID = 38728) (LIMIT 0,49)
--
INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:00:00', 14, 1, 16);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:00:00', 15, 2, 16);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:10:00', 16, 1, 17);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:20:00', 17, 2, 17);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:05:00', 18, 1, 18);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('04:00:00', 19, 2, 18);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:15:00', 20, 1, 19);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:25:00', 21, 2, 19);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:55:45', 22, 1, 20);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('03:20:00', 23, 2, 20);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:59:22', 24, 1, 21);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:34:00', 25, 1, 22);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('04:30:00', 26, 2, 22);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:59:00', 27, 1, 23);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:23:00', 28, 2, 23);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:12:00', 29, 1, 24);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:09:00', 30, 2, 24);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:09:09', 31, 1, 25);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:18:18', 32, 2, 25);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:32:33', 33, 1, 26);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:33:00', 34, 2, 26);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:02:59', 35, 1, 27);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:45:45', 36, 2, 27);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:45:00', 37, 1, 29);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:31:01', 38, 1, 30);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:07:19', 39, 2, 31);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('02:27:01', 40, 2, 32);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:06:00', 41, 2, 33);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('07:06:40', 42, 2, 34);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:10:58', 43, 1, 35);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:07:19', 44, 2, 35);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('03:40:32', 45, 1, 36);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('02:27:01', 46, 2, 36);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('09:36:00', 47, 3, 37);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('09:36:00', 48, 2, 38);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('05:33:20', 49, 2, 39);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('10:40:00', 50, 1, 40);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:10:58', 51, 1, 41);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:10:58', 52, 1, 42);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:10:58', 53, 1, 43);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:10:58', 54, 1, 44);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:10:58', 55, 1, 45);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('04:55:00', 56, 1, 46);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('04:55:00', 57, 1, 47);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:22:33', 58, 1, 48);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:10:58', 59, 1, 49);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:07:19', 60, 2, 49);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('00:43:00', 61, 3, 49);

INSERT INTO detallepiezapresupuesto (duracionpiezaxetapa, iddetalle, idetapa, iddetalleproductopresupuesto)
VALUES ('02:27:01', 62, 2, 50);

--
-- Data for table public.detalleproductopresupuesto (OID = 38742) (LIMIT 0,34)
--
INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (25, 20, 8, 4, 1, 1, 75, 2);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (24, 20, 6, 1, 1, 1, 50, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (21, 18, 8, 4, 1, 1, 75, 2);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (40, 28, 13, 1, -1, 1, 0, 0);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (16, 16, 6, 1, 1, 1, 45, 2);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (17, 16, 8, 4, 1, 2, 75, 2);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (18, 16, 9, 5, 1, 1, 40, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (19, 17, 6, 1, 1, 1, 45, 2);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (20, 17, 8, 4, 1, 1, 75, 2);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (41, 29, 6, 1, 1, 1, 45, 2);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (42, 30, 6, 1, 1, 1, 45, 2);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (34, 24, 13, 1, -1, 1, 0, NULL);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (35, 25, 6, 1, 1, 1, 0, NULL);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (36, 25, 8, 5, 1, 1, 0, NULL);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (43, 31, 6, 1, 1, 1, 45, 2);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (22, 19, 6, 1, 1, 1, 0, NULL);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (23, 19, 8, 4, 1, 1, 0, NULL);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (26, 21, 6, 1, 1, 1, 0, NULL);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (27, 21, 8, 4, 1, 1, 0, NULL);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (31, 23, 6, 1, 1, 5, 0, NULL);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (32, 23, 8, 4, 1, 3, 0, NULL);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (33, 23, 9, 5, 1, 1, 0, NULL);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (30, 22, 8, 4, 1, 1, 45, 2);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (44, 32, 6, 1, 1, 1, 45, 2);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (45, 33, 6, 1, 1, 1, 45, 2);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (46, 34, 14, 1, 1, 2, 45, 2);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (48, 35, 15, 4, 1, 2, 75, 2);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (29, 22, 6, 1, 1, 1, 75, 2);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (47, 35, 14, 1, 1, 1, 45, 2);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (37, 26, 14, 7, 1, 2, 40, 2);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (39, 27, 15, 8, 1, 2, 59, 1);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (38, 27, 14, 7, 1, 1, 40, 2);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (49, 36, 6, 1, 1, 1, 45, 2);

INSERT INTO detalleproductopresupuesto (iddetalle, iddetallepresupuesto, idpieza, idmateriaprima, cantmateriaprima, cantpiezas, preciomateriaprima, idproveedor)
VALUES (50, 37, 8, 4, 1, 1, 75, 2);

--
-- Data for table public.detallepiezacalidadpresupuesto (OID = 38928) (LIMIT 0,44)
--
INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (1, 1, '00:00:00', 2, 29);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (2, 1, '00:00:00', 1, 29);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (3, 1, '00:00:00', 1, 30);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (4, 1, '00:10:00', 2, 29);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (5, 1, '00:05:00', 1, 29);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (6, 1, '00:10:00', 2, 30);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (7, 1, '00:05:00', 1, 30);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (8, 1, '00:10:00', 2, 30);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (9, 1, '00:05:00', 1, 30);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (10, 1, '00:05:00', 1, 21);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (11, 2, '00:10:00', 2, 31);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (12, 5, '00:05:00', 1, 32);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (13, 1, '00:10:00', 2, 33);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (14, 1, '00:10:00', 2, 16);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (15, 3, '00:05:00', 1, 17);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (16, 1, '00:10:00', 2, 17);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (17, 1, '00:05:00', 1, 18);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (18, 1, '00:05:00', 1, 19);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (19, 1, '00:05:00', 1, 20);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (20, 1, '00:05:00', 1, 22);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (21, 1, '00:05:00', 1, 23);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (22, 1, '00:05:00', 1, 16);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (23, 1, '00:05:00', 1, 17);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (24, 1, '00:05:00', 1, 18);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (25, 1, '00:10:00', 2, 19);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (26, 1, '00:10:00', 2, 20);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (27, 1, '00:05:00', 1, 24);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (28, 1, '00:05:00', 1, 25);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (29, 1, '00:05:00', 1, 26);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (30, 1, '00:05:00', 1, 27);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (31, 1, '00:10:00', 2, 34);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (32, 1, '00:10:00', 2, 37);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (33, 1, '00:05:00', 1, 38);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (34, 1, '00:05:00', 1, 39);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (35, 1, '00:10:00', 2, 41);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (36, 1, '00:10:00', 2, 42);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (37, 1, '00:10:00', 2, 43);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (38, 1, '00:10:00', 2, 44);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (39, 1, '00:10:00', 2, 45);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (40, 1, '00:10:00', 2, 46);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (41, 1, '00:10:00', 2, 47);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (42, 1, '00:10:00', 2, 48);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (43, 1, '00:10:00', 2, 49);

INSERT INTO detallepiezacalidadpresupuesto (iddetalle, cantprocesocalidad, duracionxpieza, idprocesocalidad, iddetalleproductopresupuesto)
VALUES (44, 1, '00:10:00', 2, 50);

--
-- Data for table public.calendario (OID = 63174) (LIMIT 0,7)
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
-- Data for table public.disponibilidadhoraria (OID = 63211) (LIMIT 0,6)
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
-- Data for table public.estadoplanificacionproduccion (OID = 63242) (LIMIT 0,3)
--
INSERT INTO estadoplanificacionproduccion (id, nombre, descripcion)
VALUES (1, 'REC-ASIG', 'Recusos Asignados');

INSERT INTO estadoplanificacionproduccion (id, nombre, descripcion)
VALUES (2, 'MP-ASIG', 'Materia Prima Asignada');

INSERT INTO estadoplanificacionproduccion (id, nombre, descripcion)
VALUES (3, 'GENERADA', 'Estado inicial de la planificación');

--
-- Data for table public.maquina (OID = 63310) (LIMIT 0,2)
--
INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (1, 'MAQUINA1', 1, 'fefesrfresf', 1, 1, '2010-10-10', NULL, '00:03:00', 1);

INSERT INTO maquina (idmaquina, nombre, marca, descripcion, estado, tipomaquina, fechaalta, fechabaja, tiempocapacidadproduccion, idunidadmedida)
VALUES (2, 'TORNO-1', 1, 'muy buena', 1, 1, '2010-10-19', NULL, '00:02:00', 1);

--
-- Data for table public.piezareal (OID = 63338) (LIMIT 0,15)
--
INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (3, 6, 1, 6, 2);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (4, 6, 1, 6, 3);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (5, 6, 1, 6, 4);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (6, 6, 1, 6, 5);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (7, 14, 1, 14, 6);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (8, 14, 1, 14, 7);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (10, 15, 1, 15, 9);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (11, 15, 1, 15, 10);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (36, 14, 1, 14, 35);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (37, 14, 1, 14, 36);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (38, 14, 1, 14, 37);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (39, 14, 1, 14, 38);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (40, 14, 1, 14, 39);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (41, 15, 1, 15, 40);

INSERT INTO piezareal (idpiezareal, idpieza, estado, nropieza, idcodigobarra)
VALUES (42, 15, 1, 15, 41);

--
-- Data for table public.pieza (OID = 63365) (LIMIT 0,11)
--
INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (11, 'nino', 2, 4, 1, 3.330, 4.000, 3.300, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (6, 'Mango', 2, 1, NULL, 3.560, 5.000, 1.234, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (8, 'volante', 2, 4, 1, 5.550, 7.540, 10.540, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (9, 'Eje', 4, 5, 1, 4.500, 2.000, 2.000, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (10, 'Panel', 4, 5, NULL, 10.000, 3.200, 3.200, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (12, 'prueba1', 4, 1, 1, 10.000, 10.000, 10.000, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (13, 'ewrgfewrgeg', 4, 1, NULL, 5.000, 5.000, 5.000, 1);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (14, 'rerererererere', 2, 1, NULL, 10.000, 5.900, 10.000, 1);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (17, 'ttttttttttttttt', 4, 4, NULL, 3.560, 3.560, 3.560, 2);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (15, 'wwwwwwwwwwww', 4, 4, NULL, 3.560, 3.560, 3.560, 2);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo, unidadmedida)
VALUES (16, 'tttttttttttt', 2, 4, NULL, 3.560, 3.560, 3.560, 2);

--
-- Data for table public.detallempasignada (OID = 63388) (LIMIT 0,5)
--
INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (2, 1, 1, 14);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (3, 1, 1, 15);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (4, 1, 1, 16);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (7, 1, 2, 27);

INSERT INTO detallempasignada (id, idmateriaprima, cantidadmp, idplanificacionproduccion)
VALUES (8, 4, 1, 27);

--
-- Data for table public.mpasignadaxpiezareal (OID = 63406) (LIMIT 0,8)
--
INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (4, 2, 2);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (5, 3, 3);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (6, 4, 4);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (38, 7, 24);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (39, 7, 25);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (40, 7, 26);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (41, 8, 27);

INSERT INTO mpasignadaxpiezareal (idpiezareal, iddetallempasignada, id)
VALUES (42, 8, 28);

--
-- Data for table public.asistencia (OID = 63462) (LIMIT 0,1)
--
INSERT INTO asistencia (empleado, fechaingreso, horaingreso, horaegreso, observaciones)
VALUES (1, '2010-10-19', '00:00:00', '00:00:00', NULL);

--
-- Definition for index pedido_nropedido_key (OID = 29306) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_nropedido_key UNIQUE (nropedido);
--
-- Definition for index ejecucionplanificacioncalidad_idplanificacioncalidad_key (OID = 29312) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_idplanificacioncalidad_key UNIQUE (idplanificacioncalidad);
--
-- Definition for index ejecucionprocesocalidad_idprocesocalidad_key (OID = 29314) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_idprocesocalidad_key UNIQUE (idprocesocalidad);
--
-- Definition for index proveedorxmateriaprima_idx (OID = 29326) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_idx PRIMARY KEY (idmateriaprima, idproveedor);
--
-- Definition for index maquinaxejecucionetapaproduccion_idx (OID = 29328) : 
--
ALTER TABLE ONLY maquinaxejecucionetapaproduccion
    ADD CONSTRAINT maquinaxejecucionetapaproduccion_idx PRIMARY KEY (idejecucionetapaproduccion, idetapaproduccion, idmaquina);
--
-- Definition for index maquinaxprocesocalidad_idx (OID = 29330) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT maquinaxprocesocalidad_idx PRIMARY KEY (idprocesocalidad, idmaquina);
--
-- Definition for index piezaxetapadeproduccion_idx (OID = 29332) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT piezaxetapadeproduccion_idx PRIMARY KEY (idpieza, idetapaproduccion);
--
-- Definition for index empleadoxturno_idx (OID = 29334) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_idx PRIMARY KEY (idempleado, idturno);
--
-- Definition for index usuarioxrol_pkey (OID = 29336) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_pkey PRIMARY KEY (idrol, idusuario);
--
-- Definition for index rolxprivilegio_pkey (OID = 29338) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_pkey PRIMARY KEY (idrol, idprivilegio);
--
-- Definition for index prueba_pkey (OID = 29340) : 
--
ALTER TABLE ONLY prueba
    ADD CONSTRAINT prueba_pkey PRIMARY KEY (id);
--
-- Definition for index usuario_pkey (OID = 29342) : 
--
ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (idusuario);
--
-- Definition for index factura_fk2 (OID = 29344) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk2 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index comprobantepago_fk1 (OID = 29349) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk1 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index cliente_fk2 (OID = 29354) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk2 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index empleado_fk3 (OID = 29359) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk3 FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index sesion_fk (OID = 29364) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT sesion_fk FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index usuarioxrol_fk1 (OID = 29369) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_fk1 FOREIGN KEY (idusuario) REFERENCES usuario(idusuario);
--
-- Definition for index tipomaterial_pkey (OID = 29374) : 
--
ALTER TABLE ONLY tipomaterial
    ADD CONSTRAINT tipomaterial_pkey PRIMARY KEY (idtipomaterial);
--
-- Definition for index accioncalidad_pkey (OID = 29376) : 
--
ALTER TABLE ONLY accioncalidad
    ADD CONSTRAINT accioncalidad_pkey PRIMARY KEY (idaccioncalidad);
--
-- Definition for index procesocalidad_fk (OID = 29378) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT procesocalidad_fk FOREIGN KEY (accioncalidad) REFERENCES accioncalidad(idaccioncalidad);
--
-- Definition for index barrio_pkey (OID = 29383) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT barrio_pkey PRIMARY KEY (idbarrio);
--
-- Definition for index domicilio_fk (OID = 29385) : 
--
ALTER TABLE ONLY domicilio
    ADD CONSTRAINT domicilio_fk FOREIGN KEY (barrio) REFERENCES barrio(idbarrio);
--
-- Definition for index cargo_pkey (OID = 29390) : 
--
ALTER TABLE ONLY cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (idcargo);
--
-- Definition for index empleado_fk4 (OID = 29392) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk4 FOREIGN KEY (cargo) REFERENCES cargo(idcargo);
--
-- Definition for index categoria_pkey (OID = 29397) : 
--
ALTER TABLE ONLY categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (idcategoria);
--
-- Definition for index empleado_fk2 (OID = 29399) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk2 FOREIGN KEY (categoria) REFERENCES categoria(idcategoria);
--
-- Definition for index cliente_pkey (OID = 29404) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (idcliente);
--
-- Definition for index pedido_fk4 (OID = 29406) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk4 FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index reclamocliente_fk1 (OID = 29411) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_fk1 FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index codigodebarra_pkey (OID = 29416) : 
--
ALTER TABLE ONLY codigodebarra
    ADD CONSTRAINT codigodebarra_pkey PRIMARY KEY (idcodigo);
--
-- Definition for index materiaprima_fk1 (OID = 29418) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk1 FOREIGN KEY (codbarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index productoreal_fk2 (OID = 29423) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk2 FOREIGN KEY (codigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index compra_pkey (OID = 29428) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_pkey PRIMARY KEY (idcompra);
--
-- Definition for index detallecompra_fk (OID = 29430) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk FOREIGN KEY (idcompra) REFERENCES compra(idcompra);
--
-- Definition for index reclamoproveedor_fk1 (OID = 29435) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_fk1 FOREIGN KEY (compra) REFERENCES compra(idcompra);
--
-- Definition for index comprobantepago_pkey (OID = 29440) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_pkey PRIMARY KEY (idcomprobantepago);
--
-- Definition for index condicioniva_pkey (OID = 29442) : 
--
ALTER TABLE ONLY condicioniva
    ADD CONSTRAINT condicioniva_pkey PRIMARY KEY (idcondicioniva);
--
-- Definition for index cliente_fk5 (OID = 29444) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk5 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index empresametalurgica_fk2 (OID = 29449) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk2 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index proveedormantenimientomaquina_fk2 (OID = 29454) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk2 FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index proveedor_fk2 (OID = 29459) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk2 FOREIGN KEY (condicion) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index detallecompra_idx (OID = 29468) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_idx PRIMARY KEY (iddetalle, idcompra);
--
-- Definition for index detallereclamoproveedor_fk1 (OID = 29470) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_fk1 FOREIGN KEY (iddetallecompra, idcompra) REFERENCES detallecompra(iddetalle, idcompra);
--
-- Definition for index detallecompra_iddetalle_key (OID = 29475) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleejecucionplanificacioncalidad_idx (OID = 29486) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_idx PRIMARY KEY (iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad);
--
-- Definition for index detalleplanificacioncalidad_fk1 (OID = 29488) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk1 FOREIGN KEY (idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, idejecucionplanificacioncalidad) REFERENCES detalleejecucionplanificacioncalidad(iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad);
--
-- Definition for index detalleejecucionplanificacioncalidad_iddetalle_key (OID = 29493) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallefactura_idx (OID = 29495) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_idx PRIMARY KEY (iddetalle, idfactura);
--
-- Definition for index detallefactura_iddetalle_key (OID = 29497) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallemantenimientocorrectivo_pkey (OID = 29499) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_pkey PRIMARY KEY (idmantenimientocorrectivo, iddetalle);
--
-- Definition for index detallemantenimientocorrectivo_idmantenimientocorrectivo_key (OID = 29501) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_idmantenimientocorrectivo_key UNIQUE (idmantenimientocorrectivo);
--
-- Definition for index detallemantenimientopreventivo_pkey (OID = 29503) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_pkey PRIMARY KEY (idmantenimientopreventivo, iddetalle);
--
-- Definition for index detallemantenimientopreventivo_idmantenimientopreventivo_key (OID = 29505) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_idmantenimientopreventivo_key UNIQUE (idmantenimientopreventivo);
--
-- Definition for index detalleplanificacioncalidad_idx (OID = 29521) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_idx PRIMARY KEY (iddetalle, idplanificacioncalidad);
--
-- Definition for index detalleplanificacioncalidad_iddetalle_key (OID = 29523) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleplanprocedimientos_idx (OID = 29525) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_idx PRIMARY KEY (iddetalle, idplanpprocedimientos);
--
-- Definition for index detalleplanprocedimientos_iddetalle_key (OID = 29527) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleplanprocesoscalidad_idx (OID = 29529) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_idx PRIMARY KEY (iddetalle, idplanprocesoscalidad);
--
-- Definition for index detalleplanprocesoscalidad_iddetalle_key (OID = 29531) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleproductoreal_idx (OID = 29537) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_idx PRIMARY KEY (iddetalle, idproductoreal);
--
-- Definition for index detalleproductoreal_iddetalle_key (OID = 29539) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamocliente_idx (OID = 29541) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamocliente_iddetalle_key (OID = 29543) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamoempresametalurgica_idx (OID = 29545) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamoempresametalurgica_iddetalle_key (OID = 29547) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallereclamoproveedor_idx (OID = 29549) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_idx PRIMARY KEY (iddetalle, idreclamo);
--
-- Definition for index detallereclamoproveedor_iddetalle_key (OID = 29551) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalleremito_idx (OID = 29553) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_idx PRIMARY KEY (iddetalle, idremito);
--
-- Definition for index detalleremito_iddetalle_key (OID = 29555) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detallerequerimientosmateriaprima_idx (OID = 29557) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_idx PRIMARY KEY (iddetalle, idplanrequerimientosmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_iddetalle_key (OID = 29559) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index detalletrabajotercerizado_idx (OID = 29561) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_idx PRIMARY KEY (iddetalle, idtrabajotercerizado);
--
-- Definition for index detalletrabajotercerizado_iddetalle_key (OID = 29563) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_iddetalle_key UNIQUE (iddetalle);
--
-- Definition for index domicilio_pkey (OID = 29565) : 
--
ALTER TABLE ONLY domicilio
    ADD CONSTRAINT domicilio_pkey PRIMARY KEY (iddomicilio);
--
-- Definition for index cliente_fk4 (OID = 29567) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk4 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index empresametalurgica_fk1 (OID = 29572) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index responsable_fk (OID = 29577) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_fk FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index proveedormantenimientomaquina_fk1 (OID = 29582) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index empleado_fk (OID = 29587) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index proveedor_fk1 (OID = 29592) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk1 FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index ejecucionetapaproduccion_nroejecucion_key1 (OID = 29611) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_nroejecucion_key1 UNIQUE (nroejecucion);
--
-- Definition for index ejecucionplanificacioncalidad_idejecucion_key (OID = 29613) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_idejecucion_key UNIQUE (idejecucion);
--
-- Definition for index ejecucionplanificacioncalidad_pkey (OID = 29615) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_pkey PRIMARY KEY (idejecucion, idplanificacioncalidad);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk (OID = 29617) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk FOREIGN KEY (idejecucionplanificacioncalidad, idplanificacioncalidad) REFERENCES ejecucionplanificacioncalidad(idejecucion, idplanificacioncalidad);
--
-- Definition for index ejecucionprocesocalidad_nroejecucion_key (OID = 29631) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_nroejecucion_key UNIQUE (idejecucion);
--
-- Definition for index ejecucionprocesocalidad_pkey (OID = 29633) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_pkey PRIMARY KEY (idejecucion, idprocesocalidad);
--
-- Definition for index detalleejecucionplanificacioncalidad_fk1 (OID = 29635) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT detalleejecucionplanificacioncalidad_fk1 FOREIGN KEY (ejecucionprocesocalidad, idprocesocalidad) REFERENCES ejecucionprocesocalidad(idejecucion, idprocesocalidad);
--
-- Definition for index empleado_pkey (OID = 29640) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (idempleado);
--
-- Definition for index mantenimientocorrectivo_fk (OID = 29642) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_fk FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index ejecucionetapaproduccion_fk1 (OID = 29647) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk1 FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index detallaplanificacionproduccion_fk3 (OID = 29657) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk3 FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index empleadoxturno_fk (OID = 29662) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_fk FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index empresametalurgica_pkey (OID = 29667) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_pkey PRIMARY KEY (idempresametalurgica);
--
-- Definition for index trabajotercerizado_fk1 (OID = 29669) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk1 FOREIGN KEY (empresa) REFERENCES empresametalurgica(idempresametalurgica);
--
-- Definition for index estadocliente_pkey (OID = 29674) : 
--
ALTER TABLE ONLY estadocliente
    ADD CONSTRAINT estadocliente_pkey PRIMARY KEY (idestado);
--
-- Definition for index cliente_fk1 (OID = 29676) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk1 FOREIGN KEY (estado) REFERENCES estadocliente(idestado);
--
-- Definition for index estadocompra_pkey (OID = 29681) : 
--
ALTER TABLE ONLY estadocompra
    ADD CONSTRAINT estadocompra_pkey PRIMARY KEY (idestado);
--
-- Definition for index compra_fk (OID = 29683) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_fk FOREIGN KEY (estado) REFERENCES estadocompra(idestado);
--
-- Definition for index estadodetallecompra_pkey (OID = 29688) : 
--
ALTER TABLE ONLY estadodetallecompra
    ADD CONSTRAINT estadodetallecompra_pkey PRIMARY KEY (idestado);
--
-- Definition for index detallecompra_fk2 (OID = 29690) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk2 FOREIGN KEY (estado) REFERENCES estadodetallecompra(idestado);
--
-- Definition for index estadodetalletrabajotercerizado_pkey (OID = 29695) : 
--
ALTER TABLE ONLY estadodetalletrabajotercerizado
    ADD CONSTRAINT estadodetalletrabajotercerizado_pkey PRIMARY KEY (idestado);
--
-- Definition for index detalletrabajotercerizado_fk3 (OID = 29697) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk3 FOREIGN KEY (estado) REFERENCES estadodetalletrabajotercerizado(idestado);
--
-- Definition for index estadoejecetapaprod_pkey (OID = 29702) : 
--
ALTER TABLE ONLY estadoejecetapaprod
    ADD CONSTRAINT estadoejecetapaprod_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionetapaproduccion_fk2 (OID = 29704) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk2 FOREIGN KEY (estado) REFERENCES estadoejecetapaprod(idestado);
--
-- Definition for index estadoejecplancalidad_pkey (OID = 29709) : 
--
ALTER TABLE ONLY estadoejecplancalidad
    ADD CONSTRAINT estadoejecplancalidad_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionplanificacioncalidad_fk1 (OID = 29711) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_fk1 FOREIGN KEY (estado) REFERENCES estadoejecplancalidad(idestado);
--
-- Definition for index estadoejecplanifpedido_pkey (OID = 29716) : 
--
ALTER TABLE ONLY estadoejecplanifpedido
    ADD CONSTRAINT estadoejecplanifpedido_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionplanificacionproduccion_fk1 (OID = 29718) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_fk1 FOREIGN KEY (estado) REFERENCES estadoejecplanifpedido(idestado);
--
-- Definition for index estadoejecucionprocesocalidad_pkey (OID = 29723) : 
--
ALTER TABLE ONLY estadoejecucionprocesocalidad
    ADD CONSTRAINT estadoejecucionprocesocalidad_pkey PRIMARY KEY (idestado);
--
-- Definition for index ejecucionprocesocalidad_fk1 (OID = 29725) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_fk1 FOREIGN KEY (estado) REFERENCES estadoejecucionprocesocalidad(idestado);
--
-- Definition for index estadofactura_pkey (OID = 29730) : 
--
ALTER TABLE ONLY estadofactura
    ADD CONSTRAINT estadofactura_pkey PRIMARY KEY (idestado);
--
-- Definition for index factura_fk3 (OID = 29732) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk3 FOREIGN KEY (estado) REFERENCES estadofactura(idestado);
--
-- Definition for index estadomaquina_pkey (OID = 29737) : 
--
ALTER TABLE ONLY estadomaquina
    ADD CONSTRAINT estadomaquina_pkey PRIMARY KEY (idestado);
--
-- Definition for index estadopedido_pkey (OID = 29744) : 
--
ALTER TABLE ONLY estadopedido
    ADD CONSTRAINT estadopedido_pkey PRIMARY KEY (idestado);
--
-- Definition for index pedido_fk (OID = 29746) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk FOREIGN KEY (estado) REFERENCES estadopedido(idestado);
--
-- Definition for index estadopiezareal_pkey (OID = 29751) : 
--
ALTER TABLE ONLY estadopiezareal
    ADD CONSTRAINT estadopiezareal_pkey PRIMARY KEY (idestado);
--
-- Definition for index estadoproductoreal_pkey (OID = 29758) : 
--
ALTER TABLE ONLY estadoproductoreal
    ADD CONSTRAINT estadoproductoreal_pkey PRIMARY KEY (idestado);
--
-- Definition for index productoreal_fk3 (OID = 29760) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk3 FOREIGN KEY (estado) REFERENCES estadoproductoreal(idestado);
--
-- Definition for index estadoremito_pkey (OID = 29765) : 
--
ALTER TABLE ONLY estadoremito
    ADD CONSTRAINT estadoremito_pkey PRIMARY KEY (idestado);
--
-- Definition for index remito_fk1 (OID = 29767) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_fk1 FOREIGN KEY (estado) REFERENCES estadoremito(idestado);
--
-- Definition for index estadotrabajotercerizado_pkey (OID = 29772) : 
--
ALTER TABLE ONLY estadotrabajotercerizado
    ADD CONSTRAINT estadotrabajotercerizado_pkey PRIMARY KEY (idestado);
--
-- Definition for index trabajotercerizado_fk2 (OID = 29774) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk2 FOREIGN KEY (estado) REFERENCES estadotrabajotercerizado(idestado);
--
-- Definition for index etapadeproduccion_pkey (OID = 29779) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_pkey PRIMARY KEY (idetapaproduccion);
--
-- Definition for index ejecucionetapaproduccion_fk (OID = 29781) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_fk FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detalletrabajotercerizado_fk2 (OID = 29786) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk2 FOREIGN KEY (proceso) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index piezaxetapadeproduccion_fk1 (OID = 29791) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT piezaxetapadeproduccion_fk1 FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index detalleplanprocedimientos_fk2 (OID = 29796) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_fk2 FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index factura_pkey (OID = 29801) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_pkey PRIMARY KEY (idfactura);
--
-- Definition for index pedido_fk1 (OID = 29803) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk1 FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index comprobantepago_fk2 (OID = 29808) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk2 FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index detallefactura_fk (OID = 29813) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_fk FOREIGN KEY (idfactura) REFERENCES factura(idfactura);
--
-- Definition for index formadepago_pkey (OID = 29818) : 
--
ALTER TABLE ONLY formadepago
    ADD CONSTRAINT formadepago_pkey PRIMARY KEY (idformapago);
--
-- Definition for index factura_fk1 (OID = 29820) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk1 FOREIGN KEY (formapago) REFERENCES formadepago(idformapago);
--
-- Definition for index comprobantepago_fk (OID = 29825) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT comprobantepago_fk FOREIGN KEY (formadepago) REFERENCES formadepago(idformapago);
--
-- Definition for index localidad_pkey (OID = 29830) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT localidad_pkey PRIMARY KEY (idlocalidad);
--
-- Definition for index barrio_fk (OID = 29832) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT barrio_fk FOREIGN KEY (localidad) REFERENCES localidad(idlocalidad);
--
-- Definition for index mantenimientocorrectivo_pkey (OID = 29837) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_pkey PRIMARY KEY (idmantenimientocorrectivo);
--
-- Definition for index detallemantenimientocorrectivo_fk1 (OID = 29839) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_fk1 FOREIGN KEY (idmantenimientocorrectivo) REFERENCES mantenimientocorrectivo(idmantenimientocorrectivo);
--
-- Definition for index mantenimientopreventivo_pkey (OID = 29844) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_pkey PRIMARY KEY (idmantenimientopreventivo);
--
-- Definition for index detallemantenimientopreventivo_fk (OID = 29846) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_fk FOREIGN KEY (idmantenimientopreventivo) REFERENCES mantenimientopreventivo(idmantenimientopreventivo);
--
-- Definition for index marca_pkey (OID = 29878) : 
--
ALTER TABLE ONLY marca
    ADD CONSTRAINT marca_pkey PRIMARY KEY (idmarca);
--
-- Definition for index materiaprima_pkey (OID = 29885) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_pkey PRIMARY KEY (idmateriaprima);
--
-- Definition for index matriz_fk (OID = 29887) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT matriz_fk FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallecompra_fk1 (OID = 29897) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT detallecompra_fk1 FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index proveedorxmateriaprima_fk1 (OID = 29902) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_fk1 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_fk2 (OID = 29907) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_fk2 FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index matriz_pkey (OID = 29912) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT matriz_pkey PRIMARY KEY (idmatriz);
--
-- Definition for index pedidomatriz_fk (OID = 29919) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT pedidomatriz_fk FOREIGN KEY (idmatriz) REFERENCES matriz(idmatriz);
--
-- Definition for index pedido_pkey (OID = 29924) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (idpedido);
--
-- Definition for index plano_fk (OID = 29926) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT plano_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index remito_fk (OID = 29931) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index trabajotercerizado_fk (OID = 29936) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index planificacionproduccion_fk (OID = 29941) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index planificacioncalidad_fk (OID = 29946) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT planificacioncalidad_fk FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index detallepedido_fk (OID = 29951) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_fk FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index productoreal_fk1 (OID = 29956) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_fk1 FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index detallefactura_fk1 (OID = 29961) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT detallefactura_fk1 FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index pedidomatriz_idx (OID = 29966) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT pedidomatriz_idx PRIMARY KEY (idpedidomatriz);
--
-- Definition for index planificacioncalidad_pkey (OID = 30044) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT planificacioncalidad_pkey PRIMARY KEY (idplanificacion);
--
-- Definition for index ejecucionplanificacioncalidad_fk (OID = 30046) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT ejecucionplanificacioncalidad_fk FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index detalleplanificacioncalidad_fk (OID = 30051) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index planificacionproduccion_pkey (OID = 30056) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_pkey PRIMARY KEY (idplanificacionproduccion);
--
-- Definition for index ejecucionplanificacionproduccion_fk (OID = 30058) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_fk FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index detallaplanificacionproduccion_fk (OID = 30063) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detallaplanificacionproduccion_fk FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index plano_pkey (OID = 30068) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT plano_pkey PRIMARY KEY (idplano);
--
-- Definition for index pedido_fk3 (OID = 30070) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk3 FOREIGN KEY (plano) REFERENCES plano(idplano);
--
-- Definition for index planprocedimientos_pkey (OID = 30075) : 
--
ALTER TABLE ONLY planprocedimientos
    ADD CONSTRAINT planprocedimientos_pkey PRIMARY KEY (idplanprocedimientos);
--
-- Definition for index detalleplanprocedimientos_fk (OID = 30077) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT detalleplanprocedimientos_fk FOREIGN KEY (idplanpprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index pedido_fk5 (OID = 30082) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk5 FOREIGN KEY (planprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index planprocesoscalidad_pkey (OID = 30087) : 
--
ALTER TABLE ONLY planprocesoscalidad
    ADD CONSTRAINT planprocesoscalidad_pkey PRIMARY KEY (idplanprocesoscalidad);
--
-- Definition for index detalleplanprocesoscalidad_fk (OID = 30089) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_fk FOREIGN KEY (idplanprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index pedido_fk7 (OID = 30094) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk7 FOREIGN KEY (planprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index planrequerimientosmateriaprima_pkey (OID = 30099) : 
--
ALTER TABLE ONLY planrequerimientosmateriaprima
    ADD CONSTRAINT planrequerimientosmateriaprima_pkey PRIMARY KEY (idplanrequerimientosmateriaprima);
--
-- Definition for index detallerequerimientosmateriaprima_fk (OID = 30101) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT detallerequerimientosmateriaprima_fk FOREIGN KEY (idplanrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index pedido_fk6 (OID = 30106) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk6 FOREIGN KEY (planrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index presupuesto_pkey (OID = 30111) : 
--
ALTER TABLE ONLY presupuesto
    ADD CONSTRAINT presupuesto_pkey PRIMARY KEY (idpresupuesto);
--
-- Definition for index pedido_fk2 (OID = 30113) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk2 FOREIGN KEY (presupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index detallepresupuesto_fk (OID = 30118) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_fk FOREIGN KEY (idpresupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index prioridad_pkey (OID = 30123) : 
--
ALTER TABLE ONLY prioridad
    ADD CONSTRAINT prioridad_pkey PRIMARY KEY (idprioridad);
--
-- Definition for index cliente_fk (OID = 30125) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index privilegio_pkey (OID = 30130) : 
--
ALTER TABLE ONLY privilegio
    ADD CONSTRAINT privilegio_pkey PRIMARY KEY (idprivilegio);
--
-- Definition for index rolxprivilegio_fk (OID = 30132) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_fk FOREIGN KEY (idprivilegio) REFERENCES privilegio(idprivilegio);
--
-- Definition for index procesocalidad_pkey (OID = 30137) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT procesocalidad_pkey PRIMARY KEY (idprocesocalidad);
--
-- Definition for index ejecucionprocesocalidad_fk (OID = 30139) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT ejecucionprocesocalidad_fk FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detalleplanificacioncalidad_fk2 (OID = 30144) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT detalleplanificacioncalidad_fk2 FOREIGN KEY (procesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index maquinaxprocesocalidad_fk (OID = 30149) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT maquinaxprocesocalidad_fk FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index detalleplanprocesoscalidad_fk2 (OID = 30154) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT detalleplanprocesoscalidad_fk2 FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index producto_pkey (OID = 30159) : 
--
ALTER TABLE ONLY producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (idproducto);
--
-- Definition for index detalleproducto_fk (OID = 30161) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_fk FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index detallepedido_fk1 (OID = 30166) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detalleremito_fk1 (OID = 30171) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detallereclamocliente_fk1 (OID = 30176) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_fk1 FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index detallepresupuesto_fk2 (OID = 30181) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_fk2 FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index productoreal_idx (OID = 30186) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT productoreal_idx PRIMARY KEY (idproductoreal);
--
-- Definition for index detalleproductoreal_fk (OID = 30188) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT detalleproductoreal_fk FOREIGN KEY (idproductoreal) REFERENCES productoreal(idproductoreal);
--
-- Definition for index proveedor_pkey (OID = 30193) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_pkey PRIMARY KEY (idproveedor);
--
-- Definition for index compra_fk1 (OID = 30195) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_fk1 FOREIGN KEY (proveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index proveedorxmateriaprima_fk (OID = 30200) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT proveedorxmateriaprima_fk FOREIGN KEY (idproveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index proveedormantenimientomaquina_pkey (OID = 30205) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_pkey PRIMARY KEY (idproveedormantenimiento);
--
-- Definition for index mantenimientopreventivo_fk (OID = 30207) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT mantenimientopreventivo_fk FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index mantenimientocorrectivo_fk1 (OID = 30212) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT mantenimientocorrectivo_fk1 FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index provincia_pkey (OID = 30217) : 
--
ALTER TABLE ONLY provincia
    ADD CONSTRAINT provincia_pkey PRIMARY KEY (idprovincia);
--
-- Definition for index localidad_fk (OID = 30219) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT localidad_fk FOREIGN KEY (provincia) REFERENCES provincia(idprovincia);
--
-- Definition for index reclamocliente_idx (OID = 30224) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_idx PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamocliente_fk (OID = 30226) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT detallereclamocliente_fk FOREIGN KEY (idreclamo) REFERENCES reclamocliente(idreclamo);
--
-- Definition for index reclamoempresametalurgica_pkey (OID = 30231) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_pkey PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamoempresametalurgica_fk (OID = 30233) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT detallereclamoempresametalurgica_fk FOREIGN KEY (idreclamo) REFERENCES reclamoempresametalurgica(idreclamo);
--
-- Definition for index reclamoproveedor_idx (OID = 30238) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_idx PRIMARY KEY (idreclamo);
--
-- Definition for index detallereclamoproveedor_fk (OID = 30240) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT detallereclamoproveedor_fk FOREIGN KEY (idreclamo) REFERENCES reclamoproveedor(idreclamo);
--
-- Definition for index remito_pkey (OID = 30245) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT remito_pkey PRIMARY KEY (idremito);
--
-- Definition for index detalleremito_fk (OID = 30247) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT detalleremito_fk FOREIGN KEY (idremito) REFERENCES remito(idremito);
--
-- Definition for index responsable_pkey (OID = 30252) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_pkey PRIMARY KEY (idresponsable);
--
-- Definition for index cliente_fk3 (OID = 30254) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_fk3 FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index empresametalurgica_fk (OID = 30259) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT empresametalurgica_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index proveedormantenimientomaquina_fk (OID = 30264) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT proveedormantenimientomaquina_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index proveedor_fk (OID = 30269) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_fk FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index rol_pkey (OID = 30274) : 
--
ALTER TABLE ONLY rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (idrol);
--
-- Definition for index rolxprivilegio_fk1 (OID = 30276) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT rolxprivilegio_fk1 FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index usuarioxrol_fk (OID = 30281) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT usuarioxrol_fk FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index rotura_pkey (OID = 30286) : 
--
ALTER TABLE ONLY rotura
    ADD CONSTRAINT rotura_pkey PRIMARY KEY (idrotura);
--
-- Definition for index detallemantenimientocorrectivo_fk (OID = 30288) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT detallemantenimientocorrectivo_fk FOREIGN KEY (rotura) REFERENCES rotura(idrotura);
--
-- Definition for index servicio_pkey (OID = 30293) : 
--
ALTER TABLE ONLY servicio
    ADD CONSTRAINT servicio_pkey PRIMARY KEY (idservicio);
--
-- Definition for index detallemantenimientopreventivo_fk1 (OID = 30295) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT detallemantenimientopreventivo_fk1 FOREIGN KEY (servicio) REFERENCES servicio(idservicio);
--
-- Definition for index sesion_pkey (OID = 30300) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT sesion_pkey PRIMARY KEY (idsesion);
--
-- Definition for index tipodocumento_pkey (OID = 30302) : 
--
ALTER TABLE ONLY tipodocumento
    ADD CONSTRAINT tipodocumento_pkey PRIMARY KEY (idtipodocumento);
--
-- Definition for index responsable_fk1 (OID = 30304) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT responsable_fk1 FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index empleado_fk1 (OID = 30309) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_fk1 FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index tipoiva_pkey (OID = 30314) : 
--
ALTER TABLE ONLY tipoiva
    ADD CONSTRAINT tipoiva_pkey PRIMARY KEY (idtipoiva);
--
-- Definition for index factura_fk (OID = 30316) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_fk FOREIGN KEY (tipoiva) REFERENCES tipoiva(idtipoiva);
--
-- Definition for index tipomaquina_pkey (OID = 30321) : 
--
ALTER TABLE ONLY tipomaquina
    ADD CONSTRAINT tipomaquina_pkey PRIMARY KEY (idtipomaquina);
--
-- Definition for index tiporeclamo_pkey (OID = 30328) : 
--
ALTER TABLE ONLY tiporeclamo
    ADD CONSTRAINT tiporeclamo_pkey PRIMARY KEY (idtiporeclamo);
--
-- Definition for index reclamoempresametalurgica_fk (OID = 30330) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index reclamoproveedor_fk (OID = 30335) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT reclamoproveedor_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index reclamocliente_fk (OID = 30340) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT reclamocliente_fk FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index trabajotercerizado_pkey (OID = 30345) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT trabajotercerizado_pkey PRIMARY KEY (idtrabajo);
--
-- Definition for index detalletrabajotercerizado_fk (OID = 30347) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT detalletrabajotercerizado_fk FOREIGN KEY (idtrabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index reclamoempresametalurgica_fk1 (OID = 30352) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT reclamoempresametalurgica_fk1 FOREIGN KEY (trabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index turno_pkey (OID = 30357) : 
--
ALTER TABLE ONLY turno
    ADD CONSTRAINT turno_pkey PRIMARY KEY (idturno);
--
-- Definition for index empleadoxturno_fk1 (OID = 30359) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT empleadoxturno_fk1 FOREIGN KEY (idturno) REFERENCES turno(idturno);
--
-- Definition for index materiaprima_fk (OID = 30364) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk FOREIGN KEY (tipomaterial) REFERENCES tipomaterial(idtipomaterial);
--
-- Definition for index detalleproducto_iddetalle_key (OID = 30369) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT detalleproducto_iddetalle_key PRIMARY KEY (iddetalle);
ALTER TABLE detalleproducto CLUSTER ON detalleproducto_iddetalle_key;
--
-- Definition for index pedido_fk8 (OID = 30371) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_fk8 FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index unidadmedida_pkey (OID = 30376) : 
--
ALTER TABLE ONLY unidadmedida
    ADD CONSTRAINT unidadmedida_pkey PRIMARY KEY (idunidadmedida);
--
-- Definition for index etapadeproduccion_fk1 (OID = 30378) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_fk1 FOREIGN KEY (unidaddemedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index materiaprima_fk2 (OID = 30383) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT materiaprima_fk2 FOREIGN KEY (unidadmedida) REFERENCES unidadmedida(idunidadmedida);
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
-- Definition for index detallepresupuesto_pkey (OID = 38770) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT detallepresupuesto_pkey PRIMARY KEY (iddetalle);
--
-- Definition for index detallepedido_pkey (OID = 38772) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT detallepedido_pkey PRIMARY KEY (iddetalle);
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
-- Definition for index calendario_pkey (OID = 63178) : 
--
ALTER TABLE ONLY calendario
    ADD CONSTRAINT calendario_pkey PRIMARY KEY (id);
--
-- Definition for index disponibilidadhoraria_pkey (OID = 63215) : 
--
ALTER TABLE ONLY disponibilidadhoraria
    ADD CONSTRAINT disponibilidadhoraria_pkey PRIMARY KEY (id);
--
-- Definition for index estadoplanificacionproduccion_pkey (OID = 63249) : 
--
ALTER TABLE ONLY estadoplanificacionproduccion
    ADD CONSTRAINT estadoplanificacionproduccion_pkey PRIMARY KEY (id);
--
-- Definition for index planificacionproduccion_fk1 (OID = 63260) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT planificacionproduccion_fk1 FOREIGN KEY (idestado) REFERENCES estadoplanificacionproduccion(id);
--
-- Definition for index disponibilidadhoraria_fk (OID = 63265) : 
--
ALTER TABLE ONLY disponibilidadhoraria
    ADD CONSTRAINT disponibilidadhoraria_fk FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index detalleplanificacionproduccion_pkey (OID = 63270) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_pkey PRIMARY KEY (id);
--
-- Definition for index detalleplanificacionproduccion_fk (OID = 63272) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index maquina_pkey (OID = 63314) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_pkey PRIMARY KEY (idmaquina);
--
-- Definition for index maquina_fk (OID = 63316) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk FOREIGN KEY (marca) REFERENCES marca(idmarca);
--
-- Definition for index maquina_fk1 (OID = 63321) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk1 FOREIGN KEY (estado) REFERENCES estadomaquina(idestado);
--
-- Definition for index maquina_fk2 (OID = 63326) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk2 FOREIGN KEY (tipomaquina) REFERENCES tipomaquina(idtipomaquina);
--
-- Definition for index maquina_fk3 (OID = 63331) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT maquina_fk3 FOREIGN KEY (idunidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index piezareal_fk1 (OID = 63353) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_fk1 FOREIGN KEY (estado) REFERENCES estadopiezareal(idestado);
--
-- Definition for index piezareal_fk2 (OID = 63358) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_fk2 FOREIGN KEY (idcodigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index pieza_pkey (OID = 63369) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_pkey PRIMARY KEY (idpieza);
--
-- Definition for index pieza_fk (OID = 63371) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk FOREIGN KEY (unidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index pieza_fk1 (OID = 63376) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk1 FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index pieza_fk2 (OID = 63381) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT pieza_fk2 FOREIGN KEY (matriz) REFERENCES matriz(idmatriz);
--
-- Definition for index detallempasignada_pkey (OID = 63392) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT detallempasignada_pkey PRIMARY KEY (id);
--
-- Definition for index detallempasignada_fk (OID = 63394) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT detallempasignada_fk FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index detallempasignada_fk1 (OID = 63399) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT detallempasignada_fk1 FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index mpasignadaxpiezareal_pkey (OID = 63410) : 
--
ALTER TABLE ONLY mpasignadaxpiezareal
    ADD CONSTRAINT mpasignadaxpiezareal_pkey PRIMARY KEY (id);
--
-- Definition for index mpasignadaxpiezareal_fk1 (OID = 63417) : 
--
ALTER TABLE ONLY mpasignadaxpiezareal
    ADD CONSTRAINT mpasignadaxpiezareal_fk1 FOREIGN KEY (iddetallempasignada) REFERENCES detallempasignada(id);
--
-- Definition for index asistencia_idx (OID = 63465) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_idx PRIMARY KEY (empleado, horaingreso, fechaingreso);
--
-- Definition for index asistencia_fk (OID = 63467) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT asistencia_fk FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index piezareal_idpiezareal_key (OID = 66293) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT piezareal_idpiezareal_key PRIMARY KEY (idpiezareal);
--
-- Definition for index detalleplanificacionproduccion_fk1 (OID = 66306) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk1 FOREIGN KEY (idmaquina) REFERENCES maquina(idmaquina);
--
-- Definition for index detalleplanificacionproduccion_fk2 (OID = 66322) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk2 FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleproductopresupuesto_fk (OID = 66347) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT detalleproductopresupuesto_fk FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index ejecucionplanificacionproduccion_idejecucion_key (OID = 69211) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT ejecucionplanificacionproduccion_idejecucion_key PRIMARY KEY (idejecucion);
--
-- Definition for index detalleejecucionplanificacion_iddetalle_key (OID = 69213) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_iddetalle_key PRIMARY KEY (id);
--
-- Definition for index detalleplanificacionproduccion_fk3 (OID = 69225) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk3 FOREIGN KEY (iddetalleejecucionplanificacion) REFERENCES detalleejecucionplanificacion(id);
--
-- Definition for index fk_responsable_domicilio (OID = 71557) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT fk_responsable_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_responsable_tipodocumento (OID = 71562) : 
--
ALTER TABLE ONLY responsable
    ADD CONSTRAINT fk_responsable_tipodocumento FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index fk_procesocalidad_accioncalidad (OID = 71567) : 
--
ALTER TABLE ONLY procesocalidad
    ADD CONSTRAINT fk_procesocalidad_accioncalidad FOREIGN KEY (accioncalidad) REFERENCES accioncalidad(idaccioncalidad);
--
-- Definition for index fk_detallecompra_estado (OID = 71572) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT fk_detallecompra_estado FOREIGN KEY (estado) REFERENCES estadodetallecompra(idestado);
--
-- Definition for index fk_detallecompra_materiaprima (OID = 71577) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT fk_detallecompra_materiaprima FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detallecompra_idcompra (OID = 71582) : 
--
ALTER TABLE ONLY detallecompra
    ADD CONSTRAINT fk_detallecompra_idcompra FOREIGN KEY (idcompra) REFERENCES compra(idcompra);
--
-- Definition for index fk_reclamoproveedor_tiporeclamo (OID = 71587) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT fk_reclamoproveedor_tiporeclamo FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index fk_reclamoproveedor_compra (OID = 71592) : 
--
ALTER TABLE ONLY reclamoproveedor
    ADD CONSTRAINT fk_reclamoproveedor_compra FOREIGN KEY (compra) REFERENCES compra(idcompra);
--
-- Definition for index fk_empresametalurgica_condicioniva (OID = 71597) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT fk_empresametalurgica_condicioniva FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index fk_empresametalurgica_domicilio (OID = 71602) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT fk_empresametalurgica_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_empresametalurgica_responsable (OID = 71607) : 
--
ALTER TABLE ONLY empresametalurgica
    ADD CONSTRAINT fk_empresametalurgica_responsable FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index fk_detalleplanprocesoscalidad_idprocesocalidad (OID = 71612) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT fk_detalleplanprocesoscalidad_idprocesocalidad FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index fk_detalleplanprocesoscalidad_idplanprocesoscalidad (OID = 71617) : 
--
ALTER TABLE ONLY detalleplanprocesoscalidad
    ADD CONSTRAINT fk_detalleplanprocesoscalidad_idplanprocesoscalidad FOREIGN KEY (idplanprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index fk_detalleremito_idremito (OID = 71622) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT fk_detalleremito_idremito FOREIGN KEY (idremito) REFERENCES remito(idremito);
--
-- Definition for index fk_detalleremito_producto (OID = 71627) : 
--
ALTER TABLE ONLY detalleremito
    ADD CONSTRAINT fk_detalleremito_producto FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index fk_detalleplanificacionproduccion_idpieza (OID = 71632) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idpieza FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index fk_detalleplanificacionproduccion_idetapaproduccion (OID = 71637) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idetapaproduccion FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_detalleplanificacionproduccion_idempleado (OID = 71642) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idempleado FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index detalleplanificacionproduccion_iddetalleejecucionplanificacion (OID = 71647) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_iddetalleejecucionplanificacion FOREIGN KEY (iddetalleejecucionplanificacion) REFERENCES detalleejecucionplanificacion(id);
--
-- Definition for index fk_detalleplanificacionproduccion_idmaquina (OID = 71652) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idmaquina FOREIGN KEY (idmaquina) REFERENCES maquina(idmaquina);
--
-- Definition for index fk_detalleplanificacionproduccion_idplanificacionproduccion (OID = 71657) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT fk_detalleplanificacionproduccion_idplanificacionproduccion FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index fk_ejecucionplanificacioncalidad_estado (OID = 71662) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT fk_ejecucionplanificacioncalidad_estado FOREIGN KEY (estado) REFERENCES estadoejecplancalidad(idestado);
--
-- Definition for index fk_ejecucionplanificacioncalidad_idplanificacioncalidad (OID = 71667) : 
--
ALTER TABLE ONLY ejecucionplanificacioncalidad
    ADD CONSTRAINT fk_ejecucionplanificacioncalidad_idplanificacioncalidad FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index fk_planificacionproduccion_pedido (OID = 71672) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT fk_planificacionproduccion_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_planificacionproduccion_idestado (OID = 71677) : 
--
ALTER TABLE ONLY planificacionproduccion
    ADD CONSTRAINT fk_planificacionproduccion_idestado FOREIGN KEY (idestado) REFERENCES estadoplanificacionproduccion(id);
--
-- Definition for index fk_pedido_estado (OID = 71682) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_estado FOREIGN KEY (estado) REFERENCES estadopedido(idestado);
--
-- Definition for index fk_pedido_planprocesoscalidad (OID = 71687) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_planprocesoscalidad FOREIGN KEY (planprocesoscalidad) REFERENCES planprocesoscalidad(idplanprocesoscalidad);
--
-- Definition for index fk_pedido_presupuesto (OID = 71692) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_presupuesto FOREIGN KEY (presupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index fk_pedido_planrequerimientosmateriaprima (OID = 71697) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_planrequerimientosmateriaprima FOREIGN KEY (planrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index fk_pedido_planprocedimientos (OID = 71702) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_planprocedimientos FOREIGN KEY (planprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index fk_pedido_plano (OID = 71707) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_plano FOREIGN KEY (plano) REFERENCES plano(idplano);
--
-- Definition for index fk_pedido_prioridad (OID = 71712) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_prioridad FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index fk_pedido_cliente (OID = 71717) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index fk_pedido_factura (OID = 71722) : 
--
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_pedido_factura FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index fk_empleadoxturno_idempleado (OID = 71727) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT fk_empleadoxturno_idempleado FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_empleadoxturno_idturno (OID = 71732) : 
--
ALTER TABLE ONLY empleadoxturno
    ADD CONSTRAINT fk_empleadoxturno_idturno FOREIGN KEY (idturno) REFERENCES turno(idturno);
--
-- Definition for index fk_detallereclamoempresametalurgica_idreclamo (OID = 71737) : 
--
ALTER TABLE ONLY detallereclamoempresametalurgica
    ADD CONSTRAINT fk_detallereclamoempresametalurgica_idreclamo FOREIGN KEY (idreclamo) REFERENCES reclamoempresametalurgica(idreclamo);
--
-- Definition for index fk_ejecucionetapaproduccion_empleado (OID = 71742) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT fk_ejecucionetapaproduccion_empleado FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_ejecucionetapaproduccion_estado (OID = 71747) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT fk_ejecucionetapaproduccion_estado FOREIGN KEY (estado) REFERENCES estadoejecetapaprod(idestado);
--
-- Definition for index fk_ejecucionetapaproduccion_idetapaproduccion (OID = 71752) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT fk_ejecucionetapaproduccion_idetapaproduccion FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_compra_proveedor (OID = 71757) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT fk_compra_proveedor FOREIGN KEY (proveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index fk_compra_estado (OID = 71762) : 
--
ALTER TABLE ONLY compra
    ADD CONSTRAINT fk_compra_estado FOREIGN KEY (estado) REFERENCES estadocompra(idestado);
--
-- Definition for index fk_maquinaxprocesocalidad_idprocesocalidad (OID = 71767) : 
--
ALTER TABLE ONLY maquinaxprocesocalidad
    ADD CONSTRAINT fk_maquinaxprocesocalidad_idprocesocalidad FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index fk_productoreal_codigobarra (OID = 71772) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT fk_productoreal_codigobarra FOREIGN KEY (codigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index fk_productoreal_idpedido (OID = 71777) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT fk_productoreal_idpedido FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_productoreal_estado (OID = 71782) : 
--
ALTER TABLE ONLY productoreal
    ADD CONSTRAINT fk_productoreal_estado FOREIGN KEY (estado) REFERENCES estadoproductoreal(idestado);
--
-- Definition for index fk_materiaprima_unidadmedida (OID = 71787) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT fk_materiaprima_unidadmedida FOREIGN KEY (unidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index fk_materiaprima_tipomaterial (OID = 71792) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT fk_materiaprima_tipomaterial FOREIGN KEY (tipomaterial) REFERENCES tipomaterial(idtipomaterial);
--
-- Definition for index fk_materiaprima_codbarra (OID = 71797) : 
--
ALTER TABLE ONLY materiaprima
    ADD CONSTRAINT fk_materiaprima_codbarra FOREIGN KEY (codbarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index fk_cliente_estado (OID = 71802) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_estado FOREIGN KEY (estado) REFERENCES estadocliente(idestado);
--
-- Definition for index fk_cliente_domicilio (OID = 71807) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_cliente_usuario (OID = 71812) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_cliente_condicioniva (OID = 71817) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_condicioniva FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index fk_cliente_responsable (OID = 71822) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_responsable FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index fk_cliente_prioridad (OID = 71827) : 
--
ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_prioridad FOREIGN KEY (prioridad) REFERENCES prioridad(idprioridad);
--
-- Definition for index fk_pieza_matriz (OID = 71832) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT fk_pieza_matriz FOREIGN KEY (matriz) REFERENCES matriz(idmatriz);
--
-- Definition for index fk_pieza_unidadmedida (OID = 71837) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT fk_pieza_unidadmedida FOREIGN KEY (unidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index fk_pieza_materiaprima (OID = 71842) : 
--
ALTER TABLE ONLY pieza
    ADD CONSTRAINT fk_pieza_materiaprima FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detallepiezacalidadpresupuesto_idprocesocalidad (OID = 71847) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT fk_detallepiezacalidadpresupuesto_idprocesocalidad FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index fk_detallepiezacalidadpresupuesto_iddetalleproductopresupuesto (OID = 71852) : 
--
ALTER TABLE ONLY detallepiezacalidadpresupuesto
    ADD CONSTRAINT fk_detallepiezacalidadpresupuesto_iddetalleproductopresupuesto FOREIGN KEY (iddetalleproductopresupuesto) REFERENCES detalleproductopresupuesto(iddetalle);
--
-- Definition for index fk_detalleproductoreal_idproductoreal (OID = 71857) : 
--
ALTER TABLE ONLY detalleproductoreal
    ADD CONSTRAINT fk_detalleproductoreal_idproductoreal FOREIGN KEY (idproductoreal) REFERENCES productoreal(idproductoreal);
--
-- Definition for index fk_factura_tipoiva (OID = 71862) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_factura_tipoiva FOREIGN KEY (tipoiva) REFERENCES tipoiva(idtipoiva);
--
-- Definition for index fk_factura_formapago (OID = 71867) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_factura_formapago FOREIGN KEY (formapago) REFERENCES formadepago(idformapago);
--
-- Definition for index fk_factura_usuario (OID = 71872) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_factura_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_factura_estado (OID = 71877) : 
--
ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_factura_estado FOREIGN KEY (estado) REFERENCES estadofactura(idestado);
--
-- Definition for index fk_detallerequerimientosmateriaprima_idmateriaprima (OID = 71882) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT fk_detallerequerimientosmateriaprima_idmateriaprima FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index dtallerequerimientosmateriaprimadplanrequerimientosmateriaprima (OID = 71887) : 
--
ALTER TABLE ONLY detallerequerimientosmateriaprima
    ADD CONSTRAINT dtallerequerimientosmateriaprimadplanrequerimientosmateriaprima FOREIGN KEY (idplanrequerimientosmateriaprima) REFERENCES planrequerimientosmateriaprima(idplanrequerimientosmateriaprima);
--
-- Definition for index fk_plano_pedido (OID = 71892) : 
--
ALTER TABLE ONLY plano
    ADD CONSTRAINT fk_plano_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_usuarioxrol_idrol (OID = 71897) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT fk_usuarioxrol_idrol FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index fk_usuarioxrol_idusuario (OID = 71902) : 
--
ALTER TABLE ONLY usuarioxrol
    ADD CONSTRAINT fk_usuarioxrol_idusuario FOREIGN KEY (idusuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_trabajotercerizado_empresa (OID = 71907) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT fk_trabajotercerizado_empresa FOREIGN KEY (empresa) REFERENCES empresametalurgica(idempresametalurgica);
--
-- Definition for index fk_trabajotercerizado_estado (OID = 71912) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT fk_trabajotercerizado_estado FOREIGN KEY (estado) REFERENCES estadotrabajotercerizado(idestado);
--
-- Definition for index fk_trabajotercerizado_pedido (OID = 71917) : 
--
ALTER TABLE ONLY trabajotercerizado
    ADD CONSTRAINT fk_trabajotercerizado_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_piezaxetapadeproduccion_idetapaproduccion (OID = 71922) : 
--
ALTER TABLE ONLY piezaxetapadeproduccion
    ADD CONSTRAINT fk_piezaxetapadeproduccion_idetapaproduccion FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_proveedor_responsable (OID = 71927) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT fk_proveedor_responsable FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index fk_proveedor_domicilio (OID = 71932) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT fk_proveedor_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_proveedor_condicion (OID = 71937) : 
--
ALTER TABLE ONLY proveedor
    ADD CONSTRAINT fk_proveedor_condicion FOREIGN KEY (condicion) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index fk_reclamoempresametalurgica_trabajotercerizado (OID = 71942) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT fk_reclamoempresametalurgica_trabajotercerizado FOREIGN KEY (trabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index fk_reclamoempresametalurgica_tiporeclamo (OID = 71947) : 
--
ALTER TABLE ONLY reclamoempresametalurgica
    ADD CONSTRAINT fk_reclamoempresametalurgica_tiporeclamo FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index fk_matriz_materiaprima (OID = 71952) : 
--
ALTER TABLE ONLY matriz
    ADD CONSTRAINT fk_matriz_materiaprima FOREIGN KEY (materiaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detalleproducto_idproducto (OID = 71957) : 
--
ALTER TABLE ONLY detalleproducto
    ADD CONSTRAINT fk_detalleproducto_idproducto FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index fk_mantenimientocorrectivo_empleado (OID = 71962) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT fk_mantenimientocorrectivo_empleado FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_mantenimientocorrectivo_proveedormantenimiento (OID = 71967) : 
--
ALTER TABLE ONLY mantenimientocorrectivo
    ADD CONSTRAINT fk_mantenimientocorrectivo_proveedormantenimiento FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index fk_piezareal_estado (OID = 71972) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT fk_piezareal_estado FOREIGN KEY (estado) REFERENCES estadopiezareal(idestado);
--
-- Definition for index fk_piezareal_idcodigobarra (OID = 71977) : 
--
ALTER TABLE ONLY piezareal
    ADD CONSTRAINT fk_piezareal_idcodigobarra FOREIGN KEY (idcodigobarra) REFERENCES codigodebarra(idcodigo);
--
-- Definition for index fk_detallempasignada_idmateriaprima (OID = 71982) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT fk_detallempasignada_idmateriaprima FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detallempasignada_idplanificacionproduccion (OID = 71987) : 
--
ALTER TABLE ONLY detallempasignada
    ADD CONSTRAINT fk_detallempasignada_idplanificacionproduccion FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index fk_ejecucionprocesocalidad_idprocesocalidad (OID = 71992) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT fk_ejecucionprocesocalidad_idprocesocalidad FOREIGN KEY (idprocesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index fk_ejecucionprocesocalidad_estado (OID = 71997) : 
--
ALTER TABLE ONLY ejecucionprocesocalidad
    ADD CONSTRAINT fk_ejecucionprocesocalidad_estado FOREIGN KEY (estado) REFERENCES estadoejecucionprocesocalidad(idestado);
--
-- Definition for index fk_pedidomatriz_idmatriz (OID = 72002) : 
--
ALTER TABLE ONLY pedidomatriz
    ADD CONSTRAINT fk_pedidomatriz_idmatriz FOREIGN KEY (idmatriz) REFERENCES matriz(idmatriz);
--
-- Definition for index fk_maquina_idunidadmedida (OID = 72022) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT fk_maquina_idunidadmedida FOREIGN KEY (idunidadmedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index fk_maquina_estado (OID = 72027) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT fk_maquina_estado FOREIGN KEY (estado) REFERENCES estadomaquina(idestado);
--
-- Definition for index fk_maquina_tipomaquina (OID = 72032) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT fk_maquina_tipomaquina FOREIGN KEY (tipomaquina) REFERENCES tipomaquina(idtipomaquina);
--
-- Definition for index fk_maquina_marca (OID = 72037) : 
--
ALTER TABLE ONLY maquina
    ADD CONSTRAINT fk_maquina_marca FOREIGN KEY (marca) REFERENCES marca(idmarca);
--
-- Definition for index fk_planificacioncalidad_pedido (OID = 72042) : 
--
ALTER TABLE ONLY planificacioncalidad
    ADD CONSTRAINT fk_planificacioncalidad_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_detalleplanificacioncalidad_idplanificacioncalidad (OID = 72047) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT fk_detalleplanificacioncalidad_idplanificacioncalidad FOREIGN KEY (idplanificacioncalidad) REFERENCES planificacioncalidad(idplanificacion);
--
-- Definition for index dtalleplanificacioncalidadddetalleejecucionplanificacioncalidad (OID = 72052) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT dtalleplanificacioncalidadddetalleejecucionplanificacioncalidad FOREIGN KEY (iddetalleejecucionplanificacioncalidad, idejecucionplanificacioncalidad, idplanificacioncalidad) REFERENCES detalleejecucionplanificacioncalidad(idejecucionplanificacioncalidad, idplanificacioncalidad, iddetalle);
--
-- Definition for index fk_detalleplanificacioncalidad_procesocalidad (OID = 72057) : 
--
ALTER TABLE ONLY detalleplanificacioncalidad
    ADD CONSTRAINT fk_detalleplanificacioncalidad_procesocalidad FOREIGN KEY (procesocalidad) REFERENCES procesocalidad(idprocesocalidad);
--
-- Definition for index fk_detallemantenimientocorrectivo_rotura (OID = 72062) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT fk_detallemantenimientocorrectivo_rotura FOREIGN KEY (rotura) REFERENCES rotura(idrotura);
--
-- Definition for index fk_detallemantenimientocorrectivo_idmantenimientocorrectivo (OID = 72067) : 
--
ALTER TABLE ONLY detallemantenimientocorrectivo
    ADD CONSTRAINT fk_detallemantenimientocorrectivo_idmantenimientocorrectivo FOREIGN KEY (idmantenimientocorrectivo) REFERENCES mantenimientocorrectivo(idmantenimientocorrectivo);
--
-- Definition for index fk_localidad_provincia (OID = 72072) : 
--
ALTER TABLE ONLY localidad
    ADD CONSTRAINT fk_localidad_provincia FOREIGN KEY (provincia) REFERENCES provincia(idprovincia);
--
-- Definition for index fk_proveedorxmateriaprima_idmateriaprima (OID = 72077) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT fk_proveedorxmateriaprima_idmateriaprima FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_proveedorxmateriaprima_idproveedor (OID = 72082) : 
--
ALTER TABLE ONLY proveedorxmateriaprima
    ADD CONSTRAINT fk_proveedorxmateriaprima_idproveedor FOREIGN KEY (idproveedor) REFERENCES proveedor(idproveedor);
--
-- Definition for index fk_remito_pedido (OID = 72087) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT fk_remito_pedido FOREIGN KEY (pedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_remito_estado (OID = 72092) : 
--
ALTER TABLE ONLY remito
    ADD CONSTRAINT fk_remito_estado FOREIGN KEY (estado) REFERENCES estadoremito(idestado);
--
-- Definition for index fk_detalleejecucionplanificacioncalidad_idplanificacioncalidad (OID = 72097) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT fk_detalleejecucionplanificacioncalidad_idplanificacioncalidad FOREIGN KEY (idplanificacioncalidad, idejecucionplanificacioncalidad) REFERENCES ejecucionplanificacioncalidad(idplanificacioncalidad, idejecucion);
--
-- Definition for index fk_detalleejecucionplanificacioncalidad_idprocesocalidad (OID = 72102) : 
--
ALTER TABLE ONLY detalleejecucionplanificacioncalidad
    ADD CONSTRAINT fk_detalleejecucionplanificacioncalidad_idprocesocalidad FOREIGN KEY (idprocesocalidad, ejecucionprocesocalidad) REFERENCES ejecucionprocesocalidad(idprocesocalidad, idejecucion);
--
-- Definition for index fk_disponibilidadhoraria_idempleado (OID = 72107) : 
--
ALTER TABLE ONLY disponibilidadhoraria
    ADD CONSTRAINT fk_disponibilidadhoraria_idempleado FOREIGN KEY (idempleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_sesion_usuario (OID = 72112) : 
--
ALTER TABLE ONLY sesion
    ADD CONSTRAINT fk_sesion_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_detallereclamoproveedor_idreclamo (OID = 72117) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT fk_detallereclamoproveedor_idreclamo FOREIGN KEY (idreclamo) REFERENCES reclamoproveedor(idreclamo);
--
-- Definition for index fk_detallereclamoproveedor_idcompra (OID = 72122) : 
--
ALTER TABLE ONLY detallereclamoproveedor
    ADD CONSTRAINT fk_detallereclamoproveedor_idcompra FOREIGN KEY (idcompra, iddetallecompra) REFERENCES detallecompra(idcompra, iddetalle);
--
-- Definition for index fk_barrio_localidad (OID = 72127) : 
--
ALTER TABLE ONLY barrio
    ADD CONSTRAINT fk_barrio_localidad FOREIGN KEY (localidad) REFERENCES localidad(idlocalidad);
--
-- Definition for index fk_asistencia_empleado (OID = 72132) : 
--
ALTER TABLE ONLY asistencia
    ADD CONSTRAINT fk_asistencia_empleado FOREIGN KEY (empleado) REFERENCES empleado(idempleado);
--
-- Definition for index fk_detallemantenimientopreventivo_servicio (OID = 72137) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT fk_detallemantenimientopreventivo_servicio FOREIGN KEY (servicio) REFERENCES servicio(idservicio);
--
-- Definition for index fk_detallemantenimientopreventivo_idmantenimientopreventivo (OID = 72142) : 
--
ALTER TABLE ONLY detallemantenimientopreventivo
    ADD CONSTRAINT fk_detallemantenimientopreventivo_idmantenimientopreventivo FOREIGN KEY (idmantenimientopreventivo) REFERENCES mantenimientopreventivo(idmantenimientopreventivo);
--
-- Definition for index fk_etapadeproduccion_unidaddemedida (OID = 72147) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT fk_etapadeproduccion_unidaddemedida FOREIGN KEY (unidaddemedida) REFERENCES unidadmedida(idunidadmedida);
--
-- Definition for index fk_detallefactura_idpedido (OID = 72152) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT fk_detallefactura_idpedido FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_detallefactura_idfactura (OID = 72157) : 
--
ALTER TABLE ONLY detallefactura
    ADD CONSTRAINT fk_detallefactura_idfactura FOREIGN KEY (idfactura) REFERENCES factura(idfactura);
--
-- Definition for index fk_detallepedido_producto (OID = 72162) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT fk_detallepedido_producto FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index fk_detallepedido_idpedido (OID = 72167) : 
--
ALTER TABLE ONLY detallepedido
    ADD CONSTRAINT fk_detallepedido_idpedido FOREIGN KEY (idpedido) REFERENCES pedido(idpedido);
--
-- Definition for index fk_mantenimientopreventivo_proveedormantenimiento (OID = 72172) : 
--
ALTER TABLE ONLY mantenimientopreventivo
    ADD CONSTRAINT fk_mantenimientopreventivo_proveedormantenimiento FOREIGN KEY (proveedormantenimiento) REFERENCES proveedormantenimientomaquina(idproveedormantenimiento);
--
-- Definition for index fk_rolxprivilegio_idprivilegio (OID = 72177) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT fk_rolxprivilegio_idprivilegio FOREIGN KEY (idprivilegio) REFERENCES privilegio(idprivilegio);
--
-- Definition for index fk_rolxprivilegio_idrol (OID = 72182) : 
--
ALTER TABLE ONLY rolxprivilegio
    ADD CONSTRAINT fk_rolxprivilegio_idrol FOREIGN KEY (idrol) REFERENCES rol(idrol);
--
-- Definition for index fk_detallereclamocliente_producto (OID = 72187) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT fk_detallereclamocliente_producto FOREIGN KEY (producto) REFERENCES producto(idproducto);
--
-- Definition for index fk_detallereclamocliente_idreclamo (OID = 72192) : 
--
ALTER TABLE ONLY detallereclamocliente
    ADD CONSTRAINT fk_detallereclamocliente_idreclamo FOREIGN KEY (idreclamo) REFERENCES reclamocliente(idreclamo);
--
-- Definition for index fk_proveedormantenimientomaquina_condicioniva (OID = 72202) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT fk_proveedormantenimientomaquina_condicioniva FOREIGN KEY (condicioniva) REFERENCES condicioniva(idcondicioniva);
--
-- Definition for index fk_proveedormantenimientomaquina_domicilio (OID = 72207) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT fk_proveedormantenimientomaquina_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_proveedormantenimientomaquina_responsable (OID = 72212) : 
--
ALTER TABLE ONLY proveedormantenimientomaquina
    ADD CONSTRAINT fk_proveedormantenimientomaquina_responsable FOREIGN KEY (responsable) REFERENCES responsable(idresponsable);
--
-- Definition for index fk_detallepiezapresupuesto_iddetalleproductopresupuesto (OID = 72217) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT fk_detallepiezapresupuesto_iddetalleproductopresupuesto FOREIGN KEY (iddetalleproductopresupuesto) REFERENCES detalleproductopresupuesto(iddetalle);
--
-- Definition for index fk_detallepiezapresupuesto_idetapa (OID = 72222) : 
--
ALTER TABLE ONLY detallepiezapresupuesto
    ADD CONSTRAINT fk_detallepiezapresupuesto_idetapa FOREIGN KEY (idetapa) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_ejecucionplanificacionproduccion_estado (OID = 72227) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT fk_ejecucionplanificacionproduccion_estado FOREIGN KEY (estado) REFERENCES estadoejecplanifpedido(idestado);
--
-- Definition for index fk_ejecucionplanificacionproduccion_idplanificacionproduccion (OID = 72232) : 
--
ALTER TABLE ONLY ejecucionplanificacionproduccion
    ADD CONSTRAINT fk_ejecucionplanificacionproduccion_idplanificacionproduccion FOREIGN KEY (idplanificacionproduccion) REFERENCES planificacionproduccion(idplanificacionproduccion);
--
-- Definition for index fk_detallepresupuesto_idpresupuesto (OID = 72237) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT fk_detallepresupuesto_idpresupuesto FOREIGN KEY (idpresupuesto) REFERENCES presupuesto(idpresupuesto);
--
-- Definition for index fk_detallepresupuesto_idproducto (OID = 72242) : 
--
ALTER TABLE ONLY detallepresupuesto
    ADD CONSTRAINT fk_detallepresupuesto_idproducto FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Definition for index fk_domicilio_barrio (OID = 72247) : 
--
ALTER TABLE ONLY domicilio
    ADD CONSTRAINT fk_domicilio_barrio FOREIGN KEY (barrio) REFERENCES barrio(idbarrio);
--
-- Definition for index fk_empleado_cargo (OID = 72252) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_cargo FOREIGN KEY (cargo) REFERENCES cargo(idcargo);
--
-- Definition for index fk_empleado_categoria (OID = 72257) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_categoria FOREIGN KEY (categoria) REFERENCES categoria(idcategoria);
--
-- Definition for index fk_empleado_domicilio (OID = 72262) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(iddomicilio);
--
-- Definition for index fk_empleado_tipodocumento (OID = 72267) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_tipodocumento FOREIGN KEY (tipodocumento) REFERENCES tipodocumento(idtipodocumento);
--
-- Definition for index fk_empleado_usuario (OID = 72272) : 
--
ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_detalleplanprocedimientos_idetapaproduccion (OID = 72277) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT fk_detalleplanprocedimientos_idetapaproduccion FOREIGN KEY (idetapaproduccion) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index fk_detalleplanprocedimientos_idplanpprocedimientos (OID = 72282) : 
--
ALTER TABLE ONLY detalleplanprocedimientos
    ADD CONSTRAINT fk_detalleplanprocedimientos_idplanpprocedimientos FOREIGN KEY (idplanpprocedimientos) REFERENCES planprocedimientos(idplanprocedimientos);
--
-- Definition for index fk_reclamocliente_cliente (OID = 72287) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT fk_reclamocliente_cliente FOREIGN KEY (cliente) REFERENCES cliente(idcliente);
--
-- Definition for index fk_reclamocliente_tiporeclamo (OID = 72292) : 
--
ALTER TABLE ONLY reclamocliente
    ADD CONSTRAINT fk_reclamocliente_tiporeclamo FOREIGN KEY (tiporeclamo) REFERENCES tiporeclamo(idtiporeclamo);
--
-- Definition for index fk_comprobantepago_usuario (OID = 72297) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT fk_comprobantepago_usuario FOREIGN KEY (usuario) REFERENCES usuario(idusuario);
--
-- Definition for index fk_comprobantepago_factura (OID = 72302) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT fk_comprobantepago_factura FOREIGN KEY (factura) REFERENCES factura(idfactura);
--
-- Definition for index fk_comprobantepago_formadepago (OID = 72307) : 
--
ALTER TABLE ONLY comprobantepago
    ADD CONSTRAINT fk_comprobantepago_formadepago FOREIGN KEY (formadepago) REFERENCES formadepago(idformapago);
--
-- Definition for index fk_mpasignadaxpiezareal_iddetallempasignada (OID = 72312) : 
--
ALTER TABLE ONLY mpasignadaxpiezareal
    ADD CONSTRAINT fk_mpasignadaxpiezareal_iddetallempasignada FOREIGN KEY (iddetallempasignada) REFERENCES detallempasignada(id);
--
-- Definition for index fk_detalleproductopresupuesto_idpieza (OID = 72317) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT fk_detalleproductopresupuesto_idpieza FOREIGN KEY (idpieza) REFERENCES pieza(idpieza);
--
-- Definition for index fk_detalleproductopresupuesto_iddetallepresupuesto (OID = 72322) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT fk_detalleproductopresupuesto_iddetallepresupuesto FOREIGN KEY (iddetallepresupuesto) REFERENCES detallepresupuesto(iddetalle);
--
-- Definition for index fk_detalleproductopresupuesto_idmateriaprima (OID = 72327) : 
--
ALTER TABLE ONLY detalleproductopresupuesto
    ADD CONSTRAINT fk_detalleproductopresupuesto_idmateriaprima FOREIGN KEY (idmateriaprima) REFERENCES materiaprima(idmateriaprima);
--
-- Definition for index fk_detalletrabajotercerizado_estado (OID = 72332) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT fk_detalletrabajotercerizado_estado FOREIGN KEY (estado) REFERENCES estadodetalletrabajotercerizado(idestado);
--
-- Definition for index fk_detalletrabajotercerizado_idtrabajotercerizado (OID = 72337) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT fk_detalletrabajotercerizado_idtrabajotercerizado FOREIGN KEY (idtrabajotercerizado) REFERENCES trabajotercerizado(idtrabajo);
--
-- Definition for index fk_detalletrabajotercerizado_proceso (OID = 72342) : 
--
ALTER TABLE ONLY detalletrabajotercerizado
    ADD CONSTRAINT fk_detalletrabajotercerizado_proceso FOREIGN KEY (proceso) REFERENCES etapadeproduccion(idetapaproduccion);
--
-- Definition for index ejecucionetapaproduccion_pkey (OID = 72355) : 
--
ALTER TABLE ONLY ejecucionetapaproduccion
    ADD CONSTRAINT ejecucionetapaproduccion_pkey PRIMARY KEY (id);
--
-- Definition for index detalleejecucionplanificacion_fk (OID = 72363) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk FOREIGN KEY (idejecucionplanificacionproduccion) REFERENCES ejecucionplanificacionproduccion(idejecucion);
--
-- Definition for index detalleejecucionplanificacion_fk1 (OID = 72368) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk1 FOREIGN KEY (pieza) REFERENCES pieza(idpieza);
--
-- Definition for index detalleejecucionplanificacion_fk2 (OID = 72373) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk2 FOREIGN KEY (ejecucionetapa) REFERENCES ejecucionetapaproduccion(id);
--
-- Definition for index detalleejecucionplanificacion_fk3 (OID = 72378) : 
--
ALTER TABLE ONLY detalleejecucionplanificacion
    ADD CONSTRAINT detalleejecucionplanificacion_fk3 FOREIGN KEY (piezareal) REFERENCES piezareal(idpiezareal);
--
-- Definition for index etapadeproduccion_fk (OID = 72408) : 
--
ALTER TABLE ONLY etapadeproduccion
    ADD CONSTRAINT etapadeproduccion_fk FOREIGN KEY (maquina) REFERENCES maquina(idmaquina);
--
-- Definition for index detalleplanificacionproduccion_fk4 (OID = 72414) : 
--
ALTER TABLE ONLY detalleplanificacionproduccion
    ADD CONSTRAINT detalleplanificacionproduccion_fk4 FOREIGN KEY (idproducto) REFERENCES producto(idproducto);
--
-- Data for sequence public.prueba_id_seq (OID = 29084)
--
SELECT pg_catalog.setval('prueba_id_seq', 1, false);
--
-- Data for sequence public.usuario_idusuario_seq (OID = 29090)
--
SELECT pg_catalog.setval('usuario_idusuario_seq', 6, true);
--
-- Data for sequence public.tipomaterial_idtipomaterial_seq (OID = 29092)
--
SELECT pg_catalog.setval('tipomaterial_idtipomaterial_seq', 5, true);
--
-- Data for sequence public.accioncalidad_idaccioncalidad_seq (OID = 29094)
--
SELECT pg_catalog.setval('accioncalidad_idaccioncalidad_seq', 3, true);
--
-- Data for sequence public.barrio_idbarrio_seq (OID = 29096)
--
SELECT pg_catalog.setval('barrio_idbarrio_seq', 3, true);
--
-- Data for sequence public.cargo_idcargo_seq (OID = 29098)
--
SELECT pg_catalog.setval('cargo_idcargo_seq', 1, true);
--
-- Data for sequence public.categoria_idcategoria_seq (OID = 29100)
--
SELECT pg_catalog.setval('categoria_idcategoria_seq', 1, true);
--
-- Data for sequence public.cliente_idcliente_seq (OID = 29102)
--
SELECT pg_catalog.setval('cliente_idcliente_seq', 16, true);
--
-- Data for sequence public.codigodebarra_idcodigo_seq (OID = 29104)
--
SELECT pg_catalog.setval('codigodebarra_idcodigo_seq', 41, true);
--
-- Data for sequence public.compra_idcompra_seq (OID = 29106)
--
SELECT pg_catalog.setval('compra_idcompra_seq', 1, false);
--
-- Data for sequence public.comprobantepago_idcomprobantepago_seq (OID = 29108)
--
SELECT pg_catalog.setval('comprobantepago_idcomprobantepago_seq', 1, false);
--
-- Data for sequence public.condicioniva_idcondicioniva_seq (OID = 29110)
--
SELECT pg_catalog.setval('condicioniva_idcondicioniva_seq', 3, true);
--
-- Data for sequence public.detalleplanificacionproduccion_id_seq (OID = 29112)
--
SELECT pg_catalog.setval('detalleplanificacionproduccion_id_seq', 34, true);
--
-- Data for sequence public.detallecompra_iddetalle_seq (OID = 29114)
--
SELECT pg_catalog.setval('detallecompra_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleejecucionplanificacion_iddetalle_seq (OID = 29116)
--
SELECT pg_catalog.setval('detalleejecucionplanificacion_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleejecucionplanificacioncalidad_iddetalle_seq (OID = 29118)
--
SELECT pg_catalog.setval('detalleejecucionplanificacioncalidad_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallefactura_iddetalle_seq (OID = 29120)
--
SELECT pg_catalog.setval('detallefactura_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallemantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 29122)
--
SELECT pg_catalog.setval('detallemantenimientocorrectivo_idmantenimientocorrectivo_seq', 1, false);
--
-- Data for sequence public.detallemantenimientopreventivo_idmantenimientopreventivo_seq (OID = 29124)
--
SELECT pg_catalog.setval('detallemantenimientopreventivo_idmantenimientopreventivo_seq', 1, false);
--
-- Data for sequence public.detallepedido_iddetalle_seq (OID = 29126)
--
SELECT pg_catalog.setval('detallepedido_iddetalle_seq', 32, true);
--
-- Data for sequence public.detalleplanificacioncalidad_iddetalle_seq (OID = 29128)
--
SELECT pg_catalog.setval('detalleplanificacioncalidad_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleplanprocedimientos_iddetalle_seq (OID = 29130)
--
SELECT pg_catalog.setval('detalleplanprocedimientos_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleplanprocesoscalidad_iddetalle_seq (OID = 29132)
--
SELECT pg_catalog.setval('detalleplanprocesoscalidad_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallepresupuesto_iddetalle_seq (OID = 29134)
--
SELECT pg_catalog.setval('detallepresupuesto_iddetalle_seq', 37, true);
--
-- Data for sequence public.detalleproducto_iddetalle_seq (OID = 29136)
--
SELECT pg_catalog.setval('detalleproducto_iddetalle_seq', 19, true);
--
-- Data for sequence public.detalleproductoreal_iddetalle_seq (OID = 29138)
--
SELECT pg_catalog.setval('detalleproductoreal_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallereclamocliente_iddetalle_seq (OID = 29140)
--
SELECT pg_catalog.setval('detallereclamocliente_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallereclamoempresametalurgica_iddetalle_seq (OID = 29142)
--
SELECT pg_catalog.setval('detallereclamoempresametalurgica_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallereclamoproveedor_iddetalle_seq (OID = 29144)
--
SELECT pg_catalog.setval('detallereclamoproveedor_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleremito_iddetalle_seq (OID = 29146)
--
SELECT pg_catalog.setval('detalleremito_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallerequerimientosmateriaprima_iddetalle_seq (OID = 29148)
--
SELECT pg_catalog.setval('detallerequerimientosmateriaprima_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalletrabajotercerizado_iddetalle_seq (OID = 29150)
--
SELECT pg_catalog.setval('detalletrabajotercerizado_iddetalle_seq', 1, false);
--
-- Data for sequence public.domicilio_iddomicilio_seq (OID = 29152)
--
SELECT pg_catalog.setval('domicilio_iddomicilio_seq', 40, true);
--
-- Data for sequence public.ejecucionetapaproduccion_idejecucion_seq (OID = 29154)
--
SELECT pg_catalog.setval('ejecucionetapaproduccion_idejecucion_seq', 1, false);
--
-- Data for sequence public.ejecucionplanificacioncalidad_idejecucion_seq (OID = 29156)
--
SELECT pg_catalog.setval('ejecucionplanificacioncalidad_idejecucion_seq', 1, false);
--
-- Data for sequence public.ejecucionplanificacionproduccion_idejecucion_seq (OID = 29158)
--
SELECT pg_catalog.setval('ejecucionplanificacionproduccion_idejecucion_seq', 7, true);
--
-- Data for sequence public.ejecucionprocesocalidad_idejecucion_seq (OID = 29160)
--
SELECT pg_catalog.setval('ejecucionprocesocalidad_idejecucion_seq', 1, false);
--
-- Data for sequence public.empleado_idempleado_seq (OID = 29162)
--
SELECT pg_catalog.setval('empleado_idempleado_seq', 2, true);
--
-- Data for sequence public.empresametalurgica_idempresametalurgica_seq (OID = 29164)
--
SELECT pg_catalog.setval('empresametalurgica_idempresametalurgica_seq', 1, false);
--
-- Data for sequence public.estadocliente_idestado_seq (OID = 29166)
--
SELECT pg_catalog.setval('estadocliente_idestado_seq', 4, true);
--
-- Data for sequence public.estadocompra_idestado_seq (OID = 29168)
--
SELECT pg_catalog.setval('estadocompra_idestado_seq', 1, false);
--
-- Data for sequence public.estadodetallecompra_idestado_seq (OID = 29170)
--
SELECT pg_catalog.setval('estadodetallecompra_idestado_seq', 1, false);
--
-- Data for sequence public.estadodetalletrabajotercerizado_idestado_seq (OID = 29172)
--
SELECT pg_catalog.setval('estadodetalletrabajotercerizado_idestado_seq', 1, false);
--
-- Data for sequence public.estadoejecetapaprod_idestado_seq (OID = 29174)
--
SELECT pg_catalog.setval('estadoejecetapaprod_idestado_seq', 1, false);
--
-- Data for sequence public.estadoejecplancalidad_idestado_seq (OID = 29176)
--
SELECT pg_catalog.setval('estadoejecplancalidad_idestado_seq', 1, false);
--
-- Data for sequence public.estadoejecplanifpedido_idestado_seq (OID = 29178)
--
SELECT pg_catalog.setval('estadoejecplanifpedido_idestado_seq', 1, true);
--
-- Data for sequence public.estadoejecucionprocesocalidad_idestado_seq (OID = 29180)
--
SELECT pg_catalog.setval('estadoejecucionprocesocalidad_idestado_seq', 1, false);
--
-- Data for sequence public.estadofactura_idestado_seq (OID = 29182)
--
SELECT pg_catalog.setval('estadofactura_idestado_seq', 1, false);
--
-- Data for sequence public.estadomaquina_idestado_seq (OID = 29184)
--
SELECT pg_catalog.setval('estadomaquina_idestado_seq', 1, true);
--
-- Data for sequence public.estadopedido_idestado_seq (OID = 29186)
--
SELECT pg_catalog.setval('estadopedido_idestado_seq', 18, true);
--
-- Data for sequence public.estadopiezareal_idestado_seq (OID = 29188)
--
SELECT pg_catalog.setval('estadopiezareal_idestado_seq', 1, true);
--
-- Data for sequence public.estadoproductoreal_idestado_seq (OID = 29190)
--
SELECT pg_catalog.setval('estadoproductoreal_idestado_seq', 1, false);
--
-- Data for sequence public.estadoremito_idestado_seq (OID = 29192)
--
SELECT pg_catalog.setval('estadoremito_idestado_seq', 1, false);
--
-- Data for sequence public.estadotrabajotercerizado_idestado_seq (OID = 29194)
--
SELECT pg_catalog.setval('estadotrabajotercerizado_idestado_seq', 1, false);
--
-- Data for sequence public.etapadeproduccion_idetapaproduccion_seq (OID = 29196)
--
SELECT pg_catalog.setval('etapadeproduccion_idetapaproduccion_seq', 5, true);
--
-- Data for sequence public.factura_idfactura_seq (OID = 29198)
--
SELECT pg_catalog.setval('factura_idfactura_seq', 1, false);
--
-- Data for sequence public.formadepago_idformapago_seq (OID = 29200)
--
SELECT pg_catalog.setval('formadepago_idformapago_seq', 1, false);
--
-- Data for sequence public.localidad_idlocalidad_seq (OID = 29202)
--
SELECT pg_catalog.setval('localidad_idlocalidad_seq', 11, true);
--
-- Data for sequence public.mantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 29204)
--
SELECT pg_catalog.setval('mantenimientocorrectivo_idmantenimientocorrectivo_seq', 1, false);
--
-- Data for sequence public.mantenimientopreventivo_idmantenimientopreventivo_seq (OID = 29206)
--
SELECT pg_catalog.setval('mantenimientopreventivo_idmantenimientopreventivo_seq', 1, false);
--
-- Data for sequence public.maquina_idmaquina_seq (OID = 29208)
--
SELECT pg_catalog.setval('maquina_idmaquina_seq', 1, true);
--
-- Data for sequence public.marca_idmarca_seq (OID = 29210)
--
SELECT pg_catalog.setval('marca_idmarca_seq', 1, true);
--
-- Data for sequence public.materiaprima_idmateriaprima_seq (OID = 29212)
--
SELECT pg_catalog.setval('materiaprima_idmateriaprima_seq', 9, true);
--
-- Data for sequence public.matriz_idmatriz_seq (OID = 29214)
--
SELECT pg_catalog.setval('matriz_idmatriz_seq', 4, true);
--
-- Data for sequence public.pedido_idpedido_seq (OID = 29216)
--
SELECT pg_catalog.setval('pedido_idpedido_seq', 25, true);
--
-- Data for sequence public.pedidomatriz_idpedidomatriz_seq (OID = 29218)
--
SELECT pg_catalog.setval('pedidomatriz_idpedidomatriz_seq', 1, false);
--
-- Data for sequence public.pieza_idpieza_seq (OID = 29220)
--
SELECT pg_catalog.setval('pieza_idpieza_seq', 15, true);
--
-- Data for sequence public.piezareal_idpiezareal_seq (OID = 29222)
--
SELECT pg_catalog.setval('piezareal_idpiezareal_seq', 1, false);
--
-- Data for sequence public.planificacioncalidad_idplanificacion_seq (OID = 29224)
--
SELECT pg_catalog.setval('planificacioncalidad_idplanificacion_seq', 1, false);
--
-- Data for sequence public.planificacionproduccion_idplanificacionproduccion_seq (OID = 29226)
--
SELECT pg_catalog.setval('planificacionproduccion_idplanificacionproduccion_seq', 27, true);
--
-- Data for sequence public.plano_idplano_seq (OID = 29228)
--
SELECT pg_catalog.setval('plano_idplano_seq', 1, false);
--
-- Data for sequence public.planprocedimientos_idplanprocedimientos_seq (OID = 29230)
--
SELECT pg_catalog.setval('planprocedimientos_idplanprocedimientos_seq', 1, false);
--
-- Data for sequence public.planprocesoscalidad_idplanprocesoscalidad_seq (OID = 29232)
--
SELECT pg_catalog.setval('planprocesoscalidad_idplanprocesoscalidad_seq', 1, false);
--
-- Data for sequence public.planrequerimientosmateriaprima_idplanrequerimientosmateriaprima (OID = 29234)
--
SELECT pg_catalog.setval('planrequerimientosmateriaprima_idplanrequerimientosmateriaprima', 1, false);
--
-- Data for sequence public.presupuesto_idpresupuesto_seq (OID = 29236)
--
SELECT pg_catalog.setval('presupuesto_idpresupuesto_seq', 38, true);
--
-- Data for sequence public.prioridad_idprioridad_seq (OID = 29238)
--
SELECT pg_catalog.setval('prioridad_idprioridad_seq', 3, true);
--
-- Data for sequence public.privilegio_idprivilegio_seq (OID = 29240)
--
SELECT pg_catalog.setval('privilegio_idprivilegio_seq', 1, false);
--
-- Data for sequence public.procesocalidad_idprocesocalidad_seq (OID = 29242)
--
SELECT pg_catalog.setval('procesocalidad_idprocesocalidad_seq', 2, true);
--
-- Data for sequence public.producto_idproducto_seq (OID = 29244)
--
SELECT pg_catalog.setval('producto_idproducto_seq', 11, true);
--
-- Data for sequence public.productoreal_idproductoreal_seq (OID = 29246)
--
SELECT pg_catalog.setval('productoreal_idproductoreal_seq', 1, false);
--
-- Data for sequence public.proveedor_idproveedor_seq (OID = 29248)
--
SELECT pg_catalog.setval('proveedor_idproveedor_seq', 3, true);
--
-- Data for sequence public.proveedormantenimientomaquina_idproveedormantenimiento_seq (OID = 29250)
--
SELECT pg_catalog.setval('proveedormantenimientomaquina_idproveedormantenimiento_seq', 1, false);
--
-- Data for sequence public.provincia_idprovincia_seq (OID = 29252)
--
SELECT pg_catalog.setval('provincia_idprovincia_seq', 14, true);
--
-- Data for sequence public.reclamocliente_idreclamo_seq (OID = 29254)
--
SELECT pg_catalog.setval('reclamocliente_idreclamo_seq', 1, false);
--
-- Data for sequence public.reclamoempresametalurgica_idreclamo_seq (OID = 29256)
--
SELECT pg_catalog.setval('reclamoempresametalurgica_idreclamo_seq', 1, false);
--
-- Data for sequence public.reclamoproveedor_idreclamo_seq (OID = 29258)
--
SELECT pg_catalog.setval('reclamoproveedor_idreclamo_seq', 1, false);
--
-- Data for sequence public.remito_idremito_seq (OID = 29260)
--
SELECT pg_catalog.setval('remito_idremito_seq', 1, false);
--
-- Data for sequence public.responsable_idresponsable_seq (OID = 29262)
--
SELECT pg_catalog.setval('responsable_idresponsable_seq', 17, true);
--
-- Data for sequence public.rol_idrol_seq (OID = 29264)
--
SELECT pg_catalog.setval('rol_idrol_seq', 5, true);
--
-- Data for sequence public.rotura_idrotura_seq (OID = 29266)
--
SELECT pg_catalog.setval('rotura_idrotura_seq', 1, false);
--
-- Data for sequence public.servicio_idservicio_seq (OID = 29268)
--
SELECT pg_catalog.setval('servicio_idservicio_seq', 1, false);
--
-- Data for sequence public.sesion_idsesion_seq (OID = 29270)
--
SELECT pg_catalog.setval('sesion_idsesion_seq', 1, false);
--
-- Data for sequence public.tipodocumento_idtipodocumento_seq (OID = 29272)
--
SELECT pg_catalog.setval('tipodocumento_idtipodocumento_seq', 3, true);
--
-- Data for sequence public.tipoiva_idtipoiva_seq (OID = 29274)
--
SELECT pg_catalog.setval('tipoiva_idtipoiva_seq', 1, false);
--
-- Data for sequence public.tipomaquina_idtipomaquina_seq (OID = 29276)
--
SELECT pg_catalog.setval('tipomaquina_idtipomaquina_seq', 1, true);
--
-- Data for sequence public.tiporeclamo_idtiporeclamo_seq (OID = 29278)
--
SELECT pg_catalog.setval('tiporeclamo_idtiporeclamo_seq', 1, false);
--
-- Data for sequence public.trabajotercerizado_idtrabajo_seq (OID = 29280)
--
SELECT pg_catalog.setval('trabajotercerizado_idtrabajo_seq', 1, false);
--
-- Data for sequence public.turno_idturno_seq (OID = 29282)
--
SELECT pg_catalog.setval('turno_idturno_seq', 3, true);
--
-- Data for sequence public.unidadmedida_idunidadmedida_seq (OID = 29300)
--
SELECT pg_catalog.setval('unidadmedida_idunidadmedida_seq', 4, true);
--
-- Data for sequence public.detallepiezapresupuesto_iddetalle_seq (OID = 38726)
--
SELECT pg_catalog.setval('detallepiezapresupuesto_iddetalle_seq', 62, true);
--
-- Data for sequence public.detalleproductopresupuesto_iddetalle_seq (OID = 38740)
--
SELECT pg_catalog.setval('detalleproductopresupuesto_iddetalle_seq', 50, true);
--
-- Data for sequence public.detallepiezacalidadpresupuesto_iddetalle_seq (OID = 38926)
--
SELECT pg_catalog.setval('detallepiezacalidadpresupuesto_iddetalle_seq', 44, true);
--
-- Data for sequence public.calendario_id_seq (OID = 63172)
--
SELECT pg_catalog.setval('calendario_id_seq', 7, true);
--
-- Data for sequence public.disponibilidadhoraria_id_seq (OID = 63209)
--
SELECT pg_catalog.setval('disponibilidadhoraria_id_seq', 7, true);
--
-- Data for sequence public.estadoplanificacionproduccion_id_seq (OID = 63240)
--
SELECT pg_catalog.setval('estadoplanificacionproduccion_id_seq', 3, true);
--
-- Data for sequence public.maquina_idmaquina_seq1 (OID = 63308)
--
SELECT pg_catalog.setval('maquina_idmaquina_seq1', 2, true);
--
-- Data for sequence public.piezareal_idpiezareal_seq1 (OID = 63336)
--
SELECT pg_catalog.setval('piezareal_idpiezareal_seq1', 42, true);
--
-- Data for sequence public.pieza_idpieza_seq1 (OID = 63363)
--
SELECT pg_catalog.setval('pieza_idpieza_seq1', 6, true);
--
-- Data for sequence public.detallempasignada_id_seq (OID = 63386)
--
SELECT pg_catalog.setval('detallempasignada_id_seq', 8, true);
--
-- Data for sequence public.mpasignadaxpiezareal_id_seq (OID = 63404)
--
SELECT pg_catalog.setval('mpasignadaxpiezareal_id_seq', 28, true);
--
-- Comments
--
COMMENT ON SCHEMA public IS 'standard public schema';
