package client;

import fontys.observer.RemotePropertyListener;
import shared.IFunds;
import shared.IStockMarket;
import java.beans.PropertyChangeEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by quintaartsen on 04-10-17.
 */
public class BannerController extends UnicastRemoteObject implements RemotePropertyListener {
    private AEXBanner banner;
    private Registry registry = null;
    private IStockMarket stockMarket;
    private static final String bindingName = "stockMarket";


    public BannerController(AEXBanner banner) throws RemoteException{
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
                stockMarket.addListener(this, "stockMarket");
            } catch (RemoteException rex) {
                System.out.println(rex.getMessage());
                stockMarket = null;
            } catch (NotBoundException nbex) {
                System.out.println(nbex.getMessage());
                stockMarket = null;
            }
        }

        this.banner = banner;
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

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) throws RemoteException {
        if(propertyChangeEvent.getPropertyName().equals("stockMarket")){
            setExchanges((List<IFunds>) propertyChangeEvent.getNewValue());
        }
    }

    private void setExchanges(List<IFunds> funds){
        String fundString = "";
        for (IFunds fund : funds){
            fundString += fund.toString() + " ";
        }

        banner.setExchange(fundString);
    }

    public void stop(){
        try {
            stockMarket.removeListener(this, "stockMarket");
        } catch (RemoteException ex){
            System.out.println(ex.getMessage());
        }
    }
}
