package me.tqi.dto.request

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.tqi.entity.Categoria
import me.tqi.entity.Produto

data class ProdutoDto(
    @field:NotEmpty(message = "Invalid input") val nomeProduto: String,
    val precoUnitario: Double,
    @field:NotEmpty(message = "Invalid input") val nomeCategoria: String,
    @field:NotNull(message = "Invalid input") val idCategoria: Long
){
    fun toEntity(): Produto = Produto(
        nomeProduto = this.nomeProduto,
        precoUnitario = this.precoUnitario,
        categoria = Categoria(
            nomeCategoria = this.nomeCategoria,
            id = this.idCategoria
        )
    )
}
