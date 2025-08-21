public class Thermostat {
    private int temperature = 22; // default temp

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println("Thermostat set to " + temperature + "°C");
    }
}
