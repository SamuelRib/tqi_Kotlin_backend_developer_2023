package me.tqi.service.impl

import me.tqi.entity.Categoria
import me.tqi.repository.CategoriaRepository
import me.tqi.service.ICategoriaService
import org.springframework.stereotype.Service


@Service
class CategoriaService(
    private val categoriaRepository: CategoriaRepository //injetamos aqui para termos acesso ao BD
) : ICategoriaService {
    override fun save(categoria: Categoria): Categoria = this.categoriaRepository.save(categoria)


    override fun findByIdCategoria(idCategoria: Long): Categoria {
        return this.categoriaRepository.findById(idCategoria)
            .orElseThrow { throw RuntimeException("IdCategoria $idCategoria não encontrado") }
    }

    override fun deleteCategoria(idCategoria: Long) {
        val categoria: Categoria = this.findByIdCategoria(idCategoria)
        this.categoriaRepository.deleteById(idCategoria)
    }
}