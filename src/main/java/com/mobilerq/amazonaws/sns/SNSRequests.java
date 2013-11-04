package com.mobilerq.amazonaws.sns;

import java.io.InputStream;

public class SNSRequests {

    public CreatePlatformApplicationRequestBuilder createAPNSApp(InputStream p12, char[] p12Password) {
        return new CreatePlatformApplicationRequestBuilder(Platform.APNS, p12, p12Password);
    }

    public CreatePlatformApplicationRequestBuilder createAPNSSandboxApp(InputStream p12, char[] p12Password) {
        return new CreatePlatformApplicationRequestBuilder(Platform.APNS_SANDBOX, p12, p12Password);
    }

    public CreatePlatformApplicationRequestBuilder createGCMApp(String name, String apiKey) {
        return new CreatePlatformApplicationRequestBuilder(Platform.GCM, name, apiKey);
    }

    public CreatePlatformApplicationRequestBuilder createADMApp(String name, String clientSecret) {
        return new CreatePlatformApplicationRequestBuilder(Platform.ADM, name, clientSecret);
    }

    public CreatePlatformEndpointRequestBuilder createEndpoint(PlatformApplicationArn arn, String token) {
        return new CreatePlatformEndpointRequestBuilder(arn, token);
    }

    public PublishRequestBuilder publish(String message) {
        return new PublishRequestBuilder(message);
    }

    public PublishRequestBuilder publish(PublishMessage message) {
        return new PublishRequestBuilder(message);
    }
}
