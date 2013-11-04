package com.mobilerq.amazonaws.sns;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class PemTest {

    final String p12Resource = "/unittest.p12";
    final char[] p12Password = "test".toCharArray();
    Certificate p12Cert;
    PrivateKey p12Key;

    @Before
    public void loadP12() throws Exception {
        final KeyStore p12 = KeyStore.getInstance("pkcs12");
        p12.load(getClass().getResourceAsStream(p12Resource), p12Password);
        for (String alias : Collections.list(p12.aliases())) {
            p12Cert = p12.getCertificate(alias);
            p12Key = (PrivateKey) p12.getKey(alias, p12Password);
            break;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromObject() throws Exception {
        Pem.from(new Object());
    }

    @Test
    public void testFromCertificate() throws Exception {
        final String actualCert = Pem.from(p12Cert).trim();
        final String expectedCert = IO.getResourceAsString("/unittest.crt").trim();
        assertEquals(expectedCert, actualCert);
    }

    @Test
    public void testFromPrivateKey() throws Exception {
        final String actualCert = Pem.from(p12Key).trim();
        final String expectedCert = IO.getResourceAsString("/unittest.key").trim();
        assertEquals(expectedCert, actualCert);
    }
}
