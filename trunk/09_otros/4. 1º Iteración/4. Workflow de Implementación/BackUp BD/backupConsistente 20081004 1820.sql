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
 (2,1),
 (3,1),
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
 (4,10),
 (1,11),
 (4,11);
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
 (1,2345670876543,12,2),
 (2,8765342189063,13,2),
 (3,7654324598763,14,2),
 (4,7854325678543,15,1),
 (5,8754367854328,16,1),
 (6,6754325678543,17,4),
 (7,7654896523456,18,4),
 (8,8967452309653,19,4),
 (9,7645345678903,20,3),
 (10,8965432345678,21,3),
 (11,7856780345632,22,3),
 (12,7865432345654,23,3),
 (13,7856432345678,24,3);
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
 (1,'VILLA CARLOS PAZ',1),
 (2,'VILLA MARIA',1),
 (3,'SAN RAFAEL',2),
 (4,'MENDOZA',2),
 (5,'CATAMARCA',4),
 (6,'CORDOBA',1),
 (7,'SAN JAVIER',3),
 (8,'CAPITAL FEDERAL',9),
 (9,'LA PLATA',9),
 (532,'ACHIRAS',1),
 (533,'ADELIA MARIA',1),
 (534,'AGUA DE ORO',1),
 (535,'ALCIRA GIGENA',1),
 (536,'ALDEA SANTA MARIA',1),
 (537,'ALEJANDRO ROCA',1),
 (538,'ALEJO LEDESMA',1),
 (539,'ALICIA',1),
 (540,'ALMAFUERTE',1),
 (541,'ALPA CORRAL',1),
 (542,'ALTA GRACIA',1),
 (543,'ALTO ALEGRE',1),
 (544,'ALTO DE LOS QUEBRACHOS',1),
 (545,'ALTOS DE CHIPION',1),
 (546,'AMBOY',1),
 (547,'AMBUL',1),
 (548,'ANA ZUMARAN',1),
 (549,'ANISACATE',1),
 (550,'ARGUELLO',1),
 (551,'ARIAS',1),
 (552,'ARROYITO',1),
 (553,'ARROYO ALGODON',1),
 (554,'ARROYO CABRAL',1),
 (555,'ARROYO LOS PATOS',1),
 (556,'ASSUNTA',1),
 (557,'ATAHONA',1),
 (558,'AUSONIA',1),
 (559,'AVELLANEDA',1),
 (560,'BALLESTEROS',1),
 (561,'BALLESTEROS SUD',1),
 (562,'BALNEARIA',1),
 (563,'BAADO DE SOTO',1),
 (564,'BELL VILLE',1);
INSERT INTO `ciudad` (`Id_Ciudad`,`nombre`,`fk_Provincia`) VALUES 
 (565,'BENGOLEA',1),
 (566,'BENJAMIN GOULD',1),
 (567,'BERROTARAN',1),
 (568,'BIALET MASSE',1),
 (569,'BOUWER',1),
 (570,'BRINKMANN',1),
 (571,'BUCHARDO',1),
 (572,'BULNES',1),
 (573,'CABALANGO',1),
 (574,'CALAMUCHITA',1),
 (575,'CALCHIN',1),
 (576,'CALCHIN OESTE',1),
 (577,'CALMAYO',1),
 (578,'CAMILO ALDAO',1),
 (579,'CAMINIAGA',1),
 (580,'CAADA DE LUQUE',1),
 (581,'CAADA DE MACHADO',1),
 (582,'CAADA DE RIO PINTO',1),
 (583,'CAADA DEL SAUCE',1),
 (584,'CANALS',1),
 (585,'CANDELARIA SUD',1),
 (586,'CAPILLA DE REMEDIOS',1),
 (587,'CAPILLA DE SITON',1),
 (588,'CAPILLA DEL CARMEN',1),
 (589,'CAPILLA DEL MONTE',1),
 (590,'CAPITAL',1),
 (591,'CAPITAN GRAL B. OHIGGINS',1),
 (592,'CARNERILLO',1),
 (593,'CARRILOBO',1),
 (594,'CASA GRANDE',1),
 (595,'CAVANAGH',1),
 (596,'CERRO COLORADO',1),
 (597,'CHAJN',1),
 (598,'CHALACEA',1),
 (599,'CHAAR VIEJO',1),
 (600,'CHANCAN',1),
 (601,'CHARBONIER',1),
 (602,'CHARRAS',1),
 (603,'CHAZN',1),
 (604,'CHILIBROSTE',1);
