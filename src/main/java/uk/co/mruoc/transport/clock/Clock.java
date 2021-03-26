package uk.co.mruoc.transport.clock;

import lombok.AllArgsConstructor;

import java.time.Duration;

@AllArgsConstructor
public class Clock {

    private static final Duration DEFAULT_INTERVAL = Duration.ofHours(1);

    private Duration time;

    private final Duration interval;

    public Clock() {
        this(Duration.ZERO, DEFAULT_INTERVAL);
    }

    public Duration getTime() {
        return time;
    }

    public TimeElapsedEvent tick() {
        TimeElapsedEvent event = TimeElapsedEvent.builder()
                .start(time)
                .end(time.plus(interval))
                .build();
        this.time = event.getEnd();
        return event;
    }

}
