package com.mobilerq.amazonaws.sns;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PublishMessageBuilderTest {

    @Test
    public void testConstructor() throws Exception {
        PublishMessage msg = new PublishMessageBuilder("m1").getMessage();
        assertEquals("m1", msg.get("default"));
    }

    @Test
    public void testPlatformMessage() throws Exception {
        Map<String, Object> aps = new MapBuilder<String, Object>()
                .put("aps", new MapBuilder<String, String>()
                        .put("alert", "me")
                        .build())
                .build();
        PublishMessage msg = new PublishMessageBuilder("m1")
                .platformMessage(Platform.APNS, aps)
                .getMessage();
        assertEquals("{\"aps\":{\"alert\":\"me\"}}", msg.get(Platform.APNS.name()));
    }

    @Test
    public void testPutObject() throws Exception {
        String message = "message";
        Object obj = new MapBuilder()
                .put("test", "this")
                .build();
        PublishMessage msg = new PublishMessageBuilder(message)
                .putObject("obj", obj)
                .putObject("str", "string")
                .getMessage();
        assertEquals(message, msg.get("default"));
        assertEquals("{\"test\":\"this\"}", msg.get("obj"));
        assertEquals("string", msg.get("str"));
    }

    @Test
    public void testPut() throws Exception {
        String message = "message";
        PublishMessage msg = new PublishMessageBuilder(message)
                .put("test", "this")
                .getMessage();
        assertEquals(message, msg.get("default"));
        assertEquals("this", msg.get("test"));
    }

    @Test
    public void testDefaultMessage() throws Exception {
        String message = "message";
        PublishMessage msg = new PublishMessageBuilder("blah")
                .defaultMessage(message)
                .getMessage();
        assertEquals(message, msg.get("default"));
    }

    @Test
    public void testGetMessage() throws Exception {
        String message = "message";
        Map<String, Object> aps = new MapBuilder<String, Object>()
                .put("aps", new MapBuilder<String, String>()
                        .put("alert", "me")
                        .build())
                .build();
        PublishMessage msg = new PublishMessageBuilder("blah")
                .defaultMessage(message)
                .platformMessage(Platform.APNS, aps)
                .put("extra", "value")
                .getMessage();
        assertEquals(message, msg.get("default"));
        assertEquals("{\"aps\":{\"alert\":\"me\"}}", msg.get(Platform.APNS.name()));
        assertEquals("value", msg.get("extra"));
    }
}
