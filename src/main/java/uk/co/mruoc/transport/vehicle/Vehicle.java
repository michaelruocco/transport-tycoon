package uk.co.mruoc.transport.vehicle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.transport.cargo.Cargo;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class Vehicle {

    private final long id;
    private final Type type;
    private final Duration loadDuration;
    private final String homeTerminal;
    private final int maxCapacity;
    private final Deque<Cargo> cargos = new ArrayDeque<>();

    private Duration loadingUntil;
    private Duration unloadingUntil;

    public long getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public String getDescription() {
        return String.format("%s %d", type, id);
    }

    public String getDestination() {
        return cargos.peek().getDestination();
    }

    public Duration getLoadDuration() {
        return loadDuration;
    }

    public Collection<Cargo> getCargos() {
        return Collections.unmodifiableCollection(new ArrayList<>(cargos));
    }

    public boolean hasCapacityRemaining() {
        return cargos.size() < maxCapacity;
    }

    public void startLoading(Cargo cargo, Duration time) {
        loadingUntil = time.plus(loadDuration);
        this.cargos.add(cargo);
    }

    public boolean isLoaded() {
        return !cargos.isEmpty() && isReadyToDepart();
    }

    public void completeLoading() {
        loadingUntil = null;
    }

    public boolean isEmpty() {
        return cargos.isEmpty();
    }

    public Cargo startUnloading(Duration time) {
        unloadingUntil = time.plus(loadDuration);
        return cargos.poll();
    }

    public void completeUnloading() {
        unloadingUntil = null;
    }

    public boolean isReadyToDepart() {
        return !isLoading() && !isUnloading();
    }

    public String getHomeTerminal() {
        return homeTerminal;
    }

    public boolean isLoadingReadyToComplete(Duration time) {
        return getLoadingUntil()
                .map(until -> until.toMillis() <= time.toMillis())
                .orElse(false);
    }

    public boolean isUnloadingReadyToComplete(Duration time) {
        return getUnloadingUntil()
                .map(until -> until.toMillis() <= time.toMillis())
                .orElse(false);
    }

    public boolean isLoading() {
        return getLoadingUntil().isPresent();
    }

    public boolean isUnloading() {
        return getUnloadingUntil().isPresent();
    }

    public Optional<Duration> getLoadingUntil() {
        return Optional.ofNullable(loadingUntil);
    }

    public Optional<Duration> getUnloadingUntil() {
        return Optional.ofNullable(unloadingUntil);
    }

    public enum Type {
        TRUCK,
        SHIP
    }

}
