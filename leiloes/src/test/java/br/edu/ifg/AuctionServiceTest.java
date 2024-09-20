package br.edu.ifg;

import br.edu.ifg.repository.AuctionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


/**
 * This class has a set of unit tests to cover the AuctionService class
 */
public class AuctionServiceTest {

    private final AuctionRepository repositoryMock = mock(AuctionRepository.class);;
    private final AuctionService subject = new AuctionService(repositoryMock);;

    @DisplayName("Creating an auction successfully")
    @Test
    public void shouldCreateAuction() {
        Auction auction = new Auction("PS 5", BigDecimal.valueOf(1000), LocalDate.now());
        assertDoesNotThrow(() -> subject.create(auction));
        verify(repositoryMock, times(1)).save(any(Auction.class));
    }
    @DisplayName("Should not update an auction if there is no any auction")
    @Test
    public void shouldNotUpdateAuctionWhenThereIsNoAnyAuction() {
        when(repositoryMock.list()).thenReturn(null);
        Auction auction = new Auction("PS 5", BigDecimal.valueOf(1000), LocalDate.now());
        subject.update(auction);
        verify(repositoryMock, never()).update(any());
    }

    @ParameterizedTest
    @MethodSource("auctionOracle")
    public void shouldNotCreateToolWhenInvalidProperties(Auction auction, String message){
        RuntimeException ex = assertThrows(RuntimeException.class, () -> subject.create(auction));
        assertTrue(ex.getMessage().contains(message));
    }

    private static Stream<Arguments> auctionOracle() {
        User user = new User(10L, "user name", "user-login", "wrong pass");
        Auction emptyName = new Auction("", BigDecimal.valueOf(1000), LocalDate.now(), user);
        Auction nullName = new Auction(null, BigDecimal.valueOf(1000), LocalDate.now(), user);
        Auction nagativeInitialValue = new Auction("any valid name", BigDecimal.valueOf(-1), LocalDate.now(), user);
        Auction zeroInitialValue = new Auction("any valid name", BigDecimal.ZERO, LocalDate.now(), user);
        Auction noUser = new Auction("any valid name", BigDecimal.valueOf(1000), LocalDate.now(), null);
        return Stream.of(
                Arguments.of(emptyName, "Auction name is mandatory"),
                Arguments.of(nullName, "Auction name is mandatory"),
                Arguments.of(nagativeInitialValue, "Initial value needs to be higher than zero"),
                Arguments.of(zeroInitialValue, "Initial value needs to be higher than zero"),
                Arguments.of(noUser, "User not found")
        );
    }
}
