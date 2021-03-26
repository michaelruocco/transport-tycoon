package uk.co.mruoc.transport.control;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.transport.clock.Clock;
import uk.co.mruoc.transport.clock.TimeElapsedEvent;
import uk.co.mruoc.transport.map.TransportMap;
import uk.co.mruoc.transport.vehicle.Vehicle;
import uk.co.mruoc.transport.vehicle.event.VehicleEventRepository;

import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
public class VehicleService {

    private final Collection<Vehicle> vehicles;
    private final VehicleLocationRepository vehicleLocations;
    private final LoadService loadService;
    private final DepartService departService;
    private final TravelService travelService;
    private final UnloadService unloadService;

    @Builder
    public VehicleService(Collection<Vehicle> vehicles,
                          Clock clock,
                          TransportMap map,
                          VehicleEventRepository eventRepository) {
        this(vehicles, new VehicleLocationRepository(map), clock, map, eventRepository);
    }

    public VehicleService(Collection<Vehicle> vehicles,
                          VehicleLocationRepository locationRepository,
                          Clock clock,
                          TransportMap map,
                          VehicleEventRepository eventRepository) {
        this(vehicles,
                locationRepository,
                new LoadService(eventRepository, locationRepository, clock),
                new DepartService(eventRepository, locationRepository, clock, map),
                new TravelService(eventRepository, locationRepository, clock, map),
                new UnloadService(eventRepository, locationRepository, clock, map)
        );
    }

    public void setAllAtHome() {
        vehicles.forEach(vehicleLocations::setAtHomeLocation);
    }

    public void startTurn() {
        log.debug("starting turn");
        vehicles.forEach(loadService::tryLoad);
        vehicles.forEach(departService::tryDepart);
    }

    public void endTurn(TimeElapsedEvent event) {
        log.info("ending turn {}", event);
        vehicles.forEach(vehicle -> travelService.tryTravel(vehicle, event.getElapsed()));
        vehicles.forEach(unloadService::tryUnload);
    }

    public boolean allAtHome() {
        return vehicles.stream().allMatch(vehicleLocations::isAtHome);
    }

}
