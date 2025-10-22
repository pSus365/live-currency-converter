
package pl.currency.app;

import pl.currency.codec.Encoder;
import pl.currency.codec.ISOEncoder;
import pl.currency.model.ExchangeTable;
import pl.currency.parser.Document;
import pl.currency.parser.XMLDocument;
import pl.currency.repo.RemoteRepository;
import pl.currency.repo.RestRepository;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * Application singleton orchestrating data download and parsing.
 */
public class ExchangeApp {

    private static final String NBP_URL = "https://static.nbp.pl/dane/kursy/xml/LastA.xml";

    private static ExchangeApp instance;

    public static synchronized ExchangeApp getInstance() {
        if (instance == null) instance = new ExchangeApp();
        return instance;
    }

    private final RemoteRepository repo = new RestRepository();
    private final Encoder encoder = new ISOEncoder();
    private final Document parser = new XMLDocument();

    private CompletableFuture<ExchangeTable> tableFuture;

    private ExchangeApp() { }

    public CompletableFuture<ExchangeTable> getTableAsync() {
        if (tableFuture == null) {
            tableFuture = repo.get(NBP_URL)
                    .thenApply(encoder::encode)
                    .thenApply(content -> {
                        try {
                            return parser.getTable(content);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
        return tableFuture;
    }
}
