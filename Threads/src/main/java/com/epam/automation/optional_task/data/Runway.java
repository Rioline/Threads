package com.epam.automation.optional_task.data;

import com.epam.automation.optional_task.bean.Plane;

import java.util.concurrent.TimeUnit;

public class Runway implements Runnable{
    private final Plane plane;
    private static final int runwaysCount = 5;

    public static int getRunwaysCount() {
        return runwaysCount;
    }

    public Runway(Plane plane) {
        this.plane = plane;
    }

    private void timeToTakeOff(int timeOut) {
        try {
            TimeUnit.SECONDS.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println(plane.getPlaneName() + " moving to Runway-" +
                Thread.currentThread()
                        .getName()
                        .charAt(Thread.currentThread()
                                .getName()
                                .length() - 1));
        timeToTakeOff(1);
        System.out.println("the Runway-" +
                Thread.currentThread()
                        .getName()
                        .charAt(Thread.currentThread()
                                .getName()
                                .length() - 1) + " received the " + plane.getPlaneName());
        timeToTakeOff(1);
        System.out.println(plane.getPlaneName() + " took off from Runway-" +
                Thread.currentThread()
                        .getName()
                        .charAt(Thread.currentThread()
                                .getName()
                                .length() - 1));
        timeToTakeOff(1);
        System.out.println("Runway-" +
                Thread.currentThread()
                        .getName()
                        .charAt(Thread.currentThread()
                                .getName()
                                .length() - 1) + " is free");
    }

}
