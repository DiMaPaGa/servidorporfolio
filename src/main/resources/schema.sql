-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema portfolio
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema portfolio
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `portfolio` DEFAULT CHARACTER SET utf8mb3 ;
USE `portfolio` ;

-- -----------------------------------------------------
-- Table `portfolio`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio`.`status` (
  `status_id` INT NOT NULL AUTO_INCREMENT,
  `status_name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`status_id`),
  UNIQUE INDEX `status_name_UNIQUE` (`status_name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio`.`projects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio`.`projects` (
  `project_id` INT NOT NULL AUTO_INCREMENT,
  `project_name` VARCHAR(45) NOT NULL,
  `description` LONGTEXT NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  `repository_url` VARCHAR(255) NULL,
  `demo_url` VARCHAR(255) NULL,
  `picture` VARCHAR(255) NULL,
  `status_status_id` INT NOT NULL,
  PRIMARY KEY (`project_id`),
  UNIQUE INDEX `project_name_UNIQUE` (`project_name` ASC) VISIBLE,
  INDEX `fk_projects_status_idx` (`status_status_id` ASC) VISIBLE,
  CONSTRAINT `fk_projects_status`
    FOREIGN KEY (`status_status_id`)
    REFERENCES `portfolio`.`status` (`status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio`.`technologies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio`.`technologies` (
  `tech_id` INT NOT NULL AUTO_INCREMENT,
  `tech_name` VARCHAR(45) NULL,
  PRIMARY KEY (`tech_id`),
  UNIQUE INDEX `tech_name_UNIQUE` (`tech_name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio`.`developers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio`.`developers` (
  `dev_id` INT NOT NULL AUTO_INCREMENT,
  `dev_name` VARCHAR(45) NULL,
  `dev_surname` VARCHAR(45) NULL,
  `email` VARCHAR(255) NULL,
  `linkedin_url` VARCHAR(255) NULL,
  `github_url` VARCHAR(255) NULL,
  PRIMARY KEY (`dev_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `linkedin_url_UNIQUE` (`linkedin_url` ASC) VISIBLE,
  UNIQUE INDEX `github_url_UNIQUE` (`github_url` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio`.`developers_worked_on_projects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio`.`developers_worked_on_projects` (
  `developers_dev_id` INT NOT NULL,
  `projects_project_id` INT NOT NULL,
  PRIMARY KEY (`developers_dev_id`, `projects_project_id`),
  INDEX `fk_developers_has_projects_projects1_idx` (`projects_project_id` ASC) VISIBLE,
  INDEX `fk_developers_has_projects_developers1_idx` (`developers_dev_id` ASC) VISIBLE,
  CONSTRAINT `fk_developers_has_projects_developers1`
    FOREIGN KEY (`developers_dev_id`)
    REFERENCES `portfolio`.`developers` (`dev_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_developers_has_projects_projects1`
    FOREIGN KEY (`projects_project_id`)
    REFERENCES `portfolio`.`projects` (`project_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio`.`technologies_used_in_projects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio`.`technologies_used_in_projects` (
  `technologies_tech_id` INT NOT NULL,
  `projects_project_id` INT NOT NULL,
  PRIMARY KEY (`technologies_tech_id`, `projects_project_id`),
  INDEX `fk_technologies_has_projects_projects1_idx` (`projects_project_id` ASC) VISIBLE,
  INDEX `fk_technologies_has_projects_technologies1_idx` (`technologies_tech_id` ASC) VISIBLE,
  CONSTRAINT `fk_technologies_has_projects_technologies1`
    FOREIGN KEY (`technologies_tech_id`)
    REFERENCES `portfolio`.`technologies` (`tech_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_technologies_has_projects_projects1`
    FOREIGN KEY (`projects_project_id`)
    REFERENCES `portfolio`.`projects` (`project_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `portfolio`.`status` VALUES (1, "In Development");
INSERT INTO `portfolio`.`status` VALUES (2, "Testing");
INSERT INTO `portfolio`.`status` VALUES (3, "In Production");

-- Eliminar la clave foránea actual
ALTER TABLE `portfolio`.`developers_worked_on_projects`
  DROP FOREIGN KEY `fk_developers_has_projects_projects1`;

-- Agregar la nueva clave foránea con ON DELETE CASCADE
ALTER TABLE `portfolio`.`developers_worked_on_projects`
  ADD CONSTRAINT `fk_developers_has_projects_projects1`
  FOREIGN KEY (`projects_project_id`)
  REFERENCES `portfolio`.`projects` (`project_id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;

  -- Datos de ejemplo para la tabla `projects`
INSERT INTO `portfolio`.`projects` (`project_name`, `description`, `start_date`, `end_date`, `repository_url`, `demo_url`, `picture`, `status_status_id`)
VALUES 
    ('Twitter Clone', 'Este proyecto es una red social donde los usuarios pueden enviarse mensajes (publicos o privados), seguir o no a otros, así como ver sus seguidores.', '2024-10-12', '2024-11-15', 'https://github.com/usuario/portfolio-web', '', '../../public/images/twitter.png', 1),
    ('Agencias viajes Node', 'Proyecto de Agencia de Viajes -Introducción a Node.js', '2024-07-20', '2024-07-30', 'https://github.com/DiMaPaGa/agenciaviajesnode.png', '', '../../public/images/projectNode.jpg', 3),
    ('API de Datos Poblacionales', 'Proyecto de API RESTful que permite almacenar y recuperar datos poblacionales de países, utilizando la API externa de REST Countries.', '2024-11-01', '2024-11-08', 'https://github.com/DiMaPaGa/Servidor2DAW/tree/master/pruebatecnica', '', '../../public/images/projectRestApi.png', 3),
    ('Proyecto Portafolio API', 'API REST que permite a un desarrollador/a gestionar su portafolio de proyectos personales.', '2024-11-11', '2024-11-30', 'https://github.com/DiMaPaGa/servidorporfolio', '', '../../public/images/apiPortafolio.png', 2),
    ('Proyecto Portfolio-CV', 'Página web de portfolio curricular creada con Astro.', '2024-11-21', '2024-12-01', 'https://github.com/DiMaPaGa/portfolio-cv', '', '../../public/images/fondoHome.png', 2),
    ('Twitter Clone 2', 'Este proyecto es una red social donde los usuarios pueden enviarse mensajes (publicos o privados), seguir o no a otros, así como ver sus seguidores.', '2024-10-12', '2024-11-15', 'https://github.com/usuario/portfolio-web', '', '../../public/images/twitter.png', 1),
    ('Agencias viajes Node 2', 'Proyecto de Agencia de Viajes -Introducción a Node.js', '2024-07-20', '2024-07-30', 'https://github.com/DiMaPaGa/agenciaviajesnode.png', '', '../../public/images/projectNode.jpg', 3),
    ('API de Datos Poblacionales 2', 'Proyecto de API RESTful que permite almacenar y recuperar datos poblacionales de países, utilizando la API externa de REST Countries.', '2024-11-01', '2024-11-08', 'https://github.com/DiMaPaGa/Servidor2DAW/tree/master/pruebatecnica', '', '../../public/images/projectRestApi.png', 3),
    ('Proyecto Portafolio API 2', 'API REST que permite a un desarrollador/a gestionar su portafolio de proyectos personales.', '2024-11-11', '2024-11-30', 'https://github.com/DiMaPaGa/servidorporfolio', '', '../../public/images/apiPortafolio.png', 2),
    ('Proyecto Portfolio-CV 2', 'Página web de portfolio curricular creada con Astro.', '2024-11-21', '2024-12-01', 'https://github.com/DiMaPaGa/portfolio-cv', '', '../../public/images/fondoHome.png', 2);

-- Datos de ejemplo para la tabla `technologies`
INSERT INTO `portfolio`.`technologies` (`tech_name`)
VALUES 
    ('Java'),
    ('Spring Boot'),
    ('PHP'),
    ('REST APIs'),
    ('Node.js'),
    ('MySQL'),
    ('HTML'),
    ('JavaScript'),
    ('Tailwind CSS'),
    ('Astro'),
    ('CSS'),
    ('Microsoft Azure'),
    ('Git'),
    ('GitHub Actions');

-- Datos de ejemplo para la tabla `developers`
INSERT INTO `portfolio`.`developers` (`dev_name`, `dev_surname`, `email`, `linkedin_url`, `github_url`)
VALUES 
    ('Diana', 'Pascual', 'dianamariapascual@gmail.com', 'https://www.linkedin.com/in/diana-pascual-garc%C3%ADa-47209431/ ', 'https://github.com/DiMaPaGa'),
    ('Joaquin', 'Borrego', 'joaquin.borrego@vedruna.es', 'https://linkedin.com/in/joaquinborrego', ''),
    ('José Carlos', 'Moreno', 'josecarlos.moreno@vedruna.es', 'https://linkedin.com/in/josecarlosmoreno', 'https://github.com/carlossanchez');

-- Datos de ejemplo para la tabla `developers_worked_on_projects`
INSERT INTO `portfolio`.`developers_worked_on_projects` (`developers_dev_id`, `projects_project_id`)
VALUES 
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 5),
    (1, 6),
    (1, 7),
    (1, 8),
    (1, 9),
    (1, 10),
    (2, 6),
    (2, 7),
    (2, 8),
    (2, 9),
    (3, 10);

-- Datos de ejemplo para la tabla `technologies_used_in_projects`
INSERT INTO `portfolio`.`technologies_used_in_projects` (`technologies_tech_id`, `projects_project_id`)
VALUES 
    (1, 4),
    (1, 3),
    (1, 8),
    (1, 9),
    (2, 3),
    (2, 4),
    (2, 8),
    (2, 9),
    (3, 1),
    (3, 6),
    (4, 3),
    (4, 4),
    (4, 5),
    (4, 8),
    (4, 9),
    (4, 10),
    (5, 2),
    (5, 7),
    (6, 1),
    (6, 4),
    (6, 6),
    (6, 9),
    (7, 1),
    (7, 5),
    (7, 6),
    (7, 10),
    (8, 2),
    (8, 5),
    (8, 7),
    (8, 10),
    (9, 1),
    (9, 5),
    (9, 6),
    (9, 10),
    (10, 5),
    (10, 10),
    (11, 3),
    (11, 8),
    (12, 1),
    (12, 5),
    (12, 6),
    (12, 10),
    (13, 1),
    (13, 2),
    (13, 3),
    (13, 4),
    (13, 5),
    (13, 6),
    (13, 7),
    (13, 8),
    (13, 9),
    (13, 10),
    (14, 5),
    (14, 10);
