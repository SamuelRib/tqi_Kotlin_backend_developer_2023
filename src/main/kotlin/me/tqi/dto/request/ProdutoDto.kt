package me.tqi.dto.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.tqi.entity.Categoria
import me.tqi.entity.Produto

data class ProdutoDto(
    @field:NotEmpty(message = "Invalid input") val nomeProduto: String,
    val precoUnitario: Double,
    val unidadeMedida: String,
    @field:NotNull(message = "Invalid input") val idCategoria: Long
){
    fun toEntity(): Produto = Produto(
        nomeProduto = this.nomeProduto,
        precoUnitario = this.precoUnitario,
        unidadeMedida = this.unidadeMedida,
        categoria = Categoria(
            id = this.idCategoria
        )
    )
}
