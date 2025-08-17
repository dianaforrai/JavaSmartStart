package com.zoo.animals;

public class Tiger extends Animal {
    public Tiger(String name) {
        super(name, "Tiger");
    }

    @Override
    public String sound() {
        return "Growl!";
    }
}