package uk.co.mruoc.transport.control;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.transport.cargo.Cargo;
import uk.co.mruoc.transport.clock.Clock;
import uk.co.mruoc.transport.location.terminal.Terminal;
import uk.co.mruoc.transport.vehicle.Vehicle;
import uk.co.mruoc.transport.vehicle.event.LoadEvent;
import uk.co.mruoc.transport.vehicle.event.VehicleEventRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class LoadService {

    private final VehicleEventRepository eventRepository;
    private final VehicleLocationRepository locationRepository;
    private final Clock clock;

    public void tryLoad(Vehicle vehicle) {
        Optional<Terminal> terminal = canLoad(vehicle);
        terminal.ifPresent(value -> load(vehicle, value));
    }

    private Optional<Terminal> canLoad(Vehicle vehicle) {
        if (vehicle.isUnloading()) {
            log.info("{} cannot be loaded as unloading is in progress", vehicle.getDescription());
            return Optional.empty();
        }
        return locationRepository.isAtTerminal(vehicle)
                .flatMap(terminal -> canLoad(vehicle, terminal));
    }

    private Optional<Terminal> canLoad(Vehicle vehicle, Terminal terminal) {
        if (!terminal.hasCargo() && !vehicle.isLoading()) {
            log.debug("no cargo to load for {} at {}", vehicle.getDescription(), terminal.getName());
            return Optional.empty();
        }
        return Optional.of(terminal);
    }

    public void load(Vehicle vehicle, Terminal terminal) {
        if (vehicle.isLoadingReadyToComplete(clock.getTime())) {
            completeLoading(vehicle, terminal);
            return;
        }
        startLoading(vehicle, terminal);
        if (vehicle.isLoadingReadyToComplete(clock.getTime())) {
            completeLoading(vehicle, terminal);
        }
    }

    private void startLoading(Vehicle vehicle, Terminal terminal) {
        while (vehicle.hasCapacityRemaining() && terminal.hasCargo()) {
            Cargo cargo = terminal.getNextCargo();
            vehicle.startLoading(cargo, clock.getTime());
            log.info("{} started loading cargo {} at {}", vehicle.getDescription(), cargo.getId(), terminal.getName());
        }
        eventRepository.store(toLoadEvent(vehicle, terminal));
    }

    private void completeLoading(Vehicle vehicle, Terminal terminal) {
        vehicle.completeLoading();
        log.info("{} completed loading at {}", vehicle.getDescription(), terminal.getName());
    }

    private LoadEvent toLoadEvent(Vehicle vehicle, Terminal terminal) {
        return LoadEvent.builder()
                .location(terminal.getName())
                .vehicle(vehicle)
                .time(clock.getTime())
                .build();
    }

}
