package server;

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

    public RMIServer() {

        try {
            registry = LocateRegistry.createRegistry(1099);
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
    }

    public static void main(String[] args) {
        RMIServer server = new RMIServer();
    }
}
