package com.epam.automation.main_task.bean;

import com.epam.automation.main_task.constant.BetStatus;
import com.epam.automation.main_task.data.BetList;
import com.epam.automation.main_task.data.LotList;
import com.epam.automation.main_task.service.Locking;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

public class Buyer extends Thread {

    public final long id;
    private final Locking locking;


    public Buyer(long id) {
        this.id = id;
        this.locking = new Locking();
    }

    @Override
    public long getId() {
        return id;
    }

    public void lock(int lotsCount) {
        this.locking.setLocked(true);
        this.locking.setLotsCount(lotsCount);
    }

    public void executePayment(Bet winnerBet) throws InterruptedException {

        Thread.sleep(3000);
        if (new Random().nextBoolean()) {
            winnerBet.setBetStatus(BetStatus.PAID);
            System.out.println("Buyer " + id + " paid for the lot " + winnerBet.getLot()
                    .getName());
        } else {
            System.out.println("Buyer " + id + " don't paid for the lot " + winnerBet.getLot()
                    .getName());
        }
        System.out.println("Auction for this lot ended");
    }

    public void makeBet(Lot lot) {
        int amount = new Random().nextInt(1000);
        BetList.add(lot, amount, this);
        System.out.println("Buyer " + id + " make a bet on the lot " + lot.getName() + " in the amount of " + amount +
                " euros");
    }

    public void changeBet(Lot lot) {
        if (BetList.isBetExist(this, lot)) {
            Bet bet  = BetList.get(this, lot);
            int amountIncrease = new Random().nextInt(500);
            int newPrice = Objects.requireNonNull(bet)
                    .getPrice() + amountIncrease;
            bet.setPrice(newPrice);
            System.out.println("Buyer " + id + " raised the bet on the lot " + lot.getName() + " to " + newPrice +
                    " euros");
        }

    }

    @Override
    public void run() {
        try {
            for (Lot lot : LotList.getLots()) {
                Thread.sleep(1500);
                if (!locking.isLocked()) {

                    int randomNumberForMakeBet = new Random().nextInt(10);

                    Thread.sleep(randomNumberForMakeBet * 1000);
                    if (new Random().nextBoolean()) {
                        makeBet(lot);
                    } else {
                        System.out.println("Buyer " + id + " ignored the lot " + lot.getName());
                    }

                    int randomNumberForChangeBet = new Random().nextInt(10);
                    for (int i = 0; i < new Random().nextInt(5); i++) {
                        Thread.sleep(randomNumberForChangeBet * 1000);
                        if (new Random().nextBoolean()) {
                            changeBet(lot);
                        } else {
                            System.out.println("Buyer " + id + " ignored bet increasing per lot " + lot.getName());
                        }
                    }
                } else {
                    System.out.println("Buyer " + getId() + " is locked for " + locking.getLotsCount() + " cycle");
                    locking.reduceLotsCount();
                }
                lot.getBarrier()
                        .await();
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Buyer)) return false;
        Buyer buyer = (Buyer) o;
        return id == buyer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
