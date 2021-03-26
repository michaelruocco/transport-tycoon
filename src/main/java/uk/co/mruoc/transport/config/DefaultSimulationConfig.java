package uk.co.mruoc.transport.config;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.transport.simulation.SimulationController;
import uk.co.mruoc.transport.simulation.Simulation;
import uk.co.mruoc.transport.clock.Clock;
import uk.co.mruoc.transport.control.VehicleService;
import uk.co.mruoc.transport.map.TransportMap;
import uk.co.mruoc.transport.vehicle.config.VehicleConfig;
import uk.co.mruoc.transport.vehicle.event.InMemoryVehicleEventRepository;
import uk.co.mruoc.transport.vehicle.event.VehicleEventRepository;

@RequiredArgsConstructor
public class DefaultSimulationConfig implements SimulationConfig {

    private final Clock clock = new Clock();
    private final VehicleEventRepository eventRepository = new InMemoryVehicleEventRepository();

    private final TransportMap map;
    private final VehicleConfig vehicleConfig;

    @Override
    public Simulation buildSimulation() {
        return new Simulation(buildController());
    }

    private SimulationController buildController() {
        return SimulationController.builder()
                .clock(clock)
                .map(map)
                .eventRepository(eventRepository)
                .vehicleService(buildVehiclesController())
                .build();
    }

    private VehicleService buildVehiclesController() {
        return VehicleService.builder()
                .map(map)
                .vehicles(vehicleConfig.vehicles())
                .eventRepository(eventRepository)
                .clock(clock)
                .build();
    }

}
