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
        //produto.apply { categoria = categoriaService.findById(produto.categoria?.id!!) } //ver depois se precisa desse codigo
    }

    override fun findAllByProdutosCategoria(idCategoria: Long): List<Produto> { //Traz todos os produtos de uma determinada categoria
        return this.produtoRepository.findByAllCategoriaId(idCategoria)
    }

    override fun findByIdProduto(idProduto: Long): Produto {
        return produtoRepository.findByIdProduto(idProduto)
            ?: throw RuntimeException("ProdutoCode $idProduto not found")
    }

    override fun deleteProduto(idProduto: Long) {
        this.produtoRepository.deleteById(idProduto)
    }
}