package uk.co.mruoc.transport.vehicle;

import lombok.Getter;

import java.time.Duration;

import static uk.co.mruoc.transport.vehicle.Vehicle.Type.SHIP;

@Getter
public class LargeShip extends Vehicle {

    private static final Duration LOAD_DURATION = Duration.ofHours(1);
    private static final int MAX_CAPACITY = 4;

    public LargeShip(long id, String homeTerminal) {
        super(id, SHIP, LOAD_DURATION, homeTerminal, MAX_CAPACITY);
    }

}
