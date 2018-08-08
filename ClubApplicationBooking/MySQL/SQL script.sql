-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cab
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cab
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cab` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `cab` ;

-- -----------------------------------------------------
-- Table `cab`.`facility`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cab`.`facility` (
  `facility_id` INT(11) NOT NULL AUTO_INCREMENT,
  `facility_name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `price` INT(11) NOT NULL,
  `status` TINYINT(1) NOT NULL,
  PRIMARY KEY (`facility_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `cab`.`facility` (`facility_name`, `description`, `price`, `status`) VALUES ('Squash', 'Court 1', '20', '0');
INSERT INTO `cab`.`facility` (`facility_name`, `description`, `price`, `status`) VALUES ('TreadMill', 'Level 2-1', '5', '1');
INSERT INTO `cab`.`facility` (`facility_name`, `description`, `price`, `status`) VALUES ('TreadMill', 'Level 2-2', '5', '0');
INSERT INTO `cab`.`facility` (`facility_name`, `description`, `price`, `status`) VALUES ('Table Tennis', 'Hall ', '10', '1');
-- -----------------------------------------------------
-- Table `cab`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cab`.`user` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(15) NOT NULL,
  `phone` VARCHAR(12) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC)) 
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


INSERT INTO `cab`.`user` (`user_name`, `password`, `role`, `phone`, `email`) VALUES ('Moe', 'moe', 'member', '123456', 'moe@gmail.com');
INSERT INTO `cab`.`user` (`user_name`, `password`, `role`, `phone`, `email`) VALUES ('Mad', 'mad', 'member', '123457', 'mad@gmail.com');
INSERT INTO `cab`.`user` (`user_name`, `password`, `role`, `phone`, `email`) VALUES ('Miz', 'miz', 'admin', '123458', 'miz@gmail.com');
INSERT INTO `cab`.`user` (`user_name`, `password`, `role`, `phone`, `email`) VALUES ('Tink', 'tink', 'admin', '123459', 'tink@gmail.com');
-- -----------------------------------------------------
-- Table `cab`.`booking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cab`.`booking` (
  `booking_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `facility_id` INT(11) NOT NULL,
  `date` DATE NOT NULL,
  `type` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`booking_id`),
  INDEX `fk_facility_id_idx` (`facility_id` ASC),
  INDEX `fk_user_id_idx` (`user_id` ASC),
  CONSTRAINT `fk_facility_id`
    FOREIGN KEY (`facility_id`)
    REFERENCES `cab`.`facility` (`facility_id`)
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `cab`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `cab`.`booking` (`user_id`, `facility_id`, `date`, `type`) VALUES ('1', '1', '2018/6/9', 'M');
INSERT INTO `cab`.`booking` (`user_id`, `facility_id`, `date`, `type`) VALUES ('2', '3', '2018/6/9', 'M');
INSERT INTO `cab`.`booking` (`user_id`, `facility_id`, `date`, `type`) VALUES ('4', '3', '2018/6/10', 'A');
INSERT INTO `cab`.`booking` (`user_id`, `facility_id`, `date`, `type`) VALUES ('2', '4', '2018/6/12', 'M');



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
