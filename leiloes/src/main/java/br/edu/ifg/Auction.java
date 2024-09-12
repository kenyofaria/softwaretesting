package br.edu.ifg;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Auction {

    private Long id;

    private String name;

    private BigDecimal initialValue;

    private User user;

    private LocalDate openingDate;

    private List<Bid> bids = new ArrayList<>();

    @Deprecated
    public Auction() {
    }

    public Auction(String name) {
        this.name = name;
    }

    public Auction(String name, BigDecimal initialValue, LocalDate openingDate) {
        this.name = name;
        this.initialValue = initialValue;
        this.openingDate = openingDate;
    }


    public Auction(String name, BigDecimal initialValue, User user) {
        this.name = name;
        this.initialValue = initialValue;
        this.user = user;
        this.openingDate = LocalDate.now();
    }

    public Auction(String name, BigDecimal initialValue,
                   LocalDate date, User user) {
        this.name = name;
        this.initialValue = initialValue;
        this.user = user;
        this.openingDate = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setInitialValue(BigDecimal initialValue) {
        this.initialValue = initialValue;
    }

    public BigDecimal getInitialValue() {
        return initialValue;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    /**
     * This method places a bid to an auction
     * @param bid
     * @return boolean
     */
    public void place(Bid bid){
        if(!isValid(bid)) {
            throw new RuntimeException("Bid value needs to be greater than zero");
        }

        if (this.bids.isEmpty() || isValidBid(bid)) {
            addBid(bid);
        }else {
            throw new RuntimeException("Unexpected error");
        }
    }

    private boolean isValid(Bid bid) {
        return bid.getValor().compareTo(BigDecimal.ZERO) > 0;
    }

    private void addBid(Bid bid) {
        bids.add(bid);
    }

    private boolean isValidBid(Bid bid) {
        return bidValueIsHigher(bid, ultimoLanceDado()) &&
                lastUserNotSame(bid);
    }

    private boolean bidValueIsHigher(Bid lance, Bid ultimoLanceDado) {
        return lance.getValor().compareTo(ultimoLanceDado.getValor()) > 0;
    }


    private boolean lastUserNotSame(Bid lance) {
        User ultimoUsuarioQueDeuLance = ultimoLanceDado().getUsuario();
        return !ultimoUsuarioQueDeuLance.equals(lance.getUsuario());
    }

    private Bid ultimoLanceDado() {
        return bids.get(bids.size() - 1);
    }

    public List<Bid> getBids() {
        return Collections.unmodifiableList(bids);
    }
}