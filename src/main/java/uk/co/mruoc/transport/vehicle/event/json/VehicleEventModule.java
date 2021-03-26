package uk.co.mruoc.transport.vehicle.event.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import uk.co.mruoc.transport.cargo.Cargo;
import uk.co.mruoc.transport.vehicle.event.VehicleEvent;

public class VehicleEventModule extends SimpleModule {

    public VehicleEventModule() {
        super("vehicle-event-module", Version.unknownVersion());

        addSerializer(VehicleEvent.class, new VehicleEventSerializer());
        addSerializer(Cargo.class, new CargoSerializer());
    }

}
