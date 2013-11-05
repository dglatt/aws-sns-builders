package com.mobilerq.amazonaws.sns;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.sns.model.DeletePlatformApplicationRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class DeletePlatformApplicationRequestBuilderTest {

    @Test
    public void testConstructor() throws Exception {
        PlatformApplicationArn arn1 = new PlatformApplicationArn("a1");
        DeletePlatformApplicationRequest r = new DeletePlatformApplicationRequestBuilder(arn1)
                .getRequest();
        assertEquals(arn1.toString(), r.getPlatformApplicationArn());
    }

    @Test
    public void testPlatformApplicationArn() throws Exception {
        PlatformApplicationArn arn1 = new PlatformApplicationArn("a1");
        PlatformApplicationArn arn2 = new PlatformApplicationArn("a2");
        DeletePlatformApplicationRequest r = new DeletePlatformApplicationRequestBuilder(arn1)
                .platformApplicationArn(arn2)
                .getRequest();
        assertEquals(arn2.toString(), r.getPlatformApplicationArn());
    }

    @Test
    public void testRequestCredentials() throws Exception {
        PlatformApplicationArn arn1 = new PlatformApplicationArn("a1");
        AWSCredentials mockCredentials = mock(AWSCredentials.class);
        DeletePlatformApplicationRequest r = new DeletePlatformApplicationRequestBuilder(arn1)
                .requestCredentials(mockCredentials)
                .getRequest();
        assertEquals(mockCredentials, r.getRequestCredentials());
    }

    @Test
    public void testGetRequest() throws Exception {
        PlatformApplicationArn arn1 = new PlatformApplicationArn("a1");
        PlatformApplicationArn arn2 = new PlatformApplicationArn("a2");
        AWSCredentials mockCredentials = mock(AWSCredentials.class);
        DeletePlatformApplicationRequest r = new DeletePlatformApplicationRequestBuilder(arn1)
                .requestCredentials(mockCredentials)
                .platformApplicationArn(arn2)
                .getRequest();
        assertEquals(mockCredentials, r.getRequestCredentials());
        assertEquals(arn2.toString(), r.getPlatformApplicationArn());
    }
}
