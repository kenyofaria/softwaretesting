package br.edu.ifg;

import java.util.Iterator;
import java.util.List;

public interface IPersistence {

    void insere(Leilao leilao);

    void atualiza(Leilao leilao);

    void remove(Long id);

    List<Leilao> listagem();

}
