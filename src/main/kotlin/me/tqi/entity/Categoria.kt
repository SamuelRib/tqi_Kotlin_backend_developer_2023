package me.tqi.entity

import jakarta.persistence.*



@Entity
@Table(name="Categoria")
data class Categoria(
    @Id @GeneratedValue(strategy =GenerationType.IDENTITY) var id: Long? = null,
    @Column(nullable = false) var nomeCategoria: String ="",
    @Column(nullable = false) @OneToMany(fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.REMOVE,
        CascadeType.PERSIST), mappedBy = "categoria")
    var produto: List<Produto> = mutableListOf()
)
