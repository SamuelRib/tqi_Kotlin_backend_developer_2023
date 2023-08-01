package me.tqi.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class UsuarioTotalCompra(
    @Id
    val idUsuario: Long,
    val totalCompra: Double
)