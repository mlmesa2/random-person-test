package com.test.randomuser.data.model;

public class Location{
        public Street street;
        public String city;
        public String state;
        public String country;
        public Coordinates coordinates;
        public Timezone timezone;

        public Location(Street street, String city, String state, String country, Coordinates coordinates, Timezone timezone) {
                this.street = street;
                this.city = city;
                this.state = state;
                this.country = country;
                this.coordinates = coordinates;
                this.timezone = timezone;
        }

        public Location() {
        }

        public Street getStreet() {
                return street;
        }

        public void setStreet(Street street) {
                this.street = street;
        }

        public String getCity() {
                return city;
        }

        public void setCity(String city) {
                this.city = city;
        }

        public String getState() {
                return state;
        }

        public void setState(String state) {
                this.state = state;
        }

        public String getCountry() {
                return country;
        }

        public void setCountry(String country) {
                this.country = country;
        }

        public Coordinates getCoordinates() {
                return coordinates;
        }

        public void setCoordinates(Coordinates coordinates) {
                this.coordinates = coordinates;
        }

        public Timezone getTimezone() {
                return timezone;
        }

        public void setTimezone(Timezone timezone) {
                this.timezone = timezone;
        }
}