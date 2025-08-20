public class WaterVehicleFactory implements AbstractVehicleFactory {
    @Override
    public AbstractVehicle createCar() {
        return null;
    }

    @Override
    public AbstractVehicle createBike() {
        return null;
    }

    @Override
    public AbstractVehicle createBoat() {
        return new Boat();
    }

    @Override
    public AbstractVehicle createSubmarine() {
        return new Submarine();
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
