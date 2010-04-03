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
  CONSTRAINT `fk_ambitoestadoAxE` FOREIGN KEY (`fk_AmbitoEstado`) REFERENCES `ambitoestado` (`Id_AmbitoEstado`),
  CONSTRAINT `fk_EstadoAxE` FOREIGN KEY (`fk_Estado`) REFERENCES `estado` (`Id_Estado`) ON DELETE CASCADE ON UPDATE CASCADE
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
  CONSTRAINT `fk_ProductoArticuloMinishop` FOREIGN KEY (`fk_Producto`) REFERENCES `producto` (`Id_Producto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_TipoProductoArticuloMinishop` FOREIGN KEY (`fk_TipoProducto`) REFERENCES `tipoproducto` (`Id_TipoProducto`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `articulominishop`
--

/*!40000 ALTER TABLE `articulominishop` DISABLE KEYS */;
INSERT INTO `articulominishop` (`Id_ArticuloMinishop`,`numeroCodigoBarra`,`fk_Producto`,`fk_TipoProducto`) VALUES 
 (2,123543,3,NULL),
 (3,123344,5,2),
 (4,454545,6,4),
 (5,454566,7,3);
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
  CONSTRAINT `fk_ProviniciaCiudad` FOREIGN KEY (`fk_Provincia`) REFERENCES `provincia` (`Id_Provincia`) ON DELETE CASCADE ON UPDATE CASCADE
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
-- Definition of table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `Id_Cliente` int(10) unsigned NOT NULL auto_increment,
  `numeroCliente` int(10) unsigned NOT NULL,
  `mail` varchar(50) NOT NULL,
  `fk_Domicilio` int(10) unsigned NOT NULL,
  `fk_CondicionIva` int(10) unsigned NOT NULL,
  `fk_CtaCteCliente` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Id_Cliente`),
  KEY `FK_ClienteDomicilio` (`fk_Domicilio`),
  KEY `FK_ClienteCondicionIva` (`fk_CondicionIva`),
  KEY `FK_ClienteCtaCte` (`fk_CtaCteCliente`),
  CONSTRAINT `FK_ClienteCondicionIva` FOREIGN KEY (`fk_CondicionIva`) REFERENCES `condicioniva` (`Id_CondicionIva`),
  CONSTRAINT `FK_ClienteCtaCte` FOREIGN KEY (`fk_CtaCteCliente`) REFERENCES `cuentacorrientecliente` (`Id_CuentaCorrienteCliente`),
  CONSTRAINT `FK_ClienteDomicilio` FOREIGN KEY (`fk_Domicilio`) REFERENCES `domicilio` (`Id_Domicilio`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cliente`
--

/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;


--
-- Definition of table `clienteempresa`
--

DROP TABLE IF EXISTS `clienteempresa`;
CREATE TABLE `clienteempresa` (
  `Id_ClienteEmpresa` int(10) unsigned NOT NULL auto_increment,
  `razonSocial` varchar(50) NOT NULL,
  `cuit` int(10) unsigned NOT NULL,
  `numIngresoBruto` int(10) unsigned NOT NULL,
  `fk_Cliente` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Id_ClienteEmpresa`),
  KEY `FK_ClienteEmpresaCliente` (`fk_Cliente`),
  CONSTRAINT `FK_ClienteEmpresaCliente` FOREIGN KEY (`fk_Cliente`) REFERENCES `cliente` (`Id_Cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clienteempresa`
--

/*!40000 ALTER TABLE `clienteempresa` DISABLE KEYS */;
/*!40000 ALTER TABLE `clienteempresa` ENABLE KEYS */;


--
-- Definition of table `clienteparticular`
--

DROP TABLE IF EXISTS `clienteparticular`;
CREATE TABLE `clienteparticular` (
  `Id_ClienteParticular` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(50) NOT NULL,
  `numeroDocumento` int(10) unsigned NOT NULL,
  `sexo` varchar(50) NOT NULL,
  `fechaNacimiento` datetime NOT NULL,
  `fk_Cliente` int(10) unsigned NOT NULL,
  `fk_TipoDocumento` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Id_ClienteParticular`),
  KEY `FK_ClienteParticularCliente` (`fk_Cliente`),
  KEY `FK_clienteparticularTipoDoc` (`fk_TipoDocumento`),
  CONSTRAINT `FK_ClienteParticularCliente` FOREIGN KEY (`fk_Cliente`) REFERENCES `cliente` (`Id_Cliente`),
  CONSTRAINT `FK_clienteparticularTipoDoc` FOREIGN KEY (`fk_TipoDocumento`) REFERENCES `tipodocumento` (`id_TipoDocumento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clienteparticular`
--

/*!40000 ALTER TABLE `clienteparticular` DISABLE KEYS */;
/*!40000 ALTER TABLE `clienteparticular` ENABLE KEYS */;


--
-- Definition of table `combustible`
--

DROP TABLE IF EXISTS `combustible`;
CREATE TABLE `combustible` (
  `Id_Combustible` int(10) unsigned NOT NULL auto_increment,
  `fk_Producto` int(10) unsigned default NULL,
  `color` varchar(45) default NULL,
  PRIMARY KEY  (`Id_Combustible`),
  KEY `fk_Producto` (`fk_Producto`),
  CONSTRAINT `fk_ProductoCombustible` FOREIGN KEY (`fk_Producto`) REFERENCES `producto` (`Id_Producto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `combustible`
--

/*!40000 ALTER TABLE `combustible` DISABLE KEYS */;
INSERT INTO `combustible` (`Id_Combustible`,`fk_Producto`,`color`) VALUES 
 (1,2,'b'),
 (2,4,'y'),
 (3,8,'r'),
 (4,9,'y'),
 (5,10,'g');
/*!40000 ALTER TABLE `combustible` ENABLE KEYS */;


--
-- Definition of table `combustibleximpuesto`
--

DROP TABLE IF EXISTS `combustibleximpuesto`;
CREATE TABLE `combustibleximpuesto` (
  `fk_Combustible` int(10) unsigned NOT NULL,
  `fk_Impuesto` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`fk_Combustible`,`fk_Impuesto`),
  KEY `FK_ImpuestoCxI` (`fk_Impuesto`),
  CONSTRAINT `FK_CombustiblesCxI` FOREIGN KEY (`fk_Combustible`) REFERENCES `combustible` (`Id_Combustible`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ImpuestoCxI` FOREIGN KEY (`fk_Impuesto`) REFERENCES `impuesto` (`Id_Impuesto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `combustibleximpuesto`
--

/*!40000 ALTER TABLE `combustibleximpuesto` DISABLE KEYS */;
INSERT INTO `combustibleximpuesto` (`fk_Combustible`,`fk_Impuesto`) VALUES 
 (3,1),
 (4,1),
 (1,2),
 (2,2),
 (5,2);
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
 (1,'CONSUMIDOR FINAL','POR DEFECTO'),
 (2,'RESPONSABLE INSCRIPTO','DISCRIMINA IVA'),
 (3,'RESPONSABLE NO INSCRIPTO','NO DISCRIMINA IVA'),
 (4,'MONOTRIBUTO',' ');
/*!40000 ALTER TABLE `condicioniva` ENABLE KEYS */;


--
-- Definition of table `cuentacorrientecliente`
--

DROP TABLE IF EXISTS `cuentacorrientecliente`;
CREATE TABLE `cuentacorrientecliente` (
  `Id_CuentaCorrienteCliente` int(10) unsigned NOT NULL auto_increment,
  `montoTotalAdeudado` double NOT NULL,
  `fk_Estado` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Id_CuentaCorrienteCliente`),
  KEY `FK_CuentaCorrienteClienteEstado` (`fk_Estado`),
  CONSTRAINT `FK_CuentaCorrienteClienteEstado` FOREIGN KEY (`fk_Estado`) REFERENCES `estado` (`Id_Estado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cuentacorrientecliente`
--

/*!40000 ALTER TABLE `cuentacorrientecliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuentacorrientecliente` ENABLE KEYS */;


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
  CONSTRAINT `FK_DomicilioDatosEmpresa` FOREIGN KEY (`fk_Domicilio`) REFERENCES `domicilio` (`Id_Domicilio`) ON UPDATE CASCADE
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
  CONSTRAINT `fk_DetalleRecepcionDescarga` FOREIGN KEY (`fk_DetalleRecepcion`) REFERENCES `detallerecepcion` (`Id_DetalleRecepcion`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_TanqueDescarga` FOREIGN KEY (`fk_Tanque`) REFERENCES `tanque` (`Id_Tanque`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `descarga`
--

/*!40000 ALTER TABLE `descarga` DISABLE KEYS */;
/*!40000 ALTER TABLE `descarga` ENABLE KEYS */;


--
-- Definition of table `detallectactecliente`
--

DROP TABLE IF EXISTS `detallectactecliente`;
CREATE TABLE `detallectactecliente` (
  `Id_DetalleCtaCteCliente` int(10) unsigned NOT NULL auto_increment,
  `fk_CtaCteCliente` int(10) unsigned NOT NULL,
  `fk_TransaccionVenta` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Id_DetalleCtaCteCliente`),
  KEY `FK_DetalleCtaCteClienteCteCte` (`fk_CtaCteCliente`),
  KEY `FK_DetalleCtaCteClienteTransaccionVenta` (`fk_TransaccionVenta`),
  CONSTRAINT `FK_DetalleCtaCteClienteCteCte` FOREIGN KEY (`fk_CtaCteCliente`) REFERENCES `cuentacorrientecliente` (`Id_CuentaCorrienteCliente`),
  CONSTRAINT `FK_DetalleCtaCteClienteTransaccionVenta` FOREIGN KEY (`fk_TransaccionVenta`) REFERENCES `transaccionventa` (`Id_TransaccionVenta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detallectactecliente`
--

/*!40000 ALTER TABLE `detallectactecliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `detallectactecliente` ENABLE KEYS */;


--
-- Definition of table `detalleentrega`
--

DROP TABLE IF EXISTS `detalleentrega`;
CREATE TABLE `detalleentrega` (
  `Id_DetalleEntrega` int(10) unsigned NOT NULL auto_increment,
  `fechaEntrega` datetime NOT NULL,
  `horaEntrega` varchar(50) NOT NULL,
  `cantidadEntregada` double NOT NULL,
  `precioUnitario` double NOT NULL,
  `fk_Empleado` int(10) unsigned NOT NULL,
  `fk_Entrega` int(10) unsigned NOT NULL,
  `fk_DetalleTransaccionVenta` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Id_DetalleEntrega`),
  KEY `FK_detalleentregaEmpleado` (`fk_Empleado`),
  KEY `FK_detalleentregaEntrega` (`fk_Entrega`),
  KEY `FK_detalleentregaDetalleTransaccionVenta` (`fk_DetalleTransaccionVenta`),
  CONSTRAINT `FK_detalleentregaDetalleTransaccionVenta` FOREIGN KEY (`fk_DetalleTransaccionVenta`) REFERENCES `detalletransaccionventa` (`Id_DetalleTransaccionVenta`),
  CONSTRAINT `FK_detalleentregaEmpleado` FOREIGN KEY (`fk_Empleado`) REFERENCES `empleado` (`Id_Empleado`),
  CONSTRAINT `FK_detalleentregaEntrega` FOREIGN KEY (`fk_Entrega`) REFERENCES `entrega` (`Id_Entrega`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detalleentrega`
--

/*!40000 ALTER TABLE `detalleentrega` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalleentrega` ENABLE KEYS */;


--
-- Definition of table `detallefactura`
--

DROP TABLE IF EXISTS `detallefactura`;
CREATE TABLE `detallefactura` (
  `Id_DetalleFactura` int(10) unsigned NOT NULL auto_increment,
  `fk_DetalleTransaccionVenta` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Id_DetalleFactura`),
  KEY `FK_DetalleFacturaDetalleTransaccion` (`fk_DetalleTransaccionVenta`),
  CONSTRAINT `FK_DetalleFacturaDetalleTransaccion` FOREIGN KEY (`fk_DetalleTransaccionVenta`) REFERENCES `detalletransaccionventa` (`Id_DetalleTransaccionVenta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detallefactura`
--

/*!40000 ALTER TABLE `detallefactura` DISABLE KEYS */;
/*!40000 ALTER TABLE `detallefactura` ENABLE KEYS */;


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
  CONSTRAINT `fk_EstadoDetallePedidoaProveedor` FOREIGN KEY (`fk_Estado`) REFERENCES `estado` (`Id_Estado`) ON UPDATE CASCADE,
  CONSTRAINT `fk_PedidoAProveedorDetallePedidoaProveedor` FOREIGN KEY (`fk_PedidoAProveedor`) REFERENCES `pedidoaproveedor` (`Id_PedidoAProveedor`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ProductoDetallePedidoAProveedor` FOREIGN KEY (`fk_Producto`) REFERENCES `producto` (`Id_Producto`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detallepedidoaproveedor`
--

/*!40000 ALTER TABLE `detallepedidoaproveedor` DISABLE KEYS */;
INSERT INTO `detallepedidoaproveedor` (`Id_DetallePedidoAProveedor`,`cantidad`,`precioActual`,`fk_Producto`,`fk_PedidoAProveedor`,`fk_Estado`) VALUES 
 (43,1000,2,2,37,10),
 (44,2000,2,2,38,9),
 (45,2000,2,2,39,10),
 (46,3000,2,8,39,10),
 (47,3000,1,4,39,10),
 (48,40,5,1,40,10),
 (49,10,5,3,41,9),
 (50,30,4,5,41,9);
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
  CONSTRAINT `fk_DetallePedidoAProveedorDetalleRecepcion` FOREIGN KEY (`fk_DetallePedidoAProveedor`) REFERENCES `detallepedidoaproveedor` (`Id_DetallePedidoAProveedor`) ON UPDATE CASCADE,
  CONSTRAINT `fk_EmpleadoDetalleRecepcion` FOREIGN KEY (`fk_Empleado`) REFERENCES `empleado` (`Id_Empleado`) ON UPDATE CASCADE,
  CONSTRAINT `fk_ProductoDetalleRecepcion` FOREIGN KEY (`fk_Producto`) REFERENCES `producto` (`Id_Producto`) ON UPDATE CASCADE,
  CONSTRAINT `fk_RecepcionDetalleRecepcion` FOREIGN KEY (`fk_Recepcion`) REFERENCES `recepcion` (`Id_Recepcion`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detallerecepcion`
--

/*!40000 ALTER TABLE `detallerecepcion` DISABLE KEYS */;
/*!40000 ALTER TABLE `detallerecepcion` ENABLE KEYS */;


--
-- Definition of table `detalletransaccionventa`
--

DROP TABLE IF EXISTS `detalletransaccionventa`;
CREATE TABLE `detalletransaccionventa` (
  `Id_DetalleTransaccionVenta` int(10) unsigned NOT NULL auto_increment,
  `cantidad` double NOT NULL,
  `precioUnitario` double NOT NULL,
  `fk_Producto` int(10) unsigned NOT NULL,
  `fk_TransaccionVenta` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Id_DetalleTransaccionVenta`),
  KEY `FK_DetalleTransaccionVentaProducto` (`fk_Producto`),
  KEY `FK_DetalleTransaccionVentaTransaccion` (`fk_TransaccionVenta`),
  CONSTRAINT `FK_DetalleTransaccionVentaProducto` FOREIGN KEY (`fk_Producto`) REFERENCES `producto` (`Id_Producto`),
  CONSTRAINT `FK_DetalleTransaccionVentaTransaccion` FOREIGN KEY (`fk_TransaccionVenta`) REFERENCES `transaccionventa` (`Id_TransaccionVenta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detalletransaccionventa`
--

/*!40000 ALTER TABLE `detalletransaccionventa` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalletransaccionventa` ENABLE KEYS */;


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
  CONSTRAINT `fk_CiudadDomicilio` FOREIGN KEY (`fk_Ciudad`) REFERENCES `ciudad` (`Id_Ciudad`) ON UPDATE CASCADE,
  CONSTRAINT `fk_ProvinciaDomicilio` FOREIGN KEY (`fk_Provincia`) REFERENCES `provincia` (`Id_Provincia`) ON UPDATE CASCADE
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
 (23,'Tineo',234,'-',0,'Ayacucho',1,1),
 (24,'Maipu',435,'-',0,'Centro',6,1);
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
 (1,'RAMON','HEREÑU'),
 (2,'DAVID','BIANCOTTI'),
 (3,'YONY','SCHYANI'),
 (4,'FERNANDO','BORNORONI');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;


--
-- Definition of table `entrega`
--

DROP TABLE IF EXISTS `entrega`;
CREATE TABLE `entrega` (
  `Id_Entrega` int(10) unsigned NOT NULL auto_increment,
  `fechaRealEntrega` datetime NOT NULL,
  `horaRealEntrega` varchar(50) NOT NULL,
  `montoTotal` double NOT NULL,
  `fk_PedidoCliente` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Id_Entrega`),
  KEY `FK_EntregaPedido` (`fk_PedidoCliente`),
  CONSTRAINT `FK_EntregaPedido` FOREIGN KEY (`fk_PedidoCliente`) REFERENCES `pedidocliente` (`Id_PedidoCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `entrega`
--

/*!40000 ALTER TABLE `entrega` DISABLE KEYS */;
/*!40000 ALTER TABLE `entrega` ENABLE KEYS */;


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
-- Definition of table `facturacliente`
--

DROP TABLE IF EXISTS `facturacliente`;
CREATE TABLE `facturacliente` (
  `Id_FacturaCliente` int(10) unsigned NOT NULL auto_increment,
  `montoTotal` double NOT NULL,
  `fecha` datetime NOT NULL,
  `numero` int(10) unsigned NOT NULL,
  `fk_TipoFactura` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Id_FacturaCliente`),
  KEY `FK_facturaclienteTipoFactura` (`fk_TipoFactura`),
  CONSTRAINT `FK_facturaclienteTipoFactura` FOREIGN KEY (`fk_TipoFactura`) REFERENCES `tipofactura` (`Id_TipoFactura`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `facturacliente`
--

/*!40000 ALTER TABLE `facturacliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `facturacliente` ENABLE KEYS */;


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
  CONSTRAINT `fk_PedidoAProveedorFacturaProveedor` FOREIGN KEY (`fk_PedidoAProveedor`) REFERENCES `pedidoaproveedor` (`Id_PedidoAProveedor`) ON UPDATE CASCADE,
  CONSTRAINT `fk_TipoFacturaFacturaProveedor` FOREIGN KEY (`fk_TipoFactura`) REFERENCES `tipofactura` (`Id_TipoFactura`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `facturaproveedor`
--

/*!40000 ALTER TABLE `facturaproveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `facturaproveedor` ENABLE KEYS */;


--
-- Definition of table `facturaservicio`
--

DROP TABLE IF EXISTS `facturaservicio`;
CREATE TABLE `facturaservicio` (
  `Id_FacturaServicio` int(10) unsigned NOT NULL auto_increment,
  `fk_PrestacionServicio` int(10) unsigned NOT NULL,
  `fk_FacturaCliente` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Id_FacturaServicio`),
  KEY `FK_FacturaServicioPrestacion` (`fk_PrestacionServicio`),
  KEY `FK_FacturaServicioFacturaCliente` (`fk_FacturaCliente`),
  CONSTRAINT `FK_FacturaServicioFacturaCliente` FOREIGN KEY (`fk_FacturaCliente`) REFERENCES `facturacliente` (`Id_FacturaCliente`),
  CONSTRAINT `FK_FacturaServicioPrestacion` FOREIGN KEY (`fk_PrestacionServicio`) REFERENCES `prestacionservicio` (`Id_PrestacionServicio`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `facturaservicio`
--

/*!40000 ALTER TABLE `facturaservicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `facturaservicio` ENABLE KEYS */;


--
-- Definition of table `facturatransaccionventa`
--

DROP TABLE IF EXISTS `facturatransaccionventa`;
CREATE TABLE `facturatransaccionventa` (
  `Id_FacturaTransaccionVenta` int(10) unsigned NOT NULL auto_increment,
  `fk_TransaccionVenta` int(10) unsigned NOT NULL,
  `fk_DetalleFactura` int(10) unsigned NOT NULL,
  `fk_FacturaCliente` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Id_FacturaTransaccionVenta`),
  KEY `FK_FacturaTransaccionVentaTransaccion` (`fk_TransaccionVenta`),
  KEY `FK_FacturaTransaccionVentaDetalleFactura` (`fk_DetalleFactura`),
  KEY `FK_facturatransaccionventaFacturaCliente` (`fk_FacturaCliente`),
  CONSTRAINT `FK_FacturaTransaccionVentaDetalleFactura` FOREIGN KEY (`fk_DetalleFactura`) REFERENCES `detallefactura` (`Id_DetalleFactura`),
  CONSTRAINT `FK_facturatransaccionventaFacturaCliente` FOREIGN KEY (`fk_FacturaCliente`) REFERENCES `facturacliente` (`Id_FacturaCliente`),
  CONSTRAINT `FK_FacturaTransaccionVentaTransaccion` FOREIGN KEY (`fk_TransaccionVenta`) REFERENCES `transaccionventa` (`Id_TransaccionVenta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `facturatransaccionventa`
--

/*!40000 ALTER TABLE `facturatransaccionventa` DISABLE KEYS */;
/*!40000 ALTER TABLE `facturatransaccionventa` ENABLE KEYS */;


--
-- Definition of table `formapago`
--

DROP TABLE IF EXISTS `formapago`;
CREATE TABLE `formapago` (
  `Id_FormaPago` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(50) NOT NULL,
  `descrpcion` varchar(50) NOT NULL,
  PRIMARY KEY  (`Id_FormaPago`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `formapago`
--

/*!40000 ALTER TABLE `formapago` DISABLE KEYS */;
/*!40000 ALTER TABLE `formapago` ENABLE KEYS */;


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
INSERT INTO `impuesto` (`Id_Impuesto`,`nombre`,`porcentaje`,`monto`) VALUES 
 (1,'IMPUESTO INTERNO GASOIL',10,0.35),
 (2,'IMPUESTO INTERNO NAFTAS',25,0.75);
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
INSERT INTO `iva` (`Id_Iva`,`porcentaje`,`descripcion`) VALUES 
 (1,21,' '),
 (2,10,' ');
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
  CONSTRAINT `fk_SurtidorManguera` FOREIGN KEY (`fk_Surtidor`) REFERENCES `surtidor` (`Id_Surtidor`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_TanqueManguera` FOREIGN KEY (`fk_Tanque`) REFERENCES `tanque` (`Id_Tanque`) ON DELETE SET NULL ON UPDATE CASCADE
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
  CONSTRAINT `fk_EmpleadoMedicionTanque` FOREIGN KEY (`fk_Empleado`) REFERENCES `empleado` (`Id_Empleado`) ON UPDATE CASCADE,
  CONSTRAINT `fk_TanqueMedicionTanque` FOREIGN KEY (`fk_Tanque`) REFERENCES `tanque` (`Id_Tanque`) ON DELETE CASCADE ON UPDATE CASCADE
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
  CONSTRAINT `fk_EmpleadoPedidoAProveedor` FOREIGN KEY (`fk_Empleado`) REFERENCES `empleado` (`Id_Empleado`) ON UPDATE CASCADE,
  CONSTRAINT `fk_EstadoPedidoAProveedor` FOREIGN KEY (`fk_Estado`) REFERENCES `estado` (`Id_Estado`) ON UPDATE CASCADE,
  CONSTRAINT `fk_ProveedorPedidoAProveedor` FOREIGN KEY (`fk_Proveedor`) REFERENCES `proveedor` (`Id_Proveedor`) ON UPDATE CASCADE,
  CONSTRAINT `fk_RepresentanteDeProveedorPedidoAProveedor` FOREIGN KEY (`fk_RepresentanteDeProveedor`) REFERENCES `representantedeproveedor` (`Id_RepresentanteDeProveedor`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pedidoaproveedor`
--

/*!40000 ALTER TABLE `pedidoaproveedor` DISABLE KEYS */;
INSERT INTO `pedidoaproveedor` (`Id_PedidoAProveedor`,`fechaRealizacion`,`horaRealizacion`,`fechaEstimadaEntrega`,`horaEstimadaEntrega`,`montoTotal`,`fk_Estado`,`fk_RepresentanteDeProveedor`,`fk_Proveedor`,`fk_Empleado`) VALUES 
 (37,'2008-08-11 00:00:00','19:34:34','2008-08-11 00:00:00','19:19:00',2000,9,NULL,1,1),
 (38,'2008-08-13 00:00:00','17:28:25','2008-08-13 00:00:00','17:17:00',4000,9,NULL,1,1),
 (39,'2008-08-13 00:00:00','17:46:38','2008-08-13 00:00:00','17:17:00',4000,9,6,1,1),
 (40,'2008-09-09 00:00:00','17:58:18','2008-09-09 00:00:00','17:57:00',200,10,NULL,23,1),
 (41,'2008-09-09 00:00:00','17:58:18','2008-09-09 00:00:00','17:57:00',200,9,NULL,16,1);
/*!40000 ALTER TABLE `pedidoaproveedor` ENABLE KEYS */;


--
-- Definition of table `pedidocliente`
--

DROP TABLE IF EXISTS `pedidocliente`;
CREATE TABLE `pedidocliente` (
  `Id_PedidoCliente` int(10) unsigned NOT NULL auto_increment,
  `fechaSolicitudEntrega` datetime NOT NULL,
  `horaSolicitudEntrega` varchar(50) NOT NULL,
  `fk_TransaccionVenta` int(10) unsigned NOT NULL,
  `fk_Estado` int(10) unsigned NOT NULL,
  `nroPedido` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Id_PedidoCliente`),
  KEY `FK_TransaccionVentaPedido` (`fk_TransaccionVenta`),
  KEY `FK_EstadoPedido` (`fk_Estado`),
  CONSTRAINT `FK_EstadoPedido` FOREIGN KEY (`fk_Estado`) REFERENCES `estado` (`Id_Estado`),
  CONSTRAINT `FK_TransaccionVentaPedido` FOREIGN KEY (`fk_TransaccionVenta`) REFERENCES `transaccionventa` (`Id_TransaccionVenta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pedidocliente`
--

/*!40000 ALTER TABLE `pedidocliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedidocliente` ENABLE KEYS */;


--
-- Definition of table `prestacionservicio`
--

DROP TABLE IF EXISTS `prestacionservicio`;
CREATE TABLE `prestacionservicio` (
  `Id_PrestacionServicio` int(10) unsigned NOT NULL auto_increment,
  `fk_Cliente` int(10) unsigned NOT NULL,
  `fecha` datetime NOT NULL,
  `hora` varchar(50) NOT NULL,
  `fk_Empleado` int(10) unsigned NOT NULL,
  `fk_Estado` int(10) unsigned NOT NULL,
  `fk_Vehiculo` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Id_PrestacionServicio`),
  KEY `FK_PrestacionServicioCliente` (`fk_Cliente`),
  KEY `FK_prestacionservicioEmpleado` (`fk_Empleado`),
  KEY `FK_prestacionservicioEstado` (`fk_Estado`),
  KEY `FK_prestacionservicioVehiculo` (`fk_Vehiculo`),
  CONSTRAINT `FK_PrestacionServicioCliente` FOREIGN KEY (`fk_Cliente`) REFERENCES `cliente` (`Id_Cliente`),
  CONSTRAINT `FK_prestacionservicioEmpleado` FOREIGN KEY (`fk_Empleado`) REFERENCES `empleado` (`Id_Empleado`),
  CONSTRAINT `FK_prestacionservicioEstado` FOREIGN KEY (`fk_Estado`) REFERENCES `estado` (`Id_Estado`),
  CONSTRAINT `FK_prestacionservicioVehiculo` FOREIGN KEY (`fk_Vehiculo`) REFERENCES `vehiculo` (`Id_Vehiculo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prestacionservicio`
--

/*!40000 ALTER TABLE `prestacionservicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `prestacionservicio` ENABLE KEYS */;


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
  CONSTRAINT `fk_IvaProducto` FOREIGN KEY (`fk_Iva`) REFERENCES `iva` (`Id_Iva`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `producto`
--

/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` (`Id_Producto`,`nombre`,`precioCosto`,`precioVenta`,`stockMinimo`,`stockActual`,`unidad`,`autorizacionPedidoEnPlaya`,`fk_Iva`) VALUES 
 (1,'CARBON',5,7,50,644,'kg',1,NULL),
 (2,'SUPER',2,4,500,2000,'litros',1,NULL),
 (3,'GALLETAS SONRISAS',1,2,50,197,'gramos',1,NULL),
 (4,'NORMAL',1,3,500,2000,'litros',1,NULL),
 (5,'COCA COLA',1,2,15,253,'litros',1,NULL),
 (6,'CHUPETIN PICO DULCE',2,4,45,100,'kg',1,NULL),
 (7,'LECHE SANCOR',1,2,15,60,'litros',1,NULL),
 (8,'KEROSENE',1,2,500,4000,'litros',1,NULL),
 (9,'GASOIL',2.37,2.599,1500,5000,'Litros',0,1),
 (10,'ULTRA',2.78,3.35,1500,2000,'Litros',0,1);
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
  CONSTRAINT `fk_ProductoProductoDePlaya` FOREIGN KEY (`fk_Producto`) REFERENCES `producto` (`Id_Producto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `productodeplaya`
--

/*!40000 ALTER TABLE `productodeplaya` DISABLE KEYS */;
INSERT INTO `productodeplaya` (`Id_ProductoDePlaya`,`stockEnPlaya`,`fk_Producto`) VALUES 
 (1,10,1);
/*!40000 ALTER TABLE `productodeplaya` ENABLE KEYS */;


--
-- Definition of table `productoxproveedor`
--

DROP TABLE IF EXISTS `productoxproveedor`;
CREATE TABLE `productoxproveedor` (
  `fk_Producto` int(10) unsigned NOT NULL,
  `fk_Proveedor` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`fk_Producto`,`fk_Proveedor`),
  KEY `FK_ProveedorPxP` (`fk_Proveedor`),
  CONSTRAINT `FK_ProductoPxP` FOREIGN KEY (`fk_Producto`) REFERENCES `producto` (`Id_Producto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ProveedorPxP` FOREIGN KEY (`fk_Proveedor`) REFERENCES `proveedor` (`Id_Proveedor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `productoxproveedor`
--

/*!40000 ALTER TABLE `productoxproveedor` DISABLE KEYS */;
INSERT INTO `productoxproveedor` (`fk_Producto`,`fk_Proveedor`) VALUES 
 (2,1),
 (4,1),
 (8,1),
 (9,1),
 (10,1),
 (6,16),
 (7,17),
 (1,18),
 (3,23),
 (5,24);
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
  CONSTRAINT `fk_CondicionIvaProveedor` FOREIGN KEY (`fk_CondicionIva`) REFERENCES `condicioniva` (`Id_CondicionIva`) ON UPDATE CASCADE,
  CONSTRAINT `fk_CuentaCorrienteProveedor` FOREIGN KEY (`fk_CuentaCorrienteProveedor`) REFERENCES `cuentacorrienteproveedor` (`Id_CuentaCorrienteProveedor`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_DomicilioProveedor` FOREIGN KEY (`fk_Domicilio`) REFERENCES `domicilio` (`Id_Domicilio`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `proveedor`
--

/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` (`Id_Proveedor`,`razonSocial`,`cuit`,`ingresoBruto`,`fk_CondicionIva`,`fk_Domicilio`,`fk_CuentaCorrienteProveedor`) VALUES 
 (1,'YPF',1234,2345,1,1,NULL),
 (15,'SHELL',123,123456798,2,16,NULL),
 (16,'ARCOR',85,78956,2,17,NULL),
 (17,'ESSO GIAVENO',456,54689713,2,18,NULL),
 (18,'CARBONERA ',4242,1234,2,19,NULL),
 (21,'LA SERENISIMA',20145896,0,2,22,NULL),
 (22,'SANCOR',43242,456,2,23,NULL),
 (23,'ESSO LUBRICANTES',4568,555,2,24,NULL),
 (24,'COCA COLA',311221321,12312,2,24,NULL);
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
 (15,'6'),
 (16,'4'),
 (17,'6'),
 (18,'5'),
 (21,'4'),
 (22,'4'),
 (23,'5'),
 (24,'4');
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
  CONSTRAINT `FK_PaisProvincia` FOREIGN KEY (`fk_Pais`) REFERENCES `pais` (`Id_Pais`) ON DELETE CASCADE ON UPDATE CASCADE
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
  CONSTRAINT `fk_FacturaProveedorRecepcion` FOREIGN KEY (`fk_FacturaProveedor`) REFERENCES `facturaproveedor` (`Id_FacturaProveedor`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_PedidoAProveedorRecepcion` FOREIGN KEY (`fk_PedidoAProveedor`) REFERENCES `pedidoaproveedor` (`Id_PedidoAProveedor`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `recepcion`
--

/*!40000 ALTER TABLE `recepcion` DISABLE KEYS */;
/*!40000 ALTER TABLE `recepcion` ENABLE KEYS */;


--
-- Definition of table `reclamocliente`
--

DROP TABLE IF EXISTS `reclamocliente`;
CREATE TABLE `reclamocliente` (
  `Id_ReclamoCliente` int(10) unsigned NOT NULL auto_increment,
  `fecha` datetime NOT NULL,
  `motivo` varchar(50) NOT NULL,
  `fk_Cliente` int(10) unsigned NOT NULL,
  `fk_Estado` int(10) unsigned NOT NULL,
  `fk_TransaccionVenta` int(10) unsigned NOT NULL,
  `fk_TipoReclamo` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Id_ReclamoCliente`),
  KEY `FK_reclamoclienteCliente` (`fk_Cliente`),
  KEY `FK_reclamoclienteEstado` (`fk_Estado`),
  KEY `FK_reclamoclienteTransaccion` (`fk_TransaccionVenta`),
  KEY `FK_reclamoclienteTipo` (`fk_TipoReclamo`),
  CONSTRAINT `FK_reclamoclienteCliente` FOREIGN KEY (`fk_Cliente`) REFERENCES `cliente` (`Id_Cliente`),
  CONSTRAINT `FK_reclamoclienteEstado` FOREIGN KEY (`fk_Estado`) REFERENCES `estado` (`Id_Estado`),
  CONSTRAINT `FK_reclamoclienteTipo` FOREIGN KEY (`fk_TipoReclamo`) REFERENCES `tiporeclamo` (`Id_TipoReclamo`),
  CONSTRAINT `FK_reclamoclienteTransaccion` FOREIGN KEY (`fk_TransaccionVenta`) REFERENCES `transaccionventa` (`Id_TransaccionVenta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reclamocliente`
--

/*!40000 ALTER TABLE `reclamocliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `reclamocliente` ENABLE KEYS */;


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
  CONSTRAINT `fk_DetalleRecepcionRemito` FOREIGN KEY (`fk_DetalleRecepcion`) REFERENCES `detallerecepcion` (`Id_DetalleRecepcion`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_RecepcionRemito` FOREIGN KEY (`fk_Recepcion`) REFERENCES `recepcion` (`Id_Recepcion`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `remito`
--

/*!40000 ALTER TABLE `remito` DISABLE KEYS */;
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
  CONSTRAINT `fk_ProveedorRepresentanteDeProveedor` FOREIGN KEY (`fk_Proveedor`) REFERENCES `proveedor` (`Id_Proveedor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `representantedeproveedor`
--

/*!40000 ALTER TABLE `representantedeproveedor` DISABLE KEYS */;
INSERT INTO `representantedeproveedor` (`Id_RepresentanteDeProveedor`,`nombre`,`apellido`,`fk_Proveedor`) VALUES 
 (6,'Cecilia','Andurno',17),
 (7,'Diego','Cohen',18),
 (9,'Roberto','Gonzales',1),
 (10,'Carlos','Alvarez',16),
 (11,'Mauro','Bornoroni',16),
 (12,'Mauricio','Escalante',16),
 (13,'Lucas','Sortica',16),
 (14,'Fernando','Salas',16),
 (15,'Mario','Cristaldo',15);
/*!40000 ALTER TABLE `representantedeproveedor` ENABLE KEYS */;


--
-- Definition of table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
CREATE TABLE `reserva` (
  `Id_Reserva` int(10) unsigned NOT NULL auto_increment,
  `fk_Cliente` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Id_Reserva`),
  KEY `FK_ReservaCliente` (`fk_Cliente`),
  CONSTRAINT `FK_ReservaCliente` FOREIGN KEY (`fk_Cliente`) REFERENCES `cliente` (`Id_Cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reserva`
--

/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;


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
  CONSTRAINT `fk_EstadoSurtidor` FOREIGN KEY (`fk_Estado`) REFERENCES `estado` (`Id_Estado`) ON UPDATE CASCADE
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
  CONSTRAINT `fk_CombustibleTanque` FOREIGN KEY (`fk_Combustible`) REFERENCES `combustible` (`Id_Combustible`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_EstadoTanque` FOREIGN KEY (`fk_Estado`) REFERENCES `estado` (`Id_Estado`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tanque`
--

/*!40000 ALTER TABLE `tanque` DISABLE KEYS */;
INSERT INTO `tanque` (`Id_Tanque`,`numero`,`capacidad`,`fechaInstalacion`,`stockTanque`,`fk_Estado`,`fk_Combustible`) VALUES 
 (1,1,10000,'1999-10-10 00:00:00',5000,3,1),
 (2,2,20000,'1999-10-10 00:00:00',17000,3,2),
 (3,3,20000,'1999-10-10 00:00:00',15000,3,3),
 (4,4,20000,'1999-10-10 00:00:00',12000,3,4),
 (5,5,20000,'1999-10-10 00:00:00',12000,3,3),
 (6,6,20000,'1999-10-10 00:00:00',17500,3,5),
 (7,7,20000,'1999-10-10 00:00:00',13444,3,2),
 (8,8,20000,'1999-10-10 00:00:00',15677,3,2),
 (9,9,20000,'1999-10-10 00:00:00',12000,3,3),
 (10,10,20000,'1999-10-10 00:00:00',1500,3,4),
 (11,11,20000,'1999-10-10 00:00:00',17000,3,4);
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
  CONSTRAINT `fk_TipoTelefonoTelefono` FOREIGN KEY (`fk_TipoTelefono`) REFERENCES `tipotelefono` (`id_TipoTelefono`) ON UPDATE CASCADE
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
 (63,23456789,351,2),
 (64,4899255,351,2),
 (65,4820609,351,2),
 (66,4899255,351,2),
 (67,458963,351,2);
/*!40000 ALTER TABLE `telefono` ENABLE KEYS */;


--
-- Definition of table `telefonoxcliente`
--

DROP TABLE IF EXISTS `telefonoxcliente`;
CREATE TABLE `telefonoxcliente` (
  `fk_Telefono` int(10) unsigned NOT NULL auto_increment,
  `fk_Cliente` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`fk_Telefono`,`fk_Cliente`),
  KEY `FK_TelefonoXClienteCliente` (`fk_Cliente`),
  CONSTRAINT `FK_TelefonoXClienteCliente` FOREIGN KEY (`fk_Cliente`) REFERENCES `cliente` (`Id_Cliente`),
  CONSTRAINT `FK_TelefonoXClienteTel` FOREIGN KEY (`fk_Telefono`) REFERENCES `telefono` (`Id_Telefono`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `telefonoxcliente`
--

/*!40000 ALTER TABLE `telefonoxcliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `telefonoxcliente` ENABLE KEYS */;


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
 (56,22),
 (67,23);
/*!40000 ALTER TABLE `telefonoxproveedor` ENABLE KEYS */;


--
-- Definition of table `telefonoxrepresentanteproveedor`
--

DROP TABLE IF EXISTS `telefonoxrepresentanteproveedor`;
CREATE TABLE `telefonoxrepresentanteproveedor` (
  `fk_Telefono` int(10) unsigned NOT NULL,
  `fk_RepresentanteDeProveedor` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`fk_Telefono`,`fk_RepresentanteDeProveedor`),
  KEY `fk_RepresentanteDeProveedorTxRP` (`fk_RepresentanteDeProveedor`),
  CONSTRAINT `fk_RepresentanteDeProveedorTxRP` FOREIGN KEY (`fk_RepresentanteDeProveedor`) REFERENCES `representantedeproveedor` (`Id_RepresentanteDeProveedor`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_TelefonoTxRP` FOREIGN KEY (`fk_Telefono`) REFERENCES `telefono` (`Id_Telefono`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `telefonoxrepresentanteproveedor`
--

/*!40000 ALTER TABLE `telefonoxrepresentanteproveedor` DISABLE KEYS */;
INSERT INTO `telefonoxrepresentanteproveedor` (`fk_Telefono`,`fk_RepresentanteDeProveedor`) VALUES 
 (39,6),
 (40,7),
 (41,7),
 (57,9),
 (58,9),
 (59,9),
 (60,10),
 (61,11),
 (62,12),
 (63,13),
 (64,14),
 (65,15),
 (66,15);
/*!40000 ALTER TABLE `telefonoxrepresentanteproveedor` ENABLE KEYS */;


--
-- Definition of table `tipodocumento`
--

DROP TABLE IF EXISTS `tipodocumento`;
CREATE TABLE `tipodocumento` (
  `id_TipoDocumento` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY  (`id_TipoDocumento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipodocumento`
--

/*!40000 ALTER TABLE `tipodocumento` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipodocumento` ENABLE KEYS */;


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
 (1,'A'),
 (2,'B'),
 (3,'TICKET');
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
INSERT INTO `tipomoneda` (`id_TipoMoneda`,`nombre`) VALUES 
 (1,'PESOS ARGENTINOS'),
 (2,'DOLARES '),
 (3,'EUROS');
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
  PRIMARY KEY  (`fk_TipoProveedor`,`fk_Producto`),
  KEY `fk_ProductoTPxP` (`fk_Producto`),
  CONSTRAINT `fk_ProductoTPxP` FOREIGN KEY (`fk_Producto`) REFERENCES `producto` (`Id_Producto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_TipoProveedorTPxP` FOREIGN KEY (`fk_TipoProveedor`) REFERENCES `tipoproveedor` (`Id_TipoProveedor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipoproveedorxproducto`
--

/*!40000 ALTER TABLE `tipoproveedorxproducto` DISABLE KEYS */;
INSERT INTO `tipoproveedorxproducto` (`fk_TipoProveedor`,`fk_Producto`) VALUES 
 (5,1),
 (6,2),
 (4,3),
 (6,4),
 (4,5),
 (4,6),
 (4,7);
/*!40000 ALTER TABLE `tipoproveedorxproducto` ENABLE KEYS */;


--
-- Definition of table `tiporeclamo`
--

DROP TABLE IF EXISTS `tiporeclamo`;
CREATE TABLE `tiporeclamo` (
  `Id_TipoReclamo` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  PRIMARY KEY  (`Id_TipoReclamo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tiporeclamo`
--

/*!40000 ALTER TABLE `tiporeclamo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tiporeclamo` ENABLE KEYS */;


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


--
-- Definition of table `tipovehiculo`
--

DROP TABLE IF EXISTS `tipovehiculo`;
CREATE TABLE `tipovehiculo` (
  `Id_TipoVehiculo` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  PRIMARY KEY  (`Id_TipoVehiculo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipovehiculo`
--

/*!40000 ALTER TABLE `tipovehiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipovehiculo` ENABLE KEYS */;


--
-- Definition of table `tipoventa`
--

DROP TABLE IF EXISTS `tipoventa`;
CREATE TABLE `tipoventa` (
  `Id_TipoVenta` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  PRIMARY KEY  (`Id_TipoVenta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipoventa`
--

/*!40000 ALTER TABLE `tipoventa` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipoventa` ENABLE KEYS */;


--
-- Definition of table `transaccionventa`
--

DROP TABLE IF EXISTS `transaccionventa`;
CREATE TABLE `transaccionventa` (
  `Id_TransaccionVenta` int(10) unsigned NOT NULL auto_increment,
  `fecha` datetime NOT NULL,
  `hora` varchar(50) NOT NULL,
  `montoTotal` double NOT NULL,
  `fk_Cliente` int(10) unsigned NOT NULL,
  `fk_Empleado` int(10) unsigned NOT NULL,
  `fk_FormaPago` int(10) unsigned NOT NULL,
  `fk_TipoVenta` int(10) unsigned NOT NULL,
  `nroTransaccionVenta` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Id_TransaccionVenta`),
  KEY `FK_transaccionventaCliente` (`fk_Cliente`),
  KEY `FK_transaccionventaEmpleado` (`fk_Empleado`),
  KEY `FK_transaccionventaFormaPago` (`fk_FormaPago`),
  KEY `FK_transaccionventaTipoVenta` (`fk_TipoVenta`),
  CONSTRAINT `FK_transaccionventaCliente` FOREIGN KEY (`fk_Cliente`) REFERENCES `cliente` (`Id_Cliente`),
  CONSTRAINT `FK_transaccionventaEmpleado` FOREIGN KEY (`fk_Empleado`) REFERENCES `empleado` (`Id_Empleado`),
  CONSTRAINT `FK_transaccionventaFormaPago` FOREIGN KEY (`fk_FormaPago`) REFERENCES `formapago` (`Id_FormaPago`),
  CONSTRAINT `FK_transaccionventaTipoVenta` FOREIGN KEY (`fk_TipoVenta`) REFERENCES `tipoventa` (`Id_TipoVenta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaccionventa`
--

/*!40000 ALTER TABLE `transaccionventa` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaccionventa` ENABLE KEYS */;


--
-- Definition of table `vehiculo`
--

DROP TABLE IF EXISTS `vehiculo`;
CREATE TABLE `vehiculo` (
  `Id_Vehiculo` int(10) unsigned NOT NULL auto_increment,
  `patente` int(10) unsigned NOT NULL,
  `aÃƒÂ±o` varchar(45) NOT NULL,
  `modelo` varchar(45) NOT NULL,
  `marca` varchar(50) NOT NULL,
  `fk_TipoVehiculo` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Id_Vehiculo`),
  KEY `FK_vehiculoTipo` (`fk_TipoVehiculo`),
  CONSTRAINT `FK_vehiculoTipo` FOREIGN KEY (`fk_TipoVehiculo`) REFERENCES `tipovehiculo` (`Id_TipoVehiculo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vehiculo`
--

/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;


--
-- Definition of table `venta`
--

DROP TABLE IF EXISTS `venta`;
CREATE TABLE `venta` (
  `Id_Venta` int(10) unsigned NOT NULL auto_increment,
  `fk_TransaccionVenta` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Id_Venta`),
  KEY `FK_TransaccionVentaVenta` (`fk_TransaccionVenta`),
  CONSTRAINT `FK_TransaccionVentaVenta` FOREIGN KEY (`fk_TransaccionVenta`) REFERENCES `transaccionventa` (`Id_TransaccionVenta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `venta`
--

/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
