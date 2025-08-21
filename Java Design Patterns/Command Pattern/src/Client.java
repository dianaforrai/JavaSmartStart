public class Client {
    public static void main(String[] args) {
        // Create receivers
        Light livingRoomLight = new Light();
        Fan ceilingFan = new Fan();
        Thermostat thermostat = new Thermostat();

        // Create commands
        Command turnOnLight = new TurnOnLightCommand(livingRoomLight);
        Command turnOffLight = new TurnOffLightCommand(livingRoomLight);
        Command increaseFan = new IncreaseFanSpeedCommand(ceilingFan);
        Command decreaseFan = new DecreaseFanSpeedCommand(ceilingFan);
        Command setTemp = new SetTemperatureCommand(thermostat, 25);

        // Create invoker
        RemoteControlInvoker remote = new RemoteControlInvoker();

        // Set commands
        remote.setCommand(turnOnLight);
        remote.pressButton();

        remote.setCommand(increaseFan);
        remote.pressButton();

        remote.setCommand(setTemp);
        remote.pressButton();

        remote.setCommand(turnOffLight);
        remote.pressButton();

        remote.setCommand(decreaseFan);
        remote.pressButton();
    }
}
