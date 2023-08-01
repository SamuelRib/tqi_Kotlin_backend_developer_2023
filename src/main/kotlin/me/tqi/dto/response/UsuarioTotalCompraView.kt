package me.tqi.dto.response

import me.tqi.entity.UsuarioTotalCompra

data class UsuarioTotalCompraView(
    val idUsuario: Long, //transformar essa parte em long no sql
    val totalCompra: Double
    //USUARIO_ID  TOTAL_COMPRA   vem assim na tab carrinho
){
    constructor(usuarioTotalCompra: UsuarioTotalCompra):this(
        idUsuario = usuarioTotalCompra.idUsuario,
        totalCompra = usuarioTotalCompra.totalCompra
    )
}
//Failed to convert value of type 'java.lang.String' to required type 'long'; For input string: "usuarioId"]