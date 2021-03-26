package uk.co.mruoc.transport.vehicle.event;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;

public class VehicleEvents implements Iterable<VehicleEvent> {

    private final LinkedList<VehicleEvent> events;

    public VehicleEvents(VehicleEvent... events) {
        this(Arrays.asList(events));
    }

    public VehicleEvents(Collection<VehicleEvent> events) {
        this.events = new LinkedList<>(events);
    }

    @Override
    public Iterator<VehicleEvent> iterator() {
        return events.iterator();
    }

    public Duration getDuration() {
        return getLast().map(VehicleEvent::getTime).orElse(Duration.ZERO);
    }

    public Duration getLastUnloadTime() {
        return getLastUnload().map(VehicleEvent::getTime).orElse(Duration.ZERO);
    }

    public Optional<VehicleEvent> getLast() {
        if (events.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(events.getLast());
    }

    public Optional<VehicleEvent> getLastUnload() {
        return getLastOfType(VehicleEvent.Type.UNLOAD);
    }

    public Optional<VehicleEvent> getLastOfType(VehicleEvent.Type type) {
        return events.stream()
                .filter(event -> event.getType() == type)
                .reduce((prev, next) -> next);
    }

}
