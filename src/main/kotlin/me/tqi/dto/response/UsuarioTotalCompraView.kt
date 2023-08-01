package me.tqi.dto.response

import me.tqi.entity.UsuarioTotalCompra

data class UsuarioTotalCompraView(
    val idUsuario: Long,
    val totalCompra: Double

){
    constructor(usuarioTotalCompra: UsuarioTotalCompra):this(
        idUsuario = usuarioTotalCompra.idUsuario,
        totalCompra = usuarioTotalCompra.totalCompra
    )
}