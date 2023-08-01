package me.tqi.dto.response

import me.tqi.entity.Categoria

data class CategoriaView(
    val nomeCategoria: String
) {
    constructor(categoria: Categoria): this (
        nomeCategoria = categoria.nomeCategoria
    )

}
