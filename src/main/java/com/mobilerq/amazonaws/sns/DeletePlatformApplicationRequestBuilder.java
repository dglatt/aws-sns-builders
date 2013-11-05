package com.mobilerq.amazonaws.sns;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.sns.model.DeletePlatformApplicationRequest;

/**
 * The DeletePlatformApplication action deletes a platform application object for one of the supported push
 * notification services, such as APNS and GCM.
 */
public class DeletePlatformApplicationRequestBuilder {
    private DeletePlatformApplicationRequest request = new DeletePlatformApplicationRequest();

    public DeletePlatformApplicationRequestBuilder(PlatformApplicationArn arn) {
        platformApplicationArn(arn);
    }

    public DeletePlatformApplicationRequestBuilder platformApplicationArn(PlatformApplicationArn arn) {
        request.setPlatformApplicationArn(arn.toString());
        return this;
    }

    public DeletePlatformApplicationRequestBuilder requestCredentials(AWSCredentials credentials) {
        request.setRequestCredentials(credentials);
        return this;
    }

    public DeletePlatformApplicationRequest getRequest() {
        return request;
    }

}
