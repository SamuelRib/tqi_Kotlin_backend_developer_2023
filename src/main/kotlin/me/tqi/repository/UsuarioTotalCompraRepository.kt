package me.tqi.repository

import me.tqi.entity.UsuarioTotalCompra
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UsuarioTotalCompraRepository: JpaRepository<UsuarioTotalCompra, Long> {

    @Query(
        value = "SELECT usuario_id, SUM(quantidade + preco_unitario) AS total_compra" +
                " FROM carrinho" +
                " JOIN produto ON carrinho.produto_id_produto = produto.id_produto" +
                " WHERE carrinho.usuario_id = ?1 GROUP BY usuario_id;",
        nativeQuery = true
    )
    fun obterTotalCompraPorUsuario(usuarioId: Long): UsuarioTotalCompra
}