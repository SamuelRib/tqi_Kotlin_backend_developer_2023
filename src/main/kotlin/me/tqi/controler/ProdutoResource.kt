package me.tqi.controler

import jakarta.validation.Valid
import me.tqi.dto.request.ProdutoDto
import me.tqi.dto.response.ProdutoViewList
import me.tqi.entity.Produto
import me.tqi.service.impl.ProdutoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/produto")
class ProdutoResource(
    private val produtoService: ProdutoService
) {
    @PostMapping
    fun saveProduto(@RequestBody @Valid produtoDto: ProdutoDto): ResponseEntity<String> {
        val savedProduto: Produto = this.produtoService.save(produtoDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Produto ${savedProduto.nomeProduto} salvo!")
    }

    @GetMapping
    fun findAllByProdutosCategoria(@RequestParam(value = "idCategoria") idCategoria: Long):
            ResponseEntity<List<ProdutoViewList>> {
        val produtoViewList: List<ProdutoViewList> = this.produtoService.findAllByProdutosCategoria(idCategoria)
            .stream()
            .map { produto: Produto -> ProdutoViewList(produto) }
            .collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(produtoViewList)
    }

    //Localiza através do Id do produto, os dados do produto cadastrado pelo adm.
    @GetMapping("/{idProduto}")
    fun findByIdProduto(@PathVariable idProduto: Long): ResponseEntity<ProdutoViewList> {
        val produto: Produto = this.produtoService.findByIdProduto(idProduto)
        return ResponseEntity.status(HttpStatus.OK).body(ProdutoViewList(produto))
    }

    @DeleteMapping("/{idProduto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteProduto(@PathVariable idProduto: Long) {
        this.produtoService.deleteProduto(idProduto)
    }


    //Não acho q tenha necessidade, está desativado, FUNÇÃO: PESQUISA PELO PRODUTOCODE
    @GetMapping("/{produtoCode}")
    fun findByProdutoCode(
        @RequestParam(value = "idProduto") idProduto: Long,
        @PathVariable produtoCode: UUID
    ): ResponseEntity<ProdutoViewList> {
        val produto: Produto = this.produtoService.findByIdProduto(idProduto)
        return ResponseEntity.status(HttpStatus.OK).body(ProdutoViewList(produto))

    }
}