package br.edu.ifg;

import br.edu.ifg.repository.AuctionRepository;
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
public class AuctionServiceTest {

    @ParameterizedTest
    @MethodSource("oraculoCamposInvalidos")
    public void naoDeveCadastrarLeilao(Auction leilao, String message){
        AuctionRepository leilaoPersistence = Mockito.mock(AuctionRepository.class);
        AuctionService leilaoService = new AuctionService(leilaoPersistence);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            leilaoService.create(leilao);
        });

        Assertions.assertEquals(message, exception.getMessage());
    }

    public static Stream<Arguments> oraculoCamposInvalidos(){
        User usuario = new User();
        Auction nomeVazio = new Auction("", BigDecimal.valueOf(100), usuario);
        Auction nomeNull = new Auction(null, BigDecimal.valueOf(100), usuario);
        Auction valorInicialZero = new Auction("something", BigDecimal.ZERO, usuario);
        Auction valorInicialNull = new Auction("something", null, usuario);

        return Stream.of(
                Arguments.of(nomeVazio, "Auction name is mandatory"),
                Arguments.of(nomeNull, "Auction name is mandatory"),
                Arguments.of(valorInicialZero, "Initial value needs to be higher than zero"),
                Arguments.of(valorInicialNull, "Initial value needs to be higher than zero"),
                Arguments.of(valorInicialNull, "Initial value needs to be higher than zero")
        );
    }

}