INSERT INTO `ciudad` (`Id_Ciudad`,`nombre`,`fk_Provincia`) VALUES 
 (605,'CHUCUL',1),
 (606,'CHUA',1),
 (607,'CHUA HUASI',1),
 (608,'CHURQUI CAADA',1),
 (609,'CIENAGA DEL CORO',1),
 (610,'CINTRA',1),
 (611,'COL. ALMADA',1),
 (612,'COL. ANITA',1),
 (613,'COL. BARGE',1),
 (614,'COL. BISMARK',1),
 (615,'COL. BREMEN',1),
 (616,'COL. CAROYA',1),
 (617,'COL. ITALIANA',1),
 (618,'COL. ITURRASPE',1),
 (619,'COL. LAS CUATRO ESQUINAS',1),
 (620,'COL. LAS PICHANAS',1),
 (621,'COL. MARINA',1),
 (622,'COL. PROSPERIDAD',1),
 (623,'COL. SAN BARTOLOME',1),
 (624,'COL. SAN PEDRO',1),
 (625,'COL. TIROLESA',1),
 (626,'COL. VICENTE AGUERO',1),
 (627,'COL. VIDELA',1),
 (628,'COL. VIGNAUD',1),
 (629,'COL. WALTELINA',1),
 (630,'COLAZO',1),
 (631,'COMECHINGONES',1),
 (632,'CONLARA',1),
 (633,'COPACABANA',1),
 (634,'CORONEL BAIGORRIA',1),
 (635,'CORONEL MOLDES',1),
 (636,'CORRAL DE BUSTOS',1),
 (637,'CORRALITO',1),
 (638,'COSQUN',1),
 (639,'COSTA SACATE',1),
 (640,'CRUZ ALTA',1),
 (641,'CRUZ DE CAA',1),
 (642,'CRUZ DEL EJE',1),
 (643,'CUESTA BLANCA',1);
INSERT INTO `ciudad` (`Id_Ciudad`,`nombre`,`fk_Provincia`) VALUES 
 (644,'DEAN FUNES',1),
 (645,'DEL CAMPILLO',1),
 (646,'DESPEADEROS',1),
 (647,'DEVOTO',1),
 (648,'DIEGO DE ROJAS',1),
 (649,'DIQUE CHICO',1),
 (650,'EL ARAADO',1),
 (651,'EL BRETE',1),
 (652,'EL CHACHO',1),
 (653,'EL CRISPN',1),
 (654,'EL FORTN',1),
 (655,'EL MANZANO',1),
 (656,'EL RASTREADOR',1),
 (657,'EL RODEO',1),
 (658,'EL TO',1),
 (659,'ELENA',1),
 (660,'EMBALSE',1),
 (661,'ESQUINA',1),
 (662,'ESTACIN GRAL. PAZ',1),
 (663,'ESTACIN JUREZ CELMAN',1),
 (664,'ESTANCIA DE GUADALUPE',1),
 (665,'ESTANCIA VIEJA',1),
 (666,'ETRURIA',1),
 (667,'EUFRASIO LOZA',1),
 (668,'FALDA DEL CARMEN',1),
 (669,'FREYRE',1),
 (670,'GRAL. BALDISSERA',1),
 (671,'GRAL. CABRERA',1),
 (672,'GRAL. DEHEZA',1),
 (673,'GRAL. FOTHERINGHAM',1),
 (674,'GRAL. LEVALLE',1),
 (675,'GRAL. ROCA',1),
 (676,'GUANACO MUERTO',1),
 (677,'GUASAPAMPA',1),
 (678,'GUATIMOZIN',1),
 (679,'GUTENBERG',1),
 (680,'HERNANDO',1),
 (681,'HUANCHILLAS',1),
 (682,'HUERTA GRANDE',1),
 (683,'HUINCA RENANCO',1);
INSERT INTO `ciudad` (`Id_Ciudad`,`nombre`,`fk_Provincia`) VALUES 
 (684,'IDIAZABAL',1),
 (685,'IMPIRA',1),
 (686,'INRIVILLE',1),
 (687,'ISLA VERDE',1),
 (688,'ITAL',1),
 (689,'JAMES CRAIK',1),
 (690,'JESS MARA',1),
 (691,'JOVITA',1),
 (692,'JUSTINIANO POSSE',1),
 (693,'KM 658',1),
 (694,'L. V. MANSILLA',1),
 (695,'LA BATEA',1),
 (696,'LA CALERA',1),
 (697,'LA CARLOTA',1),
 (698,'LA CAROLINA',1),
 (699,'LA CAUTIVA',1),
 (700,'LA CESIRA',1),
 (701,'LA CRUZ',1),
 (702,'LA CUMBRE',1),
 (703,'LA CUMBRECITA',1),
 (704,'LA FALDA',1),
 (705,'LA FRANCIA',1),
 (706,'LA GRANJA',1),
 (707,'LA HIGUERA',1),
 (708,'LA LAGUNA',1),
 (709,'LA PAISANITA',1),
 (710,'LA PALESTINA',1),
 (711,'LA PAQUITA',1),
 (712,'LA PARA',1),
 (713,'LA PAZ',1),
 (714,'LA PLAYA',1),
 (715,'LA PLAYOSA',1),
 (716,'LA POBLACIN',1),
 (717,'LA POSTA',1),
 (718,'LA PUERTA',1),
 (719,'LA QUINTA',1),
 (720,'LA RANCHERITA',1),
 (721,'LA RINCONADA',1),
 (722,'LA SERRANITA',1),
 (723,'LA TORDILLA',1),
 (724,'LABORDE',1),
 (725,'LABOULAYE',1),
 (726,'LAGUNA LARGA',1);
