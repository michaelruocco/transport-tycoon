package uk.co.mruoc.transport.vehicle.event;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.transport.cargo.Cargo;
import uk.co.mruoc.transport.vehicle.Vehicle;

import java.time.Duration;
import java.util.Collection;

@RequiredArgsConstructor
public class AbstractVehicleEvent implements VehicleEvent {

    private final Type type;
    private final Duration time;
    private final String location;
    private final long vehicleId;
    private final Vehicle.Type vehicleType;
    private final Collection<Cargo> cargos;

    public AbstractVehicleEvent(Type type, Duration time, String location, Vehicle vehicle) {
        this(type, time, location, vehicle.getId(), vehicle.getType(), vehicle.getCargos());
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public Duration getTime() {
        return time;
    }

    @Override
    public long getVehicleId() {
        return vehicleId;
    }

    @Override
    public Vehicle.Type getVehicleType() {
        return vehicleType;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public Collection<Cargo> getCargos() {
        return cargos;
    }

}
