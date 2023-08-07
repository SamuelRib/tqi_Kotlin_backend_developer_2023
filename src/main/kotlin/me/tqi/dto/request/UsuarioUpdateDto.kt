package me.tqi.dto.request

import jakarta.validation.constraints.NotEmpty
import me.tqi.entity.Usuario

data class UsuarioUpdateDto(
    @field:NotEmpty(message = "Invalid input") val nome: String,
    @field:NotEmpty(message = "Invalid input") val cpf: String,
    @field:NotEmpty(message = "Invalid input") val email: String,
    @field:NotEmpty(message = "Invalid input") val endereco: String
){
    fun toEntity(usuario: Usuario): Usuario{
        usuario.nome = nome
        usuario.cpf = cpf
        usuario.email = email
        usuario.endereco = endereco
        return usuario
    }
}

