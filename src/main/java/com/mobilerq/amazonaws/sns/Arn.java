package com.mobilerq.amazonaws.sns;

/**
 * Amazon Resource Names (ARNs) uniquely identify AWS resources.
 */
public class Arn {
    private final String value;
    private transient String[] components;

    public Arn(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        this.value = value;
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
        return components.length == 6 ? components[5] : (String.format("%s:%s", components[5], components[6]));
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
        return (components == null) ? (components = value.split(":")) : components;
    }
}