INSERT INTO `ciudad` (`Id_Ciudad`,`nombre`,`fk_Provincia`) VALUES 
 (727,'LAS ACEQUIAS',1),
 (728,'LAS ALBAHACAS',1),
 (729,'LAS ARRIAS',1),
 (730,'LAS BAJADAS',1),
 (731,'LAS CALERAS',1),
 (732,'LAS CALLES',1),
 (733,'LAS CAADAS',1),
 (734,'LAS GRAMILLAS',1),
 (735,'LAS HIGUERAS',1),
 (736,'LAS ISLETILLAS',1),
 (737,'LAS JUNTURAS',1),
 (738,'LAS PALMAS',1),
 (739,'LAS PEAS',1),
 (740,'LAS PEAS SUD',1),
 (741,'LAS PERDICES',1),
 (742,'LAS PLAYAS',1),
 (743,'LAS RABONAS',1),
 (744,'LAS SALADAS',1),
 (745,'LAS TAPIAS',1),
 (746,'LAS VARAS',1),
 (747,'LAS VARILLAS',1),
 (748,'LAS VERTIENTES',1),
 (749,'LEGUIZAMN',1),
 (750,'LEONES',1),
 (751,'LOS CEDROS',1),
 (752,'LOS CERRILLOS',1),
 (753,'LOS CHAARITOS',1),
 (754,'LOS CISNES',1),
 (755,'LOS COCOS',1),
 (756,'LOS CNDORES',1),
 (757,'LOS HORNILLOS',1),
 (758,'LOS HOYOS',1),
 (759,'LOS MISTOLES',1),
 (760,'LOS MOLINOS',1),
 (761,'LOS POZOS',1),
 (762,'LOS REARTES',1),
 (763,'LOS SURGENTES',1),
 (764,'LOS TALARES',1),
 (765,'LOS ZORROS',1),
 (766,'LOZADA',1);
INSERT INTO `ciudad` (`Id_Ciudad`,`nombre`,`fk_Provincia`) VALUES 
 (767,'LUCA',1),
 (768,'LUQUE',1),
 (769,'LUYABA',1),
 (770,'MALAGUEO',1),
 (771,'MALENA',1),
 (772,'MALVINAS ARGENTINAS',1),
 (773,'MANFREDI',1),
 (774,'MAQUINISTA GALLINI',1),
 (775,'MARCOS JUREZ',1),
 (776,'MARULL',1),
 (777,'MATORRALES',1),
 (778,'MATTALDI',1),
 (779,'MAYU SUMAJ',1),
 (780,'MEDIA NARANJA',1),
 (781,'MELO',1),
 (782,'MENDIOLAZA',1),
 (783,'MI GRANJA',1),
 (784,'MINA CLAVERO',1),
 (785,'MIRAMAR',1),
 (786,'MORRISON',1),
 (787,'MORTEROS',1),
 (788,'MTE. BUEY',1),
 (789,'MTE. CRISTO',1),
 (790,'MTE. DE LOS GAUCHOS',1),
 (791,'MTE. LEA',1),
 (792,'MTE. MAZ',1),
 (793,'MTE. RALO',1),
 (794,'NICOLS BRUZONE',1),
 (795,'NOETINGER',1),
 (796,'NONO',1),
 (797,'NUEVA 7',1),
 (798,'OBISPO TREJO',1),
 (799,'OLAETA',1),
 (800,'OLIVA',1),
 (801,'OLIVARES SAN NICOLS',1),
 (802,'ONAGOLTY',1),
 (803,'ONCATIVO',1),
 (804,'ORDOEZ',1),
 (805,'PACHECO DE MELO',1),
 (806,'PAMPAYASTA N.',1),
 (807,'PAMPAYASTA S.',1),
 (808,'PANAHOLMA',1),
 (809,'PASCANAS',1);
INSERT INTO `ciudad` (`Id_Ciudad`,`nombre`,`fk_Provincia`) VALUES 
 (810,'PASCO',1),
 (811,'PASO DEL DURAZNO',1),
 (812,'PASO VIEJO',1),
 (813,'PILAR',1),
 (814,'PINCN',1),
 (815,'PIQUILLN',1),
 (816,'PLAZA DE MERCEDES',1),
 (817,'PLAZA LUXARDO',1),
 (818,'PORTEA',1),
 (819,'POTRERO DE GARAY',1),
 (820,'POZO DEL MOLLE',1),
 (821,'POZO NUEVO',1),
 (822,'PUEBLO ITALIANO',1),
 (823,'PUESTO DE CASTRO',1),
 (824,'PUNTA DEL AGUA',1),
 (825,'QUEBRACHO HERRADO',1),
 (826,'QUILINO',1),
 (827,'RAFAEL GARCA',1),
 (828,'RANQUELES',1),
 (829,'RAYO CORTADO',1),
 (830,'REDUCCIN',1),
 (831,'RINCN',1),
 (832,'RO BAMBA',1),
 (833,'RO CEBALLOS',1),
 (834,'RO CUARTO',1),
 (835,'RO DE LOS SAUCES',1),
 (836,'RO PRIMERO',1),
 (837,'RO SEGUNDO',1),
 (838,'RO TERCERO',1),
 (839,'ROSALES',1),
 (840,'ROSARIO DEL SALADILLO',1),
 (841,'SACANTA',1),
 (842,'SAGRADA FAMILIA',1),
 (843,'SAIRA',1),
 (844,'SALADILLO',1),
 (845,'SALDN',1),
 (846,'SALSACATE',1),
 (847,'SALSIPUEDES',1),
 (848,'SAMPACHO',1),
 (849,'SAN AGUSTN',1),
 (850,'SAN ANTONIO DE ARREDONDO',1);
