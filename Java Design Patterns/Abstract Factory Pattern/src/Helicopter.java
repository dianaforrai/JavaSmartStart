public class Helicopter implements AbstractVehicle {
    @Override
    public void start() {
        System.out.println("Helicopter is starting...");
    }

    @Override
    public void accelerate() {
        System.out.println("Helicopter is accelerating...");
    }

    @Override
    public void brake() {
        System.out.println("Helicopter is braking...");
    }
}
