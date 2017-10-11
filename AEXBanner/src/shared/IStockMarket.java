package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by quintaartsen on 04-10-17.
 */
public interface IStockMarket extends Remote {
    List<IFunds> getExchangeRates() throws RemoteException;
}
