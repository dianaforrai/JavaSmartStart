public class Car implements AbstractVehicle {
    @Override
    public void start() {
        System.out.println("Car is starting...");
    }

    @Override
    public void accelerate() {
        System.out.println("Car is accelerating...");
    }

    @Override
    public void brake() {
        System.out.println("Car is braking...");
    }
}

