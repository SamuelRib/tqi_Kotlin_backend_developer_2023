/*CREATE TABLE tabela_resultado AS
SELECT  usuario_id, produto.id_produto,(preco_unitario*quantidade) as total
FROM carrinho
JOIN produto ON carrinho.produto_id_produto = produto.id_produto
GROUP BY usuario_id;*/