package me.tqi.controler

import me.tqi.dto.request.CarrinhoDto
import me.tqi.dto.response.UsuarioTotalCompraView
import me.tqi.entity.Carrinho
import me.tqi.entity.UsuarioTotalCompra
import me.tqi.service.impl.CarrinhoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/carrinho")
class CarrinhoResource(
    private val carrinhoService: CarrinhoService
) {
    @PostMapping
    fun saveCarrinho(@RequestBody carrinhoDto: CarrinhoDto): ResponseEntity<String> {
        val saveCarrinho: Carrinho = this.carrinhoService.salvarProdutoNoCarrinho(carrinhoDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto ${saveCarrinho.produto} salvo no carrinho!")
    }


    //Nesse código estou tentando obter o valor total do usuário, mas não finalizei ainda.
    @GetMapping("/totalcompraporusuario/{usuarioId}")
    fun obterTotalCompraPorUsuario1(@PathVariable usuarioId: Long):
            ResponseEntity<List<UsuarioTotalCompraView>> {
        val usuarioTotalCompraView: List<UsuarioTotalCompraView> =
            this.carrinhoService.obterUsuariosTotalCompra(usuarioId)
                .stream()
                .map { usuarioTotalCompra: UsuarioTotalCompra -> UsuarioTotalCompraView(usuarioTotalCompra) }
                .collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(usuarioTotalCompraView)
    }
}


