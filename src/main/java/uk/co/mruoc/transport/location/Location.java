package uk.co.mruoc.transport.location;

public interface Location {

    String getName();

    default boolean hasName(String otherName) {
        return getName().equals(otherName);
    }

}
