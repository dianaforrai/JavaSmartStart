public class Client {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();

        WeatherObserver observer1 = new WeatherObserver("Alice");
        WeatherObserver observer2 = new WeatherObserver("Bob");

        station.addObserver(observer1);
        station.addObserver(observer2);

        station.setState(25.5f, 65.0f, 1012.0f);
        station.setState(28.0f, 70.0f, 1010.5f);

        // Removing an observer
        station.removeObserver(observer1);

        station.setState(30.0f, 60.0f, 1008.0f);
    }
}
