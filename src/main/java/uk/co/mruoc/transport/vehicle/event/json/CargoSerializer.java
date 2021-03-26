package uk.co.mruoc.transport.vehicle.event.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import uk.co.mruoc.transport.cargo.Cargo;

import java.io.IOException;

public class CargoSerializer extends StdSerializer<Cargo> {

    protected CargoSerializer() {
        super(Cargo.class);
    }

    @Override
    public void serialize(Cargo cargo, JsonGenerator json, SerializerProvider provider) throws IOException {
        json.writeStartObject();
        json.writeNumberField("cargo_id", cargo.getId());
        json.writeStringField("destination", cargo.getDestination());
        json.writeStringField("origin", cargo.getOrigin());
        json.writeEndObject();
    }

}
