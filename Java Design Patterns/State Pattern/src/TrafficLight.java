public class TrafficLight {
    private TrafficLightState currentState;

    public TrafficLight() {
        currentState = new RedLightState(); // Initial state
    }

    public void setState(TrafficLightState state) {
        this.currentState = state;
    }

    public TrafficLightState getState() {
        return currentState;
    }

    public void performStateTransition() {
        currentState.transition(this);
    }

    public void displayCurrentState() {
        currentState.display();
    }
}
