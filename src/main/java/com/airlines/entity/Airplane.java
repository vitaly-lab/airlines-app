package com.airlines.entity;

import java.time.LocalDate;

public class Airplane {
    private final int id;
    private final String codeName;
    private Model model;
    private final LocalDate manufactureDate;
    private final int capacity;
    private final int flightRange;
    private final int crewId;

    private Airplane(Builder builder) {
        this.id = builder.id;
        this.codeName = builder.codeName;
        this.model = builder.model;
        this.manufactureDate = builder.manufactureDate;
        this.capacity = builder.capacity;
        this.flightRange = builder.flightRange;
        this.crewId = builder.crewId;
    }

    public int getId() {
        return id;
    }

    public String getCodeName() {
        return codeName;
    }

    public Model getModel() {
        return model;
    }

    public LocalDate getManufacture() {
        return manufactureDate;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFlightRange() {
        return flightRange;
    }

    public int getCrews_id() {
        return crewId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private int id;
        private String codeName;
        private Model model;
        private LocalDate manufactureDate;
        private int capacity;
        private int flightRange;
        private int crewId;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withCodeName(String codeName) {
            this.codeName = codeName;
            return this;
        }

        public Builder withModel(Model model) {
            this.model = model;
            return this;
        }

        public Builder withManufacture(LocalDate manufactureDate) {
            this.manufactureDate = manufactureDate;
            return this;
        }

        public Builder withCapacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder withFlightRange(int flightRange) {
            this.flightRange = flightRange;
            return this;
        }

        public Builder withCrewId(int crewId) {
            this.crewId = crewId;
            return this;
        }

        public Airplane build() {
            return new Airplane(this);
        }
    }
}
