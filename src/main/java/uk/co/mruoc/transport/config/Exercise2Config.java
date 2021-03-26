package uk.co.mruoc.transport.config;

import uk.co.mruoc.transport.map.Exercise2TransportMap;
import uk.co.mruoc.transport.vehicle.config.Exercise2VehicleConfig;

public class Exercise2Config extends DefaultSimulationConfig {

    public Exercise2Config() {
        super(new Exercise2TransportMap(), new Exercise2VehicleConfig());
    }

}
