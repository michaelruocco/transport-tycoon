package uk.co.mruoc.transport.cargo;

import uk.co.mruoc.transport.location.terminal.TerminalNames;

public interface CargoMother {

    static Cargo withOrigin(String origin) {
        return builder().origin(origin).build();
    }

    static Cargo.CargoBuilder builder() {
        return Cargo.builder()
                .id(1)
                .origin(TerminalNames.WAREHOUSE_A)
                .destination(TerminalNames.WAREHOUSE_B);
    }

}
