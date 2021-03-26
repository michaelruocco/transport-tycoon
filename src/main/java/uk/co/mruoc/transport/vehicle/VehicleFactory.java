package uk.co.mruoc.transport.vehicle;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.transport.IdGenerator;
import uk.co.mruoc.transport.location.terminal.TerminalNames;

import java.util.function.LongSupplier;

@RequiredArgsConstructor
public class VehicleFactory {

    private final LongSupplier idSupplier;

    public VehicleFactory() {
        this(new IdGenerator());
    }

    public Vehicle truck() {
        return new Truck(idSupplier.getAsLong(), TerminalNames.FACTORY);
    }

    public Vehicle ship() {
        return new Ship(idSupplier.getAsLong(), TerminalNames.PORT);
    }

    public Vehicle largeShip() {
        return new LargeShip(idSupplier.getAsLong(), TerminalNames.PORT);
    }

}
