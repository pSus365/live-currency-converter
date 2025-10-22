
package pl.currency.model;

import java.util.Objects;

/**
 * Value object representing a single currency rate (relative to PLN).
 * ratePLNperUnit: average PLN price for 1 unit of the currency.
 */
public class ExchangeRate {
    private final String code;
    private final String name;
    private final double ratePLNperUnit;

    public ExchangeRate(String code, String name, double ratePLNperUnit) {
        this.code = code;
        this.name = name;
        this.ratePLNperUnit = ratePLNperUnit;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getRatePLNperUnit() { return ratePLNperUnit; }

    @Override
    public String toString() {
        return code + " (" + name + "): " + ratePLNperUnit + " PLN";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExchangeRate)) return false;
        ExchangeRate that = (ExchangeRate) o;
        return Double.compare(that.ratePLNperUnit, ratePLNperUnit) == 0 &&
                Objects.equals(code, that.code) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, ratePLNperUnit);
    }
}
