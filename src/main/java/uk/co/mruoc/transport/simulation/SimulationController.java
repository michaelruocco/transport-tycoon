package uk.co.mruoc.transport.simulation;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.transport.cargo.Cargos;
import uk.co.mruoc.transport.clock.Clock;
import uk.co.mruoc.transport.control.VehicleService;
import uk.co.mruoc.transport.location.terminal.Terminals;
import uk.co.mruoc.transport.map.TransportMap;
import uk.co.mruoc.transport.vehicle.event.VehicleEventRepository;
import uk.co.mruoc.transport.vehicle.event.VehicleEvents;

@Builder
@Slf4j
public class SimulationController {

    private final Clock clock;
    private final TransportMap map;
    private final VehicleEventRepository eventRepository;
    private final VehicleService vehicleService;

    public void init(Cargos cargos) {
        eventRepository.clear();
        vehicleService.setAllAtHome();
        Terminals terminals = map.getTerminals();
        terminals.clearAllCargos();
        cargos.allocateToOrigins(terminals);
    }

    public void update() {
        vehicleService.startTurn();
        vehicleService.endTurn(clock.tick());
    }

    public boolean isComplete(Cargos cargos) {
        boolean complete = allDelivered(cargos) && vehicleService.allAtHome();
        log.debug("simulation complete {}", complete);
        return complete;
    }

    public VehicleEvents getAllEvents() {
        return eventRepository.getAll();
    }

    private boolean allDelivered(Cargos cargos) {
        return cargos.allDelivered(map.getTerminals());
    }

}
