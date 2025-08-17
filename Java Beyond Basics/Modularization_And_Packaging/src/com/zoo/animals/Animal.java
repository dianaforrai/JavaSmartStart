package com.zoo.animals;

public abstract class Animal {
    protected String name;
    protected String species;

    public Animal(String name, String species) {
        this.name = name;
        this.species = species;
    }


    public abstract String sound();

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    @Override
    public String toString() {
        return String.format("%s the %s", name, species);
    }
}


