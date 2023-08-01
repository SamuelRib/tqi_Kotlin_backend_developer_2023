package me.tqi.repository

import me.tqi.entity.Carrinho
import me.tqi.entity.UsuarioTotalCompra
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface CarrinhoRepository : JpaRepository<Carrinho, Long> {

    @Query(value = "SELECT * FROM CARRINHO WHERE USUARIO_ID = ?1", nativeQuery = true)
    fun findByIdUsuarioCarrinho(idUsuario: Long): List<Carrinho>


    @Query(
        value = "SELECT usuario_id, SUM(quantidade + preco_unitario) AS total_compra" +
                " FROM carrinho" +
                " JOIN produto ON carrinho.produto_id_produto = produto.id_produto" +
                " WHERE carrinho.usuario_id = ?1 GROUP BY usuario_id;",
        nativeQuery = true
    )
    fun obterTotalCompraPorUsuario(usuarioId: Long): List<UsuarioTotalCompra>
}