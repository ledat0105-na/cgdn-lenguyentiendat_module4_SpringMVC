package com.example.currency.model;

public class Conversion {
    private double rate;
    private double usd;
    private double vnd;

    public Conversion() {
    }

    public Conversion(double rate, double usd, double vnd) {
        this.rate = rate;
        this.usd = usd;
        this.vnd = vnd;
    }

    public Conversion(double rate, double usd) {
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getUsd() {
        return usd;
    }

    public void setUsd(double usd) {
        this.usd = usd;
    }

    public double getVnd() {
        return vnd;
    }

    public void setVnd(double vnd) {
        this.vnd = vnd;
    }
}
