package uk.co.mruoc.transport.location;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.transport.map.Leg;

import java.time.Duration;

@RequiredArgsConstructor
public class Journey implements Location {

    private final Leg leg;
    private final Duration travelled;

    public Journey(Leg leg) {
        this(leg, Duration.ZERO);
    }

    public Duration getTravelled() {
        return travelled;
    }

    public Journey travel(Duration duration) {
        return new Journey(leg, travelled.plus(duration));
    }

    public String getDestination() {
        return leg.getDestinationName();
    }

    public boolean isComplete() {
        return travelled.equals(leg.getDuration());
    }

    @Override
    public String getName() {
        return String.format("travelled %s along %s", travelled, leg.getName());
    }
}
