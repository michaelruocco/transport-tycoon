package uk.co.mruoc.transport.control;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.transport.cargo.Cargo;
import uk.co.mruoc.transport.clock.Clock;
import uk.co.mruoc.transport.map.TransportMap;
import uk.co.mruoc.transport.location.terminal.Terminal;
import uk.co.mruoc.transport.vehicle.Vehicle;
import uk.co.mruoc.transport.vehicle.event.UnloadEvent;
import uk.co.mruoc.transport.vehicle.event.VehicleEventRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class UnloadService {

    private final VehicleEventRepository eventRepository;
    private final VehicleLocationRepository locationRepository;
    private final Clock clock;
    private final DepartService departService;

    public UnloadService(VehicleEventRepository eventRepository, VehicleLocationRepository locationRepository, Clock clock, TransportMap map) {
        this(eventRepository, locationRepository, clock, new DepartService(eventRepository, locationRepository, clock, map));
    }

    public void tryUnload(Vehicle vehicle) {
        Optional<Terminal> terminal = canUnload(vehicle);
        terminal.ifPresent(value -> unload(vehicle, value));
    }

    private Optional<Terminal> canUnload(Vehicle vehicle) {
        if (!vehicle.isLoaded() && !vehicle.isUnloading()) {
            log.debug("{} not loaded so cannot unload", vehicle.getDescription());
            return Optional.empty();
        }
        return locationRepository.isAtTerminal(vehicle);
    }

    private void unload(Vehicle vehicle, Terminal terminal) {
        if (vehicle.isUnloadingReadyToComplete(clock.getTime())) {
            completeUnloading(vehicle, terminal);
            return;
        }
        startUnloading(vehicle, terminal);
        if (vehicle.isUnloadingReadyToComplete(clock.getTime())) {
            completeUnloading(vehicle, terminal);
        }
    }

    private void startUnloading(Vehicle vehicle, Terminal terminal) {
        eventRepository.store(toUnloadEvent(terminal, vehicle));
        while (!vehicle.isEmpty()) {
            Cargo cargo = vehicle.startUnloading(clock.getTime());
            log.info("{} started unloaded cargo {} at {}", vehicle.getDescription(), cargo.getId(), terminal.getName());
            terminal.store(cargo);
        }
    }

    private void completeUnloading(Vehicle vehicle, Terminal terminal) {
        vehicle.completeUnloading();
        log.info("{} completed unloading at {}", vehicle.getDescription(), terminal.getName());
        Terminal destination = locationRepository.findHomeTerminal(vehicle);
        departService.depart(vehicle, terminal.getName(), destination.getName()); //TODO use publish unload event above so these services are not tied together
    }

    private UnloadEvent toUnloadEvent(Terminal terminal, Vehicle vehicle) {
        return UnloadEvent.builder()
                .location(terminal.getName())
                .vehicle(vehicle)
                .time(clock.getTime())
                .build();
    }

}
