package com.epam.automation.main_task.bean;

import com.epam.automation.main_task.service.Auction;
import com.epam.automation.main_task.service.WinnerDefiner;

import java.util.Objects;
import java.util.concurrent.CyclicBarrier;

public class Lot {

    private final long id;
    private String name;
    private CyclicBarrier barrier;


    public Lot(long id, String name) {
        this.id = id;
        this.name = name;
        barrier = new CyclicBarrier(Auction.buyersCount, () -> {
            try {
                WinnerDefiner.define(id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CyclicBarrier getBarrier() {
        return barrier;
    }

    public void setBarrier(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lot)) return false;
        Lot lot = (Lot) o;
        return id == lot.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
