package br.edu.ifg;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AuctionTest {
    @Test
    public void shouldBidAtAuction() {
        Auction auction = new Auction("PS 5", BigDecimal.valueOf(1000), LocalDate.now());
        User user = new User(1L, "Kenyo", "kenyofaria@gmail.com", "very strong");
        Bid lance = new Bid(user, BigDecimal.valueOf(1001), LocalDateTime.now());
        assertDoesNotThrow(() -> auction.place(lance));
    }
    @Test
    public void shouldBidWhenAuctionHasMoreThanOneBid(){
        Auction auction = new Auction("PS 5", BigDecimal.valueOf(1000), LocalDate.now());
        User kenyo = new User(1L, "Kenyo", "kenyofaria@gmail.com", "strong");
        User aristeu = new User(2L, "Aristeu", "aristeupatrao@gmail.com", "strong");
        Bid kenyoBid = new Bid(kenyo, BigDecimal.valueOf(1001), LocalDateTime.now());
        Bid aristeuBid = new Bid(aristeu, BigDecimal.valueOf(1002), LocalDateTime.now());
        assertDoesNotThrow(() -> auction.place(kenyoBid));
        assertDoesNotThrow(() -> auction.place(aristeuBid));
    }
    @Test
    public void shouldNotBidWhenInvalidValue(){
        Auction auction = new Auction("PS 5", BigDecimal.valueOf(1000), LocalDate.now());
        User user = new User(1L, "Kenyo", "kenyofaria@gmail.com", "strong");
        Bid bid = new Bid(user, BigDecimal.ZERO, LocalDateTime.now());
        RuntimeException ex = assertThrows(RuntimeException.class, () -> auction.place(bid));
        assertTrue(ex.getMessage().contains("Bid value needs to be greater than zero"));
    }

}
