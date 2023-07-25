package com.test.randomuser.data.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class RandomPerson {
    public String gender;
    public Name name;
    public Location location;
    public String email;
    public Registered registered;
    public String phone;
    public String cell;
    public Id id;
    public Picture picture;


    public RandomPerson() {
    }

    public RandomPerson(String gender, Name name, Location location, String email, Registered registered, String phone, String cell, Id id, Picture picture) {
        this.gender = gender;
        this.name = name;
        this.location = location;
        this.email = email;
        this.registered = registered;
        this.phone = phone;
        this.cell = cell;
        this.id = id;
        this.picture = picture;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Registered getRegistered() {
        return registered;
    }

    public void setRegistered(Registered registered) {
        this.registered = registered;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getFullName() {
        return this.name.title + " " + this.name.getFirst() + " " + this.name.last;
    }

    public static DiffUtil.ItemCallback<RandomPerson> DIFF_CALLBACK = new DiffUtil.ItemCallback<RandomPerson>() {
        @Override
        public boolean areItemsTheSame(@NonNull RandomPerson oldItem, @NonNull RandomPerson newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull RandomPerson oldItem, @NonNull RandomPerson newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        RandomPerson result = (RandomPerson) obj;
        return result.id == this.id;
    }
}
