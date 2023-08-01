package me.tqi.controler


import me.tqi.dto.response.UsuarioTotalCompraView
import me.tqi.entity.UsuarioTotalCompra
import me.tqi.repository.UsuarioTotalCompraRepository
import me.tqi.service.impl.UsuarioTotalCompraService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/carrinhototal")
class UsuarioTotalCompraResource(
    private val usuarioTotalCompraService: UsuarioTotalCompraService
) {
   /* @GetMapping ("/{idUsuario}")
    fun usuarioCompraTotal(@PathVariable IdUsuario: Long): ResponseEntity<UsuarioTotalCompraView>{
        val usuarioTotalCompraView : UsuarioTotalCompra = this.usuarioTotalCompraService.obterTotalUsuario(idUsuario)
        return ResponseEntity.status(HttpStatus.OK).body(usuarioTotalCompraView)
    //return ResponseEntity.status(HttpStatus.OK).body("Carrinho $IdUsuario salvo!")
    }

    */

    @GetMapping("/{idUsuario}")
    fun obterTotalCompraPorUsuario(@PathVariable idUsuario: Long): ResponseEntity<UsuarioTotalCompra> {
        val usuarioTotalCompra = usuarioTotalCompraService.obterTotalUsuario(idUsuario)

        // Verifica se o resultado foi encontrado ou não
        return if (usuarioTotalCompra != null) {
            ResponseEntity.status(HttpStatus.OK).body(usuarioTotalCompra)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}

