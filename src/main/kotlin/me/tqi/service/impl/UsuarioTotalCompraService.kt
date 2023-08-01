package me.tqi.service.impl

import me.tqi.entity.UsuarioTotalCompra
import me.tqi.repository.UsuarioTotalCompraRepository
import me.tqi.service.IUsuarioTotalCompra
import org.springframework.stereotype.Service

@Service
class UsuarioTotalCompraService(
    private val usuarioTotalCompraRepository: UsuarioTotalCompraRepository
): IUsuarioTotalCompra {
    override fun obterTotalUsuario(idUsuario: Long): UsuarioTotalCompra {
        return usuarioTotalCompraRepository.obterTotalCompraPorUsuario(idUsuario)
    }
}