package com.amadeus.pau;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;


@Entity
public class Market extends PanacheEntity {
    public Market() {}

    private String name;
    private String code;
    private int pin_code;

    public Market(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPin_code() {
        return pin_code;
    }

    public void setPin_code(int pin_code) {
        this.pin_code = pin_code;
    }
}

