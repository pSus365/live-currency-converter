
package pl.currency.repo;

import java.util.concurrent.CompletableFuture;

/**
 * Port (interface) for downloading raw bytes from a remote resource.
 * Asynchronous by design using CompletableFuture.
 */
public interface RemoteRepository {
    CompletableFuture<byte[]> get(String url);
}
