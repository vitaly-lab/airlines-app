package com.airlines.entity;

import java.time.LocalDate;

public class CrewMember {
    private final int id;
    private final String firsName;
    private final String lastName;
    private final String position;
    private final LocalDate birthday;
    private final String citizenship;

    private CrewMember(int id, String firsName, String lastName, String position, LocalDate birthday, String citizenship) {
        this.id = id;
        this.firsName = firsName;
        this.lastName = lastName;
        this.position = position;
        this.birthday = birthday;
        this.citizenship = citizenship;
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

    public String getPosition() {
        return position;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public static class Builder {
        private int id;
        private String firsName;
        private String lastName;
        private String position;
        private LocalDate birthday;
        private String citizenship;

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

        public Builder withPosition(String position) {
            this.position = position;
            return this;
        }

        public Builder withBirthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder withCitizenship(String citizenship) {
            this.citizenship = citizenship;
            return this;
        }
    }
}
