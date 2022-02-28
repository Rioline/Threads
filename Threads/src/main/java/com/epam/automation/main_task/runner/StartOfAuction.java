package com.epam.automation.main_task.runner;

import com.epam.automation.main_task.service.Auction;

public class StartOfAuction {
    public static void main(String[] args) {

        Auction auction = new Auction();
        auction.start();


    }
}
