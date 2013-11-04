package com.mobilerq.amazonaws.sns;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * Build messages for {@link PublishRequestBuilder}. See <a href="http://docs.aws.amazon.com/sns/latest/api/API_Publish.html">Publish API</a>
 */
public class PublishMessageBuilder {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String DEFAULT_MESSAGE_KEY = "default";
    private PublishMessage message = new PublishMessage();

    /**
     * Create a new PublishMessageBuilder with a default message.
     *
     * @param defaultMessage The default message.
     */
    public PublishMessageBuilder(String defaultMessage) {
        defaultMessage(defaultMessage);
    }

    /**
     * Set the custom message for a platform. See
     * <a href="http://docs.aws.amazon.com/sns/latest/dg/PublishTopic.html#sns-message-formatting-by-protocol">Create Different Messages for Each Protocol</a>.
     *
     * @param platform        The target platform
     * @param platformMessage The platform specific message
     * @return This builder
     */
    public PublishMessageBuilder platformMessage(Platform platform, Map<String, Object> platformMessage) {
        return putObject(platform.name(), platformMessage);
    }

    /**
     * Put an object value in the message. The value will be serialized to JSON using a Jackson {@link ObjectMapper}.
     *
     * @param name  The name of the value.
     * @param value The value
     * @return This builder.
     * @throws IllegalArgumentException when a JSON processing exception occurs.
     */
    public PublishMessageBuilder putObject(String name, Object value) {
        if (value instanceof CharSequence) {
            return put(name, (CharSequence) value);
        } else {
            try {
                return put(name, mapper.writeValueAsString(value));
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException("Value object does not map to json", e);
            }
        }
    }

    /**
     * Put a string value in the message.
     *
     * @param name  The name of the value.
     * @param value The value
     * @return This builder.
     */
    public PublishMessageBuilder put(String name, CharSequence value) {
        message.put(name, value.toString());
        return this;
    }

    /**
     * Set the default message.
     *
     * @param defaultMessage The default message.
     * @return This builder.
     */
    public PublishMessageBuilder defaultMessage(String defaultMessage) {
        return put(DEFAULT_MESSAGE_KEY, defaultMessage);
    }

    /**
     * Get the built message.
     *
     * @return The message.
     */
    public PublishMessage getMessage() {
        return message;
    }
}
