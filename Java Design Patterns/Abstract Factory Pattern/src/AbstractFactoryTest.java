import java.util.Scanner;

public class AbstractFactoryTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose platform: land, water, air");
        String platform = scanner.nextLine().toLowerCase();

        AbstractVehicleFactory factory;

        switch (platform) {
            case "land":
                factory = new LandVehicleFactory();
                System.out.println("Choose vehicle: car or bike");
                String landChoice = scanner.nextLine().toLowerCase();
                if ("car".equals(landChoice)) {
                    AbstractVehicle car = factory.createCar();
                    car.start();
                    car.accelerate();
                    car.brake();
                } else if ("bike".equals(landChoice)) {
                    AbstractVehicle bike = factory.createBike();
                    bike.start();
                    bike.accelerate();
                    bike.brake();
                }
                break;

            case "water":
                factory = new WaterVehicleFactory();
                System.out.println("Choose vehicle: boat or submarine");
                String waterChoice = scanner.nextLine().toLowerCase();
                if ("boat".equals(waterChoice)) {
                    AbstractVehicle boat = factory.createBoat();
                    boat.start();
                    boat.accelerate();
                    boat.brake();
                } else if ("submarine".equals(waterChoice)) {
                    AbstractVehicle sub = factory.createSubmarine();
                    sub.start();
                    sub.accelerate();
                    sub.brake();
                }
                break;

            case "air":
                factory = new AirVehicleFactory();
                System.out.println("Choose vehicle: airplane or helicopter");
                String airChoice = scanner.nextLine().toLowerCase();
                if ("airplane".equals(airChoice)) {
                    AbstractVehicle airplane = factory.createAirplane();
                    airplane.start();
                    airplane.accelerate();
                    airplane.brake();
                } else if ("helicopter".equals(airChoice)) {
                    AbstractVehicle heli = factory.createHelicopter();
                    heli.start();
                    heli.accelerate();
                    heli.brake();
                }
                break;

            default:
                System.out.println("Invalid platform.");
        }

        scanner.close();
    }
}
