package br.edu.ifg;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PagamentoCieloSetupComumBeforeEachAfterEachIT {

    @BeforeEach
    public void initEach(){
        System.out.println("colocando coisas no carrinho");
        System.out.println("login ok");
    }

    @Test
    public void deveRealizarPagamentoComCielo_QuandoPagamentoAVistaCredito(){
        System.out.println("---------------------------------------------------");
        System.out.println("efetuando a interacao com a cielo - a vista no credito"); //target
        System.out.println("---------------------------------------------------");
        assertTrue(true);
    }
    @Test
    public void deveRealizarPagamentoComCielo_QuandoPagamentoAPrazo(){
        System.out.println("---------------------------------------------------");
        System.out.println("efetuando a interacao com a cielo - a prazo"); //target
        System.out.println("---------------------------------------------------");
        assertTrue(true);
    }
    @Test
    public void deveRealizarPagamentoComCielo_QuandoPagamentoAViataDebito(){
        System.out.println("---------------------------------------------------");
        System.out.println("efetuando a interacao com a cielo - a vista no debito"); //target
        System.out.println("---------------------------------------------------");
        assertTrue(true);
    }

    @AfterEach
    public void afterAll(){
        System.out.println("remove itens do carrinho");
        System.out.println("efetua logout");
    }
}
