package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode
@Data
@AllArgsConstructor

public abstract class Base {
    protected Long id;
    protected Status status;

    public Base(){
        this.status = Status.ACTIVE;
    }
}
