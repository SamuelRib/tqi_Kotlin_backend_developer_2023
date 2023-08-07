package me.tqi.controler

import me.tqi.dto.request.CarrinhoDto
import me.tqi.dto.response.CarrinhoView
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

    //Salva o produto selecionado no carrinho vinculado ao id do Usuario
    @PostMapping
    fun saveCarrinho(@RequestBody carrinhoDto: CarrinhoDto): ResponseEntity<String> {
        val saveCarrinho: Carrinho = this.carrinhoService.salvarProdutoNoCarrinho(carrinhoDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto do Id_${carrinhoDto.idProduto} salvo no carrinho!")
    }

    /*
    @GetMapping("/{idCarrinho")
    fun findByIdCarrinho(@PathVariable idCarrinho: Long): ResponseEntity<CarrinhoView>{
        val carrinho: List<Carrinho> = this.carrinhoService.findByIdUsuarioCarrinho(idCarrinho)
        return ResponseEntity.status(HttpStatus.OK).body(CarrinhoView(carrinho))
    }

    @GetMapping("/totalcompraporusuario/{usuarioId}")
    fun obterTotalCompraPorUsuario2(@PathVariable usuarioId: Long):
            ResponseEntity<List<CarrinhoView>> {
        val usuarioTotalCompraView: List<CarrinhoView> =
            this.carrinhoService.findByIdUsuarioCarrinho(usuarioId)
                .stream()
                .map { carrinho: CarrinhoView -> CarrinhoView(carrinho) }
                .collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(usuarioTotalCompraView)
    }
    */

    /*
        @GetMapping("/{idCarrinho")
    fun findByIdCarrinho(@PathVariable idCarrinho: Long): ResponseEntity<CarrinhoView>{
        val carrinho: List<Carrinho> = this.carrinhoService.findByIdUsuarioCarrinho(idCarrinho)
        return ResponseEntity.status(HttpStatus.OK).body(CarrinhoView(carrinho))
    }
     */


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



