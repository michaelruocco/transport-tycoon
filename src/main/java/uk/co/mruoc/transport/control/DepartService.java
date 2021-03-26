package uk.co.mruoc.transport.control;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.transport.clock.Clock;
import uk.co.mruoc.transport.map.Leg;
import uk.co.mruoc.transport.map.Route;
import uk.co.mruoc.transport.map.TransportMap;
import uk.co.mruoc.transport.location.Journey;
import uk.co.mruoc.transport.location.terminal.Terminal;
import uk.co.mruoc.transport.vehicle.Vehicle;
import uk.co.mruoc.transport.vehicle.event.DepartEvent;
import uk.co.mruoc.transport.vehicle.event.VehicleEventRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class DepartService {

    private final VehicleEventRepository eventRepository;
    private final VehicleLocationRepository locationRepository;
    private final Clock clock;
    private final TransportMap map;

    public void tryDepart(Vehicle vehicle) {
        Optional<Terminal> terminal = canDepart(vehicle);
        terminal.ifPresent(value -> depart(vehicle, value.getName(), vehicle.getDestination()));
    }

    private Optional<Terminal> canDepart(Vehicle vehicle) {
        return locationRepository.isAtTerminal(vehicle)
                .flatMap(terminal -> canDepart(vehicle, terminal));
    }

    private Optional<Terminal> canDepart(Vehicle vehicle, Terminal terminal) {
        if (!vehicle.isLoaded()) {
            log.debug("{} is not loaded so cannot depart from {}", vehicle.getDescription(), terminal.getName());
            return Optional.empty();
        }
        return Optional.of(terminal);
    }

    public void depart(Vehicle vehicle, String origin, String destination) {
        Route route = map.findRoute(origin, destination);
        Leg leg = route.findLegWithOrigin(origin);
        log.info("{} departing on {}", vehicle.getDescription(), leg.getName());
        Journey journey = new Journey(leg);
        locationRepository.update(vehicle, journey);
        eventRepository.store(toDepartEvent(vehicle, origin, journey.getDestination()));
    }

    private DepartEvent toDepartEvent(Vehicle vehicle, String origin, String destination) {
        return DepartEvent.builder()
                .location(origin)
                .destination(destination)
                .vehicle(vehicle)
                .time(clock.getTime())
                .build();
    }

}
