package com.epam.automation.main_task.service;

public class Locking {
    private boolean isLocked;
    private int lotsCount;

    public Locking() {
        this.isLocked = false;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public int getLotsCount() {
        return lotsCount;
    }

    public void setLotsCount(int lotsCount) {
        this.lotsCount = lotsCount;
    }

    public void reduceLotsCount() {
        if (isLocked) {
            lotsCount--;
            if (lotsCount == 0) {
                isLocked = false;
            }
        }
    }

}
