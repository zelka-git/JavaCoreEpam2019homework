package ru.epam.javacore.homework20200124.storage;

import java.util.concurrent.atomic.AtomicLong;

public final class IdGenerator {

    private IdGenerator() {
    }

    private static AtomicLong id = new AtomicLong(0);

    public static Long generateId() {
        return id.incrementAndGet();
    }
}
