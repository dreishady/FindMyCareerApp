SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
DROP SCHEMA IF EXISTS `fmc` ;
CREATE SCHEMA IF NOT EXISTS `fmc` DEFAULT CHARACTER SET utf8 ;
USE `fmc` ;
DROP TABLE IF EXISTS `fmc`.`client` ;
CREATE TABLE IF NOT EXISTS `fmc`.`client` (
  `client_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `email` VARCHAR(250) NOT NULL,
  `login_time` TIMESTAMP(6) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE INDEX `ClientID_UNIQUE` (`client_id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;
DROP TABLE IF EXISTS `fmc`.`consultant` ;
CREATE TABLE IF NOT EXISTS `fmc`.`consultant` (
  `consultant_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(250) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`consultant_id`),
  UNIQUE INDEX `consultant_id_UNIQUE` (`consultant_id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;
DROP TABLE IF EXISTS `fmc`.`administrator` ;
CREATE TABLE IF NOT EXISTS `fmc`.`administrator` (
  `administrator_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`administrator_id`),
  UNIQUE INDEX `administrator_id_UNIQUE` (`administrator_id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;
DROP TABLE IF EXISTS `fmc`.`appointment` ;
CREATE TABLE IF NOT EXISTS `fmc`.`appointment` (
  `appoint_id` INT NOT NULL AUTO_INCREMENT,
  `client_id` INT NOT NULL,
  `consultant_id` INT NULL,
  `date` DATETIME NOT NULL,
  `industry_name` VARCHAR(45) NOT NULL,
  `category_name` VARCHAR(45) NOT NULL,
  `options_name` VARCHAR(45) NOT NULL,
  `career_name` VARCHAR(45) NOT NULL,
  `notes` MEDIUMTEXT NULL,
  PRIMARY KEY (`appoint_id`),
  UNIQUE INDEX `appoint_id_UNIQUE` (`appoint_id` ASC),
  INDEX `client_id_idx` (`client_id` ASC),
  INDEX `consultant_id_idx` (`consultant_id` ASC),
  CONSTRAINT `client_id`
    FOREIGN KEY (`client_id`)
    REFERENCES `fmc`.`client` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `consultant_id`
    FOREIGN KEY (`consultant_id`)
    REFERENCES `fmc`.`consultant` (`consultant_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
DROP TABLE IF EXISTS `fmc`.`industry` ;
CREATE TABLE IF NOT EXISTS `fmc`.`industry` (
  `industry_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`industry_id`),
  UNIQUE INDEX `industry_id_UNIQUE` (`industry_id` ASC))
ENGINE = InnoDB;
DROP TABLE IF EXISTS `fmc`.`sub_category` ;
CREATE TABLE IF NOT EXISTS `fmc`.`sub_category` (
  `sub_cat_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `industry_id` INT NOT NULL,
  PRIMARY KEY (`sub_cat_id`),
  INDEX `industry_id_idx` (`industry_id` ASC),
  UNIQUE INDEX `category_id_UNIQUE` (`sub_cat_id` ASC),
  CONSTRAINT `industry_id`
    FOREIGN KEY (`industry_id`)
    REFERENCES `fmc`.`industry` (`industry_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
DROP TABLE IF EXISTS `fmc`.`type` ;
CREATE TABLE IF NOT EXISTS `fmc`.`type` (
  `type_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`type_id`),
  UNIQUE INDEX `type_id_UNIQUE` (`type_id` ASC))
ENGINE = InnoDB;
DROP TABLE IF EXISTS `fmc`.`options` ;
CREATE TABLE IF NOT EXISTS `fmc`.`options` (
  `options_id` INT NOT NULL AUTO_INCREMENT,
  `sub_cat_id` INT NOT NULL,
  `type_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`options_id`),
  UNIQUE INDEX `option_id_UNIQUE` (`options_id` ASC),
  INDEX `sub_cat_id_idx` (`sub_cat_id` ASC),
  INDEX `type_id_idx` (`type_id` ASC),
  CONSTRAINT `sub_cat_id`
    FOREIGN KEY (`sub_cat_id`)
    REFERENCES `fmc`.`sub_category` (`sub_cat_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `type_id`
    FOREIGN KEY (`type_id`)
    REFERENCES `fmc`.`type` (`type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
DROP TABLE IF EXISTS `fmc`.`career` ;
CREATE TABLE IF NOT EXISTS `fmc`.`career` (
  `career_id` INT NOT NULL AUTO_INCREMENT,
  `options_id` INT NOT NULL,
  `consultant_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` MEDIUMTEXT NOT NULL,
  `demand` VARCHAR(45) NOT NULL,
  `min_salary` INT NOT NULL,
  `max_salary` INT NOT NULL,
  PRIMARY KEY (`career_id`),
  INDEX `option_id_idx` (`options_id` ASC),
  INDEX `consultant_id_idx` (`consultant_id` ASC),
  CONSTRAINT `options_id_fk`
    FOREIGN KEY (`options_id`)
    REFERENCES `fmc`.`options` (`options_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `consultant_id_fk`
    FOREIGN KEY (`consultant_id`)
    REFERENCES `fmc`.`consultant` (`consultant_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
SET SQL_MODE = '';
GRANT USAGE ON *.* TO admin;
 DROP USER admin;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'admin' IDENTIFIED BY 'password';

GRANT SELECT, INSERT, TRIGGER ON TABLE `fmc`.* TO 'admin';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `fmc`.* TO 'admin';
GRANT SELECT ON TABLE `fmc`.* TO 'admin';
START TRANSACTION;
USE `fmc`;
INSERT INTO `fmc`.`client` (`client_id`, `first_name`, `last_name`, `date_of_birth`, `phone_number`, `email`, `login_time`, `username`, `password`) VALUES (1, 'Bill', 'Gate', '1999-02-09', '00443366101', 'billgate@someone.com', '2017:1:20 08:34:00', 'billgate', '123bill');
COMMIT;
START TRANSACTION;
USE `fmc`;
INSERT INTO `fmc`.`consultant` (`consultant_id`, `first_name`, `last_name`, `email`, `username`, `password`) VALUES (1, 'John', 'Smith', 'jsmtih@somewhere.com', 'smithy777', 'tafeQLD2017');
COMMIT;
START TRANSACTION;
USE `fmc`;
INSERT INTO `fmc`.`administrator` (`administrator_id`, `first_name`, `last_name`, `username`, `password`) VALUES (1, 'na', 'na', 'Admin', 'sesame');
COMMIT;
START TRANSACTION;
USE `fmc`;
INSERT INTO `fmc`.`appointment` (`appoint_id`, `client_id`, `consultant_id`, `date`, `industry_name`, `category_name`, `options_name`, `career_name`, `notes`) VALUES (1, 1, 1, '2017-04-22', 'Business', 'Information Technology', 'Diploma Of Software Development', 'Trainee Application Programmer', 'Test Notes');
INSERT INTO `fmc`.`appointment` (`appoint_id`, `client_id`, `consultant_id`, `date`, `industry_name`, `category_name`, `options_name`, `career_name`, `notes`) VALUES (2, 1, NULL, '2017-05-30', 'Business', 'Information Technology', 'Diploma Of Software Development', 'Trainee Application Programmer', '');
COMMIT;
START TRANSACTION;
USE `fmc`;
INSERT INTO `fmc`.`industry` (`industry_id`, `name`) VALUES (1, 'Business');
COMMIT;
START TRANSACTION;
USE `fmc`;
INSERT INTO `fmc`.`sub_category` (`sub_cat_id`, `name`, `industry_id`) VALUES (1, 'Information Technology', 1);
COMMIT;
START TRANSACTION;
USE `fmc`;
INSERT INTO `fmc`.`type` (`type_id`, `name`) VALUES (1, 'Career pathways');
INSERT INTO `fmc`.`type` (`type_id`, `name`) VALUES (2, 'Skills in demand');
INSERT INTO `fmc`.`type` (`type_id`, `name`) VALUES (3, 'Essential Employability Skills ');
COMMIT;
START TRANSACTION;
USE `fmc`;
INSERT INTO `fmc`.`options` (`options_id`, `sub_cat_id`, `type_id`, `name`) VALUES (1, 1, 1, 'Diploma Of Software Development');
COMMIT;
START TRANSACTION;
USE `fmc`;
INSERT INTO `fmc`.`career` (`career_id`, `options_id`, `consultant_id`, `name`, `description`, `demand`, `min_salary`, `max_salary`) VALUES (1, 1, 1, 'Trainee Application Programmer', 'Test Description', 'Medium', 50000, 140000);
COMMIT;