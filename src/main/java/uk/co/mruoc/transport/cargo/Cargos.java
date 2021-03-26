package uk.co.mruoc.transport.cargo;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.transport.location.terminal.Terminals;

import java.util.Collection;

@RequiredArgsConstructor
public class Cargos {

    private final Collection<Cargo> values;

    public void allocateToOrigins(Terminals terminals) {
        values.forEach(new AllocateToOrigin(terminals));
    }

    public boolean allDelivered(Terminals terminals) {
        return values.stream().allMatch(new IsDelivered(terminals));
    }

}
