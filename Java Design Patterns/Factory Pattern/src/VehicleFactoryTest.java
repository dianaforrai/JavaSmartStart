import java.util.Scanner;

public class VehicleFactoryTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a vehicle type: car, bike, truck");
        String choice = scanner.nextLine();

        Vehicle vehicle = VehicleFactory.getVehicle(choice);

        if (vehicle != null) {
            vehicle.start();
            vehicle.accelerate();
            vehicle.brake();
        }

        scanner.close();
    }
}
