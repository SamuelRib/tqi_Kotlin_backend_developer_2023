package me.tqi.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class UnidadeMedida(
    @Column(nullable = true) var peso: Double = 0.0,
    @Column(nullable = true) var litro: Double = 0.0
)
