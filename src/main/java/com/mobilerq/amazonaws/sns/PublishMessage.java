package com.mobilerq.amazonaws.sns;

import java.util.HashMap;

/**
 * A structured message. Use this with {@link PublishRequestBuilder} to build {@link com.amazonaws.services.sns.model.PublishRequest}
 * which support platform specific messages. Use {@link PublishMessageBuilder} to make a new PublishMessage.
 * See <a href="http://docs.aws.amazon.com/sns/latest/api/API_Publish.html">Publish API</a>
 */
public class PublishMessage extends HashMap<String, String> {

    private static final long serialVersionUID = 1l;

}
