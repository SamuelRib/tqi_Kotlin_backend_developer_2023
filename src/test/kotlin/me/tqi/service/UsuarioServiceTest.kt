package me.tqi.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import me.tqi.entity.Usuario
import me.tqi.repository.UsuarioRepository
import me.tqi.service.impl.UsuarioService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class UsuarioServiceTest {
    @MockK
    lateinit var usuarioRepository: UsuarioRepository

    @InjectMockKs
    lateinit var usuarioService: UsuarioService

    //Test da camada de serviço do método de salvar usuario
    @Test
    fun `should create usuario`() {
        //given
        val fakeUsuario: Usuario = builderUsuario()
        every { usuarioRepository.save(fakeUsuario) } returns fakeUsuario
        //when
        val actual: Usuario = usuarioService.salvarUsuario(fakeUsuario)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeUsuario)
        verify(exactly = 1) { usuarioRepository.save(fakeUsuario) }
    }

    @Test
    fun `should find usuario by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeUsuario: Usuario = builderUsuario(fakeId)
        every { usuarioRepository.findById(fakeId) } returns Optional.of(fakeUsuario)
        //when
        val actual: Usuario = usuarioService.findByIdUsuario(fakeId)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Usuario::class.java)
        Assertions.assertThat(actual).isSameAs(fakeUsuario)
        verify(exactly = 1) { usuarioRepository.findById(fakeId) }

    }

    @Test
    fun `should not find usuario by invalid id and RuntimeException`() {
        //given
        val fakeId: Long = Random().nextLong()
        every { usuarioRepository.findById(fakeId) } returns Optional.empty()
        //then
        Assertions.assertThatExceptionOfType(RuntimeException::class.java) //é o retorno da Excepetion por ter colocado um faked id Vazio
            .isThrownBy { usuarioService.findByIdUsuario(fakeId) }
            .withMessage("IdUsuario $fakeId não encontrado")//verifica se a mensagem do RuntimeException corresponderá ao programado em UsuarioService
        verify(exactly = 1) { usuarioRepository.findById(fakeId) } // verifica se está sendo chamado apenas 1 vez
    }

    @Test
    fun `should delete usuario by id`() { //testa o método de exclusão do usuario do banco de dados.
        //given
        val fakeId: Long = Random().nextLong()
        val fakeUsuario: Usuario = builderUsuario(fakeId) //é o id salvo no banco de dados
        every { usuarioRepository.findById(fakeId) } returns Optional.of(fakeUsuario)
        every { usuarioRepository.delete(fakeUsuario) } just runs //como não vai retornar nada, usamos o just runs para indicar
        //when
        usuarioService.deleteUsuario(fakeId)
        //then
        verify(exactly = 1) { usuarioRepository.findById(fakeId) }
        verify(exactly = 1) { usuarioRepository.delete(fakeUsuario) }
    }


    private fun builderUsuario(
        id: Long = 1,
        nome: String = "Lucas Henrique",
        cpf: String = "12345",
        email: String = "lucash@gmail.com",
        endereco: String = "rua dos marqueses, 88"
    ) = Usuario(
        id = id,
        nome = nome,
        cpf = cpf,
        email = email,
        endereco = endereco
    )
}



//given -> são os dados que vamos receber
//when -> é o método que vamos testar
//then -> verificamos se há o retorno esperado