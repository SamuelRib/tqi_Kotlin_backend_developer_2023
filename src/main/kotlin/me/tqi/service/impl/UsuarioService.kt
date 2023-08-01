package me.tqi.service.impl

import me.tqi.entity.Usuario
import me.tqi.repository.UsuarioRepository
import me.tqi.service.IUsuarioService
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository
): IUsuarioService {
    override fun salvarUsuario(usuario: Usuario): Usuario {
        return this.usuarioRepository.save(usuario)
    }

    override fun findByIdUsuario(idUsuario: Long): Usuario {
        return this.usuarioRepository.findById(idUsuario)
            .orElseThrow { throw RuntimeException("IdUsuario $idUsuario não encontrado") }
    }

    override fun deleteUsuario(idUsuario: Long) {
        this.usuarioRepository.deleteById(idUsuario)
    }
}