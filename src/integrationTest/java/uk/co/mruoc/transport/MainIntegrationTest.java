package uk.co.mruoc.transport;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.file.content.ContentLoader.loadContentFromClasspath;
import static uk.co.mruoc.file.content.ContentLoader.loadContentFromFileSystem;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemErr;

@Slf4j
class MainIntegrationTest {

    private static final String OUTPUT_DIRECTORY = "build/output";

    @BeforeEach
    public void setUp() {
        DirectoryDeleter.delete(OUTPUT_DIRECTORY);
    }

    @Test
    void shouldPrintUsageOptionsIfNoArgumentsProvided() throws Exception {
        String[] args = new String[]{};

        String output = tapSystemErr(() -> Main.main(args));

        assertThat(output).isEqualTo(
                String.format(
                        "[Test worker] ERROR uk.co.mruoc.transport.Main - Usage: transport-tycoon [options]%n" +
                                "  Options:%n" +
                                "  * --destinations, -d%n" +
                                "      Destination names as a single word e.g. ABB%n" +
                                "  * --filePath, -f%n" +
                                "      Path to create json output file e.g. build/output/my.json%n%n"
                )
        );
    }

    @Test
    void shouldExecuteSimulationFromMainClass() throws Exception {
        String destinations = "ABB";
        String actualFilePath = toActualFilePath(destinations);
        String[] args = new String[]{"-f", actualFilePath, "-d", destinations};

        String output = tapSystemErr(() -> Main.main(args));

        assertThat(output).endsWith(
                String.format(
                        "[Test worker] INFO uk.co.mruoc.transport.Main - last unload at PT8H%n" +
                                "[Test worker] INFO uk.co.mruoc.transport.Main - total duration PT15H%n" +
                                "[Test worker] INFO uk.co.mruoc.transport.Main - events file for destinations ABB written at build/output/ABB.json%n"
                )
        );
    }

    @Test
    void shouldProduceFileWhenExecutingSimulationFromMainClass() {
        String destinations = "AB";
        String actualFilePath = toActualFilePath(destinations);
        String[] args = new String[]{"-f", actualFilePath, "-d", destinations};

        Main.main(args);

        String expectedFilePath = toExpectedFilePath(destinations);
        assertThat(loadContentFromFileSystem(actualFilePath)).isEqualTo(loadContentFromClasspath(expectedFilePath));
    }

    private static String toActualFilePath(String scenarioName) {
        return String.format("%s/%s.json", OUTPUT_DIRECTORY, scenarioName);
    }

    private static String toExpectedFilePath(String scenarioName) {
        return String.format("expected-json/exercise-2/%s.json", scenarioName);
    }

}
