
package pl.currency.codec;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * NBP XML historically uses ISO-8859-2 (Latin2) or UTF-8 depending on table.
 * This encoder tries ISO-8859-2 first, then falls back to UTF-8 if it fails.
 */
public class ISOEncoder implements Encoder {

    private static final Charset ISO = Charset.forName("ISO-8859-2");

    @Override
    public String encode(byte[] bytes) {
        // Heuristic: decode as ISO-8859-2, if it looks garbled we can still live with it.
        String s = new String(bytes, ISO);
        // Some feeds are UTF-8; try to detect by replacement char presence after ISO decode.
        if (s.contains("�")) { // replacement char
            return new String(bytes, StandardCharsets.UTF_8);
        }
        return s;
    }
}
