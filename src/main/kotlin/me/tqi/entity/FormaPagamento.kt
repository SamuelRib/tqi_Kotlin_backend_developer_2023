package me.tqi.entity

import jakarta.persistence.*

@Entity
data class FormaPagamento(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    val cartaoCredito: String ="",
    val debito: String = "",
    val dinheiroFisico: String ="",
    val pix: String = "",
    @ManyToOne var finalizacaoVenda: FinalizacaoVenda = FinalizacaoVenda()
)
