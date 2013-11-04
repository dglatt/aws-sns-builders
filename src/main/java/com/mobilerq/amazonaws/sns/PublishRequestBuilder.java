package com.mobilerq.amazonaws.sns;

import com.amazonaws.services.sns.model.PublishRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Build requests for {@link com.amazonaws.services.sns.AmazonSNS#publish(PublishRequest)}.
 * See <a href="http://docs.aws.amazon.com/sns/latest/api/API_Publish.html">Publish API</a>
 */
public class PublishRequestBuilder {

    private static final ObjectMapper mapper = new ObjectMapper();
    private PublishRequest request = new PublishRequest();

    /**
     * Create a new builder with a basic string message.
     *
     * @param message The message.
     */
    public PublishRequestBuilder(String message) {
        message(message);
    }

    /**
     * Create a new build message from a {@link PublishMessage} which supports custom messages for each platform.
     * See {@link PublishMessageBuilder} for more.
     *
     * @param message The message.
     */
    public PublishRequestBuilder(PublishMessage message) {
        message(message);
    }

    /**
     * Set the message. This replaces the existing message.
     *
     * @param message The message
     * @return This builder.
     */
    public PublishRequestBuilder message(String message) {
        request.setMessage(message);
        return this;
    }

    /**
     * Set the message. This replaces the existing message.
     *
     * @param message The message.
     * @return This builder.
     */

    public PublishRequestBuilder message(PublishMessage message) {
        try {
            return message(mapper.writeValueAsString(message)).messageStructure("json");
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Message does not map to json", e);
        }
    }

    /**
     * Set the messageStructure. The only valid value is "json".
     *
     * @param messageStructure The messageStructure value
     * @return this builder.
     */
    public PublishRequestBuilder messageStructure(String messageStructure) {
        request.setMessageStructure(messageStructure);
        return this;
    }

    /**
     * Set the targetArn to an EndpointArn.
     *
     * @param endpointArn the targetArn
     * @return This builder.
     */
    public PublishRequestBuilder targetArn(EndpointArn endpointArn) {
        return targetArn(endpointArn.toString());
    }

    /**
     * Set the targetArn to any ARN. This can be either a TopicArn or EndpointArn, but not both.
     *
     * @param targetArn the targetArn
     * @return This builder.
     */
    public PublishRequestBuilder targetArn(String targetArn) {
        request.setTargetArn(targetArn);
        return this;
    }

    /**
     * Set the topicArn. The topic you want to publish to.
     *
     * @param topicArn the topicArn
     * @return This builder.
     */
    public PublishRequestBuilder topicArn(String topicArn) {
        request.setTopicArn(topicArn);
        return this;
    }

    /**
     * Set the subject. Optional parameter to be used as the "Subject" line when the message is delivered to email
     * endpoints. This field will also be included, if present, in the standard JSON messages delivered to other
     * endpoints. Subjects must be ASCII text that begins with a letter, number, or punctuation mark; must not include
     * line breaks or control characters; and must be less than 100 characters long.
     *
     * @param subject The subject.
     * @return This builder
     */
    public PublishRequestBuilder subject(String subject) {
        request.setSubject(subject);
        return this;
    }

    /**
     * Get the built request.
     *
     * @return The request.
     */
    public PublishRequest getRequest() {
        return this.request;
    }

}
