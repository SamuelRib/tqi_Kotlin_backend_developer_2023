package me.tqi.dto.response


import me.tqi.entity.Produto

data class ProdutoViewList(
    val nomeProduto: String,
    val precoUnitario: Double,
) {
    constructor(produto: Produto): this (
        nomeProduto = produto.nomeProduto,
        precoUnitario = produto.precoUnitario,
    )
}
