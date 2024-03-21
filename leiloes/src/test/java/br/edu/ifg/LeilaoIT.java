package br.edu.ifg;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LeilaoIT {

    @Test
    public void deveRegistrarUmLanceQuandoLeilaoTemUmLance_PropoeVoid(){
        Leilao leilao = new Leilao("PS 5", BigDecimal.valueOf(1000), LocalDate.now());
        Usuario usuario = new Usuario(1L, "Kenyo", "muito forte", "kenyofaria@gmail.com");
        Lance lance = new Lance(usuario, BigDecimal.valueOf(1001), LocalDateTime.now());
        assertDoesNotThrow(()->leilao.propoeVoid(lance));
    }
    @Test
    public void deveRegistrarUmLanceQuandoLeilaoTemMaisDeUmLance_PropoeVoid(){
        Leilao leilao = new Leilao("PS 5", BigDecimal.valueOf(1000), LocalDate.now());
        Usuario kenyo = new Usuario(1L, "Kenyo", "muito forte", "kenyofaria@gmail.com");
        Usuario aristeu = new Usuario(2L, "Aristeu", "muito forte", "aristeupatrao@gmail.com");
        Lance lanceKenyo = new Lance(kenyo, BigDecimal.valueOf(1001), LocalDateTime.now());
        Lance lanceAristeu = new Lance(aristeu, BigDecimal.valueOf(1002), LocalDateTime.now());
        leilao.propoeVoid(lanceKenyo);
        assertDoesNotThrow(()->leilao.propoeVoid(lanceAristeu));
    }
    @Test
    public void naoDeveRegistrarUmLanceQuandoValorForZero_PropoeVoid(){
        Leilao leilao = new Leilao("PS 5", BigDecimal.valueOf(1000), LocalDate.now());
        Usuario usuario = new Usuario(1L, "Kenyo", "muito forte", "kenyofaria@gmail.com");
        Lance lance = new Lance(usuario, BigDecimal.valueOf(0), LocalDateTime.now());
        assertThrows(RuntimeException.class,
                ()->leilao.propoeVoid(lance));
    }
    @Test
    public void naoDeveRegistrarUmLanceQuandoUsuarioDuplicado_PropoeVoid(){
        Leilao leilao = new Leilao("PS 5", BigDecimal.valueOf(1000), LocalDate.now());
        Usuario kenyo = new Usuario(1L, "Kenyo", "muito forte", "kenyofaria@gmail.com");
        Lance lanceKenyo = new Lance(kenyo, BigDecimal.valueOf(1001), LocalDateTime.now());
        Lance lanceAristeu = new Lance(kenyo, BigDecimal.valueOf(1002), LocalDateTime.now());
        leilao.propoeVoid(lanceKenyo);
        assertThrows(RuntimeException.class,
                ()->leilao.propoeVoid(lanceAristeu));
    }

    //------------------------ aprendendo sobre mocks -------------------------------------------

    @Test
    public void deveRegistrarUmLeilaoNoBancoDeDados(){
        LeilaoPersistence mock = Mockito.mock(LeilaoPersistence.class);
        LeilaoService subject = new LeilaoService(mock);
        Leilao leilao = new Leilao("PS 5", BigDecimal.valueOf(1000), LocalDate.now());
        subject.cadatraNovoLeilao(leilao);
    }

    @Test
    public void naoDeveRegistrarUmLeilao_Quando_NomeVazio(){
        LeilaoPersistence mock = Mockito.mock(LeilaoPersistence.class);
        LeilaoService subject = new LeilaoService(mock);
        Leilao leilao = new Leilao("", BigDecimal.valueOf(1000), LocalDate.now());

        assertThrows(RuntimeException.class,
                ()->subject.cadatraNovoLeilao(leilao));
    }

    @Test
    public void naoDeveRegistrarUmLeilao_Quando_NomeNull(){
        LeilaoPersistence mock = Mockito.mock(LeilaoPersistence.class);
        LeilaoService subject = new LeilaoService(mock);
        Leilao leilao = new Leilao(null, BigDecimal.valueOf(1000), LocalDate.now());

        assertThrows(RuntimeException.class,
                ()->subject.cadatraNovoLeilao(leilao));
    }
}
