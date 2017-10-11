package server;

import shared.IFunds;
import shared.IStockMarket;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * Created by quintaartsen on 04-10-17.
 */
public class MockStockMarket extends UnicastRemoteObject implements IStockMarket {

    private List<IFunds> iFunds;
    private Timer timer;

    public MockStockMarket() throws RemoteException {
        iFunds = new ArrayList<>();
        createFunds();
        setTimer();
    }

    private void createFunds(){
        this.iFunds.add(new Funds("Philips"));
        this.iFunds.add(new Funds("KPN"));
        this.iFunds.add(new Funds("Heineken"));


    }


    private void setTimer()
    {
        timer = new Timer();
        timer.scheduleAtFixedRate(new FundsUpdater(this.iFunds), 0, 1000);
    }


    @Override
    public List<IFunds> getExchangeRates() {
        return iFunds;
    }
}
