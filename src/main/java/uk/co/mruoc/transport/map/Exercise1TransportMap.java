package uk.co.mruoc.transport.map;

import java.time.Duration;

public class Exercise1TransportMap extends DefaultTransportMap {

    public Exercise1TransportMap() {
        super(portToWarehouseADuration());
    }

    private static Duration portToWarehouseADuration() {
        return Duration.ofHours(4);
    }

}
