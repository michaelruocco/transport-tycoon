package uk.co.mruoc.transport;

import lombok.Builder;
import lombok.Data;
import uk.co.mruoc.transport.simulation.SimulationArguments;

@Data
@Builder
public class TestSimulationArguments implements SimulationArguments {

    private final String filePath;
    private final String destinations;

}
