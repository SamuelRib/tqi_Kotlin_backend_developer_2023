package me.tqi.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import me.tqi.entity.Categoria
import me.tqi.repository.CategoriaRepository
import me.tqi.service.impl.CategoriaService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CategoriaServiceTest {
    @MockK lateinit var categoriaRepository: CategoriaRepository
    @InjectMockKs lateinit var categoriaService: CategoriaService

    //Test da camada de serviço do método de salvar categoria
    @Test
    fun `should create categoria`(){
        //given
        val fakeCategoria: Categoria = builderCategoria()
        every { categoriaRepository.save(any()) } returns fakeCategoria //estou simulando que há um fakeCategoria salvo no meu BD
        //when
        val actual: Categoria = categoriaService.save(fakeCategoria)
        //then
        org.assertj.core.api.Assertions.assertThat(actual).isNotNull //verifica se o valor salvo não é nulo
        org.assertj.core.api.Assertions.assertThat(actual).isSameAs(fakeCategoria) // verifica se o actual é o mesmo fakeCategoria que havia sido passado
        verify(exactly = 1){ categoriaRepository.save(fakeCategoria)} //verifica se está sendo chamado apenas 1 vez o fakeCategoria
    }

}

private fun builderCategoria(
    nomeCategoria: String = "Derivados do Leite"
) = Categoria(
    nomeCategoria = nomeCategoria
)