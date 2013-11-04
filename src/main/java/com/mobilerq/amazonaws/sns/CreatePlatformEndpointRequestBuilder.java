package com.mobilerq.amazonaws.sns;

import com.amazonaws.services.sns.model.CreatePlatformEndpointRequest;

import java.util.HashMap;
import java.util.Map;

public class CreatePlatformEndpointRequestBuilder {

    private CreatePlatformEndpointRequest request = new CreatePlatformEndpointRequest();

    public CreatePlatformEndpointRequestBuilder(PlatformApplicationArn arn, String token) {
        platformApplicationArn(arn).token(token);
    }

    public CreatePlatformEndpointRequestBuilder platformApplicationArn(PlatformApplicationArn arn) {
        request.setPlatformApplicationArn(arn.toString());
        return this;
    }

    public CreatePlatformEndpointRequestBuilder token(String token) {
        request.setToken(token);
        return this;
    }

    public CreatePlatformEndpointRequestBuilder customUserData(String customUserData) {
        request.setCustomUserData(customUserData);
        return this;
    }

    public CreatePlatformEndpointRequestBuilder attribute(String name, String value) {
        Map<String, String> attributes = request.getAttributes();
        if (attributes == null) {
            attributes = new HashMap<String, String>();
            request.setAttributes(attributes);
        }
        attributes.put(name, value);
        return this;
    }

    public CreatePlatformEndpointRequest getRequest() {
        return request;
    }

}
