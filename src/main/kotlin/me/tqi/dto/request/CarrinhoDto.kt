package me.tqi.dto.request


import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.tqi.entity.Carrinho
import me.tqi.entity.Produto
import me.tqi.entity.Usuario


data class CarrinhoDto(
    @field:NotEmpty(message = "Invalid input")val idUsuario: Long,
    @field:NotNull(message = "Invalid input") val idProduto: Long,
    @field:NotEmpty(message = "Invalid input")val quantidade: Int,
    val precoUnitario: Double

    ) {
    fun toEntity(): Carrinho = Carrinho(
        usuario = Usuario(
            id = this.idUsuario
        ),
        produto = Produto(
            idProduto = this.idProduto,
            precoUnitario = this.precoUnitario

        ),
        quantidade = this.quantidade
    )

}

/*
        usuario = mutableListOf(
            Usuario(
                cpf = this.cpf
            )
        ),
 */

