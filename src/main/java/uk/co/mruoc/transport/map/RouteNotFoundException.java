package uk.co.mruoc.transport.map;

public class RouteNotFoundException extends RuntimeException {

    public RouteNotFoundException(String origin, String destination) {
        super(toMessage(origin, destination));
    }

    private static String toMessage(String origin, String destination) {
        return String.format("%s->%s", origin, destination);
    }

}
