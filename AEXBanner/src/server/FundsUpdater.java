package server;

import fontys.observer.BasicPublisher;
import shared.IFunds;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by quintaartsen on 04-10-17.
 */
public class FundsUpdater extends TimerTask {

    private List<IFunds> iFunds = new ArrayList<>();

    public FundsUpdater(List<IFunds> iFunds) {
        this.iFunds = iFunds;
    }

    @Override
    public void run() {
        for (IFunds f : this.iFunds) {
            try {
                Funds funds = (Funds)f;

                updateFunds(funds);
            } catch(Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        }
    }

    private void updateFunds(Funds funds) {
        double exchangeRate = funds.getExchangeRate() + (Math.random() * 2) - 1;
        funds.setExchangeRate(exchangeRate);
    }
}
