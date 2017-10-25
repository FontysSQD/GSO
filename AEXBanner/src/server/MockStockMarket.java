package server;

import fontys.observer.BasicPublisher;
import fontys.observer.RemotePropertyListener;
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
    private BasicPublisher publisher;

    public MockStockMarket() throws RemoteException {
        publisher = new BasicPublisher(new String[] {"stockMarket"});
        iFunds = new ArrayList<>();
        createFunds();
        setTimer();
    }

    private void createFunds(){
        this.iFunds.add(new Funds("Philips"));
        this.iFunds.add(new Funds("KPN"));
        this.iFunds.add(new Funds("Heineken"));
    }

    private void setTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new FundsUpdater(this.iFunds), 0, 1000);
    }

    @Override
    public List<IFunds> getExchangeRates() {
        publisher.inform(this, "stockMarket", null, iFunds);
        return iFunds;
    }

    @Override
    public void addListener(RemotePropertyListener remotePropertyListener, String s) throws RemoteException {
        publisher.addListener(remotePropertyListener, s);
    }

    @Override
    public void removeListener(RemotePropertyListener remotePropertyListener, String s) throws RemoteException {
        publisher.removeListener(remotePropertyListener, s);
    }
}
