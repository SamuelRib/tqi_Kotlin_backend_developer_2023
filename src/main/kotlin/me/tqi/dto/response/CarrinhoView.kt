package me.tqi.dto.response

import me.tqi.entity.Carrinho


data class CarrinhoView(
    val cpf: String,
    val nomeProduto: String,
    val precoUnitario: Double
){
    constructor(carrinho: Carrinho): this(
        cpf = carrinho.usuario.cpf,
        nomeProduto = carrinho.produto.nomeProduto,
        precoUnitario = carrinho.produto.precoUnitario
        )
}