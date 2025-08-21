public class YellowLightState implements TrafficLightState {

    @Override
    public void transition(TrafficLight trafficLight) {
        trafficLight.setState(new RedLightState());
    }

    @Override
    public void display() {
        System.out.println("Traffic Light is YELLOW. Caution!");
    }
}
