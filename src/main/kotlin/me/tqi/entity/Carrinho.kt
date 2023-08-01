package me.tqi.entity

import jakarta.persistence.*

@Entity
@Table(name = "Carrinho")
data class Carrinho(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @Column(nullable = false) val quantidade: Int = 0,
    @JoinColumn(nullable = false) @ManyToOne val produto: Produto = Produto(),
    @JoinColumn @ManyToOne val usuario: Usuario = Usuario()
)

