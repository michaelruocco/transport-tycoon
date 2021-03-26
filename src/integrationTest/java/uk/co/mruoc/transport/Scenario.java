package uk.co.mruoc.transport;

import lombok.Getter;

import java.time.Duration;

@Getter
public class Scenario {

    private final String destinations;
    private final Duration expectedLastUnloadTime;
    private final Duration expectedDuration;

    public Scenario(String destinations, Duration expectedLastUnloadTime, Duration expectedDuration) {
        this.destinations = destinations;
        this.expectedLastUnloadTime = expectedLastUnloadTime;
        this.expectedDuration = expectedDuration;
    }

}
