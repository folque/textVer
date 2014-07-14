SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `textver` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `textver` ;

-- -----------------------------------------------------
-- Table `textver`.`documents`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `textver`.`documents` ;

CREATE  TABLE IF NOT EXISTS `textver`.`documents` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `textver`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `textver`.`users` ;

CREATE  TABLE IF NOT EXISTS `textver`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `enabled` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `textver`.`versions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `textver`.`versions` ;

CREATE  TABLE IF NOT EXISTS `textver`.`versions` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `user_id` INT NOT NULL ,
  `document_id` INT NOT NULL ,
  `version_name` VARCHAR(45) NOT NULL ,
  `title` VARCHAR(45) NULL ,
  `content` LONGTEXT NULL ,
  `modified` TIMESTAMP NULL ,
  `description` TEXT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_version_document_idx` (`document_id` ASC) ,
  INDEX `fk_version_user1_idx` (`user_id` ASC) ,
  UNIQUE INDEX `version_name_UNIQUE` (`version_name` ASC, `document_id` ASC) ,
  CONSTRAINT `fk_version_document`
    FOREIGN KEY (`document_id` )
    REFERENCES `textver`.`documents` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_version_user`
    FOREIGN KEY (`user_id` )
    REFERENCES `textver`.`users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `textver`.`comments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `textver`.`comments` ;

CREATE  TABLE IF NOT EXISTS `textver`.`comments` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `user_id` INT NOT NULL ,
  `version_id` INT NOT NULL ,
  `comment` TEXT NULL ,
  `created` TIMESTAMP NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_comment_version1_idx` (`version_id` ASC) ,
  INDEX `fk_comment_user1_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_comment_version1`
    FOREIGN KEY (`version_id` )
    REFERENCES `textver`.`versions` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user`
    FOREIGN KEY (`user_id` )
    REFERENCES `textver`.`users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `textver`.`groups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `textver`.`groups` ;

CREATE  TABLE IF NOT EXISTS `textver`.`groups` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(127) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `textver`.`groups_authorities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `textver`.`groups_authorities` ;

CREATE  TABLE IF NOT EXISTS `textver`.`groups_authorities` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `group_id` INT NOT NULL ,
  `authority` VARCHAR(127) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_group_authority_group1_idx` (`group_id` ASC) ,
  CONSTRAINT `fk_group_authority_group`
    FOREIGN KEY (`group_id` )
    REFERENCES `textver`.`groups` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `textver`.`users_groups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `textver`.`users_groups` ;

CREATE  TABLE IF NOT EXISTS `textver`.`users_groups` (
  `user_id` INT NOT NULL ,
  `group_id` INT NOT NULL ,
  INDEX `fk_user_group_user1_idx` (`user_id` ASC) ,
  INDEX `fk_user_group_group1_idx` (`group_id` ASC) ,
  PRIMARY KEY (`user_id`, `group_id`) ,
  CONSTRAINT `fk_user_group_user1`
    FOREIGN KEY (`user_id` )
    REFERENCES `textver`.`users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_group_group1`
    FOREIGN KEY (`group_id` )
    REFERENCES `textver`.`groups` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `textver` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
