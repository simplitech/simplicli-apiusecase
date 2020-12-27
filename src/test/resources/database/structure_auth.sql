SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

USE `usecase`;

DROP TABLE IF EXISTS `usecase`.`permission`;
DROP TABLE IF EXISTS `usecase`.`role`;
DROP TABLE IF EXISTS `usecase`.`user_role`;
DROP TABLE IF EXISTS `usecase`.`user_permission`;
DROP TABLE IF EXISTS `usecase`.`role_permission`;
DROP TABLE IF EXISTS `usecase`.`permission_permission`;

# ------------------------------------------------------------
# SCHEMA DUMP FOR TABLE: permission
# ------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `usecase`.`permission`
(
    `idPermissionPk` INT(11)      NOT NULL AUTO_INCREMENT,
    `scope`              VARCHAR(127) NOT NULL,
    `name`               VARCHAR(127) NOT NULL,
    `description`        TEXT         NULL     DEFAULT NULL,
    `active`             TINYINT(1)   NOT NULL DEFAULT 1,
    PRIMARY KEY (`idPermissionPk`),
    UNIQUE INDEX `scope_UNIQUE` (`scope` ASC)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;

# ------------------------------------------------------------
# SCHEMA DUMP FOR TABLE: role
# ------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `usecase`.`role`
(
    `idRolePk` INT(11)      NOT NULL AUTO_INCREMENT,
    `slug`         VARCHAR(127) NOT NULL,
    `level`        INT(11)      NOT NULL DEFAULT 0,
    `name`         VARCHAR(127) NOT NULL,
    `description`  TEXT         NULL     DEFAULT NULL,
    `active`       TINYINT(1)   NOT NULL DEFAULT 1,
    PRIMARY KEY (`idRolePk`),
    UNIQUE INDEX `slug_UNIQUE` (`slug` ASC)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;

# ------------------------------------------------------------
# SCHEMA DUMP FOR TABLE: user_role
# ------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `usecase`.`user_role`
(
    `idUserFk` INT(11) NOT NULL,
    `idRoleFk` INT(11) NOT NULL,
    PRIMARY KEY (`idUserFk`, `idRoleFk`),
    INDEX `fk_user_has_role_role1_idx` (`idRoleFk` ASC),
    INDEX `fk_user_has_role_user1_idx` (`idUserFk` ASC),
    CONSTRAINT `fk_user_has_role_user1`
        FOREIGN KEY (`idUserFk`)
            REFERENCES `usecase`.`user` (`idUserPk`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_user_has_role_role1`
        FOREIGN KEY (`idRoleFk`)
            REFERENCES `usecase`.`role` (`idRolePk`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

# ------------------------------------------------------------
# SCHEMA DUMP FOR TABLE: user_permission
# ------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `usecase`.`user_permission`
(
    `idUserFk`       INT(11) NOT NULL,
    `idPermissionFk` INT(11) NOT NULL,
    PRIMARY KEY (`idUserFk`, `idPermissionFk`),
    INDEX `fk_user_has_permission_permission1_idx` (`idPermissionFk` ASC),
    INDEX `fk_user_has_permission_user1_idx` (`idUserFk` ASC),
    CONSTRAINT `fk_user_has_permission_user1`
        FOREIGN KEY (`idUserFk`)
            REFERENCES `usecase`.`user` (`idUserPk`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_user_has_permission_permission1`
        FOREIGN KEY (`idPermissionFk`)
            REFERENCES `usecase`.`permission` (`idPermissionPk`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

# ------------------------------------------------------------
# SCHEMA DUMP FOR TABLE: role_permission
# ------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `usecase`.`role_permission`
(
    `idRoleFk`       INT(11) NOT NULL,
    `idPermissionFk` INT(11) NOT NULL,
    PRIMARY KEY (`idRoleFk`, `idPermissionFk`),
    INDEX `fk_role_has_permission_permission1_idx` (`idPermissionFk` ASC),
    INDEX `fk_role_has_permission_role1_idx` (`idRoleFk` ASC),
    CONSTRAINT `fk_role_has_permission_role1`
        FOREIGN KEY (`idRoleFk`)
            REFERENCES `usecase`.`role` (`idRolePk`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_role_has_permission_permission1`
        FOREIGN KEY (`idPermissionFk`)
            REFERENCES `usecase`.`permission` (`idPermissionPk`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;

# ------------------------------------------------------------
# SCHEMA DUMP FOR TABLE: permission_permission
# ------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `usecase`.`permission_permission`
(
    `idPermissionParentFk` INT(11) NOT NULL,
    `idPermissionChildFk`  INT(11) NOT NULL,
    PRIMARY KEY (`idPermissionParentFk`, `idPermissionChildFk`),
    INDEX `fk_permission_has_permission_permission2_idx` (`idPermissionChildFk` ASC),
    INDEX `fk_permission_has_permission_permission1_idx` (`idPermissionParentFk` ASC),
    CONSTRAINT `fk_permission_has_permission_permission1`
        FOREIGN KEY (`idPermissionParentFk`)
            REFERENCES `usecase`.`permission` (`idPermissionPk`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_permission_has_permission_permission2`
        FOREIGN KEY (`idPermissionChildFk`)
            REFERENCES `usecase`.`permission` (`idPermissionPk`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


LOCK TABLES `usecase`.`role` WRITE;
--
INSERT INTO `usecase`.`role` (`idRolePk`, `slug`, `level`, `name`, `description`, `active`) VALUES ('1', 'admin', '99', 'Admin', 'Grants all permissions', '1');
INSERT INTO `usecase`.`role` (`idRolePk`, `slug`, `level`, `name`, `description`, `active`) VALUES ('2', 'manager', '80', 'Manager', 'Allowed to read, create and edit', '1');
INSERT INTO `usecase`.`role` (`idRolePk`, `slug`, `level`, `name`, `description`, `active`) VALUES ('3', 'viewer', '10', 'Viewer', 'Allowed only to read', '1');
INSERT INTO `usecase`.`role` (`idRolePk`, `slug`, `level`, `name`, `description`, `active`) VALUES ('4', 'guest', '1', 'Guest', 'Grants simple permissions', '1');
--
UNLOCK TABLES;


LOCK TABLES `usecase`.`permission` WRITE;
--
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1', 'full-control', 'Full Control', '', '1');
-- FULL CONTROL
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('101', 'conectado-full-control', 'Conectado Full Control', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('102', 'conector-principal-full-control', 'Conector Principal Full Control', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('103', 'endereco-full-control', 'Endereco Full Control', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('104', 'extensao-do-principal-full-control', 'Extensao Do Principal Full Control', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('105', 'grupo-do-principal-full-control', 'Grupo Do Principal Full Control', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('106', 'item-do-principal-full-control', 'Item Do Principal Full Control', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('107', 'permission-full-control', 'Permission Full Control', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('108', 'permission-permission-full-control', 'Permission Permission Full Control', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('109', 'principal-full-control', 'Principal Full Control', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('110', 'role-full-control', 'Role Full Control', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('111', 'role-permission-full-control', 'Role Permission Full Control', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('112', 'tag-principal-full-control', 'Tag Principal Full Control', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('113', 'tag-full-control', 'Tag Full Control', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('114', 'user-full-control', 'User Full Control', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('115', 'user-permission-full-control', 'User Permission Full Control', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('116', 'user-role-full-control', 'User Role Full Control', '', '1');


-- READ ALL
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1001', 'conectado-read-all', 'Conectado Read All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1002', 'conector-principal-read-all', 'Conector Principal Read All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1003', 'endereco-read-all', 'Endereco Read All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1004', 'extensao-do-principal-read-all', 'Extensao Do Principal Read All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1005', 'grupo-do-principal-read-all', 'Grupo Do Principal Read All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1006', 'item-do-principal-read-all', 'Item Do Principal Read All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1007', 'permission-read-all', 'Permission Read All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1008', 'permission-permission-read-all', 'Permission Permission Read All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009', 'principal-read-all', 'Principal Read All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1010', 'role-read-all', 'Role Read All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1011', 'role-permission-read-all', 'Role Permission Read All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1012', 'tag-principal-read-all', 'Tag Principal Read All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1013', 'tag-read-all', 'Tag Read All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1014', 'user-read-all', 'User Read All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1015', 'user-permission-read-all', 'User Permission Read All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1016', 'user-role-read-all', 'User Role Read All', '', '1');


-- INSERT ALL
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2001', 'conectado-insert-all', 'Conectado Insert All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2002', 'conector-principal-insert-all', 'Conector Principal Insert All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2003', 'endereco-insert-all', 'Endereco Insert All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2004', 'extensao-do-principal-insert-all', 'Extensao Do Principal Insert All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2005', 'grupo-do-principal-insert-all', 'Grupo Do Principal Insert All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2006', 'item-do-principal-insert-all', 'Item Do Principal Insert All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2007', 'permission-insert-all', 'Permission Insert All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2008', 'permission-permission-insert-all', 'Permission Permission Insert All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009', 'principal-insert-all', 'Principal Insert All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2010', 'role-insert-all', 'Role Insert All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2011', 'role-permission-insert-all', 'Role Permission Insert All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2012', 'tag-principal-insert-all', 'Tag Principal Insert All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2013', 'tag-insert-all', 'Tag Insert All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2014', 'user-insert-all', 'User Insert All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2015', 'user-permission-insert-all', 'User Permission Insert All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2016', 'user-role-insert-all', 'User Role Insert All', '', '1');


-- UPDATE ALL
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3001', 'conectado-update-all', 'Conectado Update All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3002', 'conector-principal-update-all', 'Conector Principal Update All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3003', 'endereco-update-all', 'Endereco Update All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3004', 'extensao-do-principal-update-all', 'Extensao Do Principal Update All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3005', 'grupo-do-principal-update-all', 'Grupo Do Principal Update All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3006', 'item-do-principal-update-all', 'Item Do Principal Update All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3007', 'permission-update-all', 'Permission Update All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3008', 'permission-permission-update-all', 'Permission Permission Update All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009', 'principal-update-all', 'Principal Update All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3010', 'role-update-all', 'Role Update All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3011', 'role-permission-update-all', 'Role Permission Update All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3012', 'tag-principal-update-all', 'Tag Principal Update All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3013', 'tag-update-all', 'Tag Update All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3014', 'user-update-all', 'User Update All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3015', 'user-permission-update-all', 'User Permission Update All', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3016', 'user-role-update-all', 'User Role Update All', '', '1');


-- DELETE
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('4001', 'conectado-delete', 'Conectado Delete', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('4002', 'conector-principal-delete', 'Conector Principal Delete', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('4003', 'endereco-delete', 'Endereco Delete', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('4004', 'extensao-do-principal-delete', 'Extensao Do Principal Delete', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('4005', 'grupo-do-principal-delete', 'Grupo Do Principal Delete', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('4006', 'item-do-principal-delete', 'Item Do Principal Delete', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('4007', 'permission-delete', 'Permission Delete', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('4008', 'permission-permission-delete', 'Permission Permission Delete', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('4009', 'principal-delete', 'Principal Delete', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('4010', 'role-delete', 'Role Delete', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('4011', 'role-permission-delete', 'Role Permission Delete', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('4012', 'tag-principal-delete', 'Tag Principal Delete', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('4013', 'tag-delete', 'Tag Delete', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('4014', 'user-delete', 'User Delete', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('4015', 'user-permission-delete', 'User Permission Delete', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('4016', 'user-role-delete', 'User Role Delete', '', '1');


-- CONECTADO
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1001001', 'conectado-read-id-conectado-pk', 'Conectado Read Id Conectado Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1001002', 'conectado-read-id-titulo', 'Conectado Read Id Titulo', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2001001', 'conectado-insert-id-conectado-pk', 'Conectado Insert Id Conectado Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2001002', 'conectado-insert-id-titulo', 'Conectado Insert Id Titulo', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3001001', 'conectado-update-id-conectado-pk', 'Conectado Update Id Conectado Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3001002', 'conectado-update-id-titulo', 'Conectado Update Id Titulo', '', '1');


-- CONECTOR
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1002001', 'conector-principal-read-titulo', 'Conector Principal Read Titulo', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1002002', 'conector-principal-read-id-principal-fk', 'Conector Principal Read Id Principal Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1002003', 'conector-principal-read-id-conectado-fk', 'Conector Principal Read Id Conectado Fk', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2002001', 'conector-principal-insert-titulo', 'Conector Principal Insert Titulo', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2002002', 'conector-principal-insert-id-principal-fk', 'Conector Principal Insert Id Principal Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2002003', 'conector-principal-insert-id-conectado-fk', 'Conector Principal Insert Id Conectado Fk', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3002001', 'conector-principal-update-titulo', 'Conector Principal Update Titulo', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3002002', 'conector-principal-update-id-principal-fk', 'Conector Principal Update Id Principal Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3002003', 'conector-principal-update-id-conectado-fk', 'Conector Principal Update Id Conectado Fk', '', '1');


-- ENDERECO
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1003001', 'endereco-read-id-endereco-pk', 'Endereco Read Id Endereco Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1003002', 'endereco-read-cep', 'Endereco Read Cep', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1003003', 'endereco-read-zipcode', 'Endereco Read Zipcode', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1003004', 'endereco-read-rua', 'Endereco Read Rua', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1003005', 'endereco-read-cidade', 'Endereco Read Cidade', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1003006', 'endereco-read-uf', 'Endereco Read Uf', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1003007', 'endereco-read-nro', 'Endereco Read Nro', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1003008', 'endereco-read-latitude', 'Endereco Read Latitude', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1003009', 'endereco-read-longitude', 'Endereco Read Longitude', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2003001', 'endereco-insert-id-endereco-pk', 'Endereco Insert Id Endereco Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2003002', 'endereco-insert-cep', 'Endereco Insert Cep', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2003003', 'endereco-insert-zipcode', 'Endereco Insert Zipcode', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2003004', 'endereco-insert-rua', 'Endereco Insert Rua', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2003005', 'endereco-insert-cidade', 'Endereco Insert Cidade', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2003006', 'endereco-insert-uf', 'Endereco Insert Uf', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2003007', 'endereco-insert-nro', 'Endereco Insert Nro', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2003008', 'endereco-insert-latitude', 'Endereco Insert Latitude', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2003009', 'endereco-insert-longitude', 'Endereco Insert Longitude', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3003001', 'endereco-update-id-endereco-pk', 'Endereco Update Id Endereco Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3003002', 'endereco-update-cep', 'Endereco Update Cep', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3003003', 'endereco-update-zipcode', 'Endereco Update Zipcode', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3003004', 'endereco-update-rua', 'Endereco Update Rua', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3003005', 'endereco-update-cidade', 'Endereco Update Cidade', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3003006', 'endereco-update-uf', 'Endereco Update Uf', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3003007', 'endereco-update-nro', 'Endereco Update Nro', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3003008', 'endereco-update-latitude', 'Endereco Update Latitude', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3003009', 'endereco-update-longitude', 'Endereco Update Longitude', '', '1');


-- EXTENSAO_DO_PRINCIPAL
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1004001', 'extensao-do-principal-read-id-principal-fk', 'Extensao Do Principal Read Id Principal Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1004002', 'extensao-do-principal-read-titulo', 'Extensao Do Principal Read Titulo', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2004001', 'extensao-do-principal-insert-id-principal-fk', 'Extensao Do Principal Insert Id Principal Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2004002', 'extensao-do-principal-insert-titulo', 'Extensao Do Principal Insert Titulo', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3004001', 'extensao-do-principal-update-id-principal-fk', 'Extensao Do Principal Update Id Principal Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3004002', 'extensao-do-principal-update-titulo', 'Extensao Do Principal Update Titulo', '', '1');


-- GRUPO_DO_PRINICPAL
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1005001', 'grupo-do-principal-read-id-grupo-do-principal-pk', 'Grupo Do Principal Read Id Grupo Do Principal Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1005002', 'grupo-do-principal-read-titulo', 'Grupo Do Principal Read Titulo', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2005001', 'grupo-do-principal-insert-id-grupo-do-principal-pk', 'Grupo Do Principal Insert Id Grupo Do Principal Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2005002', 'grupo-do-principal-insert-titulo', 'Grupo Do Principal Insert Titulo', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3005001', 'grupo-do-principal-update-id-grupo-do-principal-pk', 'Grupo Do Principal Update Id Grupo Do Principal Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3005002', 'grupo-do-principal-update-titulo', 'Grupo Do Principal Update Titulo', '', '1');


-- ITEM_DO_PRINCIPAL
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1006001', 'item-do-principal-read-id-item-do-principal-pk', 'Item Do Principal Read Id Item Do Principal Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1006002', 'item-do-principal-read-id-principal-fk', 'Item Do Principal Read Id Principal Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1006003', 'item-do-principal-read-titulo', 'Item Do Principal Read Titulo', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2006001', 'item-do-principal-insert-id-item-do-principal-pk', 'Item Do Principal Insert Id Item Do Principal Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2006002', 'item-do-principal-insert-id-principal-fk', 'Item Do Principal Insert Id Principal Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2006003', 'item-do-principal-insert-titulo', 'Item Do Principal Insert Titulo', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3006001', 'item-do-principal-update-id-item-do-principal-pk', 'Item Do Principal Update Id Item Do Principal Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3006002', 'item-do-principal-update-id-principal-fk', 'Item Do Principal Update Id Principal Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3006003', 'item-do-principal-update-titulo', 'Item Do Principal Update Titulo', '', '1');


-- PERMISSION
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1007001', 'permission-read-id-permission-pk', 'Permission Read Id Permission Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1007002', 'permission-read-scope', 'Permission Read Scope', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1007003', 'permission-read-name', 'Permission Read Name', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1007004', 'permission-read-description', 'Permission Read Description', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1007005', 'permission-read-active', 'Permission Read Active', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2007001', 'permission-insert-id-permission-pk', 'Permission Insert Id Permission Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2007002', 'permission-insert-scope', 'Permission Insert Scope', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2007003', 'permission-insert-name', 'Permission Insert Name', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2007004', 'permission-insert-description', 'Permission Insert Description', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2007005', 'permission-insert-active', 'Permission Insert Active', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3007001', 'permission-update-id-permission-pk', 'Permission Update Id Permission Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3007002', 'permission-update-scope', 'Permission Update Scope', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3007003', 'permission-update-name', 'Permission Update Name', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3007004', 'permission-update-description', 'Permission Update Description', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3007005', 'permission-update-active', 'Permission Update Active', '', '1');


-- PERMISSION_PERMISSION
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1008001', 'permission-permission-read-id-permission-parent-fk', 'Permission Permission Read Id Permission Parent Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1008002', 'permission-permission-read-id-permission-child-fk', 'Permission Permission Read Id Permission Child Fk', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2008001', 'permission-permission-insert-id-permission-parent-fk', 'Permission Permission Insert Id Permission Parent Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2008002', 'permission-permission-insert-id-permission-child-fk', 'Permission Permission Insert Id Permission Child Fk', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3008001', 'permission-permission-update-id-permission-parent-fk', 'Permission Permission Update Id Permission Parent Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3008002', 'permission-permission-update-id-permission-child-fk', 'Permission Permission Update Id Permission Child Fk', '', '1');


-- PRINCIPAL
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009001', 'principal-read-id-principal-pk', 'Principal Read Id Principal Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009002', 'principal-read-id-grupo-do-principal-fk', 'Principal Read Id Grupo Do Principal Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009003', 'principal-read-id-grupo-do-principal-facultativo-fk', 'Principal Read Id Grupo Do Principal Facultativo Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009004', 'principal-read-texto-obrigatorio', 'Principal Read Texto Obrigatorio', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009005', 'principal-read-unico', 'Principal Read Unico', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009006', 'principal-read-decimal-obrigatorio', 'Principal Read Decimal Obrigatorio', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009007', 'principal-read-inteiro-obrigatorio', 'Principal Read Inteiro Obrigatorio', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009008', 'principal-read-booleano-obrigatorio', 'Principal Read Booleano Obrigatorio', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009009', 'principal-read-data-obrigatoria', 'Principal Read Data Obrigatoria', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009010', 'principal-read-datahora-obrigatoris', 'Principal Read Datahora Obrigatoris', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009011', 'principal-read-ativo', 'Principal Read Ativo', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009012', 'principal-read-data-criacao', 'Principal Read Data Criacao', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009013', 'principal-read-texto-facultativo', 'Principal Read Texto Facultativo', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009014', 'principal-read-email', 'Principal Read Email', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009015', 'principal-read-url-imagem', 'Principal Read Url Imagem', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009016', 'principal-read-url', 'Principal Read Url', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009017', 'principal-read-nome', 'Principal Read Nome', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009018', 'principal-read-titulo', 'Principal Read Titulo', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009019', 'principal-read-cpf', 'Principal Read Cpf', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009020', 'principal-read-cnpj', 'Principal Read Cnpj', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009021', 'principal-read-rg', 'Principal Read Rg', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009022', 'principal-read-celular', 'Principal Read Celular', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009023', 'principal-read-texto-grande', 'Principal Read Texto Grande', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009024', 'principal-read-snake-case', 'Principal Read Snake Case', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009025', 'principal-read-decimal-facultativo', 'Principal Read Decimal Facultativo', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009026', 'principal-read-interio-facultativo', 'Principal Read Interio Facultativo', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009027', 'principal-read-booleano-facultativo', 'Principal Read Booleano Facultativo', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009028', 'principal-read-data-facultativa', 'Principal Read Data Facultativa', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009029', 'principal-read-datahora-facultativa', 'Principal Read Datahora Facultativa', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009030', 'principal-read-data-alteracao', 'Principal Read Data Alteracao', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009031', 'principal-read-preco', 'Principal Read Preco', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1009032', 'principal-read-senha', 'Principal Read Senha', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009001', 'principal-insert-id-principal-pk', 'Principal Insert Id Principal Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009002', 'principal-insert-id-grupo-do-principal-fk', 'Principal Insert Id Grupo Do Principal Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009003', 'principal-insert-id-grupo-do-principal-facultativo-fk', 'Principal Insert Id Grupo Do Principal Facultativo Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009004', 'principal-insert-texto-obrigatorio', 'Principal Insert Texto Obrigatorio', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009005', 'principal-insert-unico', 'Principal Insert Unico', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009006', 'principal-insert-decimal-obrigatorio', 'Principal Insert Decimal Obrigatorio', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009007', 'principal-insert-inteiro-obrigatorio', 'Principal Insert Inteiro Obrigatorio', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009008', 'principal-insert-booleano-obrigatorio', 'Principal Insert Booleano Obrigatorio', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009009', 'principal-insert-data-obrigatoria', 'Principal Insert Data Obrigatoria', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009010', 'principal-insert-datahora-obrigatoris', 'Principal Insert Datahora Obrigatoris', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009011', 'principal-insert-ativo', 'Principal Insert Ativo', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009012', 'principal-insert-data-criacao', 'Principal Insert Data Criacao', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009013', 'principal-insert-texto-facultativo', 'Principal Insert Texto Facultativo', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009014', 'principal-insert-email', 'Principal Insert Email', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009015', 'principal-insert-url-imagem', 'Principal Insert Url Imagem', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009016', 'principal-insert-url', 'Principal Insert Url', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009017', 'principal-insert-nome', 'Principal Insert Nome', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009018', 'principal-insert-titulo', 'Principal Insert Titulo', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009019', 'principal-insert-cpf', 'Principal Insert Cpf', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009020', 'principal-insert-cnpj', 'Principal Insert Cnpj', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009021', 'principal-insert-rg', 'Principal Insert Rg', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009022', 'principal-insert-celular', 'Principal Insert Celular', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009023', 'principal-insert-texto-grande', 'Principal Insert Texto Grande', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009024', 'principal-insert-snake-case', 'Principal Insert Snake Case', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009025', 'principal-insert-decimal-facultativo', 'Principal Insert Decimal Facultativo', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009026', 'principal-insert-interio-facultativo', 'Principal Insert Interio Facultativo', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009027', 'principal-insert-booleano-facultativo', 'Principal Insert Booleano Facultativo', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009028', 'principal-insert-data-facultativa', 'Principal Insert Data Facultativa', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009029', 'principal-insert-datahora-facultativa', 'Principal Insert Datahora Facultativa', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009030', 'principal-insert-data-alteracao', 'Principal Insert Data Alteracao', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009031', 'principal-insert-preco', 'Principal Insert Preco', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2009032', 'principal-insert-senha', 'Principal Insert Senha', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009001', 'principal-update-id-principal-pk', 'Principal Update Id Principal Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009002', 'principal-update-id-grupo-do-principal-fk', 'Principal Update Id Grupo Do Principal Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009003', 'principal-update-id-grupo-do-principal-facultativo-fk', 'Principal Update Id Grupo Do Principal Facultativo Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009004', 'principal-update-texto-obrigatorio', 'Principal Update Texto Obrigatorio', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009005', 'principal-update-unico', 'Principal Update Unico', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009006', 'principal-update-decimal-obrigatorio', 'Principal Update Decimal Obrigatorio', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009007', 'principal-update-inteiro-obrigatorio', 'Principal Update Inteiro Obrigatorio', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009008', 'principal-update-booleano-obrigatorio', 'Principal Update Booleano Obrigatorio', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009009', 'principal-update-data-obrigatoria', 'Principal Update Data Obrigatoria', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009010', 'principal-update-datahora-obrigatoris', 'Principal Update Datahora Obrigatoris', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009011', 'principal-update-ativo', 'Principal Update Ativo', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009012', 'principal-update-data-criacao', 'Principal Update Data Criacao', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009013', 'principal-update-texto-facultativo', 'Principal Update Texto Facultativo', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009014', 'principal-update-email', 'Principal Update Email', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009015', 'principal-update-url-imagem', 'Principal Update Url Imagem', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009016', 'principal-update-url', 'Principal Update Url', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009017', 'principal-update-nome', 'Principal Update Nome', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009018', 'principal-update-titulo', 'Principal Update Titulo', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009019', 'principal-update-cpf', 'Principal Update Cpf', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009020', 'principal-update-cnpj', 'Principal Update Cnpj', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009021', 'principal-update-rg', 'Principal Update Rg', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009022', 'principal-update-celular', 'Principal Update Celular', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009023', 'principal-update-texto-grande', 'Principal Update Texto Grande', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009024', 'principal-update-snake-case', 'Principal Update Snake Case', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009025', 'principal-update-decimal-facultativo', 'Principal Update Decimal Facultativo', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009026', 'principal-update-interio-facultativo', 'Principal Update Interio Facultativo', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009027', 'principal-update-booleano-facultativo', 'Principal Update Booleano Facultativo', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009028', 'principal-update-data-facultativa', 'Principal Update Data Facultativa', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009029', 'principal-update-datahora-facultativa', 'Principal Update Datahora Facultativa', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009030', 'principal-update-data-alteracao', 'Principal Update Data Alteracao', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009031', 'principal-update-preco', 'Principal Update Preco', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3009032', 'principal-update-senha', 'Principal Update Senha', '', '1');


-- ROLE
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1010001', 'role-read-id-role-pk', 'Role Read Id Role Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1010002', 'role-read-slug', 'Role Read Slug', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1010003', 'role-read-name', 'Role Read Name', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1010004', 'role-read-description', 'Role Read Description', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1010005', 'role-read-level', 'Role Read Level', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1010006', 'role-read-active', 'Role Read Active', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2010001', 'role-insert-id-role-pk', 'Role Insert Id Role Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2010002', 'role-insert-slug', 'Role Insert Slug', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2010003', 'role-insert-name', 'Role Insert Name', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2010004', 'role-insert-description', 'Role Insert Description', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2010005', 'role-insert-level', 'Role Insert Level', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2010006', 'role-insert-active', 'Role Insert Active', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3010001', 'role-update-id-role-pk', 'Role Update Id Role Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3010002', 'role-update-slug', 'Role Update Slug', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3010003', 'role-update-name', 'Role Update Name', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3010004', 'role-update-description', 'Role Update Description', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3010005', 'role-update-level', 'Role Update Level', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3010006', 'role-update-active', 'Role Update Active', '', '1');


-- ROLE_PERMISSION
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1011001', 'role-permission-read-id-role-fk', 'Role Permission Read Id Role Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1011002', 'role-permission-read-id-permission-fk', 'Role Permission Read Id Permission Fk', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2011001', 'role-permission-insert-id-role-fk', 'Role Permission Insert Id Role Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2011002', 'role-permission-insert-id-permission-fk', 'Role Permission Insert Id Permission Fk', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3011001', 'role-permission-update-id-role-fk', 'Role Permission Update Id Role Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3011002', 'role-permission-update-id-permission-fk', 'Role Permission Update Id Permission Fk', '', '1');


-- TAG_PRINCIPAL
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1012001', 'tag-principal-read-id-tag-fk', 'Tag Principal Read Id Tag Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1012002', 'tag-principal-read-id-principal-fk', 'Tag Principal Read Id Principal Fk', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2012001', 'tag-principal-insert-id-tag-fk', 'Tag Principal Insert Id Tag Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2012002', 'tag-principal-insert-id-principal-fk', 'Tag Principal Insert Id Principal Fk', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3012001', 'tag-principal-update-id-tag-fk', 'Tag Principal Update Id Tag Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3012002', 'tag-principal-update-id-principal-fk', 'Tag Principal Update Id Principal Fk', '', '1');


-- TAG
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1013001', 'tag-read-id-tag-pk', 'Tag Read Id Tag Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1013002', 'tag-read-titulo', 'Tag Read Titulo', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2013001', 'tag-insert-id-tag-pk', 'Tag Insert Id Tag Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2013002', 'tag-insert-titulo', 'Tag Insert Titulo', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3013001', 'tag-update-id-tag-pk', 'Tag Update Id Tag Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3013002', 'tag-update-titulo', 'Tag Update Titulo', '', '1');


-- USER
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1014001', 'user-read-id-user-pk', 'User Read Id User Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1014002', 'user-read-email', 'User Read Email', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1014003', 'user-read-senha', 'User Read Senha', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2014001', 'user-insert-id-user-pk', 'User Insert Id User Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2014002', 'user-insert-email', 'User Insert Email', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2014003', 'user-insert-senha', 'User Insert Senha', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3014001', 'user-update-id-user-pk', 'User Update Id User Pk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3014002', 'user-update-email', 'User Update Email', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3014003', 'user-update-senha', 'User Update Senha', '', '1');


-- USER_PERMISSION
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1015001', 'user-permission-read-id-user-fk', 'User Permission Read Id User Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1015002', 'user-permission-read-id-permission-fk', 'User Permission Read Id Permission Fk', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2015001', 'user-permission-insert-id-user-fk', 'User Permission Insert Id User Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2015002', 'user-permission-insert-id-permission-fk', 'User Permission Insert Id Permission Fk', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3015001', 'user-permission-update-id-user-fk', 'User Permission Update Id User Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3015002', 'user-permission-update-id-permission-fk', 'User Permission Update Id Permission Fk', '', '1');


-- USER_ROLE
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1016001', 'user-role-read-id-user-fk', 'User Role Read Id User Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('1016002', 'user-role-read-id-role-fk', 'User Role Read Id Role Fk', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2016001', 'user-role-insert-id-user-fk', 'User Role Insert Id User Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('2016002', 'user-role-insert-id-role-fk', 'User Role Insert Id Role Fk', '', '1');

INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3016001', 'user-role-update-id-user-fk', 'User Role Update Id User Fk', '', '1');
INSERT INTO `usecase`.`permission` (`idPermissionPk`, `scope`, `name`, `description`, `active`) VALUES ('3016002', 'user-role-update-id-role-fk', 'User Role Update Id Role Fk', '', '1');
--
UNLOCK TABLES;


LOCK TABLES `usecase`.`role_permission` WRITE;
--
INSERT INTO `usecase`.`role_permission` (idRoleFk, idPermissionFk)
VALUES
('1', '1'),
('2', '1001'),
('2', '1002'),
('2', '1003'),
('2', '1004'),
('2', '1005'),
('2', '1006'),
('2', '1007'),
('2', '1008'),
('2', '1009'),
('2', '1010'),
('2', '1011'),
('2', '1012'),
('2', '1013'),
('2', '1014'),
('2', '1015'),
('2', '1016'),
('2', '2001'),
('2', '2002'),
('2', '2003'),
('2', '2004'),
('2', '2005'),
('2', '2006'),
('2', '2007'),
('2', '2008'),
('2', '2009'),
('2', '2010'),
('2', '2011'),
('2', '2012'),
('2', '2013'),
('2', '2014'),
('2', '2015'),
('2', '2016'),
('2', '3001'),
('2', '3002'),
('2', '3003'),
('2', '3004'),
('2', '3005'),
('2', '3006'),
('2', '3007'),
('2', '3008'),
('2', '3009'),
('2', '3010'),
('2', '3011'),
('2', '3012'),
('2', '3013'),
('2', '3014'),
('2', '3015'),
('2', '3016'),
('3', '1001'),
('3', '1002'),
('3', '1003'),
('3', '1004'),
('3', '1005'),
('3', '1006'),
('3', '1007'),
('3', '1008'),
('3', '1009'),
('3', '1010'),
('3', '1011'),
('3', '1012'),
('3', '1013'),
('3', '1014'),
('3', '1015'),
('3', '1016');
--
UNLOCK TABLES;


LOCK TABLES `usecase`.`permission_permission` WRITE;
--
INSERT INTO `usecase`.`permission_permission` (idPermissionParentFk, idPermissionChildFk)
VALUES
('1', '101'),
('1', '102'),
('1', '103'),
('1', '104'),
('1', '105'),
('1', '106'),
('1', '107'),
('1', '108'),
('1', '109'),
('1', '110'),
('1', '111'),
('1', '112'),
('1', '113'),
('1', '114'),
('1', '115'),
('1', '116'),
('101', '1001'),
('101', '2001'),
('101', '3001'),
('101', '4001'),
('102', '1002'),
('102', '2002'),
('102', '3002'),
('102', '4002'),
('103', '1003'),
('103', '2003'),
('103', '3003'),
('103', '4003'),
('104', '1004'),
('104', '2004'),
('104', '3004'),
('104', '4004'),
('105', '1005'),
('105', '2005'),
('105', '3005'),
('105', '4005'),
('106', '1006'),
('106', '2006'),
('106', '3006'),
('106', '4006'),
('107', '1007'),
('107', '2007'),
('107', '3007'),
('107', '4007'),
('108', '1008'),
('108', '2008'),
('108', '3008'),
('108', '4008'),
('109', '1009'),
('109', '2009'),
('109', '3009'),
('109', '4009'),
('110', '1010'),
('110', '2010'),
('110', '3010'),
('110', '4010'),
('111', '1011'),
('111', '2011'),
('111', '3011'),
('111', '4011'),
('112', '1012'),
('112', '2012'),
('112', '3012'),
('112', '4012'),
('113', '1013'),
('113', '2013'),
('113', '3013'),
('113', '4013'),
('114', '1014'),
('114', '2014'),
('114', '3014'),
('114', '4014'),
('115', '1015'),
('115', '2015'),
('115', '3015'),
('115', '4015'),
('116', '1016'),
('116', '2016'),
('116', '3016'),
('116', '4016'),
('1001', '1001001'),
('1001', '1001002'),
('2001', '2001001'),
('2001', '2001002'),
('3001', '3001001'),
('3001', '3001002'),
('1002', '1002001'),
('1002', '1002002'),
('1002', '1002003'),
('2002', '2002001'),
('2002', '2002002'),
('2002', '2002003'),
('3002', '3002001'),
('3002', '3002002'),
('3002', '3002003'),
('1003', '1003001'),
('1003', '1003002'),
('1003', '1003003'),
('1003', '1003004'),
('1003', '1003005'),
('1003', '1003006'),
('1003', '1003007'),
('1003', '1003008'),
('1003', '1003009'),
('2003', '2003001'),
('2003', '2003002'),
('2003', '2003003'),
('2003', '2003004'),
('2003', '2003005'),
('2003', '2003006'),
('2003', '2003007'),
('2003', '2003008'),
('2003', '2003009'),
('3003', '3003001'),
('3003', '3003002'),
('3003', '3003003'),
('3003', '3003004'),
('3003', '3003005'),
('3003', '3003006'),
('3003', '3003007'),
('3003', '3003008'),
('3003', '3003009'),
('1004', '1004001'),
('1004', '1004002'),
('2004', '2004001'),
('2004', '2004002'),
('3004', '3004001'),
('3004', '3004002'),
('1005', '1005001'),
('1005', '1005002'),
('2005', '2005001'),
('2005', '2005002'),
('3005', '3005001'),
('3005', '3005002'),
('1006', '1006001'),
('1006', '1006002'),
('1006', '1006003'),
('2006', '2006001'),
('2006', '2006002'),
('2006', '2006003'),
('3006', '3006001'),
('3006', '3006002'),
('3006', '3006003'),
('1007', '1007001'),
('1007', '1007002'),
('1007', '1007003'),
('1007', '1007004'),
('1007', '1007005'),
('2007', '2007001'),
('2007', '2007002'),
('2007', '2007003'),
('2007', '2007004'),
('2007', '2007005'),
('3007', '3007001'),
('3007', '3007002'),
('3007', '3007003'),
('3007', '3007004'),
('3007', '3007005'),
('1008', '1008001'),
('1008', '1008002'),
('2008', '2008001'),
('2008', '2008002'),
('3008', '3008001'),
('3008', '3008002'),
('1009', '1009001'),
('1009', '1009002'),
('1009', '1009003'),
('1009', '1009004'),
('1009', '1009005'),
('1009', '1009006'),
('1009', '1009007'),
('1009', '1009008'),
('1009', '1009009'),
('1009', '1009010'),
('1009', '1009011'),
('1009', '1009012'),
('1009', '1009013'),
('1009', '1009014'),
('1009', '1009015'),
('1009', '1009016'),
('1009', '1009017'),
('1009', '1009018'),
('1009', '1009019'),
('1009', '1009020'),
('1009', '1009021'),
('1009', '1009022'),
('1009', '1009023'),
('1009', '1009024'),
('1009', '1009025'),
('1009', '1009026'),
('1009', '1009027'),
('1009', '1009028'),
('1009', '1009029'),
('1009', '1009030'),
('1009', '1009031'),
('1009', '1009032'),
('2009', '2009001'),
('2009', '2009002'),
('2009', '2009003'),
('2009', '2009004'),
('2009', '2009005'),
('2009', '2009006'),
('2009', '2009007'),
('2009', '2009008'),
('2009', '2009009'),
('2009', '2009010'),
('2009', '2009011'),
('2009', '2009012'),
('2009', '2009013'),
('2009', '2009014'),
('2009', '2009015'),
('2009', '2009016'),
('2009', '2009017'),
('2009', '2009018'),
('2009', '2009019'),
('2009', '2009020'),
('2009', '2009021'),
('2009', '2009022'),
('2009', '2009023'),
('2009', '2009024'),
('2009', '2009025'),
('2009', '2009026'),
('2009', '2009027'),
('2009', '2009028'),
('2009', '2009029'),
('2009', '2009030'),
('2009', '2009031'),
('2009', '2009032'),
('3009', '3009001'),
('3009', '3009002'),
('3009', '3009003'),
('3009', '3009004'),
('3009', '3009005'),
('3009', '3009006'),
('3009', '3009007'),
('3009', '3009008'),
('3009', '3009009'),
('3009', '3009010'),
('3009', '3009011'),
('3009', '3009012'),
('3009', '3009013'),
('3009', '3009014'),
('3009', '3009015'),
('3009', '3009016'),
('3009', '3009017'),
('3009', '3009018'),
('3009', '3009019'),
('3009', '3009020'),
('3009', '3009021'),
('3009', '3009022'),
('3009', '3009023'),
('3009', '3009024'),
('3009', '3009025'),
('3009', '3009026'),
('3009', '3009027'),
('3009', '3009028'),
('3009', '3009029'),
('3009', '3009030'),
('3009', '3009031'),
('3009', '3009032'),
('1010', '1010001'),
('1010', '1010002'),
('1010', '1010003'),
('1010', '1010004'),
('1010', '1010005'),
('1010', '1010006'),
('2010', '2010001'),
('2010', '2010002'),
('2010', '2010003'),
('2010', '2010004'),
('2010', '2010005'),
('2010', '2010006'),
('3010', '3010001'),
('3010', '3010002'),
('3010', '3010003'),
('3010', '3010004'),
('3010', '3010005'),
('3010', '3010006'),
('1011', '1011001'),
('1011', '1011002'),
('2011', '2011001'),
('2011', '2011002'),
('3011', '3011001'),
('3011', '3011002'),
('1012', '1012001'),
('1012', '1012002'),
('2012', '2012001'),
('2012', '2012002'),
('3012', '3012001'),
('3012', '3012002'),
('1013', '1013001'),
('1013', '1013002'),
('2013', '2013001'),
('2013', '2013002'),
('3013', '3013001'),
('3013', '3013002'),
('1014', '1014001'),
('1014', '1014002'),
('1014', '1014003'),
('2014', '2014001'),
('2014', '2014002'),
('2014', '2014003'),
('3014', '3014001'),
('3014', '3014002'),
('3014', '3014003'),
('1015', '1015001'),
('1015', '1015002'),
('2015', '2015001'),
('2015', '2015002'),
('3015', '3015001'),
('3015', '3015002'),
('1016', '1016001'),
('1016', '1016002'),
('2016', '2016001'),
('2016', '2016002'),
('3016', '3016001'),
('3016', '3016002');
--
UNLOCK TABLES;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
