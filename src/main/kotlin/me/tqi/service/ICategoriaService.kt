package me.tqi.service

import me.tqi.entity.Categoria


interface ICategoriaService {
    fun save(categoria: Categoria): Categoria
    fun findByIdCategoria(idCategoria: Long): Categoria
    fun deleteCategoria(idCategoria: Long)
}