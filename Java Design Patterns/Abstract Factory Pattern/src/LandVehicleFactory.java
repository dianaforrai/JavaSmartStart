public class LandVehicleFactory implements AbstractVehicleFactory {
    @Override
    public AbstractVehicle createCar() {
        return new Car();
    }

    @Override
    public AbstractVehicle createBike() {
        return new Bike();
    }

    @Override
    public AbstractVehicle createBoat() {
        return null;
    }

    @Override
    public AbstractVehicle createSubmarine() {
        return null;
    }

    @Override
    public AbstractVehicle createAirplane() {
        return null;
    }

    @Override
    public AbstractVehicle createHelicopter() {
        return null;
    }
}
