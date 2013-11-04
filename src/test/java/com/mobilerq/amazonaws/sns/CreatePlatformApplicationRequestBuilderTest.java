package com.mobilerq.amazonaws.sns;

import com.amazonaws.services.sns.model.CreatePlatformApplicationRequest;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class CreatePlatformApplicationRequestBuilderTest {
    char [] p12Password = "test".toCharArray();

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

    }

    @Test
    public void testPlatform() throws Exception {

    }

    @Test
    public void testPlatformObj() throws Exception {

    }

    @Test
    public void testAttribute() throws Exception {

    }

    @Test
    public void testName() throws Exception {

    }

    @Test
    public void testPrincipal() throws Exception {

    }

    @Test
    public void testPrincipalObj() throws Exception {

    }

    @Test
    public void testCredential() throws Exception {

    }

    @Test
    public void testCredentialObj() throws Exception {

    }

    @Test
    public void testGetRequest() throws Exception {

    }
}
