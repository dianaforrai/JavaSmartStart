public class GreenLightState implements TrafficLightState {

    @Override
    public void transition(TrafficLight trafficLight) {
        trafficLight.setState(new YellowLightState());
    }

    @Override
    public void display() {
        System.out.println("Traffic Light is GREEN. Go!");
    }
}
