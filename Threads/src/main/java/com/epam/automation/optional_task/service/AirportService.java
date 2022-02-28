package com.epam.automation.optional_task.service;

import com.epam.automation.optional_task.bean.Plane;
import com.epam.automation.optional_task.data.Airport;
import com.epam.automation.optional_task.data.Runway;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AirportService {

    public static void takeOff() {

        ExecutorService executorService = Executors.newFixedThreadPool(Runway.getRunwaysCount());
        for (Plane plane : Airport.planes) {
            executorService.execute(new Runway(plane));
        }
        executorService.shutdown();
        while (true) {
            if (executorService.isTerminated()) break;
        }
        System.out.println("All planes take off!");
    }

}