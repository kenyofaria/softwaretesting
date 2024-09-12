package br.edu.ifg;

import br.edu.ifg.repository.AuctionRepository;

import java.math.BigDecimal;

/**
 * As the goal of the project is teaching undergraduate students to automate tests.
 * So, there were no concerns with some aspects related to the O.O
 */
public class AuctionService {

    private final AuctionRepository persistence;

    public AuctionService(AuctionRepository persistence) {
        this.persistence = persistence;
    }

    public void create(Auction auction){
        if(auction.getName() == null || auction.getName().isEmpty()){
            throw new RuntimeException("Auction name is mandatory");
        }
        if(auction.getInitialValue() == null || auction.getInitialValue().equals(BigDecimal.ZERO) || auction.getInitialValue().compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("Initial value needs to be higher than zero");
        }
        persistence.save(auction);
    }

    public void update(Auction leilao){
        if(this.persistence.list() == null || this.persistence.list().isEmpty()){
            return;
        }
        persistence.update(leilao);
    }


}
