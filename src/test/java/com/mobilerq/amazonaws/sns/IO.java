package com.mobilerq.amazonaws.sns;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IO {

    public static String getResourceAsString(String resource) throws IOException {
        return toString(IO.class.getResourceAsStream(resource));
    }

    public static String toString(InputStream in) throws IOException {
        return toString(new InputStreamReader(in));
    }

    public static String toString(InputStreamReader in) throws IOException {
        StringBuilder out = new StringBuilder();
        char[] buf = new char[8 * 1024];
        while (in.read(buf) != -1) {
            out.append(buf);
        }
        return out.toString();
    }
}
