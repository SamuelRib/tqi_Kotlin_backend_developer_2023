package me.tqi.service

import me.tqi.entity.Categoria
import me.tqi.entity.Produto
import java.util.UUID

interface ICategoriaService {
    fun save(categoria: Categoria): Categoria
    fun findByIdCategoria(idCategoria: Long): Categoria
    fun deleteCategoria(idCategoria: Long)
}