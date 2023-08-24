package me.tqi.entity

import jakarta.persistence.*

@Entity
data class FinalizacaoVenda(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    val valorTotal: Double = 0.0,
    @ManyToOne val usuario: Usuario = Usuario(),
    @OneToMany(mappedBy = "finalizacaoVenda", cascade = [CascadeType.ALL])
    val formaPagamento: List<FormaPagamento> = mutableListOf()
)
