package com.app;

public class Measurement {
    private double time;
    private double value;

    public Measurement(double time, double value) {
        this.time = time;
        this.value = value;
    }

    public double getTime() {
        return time;
    }

    public double getValue() {
        return value;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
