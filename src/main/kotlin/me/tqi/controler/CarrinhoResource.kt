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

    //Localiza um dos itens colocados no carrinho
    @GetMapping("/id/{idCarrinho}")
    fun findByIdCarrinho(@PathVariable idCarrinho: Long): ResponseEntity<CarrinhoView> {
        val carrinho: Carrinho =  this.carrinhoService.findByIdCarrinho(idCarrinho)
        return ResponseEntity.status(HttpStatus.OK).body(CarrinhoView(carrinho))
    }



    //Localiza todos os produtos adicionados no carrinho de compra de acordo com o idUsuario selecionado.
    @GetMapping("/usuario/{idUsuario}")
    fun findByIdCarrinhoUsuario(@PathVariable idUsuario: Long):
            ResponseEntity<List<CarrinhoView>> {
        val carrinhoView: List<CarrinhoView> = this.carrinhoService.findByIdUsuarioCarrinho(idUsuario)
            .stream()
            .map { carrinho: Carrinho -> CarrinhoView(carrinho) }
            .collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(carrinhoView)
    }


    //Nesse código estou tentando obter o valor total do usuário, mas não finalizei ainda.
    @GetMapping("/totalcompraporusuario/{usuarioId}")
    fun obterTotalCompraPorUsuario1(@PathVariable idUsuario: Long):
            ResponseEntity<UsuarioTotalCompraView> {
        val usuarioTotalCompra: UsuarioTotalCompra =
            this.carrinhoService.obterUsuariosTotalCompra(idUsuario)
        return ResponseEntity.status(HttpStatus.OK).body(UsuarioTotalCompraView(usuarioTotalCompra))
    }

    //Deleta o carrinho
    @DeleteMapping("/{idCarrinho}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCarrinho(@PathVariable idCarrinho: Long) {
        this.carrinhoService.deleteCarrinho(idCarrinho)
    }

}



