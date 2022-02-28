package com.epam.automation.main_task.data;

import com.epam.automation.main_task.bean.Bet;
import com.epam.automation.main_task.constant.BetStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WinnerList {

    private static final Map<Date, Bet> winnerBets = new HashMap<>();

    public static void add(Bet bet) {
        winnerBets.put(new Date(), bet);
    }

    public static Map<Date, Bet> getWinnerBets() {
        return winnerBets;
    }

    public static List<Bet> getWaitPaymentBetsOlderThan(Date timeLimit) {
        return winnerBets.entrySet()
                .stream()
                .filter(entry ->
                        (entry.getValue()
                                .getBetStatus() == BetStatus.WAIT) && (entry.getKey()
                                .before(timeLimit))).
                map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
