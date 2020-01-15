SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `usecase`;

CREATE SCHEMA IF NOT EXISTS `usecase` DEFAULT CHARACTER SET utf8mb4 ;
USE `usecase`;

# ------------------------------------------------------------
# SCHEMA DUMP FOR TABLE: conectado
# ------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `conectado` (
  `idConectadoPk` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idConectadoPk`)
) ENGINE = InnoDB AUTO_INCREMENT = 51 DEFAULT CHARSET = utf8;

# ------------------------------------------------------------
# SCHEMA DUMP FOR TABLE: conector_principal
# ------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `conector_principal` (
  `idPrincipalFk` int(11) NOT NULL,
  `idConectadoFk` int(11) NOT NULL,
  `titulo` varchar(45) NOT NULL,
  PRIMARY KEY (`idPrincipalFk`, `idConectadoFk`),
  KEY `fk_conector_conectado_idx` (`idConectadoFk`),
  CONSTRAINT `fk_conector_conectado` FOREIGN KEY (`idConectadoFk`) REFERENCES `conectado` (`idconectadopk`),
  CONSTRAINT `fk_conector_principal` FOREIGN KEY (`idPrincipalFk`) REFERENCES `principal` (`idprincipalpk`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

# ------------------------------------------------------------
# SCHEMA DUMP FOR TABLE: endereco
# ------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `endereco` (
  `idEnderecoPk` int(11) NOT NULL AUTO_INCREMENT,
  `cep` varchar(45) DEFAULT NULL,
  `zipcode` varchar(45) DEFAULT NULL,
  `rua` varchar(45) DEFAULT NULL,
  `nro` int(11) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `uf` varchar(45) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  PRIMARY KEY (`idEnderecoPk`)
) ENGINE = InnoDB AUTO_INCREMENT = 51 DEFAULT CHARSET = utf8;

# ------------------------------------------------------------
# SCHEMA DUMP FOR TABLE: extensao_do_principal
# ------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `extensao_do_principal` (
  `idPrincipalFk` int(11) NOT NULL,
  `titulo` varchar(45) NOT NULL,
  PRIMARY KEY (`idPrincipalFk`),
  CONSTRAINT `fk_extensao_principal` FOREIGN KEY (`idPrincipalFk`) REFERENCES `principal` (`idprincipalpk`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

# ------------------------------------------------------------
# SCHEMA DUMP FOR TABLE: grupo_do_principal
# ------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `grupo_do_principal` (
  `idGrupoDoPrincipalPk` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) NOT NULL,
  PRIMARY KEY (`idGrupoDoPrincipalPk`)
) ENGINE = InnoDB AUTO_INCREMENT = 51 DEFAULT CHARSET = utf8;

# ------------------------------------------------------------
# SCHEMA DUMP FOR TABLE: item_do_principal
# ------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `item_do_principal` (
  `idItemDoPrincipalPk` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) NOT NULL,
  `idPrincipalFk` int(11) NOT NULL,
  PRIMARY KEY (`idItemDoPrincipalPk`),
  KEY `fk_item_principal_idx` (`idPrincipalFk`),
  CONSTRAINT `fk_item_principal` FOREIGN KEY (`idPrincipalFk`) REFERENCES `principal` (`idprincipalpk`)
) ENGINE = InnoDB AUTO_INCREMENT = 51 DEFAULT CHARSET = utf8;

# ------------------------------------------------------------
# SCHEMA DUMP FOR TABLE: principal
# ------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `principal` (
  `idPrincipalPk` int(11) NOT NULL AUTO_INCREMENT,
  `textoObrigatorio` varchar(160) NOT NULL COMMENT 'principal’s mandatory text',
  `textoFacultativo` varchar(45) DEFAULT NULL,
  `decimalObrigatorio` double NOT NULL,
  `decimalFacultativo` double DEFAULT NULL,
  `inteiroObrigatorio` int(11) NOT NULL,
  `inteiroFacultativo` int(11) DEFAULT NULL,
  `booleanoObrigatorio` tinyint(1) NOT NULL,
  `booleanoFacultativo` tinyint(1) DEFAULT NULL,
  `dataObrigatoria` date NOT NULL,
  `dataFacultativa` date DEFAULT NULL,
  `datahoraObrigatoria` datetime NOT NULL,
  `datahoraFacultativa` datetime DEFAULT NULL,
  `ativo` tinyint(1) NOT NULL DEFAULT '1',
  `email` varchar(200) DEFAULT NULL,
  `senha` varchar(200) DEFAULT NULL,
  `urlImagem` varchar(200) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `idGrupoDoPrincipalFk` int(11) NOT NULL,
  `idGrupoDoPrincipalFacultativoFk` int(11) DEFAULT NULL,
  `unico` varchar(40) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataAlteracao` datetime DEFAULT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `titulo` varchar(45) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `cnpj` varchar(45) DEFAULT NULL,
  `rg` varchar(45) DEFAULT NULL,
  `celular` varchar(45) DEFAULT NULL,
  `textoGrande` varchar(300) DEFAULT NULL,
  `snake_case` varchar(200) DEFAULT NULL,
  `preco` double DEFAULT NULL,
  PRIMARY KEY (`idPrincipalPk`),
  UNIQUE KEY `unico_UNIQUE` (`unico`),
  KEY `fk_principal_grupo_idx` (`idGrupoDoPrincipalFk`),
  KEY `fk_principal_grupo_facultativo_idx` (`idGrupoDoPrincipalFacultativoFk`),
  CONSTRAINT `fk_principal_grupo` FOREIGN KEY (`idGrupoDoPrincipalFk`) REFERENCES `grupo_do_principal` (`idgrupodoprincipalpk`),
  CONSTRAINT `fk_principal_grupo_facultativo` FOREIGN KEY (`idGrupoDoPrincipalFacultativoFk`) REFERENCES `grupo_do_principal` (`idgrupodoprincipalpk`)
) ENGINE = InnoDB AUTO_INCREMENT = 51 DEFAULT CHARSET = utf8 COMMENT = 'the main model of generator usecase';

# ------------------------------------------------------------
# SCHEMA DUMP FOR TABLE: tag
# ------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `tag` (
  `idTagPk` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) NOT NULL,
  PRIMARY KEY (`idTagPk`)
) ENGINE = InnoDB AUTO_INCREMENT = 51 DEFAULT CHARSET = utf8;

# ------------------------------------------------------------
# SCHEMA DUMP FOR TABLE: tag_principal
# ------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `tag_principal` (
  `idPrincipalFk` int(11) NOT NULL,
  `idTagFk` int(11) NOT NULL,
  PRIMARY KEY (`idPrincipalFk`, `idTagFk`),
  KEY `fk_principal_tag_idx` (`idTagFk`),
  CONSTRAINT `fk_principal_tag` FOREIGN KEY (`idTagFk`) REFERENCES `tag` (`idtagpk`),
  CONSTRAINT `fk_tag_principal` FOREIGN KEY (`idPrincipalFk`) REFERENCES `principal` (`idprincipalpk`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

# ------------------------------------------------------------
# SCHEMA DUMP FOR TABLE: user
# ------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `user` (
  `idUserPk` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `senha` varchar(200) NOT NULL,
  PRIMARY KEY (`idUserPk`)
) ENGINE = InnoDB AUTO_INCREMENT = 51 DEFAULT CHARSET = utf8;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
