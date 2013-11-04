package com.mobilerq.amazonaws.sns;

import com.amazonaws.services.sns.model.PublishRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PublishRequestBuilderTest {

    @Test
    public void testConstructor() throws Exception {
        PublishRequest r = new PublishRequestBuilder("m1").getRequest();
        assertEquals("m1", r.getMessage());
        assertNull(r.getMessageStructure());
    }

    @Test
    public void testMessage() throws Exception {
        PublishRequest r = new PublishRequestBuilder("m1").message("m2").getRequest();
        assertEquals("m2", r.getMessage());
        assertNull(r.getMessageStructure());
    }

    @Test
    public void testPublishMessage() throws Exception {
        PublishMessage msg = new PublishMessageBuilder("m1").getMessage();
        PublishRequest r = new PublishRequestBuilder(msg).getRequest();
        assertEquals("{\"default\":\"m1\"}", r.getMessage());
        assertEquals("json", r.getMessageStructure());
    }

    @Test
    public void testMessageStructure() throws Exception {
        PublishRequest r = new PublishRequestBuilder("message")
                .messageStructure("testy")
                .getRequest();
        assertEquals("testy", r.getMessageStructure());
    }

    @Test
    public void testMessageStructure2() throws Exception {
        PublishRequest r = new PublishRequestBuilder(new PublishMessageBuilder("m1").getMessage())
                .messageStructure("testy")
                .getRequest();
        assertEquals("testy", r.getMessageStructure());
    }

    @Test
    public void testEndpointTargetArn() throws Exception {
        String arn = "arn:aws:sns:us-east-1:123456789012:endpoint/APNS/64d65215-f7d4-4ebc-8c45-c1237510e9dc/df7feec2-1aca-3f4f-a286-64e0c1427161";
        PublishRequest r = new PublishRequestBuilder("message")
                .targetArn(new EndpointArn(arn))
                .getRequest();
        assertEquals(arn, r.getTargetArn());
    }

    @Test
    public void testTargetArnString() throws Exception {
        String arn = "arn:aws:sns:us-east-1:123456789012:endpoint/APNS/64d65215-f7d4-4ebc-8c45-c1237510e9dc/df7feec2-1aca-3f4f-a286-64e0c1427161";
        PublishRequest r = new PublishRequestBuilder("message")
                .targetArn(arn)
                .getRequest();
        assertEquals(arn, r.getTargetArn());
    }

    @Test
    public void testTopicArn() throws Exception {
        String arn = "arn:aws:sns:us-east-1:970456703086:Blah";
        PublishRequest r = new PublishRequestBuilder("message")
                .topicArn(arn)
                .getRequest();
        assertEquals(arn, r.getTopicArn());
    }

    @Test
    public void testSubject() throws Exception {
        String subject = "test_subject";
        PublishRequest r = new PublishRequestBuilder("message")
                .subject(subject)
                .getRequest();
        assertEquals(subject, r.getSubject());
    }

    @Test
    public void testGetRequest() throws Exception {
        String targetArn = "arn:aws:sns:us-east-1:123456789012:endpoint/APNS/64d65215-f7d4-4ebc-8c45-c1237510e9dc/df7feec2-1aca-3f4f-a286-64e0c1427161";
        String topicArn = "arn:aws:sns:us-east-1:970456703086:Blah";
        String message = "message";
        String subject = "subject";
        String messageStruct = "messageStructure";
        PublishRequest r = new PublishRequestBuilder(message)
                .message(message)
                .messageStructure(messageStruct)
                .subject(subject)
                .targetArn(targetArn)
                .topicArn(topicArn)
                .getRequest();
        assertEquals(message, r.getMessage());
        assertEquals(messageStruct, r.getMessageStructure());
        assertEquals(subject, r.getSubject());
        assertEquals(targetArn, r.getTargetArn());
        assertEquals(topicArn, r.getTopicArn());
    }
}
