SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `logistics` DEFAULT CHARACTER SET utf8 ;
USE `logistics` ;

-- -----------------------------------------------------
-- Table `logistics`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `logistics`.`user`;

CREATE TABLE `user` (
  `id` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `login` CHAR(32) NOT NULL,
  `password` CHAR(100) NOT NULL,
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
  `capacity` FLOAT(8,2) NOT NULL,
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
-- Table `logistics`.`value`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `logistics`.`value`;
CREATE TABLE `value` (
  `id` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `type` CHAR(50) NOT NULL,
  `code` CHAR(50) NOT NULL,
  `value` CHAR(50) NOT NULL,
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
  `weight` FLOAT(8,2) NOT NULL,
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
ALTER TABLE `user` ADD FOREIGN KEY (company_id) REFERENCES `company` (`id`);
ALTER TABLE `cargo` ADD FOREIGN KEY (user_id) REFERENCES `user` (`id`);
ALTER TABLE `vehicle` ADD FOREIGN KEY (user_id) REFERENCES `user` (`id`);
ALTER TABLE `agreement` ADD FOREIGN KEY (cargo_id) REFERENCES `cargo` (`id`);
ALTER TABLE `agreement` ADD FOREIGN KEY (vehicle_id) REFERENCES `vehicle` (`id`);

-- -----------------------------------------------------
-- Table Properties
-- -----------------------------------------------------
ALTER TABLE `user` ENGINE = InnoDB AUTO_INCREMENT = 1002;
ALTER TABLE `vehicle` ENGINE = InnoDB AUTO_INCREMENT = 1002;
ALTER TABLE `agreement` ENGINE = InnoDB AUTO_INCREMENT = 1002;
ALTER TABLE `cargo` ENGINE = InnoDB AUTO_INCREMENT = 1002;
ALTER TABLE `company` ENGINE = InnoDB AUTO_INCREMENT = 1002;

ALTER TABLE `user` ADD UNIQUE INDEX (`login`);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


/*==================================================
PAROL user1,user2,user3  =123(heshing code)*/
insert into company values (DEFAULT, "Cargo Service Inc.", "123", "Maza Kalna 13-1, Riga", "Maza Kalna 13-1, Riga", "Swedbank", "HABA2132524325734", "Latvia", "cargo");
insert into company values (DEFAULT, "Baltic Transport", "123", "Teraudlietuves 22, Riga", "Teraudlietuves 22, Riga", "Swedbank", "HABA246363534561", "Latvia", "transport");
insert into company values (DEFAULT, "Dummy company", "123", "Brivibas iela, 1", "Brivibas iela, 1", "Swedbank", "HABA234567891", "Latvia", "dummy");
insert into company values (DEFAULT, "ADMIN", "dummy", "dummy", "dummy", "dummy", "dummy", "Latvia", "admin");

SELECT id INTO @companyID  FROM company  WHERE name ='Cargo Service Inc.';
insert into user values (DEFAULT, "user1", "$2a$12$0EDKOqTMDTZYapdsRdgbR.Xy99qjaGbrE83y0sqZiN/b6irB9ht1m", "Andris", "Berzins", "ab@email.com", "+371 27689837", @companyID);
SELECT id INTO @companyID  FROM company  WHERE name ='Baltic Transport';
insert into user values (DEFAULT, "user2", "$2a$12$0EDKOqTMDTZYapdsRdgbR.Xy99qjaGbrE83y0sqZiN/b6irB9ht1m", "Janis", "Andersons", "ja@email.com", "+371 24567897", @companyID);
SELECT id INTO @companyID  FROM company  WHERE name ='Dummy company';
insert into user values (DEFAULT, "user3", "$2a$12$0EDKOqTMDTZYapdsRdgbR.Xy99qjaGbrE83y0sqZiN/b6irB9ht1m", "John", "Smith", "js@email.com", "+371 27127645", @companyID);
SELECT id INTO @companyID  FROM company  WHERE name ='ADMIN';
insert into user values (DEFAULT, "admin1", "$2a$12$0EDKOqTMDTZYapdsRdgbR.Xy99qjaGbrE83y0sqZiN/b6irB9ht1m", "Admin", "Adminov", "admin@email.com", "+371 27127645", @companyID);

SELECT id INTO @userID  FROM user  WHERE login ='user1';
insert into cargo values (DEFAULT, @userID, "platform", 11, "Riga", "Moscow", "2015/02/01", "2015/02/05", "In Progress");
insert into cargo values (DEFAULT, @userID, "tilt", 11, "Riga", "Warszawa", "2015/02/11", "2015/02/25", "Available");
insert into cargo values (DEFAULT, @userID, "refrigirator", 11, "Minsk", "Riga", "2015/02/09", "2015/02/15", "Available");

SELECT id INTO @userID  FROM user  WHERE login ='user2';
INSERT INTO vehicle VALUES (default, @userID, "KAMAZ", "platform", "FG-2056", "KA-4309", 9.0, "Reserved");
INSERT INTO vehicle VALUES (default, @userID, "SCANIA", "tilt", "PA-5611", "MZ-5098", 12.0, "Available");
INSERT INTO vehicle VALUES (default, @userID, "MAN", "refrigerator", "KL-9986", "LM-7831", 22.0, "Available");

SELECT id INTO @cargoID  FROM cargo  WHERE load_address ='Riga' and unload_address ='Moscow';
SELECT id INTO @vehicleID  FROM vehicle  WHERE plate_number ='FG-2056';
insert into agreement values(default, @cargoID, @vehicleID, "PENDING");

insert into value values (DEFAULT, "Country", "Albania", "Albania");
insert into value values (DEFAULT, "Country", "Russia", "Russia");
insert into value values (DEFAULT, "Country", "Latvia", "Latvia");
insert into value values (DEFAULT, "Country", "Germany", "Germany");
insert into value values (DEFAULT, "Country", "Poland", "Poland");
insert into value values (DEFAULT, "Country", "Lithuania", "Lithuania");
insert into value values (DEFAULT, "Country", "France", "France");
insert into value values (DEFAULT, "Country", "Holland", "Holland");
insert into value values (DEFAULT, "Country", "Denmark", "Denmark");
insert into value values (DEFAULT, "Country", "Spain", "Spain");
insert into value values (DEFAULT, "Country", "Italy", "Italy");
insert into value values (DEFAULT, "Country", "Finland", "Finland");

insert into value values (DEFAULT, "Vehicle Type", "platform", "platform");
insert into value values (DEFAULT, "Vehicle Type", "tilt", "tilt");
insert into value values (DEFAULT, "Vehicle Type", "refrigirator", "refrigirator");

insert into value values (DEFAULT, "Vehicle Status", "Available", "Available");
insert into value values (DEFAULT, "Vehicle Status", "Reserved", "Reserved");
insert into value values (DEFAULT, "Vehicle Status", "In Use", "In Use");

insert into value values (DEFAULT, "Cargo Status", "Available", "Available");
insert into value values (DEFAULT, "Cargo Status", "In Progress", "In Progress");
insert into value values (DEFAULT, "Cargo Status", "Processed", "Processed");

insert into value values (DEFAULT, "Company Type", "transport", "transport");
insert into value values (DEFAULT, "Company Type", "cargo", "cargo");
insert into value values (DEFAULT, "Company Type", "admin", "admin");
