package br.edu.ifg;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AuctionTest {
    @Test
    public void deveRegistrarUmLanceQuandoLeilaoTemUmLance(){
        Auction leilao = new Auction("PS 5", BigDecimal.valueOf(1000), LocalDate.now());
        User usuario = new User(1L, "Kenyo", "muito forte", "kenyofaria@gmail.com");
        Bid lance = new Bid(usuario, BigDecimal.valueOf(1001), LocalDateTime.now());
        assertDoesNotThrow(() -> leilao.place(lance));
    }
    @Test
    public void deveRegistrarUmLanceQuandoLeilaoTemMaisDeUmLance(){
        Auction leilao = new Auction("PS 5", BigDecimal.valueOf(1000), LocalDate.now());
        User kenyo = new User(1L, "Kenyo", "muito forte", "kenyofaria@gmail.com");
        User aristeu = new User(2L, "Aristeu", "muito forte", "aristeupatrao@gmail.com");
        Bid lanceKenyo = new Bid(kenyo, BigDecimal.valueOf(1001), LocalDateTime.now());
        Bid lanceAristeu = new Bid(aristeu, BigDecimal.valueOf(1002), LocalDateTime.now());
        assertDoesNotThrow(() -> leilao.place(lanceKenyo));
        assertDoesNotThrow(() -> leilao.place(lanceAristeu));
    }
    @Test
    public void naoDeveRegistrarUmLanceQuandoValorForZero(){
        Auction leilao = new Auction("PS 5", BigDecimal.valueOf(1000), LocalDate.now());
        User usuario = new User(1L, "Kenyo", "muito forte", "kenyofaria@gmail.com");
        Bid lance = new Bid(usuario, BigDecimal.valueOf(0), LocalDateTime.now());
        RuntimeException ex = assertThrows(RuntimeException.class, () -> leilao.place(lance));
        assertTrue(ex.getMessage().contains("Bid value needs to be greater than zero"));
    }

}
