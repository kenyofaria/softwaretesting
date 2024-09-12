package br.edu.ifg;

import br.edu.ifg.repository.AuctionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class AuctionIT {

    @Test
    public void deveRegistrarUmLeilaoNoBancoDeDados(){
        AuctionRepository mock = Mockito.mock(AuctionRepository.class);
        AuctionService subject = new AuctionService(mock);
        Auction leilao = new Auction("PS 5", BigDecimal.valueOf(1000), LocalDate.now());
        subject.create(leilao);
    }
    @Test
    public void naoDeveAtualizarUmLeilao_Quando_NaoExistemLeiloes(){
        AuctionRepository mock = Mockito.mock(AuctionRepository.class);
        when(mock.list()).thenReturn(null);
        AuctionService subject = new AuctionService(mock);
        Auction leilao = new Auction("PS 5", BigDecimal.valueOf(1000), LocalDate.now());
        subject.update(leilao);
        verify(mock, never()).update(any());
    }

    @Test
    public void naoDeveAtualizarUmLeilao_Quando_NaoExistemLeiloesXYZ(){
        AuctionRepository mock = Mockito.mock(AuctionRepository.class);
        when(mock.list()).thenReturn(Collections.emptyList());
        AuctionService subject = new AuctionService(mock);
        Auction leilao = new Auction("PS 5", BigDecimal.valueOf(1000), LocalDate.now());
        subject.update(leilao);
        verify(mock, never()).update(any());
    }

    @Test
    public void naoDeveRegistrarUmLeilao_Quando_NomeVazio(){
        AuctionRepository mock = Mockito.mock(AuctionRepository.class);
        AuctionService subject = new AuctionService(mock);
        Auction leilao = new Auction("", BigDecimal.valueOf(1000), LocalDate.now());

        assertThrows(RuntimeException.class,
                ()->subject.create(leilao));
    }

    @Test
    public void naoDeveRegistrarUmLeilao_Quando_NomeNull(){
        AuctionRepository mock = Mockito.mock(AuctionRepository.class);
        AuctionService subject = new AuctionService(mock);
        Auction leilao = new Auction(null, BigDecimal.valueOf(1000), LocalDate.now());

        assertThrows(RuntimeException.class,
                ()->subject.create(leilao));
    }
}
