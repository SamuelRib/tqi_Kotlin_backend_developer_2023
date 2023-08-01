package me.tqi.dto.request

import jakarta.validation.constraints.NotEmpty
import me.tqi.entity.Usuario

data class UsuarioDto(
    @field:NotEmpty(message = "Invalid input") val nome: String,
    @field:NotEmpty(message = "Invalid input") val cpf: String,
    @field:NotEmpty(message = "Invalid input") val email: String,
    @field:NotEmpty(message = "Invalid input") val endereco: String
){
    fun toEntity(): Usuario = Usuario(
        nome = this.nome,
        cpf = this.cpf,
        email = this.email,
        endereco = this.endereco
    )
}
