package main.homework20191211.domain;

import main.homework20191211.IdGenerator;

public class BaseEntity {
    protected Long id = IdGenerator.generateId();

    public Long getId() {
        return id;
    }

}
