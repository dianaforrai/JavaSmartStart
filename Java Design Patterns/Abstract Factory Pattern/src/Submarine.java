public class Submarine implements AbstractVehicle {
    @Override
    public void start() {
        System.out.println("Submarine is starting...");
    }

    @Override
    public void accelerate() {
        System.out.println("Submarine is accelerating...");
    }

    @Override
    public void brake() {
        System.out.println("Submarine is braking...");
    }
}
