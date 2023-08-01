package me.tqi.service

import me.tqi.entity.Carrinho


interface ICarrinhoService {
    fun salvarProdutoNoCarrinho(carrinho: Carrinho): Carrinho
    fun findByIdUsuarioCarrinho(idUsuario: Long): List<Carrinho>
    fun deleteCarrinho(idCarrinho: Long)

}