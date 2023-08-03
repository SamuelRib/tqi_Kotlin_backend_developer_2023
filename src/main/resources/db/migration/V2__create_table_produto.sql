CREATE TABLE produto (
  id_produto BIGINT AUTO_INCREMENT NOT NULL,
   produto_code char(36) NOT NULL,
   nome_produto VARCHAR(255) NOT NULL,
   preco_unitario DOUBLE NOT NULL,
   categoria_id BIGINT NULL,
   peso DOUBLE NULL,
   litro DOUBLE NULL,
   CONSTRAINT pk_produto PRIMARY KEY (id_produto)
);

ALTER TABLE produto ADD CONSTRAINT uc_produto_produtocode UNIQUE (produto_code);

ALTER TABLE produto ADD CONSTRAINT FK_PRODUTO_ON_CATEGORIA FOREIGN KEY (categoria_id) REFERENCES categoria (id);