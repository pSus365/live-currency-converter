
package pl.currency.parser;

import pl.currency.model.ExchangeTable;

/**
 * Parses text content into a domain model (ExchangeTable).
 */
public interface Document {
    ExchangeTable getTable(String content) throws Exception;
}
