package com.mobilerq.amazonaws.sns;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.Collections;

/**
 * Read certificate and private key from Pkcs12 store.
 */
class Pkcs12 {

    private final static String KEY_STORE_TYPE = "pkcs12";
    private final Certificate certificate;
    private final PrivateKey privateKey;
    private final String alias;

    private Pkcs12(Certificate certificate, PrivateKey privateKey, String alias) {
        this.certificate = certificate;
        this.privateKey = privateKey;
        this.alias = alias;
    }

    /**
     * Read a PKCS12 Certificate and private key from P12 formatted input.
     *
     * @param input    the p12 (PKCS12) content.
     * @param password the password to decrypt the p12 content.
     * @return Pkcs12 object with the certificate, private key, and alias.
     * @throws IllegalArgumentException when a valid certificate and private key are not found in the input
     * @throws NullPointerException     when the input or password are null
     */
    static public Pkcs12 from(final InputStream input, final char[] password) {
        notNull(input, "Input");
        notNull(password, "Password");
        try {
            final KeyStore p12 = KeyStore.getInstance(KEY_STORE_TYPE, new BouncyCastleProvider());
            p12.load(input, password);
            for (String alias : Collections.list(p12.aliases())) {
                if (p12.isKeyEntry(alias)) {
                    final Certificate cert = p12.getCertificate(alias);
                    final PrivateKey key = (PrivateKey) p12.getKey(alias, password);
                    if (cert != null && key != null) {
                        return new Pkcs12(cert, key, alias);
                    }
                }
            }
        } catch (IOException e) {
            if(e.getMessage().toUpperCase().contains("Illegal key size".toUpperCase())) {
                throw new IllegalArgumentException("Illegal key size found in PKCS12 input. This is usually caused by " +
                        "Java enforcing outdated US export laws. Upgrade to Java 1.7.0_40, change to OpenJDK, or " +
                        "install Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy.", e);
            }
            throw new IllegalArgumentException("Unable to read certificate or private key from PKCS12 input", e);
        } catch (GeneralSecurityException e) {
            throw new IllegalArgumentException("Unable to unpack certificate or private key from PKCS12 input", e);
        }
        throw new IllegalArgumentException("Unable to find certificate and private key in PKCS12 input");
    }

    private static <T> T notNull(final T value, final String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
        return value;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public String getAlias() {
        return alias;
    }
}
