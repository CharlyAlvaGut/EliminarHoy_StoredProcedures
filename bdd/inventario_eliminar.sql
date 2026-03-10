/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50744
Source Host           : localhost:3306
Source Database       : inventario_eliminar

Target Server Type    : MYSQL
Target Server Version : 50744
File Encoding         : 65001

Date: 2026-03-10 11:44:15
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
-- Procedure structure for sp_actualizarProducto
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_actualizarProducto`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizarProducto`(IN `nombre` varchar(20),IN `descripcion` varchar(100), IN `precio` decimal(10,2), IN `existencia` int(11), IN `id_cat` int(11), IN `id_prod` int(11))
BEGIN
		DECLARE error_ocurrido INT DEFAULT 0;
		DECLARE resultado VARCHAR(100);

    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    SET error_ocurrido = 1;

		UPDATE producto 
		SET nombreProducto = nombre, 
				descripcionProducto = descripcion, 
				precioProducto = precio, 
				existencia = existencia, 
				create_at = NOW(), 
				idCategoria = id_cat 
		WHERE idProducto = id_prod;

    IF error_ocurrido = 1 THEN
				SET resultado = 'Error al actualizar el producto! :(';
    ELSE
        SET resultado = 'Producto actualizado con exito! :)';
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
-- Procedure structure for sp_eliminarProducto
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_eliminarProducto`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminarProducto`(IN `id` int)
BEGIN
	DECLARE error_ocurrido INT DEFAULT 0;
	DECLARE resultado VARCHAR(100);

	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
	SET error_ocurrido = 1;

	DELETE FROM producto WHERE idProducto = id;

	IF error_ocurrido = 1 THEN
			SET resultado = 'Error al eliminar el producto! :(';
	ELSE
			SET resultado = 'Producto eliminado con exito! :)';
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
-- Procedure structure for sp_insertarProducto
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_insertarProducto`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertarProducto`(IN `nombre` varchar(20),IN `descripcion` varchar(100), IN `precio` decimal(10,2), IN `existencia` int(11), IN `id_cat` int(11))
BEGIN
	  DECLARE error_ocurrido INT DEFAULT 0;
		DECLARE resultado VARCHAR(100);

    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    SET error_ocurrido = 1;

    INSERT INTO producto (nombreProducto, descripcionProducto, precioProducto, existencia, create_at, idCategoria) 
		VALUES (nombre,descripcion,precio,existencia,NOW(),id_cat);

    IF error_ocurrido = 1 THEN
				SET resultado = 'Error al agregar el producto! :(';
    ELSE
        SET resultado = 'Producto agregado con exito! :)';
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

-- ----------------------------
-- Procedure structure for sp_obtenerProducto
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_obtenerProducto`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_obtenerProducto`(IN `id` int)
BEGIN
		DECLARE error_ocurrido INT DEFAULT 0;
		DECLARE resultado VARCHAR(100) DEFAULT 'Consulta realizada correctamente';

		DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
		SET error_ocurrido = 1;

		SELECT * 
		FROM producto 
		WHERE idProducto = id;

		IF error_ocurrido = 1 THEN
				SET resultado = 'Error al consultar el produicto';
		END IF;

		SELECT resultado AS tResultado;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for sp_obtenerProductos
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_obtenerProductos`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_obtenerProductos`()
BEGIN
		DECLARE error_ocurrido INT DEFAULT 0;
		DECLARE resultado VARCHAR(100) DEFAULT 'Consulta realizada correctamente';

		DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
		SET error_ocurrido = 1;

		SELECT * 
		FROM producto;

		IF error_ocurrido = 1 THEN
				SET resultado = 'Error al consultar los productos disponibles';
		END IF;

		SELECT resultado AS tResultado;
END
;;
DELIMITER ;
