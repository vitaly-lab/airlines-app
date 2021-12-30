package com.airlines.entity;

public class Crew {
    private final int id;
    private final String name;

    private Crew(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
    }
}
