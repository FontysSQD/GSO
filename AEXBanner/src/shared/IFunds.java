package shared;

import java.io.Serializable;

/**
 * Created by quintaartsen on 04-10-17.
 */
public interface IFunds extends Serializable {
    String getName();
    double getExchangeRate();
}
