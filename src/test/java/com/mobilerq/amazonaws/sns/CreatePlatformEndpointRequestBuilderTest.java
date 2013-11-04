package com.mobilerq.amazonaws.sns;

import com.amazonaws.services.sns.model.CreatePlatformEndpointRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreatePlatformEndpointRequestBuilderTest {

    @Test
    public void testARNAndTokenConstructor() throws Exception {
        CreatePlatformEndpointRequest r = new CreatePlatformEndpointRequestBuilder(new PlatformApplicationArn("a1"), "t1")
                .getRequest();
        assertEquals("a1", r.getPlatformApplicationArn());
        assertEquals("t1", r.getToken());
    }

    @Test
    public void testPlatformApplicationArn() throws Exception {
        CreatePlatformEndpointRequest r = new CreatePlatformEndpointRequestBuilder(new PlatformApplicationArn("a1"), "t1")
                .platformApplicationArn(new PlatformApplicationArn("a2"))
                .getRequest();
        assertEquals("a2", r.getPlatformApplicationArn());
    }

    @Test
    public void testToken() throws Exception {
        CreatePlatformEndpointRequest r = new CreatePlatformEndpointRequestBuilder(new PlatformApplicationArn("a1"), "t1")
                .token("t2")
                .getRequest();
        assertEquals("t2", r.getToken());
    }

    @Test
    public void testCustomUserData() throws Exception {
        CreatePlatformEndpointRequest r = new CreatePlatformEndpointRequestBuilder(new PlatformApplicationArn("a1"), "t1")
                .customUserData("c1")
                .getRequest();
        assertEquals("c1", r.getCustomUserData());
    }

    @Test
    public void testAttribute() throws Exception {
        CreatePlatformEndpointRequest r = new CreatePlatformEndpointRequestBuilder(new PlatformApplicationArn("a1"), "t1")
                .attribute("ak1", "av1")
                .getRequest();
        assertEquals("av1", r.getAttributes().get("ak1"));
    }

    @Test
    public void testGetRequest() throws Exception {
        CreatePlatformEndpointRequest r = new CreatePlatformEndpointRequestBuilder(new PlatformApplicationArn("a1"), "t1")
                .attribute("ak1", "av1")
                .customUserData("c1")
                .platformApplicationArn(new PlatformApplicationArn("a2"))
                .token("t2")
                .getRequest();
        assertEquals("av1", r.getAttributes().get("ak1"));
        assertEquals("a2", r.getPlatformApplicationArn());
        assertEquals("c1", r.getCustomUserData());
        assertEquals("t2", r.getToken());
    }
}
