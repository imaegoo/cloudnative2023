CREATE SCHEMA `testdb` DEFAULT CHARACTER SET utf8mb4;
CREATE USER 'test' IDENTIFIED BY 'test';
GRANT ALL PRIVILEGES ON testdb.* TO 'test';

CREATE TABLE `testdb`.`lowcode_pages` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `page_id` VARCHAR(50) NULL,
  `title` VARCHAR(300) NULL,
  `content` LONGTEXT NULL,
  `created_user` INT NULL,
  `created_time` TIMESTAMP NULL,
  `updated_time` TIMESTAMP NULL,
  `is_deleted` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `testdb`.`lowcode_users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NULL,
  `password` VARCHAR(100) NULL,
  `email` VARCHAR(100) NULL,
  `created_time` TIMESTAMP NULL,
  `updated_time` TIMESTAMP NULL,
  `is_deleted` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;
