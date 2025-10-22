
package pl.currency.codec;

/**
 * Converts raw bytes to a Java String using a chosen encoding.
 */
public interface Encoder {
    String encode(byte[] bytes);
}
