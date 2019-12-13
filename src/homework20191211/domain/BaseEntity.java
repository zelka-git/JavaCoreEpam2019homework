package homework20191211.domain;

import homework20191211.IdGenerator;

public class BaseEntity {
    protected Long id = IdGenerator.generateId();

    public Long getId() {
        return id;
    }

}