INSERT INTO `ciudad` (`Id_Ciudad`,`nombre`,`fk_Provincia`) VALUES 
 (851,'SAN ANTONIO DE LITN',1),
 (852,'SAN BASILIO',1),
 (853,'SAN CARLOS MINAS',1),
 (854,'SAN CLEMENTE',1),
 (855,'SAN ESTEBAN',1),
 (856,'SAN FRANCISCO',1),
 (857,'SAN IGNACIO',1),
 (858,'SAN JAVIER',1),
 (859,'SAN JERNIMO',1),
 (860,'SAN JOAQUN',1),
 (861,'SAN JOS DE LA DORMIDA',1),
 (862,'SAN JOS DE LAS SALINAS',1),
 (863,'SAN LORENZO',1),
 (864,'SAN MARCOS SIERRAS',1),
 (865,'SAN MARCOS SUD',1),
 (866,'SAN PEDRO',1),
 (867,'SAN PEDRO N.',1),
 (868,'SAN ROQUE',1),
 (869,'SAN VICENTE',1),
 (870,'SANTA CATALINA',1),
 (871,'SANTA ELENA',1),
 (872,'SANTA EUFEMIA',1),
 (873,'SANTA MARIA',1),
 (874,'SARMIENTO',1),
 (875,'SATURNINO M.LASPIUR',1),
 (876,'SAUCE ARRIBA',1),
 (877,'SEBASTIN ELCANO',1),
 (878,'SEEBER',1),
 (879,'SEGUNDA USINA',1),
 (880,'SERRANO',1),
 (881,'SERREZUELA',1),
 (882,'SGO. TEMPLE',1),
 (883,'SILVIO PELLICO',1),
 (884,'SIMBOLAR',1),
 (885,'SINSACATE',1),
 (886,'STA. ROSA DE CALAMUCHITA',1),
 (887,'STA. ROSA DE RO PRIMERO',1),
 (888,'SUCO',1);
INSERT INTO `ciudad` (`Id_Ciudad`,`nombre`,`fk_Provincia`) VALUES 
 (889,'TALA CAADA',1),
 (890,'TALA HUASI',1),
 (891,'TALAINI',1),
 (892,'TANCACHA',1),
 (893,'TANTI',1),
 (894,'TICINO',1),
 (895,'TINOCO',1),
 (896,'TO PUJIO',1),
 (897,'TOLEDO',1),
 (898,'TORO PUJIO',1),
 (899,'TOSNO',1),
 (900,'TOSQUITA',1),
 (901,'TRNSITO',1),
 (902,'TUCLAME',1),
 (903,'TUTTI',1),
 (904,'UCACHA',1),
 (905,'UNQUILLO',1),
 (906,'VALLE DE ANISACATE',1),
 (907,'VALLE HERMOSO',1),
 (908,'VLEZ SARFIELD',1),
 (909,'VIAMONTE',1),
 (910,'VICUA MACKENNA',1),
 (911,'VILLA ALLENDE',1),
 (912,'VILLA AMANCAY',1),
 (913,'VILLA ASCASUBI',1),
 (914,'VILLA CANDELARIA N.',1),
 (915,'VILLA CARLOS PAZ',1),
 (916,'VILLA CERRO AZUL',1),
 (917,'VILLA CIUDAD DE AMRICA',1),
 (918,'VILLA CIUDAD PQUE LOS REARTES',1),
 (919,'VILLA CONCEPCIN DEL TO',1),
 (920,'VILLA CURA BROCHERO',1),
 (921,'VILLA DE LAS ROSAS',1),
 (922,'VILLA DE MARA',1),
 (923,'VILLA DE POCHO',1),
 (924,'VILLA DE SOTO',1),
 (925,'VILLA DEL DIQUE',1),
 (926,'VILLA DEL PRADO',1),
 (927,'VILLA DEL ROSARIO',1);
INSERT INTO `ciudad` (`Id_Ciudad`,`nombre`,`fk_Provincia`) VALUES 
 (928,'VILLA DEL TOTORAL',1),
 (929,'VILLA DOLORES',1),
 (930,'VILLA EL CHANCAY',1),
 (931,'VILLA ELISA',1),
 (932,'VILLA FLOR SERRANA',1),
 (933,'VILLA FONTANA',1),
 (934,'VILLA GIARDINO',1),
 (935,'VILLA GRAL. BELGRANO',1),
 (936,'VILLA GUTIERREZ',1),
 (937,'VILLA HUIDOBRO',1),
 (938,'VILLA LA BOLSA',1),
 (939,'VILLA LOS AROMOS',1),
 (940,'VILLA LOS PATOS',1),
 (941,'VILLA MARA',1),
 (942,'VILLA NUEVA',1),
 (943,'VILLA PQUE. SANTA ANA',1),
 (944,'VILLA PQUE. SIQUIMAN',1),
 (945,'VILLA QUILLINZO',1),
 (946,'VILLA ROSSI',1),
 (947,'VILLA RUMIPAL',1),
 (948,'VILLA SAN ESTEBAN',1),
 (949,'VILLA SAN ISIDRO',1),
 (950,'VILLA 21',1),
 (951,'VILLA SARMIENTO (G.R)',1),
 (952,'VILLA SARMIENTO (S.A)',1),
 (953,'VILLA TULUMBA',1),
 (954,'VILLA VALERIA',1),
 (955,'VILLA YACANTO',1),
 (956,'WASHINGTON',1),
 (957,'WENCESLAO ESCALANTE',1),
 (958,'YCHO CRUZ SIERRAS',1),
 (959,'ESCOBAR',9),
 (960,'EXALTACIN DE LA CRUZ',9),
 (961,'FLORENTINO AMEGHINO',9),
 (962,'GARN',9);
