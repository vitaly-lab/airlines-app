package com.airlines.entity;

import java.time.LocalDate;

public class CrewMember {
    private final int id;
    private final String firsName;
    private final String lastName;
    private final Position position;
    private final LocalDate birthday;
    private final Citizenship citizenship;

    private CrewMember(Builder builder) {
        this.id = builder.id;
        this.firsName = builder.firsName;
        this.lastName = builder.lastName;
        this.position = builder.position;
        this.birthday = builder.birthday;
        this.citizenship = builder.citizenship;
    }

    public int getId() {
        return id;
    }

    public String getFirsName() {
        return firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public Position getPosition() {
        return position;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Citizenship getCitizenship() {
        return citizenship;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String firsName;
        private String lastName;
        private Position position;
        private LocalDate birthday;
        private Citizenship citizenship;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withFirsName(String firsName) {
            this.firsName = firsName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withPosition(Position position) {
            this.position = position;
            return this;
        }

        public Builder withBirthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder withCitizenship(Citizenship citizenship) {
            this.citizenship = citizenship;
            return this;
        }

        public CrewMember build() {
            return new CrewMember(this);
        }
    }
}
