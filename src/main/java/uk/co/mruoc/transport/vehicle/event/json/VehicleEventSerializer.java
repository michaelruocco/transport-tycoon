package uk.co.mruoc.transport.vehicle.event.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import uk.co.mruoc.transport.cargo.Cargo;
import uk.co.mruoc.transport.vehicle.event.DepartEvent;
import uk.co.mruoc.transport.vehicle.event.HandlingEvent;
import uk.co.mruoc.transport.vehicle.event.VehicleEvent;

import java.io.IOException;
import java.time.Duration;
import java.util.Optional;

public class VehicleEventSerializer extends StdSerializer<VehicleEvent> {

    protected VehicleEventSerializer() {
        super(VehicleEvent.class);
    }

    @Override
    public void serialize(VehicleEvent event, JsonGenerator json, SerializerProvider provider) throws IOException {
        json.writeStartObject();
        json.writeStringField("event", event.getType().name());
        json.writeNumberField("time", event.getTime().toHours());
        json.writeNumberField("transport_id", event.getVehicleId());
        json.writeStringField("kind", event.getVehicleType().name());
        json.writeStringField("location", event.getLocation());
        Optional<String> destination = extractDestination(event);
        if (destination.isPresent()) {
            json.writeStringField("destination", destination.get());
        }
        Optional<Duration> duration = extractDuration(event);
        if (duration.isPresent()) {
            json.writeNumberField("duration", duration.get().toHours());
        }
        json.writeArrayFieldStart("cargo");
        for (Cargo cargo : event.getCargos()) {
            provider.defaultSerializeValue(cargo, json);
        }
        json.writeEndArray();
        json.writeEndObject();
    }

    private Optional<String> extractDestination(VehicleEvent event) {
        if (event instanceof DepartEvent) {
            DepartEvent departEvent = (DepartEvent) event;
            return Optional.of(departEvent.getDestination());
        }
        return Optional.empty();
    }

    private Optional<Duration> extractDuration(VehicleEvent event) {
        if (event instanceof HandlingEvent) {
            HandlingEvent handlingEvent = (HandlingEvent) event;
            return Optional.of(handlingEvent.getDuration());
        }
        return Optional.empty();
    }


}