INSERT INTO `ciudad` (`Id_Ciudad`,`nombre`,`fk_Provincia`) VALUES 
 (963,'GRAL. ALVARADO',9),
 (964,'GRAL. ALVEAR',9),
 (965,'GRAL. ARENALES',9),
 (966,'GRAL. BELGRANO',9),
 (967,'GRAL. GUIDO',9),
 (968,'GRAL. LAMADRID',9),
 (969,'GRAL. LAS HERAS',9),
 (970,'GRAL. LAVALLE',9),
 (971,'GRAL. MADARIAGA',9),
 (972,'GRAL. PACHECO',9),
 (973,'GRAL. PAZ',9),
 (974,'GRAL. PINTO',9),
 (975,'GRAL. PUEYRREDN',9),
 (976,'GRAL. RODRGUEZ',9),
 (977,'GRAL. VIAMONTE',9),
 (978,'GRAL. VILLEGAS',9),
 (979,'GUAMIN',9),
 (980,'GUERNICA',9),
 (981,'HIPLITO YRIGOYEN',9),
 (982,'ING. MASCHWITZ',9),
 (983,'JUNN',9),
 (984,'LA PLATA',9),
 (985,'LAPRIDA',9),
 (986,'LAS FLORES',9),
 (987,'LAS TONINAS',9),
 (988,'LEANDRO N. ALEM',9),
 (989,'LINCOLN',9),
 (990,'LOBERIA',9),
 (991,'LOBOS',9),
 (992,'LOS CARDALES',9),
 (993,'LOS TOLDOS',9),
 (994,'LUCILA DEL MAR',9),
 (995,'LUJN',9),
 (996,'MAGDALENA',9),
 (997,'MAIP',9),
 (998,'MAR CHIQUITA',9),
 (999,'MAR DE AJ',9),
 (1000,'MAR DE LAS PAMPAS',9),
 (1001,'MAR DEL PLATA',9),
 (1002,'MAR DEL TUY',9);
INSERT INTO `ciudad` (`Id_Ciudad`,`nombre`,`fk_Provincia`) VALUES 
 (1003,'MARCOS PAZ',9),
 (1004,'MERCEDES',9),
 (1005,'MIRAMAR',9),
 (1006,'MONTE',9),
 (1007,'MONTE HERMOSO',9),
 (1008,'MUNRO',9),
 (1009,'NAVARRO',9),
 (1010,'NECOCHEA',9),
 (1011,'OLAVARRA',9),
 (1012,'PARTIDO DE LA COSTA',9),
 (1013,'PEHUAJ',9),
 (1014,'PELLEGRINI',9),
 (1015,'PERGAMINO',9),
 (1016,'PIG',9),
 (1017,'PILA',9),
 (1018,'PILAR',9),
 (1019,'PINAMAR',9),
 (1020,'PINAR DEL SOL',9),
 (1021,'POLVORINES',9),
 (1022,'PTE. PERN',9),
 (1023,'PUN',9),
 (1024,'PUNTA INDIO',9),
 (1025,'RAMALLO',9),
 (1026,'RAUCH',9),
 (1027,'RIVADAVIA',9),
 (1028,'ROJAS',9),
 (1029,'ROQUE PREZ',9),
 (1030,'SAAVEDRA',9),
 (1031,'SALADILLO',9),
 (1032,'SALLIQUEL',9),
 (1033,'SALTO',9),
 (1034,'SAN ANDRS DE GILES',9),
 (1035,'SAN ANTONIO DE ARECO',9),
 (1036,'SAN ANTONIO DE PADUA',9),
 (1037,'SAN BERNARDO',9),
 (1038,'SAN CAYETANO',9),
 (1039,'SAN CLEMENTE DEL TUY',9),
 (1040,'SAN NICOLS',9),
 (1041,'SAN PEDRO',9),
 (1042,'SAN VICENTE',9),
 (1043,'SANTA TERESITA',9);
