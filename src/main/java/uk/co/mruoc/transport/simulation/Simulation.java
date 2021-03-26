package uk.co.mruoc.transport.simulation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.transport.cargo.Cargos;
import uk.co.mruoc.transport.vehicle.event.VehicleEvents;

import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
public class Simulation implements Function<Cargos, VehicleEvents> {

    private final SimulationController controller;

    @Override
    public VehicleEvents apply(Cargos cargos) {
        log.info("simulation started");
        controller.init(cargos);
        while (!controller.isComplete(cargos)) {
            controller.update();
        }
        log.info("simulation ending");
        return controller.getAllEvents();
    }


}
