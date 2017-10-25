package shared;

import fontys.observer.RemotePublisher;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by quintaartsen on 04-10-17.
 */
public interface IStockMarket extends RemotePublisher {
    List<IFunds> getExchangeRates() throws RemoteException;
}
