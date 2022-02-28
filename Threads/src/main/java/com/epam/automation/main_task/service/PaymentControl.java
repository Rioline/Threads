package com.epam.automation.main_task.service;

import com.epam.automation.main_task.bean.Bet;
import com.epam.automation.main_task.bean.Buyer;
import com.epam.automation.main_task.constant.BetStatus;
import com.epam.automation.main_task.data.WinnerList;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PaymentControl extends Thread{
    public final int timeToPay = 3000;
    public final int lotsCountForLock = 2;

    @Override
    public void run() {
        try {
            if (this.isDaemon()) {
                System.out.println("Auction started!");
                while (true) {
                    Thread.sleep(1000);
                    Calendar calendar = new GregorianCalendar();
                    calendar.add(Calendar.MILLISECOND, -timeToPay);
                    Date timeLimit = calendar.getTime();
                    for (Bet bet : WinnerList.getWaitPaymentBetsOlderThan(timeLimit)) {
                        Buyer buyer = bet.getBuyer();
                        buyer.lock(lotsCountForLock);
                        System.out.println("Buyer " + buyer.getId() + " is locked");
                        bet.setBetStatus(BetStatus.NEW);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
