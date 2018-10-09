package com.tomek.terra.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Terra {
    @GeneratedValue
    @Id
    private long id;

    private double temperature;

    private double humdity;

    public long getId() {
        return id;
    }

    public double getHumdity() {
        return humdity;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setHumdity(double humdity) {
        this.humdity = humdity;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
     public Terra()
     {

     }
}
