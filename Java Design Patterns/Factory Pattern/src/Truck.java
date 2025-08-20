public class Truck implements Vehicle {
    @Override
    public void start() {
        System.out.println("Truck is starting...");
    }

    @Override
    public void accelerate() {
        System.out.println("Truck is accelerating...");
    }

    @Override
    public void brake() {
        System.out.println("Truck is braking...");
    }
}
