public class WeatherObserver implements Observer {
    private String observerName;

    public WeatherObserver(String observerName) {
        this.observerName = observerName;
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        System.out.println(observerName + " received weather update: " +
                "Temperature = " + temperature + "°C, " +
                "Humidity = " + humidity + "%, " +
                "Pressure = " + pressure + " hPa");
    }
}
