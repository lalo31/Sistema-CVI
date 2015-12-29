-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema DbScvi
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `DbScvi` ;

-- -----------------------------------------------------
-- Schema DbScvi
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `DbScvi` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
SHOW WARNINGS;
USE `DbScvi` ;

-- -----------------------------------------------------
-- Table `Categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Categoria` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Categoria` (
  `idCategoria` INT(11) NOT NULL AUTO_INCREMENT,
  `tipoCategoria` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCategoria`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `CatProveedor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CatProveedor` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `CatProveedor` (
  `numeroProveedores` INT(11) NOT NULL AUTO_INCREMENT,
  `rfcProveedores` VARCHAR(45) NOT NULL,
  `nombreProveedores` VARCHAR(45) NOT NULL,
  `nombreDistribuidora` VARCHAR(45) NOT NULL,
  `telefono` INT(11) NOT NULL,
  `correoProveedor` VARCHAR(45) NOT NULL,
  `Categoria_idCategoria` INT(11) NOT NULL,
  PRIMARY KEY (`numeroProveedores`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `CatProducto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CatProducto` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `CatProducto` (
  `idProducto` INT(11) NOT NULL AUTO_INCREMENT,
  `numeroCodigo` INT(11) NOT NULL,
  `nombreProducto` VARCHAR(45) NOT NULL,
  `fechaRegistroProducto` DATE NOT NULL,
  `descripcionProducto` VARCHAR(45) NOT NULL,
  `Categoria_idCategoria` INT(11) NOT NULL,
  `CatProveedor_numeroProveedores` INT(11) NOT NULL,
  `precioVentaUnitario` DECIMAL NOT NULL,
  `cantidad` INT(11) NOT NULL,
  PRIMARY KEY (`idProducto`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `TipoUsuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TipoUsuario` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `TipoUsuario` (
  `idTipoUsuario` INT(11) NOT NULL AUTO_INCREMENT,
  `tipoUsuario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTipoUsuario`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Usuarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Usuarios` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Usuarios` (
  `idUsuarios` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apPaterno` VARCHAR(45) NOT NULL,
  `apMaterno` VARCHAR(45) NOT NULL,
  `telefono` INT(11) NOT NULL,
  `nombreUsuario` VARCHAR(45) NOT NULL,
  `contrasena` VARCHAR(45) NOT NULL,
  `TipoUsuario_idTipoUsuario` INT(11) NOT NULL,
  PRIMARY KEY (`idUsuarios`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Pedido` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Pedido` (
  `numeroPedido` INT(11) NOT NULL AUTO_INCREMENT,
  `fechaPedido` DATE NOT NULL,
  `cantidadRequerida` INT(11) NOT NULL,
  `precioAprox` FLOAT NOT NULL,
  `Usuarios_idUsuarios` INT(11) NOT NULL,
  `Categoria_idCategoria` INT(11) NOT NULL,
  `CatProveedor_numeroProveedores` INT(11) NOT NULL,
  `CatProducto_idProducto` INT(11) NOT NULL,
  PRIMARY KEY (`numeroPedido`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Venta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Venta` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Venta` (
  `numeroVenta` INT(11) NOT NULL AUTO_INCREMENT,
  `precioVentaTotal` DECIMAL NOT NULL,
  `fechaVenta` DATE NULL,
  PRIMARY KEY (`numeroVenta`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `VentaDetalle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `VentaDetalle` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `VentaDetalle` (
  `idVentaDetalle` INT(11) NOT NULL AUTO_INCREMENT,
  `CatProducto_idProducto` INT(11) NOT NULL,
  `Venta_numeroVenta` INT(11) NOT NULL,
  `codigoBarrasProducto` INT(11) NOT NULL,
  `nombreProducto` VARCHAR(45) NOT NULL,
  `precioVentaUnitarioProducto` DECIMAL NOT NULL,
  `cantidad` INT(11) NOT NULL,
  `totalPrecioVenta` DECIMAL NOT NULL,
  PRIMARY KEY (`idVentaDetalle`))
ENGINE = InnoDB;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
