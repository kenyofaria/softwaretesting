package br.edu.ifg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Realiza operações de persistência de leiloes
 */
public class LeilaoPersistence implements IPersistence{

    private List<Leilao> tabelaDeLeiloes = new ArrayList<>();

    /**
     * ao invés de fazer a persistencia em um banco de dados, faremos a persistencia em memoria
     * @param leilao
     */
    public void insere(Leilao leilao){
        tabelaDeLeiloes.add(leilao);
    }

    public void atualiza(Leilao leilao){
        for (Leilao item : tabelaDeLeiloes) {
           if(item.getId().equals(leilao.getId())){
               tabelaDeLeiloes.add(tabelaDeLeiloes.indexOf(item), leilao);
           }
        }
    }

    public void remove(Long id){
        Iterator<Leilao> iterator = tabelaDeLeiloes.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getId().equals(id)){
                iterator.remove();
            }
        }
    }

    public List<Leilao> listagem(){
        return tabelaDeLeiloes;
    }
}
