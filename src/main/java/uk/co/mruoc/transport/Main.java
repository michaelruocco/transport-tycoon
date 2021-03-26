package uk.co.mruoc.transport;

import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.transport.config.Exercise2Config;
import uk.co.mruoc.transport.simulation.SimulationArguments;
import uk.co.mruoc.transport.simulation.SimulationRunner;
import uk.co.mruoc.transport.vehicle.event.VehicleEvents;

@Slf4j
public class Main {

    public static void main(String[] args) {
        try {
            run(args);
        } catch (ArgumentException e) {
            log.error(e.getMessage());
        }
    }

    private static void run(String[] args) {
        SimulationRunner runner = new SimulationRunner(new Exercise2Config());
        SimulationArguments arguments = MainSimulationArguments.parse(args);

        VehicleEvents events = runner.run(arguments);

        log.info("last unload at {}", events.getLastUnloadTime());
        log.info("total duration {}", events.getDuration());
        log.info("events file for destinations {} written at {}", arguments.getDestinations(), arguments.getFilePath());
    }

}
