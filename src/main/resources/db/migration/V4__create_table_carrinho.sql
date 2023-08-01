CREATE TABLE carrinho (
  id BIGINT AUTO_INCREMENT NOT NULL,
   quantidade INT NOT NULL,
   produto_id_produto BIGINT NOT NULL,
   usuario_id BIGINT NULL,
   CONSTRAINT pk_carrinho PRIMARY KEY (id)
);

ALTER TABLE carrinho ADD CONSTRAINT FK_CARRINHO_ON_PRODUTO_IDPRODUTO FOREIGN KEY (produto_id_produto) REFERENCES produto (id_produto);

ALTER TABLE carrinho ADD CONSTRAINT FK_CARRINHO_ON_USUARIO FOREIGN KEY (usuario_id) REFERENCES usuario (id);