package com.epam.automation.main_task.data;

import com.epam.automation.main_task.bean.Bet;
import com.epam.automation.main_task.bean.Buyer;
import com.epam.automation.main_task.bean.Lot;
import com.epam.automation.main_task.constant.BetStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BetList {

    private static final List<Bet> bets = new ArrayList<>();
    private static long counter = 0;

    public static List<Bet> getBets() {
        return bets;
    }

    public static List<Bet> getBetByBuyer(final Buyer buyer) {
        return bets.stream()
                .filter(bet -> bet.getBuyer()
                        .equals(buyer))
                .collect(Collectors.toList());
    }

    public static List<Bet> getBetsByLot(Lot lot) {
        return bets.stream()
                .filter(bet -> bet.getLot()
                        .equals(lot))
                .collect(Collectors.toList());
    }

    public static void add(Lot lot, int price, Buyer buyer) {
        bets.add(new Bet(counter, price, lot, buyer, BetStatus.NEW));
        counter++;
    }

    public static Bet get(long id) {
        Optional<Bet> optionalBet = bets.stream()
                .filter(bet -> bet.getId() == id)
                .findAny();
        return optionalBet.orElse(null);
    }

    public static boolean isBetExist(Buyer buyer, Lot lot) {
        return getBetByBuyer(buyer).stream()
                .anyMatch(bet -> bet.getLot()
                        .equals(lot));
    }

    public static Bet get(Buyer buyer, Lot lot) {
        Optional<Bet> optionalBet = getBetByBuyer(buyer).stream()
                .filter(bet -> bet.getLot()
                        .equals(lot))
                .findAny();
        return optionalBet.orElse(null);
    }


}
