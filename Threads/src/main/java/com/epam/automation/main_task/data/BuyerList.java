package com.epam.automation.main_task.data;

import com.epam.automation.main_task.bean.Buyer;

import java.util.ArrayList;
import java.util.List;

public class BuyerList {

    private ArrayList<Buyer> buyers;

    public BuyerList(int count) {
        buyers = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            buyers.add(new Buyer(i+1));
        }
    }

    public List<Buyer> getBuyers() {
        return buyers;
    }

}
