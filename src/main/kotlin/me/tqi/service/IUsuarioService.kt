package me.tqi.service

import me.tqi.entity.Usuario

interface IUsuarioService {
    fun salvarUsuario(usuario: Usuario): Usuario
    fun findByIdUsuario(idUsuario: Long): Usuario
    fun deleteUsuario(idUsuario: Long)
}