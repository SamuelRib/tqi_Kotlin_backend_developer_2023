package me.tqi.service


import me.tqi.entity.Produto
import java.util.*

interface IProdutoService {
    fun save(produto: Produto): Produto
    fun findAllByProdutosCategoria(idCategoria: Long): List<Produto>
    fun findByIdProduto(idProduto: Long): Produto
    fun deleteProduto(idProduto: Long)
}