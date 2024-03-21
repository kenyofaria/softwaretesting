package br.edu.ifg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PagamentoCieloSetupDiferenteIT {


    @Test
    public void deveRealizarPagamentoComCielo_QuandoPagamentoAVistaCredito(){
        System.out.println("---------------------------------------------------");
        System.out.println("colocando coisas no carrinho do jeito A");
        System.out.println("login ok");
        System.out.println("efetuando a interacao com a cielo - a vista no credito"); //target
        System.out.println("remove itens do carrinho da maneira A");
        System.out.println("efetua logout");
        System.out.println("---------------------------------------------------");
        assertTrue(true);
    }
    @Test
    public void deveRealizarPagamentoComCielo_QuandoPagamentoAPrazo(){
        System.out.println("---------------------------------------------------");
        System.out.println("colocando coisas no carrinho do jeito B");
        System.out.println("login ok");
        System.out.println("efetuando a interacao com a cielo - a prazo"); //target
        System.out.println("remove itens do carrinho da maneira B");
        System.out.println("efetua logout");
        System.out.println("---------------------------------------------------");
        assertTrue(true);
    }
    @Test
    public void deveRealizarPagamentoComCielo_QuandoPagamentoAViataDebito(){
        System.out.println("---------------------------------------------------");
        System.out.println("colocando coisas no carrinho do jeito C");
        System.out.println("login ok");
        System.out.println("efetuando a interacao com a cielo - a vista no debito"); //target
        System.out.println("remove itens do carrinho da maneira C");
        System.out.println("efetua logout");
        System.out.println("---------------------------------------------------");
        assertTrue(true);
    }

}
