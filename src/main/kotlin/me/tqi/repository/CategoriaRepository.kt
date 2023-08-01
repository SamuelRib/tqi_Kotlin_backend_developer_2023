package me.tqi.repository

import me.tqi.entity.Categoria
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
// O tipo q vai se referenciar ao BD é a classe Categoria, e primaryKey é tipo long.
// Com isso temos um ponto de acesso ao BD.
//É a camada repository que se conecta ao nosso BD.
interface CategoriaRepository: JpaRepository<Categoria,Long> {
}