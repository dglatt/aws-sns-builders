package com.mobilerq.amazonaws.sns;

import java.io.InputStream;

public class SNSRequests {

    public static CreatePlatformApplicationRequestBuilder createAPNSApp(InputStream p12, char[] p12Password) {
        return new CreatePlatformApplicationRequestBuilder(Platform.APNS, p12, p12Password);
    }

    public static CreatePlatformApplicationRequestBuilder createAPNSSandboxApp(InputStream p12, char[] p12Password) {
        return new CreatePlatformApplicationRequestBuilder(Platform.APNS_SANDBOX, p12, p12Password);
    }

    public static CreatePlatformApplicationRequestBuilder createGCMApp(String name, String apiKey) {
        return new CreatePlatformApplicationRequestBuilder(Platform.GCM, name, apiKey);
    }

    public static CreatePlatformApplicationRequestBuilder createADMApp(String name, String clientSecret) {
        return new CreatePlatformApplicationRequestBuilder(Platform.ADM, name, clientSecret);
    }

    public static CreatePlatformEndpointRequestBuilder createEndpoint(PlatformApplicationArn arn, String token) {
        return new CreatePlatformEndpointRequestBuilder(arn, token);
    }

    public static PublishRequestBuilder publish(String message) {
        return new PublishRequestBuilder(message);
    }

    public static PublishRequestBuilder publish(PublishMessage message) {
        return new PublishRequestBuilder(message);
    }
}
