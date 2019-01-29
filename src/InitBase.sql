CREATE DATABASE bdd_ecole
 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE USER 'ws'@'localhost' IDENTIFIED BY 'ws';
ALTER USER 'ws'@'localhost' IDENTIFIED BY 'ws';
GRANT ALL PRIVILEGES ON bdd_ecole.* TO 'ws'@'localhost';

CREATE TABLE  bdd_ecole.Utilisateur (
 id INT( 11 ) NOT NULL AUTO_INCREMENT ,
 email VARCHAR( 60 ) NOT NULL ,
 mot_de_passe VARCHAR( 56 ) NOT NULL ,
 nom VARCHAR( 20 ) NOT NULL ,
 date_inscription DATETIME NOT NULL ,
 PRIMARY KEY ( id ),
 UNIQUE ( email )
) ENGINE = INNODB;
