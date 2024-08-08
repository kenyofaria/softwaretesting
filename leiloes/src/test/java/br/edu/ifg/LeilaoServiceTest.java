package br.edu.ifg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.stream.Stream;

/**
 * Esta classe possui um conjunto de casos de teste que cobrem os métodos em LeilaoService.
 */
public class LeilaoServiceTest {

    @ParameterizedTest
    @MethodSource("oraculoCamposInvalidos")
    public void naoDeveCadastrarLeilao(Leilao leilao, String message){
        LeilaoPersistence leilaoPersistence = Mockito.mock(LeilaoPersistence.class);
        LeilaoService leilaoService = new LeilaoService(leilaoPersistence);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            leilaoService.cadastraNovoLeilao(leilao);
        });

        Assertions.assertEquals(message, exception.getMessage());
    }

    public static Stream<Arguments> oraculoCamposInvalidos(){
        Usuario usuario = new Usuario();
        Leilao nomeVazio = new Leilao("", BigDecimal.valueOf(100), usuario);
        Leilao nomeNull = new Leilao(null, BigDecimal.valueOf(100), usuario);
        Leilao valorInicialZero = new Leilao("something", BigDecimal.ZERO, usuario);
        Leilao valorInicialNull = new Leilao("something", null, usuario);

        return Stream.of(
                Arguments.of(nomeVazio, "É necessário informar o nome do leilão"),
                Arguments.of(nomeNull, "É necessário informar o nome do leilão"),
                Arguments.of(valorInicialZero, "É necessário ter um valor maior que zero para o leilão"),
                Arguments.of(valorInicialNull, "É necessário ter um valor maior que zero para o leilão"),
                Arguments.of(valorInicialNull, "É necessário ter um valor maior que zero para o leilão")
        );
    }

}
