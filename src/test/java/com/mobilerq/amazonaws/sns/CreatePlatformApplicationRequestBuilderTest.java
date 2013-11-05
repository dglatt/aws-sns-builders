package com.mobilerq.amazonaws.sns;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.sns.model.CreatePlatformApplicationRequest;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class CreatePlatformApplicationRequestBuilderTest {
    char[] p12Password = "test".toCharArray();

    @Test
    public void testPlatform_String_String_Constructor() throws Exception {
        CreatePlatformApplicationRequest r = new CreatePlatformApplicationRequestBuilder(Platform.GCM, "n1", "p1").getRequest();
        assertEquals("GCM", r.getPlatform());
        assertEquals("n1", r.getName());
        assertEquals("p1", r.getAttributes().get("PlatformCredential"));
    }

    @Test
    public void testPlatform_InputStream_CharArray_Constructor() throws Exception {
        Pkcs12 actualP12 = Pkcs12.from(getClass().getResourceAsStream("/unittest.p12"), p12Password);
        CreatePlatformApplicationRequest r = new CreatePlatformApplicationRequestBuilder(Platform.APNS, getClass().getResourceAsStream("/unittest.p12"), p12Password).getRequest();
        assertEquals("APNS", r.getPlatform());
        assertEquals(actualP12.getAlias(), r.getName());
        assertEquals(Pem.from(actualP12.getCertificate()), r.getAttributes().get("PlatformPrincipal"));
        assertEquals(Pem.from(actualP12.getPrivateKey()), r.getAttributes().get("PlatformCredential"));
    }

    @Test
    public void testRequestCredentials() throws Exception {
        final Platform p = Platform.APNS;
        final String name = UUID.randomUUID().toString();
        final String key = UUID.randomUUID().toString();
        final AWSCredentials mockCredentials = mock(AWSCredentials.class);
        CreatePlatformApplicationRequest r = new CreatePlatformApplicationRequestBuilder(p, name, key)
                .requestCredentials(mockCredentials)
                .getRequest();

        assertEquals(mockCredentials, r.getRequestCredentials());
        assertEquals(p.name(), r.getPlatform());
        assertEquals(name, r.getName());
        assertEquals(key, r.getAttributes().get("PlatformCredential"));
    }

    @Test
    public void testPlatform() throws Exception {
        final Platform p1 = Platform.APNS;
        final Platform p2 = Platform.GCM;
        final String name = UUID.randomUUID().toString();
        final String key = UUID.randomUUID().toString();
        CreatePlatformApplicationRequest r = new CreatePlatformApplicationRequestBuilder(p1, name, key)
                .platform(p2.name())
                .getRequest();
        assertEquals(p2.name(), r.getPlatform());
        assertEquals(name, r.getName());
        assertEquals(key, r.getAttributes().get("PlatformCredential"));
    }

    @Test
    public void testPlatformObj() throws Exception {
        final Platform p1 = Platform.APNS;
        final Platform p2 = Platform.GCM;
        final String name = UUID.randomUUID().toString();
        final String key = UUID.randomUUID().toString();
        CreatePlatformApplicationRequest r = new CreatePlatformApplicationRequestBuilder(p1, name, key)
                .platform(p2)
                .getRequest();
        assertEquals(p2.name(), r.getPlatform());
        assertEquals(name, r.getName());
        assertEquals(key, r.getAttributes().get("PlatformCredential"));
    }

    @Test
    public void testAttribute() throws Exception {
        final Platform p = Platform.APNS;
        final String name = UUID.randomUUID().toString();
        final String key = UUID.randomUUID().toString();
        final String attrName = UUID.randomUUID().toString();
        final String attrValue = UUID.randomUUID().toString();
        CreatePlatformApplicationRequest r = new CreatePlatformApplicationRequestBuilder(p, name, key)
                .attribute(attrName, attrValue)
                .getRequest();
        assertEquals(attrValue, r.getAttributes().get(attrName));
        assertEquals(p.name(), r.getPlatform());
        assertEquals(name, r.getName());
        assertEquals(key, r.getAttributes().get("PlatformCredential"));
    }

    @Test
    public void testName() throws Exception {
        final Platform p = Platform.APNS;
        final String name1 = UUID.randomUUID().toString();
        final String name2 = UUID.randomUUID().toString();
        final String key = UUID.randomUUID().toString();
        CreatePlatformApplicationRequest r = new CreatePlatformApplicationRequestBuilder(p, name1, key)
                .name(name2)
                .getRequest();
        assertEquals(p.name(), r.getPlatform());
        assertEquals(name2, r.getName());
        assertEquals(key, r.getAttributes().get("PlatformCredential"));
    }

    @Test
    public void testPrincipal() throws Exception {
        final Platform p = Platform.APNS;
        final String name = UUID.randomUUID().toString();
        final String key = UUID.randomUUID().toString();
        final String principal = UUID.randomUUID().toString();
        CreatePlatformApplicationRequest r = new CreatePlatformApplicationRequestBuilder(p, name, key)
                .principal(principal)
                .getRequest();
        assertEquals(principal, r.getAttributes().get("PlatformPrincipal"));
        assertEquals(p.name(), r.getPlatform());
        assertEquals(name, r.getName());
        assertEquals(key, r.getAttributes().get("PlatformCredential"));
    }

    @Test
    public void testPrincipalObj() throws Exception {
        final Pkcs12 p12 = Pkcs12.from(getClass().getResourceAsStream("/unittest.p12"), p12Password);
        final Platform p = Platform.APNS;
        final String name = UUID.randomUUID().toString();
        final String key = UUID.randomUUID().toString();
        final Certificate principal = p12.getCertificate();
        CreatePlatformApplicationRequest r = new CreatePlatformApplicationRequestBuilder(p, name, key)
                .principal(principal)
                .getRequest();
        assertEquals(Pem.from(principal), r.getAttributes().get("PlatformPrincipal"));
        assertEquals(p.name(), r.getPlatform());
        assertEquals(name, r.getName());
        assertEquals(key, r.getAttributes().get("PlatformCredential"));
    }

    @Test
    public void testCredential() throws Exception {
        final Platform p = Platform.APNS;
        final String name = UUID.randomUUID().toString();
        final String key1 = UUID.randomUUID().toString();
        final String key2 = UUID.randomUUID().toString();
        CreatePlatformApplicationRequest r = new CreatePlatformApplicationRequestBuilder(p, name, key1)
                .credential(key2)
                .getRequest();
        assertEquals(p.name(), r.getPlatform());
        assertEquals(name, r.getName());
        assertEquals(key2, r.getAttributes().get("PlatformCredential"));
    }

    @Test
    public void testCredentialObj() throws Exception {
        final Pkcs12 p12 = Pkcs12.from(getClass().getResourceAsStream("/unittest.p12"), p12Password);
        final Platform p = Platform.APNS;
        final String name = UUID.randomUUID().toString();
        final String key1 = UUID.randomUUID().toString();
        final PrivateKey key2 = p12.getPrivateKey();
        CreatePlatformApplicationRequest r = new CreatePlatformApplicationRequestBuilder(p, name, key1)
                .credential(key2)
                .getRequest();
        assertEquals(p.name(), r.getPlatform());
        assertEquals(name, r.getName());
        assertEquals(Pem.from(key2), r.getAttributes().get("PlatformCredential"));
    }

    @Test
    public void testGetRequest() throws Exception {
        final Pkcs12 p12 = Pkcs12.from(getClass().getResourceAsStream("/unittest.p12"), p12Password);
        final Platform p1 = Platform.APNS;
        final Platform p2 = Platform.GCM;
        final String name1 = UUID.randomUUID().toString();
        final String name2 = UUID.randomUUID().toString();
        final String key1 = UUID.randomUUID().toString();
        final PrivateKey key2 = p12.getPrivateKey();
        final Certificate principal = p12.getCertificate();
        final String attrName = UUID.randomUUID().toString();
        final String attrValue = UUID.randomUUID().toString();
        final AWSCredentials mockCredentials = mock(AWSCredentials.class);
        CreatePlatformApplicationRequest r = new CreatePlatformApplicationRequestBuilder(p1, name1, key1)
                .credential(key2)
                .platform(p2)
                .name(name2)
                .principal(principal)
                .attribute(attrName, attrValue)
                .requestCredentials(mockCredentials)
                .getRequest();
        assertEquals(p2.name(), r.getPlatform());
        assertEquals(name2, r.getName());
        assertEquals(Pem.from(key2), r.getAttributes().get("PlatformCredential"));
        assertEquals(Pem.from(principal), r.getAttributes().get("PlatformPrincipal"));
        assertEquals(attrValue, r.getAttributes().get(attrName));
        assertEquals(mockCredentials, r.getRequestCredentials());
    }
}
