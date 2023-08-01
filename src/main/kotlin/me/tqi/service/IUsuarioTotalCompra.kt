package me.tqi.service

import me.tqi.entity.UsuarioTotalCompra


interface IUsuarioTotalCompra{
    fun obterTotalUsuario (idUsuario: Long ): UsuarioTotalCompra
}