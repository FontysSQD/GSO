package server;

import shared.IFunds;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by quintaartsen on 04-10-17.
 */
public class Funds implements IFunds {

    private String name;
    private double exchangeRate;

    public Funds() {}


    public Funds(String name)
    {
        this.name = name;
        this.exchangeRate = Math.random() * 100;
    }

    public String getName()
    {
        return this.name;
    }

    public double getExchangeRate()
    {
        return this.exchangeRate;
    }

    public void setExchangeRate(double value)
    {
        this.exchangeRate = value;
    }

    @Override
    public String toString() {
        return name + " " + round(exchangeRate);
    }

    private static double round(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
