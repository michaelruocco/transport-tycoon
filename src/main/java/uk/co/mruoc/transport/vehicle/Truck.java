package uk.co.mruoc.transport.vehicle;

import lombok.Getter;

import java.time.Duration;

import static java.time.Duration.ZERO;
import static uk.co.mruoc.transport.vehicle.Vehicle.Type.TRUCK;

@Getter
public class Truck extends Vehicle {

    private static final Duration LOAD_DURATION = ZERO;
    private static final int MAX_CAPACITY = 1;

    public Truck(long id, String homeTerminal) {
        super(id, TRUCK, LOAD_DURATION, homeTerminal, MAX_CAPACITY);
    }

}
