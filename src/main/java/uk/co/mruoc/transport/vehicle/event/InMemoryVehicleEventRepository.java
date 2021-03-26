package uk.co.mruoc.transport.vehicle.event;

import java.util.LinkedList;

public class InMemoryVehicleEventRepository implements VehicleEventRepository {

    private final LinkedList<VehicleEvent> events = new LinkedList<>();

    @Override
    public void clear() {
        events.clear();
    }

    @Override
    public void store(VehicleEvent event) {
        events.add(event);
    }

    @Override
    public VehicleEvents getAll() {
        return new VehicleEvents(events);
    }

}
