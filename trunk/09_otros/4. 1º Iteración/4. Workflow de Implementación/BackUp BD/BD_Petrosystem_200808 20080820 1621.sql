-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.19-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema bdpetrosystem
--

CREATE DATABASE IF NOT EXISTS bdpetrosystem;
USE bdpetrosystem;

--
-- Definition of table `ambitoestado`
--

DROP TABLE IF EXISTS `ambitoestado`;
CREATE TABLE `ambitoestado` (
  `Id_AmbitoEstado` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(50) default NULL,
  PRIMARY KEY  (`Id_AmbitoEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ambitoestado`
--

/*!40000 ALTER TABLE `ambitoestado` DISABLE KEYS */;
INSERT INTO `ambitoestado` (`Id_AmbitoEstado`,`nombre`) VALUES 
 (1,'Pedido a proveedor'),
 (2,'Tanque'),
 (3,'Surtidor'),
 (4,'Detalle pedido proveedor');
/*!40000 ALTER TABLE `ambitoestado` ENABLE KEYS */;


--
-- Definition of table `ambitoestadoxestado`
--

DROP TABLE IF EXISTS `ambitoestadoxestado`;
CREATE TABLE `ambitoestadoxestado` (
  `fk_AmbitoEstado` int(10) unsigned NOT NULL default '0',
  `fk_Estado` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`fk_AmbitoEstado`,`fk_Estado`),
  KEY `FK_ambitoestadoporestado_2` (`fk_Estado`),
  CONSTRAINT `FK_ambitoestadoporestado_1` FOREIGN KEY (`fk_AmbitoEstado`) REFERENCES `ambitoestado` (`Id_AmbitoEstado`),
  CONSTRAINT `FK_ambitoestadoporestado_2` FOREIGN KEY (`fk_Estado`) REFERENCES `estado` (`Id_Estado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ambitoestadoxestado`
--

/*!40000 ALTER TABLE `ambitoestadoxestado` DISABLE KEYS */;
INSERT INTO `ambitoestadoxestado` (`fk_AmbitoEstado`,`fk_Estado`) VALUES 
 (1,1),
 (2,1),
 (3,1),
 (4,1),
 (2,2),
 (3,2),
 (2,3),
 (3,3),
 (2,4),
 (3,4),
 (2,5),
 (3,5),
 (2,6),
 (3,6),
 (1,7),
 (4,7),
 (1,8),
 (4,8),
 (1,9),
 (4,9),
 (1,10),
 (4,10);
/*!40000 ALTER TABLE `ambitoestadoxestado` ENABLE KEYS */;


--
-- Definition of table `articulominishop`
--

DROP TABLE IF EXISTS `articulominishop`;
CREATE TABLE `articulominishop` (
  `Id_ArticuloMinishop` int(10) unsigned NOT NULL auto_increment,
  `numeroCodigoBarra` double default NULL,
  `fk_Producto` int(10) unsigned default NULL,
  `fk_TipoProducto` int(10) unsigned default NULL,
  PRIMARY KEY  (`Id_ArticuloMinishop`),
  KEY `fk_ProductoArticuloMinishop` (`fk_Producto`),
  KEY `fk_TipoProducto` (`fk_TipoProducto`),
  CONSTRAINT `fk_ProductoArticuloMinishop` FOREIGN KEY (`fk_Producto`) REFERENCES `producto` (`Id_Producto`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_TipoProducto` FOREIGN KEY (`fk_TipoProducto`) REFERENCES `tipoproducto` (`Id_TipoProducto`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `articulominishop`
--

/*!40000 ALTER TABLE `articulominishop` DISABLE KEYS */;
INSERT INTO `articulominishop` (`Id_ArticuloMinishop`,`numeroCodigoBarra`,`fk_Producto`,`fk_TipoProducto`) VALUES 
 (2,12354,3,1);
/*!40000 ALTER TABLE `articulominishop` ENABLE KEYS */;


--
-- Definition of table `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
CREATE TABLE `ciudad` (
  `Id_Ciudad` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(50) default NULL,
  `fk_Provincia` int(10) unsigned default NULL,
  PRIMARY KEY  (`Id_Ciudad`),
  KEY `fk_Provinicia` (`fk_Provincia`),
  CONSTRAINT `fk_Provinicia` FOREIGN KEY (`fk_Provincia`) REFERENCES `provincia` (`Id_Provincia`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ciudad`
--

/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
INSERT INTO `ciudad` (`Id_Ciudad`,`nombre`,`fk_Provincia`) VALUES 
 (1,'Villa Carlos Paz',1),
 (2,'Villa Maria',1),
 (3,'San Rafael',2),
 (4,'Mendoza',2),
 (5,'Catamarca',4),
 (6,'Cordoba',1),
 (7,'San Javier',3);
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;


--
-- Definition of table `combustible`
--

DROP TABLE IF EXISTS `combustible`;
CREATE TABLE `combustible` (
  `Id_Combustible` int(10) unsigned NOT NULL auto_increment,
  `fk_Producto` int(10) unsigned default NULL,
  PRIMARY KEY  (`Id_Combustible`),
  KEY `fk_Producto` (`fk_Producto`),
  CONSTRAINT `fk_Producto` FOREIGN KEY (`fk_Producto`) REFERENCES `producto` (`Id_Producto`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `combustible`
--

/*!40000 ALTER TABLE `combustible` DISABLE KEYS */;
INSERT INTO `combustible` (`Id_Combustible`,`fk_Producto`) VALUES 
 (1,2);
/*!40000 ALTER TABLE `combustible` ENABLE KEYS */;


--
-- Definition of table `combustibleximpuesto`
--

DROP TABLE IF EXISTS `combustibleximpuesto`;
CREATE TABLE `combustibleximpuesto` (
  `fk_Combustible` int(10) unsigned NOT NULL,
  `fk_Impuesto` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`fk_Combustible`,`fk_Impuesto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `combustibleximpuesto`
--

/*!40000 ALTER TABLE `combustibleximpuesto` DISABLE KEYS */;
/*!40000 ALTER TABLE `combustibleximpuesto` ENABLE KEYS */;


--
-- Definition of table `condicioniva`
--

DROP TABLE IF EXISTS `condicioniva`;
CREATE TABLE `condicioniva` (
  `Id_CondicionIva` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(50) default NULL,
  `descripcion` varchar(50) default NULL,
  PRIMARY KEY  (`Id_CondicionIva`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `condicioniva`
--

/*!40000 ALTER TABLE `condicioniva` DISABLE KEYS */;
INSERT INTO `condicioniva` (`Id_CondicionIva`,`nombre`,`descripcion`) VALUES 
 (1,'Responsable Inscripto','discrimina iva'),
 (2,'Monotributista',' no discrimina iva');
/*!40000 ALTER TABLE `condicioniva` ENABLE KEYS */;


--
-- Definition of table `cuentacorrienteproveedor`
--

DROP TABLE IF EXISTS `cuentacorrienteproveedor`;
CREATE TABLE `cuentacorrienteproveedor` (
  `Id_CuentaCorrienteProveedor` int(10) unsigned NOT NULL auto_increment,
  PRIMARY KEY  (`Id_CuentaCorrienteProveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cuentacorrienteproveedor`
--

/*!40000 ALTER TABLE `cuentacorrienteproveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuentacorrienteproveedor` ENABLE KEYS */;


--
-- Definition of table `datosempresa`
--

DROP TABLE IF EXISTS `datosempresa`;
CREATE TABLE `datosempresa` (
  `cuit` double NOT NULL,
  `razonSocial` varchar(50) NOT NULL,
  `fk_Domicilio` int(10) unsigned default NULL,
  PRIMARY KEY  (`cuit`),
  KEY `FK_datosempresa_1` (`fk_Domicilio`),
  CONSTRAINT `FK_datosempresa_1` FOREIGN KEY (`fk_Domicilio`) REFERENCES `domicilio` (`Id_Domicilio`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 10240 kB; (`fk_Domicilio`) REFER `bdpetrosystem';

--
-- Dumping data for table `datosempresa`
--

/*!40000 ALTER TABLE `datosempresa` DISABLE KEYS */;
INSERT INTO `datosempresa` (`cuit`,`razonSocial`,`fk_Domicilio`) VALUES 
 (20234567892,'PETROBOR S.R.L.',1);
/*!40000 ALTER TABLE `datosempresa` ENABLE KEYS */;


--
-- Definition of table `descarga`
--

DROP TABLE IF EXISTS `descarga`;
CREATE TABLE `descarga` (
  `Id_Descarga` int(10) unsigned NOT NULL auto_increment,
  `cantidadLitros` double default NULL,
  `fechaDescarga` datetime default NULL,
  `horaDescarga` varchar(50) default NULL,
  `fk_Tanque` int(10) unsigned default NULL,
  `fk_DetalleRecepcion` int(10) unsigned default NULL,
  PRIMARY KEY  (`Id_Descarga`),
  KEY `fk_TanqueDescarga` (`fk_Tanque`),
  KEY `fk_DetalleRecepcion` (`fk_DetalleRecepcion`),
  CONSTRAINT `fk_DetalleRecepcion` FOREIGN KEY (`fk_DetalleRecepcion`) REFERENCES `detallerecepcion` (`Id_DetalleRecepcion`),
  CONSTRAINT `fk_TanqueDescarga` FOREIGN KEY (`fk_Tanque`) REFERENCES `tanque` (`Id_Tanque`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `descarga`
--

/*!40000 ALTER TABLE `descarga` DISABLE KEYS */;
/*!40000 ALTER TABLE `descarga` ENABLE KEYS */;


--
-- Definition of table `detallepedidoaproveedor`
--

DROP TABLE IF EXISTS `detallepedidoaproveedor`;
CREATE TABLE `detallepedidoaproveedor` (
  `Id_DetallePedidoAProveedor` int(10) unsigned NOT NULL auto_increment,
  `cantidad` double default NULL,
  `precioActual` double default NULL,
  `fk_Producto` int(10) unsigned default NULL,
  `fk_PedidoAProveedor` int(10) unsigned default NULL,
  `fk_Estado` int(10) unsigned default NULL,
  PRIMARY KEY  (`Id_DetallePedidoAProveedor`),
  KEY `fk_ProductoDetallePedidoAProveedor` (`fk_Producto`),
  KEY `fk_PedidoAProveedorDetallePedido` (`fk_PedidoAProveedor`),
  KEY `fk_EstadoDetallePedido` (`fk_Estado`),
  CONSTRAINT `fk_EstadoDetallePedido` FOREIGN KEY (`fk_Estado`) REFERENCES `estado` (`Id_Estado`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_PedidoAProveedorDetallePedido` FOREIGN KEY (`fk_PedidoAProveedor`) REFERENCES `pedidoaproveedor` (`Id_PedidoAProveedor`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_ProductoDetallePedidoAProveedor` FOREIGN KEY (`fk_Producto`) REFERENCES `producto` (`Id_Producto`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detallepedidoaproveedor`
--

/*!40000 ALTER TABLE `detallepedidoaproveedor` DISABLE KEYS */;
INSERT INTO `detallepedidoaproveedor` (`Id_DetallePedidoAProveedor`,`cantidad`,`precioActual`,`fk_Producto`,`fk_PedidoAProveedor`,`fk_Estado`) VALUES 
 (43,1000,2,2,37,8),
 (44,2000,2,2,38,10),
 (45,2000,2,2,39,10);
/*!40000 ALTER TABLE `detallepedidoaproveedor` ENABLE KEYS */;


--
-- Definition of table `detallerecepcion`
--

DROP TABLE IF EXISTS `detallerecepcion`;
CREATE TABLE `detallerecepcion` (
  `Id_DetalleRecepcion` int(10) unsigned NOT NULL auto_increment,
  `fechaRecepcion` datetime default NULL,
  `horaRealRecepcion` varchar(50) default NULL,
  `cantidadRecibida` double default NULL,
  `precioUnitario` double default NULL,
  `fk_Recepcion` int(10) unsigned default NULL,
  `fk_Producto` int(10) unsigned default NULL,
  `fk_Empleado` int(10) unsigned default NULL,
  `fk_DetallePedidoAProveedor` int(10) unsigned default NULL,
  PRIMARY KEY  (`Id_DetalleRecepcion`),
  KEY `fk_Recepcion` (`fk_Recepcion`),
  KEY `fk_ProductoDetalleRecepcion` (`fk_Producto`),
  KEY `fk_EmpleadoDetalleRecepcion` (`fk_Empleado`),
  KEY `fk_DetallePedidoAProveedorDetalleRecepcion` (`fk_DetallePedidoAProveedor`),
  CONSTRAINT `fk_DetallePedidoAProveedorDetalleRecepcion` FOREIGN KEY (`fk_DetallePedidoAProveedor`) REFERENCES `detallepedidoaproveedor` (`Id_DetallePedidoAProveedor`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_EmpleadoDetalleRecepcion` FOREIGN KEY (`fk_Empleado`) REFERENCES `empleado` (`Id_Empleado`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_ProductoDetalleRecepcion` FOREIGN KEY (`fk_Producto`) REFERENCES `producto` (`Id_Producto`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_Recepcion` FOREIGN KEY (`fk_Recepcion`) REFERENCES `recepcion` (`Id_Recepcion`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detallerecepcion`
--

/*!40000 ALTER TABLE `detallerecepcion` DISABLE KEYS */;
INSERT INTO `detallerecepcion` (`Id_DetalleRecepcion`,`fechaRecepcion`,`horaRealRecepcion`,`cantidadRecibida`,`precioUnitario`,`fk_Recepcion`,`fk_Producto`,`fk_Empleado`,`fk_DetallePedidoAProveedor`) VALUES 
 (10,'2008-08-18 00:00:00','18:28:00',2000,2,10,2,3,45),
 (11,'2008-08-18 00:00:00','18:33:00',2000,2,11,2,3,44),
 (12,'2008-08-18 00:00:00','18:35:00',0,2,12,2,1,45);
/*!40000 ALTER TABLE `detallerecepcion` ENABLE KEYS */;


--
-- Definition of table `domicilio`
--

DROP TABLE IF EXISTS `domicilio`;
CREATE TABLE `domicilio` (
  `Id_Domicilio` int(10) unsigned NOT NULL auto_increment,
  `nombreCalle` varchar(50) default NULL,
  `nroCalle` int(10) unsigned default NULL,
  `departamento` varchar(50) default NULL,
  `nroPiso` int(10) unsigned default NULL,
  `barrio` varchar(50) default NULL,
  `fk_Ciudad` int(10) unsigned default NULL,
  `fk_Provincia` int(10) unsigned default NULL,
  PRIMARY KEY  (`Id_Domicilio`),
  KEY `fk_Ciudad` (`fk_Ciudad`),
  KEY `fk_Provincia` (`fk_Provincia`),
  CONSTRAINT `fk_Ciudad` FOREIGN KEY (`fk_Ciudad`) REFERENCES `ciudad` (`Id_Ciudad`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_Provincia` FOREIGN KEY (`fk_Provincia`) REFERENCES `provincia` (`Id_Provincia`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `domicilio`
--

/*!40000 ALTER TABLE `domicilio` DISABLE KEYS */;
INSERT INTO `domicilio` (`Id_Domicilio`,`nombreCalle`,`nroCalle`,`departamento`,`nroPiso`,`barrio`,`fk_Ciudad`,`fk_Provincia`) VALUES 
 (1,'Av. Ramón J. Cárcano',2186,'-',0,'Las Rosas',1,1),
 (2,'Av Colón',123,'A',2,'Centro',1,1),
 (16,'Av. Carcano',1589,'-',0,'Centro',6,1),
 (17,'Larañaga',1289,'-',0,'Cofico',6,1),
 (18,'Fragueiro',456,'-',0,'Alta Córdoba',6,1),
 (19,'Maipu',432,'-',0,'Centro',6,1),
 (20,'Rivadavia',345,'-',0,'Centro',2,1),
 (21,'Colón',145,'-',0,'Centro',6,1),
 (22,'Colón',123,'-',0,'Centro',6,1),
 (23,'Tineo',234,'-',0,'Ayacucho',1,1);
/*!40000 ALTER TABLE `domicilio` ENABLE KEYS */;


--
-- Definition of table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
CREATE TABLE `empleado` (
  `Id_Empleado` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(50) default NULL,
  `apellido` varchar(50) default NULL,
  PRIMARY KEY  (`Id_Empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `empleado`
--

/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` (`Id_Empleado`,`nombre`,`apellido`) VALUES 
 (1,'Cecilia','Andurno'),
 (2,'Diego','Cohen'),
 (3,'Fernando','Bornoroni'),
 (4,'Armando','Ayala');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;


--
-- Definition of table `estado`
--

DROP TABLE IF EXISTS `estado`;
CREATE TABLE `estado` (
  `Id_Estado` int(10) unsigned NOT NULL,
  `nombre` varchar(50) default NULL,
  `descripcion` varchar(50) default NULL,
  PRIMARY KEY  (`Id_Estado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 10240 kB; (`fk_AmbitoEstado`) REFER `bdpetrosys';

--
-- Dumping data for table `estado`
--

/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` (`Id_Estado`,`nombre`,`descripcion`) VALUES 
 (1,'Creado',NULL),
 (2,'Inactivo',NULL),
 (3,'En funcionamiento',NULL),
 (4,'En inspeccion',NULL),
 (5,'En reparacion',NULL),
 (6,'De baja',NULL),
 (7,'Cancelado',NULL),
 (8,'Pendiente de Recepcion',NULL),
 (9,'Recibido parcial',NULL),
 (10,'Recibido total',NULL);
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;


--
-- Definition of table `facturaproveedor`
--

DROP TABLE IF EXISTS `facturaproveedor`;
CREATE TABLE `facturaproveedor` (
  `Id_FacturaProveedor` int(10) unsigned NOT NULL auto_increment,
  `numero` int(10) unsigned default NULL,
  `fecha` datetime default NULL,
  `montoTotal` double default NULL,
  `fk_TipoFactura` int(10) unsigned default NULL,
  `fk_PedidoAProveedor` int(10) unsigned default NULL,
  PRIMARY KEY  (`Id_FacturaProveedor`),
  KEY `fk_TipoFactura` (`fk_TipoFactura`),
  KEY `fk_PedidoAProveedor` (`fk_PedidoAProveedor`),
  CONSTRAINT `fk_PedidoAProveedor` FOREIGN KEY (`fk_PedidoAProveedor`) REFERENCES `pedidoaproveedor` (`Id_PedidoAProveedor`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_TipoFactura` FOREIGN KEY (`fk_TipoFactura`) REFERENCES `tipofactura` (`Id_TipoFactura`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `facturaproveedor`
--

/*!40000 ALTER TABLE `facturaproveedor` DISABLE KEYS */;
INSERT INTO `facturaproveedor` (`Id_FacturaProveedor`,`numero`,`fecha`,`montoTotal`,`fk_TipoFactura`,`fk_PedidoAProveedor`) VALUES 
 (2,555,'2008-08-13 18:29:59',4000,1,39);
/*!40000 ALTER TABLE `facturaproveedor` ENABLE KEYS */;


--
-- Definition of table `impuesto`
--

DROP TABLE IF EXISTS `impuesto`;
CREATE TABLE `impuesto` (
  `Id_Impuesto` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(50) default NULL,
  `porcentaje` double default NULL,
  `monto` double default NULL,
  PRIMARY KEY  (`Id_Impuesto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `impuesto`
--

/*!40000 ALTER TABLE `impuesto` DISABLE KEYS */;
/*!40000 ALTER TABLE `impuesto` ENABLE KEYS */;


--
-- Definition of table `iva`
--

DROP TABLE IF EXISTS `iva`;
CREATE TABLE `iva` (
  `Id_Iva` int(10) unsigned NOT NULL auto_increment,
  `porcentaje` double default NULL,
  `descripcion` varchar(50) default NULL,
  PRIMARY KEY  (`Id_Iva`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `iva`
--

/*!40000 ALTER TABLE `iva` DISABLE KEYS */;
/*!40000 ALTER TABLE `iva` ENABLE KEYS */;


--
-- Definition of table `manguera`
--

DROP TABLE IF EXISTS `manguera`;
CREATE TABLE `manguera` (
  `Id_Manguera` int(10) unsigned NOT NULL auto_increment,
  `numero` int(10) unsigned default NULL,
  `fk_Surtidor` int(10) unsigned default NULL,
  `fk_Tanque` int(10) unsigned default NULL,
  PRIMARY KEY  (`Id_Manguera`),
  KEY `fk_Surtidor` (`fk_Surtidor`),
  KEY `fk_Tanque` (`fk_Tanque`),
  CONSTRAINT `fk_Surtidor` FOREIGN KEY (`fk_Surtidor`) REFERENCES `surtidor` (`Id_Surtidor`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_Tanque` FOREIGN KEY (`fk_Tanque`) REFERENCES `tanque` (`Id_Tanque`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `manguera`
--

/*!40000 ALTER TABLE `manguera` DISABLE KEYS */;
/*!40000 ALTER TABLE `manguera` ENABLE KEYS */;


--
-- Definition of table `mediciontanque`
--

DROP TABLE IF EXISTS `mediciontanque`;
CREATE TABLE `mediciontanque` (
  `Id_MedicionTanque` int(10) unsigned NOT NULL auto_increment,
  `fechaMedicion` datetime default NULL,
  `horaMedicion` varchar(50) default NULL,
  `litrosMedidos` double default NULL,
  `fk_Tanque` int(10) unsigned default NULL,
  `fk_Empleado` int(10) unsigned default NULL,
  PRIMARY KEY  (`Id_MedicionTanque`),
  KEY `fk_MedicionTanque` (`fk_Tanque`),
  KEY `fk_Empleado` (`fk_Empleado`),
  CONSTRAINT `fk_Empleado` FOREIGN KEY (`fk_Empleado`) REFERENCES `empleado` (`Id_Empleado`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_MedicionTanque` FOREIGN KEY (`fk_Tanque`) REFERENCES `tanque` (`Id_Tanque`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mediciontanque`
--

/*!40000 ALTER TABLE `mediciontanque` DISABLE KEYS */;
/*!40000 ALTER TABLE `mediciontanque` ENABLE KEYS */;


--
-- Definition of table `pais`
--

DROP TABLE IF EXISTS `pais`;
CREATE TABLE `pais` (
  `Id_Pais` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(50) default NULL,
  PRIMARY KEY  (`Id_Pais`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pais`
--

/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` (`Id_Pais`,`nombre`) VALUES 
 (1,'Argentina'),
 (2,'Brasil'),
 (3,'Chile');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;


--
-- Definition of table `pedidoaproveedor`
--

DROP TABLE IF EXISTS `pedidoaproveedor`;
CREATE TABLE `pedidoaproveedor` (
  `Id_PedidoAProveedor` int(10) unsigned NOT NULL auto_increment,
  `fechaRealizacion` datetime default NULL,
  `horaRealizacion` varchar(50) default NULL,
  `fechaEstimadaEntrega` datetime default NULL,
  `horaEstimadaEntrega` varchar(50) default NULL,
  `montoTotal` double default NULL,
  `fk_Estado` int(10) unsigned default NULL,
  `fk_RepresentanteDeProveedor` int(10) unsigned default NULL,
  `fk_Proveedor` int(10) unsigned default NULL,
  `fk_Empleado` int(10) unsigned default NULL,
  PRIMARY KEY  (`Id_PedidoAProveedor`),
  KEY `fk_EstadoPedidoAProveedor` (`fk_Estado`),
  KEY `fk_RepresentanteDeProveedorPedido` (`fk_RepresentanteDeProveedor`),
  KEY `fk_ProveedorPedido` (`fk_Proveedor`),
  KEY `fk_EmpleadoPedido` (`fk_Empleado`),
  CONSTRAINT `fk_EmpleadoPedido` FOREIGN KEY (`fk_Empleado`) REFERENCES `empleado` (`Id_Empleado`),
  CONSTRAINT `fk_EstadoPedidoAProveedor` FOREIGN KEY (`fk_Estado`) REFERENCES `estado` (`Id_Estado`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_ProveedorPedido` FOREIGN KEY (`fk_Proveedor`) REFERENCES `proveedor` (`Id_Proveedor`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_RepresentanteDeProveedorPedido` FOREIGN KEY (`fk_RepresentanteDeProveedor`) REFERENCES `representantedeproveedor` (`Id_RepresentanteDeProveedor`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pedidoaproveedor`
--

/*!40000 ALTER TABLE `pedidoaproveedor` DISABLE KEYS */;
INSERT INTO `pedidoaproveedor` (`Id_PedidoAProveedor`,`fechaRealizacion`,`horaRealizacion`,`fechaEstimadaEntrega`,`horaEstimadaEntrega`,`montoTotal`,`fk_Estado`,`fk_RepresentanteDeProveedor`,`fk_Proveedor`,`fk_Empleado`) VALUES 
 (37,'2008-08-11 00:00:00','19:34:34','2008-08-11 00:00:00','19:19:00',2000,8,NULL,1,1),
 (38,'2008-08-13 00:00:00','17:28:25','2008-08-13 00:00:00','17:17:00',4000,10,NULL,1,1),
 (39,'2008-08-13 00:00:00','17:46:38','2008-08-13 00:00:00','17:17:00',4000,9,6,1,1);
/*!40000 ALTER TABLE `pedidoaproveedor` ENABLE KEYS */;


--
-- Definition of table `producto`
--

DROP TABLE IF EXISTS `producto`;
CREATE TABLE `producto` (
  `Id_Producto` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(50) default NULL,
  `precioCosto` double default NULL,
  `precioVenta` double default NULL,
  `stockMinimo` double default NULL,
  `stockActual` double default NULL,
  `unidad` varchar(50) default NULL,
  `autorizacionPedidoEnPlaya` tinyint(1) default NULL,
  `fk_Iva` int(10) unsigned default NULL,
  PRIMARY KEY  (`Id_Producto`),
  KEY `fk_IvaProducto` (`fk_Iva`),
  CONSTRAINT `fk_IvaProducto` FOREIGN KEY (`fk_Iva`) REFERENCES `iva` (`Id_Iva`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `producto`
--

/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` (`Id_Producto`,`nombre`,`precioCosto`,`precioVenta`,`stockMinimo`,`stockActual`,`unidad`,`autorizacionPedidoEnPlaya`,`fk_Iva`) VALUES 
 (1,'Carbon',5,7,50,200,'kg',1,NULL),
 (2,'Nafta Super',2,4,500,2000,'litros',1,NULL),
 (3,'Galletas',1,2,50,60,'gramos',1,NULL),
 (4,'Nafta Normal',1,3,500,2000,'litros',1,NULL);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;


--
-- Definition of table `productodeplaya`
--

DROP TABLE IF EXISTS `productodeplaya`;
CREATE TABLE `productodeplaya` (
  `Id_ProductoDePlaya` int(10) unsigned NOT NULL auto_increment,
  `stockEnPlaya` double default NULL,
  `fk_Producto` int(10) unsigned default NULL,
  PRIMARY KEY  (`Id_ProductoDePlaya`),
  KEY `fk_ProductoProductoDePlaya` (`fk_Producto`),
  CONSTRAINT `fk_ProductoProductoDePlaya` FOREIGN KEY (`fk_Producto`) REFERENCES `producto` (`Id_Producto`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `productodeplaya`
--

/*!40000 ALTER TABLE `productodeplaya` DISABLE KEYS */;
/*!40000 ALTER TABLE `productodeplaya` ENABLE KEYS */;


--
-- Definition of table `productoxproveedor`
--

DROP TABLE IF EXISTS `productoxproveedor`;
CREATE TABLE `productoxproveedor` (
  `fk_Producto` int(10) unsigned NOT NULL,
  `fk_Proveedor` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`fk_Producto`,`fk_Proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `productoxproveedor`
--

/*!40000 ALTER TABLE `productoxproveedor` DISABLE KEYS */;
INSERT INTO `productoxproveedor` (`fk_Producto`,`fk_Proveedor`) VALUES 
 (2,1),
 (4,1);
/*!40000 ALTER TABLE `productoxproveedor` ENABLE KEYS */;


--
-- Definition of table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE `proveedor` (
  `Id_Proveedor` int(10) unsigned NOT NULL auto_increment,
  `razonSocial` varchar(50) default NULL,
  `cuit` int(10) unsigned default NULL,
  `ingresoBruto` int(10) unsigned default NULL,
  `fk_CondicionIva` int(10) unsigned default NULL,
  `fk_Domicilio` int(10) unsigned default NULL,
  `fk_CuentaCorrienteProveedor` int(10) unsigned default NULL,
  PRIMARY KEY  (`Id_Proveedor`),
  KEY `fk_CondicionIvaProveedor` (`fk_CondicionIva`),
  KEY `fk_DomicilioProveedor` (`fk_Domicilio`),
  KEY `fk_CuentaCorrienteProveedor` (`fk_CuentaCorrienteProveedor`),
  CONSTRAINT `fk_CondicionIvaProveedor` FOREIGN KEY (`fk_CondicionIva`) REFERENCES `condicioniva` (`Id_CondicionIva`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_CuentaCorrienteProveedor` FOREIGN KEY (`fk_CuentaCorrienteProveedor`) REFERENCES `cuentacorrienteproveedor` (`Id_CuentaCorrienteProveedor`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_DomicilioProveedor` FOREIGN KEY (`fk_Domicilio`) REFERENCES `domicilio` (`Id_Domicilio`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `proveedor`
--

/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` (`Id_Proveedor`,`razonSocial`,`cuit`,`ingresoBruto`,`fk_CondicionIva`,`fk_Domicilio`,`fk_CuentaCorrienteProveedor`) VALUES 
 (1,'YPF',1234,2345,1,1,NULL),
 (15,'Shell',123,123456798,2,16,NULL),
 (16,'Arcor',85,78956,2,17,NULL),
 (17,'Sancor',456,54689713,2,18,NULL),
 (18,'Pavone',4242,1234,2,19,NULL),
 (21,'la Serenisima',20145896,0,2,22,NULL),
 (22,'Sancor',43242,456,2,23,NULL);
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;


--
-- Definition of table `proveedorxtipoproveedor`
--

DROP TABLE IF EXISTS `proveedorxtipoproveedor`;
CREATE TABLE `proveedorxtipoproveedor` (
  `fk_Proveedor` int(10) unsigned NOT NULL,
  `fk_TipoProveedor` varchar(45) NOT NULL,
  PRIMARY KEY  (`fk_Proveedor`,`fk_TipoProveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `proveedorxtipoproveedor`
--

/*!40000 ALTER TABLE `proveedorxtipoproveedor` DISABLE KEYS */;
INSERT INTO `proveedorxtipoproveedor` (`fk_Proveedor`,`fk_TipoProveedor`) VALUES 
 (1,'6'),
 (4,'4'),
 (4,'5'),
 (4,'6'),
 (6,'5'),
 (6,'6'),
 (15,'6');
/*!40000 ALTER TABLE `proveedorxtipoproveedor` ENABLE KEYS */;


--
-- Definition of table `provincia`
--

DROP TABLE IF EXISTS `provincia`;
CREATE TABLE `provincia` (
  `Id_Provincia` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(50) default NULL,
  `fk_Pais` int(10) unsigned default NULL,
  PRIMARY KEY  (`Id_Provincia`),
  KEY `fk_Pais` (`fk_Pais`),
  CONSTRAINT `fk_Pais` FOREIGN KEY (`fk_Pais`) REFERENCES `pais` (`Id_Pais`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `provincia`
--

/*!40000 ALTER TABLE `provincia` DISABLE KEYS */;
INSERT INTO `provincia` (`Id_Provincia`,`nombre`,`fk_Pais`) VALUES 
 (1,'Córdoba',1),
 (2,'Mendoza',1),
 (3,'Misiones',1),
 (4,'Catamarca',1),
 (5,'Sao Paulo',2),
 (6,'Bello Horizonte',2);
/*!40000 ALTER TABLE `provincia` ENABLE KEYS */;


--
-- Definition of table `recepcion`
--

DROP TABLE IF EXISTS `recepcion`;
CREATE TABLE `recepcion` (
  `Id_Recepcion` int(10) unsigned NOT NULL auto_increment,
  `fechaRealRecepcion` datetime default NULL,
  `horaRealRecepcion` varchar(50) default NULL,
  `montoTotal` double default NULL,
  `fk_FacturaProveedor` int(10) unsigned default NULL,
  `fk_PedidoAProveedor` int(10) unsigned default NULL,
  PRIMARY KEY  (`Id_Recepcion`),
  KEY `fk_FacturaProveedor` (`fk_FacturaProveedor`),
  KEY `fk_PedidoAProveedorRecepcion` (`fk_PedidoAProveedor`),
  CONSTRAINT `fk_FacturaProveedor` FOREIGN KEY (`fk_FacturaProveedor`) REFERENCES `facturaproveedor` (`Id_FacturaProveedor`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_PedidoAProveedorRecepcion` FOREIGN KEY (`fk_PedidoAProveedor`) REFERENCES `pedidoaproveedor` (`Id_PedidoAProveedor`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `recepcion`
--

/*!40000 ALTER TABLE `recepcion` DISABLE KEYS */;
INSERT INTO `recepcion` (`Id_Recepcion`,`fechaRealRecepcion`,`horaRealRecepcion`,`montoTotal`,`fk_FacturaProveedor`,`fk_PedidoAProveedor`) VALUES 
 (10,'2008-08-18 00:00:00','18:28:00',4000,2,39),
 (11,'2008-08-18 00:00:00','18:33:00',4000,NULL,38),
 (12,'2008-08-18 00:00:00','18:35:00',0,NULL,39);
/*!40000 ALTER TABLE `recepcion` ENABLE KEYS */;


--
-- Definition of table `remito`
--

DROP TABLE IF EXISTS `remito`;
CREATE TABLE `remito` (
  `Id_Remito` int(10) unsigned NOT NULL auto_increment,
  `numero` int(10) unsigned default NULL,
  `fk_Recepcion` int(10) unsigned default NULL,
  `fk_DetalleRecepcion` int(10) unsigned default NULL,
  PRIMARY KEY  (`Id_Remito`),
  KEY `fk_RecepcionRemito` (`fk_Recepcion`),
  KEY `fk_DetalleRecepcionRemito` (`fk_DetalleRecepcion`),
  CONSTRAINT `fk_DetalleRecepcionRemito` FOREIGN KEY (`fk_DetalleRecepcion`) REFERENCES `detallerecepcion` (`Id_DetalleRecepcion`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_RecepcionRemito` FOREIGN KEY (`fk_Recepcion`) REFERENCES `recepcion` (`Id_Recepcion`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `remito`
--

/*!40000 ALTER TABLE `remito` DISABLE KEYS */;
INSERT INTO `remito` (`Id_Remito`,`numero`,`fk_Recepcion`,`fk_DetalleRecepcion`) VALUES 
 (10,6439,10,10),
 (11,7438,11,11),
 (12,323232,12,12);
/*!40000 ALTER TABLE `remito` ENABLE KEYS */;


--
-- Definition of table `representantedeproveedor`
--

DROP TABLE IF EXISTS `representantedeproveedor`;
CREATE TABLE `representantedeproveedor` (
  `Id_RepresentanteDeProveedor` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(50) default NULL,
  `apellido` varchar(50) default NULL,
  `fk_Proveedor` int(10) unsigned default NULL,
  PRIMARY KEY  (`Id_RepresentanteDeProveedor`),
  KEY `fk_ProveedorRepresentante` (`fk_Proveedor`),
  CONSTRAINT `fk_ProveedorRepresentante` FOREIGN KEY (`fk_Proveedor`) REFERENCES `proveedor` (`Id_Proveedor`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `representantedeproveedor`
--

/*!40000 ALTER TABLE `representantedeproveedor` DISABLE KEYS */;
INSERT INTO `representantedeproveedor` (`Id_RepresentanteDeProveedor`,`nombre`,`apellido`,`fk_Proveedor`) VALUES 
 (6,'Cecilia','Andurno',17),
 (7,'Diego','Cohen',18),
 (8,'Fernando','Bornoroni',NULL),
 (9,'Roberto','Gonzales',1),
 (10,'Carlos','Alvarez',16),
 (11,'Mauro','Bornoroni',16),
 (12,'Mauricio','Escalante',16),
 (13,'Lucas','Sortica',16);
/*!40000 ALTER TABLE `representantedeproveedor` ENABLE KEYS */;


--
-- Definition of table `surtidor`
--

DROP TABLE IF EXISTS `surtidor`;
CREATE TABLE `surtidor` (
  `Id_Surtidor` int(10) unsigned NOT NULL auto_increment,
  `numero` int(10) unsigned default NULL,
  `marca` varchar(50) default NULL,
  `fechaInstalacion` datetime default NULL,
  `fk_Estado` int(10) unsigned default NULL,
  PRIMARY KEY  (`Id_Surtidor`),
  KEY `fk_EstadoSurtidor` (`fk_Estado`),
  CONSTRAINT `fk_EstadoSurtidor` FOREIGN KEY (`fk_Estado`) REFERENCES `estado` (`Id_Estado`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `surtidor`
--

/*!40000 ALTER TABLE `surtidor` DISABLE KEYS */;
/*!40000 ALTER TABLE `surtidor` ENABLE KEYS */;


--
-- Definition of table `tanque`
--

DROP TABLE IF EXISTS `tanque`;
CREATE TABLE `tanque` (
  `Id_Tanque` int(10) unsigned NOT NULL auto_increment,
  `numero` int(10) unsigned default NULL,
  `capacidad` double default NULL,
  `fechaInstalacion` datetime default NULL,
  `stockTanque` double default NULL,
  `fk_Estado` int(10) unsigned default NULL,
  `fk_Combustible` int(10) unsigned default NULL,
  PRIMARY KEY  (`Id_Tanque`),
  KEY `fk_EstadoTanque` USING BTREE (`fk_Estado`),
  KEY `fk_Combustible` (`fk_Combustible`),
  CONSTRAINT `fk_Combustible` FOREIGN KEY (`fk_Combustible`) REFERENCES `combustible` (`Id_Combustible`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_EstadoTanque` FOREIGN KEY (`fk_Estado`) REFERENCES `estado` (`Id_Estado`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tanque`
--

/*!40000 ALTER TABLE `tanque` DISABLE KEYS */;
/*!40000 ALTER TABLE `tanque` ENABLE KEYS */;


--
-- Definition of table `telefono`
--

DROP TABLE IF EXISTS `telefono`;
CREATE TABLE `telefono` (
  `Id_Telefono` int(10) unsigned NOT NULL auto_increment,
  `numero` int(10) unsigned default NULL,
  `caracteristica` int(10) unsigned default NULL,
  `fk_TipoTelefono` int(10) unsigned default NULL,
  PRIMARY KEY  (`Id_Telefono`),
  KEY `FK_telefono_1` (`fk_TipoTelefono`),
  CONSTRAINT `FK_telefono_1` FOREIGN KEY (`fk_TipoTelefono`) REFERENCES `tipotelefono` (`id_TipoTelefono`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `telefono`
--

/*!40000 ALTER TABLE `telefono` DISABLE KEYS */;
INSERT INTO `telefono` (`Id_Telefono`,`numero`,`caracteristica`,`fk_TipoTelefono`) VALUES 
 (39,4703381,351,2),
 (40,153188311,351,1),
 (41,4552267,351,2),
 (42,12,12,2),
 (43,12,12,1),
 (44,12,12,3),
 (45,12,12,1),
 (46,12,12,3),
 (47,12,12,3),
 (48,12,12,1),
 (49,12,12,3),
 (54,4235689,351,2),
 (55,156877736,351,1),
 (56,4703381,351,2),
 (57,5678923,351,3),
 (58,2345678,351,1),
 (59,2567892,351,2),
 (60,12345678,351,2),
 (61,2345678,351,2),
 (62,1234567,351,2),
 (63,23456789,351,2);
/*!40000 ALTER TABLE `telefono` ENABLE KEYS */;


--
-- Definition of table `telefonoxproveedor`
--

DROP TABLE IF EXISTS `telefonoxproveedor`;
CREATE TABLE `telefonoxproveedor` (
  `fk_Telefono` int(10) unsigned NOT NULL,
  `fk_Proveedor` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`fk_Telefono`,`fk_Proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `telefonoxproveedor`
--

/*!40000 ALTER TABLE `telefonoxproveedor` DISABLE KEYS */;
INSERT INTO `telefonoxproveedor` (`fk_Telefono`,`fk_Proveedor`) VALUES 
 (26,14),
 (27,14),
 (28,14),
 (29,15),
 (30,16),
 (31,17),
 (32,18),
 (32,19),
 (33,19),
 (39,22),
 (54,21),
 (55,22),
 (56,22);
/*!40000 ALTER TABLE `telefonoxproveedor` ENABLE KEYS */;


--
-- Definition of table `telefonoxrepresentanteproveedor`
--

DROP TABLE IF EXISTS `telefonoxrepresentanteproveedor`;
CREATE TABLE `telefonoxrepresentanteproveedor` (
  `fk_Telefono` int(10) unsigned NOT NULL,
  `fk_RepresentanteDeProveedor` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`fk_Telefono`,`fk_RepresentanteDeProveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `telefonoxrepresentanteproveedor`
--

/*!40000 ALTER TABLE `telefonoxrepresentanteproveedor` DISABLE KEYS */;
INSERT INTO `telefonoxrepresentanteproveedor` (`fk_Telefono`,`fk_RepresentanteDeProveedor`) VALUES 
 (39,6),
 (40,7),
 (41,7),
 (42,8),
 (43,8),
 (44,8),
 (45,8),
 (46,8),
 (47,8),
 (48,8),
 (49,8),
 (57,9),
 (58,9),
 (59,9),
 (60,10),
 (61,11),
 (62,12),
 (63,13);
/*!40000 ALTER TABLE `telefonoxrepresentanteproveedor` ENABLE KEYS */;


--
-- Definition of table `tipofactura`
--

DROP TABLE IF EXISTS `tipofactura`;
CREATE TABLE `tipofactura` (
  `Id_TipoFactura` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(50) default NULL,
  PRIMARY KEY  (`Id_TipoFactura`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipofactura`
--

/*!40000 ALTER TABLE `tipofactura` DISABLE KEYS */;
INSERT INTO `tipofactura` (`Id_TipoFactura`,`nombre`) VALUES 
 (1,'A');
/*!40000 ALTER TABLE `tipofactura` ENABLE KEYS */;


--
-- Definition of table `tipomoneda`
--

DROP TABLE IF EXISTS `tipomoneda`;
CREATE TABLE `tipomoneda` (
  `id_TipoMoneda` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(45) default NULL,
  PRIMARY KEY  (`id_TipoMoneda`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipomoneda`
--

/*!40000 ALTER TABLE `tipomoneda` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipomoneda` ENABLE KEYS */;


--
-- Definition of table `tipoproducto`
--

DROP TABLE IF EXISTS `tipoproducto`;
CREATE TABLE `tipoproducto` (
  `Id_TipoProducto` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(50) default NULL,
  `descripcion` varchar(50) default NULL,
  PRIMARY KEY  (`Id_TipoProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipoproducto`
--

/*!40000 ALTER TABLE `tipoproducto` DISABLE KEYS */;
INSERT INTO `tipoproducto` (`Id_TipoProducto`,`nombre`,`descripcion`) VALUES 
 (1,'Galletas',NULL),
 (2,'Bebidas',NULL),
 (3,'Lácteos',NULL),
 (4,'Golosinas',NULL);
/*!40000 ALTER TABLE `tipoproducto` ENABLE KEYS */;


--
-- Definition of table `tipoproveedor`
--

DROP TABLE IF EXISTS `tipoproveedor`;
CREATE TABLE `tipoproveedor` (
  `Id_TipoProveedor` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(50) default NULL,
  `descripcion` varchar(50) default NULL,
  PRIMARY KEY  (`Id_TipoProveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipoproveedor`
--

/*!40000 ALTER TABLE `tipoproveedor` DISABLE KEYS */;
INSERT INTO `tipoproveedor` (`Id_TipoProveedor`,`nombre`,`descripcion`) VALUES 
 (4,'Proveedor de articulos de minishop',NULL),
 (5,'Proveedor de productos de playa',NULL),
 (6,'Proveedor de combustible',NULL);
/*!40000 ALTER TABLE `tipoproveedor` ENABLE KEYS */;


--
-- Definition of table `tipoproveedorxproducto`
--

DROP TABLE IF EXISTS `tipoproveedorxproducto`;
CREATE TABLE `tipoproveedorxproducto` (
  `fk_TipoProveedor` int(10) unsigned NOT NULL default '0',
  `fk_Producto` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`fk_TipoProveedor`,`fk_Producto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipoproveedorxproducto`
--

/*!40000 ALTER TABLE `tipoproveedorxproducto` DISABLE KEYS */;
INSERT INTO `tipoproveedorxproducto` (`fk_TipoProveedor`,`fk_Producto`) VALUES 
 (4,3),
 (5,1),
 (6,2),
 (6,4);
/*!40000 ALTER TABLE `tipoproveedorxproducto` ENABLE KEYS */;


--
-- Definition of table `tipotelefono`
--

DROP TABLE IF EXISTS `tipotelefono`;
CREATE TABLE `tipotelefono` (
  `id_TipoTelefono` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY  (`id_TipoTelefono`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipotelefono`
--

/*!40000 ALTER TABLE `tipotelefono` DISABLE KEYS */;
INSERT INTO `tipotelefono` (`id_TipoTelefono`,`nombre`) VALUES 
 (1,'Celular'),
 (2,'Casa'),
 (3,'Trabajo');
/*!40000 ALTER TABLE `tipotelefono` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
