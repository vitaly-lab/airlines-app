package com.airlines.entity;

import java.time.LocalDate;

public class Airplane {
    private final int id;
    private final String codeName;
    private final String model;
    private final LocalDate manufacture;
    private final int capacity;
    private final int flightRange;
    private final int crews_id;

    private Airplane(int id, String codeName, String model, LocalDate manufacture, int capacity, int flightRange, int crews_id) {
        this.id = id;
        this.codeName = codeName;
        this.model = model;
        this.manufacture = manufacture;
        this.capacity = capacity;
        this.flightRange = flightRange;
        this.crews_id = crews_id;
    }

    public int getId() {
        return id;
    }

    public String getCodeName() {
        return codeName;
    }

    public String getModel() {
        return model;
    }

    public LocalDate getManufacture() {
        return manufacture;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFlightRange() {
        return flightRange;
    }

    public int getCrews_id() {
        return crews_id;
    }

    public static class Builder {

        private int id;
        private String codeName;
        private String model;
        private LocalDate manufacture;
        private int capacity;
        private int flightRange;
        private int crews_id;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withCodeName(String codeName) {
            this.codeName = codeName;
            return this;
        }

        public Builder withModel(LocalDate manufacture) {
            this.manufacture = manufacture;
            return this;
        }

        public Builder withCapacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder withFlightRange(int crews_id) {
            this.crews_id = crews_id;
            return this;
        }
    }
}
