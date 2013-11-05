package com.mobilerq.amazonaws.sns;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.sns.model.DeletePlatformApplicationRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class DeletePlatformApplicationRequestBuilderTest {

    @Test
    public void testConstructor() throws Exception {
        ApplicationArn arn1 = new ApplicationArn("a1");
        DeletePlatformApplicationRequest r = new DeletePlatformApplicationRequestBuilder(arn1)
                .getRequest();
        assertEquals(arn1.toString(), r.getPlatformApplicationArn());
    }

    @Test
    public void testPlatformApplicationArn() throws Exception {
        ApplicationArn arn1 = new ApplicationArn("a1");
        ApplicationArn arn2 = new ApplicationArn("a2");
        DeletePlatformApplicationRequest r = new DeletePlatformApplicationRequestBuilder(arn1)
                .platformApplicationArn(arn2)
                .getRequest();
        assertEquals(arn2.toString(), r.getPlatformApplicationArn());
    }

    @Test
    public void testRequestCredentials() throws Exception {
        ApplicationArn arn1 = new ApplicationArn("a1");
        AWSCredentials mockCredentials = mock(AWSCredentials.class);
        DeletePlatformApplicationRequest r = new DeletePlatformApplicationRequestBuilder(arn1)
                .requestCredentials(mockCredentials)
                .getRequest();
        assertEquals(mockCredentials, r.getRequestCredentials());
    }

    @Test
    public void testGetRequest() throws Exception {
        ApplicationArn arn1 = new ApplicationArn("a1");
        ApplicationArn arn2 = new ApplicationArn("a2");
        AWSCredentials mockCredentials = mock(AWSCredentials.class);
        DeletePlatformApplicationRequest r = new DeletePlatformApplicationRequestBuilder(arn1)
                .requestCredentials(mockCredentials)
                .platformApplicationArn(arn2)
                .getRequest();
        assertEquals(mockCredentials, r.getRequestCredentials());
        assertEquals(arn2.toString(), r.getPlatformApplicationArn());
    }
}
