package me.tqi.service.impl

import me.tqi.entity.Produto
import me.tqi.repository.ProdutoRepository
import me.tqi.service.IProdutoService
import org.springframework.stereotype.Service

@Service
class ProdutoService(
    private val produtoRepository: ProdutoRepository
): IProdutoService {
    override fun save(produto: Produto): Produto {
        return this.produtoRepository.save(produto)
    }

    override fun findAllByProdutosCategoria(idCategoria: Long): List<Produto> { //Traz todos os produtos de uma determinada categoria
        return this.produtoRepository.findByAllCategoriaId(idCategoria)
    }

    //Lcaliza o produto por seu IdProduto
    override fun findByIdProduto(idProduto: Long): Produto {
        return this.produtoRepository.findById(idProduto)
            .orElseThrow{ throw RuntimeException("IdProduto $idProduto not found")
        }
    }

    override fun deleteProduto(idProduto: Long) {
        val produto: Produto = this.findByIdProduto(idProduto)
        this.produtoRepository.delete(produto)
    }
}