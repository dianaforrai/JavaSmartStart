public class VehicleFactory {

    // Factory method to create Vehicle objects
    public static Vehicle getVehicle(String type) {
        if (type == null) {
            return null;
        }
        switch (type.toLowerCase()) {
            case "car":
                return new Car();
            case "bike":
                return new Bike();
            case "truck":
                return new Truck();
            default:
                System.out.println("Invalid vehicle type.");
                return null;
        }
    }
}
