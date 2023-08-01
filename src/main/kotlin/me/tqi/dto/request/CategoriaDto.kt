package me.tqi.dto.request

import jakarta.validation.constraints.NotEmpty
import me.tqi.entity.Categoria

data class CategoriaDto(
    @field:NotEmpty(message = "Invalid input") val nomeCategoria: String
) {
    fun toEntity(): Categoria = Categoria(
        nomeCategoria = this.nomeCategoria
    )
}
