package com.airlines.entity;

public class Crew {
    private final int id;
    private final String name;

    private Crew(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String name;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withName(int id) {
            this.name = name;
            return this;
        }

        public Crew build() {
            return new Crew(this);
        }
    }
}
