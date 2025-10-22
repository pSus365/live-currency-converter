
package pl.currency.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * Aggregate root for a table of currency rates.
 */
public class ExchangeTable {
    private final String id;
    private final LocalDate publicationDate;
    private final Map<String, ExchangeRate> rates = new TreeMap<>(); // by code

    public ExchangeTable(String id, LocalDate publicationDate) {
        this.id = id;
        this.publicationDate = publicationDate;
    }

    public void addRate(ExchangeRate rate) {
        rates.put(rate.getCode().toUpperCase(), rate);
    }

    public ExchangeRate getRate(String code) {
        return rates.get(code.toUpperCase());
    }

    public String getId() { return id; }

    public LocalDate getPublicationDate() { return publicationDate; }

    public Collection<ExchangeRate> getAll() {
        return rates.values();
    }
}
