package uk.co.mruoc.transport.clock;

import lombok.Builder;
import lombok.Data;

import java.time.Duration;

@Builder
@Data
public class TimeElapsedEvent {

    private final Duration start;
    private final Duration end;

    public Duration getElapsed() {
        return end.minus(start);
    }

}
