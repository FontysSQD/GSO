package client;

import jdk.nashorn.internal.ir.IfNode;
import shared.IFunds;
import shared.IStockMarket;

import java.rmi.RemoteException;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by quintaartsen on 04-10-17.
 */
public class FundsGetter extends TimerTask {

    private AEXBanner banner;
    private IStockMarket stockMarket;

    public FundsGetter(AEXBanner banner, IStockMarket stockMarket)
    {
        this.stockMarket = stockMarket;
        this.banner = banner;
    }

    @Override
    public void run() {
        try {
            List<IFunds> funds = stockMarket.getExchangeRates();
            String result = "";
            for (IFunds f:funds) {
                result += "  " + f.toString();
            }
            banner.setExchange(result);
        } catch (RemoteException rex) {
            System.out.println(rex.getMessage());
        }
    }
}
