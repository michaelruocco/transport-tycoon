package uk.co.mruoc.transport.cargo;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.transport.location.terminal.Terminal;
import uk.co.mruoc.transport.location.terminal.Terminals;

import java.util.function.Consumer;

@RequiredArgsConstructor
public class AllocateToOrigin implements Consumer<Cargo> {

    private final Terminals terminals;

    @Override
    public void accept(Cargo cargo) {
        Terminal terminal = terminals.findByName(cargo.getOrigin());
        terminal.store(cargo);
    }

}
