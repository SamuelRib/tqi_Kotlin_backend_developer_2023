package me.tqi.entity

import jakarta.persistence.*

@Entity
//@Table(name="forma_pagamento")
data class FormaPagamento(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @Column(nullable = false) val pagamento: String = "",
    @OneToMany(mappedBy = "pagamento", cascade = [CascadeType.ALL])
    val finalizacaoVenda: List<FinalizacaoVenda> = mutableListOf()
)

