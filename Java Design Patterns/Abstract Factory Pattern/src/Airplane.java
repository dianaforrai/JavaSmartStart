public class Airplane implements AbstractVehicle {
    @Override
    public void start() {
        System.out.println("Airplane is starting...");
    }

    @Override
    public void accelerate() {
        System.out.println("Airplane is accelerating...");
    }

    @Override
    public void brake() {
        System.out.println("Airplane is braking...");
    }
}

