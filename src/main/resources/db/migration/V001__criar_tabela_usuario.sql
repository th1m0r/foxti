CREATE TABLE usuario (
  id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  nome varchar(60) NOT NULL,
  login varchar(10) NOT NULL,
  senha varchar(60) NOT NULL,  
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
