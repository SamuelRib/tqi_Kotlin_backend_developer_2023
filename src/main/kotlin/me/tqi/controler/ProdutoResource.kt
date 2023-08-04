package me.tqi.controler

import jakarta.validation.Valid
import me.tqi.dto.request.ProdutoDto
import me.tqi.dto.request.ProdutoUpdateDto
import me.tqi.dto.response.ProdutoViewList
import me.tqi.entity.Produto
import me.tqi.service.impl.ProdutoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/produto")
class ProdutoResource(
    private val produtoService: ProdutoService
) {
    //Salva o produto no sistema
    @PostMapping
    fun saveProduto(@RequestBody @Valid produtoDto: ProdutoDto): ResponseEntity<String> {
        val savedProduto: Produto = this.produtoService.save(produtoDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Produto ${produtoDto.nomeProduto} salvo!")
    }

    //Localiza os dados do produto cadastrado pelo adm, através do Id do produto.
    @GetMapping("/id/{idProduto}")
    fun findByIdProduto(@PathVariable idProduto: Long): ResponseEntity<ProdutoViewList> {
        val produto: Produto = this.produtoService.findByIdProduto(idProduto)
        return ResponseEntity.status(HttpStatus.OK).body(ProdutoViewList(produto))
    }

    //Busca todos os produtos cadastrados de acordo com o Id da categoria salva.
    @GetMapping
    fun findAllByProdutosCategoria(@RequestParam(value = "idCategoria") idCategoria: Long):
            ResponseEntity<List<ProdutoViewList>> {
        val produtoViewList: List<ProdutoViewList> = this.produtoService.findAllByProdutosCategoria(idCategoria)
            .stream()
            .map { produto: Produto -> ProdutoViewList(produto) }
            .collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(produtoViewList)
    }

    //Função: pesquisa pelo produto code
    @GetMapping("/produtocode/{produtoCode}")
    fun findByProdutoCode(
        @RequestParam(value = "idProduto") idProduto: Long,
        @PathVariable produtoCode: UUID
    ): ResponseEntity<ProdutoViewList> {
        val produto: Produto = this.produtoService.findByIdProduto(idProduto)
        return ResponseEntity.status(HttpStatus.OK).body(ProdutoViewList(produto))
    }

    //Deleta o produto cadastrado a partir do id do produto selecionado
    @DeleteMapping("/{idProduto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteProduto(@PathVariable idProduto: Long) {
        this.produtoService.deleteProduto(idProduto)
    }

    //Realiza edição dos campos do produto que já foram cadastrados
    @PatchMapping
    fun updateProduto(
        @RequestParam(value = "idProduto") idProduto: Long,
        @RequestBody @Valid produtoUpdateDto: ProdutoUpdateDto
    ): ResponseEntity<ProdutoViewList> {
        val produto: Produto = this.produtoService.findByIdProduto(idProduto)
        val produtoToUpdate: Produto = produtoUpdateDto.toEntity(produto)
        val produtoUpdated: Produto = this.produtoService.save(produtoToUpdate)
        return ResponseEntity.status(HttpStatus.OK).body(ProdutoViewList(produtoUpdated))
    }


}