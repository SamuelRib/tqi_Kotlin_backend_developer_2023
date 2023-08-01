package me.tqi.dto.request

import jakarta.validation.constraints.NotEmpty
import me.tqi.entity.Categoria

data class CategoriaUpdateDto(
    @field:NotEmpty(message = "Invalid input")val nomeCategoria: String
) {
    fun toEntity(categoria: Categoria): Categoria{
        categoria.nomeCategoria = this.nomeCategoria
        return categoria
    }
}
