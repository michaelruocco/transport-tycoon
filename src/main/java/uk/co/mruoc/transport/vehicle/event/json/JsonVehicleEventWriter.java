package uk.co.mruoc.transport.vehicle.event.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.transport.vehicle.event.VehicleEvent;
import uk.co.mruoc.transport.vehicle.event.VehicleEventWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RequiredArgsConstructor
public class JsonVehicleEventWriter implements VehicleEventWriter {

    private final String filePath;
    private final ObjectMapper mapper;

    public JsonVehicleEventWriter(String filePath) {
        this(filePath, new ObjectMapper().registerModule(new VehicleEventModule()));
    }

    @Override
    public void accept(Iterable<VehicleEvent> events) {
        writeToFile(events);
    }

    private void writeToFile(Iterable<VehicleEvent> events) {
        cleanFile();
        tryWrite(toJson(events));
    }

    private String toJson(Iterable<VehicleEvent> events) {
        return StreamSupport.stream(events.spliterator(), false)
                .map(this::toJson)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String toJson(VehicleEvent event) {
        try {
            return mapper.writeValueAsString(event);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void cleanFile() {
        try {
            Path path = Paths.get(filePath);
            Files.deleteIfExists(path);
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void tryWrite(String json) {
        try (FileOutputStream stream = new FileOutputStream(filePath, true)) {
            write(stream, json);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void write(FileOutputStream stream, String json) throws IOException {
        log.info("writing json events to {}", filePath);
        stream.write(json.getBytes(StandardCharsets.UTF_8));
    }

}
