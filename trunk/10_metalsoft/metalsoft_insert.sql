-- SQL Manager 2007 for PostgreSQL 4.5.0.8
-- ---------------------------------------
-- Host      : localhost
-- Database  : metalsoft
-- Version   : PostgreSQL 8.3.11, compiled by Visual C++ build 1400



SET search_path = public, pg_catalog;
--
-- Data for table public.estadopedido (OID = 27225) (LIMIT 0,15)
--
INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (1, 'Generado', 'Pedido generado');

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (2, 'Presupuestado', NULL);

INSERT INTO estadopedido (idestado, nombre, descripcion)
VALUES (3, 'En espera de confirmación', NULL);

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

--
-- Data for table public.cliente (OID = 27256) (LIMIT 0,9)
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
-- Data for table public.usuario (OID = 27268) (LIMIT 0,2)
--
INSERT INTO usuario (idusuario, usuario, clave)
VALUES (1, 'Nino', 'Nino');

INSERT INTO usuario (idusuario, usuario, clave)
VALUES (2, 'admin', 'admin');

--
-- Data for table public.prioridad (OID = 27288) (LIMIT 0,3)
--
INSERT INTO prioridad (idprioridad, nombre, descripcion)
VALUES (1, 'Alta', 'Prioridad Alta');

INSERT INTO prioridad (idprioridad, nombre, descripcion)
VALUES (2, 'Baja', 'Prioridad Baja');

INSERT INTO prioridad (idprioridad, nombre, descripcion)
VALUES (3, 'Normal', 'Prioridad Normal');

--
-- Data for table public.estadocliente (OID = 27292) (LIMIT 0,3)
--
INSERT INTO estadocliente (idestado, nombre, descripcion)
VALUES (1, 'Activo', 'Dado de Alta');

INSERT INTO estadocliente (idestado, nombre, descripcion)
VALUES (2, 'Baja', 'Dado de Baja');

INSERT INTO estadocliente (idestado, nombre, descripcion)
VALUES (3, 'Moroso', 'Cliente Moroso, adeuda facturas');

--
-- Data for table public.responsable (OID = 27296) (LIMIT 0,11)
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
-- Data for table public.condicioniva (OID = 27300) (LIMIT 0,3)
--
INSERT INTO condicioniva (idcondicioniva, nombre, descripcion)
VALUES (1, 'R.Inscripto', 'Responsable Inscripto');

INSERT INTO condicioniva (idcondicioniva, nombre, descripcion)
VALUES (2, 'Monotributista', 'Monotributista');

INSERT INTO condicioniva (idcondicioniva, nombre, descripcion)
VALUES (3, 'C.Final', 'Consumidor Final');

--
-- Data for table public.domicilio (OID = 27304) (LIMIT 0,28)
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
-- Data for table public.tipodocumento (OID = 27344) (LIMIT 0,3)
--
INSERT INTO tipodocumento (idtipodocumento, tipo, nombre)
VALUES (1, 'DNI', 'Documento Nacional de Identidad');

INSERT INTO tipodocumento (idtipodocumento, tipo, nombre)
VALUES (2, 'LE', 'Libreta de Enrolamiento');

INSERT INTO tipodocumento (idtipodocumento, tipo, nombre)
VALUES (3, 'LC', 'Libreta Cívica');

--
-- Data for table public.codigodebarra (OID = 27348) (LIMIT 0,1)
--
INSERT INTO codigodebarra (idcodigo, descripcion, codigo)
VALUES (1, 'dsfvdsv', '1234');

--
-- Data for table public.materiaprima (OID = 27392) (LIMIT 0,3)
--
INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto)
VALUES (1, 'Acero', NULL, NULL, NULL, NULL, NULL, 2, NULL, 33.420, NULL, NULL, NULL);

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto)
VALUES (4, 'hgfj', NULL, NULL, 1, 3, 'jhtfhnfchn', 2, 2, 2.000, 3.000, 3.000, '6543');

INSERT INTO materiaprima (idmateriaprima, nombre, fechaalta, fechabaja, codbarra, stock, descripcion, tipomaterial, unidadmedida, alto, largo, ancho, codproducto)
VALUES (5, 'hgfj', NULL, NULL, 1, 3, 'jhtfhnfchn', 2, 2, 2.000, 3.000, 3.500, '6543');

