package me.tqi.entity

import jakarta.persistence.*
import java.util.*


@Entity
@Table(name="Categoria")
data class Categoria(
    @Id @GeneratedValue(strategy =GenerationType.IDENTITY) var id: Long? = null,
    @Column(nullable = false) var nomeCategoria: String ="",
    @Column(nullable = false) @OneToMany(fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.REMOVE,
        CascadeType.PERSIST), mappedBy = "categoria")
    var produto: List<Produto> = mutableListOf()
    //@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null
)
