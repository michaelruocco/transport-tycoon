package uk.co.mruoc.transport;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import uk.co.mruoc.transport.config.Exercise1Config;
import uk.co.mruoc.transport.config.Exercise2Config;

import java.util.stream.Stream;

import static java.time.Duration.ofHours;

public class SimulationRunnerArgumentsProvider implements ArgumentsProvider {

    private static final String EXERCISE_1 = "exercise-1";
    private static final String EXERCISE_2 = "exercise-2";

    private static final String A = "A";
    private static final String AB = "AB";
    private static final String BB = "BB";
    private static final String ABB = "ABB";
    private static final String AABABBAB = "AABABBAB";
    private static final String ABBBABAAABBB = "ABBBABAAABBB";

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(EXERCISE_1, new Exercise1Config(), new Scenario(A, ofHours(5), ofHours(9))),
                Arguments.of(EXERCISE_1, new Exercise1Config(), new Scenario(AB, ofHours(5), ofHours(10))),
                Arguments.of(EXERCISE_1, new Exercise1Config(), new Scenario(BB, ofHours(5), ofHours(10))),
                Arguments.of(EXERCISE_1, new Exercise1Config(), new Scenario(ABB, ofHours(7), ofHours(12))),
                Arguments.of(EXERCISE_1, new Exercise1Config(), new Scenario(AABABBAB, ofHours(29), ofHours(33))),
                Arguments.of(EXERCISE_1, new Exercise1Config(), new Scenario(ABBBABAAABBB, ofHours(41), ofHours(45))),

                Arguments.of(EXERCISE_2, new Exercise2Config(), new Scenario(A, ofHours(8), ofHours(15))),
                Arguments.of(EXERCISE_2, new Exercise2Config(), new Scenario(AB, ofHours(8), ofHours(15))),
                Arguments.of(EXERCISE_2, new Exercise2Config(), new Scenario(BB, ofHours(5), ofHours(10))),
                Arguments.of(EXERCISE_2, new Exercise2Config(), new Scenario(ABB, ofHours(8), ofHours(15))),
                Arguments.of(EXERCISE_2, new Exercise2Config(), new Scenario(AABABBAB, ofHours(22), ofHours(29))),
                Arguments.of(EXERCISE_2, new Exercise2Config(), new Scenario(ABBBABAAABBB, ofHours(39), ofHours(44)))
        );
    }

}
