package uk.co.mruoc.transport.map;

import java.time.Duration;

public class Exercise2TransportMap extends DefaultTransportMap {

    public Exercise2TransportMap() {
        super(portToWarehouseADuration());
    }

    private static Duration portToWarehouseADuration() {
        return Duration.ofHours(6);
    }

}
