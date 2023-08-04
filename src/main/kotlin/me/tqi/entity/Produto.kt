package me.tqi.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "Produto")
data class Produto(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val idProduto: Long? = null,
    @Column(nullable = false, unique = true) var produtoCode: UUID = UUID.randomUUID(),
    @Column(nullable = false) var nomeProduto: String = "",
    @Column(nullable = false) @Embedded var unidadeMedida: UnidadeMedida = UnidadeMedida(),
    @Column(nullable = false) var precoUnitario: Double = 0.0,
    @ManyToOne var categoria: Categoria = Categoria(),
    @OneToMany(mappedBy = "produto", cascade = [CascadeType.ALL])
    val carrinho: MutableList<Carrinho> = mutableListOf()
)