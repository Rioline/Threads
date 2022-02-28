package com.epam.automation.main_task.service;

import com.epam.automation.main_task.bean.Buyer;
import com.epam.automation.main_task.data.BuyerList;

public class Auction {
    private BuyerList buyers;

    public static final int buyersCount = 5;

    public Auction() {
        buyers = new BuyerList(buyersCount);
    }

    public void start() {
        PaymentControl paymentControl = new PaymentControl();
        paymentControl.setDaemon(true);
        paymentControl.start();
        for (Buyer buyer : buyers.getBuyers()) {
            buyer.start();
        }
    }
}
