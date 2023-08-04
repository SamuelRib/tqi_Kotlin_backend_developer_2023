package me.tqi.dto.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.tqi.entity.Produto

data class ProdutoUpdateDto(
    @field:NotEmpty(message = "Invalid input") val nomeProduto: String,
    val precoUnitario: Double,
    @field:NotNull(message = "Invalid input") val idCategoria: Long
){
    fun toEntity(produto: Produto): Produto{
        produto.nomeProduto = this.nomeProduto
        produto.precoUnitario = this.precoUnitario
        produto.categoria.id = this.idCategoria
        return produto
    }

}
