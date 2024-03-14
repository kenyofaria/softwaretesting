package br.edu.ifg;

import java.math.BigDecimal;

public class LeilaoService {

    private final LeilaoPersistence persistence;

    public LeilaoService(LeilaoPersistence persistence) {
        this.persistence = persistence;
    }

    public void cadatraNovoLeilao(Leilao leilao){
        if(leilao.getNome() == null || leilao.getNome().isEmpty()){
            throw new RuntimeException("É necessário informar o nome do leilão");
        }
        if(leilao.getValorInicial() == null || leilao.getValorInicial().equals(BigDecimal.ZERO) || leilao.getValorInicial().compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("É necessário ter um valor maior que zero para o leilão");
        }
        persistence.insere(leilao);
    }


}
