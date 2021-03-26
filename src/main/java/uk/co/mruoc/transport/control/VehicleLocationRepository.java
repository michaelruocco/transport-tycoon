package uk.co.mruoc.transport.control;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.transport.map.TransportMap;
import uk.co.mruoc.transport.location.Journey;
import uk.co.mruoc.transport.location.Location;
import uk.co.mruoc.transport.location.terminal.Terminal;
import uk.co.mruoc.transport.vehicle.Vehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class VehicleLocationRepository {

    private final TransportMap map;
    private final Map<Long, Location> locations = new HashMap<>();

    public void setAtHomeLocation(Vehicle vehicle) {
        Location location = findHomeTerminal(vehicle);
        update(vehicle, location);
    }

    public void update(Vehicle vehicle, Location location) {
        locations.put(vehicle.getId(), location);
        log.info("updated location of {} to {}", vehicle.getDescription(), location.getName());
    }

    public Terminal findHomeTerminal(Vehicle vehicle) {
        return map.findTerminal(vehicle.getHomeTerminal());
    }

    public Location findLocation(Vehicle vehicle) {
        Location location = locations.get(vehicle.getId());
        log.debug("return location {} for vehicle {}", location.getName(), vehicle.getDescription());
        return location;
    }

    public boolean isAtHome(Vehicle vehicle) {
        Location home = findHomeTerminal(vehicle);
        Location current = findLocation(vehicle);
        return current.hasName(home.getName());
    }

    public Optional<Terminal> isAtTerminal(Vehicle vehicle) {
        return Optional.ofNullable(findLocation(vehicle))
                .filter(location -> Terminal.class.isAssignableFrom(location.getClass()))
                .map(Terminal.class::cast);
    }

    public Optional<Journey> isOnJourney(Vehicle vehicle) {
        return Optional.ofNullable(findLocation(vehicle))
                .filter(location -> Journey.class.isAssignableFrom(location.getClass()))
                .map(Journey.class::cast);
    }

}
