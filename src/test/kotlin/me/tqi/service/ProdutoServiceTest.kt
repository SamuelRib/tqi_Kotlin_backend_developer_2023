package me.tqi.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import me.tqi.entity.Categoria
import me.tqi.entity.Produto
import me.tqi.repository.ProdutoRepository
import me.tqi.service.impl.ProdutoService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class ProdutoServiceTest {
    @MockK
    lateinit var produtoRepository: ProdutoRepository

    @InjectMockKs
    lateinit var produtoService: ProdutoService

    @Test
    fun `should create Produto`() {
        //given
        val fakeProduto: Produto = builderProduto()
        every { produtoRepository.save(fakeProduto) } returns fakeProduto
        //when
        val actual: Produto = produtoService.save(fakeProduto)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeProduto)
        verify(exactly = 1) { produtoRepository.save(fakeProduto) }
    }

    @Test
    fun `should find produto by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeProduto: Produto = builderProduto(fakeId)
        every { produtoRepository.findById(fakeId) } returns Optional.of(fakeProduto)
        //when
        val actual: Produto = produtoService.findByIdProduto(fakeId)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Produto::class.java)
        Assertions.assertThat(actual).isSameAs(fakeProduto)
        verify(exactly = 1) { produtoRepository.findById(fakeId) }

    }


    @Test
    fun `should not find produnto by invalid id and RuntimeException`() {
        //given
        val fakeId: Long = Random().nextLong()
        every { produtoRepository.findById(fakeId) } returns Optional.empty()
        //then
        Assertions.assertThatExceptionOfType(RuntimeException::class.java)
            .isThrownBy { produtoService.findByIdProduto(fakeId) }
            .withMessage("IdProduto $fakeId not found")
        verify(exactly = 1) { produtoRepository.findById(fakeId) } // verifica se está sendo chamado apenas 1 vez
    }


    @Test
    fun `should delete produto by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeProduto: Produto = builderProduto(fakeId) //é o id salvo no banco de dados
        every { produtoRepository.findById(fakeId) } returns Optional.of(fakeProduto)
        every { produtoRepository.delete(fakeProduto) } just runs //como não vai retornar nada, usamos o just runs para indicar
        //when
        produtoService.deleteProduto(fakeId)
        //then
        verify(exactly = 1) { produtoRepository.findById(fakeId) }
        verify(exactly = 1) { produtoRepository.delete(fakeProduto) }
    }


}

private fun builderProduto(
    idProduto: Long = 1,
    nomeProduto: String = "Leite Integral",
    precoUnitario: Double = 12.0,
    unidadeMedida: String = "Litro",
    idCategoria: Long = 1
) = Produto(
    idProduto = idProduto,
    nomeProduto = nomeProduto,
    precoUnitario = precoUnitario,
    unidadeMedida = unidadeMedida,
    categoria = Categoria(
        id = idCategoria
    )
)