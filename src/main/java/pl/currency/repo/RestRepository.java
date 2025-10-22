
package pl.currency.repo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

/**
 * HTTP implementation of RemoteRepository using Java 11+ HttpClient.
 */
public class RestRepository implements RemoteRepository {

    //private final HttpClient client = HttpClient.newHttpClient();
    private final HttpClient client = InsecureHttpClient.build();

    @Override
    public CompletableFuture<byte[]> get(String url) {
        HttpRequest req = HttpRequest.newBuilder(URI.create(url)).GET().build();
        return client.sendAsync(req, HttpResponse.BodyHandlers.ofByteArray())
                     .thenApply(HttpResponse::body);
    }
}
