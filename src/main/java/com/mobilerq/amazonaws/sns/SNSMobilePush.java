package com.mobilerq.amazonaws.sns;

import com.amazonaws.services.sns.model.CreatePlatformApplicationRequest;
import com.amazonaws.services.sns.model.CreatePlatformEndpointRequest;
import com.amazonaws.services.sns.model.PublishRequest;

import java.util.Map;

/**
 * An interface for pushing messages to mobile apps using Amazon SNS.
 */
public interface SNSMobilePush {

    /**
     * Create a Platform Application Arn for your app. Use {@link CreatePlatformApplicationRequestBuilder}.
     *
     * @param request A request to create a Platform Application Arn.
     * @return The platform application Arn for the app.
     */
    public PlatformApplicationArn createPlatformApplicationARN(CreatePlatformApplicationRequest request);

    /**
     * Create an Endpoint. This corresponds to an app on a device. Use {@link CreatePlatformEndpointRequestBuilder}.
     *
     * @param request
     * @return The endpoint Arn
     */
    public EndpointArn createEndpoint(CreatePlatformEndpointRequest request);

    /**
     * Delete the endpoint Arn
     *
     * @param endpointARN The Arn to delete
     */
    public void delete(EndpointArn endpointARN);

    /**
     * Delete the platform application Arn.
     *
     * @param platformApplicationArn The Arn to delete
     */
    public void delete(PlatformApplicationArn platformApplicationArn);

    /**
     * Publish a push message. Use {@link PublishRequestBuilder}.
     *
     * @param request The publish request.
     * @return A message ID
     */
    public String publish(PublishRequest request);
}
