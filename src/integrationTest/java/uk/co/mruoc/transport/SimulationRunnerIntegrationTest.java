package uk.co.mruoc.transport;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import uk.co.mruoc.transport.config.SimulationConfig;
import uk.co.mruoc.transport.simulation.SimulationArguments;
import uk.co.mruoc.transport.simulation.SimulationRunner;
import uk.co.mruoc.transport.vehicle.event.VehicleEvents;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.file.content.ContentLoader.loadContentFromClasspath;
import static uk.co.mruoc.file.content.ContentLoader.loadContentFromFileSystem;

@Slf4j
class SimulationRunnerIntegrationTest {

    private static final String OUTPUT_DIRECTORY = "build/output";

    @BeforeEach
    public void setUp() {
        DirectoryDeleter.delete(OUTPUT_DIRECTORY);
    }

    @ParameterizedTest(name = "should run simulation")
    @ArgumentsSource(SimulationRunnerArgumentsProvider.class)
    void shouldTakeExpectedAmountOfTimeAndProduceExpectedEvents(
            String exerciseName,
            SimulationConfig config,
            Scenario scenario) {
        log.info("executing scenario {} for {}", scenario.getDestinations(), exerciseName);
        String expectedFilePath = toExpectedFilePath(exerciseName, scenario.getDestinations());
        SimulationRunner runner = new SimulationRunner(config);
        SimulationArguments arguments = TestSimulationArguments.builder()
                .filePath(toActualFilePath(exerciseName, scenario.getDestinations()))
                .destinations(scenario.getDestinations())
                .build();

        VehicleEvents events = runner.run(arguments);

        assertThat(events.getLastUnloadTime()).isEqualTo(scenario.getExpectedLastUnloadTime());
        assertThat(events.getDuration()).isEqualTo(scenario.getExpectedDuration());
        assertThat(loadContentFromFileSystem(arguments.getFilePath())).isEqualTo(loadContentFromClasspath(expectedFilePath));
    }

    private static String toActualFilePath(String exerciseName, String scenarioName) {
        return String.format("%s/%s/%s.json", OUTPUT_DIRECTORY, exerciseName, scenarioName);
    }

    private static String toExpectedFilePath(String exerciseName, String scenarioName) {
        return String.format("expected-json/%s/%s.json",exerciseName, scenarioName);
    }

}
