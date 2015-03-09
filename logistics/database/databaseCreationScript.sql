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
 `code` CHAR(50) NOT NULL,
 `type` CHAR(50) NOT NULL,
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

ALTER TABLE `user` ADD UNIQUE INDEX (`login`);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



/*==================================================
PAROL user1,user2,user3  =123(heshing code)*/
insert into company values (DEFAULT, "CompanyCargo", "123", "Reg address 1", "Actual address 2", "Hansabank", "HABA21", "Latvia", "cargo");
insert into company values (DEFAULT, "CompanyTransport", "123", "Reg address 1", "Actual address 2", "Hansabank", "HABA21", "Latvia", "transport");
insert into company values (DEFAULT, "CompanyNone", "123", "Reg address 1", "Actual address 2", "Hansabank", "HABA21", "Latvia", "figna");

SELECT id INTO @companyID  FROM company  WHERE name ='CompanyCargo';
insert into user values (DEFAULT, "user1", "$2a$12$0EDKOqTMDTZYapdsRdgbR.Xy99qjaGbrE83y0sqZiN/b6irB9ht1m", "Name1", "Surname1", "user1@user1.lv", "67900000", @companyID);
SELECT id INTO @companyID  FROM company  WHERE name ='CompanyTransport';
insert into user values (DEFAULT, "user2", "$2a$12$0EDKOqTMDTZYapdsRdgbR.Xy99qjaGbrE83y0sqZiN/b6irB9ht1m", "Name1", "Surname1", "user1@user1.lv", "67900000", @companyID);
SELECT id INTO @companyID  FROM company  WHERE name ='CompanyNone';
insert into user values (DEFAULT, "user3", "$2a$12$0EDKOqTMDTZYapdsRdgbR.Xy99qjaGbrE83y0sqZiN/b6irB9ht1m", "Name1", "Surname1", "user1@user1.lv", "67900000", @companyID);



insert into cargo values (DEFAULT, 11, "platform", 11, "Moskow", "Berlin", "2015/02/01", "2015/02/05", "pending");
insert into cargo values (DEFAULT, 11, "tilt", 11, "BERLIN", "Warsaw", "2015/02/11", "2015/02/25", "pending");
insert into cargo values (DEFAULT, 11, "refrigirator", 11, "Moskow", "Berlin", "2015/02/09", "2015/02/15", "pending");

INSERT INTO vehicle VALUES (default, 11, "GAZEL", "platform", "GG111", "TT222", 9.0, "PENDING");
INSERT INTO vehicle VALUES (default, 11, "GAZELKA", "tilt", "GG111", "TT222", 12.0, "PENDING");
INSERT INTO vehicle VALUES (default, 11, "MAN", "refrigerator", "GG111", "TT222", 22.0, "PENDING");

insert into agreement values(default, 1, 2, "PENDING");
insert into agreement values(default, 1, 2, "PENDING");
insert into agreement values(default, 1, 2, "PENDING");

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