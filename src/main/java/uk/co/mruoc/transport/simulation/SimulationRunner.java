package uk.co.mruoc.transport.simulation;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.transport.cargo.CargosFactory;
import uk.co.mruoc.transport.config.SimulationConfig;
import uk.co.mruoc.transport.vehicle.event.VehicleEventWriter;
import uk.co.mruoc.transport.vehicle.event.VehicleEvents;
import uk.co.mruoc.transport.vehicle.event.json.JsonVehicleEventWriter;

@RequiredArgsConstructor
public class SimulationRunner {

    private final SimulationConfig config;
    private final CargosFactory cargosFactory;

    public SimulationRunner(SimulationConfig config) {
        this(config, new CargosFactory());
    }

    public VehicleEvents run(SimulationArguments arguments) {
        Simulation simulation = config.buildSimulation();
        VehicleEventWriter eventWriter = new JsonVehicleEventWriter(arguments.getFilePath());
        VehicleEvents events = simulation.apply(cargosFactory.build(arguments.getDestinations()));
        eventWriter.accept(events);
        return events;
    }

}
