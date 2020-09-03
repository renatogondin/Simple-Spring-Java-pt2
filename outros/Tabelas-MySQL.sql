https://dev.mysql.com/downloads/

Download MySQL Community Server

Últimas versões:
- 5.5
- 5.6
- 5.7
- 8.0.12 (atual)

DROP TABLE banco.lance;
DROP TABLE banco.produto;
DROP TABLE banco.viagem;
DROP TABLE banco.carro;
DROP TABLE banco.motorista;
DROP TABLE banco.cliente;

CREATE TABLE banco.produto (
  id              INT(11) NOT NULL AUTO_INCREMENT,
  nome            VARCHAR(30) NOT NULL,
  descricao       VARCHAR(50) DEFAULT '',
  lance_minimo    DECIMAL(8, 2) NOT NULL,
  data_cadastro   DATE NOT NULL,
  data_venda      DATE DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE banco.lance (
  id                INT(11) NOT NULL AUTO_INCREMENT,
  valor             DECIMAL(10, 2) NOT NULL,
  data_criacao      DATE NOT NULL,
  produto_id        INT(11) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT PRODUTO_LANCE_FK 
  FOREIGN KEY (produto_id)
  REFERENCES banco.produto(id) 
  ON DELETE NO ACTION ON UPDATE RESTRICT
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

INSERT INTO produto(NOME, DESCRICAO, LANCE_MINIMO, DATA_CADASTRO)
VALUES('TV SAMSUNG 20 POL', 'TV SAMSUNG 20 POL TELA PLANA', 2000, now());

INSERT INTO LANCE(VALOR, DATA_CRIACAO, PRODUTO_ID) VALUES
(2100, now(), LAST_INSERT_ID()),
(2200, now(), LAST_INSERT_ID());

INSERT INTO produto(NOME, DESCRICAO, LANCE_MINIMO, DATA_CADASTRO)
VALUES('TV SAMSUNG 22 POL', 'TV SAMSUNG 22 POL TELA PLANA', 2500, now());

INSERT INTO LANCE(VALOR, DATA_CRIACAO, PRODUTO_ID) VALUES
(2600, now(), LAST_INSERT_ID()),
(2700, now(), LAST_INSERT_ID());

CREATE TABLE banco.cliente (
  id               INT(11) NOT NULL AUTO_INCREMENT,
  nome         VARCHAR(30) NOT NULL,
  sobrenome	       VARCHAR(50) NOT NULL,
  email		       VARCHAR(50) NOT NULL,  
  PRIMARY KEY (id)
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;


CREATE TABLE banco.motorista (
  id                INT(11) NOT NULL AUTO_INCREMENT,
  nome             VARCHAR(30) NOT NULL,
  sobrenome      		VARCHAR(30) NOT NULL,
  email      		VARCHAR(30) NOT NULL,
  cliente_id        	INT(11) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT CLIENTE_MOTORISTA_FK 
  FOREIGN KEY (cliente_id)
  REFERENCES banco.cliente(id) 
  ON DELETE NO ACTION ON UPDATE RESTRICT
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE banco.carro (
  id                INT(11) NOT NULL AUTO_INCREMENT,
  marca            VARCHAR(30) NOT NULL,
  modelo      		VARCHAR(30) NOT NULL,
  placa     		VARCHAR(30) NOT NULL,
  motorista_id        	INT(11) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT MOTORISTA_CARRO_FK 
  FOREIGN KEY (motorista_id)
  REFERENCES banco.motorista(id) 
  ON DELETE NO ACTION ON UPDATE RESTRICT
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE banco.viagem (
  id                INT(11) NOT NULL AUTO_INCREMENT,
  saida              VARCHAR(30) NOT NULL,
  destino      		VARCHAR(30) NOT NULL,
  preco     		double NOT NULL,
  carro_id     INT(11) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT CARRO_VIAGEM_FK 
  FOREIGN KEY (carro_id)
  REFERENCES banco.carro(id) 
  ON DELETE NO ACTION ON UPDATE RESTRICT
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

