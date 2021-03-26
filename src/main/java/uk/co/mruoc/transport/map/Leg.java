package uk.co.mruoc.transport.map;

import lombok.Builder;
import uk.co.mruoc.transport.location.Location;

import java.time.Duration;

@Builder(toBuilder = true)
public class Leg {

    private final Location origin;
    private final Location destination;
    private final Type type;
    private final Duration duration;

    public static Leg.LegBuilder road() {
        return builder().type(Type.ROAD);
    }

    public static Leg.LegBuilder shippingLane() {
        return builder().type(Type.SHIPPING_LANE);
    }

    public Duration getDuration() {
        return duration;
    }

    public String getOriginName() {
        return origin.getName();
    }

    public String getDestinationName() {
        return destination.getName();
    }

    public String getName() {
        return String.format("%s from %s to %s",
                type.name(),
                origin.getName(),
                destination.getName()
        );
    }

    public Leg toReturnLeg() {
        return toBuilder()
                .origin(destination)
                .destination(origin)
                .build();
    }

    public enum Type {
        SHIPPING_LANE,
        ROAD
    }

}
