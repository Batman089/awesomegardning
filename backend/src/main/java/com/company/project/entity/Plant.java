package com.company.project.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PLANTS")
public class Plant {

    @Id
    private int id;
    private String name;

    public Plant() {
    }

    public Plant(String name) {
        this.name = name;
    }

    public Plant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plant plant = (Plant) o;

        return name.equals(plant.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
