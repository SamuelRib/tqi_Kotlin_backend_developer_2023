package me.tqi.dto.response


import me.tqi.entity.Usuario

data class UsuarioView(
    val nome: String,
    val cpf: String,
    val email: String,
    val endereco: String
){
    constructor(usuario: Usuario): this(
        nome = usuario.nome,
        cpf = usuario.cpf,
        email = usuario.email,
        endereco = usuario.endereco
    )
}
