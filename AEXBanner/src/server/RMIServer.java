package server;

import fontys.observer.BasicPublisher;
import shared.IStockMarket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * AEXBanner
 * Created by Dane Naebers on 11-10-17.
 */
public class RMIServer {

    private static final int portNumber = 1099;
    private static final String bindingName = "stockMarket";
    private Registry registry = null;
    private IStockMarket stockMarket = null;
    private BasicPublisher publisher;

    public RMIServer() {
        try {
            registry = LocateRegistry.createRegistry(portNumber);
        } catch (RemoteException rex) {
            System.out.println(rex.getMessage());
        }

        try {
            stockMarket = new MockStockMarket();
            registry.rebind(bindingName, stockMarket);
        } catch (RemoteException rex) {
            System.out.println(rex.getMessage());
        }
        System.out.println("Server has started");

        publisher = new BasicPublisher(new String[] {"stockMarket"});

        try{
            while(true){
                publisher.inform(this, "stockMarket", null, stockMarket.getExchangeRates());
                Thread.sleep((long)(1000 + 2000 * Math.random()));
            }
        } catch (RemoteException ex){
            System.out.println("Server has crashed!");
        } catch (InterruptedException ex){
            System.out.println("Server has interrupted!");
        }
    }

    public static void main(String[] args) {
        RMIServer server = new RMIServer();
    }
}
