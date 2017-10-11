package client;

import shared.IStockMarket;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Timer;

/**
 * Created by quintaartsen on 04-10-17.
 */
public class BannerController {
    private AEXBanner banner;
    private Timer pollingTimer;
    private Registry registry = null;
    private IStockMarket stockMarket;
    private static final String bindingName = "stockMarket";


    public BannerController(AEXBanner banner) {
        try {
            registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
        } catch (RemoteException rex) {
            System.out.println(rex.getMessage());
            registry = null;
        }

        if(registry != null) {
            printContentsRegistry();
            try {
                stockMarket = (IStockMarket) registry.lookup(bindingName);
            } catch (RemoteException rex) {
                System.out.println(rex.getMessage());
                stockMarket = null;
            } catch (NotBoundException nbex) {
                System.out.println(nbex.getMessage());
                stockMarket = null;
            }
        }

        this.banner = new AEXBanner();

        pollingTimer = new Timer();
        pollingTimer.schedule(new FundsGetter(banner, stockMarket), 500, 2000);
    }

    public void stop() {
        pollingTimer.cancel();
    }

    private void printContentsRegistry() {
        try {
            String[] listOfNames = registry.list();
            System.out.println("Client: list of names bound in registry:");
            if (listOfNames.length != 0) {
                for (String s : registry.list()) {
                    System.out.println(s);
                }
            } else {
                System.out.println("Client: list of names bound in registry is empty");
            }
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot show list of names bound in registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
        }
    }
}
