package me.tqi.dto.response

import me.tqi.entity.Carrinho


data class CarrinhoView(
    val nomeProduto: String,
    val precoUnitario: Double,
    val quantidade: Int,
){
    constructor(carrinho: Carrinho): this(
        nomeProduto = carrinho.produto.nomeProduto,
        precoUnitario = carrinho.produto.precoUnitario,
        quantidade = carrinho.quantidade
        )
}