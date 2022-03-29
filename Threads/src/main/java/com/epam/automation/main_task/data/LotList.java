package com.epam.automation.main_task.data;

import com.epam.automation.main_task.bean.Lot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LotList {

    private static ArrayList<Lot> lots;

    static {
        lots = new ArrayList<>() {
            {
                this.add(new Lot(1, "\"MANE\""));
                this.add(new Lot(2, "\"WARHOL\""));
                this.add(new Lot(3, "\"VANGOG\""));
                this.add(new Lot(4, "\"MALEVICH\""));
                this.add(new Lot(5, "\"MIKELANGELO\""));
            }
        };
    }

    public static List<Lot> getLots() {
        return lots;
    }

    public static Lot get(int index) {
        return lots.get(index);
    }

    public static Lot searchByID(long id) {
        Optional<Lot> optionalLot = lots.stream()
                .filter(lot -> lot.getId() == id)
                .findFirst();
        return optionalLot.orElse(null);
    }
}
