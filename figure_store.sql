-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema figure_store
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema figure_store
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `figure_store` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `figure_store` ;

-- -----------------------------------------------------
-- Table `figure_store`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `figure_store`.`categories` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(245) NULL DEFAULT NULL,
  `deleteAt` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `figure_store`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `figure_store`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fullName` VARCHAR(245) NULL DEFAULT NULL,
  `dob` DATE NULL DEFAULT NULL,
  `address` VARCHAR(245) NULL DEFAULT NULL,
  `phone` VARCHAR(10) NULL DEFAULT NULL,
  `email` VARCHAR(50) NULL DEFAULT NULL,
  `password` VARCHAR(245) NULL DEFAULT NULL,
  `createAt` DATE NULL DEFAULT NULL,
  `role` VARCHAR(45) NULL DEFAULT NULL,
  `deleteAt` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `figure_store`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `figure_store`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `createAt` DATE NULL DEFAULT NULL,
  `idUser` INT NULL DEFAULT NULL,
  `isPaid` BIT(1) NULL DEFAULT NULL,
  `subTotal` FLOAT NULL DEFAULT NULL,
  `discount` FLOAT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_id_user_idx` (`idUser` ASC) VISIBLE,
  CONSTRAINT `fk_id_user`
    FOREIGN KEY (`idUser`)
    REFERENCES `figure_store`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `figure_store`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `figure_store`.`products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(245) NULL DEFAULT NULL,
  `price` FLOAT NULL DEFAULT NULL,
  `leftQuantity` INT NULL DEFAULT NULL,
  `createAt` DATE NULL DEFAULT NULL,
  `scale` VARCHAR(45) NULL DEFAULT NULL,
  `idCategory` INT NULL DEFAULT NULL,
  `description` VARCHAR(245) NULL DEFAULT NULL,
  `imgLink` VARCHAR(255) NULL DEFAULT NULL,
  `deleteAt` DATE NULL DEFAULT NULL,
  `studio` VARCHAR(245) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_id_category_idx` (`idCategory` ASC) VISIBLE,
  CONSTRAINT `fk_id_category`
    FOREIGN KEY (`idCategory`)
    REFERENCES `figure_store`.`categories` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `figure_store`.`orderitems`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `figure_store`.`orderitems` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idOrder` INT NULL DEFAULT NULL,
  `idProduct` INT NULL DEFAULT NULL,
  `quantity` INT NULL DEFAULT NULL,
  `total` FLOAT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_id_order_idx` (`idOrder` ASC) VISIBLE,
  INDEX `fk_id_product_idx` (`idProduct` ASC) VISIBLE,
  CONSTRAINT `fk_id_order`
    FOREIGN KEY (`idOrder`)
    REFERENCES `figure_store`.`orders` (`id`),
  CONSTRAINT `fk_id_product`
    FOREIGN KEY (`idProduct`)
    REFERENCES `figure_store`.`products` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `categories` (`name`) 
VALUES 	('Animation'),
		('Film'),
        ('Game'),
        ('Human');
        
INSERT INTO `figure_store`.`products` (`id`, `name`, `price`, `leftQuantity`, `createAt`, `scale`, `idCategory`, `description`, `imgLink`, `studio`) VALUES ('1', 'Silvers Rayleigh and Luffy', '200', '50', '2023-06-14', '1:10', '1', ' Resin, PU Resin (Polyurethane) , PVC', 'https://cdn.shopify.com/s/files/1/0408/1097/1288/files/2_be300aa7-881a-4c7a-b077-340ef137e840.jpg', 'Jimei Palace');
INSERT INTO `figure_store`.`products` (`name`, `price`, `leftQuantity`, `createAt`, `scale`, `idCategory`, `description`, `imgLink`, `studio`)
 VALUES	('Silvers Rayleigh and Luffy', '200', '50', '2023-06-14', '1:10', '1', ' Resin, PU Resin (Polyurethane) , PVC', 'https://cdn.shopify.com/s/files/1/0408/1097/1288/files/2_be300aa7-881a-4c7a-b077-340ef137e840.jpg', 'Jimei Palace'),
		('Boa Hancock', '300', '55', '2023-06-14', '1:10', '3', ' Resin, PU Resin (Polyurethane) , PVC', 'https://cdn.shopify.com/s/files/1/0408/1097/1288/files/c40f4f9c449ac7eee5aacf1f8942f42d.jpg', 'Jimei Palace'),
		('Little Devil Lilith', '250', '25', '2023-06-14', '1:12', '2', ' Resin, PU Resin (Polyurethane) , PVC', 'https://cdn.shopify.com/s/files/1/0408/1097/1288/files/image_31e6ef05-015c-47b9-b38f-14c409ee04d4.jpg', 'AniMester'),
		('Neon Genesis Evangelion Asuka Langley Soryu', '300', '55', '2023-06-14', '1:10', '1', ' Resin, PU Resin (Polyurethane) , PVC', 'https://cdn.shopify.com/s/files/1/0408/1097/1288/products/1_8719da88-92c8-43c2-91fc-db56995302ea.jpg', 'AniMester'),
		('Re: Zero Starting Life in Another World Emilia and Rem', '300', '55', '2023-06-14', '1:10', '4', ' Resin, PU Resin (Polyurethane) , PVC', 'https://cdn.shopify.com/s/files/1/0408/1097/1288/products/01.jpg', 'AniMester');

INSERT INTO `users` (`fullName`, `dob`, `address`, `phone`, `email`, `password`, `createAt`, `role`) 
VALUES 	('Phan Em', '1979-05-30', 'Phan Thiet', '0178945689', 'phanem@gmail.com', '123456', '2023-06-14', 'USER'),
		('Tran Nguyen', '1977-05-30', 'Quang Binh', '0462845689', 'trannguyen@gmail.com', '123456', '2023-06-14', 'USER'),
		('Hoang Ha', '1977-06-30', 'Quang Tri', '0128435689', 'hoangha@gmail.com', '123456', '2023-06-14', 'USER'),
		('Nguyen Anh', '1978-07-30', 'Thai Nguyen', '0123495369', 'nguyenanh@gmail.com', '123456', '2023-06-14', 'USER'),
		('Van Nhat', '1979-08-30', 'Da Nang', '0102485689', 'vannhat@gmail.com', '123456', '2023-06-14', 'USER');

INSERT INTO `figure_store`.`orders` (`id`, `createAt`, `idUser`, `isPaid`, `discount`) VALUES ('1', '2023-06-14', '1', b'0', b'10');


