package com.mobilerq.amazonaws.sns;

import org.junit.Test;

import java.security.PrivateKey;
import java.security.cert.Certificate;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class Pkcs12Test {

    char[] p12Password = "test".toCharArray();

    @Test
    public void testFrom() throws Exception {
        Pkcs12 p12 = Pkcs12.from(getClass().getResourceAsStream("/unittest.p12"), p12Password);
        assertEquals("RSA", p12.getPrivateKey().getAlgorithm());
        assertEquals("X.509", p12.getCertificate().getType());
    }

    @Test
    public void testGetCertificate() throws Exception {
        final Certificate p12Cert = Pkcs12.from(getClass().getResourceAsStream("/unittest.p12"), p12Password).getCertificate();
        final String actual = Pem.from(p12Cert).trim();
        final String expected = IO.getResourceAsString("/unittest.crt").trim();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPrivateKey() throws Exception {
        final PrivateKey p12Key = Pkcs12.from(getClass().getResourceAsStream("/unittest.p12"), p12Password).getPrivateKey();
        final String actual = Pem.from(p12Key).trim();
        final String expected = IO.getResourceAsString("/unittest.key").trim();
        assertEquals(expected, actual);
    }
}
