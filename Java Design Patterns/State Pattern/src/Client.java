public class Client {
    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();

        // Simulate traffic light transitions
        for (int i = 0; i < 6; i++) {
            trafficLight.displayCurrentState();
            trafficLight.performStateTransition();
            System.out.println("--------------------");
        }
    }
}
