
package pl.currency.service;

import pl.currency.model.ExchangeRate;

/**
 * Domain service for currency conversion through PLN as the base.
 */
public class Exchange {

    /**
     * Convert amount from currency r1 to currency r2 using PLN rates.
     * @param amount amount in r1
     * @param r1 source currency rate (PLN per unit)
     * @param r2 target currency rate (PLN per unit)
     * @return amount in r2
     */
    public double exchange(double amount, ExchangeRate r1, ExchangeRate r2) {
        // amount r1 -> PLN -> r2
        double pln = amount * r1.getRatePLNperUnit();
        return pln / r2.getRatePLNperUnit();
    }
}
