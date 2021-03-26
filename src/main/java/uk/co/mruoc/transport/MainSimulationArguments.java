package uk.co.mruoc.transport;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import lombok.Getter;
import uk.co.mruoc.transport.simulation.SimulationArguments;

@Getter
public class MainSimulationArguments implements SimulationArguments {

    @Parameter(
            names = { "--filePath", "-f" },
            description = "Path to create json output file e.g. build/output/my.json",
            required = true
    )
    private String filePath;

    @Parameter(
            names = { "--destinations", "-d" },
            description = "Destination names as a single word e.g. ABB",
            required = true
    )
    private String destinations;

    public static SimulationArguments parse(String[] args) {
        SimulationArguments arguments = new MainSimulationArguments();
        JCommander commander = JCommander.newBuilder()
                .programName("transport-tycoon")
                .addObject(arguments)
                .build();
        parse(args, commander);
        return arguments;
    }

    public static void parse(String[] args, JCommander commander) {
        try {
            commander.parse(args);
        } catch (ParameterException e) {
            StringBuilder usage = new StringBuilder();
            commander.getUsageFormatter().usage(usage);
            throw new ArgumentException(usage.toString());
        }
    }

}
