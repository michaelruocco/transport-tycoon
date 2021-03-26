package uk.co.mruoc.transport.control;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.transport.clock.Clock;
import uk.co.mruoc.transport.map.TransportMap;
import uk.co.mruoc.transport.location.Journey;
import uk.co.mruoc.transport.location.terminal.Terminal;
import uk.co.mruoc.transport.vehicle.Vehicle;
import uk.co.mruoc.transport.vehicle.event.ArriveEvent;
import uk.co.mruoc.transport.vehicle.event.VehicleEventRepository;

import java.time.Duration;

@RequiredArgsConstructor
@Slf4j
public class TravelService {

    private final VehicleEventRepository eventRepository;
    private final VehicleLocationRepository locationRepository;
    private final Clock clock;
    private final TransportMap map;

    public void tryTravel(Vehicle vehicle, Duration duration) {
        locationRepository.isOnJourney(vehicle)
                .ifPresent(journey -> travel(vehicle, journey, duration));
    }

    private void travel(Vehicle vehicle, Journey journey, Duration duration) {
        Journey updated = journey.travel(duration);
        log.info("{} has travelled {} to {} complete {}", vehicle.getDescription(), updated.getTravelled(), updated.getDestination(), updated.isComplete());
        if (updated.isComplete()) {
            arrive(vehicle, updated);
        } else {
            locationRepository.update(vehicle, updated);
        }
    }

    private void arrive(Vehicle vehicle, Journey journey) {
        Terminal terminal = map.findTerminal(journey.getDestination());
        locationRepository.update(vehicle, terminal);
        eventRepository.store(toArriveEvent(vehicle, terminal));
    }

    private ArriveEvent toArriveEvent( Vehicle vehicle, Terminal terminal) {
        return ArriveEvent.builder()
                .location(terminal.getName())
                .vehicle(vehicle)
                .time(clock.getTime())
                .build();
    }

}
