package me.tqi.repository

import me.tqi.entity.Produto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface ProdutoRepository: JpaRepository<Produto, Long> {
    fun findByIdProduto(id: Long): Produto?
    //fun findByProdutoCode(produtoCode: UUID): Produto? //Pesquisa produto pelo code dele. (Implementar depois)


    //ele vai olhar na tabela produtos a coluna CATEGORIA_ID_CATEGORIA e usar o parâmetro idCategoria do método para encontrá-lo
    @Query(value = "SELECT * FROM produto WHERE CATEGORIA_ID = ?1", nativeQuery = true)
    fun findByAllCategoriaId(idCategoria: Long): List<Produto>
}