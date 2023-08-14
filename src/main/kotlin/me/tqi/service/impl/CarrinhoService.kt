package me.tqi.service.impl

import me.tqi.entity.*
import me.tqi.repository.CarrinhoRepository
import me.tqi.service.ICarrinhoService
import org.springframework.stereotype.Service



@Service
class CarrinhoService(
    private val carrinhoRepository: CarrinhoRepository
) : ICarrinhoService {
    override fun salvarProdutoNoCarrinho(carrinho: Carrinho): Carrinho {
        return this.carrinhoRepository.save(carrinho)
    }

    override fun findByIdCarrinho(idCarrinho: Long): Carrinho {
        return this.carrinhoRepository.findById(idCarrinho)
            .orElseThrow { throw RuntimeException("idCarrinho $idCarrinho não encontrado") }
    }

    override fun findByIdUsuarioCarrinho(idUsuario: Long): List<Carrinho> {
        return this.carrinhoRepository.findByIdUsuarioCarrinho(idUsuario)
    }


    //A partir do idUsuario obtém o valor total de itens que o usuario selecionou
    fun obterUsuariosTotalCompra(idUsuario: Long): Double {
        return carrinhoRepository.obterTotalCompraPorUsuario(idUsuario)
    }

    override fun deleteCarrinho(idCarrinho: Long) {
        val carrinho: Carrinho = this.findByIdCarrinho(idCarrinho)
        this.carrinhoRepository.delete(carrinho)
    }

}
