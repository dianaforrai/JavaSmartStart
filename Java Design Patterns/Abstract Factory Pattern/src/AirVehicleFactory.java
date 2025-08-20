public class AirVehicleFactory implements AbstractVehicleFactory {
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
        return null;
    }

    @Override
    public AbstractVehicle createSubmarine() {
        return null;
    }

    @Override
    public AbstractVehicle createAirplane() {
        return new Airplane();
    }

    @Override
    public AbstractVehicle createHelicopter() {
        return new Helicopter();
    }
}
