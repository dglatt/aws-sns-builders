package com.mobilerq.amazonaws.sns;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.sns.model.CreatePlatformApplicationRequest;

import java.io.InputStream;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.HashMap;
import java.util.Map;

public class CreatePlatformApplicationRequestBuilder {

    private CreatePlatformApplicationRequest request = new CreatePlatformApplicationRequest();

    public CreatePlatformApplicationRequestBuilder(Platform platform, String name, String credential) {
        platform(platform).name(name).credential(credential);
    }

    public CreatePlatformApplicationRequestBuilder(Platform platform, InputStream p12, char[] p12Password) {
        final Pkcs12 pkcs12 = Pkcs12.from(p12, p12Password);
        platform(platform).principal(pkcs12.getCertificate()).credential(pkcs12.getPrivateKey()).name(pkcs12.getAlias());
    }

    public CreatePlatformApplicationRequestBuilder requestCredentials(AWSCredentials awsCredentials) {
        request.setRequestCredentials(awsCredentials);
        return this;
    }

    public CreatePlatformApplicationRequestBuilder platform(Platform platform) {
        return platform(platform.name());
    }

    public CreatePlatformApplicationRequestBuilder platform(String platform) {
        request.setPlatform(platform);
        return this;
    }

    public CreatePlatformApplicationRequestBuilder attribute(String name, String value) {
        Map<String, String> attributes = request.getAttributes();
        if (attributes == null) {
            attributes = new HashMap<String, String>();
            request.setAttributes(attributes);
        }
        attributes.put(name, value);
        return this;
    }

    public CreatePlatformApplicationRequestBuilder name(String name) {
        request.setName(name);
        return this;
    }

    public CreatePlatformApplicationRequestBuilder principal(String platformPrincipal) {
        return attribute("PlatformPrincipal", platformPrincipal);
    }

    public CreatePlatformApplicationRequestBuilder principal(Certificate platformPrincipal) {
        return principal(Pem.from(platformPrincipal));
    }

    public CreatePlatformApplicationRequestBuilder credential(String platformCredential) {
        return attribute("PlatformCredential", platformCredential);
    }

    public CreatePlatformApplicationRequestBuilder credential(PrivateKey platformCredential) {
        return credential(Pem.from(platformCredential));
    }

    public CreatePlatformApplicationRequest getRequest() {
        return request;
    }

}
