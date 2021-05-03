CREATE TABLE ordem_servico (
  id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  contratante_id BIGINT(20) UNSIGNED NOT NULL,
  tecnico_id BIGINT(20) UNSIGNED NOT NULL,
  rota_id BIGINT(20) UNSIGNED NOT NULL,
  numero BIGINT(20) NOT NULL,
  status VARCHAR(20) NOT NULL,
  tipo_servico VARCHAR(20) NOT NULL,
  data_abertura DATE,
  data_atendimento DATE,
  data_retorno DATE,
  data_vencimento DATE,
  data_reagendamento DATE,
  usuario_cadastro VARCHAR(20),
  usuario_reagendamento VARCHAR(20),
  usuario_encerramento VARCHAR(20),
  observacao VARCHAR(100),
  PRIMARY KEY (id),
    
  CONSTRAINT FK_ordem_servico_contratante FOREIGN KEY (contratante_id)
    REFERENCES contratante (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    
  CONSTRAINT FK_ordem_servico_tecnico FOREIGN KEY (tecnico_id)
    REFERENCES tecnico (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    
  CONSTRAINT FK_ordem_servico_rota FOREIGN KEY (rota_id)
    REFERENCES rota (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;