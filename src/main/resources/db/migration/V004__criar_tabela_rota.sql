CREATE TABLE rota (
  id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  nome varchar(10) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40000 ALTER TABLE rota DISABLE KEYS */;
INSERT INTO rota (nome) VALUES 
("R1"),
("R2"),
("R3"),
("R4"),
("R5"),
("R6"),
("R7");
/*!40000 ALTER TABLE rota ENABLE KEYS */;