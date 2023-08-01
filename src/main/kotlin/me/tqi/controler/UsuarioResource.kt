package me.tqi.controler


import me.tqi.dto.request.UsuarioDto
import me.tqi.dto.response.UsuarioView
import me.tqi.entity.Usuario
import me.tqi.service.impl.UsuarioService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/usuario")
class UsuarioResource(
    private val  usuarioService: UsuarioService
) {
    @PostMapping
    fun salvarUsuario(@RequestBody usuarioDto: UsuarioDto ): ResponseEntity<String>{
        val salvarUsuario: Usuario = this.usuarioService.salvarUsuario(usuarioDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario ${salvarUsuario.email} salvo")
    }

    @GetMapping("/{idUsuario}")
    fun findByIdUsuario(@PathVariable idUsuario: Long): ResponseEntity<UsuarioView>{
        val usuario: Usuario = this.usuarioService.findByIdUsuario(idUsuario)
        return ResponseEntity.status(HttpStatus.OK).body(UsuarioView(usuario))
    }

    @DeleteMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUsuario(@PathVariable idUsuario: Long){
        this.usuarioService.deleteUsuario(idUsuario)
    }
}