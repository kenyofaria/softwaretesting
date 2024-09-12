package br.edu.ifg.repository;

import br.edu.ifg.Auction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class performs auction persistence operations.
 * As the goal of the project is teaching undergraduate students to automate tests.
 * So, there were no concerns with some aspects related to the O.O
 */
public class AuctionRepository {

    private List<Auction> inMemoryTableFromAuctions = new ArrayList<>();

    /**
     * Instead of make the persistence in a database we are simulating an in memory database.
     * @param auction
     */
    public void save(Auction auction){
        inMemoryTableFromAuctions.add(auction);
    }

    public void update(Auction auction){
        for (Auction item : inMemoryTableFromAuctions) {
           if(item.getId().equals(auction.getId())){
               inMemoryTableFromAuctions.add(inMemoryTableFromAuctions.indexOf(item), auction);
           }
        }
    }

    public void remove(Long id){
        Iterator<Auction> iterator = inMemoryTableFromAuctions.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getId().equals(id)){
                iterator.remove();
            }
        }
    }

    public List<Auction> list(){
        return inMemoryTableFromAuctions;
    }
}