--
-- Data for table public.matriz (OID = 27396) (LIMIT 0,1)
--
INSERT INTO matriz (idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial)
VALUES (1, 123, 'Matriz1', 'Matriz	', NULL, NULL, 1, 4);

--
-- Data for table public.pieza (OID = 27400) (LIMIT 0,5)
--
INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo)
VALUES (8, 'volante', 2, NULL, 1, NULL, NULL, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo)
VALUES (6, 'Mango', 2, NULL, NULL, NULL, NULL, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo)
VALUES (9, 'Eje', 4, NULL, 1, NULL, NULL, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo)
VALUES (10, 'Panel', 4, NULL, NULL, NULL, NULL, NULL);

INSERT INTO pieza (idpieza, nombre, tipomaterial, materiaprima, matriz, alto, ancho, largo)
VALUES (11, 'nino', 2, 4, 1, 3.330, 4.000, 3.300);

--
-- Data for table public.producto (OID = 27404) (LIMIT 0,4)
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

--
-- Data for table public.etapadeproduccion (OID = 27424) (LIMIT 0,2)
--
INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, maquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (1, 2, 'soldar', '04:00:00', '04:00:00', NULL, '04:00:00', '2010-08-16', 2);

INSERT INTO etapadeproduccion (idetapaproduccion, nroetapaproduccion, nombre, horasmaquina, horashombre, maquina, duracionestimada, fechacreacion, unidaddemedida)
VALUES (2, 3, 'ractificacion', '04:00:00', '04:00:00', NULL, '04:00:00', '2010-12-12', NULL);

--
-- Data for table public.detalleproducto (OID = 27479) (LIMIT 0,8)
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

--
-- Data for table public.provincia (OID = 27546) (LIMIT 0,7)
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
-- Data for table public.localidad (OID = 27550) (LIMIT 0,10)
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
-- Data for table public.barrio (OID = 27554) (LIMIT 0,3)
--
INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (1, 'Nva. Córdoba', 5000, 1);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (2, 'Alta Córdoba', 5000, 1);

INSERT INTO barrio (idbarrio, nombre, codpostal, localidad)
VALUES (3, 'Latinoamérica', 5220, 2);

--
-- Data for table public.tipomaterial (OID = 27628) (LIMIT 0,2)
--
INSERT INTO tipomaterial (idtipomaterial, nombre, descripcion)
VALUES (2, 'otro mas', 'algun ootr');

INSERT INTO tipomaterial (idtipomaterial, nombre, descripcion)
VALUES (4, 'Chapa', 'Chapa de acero inoxidable');

--
-- Data for table public.unidadmedida (OID = 27842) (LIMIT 0,3)
--
INSERT INTO unidadmedida (idunidadmedida, nombre, descripcion)
VALUES (1, 'mm', 'milímetros');

INSERT INTO unidadmedida (idunidadmedida, nombre, descripcion)
VALUES (2, 'cm', 'centímetros');

INSERT INTO unidadmedida (idunidadmedida, nombre, descripcion)
VALUES (3, 'mt', 'metros');

