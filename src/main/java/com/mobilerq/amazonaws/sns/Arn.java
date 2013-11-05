package com.mobilerq.amazonaws.sns;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Amazon Resource Names (ARNs) uniquely identify AWS resources.
 */
public class Arn {

    private static final String DELIM = ":";
    private static final String HEAD = "arn" + DELIM + "aws" + DELIM;
    private final String value;
    // cache arn components. never serialize
    private transient String[] components;

    public Arn(String value) {
        this.value = notNull(value, "arn");
    }

    public Arn(String service, String region, String account, String resource) {
        this.value = HEAD +
                notNull(service, "service") + DELIM +
                notNull(region, "region") + DELIM +
                notNull(account, "account") + DELIM +
                notNull(resource, "resource");
    }

    public String service() {
        return getComponents()[2];
    }

    public String region() {
        return getComponents()[3];
    }

    public String account() {
        return getComponents()[4];
    }

    public String resource() {
        final String[] components = getComponents();
        if (components.length == 6) {
            return components[5];
        } else {
            final StringBuilder out = new StringBuilder();
            final Iterator<String> itr = Arrays.asList(components).subList(5, components.length).iterator();
            while (itr.hasNext()) {
                out.append(itr.next());
                if (itr.hasNext()) {
                    out.append(DELIM);
                }
            }
            return out.toString();
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Arn arn = (Arn) o;

        if (!value.equals(arn.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    private String[] getComponents() {
        return (components == null) ? (components = value.split(DELIM)) : components;
    }

    private String notNull(String value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        } else {
            return value;
        }
    }
}
