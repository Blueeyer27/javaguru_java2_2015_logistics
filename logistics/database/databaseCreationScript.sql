SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `logistics` DEFAULT CHARACTER SET utf8 ;
USE `logistics` ;

-- -----------------------------------------------------
-- Table `logistics`.`users`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `logistics`.`users`;

CREATE TABLE IF NOT EXISTS `logistics`.`users` (
  `UserID` INT(11) NOT NULL AUTO_INCREMENT,
  `FirstName` CHAR(32) NOT NULL,
  `LastName` CHAR(32) NOT NULL,
  PRIMARY KEY (`UserID`)
);

-- -----------------------------------------------------
-- Table `logistics`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `logistics`.`user`;
		
CREATE TABLE `user` (
  `id` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `login` CHAR(32) NOT NULL,
  `password` CHAR(32) NOT NULL,
  `first_name` CHAR(32) NOT NULL,
  `last_name` CHAR(32) NOT NULL,
  `e_mail` CHAR(32) NOT NULL,
  `phone_number` CHAR(20) NOT NULL,
  `company_id` INTEGER(11) NOT NULL,
  PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- Table `logistics`.`company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `logistics`.`company`;

CREATE TABLE `company` (
  `id` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `name` CHAR(50) NOT NULL,
  `reg_number` CHAR(50) NOT NULL,
  `reg_address` CHAR(100) NOT NULL,
  `actual_address` CHAR(100) NOT NULL,
  `bank` CHAR(50) NOT NULL,
  `iban` CHAR(50) NOT NULL,
  `country` CHAR(50) NOT NULL,
  `type` CHAR(30) NOT NULL,
  PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- Table `logistics`.`vehicle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `logistics`.`vehicle`;
		
CREATE TABLE `vehicle` (
  `id` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `user_id` INTEGER(11) NOT NULL,
  `name` CHAR(50) NOT NULL,
  `type` CHAR(30) NOT NULL,
  `plate_number` CHAR(30) NOT NULL,
  `trailer_number` CHAR(30) NOT NULL,
  `capacity` DECIMAL NOT NULL,
  `status` CHAR(30) NOT NULL,
  PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- Table `logistics`.`agreement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `logistics`.`agreement`;
		
CREATE TABLE `agreement` (
  `id` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `cargo_id` INTEGER(11) NOT NULL ,
  `vehicle_id` INTEGER(11) NOT NULL ,
  `status` CHAR(30) NOT NULL ,
  PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- Table `logistics`.`cargo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `logistics`.`cargo`;
		
CREATE TABLE `cargo` (
  `id` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `user_id` INTEGER(11) NOT NULL,
  `vehicle_type` CHAR(20) NOT NULL,
  `weight` DECIMAL NOT NULL,
  `load_address` CHAR(100) NOT NULL,
  `unload_address` CHAR(100) NOT NULL,
  `load_date` DATE NOT NULL,
  `unload_date` DATE NOT NULL,
  `status` CHAR(30) NOT NULL,
  PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- Foreign Keys 
-- -----------------------------------------------------
/*
ALTER TABLE `user` ADD FOREIGN KEY (company_id) REFERENCES `company` (`id`);
ALTER TABLE `cargo` ADD FOREIGN KEY (user_id) REFERENCES `user` (`id`);
ALTER TABLE `vehicle` ADD FOREIGN KEY (user_id) REFERENCES `user` (`id`);
ALTER TABLE `agreement` ADD FOREIGN KEY (cargo_id) REFERENCES `cargo` (`id`);
ALTER TABLE `agreement` ADD FOREIGN KEY (vehicle_id) REFERENCES `vehicle` (`id`);
*/

-- -----------------------------------------------------
-- Table Properties
-- -----------------------------------------------------
ALTER TABLE `user` ENGINE = InnoDB AUTO_INCREMENT = 1002;
ALTER TABLE `vehicle` ENGINE = InnoDB AUTO_INCREMENT = 1002;
ALTER TABLE `agreement` ENGINE = InnoDB AUTO_INCREMENT = 1002;
ALTER TABLE `cargo` ENGINE = InnoDB AUTO_INCREMENT = 1002;
ALTER TABLE `company` ENGINE = InnoDB AUTO_INCREMENT = 1002;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;