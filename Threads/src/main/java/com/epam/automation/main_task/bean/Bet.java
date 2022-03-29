package com.epam.automation.main_task.bean;

import com.epam.automation.main_task.constant.BetStatus;

public class Bet { // действительно bean

    private long id;
    private int price;
    private Lot lot;
    private Buyer buyer;
    private BetStatus betStatus;

    public Bet(long id, int price, Lot lot, Buyer buyer, BetStatus betStatus) {
        this.id = id;
        this.price = price;
        this.lot = lot;
        this.buyer = buyer;
        this.betStatus = betStatus;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public Lot getLot() {
        return lot;
    }
    public void setLot(Lot lot) {
        this.lot = lot;
    }
    public Buyer getBuyer() {
        return buyer;
    }
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
    public BetStatus getBetStatus() {
        return betStatus;
    }
    public void setBetStatus(BetStatus betStatus) {
        this.betStatus = betStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet bet = (Bet) o;
        if (id != bet.id) return false;
        if (price != bet.price) return false;
        if (!lot.equals(bet.lot)) return false;
        if (!buyer.equals(bet.buyer)) return false;
        return betStatus == bet.betStatus;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + price;
        result = 31 * result + lot.hashCode();
        result = 31 * result + buyer.hashCode();
        result = 31 * result + betStatus.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "id=" + id +
                ", price=" + price +
                ", lot=" + lot +
                ", buyer=" + buyer +
                ", betStatus=" + betStatus +
                '}';
    }

}
