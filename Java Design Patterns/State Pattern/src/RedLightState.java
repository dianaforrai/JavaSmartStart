public class RedLightState implements TrafficLightState {

    @Override
    public void transition(TrafficLight trafficLight) {
        trafficLight.setState(new GreenLightState());
    }

    @Override
    public void display() {
        System.out.println("Traffic Light is RED. Stop!");
    }
}
