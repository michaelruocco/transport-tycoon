package uk.co.mruoc.transport.vehicle.event;

import uk.co.mruoc.transport.cargo.Cargo;
import uk.co.mruoc.transport.vehicle.Vehicle;

import java.time.Duration;
import java.util.Collection;

public interface VehicleEvent {

    VehicleEvent.Type getType();

    Duration getTime();

    long getVehicleId();

    Vehicle.Type getVehicleType();

    String getLocation();

    Collection<Cargo> getCargos();

    enum Type {
        LOAD,
        DEPART,
        ARRIVE,
        UNLOAD
    }

}
