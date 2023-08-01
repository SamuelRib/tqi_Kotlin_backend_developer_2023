package me.tqi.service

import me.tqi.entity.UsuarioTotalCompra
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface IUsuarioTotalCompra{
    fun obterTotalUsuario (idUsuario: Long ): UsuarioTotalCompra
}