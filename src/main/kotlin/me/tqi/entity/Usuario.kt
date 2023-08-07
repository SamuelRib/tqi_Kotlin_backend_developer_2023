package me.tqi.entity

import jakarta.persistence.*


@Entity
@Table(name = "Usuario")
data class Usuario(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @Column(nullable = false) var nome: String="",
    @Column(nullable = false, unique = true) var cpf: String ="",
    @Column(nullable = false, unique = true) var email: String = "",
    @Column(nullable = false)var endereco: String = "",
    @Column(nullable = false) @OneToMany(fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.REMOVE,
    CascadeType.PERSIST), mappedBy = "usuario")
    var carrinho: List<Carrinho> = mutableListOf()
)
