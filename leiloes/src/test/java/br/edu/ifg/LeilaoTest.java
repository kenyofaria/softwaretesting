package br.edu.ifg;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeilaoTest {
    @Test
    public void deveRegistrarUmLanceQuandoLeilaoTemUmLance(){
        Leilao leilao = new Leilao("PS 5", BigDecimal.valueOf(1000), LocalDate.now());
        Usuario usuario = new Usuario(1L, "Kenyo", "muito forte", "kenyofaria@gmail.com");
        Lance lance = new Lance(usuario, BigDecimal.valueOf(1001), LocalDateTime.now());
        boolean result = leilao.propoe(lance);
        assertTrue(result);
    }
    @Test
    public void deveRegistrarUmLanceQuandoLeilaoTemMaisDeUmLance(){
        Leilao leilao = new Leilao("PS 5", BigDecimal.valueOf(1000), LocalDate.now());
        Usuario kenyo = new Usuario(1L, "Kenyo", "muito forte", "kenyofaria@gmail.com");
        Usuario aristeu = new Usuario(2L, "Aristeu", "muito forte", "aristeupatrao@gmail.com");
        Lance lanceKenyo = new Lance(kenyo, BigDecimal.valueOf(1001), LocalDateTime.now());
        Lance lanceAristeu = new Lance(aristeu, BigDecimal.valueOf(1002), LocalDateTime.now());
        boolean result = leilao.propoe(lanceKenyo);
        assertTrue(result);
        result = leilao.propoe(lanceAristeu);
        assertTrue(result);
    }
    @Test
    public void naoDeveRegistrarUmLanceQuandoValorForZero(){
        Leilao leilao = new Leilao("PS 5", BigDecimal.valueOf(1000), LocalDate.now());
        Usuario usuario = new Usuario(1L, "Kenyo", "muito forte", "kenyofaria@gmail.com");
        Lance lance = new Lance(usuario, BigDecimal.valueOf(0), LocalDateTime.now());
        boolean result = leilao.propoe(lance);
        assertFalse(result);
    }
    @Test
    public void naoDeveRegistrarUmLanceQuandoUsuarioDuplicado(){
        Leilao leilao = new Leilao("PS 5", BigDecimal.valueOf(1000), LocalDate.now());
        Usuario kenyo = new Usuario(1L, "Kenyo", "muito forte", "kenyofaria@gmail.com");
        Lance lanceKenyo = new Lance(kenyo, BigDecimal.valueOf(1001), LocalDateTime.now());
        Lance lanceAristeu = new Lance(kenyo, BigDecimal.valueOf(1002), LocalDateTime.now());
        boolean result = leilao.propoe(lanceKenyo);
        assertTrue(result);
        result = leilao.propoe(lanceAristeu);
        assertFalse(result);
    }

}
