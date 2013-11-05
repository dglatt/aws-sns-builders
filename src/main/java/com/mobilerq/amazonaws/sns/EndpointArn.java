package com.mobilerq.amazonaws.sns;

/**
 * An ARN for an SNS endpoint object.
 */
public class EndpointArn extends Arn {
    public EndpointArn(String value) {
        super(value);
    }

    public EndpointArn(String service, String region, String account, String resource) {
        super(service, region, account, resource);
    }
}
