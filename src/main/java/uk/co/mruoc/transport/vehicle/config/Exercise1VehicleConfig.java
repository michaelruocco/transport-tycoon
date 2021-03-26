package uk.co.mruoc.transport.vehicle.config;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.transport.vehicle.Vehicle;
import uk.co.mruoc.transport.vehicle.VehicleFactory;

import java.util.Arrays;
import java.util.Collection;

@RequiredArgsConstructor
public class Exercise1VehicleConfig implements VehicleConfig {

    private final Collection<Vehicle> vehicles;

    public Exercise1VehicleConfig() {
        this(buildVehicles(new VehicleFactory()));
    }

    @Override
    public Collection<Vehicle> vehicles() {
        return vehicles;
    }

    private static Collection<Vehicle> buildVehicles(VehicleFactory factory) {
        return Arrays.asList(
                factory.ship(),
                factory.truck(),
                factory.truck()
        );
    }

}
