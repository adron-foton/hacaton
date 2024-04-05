package org.server;

public class GenericExample <Type> {

    private Type object;
    public GenericExample (Type object) {

    }

    public Type getType() {
        return this.object;
    }
}
