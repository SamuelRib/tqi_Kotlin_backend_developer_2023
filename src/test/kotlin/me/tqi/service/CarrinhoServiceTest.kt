package me.tqi.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import me.tqi.entity.Carrinho
import me.tqi.entity.Produto
import me.tqi.entity.Usuario
import me.tqi.repository.CarrinhoRepository
import me.tqi.service.impl.CarrinhoService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CarrinhoServiceTest {
    @MockK
    lateinit var carrinhoRepository: CarrinhoRepository

    @InjectMockKs
    lateinit var carrinhoService: CarrinhoService

    @Test
    fun `should create carrinho`() {
        //given
        val fakeCarrinho: Carrinho = builderCarrinho()
        every { carrinhoRepository.save(fakeCarrinho) } returns fakeCarrinho
        //when
        val actual: Carrinho = carrinhoService.salvarProdutoNoCarrinho(fakeCarrinho)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCarrinho)
        verify(exactly = 1) { carrinhoRepository.save(fakeCarrinho) }

    }

    @Test
    fun `should find carrinho by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCarrinho: Carrinho = builderCarrinho(fakeId)
        every { carrinhoRepository.findById(fakeId) } returns Optional.of(fakeCarrinho)
        //when
        val actual: Carrinho = carrinhoService.findByIdCarrinho(fakeId)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Carrinho::class.java)
        Assertions.assertThat(actual).isSameAs(fakeCarrinho)
        verify(exactly = 1) { carrinhoRepository.findById(fakeId) }
    }

    @Test
    fun `should not find carrinho by invalid id and RuntimeException`() {
        //given
        val fakeId: Long = Random().nextLong()
        every { carrinhoRepository.findById(fakeId) } returns Optional.empty()
        //then
        Assertions.assertThatExceptionOfType(RuntimeException::class.java)
            .isThrownBy { carrinhoService.findByIdCarrinho(fakeId) }
            .withMessage("idCarrinho $fakeId not found")
        verify(exactly = 1) { carrinhoRepository.findById(fakeId) } // verifica se está sendo chamado apenas 1 vez
    }

    @Test
    fun `should delete carrinho by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCarrinho: Carrinho = builderCarrinho(fakeId) //é o id salvo no banco de dados
        every { carrinhoRepository.findById(fakeId) } returns Optional.of(fakeCarrinho)
        every { carrinhoRepository.delete(fakeCarrinho) } just runs //como não vai retornar nada, usamos o just runs para indicar
        //when
        carrinhoService.deleteCarrinho(fakeId)
        //then
        verify(exactly = 1) { carrinhoRepository.findById(fakeId) }
        verify(exactly = 1) { carrinhoRepository.delete(fakeCarrinho) }
    }
}

private fun builderCarrinho(
    idCarrinho: Long = 1,
    idUsuario: Long = 1,
    idProduto: Long = 1,
    quantidade: Int = 1

) = Carrinho(
    id = idCarrinho,
    usuario = Usuario(
        id = idUsuario,
    ),
    produto = Produto(
        idProduto = idProduto
    ),
    quantidade = quantidade,
)