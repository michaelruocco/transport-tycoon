package uk.co.mruoc.transport.config;

import uk.co.mruoc.transport.map.Exercise1TransportMap;
import uk.co.mruoc.transport.vehicle.config.Exercise1VehicleConfig;

public class Exercise1Config extends DefaultSimulationConfig {

    public Exercise1Config() {
        super(new Exercise1TransportMap(), new Exercise1VehicleConfig());
    }

}
