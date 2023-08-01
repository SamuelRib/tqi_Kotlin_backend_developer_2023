CREATE TABLE item_carrinho (
  id BIGINT AUTO_INCREMENT NOT NULL,
   produto_id_produto BIGINT NULL,
   carrinho_id BIGINT NULL,
   quantidade INT NOT NULL,
   CONSTRAINT pk_itemcarrinho PRIMARY KEY (id)
);

ALTER TABLE item_carrinho ADD CONSTRAINT FK_ITEMCARRINHO_ON_CARRINHO FOREIGN KEY (carrinho_id) REFERENCES carrinho (id);

ALTER TABLE item_carrinho ADD CONSTRAINT FK_ITEMCARRINHO_ON_PRODUTO_IDPRODUTO FOREIGN KEY (produto_id_produto) REFERENCES produto (id_produto);