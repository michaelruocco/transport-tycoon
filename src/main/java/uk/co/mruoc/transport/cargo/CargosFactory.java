package uk.co.mruoc.transport.cargo;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.transport.IdGenerator;
import uk.co.mruoc.transport.location.terminal.TerminalNames;

import java.util.Collection;
import java.util.function.LongSupplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class CargosFactory {

    private final String origin;
    private final LongSupplier idSupplier;

    public CargosFactory() {
        this(TerminalNames.FACTORY);
    }

    public CargosFactory(String origin) {
        this(origin, new IdGenerator());
    }

    public Cargos build(String name) {
        return build(Stream.of(name.split(""))
                .map(String::valueOf)
                .collect(Collectors.toList())
        );
    }

    public Cargos build(Collection<String> destinations) {
        return new Cargos(destinations.stream()
                .map(this::toCargo)
                .collect(Collectors.toList())
        );
    }

    private Cargo toCargo(String destination) {
        return Cargo.builder()
                .id(idSupplier.getAsLong())
                .origin(origin)
                .destination(destination)
                .build();
    }

}
