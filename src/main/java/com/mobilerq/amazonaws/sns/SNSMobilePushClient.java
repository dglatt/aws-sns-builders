package com.mobilerq.amazonaws.sns;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

/**
 * A client for pushing messages to mobile apps using Amazon SNS.
 */
public class SNSMobilePushClient implements SNSMobilePush {
    private final static ObjectMapper mapper = new ObjectMapper();
    private final AmazonSNS snsClient;

    public SNSMobilePushClient(AmazonSNS amazonSNS) {
        snsClient = amazonSNS;
    }

    public ApplicationArn createAPNSApp(InputStream p12, char[] p12password) {
        return createPlatformApplicationARN(new CreatePlatformApplicationRequestBuilder(Platform.APNS, p12, p12password).getRequest());
    }

    public ApplicationArn createAPNSSandboxApp(InputStream p12, char[] p12password) {
        return createPlatformApplicationARN(new CreatePlatformApplicationRequestBuilder(Platform.APNS_SANDBOX, p12, p12password).getRequest());
    }

    public ApplicationArn createGCMApp(String name, String apiKey) {
        return createPlatformApplicationARN(new CreatePlatformApplicationRequestBuilder(Platform.GCM, name, apiKey).getRequest());
    }

    public ApplicationArn createADMApp(String name, String secret) {
        return createPlatformApplicationARN(new CreatePlatformApplicationRequestBuilder(Platform.ADM, name, secret).getRequest());
    }

    @Override
    public ApplicationArn createPlatformApplicationARN(CreatePlatformApplicationRequest request) {
        return new ApplicationArn(snsClient.createPlatformApplication(request).getPlatformApplicationArn());
    }

    @Override
    public EndpointArn createEndpoint(CreatePlatformEndpointRequest request) {
        return new EndpointArn(snsClient.createPlatformEndpoint(request).getEndpointArn());
    }

    @Override
    public void delete(EndpointArn arn) {
        snsClient.deleteEndpoint(new DeleteEndpointRequest().withEndpointArn(arn.toString()));
    }

    @Override
    public void delete(ApplicationArn arn) {
        snsClient.deletePlatformApplication(new DeletePlatformApplicationRequest().withPlatformApplicationArn(arn.toString()));
    }

    @Override
    public String publish(PublishRequest request) {
        return snsClient.publish(request).getMessageId();
    }

}
