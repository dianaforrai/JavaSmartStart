public class Car {
    // Car properties
    private final String brand;
    private final String model;
    private final String color;
    private final String engine;
    private final String transmission;
    private final String fuelType;

    // Private constructor
    private Car(CarBuilder builder) {
        this.brand = builder.brand;
        this.model = builder.model;
        this.color = builder.color;
        this.engine = builder.engine;
        this.transmission = builder.transmission;
        this.fuelType = builder.fuelType;
    }

    // Display car details
    public void showCarDetails() {
        System.out.println("Car Details: ");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Color: " + color);
        System.out.println("Engine: " + engine);
        System.out.println("Transmission: " + transmission);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("---------------------------");
    }

    // Static inner Builder class
    public static class CarBuilder {
        private String brand;
        private String model;
        private String color;
        private String engine;
        private String transmission;
        private String fuelType;

        public CarBuilder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public CarBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public CarBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public CarBuilder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public CarBuilder setTransmission(String transmission) {
            this.transmission = transmission;
            return this;
        }

        public CarBuilder setFuelType(String fuelType) {
            this.fuelType = fuelType;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}