--
-- Data for sequence public.prueba_id_seq (OID = 27632)
--
SELECT pg_catalog.setval('prueba_id_seq', 1, false);
--
-- Data for sequence public.usuario_idusuario_seq (OID = 27638)
--
SELECT pg_catalog.setval('usuario_idusuario_seq', 6, true);
--
-- Data for sequence public.tipomaterial_idtipomaterial_seq (OID = 27640)
--
SELECT pg_catalog.setval('tipomaterial_idtipomaterial_seq', 4, true);
--
-- Data for sequence public.accioncalidad_idaccioncalidad_seq (OID = 27642)
--
SELECT pg_catalog.setval('accioncalidad_idaccioncalidad_seq', 1, false);
--
-- Data for sequence public.barrio_idbarrio_seq (OID = 27644)
--
SELECT pg_catalog.setval('barrio_idbarrio_seq', 3, true);
--
-- Data for sequence public.cargo_idcargo_seq (OID = 27646)
--
SELECT pg_catalog.setval('cargo_idcargo_seq', 1, false);
--
-- Data for sequence public.categoria_idcategoria_seq (OID = 27648)
--
SELECT pg_catalog.setval('categoria_idcategoria_seq', 1, false);
--
-- Data for sequence public.cliente_idcliente_seq (OID = 27650)
--
SELECT pg_catalog.setval('cliente_idcliente_seq', 14, true);
--
-- Data for sequence public.codigodebarra_idcodigo_seq (OID = 27652)
--
SELECT pg_catalog.setval('codigodebarra_idcodigo_seq', 1, true);
--
-- Data for sequence public.compra_idcompra_seq (OID = 27654)
--
SELECT pg_catalog.setval('compra_idcompra_seq', 1, false);
--
-- Data for sequence public.comprobantepago_idcomprobantepago_seq (OID = 27656)
--
SELECT pg_catalog.setval('comprobantepago_idcomprobantepago_seq', 1, false);
--
-- Data for sequence public.condicioniva_idcondicioniva_seq (OID = 27658)
--
SELECT pg_catalog.setval('condicioniva_idcondicioniva_seq', 3, true);
--
-- Data for sequence public.detallaplanificacionproduccion_iddetalle_seq (OID = 27660)
--
SELECT pg_catalog.setval('detallaplanificacionproduccion_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallecompra_iddetalle_seq (OID = 27662)
--
SELECT pg_catalog.setval('detallecompra_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleejecucionplanificacion_iddetalle_seq (OID = 27664)
--
SELECT pg_catalog.setval('detalleejecucionplanificacion_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleejecucionplanificacioncalidad_iddetalle_seq (OID = 27666)
--
SELECT pg_catalog.setval('detalleejecucionplanificacioncalidad_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallefactura_iddetalle_seq (OID = 27668)
--
SELECT pg_catalog.setval('detallefactura_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallemantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 27670)
--
SELECT pg_catalog.setval('detallemantenimientocorrectivo_idmantenimientocorrectivo_seq', 1, false);
--
-- Data for sequence public.detallemantenimientopreventivo_idmantenimientopreventivo_seq (OID = 27672)
--
SELECT pg_catalog.setval('detallemantenimientopreventivo_idmantenimientopreventivo_seq', 1, false);
--
-- Data for sequence public.detallepedido_iddetalle_seq (OID = 27674)
--
SELECT pg_catalog.setval('detallepedido_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleplanificacioncalidad_iddetalle_seq (OID = 27676)
--
SELECT pg_catalog.setval('detalleplanificacioncalidad_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleplanprocedimientos_iddetalle_seq (OID = 27678)
--
SELECT pg_catalog.setval('detalleplanprocedimientos_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleplanprocesoscalidad_iddetalle_seq (OID = 27680)
--
SELECT pg_catalog.setval('detalleplanprocesoscalidad_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallepresupuesto_iddetalle_seq (OID = 27682)
--
SELECT pg_catalog.setval('detallepresupuesto_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleproducto_iddetalle_seq (OID = 27684)
--
SELECT pg_catalog.setval('detalleproducto_iddetalle_seq', 13, true);
--
-- Data for sequence public.detalleproductoreal_iddetalle_seq (OID = 27686)
--
SELECT pg_catalog.setval('detalleproductoreal_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallereclamocliente_iddetalle_seq (OID = 27688)
--
SELECT pg_catalog.setval('detallereclamocliente_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallereclamoempresametalurgica_iddetalle_seq (OID = 27690)
--
SELECT pg_catalog.setval('detallereclamoempresametalurgica_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallereclamoproveedor_iddetalle_seq (OID = 27692)
--
SELECT pg_catalog.setval('detallereclamoproveedor_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalleremito_iddetalle_seq (OID = 27694)
--
SELECT pg_catalog.setval('detalleremito_iddetalle_seq', 1, false);
--
-- Data for sequence public.detallerequerimientosmateriaprima_iddetalle_seq (OID = 27696)
--
SELECT pg_catalog.setval('detallerequerimientosmateriaprima_iddetalle_seq', 1, false);
--
-- Data for sequence public.detalletrabajotercerizado_iddetalle_seq (OID = 27698)
--
SELECT pg_catalog.setval('detalletrabajotercerizado_iddetalle_seq', 1, false);
--
-- Data for sequence public.domicilio_iddomicilio_seq (OID = 27700)
--
SELECT pg_catalog.setval('domicilio_iddomicilio_seq', 32, true);
--
-- Data for sequence public.ejecucionetapaproduccion_idejecucion_seq (OID = 27702)
--
SELECT pg_catalog.setval('ejecucionetapaproduccion_idejecucion_seq', 1, false);
--
-- Data for sequence public.ejecucionplanificacioncalidad_idejecucion_seq (OID = 27704)
--
SELECT pg_catalog.setval('ejecucionplanificacioncalidad_idejecucion_seq', 1, false);
--
-- Data for sequence public.ejecucionplanificacionproduccion_idejecucion_seq (OID = 27706)
--
SELECT pg_catalog.setval('ejecucionplanificacionproduccion_idejecucion_seq', 1, false);
--
-- Data for sequence public.ejecucionprocesocalidad_idejecucion_seq (OID = 27708)
--
SELECT pg_catalog.setval('ejecucionprocesocalidad_idejecucion_seq', 1, false);
--
-- Data for sequence public.empleado_idempleado_seq (OID = 27710)
--
SELECT pg_catalog.setval('empleado_idempleado_seq', 1, false);
--
-- Data for sequence public.empresametalurgica_idempresametalurgica_seq (OID = 27712)
--
SELECT pg_catalog.setval('empresametalurgica_idempresametalurgica_seq', 1, false);
--
-- Data for sequence public.estadocliente_idestado_seq (OID = 27714)
--
SELECT pg_catalog.setval('estadocliente_idestado_seq', 4, true);
--
-- Data for sequence public.estadocompra_idestado_seq (OID = 27716)
--
SELECT pg_catalog.setval('estadocompra_idestado_seq', 1, false);
--
-- Data for sequence public.estadodetallecompra_idestado_seq (OID = 27718)
--
SELECT pg_catalog.setval('estadodetallecompra_idestado_seq', 1, false);
--
-- Data for sequence public.estadodetalletrabajotercerizado_idestado_seq (OID = 27720)
--
SELECT pg_catalog.setval('estadodetalletrabajotercerizado_idestado_seq', 1, false);
--
-- Data for sequence public.estadoejecetapaprod_idestado_seq (OID = 27722)
--
SELECT pg_catalog.setval('estadoejecetapaprod_idestado_seq', 1, false);
--
-- Data for sequence public.estadoejecplancalidad_idestado_seq (OID = 27724)
--
SELECT pg_catalog.setval('estadoejecplancalidad_idestado_seq', 1, false);
--
-- Data for sequence public.estadoejecplanifpedido_idestado_seq (OID = 27726)
--
SELECT pg_catalog.setval('estadoejecplanifpedido_idestado_seq', 1, false);
--
-- Data for sequence public.estadoejecucionprocesocalidad_idestado_seq (OID = 27728)
--
SELECT pg_catalog.setval('estadoejecucionprocesocalidad_idestado_seq', 1, false);
--
-- Data for sequence public.estadofactura_idestado_seq (OID = 27730)
--
SELECT pg_catalog.setval('estadofactura_idestado_seq', 1, false);
--
-- Data for sequence public.estadomaquina_idestado_seq (OID = 27732)
--
SELECT pg_catalog.setval('estadomaquina_idestado_seq', 1, false);
--
-- Data for sequence public.estadopedido_idestado_seq (OID = 27734)
--
SELECT pg_catalog.setval('estadopedido_idestado_seq', 15, true);
--
-- Data for sequence public.estadopiezareal_idestado_seq (OID = 27736)
--
SELECT pg_catalog.setval('estadopiezareal_idestado_seq', 1, false);
--
-- Data for sequence public.estadoproductoreal_idestado_seq (OID = 27738)
--
SELECT pg_catalog.setval('estadoproductoreal_idestado_seq', 1, false);
--
-- Data for sequence public.estadoremito_idestado_seq (OID = 27740)
--
SELECT pg_catalog.setval('estadoremito_idestado_seq', 1, false);
--
-- Data for sequence public.estadotrabajotercerizado_idestado_seq (OID = 27742)
--
SELECT pg_catalog.setval('estadotrabajotercerizado_idestado_seq', 1, false);
--
-- Data for sequence public.etapadeproduccion_idetapaproduccion_seq (OID = 27744)
--
SELECT pg_catalog.setval('etapadeproduccion_idetapaproduccion_seq', 2, true);
--
-- Data for sequence public.factura_idfactura_seq (OID = 27746)
--
SELECT pg_catalog.setval('factura_idfactura_seq', 1, false);
--
-- Data for sequence public.formadepago_idformapago_seq (OID = 27748)
--
SELECT pg_catalog.setval('formadepago_idformapago_seq', 1, false);
--
-- Data for sequence public.localidad_idlocalidad_seq (OID = 27750)
--
SELECT pg_catalog.setval('localidad_idlocalidad_seq', 11, true);
--
-- Data for sequence public.mantenimientocorrectivo_idmantenimientocorrectivo_seq (OID = 27752)
--
SELECT pg_catalog.setval('mantenimientocorrectivo_idmantenimientocorrectivo_seq', 1, false);
--
-- Data for sequence public.mantenimientopreventivo_idmantenimientopreventivo_seq (OID = 27754)
--
SELECT pg_catalog.setval('mantenimientopreventivo_idmantenimientopreventivo_seq', 1, false);
--
-- Data for sequence public.maquina_idmaquina_seq (OID = 27756)
--
SELECT pg_catalog.setval('maquina_idmaquina_seq', 1, false);
--
-- Data for sequence public.marca_idmarca_seq (OID = 27758)
--
SELECT pg_catalog.setval('marca_idmarca_seq', 1, false);
--
-- Data for sequence public.materiaprima_idmateriaprima_seq (OID = 27760)
--
SELECT pg_catalog.setval('materiaprima_idmateriaprima_seq', 5, true);
--
-- Data for sequence public.matriz_idmatriz_seq (OID = 27762)
--
SELECT pg_catalog.setval('matriz_idmatriz_seq', 2, true);
--
-- Data for sequence public.pedido_idpedido_seq (OID = 27764)
--
SELECT pg_catalog.setval('pedido_idpedido_seq', 1, false);
--
-- Data for sequence public.pedidomatriz_idpedidomatriz_seq (OID = 27766)
--
SELECT pg_catalog.setval('pedidomatriz_idpedidomatriz_seq', 1, false);
--
-- Data for sequence public.pieza_idpieza_seq (OID = 27768)
--
SELECT pg_catalog.setval('pieza_idpieza_seq', 11, true);
--
-- Data for sequence public.piezareal_idpiezareal_seq (OID = 27770)
--
SELECT pg_catalog.setval('piezareal_idpiezareal_seq', 1, false);
--
-- Data for sequence public.planificacioncalidad_idplanificacion_seq (OID = 27772)
--
SELECT pg_catalog.setval('planificacioncalidad_idplanificacion_seq', 1, false);
--
-- Data for sequence public.planificacionproduccion_idplanificacionproduccion_seq (OID = 27774)
--
SELECT pg_catalog.setval('planificacionproduccion_idplanificacionproduccion_seq', 1, false);
--
-- Data for sequence public.plano_idplano_seq (OID = 27776)
--
SELECT pg_catalog.setval('plano_idplano_seq', 1, false);
--
-- Data for sequence public.planprocedimientos_idplanprocedimientos_seq (OID = 27778)
--
SELECT pg_catalog.setval('planprocedimientos_idplanprocedimientos_seq', 1, false);
--
-- Data for sequence public.planprocesoscalidad_idplanprocesoscalidad_seq (OID = 27780)
--
SELECT pg_catalog.setval('planprocesoscalidad_idplanprocesoscalidad_seq', 1, false);
--
-- Data for sequence public.planrequerimientosmateriaprima_idplanrequerimientosmateriaprima (OID = 27782)
--
SELECT pg_catalog.setval('planrequerimientosmateriaprima_idplanrequerimientosmateriaprima', 1, false);
--
-- Data for sequence public.presupuesto_idpresupuesto_seq (OID = 27784)
--
SELECT pg_catalog.setval('presupuesto_idpresupuesto_seq', 1, false);
--
-- Data for sequence public.prioridad_idprioridad_seq (OID = 27786)
--
SELECT pg_catalog.setval('prioridad_idprioridad_seq', 3, true);
--
-- Data for sequence public.privilegio_idprivilegio_seq (OID = 27788)
--
SELECT pg_catalog.setval('privilegio_idprivilegio_seq', 1, false);
--
-- Data for sequence public.procesocalidad_idprocesocalidad_seq (OID = 27790)
--
SELECT pg_catalog.setval('procesocalidad_idprocesocalidad_seq', 1, false);
--
-- Data for sequence public.producto_idproducto_seq (OID = 27792)
--
SELECT pg_catalog.setval('producto_idproducto_seq', 6, true);
--
-- Data for sequence public.productoreal_idproductoreal_seq (OID = 27794)
--
SELECT pg_catalog.setval('productoreal_idproductoreal_seq', 1, false);
--
-- Data for sequence public.proveedor_idproveedor_seq (OID = 27796)
--
SELECT pg_catalog.setval('proveedor_idproveedor_seq', 1, false);
--
-- Data for sequence public.proveedormantenimientomaquina_idproveedormantenimiento_seq (OID = 27798)
--
SELECT pg_catalog.setval('proveedormantenimientomaquina_idproveedormantenimiento_seq', 1, false);
--
-- Data for sequence public.provincia_idprovincia_seq (OID = 27800)
--
SELECT pg_catalog.setval('provincia_idprovincia_seq', 14, true);
--
-- Data for sequence public.reclamocliente_idreclamo_seq (OID = 27802)
--
SELECT pg_catalog.setval('reclamocliente_idreclamo_seq', 1, false);
--
-- Data for sequence public.reclamoempresametalurgica_idreclamo_seq (OID = 27804)
--
SELECT pg_catalog.setval('reclamoempresametalurgica_idreclamo_seq', 1, false);
--
-- Data for sequence public.reclamoproveedor_idreclamo_seq (OID = 27806)
--
SELECT pg_catalog.setval('reclamoproveedor_idreclamo_seq', 1, false);
--
-- Data for sequence public.remito_idremito_seq (OID = 27808)
--
SELECT pg_catalog.setval('remito_idremito_seq', 1, false);
--
-- Data for sequence public.responsable_idresponsable_seq (OID = 27810)
--
SELECT pg_catalog.setval('responsable_idresponsable_seq', 13, true);
--
-- Data for sequence public.rol_idrol_seq (OID = 27812)
--
SELECT pg_catalog.setval('rol_idrol_seq', 1, false);
--
-- Data for sequence public.rotura_idrotura_seq (OID = 27814)
--
SELECT pg_catalog.setval('rotura_idrotura_seq', 1, false);
--
-- Data for sequence public.servicio_idservicio_seq (OID = 27816)
--
SELECT pg_catalog.setval('servicio_idservicio_seq', 1, false);
--
-- Data for sequence public.sesion_idsesion_seq (OID = 27818)
--
SELECT pg_catalog.setval('sesion_idsesion_seq', 1, false);
--
-- Data for sequence public.tipodocumento_idtipodocumento_seq (OID = 27820)
--
SELECT pg_catalog.setval('tipodocumento_idtipodocumento_seq', 3, true);
--
-- Data for sequence public.tipoiva_idtipoiva_seq (OID = 27822)
--
SELECT pg_catalog.setval('tipoiva_idtipoiva_seq', 1, false);
--
-- Data for sequence public.tipomaquina_idtipomaquina_seq (OID = 27824)
--
SELECT pg_catalog.setval('tipomaquina_idtipomaquina_seq', 1, false);
--
-- Data for sequence public.tiporeclamo_idtiporeclamo_seq (OID = 27826)
--
SELECT pg_catalog.setval('tiporeclamo_idtiporeclamo_seq', 1, false);
--
-- Data for sequence public.trabajotercerizado_idtrabajo_seq (OID = 27828)
--
SELECT pg_catalog.setval('trabajotercerizado_idtrabajo_seq', 1, false);
--
-- Data for sequence public.turno_idturno_seq (OID = 27830)
--
SELECT pg_catalog.setval('turno_idturno_seq', 1, false);
--
-- Data for sequence public.unidadmedida_idunidadmedida_seq (OID = 27840)
--
SELECT pg_catalog.setval('unidadmedida_idunidadmedida_seq', 3, true);
