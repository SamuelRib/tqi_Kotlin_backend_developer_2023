package me.tqi.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
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