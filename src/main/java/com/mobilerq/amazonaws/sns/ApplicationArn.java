package com.mobilerq.amazonaws.sns;

/**
 * An ARN for an SNS platform application object.
 */
public class ApplicationArn extends Arn {
    public ApplicationArn(String value) {
        super(value);
    }

    public ApplicationArn(String service, String region, String account, String resource) {
        super(service, region, account, resource);
    }
}
