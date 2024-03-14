package br.edu.ifg;

import java.util.ArrayList;
import java.util.List;

/**
 * útil somente quando nao estamos usando mocks
 */

public class LeilaoPersistenceFake implements IPersistence{


    /**
     * ao invés de fazer a persistencia em um banco de dados, faremos a persistencia em memoria
     * @param leilao
     */
    public void insere(Leilao leilao){
    }

    public void atualiza(Leilao leilao){
    }

    public void remove(Long id){

    }

    public List<Leilao> listagem(){
        return new ArrayList<Leilao>();
    }
}
