package com.ariso.jtrade;

public class TickData {

    public String TickTime;

    public double High;

    public double Low;

    public double Open;

    public double Close;

    public double Volume;

    public String getTickTime() {
        return TickTime;
    }

    public void setTickTime(String tickTime) {
        TickTime = tickTime;
    }

    public double getHigh() {
        return High;
    }

    public void setHigh(double high) {
        High = high;
    }

    public double getLow() {
        return Low;
    }

    public void setLow(double low) {
        Low = low;
    }

    public double getOpen() {
        return Open;
    }

    public void setOpen(double open) {
        Open = open;
    }

    public double getClose() {
        return Close;
    }

    public void setClose(double close) {
        Close = close;
    }

    public double getVolume() {
        return Volume;
    }

    public void setVolume(double volume) {
        Volume = volume;
    }
}
