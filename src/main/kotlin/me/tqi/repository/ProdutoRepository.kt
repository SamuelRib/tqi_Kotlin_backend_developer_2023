package me.tqi.repository

import me.tqi.entity.Produto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface ProdutoRepository: JpaRepository<Produto, Long> {
    fun findByIdProduto(id: Long): Produto?
    //fun findByProdutoCode(produtoCode: UUID): Produto? //Pesquisa produto pelo code dele.

    @Query(value = "SELECT * FROM PRODUTO WHERE CATEGORIA_ID = ?1", nativeQuery = true)//ele vai olhar na tabela produtos a coluna CATEGORIA_ID_CATEGORIA e usar o parâmetro idCategoria do método para encontrá-lo
    fun findByAllCategoriaId(idCategoria: Long): List<Produto>
}