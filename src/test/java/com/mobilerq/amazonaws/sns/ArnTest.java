package com.mobilerq.amazonaws.sns;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArnTest {

    String arn = "arn:aws:sns:us-mock-1:945321503486:app/APNS/12345419-dc2a-48d0-a45c-91ab76543222";

    @Test(expected = NullPointerException.class)
    public void testSingleConstructorWithNull() {
        new Arn(null);
    }

    @Test
    public void testMultiPartConstructorWithComplexResource() {
        Arn arn = new Arn("service", "region", "account", "resourcetype:resource");
        assertEquals("account", arn.account());
        assertEquals("region", arn.region());
        assertEquals("resourcetype:resource", arn.resource());
        assertEquals("service", arn.service());
        assertEquals("arn:aws:service:region:account:resourcetype:resource", arn.toString());
    }

    @Test
    public void testMultiPartConstructor() {
        Arn arn = new Arn("service", "region", "account", "resource");
        assertEquals("account", arn.account());
        assertEquals("region", arn.region());
        assertEquals("resource", arn.resource());
        assertEquals("service", arn.service());
        assertEquals("arn:aws:service:region:account:resource", arn.toString());
    }

    @Test(expected = NullPointerException.class)
    public void testMultiPartConstructorWithNullResource() {
        new Arn("service", "region", "account", null);
    }

    @Test(expected = NullPointerException.class)
    public void testMultiPartConstructorWithNullService() {
        new Arn(null, "region", "account", "resource");
    }

    @Test(expected = NullPointerException.class)
    public void testMultiPartConstructorWithNullRegion() {
        new Arn("service", null, "account", "resource");
    }

    @Test(expected = NullPointerException.class)
    public void testMultiPartConstructorWithNullAccount() {
        new Arn("service", "region", null, "resource");
    }

    @Test
    public void testService() throws Exception {
        assertEquals("sns", new Arn(arn).service());
    }

    @Test
    public void testRegion() throws Exception {
        assertEquals("us-mock-1", new Arn(arn).region());
    }

    @Test
    public void testAccount() throws Exception {
        assertEquals("945321503486", new Arn(arn).account());
    }

    @Test
    public void testResource() throws Exception {
        assertEquals("app/APNS/12345419-dc2a-48d0-a45c-91ab76543222", new Arn(arn).resource());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals(arn, new Arn(arn).toString());
    }

    @Test
    public void testEquals() throws Exception {
        String arn1 = "arn:aws:sns:us-mock-1:945321503481:app/APNS/12345419-dc2a-48d0-a45c-91ab76543221";
        String arn2 = "arn:aws:sns:us-mock-1:945321503481:app/APNS/12345419-dc2a-48d0-a45c-91ab76543222";
        Arn a1 = new Arn(arn1);
        assertTrue(a1.equals(a1));
        assertTrue(a1.equals(new Arn(arn1)));
        assertFalse(a1.equals(new Arn(arn2)));
        assertFalse(a1.equals(arn1));
        assertFalse(a1.equals(null));
    }

    @Test
    public void testHashCode() throws Exception {
        String arn1 = "arn:aws:sns:us-mock-1:945321503481:app/APNS/12345419-dc2a-48d0-a45c-91ab76543241";
        String arn2 = "arn:aws:sns:us-mock-1:945321503481:app/APNS/12345419-dc2a-48d0-a45c-91ab76543242";
        assertTrue(new Arn(arn1).hashCode() == new Arn(arn1).hashCode());
        assertFalse(new Arn(arn1).hashCode() == new Arn(arn2).hashCode());
    }
}
