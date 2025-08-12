package com.gl.app.model;

import java.util.List;

class Box<T> {
    private T item;

    public Box(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    // Method to inspect if the passed object is of the same type as the item in the box
    public boolean inspect(Object obj) {
        if (item == null || obj == null) {
            return false;
        }
        // Compare the runtime classes of both objects
        return item.getClass().equals(obj.getClass());
    }
}

class Shipment<T> {
    private List<Box<?>> boxes;

    public Shipment(List<Box<?>> boxes) {
        this.boxes = boxes;
    }

    // Check if any box can hold an object of the same type as obj
    public boolean inspectShipment(Object obj) {
        if (obj == null) {
            return false;
        }
        for (Box<?> box : boxes) {
            if (box.inspect(obj)) {
                return true;
            }
        }
        return false;
    }
}
