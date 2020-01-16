package ru.epam.javacore.homework20191211.domain;

import ru.epam.javacore.homework20191211.IdGenerator;

public class BaseEntity {
    protected Long id = IdGenerator.generateId();

    public Long getId() {
        return id;
    }

}
