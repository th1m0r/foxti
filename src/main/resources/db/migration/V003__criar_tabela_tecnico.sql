CREATE TABLE tecnico (
  id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  nome varchar(60) NOT NULL,
  email varchar(100) NOT NULL,
  telefone varchar(15) NOT NULL,  
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