INSERT INTO `ciudad` (`Id_Ciudad`,`nombre`,`fk_Provincia`) VALUES 
 (1044,'SUIPACHA',9),
 (1045,'TANDIL',9),
 (1046,'TAPALQU',9),
 (1047,'TORDILLO',9),
 (1048,'TORNQUIST',9),
 (1049,'TRENQUE LAUQUEN',9),
 (1050,'TRES LOMAS',9),
 (1051,'VILLA GESELL',9),
 (1052,'VILLARINO',9),
 (1053,'ZRATE',9);
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
  `cuit` double NOT NULL,
  `numIngresoBruto` double NOT NULL,
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
 (1,7,'b'),
 (2,8,'y'),
 (4,9,'r'),
 (5,10,'a'),
 (6,11,'g');
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
 (4,1),
 (5,1),
 (1,2),
 (2,2),
 (6,2);
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
 (4,'MONOTRIBUTO','NO PAGA IVA');
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
INSERT INTO `descarga` (`Id_Descarga`,`cantidadLitros`,`fechaDescarga`,`horaDescarga`,`fk_Tanque`,`fk_DetalleRecepcion`) VALUES 
 (1,4000,'2008-10-04 00:00:00','18:16:00',2,4),
 (2,3000,'2008-10-04 00:00:00','18:16:00',4,5),
 (3,5000,'2008-10-04 00:00:00','18:16:00',5,6);
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
 (1,5000,2.7,10,1,10),
 (2,3000,2.6,9,1,10),
 (3,4000,2.2,8,1,10),
 (4,5000,2,5,2,11),
 (5,3000,3,7,2,11);
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
INSERT INTO `detallerecepcion` (`Id_DetalleRecepcion`,`fechaRecepcion`,`horaRealRecepcion`,`cantidadRecibida`,`precioUnitario`,`fk_Recepcion`,`fk_Producto`,`fk_Empleado`,`fk_DetallePedidoAProveedor`) VALUES 
 (4,'2008-10-04 00:00:00','18:16:00',4000,2.2,2,8,4,3),
 (5,'2008-10-04 00:00:00','18:16:00',3000,2.6,2,9,4,2),
 (6,'2008-10-04 00:00:00','18:16:00',5000,2.7,2,10,4,1);
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
 (3,'MAIPU',1,'-',0,'PALERMO',8,9),
 (4,'COLON',145,'-',0,'CENTRO',6,1),
 (5,'CASTRO BARROS',1233,'-',0,'SAN MARTIN',6,1),
 (6,'JUAN B JUSTO',1567,'-',0,'ALTA CORDOBA',6,1),
 (7,'MAIPU',1234,'-',0,'CENTRO',6,1),
 (8,'AV FUERZA AEREA',12343,'-',0,'SAN JERONIMO',6,1);
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
 (4,'FERNANDO','BORNORONI'),
 (5,'DIEGO','COHEN'),
 (6,'ARMANDO','AYALA'),
 (7,'CECILIA','ANDURNO');
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
 (10,'Recibido total',NULL),
 (11,'Pendiente de Envio',NULL);
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
 (1,'ARGENTINA'),
 (2,'BRASIL'),
 (3,'CHLE');
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
 (1,'2008-10-04 00:00:00','17:42:49','2008-10-04 00:00:00','17:41:00',30100,10,6,7,1),
 (2,'2008-10-04 17:47:32','17:47:32','2008-10-04 17:46:56','17:46:00',19000,11,1,2,1);
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
  `activo` tinyint(1) unsigned NOT NULL default '1',
  PRIMARY KEY  (`Id_Producto`),
  KEY `fk_IvaProducto` (`fk_Iva`),
  CONSTRAINT `fk_IvaProducto` FOREIGN KEY (`fk_Iva`) REFERENCES `iva` (`Id_Iva`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `producto`
--

/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` (`Id_Producto`,`nombre`,`precioCosto`,`precioVenta`,`stockMinimo`,`stockActual`,`unidad`,`autorizacionPedidoEnPlaya`,`fk_Iva`,`activo`) VALUES 
 (1,'CARBON X 5 KG.',3.25,5.5,5,10,'Unidad',1,1,1),
 (2,'HIELO X 3 KG.',2.9,5,5,10,'Unidad',1,1,1),
 (3,'GAS EN GARRAFA X 10 KG.',18,23,4,10,'Unidad',1,1,1),
 (4,'LEÑA X 5 KG.',3,5,5,10,'Unidad',1,1,1),
 (5,'ACEITE LUBRICANTE SUELTO X 1 LTS.',2,8,100,500,'Litros',0,1,1),
 (6,'ACEITE 2T MOTOS X 100 CC.',1.75,2.75,50,200,'Unidad',0,1,1),
 (7,'NAFTA SUPER',3,2.99,2000,10000,'Litros',0,1,1),
 (8,'NAFTA NORMAL',2.2,2.7,1000,9000,'Litros',0,NULL,1),
 (9,'KEROSENE',2.6,2.99,500,7000,'Litros',0,NULL,1),
 (10,'GASOIL',2.7,2.59,2000,20000,'Litros',0,NULL,1),
 (11,'NAFTA PREMIUM',2.8,3.2,500,4000,'Litros',0,1,1),
 (12,'COCA COLA X 1 LTS.',2.5,4,15,30,'Unidad',0,1,1),
 (13,'PEPSI X 1/2 LTS.',1.5,2.5,15,40,'Unidad',0,1,1),
 (14,'AGUA VILLA DEL SUR X 3/4 LTS.',2,2.8,10,30,'Unidad',0,1,1),
 (15,'GALLETA SONRISA',1.5,2.6,10,25,'Unidad',0,1,1),
 (16,'GALLETA RUMBA',2,2.7,10,30,'Unidad',0,1,1);
INSERT INTO `producto` (`Id_Producto`,`nombre`,`precioCosto`,`precioVenta`,`stockMinimo`,`stockActual`,`unidad`,`autorizacionPedidoEnPlaya`,`fk_Iva`,`activo`) VALUES 
 (17,'CHUPETIN PICO DULCE',0.25,0.5,20,50,'Unidad',1,1,1),
 (18,'CHICLE BELDENT',0.6,1,20,50,'Unidad',1,1,1),
 (19,'TORRON DE MANI ARCOR',0.25,0.5,10,25,'Unidad',1,1,1),
 (20,'LECHE SANCOR X 1LTS.',2,2.5,10,20,'Unidad',0,1,1),
 (21,'LECHE LA SERENISIMA X 1 LTS',2.3,2.8,10,20,'Unidad',0,1,1),
 (22,'YOGURT VAINILLA X 1 LTS.',2,3,5,15,'Unidad',0,1,1),
 (23,'YOGURT FRUTILLA X 1 LTS.',2,3,5,16,'Unidad',0,1,1),
 (24,'YOGURT DULCE DE LECHE X 1 LTS.',2,3,5,15,'Unidad',0,1,1);
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
 (1,10,1),
 (2,10,2),
 (3,10,3),
 (4,10,4),
 (5,20,5),
 (6,20,6);
/*!40000 ALTER TABLE `productodeplaya` ENABLE KEYS */;


--
-- Definition of table `productoxproveedor`
--

DROP TABLE IF EXISTS `productoxproveedor`;
CREATE TABLE `productoxproveedor` (
  `fk_Producto` int(10) unsigned NOT NULL,
  `fk_Proveedor` int(10) unsigned NOT NULL,
  `precioCostoProducto` double NOT NULL,
  PRIMARY KEY  (`fk_Producto`,`fk_Proveedor`),
  KEY `FK_ProveedorPxP` (`fk_Proveedor`),
  CONSTRAINT `FK_ProductoPxP` FOREIGN KEY (`fk_Producto`) REFERENCES `producto` (`Id_Producto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ProveedorPxP` FOREIGN KEY (`fk_Proveedor`) REFERENCES `proveedor` (`Id_Proveedor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `productoxproveedor`
--

/*!40000 ALTER TABLE `productoxproveedor` DISABLE KEYS */;
INSERT INTO `productoxproveedor` (`fk_Producto`,`fk_Proveedor`,`precioCostoProducto`) VALUES 
 (2,6,6),
 (5,2,2),
 (7,2,3),
 (7,7,2.9),
 (8,7,2.2),
 (9,7,2.6),
 (10,7,2.7),
 (11,7,3.4),
 (12,4,3),
 (17,5,0.5),
 (18,5,1),
 (19,5,0.5),
 (20,3,2),
 (22,3,2.5),
 (23,3,2.5),
 (24,3,2.5);
/*!40000 ALTER TABLE `productoxproveedor` ENABLE KEYS */;


--
-- Definition of table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE `proveedor` (
  `Id_Proveedor` int(10) unsigned NOT NULL auto_increment,
  `razonSocial` varchar(50) default NULL,
  `cuit` double default NULL,
  `ingresoBruto` double default NULL,
  `fk_CondicionIva` int(10) unsigned default NULL,
  `fk_Domicilio` int(10) unsigned default NULL,
  `fk_CuentaCorrienteProveedor` int(10) unsigned default NULL,
  PRIMARY KEY  (`Id_Proveedor`),
  KEY `fk_CondicionIvaProveedor` (`fk_CondicionIva`),
  KEY `fk_DomicilioProveedor` (`fk_Domicilio`),
  KEY `fk_CuentaCorrienteProveedor` (`fk_CuentaCorrienteProveedor`),
  CONSTRAINT `fk_CondicionIvaProveedor` FOREIGN KEY (`fk_CondicionIva`) REFERENCES `condicioniva` (`Id_CondicionIva`) ON UPDATE CASCADE,
  CONSTRAINT `fk_CuentaCorrienteProveedor` FOREIGN KEY (`fk_CuentaCorrienteProveedor`) REFERENCES `cuentacorrienteproveedor` (`Id_CuentaCorrienteProveedor`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_DomicilioProveedor` FOREIGN KEY (`fk_Domicilio`) REFERENCES `domicilio` (`Id_Domicilio`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `proveedor`
--

/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` (`Id_Proveedor`,`razonSocial`,`cuit`,`ingresoBruto`,`fk_CondicionIva`,`fk_Domicilio`,`fk_CuentaCorrienteProveedor`) VALUES 
 (2,'PETROBRAS ENERGIA SA',12345123451,12345123451,2,3,NULL),
 (3,'SANCOR',12345678900,12345678900,2,4,NULL),
 (4,'COCA COLA SA',12345612345,12345612345,2,5,NULL),
 (5,'ARCOR',12345671234,12345671234,2,6,NULL),
 (6,'HIELERA SA',98765432112,98765432112,2,7,NULL),
 (7,'ESSO',23456789009,23456789009,2,8,NULL);
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;


--
-- Definition of table `proveedorxtipoproveedor`
--

DROP TABLE IF EXISTS `proveedorxtipoproveedor`;
CREATE TABLE `proveedorxtipoproveedor` (
  `fk_Proveedor` int(10) unsigned NOT NULL,
  `fk_TipoProveedor` varchar(45) NOT NULL,
  PRIMARY KEY  (`fk_Proveedor`,`fk_TipoProveedor`),
  CONSTRAINT `fk_ProveedorPxTP` FOREIGN KEY (`fk_Proveedor`) REFERENCES `proveedor` (`Id_Proveedor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `proveedorxtipoproveedor`
--

/*!40000 ALTER TABLE `proveedorxtipoproveedor` DISABLE KEYS */;
INSERT INTO `proveedorxtipoproveedor` (`fk_Proveedor`,`fk_TipoProveedor`) VALUES 
 (2,'5'),
 (2,'6'),
 (3,'4'),
 (4,'4'),
 (5,'4'),
 (6,'5'),
 (7,'5'),
 (7,'6');
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
 (1,'CORDOBA',1),
 (2,'MENDOZA',1),
 (3,'MISIONES',1),
 (4,'CATAMARCA',1),
 (5,'FLORIANOPOLIS',2),
 (6,'BELLO HORIZONTE',2),
 (7,'SANTIAGO DE CHILE',3),
 (8,'VIÑA DEL MAR',3),
 (9,'BUENOS AIRES',1),
 (10,'TUCUMÁN',1),
 (11,'BUENOS AIRES-GBA',1),
 (12,'CAPITAL FEDERAL',1),
 (13,'CHACO',1),
 (14,'CHUBUT',1),
 (15,'CORRIENTES',1),
 (16,'ENTRE ROS',1),
 (17,'FORMOSA',1),
 (18,'JUJUY',1),
 (19,'LA PAMPA',1),
 (20,'LA RIOJA',1),
 (21,'NEUQUN',1),
 (22,'RO NEGRO',1),
 (23,'SALTA',1),
 (24,'SAN JUAN',1),
 (25,'SAN LUIS',1),
 (26,'SANTA CRUZ',1),
 (27,'SANTA FE',1),
 (28,'SANTIAGO DEL ESTERO',1),
 (29,'TIERRA DEL FUEGO',1);
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
INSERT INTO `recepcion` (`Id_Recepcion`,`fechaRealRecepcion`,`horaRealRecepcion`,`montoTotal`,`fk_FacturaProveedor`,`fk_PedidoAProveedor`) VALUES 
 (2,'2008-10-04 00:00:00','18:16:00',30100,NULL,1);
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
INSERT INTO `remito` (`Id_Remito`,`numero`,`fk_Recepcion`,`fk_DetalleRecepcion`) VALUES 
 (4,64791,2,4),
 (5,64792,2,5),
 (6,64793,2,6);
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
 (1,'PEDRO','ALONSO',2),
 (2,'JORGE','GOMEZ',3),
 (3,'cecilia','pereyra',4),
 (4,'pablo','stein',5),
 (5,'tatiana','rubin',6),
 (6,'diego','prinzi',7);
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
  `longitud` double default NULL,
  `altura` double default NULL,
  `desnivel` double NOT NULL default '0',
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
INSERT INTO `tanque` (`Id_Tanque`,`numero`,`capacidad`,`fechaInstalacion`,`stockTanque`,`fk_Estado`,`fk_Combustible`,`longitud`,`altura`,`desnivel`) VALUES 
 (1,1,20000,'1999-10-10 00:00:00',5000,3,1,5.25,1.59,0),
 (2,2,20000,'1999-10-10 00:00:00',6500,3,2,5.25,2.23,0),
 (3,3,20000,'1999-10-10 00:00:00',2500,3,2,5.25,2.23,0),
 (4,4,20000,'1999-10-10 00:00:00',7000,3,4,5.25,2.23,0),
 (5,5,20000,'1999-10-10 00:00:00',20000,3,5,5.25,2.23,0),
 (6,6,20000,'1999-10-10 00:00:00',4000,3,6,5.25,2.23,0),
 (7,7,20000,'1999-10-10 00:00:00',5000,3,1,5.25,2.23,0);
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
 (69,43446000,11,2),
 (70,4356789,351,3),
 (71,4678965,351,2),
 (72,4678954,351,2),
 (73,4789876,351,3),
 (74,156789876,351,1),
 (75,4703380,351,3),
 (76,156345264,351,1),
 (77,4235687,351,3),
 (78,4235674,351,2),
 (79,4786534,351,2),
 (80,156783465,351,1);
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
 (69,2),
 (70,3),
 (73,4),
 (75,5),
 (77,6),
 (79,7);
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
 (71,1),
 (72,2),
 (74,3),
 (76,4),
 (78,5),
 (80,6);
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
 (1,'GALLETAS',NULL),
 (2,'BEBIDAS',NULL),
 (3,'LACTEOS',NULL),
 (4,'GOLOSINAS',NULL);
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
 (5,2),
 (5,3),
 (5,4),
 (5,5),
 (5,6),
 (6,7),
 (6,8),
 (6,9),
 (6,10),
 (6,11),
 (4,12),
 (4,13),
 (4,14),
 (4,15),
 (4,16),
 (4,17),
 (4,18),
 (4,19),
 (4,20),
 (4,21),
 (4,22),
 (4,23),
 (4,24);
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
  `anio` varchar(45) NOT NULL,
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
