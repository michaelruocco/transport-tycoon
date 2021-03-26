package uk.co.mruoc.transport.location.terminal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.transport.cargo.Cargo;
import uk.co.mruoc.transport.location.Location;

import java.util.Deque;
import java.util.Optional;
import java.util.concurrent.LinkedBlockingDeque;

@Slf4j
@RequiredArgsConstructor
public class Terminal implements Location {

    @Getter
    private final String name;
    private final Deque<Cargo> cargos = new LinkedBlockingDeque<>();

    public boolean contains(Cargo cargo) {
        return cargos.contains(cargo);
    }

    public void clearCargos() {
        cargos.clear();
    }

    public void store(Cargo cargo) {
        log.info("storing cargo {} at {}", cargo.getId(), name);
        cargos.add(cargo);
    }

    public boolean hasCargo() {
        return !cargos.isEmpty();
    }

    public Cargo getNextCargo() {
        return Optional.ofNullable(cargos.poll()).orElseThrow(() -> new NoCargoRemainingException(name));
    }

}
