package uk.co.mruoc.transport;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongSupplier;

@RequiredArgsConstructor
public class IdGenerator implements LongSupplier {

    private final AtomicLong nextId;

    public IdGenerator() {
        this(new AtomicLong());
    }

    @Override
    public long getAsLong() {
        return nextId.getAndIncrement();
    }

}
