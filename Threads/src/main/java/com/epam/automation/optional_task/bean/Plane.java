package com.epam.automation.optional_task.bean;

public class Plane {

    private String planeName;

    public Plane(String planeName) {
        this.planeName = planeName;
    }

    public String getPlaneName() {
        return planeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return planeName.equals(plane.planeName);
    }

    @Override
    public int hashCode() {
        return planeName.hashCode();
    }

    @Override
    public String toString() {
        return "Plane{" +
                "planeName='" + planeName + '\'' +
                '}';
    }

}
