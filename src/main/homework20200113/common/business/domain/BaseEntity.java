package main.homework20200113.common.business.domain;

import java.io.Serializable;

public class BaseEntity implements Serializable {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
