/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50744
Source Host           : localhost:3306
Source Database       : inventario_eliminar

Target Server Type    : MYSQL
Target Server Version : 50744
File Encoding         : 65001

Date: 2026-03-06 13:07:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for categoria
-- ----------------------------
DROP TABLE IF EXISTS `categoria`;
CREATE TABLE `categoria` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombreCategoria` varchar(20) NOT NULL,
  `descripcionCategoria` varchar(100) NOT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of categoria
-- ----------------------------

-- ----------------------------
-- Table structure for producto
-- ----------------------------
DROP TABLE IF EXISTS `producto`;
CREATE TABLE `producto` (
  `idProducto` int(11) NOT NULL AUTO_INCREMENT,
  `nombreProducto` varchar(50) NOT NULL,
  `descripcionProducto` varchar(100) NOT NULL,
  `precioProducto` decimal(10,2) NOT NULL,
  `existencia` int(11) NOT NULL,
  `create_at` date DEFAULT NULL,
  `idCategoria` int(11) NOT NULL,
  PRIMARY KEY (`idProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of producto
-- ----------------------------

-- ----------------------------
-- Procedure structure for sp_actualizarCategoria
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_actualizarCategoria`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizarCategoria`(IN `nombre` varchar(20),IN `descripcion` varchar(100), IN `id` int(11))
BEGIN
		DECLARE error_ocurrido INT DEFAULT 0;
		DECLARE resultado VARCHAR(100);

    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    SET error_ocurrido = 1;

    UPDATE categoria SET nombreCategoria = nombre, descripcionCategoria = descripcion WHERE idCategoria = id;

    IF error_ocurrido = 1 THEN
				SET resultado = 'Error al actualizar la categoria! :(';
    ELSE
        SET resultado = 'Categoria actualizada con exito! :)';
    END IF;

		SELECT resultado AS tResultado;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for sp_eliminarCategoria
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_eliminarCategoria`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminarCategoria`(IN `id` int)
BEGIN
	DECLARE error_ocurrido INT DEFAULT 0;
	DECLARE resultado VARCHAR(100);

	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
	SET error_ocurrido = 1;

	DELETE FROM categoria WHERE idCategoria = id;

	IF error_ocurrido = 1 THEN
			SET resultado = 'Error al eliminar la categoria! :(';
	ELSE
			SET resultado = 'Categoria eliminada con exito! :)';
	END IF;

	SELECT resultado AS tResultado;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for sp_insertarCategoria
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_insertarCategoria`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertarCategoria`(IN `nombre` varchar(20),IN `descripcion` varchar(100))
BEGIN
	  DECLARE error_ocurrido INT DEFAULT 0;
		DECLARE resultado VARCHAR(100);

    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    SET error_ocurrido = 1;

    INSERT INTO categoria (nombreCategoria, descripcionCategoria)
    VALUES (nombre, descripcion);

    IF error_ocurrido = 1 THEN
				SET resultado = 'Error al agregar la categoria! :(';
    ELSE
        SET resultado = 'Categoria agregada con exito! :)';
    END IF;

		SELECT resultado AS tResultado;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for sp_obtenerCategoria
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_obtenerCategoria`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_obtenerCategoria`(IN `id` int)
BEGIN
		DECLARE error_ocurrido INT DEFAULT 0;
		DECLARE resultado VARCHAR(100) DEFAULT 'Consulta realizada correctamente';

		DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
		SET error_ocurrido = 1;

		SELECT * 
		FROM categoria 
		WHERE idCategoria = id;

		IF error_ocurrido = 1 THEN
				SET resultado = 'Error al consultar la categoria';
		END IF;

		SELECT resultado AS tResultado;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for sp_obtenerCategorias
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_obtenerCategorias`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_obtenerCategorias`()
BEGIN
		DECLARE error_ocurrido INT DEFAULT 0;
		DECLARE resultado VARCHAR(100) DEFAULT 'Consulta realizada correctamente';

		DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
		SET error_ocurrido = 1;

		SELECT * 
		FROM categoria;

		IF error_ocurrido = 1 THEN
				SET resultado = 'Error al consultar las categorias disponibles';
		END IF;

		SELECT resultado AS tResultado;
END
;;
DELIMITER ;
