package uk.co.mruoc.transport.vehicle.event;

import java.util.function.Consumer;

public interface VehicleEventWriter extends Consumer<Iterable<VehicleEvent>> {

    // intentionally blank

}
