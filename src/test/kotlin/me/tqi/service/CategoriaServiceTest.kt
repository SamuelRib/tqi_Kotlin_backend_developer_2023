package me.tqi.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import me.tqi.entity.Categoria
import me.tqi.repository.CategoriaRepository
import me.tqi.service.impl.CategoriaService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.util.*
import kotlin.RuntimeException

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CategoriaServiceTest {
    @MockK
    lateinit var categoriaRepository: CategoriaRepository
    @InjectMockKs
    lateinit var categoriaService: CategoriaService

    //Test da camada de serviço do método de salvar categoria
    @Test
    fun `should create categoria`() {
        //given
        val fakeCategoria: Categoria = builderCategoria()
        every { categoriaRepository.save(any()) } returns fakeCategoria //simula uma fakeCategoria salva no BD
        //when
        val actual: Categoria = categoriaService.save(fakeCategoria)
        //then
        org.assertj.core.api.Assertions.assertThat(actual).isNotNull //verifica se o valor salvo no banco de dados se não é null
        org.assertj.core.api.Assertions.assertThat(actual)
            .isSameAs(fakeCategoria) // verifica se o actual é o mesmo fakeCategoria que havia sido passado
        verify(exactly = 1) { categoriaRepository.save(fakeCategoria) } //verifica se está sendo chamado apenas 1 vez o fakeCategoria
    }

    @Test
    fun `should find categoria by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCategoria: Categoria = builderCategoria(fakeId)
        every { categoriaRepository.findById(fakeId) } returns Optional.of(fakeCategoria)
        //when
        val actual: Categoria = categoriaService.findByIdCategoria(fakeId)
        //then
        org.assertj.core.api.Assertions.assertThat(actual).isNotNull
        org.assertj.core.api.Assertions.assertThat(actual)
            .isExactlyInstanceOf(Categoria::class.java)//verifica se o valor salvo(actual) está retornando na classe certa (Categoria)
        org.assertj.core.api.Assertions.assertThat(actual).isSameAs(fakeCategoria)
        verify(exactly = 1) { categoriaRepository.findById(fakeId) } // verifica se está sendo chamado apenas 1 vez
    }

    @Test
    fun `should not find categoria by invalid id and RuntimeException`() {
        //given
        val fakeId: Long = Random().nextLong()
        every { categoriaRepository.findById(fakeId) } returns Optional.empty() //no teste, o fake id salvo corresponde a um valor vazio
        //then
        org.assertj.core.api.Assertions.assertThatExceptionOfType(RuntimeException::class.java) //é o retorno da Excepetion por ter colocado um faked id Vazio
            .isThrownBy { categoriaService.findByIdCategoria(fakeId) }
            .withMessage("IdCategoria $fakeId não encontrado")//verifica se a mensagem do RuntimeException corresponderá ao programado em CategoriaService
        verify(exactly = 1) { categoriaRepository.findById(fakeId) } // verifica se está sendo chamado apenas 1 vez
    }


    @Test
    fun `should delete categoria by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCategoria: Categoria = builderCategoria(fakeId) //é o id salvo no banco de dados
        every { categoriaRepository.findById(fakeId) } returns Optional.of(fakeCategoria)
        every { categoriaRepository.delete(fakeCategoria) } just runs //como não vai retornar nada, usamos o just runs para indicar        //when
        //when
        categoriaService.deleteCategoria(fakeId)
        //then
       verify(exactly = 1) { categoriaRepository.findById(fakeId) }
       verify(exactly = 1) { categoriaRepository.delete(fakeCategoria) }
    }


}

private fun builderCategoria(
    id: Long = 1L,
    nomeCategoria: String = "Derivados do Leite"
) = Categoria(
    id = id,
    nomeCategoria = nomeCategoria
)
