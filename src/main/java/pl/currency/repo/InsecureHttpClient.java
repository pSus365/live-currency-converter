package pl.currency.repo;

import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.net.http.HttpClient;

/**
 * Tymczasowy klient HTTP z wyłączoną walidacją SSL.
 * NIE używać w środowisku produkcyjnym!
 */
final class InsecureHttpClient {
    static HttpClient build() {
        try {
            // TrustManager, który akceptuje wszystkie certyfikaty
            TrustManager[] trustAll = new TrustManager[]{
                    new X509TrustManager() {
                        public void checkClientTrusted(X509Certificate[] x, String a) {}
                        public void checkServerTrusted(X509Certificate[] x, String a) {}
                        public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
                    }
            };

            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, trustAll, new SecureRandom());

            // Budujemy klienta HTTP z tym kontekstem
            return HttpClient.newBuilder()
                    .sslContext(ctx)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
