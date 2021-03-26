package uk.co.mruoc.transport.cargo;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import uk.co.mruoc.transport.location.terminal.Terminal;
import uk.co.mruoc.transport.location.terminal.Terminals;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AllocateToOriginTest {

    private final Terminals terminals = mock(Terminals.class);

    private final AllocateToOrigin allocate = new AllocateToOrigin(terminals);

    @Test
    void shouldLookupOriginTerminalAndAllocateCargo() {
        String origin = "origin-terminal";
        Terminal terminal = givenTerminalWithName(origin);
        Cargo cargo = CargoMother.withOrigin(origin);

        allocate.accept(cargo);

        ArgumentCaptor<Cargo> storedCargo = ArgumentCaptor.forClass(Cargo.class);
        verify(terminal).store(storedCargo.capture());
        assertThat(storedCargo.getValue()).isEqualTo(cargo);
    }

    private Terminal givenTerminalWithName(String name) {
        Terminal terminal = mock(Terminal.class);
        given(terminals.findByName(name)).willReturn(terminal);
        return terminal;
    }


}
