package com.epam.automation.main_task.service;

import com.epam.automation.main_task.bean.Bet;
import com.epam.automation.main_task.bean.Lot;
import com.epam.automation.main_task.constant.BetStatus;
import com.epam.automation.main_task.data.BetList;
import com.epam.automation.main_task.data.WinnerList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static com.epam.automation.main_task.data.LotList.searchByID;

public class WinnerDefiner {

    public static void define(long lotID) throws InterruptedException {
        Lot lot = searchByID(lotID);
        List<Bet> betsWithLot = BetList.getBetsByLot(lot);
        if (!betsWithLot.isEmpty()) {
            Bet winnerBet = Collections.max(BetList.getBetsByLot(lot), Comparator.comparingInt(Bet::getPrice));
            winnerBet.setBetStatus(BetStatus.WAIT);
            WinnerList.add(winnerBet);
            System.out.println("Lot " + Objects.requireNonNull(lot)
                    .getName() + " goes to the buyer " + winnerBet.getBuyer()
                    .getId());
            winnerBet.getBuyer()
                    .executePayment(winnerBet);

        } else {
            System.out.println("Lot " + Objects.requireNonNull(lot)
                    .getName() + " is not passed to any of the buyers");
        }
    }

}

