public class BuilderPatternDemo {
    public static void main(String[] args) {
        // Building a sports car
        Car sportsCar = new Car.CarBuilder()
                .setBrand("Ferrari")
                .setModel("488 Spider")
                .setColor("Red")
                .setEngine("V8")
                .setTransmission("Automatic")
                .setFuelType("Petrol")
                .build();

        sportsCar.showCarDetails();

        // Building a family car with fewer options
        Car familyCar = new Car.CarBuilder()
                .setBrand("Toyota")
                .setModel("Corolla")
                .setColor("Blue")
                .setTransmission("Manual")
                .build();

        familyCar.showCarDetails();
    }
}
