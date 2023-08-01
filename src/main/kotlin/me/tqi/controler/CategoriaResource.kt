package me.tqi.controler

import jakarta.validation.Valid
import me.tqi.dto.request.CategoriaDto
import me.tqi.dto.request.CategoriaUpdateDto
import me.tqi.dto.response.CategoriaView
import me.tqi.entity.Categoria
import me.tqi.service.impl.CategoriaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/categoria")
class CategoriaResource(
    private val categoriaService: CategoriaService
) {
    @PostMapping
    fun saveCategoria(@RequestBody categoriaDto: CategoriaDto): ResponseEntity<String> {
        val savedCategoria = this.categoriaService.save(categoriaDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Categoria ${savedCategoria.nomeCategoria} salva!") //quando salvar a Categoria no banco de dados ele exibirá essa mensagem
    }

    @GetMapping("/{idCategoria}")
    fun findById(@PathVariable idCategoria: Long): ResponseEntity<CategoriaView> {
        val categoria: Categoria = this.categoriaService.findByIdCategoria(idCategoria)
        return ResponseEntity.status(HttpStatus.OK).body(CategoriaView(categoria))
    }

    @DeleteMapping("/{idCategoria}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCategoria(@PathVariable idCategoria: Long) {
        this.categoriaService.deleteCategoria(idCategoria)
    }

    @PatchMapping
    fun updateCategoria(
        @RequestParam(value = "idCategoria") idCategoria: Long,
        @RequestBody @Valid categoriaUpdateDto: CategoriaUpdateDto
    ): ResponseEntity<CategoriaView> {
        val categoria: Categoria = this.categoriaService.findByIdCategoria(idCategoria)
        val categoriaToUpdate: Categoria = categoriaUpdateDto.toEntity(categoria)
        val categoriaUpdated: Categoria = this.categoriaService.save(categoriaToUpdate)
        return ResponseEntity.status(HttpStatus.OK).body(CategoriaView(categoriaUpdated))
    }


}