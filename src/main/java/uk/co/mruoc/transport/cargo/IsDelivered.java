package uk.co.mruoc.transport.cargo;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.transport.location.terminal.Terminal;
import uk.co.mruoc.transport.location.terminal.Terminals;

import java.util.function.Predicate;

@RequiredArgsConstructor
public class IsDelivered implements Predicate<Cargo> {

    private final Terminals terminals;

    @Override
    public boolean test(Cargo cargo) {
        return isDelivered(cargo);
    }

    private boolean isDelivered(Cargo cargo) {
        Terminal terminal = terminals.findByName(cargo.getDestination());
        return terminal.contains(cargo);
    }

}
