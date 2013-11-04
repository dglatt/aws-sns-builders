package com.mobilerq.amazonaws.sns;

import org.bouncycastle.openssl.PEMWriter;

import java.io.IOException;
import java.io.StringWriter;


/**
 * Serialize objects to Pem format
 */
class Pem {

    /**
     * Serialize objects to PEM format. Not all objects are supported. Supported objects are identical to those
     * supported by {@link PEMWriter#writeObject(Object)}.
     *
     * @param obj The object to serialize.
     * @return A string containing the object in PEM format.
     * @throws IllegalArgumentException when the object is not supported.
     */
    public static String from(final Object obj) {
        try {
            final StringWriter out = new StringWriter();
            final PEMWriter pem = new PEMWriter(out);
            pem.writeObject(obj);
            pem.close();
            return out.getBuffer().toString();
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to encode as PEM", e);
        }
    }

}
